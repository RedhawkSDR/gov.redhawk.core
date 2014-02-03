/**
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.ui.views.namebrowser.view;

import gov.redhawk.sca.util.CorbaURIUtil;
import gov.redhawk.sca.util.Debug;
import gov.redhawk.sca.util.OrbSession;
import gov.redhawk.ui.views.namebrowser.NameBrowserPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import mil.jpeojtrs.sca.util.ProtectedThreadExecutor;

import org.eclipse.core.runtime.Assert;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.omg.CORBA.ORB;
import org.omg.CORBA.SystemException;
import org.omg.CORBA.UserException;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.Binding;
import org.omg.CosNaming.BindingIteratorHolder;
import org.omg.CosNaming.BindingListHolder;
import org.omg.CosNaming.BindingType;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

/**
 * @since 1.1
 */
public class BindingNode implements IPropertySource {
	public static enum Type {
		ROOT(null), CONTEXT(BindingType.ncontext), OBJECT(BindingType.nobject), UNKNOWN(null);
		private BindingType type;

		Type(final BindingType type) {
			this.type = type;
		}

		public BindingType bindingType() {
			return this.type;
		}

		public static Type fromBindingType(final BindingType type) {
			switch (type.value()) {
			case BindingType._ncontext:
				return CONTEXT;
			case BindingType._nobject:
				return OBJECT;
			default:
				return null;
			}
		}
	}

	private static final Debug DEBUG = new Debug(NameBrowserPlugin.PLUGIN_ID, "contentprovider");

	private static final String HOST_ID = "binding.host";
	private static final ReadOnlyTextPropertyDescriptor HOST_PROPERTY_DESCRIPTOR = new ReadOnlyTextPropertyDescriptor(BindingNode.HOST_ID, "Host");

	private static final String IOR_ID = "binding.ior";
	private static final ReadOnlyTextPropertyDescriptor IOR_PROPERTY_DESCRIPTOR = new ReadOnlyTextPropertyDescriptor(BindingNode.IOR_ID, "IOR");

	private static final String NAME_ID = "binding.name";
	private static final ReadOnlyTextPropertyDescriptor NAME_PROPERTY_DESCRIPTOR = new ReadOnlyTextPropertyDescriptor(BindingNode.NAME_ID, "Name");

	private static final String PATH_ID = "binding.path";
	private static final ReadOnlyTextPropertyDescriptor PATH_PROPERTY_DESCRIPTOR = new ReadOnlyTextPropertyDescriptor(BindingNode.PATH_ID, "Path");

	private static final IPropertyDescriptor[] DESCRIPTORS = { BindingNode.NAME_PROPERTY_DESCRIPTOR, BindingNode.HOST_PROPERTY_DESCRIPTOR,
		BindingNode.PATH_PROPERTY_DESCRIPTOR, BindingNode.IOR_PROPERTY_DESCRIPTOR };

	private static final Binding[] EMPTY = new Binding[0];
	private static final BindingNode[] NO_CONTENT = new BindingNode[0];
	private Map<String, Boolean> knownRepIds = new HashMap<String, Boolean>();

	private static class SessionInfo {
		private final String host;
		private NamingContextExt namingContext;
		private OrbSession session;

		private SessionInfo(final String host) {
			this.host = host;
		}
	}

	private final Binding binding;
	private final BindingNode parent;
	private final String ior;
	private BindingNode[] contents = null;
	private final SessionInfo info;

	public BindingNode(final BindingNode parent, final Binding binding, final String ior) {
		this.parent = parent;
		this.binding = binding;
		this.ior = ior;
		Assert.isNotNull(this.ior);
		Assert.isNotNull(parent.info);
		this.info = parent.info;
	}

	public BindingNode(final String host) {
		this.info = new SessionInfo(host);
		this.parent = null;
		this.binding = null;
		this.ior = "";
	}

	public NamingContextExt getNamingContext() {
		return this.info.namingContext;
	}

	/**
	 * @since 1.2
	 */
	public boolean is_a(final String repID) {
		Boolean retVal = knownRepIds.get(repID);
		if (retVal == null) {
			if (getType() == BindingNode.Type.OBJECT) {
				final NamingContextExt rootContext = getNamingContext();
				try {
					retVal = ProtectedThreadExecutor.submit(new Callable<Boolean>() {

						@Override
						public Boolean call() throws Exception {
							final String path = getPath();
							final org.omg.CORBA.Object obj = rootContext.resolve_str(path);
							return obj._is_a(repID);
						}
					});
				} catch (InterruptedException e) {
					retVal = false;
				} catch (ExecutionException e) {
					retVal = false;
				} catch (TimeoutException e) {
					retVal = false;
				}

			} else {
				retVal = false;
			}
			knownRepIds.put(repID, retVal);
		}
		return retVal;

	}

	public void connect() throws InvalidName {
		// Create the CORBA ORB, overriding the Java ORB's default port if
		// necessary
		if (BindingNode.DEBUG.enabled) {
			BindingNode.DEBUG.enteringMethod();
		}

		final String nameRef = CorbaURIUtil.addDefaultPort(this.info.host);
		this.info.session = OrbSession.createSession();

		// Lookup the NameService
		this.info.namingContext = NamingContextExtHelper.narrow(this.info.session.getOrb().string_to_object(nameRef));
		this.contents = null;
		if (BindingNode.DEBUG.enabled) {
			BindingNode.DEBUG.exitingMethod();
		}
	}

	public void dispose() {
		if (this.info.session != null) {
			this.info.session.dispose();
			this.info.session = null;
		}
		if (this.info.namingContext != null) {
			this.info.namingContext._release();
			this.info.namingContext = null;
		}
	}

	public ORB getOrb() {
		return this.info.session.getOrb();
	}

	public BindingNode getRoot() {
		if (this.parent == null) {
			return this;
		}
		return this.parent.getRoot();
	}

	public BindingNode getParent() {
		return this.parent;
	}

	@Override
	public String toString() {
		if (this.binding != null) {
			return this.binding.binding_name[0].id;
		} else {
			return this.getHost();
		}
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof BindingNode) {
			return this.ior.equals(((BindingNode) obj).ior);
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return this.ior.hashCode();
	}

	public Binding getBinding() {
		return this.binding;
	}

	public String getHost() {
		return this.info.host;
	}

	public String getPath() {
		return this.getPathToNode();
	}

	@Override
	public Object getEditableValue() {
		return this;
	}

	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return BindingNode.DESCRIPTORS;
	}

	@Override
	public Object getPropertyValue(final Object id) {
		if (BindingNode.PATH_ID.equals(id)) {
			return this.getPath();
		} else if (BindingNode.NAME_ID.equals(id)) {
			return this.toString();
		} else if (BindingNode.HOST_ID.equals(id)) {
			return this.info.host;
		} else if (BindingNode.IOR_ID.equals(id)) {
			return this.ior;
		}
		return null;
	}

	@Override
	public boolean isPropertySet(final Object id) {
		return true;
	}

	@Override
	public void resetPropertyValue(final Object id) {
		// PASS
	}

	@Override
	public void setPropertyValue(final Object id, final Object value) {
		// PASS
	}

	public boolean hasContents() {
		if (this.parent == null) {
			return true;
		}
		if (this.binding == null) {
			return false;
		}
		if (this.contents != null) {
			return this.contents.length > 0;
		}
		return this.binding.binding_type == BindingType.ncontext;
	}

	public BindingNode[] getContents() {
		return this.contents;
	}

	public BindingNode[] fetchContents() {
		if (BindingNode.DEBUG.enabled) {
			BindingNode.DEBUG.enteringMethod();
		}
		// If the type is ncontext, it has children, otherwise none
		// We only need to do something to a node that has children
		if (hasContents()) {
			final String str = getPathToNode();

			final Binding[] children = getChildren(str);

			final List<BindingNode> retVal = new ArrayList<BindingNode>();
			// Loop through the resolved names and add a node for each
			for (final Binding b : children) {
				String childIor = null;
				org.omg.CORBA.Object objRef = null;
				try {
					String ref = str;
					if (ref.length() > 0) {
						ref = ref + "/";
					}
					ref = ref + b.binding_name[0].id;
					objRef = getNamingContext().resolve_str(ref);
					childIor = objRef.toString();
					retVal.add(new BindingNode(this, b, childIor));
				} catch (final UserException e) {
					continue;
				} catch (final SystemException e) {
					continue;
				} finally {
					if (objRef != null) {
						objRef._release();
						objRef = null;
					}
				}
			}
			this.contents = retVal.toArray(new BindingNode[retVal.size()]);
		} else {
			this.contents = BindingNode.NO_CONTENT;
		}
		if (BindingNode.DEBUG.enabled) {
			BindingNode.DEBUG.exitingMethod(Arrays.asList(this.contents));
		}
		return this.contents;
	}

	/**
	 * This creates the String form of the full path to the node.
	 * 
	 * @param bindingNode the node to locate
	 * @return String path of the node
	 */
	private String getPathToNode() {
		StringBuilder str = new StringBuilder();
		if (this.parent != null) {
			str.append(this.parent.getPathToNode());
		}

		// Create the full path to the name of the object
		if (this.binding != null) {
			if (this.binding.binding_name.length > 0 && str.length() > 0) {
				str.append("/");
			}
			for (int i = 0; i < this.binding.binding_name.length; i++) {
				final NameComponent name = this.binding.binding_name[i];
				str.append(name.id);
				if (i + 1 < this.binding.binding_name.length) {
					str.append("/");
				}
			}

		}
		if (BindingNode.DEBUG.enabled) {
			BindingNode.DEBUG.exitingMethod(str);
		}
		return str.toString();
	}

	private Binding[] getChildren(final String pathToNode) {
		Binding[] retVal = BindingNode.EMPTY;

		try {
			final BindingListHolder bl = new BindingListHolder();
			if (this.parent == null) {
				// Get a listing of root names
				getNamingContext().list(-1, bl, new BindingIteratorHolder());
			} else {
				// Lookup the name in the NameService
				org.omg.CORBA.Object obj;
				obj = getNamingContext().resolve_str(pathToNode);
				final NamingContextExt nObj = NamingContextExtHelper.narrow(obj);

				// Get a list of objects at this name/path
				final BindingIteratorHolder bi = new BindingIteratorHolder();
				nObj.list(-1, bl, bi);
			}
			retVal = bl.value;
		} catch (final UserException e) {
			// PASS
		} catch (final SystemException e) {
			// PASS
		}

		if (BindingNode.DEBUG.enabled) {
			BindingNode.DEBUG.exitingMethod(Arrays.asList(retVal));
		}
		return retVal;
	}

	public void clearContents() {
		this.contents = null;
	}

	public Type getType() {
		if (getParent() == null || this.binding == null) {
			return Type.ROOT;
		} else {
			return Type.fromBindingType(this.binding.binding_type);
		}
	}

}

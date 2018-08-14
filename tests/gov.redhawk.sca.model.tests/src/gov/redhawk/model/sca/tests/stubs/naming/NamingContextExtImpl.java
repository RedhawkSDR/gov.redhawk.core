/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.model.sca.tests.stubs.naming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.jacorb.naming.Name;
import org.omg.CORBA.Object;
import org.omg.CosNaming.Binding;
import org.omg.CosNaming.BindingHolder;
import org.omg.CosNaming.BindingIteratorHelper;
import org.omg.CosNaming.BindingIteratorHolder;
import org.omg.CosNaming.BindingIteratorPOA;
import org.omg.CosNaming.BindingListHolder;
import org.omg.CosNaming.BindingType;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextExtPOA;
import org.omg.CosNaming.NamingContextExtPackage.InvalidAddress;
import org.omg.CosNaming.NamingContextPackage.AlreadyBound;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotEmpty;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.CosNaming.NamingContextPackage.NotFoundReason;
import org.omg.PortableServer.POAPackage.ObjectNotActive;
import org.omg.PortableServer.POAPackage.ServantAlreadyActive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongAdapter;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import gov.redhawk.model.sca.tests.TestEnvirornment;
import gov.redhawk.sca.util.OrbSession;

/**
 * An implementation of {@link NamingContextExt}.
 * <br/>
 * In our implementation, we keep everything local. We don't chain CORBA as we work through the elements of a
 * {@link NameComponent}[]. This also means we don't implement the calls that allow binding outside naming contexts,
 * or even creating them with this instance and then binding them later.
 */
public class NamingContextExtImpl extends NamingContextExtPOA {

	private OrbSession session;
	private NameComponent[] myContextPath = new NameComponent[0];
	private NamingContextExt self;
	private Map<NameComponentWrapper, org.omg.CORBA.Object> nameToObject = new HashMap<>();
	private Map<NameComponentWrapper, NamingContextExtImpl> nameToChildContext = new HashMap<>();

	/**
	 * Create a root naming context
	 * @param session
	 * @throws CoreException
	 */
	public NamingContextExtImpl(OrbSession session) throws CoreException {
		this.session = session;
		try {
			byte[] oid = session.getPOA().activate_object(this);
			self = NamingContextExtHelper.unchecked_narrow(session.getPOA().id_to_reference(oid));
		} catch (ServantAlreadyActive | WrongPolicy | ObjectNotActive e) {
			throw new CoreException(new Status(IStatus.ERROR, "gov.redhawk.sca.model.tests", "Unable to active naming context", e));
		}
	}

	/**
	 * Used internally to track the path of this naming context back to the root. This is used purely for debugging
	 * purposes.
	 * @param session
	 * @param myContextPath
	 * @throws CoreException
	 */
	protected NamingContextExtImpl(OrbSession session, NameComponent[] myContextPath) throws CoreException {
		this(session);
		this.myContextPath = myContextPath;
	}

	@Override
	public String to_string(NameComponent[] n) throws InvalidName {
		return Name.toString(n);
	}

	@Override
	public NameComponent[] to_name(String sn) throws InvalidName {
		return Name.toName(sn);
	}

	@Override
	public String to_url(String addr, String sn) throws InvalidAddress, InvalidName {
		return null;
	}

	@Override
	public Object resolve_str(String sn) throws NotFound, CannotProceed, InvalidName {
		return resolve(to_name(sn));
	}

	@Override
	public void bind(NameComponent[] n, Object obj) throws NotFound, CannotProceed, InvalidName, AlreadyBound {
		validateActive();
		validateName(n);

		if (n.length != 1) {
			getParent(n).bind(endOf(n), obj);
		} else {
			NameComponentWrapper wrappedName = new NameComponentWrapper(n[0]);
			org.omg.CORBA.Object oldObj = nameToObject.get(wrappedName);
			if (oldObj != null) {
				throw new AlreadyBound();
			}
			nameToObject.put(wrappedName, obj);
		}
	}

	@Override
	public void bind_context(NameComponent[] n, NamingContext nc) throws NotFound, CannotProceed, InvalidName, AlreadyBound {
		// This implementation doesn't support this
		throw new CannotProceed();
	}

	@Override
	public void rebind(NameComponent[] n, Object obj) throws NotFound, CannotProceed, InvalidName {
		validateActive();
		validateName(n);

		if (n.length != 1) {
			getParent(n).rebind(endOf(n), obj);
		} else {
			NameComponentWrapper wrappedName = new NameComponentWrapper(n[0]);
			org.omg.CORBA.Object oldObj = nameToObject.put(wrappedName, obj);
			if (oldObj != null) {
				oldObj._release();
			}
		}
	}

	@Override
	public void rebind_context(NameComponent[] n, NamingContext nc) throws NotFound, CannotProceed, InvalidName {
		// This implementation doesn't support this
		throw new CannotProceed();
	}

	@Override
	public Object resolve(NameComponent[] n) throws NotFound, CannotProceed, InvalidName {
		validateActive();
		validateName(n);

		if (n.length != 1) {
			return getParent(n).resolve(endOf(n));
		} else {
			NameComponentWrapper wrappedName = new NameComponentWrapper(n[0]);
			if (nameToObject.containsKey(wrappedName)) {
				return nameToObject.get(wrappedName);
			} else if (nameToChildContext.containsKey(wrappedName)) {
				return nameToChildContext.get(wrappedName).getNarrowedObj();
			} else {
				throw new NotFound(NotFoundReason.missing_node, n);
			}
		}
	}

	@Override
	public void unbind(NameComponent[] n) throws NotFound, CannotProceed, InvalidName {
		validateActive();
		validateName(n);

		if (n.length != 1) {
			getParent(n).unbind(endOf(n));
		} else {
			NameComponentWrapper wrappedName = new NameComponentWrapper(n[0]);
			if (nameToObject.containsKey(wrappedName)) {
				nameToObject.remove(wrappedName)._release();
			} else if (nameToChildContext.containsKey(wrappedName)) {
				NamingContextExtImpl childContext = nameToChildContext.remove(wrappedName);
				if (childContext.self != null) {
					String nameStr = to_string(n);
					if (!childContext.nameToObject.isEmpty() || !childContext.nameToChildContext.isEmpty()) {
						String msg = String.format("Name context '%s' unbound, but still active with entries", nameStr);
						TestEnvirornment.log(IStatus.WARNING, msg, null);
					} else {
						String msg = String.format("Name context '%s' unbound, but still active", nameStr);
						TestEnvirornment.log(IStatus.WARNING, msg, null);
					}
				}
			} else {
				throw new NotFound(NotFoundReason.missing_node, n);
			}
		}
	}

	@Override
	public void list(int howMany, BindingListHolder bl, BindingIteratorHolder bi) {
		try {
			validateActive();
		} catch (CannotProceed e) {
			return;
		}

		// We ignore how_many and return all
		int index = 0;
		bl.value = new Binding[nameToObject.size() + nameToChildContext.size()];
		for (NameComponentWrapper name : nameToObject.keySet()) {
			bl.value[index++] = new Binding(new NameComponent[] { name.getValue() }, BindingType.nobject);
		}
		for (NameComponentWrapper name : nameToChildContext.keySet()) {
			bl.value[index++] = new Binding(new NameComponent[] { name.getValue() }, BindingType.ncontext);
		}

		// Create an iterator
		BindingIteratorPOA iter = new BindingIteratorPOA() {

			@Override
			public boolean next_one(BindingHolder b) {
				return false;
			}

			@Override
			public boolean next_n(int howMany, BindingListHolder bl) {
				return false;
			}

			@Override
			public void destroy() {
				try {
					byte[] oid = session.getPOA().servant_to_id(this);
					session.getPOA().deactivate_object(oid);
				} catch (CoreException | ServantNotActive | WrongPolicy | ObjectNotActive e) {
					TestEnvirornment.log(IStatus.ERROR, "Unable to deactive naming context iterator", e);
				}
			}
		};
		try {
			bi.value = BindingIteratorHelper.unchecked_narrow(session.getPOA().servant_to_reference(iter));
		} catch (CoreException | ServantNotActive | WrongPolicy e) {
			TestEnvirornment.log(IStatus.ERROR, "Unable to active naming context iterator", e);
		}
	}

	@Override
	public NamingContext new_context() {
		// This implementation doesn't support this
		return null;
	}

	@Override
	public NamingContext bind_new_context(NameComponent[] n) throws NotFound, AlreadyBound, CannotProceed, InvalidName {
		validateActive();
		validateName(n);

		if (n.length != 1) {
			return getParent(n).bind_new_context(endOf(n));
		} else {
			NameComponentWrapper wrappedName = new NameComponentWrapper(n[0]);
			if (nameToObject.containsKey(wrappedName)) {
				throw new AlreadyBound();
			}
			if (nameToChildContext.containsKey(wrappedName)) {
				throw new AlreadyBound();
			}

			NamingContextExtImpl childContext;
			try {
				NameComponent[] path = Arrays.copyOf(myContextPath, myContextPath.length + 1);
				path[path.length - 1] = n[0];
				childContext = new NamingContextExtImpl(session, path);
			} catch (CoreException e) {
				TestEnvirornment.log(IStatus.ERROR, "Unable to instantiate new naming context", e);
				throw new CannotProceed();
			}
			nameToChildContext.put(wrappedName, childContext);
			return childContext.getNarrowedObj();
		}
	}

	@Override
	public void destroy() throws NotEmpty {
		if (self == null) {
			return;
		}
		if (!nameToObject.isEmpty() || !nameToChildContext.isEmpty()) {
			throw new NotEmpty();
		}

		try {
			byte[] oid = session.getPOA().reference_to_id(self);
			session.getPOA().deactivate_object(oid);
			self = null;
		} catch (CoreException | WrongAdapter | WrongPolicy | ObjectNotActive e) {
			TestEnvirornment.log(IStatus.ERROR, "Unable to destroy naming context", e);
		}
	}

	/**
	 * @return The narrowed CORBA object for this {@link NamingContetExt}
	 */
	public NamingContextExt getNarrowedObj() {
		return self;
	}

	/**
	 * Reset this naming context by recursively unbinding all objects and destroying all child naming contexts.
	 */
	public void reset() {
		for (org.omg.CORBA.Object obj : nameToObject.values()) {
			obj._release();
		}
		nameToObject.clear();
		for (NamingContextExtImpl childContext : nameToChildContext.values()) {
			childContext.reset();
			try {
				childContext.destroy();
			} catch (NotEmpty e) {
				TestEnvirornment.log(IStatus.ERROR, "Unexpected - child context should have been empty", e);
			}
		}
		nameToChildContext.clear();
	}

	/**
	 * @throws CannotProceed If this object is not still active in the POA
	 */
	private void validateActive() throws CannotProceed {
		if (self == null) {
			throw new CannotProceed();
		}
	}

	/**
	 * Validate that a name doesn't have any nulls, etc.
	 * @param n
	 * @throws InvalidName
	 */
	private void validateName(NameComponent[] n) throws InvalidName {
		if (n == null || n.length == 0) {
			throw new InvalidName();
		}
		for (NameComponent nc : n) {
			if (nc == null || nc.id == null || nc.kind == null) {
				throw new InvalidName();
			}
		}
	}

	/**
	 * Returns the parent of the requested name (possibly this object)
	 * @param n
	 * @return
	 */
	private NamingContextExtImpl getParent(NameComponent[] n) throws NotFound, CannotProceed, InvalidName {
		if (n.length == 0) {
			throw new InvalidName();
		}
		validateActive();

		if (n.length == 1) {
			return this;
		} else {
			NameComponentWrapper wrappedName = new NameComponentWrapper(n[0]);
			if (nameToObject.containsKey(wrappedName)) {
				throw new NotFound(NotFoundReason.not_context, n);
			}
			if (!nameToChildContext.containsKey(wrappedName)) {
				throw new NotFound(NotFoundReason.missing_node, n);
			}
			return nameToChildContext.get(wrappedName).getParent(Arrays.copyOfRange(n, 1, n.length));
		}
	}

	/**
	 * @param n
	 * @return An array containing only the very last segment of n
	 */
	private NameComponent[] endOf(NameComponent[] n) {
		return new NameComponent[] { n[n.length - 1] };
	}

	@Override
	public String toString() {
		if (myContextPath.length == 0) {
			return super.toString() + " (root)";
		} else {
			try {
				return super.toString() + " " + to_string(myContextPath);
			} catch (InvalidName e) {
				return super.toString() + " (invalid context path)";
			}
		}
	}
}

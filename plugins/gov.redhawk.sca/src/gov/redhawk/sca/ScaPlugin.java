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
package gov.redhawk.sca;

import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.services.IScaObjectLocator;
import gov.redhawk.sca.compatibility.ICompatibilityUtil;
import gov.redhawk.sca.internal.ScaDomainRegistryObjectLocator;
import gov.redhawk.sca.preferences.ScaPreferenceConstants;
import gov.redhawk.sca.properties.IPropertiesProviderRegistry;
import gov.redhawk.sca.properties.PropertiesProviderRegistry;
import gov.redhawk.sca.util.CorbaURIUtil;
import gov.redhawk.sca.util.IPreferenceAccessor;
import gov.redhawk.sca.util.OrbSession;
import gov.redhawk.sca.util.ScopedPreferenceAccessor;

import java.util.ArrayList;
import java.util.concurrent.Callable;

import mil.jpeojtrs.sca.util.CorbaUtils;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.omg.CosNaming.Binding;
import org.omg.CosNaming.BindingIteratorHolder;
import org.omg.CosNaming.BindingListHolder;
import org.omg.CosNaming.BindingType;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;

import CF.DomainManagerHelper;

/**
 * The activator class controls the plug-in life cycle.
 */
public class ScaPlugin extends Plugin {

	/**
	 * @since 4.0
	 */
	public static final String PLUGIN_ID = "gov.redhawk.sca";

	/**
	 * @since 6.0
	 */
	public static final String EDITING_DOMAIN_ID = ScaPlugin.PLUGIN_ID + ".editingDomain";

	// The shared instance
	/** The plugin. */
	private static ScaPlugin plugin;

	private ScopedPreferenceAccessor scaPreferenceStore;

	private ServiceTracker<IScaDomainManagerRegistryFactoryService, IScaDomainManagerRegistryFactoryService> registryService;

	private ServiceRegistration<IScaObjectLocator> serviceReg;

	private ServiceTracker<ICompatibilityUtil, ICompatibilityUtil> compatibilityUtil;

	/**
	 * The constructor.
	 */
	public ScaPlugin() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void start(final BundleContext context) throws Exception {
		super.start(context);
		ScaPlugin.plugin = this;
		this.serviceReg = context.registerService(IScaObjectLocator.class, new ScaDomainRegistryObjectLocator(), null);
		this.compatibilityUtil = new ServiceTracker<ICompatibilityUtil, ICompatibilityUtil>(getBundle().getBundleContext(), ICompatibilityUtil.class, null);
		this.compatibilityUtil.open(true);
		this.registryService = new ServiceTracker<IScaDomainManagerRegistryFactoryService, IScaDomainManagerRegistryFactoryService>(context,
			IScaDomainManagerRegistryFactoryService.class, null);
		this.registryService.open();
	}

	/**
	 * Returns the bundle id of the bundle that contains the provided object, or
	 * <code>null</code> if the bundle could not be determined.
	 * 
	 * @since 2.4
	 */
	public String getBundleId(final Object object) {
		if (object == null) {
			return null;
		}

		final Bundle source = FrameworkUtil.getBundle(object.getClass());
		if (source != null && source.getSymbolicName() != null) {
			return source.getSymbolicName();
		}
		return null;
	}

	/**
	 * @since 3.0
	 * @deprecated Use {@link #getDomainManagerRegistry(Object) instead. In RAP, the domain registry cannot be retrieved without the active Display instance}
	 */
	@Deprecated
	public ScaDomainManagerRegistry getDomainManagerRegistry() {
		return getDomainManagerRegistry(null);
	}

	/**
	 * @since 7.0
	 *
	 * Returns the SCA Domain Manager registry.
	 * @param context
	 * 			the current Display (meaningful in RAP only), used to ensure user-specific context.
	 * @return
	 * 			the SCA Domain Manager registry
	 */
	public ScaDomainManagerRegistry getDomainManagerRegistry(Object context) {
		IScaDomainManagerRegistryFactoryService service = this.registryService.getService();
		if (service != null) {
			IScaDomainManagerRegistryContainer container = service.getRegistryContainer(context);
			if (container != null) {
				return container.getRegistry(context);
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stop(final BundleContext context) throws Exception {
		try {
			if (this.compatibilityUtil != null) {
				this.compatibilityUtil.close();
				this.compatibilityUtil = null;
			}
			if (this.registryService != null) {
				this.registryService.close();
				this.registryService = null;
			}
			if (this.serviceReg != null) {
				try {
					this.serviceReg.unregister();
				} catch (final Exception e) {
					// PASS
				}
				this.serviceReg = null;
			}
			ScaPlugin.plugin = null;
		} finally {
			super.stop(context);
		}
	}

	/**
	 * Returns the shared instance.
	 * 
	 * @return the shared instance
	 */
	public static ScaPlugin getDefault() {
		return ScaPlugin.plugin;
	}

	/**
	 * @since 3.0
	 *
	 * Returns an object that can be used to read and write system Preferences.
	 *  
	 * @return 
	 * 			an object used to access system preferences. For RAP, preferences are generally scoped and
	 * persisted on a per-user basis. That is not the case with this plug-in. Because there are no UI
	 * dependencies, there is no way to access a user context.
	 */
	public IPreferenceAccessor getScaPreferenceAccessor() {

		if (this.scaPreferenceStore == null) {
			this.scaPreferenceStore = new ScopedPreferenceAccessor(InstanceScope.INSTANCE, ScaPlugin.getPluginId());
		}
		return this.scaPreferenceStore;
	}

	/**
	 * @since 7.0
	 *
	 * Returns a utility class that provides platform-specific implementations for RAP
	 * and RCP runtime environments.
	 * 
	 * @return 
	 * 			the utility class
	 */
	public ICompatibilityUtil getCompatibilityUtil() {
		return compatibilityUtil.getService();
	}

	/**
	 * @since 3.0
	 */
	public static String getPluginId() {
		return PLUGIN_ID;
	}

	/**
	 * @since 5.0
	 */
	public static IPropertiesProviderRegistry getPropertiesProviderRegistry() {
		return PropertiesProviderRegistry.INSTANCE;
	}

	/**
	 * Determines if a domain of the given name is bound to the default naming service and
	 * appears to be existant.
	 * @since 6.0
	 * @deprecated Use {@link #isDomainOnline(String, IProgressMonitor)}
	 */
	@Deprecated
	public static boolean isDomainOnline(final String domainName) {
		try {
			return isDomainOnline(domainName, (IProgressMonitor) null);
		} catch (CoreException e) {
			return false;
		} catch (InterruptedException e) {
			return false;
		}
	}

	/**
	 * Determines if a domain of the given name is bound to the default naming service and
	 * appears to be existant.
	 * @throws InterruptedException 
	 * @throws CoreException 
	 * @since 7.0
	 */
	public static boolean isDomainOnline(final String domainName, IProgressMonitor monitor) throws CoreException, InterruptedException {
		IPreferenceAccessor prefs = ScaPlugin.getDefault().getScaPreferenceAccessor();
		final String namingService = prefs.getString(ScaPreferenceConstants.SCA_DEFAULT_NAMING_SERVICE);
		//final String namingService = Platform.getPreferencesService().getString(ScaPreferenceConstants.SCA_DEFAULT_NAMING_SERVICE);
		return isDomainOnline(domainName, namingService, monitor);
	}

	/**
	 * Determines if a domain of the given name is bound to the naming service and
	 * appears to be existant.
	 * @since 6.0
	 * @deprecated {@link #isDomainOnline(String, String, IProgressMonitor)}
	 */
	@Deprecated
	public static boolean isDomainOnline(final String domainName, final String namingService) {
		try {
			return isDomainOnline(domainName, namingService, null);
		} catch (CoreException e) {
			return false;
		} catch (InterruptedException e) {
			return false;
		}
	}

	/**
	 * Determines if a domain of the given name is bound to the naming service and
	 * appears to be existant.
	 * @throws InterruptedException 
	 * @throws CoreException 
	 * @since 7.0
	 */
	public static boolean isDomainOnline(final String domainName, final String namingService, IProgressMonitor monitor) throws CoreException,
		InterruptedException {
		return nameServiceObjectExists(domainName, namingService, monitor);
	}

	/**
	 * Determine's if an object with a given name is bound to the provided nameservice and
	 * the object exists.
	 * 
	 * @since 6.0
	 * @deprecated {@link #nameServiceObjectExists(String, String, IProgressMonitor)}
	 */
	@Deprecated
	public static boolean nameServiceObjectExists(final String name, final String nameServiceInitRef) {
		try {
			return nameServiceObjectExists(name, nameServiceInitRef, null);
		} catch (CoreException e) {
			return false;
		} catch (InterruptedException e) {
			return false;
		}
	}

	/**
	 * Determine's if an object with a given name is bound to the provided nameservice and
	 * the object exists.
	 * @throws InterruptedException 
	 * @throws CoreException 
	 * 
	 * @since 7.0
	 */
	public static boolean nameServiceObjectExists(final String name, final String nameServiceInitRef, IProgressMonitor parentMonitor) throws CoreException,
		InterruptedException {
		SubMonitor subMonitor = SubMonitor.convert(parentMonitor, "Checking if name service object exists...", 2);
		final String nameServiceRef = CorbaURIUtil.addDefaultPort(nameServiceInitRef);
		OrbSession session = OrbSession.createSession();

		// Allow the user to specify the DomainManager. If there is no '/'
		// then the user did not specify it(Use the SCA 2.2.2 specific
		// implementation which is <name>/<name>)
		final String orbName;
		if (name.indexOf('/') == -1) {
			orbName = name + "/" + name;
		} else {
			orbName = name;
		}

		NamingContextExt rootContext = null;
		org.omg.CORBA.Object object = null;

		try {
			rootContext = NamingContextExtHelper.narrow(session.getOrb().string_to_object(nameServiceRef));
			object = CorbaUtils.resolve_str(rootContext, orbName, subMonitor.newChild(1));
			return !CorbaUtils.non_existent(object, subMonitor.newChild(1));
		} finally {
			subMonitor.done();
			if (rootContext != null) {
				rootContext._release();
				rootContext = null;
			}
			if (object != null) {
				object._release();
				object = null;
			}
			session.dispose();
		}
	}

	/**
	 * Scan the naming service to find available domains
	 * @param monitor Progress monitor to interrupt the opperations with
	 * @throws InterruptedException 
	 * @throws CoreException 
	 * 
	 * @since 7.0
	 */
	public static String[] findDomainNamesOnDefaultNameServer(IProgressMonitor monitor) throws CoreException, InterruptedException {
		IPreferenceAccessor prefs = ScaPlugin.getDefault().getScaPreferenceAccessor();
		final String namingService = prefs.getString(ScaPreferenceConstants.SCA_DEFAULT_NAMING_SERVICE);
		return findDomainNamesOnNameServer(namingService, monitor);
	}

	/**
	 * Scan the naming service to find available domains
	 * 
	 * @since 6.0
	 * @deprecated Use {@link #findDomainNamesOnDefaultNameServer(IProgressMonitor)}
	 */
	@Deprecated
	public static String[] findDomainNamesOnDefaultNameServer() {
		try {
			return findDomainNamesOnDefaultNameServer(null);
		} catch (CoreException e) {
			return new String[0];
		} catch (InterruptedException e) {
			return new String[0];
		}
	}

	/**
	 * Scan the naming service to find available domains
	 * 
	 * @since 6.0
	 * @deprecated {@link #findDomainNamesOnDefaultNameServer(String, IProgressMonitor)}
	 */
	@Deprecated
	public static String[] findDomainNamesOnNameServer(final String nameServiceInitRef) {
		try {
			return findDomainNamesOnNameServer(nameServiceInitRef, null);
		} catch (CoreException e) {
			return new String[0];
		} catch (InterruptedException e) {
			return new String[0];
		}
	}

	/**
	 * Scan the naming service to find available domains
	 * @throws InterruptedException 
	 * @throws CoreException 
	 * 
	 * @since 7.0
	 */
	public static String[] findDomainNamesOnNameServer(final String nameServiceInitRef, IProgressMonitor parentMonitor) throws CoreException, InterruptedException {
		SubMonitor subMonitor = SubMonitor.convert(parentMonitor, "Finding domains on name server...", 5);
		final ArrayList<String> retVal = new ArrayList<String>();

		final String nameServiceRef = CorbaURIUtil.addDefaultPort(nameServiceInitRef);
		OrbSession session = OrbSession.createSession();
		NamingContextExt rootContext = null;
		try {
			final org.omg.CORBA.Object rootContextRef = CorbaUtils.string_to_object(session.getOrb(), nameServiceRef, subMonitor.newChild(1));
			rootContext = CorbaUtils.invoke(new Callable<NamingContextExt>() {

				@Override
				public NamingContextExt call() throws Exception {
					return NamingContextExtHelper.narrow(rootContextRef);
				}

			}, subMonitor.newChild(1));

			final BindingListHolder bl = new BindingListHolder();
			// Get a listing of root names
			rootContext.list(-1, bl, new BindingIteratorHolder());
			for (Binding b : bl.value) {
				// Domains are always bound in a context with the same name as the domain
				if (b.binding_type.value() == BindingType._ncontext) {
					String guessedDomainName = b.binding_name[0].id + "/" + b.binding_name[0].id;
					org.omg.CORBA.Object object = null;
					try {
						object = CorbaUtils.resolve_str(rootContext, guessedDomainName, subMonitor.newChild(1));
						if (!CorbaUtils.non_existent(object, subMonitor.newChild(1)) && CorbaUtils.is_a(object, DomainManagerHelper.id(), subMonitor.newChild(1))) {
							retVal.add(b.binding_name[0].id);
						}
					} finally {
						if (object != null) {
							object._release();
							object = null;
						}
					}
				}
			}
			return retVal.toArray(new String[retVal.size()]);
		} finally {
			subMonitor.done();
			if (rootContext != null) {
				rootContext._release();
			}
			session.dispose();
		}
	}
}

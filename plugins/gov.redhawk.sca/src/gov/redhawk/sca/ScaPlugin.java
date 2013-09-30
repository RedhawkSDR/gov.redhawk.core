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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
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
		this.registryService = new ServiceTracker<IScaDomainManagerRegistryFactoryService, IScaDomainManagerRegistryFactoryService>(context, IScaDomainManagerRegistryFactoryService.class, null);
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
	 */
	public ScaDomainManagerRegistry getDomainManagerRegistry() {
		return getDomainManagerRegistry(null);
	}
	
	/**
	 * @since 6.1
	 */
	public ScaDomainManagerRegistry getDomainManagerRegistry(Object context) {
		IScaDomainManagerRegistryFactoryService service = this.registryService.getService();
		if (service != null) {
			IScaDomainManagerRegistryContainer container = service.getRegistryContainer();
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
	 */
	public IPreferenceAccessor getScaPreferenceAccessor() {
		
		if (this.scaPreferenceStore == null) {
			this.scaPreferenceStore = new ScopedPreferenceAccessor(InstanceScope.INSTANCE, ScaPlugin.getPluginId());
		}
		return this.scaPreferenceStore;
	}
	
	/**
	 * @since 6.1
	 */

	public ICompatibilityUtil getCompatibilityUtil() {
		return getDefault().compatibilityUtil.getService();
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
	 */
	public static boolean isDomainOnline(final String domainName) {
		IPreferenceAccessor prefs = ScaPlugin.getDefault().getScaPreferenceAccessor();
		final String namingService = prefs.getString(ScaPreferenceConstants.SCA_DEFAULT_NAMING_SERVICE);
		//final String namingService = Platform.getPreferencesService().getString(ScaPreferenceConstants.SCA_DEFAULT_NAMING_SERVICE);
		return isDomainOnline(domainName, namingService);
	}

	/**
	 * Determines if a domain of the given name is bound to the naming service and
	 * appears to be existant.
	 * @since 6.0
	 */
	public static boolean isDomainOnline(final String domainName, final String namingService) {
		return nameServiceObjectExists(domainName, namingService);
	}

	/**
	 * Determine's if an object with a given name is bound to the provided nameservice and
	 * the object exists.
	 * 
	 * @since 6.0
	 */
	public static boolean nameServiceObjectExists(final String name, final String nameServiceInitRef) {
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
			object = rootContext.resolve_str(orbName);
			return (!object._non_existent());
		} catch (final Exception e) {
			return false;
		} finally {
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
	 * 
	 * @since 6.0
	 */
	public static String[] findDomainNamesOnDefaultNameServer() {
		IPreferenceAccessor prefs = ScaPlugin.getDefault().getScaPreferenceAccessor();
		final String namingService = prefs.getString(ScaPreferenceConstants.SCA_DEFAULT_NAMING_SERVICE);
		return findDomainNamesOnNameServer(namingService);
	}

	/**
	 * Scan the naming service to find available domains
	 * 
	 * @since 6.0
	 */
	public static String[] findDomainNamesOnNameServer(final String nameServiceInitRef) {
		final ArrayList<String> retVal = new ArrayList<String>();

		final String nameServiceRef = CorbaURIUtil.addDefaultPort(nameServiceInitRef);
		OrbSession session = OrbSession.createSession();
		NamingContextExt rootContext = null;
		try {

			rootContext = NamingContextExtHelper.narrow(session.getOrb().string_to_object(nameServiceRef));

			final BindingListHolder bl = new BindingListHolder();
			// Get a listing of root names
			rootContext.list(-1, bl, new BindingIteratorHolder());
			for (Binding b : bl.value) {
				// Domains are always bound in a context with the same name as the domain
				if (b.binding_type.value() == BindingType._ncontext) {
					String guessedDomainName = b.binding_name[0].id + "/" + b.binding_name[0].id;
					org.omg.CORBA.Object object = null;
					try {
						object = rootContext.resolve_str(guessedDomainName);
						if ((!object._non_existent()) && (object._is_a(DomainManagerHelper.id()))) {
							retVal.add(b.binding_name[0].id);
						}
					} catch (final Exception e) {
						continue;
					} finally {
						if (object != null) {
							object._release();
							object = null;
						}
					}
				}
			}
			return retVal.toArray(new String[retVal.size()]);
		} catch (final Exception e) {
			ScaPlugin.getDefault().getLog().log(new Status(IStatus.WARNING, ScaPlugin.PLUGIN_ID, "Failed to find domain names", e));
			return new String[0];
		} finally {
			if (rootContext != null) {
				rootContext._release();
			}
			session.dispose();
		}
	}
}

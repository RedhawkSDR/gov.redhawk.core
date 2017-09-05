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

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Callable;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.jacorb.JacorbActivator;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceRegistration;
import org.osgi.util.tracker.ServiceTracker;

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.services.IScaObjectLocator;
import gov.redhawk.sca.compatibility.ICompatibilityUtil;
import gov.redhawk.sca.internal.ScaDomainRegistryObjectLocator;
import gov.redhawk.sca.jobs.FindDomainsJob;
import gov.redhawk.sca.preferences.ScaPreferenceConstants;
import gov.redhawk.sca.properties.IPropertiesProviderRegistry;
import gov.redhawk.sca.properties.PropertiesProviderRegistry;
import gov.redhawk.sca.util.CorbaURIUtil;
import gov.redhawk.sca.util.IPreferenceAccessor;
import gov.redhawk.sca.util.ORBUtil;
import gov.redhawk.sca.util.OrbSession;
import gov.redhawk.sca.util.ScopedPreferenceAccessor;
import mil.jpeojtrs.sca.util.CorbaUtils;

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

	private ServiceTracker<ICompatibilityUtil, ICompatibilityUtil> compatibilityUtil;

	private ServiceRegistration< ? > serviceReg;

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
		ScaPlugin.plugin = this;
		super.start(context);
		JacorbActivator.getDefault().init();
		this.compatibilityUtil = new ServiceTracker<ICompatibilityUtil, ICompatibilityUtil>(getBundle().getBundleContext(), ICompatibilityUtil.class, null);
		this.compatibilityUtil.open(true);
		this.registryService = new ServiceTracker<IScaDomainManagerRegistryFactoryService, IScaDomainManagerRegistryFactoryService>(context,
				IScaDomainManagerRegistryFactoryService.class, null);
		this.registryService.open();
		
		this.serviceReg = context.registerService(IScaObjectLocator.class.getName(), new ScaDomainRegistryObjectLocator(), null);
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
	 * @since 7.0
	 *
	 * Returns the domain manager registry.
	 * @param context
	 * the current Display (meaningful in RAP only), used to ensure user-specific context.
	 * @return the domain manager registry
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

	/**
	 * @since 7.0
	 *
	 * Creates a domain outside of the domain manager registry, this domain will not be saved by the domain manager
	 * registry service
	 */
	public ScaDomainManager createTransientDomain(final String domainName, Map<String, String> connectionProperties) {
		TransactionalEditingDomain editingDomain = TransactionalEditingDomain.Registry.INSTANCE.getEditingDomain(ScaPlugin.EDITING_DOMAIN_ID);
		final ResourceSet scaModelResourceSet = editingDomain.getResourceSet();

		URI uri = org.eclipse.emf.common.util.URI.createURI("virtual://transientDomains.sca");

		final Resource resource = scaModelResourceSet.createResource(uri);

		final ScaDomainManager newDomain = ScaFactory.eINSTANCE.createScaDomainManager();
		newDomain.setName(domainName);
		if (connectionProperties == null) {
			connectionProperties = Collections.emptyMap();
		}
		newDomain.getConnectionProperties().putAll(connectionProperties);

		editingDomain.getCommandStack().execute(new ScaModelCommand() {

			@Override
			public void execute() {
				resource.getContents().add(newDomain);
				newDomain.eAdapters().add(new AdapterImpl() {
					@Override
					public void notifyChanged(Notification msg) {
						switch (msg.getFeatureID(ScaDomainManager.class)) {
						case ScaPackage.SCA_DOMAIN_MANAGER__DISPOSED:
							if (msg.getNewBooleanValue()) {
								resource.getContents().remove(newDomain);
							}
							newDomain.eAdapters().remove(this);
							scaModelResourceSet.getResources().remove(resource);
							break;
						default:
							break;
						}
					}
				});
			}
		});

		return newDomain;
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
				this.serviceReg.unregister();
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
	 * an object used to access system preferences. For RAP, preferences are generally scoped and
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
	 * the utility class
	 */
	public ICompatibilityUtil getCompatibilityUtil() {
		return compatibilityUtil.getService();
	}

	/**
	 * @since 3.0
	 */
	public static String getPluginId() {
		return ScaPlugin.PLUGIN_ID;
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
	 * @throws InterruptedException
	 * @throws CoreException
	 * @since 7.0
	 */
	public static boolean isDomainOnline(final String domainName, IProgressMonitor monitor) throws CoreException, InterruptedException {
		IPreferenceAccessor prefs = ScaPlugin.getDefault().getScaPreferenceAccessor();
		final String namingService = prefs.getString(ScaPreferenceConstants.SCA_DEFAULT_NAMING_SERVICE);
		//final String namingService = Platform.getPreferencesService().getString(ScaPreferenceConstants.SCA_DEFAULT_NAMING_SERVICE);
		return ScaPlugin.isDomainOnline(domainName, namingService, monitor);
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
		return ScaPlugin.nameServiceObjectExists(domainName, namingService, monitor);
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
			final org.omg.CORBA.Object ref = CorbaUtils.string_to_object(session.getOrb(), nameServiceRef, subMonitor.newChild(1));
			rootContext = CorbaUtils.invoke(new Callable<NamingContextExt>() {

				@Override
				public NamingContextExt call() throws Exception {
					return NamingContextExtHelper.narrow(ref);
				}

			}, subMonitor.newChild(1));
			object = CorbaUtils.resolve_str(rootContext, orbName, subMonitor.newChild(1));

			return !CorbaUtils.non_existent(object, subMonitor.newChild(1));
		} catch (CoreException e1) {
			return false;
		} finally {
			subMonitor.done();
			if (rootContext != null) {
				ORBUtil.release(rootContext);
				rootContext = null;
			}
			if (object != null) {
				ORBUtil.release(object);
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
		return ScaPlugin.findDomainNamesOnNameServer(namingService, monitor);
	}

	/**
	 * Scan the naming service to find available domains
	 * @throws InterruptedException
	 * @throws CoreException
	 * @see FindDomainsJob
	 * @since 7.0
	 */
	public static String[] findDomainNamesOnNameServer(final String nameServiceInitRef, IProgressMonitor monitor) throws CoreException, InterruptedException {
		final String nameServiceRef = CorbaURIUtil.addDefaultPort(nameServiceInitRef);
		FindDomainsJob findDomainsJob = new FindDomainsJob(nameServiceRef, true);
		findDomainsJob.schedule();
		findDomainsJob.join();
		IStatus status = findDomainsJob.getResult();
		if (status.isOK()) {
			return findDomainsJob.getDomainNames().toArray(new String[0]);
		} else if (status.getSeverity() == Status.CANCEL) {
			throw new InterruptedException();
		} else {
			throw new CoreException(new Status(status.getSeverity(), ScaPlugin.PLUGIN_ID, "Unable to retrieve domain names", status.getException()));
		}
	}
}

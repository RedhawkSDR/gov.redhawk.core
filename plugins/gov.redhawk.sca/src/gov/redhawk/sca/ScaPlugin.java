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

import gov.redhawk.model.sca.DomainConnectionException;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDocumentRoot;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.services.IScaObjectLocator;
import gov.redhawk.sca.internal.ScaDomainRegistryObjectLocator;
import gov.redhawk.sca.preferences.ScaPreferenceConstants;
import gov.redhawk.sca.preferences.ScaPreferenceInitializer;
import gov.redhawk.sca.properties.IPropertiesProviderRegistry;
import gov.redhawk.sca.properties.PropertiesProviderRegistry;
import gov.redhawk.sca.util.CorbaURIUtil;
import gov.redhawk.sca.util.IPreferenceAccessor;
import gov.redhawk.sca.util.OrbSession;
import gov.redhawk.sca.util.ScopedPreferenceAccessor;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.omg.CosNaming.Binding;
import org.omg.CosNaming.BindingIteratorHolder;
import org.omg.CosNaming.BindingListHolder;
import org.omg.CosNaming.BindingType;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.packageadmin.PackageAdmin;
import org.osgi.util.tracker.ServiceTracker;

import CF.DomainManagerHelper;

/**
 * The activator class controls the plug-in life cycle.
 */
@SuppressWarnings("deprecation")
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

	private static BundleContext bundleContext;

	private ServiceTracker<PackageAdmin, PackageAdmin> bundleTracker;

	private ScaDomainManagerRegistry scaDomainManagerRegistry;

	private Resource registryResource;

	private ScopedPreferenceAccessor scaPreferenceStore;

	private Job startupJob;

	private ResourceSet scaModelResourceSet;

	private TransactionalEditingDomain editingDomain;

	private ServiceRegistration<IScaObjectLocator> serviceReg;

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
		ScaPlugin.bundleContext = context;
		ScaPlugin.plugin = this;
		this.serviceReg = context.registerService(IScaObjectLocator.class, new ScaDomainRegistryObjectLocator(), null);
	}

	/*
	 * Return this bundle's context.
	 */
	static BundleContext getContext() {
		return ScaPlugin.bundleContext;
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

		final PackageAdmin packageAdmin = getBundleAdmin();
		if (packageAdmin == null) {
			return null;
		}

		final Bundle source = packageAdmin.getBundle(object.getClass());
		if (source != null && source.getSymbolicName() != null) {
			return source.getSymbolicName();
		}
		return null;
	}

	/*
	 * Return the package admin service, if available.
	 */
	private PackageAdmin getBundleAdmin() {
		if (this.bundleTracker == null) {
			this.bundleTracker = new ServiceTracker<PackageAdmin, PackageAdmin>(ScaPlugin.getContext(), PackageAdmin.class, null);
			this.bundleTracker.open();
		}
		return (PackageAdmin) this.bundleTracker.getService();
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
			if (this.serviceReg != null) {
				try {
					this.serviceReg.unregister();
				} catch (final Exception e) {
					// PASS
				}
				this.serviceReg = null;
			}
			ScaPlugin.plugin = null;
			if (this.bundleTracker != null) {
				this.bundleTracker.close();
				this.bundleTracker = null;
			}
			ScaPlugin.bundleContext = null;
			if (this.scaDomainManagerRegistry != null) {
				ScaModelCommand.execute(this.scaDomainManagerRegistry, new ScaModelCommand() {

					public void execute() {
						if (registryResource != null) {
							Resource resource = ScaPlugin.this.registryResource;
							ScaPlugin.this.registryResource = null;

							if (resource != null) {
								try {
									resource.save(null);
								} catch (IOException e) {
									getLog().log(new Status(Status.ERROR, ScaPlugin.getPluginId(), "Failed to save Domain connections.", e));
								}
							}
						}

						ScaDomainManagerRegistry registry = scaDomainManagerRegistry;
						registry.dispose();

						scaDomainManagerRegistry = null;

						if (editingDomain != null) {
							// Do not dispose since it is a statically mapped editing domain
							//							editingDomain.dispose();
							editingDomain = null;
						}

					}
				});
			}

			if (this.startupJob != null) {
				this.startupJob.cancel();
				this.startupJob = null;
			}
			savePluginPreferences();
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
	public ScaDomainManagerRegistry getDomainManagerRegistry() {
		if (this.scaDomainManagerRegistry == null) {
			synchronized (this) {
				if (this.scaDomainManagerRegistry == null) { // SUPPRESS CHECKSTYLE DoubleCheck
					this.editingDomain = TransactionalEditingDomain.Registry.INSTANCE.getEditingDomain(ScaPlugin.EDITING_DOMAIN_ID);
					this.scaModelResourceSet = this.editingDomain.getResourceSet();

					loadScaModel();

					connectOnStartup();
				}
			}
		}
		return this.scaDomainManagerRegistry;
	}

	private void loadScaModel() {
		try {
			final URI fileUri = getStateLocation().append("domains.sca").toFile().toURI();
			final org.eclipse.emf.common.util.URI uri = org.eclipse.emf.common.util.URI.createURI(fileUri.toString());

			try {
				this.registryResource = this.scaModelResourceSet.getResource(uri, true);
			} catch (final Exception e) {
				// Clear the resource set here to remove the bad
				// resource from above
				this.scaModelResourceSet.getResources().clear();
				this.registryResource = this.scaModelResourceSet.createResource(uri, ScaPackage.eCONTENT_TYPE);
			}
		} catch (final IllegalStateException e1) {
			// This can be thrown by the getStateLocation() call if
			// the -noData option is specified. Therefore we will
			// load into memory
			this.registryResource = this.scaModelResourceSet.createResource(org.eclipse.emf.common.util.URI.createURI("virtual://instanceDomains.sca"),
			        ScaPackage.eCONTENT_TYPE);
			getLog().log(new Status(IStatus.WARNING, ScaPlugin.getPluginId(), "Using memory store for sca domains.  Will not presist domain connections.", e1));
		}

		this.scaDomainManagerRegistry = ScaDomainManagerRegistry.Util.getScaDomainManagerRegistry(this.registryResource);
		if (this.scaDomainManagerRegistry == null) { // SUPPRESS CHECKSTYLE DoubleCheck
			this.editingDomain.getCommandStack().execute(new ScaModelCommand() {

				public void execute() {
					ScaPlugin.this.scaDomainManagerRegistry = ScaPlugin.initScaResource(ScaPlugin.this.registryResource);
				}
			});

		}
	}

	private static ScaDomainManagerRegistry initScaResource(final Resource resource) {
		resource.getContents().clear();

		ScaDocumentRoot root = ScaFactory.eINSTANCE.createScaDocumentRoot();

		final ScaDomainManagerRegistry retVal = ScaFactory.eINSTANCE.createScaDomainManagerRegistry();

		final ScaDomainManagerRegistry defaultRegistry = ScaPreferenceInitializer.getDefaultScaDomainManagerRegistry();
		for (final ScaDomainManager domain : defaultRegistry.getDomains()) {
			retVal.getDomains().add(EcoreUtil.copy(domain));
		}

		root.setDomainManagerRegistry(retVal);
		resource.getContents().add(root);

		return retVal;

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

	private void connectOnStartup() {
		this.startupJob = new Job("Connecting to domains on startup...") {

			@Override
			protected IStatus run(final IProgressMonitor monitor) {
				try {
					monitor.beginTask("Connecting to domains on startup...", IProgressMonitor.UNKNOWN);
					ScaDomainManager[] domains;
					try {
						domains = TransactionUtil.runExclusive(ScaPlugin.this.editingDomain, new RunnableWithResult.Impl<ScaDomainManager[]>() {

							public void run() {
								setResult(getDomainManagerRegistry().getDomains().toArray(new ScaDomainManager[getDomainManagerRegistry().getDomains().size()]));
							}

						});
					} catch (final InterruptedException e1) {
						return Status.CANCEL_STATUS;
					}
					for (final ScaDomainManager domain : domains) {
						if (domain.isAutoConnect()) {
							final Job job = new Job("Connecting to: " + domain.getName()) {

								@Override
								protected IStatus run(final IProgressMonitor monitor) {
									try {
										domain.connect(monitor, RefreshDepth.SELF);
									} catch (final DomainConnectionException e) {
										return new Status(IStatus.ERROR, ScaPlugin.getPluginId(), "Failed to connect to domain " + domain.getName() + ".", e);
									}
									return Status.OK_STATUS;
								}

							};
							job.schedule();

						}
					}
					return Status.OK_STATUS;
				} finally {
					monitor.done();
				}
			}

		};
		this.startupJob.setPriority(Job.LONG);
		this.startupJob.schedule();
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

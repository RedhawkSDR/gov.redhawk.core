/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.sca.rap.internal;

import gov.redhawk.model.sca.DomainConnectionException;
import gov.redhawk.model.sca.Properties;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDocumentRoot;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.sca.IScaDomainManagerRegistryContainer;
import gov.redhawk.sca.ScaPlugin;
import gov.redhawk.sca.preferences.ScaPreferenceInitializer;

import java.io.IOException;
import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.rwt.SessionSingletonBase;
import org.eclipse.swt.widgets.Display;

/**
 * This class provides a wrapper around ScaDomainManagerRegistry, so that either shared singleton
 * or session singleton instance can be used for obtaining the registry and managing its listeners
 */
public class ScaDomainManagerRegistryContainerImpl extends SessionSingletonBase implements IScaDomainManagerRegistryContainer {

	private static ScaDomainManagerRegistryContainerImpl instance = null;

	private ScaDomainManagerRegistry scaDomainManagerRegistry;

	private Resource registryResource;

	private Job startupJob;

	private TransactionalEditingDomain editingDomain = TransactionalEditingDomain.Registry.INSTANCE.getEditingDomain(ScaPlugin.EDITING_DOMAIN_ID);
	private ResourceSet scaModelResourceSet = this.editingDomain.getResourceSet();

	/**
	 * Listens for changes to domain manager properties, and persists them to the
	 * configured preference store.
	 */
	private Adapter propListener = new AdapterImpl() {
		public void notifyChanged(Notification msg) {
			if (msg.getFeatureID(Properties.class) == ScaPackage.PROPERTIES__PROPERTY) {
				switch (msg.getEventType()) {
				case Notification.SET:
				case Notification.ADD:
					saveDomainManagerRegistryResource();
					break;
				}
			}
		};
	};

	/**
	 * Listens for changes to a domain manager, and persists domain preferences
	 * to the configured preference store.
	 */
	private Adapter domainManagerListener = new AdapterImpl() {
		public void notifyChanged(Notification msg) {
			switch (msg.getFeatureID(ScaDomainManagerRegistry.class)) {
			case ScaPackage.SCA_DOMAIN_MANAGER__AUTO_CONNECT:
			case ScaPackage.SCA_DOMAIN_MANAGER__DATA_PROVIDERS_ENABLED:
			case ScaPackage.SCA_DOMAIN_MANAGER__NAME:
				saveDomainManagerRegistryResource();
				break;
			default:
			}
		};
	};

	/**
	 * Listens for changes to the domain manager registry, and persists domain preferences
	 * to the configured preference store.
	 */
	private Adapter domainManagerRegistrylistener = new AdapterImpl() {
		@SuppressWarnings("unchecked")
		public void notifyChanged(Notification msg) {
			if (msg.getFeatureID(ScaDomainManagerRegistry.class) == ScaPackage.SCA_DOMAIN_MANAGER_REGISTRY__DOMAINS) {
				switch (msg.getEventType()) {
				case Notification.ADD:
					saveDomainManagerRegistryResource();
					ScaDomainManager manager = (ScaDomainManager) msg.getNewValue();
					addDomainManagerPropertiesListeners(manager);
					break;
				case Notification.ADD_MANY:
					saveDomainManagerRegistryResource();
					List<ScaDomainManager> domains = (List<ScaDomainManager>) msg.getNewValue();
					for (ScaDomainManager mgr : domains) {
						addDomainManagerPropertiesListeners(mgr);
					}
					break;
				case Notification.REMOVE:
					saveDomainManagerRegistryResource();
					manager = (ScaDomainManager) msg.getOldValue();
					removeDomainManagerPropertiesListeners(manager);
					break;
				case Notification.REMOVE_MANY:
					saveDomainManagerRegistryResource();
					domains = (List<ScaDomainManager>) msg.getOldValue();
					for (ScaDomainManager mgr : domains) {
						removeDomainManagerPropertiesListeners(mgr);
					}
					break;
				default:
				}
			}
		};
	};

	private Set<Object> initializedContexts = new HashSet<Object>();

	private ScaDomainManagerRegistryContainerImpl() {
		//Class must be used as a singleton or a (RAP) session singleton
	}


	/**
	 * Return a singleton instance. In RAP, the singleton will be shared
	 * across all UI Sessions.
	 * 
	 * @return 
	 * 			a singleton instance (RCP), or a singleton instance shared
	 * across all UI Sessions (RAP).
	 */
	public static ScaDomainManagerRegistryContainerImpl getInstance() {
		if (instance == null) {
			instance = new ScaDomainManagerRegistryContainerImpl();
		}
		return instance;
	}

	public void dispose() {
		if (this.scaDomainManagerRegistry != null) {
			ScaModelCommand.execute(this.scaDomainManagerRegistry, new ScaModelCommand() {

				public void execute() {
					if (registryResource != null) {
						Resource resource = registryResource;
						registryResource = null;

						if (resource != null) {
							try {
								resource.save(null);
							} catch (IOException e) {
								ScaPlugin.getDefault().getLog().log(new Status(Status.ERROR, ScaPlugin.getPluginId(), "Failed to save Domain connections.", e));
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
		this.initializedContexts.clear();

		if (this.startupJob != null) {
			this.startupJob.cancel();
			this.startupJob = null;
		}
	}


	@Override
	public ScaDomainManagerRegistry getRegistry(final Object context) {

		Assert.isTrue(context instanceof Display, "The context object must be an instance of Display");
		Assert.isTrue(!((Display) context).isDisposed(), "The context object must not be disposed");

		if (Display.getCurrent() != null) {
			loadScaModel(Display.getCurrent());
			if (initialStartup(context)) {
				connectOnStartup();
			}
			return scaDomainManagerRegistry;
		} else {
			((Display) context).asyncExec(new Runnable() {

				@Override
				public void run() {
					scaDomainManagerRegistry = null;
					loadScaModel(context);
				}
				
			});
			
			while (scaDomainManagerRegistry == null) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					//PASS
				}
			}
			if (initialStartup(context)) {
				connectOnStartup();
			}
			return scaDomainManagerRegistry;
		}
	}

	private boolean initialStartup(Object context) {
		if (this.initializedContexts.contains(context)) {
			return false;
		}
		this.initializedContexts.add(context);
		return true;
	}

	private void loadScaModel(final Object context) {
		try {
			URI path = new Path(ScaPlugin.getDefault().getCompatibilityUtil().getSettingStoreWorkDir().getAbsolutePath())
				.append(ScaPlugin.getDefault().getCompatibilityUtil().getUserSpecificPath(context) + "_sca")
				.append("domains.sca").toFile().toURI();

			final org.eclipse.emf.common.util.URI uri = org.eclipse.emf.common.util.URI.createURI(path.toString());
			try {
				this.registryResource = this.scaModelResourceSet.getResource(uri, true);
			} catch (final Exception e) {
				// Clear the resource set here to remove the bad
				// resource from above
				try {
					this.scaModelResourceSet.getResources().clear();
				} catch (Exception e2) {
					//PASS
				}
				this.registryResource = this.scaModelResourceSet.createResource(uri, ScaPackage.eCONTENT_TYPE);
			}
		} catch (final IllegalStateException e1) {
			// This can be thrown by the getStateLocation() call if
			// the -noData option is specified. Therefore we will
			// load into memory
			this.registryResource = this.scaModelResourceSet.createResource(org.eclipse.emf.common.util.URI.createURI("virtual://instanceDomains.sca"),
					ScaPackage.eCONTENT_TYPE);
			ScaPlugin.getDefault().getLog().log(new Status(IStatus.WARNING, ScaPlugin.getPluginId(), "Using memory store for sca domains.  Will not presist domain connections.", e1));
		}

		this.scaDomainManagerRegistry = ScaDomainManagerRegistry.Util.getScaDomainManagerRegistry(this.registryResource);
		if (this.scaDomainManagerRegistry == null) { // SUPPRESS CHECKSTYLE DoubleCheck
			this.editingDomain.getCommandStack().execute(new ScaModelCommand() {

				public void execute() {
					scaDomainManagerRegistry = initScaResource(registryResource);
					addDomainManagerRegistryListener();
					addDomainManagerListeners();
				}
			});

		} else {
			addDomainManagerRegistryListener();
			addDomainManagerListeners();
		}
	}

	/**
	 * @since 6.1
	 */
	protected void removeDomainManagerPropertiesListeners(ScaDomainManager domain) {
		Properties props = domain.getConnectionPropertiesContainer();
		props.eAdapters().remove(propListener);
		domain.eAdapters().remove(this.domainManagerListener);
	}

	/**
	 * @since 6.1
	 */
	protected void addDomainManagerPropertiesListeners(ScaDomainManager domain) {
		//Listen for connection properties changes
		Properties props = domain.getConnectionPropertiesContainer();
		if (!props.eAdapters().contains(propListener)) {
			props.eAdapters().add(propListener);
		}
		//Listen for changes to other properties, such as domain name, auto-connect, etc.
		if (!domain.eAdapters().contains(this.domainManagerListener)) {
			domain.eAdapters().add(this.domainManagerListener);
		}
	}


	private void addDomainManagerListeners() {
		for (ScaDomainManager domain : this.scaDomainManagerRegistry.getDomains()) {
			addDomainManagerPropertiesListeners(domain);
		}
	}

	private void addDomainManagerRegistryListener() {
		this.scaDomainManagerRegistry.eAdapters().add(this.domainManagerRegistrylistener);
	}

	private void saveDomainManagerRegistryResource() {
		if (this.scaDomainManagerRegistry != null) {
			ScaModelCommand.execute(this.scaDomainManagerRegistry, new ScaModelCommand() {

				public void execute() {
					if (registryResource != null) {
						Resource resource = registryResource;

						if (resource != null) {
							try {
								resource.save(null);
							} catch (IOException e) {
								ScaPlugin.getDefault().getLog().log(new Status(Status.ERROR, ScaPlugin.getPluginId(), "Failed to save Domain connections.", e));
							}
						}
					}
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

	private void connectOnStartup() {
		this.startupJob = new Job("Connecting to domains on startup...") {

			@Override
			protected IStatus run(final IProgressMonitor monitor) {
				try {
					monitor.beginTask("Connecting to domains on startup...", IProgressMonitor.UNKNOWN);
					ScaDomainManager[] domains;
					try {
						domains = TransactionUtil.runExclusive(editingDomain, new RunnableWithResult.Impl<ScaDomainManager[]>() {

							public void run() {
								setResult(scaDomainManagerRegistry.getDomains().toArray(new ScaDomainManager[scaDomainManagerRegistry.getDomains().size()]));
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

}

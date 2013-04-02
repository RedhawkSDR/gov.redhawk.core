/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.core.internal.resourcefactory;

import gov.redhawk.core.filemanager.FileManagerImpl;
import gov.redhawk.core.filemanager.IFileManager;
import gov.redhawk.core.filemanager.filesystem.BundleFileSystem;
import gov.redhawk.core.resourcefactory.IResourceFactoryProvider;
import gov.redhawk.core.resourcefactory.IResourceFactoryRegistry;
import gov.redhawk.core.resourcefactory.ResourceDesc;
import gov.redhawk.core.resourcefactory.ResourceFactoryPlugin;
import gov.redhawk.sca.util.ORBUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.dynamichelpers.ExtensionTracker;
import org.eclipse.core.runtime.dynamichelpers.IExtensionChangeHandler;
import org.eclipse.core.runtime.dynamichelpers.IExtensionTracker;
import org.eclipse.core.runtime.dynamichelpers.IFilter;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;
import org.osgi.framework.Bundle;

import CF.FileException;
import CF.FileSystemHelper;
import CF.FileSystemPOATie;
import CF.InvalidFileName;
import CF.ResourceFactory;
import CF.ResourceFactoryHelper;
import CF.ResourceFactoryOperations;
import CF.ResourceFactoryPOATie;
import CF.FileManagerPackage.InvalidFileSystem;
import CF.FileManagerPackage.MountPointAlreadyExists;
import CF.FileManagerPackage.NonExistentMount;

/**
 * 
 */
public enum ResourceFactoryRegistry implements IResourceFactoryRegistry, IExtensionChangeHandler {
	INSTANCE;

	private static class FactoryDesc {
		private final String id;
		private final IResourceFactoryProvider provider;

		public FactoryDesc(final String id, final IResourceFactoryProvider provider) {
			super();
			this.id = id;
			this.provider = provider;
		}

		public String getId() {
			return this.id;
		}

		public IResourceFactoryProvider getProvider() {
			return this.provider;
		}

	}

	private static final String EP_ID = "resourceFactories";
	private static final String ELM_FACTORY = "resourceFactory";
	private static final String ELM_PROVIDER = "factoryProvider";
	private ExtensionTracker tracker;

	private final Map<String, SortedSet<ResourceDesc>> registry = Collections.synchronizedMap(new HashMap<String, SortedSet<ResourceDesc>>());
	private final List<IResourceFactoryProvider> providerRegistry = Collections.synchronizedList(new ArrayList<IResourceFactoryProvider>());
	private final FileManagerImpl fileManager = new FileManagerImpl();
	private final ORB orb;
	private final POA poa;

	private ResourceFactoryRegistry() {
		for (final String s : new String[]{"components","waveforms","devices","services"}) {
			try {
				this.fileManager.mkdir(s);
			} catch (final InvalidFileName e) {
				// PASS
			} catch (final FileException e) {
				// PASS
			}
		}
		this.orb = ORBUtil.init(null);
		try {
			this.poa = POAHelper.narrow(this.orb.resolve_initial_references("RootPOA"));
			this.poa.the_POAManager().activate();
		} catch (final InvalidName e) {
			throw new RuntimeException(e);
		} catch (final AdapterInactive e) {
			throw new RuntimeException(e);
		}

		final IExtensionRegistry reg = Platform.getExtensionRegistry();

		final IExtensionPoint ep = reg.getExtensionPoint(ResourceFactoryPlugin.ID, ResourceFactoryRegistry.EP_ID);

		this.tracker = new ExtensionTracker(reg);

		if (ep != null) {
			final IFilter filter = ExtensionTracker.createExtensionPointFilter(ep);
			this.tracker.registerHandler(this, filter);
			final IExtension[] extensions = ep.getExtensions();
			for (final IExtension extension : extensions) {
				addExtension(this.tracker, extension);
			}
		}
	}

	public void addExtension(final IExtensionTracker tracker, final IExtension extension) {
		for (final IConfigurationElement element : extension.getConfigurationElements()) {
			final String refId = element.getAttribute("redId");
			final String id = element.getAttribute("id");
			if (element.getName().equals(ResourceFactoryRegistry.ELM_FACTORY)) {
				try {
					addFactory(extension, element);
				} catch (final CoreException e) {
					ResourceFactoryPlugin.getDefault().getLog().log(new Status(IStatus.ERROR, ResourceFactoryPlugin.ID, "Failed to add Factory: " + refId, e));
				}
			} else if (element.getName().equals(ResourceFactoryRegistry.ELM_PROVIDER)) {
				try {
					addFactoryProvider(element, extension);
				} catch (final CoreException e) {
					ResourceFactoryPlugin.getDefault().getLog()
					        .log(new Status(IStatus.ERROR, ResourceFactoryPlugin.ID, "Failed to add Factory Provider: " + id, e));
				}
			}
		}
	}

	private void addFactoryProvider(final IConfigurationElement element, final IExtension extension) throws CoreException {
		final String id = element.getAttribute("id");
		final IResourceFactoryProvider provider = (IResourceFactoryProvider) element.createExecutableExtension("class");
		final FactoryDesc desc = new FactoryDesc(id, provider);
		SafeRunner.run(new ISafeRunnable() {

			public void run() throws Exception {
				provider.init(ResourceFactoryRegistry.this, ResourceFactoryRegistry.this.orb, ResourceFactoryRegistry.this.poa);
			}

			public void handleException(final Throwable exception) {
				// PASS

			}
		});

		this.providerRegistry.add(provider);
		this.tracker.registerObject(extension, desc, IExtensionTracker.REF_SOFT);
	}

	private void addFactory(final IExtension extension, final IConfigurationElement element) throws CoreException {
		final String profilePathStr = element.getAttribute("profile");
		final ResourceFactoryOperations factory = (ResourceFactoryOperations) element.createExecutableExtension("factory");
		final String bundleName = element.getContributor().getName();
		final Bundle bundle = Platform.getBundle(bundleName);
		final Path profilePath = new Path(profilePathStr);
		org.omg.CORBA.Object ref;
		try {
			final BundleFileSystem bundleFs = new BundleFileSystem(this.orb, this.poa, bundle, profilePath);
			ref = this.poa.servant_to_reference(new FileSystemPOATie(bundleFs));

			final ResourceFactory factoryRef = ResourceFactoryHelper.narrow(this.poa.servant_to_reference(new ResourceFactoryPOATie(factory)));


			final ResourceDesc desc = new ResourceDesc(FileSystemHelper.narrow(ref), profilePath.lastSegment(), factory.identifier(), factoryRef, -1);
			addResourceFactory(desc);
			this.tracker.registerObject(extension, desc, IExtensionTracker.REF_SOFT);
		} catch (final ServantNotActive e) {
			ResourceFactoryPlugin.getDefault().getLog()
			        .log(new Status(IStatus.ERROR, ResourceFactoryPlugin.ID, "Failed to add bundle resource factory: " + profilePath));
		} catch (final WrongPolicy e) {
			ResourceFactoryPlugin.getDefault().getLog()
			        .log(new Status(IStatus.ERROR, ResourceFactoryPlugin.ID, "Failed to add bundle resource factory: " + profilePath));
		} catch (final MountPointAlreadyExists e) {
			ResourceFactoryPlugin.getDefault().getLog()
			        .log(new Status(IStatus.ERROR, ResourceFactoryPlugin.ID, "Failed to add bundle resource factory: " + profilePath));
		} catch (final InvalidFileName e) {
			ResourceFactoryPlugin.getDefault().getLog()
			        .log(new Status(IStatus.ERROR, ResourceFactoryPlugin.ID, "Failed to add bundle resource factory: " + profilePath));
		} catch (final InvalidFileSystem e) {
			ResourceFactoryPlugin.getDefault().getLog()
			        .log(new Status(IStatus.ERROR, ResourceFactoryPlugin.ID, "Failed to add bundle resource factory: " + profilePath));
		}
	}

	public void removeExtension(final IExtension extension, final Object[] objects) {
		for (final Object obj : objects) {
			if (obj instanceof ResourceDesc) {
				removeResourceFactory((ResourceDesc) obj);
			} else if (obj instanceof FactoryDesc) {
				((FactoryDesc) obj).provider.dispose();
			}
		}

	}

	public void addResourceFactory(final ResourceDesc desc) throws MountPointAlreadyExists, InvalidFileName, InvalidFileSystem {
		synchronized (this.registry) {
			mount(desc);
			SortedSet<ResourceDesc> descList = this.registry.get(desc.getRefId());
			if (descList == null) {
				descList = Collections.synchronizedSortedSet(new TreeSet<ResourceDesc>());

				this.registry.put(desc.getRefId(), descList);
			}
			descList.add(desc);
		}
	}

	private void mount(final ResourceDesc desc) throws MountPointAlreadyExists, InvalidFileName, InvalidFileSystem {
		this.fileManager.mount(getMountPoint(desc), desc.getRoot());
	}

	private void unmount(final ResourceDesc desc) throws NonExistentMount {
		this.fileManager.unmount(getMountPoint(desc));
	}

	private String getMountPoint(final ResourceDesc desc) {
		int index = desc.getProfile().lastIndexOf(File.separatorChar);
		if (index > 0) {
			return desc.getProfile().substring(0, index);
		} else {
			return desc.getProfile();
		}
	}

	public void removeResourceFactory(final ResourceDesc desc) {
		synchronized (this.registry) {
			try {
				unmount(desc);
			} catch (final NonExistentMount e) {
				// PASS
			}
			final SortedSet<ResourceDesc> descList = this.registry.get(desc.getRefId());
			if (descList != null) {
				descList.remove(desc);
				if (descList.isEmpty()) {
					this.registry.remove(desc.getRefId());
				}
			}
		}
	}

	public void dispose() {
		synchronized (this.providerRegistry) {
			for (final IResourceFactoryProvider provider : this.providerRegistry) {
				provider.dispose();
			}
			this.providerRegistry.clear();
		}

		synchronized (this.registry) {
			for (final SortedSet<ResourceDesc> set : this.registry.values()) {
				synchronized (set) {
					for (final ResourceDesc desc : set) {
						desc.dispose();
					}
					set.clear();
				}
			}
			this.registry.clear();
		}
	}

	public IFileManager getFileManager() {
		return this.fileManager;
	}

	public ResourceDesc[] getResources() {
		final List<ResourceDesc> retVal = new ArrayList<ResourceDesc>();
		final Object[] array = this.registry.values().toArray();
		for (final Object obj : array) {
			final SortedSet< ? > value = (SortedSet< ? >) obj;
			if (!value.isEmpty()) {
				retVal.add((ResourceDesc) value.first());
			}
		}
		return retVal.toArray(new ResourceDesc[retVal.size()]);
	}

	public ResourceDesc getDescByProfile(final String profile) {
		if (profile == null) {
			return null;
		}
		synchronized (this.registry) {
			for (final SortedSet<ResourceDesc> descSet : this.registry.values()) {
				for (final ResourceDesc desc : descSet) {
					if (profile.equals(desc.getProfile())) {
						return desc;
					}
				}
			}
		}
		return null;
	}

	public ResourceDesc getDescByID(final String refID) {
		final SortedSet<ResourceDesc> descList = this.registry.get(refID);
		if (descList != null) {
			return descList.first();
		} else {
			return null;
		}
	}

}

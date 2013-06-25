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
package gov.redhawk.core.resourcefactory;

import java.io.File;

import gov.redhawk.core.filemanager.filesystem.BundleFileSystem;
import gov.redhawk.core.filemanager.filesystem.FileStoreFileSystem;
import gov.redhawk.core.filemanager.filesystem.JavaFileSystem;
import gov.redhawk.sca.util.OrbSession;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;
import org.osgi.framework.Bundle;

import CF.FileSystem;
import CF.FileSystemHelper;
import CF.FileSystemOperations;
import CF.FileSystemPOATie;
import CF.ResourceFactory;
import CF.ResourceFactoryHelper;
import CF.ResourceFactoryOperations;
import CF.ResourceFactoryPOATie;
import CF.ResourceFactoryPackage.ShutdownFailure;

/**
 * @noextend This class is not intended to be subclassed by clients.
 */
public abstract class ResourceDesc {

	private OrbSession session = OrbSession.createSession();
	private final String identifier;
	private String name;
	private String description;
	private final ResourceFactoryOperations factory;
	private final FileSystemOperations fileSystem;
	private ResourceFactory factoryRef;
	private FileSystem fileSystemRef;
	private final URI resourceURI;
	private String[] launchModes = new String[] { "run" };
	private String category;
	private final String profile;
	private final String version;

	/**
	 * @since 2.0
	 */
	public ResourceDesc(String identifier, URI resourceURI, String profile, final String version, ResourceFactoryOperations factory) {
		this.factory = factory;
		this.profile = profile;
		this.identifier = identifier;
		this.version = version;
		this.resourceURI = resourceURI;
		FileSystemOperations fs;
		try {
			fs = createFileSystem();
		} catch (CoreException e) {
			ResourceFactoryPlugin.log("Failed to create Resource Descriptor file system: " + name, e);
			fs = null;
		}
		this.fileSystem = fs;
	}

	public String getProfile() {
		return profile;
	}

	/**
	 * @since 2.0
	 */
	public URI getResourceURI() {
		return resourceURI;
	}

	/**
	 * @since 2.0
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @since 2.0
	 */
	public String[] getLaunchModes() {
		return launchModes;
	}

	/**
	 * @since 2.0
	 */
	public String getName() {
		return name;
	}

	/**
	 * @since 2.0
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * @since 2.0
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @since 2.0
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @since 2.0
	 */
	public ResourceFactoryOperations getFactory() {
		return factory;
	}

	/**
	 * @since 2.0
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @since 2.0
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @since 2.0
	 */
	public void setLaunchModes(String[] launchModes) {
		this.launchModes = launchModes;
	}

	/**
	 * @since 2.0
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	public void dispose() {
		if (this.factory != null) {
			try {
				this.factory.shutdown();
			} catch (final ShutdownFailure e) {
				// PASS
			}
		}
		if (fileSystemRef != null) {
			fileSystemRef._release();
			fileSystemRef = null;
		}
		if (factoryRef != null) {
			factoryRef._release();
			factoryRef = null;
		}
		if (session != null) {
			session.dispose();
			session = null;
		}
	}

	/**
	 * @since 2.0
	 */
	public ResourceFactory getFactoryRef() {
		if (factoryRef == null) {
			try {
				factoryRef = ResourceFactoryHelper.narrow(session.getPOA().servant_to_reference(new ResourceFactoryPOATie(factory)));
			} catch (ServantNotActive e) {
				ResourceFactoryPlugin.log("Failed to create Resource Descriptor factory: " + name, e);
			} catch (WrongPolicy e) {
				ResourceFactoryPlugin.log("Failed to create Resource Descriptor factory: " + name, e);
			} catch (CoreException e) {
				ResourceFactoryPlugin.log("Failed to create Resource Descriptor factory: " + name, e);
			}
		}
		return factoryRef;
	}

	/**
	 * @since 2.0
	 */
	public FileSystemOperations getFileSystem() {
		return fileSystem;
	}

	/**
	 * @since 2.0
	 */
	public FileSystem getFileSystemRef() {
		if (fileSystemRef == null) {
			try {
				fileSystemRef = FileSystemHelper.narrow(session.getPOA().servant_to_reference(new FileSystemPOATie(createFileSystem())));
			} catch (ServantNotActive e) {
				ResourceFactoryPlugin.log("Failed to create Resource Descriptor file system: " + name, e);
			} catch (WrongPolicy e) {
				ResourceFactoryPlugin.log("Failed to create Resource Descriptor file system: " + name, e);
			} catch (CoreException e) {
				ResourceFactoryPlugin.log("Failed to create Resource Descriptor file system: " + name, e);
			}
		}
		return fileSystemRef;
	}

	private FileSystemOperations createFileSystem() throws CoreException {
		if (this.resourceURI.isPlatformPlugin()) {
			Bundle bundle = Platform.getBundle(this.resourceURI.segment(1));
			IPath path = new Path(resourceURI.toPlatformString(true));
			path = path.removeFirstSegments(1); // Remove plugin ID
			path = path.removeLastSegments(1); // Remove file name
			return new BundleFileSystem(session.getOrb(), session.getPOA(), bundle, path);
		} else if (this.resourceURI.isPlatformResource()) {
			String path = this.resourceURI.toPlatformString(true);
			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(path));
			IContainer parent = file.getParent();
			java.net.URI uri = parent.getLocationURI();
			IFileStore store = EFS.getStore(uri);
			return new FileStoreFileSystem(session.getOrb(), session.getPOA(), store);
		} else if (this.resourceURI.isFile()) {
			File file = new File(resourceURI.toFileString());
			File root = file.getParentFile();
			return new JavaFileSystem(session.getOrb(), session.getPOA(), root);
		} else {
			IFileStore store = EFS.getStore(java.net.URI.create(resourceURI.toString()));
			IFileStore parent = store.getParent();
			return new FileStoreFileSystem(session.getOrb(), session.getPOA(), parent);
		}
	}

}

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

import gov.redhawk.core.filemanager.filesystem.BundleFileSystem;
import gov.redhawk.core.filemanager.filesystem.FileStoreFileSystem;
import gov.redhawk.sca.util.ORBUtil;
import gov.redhawk.sca.util.OrbSession;
import mil.jpeojtrs.sca.util.ScaFileSystemConstants;

import java.io.File;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
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

	private OrbSession session = OrbSession.createSession(ResourceFactoryPlugin.ID);
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
	 * @since 4.0
	 */
	public ResourceDesc(String identifier, URI resourceURI, final String version, ResourceFactoryOperations factory) {
		this.factory = factory;
		this.identifier = identifier;
		this.version = version;
		this.resourceURI = resourceURI;
		this.profile = createProfile();
		this.fileSystem = createFileSystem();
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
			ORBUtil.release(fileSystemRef);
			fileSystemRef = null;
		}
		if (factoryRef != null) {
			ORBUtil.release(factoryRef);
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
				ResourceFactoryPlugin.logError("Failed to create Resource Descriptor factory: " + name, e);
			} catch (WrongPolicy e) {
				ResourceFactoryPlugin.logError("Failed to create Resource Descriptor factory: " + name, e);
			} catch (CoreException e) {
				ResourceFactoryPlugin.logError("Failed to create Resource Descriptor factory: " + name, e);
			}
		}
		return factoryRef;
	}

	/**
	 * Get the file system
	 * @return The file system, or null to indicate the local file system
	 * @since 2.0
	 */
	public FileSystemOperations getFileSystem() {
		return fileSystem;
	}

	/**
	 * Get a CORBA reference for the file system servant.
	 * @return The file system CORBA object, or null to indicate the local file system
	 * @since 2.0
	 */
	public FileSystem getFileSystemRef() {
		if (fileSystemRef == null) {
			try {
				fileSystemRef = FileSystemHelper.narrow(session.getPOA().servant_to_reference(new FileSystemPOATie(createFileSystem())));
			} catch (ServantNotActive e) {
				ResourceFactoryPlugin.logError("Failed to create Resource Descriptor file system: " + name, e);
			} catch (WrongPolicy e) {
				ResourceFactoryPlugin.logError("Failed to create Resource Descriptor file system: " + name, e);
			} catch (CoreException e) {
				ResourceFactoryPlugin.logError("Failed to create Resource Descriptor file system: " + name, e);
			}
		}
		return fileSystemRef;
	}

	/**
	 * Create an appropriate file system based on the resource URI. For anything referencing the local file system,
	 * no file system need be created (the local file system is provided by default)
	 * @return
	 * @throws CoreException
	 */
	private FileSystemOperations createFileSystem() {
		try {
			if (ScaFileSystemConstants.SCHEME.equals(this.resourceURI.scheme())) {
				return null;
			} else if (this.resourceURI.isPlatformPlugin()) {
				Bundle bundle = Platform.getBundle(this.resourceURI.segment(1));
				IPath path = new Path(resourceURI.toPlatformString(true));
				path = path.removeFirstSegments(1); // Remove plugin ID
				path = path.removeLastSegments(1); // Remove file name
				return new BundleFileSystem(session.getOrb(), session.getPOA(), bundle, path);
			} else if (this.resourceURI.isPlatformResource()) {
				return null;
			} else if (this.resourceURI.isFile()) {
				return null;
			} else {
				// Use EFS; note this code path is not usual or expected!
				IFileStore store = EFS.getStore(java.net.URI.create(resourceURI.toString()));
				IFileStore parent = store.getParent();
				return new FileStoreFileSystem(session.getOrb(), session.getPOA(), parent);
			}
		} catch (CoreException e) {
			String errorMsg = String.format("Unable to create file system for resource URI '%s'", this.resourceURI);
			ResourceFactoryPlugin.logError(errorMsg, e);
			return null;
		}
	}

	/**
	 * Creates an appropriate profile based on the resource URI
	 * @return
	 * @see gov.redhawk.ide.debug.internal.variables.ProfileNameVariableResolver
	 */
	private String createProfile() {
		if (ScaFileSystemConstants.SCHEME.equals(this.resourceURI.scheme())) {
			// Use EFS to convert the sca URI to an absolute file path
			try {
				IFileStore store = EFS.getStore(java.net.URI.create(this.resourceURI.toString()));
				File file = store.toLocalFile(EFS.NONE, new NullProgressMonitor());
				return file.getAbsolutePath();
			} catch (CoreException e) {
				ResourceFactoryPlugin.logError("Unable to convert sca URI to absolute file path", e);
				return null;
			}
		} else if (this.resourceURI.isPlatformPlugin()) {
			// Use 'bundle' as the first directory segment, then the platform string
			return new Path("/bundle").append(resourceURI.toPlatformString(true)).toString();
		} else if (this.resourceURI.isPlatformResource()) {
			// Get an absolute file path for the workspace resource
			String path = this.resourceURI.toPlatformString(true);
			IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(path));
			return file.getLocation().toString();
		} else if (this.resourceURI.isFile()) {
			return this.resourceURI.path();
		} else {
			// Use the scheme as the first directory segment, then the path
			// Note this code path is not usual or expected!
			return new Path(this.resourceURI.scheme()).append(this.resourceURI.path()).toString();
		}
	}

}

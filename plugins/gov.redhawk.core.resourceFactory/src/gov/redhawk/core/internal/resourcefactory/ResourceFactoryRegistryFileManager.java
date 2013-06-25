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
package gov.redhawk.core.internal.resourcefactory;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import CF.FileException;
import CF.FileSystem;
import CF.FileSystemHelper;
import CF.FileSystemPOATie;
import CF.InvalidFileName;
import CF.FileManagerPackage.InvalidFileSystem;
import CF.FileManagerPackage.MountPointAlreadyExists;
import CF.FileManagerPackage.NonExistentMount;

import gov.redhawk.core.filemanager.FileManagerImpl;
import gov.redhawk.core.resourcefactory.ResourceDesc;
import gov.redhawk.core.resourcefactory.ResourceFactoryPlugin;
import gov.redhawk.sca.util.OrbSession;

public class ResourceFactoryRegistryFileManager extends FileManagerImpl {
	private Map<IPath, ProxyFileSystem> proxies = new HashMap<IPath, ProxyFileSystem>();
	private OrbSession session = OrbSession.createSession();

	public synchronized void mount(ResourceDesc desc, int priority) throws CoreException {
		String profile = desc.getProfile();
		Path path = new Path(profile);
		IPath mountPoint = path.removeLastSegments(1);
		ProxyFileSystem proxy = createProxyFileSystem(mountPoint);
		proxy.mount(desc, priority);
	}

	public synchronized void unmount(ResourceDesc desc) {
		String profile = desc.getProfile();
		Path path = new Path(profile);
		IPath mountPoint = path.removeLastSegments(1);
		ProxyFileSystem proxy = proxies.get(mountPoint);
		if (proxy != null) {
			proxy.unmount(desc);
			if (proxy.isEmpty()) {
				try {
					unmount(mountPoint.toPortableString());
				} catch (NonExistentMount e) {
					// PASS
				}
				proxies.remove(proxy);
			}
		}

	}

	private ProxyFileSystem createProxyFileSystem(IPath mountPoint) throws CoreException {
		ProxyFileSystem proxy = proxies.get(mountPoint);
		try {
			if (proxy == null) {
				String parent = mountPoint.removeLastSegments(1).toPortableString();
				if (!exists(parent)) {
					mkdir(parent);
				}
				proxy = new ProxyFileSystem();
				proxies.put(mountPoint, proxy);
				FileSystem fs = FileSystemHelper.narrow(session.getPOA().servant_to_reference(new FileSystemPOATie(proxy)));
				mount(mountPoint.toPortableString(), fs);
			}
			return proxy;
		} catch (InvalidFileName e) {
			throw new CoreException(new Status(Status.ERROR, ResourceFactoryPlugin.ID, "Failed to create proxy file system: " + mountPoint, e));
		} catch (FileException e) {
			throw new CoreException(new Status(Status.ERROR, ResourceFactoryPlugin.ID, "Failed to create proxy file system: " + mountPoint, e));
		} catch (ServantNotActive e) {
			throw new CoreException(new Status(Status.ERROR, ResourceFactoryPlugin.ID, "Failed to create proxy file system: " + mountPoint, e));
		} catch (WrongPolicy e) {
			throw new CoreException(new Status(Status.ERROR, ResourceFactoryPlugin.ID, "Failed to create proxy file system: " + mountPoint, e));
		} catch (InvalidFileSystem e) {
			throw new CoreException(new Status(Status.ERROR, ResourceFactoryPlugin.ID, "Failed to create proxy file system: " + mountPoint, e));
		} catch (MountPointAlreadyExists e) {
			throw new CoreException(new Status(Status.ERROR, ResourceFactoryPlugin.ID, "Failed to create proxy file system: " + mountPoint, e));
		}
	}

	public ResourceDesc getResourceDesc(String profile) {
		Path path = new Path(profile);
		IPath mountPoint = path.removeLastSegments(1);
		ProxyFileSystem proxy = proxies.get(mountPoint);
		if (proxy != null) {
			return proxy.getResourceDesc();
		}
		return null;
	}
}

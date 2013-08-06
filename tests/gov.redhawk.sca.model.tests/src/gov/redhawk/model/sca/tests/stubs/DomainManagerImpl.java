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
package gov.redhawk.model.sca.tests.stubs;

import gov.redhawk.core.filemanager.FileManagerImpl;
import gov.redhawk.core.filemanager.filesystem.JavaFileSystem;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import mil.jpeojtrs.sca.dmd.DomainManagerConfiguration;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Assert;
import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import CF.Application;
import CF.ApplicationFactory;
import CF.ApplicationFactoryHelper;
import CF.ApplicationFactoryPOATie;
import CF.Device;
import CF.DeviceManager;
import CF.DomainManagerOperations;
import CF.ErrorNumberType;
import CF.FileManager;
import CF.FileManagerHelper;
import CF.FileManagerPOATie;
import CF.FileSystemHelper;
import CF.FileSystemPOATie;
import CF.InvalidFileName;
import CF.InvalidObjectReference;
import CF.InvalidProfile;
import CF.DomainManagerPackage.AlreadyConnected;
import CF.DomainManagerPackage.ApplicationAlreadyInstalled;
import CF.DomainManagerPackage.ApplicationInstallationError;
import CF.DomainManagerPackage.ApplicationUninstallationError;
import CF.DomainManagerPackage.DeviceManagerNotRegistered;
import CF.DomainManagerPackage.InvalidEventChannelName;
import CF.DomainManagerPackage.InvalidIdentifier;
import CF.DomainManagerPackage.NotConnected;
import CF.DomainManagerPackage.RegisterError;
import CF.DomainManagerPackage.UnregisterError;
import CF.FileManagerPackage.InvalidFileSystem;
import CF.FileManagerPackage.MountPointAlreadyExists;

/**
 * 
 */
public class DomainManagerImpl extends AbstractResourceImpl implements DomainManagerOperations {

	private final List<DeviceManager> deviceManagers = new ArrayList<DeviceManager>();
	private final List<ApplicationFactory> applicationFactories = new ArrayList<ApplicationFactory>();
	private final List<Application> applications = new ArrayList<Application>();
	private final FileManager fileManager;
	private final DomainManagerConfiguration dmd;
	private ResourceSetImpl resourceSet;

	public DomainManagerImpl(File domRoot, String dmdPath, String identifier, String label, ORB orb, POA poa) throws InvalidFileName, InvalidFileSystem,
		MountPointAlreadyExists, ServantNotActive, WrongPolicy {
		super(identifier, label, orb, poa);
		FileManagerImpl fileManagerImpl = new FileManagerImpl();
		this.fileManager = FileManagerHelper.narrow(poa.servant_to_reference(new FileManagerPOATie(fileManagerImpl)));

		JavaFileSystem componentsFileSystem = new JavaFileSystem(orb, poa, new File(domRoot, "components"));
		JavaFileSystem domainFileSystem = new JavaFileSystem(orb, poa, new File(domRoot, "domain"));
		JavaFileSystem mgrFileSystem = new JavaFileSystem(orb, poa, new File(domRoot, "mgr"));
		JavaFileSystem waveformsFileSystem = new JavaFileSystem(orb, poa, new File(domRoot, "waveforms"));

		this.fileManager.mount("components", FileSystemHelper.narrow(poa.servant_to_reference(new FileSystemPOATie(componentsFileSystem))));
		this.fileManager.mount("domain", FileSystemHelper.narrow(poa.servant_to_reference(new FileSystemPOATie(domainFileSystem))));
		this.fileManager.mount("mgr", FileSystemHelper.narrow(poa.servant_to_reference(new FileSystemPOATie(mgrFileSystem))));
		this.fileManager.mount("waveforms", FileSystemHelper.narrow(poa.servant_to_reference(new FileSystemPOATie(waveformsFileSystem))));
		URI uri = ScaURIFactory.createURI(dmdPath, fileManager);

		this.resourceSet = new ResourceSetImpl();
		this.dmd = DomainManagerConfiguration.Util.getDomainManagerConfiguration(resourceSet.getResource(uri, true));
		Assert.assertNotNull(this.dmd);

		if (this.compId == null) {
			this.compId = dmd.getId();
		}

		if (this.compName == null) {
			this.compName = dmd.getName();
		}
	}

	public void reset() {
		deviceManagers.clear();
		applicationFactories.clear();
		applications.clear();
	}

	@Override
	protected POA getPoa() {
		return super.getPoa();
	}

	@Override
	protected ORB getOrb() {
		return super.getOrb();
	}

	/**
	 * {@inheritDoc}
	 */
	public String domainManagerProfile() {
		return this.dmd.eResource().getURI().path();
	}

	/**
	 * {@inheritDoc}
	 */
	public DeviceManager[] deviceManagers() {
		return this.deviceManagers.toArray(new DeviceManager[this.deviceManagers.size()]);
	}

	/**
	 * {@inheritDoc}
	 */
	public Application[] applications() {
		return this.applications.toArray(new Application[this.applications.size()]);
	}

	/**
	 * {@inheritDoc}
	 */
	public ApplicationFactory[] applicationFactories() {
		return this.applicationFactories.toArray(new ApplicationFactory[this.applicationFactories.size()]);
	}

	/**
	 * {@inheritDoc}
	 */
	public FileManager fileMgr() {
		return this.fileManager;
	}

	/**
	 * {@inheritDoc}
	 */
	public void registerDevice(final Device registeringDevice, final DeviceManager registeredDeviceMgr) throws InvalidObjectReference, InvalidProfile,
		DeviceManagerNotRegistered, RegisterError {
		if (registeredDeviceMgr == null) {
			throw new InvalidObjectReference("");
		}
		registeredDeviceMgr.registerDevice(registeringDevice);
	}

	/**
	 * {@inheritDoc}
	 */
	public void registerDeviceManager(final DeviceManager deviceMgr) throws InvalidObjectReference, InvalidProfile, RegisterError {
		if (deviceMgr != null) {
			this.deviceManagers.add(deviceMgr);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void unregisterDeviceManager(final DeviceManager deviceMgr) throws InvalidObjectReference, UnregisterError {
		this.deviceManagers.remove(deviceMgr);
	}

	/**
	 * {@inheritDoc}
	 */
	public void unregisterDevice(final Device unregisteringDevice) throws InvalidObjectReference, UnregisterError {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	public void installApplication(final String profileFileName) throws InvalidProfile, InvalidFileName, ApplicationInstallationError,
		ApplicationAlreadyInstalled {
		URI uri = ScaURIFactory.createURI(profileFileName, fileManager);
		try {
			SoftwareAssembly sad = SoftwareAssembly.Util.getSoftwareAssembly(resourceSet.getResource(uri, true));
			ApplicationFactoryImpl factory = new ApplicationFactoryImpl(sad, this);
			this.applicationFactories.add(ApplicationFactoryHelper.narrow(poa.servant_to_reference(new ApplicationFactoryPOATie(factory))));
		} catch (ServantNotActive e) {
			throw new ApplicationInstallationError(e.getMessage(), ErrorNumberType.CF_EIO, profileFileName);
		} catch (WrongPolicy e) {
			throw new ApplicationInstallationError(e.getMessage(), ErrorNumberType.CF_EIO, profileFileName);
		} catch (Exception e) {
			throw new InvalidProfile(e.getMessage());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void uninstallApplication(final String applicationId) throws InvalidIdentifier, ApplicationUninstallationError {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 */
	public void registerService(final Object registeringService, final DeviceManager registeredDeviceMgr, final String name) throws InvalidObjectReference,
		DeviceManagerNotRegistered, RegisterError {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 */
	public void unregisterService(final Object unregisteringService, final String name) throws InvalidObjectReference, UnregisterError {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 */
	public void registerWithEventChannel(final Object registeringObject, final String registeringId, final String eventChannelName)
		throws InvalidObjectReference, InvalidEventChannelName, AlreadyConnected {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	public void unregisterFromEventChannel(final String unregisteringId, final String eventChannelName) throws InvalidEventChannelName, NotConnected {
		// TODO Auto-generated method stub

	}

	public void registerApplication(Application retVal) {
		// TODO Auto-generated method stub

	}

}

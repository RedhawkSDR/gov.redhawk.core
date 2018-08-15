/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.model.sca.tests.stubs;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Assert;
import org.omg.CORBA.Object;
import org.omg.CORBA.SystemException;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextPackage.AlreadyBound;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import CF.AllocationManager;
import CF.Application;
import CF.ApplicationFactory;
import CF.ApplicationFactoryHelper;
import CF.ApplicationFactoryPOATie;
import CF.ConnectionManager;
import CF.DataType;
import CF.Device;
import CF.DeviceAssignmentType;
import CF.DeviceManager;
import CF.DomainManager;
import CF.DomainManagerHelper;
import CF.DomainManagerOperations;
import CF.DomainManagerPOATie;
import CF.ErrorNumberType;
import CF.EventChannelManager;
import CF.EventChannelManagerHelper;
import CF.EventChannelManagerPOATie;
import CF.FileManager;
import CF.FileManagerHelper;
import CF.FileManagerPOATie;
import CF.FileSystemHelper;
import CF.FileSystemPOATie;
import CF.InvalidFileName;
import CF.InvalidObjectReference;
import CF.InvalidProfile;
import CF.ApplicationFactoryPackage.CreateApplicationError;
import CF.ApplicationFactoryPackage.CreateApplicationInsufficientCapacityError;
import CF.ApplicationFactoryPackage.CreateApplicationRequestError;
import CF.ApplicationFactoryPackage.InvalidInitConfiguration;
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
import CF.EventChannelManagerPackage.ChannelAlreadyExists;
import CF.EventChannelManagerPackage.OperationFailed;
import CF.EventChannelManagerPackage.OperationNotAllowed;
import CF.EventChannelManagerPackage.ServiceUnavailable;
import CF.FileManagerPackage.InvalidFileSystem;
import CF.FileManagerPackage.MountPointAlreadyExists;
import gov.redhawk.core.filemanager.FileManagerImpl;
import gov.redhawk.core.filemanager.filesystem.JavaFileSystem;
import gov.redhawk.sca.util.OrbSession;
import mil.jpeojtrs.sca.dmd.DomainManagerConfiguration;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;

public class DomainManagerImpl extends AbstractResourceImpl implements DomainManagerOperations {

	private final List<DomainManager> remoteDomMgrs = new ArrayList<DomainManager>();
	private final List<DeviceManager> deviceManagers = new ArrayList<DeviceManager>();
	private final Map<String, DeviceManager> deviceIDToDevMgr = new HashMap<String, DeviceManager>();
	private final List<ApplicationFactory> applicationFactories = new ArrayList<ApplicationFactory>();
	private final List<Application> applications = new ArrayList<Application>();

	private DomainManager domMgrRef;
	private NamingContext domMgrNamingContext;

	private FileManagerImpl fileManagerImpl;
	private final FileManager fileManagerRef;

	private EventChannelManagerImpl ecmImpl;
	private EventChannelManager ecmRef;

	private ResourceSetImpl resourceSet;
	private final DomainManagerConfiguration dmd;

	public DomainManagerImpl(File domRoot, String dmdPath, String identifier, String label, OrbSession session, NamingContext context)
		throws InvalidFileName, InvalidFileSystem, MountPointAlreadyExists, ServantNotActive, WrongPolicy, CoreException {
		super(identifier, label, dmdPath, session);

		org.omg.CORBA.Object ref = session.getPOA().servant_to_reference(new DomainManagerPOATie(this));
		this.domMgrRef = DomainManagerHelper.unchecked_narrow(ref);

		try {
			domMgrNamingContext = context.bind_new_context(new NameComponent[] { new NameComponent(identifier, "") });
		} catch (NotFound | AlreadyBound | CannotProceed | InvalidName e) {
			throw new CoreException(
				new Status(IStatus.ERROR, "gov.redhawk.sca.model.tests", "Cannot create domain manager naming context in naming service", e));
		}

		try {
			domMgrNamingContext.bind(new NameComponent[] { new NameComponent(identifier, "") }, this.domMgrRef);
		} catch (NotFound | AlreadyBound | CannotProceed | InvalidName e) {
			throw new CoreException(new Status(IStatus.ERROR, "gov.redhawk.sca.model.tests", "Cannot bind domain manager to naming service", e));
		}

		this.fileManagerImpl = new FileManagerImpl();
		this.fileManagerImpl.setLocalFileSystem(new JavaFileSystem(orb, poa, new File("/")));
		ref = poa.servant_to_reference(new FileManagerPOATie(this.fileManagerImpl));
		this.fileManagerRef = FileManagerHelper.unchecked_narrow(ref);

		JavaFileSystem componentsFileSystem = new JavaFileSystem(orb, poa, new File(domRoot, "components"));
		JavaFileSystem domainFileSystem = new JavaFileSystem(orb, poa, new File(domRoot, "domain"));
		JavaFileSystem mgrFileSystem = new JavaFileSystem(orb, poa, new File(domRoot, "mgr"));
		JavaFileSystem waveformsFileSystem = new JavaFileSystem(orb, poa, new File(domRoot, "waveforms"));

		mount("/components", componentsFileSystem);
		mount("/domain", domainFileSystem);
		mount("/domain2", domainFileSystem);
		mount("/mgr", mgrFileSystem);
		mount("/waveforms", waveformsFileSystem);
		URI uri = ScaURIFactory.createURI(dmdPath, this.fileManagerRef);

		this.ecmImpl = new EventChannelManagerImpl(session, domMgrNamingContext);
		ref = poa.servant_to_reference(new EventChannelManagerPOATie(ecmImpl));
		this.ecmRef = EventChannelManagerHelper.unchecked_narrow(ref);
		registerStandardEventChannels();

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

	private void mount(String mountPoint, JavaFileSystem fs) throws ServantNotActive, WrongPolicy, InvalidFileName, InvalidFileSystem, MountPointAlreadyExists {
		org.omg.CORBA.Object ref = poa.servant_to_reference(new FileSystemPOATie(fs));
		this.fileManagerImpl.mount(mountPoint, FileSystemHelper.unchecked_narrow(ref));
	}

	private void registerStandardEventChannels() throws CoreException {
		try {
			this.ecmImpl.create("IDM_Channel");
			this.ecmImpl.create("ODM_Channel");
		} catch (ChannelAlreadyExists | OperationNotAllowed | OperationFailed | ServiceUnavailable e) {
			throw new CoreException(new Status(IStatus.ERROR, "gov.redhawk.sca.model.tests", "Can't create initial event channels for domain manager", e));
		}
	}

	public DomainManager getRef() {
		return this.domMgrRef;
	}

	public void reset() throws CoreException {
		deviceManagers.clear();
		applicationFactories.clear();
		applications.clear();

		this.ecmImpl.reset();
		registerStandardEventChannels();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String domainManagerProfile() {
		return this.dmd.eResource().getURI().path();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DeviceManager[] deviceManagers() {
		return this.deviceManagers.toArray(new DeviceManager[this.deviceManagers.size()]);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Application[] applications() {
		return this.applications.toArray(new Application[this.applications.size()]);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ApplicationFactory[] applicationFactories() {
		return this.applicationFactories.toArray(new ApplicationFactory[this.applicationFactories.size()]);
	}

	@Override
	public EventChannelManager eventChannelMgr() {
		return ecmRef;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FileManager fileMgr() {
		return this.fileManagerRef;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerDevice(final Device registeringDevice, final DeviceManager registeredDeviceMgr)
		throws InvalidObjectReference, InvalidProfile, DeviceManagerNotRegistered, RegisterError {
		if (registeredDeviceMgr == null) {
			throw new InvalidObjectReference("");
		}
		registeredDeviceMgr.registerDevice(registeringDevice);
		synchronized (deviceIDToDevMgr) {
			deviceIDToDevMgr.put(registeringDevice.identifier(), registeredDeviceMgr);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerDeviceManager(final DeviceManager deviceMgr) throws InvalidObjectReference, InvalidProfile, RegisterError {
		if (deviceMgr != null) {
			this.deviceManagers.add(deviceMgr);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void unregisterDeviceManager(final DeviceManager deviceMgr) throws InvalidObjectReference, UnregisterError {
		this.deviceManagers.remove(deviceMgr);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void unregisterDevice(final Device unregisteringDevice) throws InvalidObjectReference, UnregisterError {
		if (unregisteringDevice == null) {
			throw new InvalidObjectReference("");
		}
		synchronized (deviceIDToDevMgr) {
			DeviceManager devMgr = deviceIDToDevMgr.remove(unregisteringDevice.identifier());
			if (devMgr == null) {
				throw new UnregisterError();
			}
			devMgr.unregisterDevice(unregisteringDevice);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void installApplication(final String profileFileName)
		throws InvalidProfile, InvalidFileName, ApplicationInstallationError, ApplicationAlreadyInstalled {
		URI uri = ScaURIFactory.createURI(profileFileName, fileManagerRef);
		try {
			SoftwareAssembly sad = SoftwareAssembly.Util.getSoftwareAssembly(resourceSet.getResource(uri, true));
			ApplicationFactoryImpl factory = new ApplicationFactoryImpl(sad, this);
			org.omg.CORBA.Object ref = poa.servant_to_reference(new ApplicationFactoryPOATie(factory));
			this.applicationFactories.add(ApplicationFactoryHelper.unchecked_narrow(ref));
		} catch (ServantNotActive e) {
			throw new ApplicationInstallationError(e.getMessage(), ErrorNumberType.CF_EIO, profileFileName);
		} catch (WrongPolicy e) {
			throw new ApplicationInstallationError(e.getMessage(), ErrorNumberType.CF_EIO, profileFileName);
		} catch (Exception e) { // SUPPRESS CHECKSTYLE Logged Catch all exception
			throw new InvalidProfile(e.getMessage());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void uninstallApplication(final String applicationId) throws InvalidIdentifier, ApplicationUninstallationError {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerService(final Object registeringService, final DeviceManager registeredDeviceMgr, final String name)
		throws InvalidObjectReference, DeviceManagerNotRegistered, RegisterError {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void unregisterService(final Object unregisteringService, final String name) throws InvalidObjectReference, UnregisterError {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerWithEventChannel(final Object registeringObject, final String registeringId, final String eventChannelName)
		throws InvalidObjectReference, InvalidEventChannelName, AlreadyConnected {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void unregisterFromEventChannel(final String unregisteringId, final String eventChannelName) throws InvalidEventChannelName, NotConnected {
		// TODO Auto-generated method stub

	}

	public void registerApplication(Application retVal) {
		applications.add(retVal);
	}

	@Override
	public AllocationManager allocationMgr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String name() {
		// TODO Auto-generated method stub
		return "domain";
	}

	@Override
	public DomainManager[] remoteDomainManagers() {
		synchronized (remoteDomMgrs) {
			return remoteDomMgrs.toArray(new DomainManager[remoteDomMgrs.size()]);
		}
	}

	@Override
	public void registerRemoteDomainManager(DomainManager registeringDomainManager) throws InvalidObjectReference, RegisterError {
		try {
			registeringDomainManager.identifier();
		} catch (SystemException e) {
			throw new InvalidObjectReference();
		}
		synchronized (remoteDomMgrs) {
			remoteDomMgrs.add(registeringDomainManager);
		}
	}

	@Override
	public void unregisterRemoteDomainManager(DomainManager unregisteringDomainManager) throws InvalidObjectReference, UnregisterError {
		try {
			unregisteringDomainManager.identifier();
		} catch (SystemException e) {
			throw new InvalidObjectReference();
		}
		synchronized (remoteDomMgrs) {
			if (!remoteDomMgrs.remove(unregisteringDomainManager)) {
				throw new UnregisterError();
			}
		}
	}

	@Override
	public ConnectionManager connectionMgr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Application createApplication(String profileFileName, String name, DataType[] initConfiguration, DeviceAssignmentType[] deviceAssignments)
		throws InvalidProfile, InvalidFileName, ApplicationInstallationError, CreateApplicationError, CreateApplicationRequestError,
		CreateApplicationInsufficientCapacityError, InvalidInitConfiguration {
		// Re-use the application factory logic to create the application
		URI uri = ScaURIFactory.createURI(profileFileName, fileManagerRef);
		try {
			SoftwareAssembly sad = SoftwareAssembly.Util.getSoftwareAssembly(resourceSet.getResource(uri, true));
			ApplicationFactoryImpl factory = new ApplicationFactoryImpl(sad, this);
			return factory.create(name, initConfiguration, deviceAssignments);
		} catch (Exception e) { // SUPPRESS CHECKSTYLE Logged Catch all exception
			throw new InvalidProfile(e.getMessage());
		}
	}
}

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

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.junit.Assert;
import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContext;
import org.omg.CosNaming.NamingContextHelper;
import org.omg.CosNaming.NamingContextPackage.AlreadyBound;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.omg.PortableServer.POA;

import CF.Device;
import CF.DeviceHelper;
import CF.DeviceManager;
import CF.DeviceManagerHelper;
import CF.DeviceManagerOperations;
import CF.DeviceManagerPOATie;
import CF.DevicePOATie;
import CF.DomainManager;
import CF.ExecutableDeviceHelper;
import CF.ExecutableDevicePOATie;
import CF.FileSystem;
import CF.FileSystemHelper;
import CF.FileSystemPOATie;
import CF.InvalidObjectReference;
import CF.LoadableDeviceHelper;
import CF.LoadableDevicePOATie;
import CF.DeviceManagerPackage.ServiceType;
import CF.LifeCyclePackage.ReleaseError;
import gov.redhawk.core.filemanager.filesystem.JavaFileSystem;
import gov.redhawk.sca.util.OrbSession;
import mil.jpeojtrs.sca.dcd.DcdComponentInstantiation;
import mil.jpeojtrs.sca.dcd.DcdComponentPlacement;
import mil.jpeojtrs.sca.dcd.DeviceConfiguration;
import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;
import mil.jpeojtrs.sca.scd.SoftwareComponent;
import mil.jpeojtrs.sca.scd.SupportsInterface;
import mil.jpeojtrs.sca.spd.SoftPkg;

/**
 * 
 */
public class DeviceManagerImpl extends AbstractResourceImpl implements DeviceManagerOperations {

	private final List<Device> devices = Collections.synchronizedList(new ArrayList<Device>());
	private final Map<String, ServiceType> services = Collections.synchronizedMap(new HashMap<String, ServiceType>());

	private DeviceManager devMgrRef;
	private NamingContext devMgrNamingContext;

	private final FileSystem fileSys;

	private final DeviceConfiguration dcd;

	public DeviceManagerImpl(File devRoot, String profilePath, String domainName, String identifier, String label, OrbSession session, NamingContext context)
		throws Exception {
		super(identifier, label, profilePath, session);

		org.omg.CORBA.Object ref = session.getPOA().servant_to_reference(new DeviceManagerPOATie(this));
		this.devMgrRef = DeviceManagerHelper.unchecked_narrow(ref);

		try {
			ref = context.resolve(new NameComponent[] { new NameComponent(domainName, "") });
			NamingContext domMgrNamingContext = NamingContextHelper.narrow(ref);
			devMgrNamingContext = domMgrNamingContext.bind_new_context(new NameComponent[] { new NameComponent(identifier, "") });
			domMgrNamingContext._release();
		} catch (NotFound | AlreadyBound | CannotProceed | InvalidName e) {
			throw new CoreException(
				new Status(IStatus.ERROR, "gov.redhawk.sca.model.tests", "Cannot create domain manager naming context in naming service", e));
		}

		this.fileSys = FileSystemHelper.narrow(poa.servant_to_reference(new FileSystemPOATie(new JavaFileSystem(orb, poa, devRoot))));

		ResourceSet resourceSet = new ResourceSetImpl();
		Resource dcdResource = resourceSet.getResource(ScaURIFactory.createURI(profilePath, fileSys), true);
		this.dcd = DeviceConfiguration.Util.getDeviceConfiguration(dcdResource);

		Assert.assertNotNull(dcd);

		if (this.compId == null) {
			this.compId = dcd.getId();
		}

		if (this.compName == null) {
			this.compName = dcd.getName();
		}

		initDevices();
		initServices();
	}

	private void initServices() {
		try {
			registerService(devices.get(0), ScaTestConstaints.SERVICE_NAME);
		} catch (InvalidObjectReference e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); // CHECKSTYLE: DEBUG CODE
		}
	}

	private void initDevices() throws Exception {
		TreeIterator<EObject> contents = this.dcd.getPartitioning().eAllContents();
		while (contents.hasNext()) {
			EObject obj = contents.next();
			if (obj instanceof DcdComponentPlacement) {
				continue;
			} else if (obj instanceof DcdComponentInstantiation) {
				DcdComponentInstantiation inst = (DcdComponentInstantiation) obj;
				SoftPkg spd = inst.getPlacement().getComponentFileRef().getFile().getSoftPkg();
				if (spd.getDescriptor() == null) {
					continue;
				}
				SoftwareComponent scd = spd.getDescriptor().getComponent();
				int type = 0;
				for (SupportsInterface s : scd.getComponentFeatures().getSupportsInterface()) {
					if (ExecutableDeviceHelper.id().equals(s.getRepId())) {
						type |= 4;
					} else if (LoadableDeviceHelper.id().equals(s.getRepId())) {
						type |= 2;
					} else if (DeviceHelper.id().equals(s.getRepId())) {
						type |= 1;
					}
				}

				Device device = null;
				if ((type & 4) == 4) {
					AbstractExecutableDeviceImpl impl = new AbstractExecutableDeviceImpl(inst.getId(), inst.getUsageName(), getProfile(inst), getSession());
					impl.init(inst.getPlacement().getComponentFileRef().getFile().getSoftPkg());
					device = ExecutableDeviceHelper.narrow(poa.servant_to_reference(new ExecutableDevicePOATie(impl)));
				} else if ((type & 2) == 2) {
					AbstractLoadableDeviceImpl impl = new AbstractLoadableDeviceImpl(inst.getId(), inst.getUsageName(), getProfile(inst), getSession());
					impl.init(inst.getPlacement().getComponentFileRef().getFile().getSoftPkg());
					device = LoadableDeviceHelper.narrow(poa.servant_to_reference(new LoadableDevicePOATie(impl)));
				} else if ((type & 1) == 1) {
					AbstractDeviceImpl impl = new AbstractDeviceImpl(inst.getId(), inst.getUsageName(), getProfile(inst), getSession());
					impl.init(inst.getPlacement().getComponentFileRef().getFile().getSoftPkg());
					device = DeviceHelper.narrow(poa.servant_to_reference(new DevicePOATie(impl)));
				}
				if (device != null) {
					registerDevice(device);
				}
			} else {
				contents.prune();
			}
		}
	}

	private String getProfile(ComponentInstantiation inst) {
		return inst.getPlacement().getComponentFileRef().getFile().getLocalFile().getName();
	}

	@Override
	public POA getPoa() {
		return super.getPoa();
	}

	@Override
	public ORB getOrb() {
		return super.getOrb();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String deviceConfigurationProfile() {
		return this.dcd.eResource().getURI().path();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public FileSystem fileSys() {
		return this.fileSys;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String label() {
		return this.compName;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Device[] registeredDevices() {
		return this.devices.toArray(new Device[this.devices.size()]);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ServiceType[] registeredServices() {
		return this.services.values().toArray(new ServiceType[this.services.size()]);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerDevice(final Device registeringDevice) throws InvalidObjectReference {
		if (registeringDevice != null) {
			this.devices.add(registeringDevice);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void unregisterDevice(final Device registeredDevice) throws InvalidObjectReference {
		this.devices.remove(registeredDevice);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void shutdown() {
		for (Device dev : devices) {
			try {
				dev.releaseObject();
			} catch (ReleaseError e) {
				// TODO Auto-generated catch block
				e.printStackTrace(); // CHECKSTYLE: DEBUG CODE
			}
		}
		try {
			releaseObject();
		} catch (ReleaseError e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); // CHECKSTYLE: DEBUG CODE
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void registerService(final Object registeringService, final String name) throws InvalidObjectReference {
		if (registeringService == null || name == null) {
			throw new InvalidObjectReference("", "");
		}
		final ServiceType type = new ServiceType();
		type.serviceName = name;
		type.serviceObject = registeringService;
		this.services.put(name, type);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void unregisterService(final Object unregisteringService, final String name) throws InvalidObjectReference {
		ServiceType type = services.remove(name);
		if (type == null) {
			throw new InvalidObjectReference("", "");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getComponentImplementationId(final String componentInstantiationId) {
		// TODO Auto-generated method stub
		return "";
	}

	public DeviceManager getRef() {
		return this.devMgrRef;
	}

	public void reset() {
		devices.clear();
		services.clear();
		try {
			initDevices();
			initServices();
		} catch (Exception e) { // SUPPRESS CHECKSTYLE DEBUG CODE
			// TODO Auto-generated catch block
			e.printStackTrace(); // CHECKSTYLE: DEBUG CODE
		}

	}

	@Override
	public DomainManager domMgr() {
		return null;
	}

}

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
package gov.redhawk.core.graphiti.dcd.ui.modelmap;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.AbstractCommand;

import gov.redhawk.core.graphiti.dcd.ui.GraphitiDcdUIPlugin;
import gov.redhawk.model.sca.CorbaObjWrapper;
import gov.redhawk.model.sca.ScaConnection;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaPortContainer;
import gov.redhawk.model.sca.ScaProvidesPort;
import gov.redhawk.model.sca.ScaService;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.sca.util.PluginUtil;
import mil.jpeojtrs.sca.dcd.DcdComponentInstantiation;
import mil.jpeojtrs.sca.dcd.DcdComponentInstantiationRef;
import mil.jpeojtrs.sca.dcd.DcdComponentPlacement;
import mil.jpeojtrs.sca.dcd.DcdConnectInterface;
import mil.jpeojtrs.sca.dcd.DcdFactory;
import mil.jpeojtrs.sca.dcd.DcdProvidesPort;
import mil.jpeojtrs.sca.dcd.DcdUsesPort;
import mil.jpeojtrs.sca.dcd.DeviceConfiguration;
import mil.jpeojtrs.sca.partitioning.ComponentFile;
import mil.jpeojtrs.sca.partitioning.ComponentFileRef;
import mil.jpeojtrs.sca.partitioning.ComponentFiles;
import mil.jpeojtrs.sca.partitioning.ComponentSupportedInterface;
import mil.jpeojtrs.sca.partitioning.PartitioningFactory;
import mil.jpeojtrs.sca.sad.SadFactory;
import mil.jpeojtrs.sca.spd.SoftPkg;
import mil.jpeojtrs.sca.util.ProtectedThreadExecutor;
import mil.jpeojtrs.sca.util.ScaUriHelpers;
import mil.jpeojtrs.sca.util.collections.FeatureMapList;

/**
 * Uses the REDHAWK SCA model to build a corresponding DCD
 */
public class GraphitiDCDModelMapInitializerCommand extends AbstractCommand {

	private final GraphitiDCDModelMap modelMap;

	private final ScaDeviceManager deviceManager;
	private List<ScaPortContainer> portContainers;
	private List<CorbaObjWrapper< ? >> componentSupportedInterfaceTargets;

	private final DeviceConfiguration dcd;

	public GraphitiDCDModelMapInitializerCommand(final GraphitiDCDModelMap modelMap, final DeviceConfiguration dcd, final ScaDeviceManager deviceManager) {
		this.modelMap = modelMap;
		this.deviceManager = deviceManager;
		this.dcd = dcd;
	}

	private void initConnection(final ScaConnection con) {
		final ScaUsesPort uses = con.getPort();
		final ScaPortContainer container = uses.getPortContainer();
		if (!(container instanceof ScaDevice || container instanceof ScaService)) {
			// Can only add connections from devices' and services' ports
			return;
		}

		final DcdConnectInterface dcdCon = DcdFactory.eINSTANCE.createDcdConnectInterface();
		dcdCon.setId(con.getId());
		initConnectionSource(dcdCon, container, uses);
		initConnectionTarget(dcdCon, uses, con.getData().port);
		if (dcdCon.getProvidesPort() == null && dcdCon.getComponentSupportedInterface() == null) {
			// We were unable to find the target side of the connection; ignore it
			return;
		}

		if (dcd.getConnections() == null) {
			dcd.setConnections(DcdFactory.eINSTANCE.createDcdConnections());
		}
		dcd.getConnections().getConnectInterface().add(dcdCon);
		modelMap.put(con, dcdCon);
	}

	private void initConnectionSource(DcdConnectInterface dcdCon, ScaPortContainer sourceContainer, ScaUsesPort sourcePort) {
		DcdUsesPort usesPort = DcdFactory.eINSTANCE.createDcdUsesPort();
		DcdComponentInstantiationRef usesCompRef = DcdFactory.eINSTANCE.createDcdComponentInstantiationRef();
		usesCompRef.setInstantiation(modelMap.getComponentInstantiation(sourceContainer));
		usesPort.setComponentInstantiationRef(usesCompRef);
		usesPort.setUsesIdentifier(sourcePort.getName());
		dcdCon.setUsesPort(usesPort);
	}

	private void initConnectionTarget(DcdConnectInterface dcdCon, ScaUsesPort scaUsesPort, org.omg.CORBA.Object targetPortObject) {
		// Iterate port containers looking for a provides ports which may match
		for (final ScaPortContainer portContainer : portContainers) {
			for (final ScaPort< ? , ? > port : portContainer.getPorts()) {
				if (port instanceof ScaProvidesPort && is_equivalent(targetPortObject, port.getCorbaObj())) {
					final DcdProvidesPort dcdProvidesPort = DcdFactory.eINSTANCE.createDcdProvidesPort();
					final DcdComponentInstantiationRef ref = DcdFactory.eINSTANCE.createDcdComponentInstantiationRef();
					ref.setInstantiation(modelMap.getComponentInstantiation(portContainer));
					dcdProvidesPort.setComponentInstantiationRef(ref);
					dcdProvidesPort.setProvidesIdentifier(port.getName());
					dcdCon.setProvidesPort(dcdProvidesPort);
					return;
				}
			}
		}

		// Iterate anything that could be a component supported interface looking for a match
		for (final CorbaObjWrapper< ? > csiTarget : componentSupportedInterfaceTargets) {
			if (is_equivalent(targetPortObject, csiTarget.getCorbaObj())) {
				final ComponentSupportedInterface csi = PartitioningFactory.eINSTANCE.createComponentSupportedInterface();
				final DcdComponentInstantiationRef ref = DcdFactory.eINSTANCE.createDcdComponentInstantiationRef();
				ref.setInstantiation(modelMap.getComponentInstantiation(csiTarget));
				csi.setComponentInstantiationRef(ref);
				csi.setSupportedIdentifier(scaUsesPort.getProfileObj().getRepID());
				dcdCon.setComponentSupportedInterface(csi);
				return;
			}
		}
	}

	private static boolean is_equivalent(final org.omg.CORBA.Object obj1, final org.omg.CORBA.Object obj2) {
		if (obj1 == null || obj2 == null) {
			return false;
		}
		if (obj1 == obj2) {
			return true;
		}
		try {
			return ProtectedThreadExecutor.submit(new Callable<Boolean>() {

				@Override
				public Boolean call() throws Exception {
					return obj1._is_equivalent(obj2);
				}

			});
		} catch (InterruptedException e) {
			return false;
		} catch (ExecutionException e) {
			return false;
		} catch (TimeoutException e) {
			return false;
		}

	}

	/**
	 * Adds the XML to the DCD for a {@link ScaDevice}.
	 * @param device
	 */
	private void initDevice(final ScaDevice< ? > device) {
		final SoftPkg spd = device.getProfileObj();
		if (spd == null) {
			IStatus status = new Status(IStatus.ERROR, GraphitiDcdUIPlugin.PLUGIN_ID, "Failed to find SPD for device: " + device.getIdentifier());
			Platform.getLog(Platform.getBundle(GraphitiDcdUIPlugin.PLUGIN_ID)).log(status);
			return;
		}

		DcdComponentInstantiation inst = createComponentInstatitation(spd, device.getIdentifier(), device.getLabel());
		modelMap.put(device, inst);
	}

	/**
	 * Adds the XML to the DCD for a {@link ScaService}.
	 * @param device
	 */
	private void initService(final ScaService service) {
		final SoftPkg spd = service.getProfileObj();
		if (spd == null) {
			IStatus status = new Status(IStatus.ERROR, GraphitiDcdUIPlugin.PLUGIN_ID, "Failed to find SPD for service: " + service.getName());
			Platform.getLog(Platform.getBundle(GraphitiDcdUIPlugin.PLUGIN_ID)).log(status);
			return;
		}

		DcdComponentInstantiation inst = createComponentInstatitation(spd, dcd.getName() + ":" + service.getName(), service.getName());
		modelMap.put(service, inst);
	}

	/**
	 * Create a new component instantiation
	 * @param spd
	 * @param id
	 * @param usageName
	 * @return
	 */
	private DcdComponentInstantiation createComponentInstatitation(SoftPkg spd, String id, String usageName) {
		// Create the componentfiles element if it doesn't exist already
		ComponentFiles componentFiles = dcd.getComponentFiles();
		if (componentFiles == null) {
			componentFiles = PartitioningFactory.eINSTANCE.createComponentFiles();
			dcd.setComponentFiles(componentFiles);
		}

		// Find the matching component file, or create if necessary
		ComponentFile componentFile = getComponentFile(spd, componentFiles);

		// Create the component placement and add to its parent, the partitioning
		final DcdComponentPlacement placement = DcdFactory.eINSTANCE.createDcdComponentPlacement();
		dcd.getPartitioning().getComponentPlacement().add(placement);

		// Create a component file ref
		final ComponentFileRef fileRef = PartitioningFactory.eINSTANCE.createComponentFileRef();
		fileRef.setFile(componentFile);

		// Create a component instantiation
		final DcdComponentInstantiation inst = DcdFactory.eINSTANCE.createDcdComponentInstantiation();
		inst.setId(id);
		inst.setUsageName(usageName);

		// Add the component file ref and then the component instantiation to their parent the component placement.
		// IMPORTANT: The component instantiation is added to its parent LAST, because it must be able to navigate the
		// XML all the way to the SCD file so it can populate the port stubs. See code paths to
		// ComponentInstantiationImpl#refreshPorts().
		placement.setComponentFileRef(fileRef);
		placement.getComponentInstantiation().add(inst);

		return inst;
	}

	/**
	 * Finds a {@link ComponentFile} that matches the {@link SoftPkg} for this class, creating and adding one if
	 * necessary.
	 * @param spd
	 * @param componentFiles The list of component files
	 * @return
	 */
	protected ComponentFile getComponentFile(SoftPkg spd, ComponentFiles componentFiles) {
		final String spdPath = ScaUriHelpers.getLocalFilePath(componentFiles, spd);
		for (final ComponentFile componentFile : componentFiles.getComponentFile()) {
			final SoftPkg fSpd = componentFile.getSoftPkg();
			if (fSpd != null && PluginUtil.equals(spdPath, componentFile.getLocalFile().getName())) {
				return componentFile;
			}
		}

		ComponentFile newFile = SadFactory.eINSTANCE.createComponentFile();
		componentFiles.getComponentFile().add(newFile);
		newFile.setSoftPkg(spd);
		return newFile;
	}

	@Override
	public void execute() {
		this.dcd.setComponentFiles(PartitioningFactory.eINSTANCE.createComponentFiles());
		this.dcd.setPartitioning(DcdFactory.eINSTANCE.createDcdPartitioning());
		this.dcd.setConnections(DcdFactory.eINSTANCE.createDcdConnections());

		if (deviceManager != null) {
			portContainers = new ArrayList<>();
			componentSupportedInterfaceTargets = new ArrayList<>();

			for (ScaDevice< ? > device : new FeatureMapList<>(this.deviceManager.getDevices(), ScaDevice.class)) {
				portContainers.add(device);
				componentSupportedInterfaceTargets.add(device);
				initDevice(device);
			}
			for (ScaService service : this.deviceManager.getServices()) {
				portContainers.add(service);
				componentSupportedInterfaceTargets.add(service);
				initService(service);
			}

			for (final ScaPortContainer portContainer : portContainers) {
				for (final ScaPort< ? , ? > port : portContainer.getPorts()) {
					if (port instanceof ScaUsesPort) {
						final ScaUsesPort uses = (ScaUsesPort) port;
						for (final ScaConnection con : uses.getConnections()) {
							if (con != null) {
								initConnection(con);
							}
						}
					}
				}
			}
		}
	}

	@Override
	protected boolean prepare() {
		return true;
	}

	@Override
	public boolean canUndo() {
		return false;
	}

	@Override
	public void redo() {
		throw new UnsupportedOperationException();
	}
}

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
package gov.redhawk.core.graphiti.dcd.ui.utils;

import java.util.ArrayList;
import java.util.List;

import gov.redhawk.sca.util.PluginUtil;
import mil.jpeojtrs.sca.dcd.DcdComponentInstantiation;
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
import mil.jpeojtrs.sca.spd.SoftPkg;
import mil.jpeojtrs.sca.util.ScaUriHelpers;

/**
 * @since 1.1
 */
public class DCDUtils {

	private DCDUtils() {

	}

	public static DcdComponentInstantiation createComponentInstantiation(final SoftPkg spd, final DeviceConfiguration dcd, String usageName,
		String instantiationId, String implementationId) {

		// add component files if necessary
		ComponentFiles componentFiles = dcd.getComponentFiles();
		if (componentFiles == null) {
			componentFiles = PartitioningFactory.eINSTANCE.createComponentFiles();
			dcd.setComponentFiles(componentFiles);
		}

		// find a compatible component file, or create a new one
		final String spdPath = ScaUriHelpers.getLocalFilePath(componentFiles, spd);
		ComponentFile componentFile = null;
		for (final ComponentFile f : componentFiles.getComponentFile()) {
			final SoftPkg fSpd = f.getSoftPkg();
			if (fSpd != null && PluginUtil.equals(spdPath, f.getLocalFile().getName())) {
				componentFile = f;
				break;
			}
		}

		if (componentFile == null) {
			componentFile = DcdFactory.eINSTANCE.createComponentFile();
			componentFile.setSoftPkg(spd);
			componentFiles.getComponentFile().add(componentFile);
		}

		// create component placement and add to list
		final DcdComponentPlacement componentPlacement = DcdFactory.eINSTANCE.createDcdComponentPlacement();
		dcd.getPartitioning().getComponentPlacement().add(componentPlacement);

		// create component file ref
		final ComponentFileRef ref = PartitioningFactory.eINSTANCE.createComponentFileRef();
		ref.setFile(componentFile);
		componentPlacement.setComponentFileRef(ref);

		// create component instantiation
		DcdComponentInstantiation dcdComponentInstantiation = DcdFactory.eINSTANCE.createDcdComponentInstantiation();

		// use provided name/id/implId if provided otherwise generate
		usageName = (usageName != null) ? usageName : DeviceConfiguration.Util.createDeviceUsageName(dcd, spd.getName());
		String id = (instantiationId != null) ? instantiationId : dcd.getName() + ":" + usageName;
		implementationId = (implementationId != null) ? implementationId : spd.getImplementation().get(0).getId();
		dcdComponentInstantiation.setUsageName(usageName);
		dcdComponentInstantiation.setId(id);
		dcdComponentInstantiation.setImplID(implementationId);

		// add to placement
		componentPlacement.getComponentInstantiation().add(dcdComponentInstantiation);

		return dcdComponentInstantiation;
	}

	/**
	 * Delete DcdComponentInstantiation and corresponding DcdComponentPlacement business object from DeviceConfiguration
	 * This method should be executed within a RecordingCommand.
	 * @param ciToDelete
	 * @param diagram
	 */
	public static void deleteComponentInstantiation(final DcdComponentInstantiation ciToDelete, final DeviceConfiguration dcd) {
		// get placement for instantiation and delete it from dcd partitioning after we look at removing the component
		// file ref
		DcdComponentPlacement placement = (DcdComponentPlacement) ciToDelete.getPlacement();

		// find and remove any attached connections
		List<DcdConnectInterface> connectionsToRemove = new ArrayList<DcdConnectInterface>();
		if (dcd.getConnections() != null) {
			for (DcdConnectInterface connection : dcd.getConnections().getConnectInterface()) {
				// Check for a connection to the component's ComponentSupportedInterface
				// A connection can target a providesPort or componentSupportedInterface, so either could be null
				// FindBy connections don't have ComponentInstantiationRefs and so they can also be null
				ComponentSupportedInterface csi = connection.getComponentSupportedInterface();
				if (csi != null && csi.getComponentInstantiationRef() != null && ciToDelete.getId().equals(csi.getComponentInstantiationRef().getRefid())) {
					connectionsToRemove.add(connection);
					continue;
				}

				// Check for a connection to the targets Provides port
				// A connection can target a providesPort or componentSupportedInterface, so either could be null
				DcdProvidesPort provides = connection.getProvidesPort();
				if (provides != null && provides.getComponentInstantiationRef() != null
					&& ciToDelete.getId().equals(provides.getComponentInstantiationRef().getRefid())) {
					connectionsToRemove.add(connection);
					continue;
				}

				// Check for a connection to the uses port
				DcdUsesPort uses = connection.getUsesPort();
				if (uses != null && uses.getComponentInstantiationRef() != null && ciToDelete.getId().equals(uses.getComponentInstantiationRef().getRefid())) {
					connectionsToRemove.add(connection);
					continue;
				}
			}
		}
		// remove gathered connections
		if (dcd.getConnections() != null) {
			dcd.getConnections().getConnectInterface().removeAll(connectionsToRemove);
		}

		// If the placement contains more than one instantiation, then don't remove it or the component file
		ComponentFile componentFileToRemove = placement.getComponentFileRef().getFile();
		boolean removePlacement = true;
		if (placement.getComponentInstantiation().size() > 1) {
			removePlacement = false;
			componentFileToRemove = null;
		}

		// If we are removing the placement, then see if other component placements are using the component file.
		// If not, mark it for removal
		if (removePlacement) {
			String placementRefID = placement.getComponentFileRef().getRefid();
			for (DcdComponentPlacement tempPlacement : dcd.getPartitioning().getComponentPlacement()) {
				if (tempPlacement == placement) {
					continue;
				}
				String tempRefID = tempPlacement.getComponentFileRef().getRefid();
				if (tempRefID.equals(placementRefID)) {
					componentFileToRemove = null;
				}
			}
		}

		if (componentFileToRemove != null) {
			dcd.getComponentFiles().getComponentFile().remove(componentFileToRemove);
		}

		if (dcd.getComponentFiles().getComponentFile().isEmpty()) {
			dcd.setComponentFiles(null);
		}

		for (DcdComponentPlacement p : dcd.getPartitioning().getComponentPlacement()) {
			if (p.getCompositePartOfDevice() != null && ciToDelete.getId().equals(p.getCompositePartOfDevice().getRefID())) {
				p.setCompositePartOfDevice(null);
			}
		}

		// delete component instantiation
		placement.getComponentInstantiation().remove(ciToDelete);

		// delete component placement
		if (removePlacement) {
			dcd.getPartitioning().getComponentPlacement().remove(placement);
		}
	}
}

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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.mm.pictograms.Diagram;

import CF.ResourceHelper;
import gov.redhawk.core.graphiti.ui.diagram.patterns.AbstractPortSupplierPattern;
import gov.redhawk.core.graphiti.ui.ext.RHContainerShape;
import gov.redhawk.core.graphiti.ui.util.DUtil;
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
import mil.jpeojtrs.sca.partitioning.PartitioningPackage;
import mil.jpeojtrs.sca.scd.Interface;
import mil.jpeojtrs.sca.scd.ScdFactory;
import mil.jpeojtrs.sca.scd.SoftwareComponent;
import mil.jpeojtrs.sca.spd.SoftPkg;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;
import mil.jpeojtrs.sca.util.ScaUriHelpers;

/**
 * @since 1.1
 */
public class DCDUtils {

	private DCDUtils() {

	}

	/**
	 * Finds a component instantiation with the provided start order.
	 * @param sad
	 * @param startOrder
	 * @return
	 */
	public static DcdComponentInstantiation getComponentInstantiationViaStartOrder(final DeviceConfiguration dcd, final BigInteger startOrder) {
		for (DcdComponentInstantiation ci : dcd.getAllComponentInstantiations()) {
			if (ci.getStartOrder() != null && ci.getStartOrder().compareTo(startOrder) == 0) {
				return ci;
			}
		}
		return null;
	}

	/**
	 * Organize start order.
	 * <ul>
	 * <li>Components with start orders may have them modified so that each component is only +1 ahead of the previous
	 * component</li>
	 * </ul>
	 */
	public static void organizeStartOrder(final DeviceConfiguration dcd, final Diagram diagram, final IFeatureProvider featureProvider) {
		BigInteger startOrder = BigInteger.ZERO;

		// get instantiations by start order
		EList<DcdComponentInstantiation> componentInstantiationsInStartOrder = dcd.getComponentInstantiationsInStartOrder();

		// set start order
		for (final DcdComponentInstantiation ci : componentInstantiationsInStartOrder) {
			// Don't update start order if it has not already been declared for this component
			if (ci.getStartOrder() != null) {
				// Only call the update if a change is needed
				if (ci.getStartOrder().intValue() != startOrder.intValue()) {
					final BigInteger newStartOrder = startOrder;
					TransactionalEditingDomain editingDomain = featureProvider.getDiagramTypeProvider().getDiagramBehavior().getEditingDomain();
					TransactionalCommandStack stack = (TransactionalCommandStack) editingDomain.getCommandStack();
					stack.execute(new RecordingCommand(editingDomain) {

						@Override
						protected void doExecute() {
							ci.setStartOrder(newStartOrder);

							// Force pictogram elements to update
							RHContainerShape componentShape = (RHContainerShape) DUtil.getPictogramElementForBusinessObject(diagram, ci,
								RHContainerShape.class);
							UpdateContext context = new UpdateContext(componentShape);
							IUpdateFeature feature = featureProvider.getUpdateFeature(context);
							feature.execute(context);
						}
					});
				}

				// Increment start order for next pass
				startOrder = startOrder.add(BigInteger.ONE);
			}
		}
	}

	/**
	 * Swap start order of provided components. Change assembly controller if start order zero.
	 * @param sad
	 * @param featureProvider
	 * @param lowerCi - The component that currently has the lower start order
	 * @param higherCi - The component that currently has the higher start order
	 */
	public static void swapStartOrder(DeviceConfiguration sad, IFeatureProvider featureProvider, final DcdComponentInstantiation lowerCi,
		final DcdComponentInstantiation higherCi) {

		// editing domain for our transaction
		TransactionalEditingDomain editingDomain = featureProvider.getDiagramTypeProvider().getDiagramBehavior().getEditingDomain();

		// Perform business object manipulation in a Command
		TransactionalCommandStack stack = (TransactionalCommandStack) editingDomain.getCommandStack();
		stack.execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {

				// Increment start order
				lowerCi.setStartOrder(higherCi.getStartOrder());
				// Decrement start order
				higherCi.setStartOrder(higherCi.getStartOrder().subtract(BigInteger.ONE));
			}
		});
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

		// determine start order
		addStartOrder(dcd, dcdComponentInstantiation);

		return dcdComponentInstantiation;
	}

	private static void addStartOrder(DeviceConfiguration dcd, DcdComponentInstantiation dcdComponentInstantiation) {
		final EStructuralFeature[] COMP_TO_SPD = new EStructuralFeature[] { PartitioningPackage.Literals.COMPONENT_INSTANTIATION__PLACEMENT,
			PartitioningPackage.Literals.COMPONENT_PLACEMENT__COMPONENT_FILE_REF, PartitioningPackage.Literals.COMPONENT_FILE_REF__FILE,
			PartitioningPackage.Literals.COMPONENT_FILE__SOFT_PKG };
		SoftPkg spd = ScaEcoreUtils.getFeature(dcdComponentInstantiation, COMP_TO_SPD);
		if (spd == null) {
			return;
		}

		Interface tmpInterface = ScdFactory.eINSTANCE.createInterface();
		tmpInterface.setRepid(ResourceHelper.id());
		SoftwareComponent scd = spd.getDescriptor().getComponent();
		boolean implementsResource = false;
		for (Interface serviceInterface : scd.getInterfaces().getInterface()) {
			if (serviceInterface.isInstance(tmpInterface)) {
				implementsResource = true;
			}
		}

		if (!implementsResource) {
			return;
		}

		// determine start order for existing components
		BigInteger highestStartOrder = AbstractPortSupplierPattern.determineHighestStartOrder(dcd.getAllComponentInstantiations());

		// increment start order for new component
		BigInteger startOrder = null;
		if (highestStartOrder == null) {
			// Should only get here if no other components exist
			// Assume assembly controller and assign start order of 0
			startOrder = BigInteger.ZERO;
		} else {
			startOrder = highestStartOrder.add(BigInteger.ONE);
		}

		dcdComponentInstantiation.setStartOrder(startOrder);
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

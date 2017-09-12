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
package gov.redhawk.core.graphiti.sad.ui.utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.Shape;

import gov.redhawk.core.graphiti.sad.ui.ext.ComponentShape;
import gov.redhawk.core.graphiti.ui.util.DUtil;
import gov.redhawk.sca.util.PluginUtil;
import mil.jpeojtrs.sca.partitioning.ComponentFile;
import mil.jpeojtrs.sca.partitioning.ComponentFileRef;
import mil.jpeojtrs.sca.partitioning.ComponentFiles;
import mil.jpeojtrs.sca.partitioning.ComponentSupportedInterface;
import mil.jpeojtrs.sca.partitioning.NamingService;
import mil.jpeojtrs.sca.partitioning.PartitioningFactory;
import mil.jpeojtrs.sca.sad.AssemblyController;
import mil.jpeojtrs.sca.sad.ExternalProperty;
import mil.jpeojtrs.sca.sad.FindComponent;
import mil.jpeojtrs.sca.sad.HostCollocation;
import mil.jpeojtrs.sca.sad.Port;
import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.sad.SadComponentInstantiationRef;
import mil.jpeojtrs.sca.sad.SadComponentPlacement;
import mil.jpeojtrs.sca.sad.SadConnectInterface;
import mil.jpeojtrs.sca.sad.SadFactory;
import mil.jpeojtrs.sca.sad.SadProvidesPort;
import mil.jpeojtrs.sca.sad.SadUsesPort;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.spd.SoftPkg;
import mil.jpeojtrs.sca.util.ScaUriHelpers;

public class SADUtils {

	private SADUtils() {
	}

	/**
	 * Recursively find all {@link ComponentShape}s in the diagram.
	 * @param containerShape
	 * @return
	 */
	public static List<ComponentShape> getAllComponentShapes(Diagram diagram) {
		List<ComponentShape> children = new ArrayList<ComponentShape>();
		for (Shape s : diagram.getChildren()) {
			if (s instanceof ContainerShape) {
				getAllComponentShapesInternal(children, (ContainerShape) s);
			}
		}
		return children;
	}

	private static void getAllComponentShapesInternal(List<ComponentShape> children, ContainerShape containerShape) {
		if (containerShape instanceof ComponentShape) {
			children.add((ComponentShape) containerShape);
		} else {
			for (Shape s : containerShape.getChildren()) {
				if (s instanceof ContainerShape) {
					getAllComponentShapesInternal(children, (ContainerShape) s);
				}
			}
		}
	}

	/**
	 * Get the assembly controller. Checks that the component instantiation ref is present first.
	 * @param featureProvider
	 * @param diagram
	 * @return
	 */
	private static AssemblyController getAssemblyController(IFeatureProvider featureProvider, Diagram diagram) {
		final SoftwareAssembly sad = DUtil.getDiagramSAD(diagram);
		if (sad.getAssemblyController() != null && sad.getAssemblyController().getComponentInstantiationRef() != null) {
			return sad.getAssemblyController();
		}
		return null;

	}

	/**
	 * Finds a component instantiation with the provided start order.
	 * @param sad
	 * @param startOrder
	 * @return
	 */
	public static SadComponentInstantiation getComponentInstantiationViaStartOrder(final SoftwareAssembly sad, final BigInteger startOrder) {
		for (SadComponentInstantiation ci : sad.getAllComponentInstantiations()) {
			if (ci.getStartOrder() != null && ci.getStartOrder().compareTo(startOrder) == 0) {
				return ci;
			}
		}
		return null;
	}

	/**
	 * Organize start order.
	 * <ul>
	 * <li>If there is no assembly controller, the earliest started component becomes it</li>
	 * <li>The assembly controller gets start order 0</li>
	 * <li>Components with start orders may have them modified so that each component is only +1 ahead of the previous
	 * component</li>
	 * </ul>
	 * @param sad
	 * @param diagram
	 * @param featureProvider
	 */
	public static void organizeStartOrder(final SoftwareAssembly sad, final Diagram diagram, final IFeatureProvider featureProvider) {
		BigInteger startOrder = BigInteger.ZERO;

		// get components by start order
		EList<SadComponentInstantiation> componentInstantiationsInStartOrder = sad.getComponentInstantiationsInStartOrder();

		// if assembly controller was deleted (or component that used to be assembly controller was deleted)
		// set a new assembly controller
		AssemblyController assemblyController = getAssemblyController(featureProvider, diagram);
		if ((assemblyController == null || assemblyController.getComponentInstantiationRef().getInstantiation() == null)
			&& componentInstantiationsInStartOrder.size() > 0) {
			// assign assembly controller assign to first component
			assemblyController = SadFactory.eINSTANCE.createAssemblyController();
			SadComponentInstantiation ci = componentInstantiationsInStartOrder.get(0);
			SadComponentInstantiationRef sadComponentInstantiationRef = SadFactory.eINSTANCE.createSadComponentInstantiationRef();
			sadComponentInstantiationRef.setInstantiation(ci);
			assemblyController.setComponentInstantiationRef(sadComponentInstantiationRef);
			sad.setAssemblyController(assemblyController);

			// If the component has a start order defined, update it to run first
			if (ci.getStartOrder() != null) {
				ci.setStartOrder(BigInteger.ZERO);
			}

		}

		if (assemblyController != null && assemblyController.getComponentInstantiationRef() != null) {
			final SadComponentInstantiation ci = assemblyController.getComponentInstantiationRef().getInstantiation();
			// first check to make sure start order is set to zero
			if (ci != null && ci.getStartOrder() != null && ci.getStartOrder() != BigInteger.ZERO) {
				TransactionalEditingDomain editingDomain = featureProvider.getDiagramTypeProvider().getDiagramBehavior().getEditingDomain();
				TransactionalCommandStack stack = (TransactionalCommandStack) editingDomain.getCommandStack();
				stack.execute(new RecordingCommand(editingDomain) {

					@Override
					protected void doExecute() {
						ci.setStartOrder(BigInteger.ZERO);
					}
				});
			}

			// remove assembly controller from list, it has already been updated
			componentInstantiationsInStartOrder.remove(assemblyController.getComponentInstantiationRef().getInstantiation());
		}

		// set start order
		for (final SadComponentInstantiation ci : componentInstantiationsInStartOrder) {
			// Don't update start order if it has not already been declared for this component
			if (ci.getStartOrder() != null) {
				startOrder = startOrder.add(BigInteger.ONE);

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
							ComponentShape componentShape = (ComponentShape) DUtil.getPictogramElementForBusinessObject(diagram, ci, ComponentShape.class);
							UpdateContext context = new UpdateContext(componentShape);
							IUpdateFeature feature = featureProvider.getUpdateFeature(context);
							feature.execute(context);
						}
					});
				}
			}
		}
	}

	/**
	 * Create SadComponentInstantiation and corresponding SadComponentPlacement business object in a SoftwareAssembly
	 * This method should be executed within a RecordingCommand.
	 * 
	 * @param spd
	 * @param sad
	 * @param hostCollocation - Target hostCollocation if this is to be the component container, otherwise null
	 * @param usageName - Usage name override, may be null
	 * @param instantiationId - Instantiation ID override, may be null
	 * @param implementationId - may be null
	 * @return
	 */
	public static SadComponentInstantiation createComponentInstantiation(final SoftPkg spd, final SoftwareAssembly sad, final HostCollocation hostCollocation,
		String usageName, String instantiationId, String implementationId) {

		// add component files if necessary
		ComponentFiles componentFiles = sad.getComponentFiles();
		if (componentFiles == null) {
			componentFiles = PartitioningFactory.eINSTANCE.createComponentFiles();
			sad.setComponentFiles(componentFiles);
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
			componentFile = SadFactory.eINSTANCE.createComponentFile();
			componentFile.setSoftPkg(spd);
			componentFiles.getComponentFile().add(componentFile);
		}

		// create component placement and add to list
		final SadComponentPlacement componentPlacement = SadFactory.eINSTANCE.createSadComponentPlacement();
		if (hostCollocation != null) {
			hostCollocation.getComponentPlacement().add(componentPlacement);
		} else {
			sad.getPartitioning().getComponentPlacement().add(componentPlacement);
		}

		// create component file ref
		final ComponentFileRef ref = PartitioningFactory.eINSTANCE.createComponentFileRef();
		ref.setFile(componentFile);
		componentPlacement.setComponentFileRef(ref);

		// create component instantiation
		SadComponentInstantiation sadComponentInstantiation = SadFactory.eINSTANCE.createSadComponentInstantiation();

		// use provided name/id if provided otherwise generate
		String id = (instantiationId != null) ? instantiationId : SoftwareAssembly.Util.createComponentIdentifier(sad, spd.getName());
		String compName = (usageName != null) ? usageName : SoftwareAssembly.Util.createComponentUsageName(sad, id);
		sadComponentInstantiation.setUsageName(compName);
		sadComponentInstantiation.setId(id);

		final FindComponent findComponent = SadFactory.eINSTANCE.createFindComponent();
		final NamingService namingService = PartitioningFactory.eINSTANCE.createNamingService();
		namingService.setName(compName);
		findComponent.setNamingService(namingService);
		sadComponentInstantiation.setFindComponent(findComponent);
		if (implementationId == null) {
			implementationId = spd.getImplementation().get(0).getId();
		}
		sadComponentInstantiation.setImplID(implementationId);
		componentPlacement.getComponentInstantiation().add(sadComponentInstantiation);

		// determine start order
		int startOrder = 0;
		for (SadComponentInstantiation comp : sad.getComponentInstantiationsInStartOrder()) {
			if (comp.getStartOrder() == null) {
				break;
			}
			startOrder = comp.getStartOrder().intValue() + 1;
		}
		sadComponentInstantiation.setStartOrder(BigInteger.valueOf(startOrder));

		// determine if component should be the assembly controller
		if (sadComponentInstantiation.getStartOrder().compareTo(BigInteger.ZERO) == 0) {
			// create assembly controller
			AssemblyController assemblyController = SadFactory.eINSTANCE.createAssemblyController();
			SadComponentInstantiationRef sadComponentInstantiationRef = SadFactory.eINSTANCE.createSadComponentInstantiationRef();
			sadComponentInstantiationRef.setInstantiation(sadComponentInstantiation);
			assemblyController.setComponentInstantiationRef(sadComponentInstantiationRef);
			sad.setAssemblyController(assemblyController);
		}

		return sadComponentInstantiation;
	}

	/**
	 * Delete SadComponentInstantiation and corresponding SadComponentPlacement business object from SoftwareAssembly
	 * This method should be executed within a RecordingCommand.
	 * @param ciToDelete
	 * @param sad
	 */
	public static void deleteComponentInstantiation(final SadComponentInstantiation ciToDelete, final SoftwareAssembly sad) {

		// assembly controller may reference componentInstantiation
		// delete reference if applicable
		if (sad.getAssemblyController() != null && sad.getAssemblyController().getComponentInstantiationRef() != null
			&& sad.getAssemblyController().getComponentInstantiationRef().getInstantiation().equals(ciToDelete)) {
			EcoreUtil.delete(sad.getAssemblyController().getComponentInstantiationRef());
			sad.getAssemblyController().setComponentInstantiationRef(null);
		}

		// get placement for instantiation and delete it from sad partitioning after we look at removing the component
		// file ref.
		SadComponentPlacement placement = (SadComponentPlacement) ciToDelete.getPlacement();

		// find and remove any attached connections
		List<SadConnectInterface> connectionsToRemove = new ArrayList<SadConnectInterface>();
		if (sad.getConnections() != null) {
			for (SadConnectInterface connection : sad.getConnections().getConnectInterface()) {
				// Check for a connection to the component's ComponentSupportedInterface
				// A connection can target a providesPort or componentSupportedInterface, so either could be null
				// FindBy connections don't have ComponentInstantiationRefs and so they can also be null
				ComponentSupportedInterface csi = connection.getComponentSupportedInterface();
				if (csi != null && csi.getComponentInstantiationRef() != null && ciToDelete.getId().equals(csi.getComponentInstantiationRef().getRefid())) {
					connectionsToRemove.add(connection);
				}

				// Check for a connection to the targets Provides port
				// A connection can target a providesPort or componentSupportedInterface, so either could be null
				SadProvidesPort provides = connection.getProvidesPort();
				if (provides != null && provides.getComponentInstantiationRef() != null
					&& ciToDelete.getId().equals(provides.getComponentInstantiationRef().getRefid())) {
					connectionsToRemove.add(connection);
					continue;
				}

				// Check for a connection to the uses port
				SadUsesPort uses = connection.getUsesPort();
				if (uses != null && uses.getComponentInstantiationRef() != null && ciToDelete.getId().equals(uses.getComponentInstantiationRef().getRefid())) {
					connectionsToRemove.add(connection);
					continue;
				}
			}
		}
		// remove gathered connections
		if (sad.getConnections() != null) {
			sad.getConnections().getConnectInterface().removeAll(connectionsToRemove);
		}

		// remove any associated external ports
		if (sad.getExternalPorts() != null) {
			List<Port> externalPortsToRemove = new ArrayList<Port>();
			for (Port port : sad.getExternalPorts().getPort()) {
				if (port.getComponentInstantiationRef().getRefid().equals(ciToDelete.getId())) {
					externalPortsToRemove.add(port);
				}
			}

			for (Port port : externalPortsToRemove) {
				sad.getExternalPorts().getPort().remove(port);
			}

			if (sad.getExternalPorts().getPort().isEmpty()) {
				sad.setExternalPorts(null);
			}
		}

		// remove any associated external properties
		if (sad.getExternalProperties() != null) {
			List<ExternalProperty> externalPropertiesToRemove = new ArrayList<ExternalProperty>();
			for (ExternalProperty property : sad.getExternalProperties().getProperties()) {
				if (property.getCompRefID().equals(ciToDelete.getId())) {
					externalPropertiesToRemove.add(property);
				}
			}

			for (ExternalProperty property : externalPropertiesToRemove) {
				sad.getExternalProperties().getProperties().remove(property);
			}

			if (sad.getExternalProperties().getProperties().isEmpty()) {
				sad.setExternalProperties(null);
			}
		}

		// If the placement contains more than one instantiation, then don't remove it or the component file
		ComponentFile componentFileToRemove = placement.getComponentFileRef().getFile();
		boolean removePlacement = true;
		if (placement.getComponentInstantiation().size() > 1) {
			removePlacement = false;
			componentFileToRemove = null;
		}

		// If we are removing the placement, then see if other component placements are using the component file.
		// If not, mark it for removal;
		if (removePlacement) {
			String placementRefID = placement.getComponentFileRef().getRefid();

			// check top-level components (not in host collocation)
			for (SadComponentPlacement tmpPlacement : sad.getPartitioning().getComponentPlacement()) {
				if (tmpPlacement == placement) {
					continue;
				}
				String tmpRefID = tmpPlacement.getComponentFileRef().getRefid();
				if (tmpRefID.equals(placementRefID)) {
					componentFileToRemove = null;
					break;
				}
			}

			// check components in a host collocation
			out: for (HostCollocation hostCol : sad.getPartitioning().getHostCollocation()) {
				for (SadComponentPlacement tmpPlacement : hostCol.getComponentPlacement()) {
					if (tmpPlacement == placement) {
						continue;
					}
					String tmpRefID = tmpPlacement.getComponentFileRef().getRefid();
					if (tmpRefID.equals(placementRefID)) {
						componentFileToRemove = null;
						break out;
					}
				}
			}
		}

		if (componentFileToRemove != null) {
			sad.getComponentFiles().getComponentFile().remove(componentFileToRemove);
		}

		// delete component instantiation
		placement.getComponentInstantiation().remove(ciToDelete);

		// delete component placement
		if (removePlacement) {
			sad.getPartitioning().getComponentPlacement().remove(placement);
		}
	}

	/**
	 * Swap start order of provided components. Change assembly controller if start order zero.
	 * @param sad
	 * @param featureProvider
	 * @param lowerCi - The component that currently has the lower start order
	 * @param higherCi - The component that currently has the higher start order
	 */
	public static void swapStartOrder(SoftwareAssembly sad, IFeatureProvider featureProvider, final SadComponentInstantiation lowerCi,
		final SadComponentInstantiation higherCi) {

		// editing domain for our transaction
		TransactionalEditingDomain editingDomain = featureProvider.getDiagramTypeProvider().getDiagramBehavior().getEditingDomain();

		// get AssemblyController
		final AssemblyController assemblyController = sad.getAssemblyController();

		// Perform business object manipulation in a Command
		TransactionalCommandStack stack = (TransactionalCommandStack) editingDomain.getCommandStack();
		stack.execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {

				// Increment start order
				lowerCi.setStartOrder(higherCi.getStartOrder());
				// Decrement start order
				higherCi.setStartOrder(higherCi.getStartOrder().subtract(BigInteger.ONE));

				// set assembly controller if start order is zero
				if (lowerCi.getStartOrder().compareTo(BigInteger.ZERO) == 0) {
					assemblyController.getComponentInstantiationRef().setInstantiation(lowerCi);
				} else if (higherCi.getStartOrder().compareTo(BigInteger.ZERO) == 0) {
					assemblyController.getComponentInstantiationRef().setInstantiation(higherCi);
				}
			}
		});
	}

}

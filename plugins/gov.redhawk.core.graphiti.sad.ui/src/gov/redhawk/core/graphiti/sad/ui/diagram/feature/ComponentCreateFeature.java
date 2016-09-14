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
package gov.redhawk.core.graphiti.sad.ui.diagram.feature;

import java.math.BigInteger;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;
import org.eclipse.graphiti.mm.pictograms.Diagram;

import gov.redhawk.core.graphiti.sad.ui.ext.ComponentShape;
import gov.redhawk.core.graphiti.sad.ui.internal.diagram.patterns.ComponentPattern;
import gov.redhawk.core.graphiti.ui.util.DUtil;
import gov.redhawk.sca.util.PluginUtil;
import mil.jpeojtrs.sca.partitioning.ComponentFile;
import mil.jpeojtrs.sca.partitioning.ComponentFileRef;
import mil.jpeojtrs.sca.partitioning.ComponentFiles;
import mil.jpeojtrs.sca.partitioning.NamingService;
import mil.jpeojtrs.sca.partitioning.PartitioningFactory;
import mil.jpeojtrs.sca.sad.AssemblyController;
import mil.jpeojtrs.sca.sad.FindComponent;
import mil.jpeojtrs.sca.sad.HostCollocation;
import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.sad.SadComponentInstantiationRef;
import mil.jpeojtrs.sca.sad.SadComponentPlacement;
import mil.jpeojtrs.sca.sad.SadFactory;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.spd.SoftPkg;

public class ComponentCreateFeature extends AbstractCreateFeature {

	public static final String OVERRIDE_USAGE_NAME = "OverrideUsageName";
	public static final String OVERRIDE_INSTANTIATION_ID = "OverrideInstantiationId";

	private SoftPkg spd = null;
	private String implId = null;

	@Override
	public String getDescription() {
		// Provides the context menu Undo/Redo description
		return "Add Component to Diagram";
	}

	public ComponentCreateFeature(IFeatureProvider fp, final SoftPkg spd, String implId) {
		super(fp, spd.getName(), spd.getDescription());
		this.spd = spd;
		this.implId = implId;
	}

	// diagram and hostCollocation acceptable
	@Override
	public boolean canCreate(ICreateContext context) {
		if (context.getTargetContainer() instanceof Diagram || DUtil.getBusinessObject(context.getTargetContainer(), HostCollocation.class) != null) {
			return true;
		}
		return false;
	}

	public Object[] create(ICreateContext context) {

		if (spd == null) {
			// TODO: return some kind of error
			return null;
		}

		// collect overrides (currently used by GraphitiModelMap)
		final String usageName = (String) context.getProperty(OVERRIDE_USAGE_NAME);
		final String instantiationId = (String) context.getProperty(OVERRIDE_INSTANTIATION_ID);

		// editing domain for our transaction
		TransactionalEditingDomain editingDomain = getFeatureProvider().getDiagramTypeProvider().getDiagramBehavior().getEditingDomain();

		// get sad from diagram
		final SoftwareAssembly sad = DUtil.getDiagramSAD(getDiagram());

		// determine if target is HostCollocation ContainerShape
		HostCollocation hostCollocation = DUtil.getBusinessObject(context.getTargetContainer(), HostCollocation.class);

		// if HostCollocation was the target use it, otherwise add to sad partitioning
		final EList<SadComponentPlacement> componentPlacementList = hostCollocation != null ? hostCollocation.getComponentPlacement()
			: sad.getPartitioning().getComponentPlacement();

		// container for new component instantiation, necessary for reference after command execution
		final SadComponentInstantiation[] componentInstantiations = new SadComponentInstantiation[1];

		// Create Component Related objects in SAD model
		TransactionalCommandStack stack = (TransactionalCommandStack) editingDomain.getCommandStack();
		stack.execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				// add component file
				ComponentFile componentFile = createComponentFile(sad, spd);

				// create component placement and add to list
				final SadComponentPlacement componentPlacement = SadFactory.eINSTANCE.createSadComponentPlacement();
				componentPlacementList.add(componentPlacement);

				// create component file ref
				final ComponentFileRef ref = PartitioningFactory.eINSTANCE.createComponentFileRef();
				ref.setFile(componentFile);
				componentPlacement.setComponentFileRef(ref);

				// component instantiation
				componentInstantiations[0] = createComponentInstantiation(sad, componentPlacement, spd, usageName, instantiationId);

				// determine start order and potentially create assembly controller if zero is zero
				intializeComponentStartOrder(sad, componentInstantiations[0]);

				// if start order is zero then set as assembly controller
				if (componentInstantiations[0].getStartOrder().compareTo(BigInteger.ZERO) == 0) {
					// create assembly controller
					AssemblyController assemblyController = SadFactory.eINSTANCE.createAssemblyController();
					SadComponentInstantiationRef sadComponentInstantiationRef = SadFactory.eINSTANCE.createSadComponentInstantiationRef();
					sadComponentInstantiationRef.setInstantiation(componentInstantiations[0]);
					assemblyController.setComponentInstantiationRef(sadComponentInstantiationRef);
					sad.setAssemblyController(assemblyController);
				}
			}
		});

		// call add feature
		ComponentShape shape = (ComponentShape) addGraphicalRepresentation(context, componentInstantiations[0]);

		// If this is a runtime diagram (i.e., local sandbox), the component shape should start off as disabled
		// because there is no LocalScaComponent associated with the SadComponentInstantiation yet.
		if (DUtil.isDiagramRuntime(getDiagram())) {
			shape.setEnabled(false);
		}

		return new Object[] { componentInstantiations[0] };
	}

	// adds corresponding component file to sad if not already present
	private ComponentFile createComponentFile(final SoftwareAssembly sad, final SoftPkg spd) {

		// See if we have to add a new component file
		ComponentFile file = null;
		// set component files is not already set
		ComponentFiles cFiles = sad.getComponentFiles();
		if (cFiles == null) {
			cFiles = PartitioningFactory.eINSTANCE.createComponentFiles();
			sad.setComponentFiles(cFiles);
		}
		// search for existing compatible component file for spd
		for (final ComponentFile f : cFiles.getComponentFile()) {
			if (f == null) {
				continue;
			}
			final SoftPkg fSpd = f.getSoftPkg();
			if (fSpd != null && PluginUtil.equals(spd.getId(), fSpd.getId())) {
				file = f;
				break;
			}
		}
		// add new component file if not found above
		if (file == null) {
			file = SadFactory.eINSTANCE.createComponentFile();
			cFiles.getComponentFile().add(file);
			file.setSoftPkg(spd);
		}

		return file;
	}

	// create ComponentInstantiation
	private SadComponentInstantiation createComponentInstantiation(final SoftwareAssembly sad, final SadComponentPlacement componentPlacement,
		final SoftPkg spd, final String providedUsageName, final String providedInstantiationId) {

		SadComponentInstantiation sadComponentInstantiation = SadFactory.eINSTANCE.createSadComponentInstantiation();

		// use provided name/id if provided otherwise generate
		String id = (providedInstantiationId != null) ? providedInstantiationId : SoftwareAssembly.Util.createComponentIdentifier(sad, spd.getName());
		String compName = (providedUsageName != null) ? providedUsageName : SoftwareAssembly.Util.createComponentUsageName(sad, id);

		sadComponentInstantiation.setUsageName(compName);
		sadComponentInstantiation.setId(id);

		final FindComponent findComponent = SadFactory.eINSTANCE.createFindComponent();
		final NamingService namingService = PartitioningFactory.eINSTANCE.createNamingService();
		namingService.setName(compName);
		findComponent.setNamingService(namingService);
		sadComponentInstantiation.setFindComponent(findComponent);
		sadComponentInstantiation.setImplID(implId);

		// add to placement
		componentPlacement.getComponentInstantiation().add(sadComponentInstantiation);

		return sadComponentInstantiation;
	}

	/**
	 * Initialize component with appropriate start order in sad (one - up).
	 * if no other components exist in sad make component assembly controller
	 * @param sad
	 * @param component
	 */
	public void intializeComponentStartOrder(final SoftwareAssembly sad, final SadComponentInstantiation component) {

		// determine start order for existing components
		BigInteger highestStartOrder = ComponentPattern.determineHighestStartOrder(sad);

		// increment start order for new component
		BigInteger startOrder = null;
		if (highestStartOrder == null) {
			// Should only get here if no other components exist
			// Assume assembly controller and assign start order of 0
			startOrder = BigInteger.ZERO;
		} else {
			startOrder = highestStartOrder.add(BigInteger.ONE);
		}

		// set start order
		component.setStartOrder(startOrder);
	}

}

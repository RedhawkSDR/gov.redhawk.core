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

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICreateContext;
import org.eclipse.graphiti.mm.pictograms.Diagram;

import gov.redhawk.core.graphiti.sad.ui.ext.ComponentShape;
import gov.redhawk.core.graphiti.sad.ui.utils.SADUtils;
import gov.redhawk.core.graphiti.ui.diagram.features.AbstractCreateInstantiationFeature;
import gov.redhawk.core.graphiti.ui.util.DUtil;
import mil.jpeojtrs.sca.sad.HostCollocation;
import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.spd.SoftPkg;

public class ComponentCreateFeature extends AbstractCreateInstantiationFeature {

	public static final String OVERRIDE_USAGE_NAME = "OverrideUsageName";
	public static final String OVERRIDE_INSTANTIATION_ID = "OverrideInstantiationId";

	@Override
	public String getDescription() {
		// Provides the context menu Undo/Redo description
		return "Add Component to Diagram";
	}

	public ComponentCreateFeature(IFeatureProvider fp, final SoftPkg spd, String implId) {
		super(fp, spd, implId);
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
		if (getSoftPkg() == null) {
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
		final HostCollocation hostCollocation = DUtil.getBusinessObject(context.getTargetContainer(), HostCollocation.class);

		// container for new component instantiation, necessary for reference after command execution
		final SadComponentInstantiation[] componentInstantiations = new SadComponentInstantiation[1];

		// Create Component Related objects in SAD model
		TransactionalCommandStack stack = (TransactionalCommandStack) editingDomain.getCommandStack();
		stack.execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {
				componentInstantiations[0] = SADUtils.createComponentInstantiation(getSoftPkg(), sad, hostCollocation, usageName, instantiationId,
					getImplementationId());
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
}

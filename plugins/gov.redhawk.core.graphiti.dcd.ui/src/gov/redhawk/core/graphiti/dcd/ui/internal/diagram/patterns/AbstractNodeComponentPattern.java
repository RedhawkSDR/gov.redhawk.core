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
package gov.redhawk.core.graphiti.dcd.ui.internal.diagram.patterns;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.context.IAddContext;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.IMoveShapeContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.IResizeShapeContext;
import org.eclipse.graphiti.features.context.impl.RemoveContext;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import gov.redhawk.core.graphiti.ui.diagram.patterns.AbstractPortSupplierPattern;
import gov.redhawk.core.graphiti.ui.util.DUtil;
import mil.jpeojtrs.sca.dcd.DcdComponentInstantiation;
import mil.jpeojtrs.sca.dcd.DcdComponentPlacement;
import mil.jpeojtrs.sca.dcd.DcdConnectInterface;
import mil.jpeojtrs.sca.dcd.DeviceConfiguration;
import mil.jpeojtrs.sca.partitioning.ComponentFile;
import mil.jpeojtrs.sca.partitioning.ComponentSupportedInterfaceStub;
import mil.jpeojtrs.sca.partitioning.ProvidesPortStub;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;

public abstract class AbstractNodeComponentPattern extends AbstractPortSupplierPattern {

	public AbstractNodeComponentPattern() {
		super(null);
	}

	// THE FOLLOWING THREE METHODS DETERMINE IF PATTERN IS APPLICABLE TO OBJECT
	@Override
	public boolean isMainBusinessObjectApplicable(Object mainBusinessObject) {
		if (mainBusinessObject instanceof DcdComponentInstantiation) {
			return isInstantiationApplicable((DcdComponentInstantiation) mainBusinessObject);
		}
		return false;
	}

	@Override
	protected boolean isPatternControlled(PictogramElement pictogramElement) {
		Object domainObject = getBusinessObjectForPictogramElement(pictogramElement);
		return isMainBusinessObjectApplicable(domainObject);
	}

	@Override
	protected boolean isPatternRoot(PictogramElement pictogramElement) {
		Object domainObject = getBusinessObjectForPictogramElement(pictogramElement);
		return isMainBusinessObjectApplicable(domainObject);
	}

	protected abstract boolean isInstantiationApplicable(DcdComponentInstantiation instantiation);

	@Override
	public boolean canAdd(IAddContext context) {
		if (context.getNewObject() instanceof DcdComponentInstantiation) {
			if (context.getTargetContainer() instanceof Diagram) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean canRemove(IRemoveContext context) {
		// TODO: this used to return false, doing this so we can remove components during the
		// RHDiagramUpdateFeature...might be negative consequences
		Object obj = DUtil.getBusinessObject(context.getPictogramElement());
		if (obj instanceof DcdComponentInstantiation) {
			return true;
		}
		return false;
	}

	/**
	 * Return true if the user has selected a pictogram element that is linked with
	 * a DcdComponentInstantiation instance
	 */
	@Override
	public boolean canDelete(IDeleteContext context) {
		Object obj = DUtil.getBusinessObject(context.getPictogramElement());
		if (obj instanceof DcdComponentInstantiation) {
			return true;
		}
		return false;
	}

	/**
	 * Delete the DcdComponentInstantiation linked to the PictogramElement.
	 */
	@Override
	public void delete(IDeleteContext context) {

		// set componentToDelete
		final DcdComponentInstantiation ciToDelete = (DcdComponentInstantiation) DUtil.getBusinessObject(context.getPictogramElement());

		// editing domain for our transaction
		TransactionalEditingDomain editingDomain = getFeatureProvider().getDiagramTypeProvider().getDiagramBehavior().getEditingDomain();

		// get dcd from diagram
		final DeviceConfiguration dcd = DUtil.getDiagramDCD(getDiagram());

		// Perform business object manipulation in a Command
		TransactionalCommandStack stack = (TransactionalCommandStack) editingDomain.getCommandStack();
		stack.execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {

				// delete component from DeviceConfiguration
				deleteComponentInstantiation(ciToDelete, dcd);

			}
		});

		// delete graphical component for component as well as removing all connections
		IRemoveContext rc = new RemoveContext(context.getPictogramElement());
		IFeatureProvider featureProvider = getFeatureProvider();
		IRemoveFeature removeFeature = featureProvider.getRemoveFeature(rc);
		if (removeFeature != null) {
			removeFeature.remove(rc);
		}
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
		// gather connections
		List<DcdConnectInterface> connectionsToRemove = new ArrayList<DcdConnectInterface>();
		if (dcd.getConnections() != null) {
			for (DcdConnectInterface connectionInterface : dcd.getConnections().getConnectInterface()) {
				// we need to do thorough null checks here because of the many connection possibilities. Firstly a
				// connection requires only a usesPort and either (providesPort || componentSupportedInterface)
				// and therefore null checks need to be performed.
				// FindBy connections don't have ComponentInstantiationRefs and so they can also be null
				if ((connectionInterface.getComponentSupportedInterface() != null
					&& connectionInterface.getComponentSupportedInterface().getComponentInstantiationRef() != null
					&& ciToDelete.getId().equals(connectionInterface.getComponentSupportedInterface().getComponentInstantiationRef().getRefid()))
					|| (connectionInterface.getUsesPort() != null && connectionInterface.getUsesPort().getComponentInstantiationRef() != null
						&& ciToDelete.getId().equals(connectionInterface.getUsesPort().getComponentInstantiationRef().getRefid()))
					|| (connectionInterface.getProvidesPort() != null && connectionInterface.getProvidesPort().getComponentInstantiationRef() != null
						&& ciToDelete.getId().equals(connectionInterface.getProvidesPort().getComponentInstantiationRef().getRefid()))) {
					connectionsToRemove.add(connectionInterface);
				}
			}
		}
		// remove gathered connections
		if (dcd.getConnections() != null) {
			dcd.getConnections().getConnectInterface().removeAll(connectionsToRemove);
		}

		// delete component file if applicable
		// figure out which component file we are using and if no other component placements using it then remove it.
		ComponentFile componentFileToRemove = placement.getComponentFileRef().getFile();
		// check components (not in host collocation)
		for (DcdComponentPlacement p : dcd.getPartitioning().getComponentPlacement()) {
			if (p != placement && p.getComponentFileRef().getRefid().equals(placement.getComponentFileRef().getRefid())) {
				componentFileToRemove = null;
			}
		}

		if (componentFileToRemove != null) {
			dcd.getComponentFiles().getComponentFile().remove(componentFileToRemove);
		}

		// delete component placement
		EcoreUtil.delete(placement);
	}

	@Override
	public boolean canResizeShape(IResizeShapeContext context) {
		return true;
	}

	public boolean canMoveShape(IMoveShapeContext context) {
		DcdComponentInstantiation dcdComponentInstantiation = (DcdComponentInstantiation) DUtil.getBusinessObject(context.getPictogramElement());
		if (dcdComponentInstantiation == null) {
			return false;
		}

		if (context.getTargetContainer() instanceof Diagram) {
			return true;
		}

		return false;
	}

	@Override
	protected String getOuterTitle(EObject obj) {
		if (obj instanceof DcdComponentInstantiation) {
			try {
				return ((DcdComponentInstantiation) obj).getPlacement().getComponentFileRef().getFile().getSoftPkg().getName();
			} catch (NullPointerException e) {
				return "< Component Bad Reference >";
			}
		}
		return null;
	}

	@Override
	protected String getInnerTitle(EObject obj) {
		if (obj instanceof DcdComponentInstantiation) {
			return ((DcdComponentInstantiation) obj).getUsageName();
		}
		return null;
	}

	@Override
	protected void setInnerTitle(EObject businessObject, String value) {
		((DcdComponentInstantiation) businessObject).setUsageName(value);
	}

	@Override
	protected EList<UsesPortStub> getUses(EObject obj) {
		if (obj instanceof DcdComponentInstantiation) {
			return ((DcdComponentInstantiation) obj).getUses();
		}
		return null;
	}

	@Override
	protected EList<ProvidesPortStub> getProvides(EObject obj) {
		if (obj instanceof DcdComponentInstantiation) {
			return ((DcdComponentInstantiation) obj).getProvides();
		}
		return null;
	}

	@Override
	protected ComponentSupportedInterfaceStub getInterface(EObject obj) {
		if (obj instanceof DcdComponentInstantiation) {
			return ((DcdComponentInstantiation) obj).getInterfaceStub();
		}
		return null;
	}
}

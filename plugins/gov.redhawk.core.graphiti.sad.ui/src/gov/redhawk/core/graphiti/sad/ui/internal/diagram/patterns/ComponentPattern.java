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
package gov.redhawk.core.graphiti.sad.ui.internal.diagram.patterns;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
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

import gov.redhawk.core.graphiti.sad.ui.diagram.providers.WaveformImageProvider;
import gov.redhawk.core.graphiti.sad.ui.ext.RHSadGxFactory;
import gov.redhawk.core.graphiti.sad.ui.utils.SADUtils;
import gov.redhawk.core.graphiti.ui.diagram.patterns.AbstractPortSupplierPattern;
import gov.redhawk.core.graphiti.ui.diagram.providers.ImageProvider;
import gov.redhawk.core.graphiti.ui.ext.RHContainerShape;
import gov.redhawk.core.graphiti.ui.util.DUtil;
import gov.redhawk.core.graphiti.ui.util.StyleUtil;
import mil.jpeojtrs.sca.partitioning.ComponentSupportedInterfaceStub;
import mil.jpeojtrs.sca.partitioning.NamingService;
import mil.jpeojtrs.sca.partitioning.PartitioningFactory;
import mil.jpeojtrs.sca.partitioning.ProvidesPortStub;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;
import mil.jpeojtrs.sca.sad.ExternalPorts;
import mil.jpeojtrs.sca.sad.FindComponent;
import mil.jpeojtrs.sca.sad.HostCollocation;
import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.sad.SadComponentPlacement;
import mil.jpeojtrs.sca.sad.SadFactory;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;

public class ComponentPattern extends AbstractPortSupplierPattern {

	private URI spdUri = null;

	public ComponentPattern() {
		super(null);
	}

	public URI getSpdUri() {
		return spdUri;
	}

	public void setSpdUri(URI spdUri) {
		this.spdUri = spdUri;
	}

	@Override
	public String getCreateName() {
		return "Component";
	}

	// THE FOLLOWING THREE METHODS DETERMINE IF PATTERN IS APPLICABLE TO OBJECT
	@Override
	public boolean isMainBusinessObjectApplicable(Object mainBusinessObject) {
		return mainBusinessObject instanceof SadComponentInstantiation;
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

	// DIAGRAM FEATURES

	@Override
	public boolean canAdd(IAddContext context) {
		if (context.getNewObject() instanceof SadComponentInstantiation) {
			if (context.getTargetContainer() instanceof Diagram || DUtil.getBusinessObject(context.getTargetContainer(), HostCollocation.class) != null) {
				return true;
			}
			return false;
		}
		return false;
	}

	@Override
	public boolean canRemove(IRemoveContext context) {
		// TODO: this used to return false, doing this so we can remove components during the
		// RHDiagramUpdateFeature...might be negative consequences
		Object obj = DUtil.getBusinessObject(context.getPictogramElement());
		if (obj instanceof SadComponentInstantiation) {
			return true;
		}
		return false;
	}

	/**
	 * Return true if the user has selected a pictogram element that is linked with
	 * a SADComponentInstantiation instance
	 */
	@Override
	public boolean canDelete(IDeleteContext context) {
		Object obj = DUtil.getBusinessObject(context.getPictogramElement());
		if (obj instanceof SadComponentInstantiation) {
			return true;
		}
		return false;
	}

	/**
	 * Delete the SadComponentInstantiation linked to the PictogramElement.
	 */
	@Override
	public void delete(IDeleteContext context) {
		final SadComponentInstantiation ciToDelete = (SadComponentInstantiation) DUtil.getBusinessObject(context.getPictogramElement());
		TransactionalEditingDomain editingDomain = getFeatureProvider().getDiagramTypeProvider().getDiagramBehavior().getEditingDomain();
		final SoftwareAssembly sad = DUtil.getDiagramSAD(getDiagram());

		// Perform business object manipulation in a Command
		TransactionalCommandStack stack = (TransactionalCommandStack) editingDomain.getCommandStack();
		stack.execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {

				// delete component from SoftwareAssembly
				SADUtils.deleteComponentInstantiation(ciToDelete, sad);

				// re-organize start order
				SADUtils.organizeStartOrder(sad, getDiagram(), getFeatureProvider());
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

	@Override
	public boolean canResizeShape(IResizeShapeContext context) {
		return true;
	}

	@Override
	protected RHContainerShape createContainerShape() {
		return RHSadGxFactory.eINSTANCE.createComponentShape();
	}

	public boolean canMoveShape(IMoveShapeContext context) {

		SadComponentInstantiation sadComponentInstantiation = (SadComponentInstantiation) DUtil.getBusinessObject(context.getPictogramElement());
		if (sadComponentInstantiation == null) {
			return false;
		}

		// if moving to HostCollocation to Sad Partitioning
		if (context.getTargetContainer() instanceof Diagram || DUtil.getBusinessObject(context.getTargetContainer(), HostCollocation.class) != null) {
			return true;
		}
		return false;

	}

	/**
	 * Moves Component shape.
	 * if moving to HostCollocation or away from one modify underlying model and allow parent class to perform graphical
	 * move
	 * if moving within the same container allow parent class to perform graphical move
	 */
	public void moveShape(IMoveShapeContext context) {
		SadComponentInstantiation ci = (SadComponentInstantiation) DUtil.getBusinessObject(context.getPictogramElement());

		final SoftwareAssembly sad = DUtil.getDiagramSAD(getDiagram());

		// if moving inside the same container
		if (context.getSourceContainer() == context.getTargetContainer()) {
			super.moveShape(context);
		}

		HostCollocation sourceHostCollocation = DUtil.getBusinessObject(context.getSourceContainer(), HostCollocation.class);
		HostCollocation targetHostCollocation = DUtil.getBusinessObject(context.getTargetContainer(), HostCollocation.class);

		if (sourceHostCollocation != null && targetHostCollocation != null && sourceHostCollocation != targetHostCollocation) {
			// Moving from one host collocation to another
			sourceHostCollocation.getComponentPlacement().remove((SadComponentPlacement) ci.getPlacement());
			targetHostCollocation.getComponentPlacement().add((SadComponentPlacement) ci.getPlacement());
			super.moveShape(context);
		} else if (targetHostCollocation != null && context.getSourceContainer() instanceof Diagram) {
			// Moving from top-level partitioning to a host collocation
			sad.getPartitioning().getComponentPlacement().remove(ci.getPlacement());
			targetHostCollocation.getComponentPlacement().add((SadComponentPlacement) ci.getPlacement());
			super.moveShape(context);
		} else if (sourceHostCollocation != null && context.getTargetContainer() instanceof Diagram) {
			// Moving from a host collocation to top-level partitioning
			sad.getPartitioning().getComponentPlacement().add((SadComponentPlacement) ci.getPlacement());
			sourceHostCollocation.getComponentPlacement().remove((SadComponentPlacement) ci.getPlacement());
			super.moveShape(context);
		}
	}

	@Override
	protected String getOuterTitle(EObject obj) {
		if (obj instanceof SadComponentInstantiation) {
			return getOuterTitle((SadComponentInstantiation) obj);
		}
		return null;
	}

	@Override
	protected String getInnerTitle(EObject obj) {
		if (obj instanceof SadComponentInstantiation) {
			return getInnerTitle((SadComponentInstantiation) obj);
		}
		return null;
	}

	/**
	 * Provides the title of the outer shape
	 * @param ci
	 * @return
	 */
	public String getOuterTitle(SadComponentInstantiation ci) {
		try {
			return ci.getPlacement().getComponentFileRef().getFile().getSoftPkg().getName();
		} catch (NullPointerException e) {
			return "< Component Bad Reference >";
		}
	}

	/**
	 * Provides the title of the inner shape
	 * @param ci
	 * @return
	 */
	public String getInnerTitle(SadComponentInstantiation ci) {
		String usageName = ci.getUsageName();
		return (usageName != null) ? usageName : ci.getId();
	}

	@Override
	protected void setInnerTitle(EObject businessObject, String value) {
		((SadComponentInstantiation) businessObject).setUsageName(value);
		FindComponent fc = ((SadComponentInstantiation) businessObject).getFindComponent();
		if (fc != null && fc.getNamingService() != null) {
			fc.getNamingService().setName(value);
		} else {
			fc = SadFactory.eINSTANCE.createFindComponent();
			NamingService ns = PartitioningFactory.eINSTANCE.createNamingService();
			ns.setName(value);
			fc.setNamingService(ns);
			((SadComponentInstantiation) businessObject).setFindComponent(fc);
		}
	}

	@Override
	protected EList<UsesPortStub> getUses(EObject obj) {
		if (obj instanceof SadComponentInstantiation) {
			return ((SadComponentInstantiation) obj).getUses();
		}
		return null;
	}

	@Override
	protected EList<ProvidesPortStub> getProvides(EObject obj) {
		if (obj instanceof SadComponentInstantiation) {
			return ((SadComponentInstantiation) obj).getProvides();
		}
		return null;
	}

	@Override
	protected ComponentSupportedInterfaceStub getInterface(EObject obj) {
		if (obj instanceof SadComponentInstantiation) {
			return ((SadComponentInstantiation) obj).getInterfaceStub();
		}
		return null;
	}

	@Override
	protected String getOuterImageId() {
		return ImageProvider.IMG_SPD;
	}

	@Override
	protected String getInnerImageId() {
		return WaveformImageProvider.IMG_COMPONENT;
	}

	@Override
	protected String getStyleForOuter() {
		return StyleUtil.OUTER_SHAPE;
	}

	@Override
	protected String getStyleForInner() {
		return StyleUtil.COMPONENT_INNER;
	}

	/**
	 * Returns component, sad, and external ports. Order does matter.
	 */
	@Override
	protected List<EObject> getBusinessObjectsToLink(EObject componentInstantiation) {
		// get external ports
		ExternalPorts externalPorts = DUtil.getDiagramSAD(getDiagram()).getExternalPorts();

		// get sad from diagram, we need to link it to all shapes so the diagram will update when changes occur to
		// assembly controller and external ports
		List<EObject> businessObjectsToLink = new ArrayList<EObject>();
		final SoftwareAssembly sad = DUtil.getDiagramSAD(getDiagram());
		// ORDER MATTERS, CI must be first
		businessObjectsToLink.add(componentInstantiation);
		businessObjectsToLink.add(sad);
		if (externalPorts != null) {
			businessObjectsToLink.add(externalPorts);
		}

		return businessObjectsToLink;
	}

}

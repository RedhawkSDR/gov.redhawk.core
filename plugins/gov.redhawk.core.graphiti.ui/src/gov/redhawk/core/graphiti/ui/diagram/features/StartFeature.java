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
package gov.redhawk.core.graphiti.ui.diagram.features;

import org.eclipse.core.runtime.Platform;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.context.ICustomContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;

import gov.redhawk.core.graphiti.ui.diagram.providers.ImageProvider;
import gov.redhawk.core.graphiti.ui.ext.RHContainerShape;
import gov.redhawk.core.graphiti.ui.util.DUtil;
import gov.redhawk.model.sca.ScaAbstractComponent;
import gov.redhawk.sca.model.jobs.StartJob;
import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;
import mil.jpeojtrs.sca.scd.ComponentType;
import mil.jpeojtrs.sca.scd.SoftwareComponent;

public class StartFeature extends NonUndoableCustomFeature {

	public StartFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public String getName() {
		return "Start";
	}

	@Override
	public String getDescription() {
		return "Start resource";
	}

	@Override
	public boolean canExecute(ICustomContext context) {
		RHContainerShape shape = (RHContainerShape) context.getPictogramElements()[0];
		Object object = DUtil.getBusinessObject(shape);
		if (!(object instanceof ComponentInstantiation)) {
			return false;
		}
		SoftwareComponent scd = ComponentInstantiation.Util.getScd((ComponentInstantiation) object);
		boolean isService = SoftwareComponent.Util.getWellKnownComponentType(scd).equals(ComponentType.SERVICE);
		return (!isService && !shape.isStarted() && shape.isEnabled());
	}

	@Override
	public void execute(ICustomContext context) {
		for (PictogramElement pe : context.getPictogramElements()) {
			ScaAbstractComponent< ? > component = Platform.getAdapterManager().getAdapter(pe, ScaAbstractComponent.class);
			final StartJob job = new StartJob(component.identifier(), component);
			job.setUser(true);
			job.schedule();
		}
	}

	@Override
	public String getImageId() {
		return ImageProvider.IMG_START;
	}

}

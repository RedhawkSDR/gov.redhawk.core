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
package gov.redhawk.core.graphiti.ui.properties;

import org.eclipse.core.runtime.Platform;
import org.eclipse.gef.EditPart;
import org.eclipse.graphiti.mm.pictograms.Anchor;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AdvancedPropertySection;

import gov.redhawk.core.graphiti.ui.ext.RHContainerShape;
import gov.redhawk.core.graphiti.ui.util.DUtil;
import gov.redhawk.model.sca.ScaPropertyContainer;
import gov.redhawk.model.sca.ScaProvidesPort;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.ScaWaveform;
import mil.jpeojtrs.sca.partitioning.ProvidesPortStub;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;

public class AdvancedEditPartPropertySection extends AdvancedPropertySection {

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		ISelection newSelection = selection;
		if (selection instanceof StructuredSelection) {
			StructuredSelection ss = (StructuredSelection) selection;
			Object obj = ss.getFirstElement();
			if (obj instanceof EditPart) {
				EditPart ep = (EditPart) obj;
				obj = getScaObjectForEditPart(ep);
			}
			newSelection = new StructuredSelection(new Object[] { obj });
		}
		super.setInput(part, newSelection);
	}

	protected Object getScaObjectForEditPart(EditPart ep) {
		Object graphitiModel = ep.getModel();
		if (graphitiModel instanceof RHContainerShape) {
			return Platform.getAdapterManager().getAdapter(ep, ScaPropertyContainer.class);
		} else if (graphitiModel instanceof Anchor) {
			Object rhModel = ((Anchor) graphitiModel).getLink().getBusinessObjects().get(0);
			if (rhModel instanceof ProvidesPortStub) {
				return Platform.getAdapterManager().getAdapter(ep, ScaProvidesPort.class);
			} else if (rhModel instanceof UsesPortStub) {
				return Platform.getAdapterManager().getAdapter(ep, ScaUsesPort.class);
			}
		} else if (graphitiModel instanceof Diagram) {
			return DUtil.getBusinessObject((Diagram) graphitiModel, ScaWaveform.class);
		}
		return null;
	}

}

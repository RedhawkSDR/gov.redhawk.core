/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.sca.dcd.diagram.providers;

import gov.redhawk.diagram.providers.PartitioningElementTypes;
import gov.redhawk.diagram.providers.PartitioningModelingAssistantProvider;

import java.util.ArrayList;
import java.util.List;

import mil.jpeojtrs.sca.dcd.DeviceConfiguration;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DeviceConfigurationEditPart;
import mil.jpeojtrs.sca.dcd.diagram.part.DcdVisualIDRegistry;
import mil.jpeojtrs.sca.dcd.diagram.providers.DcdElementTypes;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.modelingassistant.IModelingAssistantOperation;
import org.eclipse.gmf.runtime.notation.View;

/**
 * 
 */
public class DcdModelingAssistantProvider extends PartitioningModelingAssistantProvider {

	public DcdModelingAssistantProvider() {
		super(RedhawkDcdElementTypes.INSTANCE);
	}

	@Override
	public boolean provides(final IOperation operation) {
		final boolean provides = super.provides(operation);
		if (provides && operation instanceof IModelingAssistantOperation) {
			final IModelingAssistantOperation modOp = (IModelingAssistantOperation) operation;
			final Object editPart = modOp.getContext().getAdapter(EditPart.class);
			if (editPart instanceof EditPart) {
				final EditPart part = (EditPart) editPart;
				final String modelId = DcdVisualIDRegistry.getModelID((View) part.getModel());
				if (DeviceConfigurationEditPart.MODEL_ID.equals(modelId)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public List< ? > getTypesForPopupBar(final IAdaptable element) {
		final EObject semanticObject = (EObject) element.getAdapter(EObject.class);
		if (semanticObject instanceof DeviceConfiguration) {
			final List<IElementType> retVal = new ArrayList<IElementType>();
			retVal.add(DcdElementTypes.DcdComponentPlacement_3001);
			retVal.add(PartitioningElementTypes.FindByStub);
			return retVal;
		}
		return super.getTypesForPopupBar(element);
	}

}

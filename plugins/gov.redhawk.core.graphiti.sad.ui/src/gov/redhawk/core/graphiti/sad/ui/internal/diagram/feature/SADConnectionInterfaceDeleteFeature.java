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
package gov.redhawk.core.graphiti.sad.ui.internal.diagram.feature;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IRemoveFeature;
import org.eclipse.graphiti.features.context.IDeleteContext;
import org.eclipse.graphiti.features.context.IRemoveContext;
import org.eclipse.graphiti.features.context.impl.RemoveContext;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.ui.features.DefaultDeleteFeature;

import gov.redhawk.core.graphiti.ui.util.DUtil;
import mil.jpeojtrs.sca.sad.SadConnectInterface;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;

public class SADConnectionInterfaceDeleteFeature extends DefaultDeleteFeature {

	public SADConnectionInterfaceDeleteFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canDelete(IDeleteContext context) {
		return !DUtil.isDiagramReadOnly(getDiagram());
	}

	@Override
	public void delete(IDeleteContext context) {
		setDoneChanges(false);

		PictogramElement pe = context.getPictogramElement();

		preDelete(context);

		// delete business objects
		SadConnectInterface connectInterface = null;
		if (pe != null && pe.getLink() != null) {
			for (EObject eObj : pe.getLink().getBusinessObjects()) {
				if (eObj instanceof SadConnectInterface) {
					connectInterface = (SadConnectInterface) eObj;
					break;
				}
			}
		}
		final SadConnectInterface finalConnectInterface = connectInterface;

		// editing domain for our transaction
		TransactionalEditingDomain editingDomain = getFeatureProvider().getDiagramTypeProvider().getDiagramBehavior().getEditingDomain();

		// get sad from diagram
		final SoftwareAssembly sad = DUtil.getDiagramSAD(getDiagram());

		// Create Component Related objects in SAD model
		TransactionalCommandStack stack = (TransactionalCommandStack) editingDomain.getCommandStack();
		stack.execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {

				// assembly controller may reference componentInstantiation
				// delete reference if applicable
				if (sad.getConnections() != null) {
					sad.getConnections().getConnectInterface().remove(finalConnectInterface);
				}

			}
		});

		// remove graphical components
		IRemoveContext rc = new RemoveContext(pe);
		IFeatureProvider featureProvider = getFeatureProvider();
		IRemoveFeature removeFeature = featureProvider.getRemoveFeature(rc);
		if (removeFeature != null) {
			removeFeature.remove(rc);
			setDoneChanges(true);
		}

		postDelete(context);
	}

}

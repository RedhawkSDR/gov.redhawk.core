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
package gov.redhawk.core.graphiti.dcd.ui.internal.diagram.feature;

import org.eclipse.graphiti.features.IDeleteFeature;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IUpdateContext;
import org.eclipse.graphiti.features.context.impl.DeleteContext;
import org.eclipse.graphiti.features.impl.AbstractUpdateFeature;
import org.eclipse.graphiti.features.impl.Reason;
import org.eclipse.graphiti.mm.pictograms.Connection;

import mil.jpeojtrs.sca.dcd.DcdConnectInterface;
import mil.jpeojtrs.sca.partitioning.ConnectionTarget;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;

public class DCDConnectionInterfaceUpdateFeature extends AbstractUpdateFeature {

	public DCDConnectionInterfaceUpdateFeature(IFeatureProvider fp) {
		super(fp);
	}

	@Override
	public boolean canUpdate(IUpdateContext context) {
		Object obj = DUtil.getBusinessObject(context.getPictogramElement());

		if (obj != null && obj instanceof DcdConnectInterface) {
			return true;
		}
		return false;
	}

	@Override
	public IReason updateNeeded(IUpdateContext context) {
		Connection connectionPE = null;
		DcdConnectInterface sadConnectInterface = null;

		if (context.getPictogramElement() instanceof Connection) {
			connectionPE = (Connection) context.getPictogramElement();
		}
		if (DUtil.getBusinessObject(context.getPictogramElement()) instanceof DcdConnectInterface) {
			sadConnectInterface = (DcdConnectInterface) DUtil.getBusinessObject(context.getPictogramElement());
		}

		Reason requiresUpdate = internalUpdate(connectionPE, sadConnectInterface, getFeatureProvider(), false);

		return requiresUpdate;
	}

	@Override
	public boolean update(IUpdateContext context) {

		Connection connectionPE = (Connection) context.getPictogramElement();
		DcdConnectInterface sadConnectInterface = (DcdConnectInterface) DUtil.getBusinessObject(context.getPictogramElement());

		Reason updated = internalUpdate(connectionPE, sadConnectInterface, getFeatureProvider(), true);

		return updated.toBoolean();
	}

	/**
	 * Performs either an update or a check to determine if update is required.
	 * if performUpdate flag is true it will update the shape,
	 * otherwise it will return reason why update is required.
	 * @param ci
	 * @param performUpdate
	 * @return
	 */
	public Reason internalUpdate(Connection connectionPE, DcdConnectInterface connectInterface, IFeatureProvider featureProvider, boolean performUpdate) {

		boolean updateStatus = false;
		if (connectInterface == null) {
			return new Reason(false, "No updates required");
		}

		// Remove old connection error "X" decorator--we now use color decorators to handle error markings
		if (connectionPE.getConnectionDecorators().size() > 1) {
			if (performUpdate) {
				updateStatus = true;
				connectionPE.getConnectionDecorators().remove(1);
			} else {
				return new Reason(true, "Stale error decorator");
			}
		}

		// establish source/target for connection
		UsesPortStub source = connectInterface.getSource();
		ConnectionTarget target = connectInterface.getTarget();

		// source and target will be null if findBy or usesDevice is used, in this case pull stubs from diagram
		if (source == null) {
			source = DUtil.getBusinessObject(connectionPE.getStart(), UsesPortStub.class);
		}
		if (target == null) {
			target = DUtil.getBusinessObject(connectionPE.getEnd(), ConnectionTarget.class);
		}

		// problem if either source or target not present, unless dealing with a findby element
		if (source == null || target == null) {
			if (performUpdate) {
				updateStatus = true;
				// remove the connection (handles pe and business object)
				DeleteContext dc = new DeleteContext(connectionPE);
				IDeleteFeature deleteFeature = featureProvider.getDeleteFeature(dc);
				if (deleteFeature != null) {
					deleteFeature.delete(dc);
				}
			} else {
				String tmpMsg = "Target";
				if (connectInterface.getSource() == null) {
					tmpMsg = "Source";
				}
				return new Reason(true, tmpMsg + " endpoint for connection is null");
			}

			// check if not compatible draw error/warning decorator
		}

		if (updateStatus && performUpdate) {
			return new Reason(true, "Update successful");
		}

		return new Reason(false, "No updates required");
	}
}

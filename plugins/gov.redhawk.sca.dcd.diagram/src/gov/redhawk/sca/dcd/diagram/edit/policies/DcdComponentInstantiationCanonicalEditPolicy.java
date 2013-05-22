/**
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.sca.dcd.diagram.edit.policies;

import gov.redhawk.sca.dcd.diagram.edit.parts.DcdComponentInstantiationEditPart;
import gov.redhawk.sca.dcd.diagram.edit.parts.ProvidesPortStubEditPart;
import gov.redhawk.sca.dcd.diagram.edit.parts.UsesPortStubEditPart;
import mil.jpeojtrs.sca.dcd.DcdComponentInstantiation;
import mil.jpeojtrs.sca.spd.SoftPkg;

import org.eclipse.gmf.runtime.notation.Shape;

/**
 * @since 2.0
 */
public class DcdComponentInstantiationCanonicalEditPolicy extends mil.jpeojtrs.sca.dcd.diagram.edit.policies.DcdComponentInstantiationCanonicalEditPolicy {

	@Override
	protected void refreshSemantic() {
		if (getHost() instanceof DcdComponentInstantiationEditPart) {

			final DcdComponentInstantiationEditPart editPart = (DcdComponentInstantiationEditPart) getHost();

			if (editPart.getChildren() != null) {
				final DcdComponentInstantiation inst = (DcdComponentInstantiation) ((Shape) editPart.getModel()).getElement();
				final boolean showPorts = shouldShowPorts(inst.getPlacement().getComponentFileRef().getFile().getSoftPkg());

				for (final Object obj : editPart.getChildren()) {
					if (obj instanceof ProvidesPortStubEditPart) {
						final ProvidesPortStubEditPart part = (ProvidesPortStubEditPart) obj;
						part.setVisibility(showPorts);
					} else if (obj instanceof UsesPortStubEditPart) {
						final UsesPortStubEditPart part = (UsesPortStubEditPart) obj;
						part.setVisibility(showPorts);
					}
				}
			}
			super.refreshSemantic();
		}
	}

	private boolean shouldShowPorts(final SoftPkg softPkg) {
		return SoftPkg.Util.supportsPorts(softPkg);
	}
}

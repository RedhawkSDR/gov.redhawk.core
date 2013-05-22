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
package gov.redhawk.sca.sad.diagram.edit.policies;

import gov.redhawk.sca.sad.diagram.edit.parts.ProvidesPortStubEditPart;
import gov.redhawk.sca.sad.diagram.edit.parts.SadComponentInstantiationEditPart;
import gov.redhawk.sca.sad.diagram.edit.parts.UsesPortStubEditPart;
import mil.jpeojtrs.sca.partitioning.PartitioningPackage;
import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.spd.SoftPkg;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.notation.Shape;

/**
 * @since 2.0
 */
public class SadComponentInstantiationCanonicalEditPolicy extends mil.jpeojtrs.sca.sad.diagram.edit.policies.SadComponentInstantiationCanonicalEditPolicy {

	private static final EStructuralFeature[] SPD_PATH = new EStructuralFeature[] {
	        PartitioningPackage.Literals.COMPONENT_INSTANTIATION__PLACEMENT,
	        PartitioningPackage.Literals.COMPONENT_PLACEMENT__COMPONENT_FILE_REF,
	        PartitioningPackage.Literals.COMPONENT_FILE_REF__FILE,
	        PartitioningPackage.Literals.COMPONENT_FILE__SOFT_PKG
	};

	@Override
	protected void refreshSemantic() {
		if (getHost() instanceof SadComponentInstantiationEditPart) {

			final SadComponentInstantiationEditPart editPart = (SadComponentInstantiationEditPart) getHost();

			if (editPart.getChildren() != null) {
				final EObject element = ((Shape) editPart.getModel()).getElement();
				if (element instanceof SadComponentInstantiation) {
					final SadComponentInstantiation inst = (SadComponentInstantiation) element;
					final SoftPkg spd = ScaEcoreUtils.getFeature(inst, SadComponentInstantiationCanonicalEditPolicy.SPD_PATH);
					final boolean showPorts = shouldShowPorts(spd);

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
			}
			super.refreshSemantic();
		}
	}

	private boolean shouldShowPorts(final SoftPkg softPkg) {
		return SoftPkg.Util.supportsPorts(softPkg);
	}
}

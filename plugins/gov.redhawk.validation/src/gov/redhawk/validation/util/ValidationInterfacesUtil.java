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
package gov.redhawk.validation.util;

import java.util.List;

import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;
import mil.jpeojtrs.sca.partitioning.ComponentSupportedInterfaceStub;
import mil.jpeojtrs.sca.partitioning.PartitioningPackage;
import mil.jpeojtrs.sca.partitioning.ProvidesPortStub;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;
import mil.jpeojtrs.sca.scd.ScdPackage;
import mil.jpeojtrs.sca.scd.SupportsInterface;
import mil.jpeojtrs.sca.spd.SpdPackage;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * @since 2.1
 */
public class ValidationInterfacesUtil {

	private static final EStructuralFeature[] SUPPORT_PATH = new EStructuralFeature[] {
	        PartitioningPackage.Literals.COMPONENT_INSTANTIATION__PLACEMENT,
	        PartitioningPackage.Literals.COMPONENT_PLACEMENT__COMPONENT_FILE_REF,
	        PartitioningPackage.Literals.COMPONENT_FILE_REF__FILE,
	        PartitioningPackage.Literals.COMPONENT_FILE__SOFT_PKG,
	        SpdPackage.Literals.SOFT_PKG__DESCRIPTOR,
	        SpdPackage.Literals.DESCRIPTOR__COMPONENT,
	        ScdPackage.Literals.SOFTWARE_COMPONENT__COMPONENT_FEATURES,
	        ScdPackage.Literals.COMPONENT_FEATURES__SUPPORTS_INTERFACE
	};

	private ValidationInterfacesUtil() {

	}

	/**
	 * Method that determines whether or not two ports are compatible with one another.
	 * 
	 * @param source the source object needs to be a UsesPortStub
	 * @param target the target object needs to be either ProvidesPortStub or ComponentSupportedInterfaceStub
	 * @return boolean if they are compatible
	 */
	public static boolean areCompatible(final EObject source, final EObject target) {
		if (source == null || target == null) {
			return false;
		}

		if (source instanceof UsesPortStub) {
			final UsesPortStub usesStub = (UsesPortStub) source;

			if (target instanceof ProvidesPortStub) {
				final ProvidesPortStub providesStub = (ProvidesPortStub) target;

				if (providesStub.getProvides() != null && usesStub.getUses() != null) {
					if (providesStub.getProvides().getInterface() != null && usesStub.getUses().getInterface() != null) {
						return providesStub.getProvides().getInterface().isInstance(usesStub.getUses().getInterface());
					}
				}
			} else if (target instanceof ComponentSupportedInterfaceStub) {
				final ComponentSupportedInterfaceStub compStub = (ComponentSupportedInterfaceStub) target;

				if (compStub.eContainer() != null && compStub.eContainer() instanceof ComponentInstantiation) {
					final ComponentInstantiation comp = (ComponentInstantiation) compStub.eContainer();

					final List<SupportsInterface> supportsInterfaces = ScaEcoreUtils.getFeature(comp, ValidationInterfacesUtil.SUPPORT_PATH);

					if (supportsInterfaces != null && usesStub.getUses() != null) {
						boolean match = false;
						for (final SupportsInterface si : supportsInterfaces) {
							if (si.getInterface() != null && si.getInterface().isInstance(usesStub.getUses().getInterface())) {
								match = true;
							}
						}
						return match;
					}
				}
			}
		}

		return true;
	}
}

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
package gov.redhawk.diagram.util;

import java.util.List;

import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;
import mil.jpeojtrs.sca.partitioning.ComponentSupportedInterfaceStub;
import mil.jpeojtrs.sca.partitioning.FindByStub;
import mil.jpeojtrs.sca.partitioning.PartitioningPackage;
import mil.jpeojtrs.sca.partitioning.ProvidesPortStub;
import mil.jpeojtrs.sca.partitioning.UsesDeviceStub;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;
import mil.jpeojtrs.sca.scd.ScdPackage;
import mil.jpeojtrs.sca.scd.SupportsInterface;
import mil.jpeojtrs.sca.spd.SpdPackage;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * @since 4.0
 */
public class InterfacesUtil {

	/**
	 * Prevent this class from being instantiated
	 */
	private InterfacesUtil() {

	}

	/**
	 * Determine whether or not two ports are compatible and return a boolean to reflect such
	 * 
	 * @param source The source must be a UsesPortStub
	 * @param target The target can be either a ProvidesPortStub or ComponentSupportedInterfaceStub
	 * @return Return a boolean that shows whether or not the ports are compatible
	 */
	public static boolean areCompatible(final EObject source, final EObject target) {
		if (source == null || target == null) {
			return false;
		} else if (source.eContainer() instanceof FindByStub || target.eContainer() instanceof FindByStub) {
			return true;
		} else if (source.eContainer() instanceof UsesDeviceStub || target.eContainer() instanceof UsesDeviceStub) {
			return true;
		}

		if (source instanceof UsesPortStub && source.eContainer() instanceof ComponentInstantiation) {
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

				if (compStub.eContainer() != null) {
					if (compStub.eContainer() instanceof ComponentInstantiation) {
						final ComponentInstantiation comp = (ComponentInstantiation) compStub.eContainer();

						final List<SupportsInterface> supportsInterfaces = ScaEcoreUtils.getFeature(comp, InterfacesUtil.SUPPORT_PATH);

						if (supportsInterfaces != null) {
							boolean match = false;
							for (final SupportsInterface si : supportsInterfaces) {
								if (si.getInterface() != null && si.getInterface().isInstance(usesStub.getUses().getInterface())) {
									match = true;
									break;
								}
							}
							return match;
						}
					}
				}
			}
		}

		return true;
	}

	private static final EStructuralFeature[] SUPPORT_PATH = new EStructuralFeature[] { PartitioningPackage.Literals.COMPONENT_INSTANTIATION__PLACEMENT,
	        PartitioningPackage.Literals.COMPONENT_PLACEMENT__COMPONENT_FILE_REF, PartitioningPackage.Literals.COMPONENT_FILE_REF__FILE,
	        PartitioningPackage.Literals.COMPONENT_FILE__SOFT_PKG, SpdPackage.Literals.SOFT_PKG__DESCRIPTOR, SpdPackage.Literals.DESCRIPTOR__COMPONENT,
	        ScdPackage.Literals.SOFTWARE_COMPONENT__COMPONENT_FEATURES, ScdPackage.Literals.COMPONENT_FEATURES__SUPPORTS_INTERFACE };
}

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
import mil.jpeojtrs.sca.partitioning.DomainFinderType;
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
	 * Determine whether or not two ports are a suggested match and return a boolean to reflect such
	 * 
	 * @param source The source must be a UsesPortStub
	 * @param target The target can be either a ProvidesPortStub or ComponentSupportedInterfaceStub
	 * @return Return a boolean that shows whether or not the ports are compatible
	 * @since 6.2
	 */
	public static boolean areSuggestedMatch(final EObject source, final EObject target) {
		
		if (!areCompatible(source, target)) {
			//They're types aren't compatible return false
			return false;
		} else if (source instanceof UsesPortStub && target instanceof ProvidesPortStub) {
			final UsesPortStub usesStub = (UsesPortStub) source;
			final ProvidesPortStub providesStub = (ProvidesPortStub) target;
						
			if (source.eContainer() instanceof UsesDeviceStub || target.eContainer() instanceof UsesDeviceStub) {
				//connection involves UsesDeviceStub
				//perform name comparison, uses port name should exist within provides port name   (ex. "dataFloat_out".contains.("dataFloat")
				return usesStub.getName().contains(providesStub.getName());
			}
		}

		return true;
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
		}

		if (isComponentToComponent(source, target)) {
			return areComponentToComponentCompatible(source, target);
		} else if (isComponentToUsesDeviceComponentSupportedInterfaceStub(source, target)) {
			return areComponentToUsesDeviceComponentSupportedInterfaceStubCompatible(source, target);
		} else if (isComponentToFindByComponentSupportedInterfaceStub(source, target)) {
			return areComponentToFindByComponentSupportedInterfaceStubCompatible(source, target);
		}

		return true;
	}
	
	
	//cases where we perform compatibility checks
	
	/**
	 * Return true if using component to component connection points
	 * @param source
	 * @param target
	 * @return
	 * @since 6.2
	 */
	public static boolean isComponentToComponent(final EObject source, final EObject target) {
		if (source.eContainer() instanceof ComponentInstantiation && target.eContainer() instanceof ComponentInstantiation) {
			return true;
		}
		return false;
	}
	
	/**
	 * Return true if component to component connection points are compatible
	 * @param source
	 * @param target
	 * @return
	 * @since 6.2
	 */
	public static boolean areComponentToComponentCompatible(final EObject source, final EObject target) {
		final UsesPortStub usesStub = (UsesPortStub) source;
		if (target instanceof ProvidesPortStub) {
			final ProvidesPortStub providesStub = (ProvidesPortStub) target;
			
			if (source.eContainer() instanceof ComponentInstantiation && providesStub.getProvides() != null && usesStub.getUses() != null) {
				//connection between components provides and uses stubs
				if (providesStub.getProvides().getInterface() != null && usesStub.getUses().getInterface() != null) {
					return providesStub.getProvides().getInterface().isInstance(usesStub.getUses().getInterface());
				}
			} else if (target instanceof ComponentSupportedInterfaceStub && usesStub.getUses() != null) {
				final ComponentSupportedInterfaceStub compStub = (ComponentSupportedInterfaceStub) target;

				//target contains is Component
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
		
		return true;
	}
	
	/**
	 * Returns true if component is connected to uses device's component supported interface stub
	 * @since 6.2
	 */
	public static boolean isComponentToUsesDeviceComponentSupportedInterfaceStub(final EObject source, final EObject target) {
		if (source.eContainer() instanceof ComponentInstantiation && target.eContainer() instanceof UsesDeviceStub
				&& target instanceof ComponentSupportedInterfaceStub) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Return true if component to uses device's component supported interface stub connection points are compatible
	 * @param source
	 * @param target
	 * @return
	 * @since 6.2
	 */
	public static boolean areComponentToUsesDeviceComponentSupportedInterfaceStubCompatible(final EObject source, final EObject target) {
		if (!(source instanceof UsesPortStub) || ((UsesPortStub) source).getUses() == null || ((UsesPortStub) source).getUses().getInterface() == null) {
			//something wrong with usesport stub
			return false;
		}
		
		if (!"IDL:CF/ExecutableDevice:1.0".equals(((UsesPortStub) source).getUses().getInterface().getRepid())) {
			return false;
		}
		
		return true;
	}
	
	
	/**
	 * Return true if component is connected to FindBy's component supported interface stub
	 * @since 6.2
	 */
	public static boolean isComponentToFindByComponentSupportedInterfaceStub(final EObject source, final EObject target) {
		if (source.eContainer() instanceof ComponentInstantiation && target.eContainer() instanceof FindByStub
				&& target instanceof ComponentSupportedInterfaceStub) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Return true if component to findby connection points are compatible
	 * @param source
	 * @param target
	 * @return
	 * @since 6.2
	 */
	public static boolean areComponentToFindByComponentSupportedInterfaceStubCompatible(final EObject source, final EObject target) {
		if (!(source instanceof UsesPortStub) || ((UsesPortStub) source).getUses() == null || ((UsesPortStub) source).getUses().getInterface() == null) {
			//something wrong with usesport stub
			return false;
		}
		
		FindByStub findByStub = (FindByStub) target.eContainer();
		
		if (FindByStubUtil.isFindByStubDomainManager(findByStub) 
				&& !"IDL:CF/DomainManager:1.0".equals(((UsesPortStub) source).getUses().getInterface().getRepid())) {
			return false;
		} else if (FindByStubUtil.isFindByStubFileManager(findByStub) 
				&& !"IDL:CF/FileManager:1.0".equals(((UsesPortStub) source).getUses().getInterface().getRepid())) {
			return false;
		} else if (FindByStubUtil.isFindByStubEventChannel(findByStub) 
				&& !"IDL:omg.org/CosEventChannelAdmin/EventChannel:1.0".equals(((UsesPortStub) source).getUses().getInterface().getRepid())) {
			return false;
		} else if (FindByStubUtil.isFindByStubService(findByStub)) {
			if (findByStub.getDomainFinder().getType().equals(DomainFinderType.SERVICETYPE)) {
				// using service type
				if (!findByStub.getDomainFinder().getName().equals(((UsesPortStub) source).getUses().getInterface().getRepid())) {
					// type name and uses port id don't match
					return false;
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

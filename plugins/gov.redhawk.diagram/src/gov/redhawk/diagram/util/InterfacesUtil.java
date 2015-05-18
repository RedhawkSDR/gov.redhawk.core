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

import gov.redhawk.diagram.activator.PluginActivator;
import gov.redhawk.eclipsecorba.idl.Identifiable;
import gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl;
import gov.redhawk.eclipsecorba.library.IdlLibrary;
import gov.redhawk.ui.RedhawkUiActivator;
import gov.redhawk.ui.editor.IIdlLibraryService;

import java.util.List;

import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;
import mil.jpeojtrs.sca.partitioning.ComponentSupportedInterfaceStub;
import mil.jpeojtrs.sca.partitioning.DomainFinderType;
import mil.jpeojtrs.sca.partitioning.FindByStub;
import mil.jpeojtrs.sca.partitioning.PartitioningPackage;
import mil.jpeojtrs.sca.partitioning.ProvidesPortStub;
import mil.jpeojtrs.sca.partitioning.UsesDeviceStub;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;
import mil.jpeojtrs.sca.scd.Interface;
import mil.jpeojtrs.sca.scd.ScdPackage;
import mil.jpeojtrs.sca.scd.SupportsInterface;
import mil.jpeojtrs.sca.spd.SpdPackage;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.ui.statushandlers.StatusManager;

/**
 * Utilities for determining if a uses port is compatible (or even a suggested match) for a provides port / component
 * supported interface.
 */
public class InterfacesUtil {

	private InterfacesUtil() {
	}

	/**
	 * Determine whether or not uses port is a suggested match for a provides port or component supported interface.
	 * @param source A {@link UsesPortStub}
	 * @param target A {@link ProvidesPortStub} or {@link ComponentSupportedInterfaceStub}
	 * @return Return a boolean that shows whether or not the ports are compatible
	 * @since 7.0
	 */
	public static boolean areSuggestedMatch(final UsesPortStub source, final EObject target) {
		// They must be compatible ports are we won't suggest a match
		if (!areCompatible(source, target)) {
			return false;
		}

		// If we're matching port -> port, and either of the ports is on a uses device, use name matching to determine
		// if the ports are be a suggested match
		if (target instanceof ProvidesPortStub && (source.eContainer() instanceof UsesDeviceStub || target.eContainer() instanceof UsesDeviceStub)) {
			final ProvidesPortStub providesStub = (ProvidesPortStub) target;

			// Take the beginning of each name before any underscore
			String usesPortName = source.getName();
			int index = usesPortName.indexOf('_');
			if (index != -1) {
				usesPortName = usesPortName.substring(0, index);
			}

			String providesPortName = providesStub.getName();
			index = providesPortName.indexOf('_');
			if (index != -1) {
				providesPortName = providesPortName.substring(0, index);
			}

			// If the beginnings of the names are equal, we'll suggest this as a match
			return usesPortName.equals(providesPortName);
		}

		// We know the source and target are compatible, so suggest the match
		return true;
	}

	/**
	 * Determine whether or not a uses port is compatible to connect to a provides port or a component supported
	 * interface. The port/supported interface can belong to a component, usesdevice, or findby.
	 * @param source A {@link UsesPortStub}
	 * @param target A {@link ProvidesPortStub} or {@link ComponentSupportedInterfaceStub}
	 * @return True if the objects are compatible for connection
	 * @since 7.0
	 */
	public static boolean areCompatible(final UsesPortStub source, final EObject target) {
		if (source == null || target == null) {
			return false;
		}

		EObject sourceContainer = source.eContainer();
		if (sourceContainer instanceof ComponentInstantiation) {
			return areCompatibleComponentToTarget(source, target);
		} else if (sourceContainer instanceof UsesDeviceStub) {
			return areCompatibleUsesDeviceToTarget(source, target);
		} else if (sourceContainer instanceof FindByStub) {
			return areCompatibleFindByToTarget(source, target);
		} else {
			String sourceContainerType = (sourceContainer != null) ? sourceContainer.getClass().getCanonicalName() : "null";
			throw new IllegalArgumentException("Invalid source container type: " + sourceContainerType);
		}
	}

	/**
	 * Used by {@link #areCompatible(UsesPortStub, EObject)} to determine if a component's uses port is compatible
	 * with a target.
	 * @param source
	 * @param target
	 * @return
	 */
	private static boolean areCompatibleComponentToTarget(final UsesPortStub source, final EObject target) {
		EObject targetContainer = target.eContainer();
		if (target instanceof ProvidesPortStub) {
			if (targetContainer instanceof ComponentInstantiation) {
				return areComponentToComponentCompatible(source, target);
			} else if (targetContainer instanceof FindByStub) {
				return true;
			} else if (targetContainer instanceof UsesDeviceStub) {
				return true;
			} else {
				String targetContainerType = (targetContainer != null) ? targetContainer.getClass().getCanonicalName() : "null";
				throw new IllegalArgumentException("Invalid target container type: " + targetContainerType);
			}
		} else if (target instanceof ComponentSupportedInterfaceStub) {
			if (targetContainer instanceof ComponentInstantiation) {
				return areComponentToComponentCompatible(source, target);
			} else if (targetContainer instanceof FindByStub) {
				return areComponentToFindByComponentSupportedInterfaceStubCompatible(source, (ComponentSupportedInterfaceStub) target);
			} else if (targetContainer instanceof UsesDeviceStub) {
				return areComponentToUsesDeviceComponentSupportedInterfaceCompatible(source, (ComponentSupportedInterfaceStub) target);
			} else {
				String targetContainerType = (targetContainer != null) ? targetContainer.getClass().getCanonicalName() : "null";
				throw new IllegalArgumentException("Invalid target container type: " + targetContainerType);
			}
		} else {
			throw new IllegalArgumentException("Invalid target type: " + target.getClass().getCanonicalName());
		}
	}

	/**
	 * Used by {@link #areCompatible(UsesPortStub, EObject)} to determine if a uses device's uses port is compatible
	 * with a target.
	 * @param source
	 * @param target
	 * @return
	 */
	private static boolean areCompatibleUsesDeviceToTarget(final UsesPortStub source, final EObject target) {
		// For a uses port on a uses device, we have no type info for the port
		EObject targetContainer = target.eContainer();
		if (target instanceof ProvidesPortStub) {
			if (targetContainer instanceof ComponentInstantiation) {
				return true;
			} else if (targetContainer instanceof FindByStub) {
				// TODO: Is this true? usesdevice port -> findby port
				return true;
			} else if (targetContainer instanceof UsesDeviceStub) {
				return true;
			} else {
				String targetContainerType = (targetContainer != null) ? targetContainer.getClass().getCanonicalName() : "null";
				throw new IllegalArgumentException("Invalid target container type: " + targetContainerType);
			}
		} else if (target instanceof ComponentSupportedInterfaceStub) {
			if (targetContainer instanceof ComponentInstantiation) {
				return true;
			} else if (targetContainer instanceof FindByStub) {
				// uses_device:port -> find_by:comp_supported_intf
				// This doesn't make any sense,
				return true;
			} else if (targetContainer instanceof UsesDeviceStub) {
				// TODO: Is this true? usesdevice port -> usesdevice comp. supported intf.
				return true;
			} else {
				String targetContainerType = (targetContainer != null) ? targetContainer.getClass().getCanonicalName() : "null";
				throw new IllegalArgumentException("Invalid target container type: " + targetContainerType);
			}
		} else {
			throw new IllegalArgumentException("Invalid target type: " + target.getClass().getCanonicalName());
		}
	}

	/**
	 * Used by {@link #areCompatible(UsesPortStub, EObject)} to determine if a find by's uses port is compatible
	 * with a target.
	 * @param source
	 * @param target
	 * @return
	 */
	private static boolean areCompatibleFindByToTarget(final UsesPortStub source, final EObject target) {
		// For a uses port on a find by, we have no type info about the port
		EObject targetContainer = target.eContainer();
		if (target instanceof ProvidesPortStub) {
			// Allow port -> port connections since we can't check the type info
			if (targetContainer instanceof ComponentInstantiation) {
				return true;
			} else if (targetContainer instanceof FindByStub) {
				return true;
			} else if (targetContainer instanceof UsesDeviceStub) {
				return true;
			} else {
				String targetContainerType = (targetContainer != null) ? targetContainer.getClass().getCanonicalName() : "null";
				throw new IllegalArgumentException("Invalid target container type: " + targetContainerType);
			}
		} else if (target instanceof ComponentSupportedInterfaceStub) {
			if (targetContainer instanceof ComponentInstantiation) {
				// TODO: For now we don't support this
				return false;
			} else if (targetContainer instanceof FindByStub) {
				// TODO: For now we don't support this
				return false;
			} else if (targetContainer instanceof UsesDeviceStub) {
				// TODO: For now we don't support this
				return false;
			} else {
				String targetContainerType = (targetContainer != null) ? targetContainer.getClass().getCanonicalName() : "null";
				throw new IllegalArgumentException("Invalid target container type: " + targetContainerType);
			}
		} else {
			throw new IllegalArgumentException("Invalid target type: " + target.getClass().getCanonicalName());
		}
	}

	/**
	 * Determine whether a component's port can be connected to another component's port or component supported
	 * interface.
	 * @param source The {@link UsesPortStub}
	 * @param target A {@link ProvidesPortStub} or {@link ComponentSupportedInterfaceStub}
	 * @return True if the objects are compatible for connection
	 * @since 7.0
	 */
	public static boolean areComponentToComponentCompatible(final UsesPortStub source, final EObject target) {
		if (source.getUses() == null || source.getUses().getInterface() == null) {
			// Something is wrong with the uses port
			StatusManager.getManager().handle(
				new Status(IStatus.ERROR, PluginActivator.ID, "Uses port stub was missing reference to uses port or its interface"), StatusManager.LOG);
			return true;
		}

		if (!(source.eContainer() instanceof ComponentInstantiation)) {
			throw new IllegalArgumentException("Source container was not of type ComponentInstantiation");
		}

		if (target instanceof ProvidesPortStub) {
			final ProvidesPortStub providesStub = (ProvidesPortStub) target;

			if (providesStub.getProvides() != null) {
				// connection between components provides and uses stubs
				if (providesStub.getProvides().getInterface() != null && source.getUses().getInterface() != null) {
					return providesStub.getProvides().getInterface().isInstance(source.getUses().getInterface());
				} else {
					// If we can't get an interface for one side, fall back to allowing the connection
					return true;
				}
			} else {
				// Something is wrong with the provides
				return false;
			}
		} else if (target instanceof ComponentSupportedInterfaceStub) {
			Interface sourceInterface = source.getUses().getInterface();
			final List<SupportsInterface> targetSupportsInterfaces = ScaEcoreUtils.getFeature(target.eContainer(), InterfacesUtil.SUPPORT_PATH);

			if (targetSupportsInterfaces != null) {
				// Check if any of the target's supported interfaces match the source interface
				for (final SupportsInterface supportsInterface : targetSupportsInterfaces) {
					if (supportsInterface.getInterface() != null && supportsInterface.getInterface().isInstance(sourceInterface)) {
						return true;
					}
				}
				// No interface match
				return false;
			} else {
				// We have no interfaces for the target. Fallback to allowing the connection.
				return true;
			}
		} else {
			throw new IllegalArgumentException("Invalid target type");
		}
	}

	/**
	 * Determine if connecting the component's uses port to a usesdevice's component supported interface is compatible.
	 * @param source
	 * @param target
	 * @return
	 * @since 7.0
	 */
	public static boolean areComponentToUsesDeviceComponentSupportedInterfaceCompatible(final UsesPortStub source, final ComponentSupportedInterfaceStub target) {
		if (source.getUses() == null || source.getUses().getInterface() == null) {
			// Something is wrong with the uses port
			StatusManager.getManager().handle(
				new Status(IStatus.ERROR, PluginActivator.ID, "Uses port stub was missing reference to uses port or its interface"), StatusManager.LOG);
			return true;
		}

		// Assume the broadest possible definition of the usesdevice per REDHAWK (i.e. AggregateExecutableDevice)
		// If we can't find an IDL, we'll fall back to assuming the connection points are compatible
		String sourceIdl = source.getUses().getRepID();
		return idlInstaceOf(sourceIdl, "IDL:CF/AggregateExecutableDevice:1.0", true);
	}

	/**
	 * Determine if connecting the component's uses port to a findby's component supported interface is compatible.
	 * @param source
	 * @param target
	 * @return
	 * @since 7.0
	 */
	public static boolean areComponentToFindByComponentSupportedInterfaceStubCompatible(final UsesPortStub source, final ComponentSupportedInterfaceStub target) {
		if (source.getUses() == null || source.getUses().getInterface() == null) {
			// something wrong with usesport stub
			return false;
		}
		String sourceIdl = source.getUses().getInterface().getRepid();

		FindByStub findByStub = (FindByStub) target.eContainer();

		if (FindByStubUtil.isFindByStubDomainManager(findByStub)) {
			return idlInstaceOf(sourceIdl, "IDL:CF/DomainManager:1.0", true);
		}
		if (FindByStubUtil.isFindByStubFileManager(findByStub)) {
			return idlInstaceOf(sourceIdl, "IDL:CF/FileManager:1.0", true);
		}
		if (FindByStubUtil.isFindByStubEventChannel(findByStub)) {
			return idlInstaceOf(sourceIdl, "IDL:omg.org/CosEventChannelAdmin/EventChannel:1.0", true);
		}
		if (FindByStubUtil.isFindByStubService(findByStub)) {
			if (findByStub.getDomainFinder().getType().equals(DomainFinderType.SERVICETYPE)) {
				return idlInstaceOf(sourceIdl, findByStub.getDomainFinder().getName(), true);
			} else {
				// Assume any other type (such as by service name) is fine
				return true;
			}
		}

		// Any other findby we'll assume is compatible
		return true;
	}

	/**
	 * Determines if the source IDL is an instance of the target IDL. If the IDL cannot be found, returns the specified
	 * fallback.
	 * @param sourceIdl The source IDL
	 * @param targetIdl The target IDL
	 * @param fallback The fallback value if an IDL can't be looked up in the IDL library
	 * @return
	 */
	private static boolean idlInstaceOf(String sourceIdl, String targetIdl, boolean fallback) {
		// Get the IDL library
		IIdlLibraryService libraryService = RedhawkUiActivator.getDefault().getIdlLibraryService();
		if (libraryService == null) {
			return fallback;
		}
		IdlLibrary library = libraryService.getLibrary();
		if (library == null) {
			return fallback;
		}

		// Find the interface declarations for each IDL type
		Identifiable sourceIdent = library.find(sourceIdl);
		Identifiable targetIdent = library.find(targetIdl);
		if (!(sourceIdent instanceof IdlInterfaceDcl && targetIdent instanceof IdlInterfaceDcl)) {
			return fallback;
		} else {
			IdlInterfaceDcl sourceDcl = (IdlInterfaceDcl) sourceIdent;
			IdlInterfaceDcl targetDcl = (IdlInterfaceDcl) targetIdent;
			return targetDcl.isInstance(sourceDcl);
		}
	}

	private static final EStructuralFeature[] SUPPORT_PATH = new EStructuralFeature[] { PartitioningPackage.Literals.COMPONENT_INSTANTIATION__PLACEMENT,
		PartitioningPackage.Literals.COMPONENT_PLACEMENT__COMPONENT_FILE_REF, PartitioningPackage.Literals.COMPONENT_FILE_REF__FILE,
		PartitioningPackage.Literals.COMPONENT_FILE__SOFT_PKG, SpdPackage.Literals.SOFT_PKG__DESCRIPTOR, SpdPackage.Literals.DESCRIPTOR__COMPONENT,
		ScdPackage.Literals.SOFTWARE_COMPONENT__COMPONENT_FEATURES, ScdPackage.Literals.COMPONENT_FEATURES__SUPPORTS_INTERFACE };
}

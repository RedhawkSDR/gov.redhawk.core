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
package gov.redhawk.sca.sad.validation;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

import gov.redhawk.validation.DceUuidConstraint;
import mil.jpeojtrs.sca.partitioning.NamingService;
import mil.jpeojtrs.sca.partitioning.PartitioningPackage;
import mil.jpeojtrs.sca.sad.ComponentResourceFactoryRef;
import mil.jpeojtrs.sca.sad.FindComponent;
import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.sad.SadComponentPlacement;
import mil.jpeojtrs.sca.sad.SadPackage;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.validator.EnhancedConstraintStatus;

/**
 * @since 2.1
 */
public class FindComponentConstraint extends AbstractModelConstraint {

	public static final String SOURCE_ID = SadValidationConstants.SOURCE_ID;
	public static final int STATUS_CODE = 1001;

	private static final String NO_ELEMENTS = "FindComponent must have either a ComponentResourceFactoryRef or a NamingService element defined in Component: ";
	private static final String BOTH_ELEMENTS = "FindComponent can only have either a ComponentResourceFactoryRef or a NamingService element defined in Component: ";

	private static final String NULL_NAMING_SERVICE_NAME = "The Name attribute is not defined for the Naming Service element in Component: ";
	private static final String BLANK_NAMING_SERVICE_NAME = "The Name attribute is blank for the Naming Service element in Component: ";
	private static final String DUPLICATE_NAMING_SERVICE_NAME = "Duplicate Naming Service name being used by Components: ";

	@Override
	public IStatus validate(final IValidationContext ctx) {
		final EObject target = ctx.getTarget();

		if (target instanceof FindComponent) {
			final FindComponent findComp = (FindComponent) target;

			return FindComponentConstraint.validate(findComp, ctx);
		}

		return Status.OK_STATUS;
	}

	public static IStatus validate(final FindComponent findComp, final IValidationContext ctx) {
		IStatus validStatus = FindComponentConstraint.valid(findComp, ctx);

		if (validStatus != Status.OK_STATUS) {
			return validStatus;
		} else {
			validStatus = FindComponentConstraint.unique(findComp, ctx);
		}

		if (validStatus != Status.OK_STATUS) {
			return validStatus;
		}

		return Status.OK_STATUS;
	}

	private static IStatus valid(final FindComponent findComp, final IValidationContext ctx) {
		final SadComponentInstantiation parentInst = (SadComponentInstantiation) findComp.eContainer();

		if ((findComp.getComponentResourceFactoryRef() == null) && (findComp.getNamingService() == null)) {
			return new EnhancedConstraintStatus((ConstraintStatus) ctx.createFailureStatus(FindComponentConstraint.NO_ELEMENTS + parentInst.getId()),
				SadPackage.Literals.FIND_COMPONENT__NAMING_SERVICE);
		}

		if ((findComp.getComponentResourceFactoryRef() != null) && (findComp.getNamingService() != null)) {
			return new EnhancedConstraintStatus((ConstraintStatus) ctx.createFailureStatus(FindComponentConstraint.BOTH_ELEMENTS + parentInst.getId()),
				SadPackage.Literals.FIND_COMPONENT__NAMING_SERVICE);
		}

		if (findComp.getNamingService() != null) {
			final NamingService ns = findComp.getNamingService();
			final String nsName = ns.getName();

			if (nsName == null) {
				return new EnhancedConstraintStatus(
					(ConstraintStatus) ctx.createFailureStatus(FindComponentConstraint.NULL_NAMING_SERVICE_NAME + parentInst.getId()),
					PartitioningPackage.Literals.NAMING_SERVICE__NAME);
			}

			if (nsName.trim().length() == 0) {
				return new EnhancedConstraintStatus(
					(ConstraintStatus) ctx.createFailureStatus(FindComponentConstraint.BLANK_NAMING_SERVICE_NAME + parentInst.getId()),
					PartitioningPackage.Literals.NAMING_SERVICE__NAME);
			}

		} else {
			final ComponentResourceFactoryRef ref = findComp.getComponentResourceFactoryRef();
			final String refid = ref.getRefid();
			final IStatus status = DceUuidConstraint.validate(refid);

			if (status.getSeverity() == IStatus.ERROR) {
				return new EnhancedConstraintStatus((ConstraintStatus) ctx.createFailureStatus(status.getMessage() + " for Component: " + parentInst.getId()),
					SadPackage.Literals.COMPONENT_RESOURCE_FACTORY_REF__REFID);
			}
		}

		return Status.OK_STATUS;
	}

	private static IStatus unique(final FindComponent findComp, final IValidationContext ctx) {
		final SoftwareAssembly sad = SoftwareAssembly.Util.getSoftwareAssembly(findComp.eResource());
		final SadComponentInstantiation parentInst = (SadComponentInstantiation) findComp.eContainer();
		final NamingService ns = findComp.getNamingService();
		final String nsName = ns.getName();

		for (final SadComponentPlacement sadCp : sad.getPartitioning().getComponentPlacement()) {
			for (final SadComponentInstantiation sadInst : sadCp.getComponentInstantiation()) {
				FindComponent instFindComp = sadInst.getFindComponent();
				if (instFindComp == null) {
					continue;
				}
				if (!(sadInst.getId().equals(parentInst.getId())) && (FindComponentConstraint.valid(instFindComp, ctx) == Status.OK_STATUS)
					&& (instFindComp.getNamingService().getName().equals(nsName))) {
					return new EnhancedConstraintStatus(
						(ConstraintStatus) ctx.createFailureStatus(
							FindComponentConstraint.DUPLICATE_NAMING_SERVICE_NAME + parentInst.getId() + " and " + sadInst.getId()),
						PartitioningPackage.Literals.NAMING_SERVICE__NAME);
				}
			}
		}

		return Status.OK_STATUS;
	}

}

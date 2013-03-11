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
package gov.redhawk.sca.sad.validation;

import gov.redhawk.validation.util.ValidationInterfacesUtil;
import mil.jpeojtrs.sca.partitioning.ComponentSupportedInterface;
import mil.jpeojtrs.sca.partitioning.ComponentSupportedInterfaceStub;
import mil.jpeojtrs.sca.partitioning.FindByStub;
import mil.jpeojtrs.sca.partitioning.PartitioningPackage;
import mil.jpeojtrs.sca.partitioning.ProvidesPortStub;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;
import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.sad.SadConnectInterface;
import mil.jpeojtrs.sca.validator.EnhancedConstraintStatus;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

public class ConnectInterfaceConstraint extends AbstractModelConstraint {

	public static final String SOURCE_ID = SadValidationConstants.SOURCE_ID;
	public static final int STATUS_CODE = 1002;

	private static final String INCOMPATIBLE_ELEMENTS = "Incompatible connection between ";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IStatus validate(final IValidationContext ctx) {
		final EObject target = ctx.getTarget();

		if (target instanceof SadConnectInterface) {
			final SadConnectInterface sadConn = (SadConnectInterface) target;

			return ConnectInterfaceConstraint.validate(sadConn, ctx);
		}
		return Status.OK_STATUS;
	}

	/**
	 * Determine if a connection is valid by making sure the source and target are using compatible interfaces
	 * 
	 * @param conn The Sad Connect Interface instance
	 * @param ctx The validation context instance
	 * @return returns an IStatus that reflects whether or not the SadConnectInterface is valid
	 */
	public static IStatus validate(final SadConnectInterface conn, final IValidationContext ctx) {
		if (conn.getSource() != null && conn.getTarget() != null) {
			if (conn.getSource().eContainer() instanceof FindByStub || conn.getTarget().eContainer() instanceof FindByStub) {
				return Status.OK_STATUS;
			}

			if (conn.getSource() instanceof UsesPortStub) {
				final UsesPortStub uses = conn.getSource();
				if (conn.getTarget() instanceof ProvidesPortStub) {
					final ProvidesPortStub provides = (ProvidesPortStub) conn.getTarget();
					if (!ValidationInterfacesUtil.areCompatible(uses, provides)) {
						return new EnhancedConstraintStatus((ConstraintStatus) ctx.createFailureStatus(ConnectInterfaceConstraint.INCOMPATIBLE_ELEMENTS
						        + uses.getName() + " of " + ConnectInterfaceConstraint.getComponentName(uses) + " and " + provides.getName() + " of "
						        + ConnectInterfaceConstraint.getComponentName(provides)), PartitioningPackage.Literals.CONNECTIONS__CONNECT_INTERFACE);
					}
				} else if (conn.getTarget() != null && conn.getTarget() instanceof ComponentSupportedInterfaceStub) {
					final ComponentSupportedInterfaceStub comp = (ComponentSupportedInterfaceStub) conn.getTarget();
					if (!ValidationInterfacesUtil.areCompatible(uses, comp)) {
						return new EnhancedConstraintStatus((ConstraintStatus) ctx.createFailureStatus(ConnectInterfaceConstraint.INCOMPATIBLE_ELEMENTS
						        + uses.getName() + " of " + ConnectInterfaceConstraint.getComponentName(uses) + " and the resource port of "
						        + ConnectInterfaceConstraint.getComponentName(comp)), PartitioningPackage.Literals.CONNECTIONS__CONNECT_INTERFACE);
					}
				}
			}
		}
		return Status.OK_STATUS;
	}

	/**
	 * Return the component name from an object
	 * @param object the object needs to be either a UsesPortStub, ProvidesPortStub, ComponentSupportedInterface, or a ComponentSupportedInterfaceStub
	 * @return the component name, should it exist
	 */
	private static String getComponentName(final EObject object) {
		if (object instanceof UsesPortStub || object instanceof ProvidesPortStub || object instanceof ComponentSupportedInterface
		        || object instanceof ComponentSupportedInterfaceStub) {
			if (object.eContainer() != null && object.eContainer() instanceof SadComponentInstantiation) {
				final SadComponentInstantiation compInst = (SadComponentInstantiation) object.eContainer();

				if (compInst.getUsageName() != null) {
					return compInst.getUsageName();
				}
			}
		}

		return "";
	}
}

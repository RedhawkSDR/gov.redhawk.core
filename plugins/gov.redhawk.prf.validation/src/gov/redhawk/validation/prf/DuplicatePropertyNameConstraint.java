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
package gov.redhawk.validation.prf;

import mil.jpeojtrs.sca.prf.AbstractProperty;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.Properties;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.Struct;
import mil.jpeojtrs.sca.prf.StructSequence;
import mil.jpeojtrs.sca.validator.EnhancedConstraintStatus;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

public class DuplicatePropertyNameConstraint extends AbstractModelConstraint {

	public DuplicatePropertyNameConstraint() {
		// Default constructor
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IStatus validate(final IValidationContext ctx) {
		final AbstractProperty target = (AbstractProperty) ctx.getTarget();
		if (isTopLevelProp(target)) {
			String name = target.getName();
			if (isValidName(target, (Properties) target.eContainer())) {
				return ctx.createSuccessStatus();
			} else {
				return new EnhancedConstraintStatus((ConstraintStatus) ctx.createFailureStatus(name), PrfPackage.Literals.ABSTRACT_PROPERTY__NAME);
			}
		} else { //Not a toplevel prop validate within scope
			if (target instanceof Simple) {
				if (isValidLocalSimple((Simple) target)) {
					return ctx.createSuccessStatus();
				} else {
					return new EnhancedConstraintStatus((ConstraintStatus) ctx.createFailureStatus(target.getName()),
					        PrfPackage.Literals.ABSTRACT_PROPERTY__NAME);
				}
			} else if (target instanceof Struct) {
				if (isValidName(target, (Properties) target.eContainer().eContainer())) {
					return ctx.createSuccessStatus();
				} else {
					return new EnhancedConstraintStatus((ConstraintStatus) ctx.createFailureStatus(target.getName()),
					        PrfPackage.Literals.ABSTRACT_PROPERTY__NAME);
				}
			}
			return ctx.createSuccessStatus();
		}
	}

	/**
	 * Validates the local simple within scope.
	 * 
	 * @param mySimple the simple to validate.
	 * @return <code> true </code> if the simple has a valid name locally; <code> false </code> otherwise
	 */
	private boolean isValidLocalSimple(Simple mySimple) {
		//Local simple containers are always structs
		for (Simple simple : ((Struct) mySimple.eContainer()).getSimple()) {
			String name = simple.getName();
			if (name != null && name.equals(mySimple.getName()) && simple != mySimple) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Validates a property name based against other top level properties.
	 * 
	 * @param prop the property to compare against
	 * @param props the top level properties container
	 * @return <code> true </code> if there are no other top level props with the same name; <code> false </code> otherwise
	 */
	private boolean isValidName(AbstractProperty prop, Properties props) {
		String name;
		for (Simple simple : props.getSimple()) {
			name = simple.getName();
			if (name != null && name.equals(prop.getName()) && prop != simple) {
				return false;
			}
		}
		for (SimpleSequence simpleSequence : props.getSimpleSequence()) {
			name = simpleSequence.getName();
			if (name != null && name.equals(prop.getName()) && prop != simpleSequence) {
				return false;
			}
		}
		for (Struct struct : props.getStruct()) {
			name = struct.getName();
			if (name != null && name.equals(prop.getName()) && prop != struct) {
				return false;
			}
		}
		for (StructSequence structSequence : props.getStructSequence()) {
			name = structSequence.getName();
			if (name != null && name.equals(prop.getName()) && prop != structSequence) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Determines if the property is a top level property.
	 * 
	 * @param property the property to check
	 * @return <code> true </code> if this is a top level property; <code> false </code> otherwise
	 */
	private boolean isTopLevelProp(AbstractProperty property) {
		return property.eContainer() instanceof Properties;
	}
}

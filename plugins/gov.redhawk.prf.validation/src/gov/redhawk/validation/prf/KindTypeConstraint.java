/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.validation.prf;

import java.util.ArrayList;
import java.util.List;

import mil.jpeojtrs.sca.prf.ConfigurationKind;
import mil.jpeojtrs.sca.prf.Kind;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.PropertyConfigurationType;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.Struct;
import mil.jpeojtrs.sca.prf.StructSequence;
import mil.jpeojtrs.sca.validator.EnhancedConstraintStatus;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;

/**
 * @since 1.1
 * 
 */
public class KindTypeConstraint extends AbstractModelConstraint {

	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {
		final EObject target = ctx.getTarget();
		if (target instanceof Simple) {
			Simple simple = (Simple) target;
			return validateKindList(ctx, simple.getKind(), PrfPackage.Literals.SIMPLE__KIND);
		} else if (target instanceof SimpleSequence) {
			SimpleSequence ss = (SimpleSequence) target;
			return validateKindList(ctx, ss.getKind(), PrfPackage.Literals.SIMPLE_SEQUENCE__KIND);
		} else if (target instanceof Struct) {
			Struct struct = (Struct) target;
			return validateConfigurationKindList(ctx, struct.getConfigurationKind(), PrfPackage.Literals.STRUCT__CONFIGURATION_KIND);
		} else if (target instanceof StructSequence) {
			StructSequence ss = (StructSequence) target;
			return validateConfigurationKindList(ctx, ss.getConfigurationKind(), PrfPackage.Literals.STRUCT_SEQUENCE__CONFIGURATION_KIND);
		}
		return null;
	}

	private IStatus validatePropertyConfigurationTypeList(IValidationContext ctx, List<PropertyConfigurationType> list, EStructuralFeature feature) {
		if (list.contains(PropertyConfigurationType.MESSAGE)) {
			if (list.size() > 1) {
				return new EnhancedConstraintStatus(
					(ConstraintStatus) ctx.createFailureStatus("Property type 'MESSAGE' is a unique types, can be no other type."), feature);
			}
		}

		if (list.contains(PropertyConfigurationType.EVENT)) {
			if (!list.contains(PropertyConfigurationType.CONFIGURE) && !list.contains(PropertyConfigurationType.EXECPARAM)) {
				return new EnhancedConstraintStatus(
					(ConstraintStatus) ctx.createFailureStatus("Property type 'EVENT' can be added to a 'CONFIGURE' or an 'EXEC_PARAM' but not be by itself."),
					feature);
			}
		}

		return null;
	}

	private IStatus validateConfigurationKindList(IValidationContext ctx, EList<ConfigurationKind> configurationKind, EStructuralFeature feature) {
		List<PropertyConfigurationType> list = new ArrayList<PropertyConfigurationType>(configurationKind.size());
		for (ConfigurationKind k : configurationKind) {
			list.add(k.getType().getPropertyConfigurationType());
		}
		return validatePropertyConfigurationTypeList(ctx, list, feature);
	}

	private IStatus validateKindList(IValidationContext ctx, EList<Kind> kindList, EStructuralFeature feature) {
		List<PropertyConfigurationType> list = new ArrayList<PropertyConfigurationType>(kindList.size());
		for (Kind k : kindList) {
			list.add(k.getType());
		}
		return validatePropertyConfigurationTypeList(ctx, list, feature);
	}

}

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
package gov.redhawk.spd.internal.validation;

import mil.jpeojtrs.sca.spd.Implementation;
import mil.jpeojtrs.sca.spd.SoftPkg;
import mil.jpeojtrs.sca.validator.EnhancedConstraintStatus;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.ConstraintStatus;


public class DependencyConstraint extends AbstractModelConstraint {
	
	public static final String ID = "gov.redhawk.validation.constraint.spd.dependency";

	@Override
	public IStatus validate(final IValidationContext ctx) {
		final EObject target = ctx.getTarget();
		if (target instanceof Implementation) {
			final Implementation impl = (Implementation) target;
			if (impl.getOs().isEmpty() && impl.getProcessor().isEmpty() && impl.getDependency().isEmpty()) {
				String retVal = null;
				if (impl.eContainer() instanceof SoftPkg) {
					// TODO Suuport Optional dependency on codegen
					// try {
					// final gov.redhawk.ide.codegen.WaveDevSettings
					// waveDevSettings =
					// gov.redhawk.ide.codegen.CodegenUtil.loadWaveDevSettings((SoftPkg)
					// impl.eContainer());
					// if (waveDevSettings != null) {
					// final EMap<String,
					// gov.redhawk.ide.codegen.ImplementationSettings> map =
					// waveDevSettings.getImplSettings();
					// if (map != null) {
					// retVal = map.get(impl.getId()).getName();
					// }
					// }
					// } catch (final Exception e) {
					// // Do Nothing this is to catch the ClassDef Exception
					// // for CodeGen Settings
					// }
					if (retVal == null) {
						retVal = impl.getId();
					}
				}
				return new EnhancedConstraintStatus((ConstraintStatus) ctx.createFailureStatus(retVal), null);
			}
		}
		return ctx.createSuccessStatus();
	}
}

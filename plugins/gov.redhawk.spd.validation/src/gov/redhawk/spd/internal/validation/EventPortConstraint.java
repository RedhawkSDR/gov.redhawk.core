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

import mil.jpeojtrs.sca.prf.AbstractProperty;
import mil.jpeojtrs.sca.prf.Properties;
import mil.jpeojtrs.sca.prf.PropertyConfigurationType;
import mil.jpeojtrs.sca.scd.Ports;
import mil.jpeojtrs.sca.scd.ScdPackage;
import mil.jpeojtrs.sca.scd.Uses;
import mil.jpeojtrs.sca.spd.SoftPkg;
import mil.jpeojtrs.sca.spd.SpdPackage;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;
import org.omg.CosEventChannelAdmin.EventChannelHelper;

/**
 * 
 */
public class EventPortConstraint extends AbstractModelConstraint {
	public static final String SOURCE_ID = "gov.redhawk.spd.validation";
	/**
	 * @since 1.1
	 */
	public static final String ID = "event_constraint";

	public static final int STATUS_CODE = 1002;

	private static final EStructuralFeature[] PORTS_PATH = new EStructuralFeature[] {
	        SpdPackage.Literals.SOFT_PKG__DESCRIPTOR,
	        SpdPackage.Literals.DESCRIPTOR__COMPONENT,
	        ScdPackage.Literals.SOFTWARE_COMPONENT__COMPONENT_FEATURES,
	        ScdPackage.Literals.COMPONENT_FEATURES__PORTS
	};

	@Override
	public IStatus validate(final IValidationContext ctx) {
		final EObject target = ctx.getTarget();
		if (target instanceof SoftPkg) {
			final SoftPkg spd = (SoftPkg) target;
			if (spd.getPropertyFile() != null) {
				final Properties props = spd.getPropertyFile().getProperties();
				if (props != null) {
					for (final FeatureMap.Entry entry : props.getProperties()) {
						if (entry.getValue() instanceof AbstractProperty) {
							final AbstractProperty prop = (AbstractProperty) entry.getValue();
							if (prop.isKind(PropertyConfigurationType.EVENT)) {
								boolean valid = false;
								final Ports ports = ScaEcoreUtils.getFeature(spd, EventPortConstraint.PORTS_PATH);
								if (ports != null) {
									for (final Uses uses : ports.getUses()) {
										if (EventChannelHelper.id().equals(uses.getRepID()) && Uses.PORT_NAME_PROP_EVENTS.equals(uses.getName())) {
											valid = true;
											break;
										}
									}
								}
								if (!valid) {
									return ctx.createFailureStatus();
								} else {
									break;
								}
							}
						}
					}
				}
			}
		}
		return ctx.createSuccessStatus();
	}
}

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
package gov.redhawk.sca.sad.validation;

import mil.jpeojtrs.sca.partitioning.PartitioningPackage;
import mil.jpeojtrs.sca.prf.AbstractProperty;
import mil.jpeojtrs.sca.prf.Properties;
import mil.jpeojtrs.sca.sad.AssemblyController;
import mil.jpeojtrs.sca.sad.ExternalProperty;
import mil.jpeojtrs.sca.sad.SadComponentInstantiationRef;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.spd.SpdPackage;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap.ValueListIterator;
import org.eclipse.emf.validation.AbstractModelConstraint;
import org.eclipse.emf.validation.IValidationContext;

/**
 * @since 2.0
 */
public class DuplicateAssemblyExternalPropertyIDConstraint extends AbstractModelConstraint {

	/**
	 * 
	 */
	public DuplicateAssemblyExternalPropertyIDConstraint() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.validation.AbstractModelConstraint#validate(org.eclipse.emf.validation.IValidationContext)
	 */
	@Override
	public IStatus validate(IValidationContext ctx) {
		final EObject target = ctx.getTarget();

		if (target instanceof ExternalProperty) {
			return validateProperty((ExternalProperty) target, ctx);
		}
		return ctx.createSuccessStatus();
	}

	public static boolean validateProperty(ExternalProperty prop, SoftwareAssembly sad) {
		String id = prop.resolveExternalID();
		if (id == null) {
			return true;
		}
		AssemblyController asm = sad.getAssemblyController();
		if (asm != null) {
			SadComponentInstantiationRef ref = asm.getComponentInstantiationRef();
			if (ref != null) {
				if (!ref.getRefid().equals(prop.getCompRefID())) {
					final EStructuralFeature[] PROPERTIES_PATH = new EStructuralFeature[] { PartitioningPackage.Literals.COMPONENT_INSTANTIATION_REF__INSTANTIATION , 
						PartitioningPackage.Literals.COMPONENT_INSTANTIATION__PLACEMENT, PartitioningPackage.Literals.COMPONENT_PLACEMENT__COMPONENT_FILE_REF, 
						PartitioningPackage.Literals.COMPONENT_FILE_REF__FILE, PartitioningPackage.Literals.COMPONENT_FILE__SOFT_PKG, SpdPackage.Literals.SOFT_PKG__PROPERTY_FILE,
						SpdPackage.Literals.PROPERTY_FILE__PROPERTIES };
					Properties asmProps = ScaEcoreUtils.getFeature(ref, PROPERTIES_PATH);
					if (asmProps == null) {
						return true;
					}
					for (ValueListIterator<Object> i = asmProps.getProperties().valueListIterator(); i.hasNext();) {
						Object obj = i.next();
						if (obj instanceof AbstractProperty) {
							AbstractProperty p = (AbstractProperty) obj;
							if (id.equals(p.getId())) {
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}

	private IStatus validateProperty(ExternalProperty prop, IValidationContext ctx) {
		SoftwareAssembly sad = ScaEcoreUtils.getEContainerOfType(prop, SoftwareAssembly.class);
		if (!validateProperty(prop, sad)) {
			String id = prop.resolveExternalID();
			return ctx.createFailureStatus(id);
		}

		return ctx.createSuccessStatus();
	}

}
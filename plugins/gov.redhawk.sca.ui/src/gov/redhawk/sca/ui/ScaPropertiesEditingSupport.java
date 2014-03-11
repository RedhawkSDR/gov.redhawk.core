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
package gov.redhawk.sca.ui;

import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaSimpleSequenceProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.ScaStructSequenceProperty;
import gov.redhawk.sca.ui.properties.AbstractPropertyEditingSupport;

import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.ui.views.properties.IPropertySourceProvider;

/**
 * @since 9.3
 * 
 */
public class ScaPropertiesEditingSupport extends AbstractPropertyEditingSupport {

	public ScaPropertiesEditingSupport(ColumnViewer viewer, IPropertySourceProvider propertySourceProvider) {
		super(viewer, propertySourceProvider);
	}

	@Override
	protected Object getPropertyID(final Object object) {
		if (object instanceof ScaSimpleProperty) {
			return ScaPackage.Literals.SCA_SIMPLE_PROPERTY__VALUE.getName();
		} else if (object instanceof ScaSimpleSequenceProperty) {
			return ScaPackage.Literals.SCA_SIMPLE_SEQUENCE_PROPERTY__VALUES.getName();
		} else if (object instanceof ScaStructProperty) {
			return ScaPackage.Literals.SCA_STRUCT_PROPERTY__SIMPLES.getName();
		} else if (object instanceof ScaStructSequenceProperty) {
			return ScaPackage.Literals.SCA_STRUCT_SEQUENCE_PROPERTY__STRUCTS.getName();
		}
		return null;
	}

}

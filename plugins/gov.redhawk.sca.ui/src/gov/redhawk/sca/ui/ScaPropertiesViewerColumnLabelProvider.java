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

import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaSimpleSequenceProperty;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.TreeColumnViewerLabelProvider;

/**
 * @since 9.3
 */
public class ScaPropertiesViewerColumnLabelProvider extends TreeColumnViewerLabelProvider {
	public ScaPropertiesViewerColumnLabelProvider(IBaseLabelProvider labelProvider) {
		super(labelProvider);
	}

	@Override
	public String getToolTipText(final Object element) {
		if (!(element instanceof ScaAbstractProperty< ? >)) {
			return null;
		}
		final ScaAbstractProperty< ? > prop = (ScaAbstractProperty< ? >) element;
		if (prop.getDefinition() == null) {
			return null;
		}
		String retVal = prop.getDescription();
		if (prop instanceof ScaSimpleProperty) {
			ScaSimpleProperty simple = ((ScaSimpleProperty) prop);
			final String typeString;
			if (simple.getDefinition().getEnumerations() != null) {
				typeString = prop.getId() + " enum < " + simple.getDefinition().getType().getLiteral() + " >";
			} else {
				typeString = prop.getId() + " < " + simple.getDefinition().getType().getLiteral() + " >";
			}
			if (retVal == null) {
				retVal = typeString;
			} else {
				retVal = typeString + "\n" + retVal;
			}
		} else if (prop instanceof ScaSimpleSequenceProperty) {
			ScaSimpleSequenceProperty sequence = ((ScaSimpleSequenceProperty) prop);
			final String typeString = prop.getId() + " < " + sequence.getDefinition().getType().getLiteral() + " >";
			if (retVal == null) {
				retVal = typeString;
			} else {
				retVal = typeString + "\n" + retVal;
			}
		} else {
			String idString = prop.getId();
			if (retVal == null) {
				retVal = idString;
			} else {
				retVal = idString + "\n" + retVal;
			}
		}
		return retVal;
	}

	@Override
	public int getToolTipDisplayDelayTime(final Object object) {
		return 100;
	}

	@Override
	public int getToolTipTimeDisplayed(final Object object) {
		return 5000;
	}
}

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

import org.apache.commons.lang.WordUtils;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.TreeColumnViewerLabelProvider;

/**
 * @since 9.3
 */
public class ScaPropertiesViewerColumnLabelProvider extends TreeColumnViewerLabelProvider {

	/**
	 * How many characters wide to allow a line to be in a tooltip before line wrapping it.
	 */
	private static final int TOOLTIP_WRAP_LEN = 80;

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

		StringBuilder sb = new StringBuilder();
		if (prop instanceof ScaSimpleProperty) {
			ScaSimpleProperty simple = ((ScaSimpleProperty) prop);
			sb.append(prop.getId());
			if (simple.getDefinition().getEnumerations() != null) {
				sb.append(" enum");
			}
			sb.append(" < ");
			sb.append(simple.getDefinition().getType().getLiteral());
			sb.append(" >");
		} else if (prop instanceof ScaSimpleSequenceProperty) {
			ScaSimpleSequenceProperty sequence = ((ScaSimpleSequenceProperty) prop);
			sb.append(prop.getId());
			sb.append(" < ");
			sb.append(sequence.getDefinition().getType().getLiteral());
			sb.append(" >");
		} else {
			sb.append(prop.getId());
		}

		String description = prop.getDescription();
		if (description != null) {
			if (sb.length() > 0) {
				sb.append("\n");
			}
			for (String line : description.split("\n")) {
				sb.append(WordUtils.wrap(line, TOOLTIP_WRAP_LEN, null, true));
				sb.append("\n");
			}
		}

		return sb.toString();
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

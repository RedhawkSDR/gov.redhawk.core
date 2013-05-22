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
package gov.redhawk.sca.internal.ui;

import gov.redhawk.model.sca.ScaAbstractProperty;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;

/**
 * @since 5.0
 * 
 */
public class ScaPropertyNameColumnLabelProvider extends CellLabelProvider {

	@Override
	public int getToolTipTimeDisplayed(final Object element) {
		boolean display = false;
		if (element instanceof ScaAbstractProperty< ? >) {
			display = true;
		}
		if (display) {
			return -1;
		}
		return super.getToolTipTimeDisplayed(element);
	}

	@Override
	public String getToolTipText(final Object obj) {
		if (obj instanceof ScaAbstractProperty< ? >) {
			final ScaAbstractProperty< ? > property = (ScaAbstractProperty< ? >) obj;
			return wrap(property.getDescription());
		}
		return super.getToolTipText(obj);
	}

	/**
	 * @param description
	 * @return
	 */
	private String wrap(final String description) {
		final StringBuilder builder = new StringBuilder();
		final int length = description.length();
		final int lineLength = 75;
		for (int position = 0; position < length;) {
			final int nextPosition = position + lineLength;
			int end = Math.min(nextPosition, length);
			final int endWhitespace = description.indexOf(" ", end);
			if (endWhitespace != -1) {
				end = endWhitespace;
			}
			builder.append(description.substring(position, end));
			position = end + 1;
			if (end < length) {
				builder.append('\n');
			}
		}
		return builder.toString();
	}

	@Override
	public int getToolTipDisplayDelayTime(final Object element) {
		boolean display = false;
		if (element instanceof ScaAbstractProperty< ? >) {
			display = true;
		}
		if (display) {
			return 100; //SUPPRESS CHECKSTYLE MagicNumber
		}
		return super.getToolTipDisplayDelayTime(element);
	}

	@Override
	public void update(final ViewerCell cell) {
		final Object obj = cell.getElement();
		if (obj instanceof ScaAbstractProperty< ? >) {
			final ScaAbstractProperty< ? > property = (ScaAbstractProperty< ? >) obj;
			final String name = property.getName();
			if (name != null) {
				cell.setText(property.getName());
			} else {
				cell.setText(property.getId());
			}
		}
	}

}

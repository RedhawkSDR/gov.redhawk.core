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
package gov.redhawk.sca.internal.ui.wizards;

import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleSequence;

import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ViewerCell;

/**
 * @since 2.0
 * 
 */
public class NameColumnLabelProvider extends CellLabelProvider {

	private static final int DEFAULT_TOOLTIP_DELAY = 100;

	@Override
	public int getToolTipTimeDisplayed(final Object element) {
		boolean display = false;
		if (element instanceof Simple) {
			display = true;
		} else if (element instanceof SimpleSequence) {
			display = true;
		}
		if (display) {
			return -1;
		}
		return super.getToolTipTimeDisplayed(element);
	}

	@Override
	public String getToolTipText(final Object element) {
		if (element instanceof Simple) {
			final Simple ref = (Simple) element;
			if (ref.getDescription() != null) {
				return wrap(ref.getDescription());
			}

		} else if (element instanceof SimpleSequence) {
			final SimpleSequence ref = (SimpleSequence) element;
			if (ref.getDescription() != null) {
				return wrap(ref.getDescription());
			}
		}
		return super.getToolTipText(element);
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
		if (element instanceof Simple) {
			display = true;
		} else if (element instanceof SimpleSequence) {
			display = true;
		}
		if (display) {
			return NameColumnLabelProvider.DEFAULT_TOOLTIP_DELAY;
		}
		return super.getToolTipDisplayDelayTime(element);
	}

	@Override
	public void update(final ViewerCell cell) {
		final Object element = cell.getElement();
		if (element instanceof Simple) {
			final Simple ref = (Simple) element;
			cell.setText(ref.getName());
		} else if (element instanceof SimpleSequence) {
			final SimpleSequence ref = (SimpleSequence) element;
			cell.setText(ref.getName());
		}

	}

}

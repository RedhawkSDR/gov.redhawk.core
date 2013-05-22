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
package gov.redhawk.ui.actions;

import gov.redhawk.internal.ui.ScaPluginImages;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.ViewerComparator;

/**
 * The Class SortAction.
 */
public class SortAction extends Action {

	private boolean fSorted;

	private final StructuredViewer fViewer;

	private ViewerComparator fComparator;

	private final ViewerComparator fDefaultComparator;

	/**
	 * The Constructor.
	 * 
	 * @param viewer the viewer
	 * @param tooltipText the tooltip text
	 * @param sorter the sorter
	 * @param defaultSorter the default sorter
	 * @param listener the listener
	 */
	public SortAction(final StructuredViewer viewer, final String tooltipText, final ViewerComparator sorter,
	        final ViewerComparator defaultSorter, final IPropertyChangeListener listener) {

		super(tooltipText, IAction.AS_CHECK_BOX);
		// Set the tooltip
		setToolTipText(tooltipText);
		// Set the image
		setImageDescriptor(ScaPluginImages.DESC_ALPHAB_SORT_CO);
		// Set the default comparator
		this.fDefaultComparator = defaultSorter;
		// Set the viewer
		this.fViewer = viewer;
		// Set the comparator
		// If one was not specified, use the default
		if (sorter == null) {
			this.fComparator = new ViewerComparator();
		} else {
			this.fComparator = sorter;
		}
		// Determine if the viewer is already sorted
		// Note: Most likely the default comparator is null
		if (viewer.getComparator() == this.fDefaultComparator) {
			this.fSorted = false;
		} else {
			this.fSorted = true;
		}
		// Set the status of this action depending on whether it is sorted or
		// not
		setChecked(this.fSorted);
		// If a listener was specified, use it
		if (listener != null) {
			addListenerObject(listener);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#run()
	 */
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		// Toggle sorting on/off
		if (this.fSorted) {
			// Sorting is on
			// Turn it off
			this.fViewer.setComparator(this.fDefaultComparator);
			this.fSorted = false;
		} else {
			// Sorting is off
			// Turn it on
			this.fViewer.setComparator(this.fComparator);
			this.fSorted = true;
		}
		notifyResult(true);
	}

}

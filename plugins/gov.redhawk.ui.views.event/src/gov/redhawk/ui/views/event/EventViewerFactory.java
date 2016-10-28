package gov.redhawk.ui.views.event;
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


import org.eclipse.nebula.widgets.xviewer.XViewer;
import org.eclipse.nebula.widgets.xviewer.XViewerFactory;
import org.eclipse.nebula.widgets.xviewer.XViewerSorter;
import org.eclipse.nebula.widgets.xviewer.core.model.CustomizeData;
import org.eclipse.nebula.widgets.xviewer.core.model.SortDataType;
import org.eclipse.nebula.widgets.xviewer.core.model.XViewerAlign;
import org.eclipse.nebula.widgets.xviewer.core.model.XViewerColumn;
import org.eclipse.nebula.widgets.xviewer.customize.IXViewerCustomizations;

public class EventViewerFactory extends XViewerFactory {

	public static final String NAMESPACE = EventView.ID + ".viewer";

	static final XViewerColumn TIME_COL_MS = new XViewerColumn(EventViewerFactory.NAMESPACE + ".time_ms", "Time (HH:MM::ms)", 140, XViewerAlign.Left, false,
		SortDataType.Date, false, "Timestamp of the event");
	static final XViewerColumn TIME_COL_SS = new XViewerColumn(EventViewerFactory.NAMESPACE + ".time_ss", "Time (HH:MM::ss)", 140, XViewerAlign.Left, true,
		SortDataType.Date, false, "Timestamp of the event");
	static final XViewerColumn TYPE_COL = new XViewerColumn(EventViewerFactory.NAMESPACE + ".type", "Type", 200, XViewerAlign.Left, true, SortDataType.String,
		false, "Type of event");
	static final XViewerColumn SUMMARY = new XViewerColumn(EventViewerFactory.NAMESPACE + ".summary", "Summary", 360, XViewerAlign.Left, true,
		SortDataType.String, false, "Event");

	/**
	 * @param namespace
	 */
	public EventViewerFactory() {
		super(EventViewerFactory.NAMESPACE);
		registerColumns(EventViewerFactory.TIME_COL_SS, EventViewerFactory.TIME_COL_MS, EventViewerFactory.TYPE_COL, EventViewerFactory.SUMMARY);

	}

	/* (non-Javadoc)
	 * @see org.eclipse.nebula.widgets.xviewer.IXViewerFactory#isAdmin()
	 */
	@Override
	public boolean isAdmin() {
		return false;
	}

	@Override
	public XViewerSorter createNewXSorter(XViewer xViewer) {
		return new XViewerSorter(xViewer) {

		};
	}

	@Override
	public IXViewerCustomizations getXViewerCustomizations() {
		return super.getXViewerCustomizations();
	}

	@Override
	public boolean isSearhTop() {
		return false;
	}

	@Override
	public XViewerColumn getDefaultXViewerColumn(String id) {
		return EventViewerFactory.TIME_COL_SS;
	}

	@Override
	public CustomizeData getDefaultTableCustomizeData() {
		CustomizeData retVal = super.getDefaultTableCustomizeData();
		XViewerColumn col = retVal.getColumnData().getXColumn(EventViewerFactory.TIME_COL_SS.getId());
		col.setSortForward(false);
		retVal.getSortingData().addSortingName(EventViewerFactory.TIME_COL_SS.getId());
		return retVal;
	}

}

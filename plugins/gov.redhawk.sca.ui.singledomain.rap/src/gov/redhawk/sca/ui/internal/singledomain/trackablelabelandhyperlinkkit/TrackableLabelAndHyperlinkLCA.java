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

package gov.redhawk.sca.ui.internal.singledomain.trackablelabelandhyperlinkkit;

import gov.redhawk.sca.ui.singledomain.TrackableLabel;
import gov.redhawk.sca.ui.singledomain.TrackableLabelAndHyperlink;

import java.io.IOException;

import org.eclipse.rwt.lifecycle.AbstractWidgetLCA;
import org.eclipse.rwt.lifecycle.ControlLCAUtil;
import org.eclipse.rwt.lifecycle.IWidgetAdapter;
import org.eclipse.rwt.lifecycle.JSWriter;
import org.eclipse.rwt.lifecycle.WidgetLCAUtil;
import org.eclipse.rwt.lifecycle.WidgetUtil;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;

public class TrackableLabelAndHyperlinkLCA extends AbstractWidgetLCA {

	private static final String PARAM_MOUSE_ENTER = "mouseEnter";

	private static final String PARAM_MOUSE_EXIT = "mouseExit";

	public void readData(Widget widget) {
		TrackableLabelAndHyperlink control = (TrackableLabelAndHyperlink) widget;
		String mouseEnter = WidgetLCAUtil.readPropertyValue(control, PARAM_MOUSE_ENTER);
		control.setMouseEnter(mouseEnter);
		String mouseExit = WidgetLCAUtil.readPropertyValue(control, PARAM_MOUSE_EXIT);
		control.setMouseExit(mouseExit);
	}

	@Override
	public void preserveValues(Widget widget) {
		ControlLCAUtil.preserveValues((Control) widget);
		IWidgetAdapter adapter = WidgetUtil.getAdapter(widget);
		String mouseEnter = ((TrackableLabel) widget).getMouseEnter();
		if (mouseEnter != null) {
			adapter.preserve(PARAM_MOUSE_ENTER, mouseEnter);
		}
		String mouseExit = ((TrackableLabel) widget).getMouseExit();
		if (mouseExit != null) {
			adapter.preserve(PARAM_MOUSE_EXIT, mouseExit);
		}
		WidgetLCAUtil.preserveCustomVariant(widget);
	}

	@Override
	public void renderInitialization(Widget widget) throws IOException {
		JSWriter writer = JSWriter.getWriterFor(widget);
		String id = WidgetUtil.getId(widget);
		writer.newWidget("gov.redhawk.sca.ui.singledomain.TrackableLabelAndHyperlink", new Object[] {id});
		writer.set("overflow", "hidden");
		ControlLCAUtil.writeStyleFlags((TrackableLabel) widget);
	}

	@Override
	public void renderChanges(Widget widget) throws IOException {
		TrackableLabelAndHyperlink control = (TrackableLabelAndHyperlink) widget;
		ControlLCAUtil.writeChanges(control);
		JSWriter writer = JSWriter.getWriterFor(widget);
		String mouseEnter = control.getMouseEnter();
		if (mouseEnter != null) {
			writer.set(PARAM_MOUSE_ENTER, PARAM_MOUSE_ENTER, mouseEnter);
		}
		String mouseExit = control.getMouseExit();
		if (mouseExit != null) {
			writer.set(PARAM_MOUSE_EXIT, PARAM_MOUSE_EXIT, mouseExit);
		}
		WidgetLCAUtil.writeCustomVariant(widget);
	}

	@Override
	public void renderDispose(Widget widget) throws IOException {
		JSWriter writer = JSWriter.getWriterFor(widget);
		writer.dispose();
	}

}

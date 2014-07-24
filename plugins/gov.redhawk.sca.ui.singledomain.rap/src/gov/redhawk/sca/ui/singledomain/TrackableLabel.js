qx.Class.define("gov.redhawk.sca.ui.singledomain.TrackableLabel", {
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
	extend: qx.ui.layout.CanvasLayout,

	construct: function(id) {
		this.base(arguments);
		this.setHtmlProperty("id", id);
		this._id = id;
		this.addEventListener("mouseover", this.mouseIn);
		this.addEventListener("mouseout", this.mouseOut);
		qx.ui.core.Widget.flushGlobalQueues();
	},

	properties: {
		mouseExit: {
			init: "true",
			apply: ""
		},
		mouseEnter: {
			init: "false",
			apply: ""
		}
	},

	members: {
		mouseIn: function(e) {
			var wm = org.eclipse.swt.WidgetManager.getInstance();
			var labelWidgetId = wm.findIdByWidget(this);
			var req = org.eclipse.swt.Request.getInstance();
			req.addParameter(labelWidgetId + ".mouseEnter", e.getClientX() + ":" + e.getClientY() + ":" + labelWidgetId);
			req.send();
		},

		mouseOut: function(e) {
			var wm = org.eclipse.swt.WidgetManager.getInstance();
			var labelWidgetId = wm.findIdByWidget(this);
			var req = org.eclipse.swt.Request.getInstance();
			req.addParameter(labelWidgetId + ".mouseExit", e.getClientX() + ":" + e.getClientY() + ":" + labelWidgetId);
			req.send();
		},
	}
});

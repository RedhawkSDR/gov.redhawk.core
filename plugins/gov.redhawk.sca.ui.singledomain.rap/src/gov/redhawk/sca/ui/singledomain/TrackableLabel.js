qx.Class.define("gov.redhawk.sca.ui.singledomain.TrackableLabel", {
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

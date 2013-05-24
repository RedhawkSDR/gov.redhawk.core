qx.Class.define("gov.redhawk.sca.ui.singledomain.TrackableLabelAndHyperlink", {
	extend: qx.ui.layout.CanvasLayout,

	construct: function(id) {
		this.base(arguments);
		this.setHtmlProperty("id", id);
		this._id = id;
		/*
		 * mouseover and mouseout are implemented as bubbling events, meaning they fire for
		 * the element registered plus all of its descendants. This results in mouseout events
		 * being fired often when the mouse enters the registered element. We use method moouseEvent
		 * to filter mouseover and mouseout events based on the event target and related target.
		 */
		this.addEventListener('mouseover', this.mouseEvent(this.mouseIn));
		this.addEventListener('mouseout', this.mouseEvent(this.mouseOut));
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
			/* e.getClientX() returns display-relative coordinates */
			req.addParameter(labelWidgetId + ".mouseEnter", e.getClientX() + ":" + e.getClientY() + ":" + labelWidgetId);
			req.send();
		},

		mouseOut: function(e) {
			var wm = org.eclipse.swt.WidgetManager.getInstance();
			var labelWidgetId = wm.findIdByWidget(this);
			var req = org.eclipse.swt.Request.getInstance();
			/* e.getClientX() returns display-relative coordinates */
			req.addParameter(labelWidgetId + ".mouseExit", e.getClientX() + ":" + e.getClientY() + ":" + labelWidgetId);
			req.send();
		},

		mouseEvent: function(_fn) {
			return function(_evt) {
				var relTarget = _evt.getRelatedTarget();
				var target = _evt.getTarget();
				if (this === target) {
					if (!this.contains(relTarget)) {
						_fn.call(this, _evt);
					}
				} else {
					_fn.call(this, _evt); 
				}
			}
		},
	}
});

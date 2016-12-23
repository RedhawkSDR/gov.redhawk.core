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
package gov.redhawk.sca.ui.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import CF.ResourceOperations;
import gov.redhawk.model.sca.util.StopJob;
import gov.redhawk.sca.ui.ScaUiPlugin;
import gov.redhawk.sca.util.PluginUtil;

/**
 * @since 3.0
 */
public class StopAction extends Action {
	private Object context;

	public StopAction() {
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(ScaUiPlugin.PLUGIN_ID, "icons/clcl16/stop.gif"));
		setText("Stop");
		setToolTipText("Stop");
		setId("gov.redhawk.stop");
		this.setEnabled(false);
	}

	public void setContext(final Object obj) {
		this.context = obj;
		setEnabled(PluginUtil.adapt(ResourceOperations.class, obj) != null);
	}

	@Override
	public void run() {
		if (!this.isEnabled()) {
			return;
		}
		stop(this.context);
	}

	private void stop(final Object obj) {
		final ResourceOperations resource = PluginUtil.adapt(ResourceOperations.class, obj);
		if (resource != null) {
			final StopJob job = new StopJob(resource.identifier(), resource);
			job.setUser(true);
			job.schedule();
		}
	}
}

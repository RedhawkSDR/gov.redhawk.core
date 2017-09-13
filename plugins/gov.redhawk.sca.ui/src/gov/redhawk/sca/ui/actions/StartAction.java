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

import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import CF.ResourceOperations;
import gov.redhawk.sca.model.jobs.StartJob;
import gov.redhawk.sca.ui.ScaUiPlugin;
import gov.redhawk.sca.util.PluginUtil;

/**
 * @since 3.0
 */
public class StartAction extends Action {
	private Object context;

	public StartAction() {
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(ScaUiPlugin.PLUGIN_ID, "icons/clcl16/play.gif"));
		setText("Start");
		setToolTipText("Start");
		setId("gov.redhawk.start");
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
		start(this.context);
	}

	private void start(final Object obj) {
		final ResourceOperations resource = PluginUtil.adapt(ResourceOperations.class, obj);
		if (resource != null) {
			final Job job = new StartJob(resource.identifier(), resource);
			job.setUser(true);
			job.schedule();
		}
	}
}

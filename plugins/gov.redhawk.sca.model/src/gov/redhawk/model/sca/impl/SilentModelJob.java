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
package gov.redhawk.model.sca.impl;

import gov.redhawk.model.sca.IDisposable;
import gov.redhawk.model.sca.IStatusProvider;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.commands.SetStatusCommand;
import gov.redhawk.sca.util.SilentJob;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.emf.ecore.EStructuralFeature;

/**
 * @since 14.0
 */
public abstract class SilentModelJob extends SilentJob {

	public static final String JOB_FAMILY = ScaModelPlugin.ID + ".silentModelJob";

	private static boolean shouldRun = true;

	private final IStatusProvider dataObj;
	
	private final EStructuralFeature errFeature;
	
	private final IJobChangeListener jobChangeListener = new JobChangeAdapter() {
		@Override
		public void done(org.eclipse.core.runtime.jobs.IJobChangeEvent event) {
			IStatus silentStatus = getSilentStatus();
			IStatusProvider dataProvider = getModelObject();
			EStructuralFeature feature = getErrFeature();
			if (dataProvider != null && feature != null && silentStatus != null && !silentStatus.isOK()) {
				ScaModelCommand.execute(dataProvider, new SetStatusCommand<IStatusProvider>(dataProvider, feature, silentStatus));
			}
		}
	};

	public SilentModelJob(String msg, final IStatusProvider dataObj, EStructuralFeature errFeature) {
	    super(msg);
	    this.dataObj = dataObj;
	    this.errFeature = errFeature;
	    setSystem(true);
	    addJobChangeListener(jobChangeListener);
    }
	
	public SilentModelJob(String msg) {
		super(msg);
		setSystem(true);
		this.dataObj = null;
		this.errFeature = null;
	}

	protected EStructuralFeature getErrFeature() {
	    return errFeature;
    }
	
	protected IStatusProvider getModelObject() {
		return this.dataObj;
	}
	
	private boolean validState() {
		IStatusProvider tmpDataObj = getModelObject();
		if (tmpDataObj instanceof IDisposable) {
			return !((IDisposable) tmpDataObj).isDisposed();
		} else {
			return true;
		}
	}
	
	@Override
	public boolean shouldSchedule() {
	    return validState() && super.shouldSchedule();
//		return false;
	}
	
	@Override
	public boolean shouldRun() {
	    return shouldRun && validState() && super.shouldRun();
//		return false;
	}
	
	@Override
	public boolean belongsTo(Object family) {
	    return super.belongsTo(family) || JOB_FAMILY.equals(family);
	}
	
	public static void setShouldRun(boolean b) {
	    shouldRun = b;
    }
	

}

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
package gov.redhawk.sca.ui;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.progress.UIJob;

/**
 * @since 8.0
 */
public class FlasherJob extends UIJob {
	private static final long DEFAULT_INTERVAL_DELAY = 50;
	private static final int DEFAULT_NUM_FLASHES = 5;
	private int flash = FlasherJob.DEFAULT_NUM_FLASHES;
	private final Control control;
	private final Color defaultBackground;
	private final boolean custom;
	private final long intervalDelay;
	private final int customFlash;
	private final Runnable postFlash;

	public FlasherJob(final Control control) {
		this(control, false, FlasherJob.DEFAULT_INTERVAL_DELAY, FlasherJob.DEFAULT_NUM_FLASHES, null);
	}

	public FlasherJob(final Control control, final boolean custom, final long intervalDelay, final int numFlashes, final Runnable postFlash) {
		super(control.getDisplay(), "Flasher");
		this.custom = custom;
		this.intervalDelay = intervalDelay;
		this.customFlash = numFlashes;
		this.setUser(false);
		this.setSystem(true);
		this.control = control;
		this.defaultBackground = this.control.getBackground();
		this.postFlash = postFlash;
	}

	@Override
	public IStatus runInUIThread(final IProgressMonitor monitor) {
		if (!this.control.isDisposed()) {
			this.flash--;
			if (this.flash % 2 == 1) {
				if (this.custom) {
					flash(this.control);
				} else {
					this.control.setBackground(this.control.getDisplay().getSystemColor(SWT.COLOR_BLUE));
				}
			} else {
				if (this.custom) {
					unFlash(this.control);
				} else {
					this.control.setBackground(this.defaultBackground);
				}
			}

			if (this.flash > 0) {
				if (this.custom) {
					schedule(this.intervalDelay);
				} else {
					schedule(FlasherJob.DEFAULT_INTERVAL_DELAY);
				}
			} else {
				if (this.postFlash != null) {
					this.postFlash.run();
				}
			}
		} else {
			if (this.postFlash != null) {
				this.postFlash.run();
			}
		}
		return Status.OK_STATUS;
	}

	/**
	 * Implement in subclass if constructor is called with custom = true
	 */
	protected void flash(final Control control) {
		//PASS
	}

	/**
	 * Implement in subclass if constructor is called with custom = true
	 */
	protected void unFlash(final Control control) {
		//PASS
	}

	public void reset() {
		if (this.custom) {
			this.flash = this.customFlash;
		} else {
			this.flash = FlasherJob.DEFAULT_NUM_FLASHES;
		}
		schedule(FlasherJob.DEFAULT_INTERVAL_DELAY);
	}

};

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
package gov.redhawk.sca.ui.singledomain.dialogs;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class DialogCloseJob extends Job {

	private DomainsDialog dialog;

	public DialogCloseJob(DomainsDialog dialog) {
		super("Dialog Close Listener");
		this.dialog = dialog;
		setUser(false);
		setSystem(true);
	}

	private boolean done;
	private Point mouseLoc;
	private Rectangle dialogLoc;

	@Override
	public IStatus run(IProgressMonitor monitor) {
		done = false;
		while (!monitor.isCanceled() && !done) {
			if (dialog != null && dialog.getShell() != null && !dialog.getShell().isDisposed()) {
				/**
				 * Job is hanging here. It's not used when the toolbars listeners are active,
				 * since they track mouseExit. But it works on the high side. See what's different
				 * there.
				 */
				dialog.getShell().getDisplay().syncExec(new Runnable() {

					@Override
					public void run() {
						if (dialog.getShell() != null && !dialog.getShell().isDisposed()) {
							mouseLoc = dialog.getShell().getDisplay().getCursorLocation();
							dialogLoc = dialog.getShell().getBounds();
						}
					}

				});
				if (dialogLoc != null && !dialogLoc.contains(mouseLoc)) {
					dialog.getShell().getDisplay().asyncExec(new Runnable() {

						@Override
						public void run() {
							dialog.hide();
						}

					});
					done = true;
				}
			}
			if (!done && !monitor.isCanceled()) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					continue;
				}
			} else {
				break;
			}
		}
		return Status.OK_STATUS;
	}
};

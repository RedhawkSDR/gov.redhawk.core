/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.core.graphiti.ui.editor;

import org.eclipse.graphiti.ui.editor.DefaultRefreshBehavior;
import org.eclipse.graphiti.ui.editor.DiagramBehavior;
import org.eclipse.swt.widgets.Display;

/**
 * Runs Graphiti refresh on the UI thread synchronously for the editing domain, to ensure the model is not modified
 * from another source while refreshing.
 */
/* package */ class SynchronizedRefreshBehavior extends DefaultRefreshBehavior {
	public SynchronizedRefreshBehavior(DiagramBehavior diagramBehavior) {
		super(diagramBehavior);
	}

	@Override
	public void refresh() {
		runInUIThread(new Runnable() {
			@Override
			public void run() {
				if (!diagramBehavior.isAlive()) {
					return;
				}
				try {
					diagramBehavior.getEditingDomain().runExclusive(new Runnable() {
						public void run() {
							doRefresh();
						}
					});
				} catch (InterruptedException e) {
					return;
				}
			}
		});
	}

	protected void runInUIThread(final Runnable runnable) {
		if (Display.getCurrent() == null) {
			Display.getDefault().asyncExec(runnable);
		} else {
			runnable.run();
		}
	}

	private void doRefresh() {
		super.refresh();
	}
}

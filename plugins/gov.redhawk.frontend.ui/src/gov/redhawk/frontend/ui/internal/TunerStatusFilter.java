/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.frontend.ui.internal;

import gov.redhawk.frontend.FrontendPackage;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.model.sca.commands.ScaModelCommand;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Control;

/**
 * 
 */
public class TunerStatusFilter extends ViewerFilter {

	private Adapter adapter = new AdapterImpl() {
		@Override
		public void notifyChanged(org.eclipse.emf.common.notify.Notification msg) {
			switch (msg.getFeatureID(TunerStatus.class)) {
			case FrontendPackage.TUNER_STATUS__ALLOCATION_ID:
				Control control = viewer.getControl();
				if (control.isDisposed()) {
					((Notifier) msg.getNotifier()).eAdapters().remove(this);
				} else {
					control.getDisplay().asyncExec(new Runnable() {

						@Override
						public void run() {
							viewer.refresh();
						}

					});
				}
				break;
			default:
				break;
			}
		}
	};
	private Viewer viewer;

	/**
	 * 
	 */
	public TunerStatusFilter() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean select(Viewer viewer, Object parentElement, Object element) {
		this.viewer = viewer;
		if (element instanceof TunerStatus) {
			final TunerStatus tuner = (TunerStatus) element;
			ScaModelCommand.execute(tuner, new ScaModelCommand() {

				@Override
				public void execute() {
					if (!tuner.eAdapters().contains(adapter)) {
						tuner.eAdapters().add(adapter);
					}
				}
			});
			return ((TunerStatus) element).isAllocated();
		}
		return true;
	}

}

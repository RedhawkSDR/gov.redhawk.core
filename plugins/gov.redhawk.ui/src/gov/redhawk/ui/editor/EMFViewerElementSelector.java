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
package gov.redhawk.ui.editor;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.edit.provider.WrapperItemProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Item;
import org.eclipse.ui.PlatformUI;

/**
 * This adapter handles element selection in an JFace Viewer when items are
 * added or removed from the backing model.
 */
public abstract class EMFViewerElementSelector extends AdapterImpl {

	/**
	 * The viewer the to update.
	 */
	private final Viewer viewer;

	/**
	 * Instantiates a new EMFViewerElementSelector.
	 * 
	 * @param viewer the JFace Viewer to update
	 */
	public EMFViewerElementSelector(final Viewer viewer) {
		this.viewer = viewer;
	}

	/**
	 * Selects the specified object in the Viewer
	 * 
	 * @param obj the object to select
	 */
	public abstract void selectElement(Object obj);

	/**
	 * Selects the specified object in the Viewer
	 * 
	 * @param obj the object to select
	 */
	public abstract void removeElement(Object obj);

	/**
	 * Calls the appropriate method based on event type and the type of viewer.
	 */
	@Override
	public void notifyChanged(final Notification msg) {
		final int type = msg.getEventType();
		if (type == Notification.ADD) {
			selectElement(msg.getNewValue());
		} else if (type == Notification.REMOVE) {
			removeElement(msg.getOldValue());
		}
	}

	/**
	 * Returns the underlying object if wrapped by EMF.
	 */
	protected Object getItemObject(final Item item) {
		Object value;
		if (item.getData() instanceof WrapperItemProvider) {
			value = ((WrapperItemProvider) item.getData()).getValue();
		} else {
			value = item.getData();
		}
		return value;
	}

	/**
	 * Refreshes the viewer.
	 */
	protected void refreshViewer() {
		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			public void run() {
				EMFViewerElementSelector.this.viewer.refresh();
			}
		});
	}
}

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
package gov.redhawk.core.graphiti.dcd.ui.modelmap;

import java.util.Collection;

import mil.jpeojtrs.sca.dcd.DcdComponentInstantiation;
import mil.jpeojtrs.sca.dcd.DcdComponentPlacement;
import mil.jpeojtrs.sca.dcd.DcdConnectInterface;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;

/**
 * Adapts changes to the Graphiti DCD XML model to calls on the model map.
 */
public class GraphitiDCDModelAdapter extends EContentAdapter {

	private final GraphitiDCDModelMap modelMap;

	public GraphitiDCDModelAdapter(final GraphitiDCDModelMap modelMap) {
		this.modelMap = modelMap;
	}

	@Override
	public void notifyChanged(final Notification notification) {
		super.notifyChanged(notification);
		Object newValue = notification.getNewValue();
		Object oldValue = notification.getOldValue();
		switch (notification.getEventType()) {
		case Notification.ADD:
			if (newValue instanceof DcdComponentInstantiation) {
				final DcdComponentInstantiation object = (DcdComponentInstantiation) notification.getNewValue();
				this.modelMap.add(object);
			} else if (newValue instanceof DcdConnectInterface) {
				final DcdConnectInterface object = (DcdConnectInterface) newValue;
				if (object.getSource() == null || object.getTarget() == null || object.getId() == null) {
					return;
				}
				this.modelMap.add(object);
			}
			break;
		case Notification.ADD_MANY:
			for (Object obj : ((Collection< ? >) newValue)) {
				if (obj instanceof DcdComponentInstantiation) {
					final DcdComponentInstantiation object = (DcdComponentInstantiation) obj;
					this.modelMap.add(object);
				} else if (newValue instanceof DcdConnectInterface) {
					final DcdConnectInterface object = (DcdConnectInterface) newValue;
					if (object.getSource() == null || object.getTarget() == null || object.getId() == null) {
						return;
					}
					this.modelMap.add(object);
				}
			}
			break;
		case Notification.REMOVE:
			if (oldValue instanceof DcdComponentInstantiation) {
				final DcdComponentInstantiation object = (DcdComponentInstantiation) oldValue;
				this.modelMap.remove(object);
			} else if (oldValue instanceof DcdComponentPlacement) {
				final DcdComponentPlacement object = (DcdComponentPlacement) oldValue;
				for (DcdComponentInstantiation i : object.getComponentInstantiation()) {
					this.modelMap.remove(i);
				}
			} else if (oldValue instanceof DcdConnectInterface) {
				final DcdConnectInterface object = (DcdConnectInterface) oldValue;
				this.modelMap.remove(object);
			}
			break;
		case Notification.REMOVE_MANY:
			for (Object obj : ((Collection< ? >) oldValue)) {
				if (obj instanceof DcdComponentInstantiation) {
					final DcdComponentInstantiation object = (DcdComponentInstantiation) obj;
					this.modelMap.remove(object);
				} else if (oldValue instanceof DcdComponentPlacement) {
					final DcdComponentPlacement object = (DcdComponentPlacement) oldValue;
					for (DcdComponentInstantiation i : object.getComponentInstantiation()) {
						this.modelMap.remove(i);
					}
				} else if (obj instanceof DcdConnectInterface) {
					final DcdConnectInterface object = (DcdConnectInterface) obj;
					this.modelMap.remove(object);
				}
			}
			break;
		default:
			break;
		}
	}
}

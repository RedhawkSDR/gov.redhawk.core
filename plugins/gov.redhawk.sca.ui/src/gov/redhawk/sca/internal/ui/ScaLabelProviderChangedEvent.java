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
package gov.redhawk.sca.internal.ui;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;

public class ScaLabelProviderChangedEvent extends LabelProviderChangedEvent {

	private static final long serialVersionUID = 2626099358220654381L;

	private transient Notification notification;

	public ScaLabelProviderChangedEvent(IBaseLabelProvider source, Object element, Notification notification) {
		super(source, element);
		this.notification = notification;
	}

	public Notification getNotification() {
		return notification;
	}

}

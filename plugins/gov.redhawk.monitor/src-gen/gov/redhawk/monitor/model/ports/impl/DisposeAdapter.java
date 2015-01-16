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
package gov.redhawk.monitor.model.ports.impl;

import gov.redhawk.model.sca.IDisposable;
import gov.redhawk.model.sca.ScaPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class DisposeAdapter extends AdapterImpl {

	private final EObject monitor;

	public DisposeAdapter(final EObject monitor) {
		this.monitor = monitor;
	}

	@Override
	public void notifyChanged(final Notification msg) {
		switch (msg.getFeatureID(IDisposable.class)) {
		case ScaPackage.IDISPOSABLE__DISPOSED:
			EcoreUtil.remove(this.monitor);
			((EObject) msg.getNotifier()).eAdapters().remove(this);
			break;
		default:
			break;
		}
	}
}

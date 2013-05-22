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
package gov.redhawk.model.sca.provider;

import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaWaveform;

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * @since 10.0
 * 
 */
public class ScaWaveformExternalPortsItemProvider extends TransientItemProvider {

	public ScaWaveformExternalPortsItemProvider(AdapterFactory adapterFactory, ScaWaveform waveform) {
	    super(adapterFactory, waveform);
    }

	@Override
	protected Collection< ? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(ScaPackage.Literals.SCA_PORT_CONTAINER__PORTS);
		}
		return childrenFeatures;
	}
	
	@Override
	public String getText(Object object) {
	    return "External Ports";
	}
	
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ScaWaveformExternalPorts.gif"));
	}
	
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(ScaWaveform.class)) {
			case ScaPackage.SCA_WAVEFORM__PORTS:
				fireNotifyChanged(new ViewerNotification(notification, this, true, false));
				return;
			default:
				break;
		}
		super.notifyChanged(notification);
	}
}

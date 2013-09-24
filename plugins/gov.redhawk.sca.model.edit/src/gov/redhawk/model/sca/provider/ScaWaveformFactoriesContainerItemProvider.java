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

 // BEGIN GENERATED CODE
package gov.redhawk.model.sca.provider;


import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaPackage;

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ViewerNotification;


public class ScaWaveformFactoriesContainerItemProvider
	extends TransientItemProvider
	implements
		IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource, ITableItemLabelProvider {


	/**
     * @since 11.0
     */
	public ScaWaveformFactoriesContainerItemProvider(AdapterFactory adapterFactory, ScaDomainManager object) {
	    super(adapterFactory, object);
    }
	
	
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(ScaPackage.Literals.SCA_DOMAIN_MANAGER__WAVEFORM_FACTORIES);
		}
		return childrenFeatures;
	}

	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/ScaWaveformFactoriesContainer"));
	}

	@Override
	public String getText(Object object) {
//		return getString("_UI_ScaWaveformFactoriesContainer_type");
		return "Waveform Factories";
	}
	
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(ScaDomainManager.class)) {
			case ScaPackage.SCA_DOMAIN_MANAGER__WAVEFORM_FACTORIES:
				fireNotifyChanged(new ViewerNotification(notification, this, true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	@Override
	public Object getParent(Object object) {
	    return target;
	}
}

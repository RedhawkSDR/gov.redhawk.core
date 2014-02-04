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
package gov.redhawk.ui.views.namebrowser.internal;

import gov.redhawk.model.sca.provider.ScaEditPlugin;
import gov.redhawk.ui.views.namebrowser.view.BindingNode;

import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.omg.CosEventChannelAdmin.EventChannelHelper;

import CF.ApplicationHelper;
import CF.DeviceHelper;
import CF.DeviceManagerHelper;
import CF.DomainManagerHelper;
import CF.ResourceHelper;

/**
 * 
 */
public class BindingNodeDecorator extends LabelProvider implements ILabelDecorator {

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ILabelDecorator#decorateImage(org.eclipse.swt.graphics.Image, java.lang.Object)
	 */
	@Override
	public Image decorateImage(Image image, Object element) {
		if (element instanceof BindingNode) {
			BindingNode node = (BindingNode) element;
			if (node.getType() == BindingNode.Type.OBJECT) {
				if (node.is_a(DomainManagerHelper.id())) {
					return ExtendedImageRegistry.INSTANCE.getImage(ScaEditPlugin.INSTANCE.getImage("full/obj16/ScaDomainManager"));
				} else if (node.is_a(DeviceManagerHelper.id())) {
					return ExtendedImageRegistry.INSTANCE.getImage(ScaEditPlugin.INSTANCE.getImage("full/obj16/ScaDeviceManager"));
				} else if (node.is_a(EventChannelHelper.id())) {
					return ExtendedImageRegistry.INSTANCE.getImage(ScaEditPlugin.INSTANCE.getImage("full/obj16/ScaEventChannel"));
				} else if (node.is_a(ApplicationHelper.id())) {
					return ExtendedImageRegistry.INSTANCE.getImage(ScaEditPlugin.INSTANCE.getImage("full/obj16/ScaWaveform"));
				} else if (node.is_a(DeviceHelper.id())) {
					return ExtendedImageRegistry.INSTANCE.getImage(ScaEditPlugin.INSTANCE.getImage("full/obj16/ScaDevice"));
				} else if (node.is_a(ResourceHelper.id())) {
					return ExtendedImageRegistry.INSTANCE.getImage(ScaEditPlugin.INSTANCE.getImage("full/obj16/ScaComponent"));
				}
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ILabelDecorator#decorateText(java.lang.String, java.lang.Object)
	 */
	@Override
	public String decorateText(String text, Object element) {
		// TODO Auto-generated method stub
		return null;
	}

}

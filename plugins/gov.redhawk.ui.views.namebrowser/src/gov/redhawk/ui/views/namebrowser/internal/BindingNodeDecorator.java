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

import CF.DomainManagerHelper;

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
			if (node.is_a(DomainManagerHelper.id())) {
				return ExtendedImageRegistry.INSTANCE.getImage(ScaEditPlugin.INSTANCE.getImage("full/obj16/ScaDomainManager"));
			} else if (node.is_a(EventChannelHelper.id())) {
				return ExtendedImageRegistry.INSTANCE.getImage(ScaEditPlugin.INSTANCE.getImage("full/obj16/ScaEventChannel"));
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

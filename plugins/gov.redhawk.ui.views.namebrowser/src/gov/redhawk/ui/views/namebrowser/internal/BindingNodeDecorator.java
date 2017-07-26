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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.jacorb.orb.ORB;
import org.jacorb.orb.ParsedIOR;
import org.omg.CORBA.SystemException;
import org.omg.CosEventChannelAdmin.EventChannelHelper;

import CF.AggregateExecutableDeviceHelper;
import CF.AggregateLoadableDeviceHelper;
import CF.AggregatePlainDeviceHelper;
import CF.ApplicationHelper;
import CF.DeviceHelper;
import CF.DeviceManagerHelper;
import CF.DomainManagerHelper;
import CF.ExecutableDeviceHelper;
import CF.LoadableDeviceHelper;
import CF.ResourceHelper;
import ExtendedEvent.MessageEventHelper;
import gov.redhawk.model.sca.provider.ScaEditPlugin;
import gov.redhawk.ui.views.namebrowser.view.BindingNode;

public class BindingNodeDecorator extends LabelProvider implements ILabelDecorator {

	private Map<String, String> idlTypeToImage;

	public BindingNodeDecorator() {
		// This map obviously doesn't handle inheritance of interfaces, but an is_a check entails CORBA calls
		idlTypeToImage = new HashMap<>();
		idlTypeToImage.put(DomainManagerHelper.id(), "full/obj16/ScaDomainManager");
		idlTypeToImage.put(DeviceManagerHelper.id(), "full/obj16/ScaDeviceManager");
		idlTypeToImage.put(EventChannelHelper.id(), "full/obj16/ScaEventChannel");
		idlTypeToImage.put(MessageEventHelper.id(), "full/obj16/ScaEventChannel");
		idlTypeToImage.put(ApplicationHelper.id(), "full/obj16/ScaWaveform");
		final String devicePath = "full/obj16/ScaDevice";
		idlTypeToImage.put(DeviceHelper.id(), devicePath);
		idlTypeToImage.put(AggregatePlainDeviceHelper.id(), devicePath);
		idlTypeToImage.put(LoadableDeviceHelper.id(), devicePath);
		idlTypeToImage.put(AggregateLoadableDeviceHelper.id(), devicePath);
		idlTypeToImage.put(ExecutableDeviceHelper.id(), devicePath);
		idlTypeToImage.put(AggregateExecutableDeviceHelper.id(), devicePath);
		idlTypeToImage.put(ResourceHelper.id(), "full/obj16/ScaComponent");
	}

	@Override
	public Image decorateImage(Image image, Object element) {
		if (!(element instanceof BindingNode)) {
			return null;
		}
		BindingNode node = (BindingNode) element;

		if (node.getType() != BindingNode.Type.OBJECT) {
			return null;
		}

		// Parse the IOR
		ParsedIOR parsedIOR;
		try {
			parsedIOR = new ParsedIOR((ORB) node.getOrb(), node.getIOR());
		} catch (IllegalArgumentException | SystemException e) {
			return null;
		}
		String typeId = parsedIOR.getIOR().type_id;

		// If we recognize the type ID from the IOR, show an icon for it
		String imagePath = idlTypeToImage.get(typeId);
		if (imagePath == null) {
			return null;
		}
		return ExtendedImageRegistry.INSTANCE.getImage(ScaEditPlugin.INSTANCE.getImage(imagePath));
	}

	@Override
	public String decorateText(String text, Object element) {
		return null;
	}

}

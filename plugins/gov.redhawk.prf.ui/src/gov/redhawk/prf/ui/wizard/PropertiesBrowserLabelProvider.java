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
package gov.redhawk.prf.ui.wizard;

import gov.redhawk.sca.properties.Category;
import gov.redhawk.sca.properties.IPropertiesProvider;
import gov.redhawk.sca.util.PluginUtil;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * @since 4.0
 */
public class PropertiesBrowserLabelProvider extends AdapterFactoryLabelProvider implements ILabelProvider {

	private ResourceManager resourceManager;

	public PropertiesBrowserLabelProvider(final AdapterFactory factory) {
		super(factory);
	}

	private ResourceManager getResourceManager() {
		if (resourceManager == null) {
			resourceManager = new LocalResourceManager(JFaceResources.getResources());
		}

		return resourceManager;
	}

	@Override
	public void dispose() {
		if (resourceManager != null) {
			resourceManager.dispose();
			resourceManager = null;
		}
		super.dispose();
	}

	@Override
	public Image getImage(final Object object) {
		IWorkbenchAdapter workbenchAdapter = PluginUtil.adapt(IWorkbenchAdapter.class, object, true);
		if (workbenchAdapter != null) {
			ImageDescriptor desc = workbenchAdapter.getImageDescriptor(object);
			if (desc != null) {
				return (Image) getResourceManager().get(desc);
			} else {
				return null;
			}
		} else if (object instanceof IPropertiesProvider) {
			IPropertiesProvider provider = (IPropertiesProvider) object;
			ImageDescriptor desc = AbstractUIPlugin.imageDescriptorFromPlugin(provider.getIconPluginId(), provider.getIconPath());
			return (Image) getResourceManager().get(desc);
		} else if (object instanceof Category) {
			Category category = (Category) object;
			ImageDescriptor desc = AbstractUIPlugin.imageDescriptorFromPlugin(category.getIconPluginId(), category.getIconPath());
			return (Image) getResourceManager().get(desc);
		}
		return super.getImage(object);
	}

	@Override
	public String getText(final Object object) {
		IWorkbenchAdapter workbenchAdapter = PluginUtil.adapt(IWorkbenchAdapter.class, object, true);
		if (workbenchAdapter != null) {
			return workbenchAdapter.getLabel(object);
		} else if (object instanceof IPropertiesProvider) {
			return ((IPropertiesProvider) object).getName();
		} else if (object instanceof Category) {
			return ((Category) object).getName();
		}
		return super.getText(object);
	}
}

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
import gov.redhawk.sca.properties.PropertiesProviderRegistry;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.ui.model.WorkbenchLabelProvider;

/**
 * 
 */
public class PropertiesBrowserLabelProvider extends AdapterFactoryLabelProvider implements ILabelProvider {

	private final WorkbenchLabelProvider workbenchLabelProvider = new WorkbenchLabelProvider();

	/**
	 * @param adapterFactory
	 */
	public PropertiesBrowserLabelProvider(final AdapterFactory factory) {
		super(factory);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		this.workbenchLabelProvider.dispose();
		super.dispose();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Image getImage(final Object object) {
		if (object instanceof IWorkbenchAdapter) {
			return this.workbenchLabelProvider.getImage(object);
		}
		return super.getImage(object);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getText(final Object object) {
		if (object instanceof IWorkbenchAdapter) {
			return this.workbenchLabelProvider.getText(object);
		} else if (object instanceof IPropertiesProvider) {
			return PropertiesProviderRegistry.INSTANCE.getName((IPropertiesProvider) object);
		} else if (object instanceof Category) {
			return ((Category) object).getName();
		}
		return super.getText(object);
	}
}

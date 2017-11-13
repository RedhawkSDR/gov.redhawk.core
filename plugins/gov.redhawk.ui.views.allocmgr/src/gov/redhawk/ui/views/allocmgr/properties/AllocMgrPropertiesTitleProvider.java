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
package gov.redhawk.ui.views.allocmgr.properties;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Image;

import gov.redhawk.ui.views.allocmgr.provider.AllocMgrItemProviderAdapterFactory;

/**
 * Provides the title of the properties view.
 */
public class AllocMgrPropertiesTitleProvider extends AdapterFactoryLabelProvider {

	public AllocMgrPropertiesTitleProvider() {
		super(new AllocMgrItemProviderAdapterFactory());
	}

	@Override
	public Image getImage(Object object) {
		object = unwrap(object);
		return super.getImage(object);
	}

	@Override
	public String getText(Object object) {
		object = unwrap(object);
		return super.getText(object);
	}

	private Object unwrap(Object object) {
		if (object instanceof IStructuredSelection) {
			object = ((IStructuredSelection) object).getFirstElement();
		}
		return object;
	}

}

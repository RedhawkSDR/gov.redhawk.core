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
package gov.redhawk.ui.views.connmgr.properties;

import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Image;

import gov.redhawk.ui.views.connmgr.provider.ConnMgrItemProviderAdapterFactory;

/**
 * Provides the title of the properties view.
 */
public class ConnMgrPropertiesTitleProvider extends AdapterFactoryLabelProvider {

	public ConnMgrPropertiesTitleProvider() {
		super(new ConnMgrItemProviderAdapterFactory());
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

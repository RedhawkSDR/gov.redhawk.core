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
package gov.redhawk.ui.parts;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;

/**
 * @since 8.0
 */
public class UnwrappingLabelProvider implements ILabelProvider {
	private ILabelProvider provider;

	public UnwrappingLabelProvider(ILabelProvider provider) {
		Assert.isNotNull(provider);
		this.provider = provider;
	}

	@Override
	public Image getImage(Object element) {
		return provider.getImage(unwrap(element));
	}

	@Override
	public String getText(Object element) {
		return provider.getText(unwrap(element));
	}

	@Override
	public void addListener(ILabelProviderListener listener) {
		provider.addListener(listener);
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return provider.isLabelProperty(unwrap(element), property);
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {
		provider.removeListener(listener);
	}

	@Override
	public void dispose() {
		provider.dispose();
		provider = null;
	}

	private Object unwrap(Object element) {
		return AdapterFactoryEditingDomain.unwrap(element);
	}
}

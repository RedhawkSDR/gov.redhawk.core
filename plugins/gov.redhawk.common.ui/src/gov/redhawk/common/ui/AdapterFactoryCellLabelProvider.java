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
package gov.redhawk.common.ui;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;

/**
 * This wraps the default AdapterFactory label provider within a cell label provider
 * 
 * This class may be extended by clients to customize the functionality, especially for tool-tips.
 * 
 * @since 3.0
 * 
 */
public class AdapterFactoryCellLabelProvider extends ColumnLabelProvider {

	private final AdapterFactoryLabelProvider labelProvider;

	public AdapterFactoryCellLabelProvider(final AdapterFactory adapterFactory) {
		this.labelProvider = new AdapterFactoryLabelProvider(adapterFactory);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update(final ViewerCell cell) {
		Object object = getElement(cell);
		object = AdapterFactoryEditingDomain.unwrap(object);
		cell.setBackground(this.labelProvider.getBackground(object));
		cell.setFont(this.labelProvider.getFont(object));
		cell.setForeground(this.labelProvider.getForeground(object));
		cell.setImage(this.labelProvider.getImage(object));
		cell.setText(this.labelProvider.getText(object));
	}

	/**
	 * @since 3.0
	 */
	protected Object getElement(final ViewerCell cell) {
		return cell.getElement();
	}

	@Override
	public void addListener(final ILabelProviderListener listener) {
		super.addListener(listener);
		this.labelProvider.addListener(listener);
	}

	@Override
	public void dispose() {
		super.dispose();
		this.labelProvider.dispose();
	}

	@Override
	public void removeListener(final ILabelProviderListener listener) {
		super.removeListener(listener);
		this.labelProvider.removeListener(listener);
	}

	@Override
	public boolean isLabelProperty(final Object element, final String property) {
		return this.labelProvider.isLabelProperty(element, property);
	}

	@Override
	public String getText(final Object element) {
		return this.labelProvider.getText(element);
	}

	@Override
	public Color getBackground(final Object element) {
		return this.labelProvider.getBackground(element);
	}

	@Override
	public Font getFont(final Object element) {
		return this.labelProvider.getFont(element);
	}

	@Override
	public Color getForeground(final Object element) {
		return this.labelProvider.getForeground(element);
	}

	@Override
	public Image getImage(final Object element) {
		return this.labelProvider.getImage(element);
	}

}

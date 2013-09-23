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
package gov.redhawk.ui.views.domainbrowser.view;

import org.eclipse.emf.edit.provider.AttributeValueWrapperItemProvider;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class DomainTreeLabelProvider extends DecoratingLabelProvider implements ITableLabelProvider {

	public DomainTreeLabelProvider(final ILabelProvider provider, final ILabelDecorator decorator) {
		super(provider, decorator);
	}

	@Override
	public Image getColumnImage(final Object element, final int columnIndex) {
		return super.getImage(element);
	}

	@Override
	public String getColumnText(final Object element, final int columnIndex) {
		if (element instanceof AttributeValueWrapperItemProvider) {
			final AttributeValueWrapperItemProvider attr = (AttributeValueWrapperItemProvider) element;
			switch (columnIndex) {
			case 0:
				return attr.getFeature().getName();
			case 1:
				return (String) attr.getValue();
			default:
				break;
			}
		}

		return super.getText(element);
	}
}

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
package gov.redhawk.sca.internal.ui.properties;

import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaStructProperty;

import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertyColumnLabelProvider;

/**
 * 
 */
public class StructFieldPropertyColumnLabelProvider extends PropertyColumnLabelProvider {
	private final String simpleId;

	public StructFieldPropertyColumnLabelProvider(final IPropertySourceProvider propertySourceProvider, final String simpleId) {
		super(propertySourceProvider, ScaPackage.Literals.SCA_SIMPLE_PROPERTY__VALUE.getName());
		this.simpleId = simpleId;
	}

	private ScaSimpleProperty getSimple(final Object object) {
		if (object instanceof ScaStructProperty) {
			final ScaStructProperty struct = (ScaStructProperty) object;
			return struct.getSimple(this.simpleId);
		}
		return null;
	}

	@Override
	public Color getBackground(final Object element) {
		return super.getBackground(getSimple(element));
	}

	@Override
	public Font getFont(final Object element) {
		return super.getFont(getSimple(element));
	}

	@Override
	public Color getForeground(final Object element) {
		return super.getForeground(getSimple(element));
	}

	@Override
	public Image getImage(final Object object) {
		return null;
	}

	@Override
	public String getText(final Object object) {
		final ScaSimpleProperty simple = getSimple(object);
		final String retVal = super.getText(simple);
		return retVal;
	}

	@Override
	public Color getToolTipBackgroundColor(final Object object) {
		return super.getToolTipBackgroundColor(getSimple(object));
	}

	@Override
	public int getToolTipDisplayDelayTime(final Object object) {
		return 100;
	}

	@Override
	public Font getToolTipFont(final Object object) {
		return super.getToolTipFont(getSimple(object));
	}

	@Override
	public Color getToolTipForegroundColor(final Object object) {
		return super.getToolTipForegroundColor(getSimple(object));
	}

	@Override
	public Image getToolTipImage(final Object object) {
		return super.getToolTipImage(getSimple(object));
	}

	@Override
	public Point getToolTipShift(final Object object) {
		return super.getToolTipShift(getSimple(object));
	}

	@Override
	public int getToolTipStyle(final Object object) {
		return super.getToolTipStyle(getSimple(object));
	}

	@Override
	public String getToolTipText(final Object element) {
		final ScaSimpleProperty simple = getSimple(element);
		String retVal = simple.getDescription();
		if (simple.getDefinition() == null) {
			return null;
		}
		if (retVal == null) {
			retVal = "< " + simple.getDefinition().getType().getLiteral() + " >";
		} else {
			retVal = "< " + simple.getDefinition().getType().getLiteral() + " >\n" + retVal;
		}
		return retVal;
	}

	@Override
	public int getToolTipTimeDisplayed(final Object object) {
		return 5000;
	}

	@Override
	public boolean isLabelProperty(final Object element, final String property) {
		return super.isLabelProperty(getSimple(element), property);
	}

	@Override
	public boolean useNativeToolTip(final Object object) {
		return super.useNativeToolTip(getSimple(object));
	}

	@Override
	protected void fireLabelProviderChanged(LabelProviderChangedEvent event) {
		if (event.getElement() instanceof ScaSimpleProperty) {
			event = new LabelProviderChangedEvent(this, ((ScaSimpleProperty) event.getElement()).eContainer());
		}
		super.fireLabelProviderChanged(event);
	}

}

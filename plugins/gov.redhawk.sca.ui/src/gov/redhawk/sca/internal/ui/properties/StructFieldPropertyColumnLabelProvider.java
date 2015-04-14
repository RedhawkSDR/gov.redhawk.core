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

import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaSimpleSequenceProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import mil.jpeojtrs.sca.prf.AbstractProperty;
import mil.jpeojtrs.sca.prf.PropertyValueType;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleSequence;

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
	private final String elementId;

	public StructFieldPropertyColumnLabelProvider(final IPropertySourceProvider propertySourceProvider, final String simpleId, final boolean isSequence) {
		super(propertySourceProvider, (isSequence) ? ScaPackage.Literals.SCA_SIMPLE_SEQUENCE_PROPERTY__VALUES.getName() : ScaPackage.Literals.SCA_SIMPLE_PROPERTY__VALUE.getName());
		this.elementId = simpleId;
	}

	public StructFieldPropertyColumnLabelProvider(final IPropertySourceProvider propertySourceProvider, final String simpleId) {
		this(propertySourceProvider, simpleId, false);
	}

	private ScaAbstractProperty<? extends AbstractProperty> getElement(final Object object) {
		ScaSimpleProperty simple = getSimple(object);
		if (simple != null) {
			return simple;
		}
		return getSequence(object);
	}

	private ScaSimpleProperty getSimple(final Object object) {
		if (object instanceof ScaStructProperty) {
			final ScaStructProperty struct = (ScaStructProperty) object;
			return struct.getSimple(this.elementId);
		}
		return null;
	}

	private ScaSimpleSequenceProperty getSequence(final Object object) {
		if (object instanceof ScaStructProperty) {
			final ScaStructProperty struct = (ScaStructProperty) object;
			return struct.getSequence(this.elementId);
		}
		return null;
	}

	@Override
	public Color getBackground(final Object element) {
		return super.getBackground(getElement(element));
	}

	@Override
	public Font getFont(final Object element) {
		return super.getFont(getElement(element));
	}

	@Override
	public Color getForeground(final Object element) {
		return super.getForeground(getElement(element));
	}

	@Override
	public Image getImage(final Object object) {
		return null;
	}

	@Override
	public String getText(final Object object) {
		final ScaAbstractProperty<?> simple = getElement(object);
		final String retVal = super.getText(simple);
		return retVal;
	}

	@Override
	public Color getToolTipBackgroundColor(final Object object) {
		return super.getToolTipBackgroundColor(getElement(object));
	}

	@Override
	public int getToolTipDisplayDelayTime(final Object object) {
		return 100;
	}

	@Override
	public Font getToolTipFont(final Object object) {
		return super.getToolTipFont(getElement(object));
	}

	@Override
	public Color getToolTipForegroundColor(final Object object) {
		return super.getToolTipForegroundColor(getElement(object));
	}

	@Override
	public Image getToolTipImage(final Object object) {
		return super.getToolTipImage(getElement(object));
	}

	@Override
	public Point getToolTipShift(final Object object) {
		return super.getToolTipShift(getElement(object));
	}

	@Override
	public int getToolTipStyle(final Object object) {
		return super.getToolTipStyle(getElement(object));
	}

	@Override
	public String getToolTipText(final Object element) {
		final ScaAbstractProperty<? extends AbstractProperty> simple = getElement(element);
		String retVal = simple.getDescription();
		if (simple.getDefinition() == null) {
			return null;
		}
		PropertyValueType type;
		if (simple.getDefinition() instanceof Simple) {
			type = ((Simple) simple.getDefinition()).getType();
		} else if (simple.getDefinition() instanceof SimpleSequence) {
			type = ((SimpleSequence) simple.getDefinition()).getType();
		} else {
			return null;
		}
		if (retVal == null) {
			retVal = "< " + type.getLiteral() + " >";
		} else {
			retVal = "< " + type.getLiteral() + " >\n" + retVal;
		}
		return retVal;
	}

	@Override
	public int getToolTipTimeDisplayed(final Object object) {
		return 5000;
	}

	@Override
	public boolean isLabelProperty(final Object element, final String property) {
		return super.isLabelProperty(getElement(element), property);
	}

	@Override
	public boolean useNativeToolTip(final Object object) {
		return super.useNativeToolTip(getElement(object));
	}

	@Override
	protected void fireLabelProviderChanged(LabelProviderChangedEvent event) {
		Object element = event.getElement();
		if (element instanceof ScaAbstractProperty) {
			event = new LabelProviderChangedEvent(this, ((ScaAbstractProperty<?>) element).eContainer());
		}
		super.fireLabelProviderChanged(event);
	}

}

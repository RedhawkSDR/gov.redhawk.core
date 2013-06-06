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

import mil.jpeojtrs.sca.prf.PropertyValueType;
import mil.jpeojtrs.sca.prf.provider.RadixLabelProviderUtil;
import mil.jpeojtrs.sca.util.AnyUtils;

import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.swt.widgets.Composite;

/**
 * @since 2.1
 * 
 */
public abstract class PropertyValueTypePropertyDescriptor extends PropertyDescriptor {

	protected static class PropertyValueTypeValueHandler extends EDataTypeValueHandler {

		private final PropertyValueType type;
		private static final int DEFAULT_RADIX = 10;
		private int radix = PropertyValueTypeValueHandler.DEFAULT_RADIX;
		private Boolean complex;

		public PropertyValueTypeValueHandler(final PropertyValueType type, Boolean complex) {
			super(type.toEDataType(complex));
			this.type = type;
			this.complex = complex;
		}

		public PropertyValueType getType() {
			return this.type;
		}

		@Override
		public String isValid(final Object object) {
			return isValid(object.toString());
		}

		@Override
		public String isValid(final String text) {
			try {
				AnyUtils.convertString(text, this.type.getLiteral(), complex);
				return null;
			} catch (final Exception e) {
				return e.getMessage();
			}
		}

		public void setRadix(final int value) {
			this.radix = value;
		}

		public int getRadix(final Object value) {
			return this.radix;
		}

		@Override
		public Object toValue(final String string) {
			return AnyUtils.convertString(string, getType().getLiteral(), complex);
		}

		@Override
		public String toString(final Object value) {
			if (RadixLabelProviderUtil.supports(getType(), complex)) {
				return RadixLabelProviderUtil.getText(value, getRadix(value));
			} else {
				return super.toString(value);
			}
		}
	}

	public static class PropertyValueTypeCellEditor extends EDataTypeCellEditor {

		public PropertyValueTypeCellEditor(final PropertyValueType type, Boolean complex, final Composite arg1) {
			super(type.toEDataType(complex), arg1);
			this.valueHandler = new PropertyValueTypeValueHandler(type, complex);
			setValidator(this.valueHandler);
		}

		@Override
		public PropertyValueTypeValueHandler getValidator() {
			return (PropertyValueTypeValueHandler) super.getValidator();
		}
	}

	public PropertyValueTypePropertyDescriptor(final Object obj, final IItemPropertyDescriptor itemPropertyDescriptor) {
		super(obj, itemPropertyDescriptor);
	}
}

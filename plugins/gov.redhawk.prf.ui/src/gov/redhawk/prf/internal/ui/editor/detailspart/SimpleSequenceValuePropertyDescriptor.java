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
package gov.redhawk.prf.internal.ui.editor.detailspart;

import mil.jpeojtrs.sca.prf.PropertyValueType;
import mil.jpeojtrs.sca.util.AnyUtils;

import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.swt.widgets.Composite;

/**
 * @since 2.1
 * 
 */
public abstract class SimpleSequenceValuePropertyDescriptor extends PropertyDescriptor {

	protected static class SimpleSequenceValueHandler extends EDataTypeValueHandler {

		private final PropertyValueType type;
		private final Boolean complex;

		public SimpleSequenceValueHandler(final PropertyValueType type, Boolean complex) {
			super(EcorePackage.Literals.ESTRING);
			this.type = type;
			this.complex = complex;
		}

		@Override
		public String isValid(final Object object) {
			return isValid(object.toString());
		}

		@Override
		public String isValid(final String text) {
			try {
				AnyUtils.convertString(text, this.type.getLiteral(), this.complex);
				return null;
			} catch (final Exception e) { // SUPPRESS CHECKSTYLE Logged Error
				return e.getMessage();
			}
		}
	}

	public static class SimpleSequenceValueCellEditor extends EDataTypeCellEditor {

		public SimpleSequenceValueCellEditor(final PropertyValueType type, Boolean complex, final Composite arg1) {
			super(type.toEDataType(complex), arg1);
			this.valueHandler = createPropertyValueTypeValueHandler(type, complex);
			setValidator(this.valueHandler);
		}

		protected SimpleSequenceValueHandler createPropertyValueTypeValueHandler(final PropertyValueType type, Boolean complex) {
			return new SimpleSequenceValueHandler(type, complex);
		}

	}

	public SimpleSequenceValuePropertyDescriptor(final Object obj, final IItemPropertyDescriptor itemPropertyDescriptor) {
		super(obj, itemPropertyDescriptor);
	}

}

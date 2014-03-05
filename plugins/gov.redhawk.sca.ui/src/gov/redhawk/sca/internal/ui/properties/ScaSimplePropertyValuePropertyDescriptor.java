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

import gov.redhawk.model.sca.ScaSimpleProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import mil.jpeojtrs.sca.prf.PropertyValueType;
import mil.jpeojtrs.sca.prf.provider.RadixLabelProviderUtil;

import org.eclipse.emf.common.ui.celleditor.ExtendedComboBoxCellEditor;
import org.eclipse.emf.common.ui.celleditor.ExtendedDialogCellEditor;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.ui.celleditor.FeatureEditorDialog;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * @since 8.0
 * 
 */
public class ScaSimplePropertyValuePropertyDescriptor extends PropertyValueTypePropertyDescriptor {

	public static class ScaSimplePropertyValueTypeCellEditor extends PropertyValueTypeCellEditor {

		public ScaSimplePropertyValueTypeCellEditor(final ScaSimpleProperty property, final Composite arg1) {
			super(property.getDefinition().getType(), property.getDefinition().isComplex(), arg1);
			final int radix = RadixLabelProviderUtil.getRadix(property.getDefinition().getValue());
			((PropertyValueTypeValueHandler) this.valueHandler).setRadix(radix);
		}
	}

	public ScaSimplePropertyValuePropertyDescriptor(final Object obj, final IItemPropertyDescriptor itemPropertyDescriptor) {
		super(obj, itemPropertyDescriptor);
	}

	@Override
	public CellEditor createPropertyEditor(final Composite composite) {
		CellEditor result = null;

		final Object genericFeature = this.itemPropertyDescriptor.getFeature(this.object);
		final ScaSimpleProperty property = (ScaSimpleProperty) this.object;

		if (genericFeature instanceof EReference[]) {
			result = new ExtendedComboBoxCellEditor(composite, new ArrayList<Object>(this.itemPropertyDescriptor.getChoiceOfValues(this.object)),
				getEditLabelProvider(), this.itemPropertyDescriptor.isSortChoices(this.object));
		} else if (genericFeature instanceof EStructuralFeature) {
			final EStructuralFeature feature = (EStructuralFeature) genericFeature;
			if (property.getDefinition() == null) {
				return null;
			}
			final EClassifier eType = property.getDefinition().getType().toEDataType(property.getDefinition().isComplex());
			final Collection< ? > choiceOfValues = this.itemPropertyDescriptor.getChoiceOfValues(this.object);
			if (choiceOfValues != null) {
				if (this.itemPropertyDescriptor.isMany(this.object)) {
					boolean valid = true;
					for (final Object choice : choiceOfValues) {
						if (!eType.isInstance(choice)) {
							valid = false;
							break;
						}
					}

					if (valid) {
						final ILabelProvider editLabelProvider = getEditLabelProvider();
						result = new ExtendedDialogCellEditor(composite, editLabelProvider) {
							@Override
							protected Object openDialogBox(final Control cellEditorWindow) {
								final FeatureEditorDialog dialog = new FeatureEditorDialog(
									cellEditorWindow.getShell(),
									editLabelProvider,
									ScaSimplePropertyValuePropertyDescriptor.this.object,
									eType,
									(List< ? >) doGetValue(),
									getDisplayName(),
									new ArrayList<Object>(choiceOfValues),
									false,
									ScaSimplePropertyValuePropertyDescriptor.this.itemPropertyDescriptor.isSortChoices(ScaSimplePropertyValuePropertyDescriptor.this.object),
									feature.isUnique());
								dialog.open();
								return dialog.getResult();
							}
						};
					}
				}

				if (result == null) {
					result = new ExtendedComboBoxCellEditor(composite, new ArrayList<Object>(choiceOfValues), getEditLabelProvider(),
						this.itemPropertyDescriptor.isSortChoices(this.object));
				}
			} else if (eType instanceof EDataType) {
				final EDataType eDataType = (EDataType) eType;
				if (eDataType.isSerializable()) {
					if (this.itemPropertyDescriptor.isMany(this.object)) {
						final ILabelProvider editLabelProvider = getEditLabelProvider();
						result = new ExtendedDialogCellEditor(composite, editLabelProvider) {
							@Override
							protected Object openDialogBox(final Control cellEditorWindow) {
								final FeatureEditorDialog dialog = new FeatureEditorDialog(
									cellEditorWindow.getShell(),
									editLabelProvider,
									ScaSimplePropertyValuePropertyDescriptor.this.object,
									eType,
									(List< ? >) doGetValue(),
									getDisplayName(),
									null,
									ScaSimplePropertyValuePropertyDescriptor.this.itemPropertyDescriptor.isMultiLine(ScaSimplePropertyValuePropertyDescriptor.this.object),
									false, feature.isUnique());
								dialog.open();
								return dialog.getResult();
							}
						};
					} else if (eDataType.getInstanceClass() == Boolean.class || eDataType.getInstanceClass() == Boolean.TYPE) {
						result = new ExtendedComboBoxCellEditor(composite, Arrays.asList(new Object[] { Boolean.FALSE, Boolean.TRUE }), new LabelProvider(),
							this.itemPropertyDescriptor.isSortChoices(this.object));
					} else {
						result = createEDataTypeCellEditor(eDataType, composite);
					}
				}
			}
		}

		return result;
	}

	@Override
	protected CellEditor createEDataTypeCellEditor(final EDataType eDataType, final Composite composite) {
		if (!this.itemPropertyDescriptor.canSetProperty(this.object)) {
			return null;
		}
		final ScaSimpleProperty property = (ScaSimpleProperty) this.object;
		if (property.getDefinition() == null) {
			return null;
		}
		PropertyValueType type = property.getDefinition().getType();
		if (type == null) {
			return null;
		}
		if (RadixLabelProviderUtil.supports(type, property.getDefinition().isComplex())) {
			return new ScaSimplePropertyValueTypeCellEditor(property, composite);
		} else {
			return super.createEDataTypeCellEditor(property.getDefinition().getType().toEDataType(property.getDefinition().isComplex()), composite);
		}
	}

}

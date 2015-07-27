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
package gov.redhawk.sca.ui.wizards;

import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaPropertyContainer;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaSimpleSequenceProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.ScaStructSequenceProperty;

import java.util.ArrayList;
import java.util.List;

import mil.jpeojtrs.sca.util.AnyUtils;

import org.eclipse.jface.dialogs.IDialogSettings;

/**
 * @since 8.0
 * 
 */
public final class ScaPropertyUtil {

	private static final String SIMPLE_SEQ_VALUES = "values";
	private static final String SIMPLE_VALUE = "value";

	private ScaPropertyUtil() {
	}

	/**
	 * @since 9.1
	 */
	public static void load(ScaPropertyContainer< ? , ? > container, final IDialogSettings propSettings) {
		if (container != null && propSettings != null) {
			ScaPropertyUtil.restoreProperties(container, propSettings);
		}
	}

	/**
	 * @since 9.1
	 */
	public static void save(final ScaPropertyContainer< ? , ? > container, final IDialogSettings propSettings) {
		if (container != null && propSettings != null) {
			ScaPropertyUtil.storeProperties(container, propSettings);
		}
	}

	private static void storeProperties(final ScaPropertyContainer< ? , ? > container, final IDialogSettings propertySettings) {
		for (final ScaAbstractProperty< ? > prop : container.getProperties()) {
			if (prop.isDefaultValue()) {
				continue;
			}
			final IDialogSettings valueSection = propertySettings.addNewSection(prop.getId());
			ScaPropertyUtil.storeProperty(prop, valueSection);
		}
	}

	private static void storeProperty(final ScaAbstractProperty< ? > prop, final IDialogSettings valueSection) {
		switch (prop.eClass().getClassifierID()) {
		case ScaPackage.SCA_SIMPLE_PROPERTY:
			ScaPropertyUtil.storeSimple((ScaSimpleProperty) prop, valueSection);
			break;
		case ScaPackage.SCA_SIMPLE_SEQUENCE_PROPERTY:
			ScaPropertyUtil.storeSimpleSequence((ScaSimpleSequenceProperty) prop, valueSection);
			break;
		case ScaPackage.SCA_STRUCT_PROPERTY:
			ScaPropertyUtil.storeStruct((ScaStructProperty) prop, valueSection);
			break;
		case ScaPackage.SCA_STRUCT_SEQUENCE_PROPERTY:
			ScaPropertyUtil.storeStructSequence((ScaStructSequenceProperty) prop, valueSection);
			break;
		}
	}

	private static void storeSimple(final ScaSimpleProperty prop, final IDialogSettings valueSection) {
		valueSection.put(ScaPropertyUtil.SIMPLE_VALUE, prop.getValue().toString());
	}

	private static void storeSimpleSequence(final ScaSimpleSequenceProperty prop, final IDialogSettings valueSection) {
		final List<String> values = new ArrayList<String>();
		for (final Object obj : prop.getValues()) {
			values.add(obj.toString());
		}
		valueSection.put(ScaPropertyUtil.SIMPLE_SEQ_VALUES, values.toArray(new String[values.size()]));
	}

	private static void storeStruct(final ScaStructProperty struct, final IDialogSettings valueSection) {
		for (final ScaAbstractProperty< ? > prop : struct.getFields()) {
			if (prop.isDefaultValue()) {
				continue;
			}
			final IDialogSettings simpleSection = valueSection.addNewSection(prop.getId());
			ScaPropertyUtil.storeProperty(prop, simpleSection);
		}
	}

	private static void storeStructSequence(final ScaStructSequenceProperty prop, final IDialogSettings valueSection) {
		int index = 0;
		for (final ScaStructProperty struct : prop.getStructs()) {
			final IDialogSettings structSection = valueSection.addNewSection("struct_" + index);
			ScaPropertyUtil.storeStruct(struct, structSection);
			index++;
		}
	}

	private static void restoreProperties(ScaPropertyContainer< ? , ? > container, final IDialogSettings propSettings) {
		for (final ScaAbstractProperty< ? > prop : container.getProperties()) {
			final IDialogSettings valueSection = propSettings.getSection(prop.getId());
			if (valueSection != null) {
				ScaPropertyUtil.restoreProperty(prop, valueSection);
			}
		}
	}

	private static void restoreProperty(final ScaAbstractProperty< ? > prop, final IDialogSettings valueSection) {
		switch (prop.eClass().getClassifierID()) {
		case ScaPackage.SCA_SIMPLE_PROPERTY:
			ScaPropertyUtil.restoreSimple((ScaSimpleProperty) prop, valueSection);
			break;
		case ScaPackage.SCA_SIMPLE_SEQUENCE_PROPERTY:
			ScaPropertyUtil.restoreSimpleSequence((ScaSimpleSequenceProperty) prop, valueSection);
			break;
		case ScaPackage.SCA_STRUCT_PROPERTY:
			ScaPropertyUtil.restoreStruct((ScaStructProperty) prop, valueSection);
			break;
		case ScaPackage.SCA_STRUCT_SEQUENCE_PROPERTY:
			ScaPropertyUtil.restoreStructSequence((ScaStructSequenceProperty) prop, valueSection);
			break;
		}
	}

	private static void restoreStructSequence(final ScaStructSequenceProperty prop, final IDialogSettings propertySettings) {
		if (propertySettings != null) {
			prop.getStructs().clear();
			for (int index = 0; propertySettings.getSection("struct_" + index) != null; index++) {
				final IDialogSettings structSettings = propertySettings.getSection("struct_" + index);
				final ScaStructProperty struct = prop.createScaStructProperty();
				ScaPropertyUtil.restoreStruct(struct, structSettings);
				prop.getStructs().add(struct);
			}
		}
	}

	private static void restoreStruct(final ScaStructProperty struct, final IDialogSettings structPropertySettings) {
		if (structPropertySettings != null) {
			for (final ScaAbstractProperty< ? > prop : struct.getFields()) {
				final IDialogSettings fieldSettings = structPropertySettings.getSection(prop.getId());
				ScaPropertyUtil.restoreProperty(prop, fieldSettings);
			}
		}
	}

	private static void restoreSimpleSequence(final ScaSimpleSequenceProperty prop, final IDialogSettings propertySettings) {
		if (propertySettings != null) {
			final String[] values = propertySettings.getArray(ScaPropertyUtil.SIMPLE_SEQ_VALUES);
			prop.getValues().clear();
			for (final String s : values) {
				prop.getValues().add(AnyUtils.convertString(s, prop.getDefinition().getType().getLiteral(), prop.getDefinition().isComplex()));
			}
		}
	}

	private static void restoreSimple(final ScaSimpleProperty prop, final IDialogSettings propertySettings) {
		if (propertySettings != null) {
			prop.setValue(AnyUtils.convertString(propertySettings.get(ScaPropertyUtil.SIMPLE_VALUE), prop.getDefinition().getType().getLiteral(),
				prop.getDefinition().isComplex()));
		}

	}
}

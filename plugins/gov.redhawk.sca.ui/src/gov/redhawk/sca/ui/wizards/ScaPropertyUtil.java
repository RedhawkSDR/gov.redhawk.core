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

import gov.redhawk.model.sca.ScaAbstractComponent;
import gov.redhawk.model.sca.ScaAbstractProperty;
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

	public static void load(final ScaAbstractComponent< ? > component, final IDialogSettings propSettings) {
		if (component != null && propSettings != null) {
			ScaPropertyUtil.restoreProperties(component, propSettings);
		}
	}

	public static void save(final ScaAbstractComponent< ? > component, final IDialogSettings propSettings) {
		if (component != null && propSettings != null) {
			ScaPropertyUtil.storeProperties(component, propSettings);
		}
	}

	private static void storeProperties(final ScaAbstractComponent< ? > component, final IDialogSettings propertySettings) {
		for (final ScaAbstractProperty< ? > prop : component.getProperties()) {
			if (prop.isDefaultValue()) {
				continue;
			}
			final IDialogSettings valueSection = propertySettings.addNewSection(prop.getId());
			if (prop instanceof ScaSimpleProperty) {
				ScaPropertyUtil.storeSimple((ScaSimpleProperty) prop, valueSection);
			} else if (prop instanceof ScaSimpleSequenceProperty) {
				ScaPropertyUtil.storeSimpleSequence((ScaSimpleSequenceProperty) prop, valueSection);
			} else if (prop instanceof ScaStructProperty) {
				ScaPropertyUtil.storeStruct((ScaStructProperty) prop, valueSection);
			} else if (prop instanceof ScaStructSequenceProperty) {
				ScaPropertyUtil.storeStructSequence((ScaStructSequenceProperty) prop, valueSection);
			}
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
		for (final ScaSimpleProperty prop : struct.getSimples()) {
			if (prop.isDefaultValue()) {
				continue;
			}
			final IDialogSettings simpleSection = valueSection.addNewSection(prop.getId());
			ScaPropertyUtil.storeSimple(prop, simpleSection);
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

	private static void restoreProperties(final ScaAbstractComponent< ? > component, final IDialogSettings propSettings) {
		for (final ScaAbstractProperty< ? > prop : component.getProperties()) {
			final IDialogSettings valueSection = propSettings.getSection(prop.getId());
			if (prop instanceof ScaSimpleProperty) {
				ScaPropertyUtil.restoreSimple((ScaSimpleProperty) prop, valueSection);
			} else if (prop instanceof ScaSimpleSequenceProperty) {
				ScaPropertyUtil.restoreSimpleSequence((ScaSimpleSequenceProperty) prop, valueSection);
			} else if (prop instanceof ScaStructProperty) {
				ScaPropertyUtil.restoreStruct((ScaStructProperty) prop, valueSection);
			} else if (prop instanceof ScaStructSequenceProperty) {
				ScaPropertyUtil.restoreStructSequence((ScaStructSequenceProperty) prop, valueSection);
			}
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
			for (final ScaSimpleProperty prop : struct.getSimples()) {
				final IDialogSettings simpleSettings = structPropertySettings.getSection(prop.getId());
				ScaPropertyUtil.restoreSimple(prop, simpleSettings);
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
			prop.setValue(AnyUtils.convertString(propertySettings.get(ScaPropertyUtil.SIMPLE_VALUE), prop.getDefinition().getType().getLiteral(), prop.getDefinition().isComplex()));
		}

	}
}

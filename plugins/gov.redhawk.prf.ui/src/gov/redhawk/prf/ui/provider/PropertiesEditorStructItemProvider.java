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
package gov.redhawk.prf.ui.provider;

import java.util.Collection;

import mil.jpeojtrs.sca.prf.AccessType;
import mil.jpeojtrs.sca.prf.ConfigurationKind;
import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.PropertyValueType;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.Struct;
import mil.jpeojtrs.sca.prf.StructPropertyConfigurationType;
import mil.jpeojtrs.sca.prf.provider.StructItemProvider;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * @since 1.2
 */
public class PropertiesEditorStructItemProvider extends StructItemProvider {

	public PropertiesEditorStructItemProvider(final AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	protected Object unwrap(Object object) {
		// Where appropriate, convert feature map entries for top-level properties; implemented here as the least
		// intrusive way of handling cross-feature map drag-and-drop
		return convertFeatureMapEntry(super.unwrap(object));
	}

	/**
	 * Convert top-level simples and simple sequences, which are feature map entries under "properties", to the
	 * corresponding "fields" entry
	 */
	private Object convertFeatureMapEntry(Object object) {
		if (object instanceof FeatureMap.Entry) {
			final FeatureMap.Entry entry = (FeatureMap.Entry) object;
			final EStructuralFeature feature = entry.getEStructuralFeature();
			if (feature == PrfPackage.Literals.PROPERTIES__SIMPLE) {
				return FeatureMapUtil.createEntry(PrfPackage.Literals.STRUCT__SIMPLE, entry.getValue());
			} else if (feature == PrfPackage.Literals.PROPERTIES__SIMPLE_SEQUENCE) {
				return FeatureMapUtil.createEntry(PrfPackage.Literals.STRUCT__SIMPLE_SEQUENCE, entry.getValue());
			}
		}
		return object;
	}

	/**
	 * Produces the add {@link Command} for adding a new struct property to an existing properties collection. It also
	 * handles adding a simple or simple sequence to a struct via delegation. This code extends the parent class to
	 * ensure the new struct has certain default values.
	 */
	@Override
	protected Command createAddCommand(final EditingDomain domain, final EObject owner, final EStructuralFeature feature, final Collection< ? > collection,
	        final int index) {
		if (feature == PrfPackage.Literals.PROPERTIES__STRUCT) {
			for (final Object object : collection) {
				final Struct struct = (Struct) object;
				configureDefaultStruct(struct);
			}
		}
		return super.createAddCommand(domain, owner, feature, collection, index);
	}

	@Override
	protected Command createCreateChildCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Object value, int index,
	        Collection< ? > collection) {
		if (feature == PrfPackage.Literals.STRUCT__FIELDS) {
			FeatureMap.Entry entry = (FeatureMap.Entry) value;
			if (entry.getEStructuralFeature() == PrfPackage.Literals.STRUCT__SIMPLE) {
				Simple simple = (Simple) entry.getValue();
				simple.setType(PropertyValueType.STRING);
			} else if (entry.getEStructuralFeature() == PrfPackage.Literals.STRUCT__SIMPLE_SEQUENCE) {
				SimpleSequence sequence = (SimpleSequence) entry.getValue();
				sequence.setType(PropertyValueType.STRING);
			}
		}
		return super.createCreateChildCommand(domain, owner, feature, value, index, collection);
	}

	/**
	 * Configure the struct before setting it to the sequence. {@inheritDoc}
	 */
	@Override
	protected Command createSetCommand(final EditingDomain domain, final EObject owner, final EStructuralFeature feature, final Object value, final int index) {
		if (feature == PrfPackage.Literals.STRUCT_SEQUENCE__STRUCT) {
			Struct struct = (Struct) value;
			configureDefaultStruct(struct);
			return super.createSetCommand(domain, owner, feature, struct, index);
		}
		return super.createSetCommand(domain, owner, feature, value, index);
	}

	/**
	 * Set default values for a new struct property, including adding a new simple member.
	 * @param struct The struct to be modified
	 */
	private static void configureDefaultStruct(final Struct struct) {
		struct.setMode(AccessType.READWRITE);
		final ConfigurationKind configurationKind = PrfFactory.eINSTANCE.createConfigurationKind();
		configurationKind.setType(StructPropertyConfigurationType.PROPERTY);
		struct.getConfigurationKind().clear();
		struct.getConfigurationKind().add(configurationKind);
		final Simple simple = PrfFactory.eINSTANCE.createSimple();
		simple.setType(PropertyValueType.STRING);
		struct.getSimple().add(simple);
	}
}

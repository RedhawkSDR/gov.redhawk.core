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
import java.util.Collections;

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
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * @since 1.2
 */
public class PropertiesEditorStructItemProvider extends StructItemProvider {

	public PropertiesEditorStructItemProvider(final AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	protected Object createWrapper(EObject object, EStructuralFeature feature, Object value, int index) {
		if (feature == PrfPackage.Literals.STRUCT__FIELDS) {
			// Use the contained type (e.g., Simple) in the feature map, rather than the feature map, to create the
			// wrapper; this removes the extra tag in the ID (e.g., "<simple>") and lets the editors work directly
			// with the contained value
			FeatureMap.Entry entry = (FeatureMap.Entry)value;
			value = entry.getValue();
			feature = entry.getEStructuralFeature();
		}
		return super.createWrapper(object, feature, value, index);
	}

	/**
	 * Create a simple with default values and add it, or add the struct depending on which feature we get.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	protected Command createAddCommand(final EditingDomain domain, final EObject owner, final EStructuralFeature feature, final Collection< ? > collection,
	        final int index) {
		if (feature == PrfPackage.Literals.PROPERTIES__STRUCT) {
			//Add the struct
			final Struct struct = (Struct) collection.toArray()[0];
			PropertiesEditorStructItemProvider.configureDefaultStruct(struct);
			return super.createAddCommand(domain, owner, feature, Collections.singleton(struct), index);
		}
		return super.createAddCommand(domain, owner, feature, collection, index);
	}

	@Override
	protected Command createCreateChildCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Object value, int index,
	        Collection< ? > collection) {
		if (feature == PrfPackage.Literals.STRUCT__FIELDS) {
			FeatureMap.Entry entry = (FeatureMap.Entry)value;
			if (entry.getEStructuralFeature() == PrfPackage.Literals.STRUCT__SIMPLE) {
				Simple simple = (Simple)entry.getValue();
				simple.setType(PropertyValueType.STRING);
			} else if (entry.getEStructuralFeature() == PrfPackage.Literals.STRUCT__SIMPLE_SEQUENCE) {
				SimpleSequence sequence = (SimpleSequence)entry.getValue();
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
			return super.createSetCommand(domain, owner, feature, PropertiesEditorStructItemProvider.configureDefaultStruct((Struct) value), index);
		}
		return super.createSetCommand(domain, owner, feature, value, index);
	}

	/**
	 * @since 2.0
	 */
	protected static Struct configureDefaultStruct(final Struct struct) {
		struct.setMode(AccessType.READWRITE);
		final ConfigurationKind configurationKind = PrfFactory.eINSTANCE.createConfigurationKind();
		configurationKind.setType(StructPropertyConfigurationType.CONFIGURE);
		struct.getConfigurationKind().clear();
		struct.getConfigurationKind().add(configurationKind);
		final Simple simple = PrfFactory.eINSTANCE.createSimple();
		simple.setType(PropertyValueType.STRING);
		struct.getSimple().add(simple);
		return struct;
	}

	/**
	 * @since 2.0
	 */
	protected static Struct configureStructChild(final Struct struct) {
		final Simple simple = PrfFactory.eINSTANCE.createSimple();
		simple.setType(PropertyValueType.STRING);
		struct.getSimple().add(simple);
		return struct;
	}
}

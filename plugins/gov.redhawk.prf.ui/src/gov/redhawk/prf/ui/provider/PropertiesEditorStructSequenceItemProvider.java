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
import mil.jpeojtrs.sca.prf.Struct;
import mil.jpeojtrs.sca.prf.StructPropertyConfigurationType;
import mil.jpeojtrs.sca.prf.StructSequence;
import mil.jpeojtrs.sca.prf.provider.StructSequenceItemProvider;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * @since 1.2
 */
public class PropertiesEditorStructSequenceItemProvider extends StructSequenceItemProvider {

	public PropertiesEditorStructSequenceItemProvider(final AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * Produces the add {@link Command} for adding a new struct sequence property to an existing properties collection.
	 * This code extends the parent class to ensure the new struct sequence has certain default values.
	 */
	@Override
	protected Command createAddCommand(final EditingDomain domain, final EObject owner, final EStructuralFeature feature, final Collection< ? > collection,
		final int index) {
		if (feature == PrfPackage.Literals.PROPERTIES__STRUCT_SEQUENCE) {
			for (Object obj : collection) {
				final StructSequence sequence = (StructSequence) obj;
				configureDefaultStructSequence(sequence);
			}
		}
		return super.createAddCommand(domain, owner, feature, collection, index);
	}

	@Override
	protected void collectNewChildDescriptors(final Collection<Object> newChildDescriptors, final Object object) {
		newChildDescriptors.add(createChildParameter(PrfPackage.Literals.STRUCT_SEQUENCE__STRUCT, PrfFactory.eINSTANCE.createStruct()));
	}

	@Override
	public Collection< ? extends EStructuralFeature> getChildrenFeatures(final Object object) {
		super.getChildrenFeatures(object);
		this.childrenFeatures.remove(PrfPackage.Literals.STRUCT_SEQUENCE__STRUCT_VALUE);
		this.childrenFeatures.remove(PrfPackage.Literals.STRUCT_SEQUENCE__CONFIGURATION_KIND);
		return this.childrenFeatures;
	}

	/**
	 * Set default values for a new struct sequence property.
	 * @param structseq The struct sequence to be modified
	 */
	private void configureDefaultStructSequence(StructSequence structseq) {
		structseq.setMode(AccessType.READWRITE);
		final ConfigurationKind configurationKind = PrfFactory.eINSTANCE.createConfigurationKind();
		configurationKind.setType(StructPropertyConfigurationType.PROPERTY);
		structseq.getConfigurationKind().clear();
		structseq.getConfigurationKind().add(configurationKind);
		final Struct struct = PrfFactory.eINSTANCE.createStruct();
		structseq.setStruct(struct);
		final Simple simple = PrfFactory.eINSTANCE.createSimple();
		simple.setType(PropertyValueType.STRING);
		struct.getSimple().add(simple);
	}
}

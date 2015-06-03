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
import mil.jpeojtrs.sca.prf.Action;
import mil.jpeojtrs.sca.prf.ActionType;
import mil.jpeojtrs.sca.prf.Kind;
import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.PropertyConfigurationType;
import mil.jpeojtrs.sca.prf.PropertyValueType;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.provider.SimpleSequenceItemProvider;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * @since 1.2
 */
public class PropertiesEditorSimpleSequenceItemProvider extends SimpleSequenceItemProvider {

	public PropertiesEditorSimpleSequenceItemProvider(final AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * Produces the add {@link Command} for adding a new simple sequence property to an existing properties collection
	 * or struct. This code extends the parent class code to ensure the new simple sequence has certain default values.
	 */
	@Override
	protected Command createAddCommand(final EditingDomain domain, final EObject owner, final EStructuralFeature feature, final Collection< ? > collection,
		final int index) {
		if (feature == PrfPackage.Literals.PROPERTIES__SIMPLE_SEQUENCE) {
			for (Object obj : collection) {
				final SimpleSequence ss = (SimpleSequence) obj;
				configureDefaultSimpleSequence(ss);
			}
		} else if (feature == PrfPackage.Literals.STRUCT__SIMPLE_SEQUENCE) {
			for (Object obj : collection) {
				final SimpleSequence ss = (SimpleSequence) obj;
				configureDefaultSimpleSequenceInStruct(ss);
			}
		}
		return super.createAddCommand(domain, owner, feature, collection, index);
	}

	/**
	 * Set default values for a new simple sequence property.
	 * @param ss The simple sequence to be modified
	 */
	private void configureDefaultSimpleSequence(SimpleSequence ss) {
		ss.setType(PropertyValueType.STRING);
		ss.setMode(AccessType.READWRITE);
		final Kind kind = PrfFactory.eINSTANCE.createKind();
		kind.setType(PropertyConfigurationType.PROPERTY);
		ss.getKind().add(kind);
		final Action action = PrfFactory.eINSTANCE.createAction();
		action.setType(ActionType.EXTERNAL);
		ss.setAction(action);
	}

	/**
	 * Set default values for a new simple sequence member of a struct property.
	 * @param ss The simple sequence to be modified
	 */
	private void configureDefaultSimpleSequenceInStruct(SimpleSequence ss) {
		ss.setType(PropertyValueType.STRING);
	}
}

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
	 * {@inheritDoc}
	 */
	@Override
	protected Command createAddCommand(final EditingDomain domain, final EObject owner, final EStructuralFeature feature, final Collection< ? > collection,
	        final int index) {
		if (feature == PrfPackage.Literals.PROPERTIES__SIMPLE_SEQUENCE || feature == PrfPackage.Literals.STRUCT__SIMPLE_SEQUENCE) {
			final SimpleSequence sequence = (SimpleSequence) collection.toArray()[0];
			if (feature == PrfPackage.Literals.PROPERTIES__SIMPLE_SEQUENCE) {
				sequence.setType(PropertyValueType.STRING);
				sequence.setMode(AccessType.READWRITE);
				final Kind kind = PrfFactory.eINSTANCE.createKind();
				kind.setType(PropertyConfigurationType.CONFIGURE);
				sequence.getKind().add(kind);
				final Action action = PrfFactory.eINSTANCE.createAction();
				action.setType(ActionType.EXTERNAL);
				sequence.setAction(action);
			}
			return super.createAddCommand(domain, owner, feature, Collections.singleton(sequence), index);
		}
		return super.createAddCommand(domain, owner, feature, collection, index);
	}
	
}

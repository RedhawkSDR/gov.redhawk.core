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
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.provider.SimpleItemProvider;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;

/**
 * @since 1.2
 */
public class PropertiesEditorSimpleItemProvider extends SimpleItemProvider {

	public PropertiesEditorSimpleItemProvider(final AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * Produces the add {@link Command} for adding a new simple property to an existing properties collection or struct.
	 * This code extends the parent class to ensure the new simple has certain default values.
	 */
	@Override
	protected Command createAddCommand(final EditingDomain domain, final EObject owner, final EStructuralFeature feature, final Collection< ? > collection,
		final int index) {
		if (feature == PrfPackage.Literals.PROPERTIES__SIMPLE) {
			for (Object obj : collection) {
				Simple simple = (Simple) obj;
				configureDefaultSimple(simple);
			}
		} else if (feature == PrfPackage.Literals.STRUCT__SIMPLE) {
			for (Object obj : collection) {
				Simple simple = (Simple) obj;
				configureDefaultSimpleInStruct(simple);
			}
		}

		return super.createAddCommand(domain, owner, feature, collection, index);
	}

	/**
	 * Set default values for a new simple property.
	 * @param simple The simple to be modified
	 */
	private void configureDefaultSimple(Simple simple) {
		simple.setType(PropertyValueType.STRING);
		simple.setMode(AccessType.READWRITE);
		final Kind kind = PrfFactory.eINSTANCE.createKind();
		kind.setType(PropertyConfigurationType.PROPERTY);
		simple.getKind().add(kind);
		final Action action = PrfFactory.eINSTANCE.createAction();
		action.setType(ActionType.EXTERNAL);
		simple.setAction(action);
	}

	/**
	 * Set default values for a new simple member of a struct property.
	 * @param simple The simple to be modified
	 */
	private void configureDefaultSimpleInStruct(Simple simple) {
		simple.setType(PropertyValueType.STRING);
	}
}

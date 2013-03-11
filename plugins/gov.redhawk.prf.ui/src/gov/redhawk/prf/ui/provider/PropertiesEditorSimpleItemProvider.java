/**
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.prf.ui.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
import org.eclipse.emf.common.command.CompoundCommand;
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
	 * {@inheritDoc}
	 */
	@Override
	protected Command createAddCommand(final EditingDomain domain, final EObject owner, final EStructuralFeature feature, final Collection< ? > collection,
	        final int index) {
		final CompoundCommand command = new CompoundCommand();
		if (feature == PrfPackage.Literals.PROPERTIES__SIMPLE || feature == PrfPackage.Literals.STRUCT__SIMPLE) {
			List<Simple> additions = new ArrayList<Simple>();
			for (Object obj : collection) {
				//This might be an unnecessary instanceof check . . .
				if (obj instanceof Simple) {
					Simple simple = (Simple) obj;
					//Don't modify an existing simple
					if (feature == PrfPackage.Literals.PROPERTIES__SIMPLE) {
						simple.setType(PropertyValueType.STRING);
						simple.setMode(AccessType.READWRITE);
						final Kind kind = PrfFactory.eINSTANCE.createKind();
						kind.setType(PropertyConfigurationType.CONFIGURE);
						simple.getKind().add(kind);
						final Action action = PrfFactory.eINSTANCE.createAction();
						action.setType(ActionType.EXTERNAL);
						simple.setAction(action);
					}
					additions.add(simple);
				}
			}

			command.append(super.createAddCommand(domain, owner, feature, additions, index));
			return command;
		}

		return super.createAddCommand(domain, owner, feature, collection, index);
	}

	/**
	 * @since 2.0
	 */
	protected static Simple configureDefaultSimple(Simple simple) {
		simple.setType(PropertyValueType.STRING);
		simple.setMode(AccessType.READWRITE);
		final Kind kind = PrfFactory.eINSTANCE.createKind();
		kind.setType(PropertyConfigurationType.CONFIGURE);
		simple.getKind().add(kind);
		final Action action = PrfFactory.eINSTANCE.createAction();
		action.setType(ActionType.EXTERNAL);
		simple.setAction(action);
		return simple;
	}
}

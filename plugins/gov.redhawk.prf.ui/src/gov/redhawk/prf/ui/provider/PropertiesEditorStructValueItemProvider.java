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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleRef;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.StructSequence;
import mil.jpeojtrs.sca.prf.StructValue;
import mil.jpeojtrs.sca.prf.Values;
import mil.jpeojtrs.sca.prf.impl.SimpleSequenceRefImpl;
import mil.jpeojtrs.sca.prf.provider.StructValueItemProvider;
import mil.jpeojtrs.sca.prf.util.PropertiesUtil;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

/**
 * @since 1.2
 */
public class PropertiesEditorStructValueItemProvider extends StructValueItemProvider {

	public PropertiesEditorStructValueItemProvider(final AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	protected Command createAddCommand(final EditingDomain domain, final EObject owner, final EStructuralFeature feature, final Collection< ? > collection,
	        final int index) {
		if (feature == PrfPackage.Literals.STRUCT_SEQUENCE__STRUCT_VALUE) {
			final StructValue structValue = (StructValue) collection.toArray()[0];
			final StructSequence sequence = (StructSequence) owner;
			for (final Simple simple : sequence.getStruct().getSimple()) {
				final SimpleRef ref = PrfFactory.eINSTANCE.createSimpleRef();
				ref.setRefID(simple.getId());
				ref.setValue(PropertiesUtil.getDefaultValue(simple));
				structValue.getSimpleRef().add(ref);
			}
			for (final SimpleSequence simples : sequence.getStruct().getSimpleSequence()) {
				final SimpleSequenceRefImpl ref = (SimpleSequenceRefImpl) PrfFactory.eINSTANCE.createSimpleSequenceRef();
				ref.setRefID(simples.getId());
				Values values = PrfFactory.eINSTANCE.createValues();
				values.getValue().addAll(PropertiesUtil.getDefaultValues(simples).getValue());
				if (values.getValue().isEmpty()) {
					List<String> defaults = new ArrayList<String>();
					defaults.add(PropertiesUtil.getDefaultValue(simples.getType()));
					((TransactionalEditingDomain) domain).getCommandStack().execute(SetCommand.create(domain, 
						values, PrfPackage.Literals.VALUES__VALUE, defaults));
//					values.getValue().add(PropertiesUtil.getDefaultValue(simples.getType()));
				}
				((TransactionalEditingDomain) domain).getCommandStack().execute(SetCommand.create(domain, 
					ref, PrfPackage.Literals.SIMPLE_SEQUENCE_REF__VALUES, values));
				structValue.getSimpleSequenceRef().add(ref);
			}
			return super.createAddCommand(domain, owner, feature, Collections.singleton(structValue), index);
		}
		return super.createAddCommand(domain, owner, feature, collection, index);
	}
	
	@Override
	public Collection< ? extends EStructuralFeature> getChildrenFeatures(Object object) {
		super.getChildrenFeatures(object);
		childrenFeatures.remove(PrfPackage.Literals.STRUCT_VALUE__REFS);
		return childrenFeatures;
	}


}

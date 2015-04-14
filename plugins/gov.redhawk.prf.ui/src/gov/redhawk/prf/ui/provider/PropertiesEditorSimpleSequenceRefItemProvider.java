/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.prf.ui.provider;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ViewerNotification;

import mil.jpeojtrs.sca.prf.AbstractPropertyRef;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.SimpleSequenceRef;
import mil.jpeojtrs.sca.prf.Struct;
import mil.jpeojtrs.sca.prf.StructSequence;
import mil.jpeojtrs.sca.prf.provider.SimpleSequenceRefItemProvider;

/**
 * 
 */
public class PropertiesEditorSimpleSequenceRefItemProvider extends SimpleSequenceRefItemProvider {

	/**
	 * @param adapterFactory
	 */
	public PropertiesEditorSimpleSequenceRefItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Command createAddCommand(final EditingDomain domain, final EObject owner, final EStructuralFeature feature, final Collection< ? > collection,
	        final int index) {
		if (feature == PrfPackage.Literals.STRUCT_VALUE__SIMPLE_SEQUENCE_REF) {
			final SimpleSequenceRef simpleSequenceRef = (SimpleSequenceRef) collection.toArray()[0];
			return super.createAddCommand(domain, owner, feature, Collections.singleton(simpleSequenceRef), index);
		}
		return super.createAddCommand(domain, owner, feature, collection, index);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getText(final Object object) {
		final SimpleSequenceRef simpleRef = (SimpleSequenceRef) object;
		final SimpleSequence simpSeq = this.findSimpleSequence(simpleRef);
		String label = simpleRef.getRefID();
		if (simpSeq != null && simpSeq.getName() != null && !simpSeq.getName().isEmpty()) {
			label = simpSeq.getName();
		}
		return label;
	}

	/**
	 * Returns the {@link Simple} referenced by the {@link SimpleRef}.
	 * 
	 * @param ref
	 *            the reference object
	 * @return the simple referenced
	 */
	private SimpleSequence findSimpleSequence(final SimpleSequenceRef ref) {
		if (ref.eContainer().eContainer() instanceof StructSequence) {
			final StructSequence structSeq = (StructSequence) ref.eContainer().eContainer();
			final Struct struct = structSeq.getStruct();
			if (struct != null) {
				for (final SimpleSequence simpSeq : struct.getSimpleSequence()) {
					if (simpSeq.getId() != null) {
						if (simpSeq.getId().equals(ref.getRefID())) {
							return simpSeq;
						}
					}
				}
			}
		}
		return null;
	}

	/**
	 * Make sure to update all parent labels appropriately.
	 */
	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);
		for (EObject container = ((EObject) notification.getNotifier()).eContainer(); container != null; container = container.eContainer()) {
			fireNotifyChanged(new ViewerNotification(notification, container, false, true));
		}
	}
	
	@Override
	protected String getValueText(AbstractPropertyRef< ? > object) {
		final SimpleSequenceRef simpleRef = (SimpleSequenceRef) object;
		return simpleRef.getValues().getValue().toString();
//		final SimpleSequence simpSeq = this.findSimpleSequence(simpleRef);
//		return super.getValueText(object);
	}
}

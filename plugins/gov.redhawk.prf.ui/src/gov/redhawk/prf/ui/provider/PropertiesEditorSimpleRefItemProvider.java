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

import java.util.Collection;
import java.util.Collections;

import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleRef;
import mil.jpeojtrs.sca.prf.Struct;
import mil.jpeojtrs.sca.prf.StructSequence;
import mil.jpeojtrs.sca.prf.provider.SimpleRefItemProvider;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * @since 1.2
 */
public class PropertiesEditorSimpleRefItemProvider extends SimpleRefItemProvider {

	public PropertiesEditorSimpleRefItemProvider(final AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Command createAddCommand(final EditingDomain domain, final EObject owner, final EStructuralFeature feature, final Collection< ? > collection,
	        final int index) {
		if (feature == PrfPackage.Literals.STRUCT_VALUE__SIMPLE_REF) {
			final SimpleRef simpleRef = (SimpleRef) collection.toArray()[0];
			return super.createAddCommand(domain, owner, feature, Collections.singleton(simpleRef), index);
		}
		return super.createAddCommand(domain, owner, feature, collection, index);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getText(final Object object) {
		final SimpleRef simpleRef = (SimpleRef) object;
		final Simple simple = this.findSimple(simpleRef);
		String label = simpleRef.getRefID();
		if (simple != null && simple.getName() != null && !simple.getName().equals("")) {
			label = simple.getName();
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
	private Simple findSimple(final SimpleRef ref) {
		if (ref.eContainer().eContainer() instanceof StructSequence) {
			final StructSequence structSeq = (StructSequence) ref.eContainer().eContainer();
			final Struct struct = structSeq.getStruct();
			if (struct != null) {
				for (final Simple simple : struct.getSimple()) {
					if (simple.getId() != null) {
						if (simple.getId().equals(ref.getRefID())) {
							return simple;
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
}

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
package gov.redhawk.ui.views.domainbrowser.extend;

import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.provider.ScaComponentItemProvider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ViewerNotification;

public class ScaComponentItemProviderExtended extends ScaComponentItemProvider {

	private ScaPropertyContainerItemProviderExtended propertyItemProvider;

	public ScaComponentItemProviderExtended(final AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public Collection< ? > getChildren(final Object object) {
		final Collection< ? > superChildren = super.getChildren(object);

		if (superChildren != null && superChildren instanceof List< ? > && object instanceof ScaComponent) {
			final Set<Object> set = new HashSet<Object>(superChildren);
			final ScaComponent comp = (ScaComponent) object;

			if (!comp.isDisposed()) {
				set.add(getPropertyContainterItemProvider());
				return set;
			}
		}

		return superChildren;
	}

	@Override
	public Collection< ? extends EStructuralFeature> getChildrenFeatures(final Object object) {
		final Collection< ? extends EStructuralFeature> superFeatures = super.getChildrenFeatures(object);

		if (superFeatures != null && superFeatures instanceof List< ? >) {
			final Set<EStructuralFeature> set = new HashSet<EStructuralFeature>(superFeatures);
			set.add(ScaPackage.Literals.SCA_COMPONENT__NAME);
			set.add(ScaPackage.Literals.SCA_COMPONENT__INSTANTIATION_IDENTIFIER);
			set.add(ScaPackage.Literals.SCA_COMPONENT__COMPONENT_INSTANTIATION);

			return new ArrayList<EStructuralFeature>(set);
		}

		return superFeatures;
	}

	@Override
	public void notifyChanged(final Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(ScaComponent.class)) {
		case ScaPackage.SCA_COMPONENT__COMPONENT_INSTANTIATION:
		case ScaPackage.SCA_PROPERTY_CONTAINER__PROPERTIES:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
			return;
		}
		super.notifyChanged(notification);
	}

	@Override
	public void dispose() {
		super.dispose();

		if (this.propertyItemProvider != null) {
			this.propertyItemProvider.dispose();
			this.propertyItemProvider = null;
		}
	}

	private ScaPropertyContainerItemProviderExtended getPropertyContainterItemProvider() {
		if (this.propertyItemProvider == null) {
			this.propertyItemProvider = new ScaPropertyContainerItemProviderExtended(this.adapterFactory, (EObject) getTarget());
		}

		return this.propertyItemProvider;
	}
}

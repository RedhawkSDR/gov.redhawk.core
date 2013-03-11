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
package gov.redhawk.sca.ui.properties;

import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaPropertyContainer;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaSimpleSequenceProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.ScaStructSequenceProperty;
import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;
import gov.redhawk.model.sca.provider.ScaPropertyContainerItemProvider;
import gov.redhawk.model.sca.provider.ScaSimplePropertyItemProvider;
import gov.redhawk.model.sca.provider.ScaSimpleSequencePropertyItemProvider;
import gov.redhawk.model.sca.provider.ScaStructPropertyItemProvider;
import gov.redhawk.model.sca.provider.ScaStructSequencePropertyItemProvider;
import gov.redhawk.model.sca.util.ScaSwitch;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * @since 9.0
 */
public class ScaPropertiesAdapterFactory extends ScaItemProviderAdapterFactory {

	private static class ScaPropertyContainerValueItemProvider extends ScaPropertyContainerItemProvider {

		public ScaPropertyContainerValueItemProvider(final AdapterFactory adapterFactory) {
			super(adapterFactory);
		}

		@Override
		protected Collection< ? extends EStructuralFeature> getChildrenFeatures(final Object object) {
			if (this.childrenFeatures == null) {
				this.childrenFeatures = new ArrayList<EStructuralFeature>();
				this.childrenFeatures.add(ScaPackage.Literals.SCA_PROPERTY_CONTAINER__PROPERTIES);
			}
			return this.childrenFeatures;
		}

		@Override
		public void notifyChanged(final Notification notification) {
			updateChildren(notification);

			switch (notification.getFeatureID(ScaPropertyContainer.class)) {
			case ScaPackage.SCA_PROPERTY_CONTAINER__PROPERTIES:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
			default:
				break;
			}
			super.notifyChanged(notification);
		}

	}

	/**
	 * @since 9.0
	 */
	public ScaPropertiesAdapterFactory() {
		this.modelSwitch = new ScaSwitch<Adapter>() {
			@Override
			public Adapter caseScaSimpleProperty(final ScaSimpleProperty object) {
				return createScaSimplePropertyAdapter();
			}

			@Override
			public Adapter caseScaSimpleSequenceProperty(final ScaSimpleSequenceProperty object) {
				return createScaSimpleSequencePropertyAdapter();
			}

			@Override
			public Adapter caseScaStructProperty(final ScaStructProperty object) {
				return createScaStructPropertyAdapter();
			}

			@Override
			public Adapter caseScaStructSequenceProperty(final ScaStructSequenceProperty object) {
				return createScaStructSequencePropertyAdapter();
			}

			@Override
			public < P extends org.omg.CORBA.Object, E > Adapter caseScaPropertyContainer(final ScaPropertyContainer<P, E> object) {
				return createScaPropertyContainerAdapter();
			}

			@Override
			public Adapter defaultCase(final EObject object) {
				return createEObjectAdapter();
			}
		};
	}

	@Override
	public Adapter createScaSimplePropertyAdapter() {
		return new ScaSimplePropertyItemProvider(this);
	}

	@Override
	public Adapter createScaSimpleSequencePropertyAdapter() {
		return new ScaSimpleSequencePropertyItemProvider(this) {
			@Override
			public Collection< ? > getChildren(final Object object) {
				return Collections.emptyList();
			}

			@Override
			public boolean hasChildren(final Object object) {
				return false;
			}

			@Override
			public void notifyChanged(final Notification notification) {
				updateChildren(notification);

				switch (notification.getFeatureID(ScaSimpleSequenceProperty.class)) {
				case ScaPackage.SCA_SIMPLE_SEQUENCE_PROPERTY__VALUES:
					fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
					return;
				default:
					break;
				}
				super.notifyChanged(notification);
			}
		};
	}

	@Override
	public Adapter createScaStructPropertyAdapter() {
		return new ScaStructPropertyItemProvider(this);
	}

	@Override
	public Adapter createScaStructSequencePropertyAdapter() {
		return new ScaStructSequencePropertyItemProvider(this) {
			@Override
			public void notifyChanged(final Notification notification) {
				updateChildren(notification);

				switch (notification.getFeatureID(ScaStructSequenceProperty.class)) {
				case ScaPackage.SCA_STRUCT_SEQUENCE_PROPERTY__STRUCTS:
					fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, true));
					return;
				default:
					break;
				}
				super.notifyChanged(notification);
			}
		};
	}

	@Override
	public Adapter createScaPropertyContainerAdapter() {
		return new ScaPropertyContainerValueItemProvider(this);
	}
}

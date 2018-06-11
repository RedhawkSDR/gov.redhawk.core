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
package gov.redhawk.sca.ui.properties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemColorProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITableItemColorProvider;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

import gov.redhawk.model.sca.ScaNegotiatedConnection;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaPropertyContainer;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaSimpleSequenceProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.ScaStructSequenceProperty;
import gov.redhawk.model.sca.ScaTransport;
import gov.redhawk.model.sca.provider.ScaEditPlugin;
import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;
import gov.redhawk.model.sca.provider.ScaSimplePropertyItemProvider;
import gov.redhawk.model.sca.provider.ScaSimpleSequencePropertyItemProvider;
import gov.redhawk.model.sca.provider.ScaStructPropertyItemProvider;
import gov.redhawk.model.sca.provider.ScaStructSequencePropertyItemProvider;
import gov.redhawk.model.sca.util.ScaSwitch;

/**
 * @since 9.0
 */
public class ScaPropertiesAdapterFactory extends ScaItemProviderAdapterFactory {

	/**
	 * An item provider that only provides children, and only for one specific feature.
	 */
	private static class ScaSingleChildFeatureItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider,
			ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource, ITableItemLabelProvider, ITableItemColorProvider, IItemColorProvider {

		private EStructuralFeature propertyFeature;

		public ScaSingleChildFeatureItemProvider(final AdapterFactory adapterFactory, EStructuralFeature propertyFeature) {
			super(adapterFactory);
			this.propertyFeature = propertyFeature;
		}

		@Override
		protected Collection< ? extends EStructuralFeature> getChildrenFeatures(final Object object) {
			if (this.childrenFeatures == null) {
				this.childrenFeatures = new ArrayList<EStructuralFeature>();
				this.childrenFeatures.add(this.propertyFeature);
			}
			return this.childrenFeatures;
		}

		@Override
		public void notifyChanged(final Notification notification) {
			updateChildren(notification);
			if (notification.getFeature() == this.propertyFeature) {
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
			}
		}

		@Override
		public ResourceLocator getResourceLocator() {
			return ScaEditPlugin.INSTANCE;
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
			public Adapter caseScaNegotiatedConnection(ScaNegotiatedConnection object) {
				return createScaNegotiatedConnectionAdapter();
			}

			@Override
			public Adapter caseScaTransport(ScaTransport object) {
				return createScaTransportAdapter();
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
				// Don't show the simple sequence's values as children
				return Collections.emptyList();
			}

			@Override
			public boolean hasChildren(final Object object) {
				// Don't show the simple sequence's values as children
				return false;
			}

			@Override
			public void notifyChanged(final Notification notification) {
				updateChildren(notification);

				switch (notification.getFeatureID(ScaSimpleSequenceProperty.class)) {
				case ScaPackage.SCA_SIMPLE_SEQUENCE_PROPERTY__VALUES:
					// Report changes to values as a label change for the simple sequence, not a content change
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
					// TODO: Why does this override parent behavior? We're adding a label update here
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
		return new ScaSingleChildFeatureItemProvider(this, ScaPackage.Literals.SCA_PROPERTY_CONTAINER__PROPERTIES);
	}

	@Override
	public Adapter createScaNegotiatedConnectionAdapter() {
		return new ScaSingleChildFeatureItemProvider(this, ScaPackage.Literals.SCA_NEGOTIATED_CONNECTION__TRANSPORT_INFO);
	}

	@Override
	public Adapter createScaTransportAdapter() {
		return new ScaSingleChildFeatureItemProvider(this, ScaPackage.Literals.SCA_TRANSPORT__TRANSPORT_PROPERTIES);
	}
}

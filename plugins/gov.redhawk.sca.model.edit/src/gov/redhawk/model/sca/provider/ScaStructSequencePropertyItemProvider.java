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

// BEGIN GENERATED CODE
package gov.redhawk.model.sca.provider;

import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaStructSequenceProperty;
import gov.redhawk.model.sca.commands.ScaModelCommand;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemColorProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITableItemColorProvider;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link gov.redhawk.model.sca.ScaStructSequenceProperty} object.
 * <!-- begin-user-doc -->
 * @since 9.0
 * <!-- end-user-doc -->
 * @generated
 */
public class ScaStructSequencePropertyItemProvider extends ScaAbstractPropertyItemProvider implements IEditingDomainItemProvider,
		IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource, ITableItemLabelProvider, ITableItemColorProvider,
		IItemColorProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaStructSequencePropertyItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addStructsPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Structs feature.
	 * <!-- begin-user-doc -->
	 * @since 12.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addStructsPropertyDescriptorGen(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_ScaStructSequenceProperty_structs_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ScaStructSequenceProperty_structs_feature", "_UI_ScaStructSequenceProperty_type"),
			ScaPackage.Literals.SCA_STRUCT_SEQUENCE_PROPERTY__STRUCTS, true, false, false, null, null, null));
	}

	/**
	 * This adds a property descriptor for the Simples feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected void addStructsPropertyDescriptor(Object object) {
		ItemPropertyDescriptor propertyDescriptor = createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(),
			getResourceLocator(), getString("_UI_ScaStructSequenceProperty_structs_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_ScaStructSequenceProperty_structs_feature", "_UI_ScaStructSequenceProperty_type"),
			ScaPackage.Literals.SCA_STRUCT_SEQUENCE_PROPERTY__STRUCTS, true, false, false, null, null, null);

		itemPropertyDescriptors.add(new ScaStructSequenceValuePropertyDescriptor(object, propertyDescriptor, getRootAdapterFactory()));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection< ? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(ScaPackage.Literals.SCA_STRUCT_SEQUENCE_PROPERTY__STRUCTS);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getText(Object object) {
		// END GENERATED CODE
		String label = ((ScaStructSequenceProperty) object).getName();
		if (label == null) {
			label = ((ScaStructSequenceProperty) object).getId();
		}
		return label;
		// BEGIN GENERATED CODE
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(ScaStructSequenceProperty.class)) {
		case ScaPackage.SCA_STRUCT_SEQUENCE_PROPERTY__STRUCTS:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
			return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

	@Override
	protected Command createSetCommand(EditingDomain domain, final EObject owner, EStructuralFeature feature, final Object value) {
		if (feature == ScaPackage.Literals.SCA_STRUCT_SEQUENCE_PROPERTY__STRUCTS) {
			return new ScaModelCommand() {

				@Override
				public void execute() {
					ScaStructSequenceProperty prop = (ScaStructSequenceProperty) owner;
					prop.eSet(ScaPackage.Literals.SCA_STRUCT_SEQUENCE_PROPERTY__STRUCTS, value);
				}
			};
		} else {
			return super.createSetCommand(domain, owner, feature, value);
		}
	}

	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getPrfResourceLocator().getImage("full/obj16/StructSequence"));
	}

	@Override
	protected String getValueText(ScaAbstractProperty< ? > object) {
		ScaStructSequenceProperty ss = (ScaStructSequenceProperty) object;
		if (ss.getStructs().isEmpty()) {
			return "[]";
		} else {
			return "[" + ((ScaStructSequenceProperty) object).getStructs().size() + "]";
		}
	}
}

/**
 */
package gov.redhawk.frontend.provider;

import gov.redhawk.frontend.FrontendPackage;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.UnallocatedTunerContainer;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemColorProvider;
import org.eclipse.emf.edit.provider.IItemFontProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITableItemColorProvider;
import org.eclipse.emf.edit.provider.ITableItemFontProvider;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link gov.redhawk.frontend.UnallocatedTunerContainer} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class UnallocatedTunerContainerItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource, ITableItemLabelProvider, ITableItemColorProvider, ITableItemFontProvider,
		IItemColorProvider, IItemFontProvider {
	/**
	* This constructs an instance from a factory and a notifier.
	* <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	* @generated
	*/
	public UnallocatedTunerContainerItemProvider(AdapterFactory adapterFactory) {
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

			addTunerContainerPropertyDescriptor(object);
			addTunerTypePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	* This adds a property descriptor for the Tuner Container feature.
	* <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	* @generated
	*/
	protected void addTunerContainerPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_UnallocatedTunerContainer_tunerContainer_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_UnallocatedTunerContainer_tunerContainer_feature", "_UI_UnallocatedTunerContainer_type"),
			FrontendPackage.Literals.UNALLOCATED_TUNER_CONTAINER__TUNER_CONTAINER, true, false, true, null, null, null));
	}

	/**
	* This adds a property descriptor for the Tuner Type feature.
	* <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	* @generated
	*/
	protected void addTunerTypePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_UnallocatedTunerContainer_tunerType_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_UnallocatedTunerContainer_tunerType_feature", "_UI_UnallocatedTunerContainer_type"),
			FrontendPackage.Literals.UNALLOCATED_TUNER_CONTAINER__TUNER_TYPE, true, false, false, ItemPropertyDescriptor.GENERIC_VALUE_IMAGE, null, null));
	}

	/**
	* This returns UnallocatedTunerContainer.gif.
	* <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	* @generated_NOT
	*/
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/TuningFork"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	@Override
	public String getText(Object object) {
		UnallocatedTunerContainer container = (UnallocatedTunerContainer) object;

		// Get a count of how many tuners are unallocated
		int unallocatedCount = 0;
		EList<TunerStatus> tuners = container.getTunerContainer().getTunerStatus();
		for (TunerStatus tuner : tuners) {
			if (tuner.getTunerType().equals(container.getTunerType())) {
				if (tuner.getAllocationID() == null || tuner.getAllocationID().equals("")) {
					unallocatedCount++;
				}
			}
		}

		String label = container.getTunerType();
		return label == null || label.length() == 0 ? getString("_UI_UnallocatedTunerContainer_type") : "Unallocated " + label + ": " + unallocatedCount
			+ " available";
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

		switch (notification.getFeatureID(UnallocatedTunerContainer.class)) {
		case FrontendPackage.UNALLOCATED_TUNER_CONTAINER__TUNER_TYPE:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
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

	/**
	* Return the resource locator for this item provider's resources.
	* <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	* @generated
	*/
	@Override
	public ResourceLocator getResourceLocator() {
		return FrontendEditPlugin.INSTANCE;
	}

}

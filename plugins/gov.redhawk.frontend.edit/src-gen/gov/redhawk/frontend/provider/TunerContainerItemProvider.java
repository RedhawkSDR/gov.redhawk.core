/**
 */
package gov.redhawk.frontend.provider;

import gov.redhawk.frontend.FrontendFactory;
import gov.redhawk.frontend.FrontendPackage;
import gov.redhawk.frontend.TunerContainer;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.UnallocatedTunerContainer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
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
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link gov.redhawk.frontend.TunerContainer} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TunerContainerItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource, ITableItemLabelProvider, ITableItemColorProvider, ITableItemFontProvider,
		IItemColorProvider, IItemFontProvider {
	/**
	* This constructs an instance from a factory and a notifier.
	* <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	* @generated
	*/
	public TunerContainerItemProvider(AdapterFactory adapterFactory) {
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

			addModelDevicePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	* This adds a property descriptor for the Model Device feature.
	* <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	* @generated
	*/
	protected void addModelDevicePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add(createItemPropertyDescriptor(((ComposeableAdapterFactory) adapterFactory).getRootAdapterFactory(), getResourceLocator(),
			getString("_UI_TunerContainer_modelDevice_feature"),
			getString("_UI_PropertyDescriptor_description", "_UI_TunerContainer_modelDevice_feature", "_UI_TunerContainer_type"),
			FrontendPackage.Literals.TUNER_CONTAINER__MODEL_DEVICE, true, false, true, null, null, null));
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
			childrenFeatures.add(FrontendPackage.Literals.TUNER_CONTAINER__UNALLOCATED_CONTAINER);
			childrenFeatures.add(FrontendPackage.Literals.TUNER_CONTAINER__TUNER_STATUS);
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

	@Override
	public Collection< ? > getChildren(Object object) {
		if (object instanceof TunerContainer) {
			TunerContainer container = (TunerContainer) object;
			List<EObject> children = new ArrayList<EObject>();
			for (TunerStatus tuner : container.getTunerStatus()) {
				if (tuner.getAllocationID() != null && !tuner.getAllocationID().equals("")) {
					children.add(tuner);
				}
			}

			// Only add an unallocated container if their is an unallocated tuner of that tuner-type
			unallocatedLoop: for (UnallocatedTunerContainer unallocatedContainer : container.getUnallocatedContainer()) {
				EList<TunerStatus> tuners = container.getTunerStatus();
				for (TunerStatus tuner : tuners) {
					if (tuner.getTunerType().equals(unallocatedContainer.getTunerType())) {
						if (tuner.getAllocationID() == null || tuner.getAllocationID().equals("")) {
							children.add(unallocatedContainer);
							continue unallocatedLoop;
						}
					}
				}
			}
			return children;
		}
		return super.getChildren(object);
	}

	/**
	* This returns TunerContainer.gif.
	* <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	* @generated
	*/
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/TunerContainer"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	@Override
	public String getText(Object object) {
		return "FrontEnd Tuners";
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

		switch (notification.getFeatureID(TunerContainer.class)) {
		case FrontendPackage.TUNER_CONTAINER__UNALLOCATED_CONTAINER:
		case FrontendPackage.TUNER_CONTAINER__TUNER_STATUS:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, true));
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

		newChildDescriptors.add(createChildParameter(FrontendPackage.Literals.TUNER_CONTAINER__UNALLOCATED_CONTAINER,
			FrontendFactory.eINSTANCE.createUnallocatedTunerContainer()));

		newChildDescriptors.add(createChildParameter(FrontendPackage.Literals.TUNER_CONTAINER__TUNER_STATUS, FrontendFactory.eINSTANCE.createTunerStatus()));
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

/**
 */
package gov.redhawk.frontend.provider;

import gov.redhawk.frontend.FrontendFactory;
import gov.redhawk.frontend.FrontendPackage;
import gov.redhawk.frontend.TunerStatus;
import java.util.Collection;
import java.util.List;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemColorProvider;
import org.eclipse.emf.edit.provider.IItemFontProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link gov.redhawk.frontend.TunerStatus} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TunerStatusItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider,
		ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource, IItemColorProvider, IItemFontProvider {
	/**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public TunerStatusItemProvider(AdapterFactory adapterFactory) {
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
    if (itemPropertyDescriptors == null)
    {
      super.getPropertyDescriptors(object);

      addTunerContainerPropertyDescriptor(object);
      addTunerStatusStructPropertyDescriptor(object);
      addSimplesPropertyDescriptor(object);
      addTunerIDPropertyDescriptor(object);
      addTunerTypePropertyDescriptor(object);
      addAllocationIDPropertyDescriptor(object);
      addCenterFrequencyPropertyDescriptor(object);
      addBandwidthPropertyDescriptor(object);
      addSampleRatePropertyDescriptor(object);
      addGroupIDPropertyDescriptor(object);
      addRfFlowIDPropertyDescriptor(object);
      addEnabledPropertyDescriptor(object);
      addGainPropertyDescriptor(object);
      addAgcPropertyDescriptor(object);
      addReferenceSourcePropertyDescriptor(object);
      addDeviceControlPropertyDescriptor(object);
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
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_TunerStatus_tunerContainer_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_TunerStatus_tunerContainer_feature", "_UI_TunerStatus_type"),
         FrontendPackage.Literals.TUNER_STATUS__TUNER_CONTAINER,
         true,
         false,
         true,
         null,
         null,
         null));
  }

	/**
   * This adds a property descriptor for the Tuner Status Struct feature.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected void addTunerStatusStructPropertyDescriptor(Object object) {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_TunerStatus_tunerStatusStruct_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_TunerStatus_tunerStatusStruct_feature", "_UI_TunerStatus_type"),
         FrontendPackage.Literals.TUNER_STATUS__TUNER_STATUS_STRUCT,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         null,
         null));
  }

	/**
   * This adds a property descriptor for the Simples feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addSimplesPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_TunerStatus_simples_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_TunerStatus_simples_feature", "_UI_TunerStatus_type"),
         FrontendPackage.Literals.TUNER_STATUS__SIMPLES,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Tuner Type feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addTunerTypePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_TunerStatus_tunerType_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_TunerStatus_tunerType_feature", "_UI_TunerStatus_type"),
         FrontendPackage.Literals.TUNER_STATUS__TUNER_TYPE,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Allocation ID feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addAllocationIDPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_TunerStatus_allocationID_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_TunerStatus_allocationID_feature", "_UI_TunerStatus_type"),
         FrontendPackage.Literals.TUNER_STATUS__ALLOCATION_ID,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Center Frequency feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addCenterFrequencyPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_TunerStatus_centerFrequency_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_TunerStatus_centerFrequency_feature", "_UI_TunerStatus_type"),
         FrontendPackage.Literals.TUNER_STATUS__CENTER_FREQUENCY,
         true,
         false,
         false,
         ItemPropertyDescriptor.REAL_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Bandwidth feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addBandwidthPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_TunerStatus_bandwidth_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_TunerStatus_bandwidth_feature", "_UI_TunerStatus_type"),
         FrontendPackage.Literals.TUNER_STATUS__BANDWIDTH,
         true,
         false,
         false,
         ItemPropertyDescriptor.REAL_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Sample Rate feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addSampleRatePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_TunerStatus_sampleRate_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_TunerStatus_sampleRate_feature", "_UI_TunerStatus_type"),
         FrontendPackage.Literals.TUNER_STATUS__SAMPLE_RATE,
         true,
         false,
         false,
         ItemPropertyDescriptor.REAL_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Group ID feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addGroupIDPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_TunerStatus_groupID_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_TunerStatus_groupID_feature", "_UI_TunerStatus_type"),
         FrontendPackage.Literals.TUNER_STATUS__GROUP_ID,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Rf Flow ID feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addRfFlowIDPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_TunerStatus_rfFlowID_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_TunerStatus_rfFlowID_feature", "_UI_TunerStatus_type"),
         FrontendPackage.Literals.TUNER_STATUS__RF_FLOW_ID,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Enabled feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addEnabledPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_TunerStatus_enabled_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_TunerStatus_enabled_feature", "_UI_TunerStatus_type"),
         FrontendPackage.Literals.TUNER_STATUS__ENABLED,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Gain feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addGainPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_TunerStatus_gain_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_TunerStatus_gain_feature", "_UI_TunerStatus_type"),
         FrontendPackage.Literals.TUNER_STATUS__GAIN,
         true,
         false,
         false,
         ItemPropertyDescriptor.REAL_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Agc feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addAgcPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_TunerStatus_agc_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_TunerStatus_agc_feature", "_UI_TunerStatus_type"),
         FrontendPackage.Literals.TUNER_STATUS__AGC,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Reference Source feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addReferenceSourcePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_TunerStatus_referenceSource_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_TunerStatus_referenceSource_feature", "_UI_TunerStatus_type"),
         FrontendPackage.Literals.TUNER_STATUS__REFERENCE_SOURCE,
         true,
         false,
         false,
         ItemPropertyDescriptor.INTEGRAL_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Device Control feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addDeviceControlPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_TunerStatus_deviceControl_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_TunerStatus_deviceControl_feature", "_UI_TunerStatus_type"),
         FrontendPackage.Literals.TUNER_STATUS__DEVICE_CONTROL,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Tuner ID feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addTunerIDPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_TunerStatus_tunerID_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_TunerStatus_tunerID_feature", "_UI_TunerStatus_type"),
         FrontendPackage.Literals.TUNER_STATUS__TUNER_ID,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         null,
         null));
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
  public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
  {
    if (childrenFeatures == null)
    {
      super.getChildrenFeatures(object);
      childrenFeatures.add(FrontendPackage.Literals.TUNER_STATUS__LISTENER_ALLOCATIONS);
    }
    return childrenFeatures;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EStructuralFeature getChildFeature(Object object, Object child)
  {
    // Check the type of the specified child object and return the proper feature to use for
    // adding (see {@link AddCommand}) it as a child.

    return super.getChildFeature(object, child);
  }

  /**
   * This returns TunerStatus.gif.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   */
	@Override
	public Object getImage(Object object) {
		String allocationID = ((TunerStatus) object).getAllocationID();
		if (!(allocationID == null || allocationID.equals(""))) {
			return overlayImage(object, getResourceLocator().getImage("full/obj16/TunerAllocated"));
		}
	    return overlayImage(object, getResourceLocator().getImage("full/obj16/TunerStatus"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 */
	@Override
	public String getText(Object object) {
		if (object instanceof TunerStatus) {
			TunerStatus tuner = (TunerStatus) object;
			String allocationID = tuner.getAllocationID();
			String label = tuner.getTunerType();
			return label == null || label.length() == 0 ? getString("_UI_TunerStatus_type") : 
				(allocationID == null || allocationID.length() == 0 ? label : label + " " + allocationID);
		}
		return getString("_UI_TunerStatus_type");
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

    switch (notification.getFeatureID(TunerStatus.class))
    {
      case FrontendPackage.TUNER_STATUS__TUNER_STATUS_STRUCT:
      case FrontendPackage.TUNER_STATUS__SIMPLES:
      case FrontendPackage.TUNER_STATUS__TUNER_ID:
      case FrontendPackage.TUNER_STATUS__TUNER_TYPE:
      case FrontendPackage.TUNER_STATUS__ALLOCATION_ID:
      case FrontendPackage.TUNER_STATUS__CENTER_FREQUENCY:
      case FrontendPackage.TUNER_STATUS__BANDWIDTH:
      case FrontendPackage.TUNER_STATUS__SAMPLE_RATE:
      case FrontendPackage.TUNER_STATUS__GROUP_ID:
      case FrontendPackage.TUNER_STATUS__RF_FLOW_ID:
      case FrontendPackage.TUNER_STATUS__ENABLED:
      case FrontendPackage.TUNER_STATUS__GAIN:
      case FrontendPackage.TUNER_STATUS__AGC:
      case FrontendPackage.TUNER_STATUS__REFERENCE_SOURCE:
      case FrontendPackage.TUNER_STATUS__DEVICE_CONTROL:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case FrontendPackage.TUNER_STATUS__LISTENER_ALLOCATIONS:
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

    newChildDescriptors.add
      (createChildParameter
        (FrontendPackage.Literals.TUNER_STATUS__LISTENER_ALLOCATIONS,
         FrontendFactory.eINSTANCE.createListenerAllocation()));
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

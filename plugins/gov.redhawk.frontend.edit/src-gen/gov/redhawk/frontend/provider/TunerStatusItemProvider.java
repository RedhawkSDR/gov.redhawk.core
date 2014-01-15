/**
 */
package gov.redhawk.frontend.provider;


import gov.redhawk.frontend.FrontendPackage;
import gov.redhawk.frontend.TunerStatus;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

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
public class TunerStatusItemProvider
  extends ItemProviderAdapter
  implements
    IEditingDomainItemProvider,
    IStructuredItemContentProvider,
    ITreeItemContentProvider,
    IItemLabelProvider,
    IItemPropertySource,
    IItemColorProvider,
    IItemFontProvider
{
  /**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TunerStatusItemProvider(AdapterFactory adapterFactory)
  {
    super(adapterFactory);
  }

  /**
   * This returns the property descriptors for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
  {
    if (itemPropertyDescriptors == null)
    {
      super.getPropertyDescriptors(object);

      addTunerPropertyDescriptor(object);
      addCenterFrequencyPropertyDescriptor(object);
      addBandwidthPropertyDescriptor(object);
      addBandwidthTolerancePropertyDescriptor(object);
      addSampleRatePropertyDescriptor(object);
      addSampleRateTolerancePropertyDescriptor(object);
      addEnabledPropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Tuner feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addTunerPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_TunerStatus_tuner_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_TunerStatus_tuner_feature", "_UI_TunerStatus_type"),
         FrontendPackage.Literals.TUNER_STATUS__TUNER,
         false,
         false,
         true,
         null,
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
   * This adds a property descriptor for the Bandwidth Tolerance feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addBandwidthTolerancePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_TunerStatus_bandwidthTolerance_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_TunerStatus_bandwidthTolerance_feature", "_UI_TunerStatus_type"),
         FrontendPackage.Literals.TUNER_STATUS__BANDWIDTH_TOLERANCE,
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
   * This adds a property descriptor for the Sample Rate Tolerance feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addSampleRateTolerancePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_TunerStatus_sampleRateTolerance_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_TunerStatus_sampleRateTolerance_feature", "_UI_TunerStatus_type"),
         FrontendPackage.Literals.TUNER_STATUS__SAMPLE_RATE_TOLERANCE,
         true,
         false,
         false,
         ItemPropertyDescriptor.REAL_VALUE_IMAGE,
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
   * This returns TunerStatus.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object getImage(Object object)
  {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/TunerStatus"));
  }

  /**
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getText(Object object)
  {
    TunerStatus tunerStatus = (TunerStatus)object;
    return getString("_UI_TunerStatus_type") + " " + tunerStatus.getCenterFrequency();
  }

  /**
   * This handles model notifications by calling {@link #updateChildren} to update any cached
   * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void notifyChanged(Notification notification)
  {
    updateChildren(notification);

    switch (notification.getFeatureID(TunerStatus.class))
    {
      case FrontendPackage.TUNER_STATUS__CENTER_FREQUENCY:
      case FrontendPackage.TUNER_STATUS__BANDWIDTH:
      case FrontendPackage.TUNER_STATUS__BANDWIDTH_TOLERANCE:
      case FrontendPackage.TUNER_STATUS__SAMPLE_RATE:
      case FrontendPackage.TUNER_STATUS__SAMPLE_RATE_TOLERANCE:
      case FrontendPackage.TUNER_STATUS__ENABLED:
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
  protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object)
  {
    super.collectNewChildDescriptors(newChildDescriptors, object);
  }

  /**
   * Return the resource locator for this item provider's resources.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ResourceLocator getResourceLocator()
  {
    return FrontendEditPlugin.INSTANCE;
  }

}

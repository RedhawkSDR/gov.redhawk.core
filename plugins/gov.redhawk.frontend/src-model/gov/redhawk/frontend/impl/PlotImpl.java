/**
 */
package gov.redhawk.frontend.impl;

import gov.redhawk.frontend.FrontendPackage;
import gov.redhawk.frontend.Plot;
import gov.redhawk.frontend.TunerStatus;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Plot</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.frontend.impl.PlotImpl#getTunerStatus <em>Tuner Status</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.PlotImpl#getListenerID <em>Listener ID</em>}</li>
 *   <li>{@link gov.redhawk.frontend.impl.PlotImpl#getPlotView <em>Plot View</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PlotImpl extends MinimalEObjectImpl.Container implements Plot
{
  /**
   * The default value of the '{@link #getListenerID() <em>Listener ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getListenerID()
   * @generated
   * @ordered
   */
  protected static final String LISTENER_ID_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getListenerID() <em>Listener ID</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getListenerID()
   * @generated
   * @ordered
   */
  protected String listenerID = LISTENER_ID_EDEFAULT;

  /**
   * The default value of the '{@link #getPlotView() <em>Plot View</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPlotView()
   * @generated
   * @ordered
   */
  protected static final Object PLOT_VIEW_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getPlotView() <em>Plot View</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPlotView()
   * @generated
   * @ordered
   */
  protected Object plotView = PLOT_VIEW_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PlotImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return FrontendPackage.Literals.PLOT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TunerStatus getTunerStatus()
  {
    if (eContainerFeatureID() != FrontendPackage.PLOT__TUNER_STATUS) return null;
    return (TunerStatus)eContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TunerStatus basicGetTunerStatus()
  {
    if (eContainerFeatureID() != FrontendPackage.PLOT__TUNER_STATUS) return null;
    return (TunerStatus)eInternalContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTunerStatus(TunerStatus newTunerStatus, NotificationChain msgs)
  {
    msgs = eBasicSetContainer((InternalEObject)newTunerStatus, FrontendPackage.PLOT__TUNER_STATUS, msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTunerStatus(TunerStatus newTunerStatus)
  {
    if (newTunerStatus != eInternalContainer() || (eContainerFeatureID() != FrontendPackage.PLOT__TUNER_STATUS && newTunerStatus != null))
    {
      if (EcoreUtil.isAncestor(this, newTunerStatus))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newTunerStatus != null)
        msgs = ((InternalEObject)newTunerStatus).eInverseAdd(this, FrontendPackage.TUNER_STATUS__PLOTS, TunerStatus.class, msgs);
      msgs = basicSetTunerStatus(newTunerStatus, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.PLOT__TUNER_STATUS, newTunerStatus, newTunerStatus));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getListenerID()
  {
    return listenerID;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setListenerID(String newListenerID)
  {
    String oldListenerID = listenerID;
    listenerID = newListenerID;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.PLOT__LISTENER_ID, oldListenerID, listenerID));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getPlotView()
  {
    return plotView;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPlotView(Object newPlotView)
  {
    Object oldPlotView = plotView;
    plotView = newPlotView;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, FrontendPackage.PLOT__PLOT_VIEW, oldPlotView, plotView));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case FrontendPackage.PLOT__TUNER_STATUS:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return basicSetTunerStatus((TunerStatus)otherEnd, msgs);
    }
    return super.eInverseAdd(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case FrontendPackage.PLOT__TUNER_STATUS:
        return basicSetTunerStatus(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
  {
    switch (eContainerFeatureID())
    {
      case FrontendPackage.PLOT__TUNER_STATUS:
        return eInternalContainer().eInverseRemove(this, FrontendPackage.TUNER_STATUS__PLOTS, TunerStatus.class, msgs);
    }
    return super.eBasicRemoveFromContainerFeature(msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case FrontendPackage.PLOT__TUNER_STATUS:
        if (resolve) return getTunerStatus();
        return basicGetTunerStatus();
      case FrontendPackage.PLOT__LISTENER_ID:
        return getListenerID();
      case FrontendPackage.PLOT__PLOT_VIEW:
        return getPlotView();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case FrontendPackage.PLOT__TUNER_STATUS:
        setTunerStatus((TunerStatus)newValue);
        return;
      case FrontendPackage.PLOT__LISTENER_ID:
        setListenerID((String)newValue);
        return;
      case FrontendPackage.PLOT__PLOT_VIEW:
        setPlotView(newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case FrontendPackage.PLOT__TUNER_STATUS:
        setTunerStatus((TunerStatus)null);
        return;
      case FrontendPackage.PLOT__LISTENER_ID:
        setListenerID(LISTENER_ID_EDEFAULT);
        return;
      case FrontendPackage.PLOT__PLOT_VIEW:
        setPlotView(PLOT_VIEW_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case FrontendPackage.PLOT__TUNER_STATUS:
        return basicGetTunerStatus() != null;
      case FrontendPackage.PLOT__LISTENER_ID:
        return LISTENER_ID_EDEFAULT == null ? listenerID != null : !LISTENER_ID_EDEFAULT.equals(listenerID);
      case FrontendPackage.PLOT__PLOT_VIEW:
        return PLOT_VIEW_EDEFAULT == null ? plotView != null : !PLOT_VIEW_EDEFAULT.equals(plotView);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (listenerID: ");
    result.append(listenerID);
    result.append(", plotView: ");
    result.append(plotView);
    result.append(')');
    return result.toString();
  }

} //PlotImpl

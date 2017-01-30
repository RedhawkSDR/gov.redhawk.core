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
package gov.redhawk.model.sca;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;

import CF.Device;
import CF.DeviceOperations;
import CF.DevicePackage.AdminType;
import CF.DevicePackage.OperationalType;
import CF.DevicePackage.UsageType;
import mil.jpeojtrs.sca.dcd.DcdComponentInstantiation;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Device</b></em>'.
 * 
 * @noimplement This interface is not intended to be implemented by clients.
 *              <!-- end-user-doc -->
 *
 *              <p>
 *              The following features are supported:
 *              </p>
 *              <ul>
 *              <li>{@link gov.redhawk.model.sca.ScaDevice#getChildDevices <em>Child Devices</em>}</li>
 *              <li>{@link gov.redhawk.model.sca.ScaDevice#getAdminState <em>Admin State</em>}</li>
 *              <li>{@link gov.redhawk.model.sca.ScaDevice#getLabel <em>Label</em>}</li>
 *              <li>{@link gov.redhawk.model.sca.ScaDevice#getOperationalState <em>Operational State</em>}</li>
 *              <li>{@link gov.redhawk.model.sca.ScaDevice#getUsageState <em>Usage State</em>}</li>
 *              <li>{@link gov.redhawk.model.sca.ScaDevice#getParentDevice <em>Parent Device</em>}</li>
 *              <li>{@link gov.redhawk.model.sca.ScaDevice#getDevMgr <em>Dev Mgr</em>}</li>
 *              <li>{@link gov.redhawk.model.sca.ScaDevice#getComponentInstantiation <em>Component
 *              Instantiation</em>}</li>
 *              </ul>
 *
 * @see gov.redhawk.model.sca.ScaPackage#getScaDevice()
 * @model superTypes="gov.redhawk.model.sca.ScaAbstractComponent<D> mil.jpeojtrs.sca.cf.DeviceOperations"
 *        DBounds="mil.jpeojtrs.sca.cf.Device"
 *        extendedMetaData="name='ScaDevice' kind='elementOnly'"
 * @generated
 */
public interface ScaDevice< D extends Device > extends ScaAbstractComponent<D>, DeviceOperations {

	/**
	 * Returns the value of the '<em><b>Child Devices</b></em>' reference list.
	 * The list contents are of type {@link gov.redhawk.model.sca.ScaDevice}&lt;?>.
	 * It is bidirectional and its opposite is '{@link gov.redhawk.model.sca.ScaDevice#getParentDevice <em>Parent
	 * Device</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Child Devices</em>' reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Child Devices</em>' reference list.
	 * @see #isSetChildDevices()
	 * @see #unsetChildDevices()
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDevice_ChildDevices()
	 * @see gov.redhawk.model.sca.ScaDevice#getParentDevice
	 * @model opposite="parentDevice" resolveProxies="false" unsettable="true" transient="true"
	 *        extendedMetaData="kind='element' name='childDevices'"
	 * @generated
	 */
	EList<ScaDevice< ? >> getChildDevices();

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaDevice#getChildDevices <em>Child Devices</em>}'
	 * reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #isSetChildDevices()
	 * @see #getChildDevices()
	 * @generated
	 */
	void unsetChildDevices();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaDevice#getChildDevices <em>Child Devices</em>}'
	 * reference list is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Child Devices</em>' reference list is set.
	 * @see #unsetChildDevices()
	 * @see #getChildDevices()
	 * @generated
	 */
	boolean isSetChildDevices();

	/**
	 * Returns the value of the '<em><b>Admin State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Admin State</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Admin State</em>' attribute.
	 * @see #isSetAdminState()
	 * @see #unsetAdminState()
	 * @see #setAdminState(AdminType)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDevice_AdminState()
	 * @model unsettable="true" dataType="gov.redhawk.model.sca.AdminType" transient="true" derived="true"
	 *        extendedMetaData="kind='attribute' name='adminState'"
	 * @generated
	 */
	AdminType getAdminState();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaDevice#getAdminState <em>Admin State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 16.0
	 *        <!-- end-user-doc -->
	 * @param value the new value of the '<em>Admin State</em>' attribute.
	 * @see #isSetAdminState()
	 * @see #unsetAdminState()
	 * @see #getAdminState()
	 * @generated
	 */
	void setAdminState(AdminType value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaDevice#getAdminState <em>Admin State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 16.0
	 *        <!-- end-user-doc -->
	 * @see #isSetAdminState()
	 * @see #getAdminState()
	 * @see #setAdminState(AdminType)
	 * @generated
	 */
	void unsetAdminState();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaDevice#getAdminState <em>Admin State</em>}'
	 * attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Admin State</em>' attribute is set.
	 * @see #unsetAdminState()
	 * @see #getAdminState()
	 * @see #setAdminState(AdminType)
	 * @generated
	 */
	boolean isSetAdminState();

	/**
	 * Returns the value of the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Label</em>' attribute.
	 * @see #isSetLabel()
	 * @see #unsetLabel()
	 * @see #setLabel(String)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDevice_Label()
	 * @model unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" derived="true"
	 *        extendedMetaData="kind='attribute' name='label'"
	 * @generated
	 */
	String getLabel();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaDevice#getLabel <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 16.0
	 *        <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' attribute.
	 * @see #isSetLabel()
	 * @see #unsetLabel()
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(String value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaDevice#getLabel <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 16.0
	 *        <!-- end-user-doc -->
	 * @see #isSetLabel()
	 * @see #getLabel()
	 * @see #setLabel(String)
	 * @generated
	 */
	void unsetLabel();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaDevice#getLabel <em>Label</em>}' attribute is
	 * set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Label</em>' attribute is set.
	 * @see #unsetLabel()
	 * @see #getLabel()
	 * @see #setLabel(String)
	 * @generated
	 */
	boolean isSetLabel();

	/**
	 * Returns the value of the '<em><b>Operational State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operational State</em>' attribute isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Operational State</em>' attribute.
	 * @see #isSetOperationalState()
	 * @see #unsetOperationalState()
	 * @see #setOperationalState(OperationalType)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDevice_OperationalState()
	 * @model unsettable="true" dataType="gov.redhawk.model.sca.OperationalType" transient="true" derived="true"
	 *        extendedMetaData="kind='attribute' name='operationalState'"
	 * @generated
	 */
	OperationalType getOperationalState();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaDevice#getOperationalState <em>Operational State</em>}'
	 * attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 16.0
	 *        <!-- end-user-doc -->
	 * @param value the new value of the '<em>Operational State</em>' attribute.
	 * @see #isSetOperationalState()
	 * @see #unsetOperationalState()
	 * @see #getOperationalState()
	 * @generated
	 */
	void setOperationalState(OperationalType value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaDevice#getOperationalState <em>Operational State</em>}'
	 * attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 16.0
	 *        <!-- end-user-doc -->
	 * @see #isSetOperationalState()
	 * @see #getOperationalState()
	 * @see #setOperationalState(OperationalType)
	 * @generated
	 */
	void unsetOperationalState();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaDevice#getOperationalState <em>Operational
	 * State</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Operational State</em>' attribute is set.
	 * @see #unsetOperationalState()
	 * @see #getOperationalState()
	 * @see #setOperationalState(OperationalType)
	 * @generated
	 */
	boolean isSetOperationalState();

	/**
	 * Returns the value of the '<em><b>Usage State</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Usage State</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Usage State</em>' attribute.
	 * @see #isSetUsageState()
	 * @see #unsetUsageState()
	 * @see #setUsageState(UsageType)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDevice_UsageState()
	 * @model unsettable="true" dataType="gov.redhawk.model.sca.UsageType" transient="true" derived="true"
	 *        extendedMetaData="kind='attribute' name='usageState'"
	 * @generated
	 */
	UsageType getUsageState();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaDevice#getUsageState <em>Usage State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 16.0
	 *        <!-- end-user-doc -->
	 * @param value the new value of the '<em>Usage State</em>' attribute.
	 * @see #isSetUsageState()
	 * @see #unsetUsageState()
	 * @see #getUsageState()
	 * @generated
	 */
	void setUsageState(UsageType value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaDevice#getUsageState <em>Usage State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 16.0
	 *        <!-- end-user-doc -->
	 * @see #isSetUsageState()
	 * @see #getUsageState()
	 * @see #setUsageState(UsageType)
	 * @generated
	 */
	void unsetUsageState();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaDevice#getUsageState <em>Usage State</em>}'
	 * attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Usage State</em>' attribute is set.
	 * @see #unsetUsageState()
	 * @see #getUsageState()
	 * @see #setUsageState(UsageType)
	 * @generated
	 */
	boolean isSetUsageState();

	/**
	 * Returns the value of the '<em><b>Parent Device</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link gov.redhawk.model.sca.ScaDevice#getChildDevices <em>Child
	 * Devices</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Device</em>' reference isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Parent Device</em>' reference.
	 * @see #setParentDevice(ScaDevice)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDevice_ParentDevice()
	 * @see gov.redhawk.model.sca.ScaDevice#getChildDevices
	 * @model opposite="childDevices" transient="true"
	 * @generated
	 */
	ScaDevice< ? > getParentDevice();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaDevice#getParentDevice <em>Parent Device</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Parent Device</em>' reference.
	 * @see #getParentDevice()
	 * @generated
	 */
	void setParentDevice(ScaDevice< ? > value);

	/**
	 * Returns the value of the '<em><b>Dev Mgr</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dev Mgr</em>' reference isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Dev Mgr</em>' reference.
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDevice_DevMgr()
	 * @model transient="true" changeable="false" volatile="true" derived="true"
	 * @generated
	 */
	ScaDeviceManager getDevMgr();

	/**
	 * Returns the value of the '<em><b>Component Instantiation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * Gets the component instantiation for this device (from the DCD XML).
	 * </p>
	 * @since 20.4
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Component Instantiation</em>' reference.
	 * @see #isSetComponentInstantiation()
	 * @see #unsetComponentInstantiation()
	 * @see #setComponentInstantiation(DcdComponentInstantiation)
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDevice_ComponentInstantiation()
	 * @model unsettable="true" required="true" transient="true"
	 * @generated
	 */
	DcdComponentInstantiation getComponentInstantiation();

	/**
	 * Sets the value of the '{@link gov.redhawk.model.sca.ScaDevice#getComponentInstantiation <em>Component
	 * Instantiation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * @since 20.4
	 * <!-- end-user-doc -->
	 * 
	 * @param value the new value of the '<em>Component Instantiation</em>' reference.
	 * @see #isSetComponentInstantiation()
	 * @see #unsetComponentInstantiation()
	 * @see #getComponentInstantiation()
	 * @generated
	 */
	void setComponentInstantiation(DcdComponentInstantiation value);

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaDevice#getComponentInstantiation <em>Component
	 * Instantiation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * @since 20.4
	 * <!-- end-user-doc -->
	 * 
	 * @see #isSetComponentInstantiation()
	 * @see #getComponentInstantiation()
	 * @see #setComponentInstantiation(DcdComponentInstantiation)
	 * @generated
	 */
	void unsetComponentInstantiation();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaDevice#getComponentInstantiation <em>Component
	 * Instantiation</em>}' reference is set.
	 * <!-- begin-user-doc -->
	 * @since 20.4
	 * <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Component Instantiation</em>' reference is set.
	 * @see #unsetComponentInstantiation()
	 * @see #getComponentInstantiation()
	 * @see #setComponentInstantiation(DcdComponentInstantiation)
	 * @generated
	 */
	boolean isSetComponentInstantiation();

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @deprecated Use {@link #fetchAggregateDevices(IProgressMonitor, RefreshDepth)}
	 * @since 14.0
	 *        <!-- end-user-doc -->
	 * @model monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	EList<ScaDevice< ? >> fetchAggregateDevices(IProgressMonitor monitor);

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 20.0
	 *        <!-- end-user-doc -->
	 * @model monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	EList<ScaDevice< ? >> fetchAggregateDevices(IProgressMonitor monitor, RefreshDepth depth);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @model dataType="gov.redhawk.model.sca.AdminType" monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	AdminType fetchAdminState(IProgressMonitor monitor);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @model monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	String fetchLabel(IProgressMonitor monitor);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @model dataType="gov.redhawk.model.sca.OperationalType" monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	OperationalType fetchOperationalState(IProgressMonitor monitor);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @model dataType="gov.redhawk.model.sca.UsageType" monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	UsageType fetchUsageState(IProgressMonitor monitor);
} // ScaDevice

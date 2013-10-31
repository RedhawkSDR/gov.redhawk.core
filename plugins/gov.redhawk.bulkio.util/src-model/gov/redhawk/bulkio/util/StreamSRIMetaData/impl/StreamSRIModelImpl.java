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
package gov.redhawk.bulkio.util.StreamSRIMetaData.impl;

import gov.redhawk.bulkio.util.StreamSRIMetaData.SRI;
import gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIMetaDataPackage;
import gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIModel;
import gov.redhawk.bulkio.util.StreamSRIMetaData.Time;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Stream SRI Model</b></em>'.
 * @since 2.0
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.bulkio.util.StreamSRIMetaData.impl.StreamSRIModelImpl#getNumberOfSamples <em>Number Of Samples</em>}</li>
 *   <li>{@link gov.redhawk.bulkio.util.StreamSRIMetaData.impl.StreamSRIModelImpl#getDataByteOrder <em>Data Byte Order</em>}</li>
 *   <li>{@link gov.redhawk.bulkio.util.StreamSRIMetaData.impl.StreamSRIModelImpl#getTime <em>Time</em>}</li>
 *   <li>{@link gov.redhawk.bulkio.util.StreamSRIMetaData.impl.StreamSRIModelImpl#getBulkIOType <em>Bulk IO Type</em>}</li>
 *   <li>{@link gov.redhawk.bulkio.util.StreamSRIMetaData.impl.StreamSRIModelImpl#getStreamSRI <em>Stream SRI</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StreamSRIModelImpl extends EObjectImpl implements StreamSRIModel
{
	/**
	 * The default value of the '{@link #getNumberOfSamples() <em>Number Of Samples</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumberOfSamples()
	 * @generated
	 * @ordered
	 */
	protected static final long NUMBER_OF_SAMPLES_EDEFAULT = 0L;

	/**
	 * The cached value of the '{@link #getNumberOfSamples() <em>Number Of Samples</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumberOfSamples()
	 * @generated
	 * @ordered
	 */
	protected long numberOfSamples = NUMBER_OF_SAMPLES_EDEFAULT;

	/**
	 * This is true if the Number Of Samples attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean numberOfSamplesESet;

	/**
	 * The default value of the '{@link #getDataByteOrder() <em>Data Byte Order</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataByteOrder()
	 * @generated
	 * @ordered
	 */
	protected static final String DATA_BYTE_ORDER_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getDataByteOrder() <em>Data Byte Order</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataByteOrder()
	 * @generated
	 * @ordered
	 */
	protected String dataByteOrder = DATA_BYTE_ORDER_EDEFAULT;

	/**
	 * This is true if the Data Byte Order attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean dataByteOrderESet;

	/**
	 * The cached value of the '{@link #getTime() <em>Time</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTime()
	 * @generated
	 * @ordered
	 */
	protected Time time;

	/**
	 * The default value of the '{@link #getBulkIOType() <em>Bulk IO Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBulkIOType()
	 * @generated
	 * @ordered
	 */
	protected static final String BULK_IO_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBulkIOType() <em>Bulk IO Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBulkIOType()
	 * @generated
	 * @ordered
	 */
	protected String bulkIOType = BULK_IO_TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getStreamSRI() <em>Stream SRI</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStreamSRI()
	 * @generated
	 * @ordered
	 */
	protected SRI streamSRI;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StreamSRIModelImpl()
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
		return StreamSRIMetaDataPackage.Literals.STREAM_SRI_MODEL;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public long getNumberOfSamples()
	{
		return numberOfSamples;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumberOfSamples(long newNumberOfSamples)
	{
		long oldNumberOfSamples = numberOfSamples;
		numberOfSamples = newNumberOfSamples;
		boolean oldNumberOfSamplesESet = numberOfSamplesESet;
		numberOfSamplesESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StreamSRIMetaDataPackage.STREAM_SRI_MODEL__NUMBER_OF_SAMPLES, oldNumberOfSamples, numberOfSamples, !oldNumberOfSamplesESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetNumberOfSamples()
	{
		long oldNumberOfSamples = numberOfSamples;
		boolean oldNumberOfSamplesESet = numberOfSamplesESet;
		numberOfSamples = NUMBER_OF_SAMPLES_EDEFAULT;
		numberOfSamplesESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, StreamSRIMetaDataPackage.STREAM_SRI_MODEL__NUMBER_OF_SAMPLES, oldNumberOfSamples, NUMBER_OF_SAMPLES_EDEFAULT, oldNumberOfSamplesESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetNumberOfSamples()
	{
		return numberOfSamplesESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDataByteOrder()
	{
		return dataByteOrder;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataByteOrder(String newDataByteOrder)
	{
		String oldDataByteOrder = dataByteOrder;
		dataByteOrder = newDataByteOrder;
		boolean oldDataByteOrderESet = dataByteOrderESet;
		dataByteOrderESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StreamSRIMetaDataPackage.STREAM_SRI_MODEL__DATA_BYTE_ORDER, oldDataByteOrder, dataByteOrder, !oldDataByteOrderESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetDataByteOrder()
	{
		String oldDataByteOrder = dataByteOrder;
		boolean oldDataByteOrderESet = dataByteOrderESet;
		dataByteOrder = DATA_BYTE_ORDER_EDEFAULT;
		dataByteOrderESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, StreamSRIMetaDataPackage.STREAM_SRI_MODEL__DATA_BYTE_ORDER, oldDataByteOrder, DATA_BYTE_ORDER_EDEFAULT, oldDataByteOrderESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetDataByteOrder()
	{
		return dataByteOrderESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Time getTime()
	{
		return time;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTime(Time newTime, NotificationChain msgs)
	{
		Time oldTime = time;
		time = newTime;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StreamSRIMetaDataPackage.STREAM_SRI_MODEL__TIME, oldTime, newTime);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTime(Time newTime)
	{
		if (newTime != time)
		{
			NotificationChain msgs = null;
			if (time != null)
				msgs = ((InternalEObject)time).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StreamSRIMetaDataPackage.STREAM_SRI_MODEL__TIME, null, msgs);
			if (newTime != null)
				msgs = ((InternalEObject)newTime).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - StreamSRIMetaDataPackage.STREAM_SRI_MODEL__TIME, null, msgs);
			msgs = basicSetTime(newTime, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StreamSRIMetaDataPackage.STREAM_SRI_MODEL__TIME, newTime, newTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getBulkIOType()
	{
		return bulkIOType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBulkIOType(String newBulkIOType)
	{
		String oldBulkIOType = bulkIOType;
		bulkIOType = newBulkIOType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StreamSRIMetaDataPackage.STREAM_SRI_MODEL__BULK_IO_TYPE, oldBulkIOType, bulkIOType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SRI getStreamSRI()
	{
		return streamSRI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStreamSRI(SRI newStreamSRI, NotificationChain msgs)
	{
		SRI oldStreamSRI = streamSRI;
		streamSRI = newStreamSRI;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StreamSRIMetaDataPackage.STREAM_SRI_MODEL__STREAM_SRI, oldStreamSRI, newStreamSRI);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStreamSRI(SRI newStreamSRI)
	{
		if (newStreamSRI != streamSRI)
		{
			NotificationChain msgs = null;
			if (streamSRI != null)
				msgs = ((InternalEObject)streamSRI).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StreamSRIMetaDataPackage.STREAM_SRI_MODEL__STREAM_SRI, null, msgs);
			if (newStreamSRI != null)
				msgs = ((InternalEObject)newStreamSRI).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - StreamSRIMetaDataPackage.STREAM_SRI_MODEL__STREAM_SRI, null, msgs);
			msgs = basicSetStreamSRI(newStreamSRI, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StreamSRIMetaDataPackage.STREAM_SRI_MODEL__STREAM_SRI, newStreamSRI, newStreamSRI));
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
			case StreamSRIMetaDataPackage.STREAM_SRI_MODEL__TIME:
				return basicSetTime(null, msgs);
			case StreamSRIMetaDataPackage.STREAM_SRI_MODEL__STREAM_SRI:
				return basicSetStreamSRI(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
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
			case StreamSRIMetaDataPackage.STREAM_SRI_MODEL__NUMBER_OF_SAMPLES:
				return getNumberOfSamples();
			case StreamSRIMetaDataPackage.STREAM_SRI_MODEL__DATA_BYTE_ORDER:
				return getDataByteOrder();
			case StreamSRIMetaDataPackage.STREAM_SRI_MODEL__TIME:
				return getTime();
			case StreamSRIMetaDataPackage.STREAM_SRI_MODEL__BULK_IO_TYPE:
				return getBulkIOType();
			case StreamSRIMetaDataPackage.STREAM_SRI_MODEL__STREAM_SRI:
				return getStreamSRI();
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
			case StreamSRIMetaDataPackage.STREAM_SRI_MODEL__NUMBER_OF_SAMPLES:
				setNumberOfSamples((Long)newValue);
				return;
			case StreamSRIMetaDataPackage.STREAM_SRI_MODEL__DATA_BYTE_ORDER:
				setDataByteOrder((String)newValue);
				return;
			case StreamSRIMetaDataPackage.STREAM_SRI_MODEL__TIME:
				setTime((Time)newValue);
				return;
			case StreamSRIMetaDataPackage.STREAM_SRI_MODEL__BULK_IO_TYPE:
				setBulkIOType((String)newValue);
				return;
			case StreamSRIMetaDataPackage.STREAM_SRI_MODEL__STREAM_SRI:
				setStreamSRI((SRI)newValue);
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
			case StreamSRIMetaDataPackage.STREAM_SRI_MODEL__NUMBER_OF_SAMPLES:
				unsetNumberOfSamples();
				return;
			case StreamSRIMetaDataPackage.STREAM_SRI_MODEL__DATA_BYTE_ORDER:
				unsetDataByteOrder();
				return;
			case StreamSRIMetaDataPackage.STREAM_SRI_MODEL__TIME:
				setTime((Time)null);
				return;
			case StreamSRIMetaDataPackage.STREAM_SRI_MODEL__BULK_IO_TYPE:
				setBulkIOType(BULK_IO_TYPE_EDEFAULT);
				return;
			case StreamSRIMetaDataPackage.STREAM_SRI_MODEL__STREAM_SRI:
				setStreamSRI((SRI)null);
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
			case StreamSRIMetaDataPackage.STREAM_SRI_MODEL__NUMBER_OF_SAMPLES:
				return isSetNumberOfSamples();
			case StreamSRIMetaDataPackage.STREAM_SRI_MODEL__DATA_BYTE_ORDER:
				return isSetDataByteOrder();
			case StreamSRIMetaDataPackage.STREAM_SRI_MODEL__TIME:
				return time != null;
			case StreamSRIMetaDataPackage.STREAM_SRI_MODEL__BULK_IO_TYPE:
				return BULK_IO_TYPE_EDEFAULT == null ? bulkIOType != null : !BULK_IO_TYPE_EDEFAULT.equals(bulkIOType);
			case StreamSRIMetaDataPackage.STREAM_SRI_MODEL__STREAM_SRI:
				return streamSRI != null;
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
		result.append(" (numberOfSamples: ");
		if (numberOfSamplesESet) result.append(numberOfSamples); else result.append("<unset>");
		result.append(", dataByteOrder: ");
		if (dataByteOrderESet) result.append(dataByteOrder); else result.append("<unset>");
		result.append(", bulkIOType: ");
		result.append(bulkIOType);
		result.append(')');
		return result.toString();
	}

} //StreamSRIModelImpl

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
import mil.jpeojtrs.sca.prf.Properties;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>SRI</b></em>'.
 * @since 1.1
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.bulkio.util.StreamSRIMetaData.impl.SRIImpl#getHversion <em>Hversion</em>}</li>
 *   <li>{@link gov.redhawk.bulkio.util.StreamSRIMetaData.impl.SRIImpl#getXstart <em>Xstart</em>}</li>
 *   <li>{@link gov.redhawk.bulkio.util.StreamSRIMetaData.impl.SRIImpl#getXdelta <em>Xdelta</em>}</li>
 *   <li>{@link gov.redhawk.bulkio.util.StreamSRIMetaData.impl.SRIImpl#getXunits <em>Xunits</em>}</li>
 *   <li>{@link gov.redhawk.bulkio.util.StreamSRIMetaData.impl.SRIImpl#getSubsize <em>Subsize</em>}</li>
 *   <li>{@link gov.redhawk.bulkio.util.StreamSRIMetaData.impl.SRIImpl#getYstart <em>Ystart</em>}</li>
 *   <li>{@link gov.redhawk.bulkio.util.StreamSRIMetaData.impl.SRIImpl#getYdelta <em>Ydelta</em>}</li>
 *   <li>{@link gov.redhawk.bulkio.util.StreamSRIMetaData.impl.SRIImpl#getYunits <em>Yunits</em>}</li>
 *   <li>{@link gov.redhawk.bulkio.util.StreamSRIMetaData.impl.SRIImpl#getMode <em>Mode</em>}</li>
 *   <li>{@link gov.redhawk.bulkio.util.StreamSRIMetaData.impl.SRIImpl#getStreamID <em>Stream ID</em>}</li>
 *   <li>{@link gov.redhawk.bulkio.util.StreamSRIMetaData.impl.SRIImpl#isBlocking <em>Blocking</em>}</li>
 *   <li>{@link gov.redhawk.bulkio.util.StreamSRIMetaData.impl.SRIImpl#getKeywords <em>Keywords</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SRIImpl extends EObjectImpl implements SRI
{
	/**
	 * The default value of the '{@link #getHversion() <em>Hversion</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHversion()
	 * @generated
	 * @ordered
	 */
	protected static final int HVERSION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getHversion() <em>Hversion</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHversion()
	 * @generated
	 * @ordered
	 */
	protected int hversion = HVERSION_EDEFAULT;

	/**
	 * This is true if the Hversion attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean hversionESet;

	/**
	 * The default value of the '{@link #getXstart() <em>Xstart</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXstart()
	 * @generated
	 * @ordered
	 */
	protected static final double XSTART_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getXstart() <em>Xstart</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXstart()
	 * @generated
	 * @ordered
	 */
	protected double xstart = XSTART_EDEFAULT;

	/**
	 * This is true if the Xstart attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean xstartESet;

	/**
	 * The default value of the '{@link #getXdelta() <em>Xdelta</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXdelta()
	 * @generated
	 * @ordered
	 */
	protected static final double XDELTA_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getXdelta() <em>Xdelta</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXdelta()
	 * @generated
	 * @ordered
	 */
	protected double xdelta = XDELTA_EDEFAULT;

	/**
	 * This is true if the Xdelta attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean xdeltaESet;

	/**
	 * The default value of the '{@link #getXunits() <em>Xunits</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXunits()
	 * @generated
	 * @ordered
	 */
	protected static final short XUNITS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getXunits() <em>Xunits</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXunits()
	 * @generated
	 * @ordered
	 */
	protected short xunits = XUNITS_EDEFAULT;

	/**
	 * This is true if the Xunits attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean xunitsESet;

	/**
	 * The default value of the '{@link #getSubsize() <em>Subsize</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubsize()
	 * @generated
	 * @ordered
	 */
	protected static final double SUBSIZE_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getSubsize() <em>Subsize</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubsize()
	 * @generated
	 * @ordered
	 */
	protected double subsize = SUBSIZE_EDEFAULT;

	/**
	 * This is true if the Subsize attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean subsizeESet;

	/**
	 * The default value of the '{@link #getYstart() <em>Ystart</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYstart()
	 * @generated
	 * @ordered
	 */
	protected static final double YSTART_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getYstart() <em>Ystart</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYstart()
	 * @generated
	 * @ordered
	 */
	protected double ystart = YSTART_EDEFAULT;

	/**
	 * This is true if the Ystart attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean ystartESet;

	/**
	 * The default value of the '{@link #getYdelta() <em>Ydelta</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYdelta()
	 * @generated
	 * @ordered
	 */
	protected static final double YDELTA_EDEFAULT = 0.0;

	/**
	 * The cached value of the '{@link #getYdelta() <em>Ydelta</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYdelta()
	 * @generated
	 * @ordered
	 */
	protected double ydelta = YDELTA_EDEFAULT;

	/**
	 * This is true if the Ydelta attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean ydeltaESet;

	/**
	 * The default value of the '{@link #getYunits() <em>Yunits</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYunits()
	 * @generated
	 * @ordered
	 */
	protected static final short YUNITS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getYunits() <em>Yunits</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYunits()
	 * @generated
	 * @ordered
	 */
	protected short yunits = YUNITS_EDEFAULT;

	/**
	 * This is true if the Yunits attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean yunitsESet;

	/**
	 * The default value of the '{@link #getMode() <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMode()
	 * @generated
	 * @ordered
	 */
	protected static final short MODE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getMode() <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMode()
	 * @generated
	 * @ordered
	 */
	protected short mode = MODE_EDEFAULT;

	/**
	 * This is true if the Mode attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean modeESet;

	/**
	 * The default value of the '{@link #getStreamID() <em>Stream ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStreamID()
	 * @generated
	 * @ordered
	 */
	protected static final String STREAM_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStreamID() <em>Stream ID</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStreamID()
	 * @generated
	 * @ordered
	 */
	protected String streamID = STREAM_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #isBlocking() <em>Blocking</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBlocking()
	 * @generated
	 * @ordered
	 */
	protected static final boolean BLOCKING_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isBlocking() <em>Blocking</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isBlocking()
	 * @generated
	 * @ordered
	 */
	protected boolean blocking = BLOCKING_EDEFAULT;

	/**
	 * This is true if the Blocking attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean blockingESet;

	/**
	 * The cached value of the '{@link #getKeywords() <em>Keywords</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKeywords()
	 * @generated
	 * @ordered
	 */
	protected Properties keywords;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SRIImpl()
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
		return StreamSRIMetaDataPackage.Literals.SRI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getHversion()
	{
		return hversion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setHversion(int newHversion)
	{
		int oldHversion = hversion;
		hversion = newHversion;
		boolean oldHversionESet = hversionESet;
		hversionESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StreamSRIMetaDataPackage.SRI__HVERSION, oldHversion, hversion, !oldHversionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetHversion()
	{
		int oldHversion = hversion;
		boolean oldHversionESet = hversionESet;
		hversion = HVERSION_EDEFAULT;
		hversionESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, StreamSRIMetaDataPackage.SRI__HVERSION, oldHversion, HVERSION_EDEFAULT, oldHversionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetHversion()
	{
		return hversionESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getXstart()
	{
		return xstart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setXstart(double newXstart)
	{
		double oldXstart = xstart;
		xstart = newXstart;
		boolean oldXstartESet = xstartESet;
		xstartESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StreamSRIMetaDataPackage.SRI__XSTART, oldXstart, xstart, !oldXstartESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetXstart()
	{
		double oldXstart = xstart;
		boolean oldXstartESet = xstartESet;
		xstart = XSTART_EDEFAULT;
		xstartESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, StreamSRIMetaDataPackage.SRI__XSTART, oldXstart, XSTART_EDEFAULT, oldXstartESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetXstart()
	{
		return xstartESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getXdelta()
	{
		return xdelta;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setXdelta(double newXdelta)
	{
		double oldXdelta = xdelta;
		xdelta = newXdelta;
		boolean oldXdeltaESet = xdeltaESet;
		xdeltaESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StreamSRIMetaDataPackage.SRI__XDELTA, oldXdelta, xdelta, !oldXdeltaESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetXdelta()
	{
		double oldXdelta = xdelta;
		boolean oldXdeltaESet = xdeltaESet;
		xdelta = XDELTA_EDEFAULT;
		xdeltaESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, StreamSRIMetaDataPackage.SRI__XDELTA, oldXdelta, XDELTA_EDEFAULT, oldXdeltaESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetXdelta()
	{
		return xdeltaESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public short getXunits()
	{
		return xunits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setXunits(short newXunits)
	{
		short oldXunits = xunits;
		xunits = newXunits;
		boolean oldXunitsESet = xunitsESet;
		xunitsESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StreamSRIMetaDataPackage.SRI__XUNITS, oldXunits, xunits, !oldXunitsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetXunits()
	{
		short oldXunits = xunits;
		boolean oldXunitsESet = xunitsESet;
		xunits = XUNITS_EDEFAULT;
		xunitsESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, StreamSRIMetaDataPackage.SRI__XUNITS, oldXunits, XUNITS_EDEFAULT, oldXunitsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetXunits()
	{
		return xunitsESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getSubsize()
	{
		return subsize;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSubsize(double newSubsize)
	{
		double oldSubsize = subsize;
		subsize = newSubsize;
		boolean oldSubsizeESet = subsizeESet;
		subsizeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StreamSRIMetaDataPackage.SRI__SUBSIZE, oldSubsize, subsize, !oldSubsizeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetSubsize()
	{
		double oldSubsize = subsize;
		boolean oldSubsizeESet = subsizeESet;
		subsize = SUBSIZE_EDEFAULT;
		subsizeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, StreamSRIMetaDataPackage.SRI__SUBSIZE, oldSubsize, SUBSIZE_EDEFAULT, oldSubsizeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetSubsize()
	{
		return subsizeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getYstart()
	{
		return ystart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setYstart(double newYstart)
	{
		double oldYstart = ystart;
		ystart = newYstart;
		boolean oldYstartESet = ystartESet;
		ystartESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StreamSRIMetaDataPackage.SRI__YSTART, oldYstart, ystart, !oldYstartESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetYstart()
	{
		double oldYstart = ystart;
		boolean oldYstartESet = ystartESet;
		ystart = YSTART_EDEFAULT;
		ystartESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, StreamSRIMetaDataPackage.SRI__YSTART, oldYstart, YSTART_EDEFAULT, oldYstartESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetYstart()
	{
		return ystartESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getYdelta()
	{
		return ydelta;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setYdelta(double newYdelta)
	{
		double oldYdelta = ydelta;
		ydelta = newYdelta;
		boolean oldYdeltaESet = ydeltaESet;
		ydeltaESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StreamSRIMetaDataPackage.SRI__YDELTA, oldYdelta, ydelta, !oldYdeltaESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetYdelta()
	{
		double oldYdelta = ydelta;
		boolean oldYdeltaESet = ydeltaESet;
		ydelta = YDELTA_EDEFAULT;
		ydeltaESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, StreamSRIMetaDataPackage.SRI__YDELTA, oldYdelta, YDELTA_EDEFAULT, oldYdeltaESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetYdelta()
	{
		return ydeltaESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public short getYunits()
	{
		return yunits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setYunits(short newYunits)
	{
		short oldYunits = yunits;
		yunits = newYunits;
		boolean oldYunitsESet = yunitsESet;
		yunitsESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StreamSRIMetaDataPackage.SRI__YUNITS, oldYunits, yunits, !oldYunitsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetYunits()
	{
		short oldYunits = yunits;
		boolean oldYunitsESet = yunitsESet;
		yunits = YUNITS_EDEFAULT;
		yunitsESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, StreamSRIMetaDataPackage.SRI__YUNITS, oldYunits, YUNITS_EDEFAULT, oldYunitsESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetYunits()
	{
		return yunitsESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public short getMode()
	{
		return mode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMode(short newMode)
	{
		short oldMode = mode;
		mode = newMode;
		boolean oldModeESet = modeESet;
		modeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StreamSRIMetaDataPackage.SRI__MODE, oldMode, mode, !oldModeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetMode()
	{
		short oldMode = mode;
		boolean oldModeESet = modeESet;
		mode = MODE_EDEFAULT;
		modeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, StreamSRIMetaDataPackage.SRI__MODE, oldMode, MODE_EDEFAULT, oldModeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetMode()
	{
		return modeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getStreamID()
	{
		return streamID;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setStreamID(String newStreamID)
	{
		String oldStreamID = streamID;
		streamID = newStreamID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StreamSRIMetaDataPackage.SRI__STREAM_ID, oldStreamID, streamID));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isBlocking()
	{
		return blocking;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBlocking(boolean newBlocking)
	{
		boolean oldBlocking = blocking;
		blocking = newBlocking;
		boolean oldBlockingESet = blockingESet;
		blockingESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StreamSRIMetaDataPackage.SRI__BLOCKING, oldBlocking, blocking, !oldBlockingESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetBlocking()
	{
		boolean oldBlocking = blocking;
		boolean oldBlockingESet = blockingESet;
		blocking = BLOCKING_EDEFAULT;
		blockingESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, StreamSRIMetaDataPackage.SRI__BLOCKING, oldBlocking, BLOCKING_EDEFAULT, oldBlockingESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetBlocking()
	{
		return blockingESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Properties getKeywords()
	{
		return keywords;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetKeywords(Properties newKeywords, NotificationChain msgs)
	{
		Properties oldKeywords = keywords;
		keywords = newKeywords;
		if (eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, StreamSRIMetaDataPackage.SRI__KEYWORDS, oldKeywords, newKeywords);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setKeywords(Properties newKeywords)
	{
		if (newKeywords != keywords)
		{
			NotificationChain msgs = null;
			if (keywords != null)
				msgs = ((InternalEObject)keywords).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - StreamSRIMetaDataPackage.SRI__KEYWORDS, null, msgs);
			if (newKeywords != null)
				msgs = ((InternalEObject)newKeywords).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - StreamSRIMetaDataPackage.SRI__KEYWORDS, null, msgs);
			msgs = basicSetKeywords(newKeywords, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, StreamSRIMetaDataPackage.SRI__KEYWORDS, newKeywords, newKeywords));
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
			case StreamSRIMetaDataPackage.SRI__KEYWORDS:
				return basicSetKeywords(null, msgs);
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
			case StreamSRIMetaDataPackage.SRI__HVERSION:
				return getHversion();
			case StreamSRIMetaDataPackage.SRI__XSTART:
				return getXstart();
			case StreamSRIMetaDataPackage.SRI__XDELTA:
				return getXdelta();
			case StreamSRIMetaDataPackage.SRI__XUNITS:
				return getXunits();
			case StreamSRIMetaDataPackage.SRI__SUBSIZE:
				return getSubsize();
			case StreamSRIMetaDataPackage.SRI__YSTART:
				return getYstart();
			case StreamSRIMetaDataPackage.SRI__YDELTA:
				return getYdelta();
			case StreamSRIMetaDataPackage.SRI__YUNITS:
				return getYunits();
			case StreamSRIMetaDataPackage.SRI__MODE:
				return getMode();
			case StreamSRIMetaDataPackage.SRI__STREAM_ID:
				return getStreamID();
			case StreamSRIMetaDataPackage.SRI__BLOCKING:
				return isBlocking();
			case StreamSRIMetaDataPackage.SRI__KEYWORDS:
				return getKeywords();
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
			case StreamSRIMetaDataPackage.SRI__HVERSION:
				setHversion((Integer)newValue);
				return;
			case StreamSRIMetaDataPackage.SRI__XSTART:
				setXstart((Double)newValue);
				return;
			case StreamSRIMetaDataPackage.SRI__XDELTA:
				setXdelta((Double)newValue);
				return;
			case StreamSRIMetaDataPackage.SRI__XUNITS:
				setXunits((Short)newValue);
				return;
			case StreamSRIMetaDataPackage.SRI__SUBSIZE:
				setSubsize((Double)newValue);
				return;
			case StreamSRIMetaDataPackage.SRI__YSTART:
				setYstart((Double)newValue);
				return;
			case StreamSRIMetaDataPackage.SRI__YDELTA:
				setYdelta((Double)newValue);
				return;
			case StreamSRIMetaDataPackage.SRI__YUNITS:
				setYunits((Short)newValue);
				return;
			case StreamSRIMetaDataPackage.SRI__MODE:
				setMode((Short)newValue);
				return;
			case StreamSRIMetaDataPackage.SRI__STREAM_ID:
				setStreamID((String)newValue);
				return;
			case StreamSRIMetaDataPackage.SRI__BLOCKING:
				setBlocking((Boolean)newValue);
				return;
			case StreamSRIMetaDataPackage.SRI__KEYWORDS:
				setKeywords((Properties)newValue);
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
			case StreamSRIMetaDataPackage.SRI__HVERSION:
				unsetHversion();
				return;
			case StreamSRIMetaDataPackage.SRI__XSTART:
				unsetXstart();
				return;
			case StreamSRIMetaDataPackage.SRI__XDELTA:
				unsetXdelta();
				return;
			case StreamSRIMetaDataPackage.SRI__XUNITS:
				unsetXunits();
				return;
			case StreamSRIMetaDataPackage.SRI__SUBSIZE:
				unsetSubsize();
				return;
			case StreamSRIMetaDataPackage.SRI__YSTART:
				unsetYstart();
				return;
			case StreamSRIMetaDataPackage.SRI__YDELTA:
				unsetYdelta();
				return;
			case StreamSRIMetaDataPackage.SRI__YUNITS:
				unsetYunits();
				return;
			case StreamSRIMetaDataPackage.SRI__MODE:
				unsetMode();
				return;
			case StreamSRIMetaDataPackage.SRI__STREAM_ID:
				setStreamID(STREAM_ID_EDEFAULT);
				return;
			case StreamSRIMetaDataPackage.SRI__BLOCKING:
				unsetBlocking();
				return;
			case StreamSRIMetaDataPackage.SRI__KEYWORDS:
				setKeywords((Properties)null);
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
			case StreamSRIMetaDataPackage.SRI__HVERSION:
				return isSetHversion();
			case StreamSRIMetaDataPackage.SRI__XSTART:
				return isSetXstart();
			case StreamSRIMetaDataPackage.SRI__XDELTA:
				return isSetXdelta();
			case StreamSRIMetaDataPackage.SRI__XUNITS:
				return isSetXunits();
			case StreamSRIMetaDataPackage.SRI__SUBSIZE:
				return isSetSubsize();
			case StreamSRIMetaDataPackage.SRI__YSTART:
				return isSetYstart();
			case StreamSRIMetaDataPackage.SRI__YDELTA:
				return isSetYdelta();
			case StreamSRIMetaDataPackage.SRI__YUNITS:
				return isSetYunits();
			case StreamSRIMetaDataPackage.SRI__MODE:
				return isSetMode();
			case StreamSRIMetaDataPackage.SRI__STREAM_ID:
				return STREAM_ID_EDEFAULT == null ? streamID != null : !STREAM_ID_EDEFAULT.equals(streamID);
			case StreamSRIMetaDataPackage.SRI__BLOCKING:
				return isSetBlocking();
			case StreamSRIMetaDataPackage.SRI__KEYWORDS:
				return keywords != null;
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
		result.append(" (hversion: ");
		if (hversionESet) result.append(hversion); else result.append("<unset>");
		result.append(", xstart: ");
		if (xstartESet) result.append(xstart); else result.append("<unset>");
		result.append(", xdelta: ");
		if (xdeltaESet) result.append(xdelta); else result.append("<unset>");
		result.append(", xunits: ");
		if (xunitsESet) result.append(xunits); else result.append("<unset>");
		result.append(", subsize: ");
		if (subsizeESet) result.append(subsize); else result.append("<unset>");
		result.append(", ystart: ");
		if (ystartESet) result.append(ystart); else result.append("<unset>");
		result.append(", ydelta: ");
		if (ydeltaESet) result.append(ydelta); else result.append("<unset>");
		result.append(", yunits: ");
		if (yunitsESet) result.append(yunits); else result.append("<unset>");
		result.append(", mode: ");
		if (modeESet) result.append(mode); else result.append("<unset>");
		result.append(", streamID: ");
		result.append(streamID);
		result.append(", blocking: ");
		if (blockingESet) result.append(blocking); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

} //SRIImpl

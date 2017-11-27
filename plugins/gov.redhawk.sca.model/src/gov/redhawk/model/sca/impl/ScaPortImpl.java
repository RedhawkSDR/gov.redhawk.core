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
package gov.redhawk.model.sca.impl;

import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaPortContainer;
import gov.redhawk.model.sca.ScaTransport;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.commands.SetLocalAttributeCommand;
import gov.redhawk.model.sca.commands.SetStatusCommand;
import gov.redhawk.model.sca.commands.VersionedFeature;
import gov.redhawk.model.sca.commands.VersionedFeature.Transaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import mil.jpeojtrs.sca.scd.AbstractPort;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.omg.CORBA.SystemException;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Port</b></em>'.
 * 
 * @since 12.0
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link gov.redhawk.model.sca.impl.ScaPortImpl#getName <em>Name</em>}</li>
 * <li>{@link gov.redhawk.model.sca.impl.ScaPortImpl#getProfileObj <em>Profile Obj</em>}</li>
 * <li>{@link gov.redhawk.model.sca.impl.ScaPortImpl#getRepid <em>Repid</em>}</li>
 * <li>{@link gov.redhawk.model.sca.impl.ScaPortImpl#getSupportedTransports <em>Supported Transports</em>}</li>
 * <li>{@link gov.redhawk.model.sca.impl.ScaPortImpl#getPortContainer <em>Port Container</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ScaPortImpl< P extends AbstractPort, P2 extends org.omg.CORBA.Object > extends CorbaObjWrapperImpl<P2> implements ScaPort<P, P2> {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 18.0
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 18.0
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;
	/**
	 * The cached value of the '{@link #getProfileObj() <em>Profile Obj</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProfileObj()
	 * @generated
	 * @ordered
	 */
	protected P profileObj;
	/**
	 * The default value of the '{@link #getRepid() <em>Repid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepid()
	 * @generated
	 * @ordered
	 */
	protected static final String REPID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSupportedTransports() <em>Supported Transports</em>}' containment reference
	 * list.
	 * <!-- begin-user-doc -->
	 * @since 21.0
	 * <!-- end-user-doc -->
	 * @see #getSupportedTransports()
	 * @generated
	 * @ordered
	 */
	protected EList<ScaTransport> supportedTransports;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScaPortImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScaPackage.Literals.SCA_PORT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 18.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNameGen() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 18.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_PORT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	public P getProfileObj() {
		if (profileObj != null && profileObj.eIsProxy()) {
			InternalEObject oldProfileObj = (InternalEObject) profileObj;
			profileObj = (P) eResolveProxy(oldProfileObj);
			if (profileObj != oldProfileObj) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ScaPackage.SCA_PORT__PROFILE_OBJ, oldProfileObj, profileObj));
			}
		}
		return profileObj;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public P basicGetProfileObj() {
		return profileObj;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setProfileObj(P newProfileObj) {
		P oldProfileObj = profileObj;
		profileObj = newProfileObj;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_PORT__PROFILE_OBJ, oldProfileObj, profileObj));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public String getRepid() {
		// END GENERATED CODE
		if (getProfileObj() != null) {
			return getProfileObj().getRepID();
		} else {
			return null;
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ScaTransport> getSupportedTransports() {
		if (supportedTransports == null) {
			supportedTransports = new EObjectContainmentEList.Unsettable.Resolving<ScaTransport>(ScaTransport.class, this,
				ScaPackage.SCA_PORT__SUPPORTED_TRANSPORTS);
		}
		return supportedTransports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetSupportedTransports() {
		if (supportedTransports != null)
			((InternalEList.Unsettable< ? >) supportedTransports).unset();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetSupportedTransports() {
		return supportedTransports != null && ((InternalEList.Unsettable< ? >) supportedTransports).isSet();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScaPortContainer getPortContainer() {
		if (eContainerFeatureID() != ScaPackage.SCA_PORT__PORT_CONTAINER)
			return null;
		return (ScaPortContainer) eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaPortContainer basicGetPortContainer() {
		if (eContainerFeatureID() != ScaPackage.SCA_PORT__PORT_CONTAINER)
			return null;
		return (ScaPortContainer) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPortContainer(ScaPortContainer newPortContainer, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newPortContainer, ScaPackage.SCA_PORT__PORT_CONTAINER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPortContainer(ScaPortContainer newPortContainer) {
		if (newPortContainer != eInternalContainer() || (eContainerFeatureID() != ScaPackage.SCA_PORT__PORT_CONTAINER && newPortContainer != null)) {
			if (EcoreUtil.isAncestor(this, newPortContainer))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newPortContainer != null)
				msgs = ((InternalEObject) newPortContainer).eInverseAdd(this, ScaPackage.SCA_PORT_CONTAINER__PORTS, ScaPortContainer.class, msgs);
			msgs = basicSetPortContainer(newPortContainer, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_PORT__PORT_CONTAINER, newPortContainer, newPortContainer));
	}

	// END GENERATED CODE

	private final VersionedFeature supportedTransportsFeature = new VersionedFeature(this, ScaPackage.Literals.SCA_PORT__SUPPORTED_TRANSPORTS);

	// BEGIN GENERATED CODE

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<ScaTransport> fetchSupportedTransports(IProgressMonitor monitor) {
		// END GENERATED CODE
		if (isDisposed()) {
			return ECollections.emptyEList();
		}

		if (!isSetSupportedTransports()) {
			SubMonitor subMonitor = SubMonitor.convert(monitor, 2);
			Object portObj = fetchNarrowedObject(subMonitor.split(1));

			if (subMonitor.isCanceled()) {
				throw new OperationCanceledException();
			}

			Transaction transaction = supportedTransportsFeature.createTransaction();
			if (portObj instanceof ExtendedCF.NegotiablePort) {
				ExtendedCF.NegotiablePort port = (ExtendedCF.NegotiablePort) portObj;
				try {
					ExtendedCF.TransportInfo[] transportInfos = port.supportedTransports();
					List<ScaTransport> scaTransports = new ArrayList<>(transportInfos.length);
					for (ExtendedCF.TransportInfo transportInfo : transportInfos) {
						scaTransports.add(ScaFactory.eINSTANCE.createScaTransport(transportInfo));
					}
					transaction.addCommand(
						new SetLocalAttributeCommand(this, scaTransports, ScaPackage.Literals.SCA_PORT__SUPPORTED_TRANSPORTS, Status.OK_STATUS));
				} catch (SystemException e) {
					IStatus status = new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch supported transports.", e);
					transaction.addCommand(new SetStatusCommand<>(this, ScaPackage.Literals.SCA_PORT__SUPPORTED_TRANSPORTS, status));
				}
			} else {
				// Not a NegotiablePort. No transport info is available.
				transaction.addCommand(
					new SetLocalAttributeCommand(this, new ArrayList<>(), ScaPackage.Literals.SCA_PORT__SUPPORTED_TRANSPORTS, Status.OK_STATUS));
			}
			transaction.commit();
			subMonitor.worked(1);
		}

		return ScaModelCommand.runExclusive(this, () -> {
			return ECollections.unmodifiableEList(new BasicEList<>(getSupportedTransports()));
		});
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ScaPackage.SCA_PORT__PORT_CONTAINER:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetPortContainer((ScaPortContainer) otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ScaPackage.SCA_PORT__SUPPORTED_TRANSPORTS:
			return ((InternalEList< ? >) getSupportedTransports()).basicRemove(otherEnd, msgs);
		case ScaPackage.SCA_PORT__PORT_CONTAINER:
			return basicSetPortContainer(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
		case ScaPackage.SCA_PORT__PORT_CONTAINER:
			return eInternalContainer().eInverseRemove(this, ScaPackage.SCA_PORT_CONTAINER__PORTS, ScaPortContainer.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ScaPackage.SCA_PORT__NAME:
			return getName();
		case ScaPackage.SCA_PORT__PROFILE_OBJ:
			if (resolve)
				return getProfileObj();
			return basicGetProfileObj();
		case ScaPackage.SCA_PORT__REPID:
			return getRepid();
		case ScaPackage.SCA_PORT__SUPPORTED_TRANSPORTS:
			return getSupportedTransports();
		case ScaPackage.SCA_PORT__PORT_CONTAINER:
			if (resolve)
				return getPortContainer();
			return basicGetPortContainer();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ScaPackage.SCA_PORT__NAME:
			setName((String) newValue);
			return;
		case ScaPackage.SCA_PORT__PROFILE_OBJ:
			setProfileObj((P) newValue);
			return;
		case ScaPackage.SCA_PORT__SUPPORTED_TRANSPORTS:
			getSupportedTransports().clear();
			getSupportedTransports().addAll((Collection< ? extends ScaTransport>) newValue);
			return;
		case ScaPackage.SCA_PORT__PORT_CONTAINER:
			setPortContainer((ScaPortContainer) newValue);
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
	public void eUnset(int featureID) {
		switch (featureID) {
		case ScaPackage.SCA_PORT__NAME:
			setName(NAME_EDEFAULT);
			return;
		case ScaPackage.SCA_PORT__PROFILE_OBJ:
			setProfileObj((P) null);
			return;
		case ScaPackage.SCA_PORT__SUPPORTED_TRANSPORTS:
			unsetSupportedTransports();
			return;
		case ScaPackage.SCA_PORT__PORT_CONTAINER:
			setPortContainer((ScaPortContainer) null);
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
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case ScaPackage.SCA_PORT__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case ScaPackage.SCA_PORT__PROFILE_OBJ:
			return profileObj != null;
		case ScaPackage.SCA_PORT__REPID:
			return REPID_EDEFAULT == null ? getRepid() != null : !REPID_EDEFAULT.equals(getRepid());
		case ScaPackage.SCA_PORT__SUPPORTED_TRANSPORTS:
			return isSetSupportedTransports();
		case ScaPackage.SCA_PORT__PORT_CONTAINER:
			return basicGetPortContainer() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

	// END GENERATED CODE

	@Override
	public void fetchAttributes(IProgressMonitor monitor) {
		SubMonitor progress = SubMonitor.convert(monitor, 2);
		super.fetchAttributes(progress.split(1));
		fetchSupportedTransports(progress.split(1));
		progress.done();
	}

	@Override
	public void dispose() {
		super.dispose();
		setProfileObj(null);
	}

	@Override
	protected void notifyChanged(Notification msg) {
		super.notifyChanged(msg);
		if (!msg.isTouch()) {
			switch (msg.getFeatureID(ScaPort.class)) {
			case ScaPackage.SCA_PORT__PROFILE_OBJ:
				eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_PORT__NAME, null, getName()));
				eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_PORT__REPID, null, getRepid()));
				break;
			default:
				break;
			}
		}
	}

	@Override
	public boolean exists() {
		if (getCorbaObj() == null) {
			ScaModelCommand.execute(this, () -> {
				setStatus(ScaPackage.Literals.CORBA_OBJ_WRAPPER__OBJ, new Status(Status.ERROR, ScaModelPlugin.ID, "Unable to find port object", null));
			});
			return true;
		}
		try {
			if (getCorbaObj()._non_existent()) {
				ScaModelCommand.execute(this, () -> {
					setStatus(ScaPackage.Literals.CORBA_OBJ_WRAPPER__OBJ, new Status(Status.ERROR, ScaModelPlugin.ID, "Non existent port object", null));
				});
			}
		} catch (final SystemException e) {
			ScaModelCommand.execute(this, () -> {
				setStatus(ScaPackage.Literals.CORBA_OBJ_WRAPPER__OBJ, new Status(Status.ERROR, ScaModelPlugin.ID, "Exception contacting port", e));
			});
		}
		return true;
	}

	// BEGIN GENERATED CODE

} // ScaPortImpl

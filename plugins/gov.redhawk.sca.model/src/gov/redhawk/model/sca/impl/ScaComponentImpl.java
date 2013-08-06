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

import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.commands.MergeComponentDevicesCommand;
import gov.redhawk.model.sca.commands.ScaModelCommandWithResult;
import gov.redhawk.model.sca.commands.UnsetLocalAttributeCommand;
import gov.redhawk.model.sca.commands.VersionedFeature;
import gov.redhawk.model.sca.commands.VersionedFeature.Transaction;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.util.DceUuidUtil;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import CF.Application;
import CF.DeviceAssignmentType;
import CF.Resource;
import CF.ResourceHelper;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Component</b></em>'.
 * @since 12.0
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaComponentImpl#getComponentInstantiation <em>Component Instantiation</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaComponentImpl#getDevices <em>Devices</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaComponentImpl#getInstantiationIdentifier <em>Instantiation Identifier</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaComponentImpl#getWaveform <em>Waveform</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaComponentImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScaComponentImpl extends ScaAbstractComponentImpl<Resource> implements ScaComponent {

	/**
	 * The cached value of the '{@link #getComponentInstantiation() <em>Component Instantiation</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponentInstantiation()
	 * @generated
	 * @ordered
	 */
	protected SadComponentInstantiation componentInstantiation;
	/**
	 * This is true if the Component Instantiation reference has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean componentInstantiationESet;
	/**
	 * The cached value of the '{@link #getDevices() <em>Devices</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDevices()
	 * @generated
	 * @ordered
	 */
	protected EList<ScaDevice<?>> devices;
	/**
	 * The default value of the '{@link #getInstantiationIdentifier() <em>Instantiation Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInstantiationIdentifier()
	 * @generated
	 * @ordered
	 */
	protected static final String INSTANTIATION_IDENTIFIER_EDEFAULT = null;
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScaComponentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScaPackage.Literals.SCA_COMPONENT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SadComponentInstantiation getComponentInstantiation() {
		if (componentInstantiation != null && componentInstantiation.eIsProxy()) {
			InternalEObject oldComponentInstantiation = (InternalEObject)componentInstantiation;
			componentInstantiation = (SadComponentInstantiation)eResolveProxy(oldComponentInstantiation);
			if (componentInstantiation != oldComponentInstantiation) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ScaPackage.SCA_COMPONENT__COMPONENT_INSTANTIATION, oldComponentInstantiation, componentInstantiation));
			}
		}
		return componentInstantiation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SadComponentInstantiation basicGetComponentInstantiation() {
		return componentInstantiation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComponentInstantiation(SadComponentInstantiation newComponentInstantiation) {
		SadComponentInstantiation oldComponentInstantiation = componentInstantiation;
		componentInstantiation = newComponentInstantiation;
		boolean oldComponentInstantiationESet = componentInstantiationESet;
		componentInstantiationESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_COMPONENT__COMPONENT_INSTANTIATION, oldComponentInstantiation, componentInstantiation, !oldComponentInstantiationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetComponentInstantiation() {
		SadComponentInstantiation oldComponentInstantiation = componentInstantiation;
		boolean oldComponentInstantiationESet = componentInstantiationESet;
		componentInstantiation = null;
		componentInstantiationESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScaPackage.SCA_COMPONENT__COMPONENT_INSTANTIATION, oldComponentInstantiation, null, oldComponentInstantiationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetComponentInstantiation() {
		return componentInstantiationESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ScaDevice<?>> getDevices() {
		if (devices == null) {
			devices = new EObjectResolvingEList.Unsettable<ScaDevice<?>>(ScaDevice.class, this, ScaPackage.SCA_COMPONENT__DEVICES);
		}
		return devices;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetDevices() {
		if (devices != null) ((InternalEList.Unsettable<?>)devices).unset();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetDevices() {
		return devices != null && ((InternalEList.Unsettable<?>)devices).isSet();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String getInstantiationIdentifier() {
		// END GENERATED CODE
		if (identifier != null) {
			if (identifier.length() >= DceUuidUtil.DCE_UUID_LENGTH) {
				String dceString = identifier.substring(0, DceUuidUtil.DCE_UUID_LENGTH);
				if (DceUuidUtil.isValid(dceString)) {
					return dceString;
				}
			}
			int index = identifier.indexOf(':');
			if (index > 0) {
				return identifier.substring(0, index);
			} else {
				return identifier;
			}
		} else {
			return identifier;
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isSetInstantiationIdentifier() {
		// END GENERATED CODE
		return isSetIdentifier();
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaWaveform getWaveform() {
		if (eContainerFeatureID() != ScaPackage.SCA_COMPONENT__WAVEFORM) return null;
		return (ScaWaveform)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaWaveform basicGetWaveform() {
		if (eContainerFeatureID() != ScaPackage.SCA_COMPONENT__WAVEFORM) return null;
		return (ScaWaveform)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWaveform(ScaWaveform newWaveform, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newWaveform, ScaPackage.SCA_COMPONENT__WAVEFORM, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWaveform(ScaWaveform newWaveform) {
		if (newWaveform != eInternalContainer() || (eContainerFeatureID() != ScaPackage.SCA_COMPONENT__WAVEFORM && newWaveform != null)) {
			if (EcoreUtil.isAncestor(this, newWaveform))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newWaveform != null)
				msgs = ((InternalEObject)newWaveform).eInverseAdd(this, ScaPackage.SCA_WAVEFORM__COMPONENTS, ScaWaveform.class, msgs);
			msgs = basicSetWaveform(newWaveform, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_COMPONENT__WAVEFORM, newWaveform, newWaveform));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_COMPONENT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InterruptedException 
	 * @generated NOT
	 */
	@Override
	public void fetchAttributes(IProgressMonitor monitor) {
		SubMonitor subMonitor = SubMonitor.convert(monitor, 3);
		super.fetchAttributes(subMonitor.newChild(1));
		fetchProfileObject(subMonitor.newChild(1));
		fetchProperties(subMonitor.newChild(1));
		subMonitor.done();
	}

	@Override
	protected void internalFetchChildren(IProgressMonitor monitor) throws InterruptedException {
		SubMonitor subMonitor = SubMonitor.convert(monitor, 2);
		super.internalFetchChildren(subMonitor.newChild(1));
		internalFetchDevices(subMonitor.newChild(1));
		subMonitor.done();
	}

	@Override
	protected void notifyChanged(Notification msg) {
		super.notifyChanged(msg);
		switch (msg.getFeatureID(ScaComponent.class)) {
		case ScaPackage.SCA_COMPONENT__OBJ:
			unsetDevices();
			unsetProfileURI();
			break;
		case ScaPackage.SCA_COMPONENT__INSTANTIATION_IDENTIFIER:
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_COMPONENT__INSTANTIATION_IDENTIFIER, null, getInstantiationIdentifier()));
			break;
		case ScaPackage.SCA_COMPONENT__COMPONENT_INSTANTIATION:
			SadComponentInstantiation newValue = getComponentInstantiation();
			if (newValue != null) {
				setName(newValue.getUsageName());
			} else {
				setName(null);
			}
		default:
			break;
		}

	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @throws InterruptedException 
	 * @generated NOT
	 */
	public EList<ScaDevice< ? >> fetchDevices(IProgressMonitor monitor) {
		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetch Devices", 2);
		internalFetchDevices(subMonitor.newChild(1));
		ScaDevice< ? >[] devices = ScaModelCommandWithResult.execute(this, new ScaModelCommandWithResult<ScaDevice< ? >[]>() {

			public void execute() {
				setResult(getDevices().toArray(new ScaDevice< ? >[getDevices().size()]));
			}
		});
		if (devices != null) {
			SubMonitor deviceMonitor = subMonitor.newChild(1);
			deviceMonitor.beginTask("Refreshing Devices", devices.length);
			for (ScaDevice< ? > device : devices) {
				try {
	                device.refresh(deviceMonitor.newChild(1), RefreshDepth.SELF);
                } catch (InterruptedException e) {
	                // PASS
                }
			}
		}
		subMonitor.done();
		return getDevices();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ScaPackage.SCA_COMPONENT__WAVEFORM:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetWaveform((ScaWaveform)otherEnd, msgs);
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
			case ScaPackage.SCA_COMPONENT__WAVEFORM:
				return basicSetWaveform(null, msgs);
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
			case ScaPackage.SCA_COMPONENT__WAVEFORM:
				return eInternalContainer().eInverseRemove(this, ScaPackage.SCA_WAVEFORM__COMPONENTS, ScaWaveform.class, msgs);
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
			case ScaPackage.SCA_COMPONENT__COMPONENT_INSTANTIATION:
				if (resolve) return getComponentInstantiation();
				return basicGetComponentInstantiation();
			case ScaPackage.SCA_COMPONENT__DEVICES:
				return getDevices();
			case ScaPackage.SCA_COMPONENT__INSTANTIATION_IDENTIFIER:
				return getInstantiationIdentifier();
			case ScaPackage.SCA_COMPONENT__WAVEFORM:
				if (resolve) return getWaveform();
				return basicGetWaveform();
			case ScaPackage.SCA_COMPONENT__NAME:
				return getName();
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
			case ScaPackage.SCA_COMPONENT__COMPONENT_INSTANTIATION:
				setComponentInstantiation((SadComponentInstantiation)newValue);
				return;
			case ScaPackage.SCA_COMPONENT__DEVICES:
				getDevices().clear();
				getDevices().addAll((Collection<? extends ScaDevice<?>>)newValue);
				return;
			case ScaPackage.SCA_COMPONENT__WAVEFORM:
				setWaveform((ScaWaveform)newValue);
				return;
			case ScaPackage.SCA_COMPONENT__NAME:
				setName((String)newValue);
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
			case ScaPackage.SCA_COMPONENT__COMPONENT_INSTANTIATION:
				unsetComponentInstantiation();
				return;
			case ScaPackage.SCA_COMPONENT__DEVICES:
				unsetDevices();
				return;
			case ScaPackage.SCA_COMPONENT__WAVEFORM:
				setWaveform((ScaWaveform)null);
				return;
			case ScaPackage.SCA_COMPONENT__NAME:
				setName(NAME_EDEFAULT);
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
			case ScaPackage.SCA_COMPONENT__COMPONENT_INSTANTIATION:
				return isSetComponentInstantiation();
			case ScaPackage.SCA_COMPONENT__DEVICES:
				return isSetDevices();
			case ScaPackage.SCA_COMPONENT__INSTANTIATION_IDENTIFIER:
				return isSetInstantiationIdentifier();
			case ScaPackage.SCA_COMPONENT__WAVEFORM:
				return basicGetWaveform() != null;
			case ScaPackage.SCA_COMPONENT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

	private final VersionedFeature devicesRevision = new VersionedFeature(this, ScaPackage.Literals.SCA_COMPONENT__DEVICES);

	/**
	 * @since 14.0
	 */
	protected void internalFetchDevices(IProgressMonitor monitor) {
		if (isSetDevices()) {
			return;
		}
		Transaction transaction = devicesRevision.createTransaction();
		SubMonitor subMonitor = SubMonitor.convert(monitor, 5);

		final String componentIdentifier = getIdentifier();
		ScaWaveform waveform = getWaveform();

		if (waveform != null) {
			Application app = waveform.fetchNarrowedObject(subMonitor.newChild(1));

			if (app != null) {
				final Map<String, ScaDevice< ? >> newDevices = new HashMap<String, ScaDevice< ? >>();
				final DeviceAssignmentType[] devices = app.componentDevices();
				MultiStatus status = new MultiStatus(ScaModelPlugin.ID, Status.OK, "Problems fetching devices.", null);
				ScaDomainManager domMgr = waveform.getDomMgr();
				if (domMgr != null) {
					boolean calledFetch = false;
					for (final DeviceAssignmentType dat : devices) {
						if (dat.componentId.equals(componentIdentifier)) {
							ScaDeviceImpl< ? > device = (ScaDeviceImpl< ? >) domMgr.getDevice(dat.assignedDeviceId);
							if (device == null && !calledFetch) {
								domMgr.fetchDeviceManagers(subMonitor.newChild(1));
								SubMonitor devMgrMonitor = subMonitor.newChild(1);
								devMgrMonitor.beginTask("Refreshing Device Managers...", domMgr.getDeviceManagers().size());
								for (ScaDeviceManager devMgr : domMgr.getDeviceManagers()) {
									devMgr.fetchDevices(devMgrMonitor.newChild(1));
								}
								device = (ScaDeviceImpl< ? >) domMgr.getDevice(dat.assignedDeviceId);
								calledFetch = true;
							}
							if (device != null) {
								newDevices.put(dat.assignedDeviceId, device);
							} else {
								status.add(new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch device: " + dat.assignedDeviceId, null));
							}
						}
					}
					transaction.addCommand(new MergeComponentDevicesCommand(this, newDevices, status));
				} else {
					transaction.addCommand(new UnsetLocalAttributeCommand(this, null, ScaPackage.Literals.SCA_COMPONENT__DEVICES));
				}
			} else {
				transaction.addCommand(new UnsetLocalAttributeCommand(this, null, ScaPackage.Literals.SCA_COMPONENT__DEVICES));
			}
		} else {
			transaction.addCommand(new UnsetLocalAttributeCommand(this, null, ScaPackage.Literals.SCA_COMPONENT__DEVICES));
		}
		subMonitor.setWorkRemaining(1);
		transaction.commit();
		subMonitor.worked(1);
		subMonitor.done();
	}

	/**
	 * {@inheritDoc}
	 * @since 14.0
	 */
	@Override
	protected Resource narrow(final org.omg.CORBA.Object obj) {
		// END GENERATED CODE
		return ResourceHelper.narrow(obj);
		// BEGIN GENERATED CODE
	}

	@Override
	protected Class< ? extends Resource> getCorbaType() {
		return Resource.class;
	}

	@Override
    public URI fetchProfileURI(IProgressMonitor monitor) {
		// We can't fetch a components Profile URI so just return what we've got
	    return getProfileURI();
    }

} //ScaComponentImpl

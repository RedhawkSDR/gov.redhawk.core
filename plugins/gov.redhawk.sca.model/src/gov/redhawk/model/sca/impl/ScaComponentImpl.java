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
import gov.redhawk.model.sca.ScaDomainManagerFileSystem;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.commands.MergeComponentDevicesCommand;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.commands.ScaModelCommandWithResult;
import gov.redhawk.model.sca.commands.UnsetLocalAttributeCommand;
import gov.redhawk.model.sca.commands.VersionedFeature;
import gov.redhawk.model.sca.commands.VersionedFeature.Transaction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.util.DceUuidUtil;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
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
 * 
 * @since 12.0
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link gov.redhawk.model.sca.impl.ScaComponentImpl#getComponentInstantiation <em>Component
 * Instantiation</em>}</li>
 * <li>{@link gov.redhawk.model.sca.impl.ScaComponentImpl#getDevices <em>Devices</em>}</li>
 * <li>{@link gov.redhawk.model.sca.impl.ScaComponentImpl#getInstantiationIdentifier <em>Instantiation
 * Identifier</em>}</li>
 * <li>{@link gov.redhawk.model.sca.impl.ScaComponentImpl#getWaveform <em>Waveform</em>}</li>
 * <li>{@link gov.redhawk.model.sca.impl.ScaComponentImpl#getName <em>Name</em>}</li>
 * </ul>
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
	protected EList<ScaDevice< ? >> devices;
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
	@Override
	public SadComponentInstantiation getComponentInstantiation() {
		if (componentInstantiation != null && componentInstantiation.eIsProxy()) {
			InternalEObject oldComponentInstantiation = (InternalEObject) componentInstantiation;
			componentInstantiation = (SadComponentInstantiation) eResolveProxy(oldComponentInstantiation);
			if (componentInstantiation != oldComponentInstantiation) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ScaPackage.SCA_COMPONENT__COMPONENT_INSTANTIATION, oldComponentInstantiation,
						componentInstantiation));
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
	@Override
	public void setComponentInstantiation(SadComponentInstantiation newComponentInstantiation) {
		SadComponentInstantiation oldComponentInstantiation = componentInstantiation;
		componentInstantiation = newComponentInstantiation;
		boolean oldComponentInstantiationESet = componentInstantiationESet;
		componentInstantiationESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_COMPONENT__COMPONENT_INSTANTIATION, oldComponentInstantiation,
				componentInstantiation, !oldComponentInstantiationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetComponentInstantiation() {
		SadComponentInstantiation oldComponentInstantiation = componentInstantiation;
		boolean oldComponentInstantiationESet = componentInstantiationESet;
		componentInstantiation = null;
		componentInstantiationESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScaPackage.SCA_COMPONENT__COMPONENT_INSTANTIATION, oldComponentInstantiation, null,
				oldComponentInstantiationESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetComponentInstantiation() {
		return componentInstantiationESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ScaDevice< ? >> getDevices() {
		if (devices == null) {
			devices = new EObjectResolvingEList.Unsettable<ScaDevice< ? >>(ScaDevice.class, this, ScaPackage.SCA_COMPONENT__DEVICES);
		}
		return devices;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetDevices() {
		if (devices != null)
			((InternalEList.Unsettable< ? >) devices).unset();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetDevices() {
		return devices != null && ((InternalEList.Unsettable< ? >) devices).isSet();
	}

	/**
	 * @since 18.0
	 */
	public static String convertIdentifierToInstantiationID(String id) {
		// END GENERATED CODE
		if (id != null) {
			if (id.length() >= DceUuidUtil.DCE_UUID_LENGTH) {
				String dceString = id.substring(0, DceUuidUtil.DCE_UUID_LENGTH);
				if (DceUuidUtil.isValid(dceString)) {
					return dceString;
				}
			}
			int index = id.indexOf(':');
			if (index > 0) {
				return id.substring(0, index);
			} else {
				return id;
			}
		} else {
			return id;
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public String getInstantiationIdentifier() {
		// END GENERATED CODE
		return convertIdentifierToInstantiationID(identifier);
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
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
	@Override
	public ScaWaveform getWaveform() {
		if (eContainerFeatureID() != ScaPackage.SCA_COMPONENT__WAVEFORM)
			return null;
		return (ScaWaveform) eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaWaveform basicGetWaveform() {
		if (eContainerFeatureID() != ScaPackage.SCA_COMPONENT__WAVEFORM)
			return null;
		return (ScaWaveform) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWaveform(ScaWaveform newWaveform, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newWaveform, ScaPackage.SCA_COMPONENT__WAVEFORM, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setWaveform(ScaWaveform newWaveform) {
		if (newWaveform != eInternalContainer() || (eContainerFeatureID() != ScaPackage.SCA_COMPONENT__WAVEFORM && newWaveform != null)) {
			if (EcoreUtil.isAncestor(this, newWaveform))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newWaveform != null)
				msgs = ((InternalEObject) newWaveform).eInverseAdd(this, ScaPackage.SCA_WAVEFORM__COMPONENTS, ScaWaveform.class, msgs);
			msgs = basicSetWaveform(newWaveform, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_COMPONENT__WAVEFORM, newWaveform, newWaveform));
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
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_COMPONENT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @throws InterruptedException
	 * @generated NOT
	 */
	@Override
	public void fetchAttributes(IProgressMonitor monitor) {
		// END GENERATED CODE
		if (isDisposed()) {
			return;
		}

		SubMonitor subMonitor = SubMonitor.convert(monitor, 3);

		if (subMonitor.isCanceled()) {
			throw new OperationCanceledException();
		}
		super.fetchAttributes(subMonitor.newChild(1));

		if (subMonitor.isCanceled()) {
			throw new OperationCanceledException();
		}
		fetchProperties(subMonitor.newChild(1));

		subMonitor.done();
		// BEGIN GENERATED CODE
	}

	@Override
	protected void internalFetchChildren(IProgressMonitor monitor) throws InterruptedException {
		// END GENERATED CODE
		SubMonitor subMonitor = SubMonitor.convert(monitor, 2);

		if (subMonitor.isCanceled()) {
			throw new OperationCanceledException();
		}
		super.internalFetchChildren(subMonitor.newChild(1));

		if (subMonitor.isCanceled()) {
			throw new OperationCanceledException();
		}
		internalFetchDevices(subMonitor.newChild(1));

		subMonitor.done();
		// BEGIN GENERATED CODE
	}

	@Override
	protected void notifyChanged(Notification msg) {
		// END GENERATED CODE
		super.notifyChanged(msg);
		if (msg.isTouch()) {
			return;
		}
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
			break;
		default:
			break;
		}
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
		case ScaPackage.SCA_COMPONENT__WAVEFORM:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetWaveform((ScaWaveform) otherEnd, msgs);
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
			if (resolve)
				return getComponentInstantiation();
			return basicGetComponentInstantiation();
		case ScaPackage.SCA_COMPONENT__DEVICES:
			return getDevices();
		case ScaPackage.SCA_COMPONENT__INSTANTIATION_IDENTIFIER:
			return getInstantiationIdentifier();
		case ScaPackage.SCA_COMPONENT__WAVEFORM:
			if (resolve)
				return getWaveform();
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
			setComponentInstantiation((SadComponentInstantiation) newValue);
			return;
		case ScaPackage.SCA_COMPONENT__DEVICES:
			getDevices().clear();
			getDevices().addAll((Collection< ? extends ScaDevice< ? >>) newValue);
			return;
		case ScaPackage.SCA_COMPONENT__WAVEFORM:
			setWaveform((ScaWaveform) newValue);
			return;
		case ScaPackage.SCA_COMPONENT__NAME:
			setName((String) newValue);
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
			setWaveform((ScaWaveform) null);
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
		if (eIsProxy())
			return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
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
		// END GENERATED CODE
		if (isSetDevices() || isDisposed()) {
			return;
		}

		Transaction transaction = devicesRevision.createTransaction();
		SubMonitor subMonitor = SubMonitor.convert(monitor, 5);

		final String componentIdentifier = getIdentifier();
		ScaWaveform waveform = getWaveform();

		if (waveform != null) {
			if (subMonitor.isCanceled()) {
				throw new OperationCanceledException();
			}
			Application app = waveform.fetchNarrowedObject(subMonitor.newChild(1));

			if (app != null) {
				final Map<String, ScaDevice< ? >> newDevices = new HashMap<String, ScaDevice< ? >>();
				if (subMonitor.isCanceled()) {
					throw new OperationCanceledException();
				}
				final DeviceAssignmentType[] deviceAssignments = app.componentDevices();

				MultiStatus status = new MultiStatus(ScaModelPlugin.ID, Status.OK, "Problems fetching devices.", null);
				ScaDomainManager domMgr = waveform.getDomMgr();
				if (domMgr != null) {
					boolean calledFetch = false;
					for (final DeviceAssignmentType deviceAssignment : deviceAssignments) {
						if (deviceAssignment.componentId.equals(componentIdentifier)) {
							ScaDevice< ? > device = domMgr.getDevice(deviceAssignment.assignedDeviceId);
							if (device == null && !calledFetch) {
								if (subMonitor.isCanceled()) {
									throw new OperationCanceledException();
								}
								domMgr.fetchDeviceManagers(subMonitor.newChild(1), RefreshDepth.SELF);

								SubMonitor devMgrMonitor = subMonitor.newChild(1).setWorkRemaining(domMgr.getDeviceManagers().size());
								for (ScaDeviceManager devMgr : domMgr.getDeviceManagers()) {
									if (subMonitor.isCanceled()) {
										throw new OperationCanceledException();
									}
									devMgr.fetchDevices(devMgrMonitor.newChild(1), RefreshDepth.SELF);
								}
								device = domMgr.getDevice(deviceAssignment.assignedDeviceId);
								calledFetch = true;
							}
							if (device != null) {
								newDevices.put(deviceAssignment.assignedDeviceId, device);
							} else {
								status.add(new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch device: " + deviceAssignment.assignedDeviceId, null));
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
		// BEGIN GENERATED CODE
	}

	/**
	 * {@inheritDoc}
	 * 
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

	private final VersionedFeature profileURIFeature = new VersionedFeature(this, ScaPackage.Literals.PROFILE_OBJECT_WRAPPER__PROFILE_URI);

	@Override
	public URI fetchProfileURI(IProgressMonitor monitor) {
		// END GENERATED CODE
		if (isDisposed()) {
			return null;
		}
		if (isSetProfileURI()) {
			return getProfileURI();
		}

		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetch profile URI.", 2);
		ScaDomainManagerFileSystem fileSystem = ScaEcoreUtils.getFeature(this, ScaPackage.Literals.SCA_COMPONENT__WAVEFORM,
			ScaPackage.Literals.SCA_WAVEFORM__DOM_MGR, ScaPackage.Literals.SCA_DOMAIN_MANAGER__FILE_MANAGER);
		if (fileSystem == null) {
			subMonitor.done();
			return null;
		}

		if (subMonitor.isCanceled()) {
			throw new OperationCanceledException();
		}
		final String localProfile = fetchProfile(subMonitor.newChild(1));

		Transaction transaction = profileURIFeature.createTransaction();
		if (localProfile != null) {
			final URI newURI = fileSystem.createURI(localProfile);
			transaction.addCommand(new ScaModelCommand() {

				@Override
				public void execute() {
					setProfileURI(newURI);
				}
			});
			transaction.commit();
			subMonitor.worked(1);
		}

		subMonitor.done();
		return getProfileURI();
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 20.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<ScaDevice< ? >> fetchDevices(IProgressMonitor monitor, RefreshDepth depth) {
		// END GENERATED CODE
		if (isDisposed()) {
			return ECollections.emptyEList();
		}

		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetch Devices", 2);
		if (subMonitor.isCanceled()) {
			throw new OperationCanceledException();
		}
		internalFetchDevices(subMonitor.newChild(1));

		List<ScaDevice< ? >> devicesCopy = ScaModelCommandWithResult.execute(this, new ScaModelCommandWithResult<List<ScaDevice< ? >>>() {

			@Override
			public void execute() {
				setResult(new ArrayList<>(getDevices()));
			}
		});
		if (devicesCopy != null && depth != RefreshDepth.NONE) {
			SubMonitor deviceMonitor = subMonitor.newChild(1).setWorkRemaining(devicesCopy.size());
			for (ScaDevice< ? > device : devicesCopy) {
				try {
					if (subMonitor.isCanceled()) {
						throw new OperationCanceledException();
					}
					device.refresh(deviceMonitor.newChild(1), depth);
				} catch (InterruptedException e) {
					throw new OperationCanceledException();
				}
			}
		}

		subMonitor.done();
		if (devicesCopy != null) {
			return ECollections.unmodifiableEList(new BasicEList<ScaDevice< ? >>(devicesCopy));
		} else {
			return ECollections.emptyEList();
		}
		// BEGIN GENERATED CODE
	}

} // ScaComponentImpl

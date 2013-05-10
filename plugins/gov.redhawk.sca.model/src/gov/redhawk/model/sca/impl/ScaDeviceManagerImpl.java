/**
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */

// BEGIN GENERATED CODE
package gov.redhawk.model.sca.impl;

import gov.redhawk.model.sca.IRefreshable;
import gov.redhawk.model.sca.IStatusProvider;
import gov.redhawk.model.sca.ProfileObjectWrapper;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaDeviceManagerFileSystem;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaPortContainer;
import gov.redhawk.model.sca.ScaPropertyContainer;
import gov.redhawk.model.sca.ScaService;
import gov.redhawk.model.sca.commands.MergePortsCommand;
import gov.redhawk.model.sca.commands.MergePortsCommand.PortData;
import gov.redhawk.model.sca.commands.MergeServicesCommand;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.commands.ScaModelCommandWithResult;
import gov.redhawk.model.sca.commands.SetDeviceManagerFileSystemCommand;
import gov.redhawk.model.sca.commands.SetLocalAttributeCommand;
import gov.redhawk.model.sca.commands.SetStatusCommand;
import gov.redhawk.model.sca.commands.UnsetLocalAttributeCommand;
import gov.redhawk.model.sca.commands.VersionedFeature;
import gov.redhawk.model.sca.commands.VersionedFeature.Transaction;
import gov.redhawk.sca.util.PluginUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mil.jpeojtrs.sca.dcd.DcdPackage;
import mil.jpeojtrs.sca.dcd.DeviceConfiguration;
import mil.jpeojtrs.sca.scd.AbstractPort;
import mil.jpeojtrs.sca.scd.ScdPackage;
import mil.jpeojtrs.sca.spd.SpdPackage;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.Entry;
import org.eclipse.emf.ecore.util.FeatureMap.ValueListIterator;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.edit.command.DeleteCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.omg.CORBA.SystemException;

import CF.DataType;
import CF.Device;
import CF.DeviceManager;
import CF.DeviceManagerHelper;
import CF.DeviceManagerOperations;
import CF.ExecutableDeviceHelper;
import CF.FileSystem;
import CF.InvalidObjectReference;
import CF.LoadableDeviceHelper;
import CF.PortSupplierOperations;
import CF.PropertiesHolder;
import CF.UnknownProperties;
import CF.DeviceManagerPackage.ServiceType;
import CF.PortSupplierPackage.UnknownPort;
import CF.PropertySetPackage.InvalidConfiguration;
import CF.PropertySetPackage.PartialConfiguration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Device Manager</b></em>'.
 * @since 12.0
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaDeviceManagerImpl#getPorts <em>Ports</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaDeviceManagerImpl#getDevices <em>Devices</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaDeviceManagerImpl#getRootDevices <em>Root Devices</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaDeviceManagerImpl#getChildDevices <em>Child Devices</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaDeviceManagerImpl#getAllDevices <em>All Devices</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaDeviceManagerImpl#getFileSystem <em>File System</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaDeviceManagerImpl#getDomMgr <em>Dom Mgr</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaDeviceManagerImpl#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaDeviceManagerImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaDeviceManagerImpl#getServices <em>Services</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaDeviceManagerImpl#getProfile <em>Profile</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScaDeviceManagerImpl extends ScaPropertyContainerImpl<DeviceManager, DeviceConfiguration> implements ScaDeviceManager {

	/**
	 * The cached value of the '{@link #getPorts() <em>Ports</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPorts()
	 * @generated
	 * @ordered
	 */
	protected EList<ScaPort<?, ?>> ports;

	/**
	 * The cached value of the '{@link #getDevices() <em>Devices</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDevices()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap devices;

	/**
	 * The cached value of the '{@link #getFileSystem() <em>File System</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileSystem()
	 * @generated
	 * @ordered
	 */
	protected ScaDeviceManagerFileSystem fileSystem;

	/**
	 * This is true if the File System containment reference has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean fileSystemESet;

	/**
	 * The default value of the '{@link #getIdentifier() <em>Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdentifier()
	 * @generated
	 * @ordered
	 */
	protected static final String IDENTIFIER_EDEFAULT = null;

	private String identifier;

	/**
	 * This is true if the Identifier attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean identifierESet;

	/**
	 * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected static final String LABEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected String label = LABEL_EDEFAULT;

	/**
	 * This is true if the Label attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean labelESet;

	/**
	 * The cached value of the '{@link #getServices() <em>Services</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getServices()
	 * @generated
	 * @ordered
	 */
	protected EList<ScaService> services;

	/**
	 * The default value of the '{@link #getProfile() <em>Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProfile()
	 * @generated
	 * @ordered
	 */
	protected static final String PROFILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProfile() <em>Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProfile()
	 * @generated
	 * @ordered
	 */
	protected String profile = PROFILE_EDEFAULT;

	/**
	 * This is true if the Profile attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean profileESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScaDeviceManagerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScaPackage.Literals.SCA_DEVICE_MANAGER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ScaPort<?, ?>> getPorts() {
		if (ports == null) {
			ports = new EObjectContainmentWithInverseEList.Unsettable<ScaPort<?, ?>>(ScaPort.class, this, ScaPackage.SCA_DEVICE_MANAGER__PORTS, ScaPackage.SCA_PORT__PORT_CONTAINER);
		}
		return ports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetPorts() {
		if (ports != null) ((InternalEList.Unsettable<?>)ports).unset();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetPorts() {
		return ports != null && ((InternalEList.Unsettable<?>)ports).isSet();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getDevices() {
		if (devices == null) {
			devices = new BasicFeatureMap(this, ScaPackage.SCA_DEVICE_MANAGER__DEVICES);
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
	 * @generated
	 */
	public EList<ScaDevice<?>> getRootDevices() {
		return getDevices().list(ScaPackage.Literals.SCA_DEVICE_MANAGER__ROOT_DEVICES);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ScaDevice<?>> getChildDevices() {
		return getDevices().list(ScaPackage.Literals.SCA_DEVICE_MANAGER__CHILD_DEVICES);
	}

	private Adapter deviceListener = new AdapterImpl() {
		public void notifyChanged(Notification msg) {
			switch (msg.getFeatureID(ScaDeviceManager.class)) {
			case ScaPackage.SCA_DEVICE_MANAGER__DEVICES:
				Object newValue = null;
				Object oldValue = null;
				switch (msg.getEventType()) {
				case Notification.ADD: {
					Entry entry = (Entry) msg.getNewValue();
					newValue = entry.getValue();
					break;
				}
				case Notification.ADD_MANY: {
					Collection< ? > entries = (Collection< ? >) msg.getNewValue();
					List<Object> newValues = new ArrayList<Object>();
					for (Object obj : entries) {
						Entry entry = (Entry) obj;
						newValues.add(entry.getValue());
					}
					newValue = newValues;
				}
				case Notification.REMOVE: {
					Entry entry = (Entry) msg.getOldValue();
					oldValue = entry.getValue();
					break;
				}
				case Notification.REMOVE_MANY: {
					Collection< ? > entries = (Collection< ? >) msg.getOldValue();
					List<Object> oldValues = new ArrayList<Object>();
					for (Object obj : entries) {
						Entry entry = (Entry) obj;
						oldValues.add(entry.getValue());
					}
					oldValue = oldValues;
				}
				default:
					break;
				}
				eNotify(new ENotificationImpl(ScaDeviceManagerImpl.this, msg.getEventType(), ScaPackage.SCA_DEVICE_MANAGER__ALL_DEVICES, oldValue, newValue));
				break;
			default:
				break;
			}
		}
	};
	{
		eAdapters().add(deviceListener);
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isSetRootDevices() {
		return isSetDevices();
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isSetChildDevices() {
		return isSetDevices();
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<ScaDevice< ? >> getAllDevices() {
		// END GENERATED CODE
		List<ScaDevice< ? >> allDevices = new ArrayList<ScaDevice< ? >>();
		for (ValueListIterator<Object> i = getDevices().valueListIterator(); i.hasNext();) {
			allDevices.add((ScaDevice< ? >) i.next());
		}
		return new EcoreEList.UnmodifiableEList<ScaDevice< ? >>(this,
		        ScaPackage.eINSTANCE.getScaDeviceManager_AllDevices(),
		        allDevices.size(),
		        allDevices.toArray());
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void unsetAllDevices() {
		// END GENERATED CODE
		unsetDevices();
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean isSetAllDevices() {
		// END GENERATED CODE
		return isSetDevices();
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaDeviceManagerFileSystem getFileSystem() {
		return fileSystem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFileSystem(ScaDeviceManagerFileSystem newFileSystem, NotificationChain msgs) {
		ScaDeviceManagerFileSystem oldFileSystem = fileSystem;
		fileSystem = newFileSystem;
		boolean oldFileSystemESet = fileSystemESet;
		fileSystemESet = true;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_DEVICE_MANAGER__FILE_SYSTEM, oldFileSystem, newFileSystem, !oldFileSystemESet);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFileSystem(ScaDeviceManagerFileSystem newFileSystem) {
		if (newFileSystem != fileSystem) {
			NotificationChain msgs = null;
			if (fileSystem != null)
				msgs = ((InternalEObject)fileSystem).eInverseRemove(this, ScaPackage.SCA_DEVICE_MANAGER_FILE_SYSTEM__DEVICE_MANAGER, ScaDeviceManagerFileSystem.class, msgs);
			if (newFileSystem != null)
				msgs = ((InternalEObject)newFileSystem).eInverseAdd(this, ScaPackage.SCA_DEVICE_MANAGER_FILE_SYSTEM__DEVICE_MANAGER, ScaDeviceManagerFileSystem.class, msgs);
			msgs = basicSetFileSystem(newFileSystem, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else {
			boolean oldFileSystemESet = fileSystemESet;
			fileSystemESet = true;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_DEVICE_MANAGER__FILE_SYSTEM, newFileSystem, newFileSystem, !oldFileSystemESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicUnsetFileSystem(NotificationChain msgs) {
		ScaDeviceManagerFileSystem oldFileSystem = fileSystem;
		fileSystem = null;
		boolean oldFileSystemESet = fileSystemESet;
		fileSystemESet = false;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.UNSET, ScaPackage.SCA_DEVICE_MANAGER__FILE_SYSTEM, oldFileSystem, null, oldFileSystemESet);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetFileSystem() {
		if (fileSystem != null) {
			NotificationChain msgs = null;
			msgs = ((InternalEObject)fileSystem).eInverseRemove(this, ScaPackage.SCA_DEVICE_MANAGER_FILE_SYSTEM__DEVICE_MANAGER, ScaDeviceManagerFileSystem.class, msgs);
			msgs = basicUnsetFileSystem(msgs);
			if (msgs != null) msgs.dispatch();
		}
		else {
			boolean oldFileSystemESet = fileSystemESet;
			fileSystemESet = false;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.UNSET, ScaPackage.SCA_DEVICE_MANAGER__FILE_SYSTEM, null, null, oldFileSystemESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetFileSystem() {
		return fileSystemESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaDomainManager getDomMgr() {
		if (eContainerFeatureID() != ScaPackage.SCA_DEVICE_MANAGER__DOM_MGR) return null;
		return (ScaDomainManager)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaDomainManager basicGetDomMgr() {
		if (eContainerFeatureID() != ScaPackage.SCA_DEVICE_MANAGER__DOM_MGR) return null;
		return (ScaDomainManager)eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDomMgr(ScaDomainManager newDomMgr, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newDomMgr, ScaPackage.SCA_DEVICE_MANAGER__DOM_MGR, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDomMgr(ScaDomainManager newDomMgr) {
		if (newDomMgr != eInternalContainer() || (eContainerFeatureID() != ScaPackage.SCA_DEVICE_MANAGER__DOM_MGR && newDomMgr != null)) {
			if (EcoreUtil.isAncestor(this, newDomMgr))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newDomMgr != null)
				msgs = ((InternalEObject)newDomMgr).eInverseAdd(this, ScaPackage.SCA_DOMAIN_MANAGER__DEVICE_MANAGERS, ScaDomainManager.class, msgs);
			msgs = basicSetDomMgr(newDomMgr, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_DEVICE_MANAGER__DOM_MGR, newDomMgr, newDomMgr));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIdentifier(String newIdentifier) {
		String oldIdentifier = identifier;
		identifier = newIdentifier;
		boolean oldIdentifierESet = identifierESet;
		identifierESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_DEVICE_MANAGER__IDENTIFIER, oldIdentifier, identifier, !oldIdentifierESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetIdentifier() {
		String oldIdentifier = identifier;
		boolean oldIdentifierESet = identifierESet;
		identifier = IDENTIFIER_EDEFAULT;
		identifierESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScaPackage.SCA_DEVICE_MANAGER__IDENTIFIER, oldIdentifier, IDENTIFIER_EDEFAULT, oldIdentifierESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIdentifier() {
		return identifierESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabel(String newLabel) {
		String oldLabel = label;
		label = newLabel;
		boolean oldLabelESet = labelESet;
		labelESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_DEVICE_MANAGER__LABEL, oldLabel, label, !oldLabelESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetLabel() {
		String oldLabel = label;
		boolean oldLabelESet = labelESet;
		label = LABEL_EDEFAULT;
		labelESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScaPackage.SCA_DEVICE_MANAGER__LABEL, oldLabel, LABEL_EDEFAULT, oldLabelESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetLabel() {
		return labelESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ScaService> getServices() {
		if (services == null) {
			services = new EObjectContainmentEList.Unsettable<ScaService>(ScaService.class, this, ScaPackage.SCA_DEVICE_MANAGER__SERVICES);
		}
		return services;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetServices() {
		if (services != null) ((InternalEList.Unsettable<?>)services).unset();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetServices() {
		return services != null && ((InternalEList.Unsettable<?>)services).isSet();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getProfile() {
		return profile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProfile(String newProfile) {
		String oldProfile = profile;
		profile = newProfile;
		boolean oldProfileESet = profileESet;
		profileESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_DEVICE_MANAGER__PROFILE, oldProfile, profile, !oldProfileESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetProfile() {
		String oldProfile = profile;
		boolean oldProfileESet = profileESet;
		profile = PROFILE_EDEFAULT;
		profileESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScaPackage.SCA_DEVICE_MANAGER__PROFILE, oldProfile, PROFILE_EDEFAULT, oldProfileESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetProfile() {
		return profileESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ScaDevice< ? > getDevice(final String deviceId) {
		// END GENERATED CODE
		if (deviceId == null) {
			return null;
		}
		for (final ScaDevice< ? > dev : getAllDevices()) {
			if (deviceId.equals(dev.getIdentifier())) {
				return dev;
			}
		}
		return null;
		// BEGIN GENERATED CODE
	}

	public String deviceConfigurationProfile() {
		// END GENERATED CODE
		return getProfile();
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 14.0
	 */
	public FileSystem fileSys() {
		// END GENERATED CODE
		return getFileSystem().fetchNarrowedObject(null);
		// BEGIN GENERATED CODE
	}

	public String getComponentImplementationId(final String componentInstantiationId) {
		// END GENERATED CODE
		DeviceManager devMgr = fetchNarrowedObject(null);
		if (devMgr == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		return devMgr.getComponentImplementationId(componentInstantiationId);
		// BEGIN GENERATED CODE
	}

	public String label() {
		// END GENERATED CODE
		return getLabel();
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 14.0
	 */
	public void registerDevice(final Device registeringDevice) throws InvalidObjectReference {
		// END GENERATED CODE
		DeviceManager devMgr = fetchNarrowedObject(null);
		if (devMgr == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		devMgr.registerDevice(registeringDevice);
		fetchDevices(null);
		// BEGIN GENERATED CODE
	}

	public void registerService(final org.omg.CORBA.Object registeringService, final String name) throws InvalidObjectReference {
		// END GENERATED CODE
		DeviceManager devMgr = fetchNarrowedObject(null);
		if (devMgr == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		devMgr.registerService(registeringService, name);
		fetchServices(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 14.0
	 */
	public Device[] registeredDevices() {
		// END GENERATED CODE
		DeviceManager devMgr = fetchNarrowedObject(null);
		if (devMgr == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		return devMgr.registeredDevices();
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 14.0
	 */
	public ServiceType[] registeredServices() {
		// END GENERATED CODE
		DeviceManager devMgr = fetchNarrowedObject(null);
		if (devMgr == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		return devMgr.registeredServices();
		// BEGIN GENERATED CODE
	}

	public void shutdown() {
		// END GENERATED CODE
		DeviceManager devMgr = fetchNarrowedObject(null);
		if (devMgr != null) {
			devMgr.shutdown();
		}
		TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(this);
		if (domain != null) {
			Command command = DeleteCommand.create(domain, this);
			domain.getCommandStack().execute(command);
		} else {
			EcoreUtil.delete(this);
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 14.0
	 */
	public void unregisterDevice(final Device registeredDevice) throws InvalidObjectReference {
		// END GENERATED CODE
		DeviceManager devMgr = fetchNarrowedObject(null);
		if (devMgr == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		devMgr.unregisterDevice(registeredDevice);
		fetchDevices(null);
		// BEGIN GENERATED CODE
	}

	public void unregisterService(final org.omg.CORBA.Object registeredService, final String name) throws InvalidObjectReference {
		// END GENERATED CODE
		DeviceManager devMgr = fetchNarrowedObject(null);
		if (devMgr == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		devMgr.unregisterService(registeredService, name);
		// BEGIN GENERATED CODE
	}

	public String identifier() {
		// END GENERATED CODE
		return getIdentifier();
		// BEGIN GENERATED CODE
	}

	public void configure(final DataType[] configProperties) throws InvalidConfiguration, PartialConfiguration {
		// END GENERATED CODE
		DeviceManager devMgr = fetchNarrowedObject(null);
		if (devMgr == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		devMgr.configure(configProperties);
		fetchProperties(null);
		// BEGIN GENERATED CODE
	}

	public void query(final PropertiesHolder configProperties) throws UnknownProperties {
		// END GENERATED CODE
		DeviceManager devMgr = fetchNarrowedObject(null);
		if (devMgr != null) {
			devMgr.query(configProperties);
		}
		// BEGIN GENERATED CODE
	}

	public org.omg.CORBA.Object getPort(final String name) throws UnknownPort {
		// END GENERATED CODE
		ScaPort< ? , ? > port = getScaPort(name);
		if (port != null) {
			return port.fetchNarrowedObject(null);
		}
		return null;
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ScaPackage.SCA_DEVICE_MANAGER__PORTS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPorts()).basicAdd(otherEnd, msgs);
			case ScaPackage.SCA_DEVICE_MANAGER__FILE_SYSTEM:
				if (fileSystem != null)
					msgs = ((InternalEObject)fileSystem).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScaPackage.SCA_DEVICE_MANAGER__FILE_SYSTEM, null, msgs);
				return basicSetFileSystem((ScaDeviceManagerFileSystem)otherEnd, msgs);
			case ScaPackage.SCA_DEVICE_MANAGER__DOM_MGR:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetDomMgr((ScaDomainManager)otherEnd, msgs);
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
			case ScaPackage.SCA_DEVICE_MANAGER__PORTS:
				return ((InternalEList<?>)getPorts()).basicRemove(otherEnd, msgs);
			case ScaPackage.SCA_DEVICE_MANAGER__DEVICES:
				return ((InternalEList<?>)getDevices()).basicRemove(otherEnd, msgs);
			case ScaPackage.SCA_DEVICE_MANAGER__ROOT_DEVICES:
				return ((InternalEList<?>)getRootDevices()).basicRemove(otherEnd, msgs);
			case ScaPackage.SCA_DEVICE_MANAGER__CHILD_DEVICES:
				return ((InternalEList<?>)getChildDevices()).basicRemove(otherEnd, msgs);
			case ScaPackage.SCA_DEVICE_MANAGER__ALL_DEVICES:
				return ((InternalEList<?>)getAllDevices()).basicRemove(otherEnd, msgs);
			case ScaPackage.SCA_DEVICE_MANAGER__FILE_SYSTEM:
				return basicUnsetFileSystem(msgs);
			case ScaPackage.SCA_DEVICE_MANAGER__DOM_MGR:
				return basicSetDomMgr(null, msgs);
			case ScaPackage.SCA_DEVICE_MANAGER__SERVICES:
				return ((InternalEList<?>)getServices()).basicRemove(otherEnd, msgs);
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
			case ScaPackage.SCA_DEVICE_MANAGER__DOM_MGR:
				return eInternalContainer().eInverseRemove(this, ScaPackage.SCA_DOMAIN_MANAGER__DEVICE_MANAGERS, ScaDomainManager.class, msgs);
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
			case ScaPackage.SCA_DEVICE_MANAGER__PORTS:
				return getPorts();
			case ScaPackage.SCA_DEVICE_MANAGER__DEVICES:
				if (coreType) return getDevices();
				return ((FeatureMap.Internal)getDevices()).getWrapper();
			case ScaPackage.SCA_DEVICE_MANAGER__ROOT_DEVICES:
				return getRootDevices();
			case ScaPackage.SCA_DEVICE_MANAGER__CHILD_DEVICES:
				return getChildDevices();
			case ScaPackage.SCA_DEVICE_MANAGER__ALL_DEVICES:
				return getAllDevices();
			case ScaPackage.SCA_DEVICE_MANAGER__FILE_SYSTEM:
				return getFileSystem();
			case ScaPackage.SCA_DEVICE_MANAGER__DOM_MGR:
				if (resolve) return getDomMgr();
				return basicGetDomMgr();
			case ScaPackage.SCA_DEVICE_MANAGER__IDENTIFIER:
				return getIdentifier();
			case ScaPackage.SCA_DEVICE_MANAGER__LABEL:
				return getLabel();
			case ScaPackage.SCA_DEVICE_MANAGER__SERVICES:
				return getServices();
			case ScaPackage.SCA_DEVICE_MANAGER__PROFILE:
				return getProfile();
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
			case ScaPackage.SCA_DEVICE_MANAGER__PORTS:
				getPorts().clear();
				getPorts().addAll((Collection<? extends ScaPort<?, ?>>)newValue);
				return;
			case ScaPackage.SCA_DEVICE_MANAGER__DEVICES:
				((FeatureMap.Internal)getDevices()).set(newValue);
				return;
			case ScaPackage.SCA_DEVICE_MANAGER__ROOT_DEVICES:
				getRootDevices().clear();
				getRootDevices().addAll((Collection<? extends ScaDevice<?>>)newValue);
				return;
			case ScaPackage.SCA_DEVICE_MANAGER__CHILD_DEVICES:
				getChildDevices().clear();
				getChildDevices().addAll((Collection<? extends ScaDevice<?>>)newValue);
				return;
			case ScaPackage.SCA_DEVICE_MANAGER__ALL_DEVICES:
				getAllDevices().clear();
				getAllDevices().addAll((Collection<? extends ScaDevice<?>>)newValue);
				return;
			case ScaPackage.SCA_DEVICE_MANAGER__FILE_SYSTEM:
				setFileSystem((ScaDeviceManagerFileSystem)newValue);
				return;
			case ScaPackage.SCA_DEVICE_MANAGER__DOM_MGR:
				setDomMgr((ScaDomainManager)newValue);
				return;
			case ScaPackage.SCA_DEVICE_MANAGER__IDENTIFIER:
				setIdentifier((String)newValue);
				return;
			case ScaPackage.SCA_DEVICE_MANAGER__LABEL:
				setLabel((String)newValue);
				return;
			case ScaPackage.SCA_DEVICE_MANAGER__SERVICES:
				getServices().clear();
				getServices().addAll((Collection<? extends ScaService>)newValue);
				return;
			case ScaPackage.SCA_DEVICE_MANAGER__PROFILE:
				setProfile((String)newValue);
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
			case ScaPackage.SCA_DEVICE_MANAGER__PORTS:
				unsetPorts();
				return;
			case ScaPackage.SCA_DEVICE_MANAGER__DEVICES:
				unsetDevices();
				return;
			case ScaPackage.SCA_DEVICE_MANAGER__ROOT_DEVICES:
				getRootDevices().clear();
				return;
			case ScaPackage.SCA_DEVICE_MANAGER__CHILD_DEVICES:
				getChildDevices().clear();
				return;
			case ScaPackage.SCA_DEVICE_MANAGER__ALL_DEVICES:
				unsetAllDevices();
				return;
			case ScaPackage.SCA_DEVICE_MANAGER__FILE_SYSTEM:
				unsetFileSystem();
				return;
			case ScaPackage.SCA_DEVICE_MANAGER__DOM_MGR:
				setDomMgr((ScaDomainManager)null);
				return;
			case ScaPackage.SCA_DEVICE_MANAGER__IDENTIFIER:
				unsetIdentifier();
				return;
			case ScaPackage.SCA_DEVICE_MANAGER__LABEL:
				unsetLabel();
				return;
			case ScaPackage.SCA_DEVICE_MANAGER__SERVICES:
				unsetServices();
				return;
			case ScaPackage.SCA_DEVICE_MANAGER__PROFILE:
				unsetProfile();
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
			case ScaPackage.SCA_DEVICE_MANAGER__PORTS:
				return isSetPorts();
			case ScaPackage.SCA_DEVICE_MANAGER__DEVICES:
				return isSetDevices();
			case ScaPackage.SCA_DEVICE_MANAGER__ROOT_DEVICES:
				return !getRootDevices().isEmpty();
			case ScaPackage.SCA_DEVICE_MANAGER__CHILD_DEVICES:
				return !getChildDevices().isEmpty();
			case ScaPackage.SCA_DEVICE_MANAGER__ALL_DEVICES:
				return isSetAllDevices();
			case ScaPackage.SCA_DEVICE_MANAGER__FILE_SYSTEM:
				return isSetFileSystem();
			case ScaPackage.SCA_DEVICE_MANAGER__DOM_MGR:
				return basicGetDomMgr() != null;
			case ScaPackage.SCA_DEVICE_MANAGER__IDENTIFIER:
				return isSetIdentifier();
			case ScaPackage.SCA_DEVICE_MANAGER__LABEL:
				return isSetLabel();
			case ScaPackage.SCA_DEVICE_MANAGER__SERVICES:
				return isSetServices();
			case ScaPackage.SCA_DEVICE_MANAGER__PROFILE:
				return isSetProfile();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == PortSupplierOperations.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == DeviceManagerOperations.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == ScaPortContainer.class) {
			switch (derivedFeatureID) {
				case ScaPackage.SCA_DEVICE_MANAGER__PORTS: return ScaPackage.SCA_PORT_CONTAINER__PORTS;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == PortSupplierOperations.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == DeviceManagerOperations.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == ScaPortContainer.class) {
			switch (baseFeatureID) {
				case ScaPackage.SCA_PORT_CONTAINER__PORTS: return ScaPackage.SCA_DEVICE_MANAGER__PORTS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (devices: ");
		result.append(devices);
		result.append(", identifier: ");
		if (identifierESet) result.append(identifier); else result.append("<unset>");
		result.append(", label: ");
		if (labelESet) result.append(label); else result.append("<unset>");
		result.append(", profile: ");
		if (profileESet) result.append(profile); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

	/**
	 * @since 14.0
	 */
	@Override
	protected DeviceManager narrow(final org.omg.CORBA.Object obj) {
		// END GENERATED CODE
		return DeviceManagerHelper.narrow(obj);
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<ScaDevice< ? >> fetchDevices(IProgressMonitor monitor) {
		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching Devices", 2);
		internalFetchDevices(subMonitor.newChild(1));
		IRefreshable[] array = ScaModelCommandWithResult.execute(this, new ScaModelCommandWithResult<IRefreshable[]>() {

			public void execute() {
				setResult(getRootDevices().toArray(new IRefreshable[getRootDevices().size()]));
			}

		});
		if (array != null) {
			SubMonitor portRefresh = subMonitor.newChild(1);
			portRefresh.beginTask("Refreshing devices ", array.length);
			for (IRefreshable element : array) {
				try {
					element.refresh(portRefresh.newChild(1), RefreshDepth.SELF);
				} catch (InterruptedException e) {
					// PASS
				}
			}
		}
		subMonitor.done();
		return getAllDevices();
	}

	private final VersionedFeature devicesRevision = new VersionedFeature(this, ScaPackage.Literals.SCA_DEVICE_MANAGER__DEVICES);

	/**
	 * @since 14.0
	 */
	protected void internalFetchDevices(IProgressMonitor monitor) {
		// END GENERATED CODE
		SubMonitor subMonitor = SubMonitor.convert(monitor, 4);
		final DeviceManager devMgr = fetchNarrowedObject(subMonitor.newChild(1));

		Transaction transaction = devicesRevision.createTransaction();

		if (devMgr != null) {
			// Setup new Device Map

			Device[] regDevices = null;
			try {
				regDevices = devMgr.registeredDevices();
				List<Device> newRootDevices = new ArrayList<Device>();
				List<Device> newChildDevices = new ArrayList<Device>();
				if (regDevices != null) {
					for (Device device : regDevices) {
						if (device.compositeDevice() == null) {
							newRootDevices.add(device);
						} else {
							newChildDevices.add(device);
						}
					}
				}
				transaction.append(fetchDevices(subMonitor.newChild(1),
				        this,
				        getRootDevices(),
				        newRootDevices.toArray(new Device[newRootDevices.size()])));
				transaction.append(fetchDevices(subMonitor.newChild(1),
				        this,
				        getChildDevices(),
				        newChildDevices.toArray(new Device[newChildDevices.size()])));
				transaction.append(new SetStatusCommand<IStatusProvider>(this, ScaPackage.Literals.SCA_DEVICE_MANAGER__DEVICES, null));
			} catch (SystemException e) {
				IStatus status = new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch devices.", e);
				transaction.append(new UnsetLocalAttributeCommand(this, status, ScaPackage.Literals.SCA_DEVICE_MANAGER__DEVICES));
			}
		} else {
			transaction.append(new UnsetLocalAttributeCommand(this, null, ScaPackage.Literals.SCA_DEVICE_MANAGER__DEVICES));
		}
		transaction.commit();

		// We must ALWAYS fetch device SELF attributes since the REFRESH FULL will fail otherwise
		ScaDevice< ? >[] deviceArray = ScaModelCommandWithResult.execute(this, new ScaModelCommandWithResult<ScaDevice< ? >[]>() {

			public void execute() {
				EList<ScaDevice< ? >> allDevices = getAllDevices();
				setResult(allDevices.toArray(new ScaDevice< ? >[allDevices.size()]));
			}
		});
		if (deviceArray != null) {
			SubMonitor deviceMonitor = subMonitor.newChild(1);
			deviceMonitor.beginTask("Fetching device ids", deviceArray.length);
			for (ScaDevice< ? > device : deviceArray) {
				device.fetchIdentifier(deviceMonitor.newChild(1));
			}
		}
		subMonitor.done();
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 17.0
	 */
	protected static class DeviceData {
		private final Device dev;
		private final EClass deviceType;

		/**
		 * @since 17.0
		 */
		private DeviceData(Device dev, EClass deviceType) {
			super();
			this.dev = dev;
			this.deviceType = deviceType;
		}
	}

	/**
	 * @since 17.0
	 */
	protected Command fetchDevices(IProgressMonitor monitor,
	        final ScaPropertyContainer< ? , ? > container,
	        final List<ScaDevice< ? >> deviceList,
	        Device[] corbaDevices) {
		SubMonitor subMonitor = SubMonitor.convert(monitor, 2);
		final Map<String, DeviceData> newDevices = new HashMap<String, DeviceData>();
		if (corbaDevices != null) {
			SubMonitor deviceMonitor = subMonitor.newChild(1);
			deviceMonitor.beginTask("Init Device", corbaDevices.length);
			for (final Device dev : corbaDevices) {
				EClass type = getType(dev);

				newDevices.put(dev.toString(), new DeviceData(dev, type));
			}
		} else {
			subMonitor.setWorkRemaining(1);
		}

		return new ScaModelCommand() {

			public void execute() {
				mergeDevices(deviceList, newDevices);
			}

		};
	}

	/**
	 * @since 17.0
	 */
	protected void mergeDevices(final List<ScaDevice< ? >> deviceList, final Map<String, DeviceData> newDevices) {
		// END GENERATED CODE
		// Perform Actions
		// Setup Current Device List
		final Map<String, ScaDevice< ? >> scaDevices = new HashMap<String, ScaDevice< ? >>();
		for (final ScaDevice< ? > device : deviceList) {
			scaDevices.put(device.getIor(), device);
		}

		// Setup Remove devices list that have been deleted
		final Map<String, ScaDevice< ? >> removeDevices = new HashMap<String, ScaDevice< ? >>();
		removeDevices.putAll(scaDevices);
		removeDevices.keySet().removeAll(newDevices.keySet());

		// Remove Duplicates
		newDevices.keySet().removeAll(scaDevices.keySet());

		// Remove Devices
		deviceList.removeAll(removeDevices.values());

		// Add devices
		for (DeviceData dev : newDevices.values()) {
			ScaDevice< ? > newDevice = createType(dev.deviceType);
			deviceList.add(newDevice);
			newDevice.setCorbaObj(dev.dev);
		}

		// Do this to ensure we always set the list
		if (newDevices.isEmpty() && deviceList.isEmpty()) {
			deviceList.clear();
		}
	}

	/**
	 * @since 17.0
	 */
	protected EClass getType(Device dev) {
		EClass type = ScaPackage.Literals.SCA_DEVICE;
		if (dev._is_a(ExecutableDeviceHelper.id())) {
			type = ScaPackage.Literals.SCA_EXECUTABLE_DEVICE;
		} else if (dev._is_a(LoadableDeviceHelper.id())) {
			type = ScaPackage.Literals.SCA_LOADABLE_DEVICE;
		}
	    return type;
    }

	/**
	 * @since 17.0
	 */
	protected ScaDevice< ? > createType(EClass type) {
		return (ScaDevice< ? >) ScaFactory.eINSTANCE.create(type);
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @throws InterruptedException
	 * @generated NOT
	 */
	public EList<ScaPort< ? , ? >> fetchPorts(IProgressMonitor monitor) {
		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching ports", 2);
		internalFetchPorts(subMonitor.newChild(1));
		ScaPort< ? , ? >[] ports = ScaModelCommandWithResult.execute(this, new ScaModelCommandWithResult<ScaPort< ? , ? >[]>() {

			public void execute() {
				setResult(getPorts().toArray(new ScaPort< ? , ? >[getPorts().size()]));
			}

		});
		if (ports != null) {
			SubMonitor portRefresh = subMonitor.newChild(1);
			portRefresh.beginTask("Refreshing state of ports", ports.length);
			for (ScaPort< ? , ? > port : ports) {
				try {
					port.refresh(portRefresh.newChild(1), RefreshDepth.SELF);
				} catch (InterruptedException e) {
					// PASS
				}
			}
		}
		subMonitor.done();
		return getPorts();
	}

	private static final EStructuralFeature[] PORTS_GROUP_PATH = {
	        ScaPackage.Literals.PROFILE_OBJECT_WRAPPER__PROFILE_OBJ,
	        DcdPackage.Literals.DEVICE_CONFIGURATION__DEVICE_MANAGER_SOFT_PKG,
	        DcdPackage.Literals.DEVICE_MANAGER_SOFT_PKG__SOFT_PKG,
	        SpdPackage.Literals.SOFT_PKG__DESCRIPTOR,
	        SpdPackage.Literals.DESCRIPTOR__COMPONENT,
	        ScdPackage.Literals.SOFTWARE_COMPONENT__COMPONENT_FEATURES,
	        ScdPackage.Literals.COMPONENT_FEATURES__PORTS,
	        ScdPackage.Literals.PORTS__GROUP
	};

	private final VersionedFeature portsRevision = new VersionedFeature(this, ScaPackage.Literals.SCA_PORT_CONTAINER__PORTS);

	/**
	 * @since 14.0
	 */
	protected void internalFetchPorts(IProgressMonitor monitor) {
		// END GENERATED CODE
		if (isSetPorts()) {
			return;
		}
		SubMonitor subMonitor = SubMonitor.convert(monitor, 4);
		Transaction transaction = portsRevision.createTransaction();
		DeviceManager currentObj = fetchNarrowedObject(subMonitor.newChild(1));
		if (currentObj != null) {

			DeviceConfiguration localProfileObj = fetchProfileObject(subMonitor.newChild(1));
			// Load all of the ports
			if (localProfileObj != null) {
				final MultiStatus fetchPortsStatus = new MultiStatus(ScaModelPlugin.ID, Status.OK, "Fetch ports status.", null);
				List<MergePortsCommand.PortData> newPorts = new ArrayList<MergePortsCommand.PortData>();
				FeatureMap portGroup = ScaEcoreUtils.getFeature(this, PORTS_GROUP_PATH);
				if (portGroup != null) {
					for (ValueListIterator<Object> i = portGroup.valueListIterator(); i.hasNext();) {
						Object portObj = i.next();
						if (portObj instanceof AbstractPort) {
							AbstractPort abstractPort = (AbstractPort) portObj;
							String portName = abstractPort.getName();
							try {
								org.omg.CORBA.Object portCorbaObj = currentObj.getPort(portName);
								newPorts.add(new PortData(abstractPort, portCorbaObj));
							} catch (UnknownPort e) {
								fetchPortsStatus.add(new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch port '" + portName + "'", e));
							} catch (SystemException e) {
								fetchPortsStatus.add(new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch port '" + portName + "'", e));
							}

						}
					}
				}
				// Perform the actions
				MergePortsCommand command = new MergePortsCommand(this, newPorts, fetchPortsStatus);
				transaction.addCommand(command);
			} else {
				transaction.addCommand(new UnsetLocalAttributeCommand(this, null, ScaPackage.Literals.SCA_PORT_CONTAINER__PORTS));
			}
		} else {
			transaction.addCommand(new UnsetLocalAttributeCommand(this, null, ScaPackage.Literals.SCA_PORT_CONTAINER__PORTS));
		}

		subMonitor.setWorkRemaining(1);
		transaction.commit();
		subMonitor.worked(1);
		subMonitor.done();

		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<ScaService> fetchServices(IProgressMonitor monitor) {
		internalFetchServices(monitor);
		return getServices();
	}

	private final VersionedFeature serviceFeature = new VersionedFeature(this, ScaPackage.Literals.SCA_DEVICE_MANAGER__SERVICES);

	/**
	 * @since 14.0
	 */
	protected void internalFetchServices(IProgressMonitor monitor) {
		// END GENERATED CODE
		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching services", 3);
		final DeviceManager currentObj = fetchNarrowedObject(subMonitor.newChild(1));
		Transaction transaction = serviceFeature.createTransaction();

		if (currentObj != null) {
			// Setup new service map
			final Map<String, ServiceType> newServices = new HashMap<String, ServiceType>();
			try {
				ServiceType[] serviceTypes = currentObj.registeredServices();
				SubMonitor serviceMonitor = subMonitor.newChild(1);
				serviceMonitor.beginTask("Init Services", serviceTypes.length);
				for (final ServiceType type : serviceTypes) {
					if (type != null && type.serviceObject != null) {
						newServices.put(type.serviceObject.toString(), type);
					}
					serviceMonitor.worked(1);
				}
				transaction.addCommand(new MergeServicesCommand(this, newServices));
			} catch (SystemException e) {
				Status status = new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch services.", e);
				transaction.addCommand(new UnsetLocalAttributeCommand(this, status, ScaPackage.Literals.SCA_DEVICE_MANAGER__SERVICES));
			}
		} else {
			transaction.addCommand(new UnsetLocalAttributeCommand(this, null, ScaPackage.Literals.SCA_DEVICE_MANAGER__SERVICES));
		}
		subMonitor.setWorkRemaining(1);
		transaction.commit();
		subMonitor.worked(1);
		subMonitor.done();
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ScaService registerScaService(org.omg.CORBA.Object registeringService, String name) throws InvalidObjectReference {
		// END GENERATED CODE
		DeviceManager devMgr = fetchNarrowedObject(null);
		if (devMgr == null) {
			throw new IllegalStateException("CORBA Obj DeviceManager is null");
		}
		devMgr.registerService(registeringService, name);
		fetchServices(null);

		ScaService retVal = getScaService(name);
		return retVal;
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ScaPort< ? , ? > getScaPort(String name) {
		// END GENERATED CODE
		if (name == null) {
			return null;
		}
		for (ScaPort< ? , ? > port : getPorts()) {
			if (name.equals(port.getName())) {
				return port;
			}
		}
		return null;
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ScaService getScaService(String name) {
		// END GENERATED CODE
		if (name == null) {
			return null;
		}
		for (ScaService service : getServices()) {
			if (name.equals(service.getName())) {
				return service;
			}
		}
		return null;
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InterruptedException
	 * @generated NOT
	 */
	@Override
	public void fetchAttributes(IProgressMonitor monitor) {
		SubMonitor subMonitor = SubMonitor.convert(monitor, 6);
		fetchNarrowedObject(subMonitor.newChild(1));
		fetchFileSystem(subMonitor.newChild(1));
		super.fetchAttributes(subMonitor.newChild(1));
		fetchLocalAttributes(subMonitor.newChild(1));
		fetchProfileObject(subMonitor.newChild(1));
		fetchProperties(subMonitor.newChild(1));
		subMonitor.done();
	}

	@Override
	protected void internalFetchChildren(IProgressMonitor monitor) throws InterruptedException {
		SubMonitor subMonitor = SubMonitor.convert(monitor, 3);
		internalFetchDevices(subMonitor.newChild(1));
		internalFetchPorts(subMonitor.newChild(1));
		internalFetchServices(subMonitor.newChild(1));
		subMonitor.done();
	}

	private final VersionedFeature fileSystemRevision = new VersionedFeature(this, ScaPackage.Literals.SCA_DEVICE_MANAGER__FILE_SYSTEM);

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ScaDeviceManagerFileSystem fetchFileSystem(IProgressMonitor monitor) {
		if (isSetFileSystem()) {
			return getFileSystem();
		}
		Transaction transaction = fileSystemRevision.createTransaction();
		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching File system", 3);
		final DeviceManager localObj = fetchNarrowedObject(subMonitor.newChild(1));
		if (localObj != null) {
			try {
				FileSystem fileSys = localObj.fileSys();
				transaction.addCommand(new SetDeviceManagerFileSystemCommand(this, fileSys));
			} catch (SystemException e) {
				transaction.addCommand(new UnsetLocalAttributeCommand(this,
				        new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch file sys.", e),
				        ScaPackage.Literals.SCA_DEVICE_MANAGER__FILE_SYSTEM));
			}
		} else {
			transaction.addCommand(new UnsetLocalAttributeCommand(this, null, ScaPackage.Literals.SCA_DEVICE_MANAGER__FILE_SYSTEM));
		}
		subMonitor.worked(1);
		transaction.commit();
		subMonitor.worked(1);
		subMonitor.done();
		return getFileSystem();
	}

	private final VersionedFeature idFeature = new VersionedFeature(this, ScaPackage.Literals.SCA_DEVICE_MANAGER__IDENTIFIER);

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String fetchIdentifier(IProgressMonitor monitor) {
		// END GENERATED CODE
		if (isSetIdentifier()) {
			return getIdentifier();
		}
		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching identifier", 3);
		DeviceManager localObj = fetchNarrowedObject(subMonitor.newChild(1));
		Transaction transaction = idFeature.createTransaction();
		if (localObj != null) {
			try {
				String newValue = localObj.identifier();
				subMonitor.worked(1);
				transaction.addCommand(new SetLocalAttributeCommand(this, newValue, ScaPackage.Literals.SCA_DEVICE_MANAGER__IDENTIFIER));
			} catch (SystemException e) {
				transaction.addCommand(new UnsetLocalAttributeCommand(this,
				        new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch identifier", e),
				        ScaPackage.Literals.SCA_DEVICE_MANAGER__IDENTIFIER));
			}
		} else {
			transaction.addCommand(new UnsetLocalAttributeCommand(this, null, ScaPackage.Literals.SCA_DEVICE_MANAGER__IDENTIFIER));
		}
		transaction.commit();
		subMonitor.done();
		return getIdentifier();
		// BEGIN GENERATED CODE
	}

	private final VersionedFeature labelFeature = new VersionedFeature(this, ScaPackage.Literals.SCA_DEVICE_MANAGER__LABEL);

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String fetchLabel(IProgressMonitor monitor) {
		// END GENERATED CODE
		if (isSetLabel()) {
			return getLabel();
		}
		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching label", 3);
		DeviceManager localObj = fetchNarrowedObject(subMonitor.newChild(1));
		Transaction transaction = labelFeature.createTransaction();
		if (localObj != null) {
			try {
				String newValue = localObj.label();
				subMonitor.worked(1);
				transaction.addCommand(new SetLocalAttributeCommand(this, newValue, ScaPackage.Literals.SCA_DEVICE_MANAGER__LABEL));
			} catch (SystemException e) {
				transaction.addCommand(new UnsetLocalAttributeCommand(this,
				        new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch label", e),
				        ScaPackage.Literals.SCA_DEVICE_MANAGER__LABEL));
			}
		} else {
			transaction.addCommand(new UnsetLocalAttributeCommand(this, null, ScaPackage.Literals.SCA_DEVICE_MANAGER__LABEL));
		}
		transaction.commit();
		subMonitor.done();
		return getLabel();
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 14.0
	 */
	@Override
	protected void notifyChanged(Notification msg) {
		super.notifyChanged(msg);
		switch (msg.getFeatureID(ScaDeviceManager.class)) {
		case ScaPackage.SCA_DEVICE_MANAGER__OBJ:
			unsetFileSystem();
			unsetProfile();
			unsetProfileObj();
			unsetLabel();
			unsetIdentifier();
			unsetPorts();
			unsetDevices();
			unsetServices();
			break;
	    case ScaPackage.SCA_DEVICE_MANAGER__PROFILE:
	    	if (!PluginUtil.equals(msg.getOldValue(), msg.getNewValue())){
	    		unsetProfileURI();
			}
	    	break;
		default:
			break;
		}
	}

	private void fetchLocalAttributes(IProgressMonitor monitor) {
		SubMonitor subMonitor = SubMonitor.convert(monitor, 3);
		fetchLabel(subMonitor.newChild(1));
		fetchIdentifier(subMonitor.newChild(1));
		fetchProfile(subMonitor.newChild(1));
		subMonitor.done();
	}

	private static final EStructuralFeature[] PRF_PATH = {
	        DcdPackage.Literals.DEVICE_CONFIGURATION__DEVICE_MANAGER_SOFT_PKG,
	        DcdPackage.Literals.DEVICE_MANAGER_SOFT_PKG__SOFT_PKG,
	        SpdPackage.Literals.SOFT_PKG__PROPERTY_FILE,
	        SpdPackage.Literals.PROPERTY_FILE__PROPERTIES
	};

	@Override
	protected EStructuralFeature[] getPrfPropertiesPath() {
		return PRF_PATH;
	}

	private final VersionedFeature profileObjectFeature = new VersionedFeature(this, ScaPackage.Literals.PROFILE_OBJECT_WRAPPER__PROFILE_OBJ);

	/**
	 * @since 14.0
	 */
	@Override
	public DeviceConfiguration fetchProfileObject(IProgressMonitor monitor) {
		Transaction transaction = profileObjectFeature.createTransaction();
		transaction.addCommand(ProfileObjectWrapper.Util.fetchProfileObject(monitor,
		        ScaDeviceManagerImpl.this,
		        DeviceConfiguration.class,
		        DeviceConfiguration.EOBJECT_PATH));
		transaction.commit();
		return getProfileObj();
	}

	/**
	 * @since 14.0
	 */
	public boolean mayHaveChildren() {
		return true;
	}

	private final VersionedFeature profileFeature = new VersionedFeature(this, ScaPackage.Literals.SCA_DEVICE_MANAGER__PROFILE);

	/**
     * @since 14.0
     * @generated NOT
     */
	public String fetchProfile(IProgressMonitor monitor) {
		if (isSetProfile()) {
			return getProfile();
		}
		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching profile", 3);
		DeviceManager localObj = fetchNarrowedObject(subMonitor.newChild(1));
		Transaction transaction = profileFeature.createTransaction();
		if (localObj != null) {
			try {
				String newValue = localObj.deviceConfigurationProfile();
				subMonitor.worked(1);
				transaction.addCommand(new SetLocalAttributeCommand(this, newValue, ScaPackage.Literals.SCA_DEVICE_MANAGER__PROFILE));
			} catch (SystemException e) {
				transaction.addCommand(new UnsetLocalAttributeCommand(this,
				        new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to profile", e),
				        ScaPackage.Literals.SCA_DEVICE_MANAGER__PROFILE));
			}
		} else {
			transaction.addCommand(new UnsetLocalAttributeCommand(this, null, ScaPackage.Literals.SCA_DEVICE_MANAGER__PROFILE));
		}
		transaction.commit();
		subMonitor.done();
		return getProfile();
	}

	private final VersionedFeature profileURIFeature = new VersionedFeature(this, ScaPackage.Literals.PROFILE_OBJECT_WRAPPER__PROFILE_URI);

	@Override
	public URI fetchProfileURI(IProgressMonitor monitor) {
		if (isSetProfileURI()) {
			return getProfileURI();
		}
		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetch Profile URI", 2);
		ScaDeviceManagerFileSystem fileSystem = fetchFileSystem(subMonitor.newChild(1));
		if (fileSystem != null) {
			Transaction transaction = profileURIFeature.createTransaction();
			final URI newURI = fileSystem.createURI(fetchProfile(subMonitor.newChild(1)));
			transaction.addCommand(new ScaModelCommand() {

				public void execute() {
					setProfileURI(newURI);
				}
			});
			transaction.commit();
		}
		subMonitor.done();
		return getProfileURI();
	}

} //ScaDeviceManagerImpl

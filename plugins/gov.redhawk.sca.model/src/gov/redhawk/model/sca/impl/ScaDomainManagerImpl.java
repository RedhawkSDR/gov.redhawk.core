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

import gov.redhawk.model.sca.DomainConnectionException;
import gov.redhawk.model.sca.DomainConnectionState;
import gov.redhawk.model.sca.ProfileObjectWrapper;
import gov.redhawk.model.sca.Properties;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaDomainManagerFileSystem;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.ScaWaveformFactory;
import gov.redhawk.model.sca.commands.ScaDomainManagerMergeDeviceManagersCommand;
import gov.redhawk.model.sca.commands.ScaDomainManagerMergeWaveformFactoriesCommand;
import gov.redhawk.model.sca.commands.ScaDomainManagerMergeWaveformsCommand;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.commands.ScaModelCommandWithResult;
import gov.redhawk.model.sca.commands.SetLocalAttributeCommand;
import gov.redhawk.model.sca.commands.UnsetLocalAttributeCommand;
import gov.redhawk.model.sca.commands.VersionedFeature;
import gov.redhawk.model.sca.commands.VersionedFeature.Transaction;
import gov.redhawk.sca.util.Debug;
import gov.redhawk.sca.util.OrbSession;
import gov.redhawk.sca.util.PluginUtil;
import gov.redhawk.sca.util.SilentJob;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import mil.jpeojtrs.sca.dmd.DmdPackage;
import mil.jpeojtrs.sca.dmd.DomainManagerConfiguration;
import mil.jpeojtrs.sca.prf.AbstractProperty;
import mil.jpeojtrs.sca.spd.SpdPackage;
import mil.jpeojtrs.sca.util.NamedThreadFactory;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap.ValueListIterator;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.omg.CORBA.ORB;
import org.omg.CORBA.SystemException;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import CF.Application;
import CF.ApplicationFactory;
import CF.DataType;
import CF.Device;
import CF.DeviceManager;
import CF.DomainManager;
import CF.DomainManagerHelper;
import CF.FileManager;
import CF.InvalidFileName;
import CF.InvalidObjectReference;
import CF.InvalidProfile;
import CF.PropertiesHolder;
import CF.UnknownProperties;
import CF.DomainManagerPackage.AlreadyConnected;
import CF.DomainManagerPackage.ApplicationAlreadyInstalled;
import CF.DomainManagerPackage.ApplicationInstallationError;
import CF.DomainManagerPackage.ApplicationUninstallationError;
import CF.DomainManagerPackage.DeviceManagerNotRegistered;
import CF.DomainManagerPackage.InvalidEventChannelName;
import CF.DomainManagerPackage.InvalidIdentifier;
import CF.DomainManagerPackage.NotConnected;
import CF.DomainManagerPackage.RegisterError;
import CF.DomainManagerPackage.UnregisterError;
import CF.PropertySetPackage.InvalidConfiguration;
import CF.PropertySetPackage.PartialConfiguration;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Domain Manager</b></em>'.
 * @since 12.0 
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaDomainManagerImpl#getWaveformFactories <em>Waveform Factories</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaDomainManagerImpl#getWaveforms <em>Waveforms</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaDomainManagerImpl#getDeviceManagers <em>Device Managers</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaDomainManagerImpl#getFileManager <em>File Manager</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaDomainManagerImpl#getConnectionPropertiesContainer <em>Connection Properties Container</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaDomainManagerImpl#getConnectionProperties <em>Connection Properties</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaDomainManagerImpl#isAutoConnect <em>Auto Connect</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaDomainManagerImpl#isConnected <em>Connected</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaDomainManagerImpl#getIdentifier <em>Identifier</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaDomainManagerImpl#getName <em>Name</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaDomainManagerImpl#getRootContext <em>Root Context</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaDomainManagerImpl#getState <em>State</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaDomainManagerImpl#getProfile <em>Profile</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScaDomainManagerImpl extends ScaPropertyContainerImpl<DomainManager, DomainManagerConfiguration> implements ScaDomainManager {
	/**
	 * The cached value of the '{@link #getWaveformFactories() <em>Waveform Factories</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWaveformFactories()
	 * @generated
	 * @ordered
	 */
	protected EList<ScaWaveformFactory> waveformFactories;
	/**
	 * The cached value of the '{@link #getWaveforms() <em>Waveforms</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWaveforms()
	 * @generated
	 * @ordered
	 */
	protected EList<ScaWaveform> waveforms;
	/**
	 * The cached value of the '{@link #getDeviceManagers() <em>Device Managers</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeviceManagers()
	 * @generated
	 * @ordered
	 */
	protected EList<ScaDeviceManager> deviceManagers;
	/**
	 * The cached value of the '{@link #getFileManager() <em>File Manager</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileManager()
	 * @generated
	 * @ordered
	 */
	protected ScaDomainManagerFileSystem fileManager;
	/**
	 * This is true if the File Manager containment reference has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean fileManagerESet;
	/**
	 * The cached value of the '{@link #getConnectionPropertiesContainer() <em>Connection Properties Container</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnectionPropertiesContainer()
	 * @generated
	 * @ordered
	 */
	protected Properties connectionPropertiesContainer;
	/**
	 * The default value of the '{@link #isAutoConnect() <em>Auto Connect</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAutoConnect()
	 * @generated
	 * @ordered
	 */
	protected static final boolean AUTO_CONNECT_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isAutoConnect() <em>Auto Connect</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isAutoConnect()
	 * @generated
	 * @ordered
	 */
	protected boolean autoConnect = AUTO_CONNECT_EDEFAULT;
	/**
	 * The default value of the '{@link #isConnected() <em>Connected</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isConnected()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CONNECTED_EDEFAULT = false;
	/**
	 * The default value of the '{@link #getIdentifier() <em>Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdentifier()
	 * @generated
	 * @ordered
	 */
	protected static final String IDENTIFIER_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getIdentifier() <em>Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIdentifier()
	 * @generated
	 * @ordered
	 */
	protected String identifier = IDENTIFIER_EDEFAULT;
	/**
	 * This is true if the Identifier attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean identifierESet;
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
	 * The default value of the '{@link #getRootContext() <em>Root Context</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRootContext()
	 * @generated
	 * @ordered
	 */
	protected static final NamingContextExt ROOT_CONTEXT_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getRootContext() <em>Root Context</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRootContext()
	 * @generated
	 * @ordered
	 */
	protected NamingContextExt rootContext = ROOT_CONTEXT_EDEFAULT;
	/**
	 * This is true if the Root Context attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean rootContextESet;
	/**
	 * The default value of the '{@link #getState() <em>State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected static final DomainConnectionState STATE_EDEFAULT = DomainConnectionState.DISCONNECTED;
	/**
	 * The cached value of the '{@link #getState() <em>State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected DomainConnectionState state = STATE_EDEFAULT;
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
	private static final Debug DEBUG = new Debug(ScaModelPlugin.ID, "scaDomainManager/connect");
	private static final Debug DEBUG_KEEP_ALIVE_ERRORS = new Debug(ScaModelPlugin.ID, "scaDomainManager/keepAliveErrors");

	private static final DeviceManager[] EMPTY_DEVICE_MANAGERS = new DeviceManager[0];

	private static final Application[] EMPTY_APPLICATIONS = new Application[0];

	private static final ApplicationFactory[] EMPTY_APPLICATION_FACTORIES = new ApplicationFactory[0];

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected ScaDomainManagerImpl() {
		super();
		this.setConnectionPropertiesContainer(ScaFactory.eINSTANCE.createProperties());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScaPackage.Literals.SCA_DOMAIN_MANAGER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 18.0
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific type known in this context.
	 * @generated
	 */
	@Override
	public void setProfileObj(DomainManagerConfiguration newProfileObj) {
		super.setProfileObj(newProfileObj);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ScaWaveformFactory> getWaveformFactories() {
		if (waveformFactories == null) {
			waveformFactories = new EObjectContainmentWithInverseEList.Unsettable<ScaWaveformFactory>(ScaWaveformFactory.class, this, ScaPackage.SCA_DOMAIN_MANAGER__WAVEFORM_FACTORIES, ScaPackage.SCA_WAVEFORM_FACTORY__DOM_MGR);
		}
		return waveformFactories;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetWaveformFactories() {
		if (waveformFactories != null) ((InternalEList.Unsettable<?>)waveformFactories).unset();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetWaveformFactories() {
		return waveformFactories != null && ((InternalEList.Unsettable<?>)waveformFactories).isSet();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ScaWaveform> getWaveforms() {
		if (waveforms == null) {
			waveforms = new EObjectContainmentWithInverseEList.Unsettable<ScaWaveform>(ScaWaveform.class, this, ScaPackage.SCA_DOMAIN_MANAGER__WAVEFORMS, ScaPackage.SCA_WAVEFORM__DOM_MGR);
		}
		return waveforms;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetWaveforms() {
		if (waveforms != null) ((InternalEList.Unsettable<?>)waveforms).unset();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetWaveforms() {
		return waveforms != null && ((InternalEList.Unsettable<?>)waveforms).isSet();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ScaDeviceManager> getDeviceManagers() {
		if (deviceManagers == null) {
			deviceManagers = new EObjectContainmentWithInverseEList.Unsettable<ScaDeviceManager>(ScaDeviceManager.class, this, ScaPackage.SCA_DOMAIN_MANAGER__DEVICE_MANAGERS, ScaPackage.SCA_DEVICE_MANAGER__DOM_MGR);
		}
		return deviceManagers;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetDeviceManagers() {
		if (deviceManagers != null) ((InternalEList.Unsettable<?>)deviceManagers).unset();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetDeviceManagers() {
		return deviceManagers != null && ((InternalEList.Unsettable<?>)deviceManagers).isSet();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaDomainManagerFileSystem getFileManager() {
		return fileManager;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetFileManager(ScaDomainManagerFileSystem newFileManager, NotificationChain msgs) {
		ScaDomainManagerFileSystem oldFileManager = fileManager;
		fileManager = newFileManager;
		boolean oldFileManagerESet = fileManagerESet;
		fileManagerESet = true;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_DOMAIN_MANAGER__FILE_MANAGER, oldFileManager, newFileManager, !oldFileManagerESet);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFileManager(ScaDomainManagerFileSystem newFileManager) {
		if (newFileManager != fileManager) {
			NotificationChain msgs = null;
			if (fileManager != null)
				msgs = ((InternalEObject)fileManager).eInverseRemove(this, ScaPackage.SCA_DOMAIN_MANAGER_FILE_SYSTEM__DOM_MGR, ScaDomainManagerFileSystem.class, msgs);
			if (newFileManager != null)
				msgs = ((InternalEObject)newFileManager).eInverseAdd(this, ScaPackage.SCA_DOMAIN_MANAGER_FILE_SYSTEM__DOM_MGR, ScaDomainManagerFileSystem.class, msgs);
			msgs = basicSetFileManager(newFileManager, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else {
			boolean oldFileManagerESet = fileManagerESet;
			fileManagerESet = true;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_DOMAIN_MANAGER__FILE_MANAGER, newFileManager, newFileManager, !oldFileManagerESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicUnsetFileManager(NotificationChain msgs) {
		ScaDomainManagerFileSystem oldFileManager = fileManager;
		fileManager = null;
		boolean oldFileManagerESet = fileManagerESet;
		fileManagerESet = false;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.UNSET, ScaPackage.SCA_DOMAIN_MANAGER__FILE_MANAGER, oldFileManager, null, oldFileManagerESet);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetFileManager() {
		if (fileManager != null) {
			NotificationChain msgs = null;
			msgs = ((InternalEObject)fileManager).eInverseRemove(this, ScaPackage.SCA_DOMAIN_MANAGER_FILE_SYSTEM__DOM_MGR, ScaDomainManagerFileSystem.class, msgs);
			msgs = basicUnsetFileManager(msgs);
			if (msgs != null) msgs.dispatch();
		}
		else {
			boolean oldFileManagerESet = fileManagerESet;
			fileManagerESet = false;
			if (eNotificationRequired())
				eNotify(new ENotificationImpl(this, Notification.UNSET, ScaPackage.SCA_DOMAIN_MANAGER__FILE_MANAGER, null, null, oldFileManagerESet));
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetFileManager() {
		return fileManagerESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Properties getConnectionPropertiesContainer() {
		if (connectionPropertiesContainer != null && connectionPropertiesContainer.eIsProxy()) {
			InternalEObject oldConnectionPropertiesContainer = (InternalEObject)connectionPropertiesContainer;
			connectionPropertiesContainer = (Properties)eResolveProxy(oldConnectionPropertiesContainer);
			if (connectionPropertiesContainer != oldConnectionPropertiesContainer) {
				InternalEObject newConnectionPropertiesContainer = (InternalEObject)connectionPropertiesContainer;
				NotificationChain msgs = oldConnectionPropertiesContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScaPackage.SCA_DOMAIN_MANAGER__CONNECTION_PROPERTIES_CONTAINER, null, null);
				if (newConnectionPropertiesContainer.eInternalContainer() == null) {
					msgs = newConnectionPropertiesContainer.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScaPackage.SCA_DOMAIN_MANAGER__CONNECTION_PROPERTIES_CONTAINER, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ScaPackage.SCA_DOMAIN_MANAGER__CONNECTION_PROPERTIES_CONTAINER, oldConnectionPropertiesContainer, connectionPropertiesContainer));
			}
		}
		return connectionPropertiesContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Properties basicGetConnectionPropertiesContainer() {
		return connectionPropertiesContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConnectionPropertiesContainer(Properties newConnectionPropertiesContainer, NotificationChain msgs) {
		Properties oldConnectionPropertiesContainer = connectionPropertiesContainer;
		connectionPropertiesContainer = newConnectionPropertiesContainer;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_DOMAIN_MANAGER__CONNECTION_PROPERTIES_CONTAINER, oldConnectionPropertiesContainer, newConnectionPropertiesContainer);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConnectionPropertiesContainer(Properties newConnectionPropertiesContainer) {
		if (newConnectionPropertiesContainer != connectionPropertiesContainer) {
			NotificationChain msgs = null;
			if (connectionPropertiesContainer != null)
				msgs = ((InternalEObject)connectionPropertiesContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScaPackage.SCA_DOMAIN_MANAGER__CONNECTION_PROPERTIES_CONTAINER, null, msgs);
			if (newConnectionPropertiesContainer != null)
				msgs = ((InternalEObject)newConnectionPropertiesContainer).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ScaPackage.SCA_DOMAIN_MANAGER__CONNECTION_PROPERTIES_CONTAINER, null, msgs);
			msgs = basicSetConnectionPropertiesContainer(newConnectionPropertiesContainer, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_DOMAIN_MANAGER__CONNECTION_PROPERTIES_CONTAINER, newConnectionPropertiesContainer, newConnectionPropertiesContainer));
	}

	private static final ExecutorService EXECUTOR_POOL = Executors.newFixedThreadPool(5, new NamedThreadFactory(ScaDomainManagerImpl.class.getName()));

	/**
	 * @since 14.0
	 */
	@Override
	protected void notifyChanged(Notification msg) {
		// END GENERATED CODE
		super.notifyChanged(msg);
		switch (msg.getFeatureID(ScaDomainManager.class)) {
		case ScaPackage.SCA_DOMAIN_MANAGER__OBJ:
			unsetWaveforms();
			unsetWaveformFactories();
			unsetDeviceManagers();
			unsetFileManager();
			unsetIdentifier();
			unsetProfile();
			break;
		case ScaPackage.SCA_DOMAIN_MANAGER__PROFILE:
			if (!PluginUtil.equals(msg.getOldValue(), msg.getNewValue())) {
				unsetProfileURI();
			}
			break;
		default:
			break;
		}
		// BEGIN GENERATED CODE
	}
	
	private static void destroyOrbSession(final OrbSession session) {
		if (session == null) {
			return;
		}
		EXECUTOR_POOL.submit(new Runnable() {

			public void run() {
				try {
					session.dispose();
				} catch (Exception e) {
					// PASS Ignore Exception on close
				}
            }
			
		});
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 8.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EMap<String, String> getConnectionProperties() {
		// END GENERATED CODE
		return getConnectionPropertiesContainer().getProperty();
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isAutoConnect() {
		return autoConnect;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAutoConnect(boolean newAutoConnect) {
		boolean oldAutoConnect = autoConnect;
		autoConnect = newAutoConnect;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_DOMAIN_MANAGER__AUTO_CONNECT, oldAutoConnect, autoConnect));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isConnected() {
		// END GENERATED CODE
		return getState() == DomainConnectionState.CONNECTED;
		// BEGIN GENERATED CODE
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
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_DOMAIN_MANAGER__IDENTIFIER, oldIdentifier, identifier, !oldIdentifierESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScaPackage.SCA_DOMAIN_MANAGER__IDENTIFIER, oldIdentifier, IDENTIFIER_EDEFAULT, oldIdentifierESet));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_DOMAIN_MANAGER__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NamingContextExt getRootContext() {
		return rootContext;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRootContext(NamingContextExt newRootContext) {
		NamingContextExt oldRootContext = rootContext;
		rootContext = newRootContext;
		boolean oldRootContextESet = rootContextESet;
		rootContextESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_DOMAIN_MANAGER__ROOT_CONTEXT, oldRootContext, rootContext, !oldRootContextESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetRootContext() {
		NamingContextExt oldRootContext = rootContext;
		boolean oldRootContextESet = rootContextESet;
		rootContext = ROOT_CONTEXT_EDEFAULT;
		rootContextESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScaPackage.SCA_DOMAIN_MANAGER__ROOT_CONTEXT, oldRootContext, ROOT_CONTEXT_EDEFAULT, oldRootContextESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetRootContext() {
		return rootContextESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DomainConnectionState getState() {
		return state;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 *  <!-- end-user-doc -->
	 * @generated
	 */
	public void setStateGen(DomainConnectionState newState) {
		DomainConnectionState oldState = state;
		state = newState == null ? STATE_EDEFAULT : newState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_DOMAIN_MANAGER__STATE, oldState, state));
	}

	private SilentJob notifyAllJob = new SilentJob("Notify All") {

		@Override
		protected IStatus runSilent(IProgressMonitor monitor) {
			synchronized (ScaDomainManagerImpl.this) {
				ScaDomainManagerImpl.this.notifyAll();
			}
			return Status.OK_STATUS;
		}

	};
	private OrbSession session;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setState(final DomainConnectionState newState) {
		// END GENERATED CODE
		final DomainConnectionState oldState = this.state;
		setStateGen(newState);
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_DOMAIN_MANAGER__CONNECTED, oldState == DomainConnectionState.CONNECTED,
				this.state == DomainConnectionState.CONNECTED));
		}
		notifyAllJob.schedule();
		// BEGIN GENERATED CODE
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
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_DOMAIN_MANAGER__PROFILE, oldProfile, profile, !oldProfileESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScaPackage.SCA_DOMAIN_MANAGER__PROFILE, oldProfile, PROFILE_EDEFAULT, oldProfileESet));
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
	 * Waits for a connect that is in progress to complete. This method assumes it is running in the protected context
	 * that can access the model.
	 *  
	 * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility
	 *  to call done() on the given monitor. Accepts null, indicating that no progress should be
	 *  reported and that the operation cannot be canceled.
	 * @throws InterruptedException 
	 */
	private void waitOnConnect(final IProgressMonitor monitor) throws InterruptedException {
		SubMonitor progress = SubMonitor.convert(monitor, SubMonitor.UNKNOWN);

		final int SLEEP_TIME_MILLIS = 1000;
		while (getState() == DomainConnectionState.CONNECTING && !progress.isCanceled()) {
			synchronized (this) {
				this.wait(SLEEP_TIME_MILLIS);
			}
			progress.worked(1);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0 
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void connect(IProgressMonitor parentMonitor, RefreshDepth refreshDepth) throws DomainConnectionException {
		// END GENERATED CODE
		try {
			if (refreshDepth == null) {
				refreshDepth = RefreshDepth.SELF;
			}

			DomainConnectionState localState = getState();

			if (localState == DomainConnectionState.CONNECTED) {
				// Silly rabbit - you're already connected
				return;
			} else if (localState == DomainConnectionState.CONNECTING) {
				// Someone is already connecting. Wait on them to finish.
				waitOnConnect(parentMonitor);

				localState = getState();
				// If the other thread connected us, or we were canceled, we're done
				if (localState == DomainConnectionState.CONNECTED || (parentMonitor != null && parentMonitor.isCanceled())) {
					return;
				}

				// Report back failure
				throw new DomainConnectionException("Unable to connect");
			} else if ((localState != DomainConnectionState.DISCONNECTED) && (localState != DomainConnectionState.FAILED)) {
				// Disconnected / Failed are the only other states connect can be called from
				throw new DomainConnectionException("Invalid state for connect (" + getState().getName() + ")");
			}

			ScaModelCommand.execute(this, new ScaModelCommand() {
				public void execute() {
					setState(DomainConnectionState.CONNECTING);
				}
			});

			final int INIT_ORB_WORK = 5;
			final int RESOLVE_NAMINGCONTEXT_WORK = 5;
			final int SET_NAMINGCONTEXT_WORK = 5;
			final int DOMAIN_MGR_WORK = 5;
			final int REFRESH_DOMAIN_WORK = 75;
			final SubMonitor monitor = SubMonitor.convert(parentMonitor, "Connecting to domain: " + getName(), 100);
			monitor.subTask("Initializing ORB...");
			java.util.Properties orbProperties = createProperties();
			java.util.Properties systemProps = System.getProperties();
			systemProps.putAll(orbProperties);
			final OrbSession orbSession = OrbSession.createSession(getName(), Platform.getApplicationArgs(), systemProps);
			setOrbSession(orbSession);
			CompoundCommand command = new CompoundCommand();

			command.append(new ScaModelCommand() {

				public void execute() {
					clearAllStatus();
				}

			});
			monitor.worked(INIT_ORB_WORK);

			// Allow the user to specify the DomainManager. If there is no '/'
			// then the user did not specify it (use the SCA 2.2.2 specific
			// implementation which is <name>/<name>)
			final String domMgrName = getDomMgrName();

			monitor.subTask("Resolving Naming Service...");
			//			String nameService = orbProperties.getProperty("ORBInitRef.NameService");
			ORB orb = orbSession.getOrb();
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			monitor.worked(RESOLVE_NAMINGCONTEXT_WORK);

			final NamingContextExt newNamingContext = NamingContextExtHelper.narrow(objRef);
			command.append(new ScaModelCommand() {

				public void execute() {
					setRootContext(newNamingContext);
				}

			});
			monitor.worked(SET_NAMINGCONTEXT_WORK);

			monitor.subTask("Resolving Domain " + domMgrName);
			final org.omg.CORBA.Object newCorbaObj = newNamingContext.resolve_str(domMgrName);
			command.append(new ScaModelCommand() {

				public void execute() {
					setCorbaObj(newCorbaObj);
					setState(DomainConnectionState.CONNECTED);
				}

			});
			monitor.worked(DOMAIN_MGR_WORK);

			ScaModelCommand.execute(this, command);

			monitor.subTask("Refreshing Domain...");
			try {
				this.refresh(monitor.newChild(REFRESH_DOMAIN_WORK), refreshDepth);
			} catch (Exception e) {
				if (DEBUG.enabled) {
					DEBUG.message("Errors during refresh in connect.");
					DEBUG.catching(e);
				}
			}

			monitor.done();
		} catch (final DomainConnectionException e) {
			// Failure occurred in another thread and we're reporting that failure to this thread
			throw e;
		} catch (final Exception e) {
			ScaModelCommand.execute(this, new ScaModelCommand() {

				public void execute() {
					setState(DomainConnectionState.FAILED);
					reset();
				}

			});
			throw new DomainConnectionException(e);
		} finally {
			if (parentMonitor != null) {
				parentMonitor.done();
			}
		}
		// BEGIN GENERATED CODE
	}

	/**
     * @since 18.0
     */
	public void setOrbSession(OrbSession session) {
		destroyOrbSession(this.session);
		this.session = session;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @deprecated Use {@link #connect(IProgressMonitor, RefreshDepth)}
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Deprecated
	public void connect(final IProgressMonitor parentMonitor) throws DomainConnectionException {
		// END GENERATED CODE
		connect(parentMonitor, RefreshDepth.FULL);
		// BEGIN GENERATED CODE
	}

	private java.util.Properties createProperties() {
		// END GENERATED CODE
		final java.util.Properties props = new java.util.Properties();
		for (final Map.Entry<String, String> entry : this.getConnectionProperties().entrySet()) {
			props.put(entry.getKey(), entry.getValue());
		}
		return props;
		// BEGIN GENERATED CODE
	}

	private String getDomMgrName() {
		// END GENERATED CODE
		String localName = this.name;
		if (localName != null && localName.indexOf('/') == -1) {
			return localName + "/" + localName;
		} else {
			return localName;
		}
		// BEGIN GENERATED CODE
	}
	
	private void internalDisconnect() {
		setState(DomainConnectionState.DISCONNECTING);
		reset();
		setState(DomainConnectionState.DISCONNECTED);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void disconnect() {
		// END GENERATED CODE
		ScaModelCommand.execute(this, new ScaModelCommand() {

			@Override
			protected boolean prepare() {
				return super.prepare() && getState() == DomainConnectionState.CONNECTED;
			}

			public void execute() {
				internalDisconnect();
			}
		});
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * @throws InterruptedException 
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<ScaDeviceManager> fetchDeviceManagers(IProgressMonitor monitor) {
		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetch Device Managers", 2);
		internalFetchDeviceManagers(subMonitor.newChild(1));
		ScaDeviceManager[] array = ScaModelCommandWithResult.execute(this, new ScaModelCommandWithResult<ScaDeviceManager[]>() {

			public void execute() {
				setResult(getDeviceManagers().toArray(new ScaDeviceManager[getDeviceManagers().size()]));
			}
		});
		if (array != null) {
			SubMonitor deviceMonitor = subMonitor.newChild(1);
			deviceMonitor.beginTask("Refreshing device Managers", array.length);
			for (ScaDeviceManager element : array) {
				try {
	                element.refresh(deviceMonitor.newChild(1), RefreshDepth.SELF);
                } catch (InterruptedException e) {
                	// PASS
                }
			}
		}
		subMonitor.done();
		return getDeviceManagers();
	}

	private final VersionedFeature devicemanagers = new VersionedFeature(this, ScaPackage.Literals.SCA_DOMAIN_MANAGER__DEVICE_MANAGERS);

	/**
	 * @since 14.0
	 */
	protected void internalFetchDeviceManagers(IProgressMonitor monitor) {
		// END GENERATED CODE
		final SubMonitor subMonitor = SubMonitor.convert(monitor, 3);
		final DomainManager domMgr = fetchNarrowedObject(subMonitor.newChild(1));
		Transaction transaction = devicemanagers.createTransaction();
		if (domMgr != null) {
			// Setup New Device Managers Map
			DeviceManager[] deviceMgrs = null;
			try {
				deviceMgrs = domMgr.deviceManagers();
				transaction.addCommand(new ScaDomainManagerMergeDeviceManagersCommand(this, deviceMgrs));
			} catch (SystemException e) {
				Status status = new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch Device Managers.", e);
				transaction.addCommand(new UnsetLocalAttributeCommand(this, status, ScaPackage.Literals.SCA_DOMAIN_MANAGER__DEVICE_MANAGERS));
			}
			subMonitor.worked(1);
		} else {
			transaction.addCommand(new UnsetLocalAttributeCommand(this, null, ScaPackage.Literals.SCA_DOMAIN_MANAGER__DEVICE_MANAGERS));
		}

		// Perform Actions
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
	public EList<ScaWaveformFactory> fetchWaveformFactories(IProgressMonitor monitor) {
		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetch Waveform Factories", 2);
		internalFetchWaveformFactories(subMonitor.newChild(1));
		ScaWaveformFactory[] array = ScaModelCommandWithResult.execute(this, new ScaModelCommandWithResult<ScaWaveformFactory[]>() {

			public void execute() {
				setResult(getWaveformFactories().toArray(new ScaWaveformFactory[getWaveformFactories().size()]));
			}
		});
		if (array != null) {
			SubMonitor childMonitor = subMonitor.newChild(1);
			childMonitor.beginTask("Refreshing waveform factory", array.length);
			for (ScaWaveformFactory element : array) {
				try {
	                element.refresh(childMonitor.newChild(1), RefreshDepth.SELF);
                } catch (InterruptedException e) {
	                // PASS
                }
			}
		}
		subMonitor.done();
		return getWaveformFactories();
	}

	private final VersionedFeature waveformFactoriesFeature = new VersionedFeature(this, ScaPackage.Literals.SCA_DOMAIN_MANAGER__WAVEFORM_FACTORIES);

	/**
	 * @since 14.0
	 */
	protected void internalFetchWaveformFactories(IProgressMonitor monitor) {
		// END GENERATED CODE
		SubMonitor subMonitor = SubMonitor.convert(monitor, 3);
		final DomainManager domMgr = fetchNarrowedObject(subMonitor.newChild(1));
		Transaction transaction = waveformFactoriesFeature.createTransaction();
		if (domMgr != null) {
			// Setup new Factory Map
			ApplicationFactory[] factories = null;
			try {
				factories = domMgr.applicationFactories();
				transaction.addCommand(new ScaDomainManagerMergeWaveformFactoriesCommand(this, factories));
			} catch (SystemException e) {
				Status status = new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch waveforms factories.", e);
				transaction.addCommand(new UnsetLocalAttributeCommand(this, status, ScaPackage.Literals.SCA_DOMAIN_MANAGER__WAVEFORM_FACTORIES));
			}
			subMonitor.worked(1);
		} else {
			transaction.addCommand(new UnsetLocalAttributeCommand(this, null, ScaPackage.Literals.SCA_DOMAIN_MANAGER__WAVEFORM_FACTORIES));
		}
		subMonitor.setWorkRemaining(1);
		transaction.commit();
		subMonitor.worked(1);
		subMonitor.done();
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * @throws InterruptedException 
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<ScaWaveform> fetchWaveforms(IProgressMonitor monitor) {
		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetch Waveforms", 2);
		internalFetchWaveforms(subMonitor.newChild(1));
		ScaWaveform[] array = ScaModelCommandWithResult.execute(this, new ScaModelCommandWithResult<ScaWaveform[]>() {

			public void execute() {
				setResult(getWaveforms().toArray(new ScaWaveform[getWaveforms().size()]));
			}
		});
		if (array != null) {
			SubMonitor childMonitor = subMonitor.newChild(1);
			childMonitor.beginTask("Refreshing waveforms", array.length);
			for (ScaWaveform element : array) {
				try {
	                element.refresh(childMonitor.newChild(1), RefreshDepth.SELF);
                } catch (InterruptedException e) {
                	// PASS
                }
			}
		}
		subMonitor.done();
		return getWaveforms();
	}

	private final VersionedFeature waveformsFeature = new VersionedFeature(this, ScaPackage.Literals.SCA_DOMAIN_MANAGER__WAVEFORMS);

	/**
	 * @since 14.0
	 */
	protected void internalFetchWaveforms(IProgressMonitor monitor) {
		// END GENERATED CODE
		SubMonitor subMonitor = SubMonitor.convert(monitor, 3); //SUPPRESS CHECKSTYLE MagicNumber
		final DomainManager domMgr = fetchNarrowedObject(subMonitor.newChild(1));
		Transaction transaction = waveformsFeature.createTransaction();

		if (domMgr != null) {
			// Fetch Applications
			Application[] applications = null;
			try {
				applications = domMgr.applications();
				transaction.addCommand(new ScaDomainManagerMergeWaveformsCommand(this, applications));
			} catch (SystemException e) {
				Status status = new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch waveforms.", e);
				transaction.addCommand(new UnsetLocalAttributeCommand(this, status, ScaPackage.Literals.SCA_DOMAIN_MANAGER__WAVEFORMS));
			}
			subMonitor.worked(1);
		} else {
			transaction.addCommand(new UnsetLocalAttributeCommand(this, null, ScaPackage.Literals.SCA_DOMAIN_MANAGER__WAVEFORMS));
		}

		// Perform Actions
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
	public ScaWaveformFactory installScaWaveformFactory(final String profilePath) throws InvalidProfile, InvalidFileName, ApplicationInstallationError,
	        ApplicationAlreadyInstalled {
		// END GENERATED CODE
		if (profilePath == null) {
			throw new InvalidProfile("Null path");
		}
		installApplication(profilePath);
		try {
			return ScaModelCommand.runExclusive(this, new RunnableWithResult.Impl<ScaWaveformFactory>() {

				public void run() {
					for (ScaWaveformFactory factory : getWaveformFactories()) {
						if (profilePath.equals(factory.getProfile())) {
							setResult(factory);
						}
					}
				}

			});
		} catch (InterruptedException e) {
			return null;
		}

		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void uninstallScaWaveformFactory(final ScaWaveformFactory factory) throws ApplicationUninstallationError, InvalidIdentifier {
		// END GENERATED CODE
		DomainManager localObj = fetchNarrowedObject(null);
		if (localObj == null) {
			throw new IllegalStateException("Corba Obj is null");
		}
		if (factory == null) {
			return;
		}
		localObj.uninstallApplication(factory.getIdentifier());
		ScaModelCommand.execute(this, new ScaModelCommand() {

			public void execute() {
				EcoreUtil.delete(factory);
			}
		});
		// BEGIN GENERATED CODE
	}

	@Override
	public void dispose() {
		// END GENERATED CODE
		internalDisconnect();
		super.dispose();
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 2.0 <!-- end-user-doc -->
	 * @generated NOT
	 */
	public ScaDevice< ? > getDevice(final String deviceId) {
		// END GENERATED CODE
		ScaDevice< ? > result = null;
		for (final ScaDeviceManager devMgr : getDeviceManagers()) {
			result = devMgr.getDevice(deviceId);
			if (result != null) {
				return result;
			}
		}
		return null;
		// BEGIN GENERATED CODE
	}

	private void reset() {
		// END GENERATED CODE
		unsetCorbaObj();
		unsetRootContext();
		setOrbSession(null);
		clearAllStatus();
		// BEGIN GENERATED CODE
	}
	
	@Override
	public IStatus getStatus() {
		if (getState() == DomainConnectionState.DISCONNECTED) {
			return Status.OK_STATUS;
		}
	    return super.getStatus();
	}

	/**
	 * @since 14.0
	 */
	public ApplicationFactory[] applicationFactories() {
		// END GENERATED CODE
		DomainManager domMgr = fetchNarrowedObject(null);
		if (domMgr == null) {
			return ScaDomainManagerImpl.EMPTY_APPLICATION_FACTORIES;
		}
		return domMgr.applicationFactories();
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 14.0
	 */
	public Application[] applications() {
		// END GENERATED CODE
		DomainManager domMgr = fetchNarrowedObject(null);
		if (domMgr == null) {
			return ScaDomainManagerImpl.EMPTY_APPLICATIONS;
		}
		return domMgr.applications();
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 14.0
	 */
	public DeviceManager[] deviceManagers() {
		// END GENERATED CODE
		DomainManager domMgr = fetchNarrowedObject(null);
		if (domMgr == null) {
			return ScaDomainManagerImpl.EMPTY_DEVICE_MANAGERS;
		}
		return domMgr.deviceManagers();
		// BEGIN GENERATED CODE
	}

	public String domainManagerProfile() {
		// END GENERATED CODE
		return this.getProfile();
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 14.0
	 */
	public FileManager fileMgr() {
		// END GENERATED CODE
		return getFileManager().fetchNarrowedObject(null);
		// BEGIN GENERATED CODE
	}

	public String identifier() {
		// END GENERATED CODE
		return getIdentifier();
		// BEGIN GENERATED CODE
	}

	public void installApplication(final String profileFileName) throws InvalidProfile, InvalidFileName, ApplicationInstallationError,
	        ApplicationAlreadyInstalled {
		// END GENERATED CODE
		DomainManager domMgr = fetchNarrowedObject(null);
		if (domMgr == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		domMgr.installApplication(profileFileName);
		fetchWaveformFactories(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 14.0
	 */
	public void registerDevice(final Device registeringDevice, final DeviceManager registeredDeviceMgr) throws InvalidObjectReference, InvalidProfile,
	        DeviceManagerNotRegistered, RegisterError {
		// END GENERATED CODE
		DomainManager domMgr = fetchNarrowedObject(null);
		if (domMgr == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		domMgr.registerDevice(registeringDevice, registeredDeviceMgr);
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 14.0
	 */
	public void registerDeviceManager(final DeviceManager deviceMgr) throws InvalidObjectReference, InvalidProfile, RegisterError {
		// END GENERATED CODE
		DomainManager domMgr = fetchNarrowedObject(null);
		if (domMgr == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		domMgr.registerDeviceManager(deviceMgr);

		fetchDeviceManagers(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 14.0
	 */
	public void registerService(final org.omg.CORBA.Object registeringService, final DeviceManager registeredDeviceMgr, final String name)
	        throws InvalidObjectReference, DeviceManagerNotRegistered, RegisterError {
		// END GENERATED CODE
		DomainManager domMgr = fetchNarrowedObject(null);
		if (domMgr == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		domMgr.registerService(registeringService, registeredDeviceMgr, name);
		// BEGIN GENERATED CODE
	}

	public void registerWithEventChannel(final org.omg.CORBA.Object registeringObject, final String registeringId, final String eventChannelName)
	        throws InvalidObjectReference, InvalidEventChannelName, AlreadyConnected {
		// END GENERATED CODE
		DomainManager domMgr = fetchNarrowedObject(null);
		if (domMgr == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		domMgr.registerWithEventChannel(registeringObject, registeringId, eventChannelName);
		// BEGIN GENERATED CODE
	}

	public void uninstallApplication(final String applicationId) throws InvalidIdentifier, ApplicationUninstallationError {
		// END GENERATED CODE
		ScaWaveformFactory factory = null;
		try {
			factory = ScaModelCommand.runExclusive(this, new RunnableWithResult.Impl<ScaWaveformFactory>() {

				public void run() {
					for (ScaWaveformFactory factory : getWaveformFactories()) {
						if (factory.getIdentifier().equals(applicationId)) {
							setResult(factory);
						}
					}
				}

			});
		} catch (InterruptedException e) {
			// PASS
		}
		if (factory != null) {
			uninstallScaWaveformFactory(factory);
		} else {
			DomainManager domMgr = fetchNarrowedObject(null);
			if (domMgr == null) {
				throw new IllegalStateException("CORBA Object is Null");
			}
			domMgr.uninstallApplication(applicationId);
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 14.0
	 */
	public void unregisterDevice(final Device unregisteringDevice) throws InvalidObjectReference, UnregisterError {
		// END GENERATED CODE
		DomainManager domMgr = fetchNarrowedObject(null);
		if (domMgr == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		domMgr.unregisterDevice(unregisteringDevice);
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 14.0
	 */
	public void unregisterDeviceManager(final DeviceManager deviceMgr) throws InvalidObjectReference, UnregisterError {
		// END GENERATED CODE
		DomainManager domMgr = fetchNarrowedObject(null);
		if (domMgr == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		domMgr.unregisterDeviceManager(deviceMgr);
		// BEGIN GENERATED CODE
	}

	public void unregisterFromEventChannel(final String unregisteringId, final String eventChannelName) throws InvalidEventChannelName, NotConnected {
		// END GENERATED CODE
		DomainManager domMgr = fetchNarrowedObject(null);
		if (domMgr == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		domMgr.unregisterFromEventChannel(unregisteringId, eventChannelName);
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
			case ScaPackage.SCA_DOMAIN_MANAGER__WAVEFORM_FACTORIES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getWaveformFactories()).basicAdd(otherEnd, msgs);
			case ScaPackage.SCA_DOMAIN_MANAGER__WAVEFORMS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getWaveforms()).basicAdd(otherEnd, msgs);
			case ScaPackage.SCA_DOMAIN_MANAGER__DEVICE_MANAGERS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDeviceManagers()).basicAdd(otherEnd, msgs);
			case ScaPackage.SCA_DOMAIN_MANAGER__FILE_MANAGER:
				if (fileManager != null)
					msgs = ((InternalEObject)fileManager).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ScaPackage.SCA_DOMAIN_MANAGER__FILE_MANAGER, null, msgs);
				return basicSetFileManager((ScaDomainManagerFileSystem)otherEnd, msgs);
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
			case ScaPackage.SCA_DOMAIN_MANAGER__WAVEFORM_FACTORIES:
				return ((InternalEList<?>)getWaveformFactories()).basicRemove(otherEnd, msgs);
			case ScaPackage.SCA_DOMAIN_MANAGER__WAVEFORMS:
				return ((InternalEList<?>)getWaveforms()).basicRemove(otherEnd, msgs);
			case ScaPackage.SCA_DOMAIN_MANAGER__DEVICE_MANAGERS:
				return ((InternalEList<?>)getDeviceManagers()).basicRemove(otherEnd, msgs);
			case ScaPackage.SCA_DOMAIN_MANAGER__FILE_MANAGER:
				return basicUnsetFileManager(msgs);
			case ScaPackage.SCA_DOMAIN_MANAGER__CONNECTION_PROPERTIES_CONTAINER:
				return basicSetConnectionPropertiesContainer(null, msgs);
			case ScaPackage.SCA_DOMAIN_MANAGER__CONNECTION_PROPERTIES:
				return ((InternalEList<?>)getConnectionProperties()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ScaPackage.SCA_DOMAIN_MANAGER__WAVEFORM_FACTORIES:
				return getWaveformFactories();
			case ScaPackage.SCA_DOMAIN_MANAGER__WAVEFORMS:
				return getWaveforms();
			case ScaPackage.SCA_DOMAIN_MANAGER__DEVICE_MANAGERS:
				return getDeviceManagers();
			case ScaPackage.SCA_DOMAIN_MANAGER__FILE_MANAGER:
				return getFileManager();
			case ScaPackage.SCA_DOMAIN_MANAGER__CONNECTION_PROPERTIES_CONTAINER:
				if (resolve) return getConnectionPropertiesContainer();
				return basicGetConnectionPropertiesContainer();
			case ScaPackage.SCA_DOMAIN_MANAGER__CONNECTION_PROPERTIES:
				if (coreType) return getConnectionProperties();
				else return getConnectionProperties().map();
			case ScaPackage.SCA_DOMAIN_MANAGER__AUTO_CONNECT:
				return isAutoConnect();
			case ScaPackage.SCA_DOMAIN_MANAGER__CONNECTED:
				return isConnected();
			case ScaPackage.SCA_DOMAIN_MANAGER__IDENTIFIER:
				return getIdentifier();
			case ScaPackage.SCA_DOMAIN_MANAGER__NAME:
				return getName();
			case ScaPackage.SCA_DOMAIN_MANAGER__ROOT_CONTEXT:
				return getRootContext();
			case ScaPackage.SCA_DOMAIN_MANAGER__STATE:
				return getState();
			case ScaPackage.SCA_DOMAIN_MANAGER__PROFILE:
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
			case ScaPackage.SCA_DOMAIN_MANAGER__WAVEFORM_FACTORIES:
				getWaveformFactories().clear();
				getWaveformFactories().addAll((Collection<? extends ScaWaveformFactory>)newValue);
				return;
			case ScaPackage.SCA_DOMAIN_MANAGER__WAVEFORMS:
				getWaveforms().clear();
				getWaveforms().addAll((Collection<? extends ScaWaveform>)newValue);
				return;
			case ScaPackage.SCA_DOMAIN_MANAGER__DEVICE_MANAGERS:
				getDeviceManagers().clear();
				getDeviceManagers().addAll((Collection<? extends ScaDeviceManager>)newValue);
				return;
			case ScaPackage.SCA_DOMAIN_MANAGER__FILE_MANAGER:
				setFileManager((ScaDomainManagerFileSystem)newValue);
				return;
			case ScaPackage.SCA_DOMAIN_MANAGER__CONNECTION_PROPERTIES_CONTAINER:
				setConnectionPropertiesContainer((Properties)newValue);
				return;
			case ScaPackage.SCA_DOMAIN_MANAGER__CONNECTION_PROPERTIES:
				((EStructuralFeature.Setting)getConnectionProperties()).set(newValue);
				return;
			case ScaPackage.SCA_DOMAIN_MANAGER__AUTO_CONNECT:
				setAutoConnect((Boolean)newValue);
				return;
			case ScaPackage.SCA_DOMAIN_MANAGER__IDENTIFIER:
				setIdentifier((String)newValue);
				return;
			case ScaPackage.SCA_DOMAIN_MANAGER__NAME:
				setName((String)newValue);
				return;
			case ScaPackage.SCA_DOMAIN_MANAGER__ROOT_CONTEXT:
				setRootContext((NamingContextExt)newValue);
				return;
			case ScaPackage.SCA_DOMAIN_MANAGER__STATE:
				setState((DomainConnectionState)newValue);
				return;
			case ScaPackage.SCA_DOMAIN_MANAGER__PROFILE:
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
			case ScaPackage.SCA_DOMAIN_MANAGER__WAVEFORM_FACTORIES:
				unsetWaveformFactories();
				return;
			case ScaPackage.SCA_DOMAIN_MANAGER__WAVEFORMS:
				unsetWaveforms();
				return;
			case ScaPackage.SCA_DOMAIN_MANAGER__DEVICE_MANAGERS:
				unsetDeviceManagers();
				return;
			case ScaPackage.SCA_DOMAIN_MANAGER__FILE_MANAGER:
				unsetFileManager();
				return;
			case ScaPackage.SCA_DOMAIN_MANAGER__CONNECTION_PROPERTIES_CONTAINER:
				setConnectionPropertiesContainer((Properties)null);
				return;
			case ScaPackage.SCA_DOMAIN_MANAGER__CONNECTION_PROPERTIES:
				getConnectionProperties().clear();
				return;
			case ScaPackage.SCA_DOMAIN_MANAGER__AUTO_CONNECT:
				setAutoConnect(AUTO_CONNECT_EDEFAULT);
				return;
			case ScaPackage.SCA_DOMAIN_MANAGER__IDENTIFIER:
				unsetIdentifier();
				return;
			case ScaPackage.SCA_DOMAIN_MANAGER__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ScaPackage.SCA_DOMAIN_MANAGER__ROOT_CONTEXT:
				unsetRootContext();
				return;
			case ScaPackage.SCA_DOMAIN_MANAGER__STATE:
				setState(STATE_EDEFAULT);
				return;
			case ScaPackage.SCA_DOMAIN_MANAGER__PROFILE:
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
			case ScaPackage.SCA_DOMAIN_MANAGER__WAVEFORM_FACTORIES:
				return isSetWaveformFactories();
			case ScaPackage.SCA_DOMAIN_MANAGER__WAVEFORMS:
				return isSetWaveforms();
			case ScaPackage.SCA_DOMAIN_MANAGER__DEVICE_MANAGERS:
				return isSetDeviceManagers();
			case ScaPackage.SCA_DOMAIN_MANAGER__FILE_MANAGER:
				return isSetFileManager();
			case ScaPackage.SCA_DOMAIN_MANAGER__CONNECTION_PROPERTIES_CONTAINER:
				return connectionPropertiesContainer != null;
			case ScaPackage.SCA_DOMAIN_MANAGER__CONNECTION_PROPERTIES:
				return !getConnectionProperties().isEmpty();
			case ScaPackage.SCA_DOMAIN_MANAGER__AUTO_CONNECT:
				return autoConnect != AUTO_CONNECT_EDEFAULT;
			case ScaPackage.SCA_DOMAIN_MANAGER__CONNECTED:
				return isConnected() != CONNECTED_EDEFAULT;
			case ScaPackage.SCA_DOMAIN_MANAGER__IDENTIFIER:
				return isSetIdentifier();
			case ScaPackage.SCA_DOMAIN_MANAGER__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ScaPackage.SCA_DOMAIN_MANAGER__ROOT_CONTEXT:
				return isSetRootContext();
			case ScaPackage.SCA_DOMAIN_MANAGER__STATE:
				return state != STATE_EDEFAULT;
			case ScaPackage.SCA_DOMAIN_MANAGER__PROFILE:
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
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (autoConnect: ");
		result.append(autoConnect);
		result.append(", identifier: ");
		if (identifierESet) result.append(identifier); else result.append("<unset>");
		result.append(", name: ");
		result.append(name);
		result.append(", rootContext: ");
		if (rootContextESet) result.append(rootContext); else result.append("<unset>");
		result.append(", state: ");
		result.append(state);
		result.append(", profile: ");
		if (profileESet) result.append(profile); else result.append("<unset>");
		result.append(')');
		return result.toString();
	}

	public void unregisterService(final org.omg.CORBA.Object unregisteringService, final String name) throws InvalidObjectReference, UnregisterError {
		// END GENERATED CODE
		DomainManager domMgr = fetchNarrowedObject(null);
		if (domMgr == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		domMgr.unregisterService(unregisteringService, name);
		// BEGIN GENERATED CODE
	}

	public void configure(final DataType[] configProperties) throws InvalidConfiguration, PartialConfiguration {
		// END GENERATED CODE
		DomainManager domMgr = fetchNarrowedObject(null);
		if (domMgr == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		domMgr.configure(configProperties);
		// BEGIN GENERATED CODE
	}

	public void query(final PropertiesHolder configProperties) throws UnknownProperties {
		// END GENERATED CODE
		DomainManager domMgr = fetchNarrowedObject(null);
		if (domMgr != null) {
			domMgr.query(configProperties);
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 14.0
	 */
	@Override
	protected DomainManager narrow(final org.omg.CORBA.Object obj) {
		// END GENERATED CODE
		return DomainManagerHelper.narrow(obj);
		// BEGIN GENERATED CODE
	}

	@Override
	protected Class<DomainManager> getCorbaType() {
		return DomainManager.class;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InterruptedException 
	 * @generated NOT
	 */
	@Override
	public void fetchAttributes(IProgressMonitor monitor) {
		SubMonitor subMonitor = SubMonitor.convert(monitor, 7);
		fetchKeepAlive(subMonitor.newChild(1));
		fetchNarrowedObject(subMonitor.newChild(1)); // Handled by the CORBA obj superclass!
		fetchFileManager(subMonitor.newChild(1));
		super.fetchAttributes(subMonitor.newChild(1));
		fetchLocalAttributes(subMonitor.newChild(1));
		fetchProfileObject(subMonitor.newChild(1));
		fetchProperties(subMonitor.newChild(1));

		subMonitor.done();
	}

	@Override
	protected void internalFetchChildren(IProgressMonitor monitor) throws InterruptedException {
		SubMonitor subMonitor = SubMonitor.convert(monitor, 3);
		internalFetchDeviceManagers(subMonitor.newChild(1));
		internalFetchWaveformFactories(subMonitor.newChild(1));
		internalFetchWaveforms(subMonitor.newChild(1));
		subMonitor.done();
	}

	private final VersionedFeature keepAliveFeature = new VersionedFeature(this, ScaPackage.Literals.CORBA_OBJ_WRAPPER__CORBA_OBJ);

	private void fetchKeepAlive(IProgressMonitor monitor) {
		SubMonitor subMonitor = SubMonitor.convert(monitor, 1);
		final boolean tmpExists = exists();
		final NamingContextExt namingContext = rootContext;
		final String domMgrname = getDomMgrName();
		Boolean shouldProceed;
        try {
	        shouldProceed = ScaModelCommand.runExclusive(this, new RunnableWithResult.Impl<Boolean>() {

	        	public void run() {
	        		setResult(isConnected() && !tmpExists && namingContext != null && domMgrname != null);
	        	}

	        });
        } catch (InterruptedException e1) {
	       return;
        }
		if (shouldProceed != null && shouldProceed) {
			try {
				Transaction transaction = keepAliveFeature.createTransaction();
				final org.omg.CORBA.Object newRootContext = namingContext.resolve_str(domMgrname);
				transaction.addCommand(new ScaModelCommand() {

					public void execute() {
						if (namingContext != getRootContext() || !domMgrname.equals(getDomMgrName())) {
							return;
						}
						setCorbaObj(newRootContext);
					}

				});
				transaction.commit();
			} catch (NotFound e) {
				if (DEBUG_KEEP_ALIVE_ERRORS.enabled) {
					DEBUG_KEEP_ALIVE_ERRORS.message("Errors durring fetch keep alive.");
					DEBUG_KEEP_ALIVE_ERRORS.catching(e);
				}
			} catch (CannotProceed e) {
				if (DEBUG_KEEP_ALIVE_ERRORS.enabled) {
					DEBUG_KEEP_ALIVE_ERRORS.message("Errors durring fetch keep alive.");
					DEBUG_KEEP_ALIVE_ERRORS.catching(e);
				}
			} catch (InvalidName e) {
				if (DEBUG_KEEP_ALIVE_ERRORS.enabled) {
					DEBUG_KEEP_ALIVE_ERRORS.message("Errors durring fetch keep alive.");
					DEBUG_KEEP_ALIVE_ERRORS.catching(e);
				}
			} catch (SystemException e) {
				if (DEBUG_KEEP_ALIVE_ERRORS.enabled) {
					DEBUG_KEEP_ALIVE_ERRORS.message("Errors durring fetch keep alive.");
					DEBUG_KEEP_ALIVE_ERRORS.catching(e);
				}
			}
		}
		subMonitor.worked(1);

		subMonitor.done();
	}

	private VersionedFeature fileManagerFeature = new VersionedFeature(this, ScaPackage.Literals.SCA_DOMAIN_MANAGER__FILE_MANAGER);

	/**
	 * @since 14.0
	 * @generated NOT
	 */
	public ScaDomainManagerFileSystem fetchFileManager(IProgressMonitor monitor) {
		if (isSetFileManager()) {
			return getFileManager();
		}
		SubMonitor subMonitor = SubMonitor.convert(monitor, 4);
		final DomainManager localObj = fetchNarrowedObject(subMonitor.newChild(1));
		Transaction transaction = fileManagerFeature.createTransaction();
		if (localObj != null) {
			try {
				final FileManager newFileMgr = localObj.fileMgr();
				transaction.addCommand(new ScaModelCommand() {

					public void execute() {
						if (fileManager == null) {
							setFileManager(ScaFactory.eINSTANCE.createScaDomainManagerFileSystem());
						}
						fileManager.setCorbaObj(newFileMgr);

						setStatus(ScaPackage.Literals.SCA_DOMAIN_MANAGER__FILE_MANAGER, null);
					}

				});
			} catch (SystemException e) {
				transaction.addCommand(new UnsetLocalAttributeCommand(this,
				        new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch file manager.", e),
				        ScaPackage.Literals.SCA_DOMAIN_MANAGER__FILE_MANAGER));
			}
			subMonitor.worked(1);
		} else {
			transaction.addCommand(new UnsetLocalAttributeCommand(this, null, ScaPackage.Literals.SCA_DOMAIN_MANAGER__FILE_MANAGER));
		}
		subMonitor.setWorkRemaining(2);
		transaction.commit();
		ScaDomainManagerFileSystem localFileManager = getFileManager();
		if (localFileManager != null) {
			try {
	            localFileManager.refresh(subMonitor.newChild(1), RefreshDepth.SELF);
            } catch (InterruptedException e) {
	            // PASS
            }
		}
		subMonitor.done();
		return getFileManager();
	}

	private final VersionedFeature identifierRevision = new VersionedFeature(this, ScaPackage.Literals.SCA_DOMAIN_MANAGER__IDENTIFIER);

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
		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetch Identifier", 3);
		DomainManager resource = fetchNarrowedObject(subMonitor.newChild(1));
		Transaction transaction = identifierRevision.createTransaction();
		if (resource != null) {
			try {
				String newValue = resource.identifier();
				transaction.append(new SetLocalAttributeCommand(this, newValue, ScaPackage.Literals.SCA_DOMAIN_MANAGER__IDENTIFIER));
			} catch (final SystemException e) {
				IStatus startedStatus = new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch identifier.", e);
				transaction.append(new UnsetLocalAttributeCommand(this, startedStatus, ScaPackage.Literals.SCA_DOMAIN_MANAGER__IDENTIFIER));
			}
		} else {
			transaction.append(new UnsetLocalAttributeCommand(this, null, ScaPackage.Literals.SCA_DOMAIN_MANAGER__IDENTIFIER));
		}
		subMonitor.worked(1);
		transaction.commit();
		subMonitor.worked(1);
		subMonitor.done();
		return getIdentifier();
		// BEGIN GENERATED CODE
	}

	private void fetchLocalAttributes(IProgressMonitor monitor) {
		SubMonitor subMonitor = SubMonitor.convert(monitor, 3);
		fetchIdentifier(subMonitor.newChild(1));
		fetchProfile(subMonitor.newChild(1));
		subMonitor.done();
	}

	private static final EStructuralFeature[] PRF_PATH = {
	        DmdPackage.Literals.DOMAIN_MANAGER_CONFIGURATION__DOMAIN_MANAGER_SOFT_PKG,
	        DmdPackage.Literals.DOMAIN_MANAGER_SOFT_PKG__SOFT_PKG,
	        SpdPackage.Literals.SOFT_PKG__PROPERTY_FILE,
	        SpdPackage.Literals.PROPERTY_FILE__PROPERTIES
	};
	
	@Override
	protected List<AbstractProperty> fetchPropertyDefinitions(IProgressMonitor monitor) {
		DomainManagerConfiguration dmd = fetchProfileObject(monitor);
		mil.jpeojtrs.sca.prf.Properties propDefintions = ScaEcoreUtils.getFeature(dmd, PRF_PATH);
		List<AbstractProperty> retVal = new ArrayList<AbstractProperty>();
		for ( ValueListIterator<Object> i = propDefintions.getProperties().valueListIterator(); i.hasNext(); ) {
			Object propDef = i.next();
			if (propDef instanceof AbstractProperty) {
				retVal.add((AbstractProperty) propDef);
			}
		}
		return retVal;
	}

	private final VersionedFeature profileObjectFeature = new VersionedFeature(this, ScaPackage.Literals.PROFILE_OBJECT_WRAPPER__PROFILE_OBJ);

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public DomainManagerConfiguration fetchProfileObject(IProgressMonitor monitor) {
		Transaction transaction = profileObjectFeature.createTransaction();
		transaction.addCommand(ProfileObjectWrapper.Util.fetchProfileObject(monitor,
		        this,
		        DomainManagerConfiguration.class,
		        DomainManagerConfiguration.EOBJECT_PATH));
		transaction.commit();
		return getProfileObj();
	}

	private final VersionedFeature profileRevision = new VersionedFeature(this, ScaPackage.Literals.SCA_DOMAIN_MANAGER__PROFILE);

    /**
     * @since 14.0
     * @generated NOT
     */
    public String fetchProfile(IProgressMonitor monitor) {
		if (isSetProfile()) {
			return getProfile();
		}
		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching profile", 3);
		DomainManager resource = fetchNarrowedObject(subMonitor.newChild(1));
		Transaction transaction = profileRevision.createTransaction();
		if (resource != null) {
			try {
				String newValue = resource.domainManagerProfile();
				transaction.append(new SetLocalAttributeCommand(this, newValue, ScaPackage.Literals.SCA_DOMAIN_MANAGER__PROFILE));
			} catch (final SystemException e) {
				IStatus startedStatus = new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch profile.", e);
				transaction.append(new UnsetLocalAttributeCommand(this, startedStatus, ScaPackage.Literals.SCA_DOMAIN_MANAGER__PROFILE));
			}
		} else {
			transaction.append(new UnsetLocalAttributeCommand(this, null, ScaPackage.Literals.SCA_DOMAIN_MANAGER__PROFILE));
		}
		subMonitor.worked(1);
		transaction.commit();
		subMonitor.worked(1);
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
		ScaDomainManagerFileSystem fileSystem = fetchFileManager(subMonitor.newChild(1));
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

} // ScaDomainManagerImpl

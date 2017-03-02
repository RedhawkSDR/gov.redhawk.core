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

import java.util.Arrays;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.omg.CORBA.SystemException;

import CF.Application;
import CF.ApplicationFactory;
import CF.ApplicationFactoryHelper;
import CF.ApplicationFactoryOperations;
import CF.DataType;
import CF.DeviceAssignmentType;
import CF.ApplicationFactoryPackage.CreateApplicationError;
import CF.ApplicationFactoryPackage.CreateApplicationInsufficientCapacityError;
import CF.ApplicationFactoryPackage.CreateApplicationRequestError;
import CF.ApplicationFactoryPackage.InvalidInitConfiguration;
import gov.redhawk.model.sca.ProfileObjectWrapper;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaDomainManagerFileSystem;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.ScaWaveformFactory;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.commands.ScaModelCommandWithResult;
import gov.redhawk.model.sca.commands.SetLocalAttributeCommand;
import gov.redhawk.model.sca.commands.UnsetLocalAttributeCommand;
import gov.redhawk.model.sca.commands.VersionedFeature;
import gov.redhawk.model.sca.commands.VersionedFeature.Transaction;
import gov.redhawk.sca.util.Debug;
import gov.redhawk.sca.util.PluginUtil;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Waveform Factory</b></em>'.
 * 
 * @since 12.0
 *        <!-- end-user-doc -->
 *        <p>
 *        The following features are implemented:
 *        </p>
 *        <ul>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaWaveformFactoryImpl#getProfileURI <em>Profile URI</em>}</li>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaWaveformFactoryImpl#getProfileObj <em>Profile Obj</em>}</li>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaWaveformFactoryImpl#getDomMgr <em>Dom Mgr</em>}</li>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaWaveformFactoryImpl#getIdentifier <em>Identifier</em>}</li>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaWaveformFactoryImpl#getName <em>Name</em>}</li>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaWaveformFactoryImpl#getProfile <em>Profile</em>}</li>
 *        </ul>
 *
 * @generated
 */
public class ScaWaveformFactoryImpl extends CorbaObjWrapperImpl<ApplicationFactory> implements ScaWaveformFactory {
	/**
	 * The default value of the '{@link #getProfileURI() <em>Profile URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getProfileURI()
	 * @generated
	 * @ordered
	 */
	protected static final URI PROFILE_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProfileURI() <em>Profile URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getProfileURI()
	 * @generated
	 * @ordered
	 */
	protected URI profileURI = PROFILE_URI_EDEFAULT;

	/**
	 * This is true if the Profile URI attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean profileURIESet;

	/**
	 * The cached value of the '{@link #getProfileObj() <em>Profile Obj</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getProfileObj()
	 * @generated
	 * @ordered
	 */
	protected SoftwareAssembly profileObj;

	/**
	 * This is true if the Profile Obj reference has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean profileObjESet;

	/**
	 * The default value of the '{@link #getIdentifier() <em>Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getIdentifier()
	 * @generated
	 * @ordered
	 */
	protected static final String IDENTIFIER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getIdentifier() <em>Identifier</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getIdentifier()
	 * @generated
	 * @ordered
	 */
	protected String identifier = IDENTIFIER_EDEFAULT;

	/**
	 * This is true if the Identifier attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean identifierESet;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * This is true if the Name attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean nameESet;

	/**
	 * The default value of the '{@link #getProfile() <em>Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getProfile()
	 * @generated
	 * @ordered
	 */
	protected static final String PROFILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProfile() <em>Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getProfile()
	 * @generated
	 * @ordered
	 */
	protected String profile = PROFILE_EDEFAULT;

	/**
	 * This is true if the Profile attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean profileESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ScaWaveformFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScaPackage.Literals.SCA_WAVEFORM_FACTORY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 18.0
	 *        <!-- end-user-doc -->
	 *        This is specialized for the more specific type known in this context.
	 * @generated
	 */
	@Override
	public void setObj(ApplicationFactory newObj) {
		super.setObj(newObj);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public URI getProfileURI() {
		return profileURI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setProfileURI(URI newProfileURI) {
		URI oldProfileURI = profileURI;
		profileURI = newProfileURI;
		boolean oldProfileURIESet = profileURIESet;
		profileURIESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_WAVEFORM_FACTORY__PROFILE_URI, oldProfileURI, profileURI, !oldProfileURIESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void unsetProfileURI() {
		URI oldProfileURI = profileURI;
		boolean oldProfileURIESet = profileURIESet;
		profileURI = PROFILE_URI_EDEFAULT;
		profileURIESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScaPackage.SCA_WAVEFORM_FACTORY__PROFILE_URI, oldProfileURI, PROFILE_URI_EDEFAULT,
				oldProfileURIESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isSetProfileURI() {
		return profileURIESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public SoftwareAssembly getProfileObj() {
		if (profileObj != null && ((EObject) profileObj).eIsProxy()) {
			InternalEObject oldProfileObj = (InternalEObject) profileObj;
			profileObj = (SoftwareAssembly) eResolveProxy(oldProfileObj);
			if (profileObj != oldProfileObj) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ScaPackage.SCA_WAVEFORM_FACTORY__PROFILE_OBJ, oldProfileObj, profileObj));
			}
		}
		return profileObj;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public SoftwareAssembly basicGetProfileObj() {
		return profileObj;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setProfileObj(SoftwareAssembly newProfileObj) {
		SoftwareAssembly oldProfileObj = profileObj;
		profileObj = newProfileObj;
		boolean oldProfileObjESet = profileObjESet;
		profileObjESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_WAVEFORM_FACTORY__PROFILE_OBJ, oldProfileObj, profileObj, !oldProfileObjESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void unsetProfileObj() {
		SoftwareAssembly oldProfileObj = profileObj;
		boolean oldProfileObjESet = profileObjESet;
		profileObj = null;
		profileObjESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScaPackage.SCA_WAVEFORM_FACTORY__PROFILE_OBJ, oldProfileObj, null, oldProfileObjESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isSetProfileObj() {
		return profileObjESet;
	}

	private static final Debug DEBUG = new Debug(ScaModelPlugin.ID, "scaWaveformFactory/create");

	private static final DataType[] DATA_EMPTY_TYPE = new DataType[0];
	private static final DeviceAssignmentType[] DEVICE_EMPTY_TYPE = new DeviceAssignmentType[0];

	@Override
	protected void notifyChanged(Notification msg) {
		// END GENERATED CODE
		super.notifyChanged(msg);
		if (msg.isTouch()) {
			return;
		}
		switch (msg.getFeatureID(ScaWaveformFactory.class)) {
		case ScaPackage.SCA_WAVEFORM_FACTORY__PROFILE:
			if (!PluginUtil.equals(msg.getOldValue(), msg.getNewValue())) {
				unsetProfileURI();
			}
			break;
		case ScaPackage.SCA_WAVEFORM__OBJ:
			unsetName();
			unsetIdentifier();
			unsetProfile();
			break;
		case ScaPackage.SCA_WAVEFORM_FACTORY__PROFILE_URI:
			if (!PluginUtil.equals(msg.getOldValue(), msg.getNewValue())) {
				unsetProfileObj();
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
	 * 
	 * @generated
	 */
	@Override
	public ScaDomainManager getDomMgr() {
		if (eContainerFeatureID() != ScaPackage.SCA_WAVEFORM_FACTORY__DOM_MGR)
			return null;
		return (ScaDomainManager) eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ScaDomainManager basicGetDomMgr() {
		if (eContainerFeatureID() != ScaPackage.SCA_WAVEFORM_FACTORY__DOM_MGR)
			return null;
		return (ScaDomainManager) eInternalContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetDomMgr(ScaDomainManager newDomMgr, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newDomMgr, ScaPackage.SCA_WAVEFORM_FACTORY__DOM_MGR, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setDomMgr(ScaDomainManager newDomMgr) {
		if (newDomMgr != eInternalContainer() || (eContainerFeatureID() != ScaPackage.SCA_WAVEFORM_FACTORY__DOM_MGR && newDomMgr != null)) {
			if (EcoreUtil.isAncestor(this, newDomMgr))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newDomMgr != null)
				msgs = ((InternalEObject) newDomMgr).eInverseAdd(this, ScaPackage.SCA_DOMAIN_MANAGER__WAVEFORM_FACTORIES, ScaDomainManager.class, msgs);
			msgs = basicSetDomMgr(newDomMgr, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_WAVEFORM_FACTORY__DOM_MGR, newDomMgr, newDomMgr));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getIdentifier() {
		return identifier;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setIdentifier(String newIdentifier) {
		String oldIdentifier = identifier;
		identifier = newIdentifier;
		boolean oldIdentifierESet = identifierESet;
		identifierESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_WAVEFORM_FACTORY__IDENTIFIER, oldIdentifier, identifier, !oldIdentifierESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void unsetIdentifier() {
		String oldIdentifier = identifier;
		boolean oldIdentifierESet = identifierESet;
		identifier = IDENTIFIER_EDEFAULT;
		identifierESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScaPackage.SCA_WAVEFORM_FACTORY__IDENTIFIER, oldIdentifier, IDENTIFIER_EDEFAULT,
				oldIdentifierESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isSetIdentifier() {
		return identifierESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		boolean oldNameESet = nameESet;
		nameESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_WAVEFORM_FACTORY__NAME, oldName, name, !oldNameESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void unsetName() {
		String oldName = name;
		boolean oldNameESet = nameESet;
		name = NAME_EDEFAULT;
		nameESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScaPackage.SCA_WAVEFORM_FACTORY__NAME, oldName, NAME_EDEFAULT, oldNameESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isSetName() {
		return nameESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getProfile() {
		return profile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setProfile(String newProfile) {
		String oldProfile = profile;
		profile = newProfile;
		boolean oldProfileESet = profileESet;
		profileESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_WAVEFORM_FACTORY__PROFILE, oldProfile, profile, !oldProfileESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void unsetProfile() {
		String oldProfile = profile;
		boolean oldProfileESet = profileESet;
		profile = PROFILE_EDEFAULT;
		profileESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScaPackage.SCA_WAVEFORM_FACTORY__PROFILE, oldProfile, PROFILE_EDEFAULT, oldProfileESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isSetProfile() {
		return profileESet;
	}

	/**
	 * @since 14.0 {@inheritDoc}
	 */
	@Override
	protected ApplicationFactory narrow(final org.omg.CORBA.Object obj) {
		return ApplicationFactoryHelper.narrow(obj);
	}

	@Override
	protected Class<ApplicationFactory> getCorbaType() {
		return ApplicationFactory.class;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 14.0
	 *        <!-- end-user-doc -->
	 * 
	 * @throws CreateApplicationError
	 * @throws InvalidInitConfiguration
	 * @throws CreateApplicationRequestError
	 * @generated NOT
	 */
	@Override
	public ScaWaveform createWaveform(IProgressMonitor monitor, final String name, DataType[] initConfiguration, DeviceAssignmentType[] deviceAssignments)
		throws CreateApplicationError, CreateApplicationRequestError, InvalidInitConfiguration, CreateApplicationInsufficientCapacityError {
		return createWaveform(monitor, name, initConfiguration, deviceAssignments, RefreshDepth.SELF);
	}

	private final VersionedFeature idFeature = new VersionedFeature(this, ScaPackage.Literals.SCA_WAVEFORM_FACTORY__IDENTIFIER);

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 14.0
	 *        <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String fetchIdentifier(IProgressMonitor monitor) {
		// END GENERATED CODE
		if (isDisposed()) {
			return null;
		}
		if (isSetIdentifier()) {
			return getIdentifier();
		}

		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching identifier", 3);
		if (subMonitor.isCanceled()) {
			throw new OperationCanceledException();
		}
		ApplicationFactory resource = fetchNarrowedObject(subMonitor.newChild(1));

		Transaction transaction = idFeature.createTransaction();
		if (resource != null) {
			try {
				if (subMonitor.isCanceled()) {
					throw new OperationCanceledException();
				}
				String newValue = resource.identifier();
				transaction.append(new SetLocalAttributeCommand(this, newValue, ScaPackage.Literals.SCA_WAVEFORM_FACTORY__IDENTIFIER));
			} catch (final SystemException e) {
				IStatus startedStatus = new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch identifier.", e);
				transaction.append(new UnsetLocalAttributeCommand(this, startedStatus, ScaPackage.Literals.SCA_WAVEFORM_FACTORY__IDENTIFIER));
			}
			subMonitor.worked(1);
		} else {
			transaction.append(new UnsetLocalAttributeCommand(this, null, ScaPackage.Literals.SCA_WAVEFORM_FACTORY__IDENTIFIER));
		}

		subMonitor.setWorkRemaining(1);
		transaction.commit();
		subMonitor.done();
		return getIdentifier();
		// BEGIN GENERATED CODE
	}

	private final VersionedFeature nameFeature = new VersionedFeature(this, ScaPackage.Literals.SCA_WAVEFORM_FACTORY__NAME);

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 14.0
	 *        <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String fetchName(IProgressMonitor monitor) {
		// END GENERATED CODE
		if (isDisposed()) {
			return null;
		}
		if (isSetName()) {
			return getName();
		}

		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching name", 3);
		if (subMonitor.isCanceled()) {
			throw new OperationCanceledException();
		}
		ApplicationFactory resource = fetchNarrowedObject(subMonitor.newChild(1));

		Transaction transaction = nameFeature.createTransaction();
		if (resource != null) {
			try {
				if (subMonitor.isCanceled()) {
					throw new OperationCanceledException();
				}
				String newValue = resource.name();
				transaction.append(new SetLocalAttributeCommand(this, newValue, ScaPackage.Literals.SCA_WAVEFORM_FACTORY__NAME));
			} catch (final SystemException e) {
				IStatus startedStatus = new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch name.", e);
				transaction.append(new UnsetLocalAttributeCommand(this, startedStatus, ScaPackage.Literals.SCA_WAVEFORM_FACTORY__NAME));
			}
			subMonitor.worked(1);
		} else {
			transaction.append(new UnsetLocalAttributeCommand(this, null, ScaPackage.Literals.SCA_WAVEFORM_FACTORY__NAME));
		}

		subMonitor.setWorkRemaining(1);
		transaction.commit();
		subMonitor.done();
		return getName();
		// BEGIN GENERATED CODE
	}

	private final VersionedFeature profileObjFeature = new VersionedFeature(this, ScaPackage.Literals.PROFILE_OBJECT_WRAPPER__PROFILE_OBJ);

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 14.0
	 *        <!-- end-user-doc -->
	 * @generated NOT
	 * 
	 */
	@Override
	public SoftwareAssembly fetchProfileObject(IProgressMonitor monitor) {
		// END GENERATED CODE
		if (isDisposed()) {
			return null;
		}
		if (isSetProfileObj()) {
			return getProfileObj();
		}

		Transaction transaction = profileObjFeature.createTransaction();
		transaction.addCommand(ProfileObjectWrapper.Util.fetchProfileObject(monitor, this, SoftwareAssembly.class, SoftwareAssembly.EOBJECT_PATH));
		transaction.commit();
		return getProfileObj();
		// BEGIN GENERATED CODE
	}

	private final VersionedFeature profileFeature = new VersionedFeature(this, ScaPackage.Literals.SCA_WAVEFORM_FACTORY__PROFILE);

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 14.0
	 *        <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String fetchProfile(IProgressMonitor monitor) {
		// END GENERATED CODE
		if (isDisposed()) {
			return null;
		}
		if (isSetProfile()) {
			return getProfile();
		}

		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching profile", 3);
		if (subMonitor.isCanceled()) {
			throw new OperationCanceledException();
		}
		ApplicationFactory resource = fetchNarrowedObject(subMonitor.newChild(1));

		Transaction transaction = profileFeature.createTransaction();
		if (resource != null) {
			try {
				if (subMonitor.isCanceled()) {
					throw new OperationCanceledException();
				}
				String newValue = resource.softwareProfile();
				transaction.append(new SetLocalAttributeCommand(this, newValue, ScaPackage.Literals.SCA_WAVEFORM_FACTORY__PROFILE));
			} catch (final SystemException e) {
				IStatus startedStatus = new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch profile.", e);
				transaction.append(new UnsetLocalAttributeCommand(this, startedStatus, ScaPackage.Literals.SCA_WAVEFORM_FACTORY__PROFILE));
			}
			subMonitor.worked(1);
		} else {
			transaction.append(new UnsetLocalAttributeCommand(this, null, ScaPackage.Literals.SCA_WAVEFORM_FACTORY__PROFILE));
		}

		subMonitor.setWorkRemaining(1);
		transaction.commit();
		subMonitor.done();
		return getProfile();
		// BEGIN GENERATED CODE
	}

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 * @throws InterruptedException
	 * 
	 * @generated NOT
	 */
	@Override
	public void fetchAttributes(IProgressMonitor monitor) {
		// END GENERATED CODE
		SubMonitor subMonitor = SubMonitor.convert(monitor, 5);

		if (subMonitor.isCanceled()) {
			throw new OperationCanceledException();
		}
		super.fetchAttributes(subMonitor.newChild(1));

		if (subMonitor.isCanceled()) {
			throw new OperationCanceledException();
		}
		fetchName(subMonitor.newChild(1));

		if (subMonitor.isCanceled()) {
			throw new OperationCanceledException();
		}
		fetchIdentifier(subMonitor.newChild(1));

		if (subMonitor.isCanceled()) {
			throw new OperationCanceledException();
		}
		fetchProfile(subMonitor.newChild(1));

		if (subMonitor.isCanceled()) {
			throw new OperationCanceledException();
		}
		fetchProfileObject(subMonitor.newChild(1));

		subMonitor.done();
		// BEGIN GENERATED CODE
	}

	// END GENERATED CODE

	@Override
	protected void internalFetchChildren(IProgressMonitor monitor) throws InterruptedException {
		// Do nothing
	}

	/**
	 * @since 14.0
	 */
	@Override
	public Application create(final String name, final DataType[] initConfiguration, final DeviceAssignmentType[] deviceAssignments)
		throws CreateApplicationError, CreateApplicationRequestError, InvalidInitConfiguration, CreateApplicationInsufficientCapacityError {
		ScaWaveform result = createWaveform(null, name, initConfiguration, deviceAssignments, RefreshDepth.NONE);
		if (result != null) {
			return result.fetchNarrowedObject(null);
		}
		return null;
	}

	// BEGIN GENERATED CODE

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ScaPackage.SCA_WAVEFORM_FACTORY__DOM_MGR:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetDomMgr((ScaDomainManager) otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ScaPackage.SCA_WAVEFORM_FACTORY__DOM_MGR:
			return basicSetDomMgr(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
		case ScaPackage.SCA_WAVEFORM_FACTORY__DOM_MGR:
			return eInternalContainer().eInverseRemove(this, ScaPackage.SCA_DOMAIN_MANAGER__WAVEFORM_FACTORIES, ScaDomainManager.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ScaPackage.SCA_WAVEFORM_FACTORY__PROFILE_URI:
			return getProfileURI();
		case ScaPackage.SCA_WAVEFORM_FACTORY__PROFILE_OBJ:
			if (resolve)
				return getProfileObj();
			return basicGetProfileObj();
		case ScaPackage.SCA_WAVEFORM_FACTORY__DOM_MGR:
			if (resolve)
				return getDomMgr();
			return basicGetDomMgr();
		case ScaPackage.SCA_WAVEFORM_FACTORY__IDENTIFIER:
			return getIdentifier();
		case ScaPackage.SCA_WAVEFORM_FACTORY__NAME:
			return getName();
		case ScaPackage.SCA_WAVEFORM_FACTORY__PROFILE:
			return getProfile();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ScaPackage.SCA_WAVEFORM_FACTORY__PROFILE_URI:
			setProfileURI((URI) newValue);
			return;
		case ScaPackage.SCA_WAVEFORM_FACTORY__PROFILE_OBJ:
			setProfileObj((SoftwareAssembly) newValue);
			return;
		case ScaPackage.SCA_WAVEFORM_FACTORY__DOM_MGR:
			setDomMgr((ScaDomainManager) newValue);
			return;
		case ScaPackage.SCA_WAVEFORM_FACTORY__IDENTIFIER:
			setIdentifier((String) newValue);
			return;
		case ScaPackage.SCA_WAVEFORM_FACTORY__NAME:
			setName((String) newValue);
			return;
		case ScaPackage.SCA_WAVEFORM_FACTORY__PROFILE:
			setProfile((String) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case ScaPackage.SCA_WAVEFORM_FACTORY__PROFILE_URI:
			unsetProfileURI();
			return;
		case ScaPackage.SCA_WAVEFORM_FACTORY__PROFILE_OBJ:
			unsetProfileObj();
			return;
		case ScaPackage.SCA_WAVEFORM_FACTORY__DOM_MGR:
			setDomMgr((ScaDomainManager) null);
			return;
		case ScaPackage.SCA_WAVEFORM_FACTORY__IDENTIFIER:
			unsetIdentifier();
			return;
		case ScaPackage.SCA_WAVEFORM_FACTORY__NAME:
			unsetName();
			return;
		case ScaPackage.SCA_WAVEFORM_FACTORY__PROFILE:
			unsetProfile();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case ScaPackage.SCA_WAVEFORM_FACTORY__PROFILE_URI:
			return isSetProfileURI();
		case ScaPackage.SCA_WAVEFORM_FACTORY__PROFILE_OBJ:
			return isSetProfileObj();
		case ScaPackage.SCA_WAVEFORM_FACTORY__DOM_MGR:
			return basicGetDomMgr() != null;
		case ScaPackage.SCA_WAVEFORM_FACTORY__IDENTIFIER:
			return isSetIdentifier();
		case ScaPackage.SCA_WAVEFORM_FACTORY__NAME:
			return isSetName();
		case ScaPackage.SCA_WAVEFORM_FACTORY__PROFILE:
			return isSetProfile();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class< ? > baseClass) {
		if (baseClass == ApplicationFactoryOperations.class) {
			switch (derivedFeatureID) {
			default:
				return -1;
			}
		}
		if (baseClass == ProfileObjectWrapper.class) {
			switch (derivedFeatureID) {
			case ScaPackage.SCA_WAVEFORM_FACTORY__PROFILE_URI:
				return ScaPackage.PROFILE_OBJECT_WRAPPER__PROFILE_URI;
			case ScaPackage.SCA_WAVEFORM_FACTORY__PROFILE_OBJ:
				return ScaPackage.PROFILE_OBJECT_WRAPPER__PROFILE_OBJ;
			default:
				return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class< ? > baseClass) {
		if (baseClass == ApplicationFactoryOperations.class) {
			switch (baseFeatureID) {
			default:
				return -1;
			}
		}
		if (baseClass == ProfileObjectWrapper.class) {
			switch (baseFeatureID) {
			case ScaPackage.PROFILE_OBJECT_WRAPPER__PROFILE_URI:
				return ScaPackage.SCA_WAVEFORM_FACTORY__PROFILE_URI;
			case ScaPackage.PROFILE_OBJECT_WRAPPER__PROFILE_OBJ:
				return ScaPackage.SCA_WAVEFORM_FACTORY__PROFILE_OBJ;
			default:
				return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (profileURI: ");
		if (profileURIESet)
			result.append(profileURI);
		else
			result.append("<unset>");
		result.append(", identifier: ");
		if (identifierESet)
			result.append(identifier);
		else
			result.append("<unset>");
		result.append(", name: ");
		if (nameESet)
			result.append(name);
		else
			result.append("<unset>");
		result.append(", profile: ");
		if (profileESet)
			result.append(profile);
		else
			result.append("<unset>");
		result.append(')');
		return result.toString();
	}

	@Override
	public String identifier() {
		// END GENERATED CODE
		return getIdentifier();
		// BEGIN GENERATED CODE
	}

	@Override
	public String name() {
		// END GENERATED CODE
		return getName();
		// BEGIN GENERATED CODE
	}

	@Override
	public String softwareProfile() {
		// END GENERATED CODE
		return getProfile();
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 14.0
	 */
	public boolean mayHaveChildren() {
		return false;
	}

	private final VersionedFeature profileURIFeature = new VersionedFeature(this, ScaPackage.Literals.PROFILE_OBJECT_WRAPPER__PROFILE_URI);

	/**
	 * @since 14.0
	 * @generated NOT
	 */
	@Override
	public URI fetchProfileURI(IProgressMonitor monitor) {
		if (isDisposed()) {
			return null;
		}
		if (isSetProfileURI()) {
			return getProfileURI();
		}

		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetch Profile URI", 2);
		ScaDomainManager domMgr = getDomMgr();
		if (domMgr != null) {
			if (subMonitor.isCanceled()) {
				throw new OperationCanceledException();
			}
			ScaDomainManagerFileSystem fileSystem = domMgr.fetchFileManager(subMonitor.newChild(1), RefreshDepth.SELF);

			if (fileSystem != null) {
				Transaction transaction = profileURIFeature.createTransaction();
				if (subMonitor.isCanceled()) {
					throw new OperationCanceledException();
				}
				final URI newURI = fileSystem.createURI(fetchProfile(subMonitor.newChild(1)));
				transaction.addCommand(new ScaModelCommand() {

					@Override
					public void execute() {
						setProfileURI(newURI);
					}
				});
				transaction.commit();
			}
			subMonitor.done();
		}

		subMonitor.done();
		return getProfileURI();
	}

	/**
	 * @since 20.0
	 */
	@Override
	public ScaWaveform createWaveform(IProgressMonitor monitor, String name, DataType[] initConfiguration, DeviceAssignmentType[] deviceAssignments,
		RefreshDepth depth) throws CreateApplicationError, CreateApplicationRequestError, InvalidInitConfiguration, CreateApplicationInsufficientCapacityError {
		// END GENERATED CODE
		if (DEBUG.enabled) {
			DEBUG.enteringMethod(name, Arrays.toString(initConfiguration), Arrays.toString(deviceAssignments));
		}
		ApplicationFactory factory = fetchNarrowedObject(null);
		SubMonitor subMonitor = SubMonitor.convert(monitor, "Create Waveform" + name, 3);
		try {
			if (factory == null) {
				throw new IllegalStateException("App Factory is null");
			} else {
				if (initConfiguration == null) {
					initConfiguration = DATA_EMPTY_TYPE;
				}
				if (deviceAssignments == null) {
					deviceAssignments = DEVICE_EMPTY_TYPE;
				}

				if (subMonitor.isCanceled()) {
					throw new OperationCanceledException();
				}
				final Application app = factory.create(name, initConfiguration, deviceAssignments);

				ScaWaveform retVal = null;
				if (app != null) {
					final String ior = app.toString();
					retVal = ScaModelCommandWithResult.execute(this, new ScaModelCommandWithResult<ScaWaveform>() {
						@Override
						public void execute() {

							if (getDomMgr() != null) {
								// Check to be sure someone else didn't already add the waveform
								for (ScaWaveform w : getDomMgr().getWaveforms()) {
									if (ior.equals(w.getIor())) {
										setResult(w);
										return;
									}
								}

								ScaWaveform newWaveform = ScaFactory.eINSTANCE.createScaWaveform();
								newWaveform.setCorbaObj(app);
								getDomMgr().getWaveforms().add(newWaveform);
								setResult(newWaveform);
							}
						}

					});
					subMonitor.worked(1);
				}
				if (retVal == null) {
					throw new IllegalStateException("Unable to find Waveform after successful create.");
				}

				if (depth != RefreshDepth.NONE) {
					try {
						if (subMonitor.isCanceled()) {
							throw new OperationCanceledException();
						}
						retVal.refresh(subMonitor.newChild(1), depth);
					} catch (InterruptedException e) {
						throw new OperationCanceledException();
					}
				}
				if (DEBUG.enabled) {
					DEBUG.exitingMethod(retVal);
				}
				return retVal;
			}
		} finally {
			subMonitor.done();
		}
		// BEGIN GENERATED CODE
	}

} // ScaWaveformFactoryImpl

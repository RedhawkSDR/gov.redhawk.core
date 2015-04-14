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

import gov.redhawk.model.sca.ProfileObjectWrapper;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaAbstractComponent;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaPortContainer;
import gov.redhawk.model.sca.commands.MergePortsCommand;
import gov.redhawk.model.sca.commands.MergePortsCommand.PortData;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.commands.SetLocalAttributeCommand;
import gov.redhawk.model.sca.commands.UnsetLocalAttributeCommand;
import gov.redhawk.model.sca.commands.VersionedFeature;
import gov.redhawk.model.sca.commands.VersionedFeature.Transaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import mil.jpeojtrs.sca.prf.AbstractProperty;
import mil.jpeojtrs.sca.prf.Properties;
import mil.jpeojtrs.sca.scd.AbstractPort;
import mil.jpeojtrs.sca.scd.ScdPackage;
import mil.jpeojtrs.sca.spd.SoftPkg;
import mil.jpeojtrs.sca.spd.SpdPackage;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.ValueListIterator;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.omg.CORBA.BAD_OPERATION;
import org.omg.CORBA.IntHolder;
import org.omg.CORBA.SystemException;

import CF.DataType;
import CF.LifeCycleOperations;
import CF.LogEvent;
import CF.PortSupplierOperations;
import CF.PropertiesHolder;
import CF.Resource;
import CF.ResourceOperations;
import CF.TestableObjectOperations;
import CF.UnknownIdentifier;
import CF.UnknownProperties;
import CF.LifeCyclePackage.InitializeError;
import CF.LifeCyclePackage.ReleaseError;
import CF.PortSupplierPackage.UnknownPort;
import CF.PropertySetPackage.InvalidConfiguration;
import CF.PropertySetPackage.PartialConfiguration;
import CF.ResourcePackage.StartError;
import CF.ResourcePackage.StopError;
import CF.TestableObjectPackage.UnknownTest;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Abstract Component</b></em>'.
 * 
 * @since 12.0
 *        <!-- end-user-doc -->
 *        <p>
 *        The following features are implemented:
 *        <ul>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaAbstractComponentImpl#getPorts <em>Ports</em>}</li>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaAbstractComponentImpl#getIdentifier <em>Identifier</em>}</li>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaAbstractComponentImpl#getStarted <em>Started</em>}</li>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaAbstractComponentImpl#getProfile <em>Profile</em>}</li>
 *        </ul>
 *        </p>
 *
 * @generated
 */
public abstract class ScaAbstractComponentImpl< R extends Resource > extends ScaPropertyContainerImpl<R, SoftPkg> implements ScaAbstractComponent<R> {

	/**
	 * The cached value of the '{@link #getPorts() <em>Ports</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getPorts()
	 * @generated
	 * @ordered
	 */
	protected EList<ScaPort< ? , ? >> ports;

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
	 * The default value of the '{@link #getStarted() <em>Started</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getStarted()
	 * @generated
	 * @ordered
	 */
	protected static final Boolean STARTED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStarted() <em>Started</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getStarted()
	 * @generated
	 * @ordered
	 */
	protected Boolean started = STARTED_EDEFAULT;

	/**
	 * This is true if the Started attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean startedESet;

	/**
	 * The default value of the '{@link #getProfile() <em>Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @see #getProfile()
	 * @generated
	 * @ordered
	 */
	protected static final String PROFILE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProfile() <em>Profile</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @see #getProfile()
	 * @generated
	 * @ordered
	 */
	protected String profile = PROFILE_EDEFAULT;

	/**
	 * This is true if the Profile attribute has been set.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
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
	protected ScaAbstractComponentImpl() {
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
		return ScaPackage.Literals.SCA_ABSTRACT_COMPONENT;
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
	public void setProfileObj(SoftPkg newProfileObj) {
		super.setProfileObj(newProfileObj);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<ScaPort< ? , ? >> getPorts() {
		if (ports == null) {
			ports = new EObjectContainmentWithInverseEList.Unsettable<ScaPort< ? , ? >>(ScaPort.class, this, ScaPackage.SCA_ABSTRACT_COMPONENT__PORTS,
				ScaPackage.SCA_PORT__PORT_CONTAINER);
		}
		return ports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void unsetPorts() {
		if (ports != null)
			((InternalEList.Unsettable< ? >) ports).unset();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isSetPorts() {
		return ports != null && ((InternalEList.Unsettable< ? >) ports).isSet();
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
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_ABSTRACT_COMPONENT__IDENTIFIER, oldIdentifier, identifier, !oldIdentifierESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScaPackage.SCA_ABSTRACT_COMPONENT__IDENTIFIER, oldIdentifier, IDENTIFIER_EDEFAULT,
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
	public Boolean getStarted() {
		return started;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setStarted(Boolean newStarted) {
		Boolean oldStarted = started;
		started = newStarted;
		boolean oldStartedESet = startedESet;
		startedESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_ABSTRACT_COMPONENT__STARTED, oldStarted, started, !oldStartedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void unsetStarted() {
		Boolean oldStarted = started;
		boolean oldStartedESet = startedESet;
		started = STARTED_EDEFAULT;
		startedESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScaPackage.SCA_ABSTRACT_COMPONENT__STARTED, oldStarted, STARTED_EDEFAULT, oldStartedESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isSetStarted() {
		return startedESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 */
	public String getProfile() {
		return profile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 */
	public void setProfile(String newProfile) {
		String oldProfile = profile;
		profile = newProfile;
		boolean oldProfileESet = profileESet;
		profileESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_ABSTRACT_COMPONENT__PROFILE, oldProfile, profile, !oldProfileESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetProfile() {
		String oldProfile = profile;
		boolean oldProfileESet = profileESet;
		profile = PROFILE_EDEFAULT;
		profileESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScaPackage.SCA_ABSTRACT_COMPONENT__PROFILE, oldProfile, PROFILE_EDEFAULT, oldProfileESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 19.0
	 *        <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetProfile() {
		return profileESet;
	}

	private final VersionedFeature identifierRevision = new VersionedFeature(this, ScaPackage.Literals.SCA_ABSTRACT_COMPONENT__IDENTIFIER);

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
		if (isSetIdentifier()) {
			return getIdentifier();
		}
		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetch Identifier", 3);
		R resource = fetchNarrowedObject(subMonitor.newChild(1));
		Transaction transaction = identifierRevision.createTransaction();
		if (resource != null) {
			try {
				String newId = resource.identifier();
				transaction.append(new SetLocalAttributeCommand(this, newId, ScaPackage.Literals.SCA_ABSTRACT_COMPONENT__IDENTIFIER));
			} catch (final SystemException e) {
				Status status = new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch identifier.", e);
				transaction.append(new UnsetLocalAttributeCommand(this, status, ScaPackage.Literals.SCA_ABSTRACT_COMPONENT__IDENTIFIER));
			}
			subMonitor.worked(1);
		} else {
			transaction.append(new UnsetLocalAttributeCommand(this, null, ScaPackage.Literals.SCA_ABSTRACT_COMPONENT__IDENTIFIER));
		}
		subMonitor.setWorkRemaining(1);
		transaction.commit();
		subMonitor.done();
		return getIdentifier();
		// BEGIN GENERATED CODE
	}

	private final VersionedFeature startedRevision = new VersionedFeature(this, ScaPackage.Literals.SCA_ABSTRACT_COMPONENT__STARTED);

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 14.0
	 *        <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Boolean fetchStarted(IProgressMonitor monitor) {
		// END GENERATED CODE
		if (isDisposed()) {
			return false;
		}
		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching Started", 3);
		R resource = fetchNarrowedObject(subMonitor.newChild(1));
		Transaction transaction = startedRevision.createTransaction();
		if (resource != null) {
			try {
				boolean newStarted = resource.started();
				transaction.append(new SetLocalAttributeCommand(this, newStarted, ScaPackage.Literals.SCA_ABSTRACT_COMPONENT__STARTED));
			} catch (BAD_OPERATION e) {
				// the started attribute is an extension to SCA
				// that was added in R.1.7.0...so don't expect it
				transaction.append(new UnsetLocalAttributeCommand(this, null, ScaPackage.Literals.SCA_ABSTRACT_COMPONENT__STARTED));
			} catch (final SystemException e) {
				IStatus startedStatus = new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch started.", e);
				transaction.append(new UnsetLocalAttributeCommand(this, startedStatus, ScaPackage.Literals.SCA_ABSTRACT_COMPONENT__STARTED));
			}
			subMonitor.worked(1);
		} else {
			transaction.append(new UnsetLocalAttributeCommand(this, Status.OK_STATUS, ScaPackage.Literals.SCA_ABSTRACT_COMPONENT__STARTED));
		}
		subMonitor.setWorkRemaining(1);
		transaction.commit();
		subMonitor.worked(1);
		subMonitor.done();
		return getStarted();
		// BEGIN GENERATED CODE
	}

	@Override
	public String identifier() {
		// END GENERATED CODE
		return this.getIdentifier();
		// BEGIN GENERATED CODE
	}

	@Override
	public boolean started() {
		Boolean retVal = getStarted();
		if (retVal == null) {
			return false;
		}
		return retVal;
	}

	@Override
	public void start() throws StartError {
		// END GENERATED CODE
		R resource = fetchNarrowedObject(null);
		if (resource == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		resource.start();
		fetchStarted(null);
		// BEGIN GENERATED CODE
	}

	@Override
	public void stop() throws StopError {
		// END GENERATED CODE
		R resource = fetchNarrowedObject(null);
		if (resource == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		resource.stop();
		fetchStarted(null);
		// BEGIN GENERATED CODE
	}

	@Override
	public void initialize() throws InitializeError {
		// END GENERATED CODE
		R resource = fetchNarrowedObject(null);
		if (resource == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		resource.initialize();
		fetchAttributes(null);
		// BEGIN GENERATED CODE
	}

	private boolean released;

	@Override
	public void releaseObject() throws ReleaseError {
		// END GENERATED CODE
		if (released) {
			return;
		}
		R resource = fetchNarrowedObject(null);
		if (resource != null) {
			resource.releaseObject();
			released = true;
		}
		released = true;
		TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(this);
		Command command = new ScaModelCommand() {

			@Override
			public void execute() {
				EcoreUtil.delete(ScaAbstractComponentImpl.this);
			}
		};
		if (domain != null) {
			domain.getCommandStack().execute(command);
		} else {
			ScaModelCommand.execute(this, command);
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ScaPackage.SCA_ABSTRACT_COMPONENT__PORTS:
			return ((InternalEList<InternalEObject>) (InternalEList< ? >) getPorts()).basicAdd(otherEnd, msgs);
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
		case ScaPackage.SCA_ABSTRACT_COMPONENT__PORTS:
			return ((InternalEList< ? >) getPorts()).basicRemove(otherEnd, msgs);
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
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ScaPackage.SCA_ABSTRACT_COMPONENT__PORTS:
			return getPorts();
		case ScaPackage.SCA_ABSTRACT_COMPONENT__IDENTIFIER:
			return getIdentifier();
		case ScaPackage.SCA_ABSTRACT_COMPONENT__STARTED:
			return getStarted();
		case ScaPackage.SCA_ABSTRACT_COMPONENT__PROFILE:
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
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ScaPackage.SCA_ABSTRACT_COMPONENT__PORTS:
			getPorts().clear();
			getPorts().addAll((Collection< ? extends ScaPort< ? , ? >>) newValue);
			return;
		case ScaPackage.SCA_ABSTRACT_COMPONENT__IDENTIFIER:
			setIdentifier((String) newValue);
			return;
		case ScaPackage.SCA_ABSTRACT_COMPONENT__STARTED:
			setStarted((Boolean) newValue);
			return;
		case ScaPackage.SCA_ABSTRACT_COMPONENT__PROFILE:
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
		case ScaPackage.SCA_ABSTRACT_COMPONENT__PORTS:
			unsetPorts();
			return;
		case ScaPackage.SCA_ABSTRACT_COMPONENT__IDENTIFIER:
			unsetIdentifier();
			return;
		case ScaPackage.SCA_ABSTRACT_COMPONENT__STARTED:
			unsetStarted();
			return;
		case ScaPackage.SCA_ABSTRACT_COMPONENT__PROFILE:
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
		case ScaPackage.SCA_ABSTRACT_COMPONENT__PORTS:
			return isSetPorts();
		case ScaPackage.SCA_ABSTRACT_COMPONENT__IDENTIFIER:
			return isSetIdentifier();
		case ScaPackage.SCA_ABSTRACT_COMPONENT__STARTED:
			return isSetStarted();
		case ScaPackage.SCA_ABSTRACT_COMPONENT__PROFILE:
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
		if (baseClass == LifeCycleOperations.class) {
			switch (derivedFeatureID) {
			default:
				return -1;
			}
		}
		if (baseClass == TestableObjectOperations.class) {
			switch (derivedFeatureID) {
			default:
				return -1;
			}
		}
		if (baseClass == PortSupplierOperations.class) {
			switch (derivedFeatureID) {
			default:
				return -1;
			}
		}
		if (baseClass == ResourceOperations.class) {
			switch (derivedFeatureID) {
			default:
				return -1;
			}
		}
		if (baseClass == ScaPortContainer.class) {
			switch (derivedFeatureID) {
			case ScaPackage.SCA_ABSTRACT_COMPONENT__PORTS:
				return ScaPackage.SCA_PORT_CONTAINER__PORTS;
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
		if (baseClass == LifeCycleOperations.class) {
			switch (baseFeatureID) {
			default:
				return -1;
			}
		}
		if (baseClass == TestableObjectOperations.class) {
			switch (baseFeatureID) {
			default:
				return -1;
			}
		}
		if (baseClass == PortSupplierOperations.class) {
			switch (baseFeatureID) {
			default:
				return -1;
			}
		}
		if (baseClass == ResourceOperations.class) {
			switch (baseFeatureID) {
			default:
				return -1;
			}
		}
		if (baseClass == ScaPortContainer.class) {
			switch (baseFeatureID) {
			case ScaPackage.SCA_PORT_CONTAINER__PORTS:
				return ScaPackage.SCA_ABSTRACT_COMPONENT__PORTS;
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
		result.append(" (identifier: ");
		if (identifierESet)
			result.append(identifier);
		else
			result.append("<unset>");
		result.append(", started: ");
		if (startedESet)
			result.append(started);
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

	/**
	 * @since 14.0
	 */
	@Override
	public void runTest(final int testid, final PropertiesHolder testValues) throws UnknownTest, UnknownProperties {
		// END GENERATED CODE
		R resource = fetchNarrowedObject(null);
		if (resource == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		resource.runTest(testid, testValues);
		// BEGIN GENERATED CODE
	}

	@Override
	public void configure(final DataType[] configProperties) throws InvalidConfiguration, PartialConfiguration {
		// END GENERATED CODE
		R resource = fetchNarrowedObject(null);
		if (resource == null) {
			return;
		}
		resource.configure(configProperties);
		fetchProperties(null);
		// BEGIN GENERATED CODE
	}

	@Override
	public void query(final PropertiesHolder configProperties) throws UnknownProperties {
		// END GENERATED CODE
		R resource = fetchNarrowedObject(null);
		if (resource == null) {
			return;
		}
		resource.query(configProperties);
		// BEGIN GENERATED CODE
	}

	@Override
	public org.omg.CORBA.Object getPort(final String name) throws UnknownPort {
		// END GENERATED CODE
		R resource = fetchNarrowedObject(null);
		if (resource == null) {
			return null;
		}
		return resource.getPort(name);
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 14.0
	 *        <!-- end-user-doc -->
	 * @throws InterruptedException
	 * @generated NOT
	 */
	@Override
	public EList<ScaPort< ? , ? >> fetchPorts(IProgressMonitor monitor) {
		if (isDisposed()) {
			return ECollections.emptyEList();
		}
		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching ports", 2);
		internalFetchPorts(subMonitor.newChild(1));
		ScaPort< ? , ? >[] ports = null;
		try {
			ports = ScaModelCommand.runExclusive(this, new RunnableWithResult.Impl<ScaPort< ? , ? >[]>() {
				@Override
				public void run() {
					setResult(getPorts().toArray(new ScaPort< ? , ? >[getPorts().size()]));
				}
			});
		} catch (InterruptedException e) {
			// PASS
		}
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
		if (ports != null) {
			return ECollections.unmodifiableEList(new BasicEList<ScaPort< ? , ? >>(Arrays.asList(ports)));
		} else {
			return ECollections.emptyEList();
		}
	}

	private static final EStructuralFeature[] PORTS_GROUP_PATH = { ScaPackage.Literals.PROFILE_OBJECT_WRAPPER__PROFILE_OBJ,
		SpdPackage.Literals.SOFT_PKG__DESCRIPTOR, SpdPackage.Literals.DESCRIPTOR__COMPONENT, ScdPackage.Literals.SOFTWARE_COMPONENT__COMPONENT_FEATURES,
		ScdPackage.Literals.COMPONENT_FEATURES__PORTS, ScdPackage.Literals.PORTS__GROUP };

	private final VersionedFeature portRevision = new VersionedFeature(this, ScaPackage.Literals.SCA_PORT_CONTAINER__PORTS);

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 14.0
	 *        <!-- end-user-doc -->
	 * @throws InterruptedException
	 * @generated NOT
	 */
	protected void internalFetchPorts(IProgressMonitor monitor) {
		// END GENERATED CODE
		if (isDisposed()) {
			return;
		}
		SubMonitor subMonitor = SubMonitor.convert(monitor, 4);
		R currentObj = this.fetchNarrowedObject(subMonitor.newChild(1));
		Transaction transaction = portRevision.createTransaction();
		if (currentObj != null) {
			fetchProfileObject(subMonitor.newChild(1));
			FeatureMap portGroup = ScaEcoreUtils.getFeature(this, PORTS_GROUP_PATH);
			int size = getPorts().size();
			int groupSize = (portGroup == null) ? 0 : portGroup.size();
			if (isSetPorts() && size == groupSize) {
				return;
			}

			List<MergePortsCommand.PortData> newPorts = new ArrayList<MergePortsCommand.PortData>();
			// Load all of the ports
			final MultiStatus fetchPortsStatus = new MultiStatus(ScaModelPlugin.ID, Status.OK, "Fetch ports status.", null);
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
			subMonitor.worked(1);

			MergePortsCommand command = new MergePortsCommand(this, newPorts, fetchPortsStatus);

			// Perform the actions
			transaction.addCommand(command);
		} else {
			transaction.addCommand(new UnsetLocalAttributeCommand(this, Status.OK_STATUS, ScaPackage.Literals.SCA_PORT_CONTAINER__PORTS));
		}
		subMonitor.setWorkRemaining(1);
		transaction.commit();
		subMonitor.worked(1);
		subMonitor.done();
		// BEGIN GENERATED CODE
	}

	@Override
	protected void internalFetchChildren(IProgressMonitor monitor) throws InterruptedException {
		internalFetchPorts(monitor);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public ScaPort< ? , ? > getScaPort(String name) {
		// END GENERATED CODE
		for (ScaPort< ? , ? > port : getPorts()) {
			if (port.getName().equals(name)) {
				return port;
			}
		}
		return null;
		// BEGIN GENERATED CODE
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
		if (isDisposed()) {
			return;
		}
		SubMonitor subMonitor = SubMonitor.convert(monitor, 5);
		super.fetchAttributes(subMonitor.newChild(1));
		fetchIdentifier(subMonitor.newChild(1));
		fetchStarted(subMonitor.newChild(1));
		fetchProfile(subMonitor.newChild(1));
		fetchProfileObject(subMonitor.newChild(1));
		subMonitor.done();
	}

	/**
	 * @since 14.0
	 */
	@Override
	protected void notifyChanged(Notification msg) {
		super.notifyChanged(msg);
		switch (msg.getFeatureID(ScaAbstractComponent.class)) {
		case ScaPackage.SCA_ABSTRACT_COMPONENT__OBJ:
			// unsetIdentifier();
			unsetStarted();
			unsetPorts();
			break;
		default:
			break;
		}
	}

	private static final EStructuralFeature[] PRF_PATH = { SpdPackage.Literals.SOFT_PKG__PROPERTY_FILE, SpdPackage.Literals.PROPERTY_FILE__PROPERTIES };

	@Override
	protected List<AbstractProperty> fetchPropertyDefinitions(IProgressMonitor monitor) {
		if (isDisposed()) {
			return Collections.emptyList();
		}
		Properties propertyDefs = ScaEcoreUtils.getFeature(fetchProfileObject(monitor), PRF_PATH);
		List<AbstractProperty> retVal;
		if (propertyDefs != null) {
			retVal = new ArrayList<AbstractProperty>(propertyDefs.getProperties().size());
			for (ValueListIterator<Object> i = propertyDefs.getProperties().valueListIterator(); i.hasNext();) {
				Object propertyDefObj = i.next();
				if (propertyDefObj instanceof AbstractProperty) {
					retVal.add((AbstractProperty) propertyDefObj);
				}
			}
		} else {
			retVal = Collections.emptyList();
		}
		return retVal;
	}

	private final VersionedFeature profileObjectRevision = new VersionedFeature(this, ScaPackage.Literals.PROFILE_OBJECT_WRAPPER__PROFILE_OBJ);

	/**
	 * @generated NOT {@inheritDoc}
	 * @since 14.0
	 */
	@Override
	public SoftPkg fetchProfileObject(IProgressMonitor monitor) {
		if (isDisposed()) {
			return null;
		}
		if (isSetProfileObj()) {
			return getProfileObj();
		}
		Transaction transaction = profileObjectRevision.createTransaction();
		Command command = ProfileObjectWrapper.Util.fetchProfileObject(monitor, this, SoftPkg.class, SoftPkg.EOBJECT_PATH);
		transaction.addCommand(command);
		transaction.commit();
		return getProfileObj();
	}

	private final VersionedFeature profileFeature = new VersionedFeature(this, ScaPackage.Literals.SCA_ABSTRACT_COMPONENT__PROFILE);

	/**
	 * @since 19.0
	 * @generated NOT {@inheritDoc}
	 */
	@Override
	public String fetchProfile(IProgressMonitor monitor) {
		if (isDisposed()) {
			return null;
		}
		if (isSetProfile()) {
			return getProfile();
		}
		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching profile", 3);
		R localObj = fetchNarrowedObject(subMonitor.newChild(1));
		Transaction transaction = profileFeature.createTransaction();
		if (localObj != null) {
			final String newProfile;
			try {
				newProfile = localObj.softwareProfile();
				transaction.addCommand(new SetLocalAttributeCommand(this, newProfile, ScaPackage.Literals.SCA_ABSTRACT_COMPONENT__PROFILE));
			} catch (BAD_OPERATION e) {
				// the profile attribute was moved from Device to resource in 1.10.0...so don't expect it
				transaction.append(new SetLocalAttributeCommand(this, null, ScaPackage.Literals.SCA_ABSTRACT_COMPONENT__PROFILE, Status.OK_STATUS));
			} catch (final SystemException e) {
				IStatus startedStatus = new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch profile.", e);
				transaction.append(new UnsetLocalAttributeCommand(this, startedStatus, ScaPackage.Literals.SCA_ABSTRACT_COMPONENT__PROFILE));
			}
			subMonitor.worked(1);
		} else {
			transaction.addCommand(new UnsetLocalAttributeCommand(this, null, ScaPackage.Literals.SCA_ABSTRACT_COMPONENT__PROFILE));
		}
		transaction.commit();
		subMonitor.done();
		return getProfile();
	}

	/**
	 * @since 19.0
	 */
	@Override
	public String softwareProfile() {
		return getProfile();
	}

	/**
	 * @since 19.0
	 */
	@Override
	public LogEvent[] retrieve_records(IntHolder howMany, int startingRecord) {
		return getObj().retrieve_records(howMany, startingRecord);
	}

	/**
	 * @since 19.0
	 */
	@Override
	public LogEvent[] retrieve_records_by_date(IntHolder howMany, long to_timeStamp) {
		return getObj().retrieve_records_by_date(howMany, to_timeStamp);
	}

	/**
	 * @since 19.0
	 */
	@Override
	public LogEvent[] retrieve_records_from_date(IntHolder howMany, long from_timeStamp) {
		return getObj().retrieve_records_from_date(howMany, from_timeStamp);
	}

	/**
	 * @since 19.0
	 */
	@Override
	public int log_level() {
		// END GENERATED CODE
		R resource = fetchNarrowedObject(null);
		if (resource == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		return resource.log_level();
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 19.0
	 */
	@Override
	public void log_level(int newLog_level) {
		// END GENERATED CODE
		R resource = fetchNarrowedObject(null);
		if (resource == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		resource.log_level(newLog_level);
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 19.0
	 */
	@Override
	public void setLogLevel(String logger_id, int newLevel) throws UnknownIdentifier {
		// END GENERATED CODE
		R resource = fetchNarrowedObject(null);
		if (resource == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		resource.setLogLevel(logger_id, newLevel);
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 19.0
	 */
	@Override
	public String getLogConfig() {
		// END GENERATED CODE
		R resource = fetchNarrowedObject(null);
		if (resource == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		return resource.getLogConfig();
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 19.0
	 */
	@Override
	public void setLogConfig(String config_contents) {
		// END GENERATED CODE
		R resource = fetchNarrowedObject(null);
		if (resource == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		resource.setLogConfig(config_contents);
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 19.0
	 */
	@Override
	public void setLogConfigURL(String config_url) {
		// END GENERATED CODE
		R resource = fetchNarrowedObject(null);
		if (resource == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		resource.setLogConfigURL(config_url);
		// BEGIN GENERATED CODE
	}
} // ScaAbstractComponentImpl

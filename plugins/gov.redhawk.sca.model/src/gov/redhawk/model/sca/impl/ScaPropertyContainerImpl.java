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
import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaPropertyContainer;
import gov.redhawk.model.sca.commands.MergePropertiesCommand;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.commands.SetPropertiesValuesCommand;
import gov.redhawk.model.sca.commands.UnsetLocalAttributeCommand;
import gov.redhawk.model.sca.commands.VersionedFeature;
import gov.redhawk.model.sca.commands.VersionedFeature.Transaction;
import gov.redhawk.sca.util.PluginUtil;

import java.util.Collection;
import java.util.List;

import mil.jpeojtrs.sca.prf.AbstractProperty;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.omg.CORBA.SystemException;

import CF.DataType;
import CF.InvalidIdentifier;
import CF.InvalidObjectReference;
import CF.PropertiesHolder;
import CF.PropertySet;
import CF.PropertySetOperations;
import CF.UnknownProperties;
import CF.PropertySetPackage.InvalidConfiguration;
import CF.PropertySetPackage.PartialConfiguration;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Property Container</b></em>'.
 * 
 * @since 12.0
 *        <!-- end-user-doc -->
 *        <p>
 *        The following features are implemented:
 *        <ul>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaPropertyContainerImpl#getProfileURI <em>Profile URI</em>}</li>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaPropertyContainerImpl#getProfileObj <em>Profile Obj</em>}</li>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaPropertyContainerImpl#getRootFileStore <em>Root File Store</em>}</li>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaPropertyContainerImpl#getProperties <em>Properties</em>}</li>
 *        </ul>
 *        </p>
 *
 * @generated
 */
public abstract class ScaPropertyContainerImpl< P extends org.omg.CORBA.Object, E extends Object > extends CorbaObjWrapperImpl<P> implements
		ScaPropertyContainer<P, E> {
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
	protected E profileObj;
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
	 * The default value of the '{@link #getRootFileStore() <em>Root File Store</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getRootFileStore()
	 * @generated
	 * @ordered
	 */
	protected static final IFileStore ROOT_FILE_STORE_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<ScaAbstractProperty< ? >> properties;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ScaPropertyContainerImpl() {
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
		return ScaPackage.Literals.SCA_PROPERTY_CONTAINER;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_PROPERTY_CONTAINER__PROFILE_URI, oldProfileURI, profileURI, !oldProfileURIESet));
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
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScaPackage.SCA_PROPERTY_CONTAINER__PROFILE_URI, oldProfileURI, PROFILE_URI_EDEFAULT,
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
	@SuppressWarnings("unchecked")
	public E getProfileObj() {
		if (profileObj != null && ((EObject) profileObj).eIsProxy()) {
			InternalEObject oldProfileObj = (InternalEObject) profileObj;
			profileObj = (E) eResolveProxy(oldProfileObj);
			if (profileObj != oldProfileObj) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ScaPackage.SCA_PROPERTY_CONTAINER__PROFILE_OBJ, oldProfileObj, profileObj));
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
	public E basicGetProfileObj() {
		return profileObj;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setProfileObj(E newProfileObj) {
		E oldProfileObj = profileObj;
		profileObj = newProfileObj;
		boolean oldProfileObjESet = profileObjESet;
		profileObjESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_PROPERTY_CONTAINER__PROFILE_OBJ, oldProfileObj, profileObj, !oldProfileObjESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void unsetProfileObj() {
		E oldProfileObj = profileObj;
		boolean oldProfileObjESet = profileObjESet;
		profileObj = null;
		profileObjESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScaPackage.SCA_PROPERTY_CONTAINER__PROFILE_OBJ, oldProfileObj, null, oldProfileObjESet));
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

	private static final DataType[] EMPTY_DATA_TYPE_ARRAY = new DataType[0];

	@Override
	protected void notifyChanged(Notification msg) {
		// END GENERATED CODE
		super.notifyChanged(msg);
		if (msg.isTouch()) {
			return;
		}
		switch (msg.getFeatureID(ScaPropertyContainer.class)) {
		case ScaPackage.SCA_PROPERTY_CONTAINER__PROFILE_OBJ:
			if (!PluginUtil.equals(msg.getOldValue(), msg.getNewValue())) {
				unsetProperties();
			}
			break;
		case ScaPackage.SCA_PROPERTY_CONTAINER__PROFILE_URI:
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
	 * 
	 * @deprecated Use {@link #getProfileURI()} instead
	 *             <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Deprecated
	@Override
	public IFileStore getRootFileStore() {
		// END GENERATED CODE
		try {
			URI uri = getProfileURI();
			if (uri != null) {
				return EFS.getStore(java.net.URI.create(uri.toString()));
			} else {
				return null;
			}
		} catch (Exception e) { // SUPPRESS CHECKSTYLE Deprecated
			return null;
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
	public EList<ScaAbstractProperty< ? >> getProperties() {
		if (properties == null) {
			properties = new EObjectContainmentEList.Unsettable<ScaAbstractProperty< ? >>(ScaAbstractProperty.class, this,
				ScaPackage.SCA_PROPERTY_CONTAINER__PROPERTIES);
		}
		return properties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void unsetProperties() {
		if (properties != null)
			((InternalEList.Unsettable< ? >) properties).unset();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isSetProperties() {
		return properties != null && ((InternalEList.Unsettable< ? >) properties).isSet();
	}

	/**
	 * @since 14.0
	 */
	@Override
	public abstract void query(final PropertiesHolder configProperties) throws UnknownProperties;

	/**
	 * <!-- begin-user-doc -->
	 * @since 19.1
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String registerPropertyListener(org.omg.CORBA.Object obj, EList<String> prop_ids, float interval) throws UnknownProperties, InvalidObjectReference {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 14.0
	 *        <!-- end-user-doc -->
	 * @generated NOT
	 * 
	 */
	@Override
	public abstract E fetchProfileObject(IProgressMonitor monitor);

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 14.0
	 *        <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public abstract URI fetchProfileURI(IProgressMonitor monitor);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ScaPackage.SCA_PROPERTY_CONTAINER__PROPERTIES:
			return ((InternalEList< ? >) getProperties()).basicRemove(otherEnd, msgs);
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
		case ScaPackage.SCA_PROPERTY_CONTAINER__PROFILE_URI:
			return getProfileURI();
		case ScaPackage.SCA_PROPERTY_CONTAINER__PROFILE_OBJ:
			if (resolve)
				return getProfileObj();
			return basicGetProfileObj();
		case ScaPackage.SCA_PROPERTY_CONTAINER__ROOT_FILE_STORE:
			return getRootFileStore();
		case ScaPackage.SCA_PROPERTY_CONTAINER__PROPERTIES:
			return getProperties();
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
		case ScaPackage.SCA_PROPERTY_CONTAINER__PROFILE_URI:
			setProfileURI((URI) newValue);
			return;
		case ScaPackage.SCA_PROPERTY_CONTAINER__PROFILE_OBJ:
			setProfileObj((E) newValue);
			return;
		case ScaPackage.SCA_PROPERTY_CONTAINER__PROPERTIES:
			getProperties().clear();
			getProperties().addAll((Collection< ? extends ScaAbstractProperty< ? >>) newValue);
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
		case ScaPackage.SCA_PROPERTY_CONTAINER__PROFILE_URI:
			unsetProfileURI();
			return;
		case ScaPackage.SCA_PROPERTY_CONTAINER__PROFILE_OBJ:
			unsetProfileObj();
			return;
		case ScaPackage.SCA_PROPERTY_CONTAINER__PROPERTIES:
			unsetProperties();
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
		case ScaPackage.SCA_PROPERTY_CONTAINER__PROFILE_URI:
			return isSetProfileURI();
		case ScaPackage.SCA_PROPERTY_CONTAINER__PROFILE_OBJ:
			return isSetProfileObj();
		case ScaPackage.SCA_PROPERTY_CONTAINER__ROOT_FILE_STORE:
			return ROOT_FILE_STORE_EDEFAULT == null ? getRootFileStore() != null : !ROOT_FILE_STORE_EDEFAULT.equals(getRootFileStore());
		case ScaPackage.SCA_PROPERTY_CONTAINER__PROPERTIES:
			return isSetProperties();
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
		if (baseClass == ProfileObjectWrapper.class) {
			switch (derivedFeatureID) {
			case ScaPackage.SCA_PROPERTY_CONTAINER__PROFILE_URI:
				return ScaPackage.PROFILE_OBJECT_WRAPPER__PROFILE_URI;
			case ScaPackage.SCA_PROPERTY_CONTAINER__PROFILE_OBJ:
				return ScaPackage.PROFILE_OBJECT_WRAPPER__PROFILE_OBJ;
			case ScaPackage.SCA_PROPERTY_CONTAINER__ROOT_FILE_STORE:
				return ScaPackage.PROFILE_OBJECT_WRAPPER__ROOT_FILE_STORE;
			default:
				return -1;
			}
		}
		if (baseClass == PropertySetOperations.class) {
			switch (derivedFeatureID) {
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
		if (baseClass == ProfileObjectWrapper.class) {
			switch (baseFeatureID) {
			case ScaPackage.PROFILE_OBJECT_WRAPPER__PROFILE_URI:
				return ScaPackage.SCA_PROPERTY_CONTAINER__PROFILE_URI;
			case ScaPackage.PROFILE_OBJECT_WRAPPER__PROFILE_OBJ:
				return ScaPackage.SCA_PROPERTY_CONTAINER__PROFILE_OBJ;
			case ScaPackage.PROFILE_OBJECT_WRAPPER__ROOT_FILE_STORE:
				return ScaPackage.SCA_PROPERTY_CONTAINER__ROOT_FILE_STORE;
			default:
				return -1;
			}
		}
		if (baseClass == PropertySetOperations.class) {
			switch (baseFeatureID) {
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
		result.append(')');
		return result.toString();
	}

	private VersionedFeature propertiesFeature = new VersionedFeature(this, ScaPackage.Literals.SCA_PROPERTY_CONTAINER__PROPERTIES);

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 14.0
	 *        <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public EList<ScaAbstractProperty< ? >> fetchProperties(IProgressMonitor monitor) {
		// END GENERATED CODE
		if (isDisposed()) {
			return ECollections.emptyEList();
		}
		final SubMonitor subMonitor = SubMonitor.convert(monitor, 4);
		Transaction transaction = propertiesFeature.createTransaction();
		IStatus fetchStatus = Status.OK_STATUS;
		final PropertiesHolder propHolder = new PropertiesHolder(EMPTY_DATA_TYPE_ARRAY);
		try {
			subMonitor.worked(1);
			List<AbstractProperty> defs = fetchPropertyDefinitions(subMonitor.newChild(1));
			if (!isSetProperties()) {
				transaction.append(new MergePropertiesCommand(this, defs));
			}
			query(propHolder);
			subMonitor.worked(1);
			transaction.append(new SetPropertiesValuesCommand(this, propHolder, defs));
		} catch (UnknownProperties e) {
			fetchStatus = new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to query property values.", e);
			transaction.append(new UnsetLocalAttributeCommand(this, fetchStatus, ScaPackage.Literals.SCA_PROPERTY_CONTAINER__PROPERTIES));
		} catch (SystemException e) {
			fetchStatus = new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to query property values.", e);
			transaction.append(new UnsetLocalAttributeCommand(this, fetchStatus, ScaPackage.Literals.SCA_PROPERTY_CONTAINER__PROPERTIES));
		} catch (Exception e) { // SUPPRESS CHECKSTYLE Logged Catch all exception
			fetchStatus = new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch properties.", e);
			transaction.append(new UnsetLocalAttributeCommand(this, fetchStatus, ScaPackage.Literals.SCA_PROPERTY_CONTAINER__PROPERTIES));
		}
		transaction.commit();
		subMonitor.worked(1);
		subMonitor.done();
		try {
			return ScaModelCommand.runExclusive(this, new RunnableWithResult.Impl<EList<ScaAbstractProperty< ? >>>() {

				@Override
				public void run() {
					setResult(ECollections.unmodifiableEList(new BasicEList<ScaAbstractProperty< ? >>(getProperties())));
				}

			});
		} catch (InterruptedException e) {
			return ECollections.emptyEList();
		}
		// BEGIN GENERATED CODE
	}

	@Override
	@Deprecated
	public void fetchPropertyValues(IProgressMonitor monitor) throws InterruptedException {
		fetchProperties(monitor);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public ScaAbstractProperty< ? > getProperty(String identifier) {
		// END GENERATED CODE
		for (ScaAbstractProperty< ? > prop : getProperties()) {
			if (prop.getId().equals(identifier)) {
				return prop;
			}
		}
		return null;
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 14.0
	 *        <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public abstract void configure(DataType[] configProperties) throws InvalidConfiguration, PartialConfiguration;

	/**
	 * @since 18.0
	 */
	protected abstract List<AbstractProperty> fetchPropertyDefinitions(IProgressMonitor monitor);

	@Override
	public IStatus getStatus() {
		// END GENERATED CODE
		if (!getProperties().isEmpty()) {
			IStatus superStatus = super.getStatus();
			MultiStatus propertiesStatus = new MultiStatus(ScaModelPlugin.ID, Status.OK, "Status of properties.", null);
			for (ScaAbstractProperty< ? > property : getProperties()) {
				IStatus propertyStatus = property.getStatus();
				if (propertyStatus != null && !propertiesStatus.isOK()) {
					propertiesStatus.add(propertyStatus);
				}
			}
			if (propertiesStatus.isOK()) {
				return superStatus;
			}
			MultiStatus status = new MultiStatus(ScaModelPlugin.ID, Status.OK, "Status of the SCA model object.", null);
			status.addAll(superStatus);
			status.add(propertiesStatus);
			if (!status.isOK()) {
				return status;
			}
			return Status.OK_STATUS;
		} else {
			return super.getStatus();
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 19.1
	 */
	@Override
	public String registerPropertyListener(org.omg.CORBA.Object obj, String[] prop_ids, float interval) throws UnknownProperties, InvalidObjectReference {
		// END GENERATED CODE
		Object propertySet = fetchNarrowedObject(null);
		if (propertySet == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		if (!(propertySet instanceof PropertySet)) {
			throw new IllegalStateException("Object does not support properties");
		}
		return ((PropertySet) propertySet).registerPropertyListener(obj, prop_ids, interval);
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 19.1
	 */
	@Override
	public void unregisterPropertyListener(String id) throws InvalidIdentifier {
		// END GENERATED CODE
		P propertySet = fetchNarrowedObject(null);
		if (propertySet == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		if (!(propertySet instanceof PropertySet)) {
			throw new IllegalStateException("Object does not support properties");
		}
		((PropertySet) propertySet).unregisterPropertyListener(id);
		// BEGIN GENERATED CODE
	}

} // ScaPropertyContainerImpl

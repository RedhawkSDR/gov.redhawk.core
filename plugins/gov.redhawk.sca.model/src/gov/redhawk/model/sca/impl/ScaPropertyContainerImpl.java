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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
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
import CF.PropertyEmitter;
import CF.PropertyEmitterOperations;
import CF.PropertySetOperations;
import CF.UnknownProperties;
import CF.PropertyEmitterPackage.AlreadyInitialized;
import CF.PropertySetPackage.InvalidConfiguration;
import CF.PropertySetPackage.PartialConfiguration;
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
import mil.jpeojtrs.sca.prf.AbstractProperty;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;
import mil.jpeojtrs.sca.util.collections.FeatureMapList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object ' <em><b>Property Container</b></em>'.
 * 
 * @since 12.0
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link gov.redhawk.model.sca.impl.ScaPropertyContainerImpl#getProfileURI <em>Profile URI</em>}</li>
 * <li>{@link gov.redhawk.model.sca.impl.ScaPropertyContainerImpl#getProfileObj <em>Profile Obj</em>}</li>
 * <li>{@link gov.redhawk.model.sca.impl.ScaPropertyContainerImpl#getProperties <em>Properties</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class ScaPropertyContainerImpl< P extends org.omg.CORBA.Object, E extends Object > extends CorbaObjWrapperImpl<P>
		implements ScaPropertyContainer<P, E> {
	/**
	 * The default value of the '{@link #getProfileURI() <em>Profile URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProfileURI()
	 * @generated
	 * @ordered
	 */
	protected static final URI PROFILE_URI_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getProfileURI() <em>Profile URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProfileURI()
	 * @generated
	 * @ordered
	 */
	protected URI profileURI = PROFILE_URI_EDEFAULT;
	/**
	 * This is true if the Profile URI attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean profileURIESet;
	/**
	 * The cached value of the '{@link #getProfileObj() <em>Profile Obj</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProfileObj()
	 * @generated
	 * @ordered
	 */
	protected E profileObj;
	/**
	 * This is true if the Profile Obj reference has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean profileObjESet;
	/**
	 * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<ScaAbstractProperty< ? >> properties;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScaPropertyContainerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScaPackage.Literals.SCA_PROPERTY_CONTAINER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public URI getProfileURI() {
		return profileURI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setProfileURI(URI newProfileURI) {
		URI oldProfileURI = profileURI;
		profileURI = newProfileURI;
		boolean oldProfileURIESet = profileURIESet;
		profileURIESet = true;
		if (eNotificationRequired())
			eNotify(
				new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_PROPERTY_CONTAINER__PROFILE_URI, oldProfileURI, profileURI, !oldProfileURIESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	 * @generated
	 */
	@Override
	public boolean isSetProfileURI() {
		return profileURIESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	 * @generated
	 */
	public E basicGetProfileObj() {
		return profileObj;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setProfileObj(E newProfileObj) {
		E oldProfileObj = profileObj;
		profileObj = newProfileObj;
		boolean oldProfileObjESet = profileObjESet;
		profileObjESet = true;
		if (eNotificationRequired())
			eNotify(
				new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_PROPERTY_CONTAINER__PROFILE_OBJ, oldProfileObj, profileObj, !oldProfileObjESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	 * <!-- end-user-doc -->
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
	 * This is an EMF-generated wrapper for {@link #registerPropertyListener(org.omg.CORBA.Object, String[], float)}.
	 * 
	 * @since 20.0
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String registerPropertyListener(org.omg.CORBA.Object obj, EList<String> prop_ids, float interval) throws UnknownProperties, InvalidObjectReference {
		return this.registerPropertyListener(obj, prop_ids.toArray(new String[prop_ids.size()]), interval);
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public E fetchProfileObject(IProgressMonitor monitor) {
		// END GENERATED CODE
		if (isDisposed()) {
			return null;
		}
		if (isSetProfileObj()) {
			return getProfileObj();
		}

		Transaction transaction = profileObjectFeature.createTransaction();
		Command command = ProfileObjectWrapper.Util.fetchProfileObject(monitor, this, getProfileObjectType(), "/");
		transaction.addCommand(command);
		transaction.commit();
		return getProfileObj();
		// BEGIN GENERATED CODE
	}

	// END GENERATED CODE

	private final VersionedFeature profileObjectFeature = new VersionedFeature(this, ScaPackage.Literals.PROFILE_OBJECT_WRAPPER__PROFILE_OBJ);

	/**
	 * The method is called by the default implementation of {@link #fetchProfileObject(IProgressMonitor)}.
	 * @return the {@link Class} object for the profile object's type.
	 * @since 23.0
	 */
	protected abstract Class<E> getProfileObjectType();

	// BEGIN GENERATED CODE

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public abstract URI fetchProfileURI(IProgressMonitor monitor);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
		case ScaPackage.SCA_PROPERTY_CONTAINER__PROPERTIES:
			return getProperties();
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
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case ScaPackage.SCA_PROPERTY_CONTAINER__PROFILE_URI:
			return isSetProfileURI();
		case ScaPackage.SCA_PROPERTY_CONTAINER__PROFILE_OBJ:
			return isSetProfileObj();
		case ScaPackage.SCA_PROPERTY_CONTAINER__PROPERTIES:
			return isSetProperties();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
		if (baseClass == PropertyEmitterOperations.class) {
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
		if (baseClass == PropertyEmitterOperations.class) {
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
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
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
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public EList<ScaAbstractProperty< ? >> fetchProperties(IProgressMonitor monitor) {
		// END GENERATED CODE
		if (isDisposed()) {
			return ECollections.emptyEList();
		}

		final SubMonitor subMonitor = SubMonitor.convert(monitor, 3);
		Transaction transaction = propertiesFeature.createTransaction();
		IStatus fetchStatus = Status.OK_STATUS;
		final PropertiesHolder propHolder = new PropertiesHolder(EMPTY_DATA_TYPE_ARRAY);
		try {
			List<AbstractProperty> defs = fetchPropertyDefinitions(subMonitor.split(1));
			if (!isSetProperties()) {
				transaction.append(new MergePropertiesCommand(this, defs));
			}

			if (subMonitor.isCanceled()) {
				throw new OperationCanceledException();
			}
			query(propHolder);
			transaction.append(new SetPropertiesValuesCommand(this, propHolder, defs));
			subMonitor.worked(1);
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

		subMonitor.setWorkRemaining(1);
		transaction.commit();
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
	 * @since 20.0
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public abstract void initializeProperties(DataType[] initialProperties) throws AlreadyInitialized, InvalidConfiguration, PartialConfiguration;

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public abstract void configure(DataType[] configProperties) throws InvalidConfiguration, PartialConfiguration;

	// END GENERATED CODE

	private VersionedObject<List<AbstractProperty>> propDefs = new VersionedObject<List<AbstractProperty>>(null, 0);

	/**
	 * @since 18.0
	 */
	protected List<AbstractProperty> fetchPropertyDefinitions(IProgressMonitor monitor) {
		if (isDisposed()) {
			return Collections.emptyList();
		}

		// Use cached property definitions if available
		VersionedObject<List<AbstractProperty>> cachedPropDefs = propDefs;
		int profileObjRevision = profileObjectFeature.getCurrentRevision();
		if (cachedPropDefs.stamp == profileObjRevision) {
			return cachedPropDefs.object;
		}

		// Fetch profile
		EObject tmpProfileObj = (EObject) fetchProfileObject(monitor);
		profileObjRevision = profileObjectFeature.getCurrentRevision();

		// Find the properties and cache them
		mil.jpeojtrs.sca.prf.Properties propDefintions = ScaEcoreUtils.getFeature(tmpProfileObj, getEmfPathToPropertyDefinitions());
		if (propDefintions != null) {
			List<AbstractProperty> retVal = new ArrayList<>(new FeatureMapList<>(propDefintions.getProperties(), AbstractProperty.class));
			propDefs = new VersionedObject<List<AbstractProperty>>(retVal, profileObjRevision);
			return retVal;
		} else {
			return Collections.emptyList();
		}
	}

	/**
	 * The method is called by the default implementation of {@link #fetchPropertyDefinitions(IProgressMonitor)}.
	 * @return The EMF path from the profile object to the {@link mil.jpeojtrs.sca.prf.Properties} object.
	 * @since 23.0
	 */
	protected abstract EStructuralFeature[] getEmfPathToPropertyDefinitions();

	// BEGIN GENERATED CODE

	@Override
	public IStatus getStatus() {
		// END GENERATED CODE
		if (getProperties().isEmpty()) {
			return super.getStatus();
		}

		IStatus superStatus = super.getStatus();
		MultiStatus propertiesStatus = new MultiStatus(ScaModelPlugin.ID, ScaModelPlugin.ERR_BAD_PROPS, "Status of properties", null);
		for (ScaAbstractProperty< ? > property : getProperties()) {
			IStatus propertyStatus = property.getStatus();
			if (propertyStatus != null && !propertyStatus.isOK()) {
				propertiesStatus.add(propertyStatus);
			}
		}

		// If there aren't problems with any properties, then return the normal status
		if (propertiesStatus.isOK()) {
			return superStatus;
		}

		// If the normal status was okay, we can return the property status which has problems
		if (superStatus.isOK()) {
			return propertiesStatus;
		}

		// Both have problems - combine into one status
		MultiStatus status;
		if (superStatus.isMultiStatus() && ScaModelPlugin.ERR_MULTIPLE_BAD_STATUS == superStatus.getCode()
			&& ScaModelPlugin.ID.equals(superStatus.getPlugin())) {
			status = (MultiStatus) superStatus;
		} else {
			status = new MultiStatus(ScaModelPlugin.ID, ScaModelPlugin.ERR_MULTIPLE_BAD_STATUS, "Multiple problems exist within this item.", null);
			status.add(superStatus);
		}
		status.add(propertiesStatus);
		return status;
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 20.0
	 */
	@Override
	public String registerPropertyListener(org.omg.CORBA.Object obj, String[] prop_ids, float interval) throws UnknownProperties, InvalidObjectReference {
		// END GENERATED CODE
		Object propertyEmitter = fetchNarrowedObject(null);
		if (propertyEmitter == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		if (!(propertyEmitter instanceof PropertyEmitter)) {
			throw new IllegalStateException("Object does not support properties");
		}
		return ((PropertyEmitter) propertyEmitter).registerPropertyListener(obj, prop_ids, interval);
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 20.0
	 */
	@Override
	public void unregisterPropertyListener(String id) throws InvalidIdentifier {
		// END GENERATED CODE
		P propertyEmitter = fetchNarrowedObject(null);
		if (propertyEmitter == null) {
			throw new IllegalStateException("CORBA Object is Null");
		}
		if (!(propertyEmitter instanceof PropertyEmitter)) {
			throw new IllegalStateException("Object does not support properties");
		}
		((PropertyEmitter) propertyEmitter).unregisterPropertyListener(id);
		// BEGIN GENERATED CODE
	}

} // ScaPropertyContainerImpl

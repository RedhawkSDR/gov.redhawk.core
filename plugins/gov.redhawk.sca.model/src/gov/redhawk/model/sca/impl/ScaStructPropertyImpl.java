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

import gov.redhawk.model.sca.IStatusProvider;
import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaSimpleSequenceProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.ScaStructSequenceProperty;
import gov.redhawk.sca.util.PluginUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mil.jpeojtrs.sca.prf.AbstractPropertyRef;
import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleRef;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.SimpleSequenceRef;
import mil.jpeojtrs.sca.prf.Struct;
import mil.jpeojtrs.sca.prf.StructRef;
import mil.jpeojtrs.sca.prf.StructValue;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.jacorb.JacorbUtil;
import org.omg.CORBA.Any;
import CF.DataType;
import CF.InvalidIdentifier;
import CF.InvalidObjectReference;
import CF.PropertiesHelper;
import CF.PropertiesHolder;
import CF.PropertySetOperations;
import CF.UnknownProperties;
import CF.PropertySetPackage.AlreadyInitialized;
import CF.PropertySetPackage.InvalidConfiguration;
import CF.PropertySetPackage.PartialConfiguration;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Struct Property</b></em>'.
 * 
 * @since 13.0
 *        <!-- end-user-doc -->
 *        <p>
 *        The following features are implemented:
 *        <ul>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaStructPropertyImpl#getFields <em>Fields</em>}</li>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaStructPropertyImpl#getSimples <em>Simples</em>}</li>
 *        </ul>
 *        </p>
 *
 * @generated
 */
public class ScaStructPropertyImpl extends ScaAbstractPropertyImpl<Struct> implements ScaStructProperty {
	/**
	 * The cached value of the '{@link #getFields() <em>Fields</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * @since 20.0
	 * <!-- end-user-doc -->
	 * 
	 * @see #getFields()
	 * @generated
	 * @ordered
	 */
	protected EList<ScaAbstractProperty< ? >> fields;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ScaStructPropertyImpl() {
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
		return ScaPackage.Literals.SCA_STRUCT_PROPERTY;
	}

	private EContentAdapter derrivedStatusListener = new EContentAdapter() {
		// END GENERATED CODE
		@Override
		public void notifyChanged(org.eclipse.emf.common.notify.Notification notification) {
			super.notifyChanged(notification);
			if (notification.getNotifier() == ScaStructPropertyImpl.this) {
				return;
			}
			switch (notification.getFeatureID(IStatusProvider.class)) {
			case ScaPackage.ISTATUS_PROVIDER__STATUS:
				if (eNotificationRequired()) {
					eNotify(new ENotificationImpl(ScaStructPropertyImpl.this, Notification.SET, ScaPackage.ISTATUS_PROVIDER__STATUS, null, getStatus()));
				}
				break;
			default:
				break;
			}
		}

		@Override
		protected void addAdapter(org.eclipse.emf.common.notify.Notifier notifier) {
			if (notifier instanceof EObject) {
				if (((EObject) notifier).eContainer() == ScaStructPropertyImpl.this) {
					super.addAdapter(notifier);
				}
			}
		}
		// BEGIN GENERATED CODE
	};
	{
		// END GENERATED CODE
		eAdapters().add(derrivedStatusListener);
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public EList<ScaSimpleProperty> getSimples() {
		List<ScaSimpleProperty> simples = new ArrayList<ScaSimpleProperty>();
		if (fields != null) {
			for (ScaAbstractProperty< ? > field : getFields()) {
				if (field instanceof ScaSimpleProperty) {
					simples.add((ScaSimpleProperty) field);
				}
			}
		}
		return ECollections.unmodifiableEList(simples);
	}

	/**
	 * @since 14.0
	 */
	@Override
	public void setDefinition(Struct newDefinition) {
		if (newDefinition != definition) {
			getFields().clear();
			for (FeatureMap.Entry entry : newDefinition.getFields()) {
				switch (entry.getEStructuralFeature().getFeatureID()) {
				case PrfPackage.STRUCT__SIMPLE:
					ScaSimpleProperty simple = ScaFactory.eINSTANCE.createScaSimpleProperty();
					simple.setDefinition((Simple) entry.getValue());
					fields.add(simple);
					break;
				case PrfPackage.STRUCT__SIMPLE_SEQUENCE:
					ScaSimpleSequenceProperty simpleSequence = ScaFactory.eINSTANCE.createScaSimpleSequenceProperty();
					simpleSequence.setDefinition((SimpleSequence) entry.getValue());
					fields.add(simpleSequence);
					break;
				}
			}
		}
		super.setDefinition(newDefinition);
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 20.0
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<ScaAbstractProperty< ? >> getFields() {
		if (fields == null) {
			fields = new EObjectContainmentEList.Resolving<ScaAbstractProperty< ? >>(ScaAbstractProperty.class, this, ScaPackage.SCA_STRUCT_PROPERTY__FIELDS);
		}
		return fields;
	}

	@Override
	protected void internalFromAny(Any newAny) {
		// END GENERATED CODE
		try {
			if (newAny != null) {
				final DataType[] fieldValues = PropertiesHelper.extract(newAny);
				if (fieldValues != null) {
					for (final DataType type : fieldValues) {
						ScaAbstractProperty< ? > prop = getField(type.id);
						if (prop != null) {
							prop.fromAny(type.value);
						} else {
							ScaSimpleProperty newProp = ScaFactory.eINSTANCE.createScaSimpleProperty();
							newProp.setId(type.id);
							newProp.fromAny(type.value);
							getFields().add(newProp);
						}
					}
					return;
				}
			} else {
				restoreDefaultValue();
			}
			setStatus(ScaPackage.Literals.SCA_STRUCT_PROPERTY__FIELDS, Status.OK_STATUS);
		} catch (Exception e) { // SUPPRESS CHECKSTYLE Logged Catch all exception
			setStatus(ScaPackage.Literals.SCA_STRUCT_PROPERTY__FIELDS,
				new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to read property value of:" + getName(), e));
		}
		// BEGIN GENERATED CODE
	}

	@Override
	public Any toAny() {
		// END GENERATED CODE
		Any retVal = JacorbUtil.init().create_any();
		List<DataType> fieldValues = new ArrayList<DataType>(getFields().size());
		for (ScaAbstractProperty< ? > field : getFields()) {
			fieldValues.add(new DataType(field.getId(), field.toAny()));
		}
		PropertiesHelper.insert(retVal, fieldValues.toArray(new DataType[fieldValues.size()]));
		return retVal;
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 13.0
	 *        <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public ScaSimpleProperty getSimple(String id) {
		// END GENERATED CODE
		for (ScaSimpleProperty prop : getSimples()) {
			if (PluginUtil.equals(prop.getId(), id)) {
				return prop;
			}
		}
		return null;
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 20.0
	 */
	@Override
	public void initializeProperties(final DataType[] configProperties) throws AlreadyInitialized, InvalidConfiguration, PartialConfiguration {
		throw new IllegalStateException("This construct method should never be called");
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 20.0
	 *        <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public ScaAbstractProperty< ? > getField(String id) {
		for (final ScaAbstractProperty< ? > field : getFields()) {
			if (PluginUtil.equals(field.getId(), id)) {
				return field;
			}
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 20.0
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public StructRef createPropertyRef() {
		final StructRef structRef = PrfFactory.eINSTANCE.createStructRef();
		structRef.setProperty(getDefinition());
		structRef.setRefID(getId());
		for (final ScaAbstractProperty< ? > field : getFields()) {
			if (!field.isDefaultValue()) {
				AbstractPropertyRef< ? > fieldRef = field.createPropertyRef();
				switch (fieldRef.eClass().getClassifierID()) {
				case PrfPackage.SIMPLE_REF:
					structRef.getSimpleRef().add((SimpleRef) fieldRef);
					break;
				case PrfPackage.SIMPLE_SEQUENCE_REF:
					structRef.getSimpleSequenceRef().add((SimpleSequenceRef) fieldRef);
					break;
				}
			}
		}
		return structRef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 20.0
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public StructValue createStructValue() {
		final StructValue structValue = PrfFactory.eINSTANCE.createStructValue();
		for (final ScaAbstractProperty< ? > field : getFields()) {
			AbstractPropertyRef< ? > fieldRef = field.createPropertyRef();
			switch (fieldRef.eClass().getClassifierID()) {
			case PrfPackage.SIMPLE_REF:
				structValue.getSimpleRef().add((SimpleRef) fieldRef);
				break;
			case PrfPackage.SIMPLE_SEQUENCE_REF:
				structValue.getSimpleSequenceRef().add((SimpleSequenceRef) fieldRef);
				break;
			}
		}
		return structValue;
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
	public void configure(DataType[] configProperties) throws InvalidConfiguration, PartialConfiguration {
		// END GENERATED CODE
		Map<String, Any> configMap = new HashMap<String, Any>();
		for (DataType type : configProperties) {
			configMap.put(type.id, type.value);
		}

		List<DataType> values = new ArrayList<DataType>();
		for (ScaAbstractProperty< ? > field : getFields()) {
			Any any = configMap.get(field.getId());
			if (any == null) {
				any = field.toAny();
			}
			values.add(new DataType(field.getId(), any));
		}
		Any any = JacorbUtil.init().create_any();
		PropertiesHelper.insert(any, values.toArray(new DataType[values.size()]));
		setRemoteValue(any);
		// BEGIN GENERATED CODE
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
	public void query(PropertiesHolder configProperties) throws UnknownProperties {
		// END GENERATED CODE
		EObject container = eContainer();
		if (container instanceof PropertySetOperations) {
			((PropertySetOperations) container).query(configProperties);
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * This is an EMF-generated wrapper for {@link #registerPropertyListener(org.omg.CORBA.Object, String[], float)}.
	 * @since 20.0
	 *        <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String registerPropertyListener(org.omg.CORBA.Object obj, EList<String> prop_ids, float interval) throws UnknownProperties, InvalidObjectReference {
		throw new UnsupportedOperationException();
	}

	/**
	 * @since 20.0
	 */
	@Override
	public String registerPropertyListener(org.omg.CORBA.Object obj, String[] prop_ids, float interval) throws UnknownProperties, InvalidObjectReference {
		throw new UnsupportedOperationException();
	}

	/**
	 * @since 20.0
	 */
	@Override
	public void unregisterPropertyListener(String id) throws InvalidIdentifier {
		throw new UnsupportedOperationException();
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
		case ScaPackage.SCA_STRUCT_PROPERTY__FIELDS:
			return ((InternalEList< ? >) getFields()).basicRemove(otherEnd, msgs);
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
		case ScaPackage.SCA_STRUCT_PROPERTY__FIELDS:
			return getFields();
		case ScaPackage.SCA_STRUCT_PROPERTY__SIMPLES:
			return getSimples();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ScaPackage.SCA_STRUCT_PROPERTY__FIELDS:
			setIgnoreRemoteSet(true);
			try {
				getFields().clear();
			} finally {
				setIgnoreRemoteSet(false);
			}
			getFields().addAll((Collection< ? extends ScaAbstractProperty< ? >>) newValue);
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
		case ScaPackage.SCA_STRUCT_PROPERTY__FIELDS:
			getFields().clear();
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
		case ScaPackage.SCA_STRUCT_PROPERTY__FIELDS:
			return fields != null && !fields.isEmpty();
		case ScaPackage.SCA_STRUCT_PROPERTY__SIMPLES:
			return !getSimples().isEmpty();
		}
		return super.eIsSet(featureID);
	}

	@Override
	public IStatus getStatus() {
		// END GENERATED CODE
		IStatus parentStatus = super.getStatus();
		if (!getFields().isEmpty()) {
			MultiStatus retVal = new MultiStatus(ScaModelPlugin.ID, Status.OK, "Struct property: " + getName(), null);
			retVal.addAll(super.getStatus());
			for (ScaAbstractProperty< ? > field : getFields()) {
				retVal.add(field.getStatus());
			}
			retVal.add(parentStatus);
			if (!retVal.isOK()) {
				return retVal;
			} else {
				return Status.OK_STATUS;
			}
		} else {
			return parentStatus;
		}
		// BEGIN GENERATED CODE
	}

	private int getIndex() {
		if (eContainer() instanceof ScaStructSequenceProperty) {
			ScaStructSequenceProperty parent = (ScaStructSequenceProperty) eContainer();
			int index = parent.getStructs().indexOf(this);
			return index;
		}
		return -1;
	}

	@Override
	public void setRemoteValue(Any any) throws PartialConfiguration, InvalidConfiguration {
		EObject container = eContainer();
		if (container instanceof ScaStructSequenceProperty) {
			((ScaStructSequenceProperty) container).configure(new DataType[] { new DataType(String.valueOf(getIndex()), any) });
		} else {
			super.setRemoteValue(any);
		}
	}

	@Override
	public boolean isDefaultValue() {
		for (ScaAbstractProperty< ? > field : getFields()) {
			if (!field.isDefaultValue()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void restoreDefaultValue() {
		for (ScaAbstractProperty< ? > field : getFields()) {
			field.restoreDefaultValue();
		}
	}

} // ScaStructPropertyImpl

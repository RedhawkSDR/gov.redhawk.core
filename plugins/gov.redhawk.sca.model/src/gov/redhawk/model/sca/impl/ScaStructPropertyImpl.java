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

import mil.jpeojtrs.sca.prf.AbstractProperty;
import mil.jpeojtrs.sca.prf.AbstractPropertyRef;
import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.SimpleRef;
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
import org.omg.CORBA.SystemException;

import CF.DataType;
import CF.InvalidObjectReference;
import CF.PropertiesHelper;
import CF.PropertiesHolder;
import CF.PropertySetOperations;
import CF.UnknownProperties;
import CF.PropertySetPackage.InvalidConfiguration;
import CF.PropertySetPackage.PartialConfiguration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object ' <em><b>Struct Property</b></em>'.
 * 
 * @since 13.0
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link gov.redhawk.model.sca.impl.ScaStructPropertyImpl#getFields <em>Fields</em>}</li>
 * <li>{@link gov.redhawk.model.sca.impl.ScaStructPropertyImpl#getSimples <em>Simples</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ScaStructPropertyImpl extends ScaAbstractPropertyImpl<Struct> implements ScaStructProperty {
	/**
	 * The cached value of the '{@link #getFields() <em>Fields</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 20.0
	 * <!-- end-user-doc -->
	 * @see #getFields()
	 * @generated
	 * @ordered
	 */
	protected EList<ScaAbstractProperty< ? >> fields;

	// END GENERATED CODE

	private EContentAdapter derrivedStatusListener = new EContentAdapter() {
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
	};

	{
		eAdapters().add(derrivedStatusListener);
	}

	// BEGIN GENERATED CODE

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScaStructPropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScaPackage.Literals.SCA_STRUCT_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public EList<ScaSimpleProperty> getSimples() {
		// END GENERATED CODE
		List<ScaSimpleProperty> simples = new ArrayList<ScaSimpleProperty>();
		if (fields != null) {
			for (ScaAbstractProperty< ? > field : getFields()) {
				if (field instanceof ScaSimpleProperty) {
					simples.add((ScaSimpleProperty) field);
				}
			}
		}
		return ECollections.unmodifiableEList(simples);
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 20.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ScaAbstractProperty< ? >> getFields() {
		if (fields == null) {
			fields = new EObjectContainmentEList.Resolving<ScaAbstractProperty< ? >>(ScaAbstractProperty.class, this, ScaPackage.SCA_STRUCT_PROPERTY__FIELDS);
		}
		return fields;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 13.0
	 * <!-- end-user-doc -->
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
	 * <!-- begin-user-doc -->
	 * 
	 * @since 20.0
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public ScaAbstractProperty< ? > getField(String id) {
		// END GENERATED CODE
		for (final ScaAbstractProperty< ? > field : getFields()) {
			if (PluginUtil.equals(field.getId(), id)) {
				return field;
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
	@Override
	public StructRef createPropertyRef() {
		// END GENERATED CODE
		final StructRef structRef = PrfFactory.eINSTANCE.createStructRef();
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
				default:
					String msg = String.format("Invalid struct field '%s' of type %s", fieldRef.getRefID(), fieldRef.getClass().getName());
					throw new IllegalArgumentException(msg);
				}
			}
		}
		return structRef;
		// BEGIN GENERATED CODE
	}

	@Override
	public void setValueFromRef(AbstractPropertyRef< ? > refValue) {
		if (!(refValue instanceof StructRef)) {
			String msg = String.format("Property ref of type '%s' does not match type of property '%s'", refValue.getClass().getSimpleName(), getId());
			setStatus(ScaPackage.Literals.SCA_STRUCT_PROPERTY__FIELDS, new Status(Status.ERROR, ScaModelPlugin.ID, msg));
			return;
		}
		setValueFromRef((StructRef) refValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 21.0
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setValueFromRef(StructRef refValue) {
		// END GENERATED CODE
		List<String> missingFields = setSimpleValuesFromRefs(refValue.getSimpleRef());
		missingFields.addAll(setSimpleSeqValuesFromRefs(refValue.getSimpleSequenceRef()));
		if (missingFields.size() > 0) {
			String msg = String.format("Invalid struct field(s) in property reference(s): %s", missingFields.toString());
			setStatus(ScaPackage.Literals.SCA_STRUCT_PROPERTY__FIELDS, new Status(Status.ERROR, ScaModelPlugin.ID, msg));
		} else {
			setStatus(ScaPackage.Literals.SCA_STRUCT_PROPERTY__FIELDS, Status.OK_STATUS);
		}
		// BEGIN GENERATED CODE
	}

	// END GENERATED CODE

	/**
	 * Set the value of this struct from a structvalue. Used by {@link ScaStructSequenceProperty}.
	 * 
	 * @since 21.0
	 */
	protected void setValueFromRef(StructValue refValue) {
		// END GENERATED CODE
		List<String> missingFields = setSimpleValuesFromRefs(refValue.getSimpleRef());
		missingFields.addAll(setSimpleSeqValuesFromRefs(refValue.getSimpleSequenceRef()));
		if (missingFields.size() > 0) {
			String msg = String.format("Invalid struct field(s) in property reference(s): %s", missingFields.toString());
			setStatus(ScaPackage.Literals.SCA_STRUCT_PROPERTY__FIELDS, new Status(Status.ERROR, ScaModelPlugin.ID, msg));
		} else {
			setStatus(ScaPackage.Literals.SCA_STRUCT_PROPERTY__FIELDS, Status.OK_STATUS);
		}
		// BEGIN GENERATED CODE
	}

	private List<String> setSimpleValuesFromRefs(List<SimpleRef> refValues) {
		List<String> missingFields = new ArrayList<>();
		for (SimpleRef simpleRef : refValues) {
			ScaSimpleProperty simple = (ScaSimpleProperty) getField(simpleRef.getRefID());
			if (simple != null) {
				simple.setValueFromRef(simpleRef);
			} else {
				missingFields.add(simpleRef.getRefID());
			}
		}
		return missingFields;
	}

	private List<String> setSimpleSeqValuesFromRefs(List<SimpleSequenceRef> refValues) {
		List<String> missingFields = new ArrayList<>();
		for (SimpleSequenceRef simpleSeqRef : refValues) {
			ScaSimpleSequenceProperty simpleSeq = (ScaSimpleSequenceProperty) getField(simpleSeqRef.getRefID());
			if (simpleSeq != null) {
				simpleSeq.setValueFromRef(simpleSeqRef);
			} else {
				missingFields.add(simpleSeqRef.getRefID());
			}
		}
		return missingFields;
	}

	// BEGIN GENERATED CODE

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 20.0
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public StructValue createStructValue() {
		// END GENERATED CODE
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
			default:
				String msg = String.format("Invalid struct field '%s' of type %s", fieldRef.getRefID(), fieldRef.getClass().getName());
				throw new IllegalArgumentException(msg);
			}
		}
		return structValue;
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 14.0
	 * <!-- end-user-doc -->
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
	 * <!-- end-user-doc -->
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
	 * 
	 * @since 20.0
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String registerPropertyListener(org.omg.CORBA.Object obj, EList<String> prop_ids, float interval) throws UnknownProperties, InvalidObjectReference {
		// END GENERATED CODE
		throw new UnsupportedOperationException();
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
		// END GENERATED CODE
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
		default:
			break;
		}
		super.eSet(featureID, newValue);
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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

	// END GENERATED CODE

	/**
	 * @since 14.0
	 */
	@Override
	public void setDefinition(Struct newDefinition) {
		// Save the old definition so that we can test that it changed, but call the superclass version first to allow
		// it to do any validity checking first
		Struct oldDefinition = getDefinition();
		super.setDefinition(newDefinition);
		if (newDefinition != oldDefinition) {
			getFields().clear();
			if (newDefinition != null) {
				for (FeatureMap.Entry entry : newDefinition.getFields()) {
					ScaAbstractProperty< ? extends AbstractProperty> field = ScaFactory.eINSTANCE.createScaProperty((AbstractProperty) entry.getValue());
					fields.add(field);
				}
			}
		}
	}

	@Override
	public Any toAny() {
		Any retVal = JacorbUtil.init().create_any();
		List<DataType> fieldValues = new ArrayList<DataType>(getFields().size());
		for (ScaAbstractProperty< ? > field : getFields()) {
			fieldValues.add(new DataType(field.getId(), field.toAny()));
		}
		PropertiesHelper.insert(retVal, fieldValues.toArray(new DataType[fieldValues.size()]));
		return retVal;
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

	@Override
	protected void internalFromAny(Any newAny) {
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
		} catch (SystemException e) {
			String msg = String.format("Failed to demarshal value of property '%s'", getId());
			setStatus(ScaPackage.Literals.SCA_STRUCT_PROPERTY__FIELDS, new Status(Status.ERROR, ScaModelPlugin.ID, msg, e));
		}
	}

	@Override
	public IStatus getStatus() {
		IStatus superStatus = super.getStatus();
		String msg = String.format("Struct property: %s", getId());
		MultiStatus structStatus = new MultiStatus(ScaModelPlugin.ID, 0, msg, null);
		for (ScaAbstractProperty< ? > field : getFields()) {
			IStatus fieldStatus = field.getStatus();
			if (!fieldStatus.isOK()) {
				structStatus.add(fieldStatus);
			}
		}

		// If there aren't problems with the struct, then return the normal status
		if (structStatus.isOK()) {
			return superStatus;
		}

		// If the normal status was okay, we can return the struct status problem(s)
		if (superStatus.isOK()) {
			return structStatus;
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
		status.add(structStatus);
		return status;
	}

	private int getIndex() {
		if (eContainer() instanceof ScaStructSequenceProperty) {
			ScaStructSequenceProperty parent = (ScaStructSequenceProperty) eContainer();
			int index = parent.getStructs().indexOf(this);
			return index;
		}
		return -1;
	}

	// BEGIN GENERATED CODE

} // ScaStructPropertyImpl

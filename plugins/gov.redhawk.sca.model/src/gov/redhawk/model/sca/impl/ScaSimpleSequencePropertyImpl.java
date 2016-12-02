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

import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaSimpleSequenceProperty;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.SimpleSequenceRef;
import mil.jpeojtrs.sca.util.AnyUtils;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.omg.CORBA.Any;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Simple Sequence Property</b></em>'.
 * 
 * @since 12.0
 *        <!-- end-user-doc -->
 *        <p>
 *        The following features are implemented:
 *        </p>
 *        <ul>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaSimpleSequencePropertyImpl#getValues <em>Values</em>}</li>
 *        </ul>
 *
 * @generated
 */
public class ScaSimpleSequencePropertyImpl extends ScaAbstractPropertyImpl<SimpleSequence> implements ScaSimpleSequenceProperty {
	/**
	 * The cached value of the '{@link #getValues() <em>Values</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 13.0
	 *        <!-- end-user-doc -->
	 * @see #getValues()
	 * @generated NOT
	 * @ordered
	 */
	protected final ObjectList values = new ObjectList(this);

	// END GENERATED CODE

	private Adapter valueListener = new AdapterImpl() {

		@Override
		public void notifyChanged(Notification msg) {
			if (!isIgnoreRemoteSet()) {
				switch (msg.getFeatureID(ScaSimpleSequenceProperty.class)) {
				case ScaPackage.SCA_SIMPLE_SEQUENCE_PROPERTY__VALUES:
					pushValueJob.setNewRemoteValue(toAny());
					pushValueJob.schedule();
					break;
				default:
					break;
				}
			}
		}

	};

	{
		eAdapters().add(valueListener);
	}

	/**
	 * @since 14.0
	 */
	protected static class ObjectList extends EDataTypeEList<Object> {
		private static final long serialVersionUID = 1L;

		public ObjectList(ScaSimpleSequencePropertyImpl property) {
			super(Object.class, property, ScaPackage.SCA_SIMPLE_SEQUENCE_PROPERTY__VALUES);
		}

		private ScaSimpleSequencePropertyImpl getOwner() {
			return (ScaSimpleSequencePropertyImpl) owner;
		}

		private static Object[] getDefaultValues(SimpleSequence definition) {
			if (definition == null) {
				return new Object[0];
			}
			List<Object> array = new ArrayList<Object>();
			if (definition != null && definition.getValues() != null) {
				for (String value : definition.getValues().getValue()) {
					array.add(AnyUtils.convertString(value, definition.getType().getLiteral(), definition.isComplex()));
				}
			}
			return array.toArray();
		}

		@Override
		protected boolean isInstance(Object object) {
			if (getOwner().getDefinition() != null) {
				EDataType dataType = getOwner().getDefinition().getType().toEDataType(getOwner().getDefinition().isComplex());
				return dataType.isInstance(object);
			} else {
				return super.isInstance(object);
			}
		}

		/**
		 * @since 14.0
		 */
		public boolean isDefaultValue() {
			return Arrays.equals(toArray(), getDefaultValues(getOwner().getDefinition()));
		}

		/**
		 * @since 14.0
		 */
		public void restoreDefaultValue() {
			Object[] defaultValues = getDefaultValues(getOwner().getDefinition());
			clear();
			if (defaultValues.length > 0) {
				addAll(Arrays.asList(defaultValues));
			}
		}
	}

	// BEGIN GENERATED CODE

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ScaSimpleSequencePropertyImpl() {
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
		return ScaPackage.Literals.SCA_SIMPLE_SEQUENCE_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 14.0
	 *        <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public ObjectList getValues() {
		// END GENERATED CODE
		return values;
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
	public Object[] getValue() {
		// END GENERATED CODE
		Class< ? > type = Object.class;
		if (getDefinition() != null) {
			EDataType dataType = getDefinition().getType().toEDataType(getDefinition().isComplex());
			type = dataType.getInstanceClass();
		}
		return getValues().toArray((Object[]) Array.newInstance(type, getValues().size()));
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 20.0
	 *        <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public SimpleSequenceRef createPropertyRef() {
		// END GENERATED CODE
		final SimpleSequenceRef simpleSequenceRef = PrfFactory.eINSTANCE.createSimpleSequenceRef();
		simpleSequenceRef.setRefID(getId());
		simpleSequenceRef.setValues(PrfFactory.eINSTANCE.createValues());
		EList<String> value = simpleSequenceRef.getValues().getValue();
		for (final Object object : getValues()) {
			value.add(object.toString());
		}
		return simpleSequenceRef;
		// BEGIN GENERATED CODE
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
		case ScaPackage.SCA_SIMPLE_SEQUENCE_PROPERTY__VALUES:
			return getValues();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 13.0
	 *        <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void setValue(Object[] newValue) {
		// END GENERATED CODE
		if (Arrays.equals(getValues().toArray(), newValue)) {
			return;
		}
		if (newValue != null && newValue.length > 0) {
			setIgnoreRemoteSet(true);
			try {
				getValues().clear();
			} finally {
				setIgnoreRemoteSet(false);
			}
			getValues().addAll(Arrays.asList(newValue));
		} else {
			getValues().clear();
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		// END GENERATED CODE
		switch (featureID) {
		case ScaPackage.SCA_SIMPLE_SEQUENCE_PROPERTY__VALUES:
			setValue(((Collection< ? extends Object>) newValue).toArray());
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
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case ScaPackage.SCA_SIMPLE_SEQUENCE_PROPERTY__VALUES:
			getValues().clear();
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
		case ScaPackage.SCA_SIMPLE_SEQUENCE_PROPERTY__VALUES:
			return values != null && !values.isEmpty();
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
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (values: ");
		result.append(values);
		result.append(')');
		return result.toString();
	}

	// END GENERATED CODE

	/**
	 * @since 14.0
	 */
	@Override
	public void setDefinition(SimpleSequence newDefinition) {
		super.setDefinition(newDefinition);
	}

	@Override
	public Any toAny() {
		return AnyUtils.toAnySequence(getValues().toArray(), getType(), isComplex());
	}

	@Override
	public boolean isDefaultValue() {
		return getValues().isDefaultValue();
	}

	@Override
	public void restoreDefaultValue() {
		getValues().restoreDefaultValue();
	}

	@Override
	protected void internalFromAny(Any newAny) {
		try {
			if (newAny != null) {
				final Object value = AnyUtils.convertAny(newAny);
				if (value != null && value.getClass().isArray()) {
					setValue((Object[]) value);
				}
			} else {
				getValues().restoreDefaultValue();
			}
			setStatus(ScaPackage.Literals.SCA_SIMPLE_SEQUENCE_PROPERTY__VALUES, Status.OK_STATUS);
		} catch (Exception e) {
			setStatus(ScaPackage.Literals.SCA_SIMPLE_SEQUENCE_PROPERTY__VALUES,
				new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to read property value of:" + getName(), e));
		}
	}

	/**
	 * @since 18.0
	 */
	protected boolean isComplex() {
		if (getDefinition() != null) {
			return getDefinition().isComplex();
		}
		return false;
	}

	private String getType() {
		if (getDefinition() != null && getDefinition().getType() != null) {
			return getDefinition().getType().getLiteral();
		}
		return null;
	}

	// BEGIN GENERATED CODE

} // ScaSimpleSequencePropertyImpl

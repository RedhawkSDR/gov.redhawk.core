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

import mil.jpeojtrs.sca.prf.AbstractPropertyRef;
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
import org.eclipse.emf.ecore.util.InternalEList;
import org.omg.CORBA.Any;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object ' <em><b>Simple Sequence Property</b></em>'.
 * 
 * @since 12.0
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link gov.redhawk.model.sca.impl.ScaSimpleSequencePropertyImpl#getValues <em>Values</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ScaSimpleSequencePropertyImpl extends ScaAbstractPropertyImpl<SimpleSequence> implements ScaSimpleSequenceProperty {
	/**
	 * The cached value of the '{@link #getValues() <em>Values</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 13.0
	 * <!-- end-user-doc -->
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
	protected static class ObjectList extends EDataTypeEList.Unsettable<Object> {
		private static final long serialVersionUID = 1L;

		public ObjectList(ScaSimpleSequencePropertyImpl property) {
			super(Object.class, property, ScaPackage.SCA_SIMPLE_SEQUENCE_PROPERTY__VALUES);
		}

		private ScaSimpleSequencePropertyImpl getOwner() {
			return (ScaSimpleSequencePropertyImpl) owner;
		}

		private static List<Object> getDefaultValues(SimpleSequence definition) {
			if (definition == null || definition.getType() == null || definition.getValues() == null) {
				return null;
			}
			List<Object> values = new ArrayList<Object>();
			if (definition != null && definition.getValues() != null) {
				for (String value : definition.getValues().getValue()) {
					values.add(AnyUtils.convertString(value, definition.getType().getLiteral(), definition.isComplex()));
				}
			}
			return values;
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
			List<Object> defaultValues = getDefaultValues(getOwner().getDefinition());
			if (defaultValues == null) {
				// The definition doesn't provide default values for the property
				// As long as no values have been added, we'll consider that equivalent
				return size() == 0;
			}
			return equals(defaultValues);
		}

		/**
		 * @since 14.0
		 */
		public void restoreDefaultValue() {
			List<Object> defaultValues = getDefaultValues(getOwner().getDefinition());
			if (defaultValues == null) {
				// No default values provided in the definition
				unset();
			} else {
				clear();
				if (defaultValues.size() > 0) {
					addAll(defaultValues);
				}
			}
		}
	}

	// BEGIN GENERATED CODE

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScaSimpleSequencePropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	 * <!-- end-user-doc -->
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
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetValues() {
		if (values != null)
			((InternalEList.Unsettable< ? >) values).unset();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetValues() {
		return values != null && ((InternalEList.Unsettable< ? >) values).isSet();
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 13.0
	 * <!-- end-user-doc -->
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
	 * <!-- end-user-doc -->
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

	@Override
	public void setValueFromRef(AbstractPropertyRef< ? > refValue) {
		if (!(refValue instanceof SimpleSequenceRef)) {
			String msg = String.format("Property ref of type '%s' does not match type of property '%s'", refValue.getClass().getSimpleName(), getId());
			setStatus(ScaPackage.Literals.SCA_SIMPLE_SEQUENCE_PROPERTY__VALUES, new Status(Status.ERROR, ScaModelPlugin.ID, msg));
			return;
		}
		setValueFromRef((SimpleSequenceRef) refValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 22.0
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setValueFromRef(SimpleSequenceRef refValue) {
		// END GENERATED CODE
		try {
			final Object[] newValues = AnyUtils.convertStringArray(refValue.getValues().getValue().toArray(), getType(), isComplex());
			setValue(newValues);
			setStatus(ScaPackage.Literals.SCA_SIMPLE_SEQUENCE_PROPERTY__VALUES, Status.OK_STATUS);
		} catch (ClassCastException | IllegalArgumentException e) {
			String msg = String.format("Failed to demarshal value of property '%s' from simplesequenceref", getId());
			setStatus(ScaPackage.Literals.SCA_SIMPLE_SEQUENCE_PROPERTY__VALUES, new Status(Status.ERROR, ScaModelPlugin.ID, msg, e));
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void setValue(Object[] newValue) {
		// END GENERATED CODE
		// Ignore the change if the value has been set previously (i.e. initialized), and this value is the same
		if (isSetValues() && Arrays.equals(getValues().toArray(), newValue)) {
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
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case ScaPackage.SCA_SIMPLE_SEQUENCE_PROPERTY__VALUES:
			unsetValues();
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
		case ScaPackage.SCA_SIMPLE_SEQUENCE_PROPERTY__VALUES:
			return isSetValues();
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
		if (!isSetValues()) {
			// Can't return an Any if unset - this implies there has been no initializing, not even to "zero values"
			return null;
		}
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
				setValue((Object[]) value);
			} else {
				getValues().restoreDefaultValue();
			}
			setStatus(ScaPackage.Literals.SCA_SIMPLE_SEQUENCE_PROPERTY__VALUES, Status.OK_STATUS);
		} catch (IllegalArgumentException e) {
			String msg = String.format("Failed to demarshal value of property '%s'", getId());
			setStatus(ScaPackage.Literals.SCA_SIMPLE_SEQUENCE_PROPERTY__VALUES, new Status(Status.ERROR, ScaModelPlugin.ID, msg, e));
		} catch (ClassCastException e) {
			String type = (newAny == null) ? "(null)" : newAny.type().toString();
			String msg = String.format("Received a value of incorrect type for simple sequence property '%s':\n%s", getId(), type);
			setStatus(ScaPackage.Literals.SCA_SIMPLE_SEQUENCE_PROPERTY__VALUES, new Status(Status.ERROR, ScaModelPlugin.ID, msg, e));
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

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
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.sca.util.PluginUtil;
import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.PropertyValueType;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleRef;
import mil.jpeojtrs.sca.util.AnyUtils;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.omg.CORBA.Any;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Simple Property</b></em>'.
 * 
 * @since 12.0
 *        <!-- end-user-doc -->
 *        <p>
 *        The following features are implemented:
 *        </p>
 *        <ul>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaSimplePropertyImpl#getValue <em>Value</em>}</li>
 *        </ul>
 *
 * @generated
 */
public class ScaSimplePropertyImpl extends ScaAbstractPropertyImpl<Simple>implements ScaSimpleProperty {
	/**
	 * The default value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected static final Object VALUE_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected Object value = VALUE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ScaSimplePropertyImpl() {
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
		return ScaPackage.Literals.SCA_SIMPLE_PROPERTY;
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
	public void setDefinition(Simple newDefinition) {
		super.setDefinition(newDefinition);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getValue() {
		return value;
	}

	private Adapter valueListener = new AdapterImpl() {
		// END GENERATED CODE

		@Override
		public void notifyChanged(Notification msg) {
			if (!isIgnoreRemoteSet()) {
				switch (msg.getFeatureID(ScaSimpleProperty.class)) {
				case ScaPackage.SCA_SIMPLE_PROPERTY__VALUE:
					if (!PluginUtil.equals(msg.getOldValue(), msg.getNewValue())) {
						pushValueJob.newRemoteValue = toAny();
						pushValueJob.schedule();
					}
					break;
				default:
					break;
				}
			}
		}

		// BEGIN GENERATED CODE
	};

	{
		eAdapters().add(valueListener);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 13.0
	 *        <!-- end-user-doc -->
	 * @generated
	 */
	public void setValueGen(Object newValue) {
		Object oldValue = value;
		value = newValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_SIMPLE_PROPERTY__VALUE, oldValue, value));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void setValue(Object newValue) {
		if (getDefinition() != null && getDefinition().getType() != null) {
			PropertyValueType propType = getDefinition().getType();
			EDataType eDataType = propType.toEDataType(getDefinition().isComplex());
			Class< ? > typeClass = eDataType.getInstanceClass();
			typeClass.cast(newValue);
		}
		if (PluginUtil.equals(value, newValue)) {
			return;
		}
		setValueGen(newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 20.0
	 *        <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public SimpleRef createPropertyRef() {
		final SimpleRef simpleRef = PrfFactory.eINSTANCE.createSimpleRef();
		simpleRef.setRefID(getId());
		final Object value = getValue();
		final String stringValue = (value == null) ? null : value.toString();
		simpleRef.setValue(stringValue);
		return simpleRef;
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
		case ScaPackage.SCA_SIMPLE_PROPERTY__VALUE:
			return getValue();
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
		case ScaPackage.SCA_SIMPLE_PROPERTY__VALUE:
			setValue(newValue);
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
		case ScaPackage.SCA_SIMPLE_PROPERTY__VALUE:
			setValue(VALUE_EDEFAULT);
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
		case ScaPackage.SCA_SIMPLE_PROPERTY__VALUE:
			return VALUE_EDEFAULT == null ? value != null : !VALUE_EDEFAULT.equals(value);
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
		result.append(" (value: ");
		result.append(value);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void restoreDefaultValue() {
		// END GENERATED CODE
		// value = VALUE_EDEFAULT;
		Object newValue;
		if (getDefinition() != null && getDefinition().getType() != null) {
			newValue = AnyUtils.convertString(getDefinition().getValue(), getDefinition().getType().getLiteral(), getDefinition().isComplex());
		} else {
			newValue = null;
		}
		setValue(newValue);
		// BEGIN GENERATED CODE
	}

	@Override
	public boolean isDefaultValue() {
		// END GENERATED CODE
		Object newValue;
		if (getDefinition() != null && getDefinition().getType() != null) {
			newValue = AnyUtils.convertString(getDefinition().getValue(), getDefinition().getType().getLiteral(), getDefinition().isComplex());
		} else {
			newValue = null;
		}
		return PluginUtil.equals(newValue, value);
		// BEGIN GENERATED CODE
	}

	@Override
	protected void internalFromAny(Any newAny) {
		// END GENERATED CODE
		try {
			if (newAny != null) {
				Object newValue = AnyUtils.convertAny(newAny);
				setValue(newValue);
			} else {
				restoreDefaultValue();
			}
			setStatus(ScaPackage.Literals.SCA_SIMPLE_PROPERTY__VALUE, Status.OK_STATUS);
		} catch (IllegalArgumentException e) {
			setStatus(ScaPackage.Literals.SCA_SIMPLE_PROPERTY__VALUE,
				new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to read property value of:" + getName(), e));
		}
		// BEGIN GENERATED CODE
	}

	@Override
	public Any toAny() {
		// END GENERATED CODE
		return AnyUtils.toAny(value, getType(), isComplex());
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 18.0
	 */
	protected boolean isComplex() {
		// END GENERATED CODE
		if (getDefinition() != null && getDefinition().getComplex() != null) {
			return getDefinition().getComplex();
		}
		return false;
		// BEGIN GENERATED CODE
	}

	private String getType() {
		// END GENERATED CODE
		if (getDefinition() != null && getDefinition().getType() != null) {
			return getDefinition().getType().getLiteral();
		}
		return null;
		// BEGIN GENERATED CODE
	}

} // ScaSimplePropertyImpl

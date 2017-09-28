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
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.ScaStructSequenceProperty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mil.jpeojtrs.sca.prf.AbstractPropertyRef;
import mil.jpeojtrs.sca.prf.ConfigurationKind;
import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleRef;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.SimpleSequenceRef;
import mil.jpeojtrs.sca.prf.Struct;
import mil.jpeojtrs.sca.prf.StructSequence;
import mil.jpeojtrs.sca.prf.StructSequenceRef;
import mil.jpeojtrs.sca.prf.StructValue;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.jacorb.JacorbUtil;
import org.omg.CORBA.Any;
import org.omg.CORBA.AnySeqHelper;
import org.omg.CORBA.SystemException;
import org.omg.CORBA.TCKind;
import CF.DataType;
import CF.InvalidObjectReference;
import CF.PropertiesHolder;
import CF.PropertySetOperations;
import CF.UnknownProperties;
import CF.PropertySetPackage.InvalidConfiguration;
import CF.PropertySetPackage.PartialConfiguration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object ' <em><b>Struct Sequence Property</b></em>'.
 * 
 * @since 12.0
 *        <!-- end-user-doc -->
 *        <p>
 *        The following features are implemented:
 *        </p>
 *        <ul>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaStructSequencePropertyImpl#getStructs <em>Structs</em>}</li>
 *        </ul>
 *
 * @generated
 */
public class ScaStructSequencePropertyImpl extends ScaAbstractPropertyImpl<StructSequence> implements ScaStructSequenceProperty {
	/**
	 * The cached value of the '{@link #getStructs() <em>Structs</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getStructs()
	 * @generated NOT
	 * @ordered
	 */
	protected final StructList structs = new StructList(this);

	// END GENERATED CODE

	private EContentAdapter derrivedStatusListener = new EContentAdapter() {

		@Override
		public void notifyChanged(org.eclipse.emf.common.notify.Notification notification) {
			super.notifyChanged(notification);
			if (notification.getNotifier() == ScaStructSequencePropertyImpl.this) {
				return;
			}
			switch (notification.getFeatureID(IStatusProvider.class)) {
			case ScaPackage.ISTATUS_PROVIDER__STATUS:
				if (eNotificationRequired()) {
					eNotify(
						new ENotificationImpl(ScaStructSequencePropertyImpl.this, Notification.SET, ScaPackage.ISTATUS_PROVIDER__STATUS, null, getStatus()));
				}
				break;
			default:
				break;
			}
		}

		@Override
		protected void addAdapter(org.eclipse.emf.common.notify.Notifier notifier) {
			if (notifier instanceof EObject) {
				if (((EObject) notifier).eContainer() == ScaStructSequencePropertyImpl.this) {
					super.addAdapter(notifier);
				}
			}
		}
	};

	private Adapter pushAdapter = new AdapterImpl() {

		@Override
		public void notifyChanged(Notification msg) {
			if (!isIgnoreRemoteSet()) {
				switch (msg.getFeatureID(ScaStructSequenceProperty.class)) {
				case ScaPackage.SCA_STRUCT_SEQUENCE_PROPERTY__STRUCTS:
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
		eAdapters().add(derrivedStatusListener);
		eAdapters().add(pushAdapter);
	}

	/**
	 * @since 14.0
	 */
	protected class StructList extends EObjectContainmentEList<ScaStructProperty> {
		private static final long serialVersionUID = 1L;

		public StructList(ScaStructSequencePropertyImpl owner) {
			super(ScaStructProperty.class, owner, ScaPackage.SCA_STRUCT_SEQUENCE_PROPERTY__STRUCTS);
		}

		public boolean isDefaultValue() {
			if (getDefinition() != null && getDefinition().getStructValue().size() != size()) {
				return false;
			}
			for (ScaStructProperty struct : this) {
				if (!struct.isDefaultValue()) {
					return false;
				}
			}
			return true;
		}

		public void restoreDefaultValue() {
			List<ScaStructProperty> props = new ArrayList<ScaStructProperty>();
			if (definition != null) {
				List<StructValue> structValues = definition.getStructValue();
				for (StructValue structVal : structValues) {
					ScaStructProperty prop = createStructValue(definition, structVal);
					props.add(prop);
				}
			}

			clear();
			addAll(props);
		}
	}

	// BEGIN GENERATED CODE

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ScaStructSequencePropertyImpl() {
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
		return ScaPackage.Literals.SCA_STRUCT_SEQUENCE_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 14.0
	 *        <!-- end-user-doc -->
	 *        This is specialized for the more specific type known in this context.
	 * @generated
	 */
	@Override
	public void setDefinition(StructSequence newDefinition) {
		super.setDefinition(newDefinition);
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
	public StructList getStructs() {
		// END GENERATED CODE
		return structs;
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
	public ScaStructProperty createScaStructProperty() {
		// END GENERATED CODE
		return createStructValue(getDefinition(), null);
		// BEGIN GENERATED CODE
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
	public StructSequenceRef createPropertyRef() {
		// END GENERATED CODE
		final StructSequenceRef structSequenceRef = PrfFactory.eINSTANCE.createStructSequenceRef();
		structSequenceRef.setRefID(getId());
		for (final ScaStructProperty struct : getStructs()) {
			final StructValue value = struct.createStructValue();
			structSequenceRef.getStructValue().add(value);
		}
		return structSequenceRef;
		// BEGIN GENERATED CODE
	}

	@Override
	public void setValueFromRef(AbstractPropertyRef< ? > refValue) {
		if (!(refValue instanceof StructSequenceRef)) {
			String msg = String.format("Property ref of type '%s' does not match type of property '%s'", refValue.getClass().getSimpleName(), getName());
			setStatus(ScaPackage.Literals.SCA_STRUCT_SEQUENCE_PROPERTY__STRUCTS, new Status(Status.ERROR, ScaModelPlugin.ID, msg));
			return;
		}
		setValueFromRef((StructSequenceRef) refValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 21.0
	 *        <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setValueFromRef(StructSequenceRef refValue) {
		// END GENERATED CODE
		for (int i = 0; i < refValue.getStructValue().size(); i++) {
			ScaStructProperty structProp;
			if (i < getStructs().size()) {
				structProp = getStructs().get(i);
				((ScaStructPropertyImpl) structProp).setValueFromRef(refValue.getStructValue().get(i));
			} else {
				structProp = createStructValue(getDefinition(), refValue.getStructValue().get(i));
				getStructs().add(structProp);
			}
		}
		for (int i = refValue.getStructValue().size(); i < getStructs().size();) {
			getStructs().remove(i);
		}
		setStatus(ScaPackage.Literals.SCA_STRUCT_SEQUENCE_PROPERTY__STRUCTS, Status.OK_STATUS);
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
	public void configure(DataType[] configProperties) throws InvalidConfiguration, PartialConfiguration {
		// END GENERATED CODE
		Map<Integer, Any> configMap = new HashMap<Integer, Any>();
		for (int i = 0; i < configProperties.length; i++) {
			try {
				configMap.put(Integer.valueOf(configProperties[i].id), configProperties[i].value);
			} catch (NumberFormatException e) {
				configMap.put(i, configProperties[i].value);
			}
		}

		List<Any> structAnys = new ArrayList<Any>();
		for (int i = 0; i < getStructs().size(); i++) {
			Any structAny = configMap.get(i);
			if (structAny == null) {
				structAny = getStructs().get(i).toAny();
			}
			structAnys.add(structAny);
		}

		Any any = JacorbUtil.init().create_any();
		AnySeqHelper.insert(any, structAnys.toArray(new Any[structAnys.size()]));
		setRemoteValue(any);
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
		case ScaPackage.SCA_STRUCT_SEQUENCE_PROPERTY__STRUCTS:
			Collection< ? extends ScaStructProperty> newSet = (Collection< ? extends ScaStructProperty>) newValue;
			if (newSet.isEmpty()) {
				getStructs().clear();
			} else {
				setIgnoreRemoteSet(true);
				try {
					getStructs().clear();
				} finally {
					setIgnoreRemoteSet(false);
				}
				getStructs().addAll(newSet);
			}
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
		case ScaPackage.SCA_STRUCT_SEQUENCE_PROPERTY__STRUCTS:
			getStructs().clear();
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
		case ScaPackage.SCA_STRUCT_SEQUENCE_PROPERTY__STRUCTS:
			return structs != null && !structs.isEmpty();
		}
		return super.eIsSet(featureID);
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
	 * 
	 * @since 20.0
	 *        <!-- end-user-doc -->
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
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ScaPackage.SCA_STRUCT_SEQUENCE_PROPERTY__STRUCTS:
			return ((InternalEList< ? >) getStructs()).basicRemove(otherEnd, msgs);
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
		case ScaPackage.SCA_STRUCT_SEQUENCE_PROPERTY__STRUCTS:
			return getStructs();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	// END GENERATED CODE

	/**
	 * @since 13.0
	 */
	@Override
	public Any toAny() {
		Any retVal = JacorbUtil.init().create_any();
		List<Any> structVals = new ArrayList<Any>();
		for (ScaStructProperty structProp : getStructs()) {
			structVals.add(structProp.toAny());
		}
		AnySeqHelper.insert(retVal, structVals.toArray(new Any[structVals.size()]));
		return retVal;
	}

	@Override
	public boolean isDefaultValue() {
		return getStructs().isDefaultValue();
	}

	@Override
	public void restoreDefaultValue() {
		getStructs().restoreDefaultValue();
	}

	/**
	 * @since 13.0
	 */
	@Override
	protected void internalFromAny(Any any) {
		try {
			Any[] structAnys;
			if (any.type().kind() == TCKind.tk_null) {
				structAnys = new Any[0];
			} else {
				structAnys = AnySeqHelper.extract(any);
			}
			for (int i = 0; i < structAnys.length; i++) {
				ScaStructProperty structProp;
				if (i < getStructs().size()) {
					structProp = getStructs().get(i);
				} else {
					structProp = createStructValue(getDefinition(), null);
					getStructs().add(structProp);
				}
				structProp.fromAny(structAnys[i]);
			}

			for (int i = structAnys.length; i < getStructs().size();) {
				getStructs().remove(i);
			}
			setStatus(ScaPackage.Literals.SCA_STRUCT_SEQUENCE_PROPERTY__STRUCTS, Status.OK_STATUS);
		} catch (SystemException e) {
			setStatus(ScaPackage.Literals.SCA_STRUCT_SEQUENCE_PROPERTY__STRUCTS,
				new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to demarshal value of property '" + getName() + "'", e));
		}
	}

	/**
	 * @since 13.0
	 */
	@Override
	public IStatus getStatus() {
		IStatus parentStatus = super.getStatus();
		if (!getStructs().isEmpty()) {
			MultiStatus retVal = new MultiStatus(ScaModelPlugin.ID, Status.OK, "Struct Sequence property: " + getName(), null);
			retVal.addAll(super.getStatus());
			for (ScaStructProperty struct : getStructs()) {
				retVal.add(struct.getStatus());
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
	}

	private ScaStructProperty createStructValue(StructSequence seqDef, StructValue value) {
		ScaStructPropertyImpl struct = new ScaStructPropertyImpl();
		if (seqDef != null) {
			Struct structDef = EcoreUtil.copy(seqDef.getStruct());
			// Copy the Mode and Configuration kind since this new struct will not be contained by anyone
			structDef.setMode(seqDef.getMode());
			for (ConfigurationKind k : seqDef.getConfigurationKind()) {
				structDef.getConfigurationKind().add(EcoreUtil.copy(k));
			}

			struct.setDefinition(structDef);

			if (value != null) {
				for (SimpleRef ref : value.getSimpleRef()) {
					for (Simple simple : structDef.getSimple()) {
						if (ref.getRefID().equals(simple.getId())) {
							simple.setValue(ref.getValue());
						}
					}
				}
			}
			if (value != null) {
				for (SimpleSequenceRef ref : value.getSimpleSequenceRef()) {
					for (SimpleSequence sequence : structDef.getSimpleSequence()) {
						if (ref.getRefID().equals(sequence.getId())) {
							sequence.setValues(ref.getValues());
						}
					}
				}
			}
			struct.restoreDefaultValue();
		} else {
			struct.setId(getId());
			struct.setName(getName());
		}
		return struct;
	}

	// BEGIN GENERATED CODE

} // ScaStructSequencePropertyImpl

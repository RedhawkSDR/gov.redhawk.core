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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.Struct;

import org.apache.commons.lang.NotImplementedException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
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
 *        <li>{@link gov.redhawk.model.sca.impl.ScaStructPropertyImpl#getSimples <em>Simples</em>}</li>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaStructPropertyImpl#getSequences <em>Sequences</em>}</li>
 *        </ul>
 *        </p>
 *
 * @generated
 */
public class ScaStructPropertyImpl extends ScaAbstractPropertyImpl<Struct> implements ScaStructProperty {
	/**
	 * The cached value of the '{@link #getSimples() <em>Simples</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getSimples()
	 * @generated NOT
	 * @ordered
	 */
	protected final SimplesList simples = new SimplesList(this);

	/**
	 * The cached value of the '{@link #getSequences() <em>Sequences</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getSequences()
	 * @generated
	 * @ordered
	 */
	protected SequencesList sequences = new SequencesList(this);

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
	 * @since 14.0
	 */
	protected static class SimplesList extends EObjectContainmentEList<ScaSimpleProperty> {
		// END GENERATED CODE

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public SimplesList(ScaStructPropertyImpl property) {
			super(ScaSimpleProperty.class, property, ScaPackage.SCA_STRUCT_PROPERTY__SIMPLES);
		}

		public void setDefinition(Struct defValue) {
			// Update the list
			if (defValue != null) {
				Map<String, ScaSimpleProperty> currentSimpleMap = new HashMap<String, ScaSimpleProperty>();
				for (ScaSimpleProperty simple : this) {
					currentSimpleMap.put(simple.getId(), simple);
				}

				Map<String, Simple> newSimpleMap = new LinkedHashMap<String, Simple>();
				for (final Simple simple : defValue.getSimple()) {
					newSimpleMap.put(simple.getId(), simple);
				}

				Map<String, ScaSimpleProperty> removeSimpleMap = new HashMap<String, ScaSimpleProperty>();
				removeSimpleMap.putAll(currentSimpleMap);
				removeSimpleMap.keySet().removeAll(newSimpleMap.keySet());
				remove(removeSimpleMap.values());

				for (final Simple simple : newSimpleMap.values()) {
					ScaSimpleProperty scaSimple = currentSimpleMap.get(simple.getId());
					if (scaSimple == null) {
						scaSimple = new ScaSimplePropertyImpl();
						add(scaSimple);
					}
					((ScaSimplePropertyImpl) scaSimple).setDefinition(simple);
				}
			} else {
				clear();
			}
		}

		/**
		 * @since 14.0
		 */
		public void restoreDefaultValue() {
			for (ScaSimpleProperty prop : this) {
				prop.restoreDefaultValue();
			}
		}

		/**
		 * @since 14.0
		 */
		public boolean isDefaultValue() {
			for (ScaSimpleProperty prop : this) {
				if (!prop.isDefaultValue()) {
					return false;
				}
			}
			return true;
		}

		// BEGIN GENERATED CODE
	}

	protected static class SequencesList extends EObjectContainmentEList<ScaSimpleSequenceProperty> {
		// END GENERATED CODE

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public SequencesList(ScaStructPropertyImpl property) {
			super(ScaSimpleSequenceProperty.class, property, ScaPackage.SCA_STRUCT_PROPERTY__SEQUENCES);
		}

		public void setDefinition(Struct defValue) {
			// Update the list
			if (defValue != null) {
				Map<String, ScaSimpleSequenceProperty> currentSimpleMap = new HashMap<String, ScaSimpleSequenceProperty>();
				for (ScaSimpleSequenceProperty simple : this) {
					currentSimpleMap.put(simple.getId(), simple);
				}

				Map<String, SimpleSequence> newSimpleMap = new LinkedHashMap<String, SimpleSequence>();
				for (final SimpleSequence simple : defValue.getSimpleSequence()) {
					newSimpleMap.put(simple.getId(), simple);
				}

				Map<String, ScaSimpleSequenceProperty> removeSimpleMap = new HashMap<String, ScaSimpleSequenceProperty>();
				removeSimpleMap.putAll(currentSimpleMap);
				removeSimpleMap.keySet().removeAll(newSimpleMap.keySet());
				remove(removeSimpleMap.values());

				for (final SimpleSequence simple : newSimpleMap.values()) {
					ScaSimpleSequenceProperty scaSimple = currentSimpleMap.get(simple.getId());
					if (scaSimple == null) {
						scaSimple = new ScaSimpleSequencePropertyImpl();
						add(scaSimple);
					}
					((ScaSimpleSequencePropertyImpl) scaSimple).setDefinition(simple);
				}
			} else {
				clear();
			}
		}

		/**
		 * @since 14.0
		 */
		public void restoreDefaultValue() {
			for (ScaSimpleSequenceProperty prop : this) {
				prop.restoreDefaultValue();
			}
		}

		/**
		 * @since 14.0
		 */
		public boolean isDefaultValue() {
			for (ScaSimpleSequenceProperty prop : this) {
				if (!prop.isDefaultValue()) {
					return false;
				}
			}
			return true;
		}

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
	public SimplesList getSimples() {
		// END GENERATED CODE
		return simples;
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public SequencesList getSequences() {
		// END GENERATED CODE
		return sequences;
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 14.0
	 */
	@Override
	public void setDefinition(Struct newDefinition) {
		if (newDefinition != definition && simples != null) {
			((SimplesList) simples).setDefinition(newDefinition);
		}
		if (newDefinition != definition && sequences != null) {
			((SequencesList) sequences).setDefinition(newDefinition);
		}
		super.setDefinition(newDefinition);
	}

	@Override
	protected void internalFromAny(Any newAny) {
		// END GENERATED CODE
		try {
			if (newAny != null) {
				final DataType[] fields = PropertiesHelper.extract(newAny);
				if (fields != null) {
					for (final DataType type : fields) {
						boolean found = false;
						for (final ScaSimpleProperty simple : getSimples()) {
							if (PluginUtil.equals(simple.getId(), type.id)) {
								simple.fromAny(type.value);
								found = true;
								break;
							}
						}
						if (!found) {
							ScaSimpleProperty newProp = ScaFactory.eINSTANCE.createScaSimpleProperty();
							newProp.setId(type.id);
							newProp.fromAny(type.value);
							getSimples().add(newProp);
						}
					}
					return;
				}
			} else {
				restoreDefaultValue();
			}
			setStatus(ScaPackage.Literals.SCA_STRUCT_PROPERTY__SIMPLES, Status.OK_STATUS);
		} catch (Exception e) { // SUPPRESS CHECKSTYLE Logged Catch all exception
			setStatus(ScaPackage.Literals.SCA_STRUCT_PROPERTY__SIMPLES, new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to read property value of:"
				+ getName(), e));
		}
		try {
			if (newAny != null) {
				final DataType[] fields = PropertiesHelper.extract(newAny);
				if (fields != null) {
					for (final DataType type : fields) {
						boolean found = false;
						for (final ScaSimpleSequenceProperty sequence : getSequences()) {
							if (PluginUtil.equals(sequence.getId(), type.id)) {
								sequence.fromAny(type.value);
								found = true;
								break;
							}
						}
						if (!found) {
							ScaSimpleSequenceProperty newProp = ScaFactory.eINSTANCE.createScaSimpleSequenceProperty();
							newProp.setId(type.id);
							newProp.fromAny(type.value);
							getSequences().add(newProp);
						}
					}
					return;
				}
			} else {
				restoreDefaultValue();
			}
			setStatus(ScaPackage.Literals.SCA_STRUCT_PROPERTY__SEQUENCES, Status.OK_STATUS);
		} catch (Exception e) { // SUPPRESS CHECKSTYLE Logged Catch all exception
			setStatus(ScaPackage.Literals.SCA_STRUCT_PROPERTY__SEQUENCES, new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to read property value of:"
				+ getName(), e));
		}
		// BEGIN GENERATED CODE
	}

	@Override
	public Any toAny() {
		// END GENERATED CODE
		Any retVal = JacorbUtil.init().create_any();
		List<DataType> fields = new ArrayList<DataType>(getSimples().size() + getSequences().size());
		for (ScaSimpleProperty prop : getSimples()) {
			fields.add(new DataType(prop.getId(), prop.toAny()));
		}
		for (ScaSimpleSequenceProperty prop : getSequences()) {
			fields.add(new DataType(prop.getId(), prop.toAny()));
		}
		PropertiesHelper.insert(retVal, fields.toArray(new DataType[fields.size()]));
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public ScaSimpleSequenceProperty getSequence(String id) {
		// END GENERATED CODE
		for (ScaSimpleSequenceProperty prop : getSequences()) {
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

		List<DataType> simpleValues = new ArrayList<DataType>(getSimples().size());
		for (ScaSimpleProperty prop : getSimples()) {
			Any simpleAny = configMap.get(prop.getId());
			if (simpleAny == null) {
				simpleAny = prop.toAny();
			}
			simpleValues.add(new DataType(prop.getId(), simpleAny));
		}
		List<DataType> sequenceValues = new ArrayList<DataType>(getSequences().size());
		for (ScaSimpleSequenceProperty prop : getSequences()) {
			Any simpleAny = configMap.get(prop.getId());
			if (simpleAny == null) {
				simpleAny = prop.toAny();
			}
			sequenceValues.add(new DataType(prop.getId(), simpleAny));
		}
		Any any = JacorbUtil.init().create_any();
		PropertiesHelper.insert(any, simpleValues.toArray(new DataType[simpleValues.size()]));
		PropertiesHelper.insert(any, sequenceValues.toArray(new DataType[sequenceValues.size()]));
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
	 * @since 19.1
	 */
	@Override
	public String registerPropertyListener(org.omg.CORBA.Object obj, String[] prop_ids, float interval) throws UnknownProperties, InvalidObjectReference {
		throw new NotImplementedException();
	}

	/**
	 * @since 19.1
	 */
	@Override
	public void unregisterPropertyListener(String id) throws InvalidIdentifier {
		throw new NotImplementedException();
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
		case ScaPackage.SCA_STRUCT_PROPERTY__SIMPLES:
			return ((InternalEList< ? >) getSimples()).basicRemove(otherEnd, msgs);
		case ScaPackage.SCA_STRUCT_PROPERTY__SEQUENCES:
			return ((InternalEList< ? >) getSequences()).basicRemove(otherEnd, msgs);
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
		case ScaPackage.SCA_STRUCT_PROPERTY__SIMPLES:
			return getSimples();
		case ScaPackage.SCA_STRUCT_PROPERTY__SEQUENCES:
			return getSequences();
		}
		return super.eGet(featureID, resolve, coreType);
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
		case ScaPackage.SCA_STRUCT_PROPERTY__SIMPLES:
			setIgnoreRemoteSet(true);
			try {
				getSimples().clear();
			} finally {
				setIgnoreRemoteSet(false);
			}
			getSimples().addAll((Collection< ? extends ScaSimpleProperty>) newValue);
			return;
		case ScaPackage.SCA_STRUCT_PROPERTY__SEQUENCES:
			setIgnoreRemoteSet(true);
			try {
				getSequences().clear();
			} finally {
				setIgnoreRemoteSet(false);
			}
			getSequences().addAll((Collection< ? extends ScaSimpleSequenceProperty>) newValue);
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
		case ScaPackage.SCA_STRUCT_PROPERTY__SIMPLES:
			getSimples().clear();
			return;
		case ScaPackage.SCA_STRUCT_PROPERTY__SEQUENCES:
			getSequences().clear();
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
		case ScaPackage.SCA_STRUCT_PROPERTY__SIMPLES:
			return simples != null && !simples.isEmpty();
		case ScaPackage.SCA_STRUCT_PROPERTY__SEQUENCES:
			return sequences != null && !sequences.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	@Override
	public IStatus getStatus() {
		// END GENERATED CODE
		IStatus parentStatus = super.getStatus();
		if (!getSimples().isEmpty()) {
			MultiStatus retVal = new MultiStatus(ScaModelPlugin.ID, Status.OK, "Struct property: " + getName(), null);
			retVal.addAll(super.getStatus());
			for (ScaSimpleProperty prop : getSimples()) {
				retVal.add(prop.getStatus());
			}
			for (ScaSimpleSequenceProperty prop : getSequences()) {
				retVal.add(prop.getStatus());
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
		return getSimples().isDefaultValue() && getSequences().isDefaultValue();
	}

	@Override
	public void restoreDefaultValue() {
		getSimples().restoreDefaultValue();
		getSequences().restoreDefaultValue();
	}

} // ScaStructPropertyImpl

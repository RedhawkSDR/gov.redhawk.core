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

import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaPropertyContainer;
import gov.redhawk.sca.util.Debug;
import gov.redhawk.sca.util.PluginUtil;
import mil.jpeojtrs.sca.prf.AbstractProperty;
import mil.jpeojtrs.sca.prf.AccessType;
import mil.jpeojtrs.sca.util.AnyUtils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.omg.CORBA.Any;
import org.omg.CORBA.ORB;

import CF.DataType;
import CF.PropertySetOperations;
import CF.PropertySetPackage.InvalidConfiguration;
import CF.PropertySetPackage.PartialConfiguration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Property</b></em>'.
 * 
 * @since 12.0
 *        <!-- end-user-doc -->
 *        <p>
 *        The following features are implemented:
 *        <ul>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaAbstractPropertyImpl#getDefinition <em>Definition</em>}</li>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaAbstractPropertyImpl#getDescription <em>Description</em>}</li>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaAbstractPropertyImpl#getId <em>Id</em>}</li>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaAbstractPropertyImpl#getMode <em>Mode</em>}</li>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaAbstractPropertyImpl#getName <em>Name</em>}</li>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaAbstractPropertyImpl#isIgnoreRemoteSet <em>Ignore Remote Set</em>}</li>
 *        </ul>
 *        </p>
 *
 * @generated
 */
public abstract class ScaAbstractPropertyImpl< T extends AbstractProperty > extends IStatusProviderImpl implements ScaAbstractProperty<T> {

	/**
	 * The cached value of the '{@link #getDefinition() <em>Definition</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getDefinition()
	 * @generated
	 * @ordered
	 */
	protected T definition;
	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;
	/**
	 * The default value of the '{@link #getMode() <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getMode()
	 * @generated
	 * @ordered
	 */
	protected static final AccessType MODE_EDEFAULT = AccessType.WRITEONLY;
	/**
	 * The cached value of the '{@link #getMode() <em>Mode</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getMode()
	 * @generated
	 * @ordered
	 */
	protected AccessType mode = MODE_EDEFAULT;
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;
	/**
	 * The default value of the '{@link #isIgnoreRemoteSet() <em>Ignore Remote Set</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #isIgnoreRemoteSet()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IGNORE_REMOTE_SET_EDEFAULT = false;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ScaAbstractPropertyImpl() {
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
		return ScaPackage.Literals.SCA_ABSTRACT_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	public T getDefinition() {
		if (definition != null && definition.eIsProxy()) {
			InternalEObject oldDefinition = (InternalEObject) definition;
			definition = (T) eResolveProxy(oldDefinition);
			if (definition != oldDefinition) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ScaPackage.SCA_ABSTRACT_PROPERTY__DEFINITION, oldDefinition, definition));
			}
		}
		return definition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public T basicGetDefinition() {
		return definition;
	}

	private static final Debug DEBUG = new Debug(ScaModelPlugin.ID, "scaAbstractProperty/ignoreRemoteSet");
	/**
	 * @since 13.0
	 */
	private int ignoreRemoteSetNumber = 0;

	/**
	 * @since 13.0
	 */
	protected class PushValueJob extends SilentModelJob {
		public Any newRemoteValue = null;

		public PushValueJob() {
			super("Setting Property Value", ScaAbstractPropertyImpl.this, ScaPackage.Literals.SCA_SIMPLE_PROPERTY__VALUE);
		}

		@Override
		public boolean shouldSchedule() {
			return super.shouldSchedule() && !isIgnoreRemoteSet();
		}

		@Override
		protected IStatus runSilent(IProgressMonitor monitor) {
			try {
				Any tmpValue = newRemoteValue;
				if (tmpValue == null) {
					tmpValue = ORB.init().create_any();
				}
				setRemoteValue(tmpValue);
			} catch (PartialConfiguration e) {
				return new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to set remove value for " + ScaAbstractPropertyImpl.this.getName(), e);
			} catch (InvalidConfiguration e) {
				return new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to set remove value for " + ScaAbstractPropertyImpl.this.getName(), e);
			} finally {
				for (EObject parent = eContainer(); parent != null; parent = parent.eContainer()) {
					if (parent instanceof ScaPropertyContainer< ? , ? >) {
						ScaPropertyContainer< ? , ? > propCont = (ScaPropertyContainer< ? , ? >) parent;
						propCont.fetchProperties(monitor);
						break;
					}
				}
			}
			return Status.OK_STATUS;
		}

	}

	/**
	 * @since 13.0
	 */
	protected final PushValueJob pushValueJob = new PushValueJob();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setDefinitionGen(T newDefinition) {
		T oldDefinition = definition;
		definition = newDefinition;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_ABSTRACT_PROPERTY__DEFINITION, oldDefinition, definition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void setDefinition(T newDefinition) {
		T oldDef = getDefinition();
		setDefinitionGen(newDefinition);
		if (!PluginUtil.equals(oldDef, newDefinition)) {
			try {
				setIgnoreRemoteSet(true);
				updateAttributes();
				restoreDefaultValue();
			} finally {
				setIgnoreRemoteSet(false);
			}
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_ABSTRACT_PROPERTY__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_ABSTRACT_PROPERTY__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public AccessType getMode() {
		return mode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setMode(AccessType newMode) {
		AccessType oldMode = mode;
		mode = newMode == null ? MODE_EDEFAULT : newMode;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_ABSTRACT_PROPERTY__MODE, oldMode, mode));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_ABSTRACT_PROPERTY__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 13.0
	 *        <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean isIgnoreRemoteSet() {
		// END GENERATED CODE
		EObject container = eContainer();
		if (container instanceof ScaAbstractProperty< ? >) {
			ScaAbstractProperty< ? > prop = (ScaAbstractProperty< ? >) container;
			if (prop.isIgnoreRemoteSet()) {
				return true;
			}
		}
		if (!(container instanceof PropertySetOperations)) {
			return true;
		}
		return ignoreRemoteSetNumber > 0;
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
	public void setIgnoreRemoteSet(boolean newIgnoreRemoteSet) {
		// END GENERATED CODE
		boolean oldValue = isIgnoreRemoteSet();
		if (newIgnoreRemoteSet) {
			ignoreRemoteSetNumber++;
		} else {
			ignoreRemoteSetNumber--;
		}
		if (ignoreRemoteSetNumber < 0) {
			if (DEBUG.enabled) {
				DEBUG.message("Setting ignore remote set to less than zero.");
				DEBUG.catching(new Exception().fillInStackTrace());
			}
			ignoreRemoteSetNumber = 0;
		}
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_ABSTRACT_PROPERTY__IGNORE_REMOTE_SET, oldValue, isIgnoreRemoteSet()));
		}
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
	public abstract Any toAny();

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 13.0
	 *        <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public final void fromAny(Any any) {
		try {
			setIgnoreRemoteSet(true);
			internalFromAny(any);
		} finally {
			setIgnoreRemoteSet(false);
		}
	}

	/**
	 * @since 13.0
	 */
	protected abstract void internalFromAny(Any any);

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 13.0
	 *        <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void setRemoteValue(Any any) throws PartialConfiguration, InvalidConfiguration {
		// END GENERATED CODE
		EObject container = eContainer();
		if (container instanceof PropertySetOperations) {
			PropertySetOperations controller = (PropertySetOperations) container;
			String localId = getId();
			if (localId != null) {
				controller.configure(new DataType[] { new DataType(localId, any) });
			}
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
	public DataType getProperty() {
		// END GENERATED CODE
		return new DataType(getId(), toAny());
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
	public abstract boolean isDefaultValue();

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 14.0
	 *        <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public abstract void restoreDefaultValue();

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 15.0
	 *        <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public boolean valueEquals(Any any) {
		// END GENERATED CODE
		return valueEquals(toAny(), any);
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
		case ScaPackage.SCA_ABSTRACT_PROPERTY__DEFINITION:
			if (resolve)
				return getDefinition();
			return basicGetDefinition();
		case ScaPackage.SCA_ABSTRACT_PROPERTY__DESCRIPTION:
			return getDescription();
		case ScaPackage.SCA_ABSTRACT_PROPERTY__ID:
			return getId();
		case ScaPackage.SCA_ABSTRACT_PROPERTY__MODE:
			return getMode();
		case ScaPackage.SCA_ABSTRACT_PROPERTY__NAME:
			return getName();
		case ScaPackage.SCA_ABSTRACT_PROPERTY__IGNORE_REMOTE_SET:
			return isIgnoreRemoteSet();
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
		case ScaPackage.SCA_ABSTRACT_PROPERTY__DEFINITION:
			setDefinition((T) newValue);
			return;
		case ScaPackage.SCA_ABSTRACT_PROPERTY__DESCRIPTION:
			setDescription((String) newValue);
			return;
		case ScaPackage.SCA_ABSTRACT_PROPERTY__ID:
			setId((String) newValue);
			return;
		case ScaPackage.SCA_ABSTRACT_PROPERTY__MODE:
			setMode((AccessType) newValue);
			return;
		case ScaPackage.SCA_ABSTRACT_PROPERTY__NAME:
			setName((String) newValue);
			return;
		case ScaPackage.SCA_ABSTRACT_PROPERTY__IGNORE_REMOTE_SET:
			setIgnoreRemoteSet((Boolean) newValue);
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
		case ScaPackage.SCA_ABSTRACT_PROPERTY__DEFINITION:
			setDefinition((T) null);
			return;
		case ScaPackage.SCA_ABSTRACT_PROPERTY__DESCRIPTION:
			setDescription(DESCRIPTION_EDEFAULT);
			return;
		case ScaPackage.SCA_ABSTRACT_PROPERTY__ID:
			setId(ID_EDEFAULT);
			return;
		case ScaPackage.SCA_ABSTRACT_PROPERTY__MODE:
			setMode(MODE_EDEFAULT);
			return;
		case ScaPackage.SCA_ABSTRACT_PROPERTY__NAME:
			setName(NAME_EDEFAULT);
			return;
		case ScaPackage.SCA_ABSTRACT_PROPERTY__IGNORE_REMOTE_SET:
			setIgnoreRemoteSet(IGNORE_REMOTE_SET_EDEFAULT);
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
		case ScaPackage.SCA_ABSTRACT_PROPERTY__DEFINITION:
			return definition != null;
		case ScaPackage.SCA_ABSTRACT_PROPERTY__DESCRIPTION:
			return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
		case ScaPackage.SCA_ABSTRACT_PROPERTY__ID:
			return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		case ScaPackage.SCA_ABSTRACT_PROPERTY__MODE:
			return mode != MODE_EDEFAULT;
		case ScaPackage.SCA_ABSTRACT_PROPERTY__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case ScaPackage.SCA_ABSTRACT_PROPERTY__IGNORE_REMOTE_SET:
			return isIgnoreRemoteSet() != IGNORE_REMOTE_SET_EDEFAULT;
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
		result.append(" (description: ");
		result.append(description);
		result.append(", id: ");
		result.append(id);
		result.append(", mode: ");
		result.append(mode);
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

	private static boolean valueEquals(DataType leftData, DataType rightData) {
		if (!PluginUtil.equals(leftData.id, rightData.id)) {
			return false;
		}
		return valueEquals(leftData.value, rightData.value);
	}

	private static boolean valueEquals(Any left, Any right) {
		final Object leftObj = AnyUtils.convertAny(left);
		final Object rightObj = AnyUtils.convertAny(right);
		return valueEquals(leftObj, rightObj);
	}

	private static boolean valueEquals(Object leftElement, Object rightElement) {
		if (leftElement instanceof DataType && rightElement instanceof DataType) {
			return valueEquals((DataType) leftElement, (DataType) rightElement);
		} else if (leftElement instanceof Any && rightElement instanceof Any) {
			return valueEquals((Any) leftElement, (Any) rightElement);
		} else if (leftElement instanceof Object[] && rightElement instanceof Object[]) {
			Object[] leftArray = (Object[]) leftElement;
			Object[] rightArray = (Object[]) rightElement;
			if (leftArray.length == rightArray.length) {
				for (int i = 0; i < leftArray.length; i++) {
					Object leftArrayElement = leftArray[i];
					Object rightArrayElement = rightArray[i];
					if (!valueEquals(leftArrayElement, rightArrayElement)) {
						return false;
					}
				}
				return true;
			} else {
				return false;
			}
		} else {
			return PluginUtil.equals(leftElement, rightElement);
		}
	}

	protected void updateAttributes() {
		T defValue = getDefinition();
		if (defValue != null) {
			setName(defValue.getName());
			setId(defValue.getId());
			setDescription(defValue.getDescription());
			setMode(defValue.getMode());
		} else {
			setName(null);
			setId(null);
			setDescription(null);
			setMode(null);
		}
	}

} // ScaAbstractPropertyImpl

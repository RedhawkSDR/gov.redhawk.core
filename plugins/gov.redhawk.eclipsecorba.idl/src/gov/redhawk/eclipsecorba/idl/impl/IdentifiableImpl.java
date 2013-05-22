/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */

 // BEGIN GENERATED CODE
package gov.redhawk.eclipsecorba.idl.impl;

import gov.redhawk.eclipsecorba.idl.Identifiable;
import gov.redhawk.eclipsecorba.idl.IdlPackage;
import gov.redhawk.eclipsecorba.idl.Specification;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Identifiable</b></em>'. 
 * @since 6.0
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.impl.IdentifiableImpl#getName <em>Name</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.impl.IdentifiableImpl#getScopedName <em>Scoped Name</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.impl.IdentifiableImpl#getRepId <em>Rep Id</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.impl.IdentifiableImpl#getPrefix <em>Prefix</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.impl.IdentifiableImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.impl.IdentifiableImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class IdentifiableImpl extends EObjectImpl implements Identifiable {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;
	/**
	 * The default value of the '{@link #getScopedName() <em>Scoped Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScopedName()
	 * @generated
	 * @ordered
	 */
	protected static final String SCOPED_NAME_EDEFAULT = "";
	/**
	 * The default value of the '{@link #getRepId() <em>Rep Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepId()
	 * @generated
	 * @ordered
	 */
	protected static final String REP_ID_EDEFAULT = null;
	/**
	 * The default value of the '{@link #getPrefix() <em>Prefix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrefix()
	 * @generated
	 * @ordered
	 */
	protected static final String PREFIX_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getPrefix() <em>Prefix</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrefix()
	 * @generated
	 * @ordered
	 */
	protected String prefix = PREFIX_EDEFAULT;
	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IdentifiableImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IdlPackage.Literals.IDENTIFIABLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdlPackage.IDENTIFIABLE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getScopedName() {
		final EObject parent = eContainer();
		if (parent instanceof Identifiable) {
			return ((Identifiable) parent).getScopedName() + "/" + getName();
		} else {
			return getName();
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public String toString() {
		if (eIsProxy()) {
			return super.toString();
		}

		final StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(getName());
		result.append(", prefix: ");
		result.append(getPrefix());
		result.append(", version: ");
		result.append(getVersion());
		result.append(", id: ");
		result.append(getId());
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getRepId() {
		final StringBuilder builder = new StringBuilder();
		builder.append("IDL:");
		builder.append(getId());
		builder.append(':');
		builder.append(this.getVersion());

		return builder.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setRepId(final String newRepId) {
		if (!newRepId.matches("IDL:[\\w/\\.]*(:(\\d)+\\.(\\d))?")) {
			throw new IllegalArgumentException("Invalid REPID: " + newRepId);
		}
		this.setFullId(newRepId.substring(4));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getPrefix() {
		if (this.prefix == null || this.prefix.length() == 0) {
			if (eContainer() instanceof Identifiable) {
				return ((Identifiable) eContainer()).getPrefix();
			} else if (eContainer() instanceof Specification) {
				return ((Specification) eContainer()).getPrefix();
			}
		}
		return this.prefix;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrefix(String newPrefix) {
		String oldPrefix = prefix;
		prefix = newPrefix;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdlPackage.IDENTIFIABLE__PREFIX, oldPrefix, prefix));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getVersion() {
		if (this.version == null) {
			if (eContainer() instanceof Identifiable) {
				return ((Identifiable) eContainer()).getVersion();
			} else {
				return "1.0";
			}
		}
		return this.version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdlPackage.IDENTIFIABLE__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getId() {
		if (this.id == null) {
			final StringBuilder builder = new StringBuilder();

			final String prefix = getPrefix();
			if (prefix != null && prefix.length() > 0) {
				builder.append(prefix);
				builder.append('/');
			}

			builder.append(getIdWithoutPrefix());

			return builder.toString();
		}
		return this.id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdlPackage.IDENTIFIABLE__ID, oldId, id));
	}

	private String getIdWithoutPrefix() {
		if (this.id == null) {
			final StringBuilder builder = new StringBuilder();

			final EObject parent = eContainer();

			if (parent instanceof IdentifiableImpl) {
				final IdentifiableImpl iParent = (IdentifiableImpl) parent;
				builder.append(iParent.getIdWithoutPrefix());
				builder.append('/');
			}

			builder.append(getName());

			return builder.toString();
		}
		return this.id;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setFullId(final String id) {
		final int index = id.lastIndexOf(":");
		// Override the prefix value
		//		setPrefix(null);
		setId(id.substring(0, index));
		setVersion(id.substring(index + 1));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case IdlPackage.IDENTIFIABLE__NAME:
				return getName();
			case IdlPackage.IDENTIFIABLE__SCOPED_NAME:
				return getScopedName();
			case IdlPackage.IDENTIFIABLE__REP_ID:
				return getRepId();
			case IdlPackage.IDENTIFIABLE__PREFIX:
				return getPrefix();
			case IdlPackage.IDENTIFIABLE__VERSION:
				return getVersion();
			case IdlPackage.IDENTIFIABLE__ID:
				return getId();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case IdlPackage.IDENTIFIABLE__NAME:
				setName((String)newValue);
				return;
			case IdlPackage.IDENTIFIABLE__REP_ID:
				setRepId((String)newValue);
				return;
			case IdlPackage.IDENTIFIABLE__PREFIX:
				setPrefix((String)newValue);
				return;
			case IdlPackage.IDENTIFIABLE__VERSION:
				setVersion((String)newValue);
				return;
			case IdlPackage.IDENTIFIABLE__ID:
				setId((String)newValue);
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
			case IdlPackage.IDENTIFIABLE__NAME:
				setName(NAME_EDEFAULT);
				return;
			case IdlPackage.IDENTIFIABLE__REP_ID:
				setRepId(REP_ID_EDEFAULT);
				return;
			case IdlPackage.IDENTIFIABLE__PREFIX:
				setPrefix(PREFIX_EDEFAULT);
				return;
			case IdlPackage.IDENTIFIABLE__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case IdlPackage.IDENTIFIABLE__ID:
				setId(ID_EDEFAULT);
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
			case IdlPackage.IDENTIFIABLE__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case IdlPackage.IDENTIFIABLE__SCOPED_NAME:
				return SCOPED_NAME_EDEFAULT == null ? getScopedName() != null : !SCOPED_NAME_EDEFAULT.equals(getScopedName());
			case IdlPackage.IDENTIFIABLE__REP_ID:
				return REP_ID_EDEFAULT == null ? getRepId() != null : !REP_ID_EDEFAULT.equals(getRepId());
			case IdlPackage.IDENTIFIABLE__PREFIX:
				return PREFIX_EDEFAULT == null ? prefix != null : !PREFIX_EDEFAULT.equals(prefix);
			case IdlPackage.IDENTIFIABLE__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case IdlPackage.IDENTIFIABLE__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		}
		return super.eIsSet(featureID);
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof Identifiable) {
			return this.getRepId().equals(((Identifiable) obj).getRepId());
		}
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return this.getRepId().hashCode();
	}

} //IdentifiableImpl

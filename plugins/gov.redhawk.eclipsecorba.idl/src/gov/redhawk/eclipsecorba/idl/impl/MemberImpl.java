/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */

 // BEGIN GENERATED CODE
package gov.redhawk.eclipsecorba.idl.impl;

import gov.redhawk.eclipsecorba.idl.Comment;
import gov.redhawk.eclipsecorba.idl.Commentable;
import gov.redhawk.eclipsecorba.idl.Declarator;
import gov.redhawk.eclipsecorba.idl.IdlPackage;
import gov.redhawk.eclipsecorba.idl.Member;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Member</b></em>'.
 * @since 6.0
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.impl.MemberImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.impl.MemberImpl#getDeclarators <em>Declarators</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MemberImpl extends TypedElementImpl implements Member {
	/**
	 * The cached value of the '{@link #getComment() <em>Comment</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComment()
	 * @generated
	 * @ordered
	 */
	protected Comment comment;
	/**
	 * The cached value of the '{@link #getDeclarators() <em>Declarators</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeclarators()
	 * @generated
	 * @ordered
	 */
	protected EList<Declarator> declarators;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MemberImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return IdlPackage.Literals.MEMBER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Comment getComment() {
		return comment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetComment(Comment newComment, NotificationChain msgs) {
		Comment oldComment = comment;
		comment = newComment;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, IdlPackage.MEMBER__COMMENT, oldComment, newComment);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setComment(Comment newComment) {
		if (newComment != comment) {
			NotificationChain msgs = null;
			if (comment != null)
				msgs = ((InternalEObject)comment).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - IdlPackage.MEMBER__COMMENT, null, msgs);
			if (newComment != null)
				msgs = ((InternalEObject)newComment).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - IdlPackage.MEMBER__COMMENT, null, msgs);
			msgs = basicSetComment(newComment, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, IdlPackage.MEMBER__COMMENT, newComment, newComment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Declarator> getDeclarators() {
		if (declarators == null) {
			declarators = new EObjectContainmentEList<Declarator>(Declarator.class, this, IdlPackage.MEMBER__DECLARATORS);
		}
		return declarators;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case IdlPackage.MEMBER__COMMENT:
				return basicSetComment(null, msgs);
			case IdlPackage.MEMBER__DECLARATORS:
				return ((InternalEList<?>)getDeclarators()).basicRemove(otherEnd, msgs);
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
			case IdlPackage.MEMBER__COMMENT:
				return getComment();
			case IdlPackage.MEMBER__DECLARATORS:
				return getDeclarators();
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
			case IdlPackage.MEMBER__COMMENT:
				setComment((Comment)newValue);
				return;
			case IdlPackage.MEMBER__DECLARATORS:
				getDeclarators().clear();
				getDeclarators().addAll((Collection<? extends Declarator>)newValue);
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
			case IdlPackage.MEMBER__COMMENT:
				setComment((Comment)null);
				return;
			case IdlPackage.MEMBER__DECLARATORS:
				getDeclarators().clear();
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
			case IdlPackage.MEMBER__COMMENT:
				return comment != null;
			case IdlPackage.MEMBER__DECLARATORS:
				return declarators != null && !declarators.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == Commentable.class) {
			switch (derivedFeatureID) {
				case IdlPackage.MEMBER__COMMENT: return IdlPackage.COMMENTABLE__COMMENT;
				default: return -1;
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
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == Commentable.class) {
			switch (baseFeatureID) {
				case IdlPackage.COMMENTABLE__COMMENT: return IdlPackage.MEMBER__COMMENT;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	@Override
	public String getName() {
		if (name == null) {
			StringBuilder builder = new StringBuilder();
			builder.append('(');
			for(Iterator<Declarator> i = getDeclarators().iterator(); i.hasNext();) {
				builder.append(i.next().getName());
				if (i.hasNext()) {
					builder.append(", ");
				}
			}
			builder.append(')');
			return builder.toString();
		}
		return super.getName();
	}

} //MemberImpl

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
package gov.redhawk.eclipsecorba.idl.operations.impl;

import gov.redhawk.eclipsecorba.idl.Comment;
import gov.redhawk.eclipsecorba.idl.Commentable;
import gov.redhawk.eclipsecorba.idl.IdlException;
import gov.redhawk.eclipsecorba.idl.IdlPackage;
import gov.redhawk.eclipsecorba.idl.expressions.StringLiteral;
import gov.redhawk.eclipsecorba.idl.impl.ExportImpl;
import gov.redhawk.eclipsecorba.idl.operations.Operation;
import gov.redhawk.eclipsecorba.idl.operations.OperationsPackage;
import gov.redhawk.eclipsecorba.idl.operations.Parameter;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation</b></em>'.
 * @since 6.0
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.operations.impl.OperationImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.operations.impl.OperationImpl#isOneway <em>Oneway</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.operations.impl.OperationImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.operations.impl.OperationImpl#getExceptions <em>Exceptions</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.operations.impl.OperationImpl#getContext <em>Context</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OperationImpl extends ExportImpl implements Operation {

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
	 * The default value of the '{@link #isOneway() <em>Oneway</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOneway()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ONEWAY_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isOneway() <em>Oneway</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOneway()
	 * @generated
	 * @ordered
	 */
	protected boolean oneway = ONEWAY_EDEFAULT;
	/**
	 * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParameters()
	 * @generated
	 * @ordered
	 */
	protected EList<Parameter> parameters;
	/**
	 * The cached value of the '{@link #getExceptions() <em>Exceptions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getExceptions()
	 * @generated
	 * @ordered
	 */
	protected EList<IdlException> exceptions;
	/**
	 * The cached value of the '{@link #getContext() <em>Context</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContext()
	 * @generated
	 * @ordered
	 */
	protected EList<StringLiteral> context;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OperationsPackage.Literals.OPERATION;
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OperationsPackage.OPERATION__COMMENT, oldComment, newComment);
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
				msgs = ((InternalEObject)comment).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OperationsPackage.OPERATION__COMMENT, null, msgs);
			if (newComment != null)
				msgs = ((InternalEObject)newComment).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OperationsPackage.OPERATION__COMMENT, null, msgs);
			msgs = basicSetComment(newComment, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OperationsPackage.OPERATION__COMMENT, newComment, newComment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOneway() {
		return oneway;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOneway(boolean newOneway) {
		boolean oldOneway = oneway;
		oneway = newOneway;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OperationsPackage.OPERATION__ONEWAY, oldOneway, oneway));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Parameter> getParameters() {
		if (parameters == null) {
			parameters = new EObjectContainmentEList<Parameter>(Parameter.class, this, OperationsPackage.OPERATION__PARAMETERS);
		}
		return parameters;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IdlException> getExceptions() {
		if (exceptions == null) {
			exceptions = new EObjectResolvingEList<IdlException>(IdlException.class, this, OperationsPackage.OPERATION__EXCEPTIONS);
		}
		return exceptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StringLiteral> getContext() {
		if (context == null) {
			context = new EObjectContainmentEList<StringLiteral>(StringLiteral.class, this, OperationsPackage.OPERATION__CONTEXT);
		}
		return context;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OperationsPackage.OPERATION__COMMENT:
				return basicSetComment(null, msgs);
			case OperationsPackage.OPERATION__PARAMETERS:
				return ((InternalEList<?>)getParameters()).basicRemove(otherEnd, msgs);
			case OperationsPackage.OPERATION__CONTEXT:
				return ((InternalEList<?>)getContext()).basicRemove(otherEnd, msgs);
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
			case OperationsPackage.OPERATION__COMMENT:
				return getComment();
			case OperationsPackage.OPERATION__ONEWAY:
				return isOneway();
			case OperationsPackage.OPERATION__PARAMETERS:
				return getParameters();
			case OperationsPackage.OPERATION__EXCEPTIONS:
				return getExceptions();
			case OperationsPackage.OPERATION__CONTEXT:
				return getContext();
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
			case OperationsPackage.OPERATION__COMMENT:
				setComment((Comment)newValue);
				return;
			case OperationsPackage.OPERATION__ONEWAY:
				setOneway((Boolean)newValue);
				return;
			case OperationsPackage.OPERATION__PARAMETERS:
				getParameters().clear();
				getParameters().addAll((Collection<? extends Parameter>)newValue);
				return;
			case OperationsPackage.OPERATION__EXCEPTIONS:
				getExceptions().clear();
				getExceptions().addAll((Collection<? extends IdlException>)newValue);
				return;
			case OperationsPackage.OPERATION__CONTEXT:
				getContext().clear();
				getContext().addAll((Collection<? extends StringLiteral>)newValue);
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
			case OperationsPackage.OPERATION__COMMENT:
				setComment((Comment)null);
				return;
			case OperationsPackage.OPERATION__ONEWAY:
				setOneway(ONEWAY_EDEFAULT);
				return;
			case OperationsPackage.OPERATION__PARAMETERS:
				getParameters().clear();
				return;
			case OperationsPackage.OPERATION__EXCEPTIONS:
				getExceptions().clear();
				return;
			case OperationsPackage.OPERATION__CONTEXT:
				getContext().clear();
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
			case OperationsPackage.OPERATION__COMMENT:
				return comment != null;
			case OperationsPackage.OPERATION__ONEWAY:
				return oneway != ONEWAY_EDEFAULT;
			case OperationsPackage.OPERATION__PARAMETERS:
				return parameters != null && !parameters.isEmpty();
			case OperationsPackage.OPERATION__EXCEPTIONS:
				return exceptions != null && !exceptions.isEmpty();
			case OperationsPackage.OPERATION__CONTEXT:
				return context != null && !context.isEmpty();
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
				case OperationsPackage.OPERATION__COMMENT: return IdlPackage.COMMENTABLE__COMMENT;
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
				case IdlPackage.COMMENTABLE__COMMENT: return OperationsPackage.OPERATION__COMMENT;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (oneway: ");
		result.append(oneway);
		result.append(')');
		return result.toString();
	}

} //OperationImpl

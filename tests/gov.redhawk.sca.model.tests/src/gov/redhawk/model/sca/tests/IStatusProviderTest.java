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
package gov.redhawk.model.sca.tests;

import gov.redhawk.model.sca.IStatusProvider;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.commands.SetStatusCommand;
import gov.redhawk.model.sca.util.ScaTransactionalCommandStack;
import gov.redhawk.model.sca.util.ScaTransactionalEditingDomain;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.junit.Assert;
import junit.framework.TestCase;

import org.eclipse.core.runtime.Status;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>IStatus Provider</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 * <li>{@link gov.redhawk.model.sca.IStatusProvider#getStatus() <em>Status</em>}</li>
 * </ul>
 * </p>
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>
 * {@link gov.redhawk.model.sca.IStatusProvider#setStatus(org.eclipse.emf.ecore.EStructuralFeature, org.eclipse.core.runtime.IStatus)
 * <em>Set Status</em>}</li>
 * <li>{@link gov.redhawk.model.sca.IStatusProvider#clearAllStatus() <em>Clear All Status</em>}</li>
 * <li>{@link gov.redhawk.model.sca.IStatusProvider#getStatus(org.eclipse.emf.ecore.EStructuralFeature) <em>Get
 * Status</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public abstract class IStatusProviderTest extends TestCase {
	/**
	 * The fixture for this IStatus Provider test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IStatusProvider fixture = null;

	/**
	 * Constructs a new IStatus Provider test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IStatusProviderTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this IStatus Provider test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(IStatusProvider fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this IStatus Provider test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IStatusProvider getFixture() {
		return fixture;
	}

	protected TestEnvirornment env = null;

	@Override
	protected void tearDown() throws Exception {
		TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(getFixture());
		ScaTransactionalEditingDomain scaDomain = (ScaTransactionalEditingDomain) domain;
		ScaTransactionalCommandStack stack = (ScaTransactionalCommandStack) scaDomain.getCommandStack();
		Assert.assertTrue(stack.isEmpty());
		super.tearDown();
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.IStatusProvider#getStatus() <em>Status</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.IStatusProvider#getStatus()
	 * @generated NOT
	 */
	public void testGetStatus() {
		// END GENERATED CODE
		StringWriter s = new StringWriter();
		PrintWriter w = new PrintWriter(s);
		if (getFixture().getStatus().getException() != null) {
			getFixture().getStatus().getException().printStackTrace(w); // CHECKSTYLE: DEBUG CODE
		}
		String msg = getFixture().getStatus().getMessage() + ":" + getFixture().getStatus().getSeverity() + " " + s.toString();
		Assert.assertTrue(msg, getFixture().getStatus().isOK());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '
	 * {@link gov.redhawk.model.sca.IStatusProvider#setStatus(org.eclipse.emf.ecore.EStructuralFeature, org.eclipse.core.runtime.IStatus)
	 * <em>Set Status</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.IStatusProvider#setStatus(org.eclipse.emf.ecore.EStructuralFeature,
	 * org.eclipse.core.runtime.IStatus)
	 * @generated NOT
	 */
	public void testSetStatus__EStructuralFeature_IStatus() {
		// END GENERATED CODE
		Assert.assertTrue(getFixture().getStatus().isOK());
		ScaModelCommand.execute(getFixture(), new SetStatusCommand<IStatusProvider>(getFixture(), ScaPackage.Literals.ISTATUS_PROVIDER__STATUS,
			new Status(Status.ERROR, ScaModelPlugin.ID, "Test Error", null)));
		Assert.assertFalse(getFixture().getStatus().isOK());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.IStatusProvider#clearAllStatus() <em>Clear All Status</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.IStatusProvider#clearAllStatus()
	 * @generated NOT
	 */
	public void testClearAllStatus() {
		// END GENERATED CODE
		Assert.assertTrue(getFixture().getStatus().isOK());
		ScaModelCommand.execute(getFixture(), new SetStatusCommand<IStatusProvider>(getFixture(), ScaPackage.Literals.ISTATUS_PROVIDER__STATUS,
			new Status(Status.ERROR, ScaModelPlugin.ID, "Test Error", null)));
		Assert.assertFalse(getFixture().getStatus().isOK());
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().clearAllStatus();
			}
		});
		Assert.assertTrue(getFixture().getStatus().isOK());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.IStatusProvider#getStatus(org.eclipse.emf.ecore.EStructuralFeature)
	 * <em>Get Status</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.IStatusProvider#getStatus(org.eclipse.emf.ecore.EStructuralFeature)
	 * @generated NOT
	 */
	public void testGetStatus__EStructuralFeature() {
		// END GENERATED CODE
		Assert.assertTrue(getFixture().getStatus().isOK());
		// BEGIN GENERATED CODE
	}

	public void testProtected() {
		// TODO
		// try {
		// this.fixture.setStatus(ScaPackage.Literals.ISTATUS_PROVIDER__STATUS, Status.CANCEL_STATUS);
		// Assert.fail("Object not protected");
		// } catch (final IllegalStateException e) {
		// Assert.assertEquals("Cannot modify resource set without a write transaction", e.getMessage());
		// }
	}

	public void testCommandStackEmpty() {
		TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(getFixture());
		Assert.assertTrue(editingDomain.getCommandStack() instanceof ScaTransactionalCommandStack);
		Assert.assertTrue(((ScaTransactionalCommandStack) editingDomain.getCommandStack()).isEmpty());
	}

} // IStatusProviderTest

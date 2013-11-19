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
package gov.redhawk.eclipsecorba.idl.tests;

import gov.redhawk.eclipsecorba.idl.Identifiable;
import org.junit.Assert;
import junit.framework.TestCase;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Identifiable</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.Identifiable#getScopedName() <em>Scoped Name</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.Identifiable#getRepId() <em>Rep Id</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.Identifiable#getPrefix() <em>Prefix</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.Identifiable#getVersion() <em>Version</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.Identifiable#getId() <em>Id</em>}</li>
 * </ul>
 * </p>
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.idl.Identifiable#setFullId(java.lang.String) <em>Set Full Id</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public abstract class IdentifiableTest extends TestCase {

	/**
	 * The fixture for this Identifiable test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Identifiable fixture = null;

	/**
	 * Constructs a new Identifiable test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IdentifiableTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Identifiable test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(Identifiable fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Identifiable test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Identifiable getFixture() {
		return fixture;
	}

	/**
	 * Tests the '
	 * {@link gov.redhawk.eclipsecorba.idl.Identifiable#getScopedName()
	 * <em>Scoped Name</em>}' feature getter. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see gov.redhawk.eclipsecorba.idl.Identifiable#getScopedName()
	 * @generated NOT
	 */
	public void testGetScopedName() {
		final Identifiable id = getFixture();
		id.setName("Test");

		if (!isForward()) {
			Assert.assertEquals("Test", id.getScopedName());
		} else {
			Assert.assertEquals("TestForward", id.getScopedName());
		}

		final Identifiable container = addToContainer("IDL:PARENT:1.0", id);
		container.setName("PARENT");
		if (!isForward()) {
			Assert.assertEquals("PARENT/Test", id.getScopedName());
		} else {
			Assert.assertEquals("PARENT/TestForward", id.getScopedName());
		}
	}

	protected boolean isForward() {
		return false;
	}

	/**
	 * Tests the '{@link gov.redhawk.eclipsecorba.idl.Identifiable#getRepId()
	 * <em>Rep Id</em>}' feature getter. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see gov.redhawk.eclipsecorba.idl.Identifiable#getRepId()
	 * @generated NOT
	 */
	public void testGetRepId() {
		final Identifiable id = getFixture();
		id.setName("Test");
		if (!isForward()) {
			Assert.assertEquals("IDL:Test:1.0", id.getRepId());
		} else {
			Assert.assertEquals("IDL:TestForward:1.0", id.getRepId());
		}

		addToContainer("IDL:PARENT:1.0", id);
		if (!isForward()) {
			Assert.assertEquals("IDL:PARENT/Test:1.0", id.getRepId());
		} else {
			Assert.assertEquals("IDL:PARENT/TestForward:1.0", id.getRepId());
		}

		id.setPrefix("PRE");
		if (!isForward()) {
			Assert.assertEquals("IDL:PRE/PARENT/Test:1.0", id.getRepId());
		} else {
			Assert.assertEquals("IDL:PRE/PARENT/TestForward:1.0", id.getRepId());
		}

		id.setId("MY/Test");
		if (!isForward()) {
			Assert.assertEquals("IDL:MY/Test:1.0", id.getRepId());
		} else {
			Assert.assertEquals("IDL:MY/Test:1.0", id.getRepId());
		}

		id.setRepId("IDL:CF/Test:2.0");
		if (!isForward()) {
			Assert.assertEquals("IDL:CF/Test:2.0", id.getRepId());
		} else {
			Assert.assertEquals("IDL:CF/Test:2.0", id.getRepId());
		}
	}

	protected abstract Identifiable addToContainer(String repId, Identifiable id);

	/**
	 * Tests the '
	 * {@link gov.redhawk.eclipsecorba.idl.Identifiable#setRepId(java.lang.String)
	 * <em>Rep Id</em>}' feature setter. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see gov.redhawk.eclipsecorba.idl.Identifiable#setRepId(java.lang.String)
	 * @generated NOT
	 */
	public void testSetRepId() {
		final Identifiable id = getFixture();
		try {
			id.setRepId("IDL:CF/Test:1.023");
			Assert.fail("Invalid REP ID");
		} catch (final Exception e) {

		}

		try {
			id.setRepId("IDL:CF/#Test");
			Assert.fail("Invalid REP ID");
		} catch (final Exception e) {

		}

		try {
			id.setRepId("CF/Test:1.0");
			Assert.fail("Invalid REP ID");
		} catch (final Exception e) {

		}

		id.setRepId("IDL:CF/Test:2.5");
		id.setRepId("IDL:omg.omg/CF/Test:2.5");
	}

	/**
	 * Tests the '{@link gov.redhawk.eclipsecorba.idl.Identifiable#getPrefix()
	 * <em>Prefix</em>}' feature getter. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see gov.redhawk.eclipsecorba.idl.Identifiable#getPrefix()
	 * @generated NOT
	 */
	public void testGetPrefix() {
		final Identifiable id = getFixture();
		Assert.assertNull(id.getPrefix());

		id.setName("TEST");
		Assert.assertNull(id.getPrefix());

		id.setPrefix("PRE");
		Assert.assertEquals("PRE", id.getPrefix());
	}

	/**
	 * Tests the '
	 * {@link gov.redhawk.eclipsecorba.idl.Identifiable#setPrefix(java.lang.String)
	 * <em>Prefix</em>}' feature setter. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see gov.redhawk.eclipsecorba.idl.Identifiable#setPrefix(java.lang.String)
	 * @generated NOT
	 */
	public void testSetPrefix() {
		final Identifiable id = getFixture();
		id.setPrefix("PRE");
		Assert.assertEquals("PRE", id.getPrefix());

		id.setRepId("IDL:CF/Test:1.0");
		Assert.assertEquals("PRE", id.getPrefix());
		Assert.assertEquals("IDL:CF/Test:1.0", id.getRepId());

		id.setPrefix("PRE2");
		Assert.assertEquals("PRE2", id.getPrefix());
		Assert.assertEquals("IDL:CF/Test:1.0", id.getRepId());
	}

	/**
	 * Tests the '{@link gov.redhawk.eclipsecorba.idl.Identifiable#getVersion()
	 * <em>Version</em>}' feature getter. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see gov.redhawk.eclipsecorba.idl.Identifiable#getVersion()
	 * @generated NOT
	 */
	public void testGetVersion() {
		final Identifiable id = getFixture();
		id.setName("Test");
		Assert.assertEquals("1.0", id.getVersion());

		addToContainer("IDL:PARENT:2.0", id);
		Assert.assertEquals("2.0", id.getVersion());

		id.setVersion("3.0");
		Assert.assertEquals("3.0", id.getVersion());

		id.setRepId("IDL:CF/Test:5.5");
		Assert.assertEquals("5.5", id.getVersion());
	}

	/**
	 * Tests the '
	 * {@link gov.redhawk.eclipsecorba.idl.Identifiable#setVersion(java.lang.String)
	 * <em>Version</em>}' feature setter. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see gov.redhawk.eclipsecorba.idl.Identifiable#setVersion(java.lang.String)
	 * @generated NOT
	 */
	public void testSetVersion() {
		// PASS Taken care of with Get
	}

	/**
	 * Tests the '{@link gov.redhawk.eclipsecorba.idl.Identifiable#getId()
	 * <em>Id</em>}' feature getter. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see gov.redhawk.eclipsecorba.idl.Identifiable#getId()
	 * @generated NOT
	 */
	public void testGetId() {
		final Identifiable id = getFixture();
		id.setName("Test");
		if (!isForward()) {
			Assert.assertEquals("Test", id.getId());
		} else {
			Assert.assertEquals("TestForward", id.getId());
		}

		id.setPrefix("PRE");
		if (!isForward()) {
			Assert.assertEquals("PRE/Test", id.getId());
		} else {
			Assert.assertEquals("PRE/TestForward", id.getId());
		}

		id.setPrefix(null);
		addToContainer("IDL:PARENT:2.0", id);
		if (!isForward()) {
			Assert.assertEquals("PARENT/Test", id.getId());
		} else {
			Assert.assertEquals("PARENT/TestForward", id.getId());
		}

		id.setPrefix("PRE");
		addToContainer("IDL:PARENT:2.0", id);
		if (!isForward()) {
			Assert.assertEquals("PRE/PARENT/Test", id.getId());
		} else {
			Assert.assertEquals("PRE/PARENT/TestForward", id.getId());
		}

		id.setId("CF/Device");
		if (!isForward()) {
			Assert.assertEquals("CF/Device", id.getId());
		} else {
			Assert.assertEquals("CF/Device", id.getId());
		}
	}

	/**
	 * Tests the '
	 * {@link gov.redhawk.eclipsecorba.idl.Identifiable#setId(java.lang.String)
	 * <em>Id</em>}' feature setter. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see gov.redhawk.eclipsecorba.idl.Identifiable#setId(java.lang.String)
	 * @generated NOT
	 */
	public void testSetId() {
		// PASS Taken care of with get
	}

	/**
	 * Tests the '
	 * {@link gov.redhawk.eclipsecorba.idl.Identifiable#setFullId(java.lang.String)
	 * <em>Set Full Id</em>}' operation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see gov.redhawk.eclipsecorba.idl.Identifiable#setFullId(java.lang.String)
	 * @generated NOT
	 */
	public void testSetFullId__String() {

	}

} //IdentifiableTest

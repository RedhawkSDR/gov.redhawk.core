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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.junit.Assert;

import CF.DataType;
import CF.PropertiesHolder;
import CF.UnknownProperties;
import CF.PropertyEmitterPackage.AlreadyInitialized;
import CF.PropertySetPackage.InvalidConfiguration;
import CF.PropertySetPackage.PartialConfiguration;
import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaPropertyContainer;
import gov.redhawk.model.sca.commands.ScaModelCommand;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Property Container</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 * <li>{@link gov.redhawk.model.sca.ProfileObjectWrapper#getProfileURI() <em>Profile URI</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ProfileObjectWrapper#getProfileObj() <em>Profile Obj</em>}</li>
 * </ul>
 * </p>
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>{@link gov.redhawk.model.sca.ScaPropertyContainer#fetchProperties(org.eclipse.core.runtime.IProgressMonitor)
 * <em>Fetch Properties</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaPropertyContainer#getProperty(java.lang.String) <em>Get Property</em>}</li>
 * <li>{@link CF.PropertyEmitterOperations#initializeProperties(CF.DataType[]) <em>Initialize Properties</em>}</li>
 * <li>
 * {@link CF.PropertyEmitterOperations#registerPropertyListener(org.omg.CORBA.Object, org.eclipse.emf.common.util.EList, float)
 * <em>Register Property Listener</em>}</li>
 * <li>{@link CF.PropertyEmitterOperations#unregisterPropertyListener(java.lang.String) <em>Unregister Property
 * Listener</em>}</li>
 * <li>{@link CF.PropertySetOperations#configure(CF.DataType[]) <em>Configure</em>}</li>
 * <li>{@link CF.PropertySetOperations#query(CF.PropertiesHolder) <em>Query</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ProfileObjectWrapper#fetchProfileObject(org.eclipse.core.runtime.IProgressMonitor)
 * <em>Fetch Profile Object</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ProfileObjectWrapper#fetchProfileURI(org.eclipse.core.runtime.IProgressMonitor)
 * <em>Fetch Profile URI</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public abstract class ScaPropertyContainerTest extends CorbaObjWrapperTest {

	/**
	 * Constructs a new Property Container test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaPropertyContainerTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Property Container test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ScaPropertyContainer< ? , ? > getFixture() {
		return (ScaPropertyContainer< ? , ? >) fixture;
	}

	// END GENERATED CODE

	@Override
	public void testGetStatus() {
		super.testGetStatus();

		// IDE-2112 Test reporting of problems with the object's properties
		Assert.assertTrue("Resource must have at least one property for test", getFixture().getProperties().size() > 0);
		ScaModelCommand.execute(getFixture(), () -> {
			getFixture().getProperties().get(0).setStatus(ScaPackage.Literals.SCA_ABSTRACT_PROPERTY__ID,
				new Status(IStatus.ERROR, ScaModelPlugin.ID, "Property problem"));
		});
		Assert.assertFalse(getFixture().getStatus().isOK());
		Assert.assertEquals(ScaModelPlugin.ERR_BAD_PROPS, getFixture().getStatus().getCode());

		// 1 prop problem + 1 problem with object
		ScaModelCommand.execute(getFixture(), () -> {
			getFixture().setStatus(ScaPackage.Literals.CORBA_OBJ_WRAPPER__CORBA_OBJ, new Status(IStatus.ERROR, ScaModelPlugin.ID, "Object problem 1"));
		});
		Assert.assertFalse(getFixture().getStatus().isOK());
		Assert.assertEquals(ScaModelPlugin.ERR_MULTIPLE_BAD_STATUS, getFixture().getStatus().getCode());
		Assert.assertEquals(2, getFixture().getStatus().getChildren().length);
		boolean found = false;
		for (IStatus childStatus : getFixture().getStatus().getChildren()) {
			if (childStatus.getCode() == ScaModelPlugin.ERR_BAD_PROPS) {
				found = true;
				break;
			}
		}
		Assert.assertTrue("Didn't find property error", found);

		// 1 prop problem + 2 problems with object
		ScaModelCommand.execute(getFixture(), () -> {
			getFixture().setStatus(ScaPackage.Literals.CORBA_OBJ_WRAPPER__OBJ, new Status(IStatus.ERROR, ScaModelPlugin.ID, "Object problem 2"));
		});
		Assert.assertFalse(getFixture().getStatus().isOK());
		Assert.assertEquals(ScaModelPlugin.ERR_MULTIPLE_BAD_STATUS, getFixture().getStatus().getCode());
		Assert.assertEquals(3, getFixture().getStatus().getChildren().length);
		found = false;
		for (IStatus childStatus : getFixture().getStatus().getChildren()) {
			if (childStatus.getCode() == ScaModelPlugin.ERR_BAD_PROPS) {
				found = true;
				break;
			}
		}
		Assert.assertTrue("Didn't find property error", found);
	}

	// BEGIN GENERATED CODE

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#getProfileURI() <em>Profile URI</em>}' feature
	 * getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#getProfileURI()
	 * @generated NOT
	 */
	public void testGetProfileURI() {
		// END GENERATED CODE
		getFixture().fetchProfileURI(null);
		Assert.assertNotNull(getFixture().getProfileURI());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#setProfileURI(org.eclipse.emf.common.util.URI)
	 * <em>Profile URI</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#setProfileURI(org.eclipse.emf.common.util.URI)
	 * @generated NOT
	 */
	public void testSetProfileURI() {
		// END GENERATED CODE
		final URI oldURI = getFixture().getProfileURI();
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().unsetProfileURI();
				Assert.assertFalse(getFixture().isSetProfileURI());
				getFixture().setProfileURI(oldURI);
				Assert.assertTrue(getFixture().isSetProfileURI());
				getFixture().unsetProfileURI();
			}
		});

		// END GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#unsetProfileURI() <em>unsetProfileURI()</em>}'
	 * method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#unsetProfileURI()
	 * @generated NOT
	 */
	public void testUnsetProfileURI() {
		// END GENERATED CODE
		Assert.assertTrue(getFixture().isSetProfileURI());
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().unsetProfileURI();
				Assert.assertFalse(getFixture().isSetProfileURI());
			}
		});

		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#isSetProfileURI() <em>isSetProfileURI()</em>}'
	 * method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#isSetProfileURI()
	 * @generated NOT
	 */
	public void testIsSetProfileURI() {
		// END GENERATED CODE
		getFixture().fetchProfileURI(null);
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				Assert.assertTrue(getFixture().isSetProfileURI());
			}
		});

		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#getProfileObj() <em>Profile Obj</em>}' feature
	 * getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#getProfileObj()
	 * @generated NOT
	 */
	public void testGetProfileObj() {
		Assert.assertNotNull(getFixture().getProfileObj());
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#setProfileObj(java.lang.Object) <em>Profile
	 * Obj</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#setProfileObj(java.lang.Object)
	 * @generated NOT
	 */
	public void testSetProfileObj() {
		// END GENERATED CODE
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().setProfileObj(null);
				getFixture().unsetProfileObj();
			}

		});
		// END GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#unsetProfileObj() <em>unsetProfileObj()</em>}'
	 * method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#unsetProfileObj()
	 * @generated NOT
	 */
	public void testUnsetProfileObj() {
		// END GENERATED CODE
		getFixture().fetchProfileObject(null);
		Assert.assertTrue(getFixture().isSetProfileObj());
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().unsetProfileObj();
				Assert.assertFalse(getFixture().isSetProfileObj());
			}
		});
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#isSetProfileObj() <em>isSetProfileObj()</em>}'
	 * method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#isSetProfileObj()
	 * @generated NOT
	 */
	public void testIsSetProfileObj() {
		// END GENERATED CODE
		getFixture().fetchProfileObject(null);
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				Assert.assertTrue(getFixture().isSetProfileObj());
			}
		});
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '
	 * {@link gov.redhawk.model.sca.ScaAbstractComponent#fetchProperties(org.eclipse.core.runtime.IProgressMonitor)
	 * <em>Fetch Properties</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaAbstractComponent#fetchProperties(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchProperties__IProgressMonitor() {
		// END GENERATED CODE
		int size = getFixture().getProperties().size();

		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().unsetProperties();
				Assert.assertEquals(0, getFixture().getProperties().size());
			}
		});
		EList<ScaAbstractProperty< ? >> propertiesEList = getFixture().fetchProperties(null);
		Assert.assertEquals(size, getFixture().getProperties().size());
		try {
			propertiesEList.clear();
			Assert.fail("fetched Properties list should be unmodifiable");
		} catch (UnsupportedOperationException e) {
			Assert.assertTrue("fetched Properties list is unmodifiable", true);
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaPropertyContainer#getProperty(java.lang.String) <em>Get Property</em>}
	 * ' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaPropertyContainer#getProperty(java.lang.String)
	 * @generated NOT
	 */
	public void testGetProperty__String() {
		// END GENERATED CODE
		Assert.assertNull(getFixture().getProperty(null));
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link CF.PropertyEmitterOperations#initializeProperties(CF.DataType[]) <em>Initialize
	 * Properties</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws AlreadyInitialized
	 * @throws PartialConfiguration 
	 * @throws InvalidConfiguration 
	 * @see CF.PropertyEmitterOperations#initializeProperties(CF.DataType[])
	 * @generated NOT
	 */
	public void testInitializeProperties__DataType() throws AlreadyInitialized, InvalidConfiguration, PartialConfiguration {
		// END GENERATED CODE
		getFixture().initializeProperties(new DataType[0]);

		// Test w/o a CORBA object
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {
			@Override
			public void execute() {
				getFixture().unsetCorbaObj();
			}
		});
		boolean caught = false;
		try {
			getFixture().initializeProperties(new DataType[] { new DataType("bad_id_and_value", null) });
		} catch (IllegalStateException e) {
			caught = true;
		}
		Assert.assertTrue(caught);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.PropertySetOperations#configure(mil.jpeojtrs.sca.cf.DataType[])
	 * <em>Configure</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws PartialConfiguration
	 * @throws InvalidConfiguration
	 * @see mil.jpeojtrs.sca.cf.PropertySetOperations#configure(mil.jpeojtrs.sca.cf.DataType[])
	 * @generated NOT
	 */
	public void testConfigure__DataType() throws InvalidConfiguration, PartialConfiguration {
		// END GENERATED CODE
		getFixture().configure(new DataType[0]);

		// Test w/o a CORBA object
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {
			@Override
			public void execute() {
				getFixture().unsetCorbaObj();
			}
		});
		boolean caught = false;
		try {
			getFixture().configure(new DataType[] { new DataType("bad_id_and_value", null) });
		} catch (IllegalStateException e) {
			caught = true;
		}
		Assert.assertTrue(caught);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.PropertySetOperations#query(mil.jpeojtrs.sca.cf.PropertiesHolder)
	 * <em>Query</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws UnknownProperties
	 * @see mil.jpeojtrs.sca.cf.PropertySetOperations#query(mil.jpeojtrs.sca.cf.PropertiesHolder)
	 * @generated NOT
	 */
	public void testQuery__PropertiesHolder() throws UnknownProperties {
		// END GENERATED CODE
		PropertiesHolder holder = new PropertiesHolder();
		holder.value = new DataType[0];
		getFixture().query(holder);

		// Test w/o a CORBA object
		holder = new PropertiesHolder();
		holder.value = new DataType[0];
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {
			@Override
			public void execute() {
				getFixture().unsetCorbaObj();
			}
		});
		getFixture().query(holder);
		Assert.assertEquals(0, holder.value.length);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '
	 * {@link CF.PropertySetOperations#registerPropertyListener(org.omg.CORBA.Object, org.eclipse.emf.common.util.EList, float)
	 * <em>Register Property Listener</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see CF.PropertySetOperations#registerPropertyListener(org.omg.CORBA.Object, org.eclipse.emf.common.util.EList,
	 * float)
	 * @generated NOT
	 */
	public void testRegisterPropertyListener__Object_EList_float() {
		// PASS - TODO
	}

	/**
	 * Tests the '{@link CF.PropertySetOperations#unregisterPropertyListener(java.lang.String) <em>Unregister Property
	 * Listener</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see CF.PropertySetOperations#unregisterPropertyListener(java.lang.String)
	 * @generated NOT
	 */
	public void testUnregisterPropertyListener__String() {
		// PASS - TODO
	}

	/**
	 * Tests the '
	 * {@link gov.redhawk.model.sca.ProfileObjectWrapper#fetchProfileObject(org.eclipse.core.runtime.IProgressMonitor)
	 * <em>Fetch Profile Object</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InterruptedException
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#fetchProfileObject(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchProfileObject__IProgressMonitor() throws InterruptedException {
		// END GENERATED CODE
		getFixture().fetchProfileObject(null);
		Assert.assertTrue(getFixture().isSetProfileObj());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '
	 * {@link gov.redhawk.model.sca.ProfileObjectWrapper#fetchProfileURI(org.eclipse.core.runtime.IProgressMonitor)
	 * <em>Fetch Profile URI</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#fetchProfileURI(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchProfileURI__IProgressMonitor() {
		// END GENERATED CODE
		getFixture().fetchProfileURI(null);
		Assert.assertTrue(getFixture().isSetProfileURI());
		// BEGIN GENERATED CODE
	}

	public void testRefreshPropertyContainerNeverChagnes() throws InterruptedException {
		Object[] propertyArray = getFixture().getProperties().toArray();
		super.testRefresh__IProgressMonitor_RefreshDepth();
		for (Object obj : propertyArray) {
			Assert.assertNotNull(((ScaAbstractProperty< ? >) obj).eContainer());
		}
	}

} // ScaPropertyContainerTest

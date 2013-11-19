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

import gov.redhawk.model.sca.ScaWaveformFactory;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import org.junit.Assert;
import junit.textui.TestRunner;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.util.TransactionUtil;

import CF.ApplicationFactoryHelper;
import CF.DataType;
import CF.DeviceAssignmentType;
import CF.ApplicationFactoryPackage.CreateApplicationError;
import CF.ApplicationFactoryPackage.CreateApplicationInsufficientCapacityError;
import CF.ApplicationFactoryPackage.CreateApplicationRequestError;
import CF.ApplicationFactoryPackage.InvalidInitConfiguration;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Waveform Factory</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.ProfileObjectWrapper#getProfileURI() <em>Profile URI</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ProfileObjectWrapper#getProfileObj() <em>Profile Obj</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ProfileObjectWrapper#getRootFileStore() <em>Root File Store</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaWaveformFactory#getIdentifier() <em>Identifier</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaWaveformFactory#getName() <em>Name</em>}</li>
 * </ul>
 * </p>
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.ScaWaveformFactory#createWaveform(org.eclipse.core.runtime.IProgressMonitor, java.lang.String, CF.DataType[], CF.DeviceAssignmentType[]) <em>Create Waveform</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaWaveformFactory#fetchIdentifier(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Identifier</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaWaveformFactory#fetchName(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Name</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaWaveformFactory#fetchProfile(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Profile</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ProfileObjectWrapper#fetchProfileObject(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Profile Object</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ProfileObjectWrapper#fetchProfileURI(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Profile URI</em>}</li>
 *   <li>{@link CF.ApplicationFactoryOperations#create(java.lang.String, CF.DataType[], CF.DeviceAssignmentType[]) <em>Create</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class ScaWaveformFactoryTest extends CorbaObjWrapperTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ScaWaveformFactoryTest.class);
	}

	/**
	 * Constructs a new Waveform Factory test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaWaveformFactoryTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Waveform Factory test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ScaWaveformFactory getFixture() {
		return (ScaWaveformFactory)fixture;
	}

	private TestEnvirornment env;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated NOT
	 */
	@Override
	protected void setUp() throws Exception {
		this.env = TestEnvirornment.getInstance();
		setFixture(this.env.getDomMgr().getWaveformFactories().get(0));
		Assert.assertNotNull(getFixture());
		Assert.assertNotNull(TransactionUtil.getEditingDomain(getFixture()));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated NOT
	 */
	@Override
	protected void tearDown() throws Exception {
		this.env = null;
		setFixture(null);
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#getProfileURI() <em>Profile URI</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#getProfileURI()
	 * @generated NOT
	 */
	public void testGetProfileURI() {
		// END GENERATED CODE
		Assert.assertNotNull(getFixture().getProfileURI());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#setProfileURI(org.eclipse.emf.common.util.URI) <em>Profile URI</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#setProfileURI(org.eclipse.emf.common.util.URI)
	 * @generated NOT
	 */
	public void testSetProfileURI() {
		// END GENERATED CODE
		Assert.assertNotNull(getFixture().getProfileURI());
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().setProfileURI(null);
				Assert.assertNull(getFixture().getProfileURI());
			}
		});

		// END GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#unsetProfileURI() <em>unsetProfileURI()</em>}' method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#unsetProfileURI()
	 * @generated NOT
	 */
	public void testUnsetProfileURI() {
		// END GENERATED CODE
		Assert.assertNotNull(getFixture().getProfileURI());
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().unsetProfileURI();
				Assert.assertNull(getFixture().getProfileURI());
				Assert.assertNull(getFixture().getProfileObj());
			}
		});

		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#isSetProfileURI() <em>isSetProfileURI()</em>}' method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#isSetProfileURI()
	 * @generated NOT
	 */
	public void testIsSetProfileURI() {
		// END GENERATED CODE
		Assert.assertTrue(getFixture().isSetProfileURI());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#getProfile() <em>Profile</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#getProfile()
	 * @generated NOT
	 */
	public void testGetProfile() {
		// END GENERATED CODE
		Assert.assertNotNull(getFixture().getProfileObj());
		final EObject profileObject = getFixture().getProfileObj();
		Assert.assertFalse(profileObject.eIsProxy());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#setProfile(java.lang.String) <em>Profile</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#setProfile(java.lang.String)
	 * @generated NOT
	 */
	public void testSetProfile() {
		// END GENERATED CODE
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().setProfile(null);
				getFixture().setProfile("BadProfile");
			}

		});
		// END GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#getProfileObj() <em>Profile Obj</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#getProfileObj()
	 * @generated NOT
	 */
	public void testGetProfileObj() {
		// END GENERATED CODE
		Assert.assertNotNull(getFixture().getProfileObj());
		final EObject profileObject = getFixture().getProfileObj();
		Assert.assertFalse(profileObject.eIsProxy());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#setProfileObj(java.lang.Object) <em>Profile Obj</em>}' feature setter.
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
			}

		});
		// END GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#unsetProfileObj() <em>unsetProfileObj()</em>}' method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#unsetProfileObj()
	 * @generated NOT
	 */
	public void testUnsetProfileObj() {
		// END GENERATED CODE
		Assert.assertNotNull(getFixture().getProfileObj());
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().unsetProfileObj();
				Assert.assertNull(getFixture().getProfileObj());
			}
		});

		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#isSetProfileObj() <em>isSetProfileObj()</em>}' method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#isSetProfileObj()
	 * @generated NOT
	 */
	public void testIsSetProfileObj() {
		// END GENERATED CODE
		Assert.assertTrue(getFixture().isSetProfileObj());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#getRootFileStore() <em>Root File Store</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#getRootFileStore()
	 * @generated NOT
	 */
	public void testGetRootFileStore() {
		// END GENERATED CODE
		Assert.assertNotNull(getFixture().getRootFileStore());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveformFactory#getDomMgr() <em>Dom Mgr</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaWaveformFactory#getDomMgr()
	 * @generated NOT
	 */
	public void testGetDomMgr() {
		// END GENERATED CODE
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				Assert.assertNotNull(getFixture().getDomMgr());
			}
		});
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveformFactory#getIdentifier() <em>Identifier</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaWaveformFactory#getIdentifier()
	 * @generated NOT
	 */
	public void testGetIdentifier() {
		// END GENERATED CODE
		final String identifier = getFixture().getObj().identifier();
		Assert.assertEquals(identifier, getFixture().getIdentifier());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveformFactory#setIdentifier(java.lang.String) <em>Identifier</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaWaveformFactory#setIdentifier(java.lang.String)
	 * @generated NOT
	 */
	public void testSetIdentifier() {
		final String id = getFixture().getIdentifier();
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {
			
			@Override
			public void execute() {
				getFixture().setIdentifier(null);
			}
		});
		
		Assert.assertNull(getFixture().getIdentifier());
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {
			
			@Override
			public void execute() {
				getFixture().setIdentifier(id);
			}
		});

		Assert.assertEquals(id, getFixture().getIdentifier());
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveformFactory#unsetIdentifier() <em>unsetIdentifier()</em>}' method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaWaveformFactory#unsetIdentifier()
	 * @generated NOT
	 */
	public void testUnsetIdentifier() {
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {
			
			@Override
			public void execute() {
				getFixture().unsetIdentifier();
			}
		});
		
		Assert.assertFalse(getFixture().isSetIdentifier());
		Assert.assertNull(getFixture().getIdentifier());
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveformFactory#isSetIdentifier() <em>isSetIdentifier()</em>}' method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaWaveformFactory#isSetIdentifier()
	 * @generated NOT
	 */
	public void testIsSetIdentifier() {
		// END GENERATED CODE
		Assert.assertTrue(getFixture().isSetIdentifier());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveformFactory#getName() <em>Name</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaWaveformFactory#getName()
	 * @generated NOT
	 */
	public void testGetName() {
		// END GENERATED CODE
		final String name = getFixture().getObj().name();
		Assert.assertEquals(name, getFixture().getName());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveformFactory#setName(java.lang.String) <em>Name</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaWaveformFactory#setName(java.lang.String)
	 * @generated NOT
	 */
	public void testSetName() {
		final String name = getFixture().getName();
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {
			
			@Override
			public void execute() {
				getFixture().setName(null);
			}
		});
		
		Assert.assertNull(getFixture().getName());
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {
			
			@Override
			public void execute() {
				getFixture().setName(name);
			}
		});

		Assert.assertEquals(name, getFixture().getName());
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveformFactory#unsetName() <em>unsetName()</em>}' method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaWaveformFactory#unsetName()
	 * @generated NOT
	 */
	public void testUnsetName() {
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {
			
			@Override
			public void execute() {
				getFixture().unsetName();
			}
		});
		
		Assert.assertFalse(getFixture().isSetName());
		Assert.assertNull(getFixture().getName());
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveformFactory#isSetName() <em>isSetName()</em>}' method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaWaveformFactory#isSetName()
	 * @generated NOT
	 */
	public void testIsSetName() {
		// END GENERATED CODE
		Assert.assertTrue(getFixture().isSetName());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveformFactory#createWaveform(org.eclipse.core.runtime.IProgressMonitor, java.lang.String, mil.jpeojtrs.sca.cf.DataType[], mil.jpeojtrs.sca.cf.DeviceAssignmentType[]) <em>Create Waveform</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InvalidInitConfiguration 
	 * @throws CreateApplicationRequestError 
	 * @throws CreateApplicationError 
	 * @see gov.redhawk.model.sca.ScaWaveformFactory#createWaveform(org.eclipse.core.runtime.IProgressMonitor, java.lang.String, mil.jpeojtrs.sca.cf.DataType[], mil.jpeojtrs.sca.cf.DeviceAssignmentType[])
	 * @generated NOT
	 */
	public void testCreateWaveform__IProgressMonitor_String_DataType_DeviceAssignmentType() throws CreateApplicationError,
	        CreateApplicationRequestError,
	        InvalidInitConfiguration, CreateApplicationInsufficientCapacityError {
		// END GENERATED CODE
		DeviceAssignmentType[] assignment = new DeviceAssignmentType[0];
		getFixture().createWaveform(null, "", new DataType[0], assignment);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveformFactory#fetchIdentifier(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Identifier</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaWaveformFactory#fetchIdentifier(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchIdentifier__IProgressMonitor() {
		// END GENERATED CODE
		getFixture().fetchIdentifier(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveformFactory#fetchName(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Name</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaWaveformFactory#fetchName(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchName__IProgressMonitor() {
		// END GENERATED CODE
		getFixture().fetchName(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveformFactory#fetchProfile(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Profile</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaWaveformFactory#fetchProfile(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchProfile__IProgressMonitor() {
		// END GENERATED CODE
		getFixture().fetchProfile(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#fetchProfileObject(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Profile Object</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InterruptedException 
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#fetchProfileObject(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchProfileObject__IProgressMonitor() throws InterruptedException {
		// END GENERATED CODE
		getFixture().fetchProfileObject(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ProfileObjectWrapper#fetchProfileURI(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Profile URI</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ProfileObjectWrapper#fetchProfileURI(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchProfileURI__IProgressMonitor() {
		// END GENERATED CODE
		getFixture().fetchProfileURI(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.ApplicationFactoryOperations#create(java.lang.String, mil.jpeojtrs.sca.cf.DataType[], mil.jpeojtrs.sca.cf.DeviceAssignmentType[]) <em>Create</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InvalidInitConfiguration 
	 * @throws CreateApplicationRequestError 
	 * @throws CreateApplicationError 
	 * @see mil.jpeojtrs.sca.cf.ApplicationFactoryOperations#create(java.lang.String, mil.jpeojtrs.sca.cf.DataType[], mil.jpeojtrs.sca.cf.DeviceAssignmentType[])
	 * @generated NOT
	 */
	public void testCreate__String_DataType_DeviceAssignmentType() throws CreateApplicationError, CreateApplicationRequestError, InvalidInitConfiguration, CreateApplicationInsufficientCapacityError {
		// END GENERATED CODE
		getFixture().create("", new DataType[0], new DeviceAssignmentType[0]);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveformFactory#createWaveform(java.lang.String, mil.jpeojtrs.sca.cf.DataType[], mil.jpeojtrs.sca.cf.DeviceAssignmentType[]) <em>Create Waveform</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws Exception  
	 * @see gov.redhawk.model.sca.ScaWaveformFactory#createWaveform(java.lang.String, mil.jpeojtrs.sca.cf.DataType[], mil.jpeojtrs.sca.cf.DeviceAssignmentType[])
	 * @generated NOT
	 */
	public void testCreateWaveform__String_DataType_DeviceAssignmentType() throws Exception {
		// END GENERATED CODE
		Assert.assertNotNull(getFixture().createWaveform(null, "", new DataType[0], new DeviceAssignmentType[0]));
		// BEGIN GENERATED CODE
	}

	@Override
	protected String getRepId() {
		return ApplicationFactoryHelper.id();
	}

} //ScaWaveformFactoryTest

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
package gov.redhawk.model.sca.tests;

import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.tests.stubs.ScaTestConstaints;
import junit.framework.Assert;
import junit.textui.TestRunner;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.omg.PortableServer.POAPackage.ServantNotActive;

import CF.Device;
import CF.DeviceManagerHelper;
import CF.InvalidObjectReference;
import CF.PortSupplierPackage.UnknownPort;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Device Manager</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.ScaDeviceManager#getRootDevices() <em>Root Devices</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDeviceManager#getChildDevices() <em>Child Devices</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDeviceManager#getAllDevices() <em>All Devices</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDeviceManager#getIdentifier() <em>Identifier</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDeviceManager#getLabel() <em>Label</em>}</li>
 * </ul>
 * </p>
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.ScaDeviceManager#getDevice(java.lang.String) <em>Get Device</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDeviceManager#fetchDevices(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Devices</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDeviceManager#fetchFileSystem(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch File System</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDeviceManager#fetchIdentifier(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Identifier</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDeviceManager#fetchLabel(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Label</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDeviceManager#fetchServices(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Services</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDeviceManager#registerScaService(org.omg.CORBA.Object, java.lang.String) <em>Register Sca Service</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDeviceManager#getScaService(java.lang.String) <em>Get Sca Service</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDeviceManager#fetchProfile(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Profile</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaPortContainer#getScaPort(java.lang.String) <em>Get Sca Port</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaPortContainer#fetchPorts(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Ports</em>}</li>
 *   <li>{@link CF.DeviceManagerOperations#registerDevice(CF.Device) <em>Register Device</em>}</li>
 *   <li>{@link CF.DeviceManagerOperations#unregisterDevice(CF.Device) <em>Unregister Device</em>}</li>
 *   <li>{@link CF.DeviceManagerOperations#shutdown() <em>Shutdown</em>}</li>
 *   <li>{@link CF.DeviceManagerOperations#registerService(org.omg.CORBA.Object, java.lang.String) <em>Register Service</em>}</li>
 *   <li>{@link CF.DeviceManagerOperations#unregisterService(org.omg.CORBA.Object, java.lang.String) <em>Unregister Service</em>}</li>
 *   <li>{@link CF.DeviceManagerOperations#getComponentImplementationId(java.lang.String) <em>Get Component Implementation Id</em>}</li>
 *   <li>{@link CF.PortSupplierOperations#getPort(java.lang.String) <em>Get Port</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class ScaDeviceManagerTest extends ScaPropertyContainerTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ScaDeviceManagerTest.class);
	}

	/**
	 * Constructs a new Device Manager test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaDeviceManagerTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Device Manager test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ScaDeviceManager getFixture() {
		return (ScaDeviceManager)fixture;
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

		setFixture(this.env.getDomMgr().getDeviceManagers().get(0));
		Assert.assertNotNull(getFixture());
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
	 * Tests the '{@link gov.redhawk.model.sca.ScaDeviceManager#getRootDevices() <em>Root Devices</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDeviceManager#getRootDevices()
	 * @generated NOT
	 */
	public void testGetRootDevices() {
		// END GENERATED CODE
		Assert.assertNotNull(getFixture().getRootDevices());
		Assert.assertFalse(getFixture().getRootDevices().isEmpty());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDeviceManager#getChildDevices() <em>Child Devices</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDeviceManager#getChildDevices()
	 * @generated NOT
	 */
	public void testGetChildDevices() {
		// END GENERATED CODE
		Assert.assertNotNull(getFixture().getChildDevices());
		Assert.assertTrue(getFixture().getChildDevices().isEmpty());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDeviceManager#getAllDevices() <em>All Devices</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDeviceManager#getAllDevices()
	 * @generated NOT
	 */
	public void testGetAllDevices() {
		// END GENERATED CODE
		Assert.assertNotNull(getFixture().getAllDevices());
		Assert.assertFalse(getFixture().getAllDevices().isEmpty());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDeviceManager#unsetAllDevices() <em>unsetAllDevices()</em>}' method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDeviceManager#unsetAllDevices()
	 * @generated NOT
	 */
	public void testUnsetAllDevices() {
		// END GENERATED CODE
		Assert.assertTrue(getFixture().isSetAllDevices());
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {
			
			public void execute() {
				getFixture().unsetAllDevices();
				Assert.assertFalse(getFixture().isSetAllDevices());
			}
		});
		// BEGIN GENERATED CODE
	}
	
	@Override
	public void testExists() {
		Assert.assertTrue(getFixture().exists());
	}
	
	@Override
	public void testDispose() {
		// PASS
	}
	
	@Override
	public void testFetchAttributes__IProgressMonitor() throws InterruptedException {
		getFixture().fetchAttributes(null);
	}
	
	@Override
	public void testSetCorbaObj__Object() {
		// PASS
	}
	
	@Override
	public void testGetProfileObj() {
		Assert.assertNotNull(getFixture().getProfileObj());
		final EObject profileObject = (EObject) getFixture().getProfileObj();
		Assert.assertFalse(profileObject.eIsProxy());
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDeviceManager#isSetAllDevices() <em>isSetAllDevices()</em>}' method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDeviceManager#isSetAllDevices()
	 * @generated NOT
	 */
	public void testIsSetAllDevices() {
		// END GENERATED CODE
		Assert.assertTrue(getFixture().isSetAllDevices());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDeviceManager#getFileSystem() <em>File System</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDeviceManager#getFileSystem()
	 * @generated NOT
	 */
	public void testGetFileSystem() {
		// END GENERATED CODE
		Assert.assertNotNull(getFixture().getFileSystem());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDeviceManager#getDomMgr() <em>Dom Mgr</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDeviceManager#getDomMgr()
	 * @generated NOT
	 */
	public void testGetDomMgr() {
		// END GENERATED CODE
		Assert.assertNotNull(getFixture().getDomMgr());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDeviceManager#getIdentifier() <em>Identifier</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDeviceManager#getIdentifier()
	 * @generated NOT
	 */
	public void testGetIdentifier() {
		// END GENERATED CODE
		final String identifier = getFixture().getObj().identifier();
		Assert.assertEquals(identifier, getFixture().getIdentifier());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDeviceManager#isSetIdentifier() <em>isSetIdentifier()</em>}' method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDeviceManager#isSetIdentifier()
	 * @generated NOT
	 */
	public void testIsSetIdentifier() {
		// END GENERATED CODE
		Assert.assertTrue(getFixture().isSetIdentifier());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDeviceManager#getLabel() <em>Label</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDeviceManager#getLabel()
	 * @generated NOT
	 */
	public void testGetLabel() {
		// END GENERATED CODE
		final String objLabel = getFixture().getObj().label();
		Assert.assertEquals(objLabel, getFixture().getLabel());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDeviceManager#isSetLabel() <em>isSetLabel()</em>}' method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDeviceManager#isSetLabel()
	 * @generated NOT
	 */
	public void testIsSetLabel() {
		// END GENERATED CODE
		Assert.assertTrue(getFixture().isSetLabel());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDeviceManager#getDevice(java.lang.String) <em>Get Device</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDeviceManager#getDevice(java.lang.String)
	 * @generated NOT
	 */
	public void testGetDevice__String() {
		// END GENERATED CODE
		Assert.assertNotNull(getFixture().getDevice(ScaTestConstaints.DCE_GPP_DEVICE));
		Assert.assertNull(getFixture().getDevice("badPort"));
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDeviceManager#fetchDevices(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Devices</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InterruptedException 
	 * @see gov.redhawk.model.sca.ScaDeviceManager#fetchDevices(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchDevices__IProgressMonitor() throws InterruptedException {
		// END GENERATED CODE
		final int size = ScaModelCommand.runExclusive(getFixture(), new RunnableWithResult.Impl<Integer>() {

			public void run() {
				setResult(getFixture().getDevices().size());
			}

		});
		final Device[] devices = getFixture().getObj().registeredDevices();
		Assert.assertEquals(devices.length, size);
		getFixture().fetchDevices(null);
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			public void execute() {
				Assert.assertEquals(size, getFixture().getDevices().size());
				getFixture().unsetDevices();
				Assert.assertEquals(0, getFixture().getDevices().size());
			}

		});
		getFixture().fetchDevices(null);
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			public void execute() {
				Assert.assertEquals(size, getFixture().getDevices().size());
			}

		});
		// BEGIN GENERATED CODE
	}
	
	@Override
	public void testRefreshWithNullAndDispose() throws InterruptedException {
		// PASS
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDeviceManager#fetchFileSystem(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch File System</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDeviceManager#fetchFileSystem(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchFileSystem__IProgressMonitor() {
		// END GENERATED CODE
		getFixture().fetchFileSystem(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDeviceManager#fetchIdentifier(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Identifier</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDeviceManager#fetchIdentifier(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchIdentifier__IProgressMonitor() {
		// END GENERATED CODE
		getFixture().fetchIdentifier(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDeviceManager#fetchLabel(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Label</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDeviceManager#fetchLabel(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchLabel__IProgressMonitor() {
		// END GENERATED CODE
		getFixture().fetchLabel(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDeviceManager#fetchPorts(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Ports</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InterruptedException 
	 * @see gov.redhawk.model.sca.ScaDeviceManager#fetchPorts(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchPorts__IProgressMonitor() throws InterruptedException {
		// END GENERATED CODE
		getFixture().fetchPorts(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDeviceManager#fetchServices(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Services</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InterruptedException 
	 * @see gov.redhawk.model.sca.ScaDeviceManager#fetchServices(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchServices__IProgressMonitor() throws InterruptedException {
		// END GENERATED CODE
		getFixture().fetchServices(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDeviceManager#registerScaService(org.omg.CORBA.Object, java.lang.String) <em>Register Sca Service</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InvalidObjectReference 
	 * @see gov.redhawk.model.sca.ScaDeviceManager#registerScaService(org.omg.CORBA.Object, java.lang.String)
	 * @generated NOT
	 */
	public void testRegisterScaService__Object_String() {
		// END GENERATED CODE
		try {
			getFixture().registerScaService(null, "");
			Assert.fail();
		} catch (InvalidObjectReference e) {
			// PASS
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDeviceManager#getScaPort(java.lang.String) <em>Get Sca Port</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDeviceManager#getScaPort(java.lang.String)
	 * @generated NOT
	 */
	public void testGetScaPort__String() {
		// END GENERATED CODE
		Assert.assertNull(getFixture().getScaPort("port"));
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDeviceManager#getScaService(java.lang.String) <em>Get Sca Service</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDeviceManager#getScaService(java.lang.String)
	 * @generated NOT
	 */
	public void testGetScaService__String() {
		// END GENERATED CODE
		Assert.assertNull(getFixture().getScaService(""));
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDeviceManager#fetchProfile(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Profile</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDeviceManager#fetchProfile(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchProfile__IProgressMonitor() {
		// END GENERATED CODE
		getFixture().fetchProfile(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.DeviceManagerOperations#registerDevice(mil.jpeojtrs.sca.cf.Device) <em>Register Device</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InvalidObjectReference 
	 * @see mil.jpeojtrs.sca.cf.DeviceManagerOperations#registerDevice(mil.jpeojtrs.sca.cf.Device)
	 * @generated NOT
	 */
	public void testRegisterDevice__Device() throws InvalidObjectReference {
		// END GENERATED CODE
		getFixture().registerDevice(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.DeviceManagerOperations#unregisterDevice(mil.jpeojtrs.sca.cf.Device) <em>Unregister Device</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InvalidObjectReference 
	 * @see mil.jpeojtrs.sca.cf.DeviceManagerOperations#unregisterDevice(mil.jpeojtrs.sca.cf.Device)
	 * @generated NOT
	 */
	public void testUnregisterDevice__Device() throws InvalidObjectReference {
		// END GENERATED CODE
		getFixture().unregisterDevice(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.DeviceManagerOperations#shutdown() <em>Shutdown</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see mil.jpeojtrs.sca.cf.DeviceManagerOperations#shutdown()
	 * @generated NOT
	 */
	public void testShutdown() {
		// PASS
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.DeviceManagerOperations#registerService(org.omg.CORBA.Object, java.lang.String) <em>Register Service</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InvalidObjectReference 
	 * @throws Exception 
	 * @throws ServantNotActive 
	 * @see mil.jpeojtrs.sca.cf.DeviceManagerOperations#registerService(org.omg.CORBA.Object, java.lang.String)
	 * @generated NOT
	 */
	public void testRegisterService__Object_String() {
		// END GENERATED CODE
		try {
			getFixture().registerService(null, "name");
			Assert.fail();
		} catch (InvalidObjectReference e) {
			// PASS
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.DeviceManagerOperations#unregisterService(org.omg.CORBA.Object, java.lang.String) <em>Unregister Service</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InvalidObjectReference 
	 * @see mil.jpeojtrs.sca.cf.DeviceManagerOperations#unregisterService(org.omg.CORBA.Object, java.lang.String)
	 * @generated NOT
	 */
	public void testUnregisterService__Object_String() {
		// END GENERATED CODE
		try {
			getFixture().unregisterService(null, "name");
			Assert.fail();
		} catch (InvalidObjectReference e) {
			// PASS
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.DeviceManagerOperations#getComponentImplementationId(java.lang.String) <em>Get Component Implementation Id</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see mil.jpeojtrs.sca.cf.DeviceManagerOperations#getComponentImplementationId(java.lang.String)
	 * @generated NOT
	 */
	public void testGetComponentImplementationId__String() {
		// END GENERATED CODE

		getFixture().getComponentImplementationId("implID");

		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.PortSupplierOperations#getPort(java.lang.String) <em>Get Port</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws UnknownPort 
	 * @see mil.jpeojtrs.sca.cf.PortSupplierOperations#getPort(java.lang.String)
	 * @generated NOT
	 */
	public void testGetPort__String() throws UnknownPort {
		// END GENERATED CODE
		getFixture().getPort("port");
		// BEGIN GENERATED CODE
	}

	@Override
	protected String getRepId() {
		return DeviceManagerHelper.id();
	}

} //ScaDeviceManagerTest

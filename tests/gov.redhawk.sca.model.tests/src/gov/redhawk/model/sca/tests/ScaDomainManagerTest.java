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

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.tests.stubs.ScaTestConstaints;
import org.junit.Assert;
import junit.textui.TestRunner;

import org.eclipse.emf.ecore.EObject;

import CF.DeviceManager;
import CF.DomainManagerHelper;
import CF.InvalidFileName;
import CF.InvalidObjectReference;
import CF.InvalidProfile;
import CF.DomainManagerPackage.AlreadyConnected;
import CF.DomainManagerPackage.ApplicationAlreadyInstalled;
import CF.DomainManagerPackage.ApplicationInstallationError;
import CF.DomainManagerPackage.ApplicationUninstallationError;
import CF.DomainManagerPackage.DeviceManagerNotRegistered;
import CF.DomainManagerPackage.InvalidEventChannelName;
import CF.DomainManagerPackage.InvalidIdentifier;
import CF.DomainManagerPackage.NotConnected;
import CF.DomainManagerPackage.RegisterError;
import CF.DomainManagerPackage.UnregisterError;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Domain Manager</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.ScaDomainManager#getConnectionProperties() <em>Connection Properties</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDomainManager#isConnected() <em>Connected</em>}</li>
 * </ul>
 * </p>
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.ScaDomainManager#getDevice(java.lang.String) <em>Get Device</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDomainManager#connect(org.eclipse.core.runtime.IProgressMonitor) <em>Connect</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDomainManager#connect(org.eclipse.core.runtime.IProgressMonitor, gov.redhawk.model.sca.RefreshDepth) <em>Connect</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDomainManager#disconnect() <em>Disconnect</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDomainManager#fetchDeviceManagers(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Device Managers</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDomainManager#fetchWaveformFactories(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Waveform Factories</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDomainManager#fetchWaveforms(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Waveforms</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDomainManager#fetchFileManager(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch File Manager</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDomainManager#fetchIdentifier(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Identifier</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDomainManager#installScaWaveformFactory(java.lang.String) <em>Install Sca Waveform Factory</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDomainManager#uninstallScaWaveformFactory(gov.redhawk.model.sca.ScaWaveformFactory) <em>Uninstall Sca Waveform Factory</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaDomainManager#fetchProfile(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Profile</em>}</li>
 *   <li>{@link CF.DomainManagerOperations#registerDevice(CF.Device, CF.DeviceManager) <em>Register Device</em>}</li>
 *   <li>{@link CF.DomainManagerOperations#registerDeviceManager(CF.DeviceManager) <em>Register Device Manager</em>}</li>
 *   <li>{@link CF.DomainManagerOperations#unregisterDeviceManager(CF.DeviceManager) <em>Unregister Device Manager</em>}</li>
 *   <li>{@link CF.DomainManagerOperations#unregisterDevice(CF.Device) <em>Unregister Device</em>}</li>
 *   <li>{@link CF.DomainManagerOperations#installApplication(java.lang.String) <em>Install Application</em>}</li>
 *   <li>{@link CF.DomainManagerOperations#uninstallApplication(java.lang.String) <em>Uninstall Application</em>}</li>
 *   <li>{@link CF.DomainManagerOperations#registerService(org.omg.CORBA.Object, CF.DeviceManager, java.lang.String) <em>Register Service</em>}</li>
 *   <li>{@link CF.DomainManagerOperations#unregisterService(org.omg.CORBA.Object, java.lang.String) <em>Unregister Service</em>}</li>
 *   <li>{@link CF.DomainManagerOperations#registerWithEventChannel(org.omg.CORBA.Object, java.lang.String, java.lang.String) <em>Register With Event Channel</em>}</li>
 *   <li>{@link CF.DomainManagerOperations#unregisterFromEventChannel(java.lang.String, java.lang.String) <em>Unregister From Event Channel</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class ScaDomainManagerTest extends ScaPropertyContainerTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ScaDomainManagerTest.class);
	}

	/**
	 * Constructs a new Domain Manager test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaDomainManagerTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Domain Manager test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ScaDomainManager getFixture() {
		return (ScaDomainManager)fixture;
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
		setFixture(this.env.getDomMgr());
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
	
	@Override
	public void testRefreshWithNullAndDispose() throws InterruptedException {
		
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDomainManager#getFileManager() <em>File Manager</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDomainManager#getFileManager()
	 * @generated NOT
	 */
	public void testGetFileManager() {
		// END GENERATED CODE
		Assert.assertNotNull(getFixture().getFileManager());
		// TODO Disable for now	
		//		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {
		//
		//			public void execute() {
		//				EcoreUtil.delete(getFixture());
		//				Assert.assertNull(getFixture().getFileManager());
		//			}
		//
		//		});

		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDomainManager#getConnectionProperties() <em>Connection Properties</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDomainManager#getConnectionProperties()
	 * @generated NOT
	 */
	public void testGetConnectionProperties() {
		// END GENERATED CODE
		Assert.assertTrue(getFixture().getConnectionProperties().size() > 0);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDomainManager#isConnected() <em>Connected</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDomainManager#isConnected()
	 * @generated NOT
	 */
	public void testIsConnected() {
		// END GENERATED CODE
		Assert.assertTrue(getFixture().isConnected());
		// TODO Disable disconnect check for now
		//		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {
		//
		//			public void execute() {
		//				getFixture().disconnect();
		//			}
		//		});
		//		Assert.assertFalse(getFixture().isConnected());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDomainManager#getDeviceManagers() <em>Device Managers</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDomainManager#getDeviceManagers()
	 * @generated NOT
	 */
	public void testGetDeviceManagers() {
		// END GENERATED CODE
		Assert.assertNotNull(getFixture().getDeviceManagers());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDomainManager#connect(org.eclipse.core.runtime.IProgressMonitor) <em>Connect</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDomainManager#connect(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 * 
	 */
	public void testConnect__IProgressMonitor() throws Exception {
		// END GENERATED CODE
		// TODO Disabled for now
		//		getFixture().connect(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDomainManager#connect(org.eclipse.core.runtime.IProgressMonitor, gov.redhawk.model.sca.RefreshDepth) <em>Connect</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDomainManager#connect(org.eclipse.core.runtime.IProgressMonitor, gov.redhawk.model.sca.RefreshDepth)
	 * @generated NOT
	 */
	public void testConnect__IProgressMonitor_RefreshDepth() {
		// END GENERATED CODE
		// TODO Disabled for now
		//		getFixture().connect(null, RefreshDepth.SELF);

		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDomainManager#disconnect() <em>Disconnect</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDomainManager#disconnect()
	 * @generated NOT
	 */
	public void testDisconnect() {
		// END GENERATED CODE
		// TODO
		//		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {
		//
		//			public void execute() {
		//				getFixture().disconnect();
		//			}
		//		});
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDomainManager#fetchDeviceManagers(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Device Managers</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InterruptedException 
	 * @see gov.redhawk.model.sca.ScaDomainManager#fetchDeviceManagers(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchDeviceManagers__IProgressMonitor() throws InterruptedException {
		// END GENERATED CODE
		getFixture().fetchDeviceManagers(null);
		// BEGIN GENERATED CODE
	}
	
	/**
	 * @generated NOT
	 */
	public void testFetchDeviceManagers__IProgressMonitor__RefreshDepth() throws InterruptedException {
		// END GENERATED CODE
		getFixture().fetchDeviceManagers(null, null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDomainManager#fetchWaveformFactories(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Waveform Factories</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InterruptedException 
	 * @see gov.redhawk.model.sca.ScaDomainManager#fetchWaveformFactories(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchWaveformFactories__IProgressMonitor() throws InterruptedException {
		// END GENERATED CODE
		getFixture().fetchWaveformFactories(null);
		// BEGIN GENERATED CODE
	}
	
	/**
	 * @generated NOT
	 */
	public void testFetchWaveformFactories__IProgressMonitor__RefreshDepth() throws InterruptedException {
		// END GENERATED CODE
		getFixture().fetchWaveformFactories(null, null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDomainManager#fetchWaveforms(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Waveforms</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InterruptedException 
	 * @see gov.redhawk.model.sca.ScaDomainManager#fetchWaveforms(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchWaveforms__IProgressMonitor() throws InterruptedException {
		// END GENERATED CODE
		getFixture().fetchWaveforms(null);
		// BEGIN GENERATED CODE
	}
	
	/**
	 * @generated NOT
	 */
	public void testFetchWaveforms__IProgressMonitor__RefreshDepth() throws InterruptedException {
		// END GENERATED CODE
		getFixture().fetchWaveforms(null, null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDomainManager#fetchFileManager(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch File Manager</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDomainManager#fetchFileManager(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchFileManager__IProgressMonitor() {
		// END GENERATED CODE
		getFixture().fetchFileManager(null);
		// BEGIN GENERATED CODE
	}
	
	/**
	 * @generated NOT
	 */
	public void testFetchFileManager__IProgressMonitor__RefreshDepth() {
		// END GENERATED CODE
		getFixture().fetchFileManager(null, null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDomainManager#fetchIdentifier(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Identifier</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDomainManager#fetchIdentifier(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchIdentifier__IProgressMonitor() {
		// END GENERATED CODE
		getFixture().fetchIdentifier(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDomainManager#installScaWaveformFactory(java.lang.String) <em>Install Sca Waveform Factory</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws ApplicationAlreadyInstalled 
	 * @throws ApplicationInstallationError 
	 * @throws InvalidFileName 
	 * @throws InvalidProfile 
	 * @see gov.redhawk.model.sca.ScaDomainManager#installScaWaveformFactory(java.lang.String)
	 * @generated NOT
	 */
	public void testInstallScaWaveformFactory__String() throws InvalidProfile, InvalidFileName, ApplicationInstallationError, ApplicationAlreadyInstalled {
		// END GENERATED CODE
		try {
			getFixture().installScaWaveformFactory("");
			Assert.fail("Expected Invalid Profile");
		} catch (final InvalidProfile e) {
			// PASS
		}
		getFixture().installScaWaveformFactory("waveforms/FinishedExampleWaveform/ExampleWaveform.sad.xml");
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDomainManager#uninstallScaWaveformFactory(gov.redhawk.model.sca.ScaWaveformFactory) <em>Uninstall Sca Waveform Factory</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InvalidIdentifier 
	 * @throws ApplicationUninstallationError 
	 * @see gov.redhawk.model.sca.ScaDomainManager#uninstallScaWaveformFactory(gov.redhawk.model.sca.ScaWaveformFactory)
	 * @generated NOT
	 */
	public void testUninstallScaWaveformFactory__ScaWaveformFactory() throws ApplicationUninstallationError, InvalidIdentifier {
		// END GENERATED CODE
		getFixture().uninstallScaWaveformFactory(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDomainManager#fetchProfile(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Profile</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDomainManager#fetchProfile(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchProfile__IProgressMonitor() {
		// END GENERATED CODE
		getFixture().fetchProfile(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.DomainManagerOperations#registerDevice(mil.jpeojtrs.sca.cf.Device, mil.jpeojtrs.sca.cf.DeviceManager) <em>Register Device</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws RegisterError 
	 * @throws DeviceManagerNotRegistered 
	 * @throws InvalidProfile 
	 * @throws InvalidObjectReference 
	 * @see mil.jpeojtrs.sca.cf.DomainManagerOperations#registerDevice(mil.jpeojtrs.sca.cf.Device, mil.jpeojtrs.sca.cf.DeviceManager)
	 * @generated NOT
	 */
	public void testRegisterDevice__Device_DeviceManager() {
		// END GENERATED CODE
		try {
			getFixture().registerDevice(null, null);
			Assert.fail();
		} catch (InvalidObjectReference e) {
			// PASS
		} catch (InvalidProfile e) {
			// PASS
		} catch (DeviceManagerNotRegistered e) {
			// PASS
		} catch (RegisterError e) {
			// PASS
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.DomainManagerOperations#registerDeviceManager(mil.jpeojtrs.sca.cf.DeviceManager) <em>Register Device Manager</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws RegisterError 
	 * @throws InvalidProfile 
	 * @throws InvalidObjectReference 
	 * @see mil.jpeojtrs.sca.cf.DomainManagerOperations#registerDeviceManager(mil.jpeojtrs.sca.cf.DeviceManager)
	 * @generated NOT
	 */
	public void testRegisterDeviceManager__DeviceManager() throws InvalidObjectReference, InvalidProfile, RegisterError {
		// END GENERATED CODE
		getFixture().registerDeviceManager(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.DomainManagerOperations#unregisterDeviceManager(mil.jpeojtrs.sca.cf.DeviceManager) <em>Unregister Device Manager</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws UnregisterError 
	 * @throws InvalidObjectReference 
	 * @see mil.jpeojtrs.sca.cf.DomainManagerOperations#unregisterDeviceManager(mil.jpeojtrs.sca.cf.DeviceManager)
	 * @generated NOT
	 */
	public void testUnregisterDeviceManager__DeviceManager() throws InvalidObjectReference, UnregisterError {
		// END GENERATED CODE
		getFixture().unregisterDeviceManager((DeviceManager) null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.DomainManagerOperations#unregisterDevice(mil.jpeojtrs.sca.cf.Device) <em>Unregister Device</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws UnregisterError 
	 * @throws InvalidObjectReference 
	 * @see mil.jpeojtrs.sca.cf.DomainManagerOperations#unregisterDevice(mil.jpeojtrs.sca.cf.Device)
	 * @generated NOT
	 */
	public void testUnregisterDevice__Device() throws InvalidObjectReference, UnregisterError {
		// END GENERATED CODE
		getFixture().unregisterDevice(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.DomainManagerOperations#installApplication(java.lang.String) <em>Install Application</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws ApplicationAlreadyInstalled 
	 * @throws ApplicationInstallationError 
	 * @throws InvalidFileName 
	 * @throws InvalidProfile 
	 * @see mil.jpeojtrs.sca.cf.DomainManagerOperations#installApplication(java.lang.String)
	 * @generated NOT
	 */
	public void testInstallApplication__String() {
		// END GENERATED CODE
		try {
			getFixture().installApplication("");
			Assert.fail();
		} catch (InvalidProfile e) {
			// PASS
		} catch (InvalidFileName e) {
			// PASS
		} catch (ApplicationInstallationError e) {
			// PASS
		} catch (ApplicationAlreadyInstalled e) {
			// PASS
		}
		// BEGIN GENERATED CODE
	}
	
	@Override
	public void testSetCorbaObj__Object() {
		Assert.assertNotNull(getFixture().getObj());
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.DomainManagerOperations#uninstallApplication(java.lang.String) <em>Uninstall Application</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws ApplicationUninstallationError 
	 * @throws InvalidIdentifier 
	 * @see mil.jpeojtrs.sca.cf.DomainManagerOperations#uninstallApplication(java.lang.String)
	 * @generated NOT
	 */
	public void testUninstallApplication__String() throws InvalidIdentifier, ApplicationUninstallationError {
		// END GENERATED CODE
		getFixture().uninstallApplication("");
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.DomainManagerOperations#registerService(org.omg.CORBA.Object, mil.jpeojtrs.sca.cf.DeviceManager, java.lang.String) <em>Register Service</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws RegisterError 
	 * @throws DeviceManagerNotRegistered 
	 * @throws InvalidObjectReference 
	 * @see mil.jpeojtrs.sca.cf.DomainManagerOperations#registerService(org.omg.CORBA.Object, mil.jpeojtrs.sca.cf.DeviceManager, java.lang.String)
	 * @generated NOT
	 */
	public void testRegisterService__Object_DeviceManager_String() throws InvalidObjectReference, DeviceManagerNotRegistered, RegisterError {
		// END GENERATED CODE
		getFixture().registerService(null, null, "");
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.DomainManagerOperations#unregisterService(org.omg.CORBA.Object, java.lang.String) <em>Unregister Service</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws UnregisterError 
	 * @throws InvalidObjectReference 
	 * @see mil.jpeojtrs.sca.cf.DomainManagerOperations#unregisterService(org.omg.CORBA.Object, java.lang.String)
	 * @generated NOT
	 */
	public void testUnregisterService__Object_String() throws InvalidObjectReference, UnregisterError {
		// END GENERATED CODE
		getFixture().unregisterService(null, "");
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.DomainManagerOperations#registerWithEventChannel(org.omg.CORBA.Object, java.lang.String, java.lang.String) <em>Register With Event Channel</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws AlreadyConnected 
	 * @throws InvalidEventChannelName 
	 * @throws InvalidObjectReference 
	 * @see mil.jpeojtrs.sca.cf.DomainManagerOperations#registerWithEventChannel(org.omg.CORBA.Object, java.lang.String, java.lang.String)
	 * @generated NOT
	 */
	public void testRegisterWithEventChannel__Object_String_String() throws InvalidObjectReference, InvalidEventChannelName, AlreadyConnected {
		// END GENERATED CODE
		getFixture().registerWithEventChannel(null, "", "");
		// BEGIN GENERATED CODE
	}
	
	@Override
	public void testExists() {
		Assert.assertTrue(getFixture().exists());
	}
	
	@Override
	public void testDispose() {
		
	}
	
	@Override
	public void testFetchAttributes__IProgressMonitor() throws InterruptedException {
		getFixture().fetchAttributes(null);
	}
	
	@Override
	public void testGetProfileObj() {
		Assert.assertNotNull(getFixture().getProfileObj());
		final EObject profileObject = (EObject) getFixture().getProfileObj();
		Assert.assertFalse(profileObject.eIsProxy());
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.DomainManagerOperations#unregisterFromEventChannel(java.lang.String, java.lang.String) <em>Unregister From Event Channel</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws NotConnected 
	 * @throws InvalidEventChannelName 
	 * @see mil.jpeojtrs.sca.cf.DomainManagerOperations#unregisterFromEventChannel(java.lang.String, java.lang.String)
	 * @generated NOT
	 */
	public void testUnregisterFromEventChannel__String_String() throws InvalidEventChannelName, NotConnected {
		// END GENERATED CODE
		getFixture().unregisterFromEventChannel("", "");
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDomainManager#getDevice(java.lang.String) <em>Get Device</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDomainManager#getDevice(java.lang.String)
	 * @generated NOT
	 */
	public void testGetDevice__String() {
		// END GENERATED CODE
		Assert.assertNull(getFixture().getDevice(null));
		Assert.assertNotNull(getFixture().getDevice(ScaTestConstaints.DCE_GPP_DEVICE));
		// BEGIN GENERATED CODE
	}

	@Override
	protected String getRepId() {
		return DomainManagerHelper.id();
	}

} //ScaDomainManagerTest

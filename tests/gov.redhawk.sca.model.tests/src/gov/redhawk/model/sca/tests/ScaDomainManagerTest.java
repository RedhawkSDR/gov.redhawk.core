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

import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.ScaWaveformFactory;
import gov.redhawk.model.sca.tests.stubs.DomainManagerImpl;
import gov.redhawk.model.sca.tests.stubs.ScaTestConstaints;
import gov.redhawk.sca.util.OrbSession;

import java.io.File;
import java.net.URL;
import java.util.List;

import junit.textui.TestRunner;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.junit.Assert;

import CF.DeviceManager;
import CF.DomainManager;
import CF.DomainManagerHelper;
import CF.DomainManagerPOATie;
import CF.InvalidFileName;
import CF.InvalidObjectReference;
import CF.InvalidProfile;
import CF.ApplicationFactoryPackage.CreateApplicationError;
import CF.ApplicationFactoryPackage.CreateApplicationInsufficientCapacityError;
import CF.ApplicationFactoryPackage.CreateApplicationRequestError;
import CF.ApplicationFactoryPackage.InvalidInitConfiguration;
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
 * <li>{@link gov.redhawk.model.sca.ScaDomainManager#getConnectionProperties() <em>Connection Properties</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaDomainManager#isConnected() <em>Connected</em>}</li>
 * </ul>
 * </p>
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>{@link gov.redhawk.model.sca.ScaDomainManager#getDevice(java.lang.String) <em>Get Device</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaDomainManager#connect(org.eclipse.core.runtime.IProgressMonitor)
 * <em>Connect</em>}</li>
 * <li>
 * {@link gov.redhawk.model.sca.ScaDomainManager#connect(org.eclipse.core.runtime.IProgressMonitor, gov.redhawk.model.sca.RefreshDepth)
 * <em>Connect</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaDomainManager#disconnect() <em>Disconnect</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaDomainManager#fetchDeviceManagers(org.eclipse.core.runtime.IProgressMonitor)
 * <em>Fetch Device Managers</em>}</li>
 * <li>
 * {@link gov.redhawk.model.sca.ScaDomainManager#fetchDeviceManagers(org.eclipse.core.runtime.IProgressMonitor, gov.redhawk.model.sca.RefreshDepth)
 * <em>Fetch Device Managers</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaDomainManager#fetchWaveformFactories(org.eclipse.core.runtime.IProgressMonitor)
 * <em>Fetch Waveform Factories</em>}</li>
 * <li>
 * {@link gov.redhawk.model.sca.ScaDomainManager#fetchWaveformFactories(org.eclipse.core.runtime.IProgressMonitor, gov.redhawk.model.sca.RefreshDepth)
 * <em>Fetch Waveform Factories</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaDomainManager#fetchWaveforms(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch
 * Waveforms</em>}</li>
 * <li>
 * {@link gov.redhawk.model.sca.ScaDomainManager#fetchWaveforms(org.eclipse.core.runtime.IProgressMonitor, gov.redhawk.model.sca.RefreshDepth)
 * <em>Fetch Waveforms</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaDomainManager#fetchFileManager(org.eclipse.core.runtime.IProgressMonitor)
 * <em>Fetch File Manager</em>}</li>
 * <li>
 * {@link gov.redhawk.model.sca.ScaDomainManager#fetchFileManager(org.eclipse.core.runtime.IProgressMonitor, gov.redhawk.model.sca.RefreshDepth)
 * <em>Fetch File Manager</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaDomainManager#fetchIdentifier(org.eclipse.core.runtime.IProgressMonitor)
 * <em>Fetch Identifier</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaDomainManager#installScaWaveformFactory(java.lang.String) <em>Install Sca
 * Waveform Factory</em>}</li>
 * <li>
 * {@link gov.redhawk.model.sca.ScaDomainManager#uninstallScaWaveformFactory(gov.redhawk.model.sca.ScaWaveformFactory)
 * <em>Uninstall Sca Waveform Factory</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaDomainManager#fetchProfile(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch
 * Profile</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaDomainManager#fetchEventChannels(org.eclipse.core.runtime.IProgressMonitor)
 * <em>Fetch Event Channels</em>}</li>
 * <li>
 * {@link gov.redhawk.model.sca.ScaDomainManager#fetchEventChannels(org.eclipse.core.runtime.IProgressMonitor, gov.redhawk.model.sca.RefreshDepth)
 * <em>Fetch Event Channels</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaDomainManager#getLabel() <em>Get Label</em>}</li>
 * <li>{@link CF.DomainManagerOperations#registerDevice(CF.Device, CF.DeviceManager) <em>Register Device</em>}</li>
 * <li>{@link CF.DomainManagerOperations#registerDeviceManager(CF.DeviceManager) <em>Register Device Manager</em>}</li>
 * <li>{@link CF.DomainManagerOperations#unregisterDeviceManager(CF.DeviceManager) <em>Unregister Device Manager</em>}
 * </li>
 * <li>{@link CF.DomainManagerOperations#unregisterDevice(CF.Device) <em>Unregister Device</em>}</li>
 * <li>
 * {@link CF.DomainManagerOperations#createApplication(java.lang.String, java.lang.String, CF.DataType[], CF.DeviceAssignmentType[])
 * <em>Create Application</em>}</li>
 * <li>{@link CF.DomainManagerOperations#installApplication(java.lang.String) <em>Install Application</em>}</li>
 * <li>{@link CF.DomainManagerOperations#uninstallApplication(java.lang.String) <em>Uninstall Application</em>}</li>
 * <li>{@link CF.DomainManagerOperations#registerService(org.omg.CORBA.Object, CF.DeviceManager, java.lang.String)
 * <em>Register Service</em>}</li>
 * <li>{@link CF.DomainManagerOperations#unregisterService(org.omg.CORBA.Object, java.lang.String) <em>Unregister
 * Service</em>}</li>
 * <li>
 * {@link CF.DomainManagerOperations#registerWithEventChannel(org.omg.CORBA.Object, java.lang.String, java.lang.String)
 * <em>Register With Event Channel</em>}</li>
 * <li>{@link CF.DomainManagerOperations#unregisterFromEventChannel(java.lang.String, java.lang.String) <em>Unregister
 * From Event Channel</em>}</li>
 * <li>{@link CF.DomainManagerOperations#registerRemoteDomainManager(CF.DomainManager) <em>Register Remote Domain
 * Manager</em>}</li>
 * <li>{@link CF.DomainManagerOperations#unregisterRemoteDomainManager(CF.DomainManager) <em>Unregister Remote Domain
 * Manager</em>}</li>
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
		return (ScaDomainManager) fixture;
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
		// ScaModelCommand.execute(getFixture(), new ScaModelCommand() {
		//
		// public void execute() {
		// EcoreUtil.delete(getFixture());
		// Assert.assertNull(getFixture().getFileManager());
		// }
		//
		// });

		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDomainManager#getConnectionProperties() <em>Connection
	 * Properties</em>}' feature getter.
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
		// ScaModelCommand.execute(getFixture(), new ScaModelCommand() {
		//
		// public void execute() {
		// getFixture().disconnect();
		// }
		// });
		// Assert.assertFalse(getFixture().isConnected());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDomainManager#getDeviceManagers() <em>Device Managers</em>}' feature
	 * getter.
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
	 * Tests the '{@link gov.redhawk.model.sca.ScaDomainManager#connect(org.eclipse.core.runtime.IProgressMonitor)
	 * <em>Connect</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDomainManager#connect(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 * 
	 */
	public void testConnect__IProgressMonitor() throws Exception {
		// END GENERATED CODE
		// TODO Disabled for now
		// getFixture().connect(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '
	 * {@link gov.redhawk.model.sca.ScaDomainManager#connect(org.eclipse.core.runtime.IProgressMonitor, gov.redhawk.model.sca.RefreshDepth)
	 * <em>Connect</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDomainManager#connect(org.eclipse.core.runtime.IProgressMonitor,
	 * gov.redhawk.model.sca.RefreshDepth)
	 * @generated NOT
	 */
	public void testConnect__IProgressMonitor_RefreshDepth() {
		// END GENERATED CODE
		// TODO Disabled for now
		// getFixture().connect(null, RefreshDepth.SELF);

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
		// ScaModelCommand.execute(getFixture(), new ScaModelCommand() {
		//
		// public void execute() {
		// getFixture().disconnect();
		// }
		// });
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '
	 * {@link gov.redhawk.model.sca.ScaDomainManager#fetchDeviceManagers(org.eclipse.core.runtime.IProgressMonitor)
	 * <em>Fetch Device Managers</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InterruptedException
	 * @see gov.redhawk.model.sca.ScaDomainManager#fetchDeviceManagers(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchDeviceManagers__IProgressMonitor() throws InterruptedException {
		// END GENERATED CODE
		@SuppressWarnings("deprecation")
		EList<ScaDeviceManager> deviceManagersEList = getFixture().fetchDeviceManagers(null);
		try {
			deviceManagersEList.clear();
			Assert.fail("fetched Device Managers list should be unmodifiable");
		} catch (UnsupportedOperationException e) {
			Assert.assertTrue("fetched Device Managers list is unmodifiable", true);
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '
	 * {@link gov.redhawk.model.sca.ScaDomainManager#fetchDeviceManagers(org.eclipse.core.runtime.IProgressMonitor, gov.redhawk.model.sca.RefreshDepth)
	 * <em>Fetch Device Managers</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDomainManager#fetchDeviceManagers(org.eclipse.core.runtime.IProgressMonitor,
	 * gov.redhawk.model.sca.RefreshDepth)
	 * @generated NOT
	 */
	public void testFetchDeviceManagers__IProgressMonitor_RefreshDepth() {
		// END GENERATED CODE
		EList<ScaDeviceManager> deviceManagersEList = getFixture().fetchDeviceManagers(new NullProgressMonitor(), RefreshDepth.SELF);
		try {
			deviceManagersEList.clear();
			Assert.fail("fetched Device Managers list should be unmodifiable");
		} catch (UnsupportedOperationException e) {
			Assert.assertTrue("fetched Device Managers list is unmodifiable", true);
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '
	 * {@link gov.redhawk.model.sca.ScaDomainManager#fetchWaveformFactories(org.eclipse.core.runtime.IProgressMonitor)
	 * <em>Fetch Waveform Factories</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InterruptedException
	 * @see gov.redhawk.model.sca.ScaDomainManager#fetchWaveformFactories(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchWaveformFactories__IProgressMonitor() throws InterruptedException {
		// END GENERATED CODE
		@SuppressWarnings("deprecation")
		EList<ScaWaveformFactory> waveformFactoriesEList = getFixture().fetchWaveformFactories(null);
		try {
			waveformFactoriesEList.clear();
			Assert.fail("fetched Waveform Factories list should be unmodifiable");
		} catch (UnsupportedOperationException e) {
			Assert.assertTrue("fetched Waveform Factories list is unmodifiable", true);
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '
	 * {@link gov.redhawk.model.sca.ScaDomainManager#fetchWaveformFactories(org.eclipse.core.runtime.IProgressMonitor, gov.redhawk.model.sca.RefreshDepth)
	 * <em>Fetch Waveform Factories</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDomainManager#fetchWaveformFactories(org.eclipse.core.runtime.IProgressMonitor,
	 * gov.redhawk.model.sca.RefreshDepth)
	 * @generated NOT
	 */
	public void testFetchWaveformFactories__IProgressMonitor_RefreshDepth() {
		// END GENERATED CODE
		EList<ScaWaveformFactory> waveformFactoriesEList = getFixture().fetchWaveformFactories(new NullProgressMonitor(), RefreshDepth.SELF);
		try {
			waveformFactoriesEList.clear();
			Assert.fail("fetched Waveform Factories list should be unmodifiable");
		} catch (UnsupportedOperationException e) {
			Assert.assertTrue("fetched Waveform Factories list is unmodifiable", true);
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '
	 * {@link gov.redhawk.model.sca.ScaDomainManager#fetchWaveforms(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch
	 * Waveforms</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InterruptedException
	 * @see gov.redhawk.model.sca.ScaDomainManager#fetchWaveforms(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchWaveforms__IProgressMonitor() throws InterruptedException {
		// END GENERATED CODE
		@SuppressWarnings("deprecation")
		EList<ScaWaveform> waveformsEList = getFixture().fetchWaveforms(null);
		try {
			waveformsEList.clear();
			Assert.fail("fetched Waveforms list should be unmodifiable");
		} catch (UnsupportedOperationException e) {
			Assert.assertTrue("fetched Waveforms list is unmodifiable", true);
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '
	 * {@link gov.redhawk.model.sca.ScaDomainManager#fetchWaveforms(org.eclipse.core.runtime.IProgressMonitor, gov.redhawk.model.sca.RefreshDepth)
	 * <em>Fetch Waveforms</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDomainManager#fetchWaveforms(org.eclipse.core.runtime.IProgressMonitor,
	 * gov.redhawk.model.sca.RefreshDepth)
	 * @generated NOT
	 */
	public void testFetchWaveforms__IProgressMonitor_RefreshDepth() {
		// END GENERATED CODE
		EList<ScaWaveform> waveformsEList = getFixture().fetchWaveforms(new NullProgressMonitor(), RefreshDepth.SELF);
		try {
			waveformsEList.clear();
			Assert.fail("fetched Waveforms list should be unmodifiable");
		} catch (UnsupportedOperationException e) {
			Assert.assertTrue("fetched Waveforms list is unmodifiable", true);
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '
	 * {@link gov.redhawk.model.sca.ScaDomainManager#fetchFileManager(org.eclipse.core.runtime.IProgressMonitor)
	 * <em>Fetch File Manager</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDomainManager#fetchFileManager(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	@SuppressWarnings("deprecation")
	public void testFetchFileManager__IProgressMonitor() {
		// END GENERATED CODE
		getFixture().fetchFileManager(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '
	 * {@link gov.redhawk.model.sca.ScaDomainManager#fetchFileManager(org.eclipse.core.runtime.IProgressMonitor, gov.redhawk.model.sca.RefreshDepth)
	 * <em>Fetch File Manager</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDomainManager#fetchFileManager(org.eclipse.core.runtime.IProgressMonitor,
	 * gov.redhawk.model.sca.RefreshDepth)
	 * @generated NOT
	 */
	public void testFetchFileManager__IProgressMonitor_RefreshDepth() {
		// END GENERATED CODE
		getFixture().fetchFileManager(new NullProgressMonitor(), RefreshDepth.SELF);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '
	 * {@link gov.redhawk.model.sca.ScaDomainManager#fetchIdentifier(org.eclipse.core.runtime.IProgressMonitor)
	 * <em>Fetch Identifier</em>}' operation.
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
	 * Tests the '{@link gov.redhawk.model.sca.ScaDomainManager#installScaWaveformFactory(java.lang.String) <em>Install
	 * Sca Waveform Factory</em>}' operation.
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
	 * Tests the '
	 * {@link gov.redhawk.model.sca.ScaDomainManager#uninstallScaWaveformFactory(gov.redhawk.model.sca.ScaWaveformFactory)
	 * <em>Uninstall Sca Waveform Factory</em>}' operation.
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
	 * Tests the '{@link gov.redhawk.model.sca.ScaDomainManager#fetchProfile(org.eclipse.core.runtime.IProgressMonitor)
	 * <em>Fetch Profile</em>}' operation.
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
	 * Tests the '
	 * {@link gov.redhawk.model.sca.ScaDomainManager#fetchEventChannels(org.eclipse.core.runtime.IProgressMonitor)
	 * <em>Fetch Event Channels</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDomainManager#fetchEventChannels(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchEventChannels__IProgressMonitor() {
		// PASS - We don't have a naming context in the test domain manager
	}

	/**
	 * Tests the '
	 * {@link gov.redhawk.model.sca.ScaDomainManager#fetchEventChannels(org.eclipse.core.runtime.IProgressMonitor, gov.redhawk.model.sca.RefreshDepth)
	 * <em>Fetch Event Channels</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDomainManager#fetchEventChannels(org.eclipse.core.runtime.IProgressMonitor,
	 * gov.redhawk.model.sca.RefreshDepth)
	 * @generated NOT
	 */
	public void testFetchEventChannels__IProgressMonitor_RefreshDepth() {
		// PASS - We don't have a naming context in the test domain manager
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDomainManager#getLabel() <em>Get Label</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDomainManager#getLabel()
	 * @generated
	 */
	public void testGetLabel() {
		Assert.assertEquals("REDHAWK_DEV", getFixture().getLabel());
	}

	/**
	 * Tests the '
	 * {@link mil.jpeojtrs.sca.cf.DomainManagerOperations#registerDevice(mil.jpeojtrs.sca.cf.Device, mil.jpeojtrs.sca.cf.DeviceManager)
	 * <em>Register Device</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws RegisterError
	 * @throws DeviceManagerNotRegistered
	 * @throws InvalidProfile
	 * @throws InvalidObjectReference
	 * @see mil.jpeojtrs.sca.cf.DomainManagerOperations#registerDevice(mil.jpeojtrs.sca.cf.Device,
	 * mil.jpeojtrs.sca.cf.DeviceManager)
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
	 * Tests the '
	 * {@link mil.jpeojtrs.sca.cf.DomainManagerOperations#registerDeviceManager(mil.jpeojtrs.sca.cf.DeviceManager)
	 * <em>Register Device Manager</em>}' operation.
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
	 * Tests the '
	 * {@link mil.jpeojtrs.sca.cf.DomainManagerOperations#unregisterDeviceManager(mil.jpeojtrs.sca.cf.DeviceManager)
	 * <em>Unregister Device Manager</em>}' operation.
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
	 * Tests the '{@link mil.jpeojtrs.sca.cf.DomainManagerOperations#unregisterDevice(mil.jpeojtrs.sca.cf.Device)
	 * <em>Unregister Device</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws UnregisterError
	 * @throws InvalidObjectReference
	 * @see mil.jpeojtrs.sca.cf.DomainManagerOperations#unregisterDevice(mil.jpeojtrs.sca.cf.Device)
	 * @generated NOT
	 */
	public void testUnregisterDevice__Device() throws InvalidObjectReference, UnregisterError {
		// END GENERATED CODE
		try {
			getFixture().unregisterDevice(null);
			Assert.fail();
		} catch (InvalidObjectReference e) {
			// PASS
		} catch (UnregisterError e) {
			// PASS
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '
	 * {@link CF.DomainManagerOperations#createApplication(java.lang.String, java.lang.String, CF.DataType[], CF.DeviceAssignmentType[])
	 * <em>Create Application</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InvalidInitConfiguration
	 * @throws CreateApplicationInsufficientCapacityError
	 * @throws CreateApplicationRequestError
	 * @throws CreateApplicationError
	 * @throws InvalidFileName
	 * @throws InvalidProfile
	 * @throws InterruptedException
	 * @see CF.DomainManagerOperations#createApplication(java.lang.String, java.lang.String, CF.DataType[],
	 * CF.DeviceAssignmentType[])
	 * @generated
	 */
	public void testCreateApplication__String_String_DataType_DeviceAssignmentType() throws InvalidProfile, InvalidFileName, CreateApplicationError, CreateApplicationRequestError, CreateApplicationInsufficientCapacityError, InvalidInitConfiguration, InterruptedException {
		String name = "testCreateApplication";
		getFixture().createApplication("/waveforms/FinishedExampleWaveform/ExampleWaveform.sad.xml", name, new CF.DataType[0], new CF.DeviceAssignmentType[0]);
		List<ScaWaveform> waveforms = getFixture().fetchWaveforms(new NullProgressMonitor(), RefreshDepth.SELF);
		for (ScaWaveform waveform : waveforms) {
			if (name.equals(waveform.getName())) {
				return;
			}
		}
		Assert.fail("Application was not created");
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.DomainManagerOperations#installApplication(java.lang.String) <em>Install
	 * Application</em>}' operation.
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
	 * Tests the '{@link mil.jpeojtrs.sca.cf.DomainManagerOperations#uninstallApplication(java.lang.String)
	 * <em>Uninstall Application</em>}' operation.
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
	 * Tests the '
	 * {@link mil.jpeojtrs.sca.cf.DomainManagerOperations#registerService(org.omg.CORBA.Object, mil.jpeojtrs.sca.cf.DeviceManager, java.lang.String)
	 * <em>Register Service</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws RegisterError
	 * @throws DeviceManagerNotRegistered
	 * @throws InvalidObjectReference
	 * @see mil.jpeojtrs.sca.cf.DomainManagerOperations#registerService(org.omg.CORBA.Object,
	 * mil.jpeojtrs.sca.cf.DeviceManager, java.lang.String)
	 * @generated NOT
	 */
	public void testRegisterService__Object_DeviceManager_String() throws InvalidObjectReference, DeviceManagerNotRegistered, RegisterError {
		// END GENERATED CODE
		getFixture().registerService(null, null, "");
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '
	 * {@link mil.jpeojtrs.sca.cf.DomainManagerOperations#unregisterService(org.omg.CORBA.Object, java.lang.String)
	 * <em>Unregister Service</em>}' operation.
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
	 * Tests the '
	 * {@link mil.jpeojtrs.sca.cf.DomainManagerOperations#registerWithEventChannel(org.omg.CORBA.Object, java.lang.String, java.lang.String)
	 * <em>Register With Event Channel</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws AlreadyConnected
	 * @throws InvalidEventChannelName
	 * @throws InvalidObjectReference
	 * @see mil.jpeojtrs.sca.cf.DomainManagerOperations#registerWithEventChannel(org.omg.CORBA.Object, java.lang.String,
	 * java.lang.String)
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
	 * Tests the '
	 * {@link mil.jpeojtrs.sca.cf.DomainManagerOperations#unregisterFromEventChannel(java.lang.String, java.lang.String)
	 * <em>Unregister From Event Channel</em>}' operation.
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
	 * Tests the '{@link CF.DomainManagerOperations#registerRemoteDomainManager(CF.DomainManager) <em>Register Remote
	 * Domain Manager</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws Exception
	 * @see CF.DomainManagerOperations#registerRemoteDomainManager(CF.DomainManager)
	 * @generated NOT
	 */
	public void testRegisterRemoteDomainManager__DomainManager() throws Exception {
		// END GENERATED CODE

		Assert.assertEquals(0, getFixture().remoteDomainManagers().length);

		// Regsiter a new domain managaer
		URL domFileUrl = FileLocator.toFileURL(FileLocator.find(Platform.getBundle("gov.redhawk.sca.model.tests"), new Path("sdr/dom"), null));
		File domRoot = new File(domFileUrl.toURI());
		Assert.assertTrue(domRoot.exists());
		OrbSession session = TestEnvirornment.getInstance().getOrbSession();
		DomainManagerImpl domainMgrImpl = new DomainManagerImpl(domRoot, "/domain2/DomainManager.dmd.xml", null, null, session.getOrb(), session.getPOA());
		DomainManager dmdRef = DomainManagerHelper.narrow(session.getPOA().servant_to_reference(new DomainManagerPOATie(domainMgrImpl)));
		getFixture().registerRemoteDomainManager(dmdRef);
		Assert.assertEquals(1, getFixture().remoteDomainManagers().length);

		// Unregister the domain manager
		getFixture().unregisterRemoteDomainManager(dmdRef);
		Assert.assertEquals(0, getFixture().remoteDomainManagers().length);

		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link CF.DomainManagerOperations#unregisterRemoteDomainManager(CF.DomainManager) <em>Unregister
	 * Remote Domain Manager</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see CF.DomainManagerOperations#unregisterRemoteDomainManager(CF.DomainManager)
	 * @generated NOT
	 */
	public void testUnregisterRemoteDomainManager__DomainManager() {
		// PASS - This is tested in testRegisterRemoteDomainManager__DomainManager()
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDomainManager#getDevice(java.lang.String) <em>Get Device</em>}'
	 * operation.
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

} // ScaDomainManagerTest

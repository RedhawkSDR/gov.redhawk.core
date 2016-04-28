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

import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.junit.Assert;

import CF.ApplicationHelper;
import CF.DataType;
import CF.PropertiesHolder;
import CF.UnknownProperties;
import CF.ApplicationPackage.ComponentElementType;
import CF.LifeCyclePackage.InitializeError;
import CF.LifeCyclePackage.ReleaseError;
import CF.PortSupplierPackage.UnknownPort;
import CF.ResourcePackage.StartError;
import CF.ResourcePackage.StopError;
import CF.TestableObjectPackage.UnknownTest;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaSimpleSequenceProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.ScaStructSequenceProperty;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.ScaWaveformFactory;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.impl.ScaDomainManagerImpl;
import gov.redhawk.model.sca.impl.ScaWaveformImpl;
import gov.redhawk.model.sca.tests.stubs.ScaTestConstaints;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Waveform</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 * <li>{@link gov.redhawk.model.sca.ScaWaveform#getAssemblyController() <em>Assembly Controller</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaWaveform#getIdentifier() <em>Identifier</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaWaveform#getName() <em>Name</em>}</li>
 * </ul>
 * </p>
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>{@link gov.redhawk.model.sca.ScaWaveform#fetchComponents(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch
 * Components</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaWaveform#fetchIdentifier(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch
 * Identifier</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaWaveform#fetchName(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch
 * Name</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaWaveform#fetchStarted(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch
 * Started</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaWaveform#findComponent(java.lang.String) <em>Find Component</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaWaveform#getScaComponent(java.lang.String) <em>Get Sca Component</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaWaveform#fetchProfile(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch
 * Profile</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaPortContainer#getScaPort(java.lang.String) <em>Get Sca Port</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaPortContainer#fetchPorts(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch
 * Ports</em>}</li>
 * <li>{@link CF.ResourceOperations#start() <em>Start</em>}</li>
 * <li>{@link CF.ResourceOperations#stop() <em>Stop</em>}</li>
 * <li>{@link CF.PortSupplierOperations#getPort(java.lang.String) <em>Get Port</em>}</li>
 * <li>{@link CF.TestableObjectOperations#runTest(int, CF.PropertiesHolder) <em>Run Test</em>}</li>
 * <li>{@link CF.LifeCycleOperations#initialize() <em>Initialize</em>}</li>
 * <li>{@link CF.LifeCycleOperations#releaseObject() <em>Release Object</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class ScaWaveformTest extends ScaPropertyContainerTest {

	private static final String PLUGIN_ID = "gov.redhawk.sca.model.tests";

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ScaWaveformTest.class);
	}

	/**
	 * Constructs a new Waveform test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaWaveformTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Waveform test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ScaWaveform getFixture() {
		return (ScaWaveform) fixture;
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

		final ScaWaveform waveform = this.env.getDomMgr().getWaveformFactories().get(0).createWaveform(null, "testWaveform", null, null);
		waveform.refresh(null, RefreshDepth.FULL);
		this.env.validateStartState();
		setFixture(waveform);
		Assert.assertNotNull(getFixture());
		Assert.assertNotNull(getFixture().getObj());
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
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveform#getAssemblyController() <em>Assembly Controller</em>}'
	 * feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaWaveform#getAssemblyController()
	 * @generated NOT
	 */
	public void testGetAssemblyController() {
		// END GENERATED CODE
		Assert.assertNotNull(getFixture().getAssemblyController());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveform#setAssemblyController(gov.redhawk.model.sca.ScaComponent)
	 * <em>Assembly Controller</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaWaveform#setAssemblyController(gov.redhawk.model.sca.ScaComponent)
	 * @generated NOT
	 */
	public void testSetAssemblyController() {
		// END GENERATED CODE
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				Assert.assertNotNull(getFixture().getAssemblyController());
				getFixture().setAssemblyController(null);
				Assert.assertNull(getFixture().getAssemblyController());

			}
		});

		// END GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveform#unsetAssemblyController() <em>unsetAssemblyController()</em>}
	 * ' method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaWaveform#unsetAssemblyController()
	 * @generated NOT
	 */
	public void testUnsetAssemblyController() {
		// END GENERATED CODE
		Assert.assertNotNull(getFixture().getAssemblyController());
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().unsetAssemblyController();
				Assert.assertNull(getFixture().getAssemblyController());
			}
		});
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveform#isSetAssemblyController() <em>isSetAssemblyController()</em>}
	 * ' method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaWaveform#isSetAssemblyController()
	 * @generated NOT
	 */
	public void testIsSetAssemblyController() {
		// END GENERATED CODE
		Assert.assertTrue(getFixture().isSetAssemblyController());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveform#getDomMgr() <em>Dom Mgr</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaWaveform#getDomMgr()
	 * @generated NOT
	 */
	public void testGetDomMgr() {
		// END GENERATED CODE
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				Assert.assertNotNull(getFixture().getDomMgr());
				((ScaDomainManagerImpl) ScaWaveformTest.this.env.getDomMgr()).setCorbaObj(null);
				Assert.assertNull(getFixture().getDomMgr());
			}
		});
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveform#getIdentifier() <em>Identifier</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaWaveform#getIdentifier()
	 * @generated NOT
	 */
	public void testGetIdentifier() {
		// END GENERATED CODE
		final String identifier = getFixture().getObj().identifier();
		Assert.assertEquals(identifier, getFixture().getIdentifier());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveform#setIdentifier(java.lang.String) <em>Identifier</em>}' feature
	 * setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaWaveform#setIdentifier(java.lang.String)
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
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveform#unsetIdentifier() <em>unsetIdentifier()</em>}' method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaWaveform#unsetIdentifier()
	 * @generated
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
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveform#isSetIdentifier() <em>isSetIdentifier()</em>}' method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaWaveform#isSetIdentifier()
	 * @generated NOT
	 */
	public void testIsSetIdentifier() {
		// END GENERATED CODE
		Assert.assertTrue(getFixture().isSetIdentifier());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveform#getName() <em>Name</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InterruptedException
	 * @see gov.redhawk.model.sca.ScaWaveform#getName()
	 * @generated NOT
	 */
	public void testGetName() throws InterruptedException {
		// END GENERATED CODE
		final String name = getFixture().getObj().name();
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				Assert.assertEquals(name, getFixture().getName());
			}
		});
		try {
			getFixture().releaseObject();
		} catch (final ReleaseError e) {
			// PASS
		}
		getFixture().refresh(null, RefreshDepth.FULL);
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				Assert.assertNull(getFixture().getName());
			}
		});
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveform#setName(java.lang.String) <em>Name</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaWaveform#setName(java.lang.String)
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
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveform#unsetName() <em>unsetName()</em>}' method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaWaveform#unsetName()
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
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveform#isSetName() <em>isSetName()</em>}' method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaWaveform#isSetName()
	 * @generated NOT
	 */
	public void testIsSetName() {
		// END GENERATED CODE
		Assert.assertTrue(getFixture().isSetName());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveform#fetchComponents(org.eclipse.core.runtime.IProgressMonitor)
	 * <em>Fetch Components</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InterruptedException
	 * @see gov.redhawk.model.sca.ScaWaveform#fetchComponents(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchComponents__IProgressMonitor() throws InterruptedException {
		// END GENERATED CODE
		final int[] size = new int[1];
		final ComponentElementType[] components = getFixture().getObj().componentImplementations();
		final ScaComponent[] component = new ScaComponent[1];
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				size[0] = getFixture().getComponents().size();

				Assert.assertEquals(components.length, size[0]);
				if (!getFixture().getComponents().isEmpty()) {
					component[0] = getFixture().getComponents().get(0);
				}
			}
		});
		getFixture().fetchComponents(null);
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				if (!getFixture().getComponents().isEmpty()) {
					Assert.assertEquals(component[0], getFixture().getComponents().get(0));
				}
				Assert.assertEquals(size[0], getFixture().getComponents().size());
				((ScaDomainManagerImpl) ScaWaveformTest.this.env.getDomMgr()).setCorbaObj(null);
				Assert.assertTrue(getFixture().isDisposed());
				Assert.assertEquals(0, getFixture().getComponents().size());
			}
		});
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveform#fetchIdentifier(org.eclipse.core.runtime.IProgressMonitor)
	 * <em>Fetch Identifier</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaWaveform#fetchIdentifier(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchIdentifier__IProgressMonitor() {
		// END GENERATED CODE
		getFixture().fetchIdentifier(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveform#fetchName(org.eclipse.core.runtime.IProgressMonitor)
	 * <em>Fetch Name</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaWaveform#fetchName(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchName__IProgressMonitor() {
		// END GENERATED CODE
		getFixture().fetchName(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveform#fetchStarted(org.eclipse.core.runtime.IProgressMonitor)
	 * <em>Fetch Started</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaWaveform#fetchStarted(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchStarted__IProgressMonitor() {
		// END GENERATED CODE
		getFixture().fetchStarted(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveform#findComponent(java.lang.String) <em>Find Component</em>}'
	 * operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaWaveform#findComponent(java.lang.String)
	 * @generated NOT
	 */
	public void testFindComponent__String() {
		// END GENERATED CODE
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				Assert.assertNull(getFixture().findComponent(null));
				Assert.assertNotNull(getFixture().findComponent(ScaTestConstaints.DCE_BASIC_AC_INSTANTIATION));
				Assert.assertNull(getFixture().findComponent("blah"));
			}
		});
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveform#fetchPorts(org.eclipse.core.runtime.IProgressMonitor)
	 * <em>Fetch Ports</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InterruptedException
	 * @see gov.redhawk.model.sca.ScaWaveform#fetchPorts(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchPorts__IProgressMonitor() throws InterruptedException {
		// END GENERATED CODE
		EList<ScaPort< ? , ? >> portsEList = getFixture().fetchPorts(null);
		try {
			portsEList.clear();
			Assert.fail("fetched Ports list should be unmodifiable");
		} catch (UnsupportedOperationException e) {
			Assert.assertTrue("fetched Ports list is unmodifiable", true);
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveform#getScaComponent(java.lang.String) <em>Get Sca Component</em>}
	 * ' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaWaveform#getScaComponent(java.lang.String)
	 * @generated NOT
	 */
	public void testGetScaComponent__String() {
		// END GENERATED CODE
		Assert.assertNull(getFixture().getScaComponent(null));
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveform#fetchProfile(org.eclipse.core.runtime.IProgressMonitor)
	 * <em>Fetch Profile</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaWaveform#fetchProfile(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchProfile__IProgressMonitor() {
		// END GENERATED CODE
		getFixture().fetchProfile(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaWaveform#getScaPort(java.lang.String) <em>Get Sca Port</em>}'
	 * operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaWaveform#getScaPort(java.lang.String)
	 * @generated NOT
	 */
	public void testGetScaPort__String() {
		// END GENERATED CODE
		Assert.assertNull(getFixture().getScaPort(null));
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.ResourceOperations#start() <em>Start</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see mil.jpeojtrs.sca.cf.ResourceOperations#start()
	 * @generated NOT
	 */
	public void testStart() {
		// END GENERATED CODE

		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				Assert.assertFalse(getFixture().started());

			}
		});
		try {
			getFixture().start();
		} catch (final StartError e) {
			Assert.fail();
		}
		Assert.assertTrue(getFixture().started());

		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.ResourceOperations#stop() <em>Stop</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see mil.jpeojtrs.sca.cf.ResourceOperations#stop()
	 * @generated NOT
	 */
	public void testStop() {
		// END GENERATED CODE
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				((ScaWaveformImpl) getFixture()).setStarted(true);
				Assert.assertTrue(getFixture().started());
			}

		});
		try {
			getFixture().stop();
		} catch (final StopError e) {
			Assert.fail();
		}
		Assert.assertFalse(getFixture().started());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.PortSupplierOperations#getPort(java.lang.String) <em>Get Port</em>}'
	 * operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws UnknownPort
	 * @see mil.jpeojtrs.sca.cf.PortSupplierOperations#getPort(java.lang.String)
	 * @generated NOT
	 */
	public void testGetPort__String() throws UnknownPort {
		// END GENERATED CODE
		getFixture().getPort(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.TestableObjectOperations#runTest(int, mil.jpeojtrs.sca.cf.PropertiesHolder)
	 * <em>Run Test</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws UnknownProperties
	 * @throws UnknownTest
	 * @see mil.jpeojtrs.sca.cf.TestableObjectOperations#runTest(int, mil.jpeojtrs.sca.cf.PropertiesHolder)
	 * @generated NOT
	 */
	public void testRunTest__int_PropertiesHolder() throws UnknownTest, UnknownProperties {
		// END GENERATED CODE
		PropertiesHolder holder = new PropertiesHolder();
		holder.value = new DataType[0];
		getFixture().runTest(0, holder);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.LifeCycleOperations#initialize() <em>Initialize</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InitializeError
	 * @see mil.jpeojtrs.sca.cf.LifeCycleOperations#initialize()
	 * @generated NOT
	 */
	public void testInitialize() throws InitializeError {
		// END GENERATED CODE
		getFixture().initialize();
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.LifeCycleOperations#releaseObject() <em>Release Object</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws ReleaseError
	 * @see mil.jpeojtrs.sca.cf.LifeCycleOperations#releaseObject()
	 * @generated NOT
	 */
	public void testReleaseObject() throws ReleaseError {
		// END GENERATED CODE
		// PASS
		// BEGIN GENERATED CODE
	}

	public void testSameEditingDomain() {
		Assert.assertEquals(TransactionUtil.getEditingDomain(getFixture().getDomMgr()), TransactionUtil.getEditingDomain(getFixture()));
	}

	/**
	 * IDE-945
	 * Test that overridden values in the sad.xml are correctly displayed in the Launch Waveform Wizard dialog
	 */
	public void testWaveformWizardValues() throws Exception {
		final String SAD_PATH = "waveforms/PropertyOverrideTestWaveform/PropertyOverrideTestWaveform.sad.xml";

		ScaDomainManager domMgr = env.getDomMgr();
		domMgr.installApplication(SAD_PATH);

		ScaWaveform waveform = null;

		for (ScaWaveformFactory factory : domMgr.getWaveformFactories()) {
			String profile = factory.getProfile();
			if ((SAD_PATH).equals(profile.substring(1, profile.length()))) {
				waveform = this.env.getDomMgr().getWaveformFactories().get(1).createWaveform(null, "PropertyOverrideWF", null, null);
			}
		}
		Assert.assertNotNull(waveform);
		waveform.refresh(null, RefreshDepth.FULL);

		final List<String> modifiedProperties = Arrays.asList("DCE:2413dca0-ddd7-4be6-9d39-cfbae210f2fd", "DCE:e4a63886-8bb7-403d-bdd7-3bc79717f5b5",
			"DCE:5e410c54-750b-41ea-9fd4-176ce624d849", "DCE:7fb68ed6-2d60-4652-8e78-ac0974659350");
		final String overrideValue = "iHaveBeenOverridden";
		final String[] hardLimitProps = { "ext_upper_limit", "ext_lower_limit" };
		final String[] hardLimitValues = { "100.0", "-0.5" };

		EList<ScaAbstractProperty< ? >> waveformProperties = waveform.fetchProperties(new NullProgressMonitor());
		for (ScaAbstractProperty< ? > prop : waveformProperties) {
			if (modifiedProperties.contains(prop.getId())) {
				switch (prop.eClass().getClassifierID()) {
				case ScaPackage.SCA_SIMPLE_PROPERTY:
					ScaSimpleProperty simple = (ScaSimpleProperty) prop;
					Assert.assertEquals("simple value was not overridden for: " + simple.getId(), overrideValue, simple.getValue());
					break;
				case ScaPackage.SCA_SIMPLE_SEQUENCE_PROPERTY:
					ScaSimpleSequenceProperty simpleSeq = (ScaSimpleSequenceProperty) prop;
					for (Object value : simpleSeq.getValues()) {
						Assert.assertEquals("simple sequence value was not overridden for: " + simpleSeq.getId(), overrideValue, value);
					}
					break;
				case ScaPackage.SCA_STRUCT_PROPERTY:
					ScaStructProperty struct = (ScaStructProperty) prop;
					ScaSimpleProperty structSimple = struct.getSimple("DCE:e0a68e78-a562-416e-8ce1-27c9c622083c");
					Assert.assertEquals("simple value was not overridden for: " + structSimple.getId(), overrideValue, structSimple.getValue());
					break;
				case ScaPackage.SCA_STRUCT_SEQUENCE_PROPERTY:
					ScaStructSequenceProperty structSeq = (ScaStructSequenceProperty) prop;
					for (ScaStructProperty structSeqStruct : structSeq.getStructs()) {
						ScaSimpleProperty structSeqSimple = structSeqStruct.getSimple("DCE:b34d9204-46fa-43ea-9ef2-189674bfc366");
						Assert.assertEquals("simple value was not overridden for: " + structSeqSimple.getId(), overrideValue, structSeqSimple.getValue());
					}
					break;
				default:
					break;
				}
			} else if (hardLimitProps[0].equals(prop.getId())) {
				ScaSimpleProperty simple = (ScaSimpleProperty) prop;
				String simpleValue = Double.toString((Double) simple.getValue());
				Assert.assertEquals("simple value was not overridden for: " + simple.getId(), hardLimitValues[0], simpleValue);
			} else if (hardLimitProps[1].equals(prop.getId())) {
				ScaSimpleProperty simple = (ScaSimpleProperty) prop;
				String simpleValue = Double.toString((Double) simple.getValue());
				Assert.assertEquals("simple value was not overridden for: " + simple.getId(), hardLimitValues[1], simpleValue);
			}
		}
	}

	@Override
	protected String getRepId() {
		return ApplicationHelper.id();
	}

} // ScaWaveformTest

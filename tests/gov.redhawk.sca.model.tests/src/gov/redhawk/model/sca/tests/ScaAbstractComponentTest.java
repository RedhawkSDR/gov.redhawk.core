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

import gov.redhawk.model.sca.ScaAbstractComponent;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.commands.ScaModelCommandWithResult;
import gov.redhawk.model.sca.impl.ScaAbstractComponentImpl;
import junit.framework.Assert;
import CF.DataType;
import CF.PropertiesHolder;
import CF.Resource;
import CF.UnknownProperties;
import CF.LifeCyclePackage.InitializeError;
import CF.LifeCyclePackage.ReleaseError;
import CF.PortSupplierPackage.UnknownPort;
import CF.ResourcePackage.StartError;
import CF.ResourcePackage.StopError;
import CF.TestableObjectPackage.UnknownTest;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Abstract Component</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.ScaAbstractComponent#getIdentifier() <em>Identifier</em>}</li>
 * </ul>
 * </p>
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.ScaAbstractComponent#fetchIdentifier(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Identifier</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaAbstractComponent#fetchStarted(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Started</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaPortContainer#getScaPort(java.lang.String) <em>Get Sca Port</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaPortContainer#fetchPorts(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Ports</em>}</li>
 *   <li>{@link CF.ResourceOperations#start() <em>Start</em>}</li>
 *   <li>{@link CF.ResourceOperations#stop() <em>Stop</em>}</li>
 *   <li>{@link CF.PortSupplierOperations#getPort(java.lang.String) <em>Get Port</em>}</li>
 *   <li>{@link CF.TestableObjectOperations#runTest(int, CF.PropertiesHolder) <em>Run Test</em>}</li>
 *   <li>{@link CF.LifeCycleOperations#initialize() <em>Initialize</em>}</li>
 *   <li>{@link CF.LifeCycleOperations#releaseObject() <em>Release Object</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public abstract class ScaAbstractComponentTest extends ScaPropertyContainerTest {

	/**
	 * Constructs a new Abstract Component test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaAbstractComponentTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Abstract Component test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ScaAbstractComponent<?> getFixture() {
		return (ScaAbstractComponent<?>)fixture;
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaAbstractComponent#getIdentifier() <em>Identifier</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InterruptedException 
	 * @see gov.redhawk.model.sca.ScaAbstractComponent#getIdentifier()
	 * @generated NOT
	 */
	public void testGetIdentifier() throws InterruptedException {
		// END GENERATED CODE
		final Resource[] resource = new Resource[1];
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			public void execute() {
				resource[0] = getFixture().getObj();
				Assert.assertNotNull(resource);
			}
		});
		getFixture().fetchAttributes(null);
		final String identifier = resource[0].identifier();
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			public void execute() {
				Assert.assertEquals(identifier, getFixture().getIdentifier());
			}
		});
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaAbstractComponent#setIdentifier(java.lang.String) <em>Identifier</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaAbstractComponent#setIdentifier(java.lang.String)
	 * @generated NOT
	 */
	public void testSetIdentifier() {
		// END GENERATED CODE
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			public void execute() {
				getFixture().setIdentifier(null);
			}

		});
		// END GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaAbstractComponent#unsetIdentifier() <em>unsetIdentifier()</em>}' method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaAbstractComponent#unsetIdentifier()
	 * @generated NOT
	 */
	public void testUnsetIdentifier() {
		// END GENERATED CODE
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			public void execute() {
				Assert.assertTrue(getFixture().isSetIdentifier());
				getFixture().unsetIdentifier();
				Assert.assertFalse(getFixture().isSetIdentifier());
			}
		});		
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaAbstractComponent#isSetIdentifier() <em>isSetIdentifier()</em>}' method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaAbstractComponent#isSetIdentifier()
	 * @generated NOT
	 */
	public void testIsSetIdentifier() {
		// END GENERATED CODE
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			public void execute() {
				Assert.assertTrue(getFixture().isSetIdentifier());
			}
		});		
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaAbstractComponent#fetchIdentifier(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Identifier</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaAbstractComponent#fetchIdentifier(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchIdentifier__IProgressMonitor() {
		// END GENERATED CODE
		getFixture().fetchIdentifier(null);
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			public void execute() {
				Assert.assertTrue(getFixture().isSetIdentifier());
			}
		});		
		
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaAbstractComponent#fetchStarted(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Started</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaAbstractComponent#fetchStarted(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchStarted__IProgressMonitor() {
		// END GENERATED CODE
		getFixture().fetchStarted(null);
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			public void execute() {
				Assert.assertTrue(getFixture().isSetStarted());
			}
		});		
		
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaAbstractComponent#fetchPorts(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Ports</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InterruptedException 
	 * @see gov.redhawk.model.sca.ScaAbstractComponent#fetchPorts(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchPorts__IProgressMonitor() throws InterruptedException {
		// END GENERATED CODE
		final int ports = ScaModelCommandWithResult.execute(getFixture(), new ScaModelCommandWithResult<Integer>() {

			public void execute() {
				final int result = getFixture().getPorts().size();
				setResult(result);
				Assert.assertTrue(result > 0);
			}
		});
		getFixture().fetchPorts(null);
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			public void execute() {
				Assert.assertEquals(ports, getFixture().getPorts().size());
				getFixture().unsetPorts();	
				Assert.assertEquals(0, getFixture().getPorts().size());
			}
		});

		getFixture().fetchPorts(null);
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			public void execute() {
				Assert.assertEquals(ports, getFixture().getPorts().size());
			}
		});
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaAbstractComponent#getScaPort(java.lang.String) <em>Get Sca Port</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaAbstractComponent#getScaPort(java.lang.String)
	 * @generated NOT
	 */
	public void testGetScaPort__String() {
		// END GENERATED CODE
		if (!getFixture().getPorts().isEmpty()) {
			final ScaPort< ? , ? > port = getFixture().getPorts().get(0);
			Assert.assertEquals(port, getFixture().getScaPort(port.getName()));
		}
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
		final boolean started = getFixture().started();
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			public void execute() {
				Assert.assertFalse(started);
				Assert.assertNotNull(getFixture().getObj());

			}
		});
		try {
			getFixture().start();
		} catch (final StartError e) {
			fail();
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

			public void execute() {
				((ScaAbstractComponentImpl< ? >) getFixture()).setStarted(true);
				Assert.assertTrue(getFixture().started());
			}
		});
		try {
			getFixture().stop();
		} catch (final StopError e) {
			fail();
		}
		Assert.assertFalse(getFixture().started());

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
		if (!getFixture().getPorts().isEmpty()) {
			final ScaPort< ? , ? > port = getFixture().getPorts().get(0);
			Assert.assertEquals(port.getObj(), getFixture().getPort(port.getName()));
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.TestableObjectOperations#runTest(int, mil.jpeojtrs.sca.cf.PropertiesHolder) <em>Run Test</em>}' operation.
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
	public void testReleaseObject() {
		// END GENERATED CODE
		// PASS
		// BEGIN GENERATED CODE
	}

} //ScaAbstractComponentTest

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
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.tests.stubs.ScaTestConstaints;

import org.junit.Assert;

import junit.textui.TestRunner;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.util.TransactionUtil;

import CF.AggregateDevice;
import CF.DataType;
import CF.DeviceHelper;
import CF.DevicePackage.AdminType;
import CF.DevicePackage.InsufficientCapacity;
import CF.DevicePackage.InvalidCapacity;
import CF.DevicePackage.InvalidState;
import CF.DevicePackage.OperationalType;
import CF.DevicePackage.UsageType;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Device</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 * <li>{@link gov.redhawk.model.sca.ScaDevice#getAdminState() <em>Admin State</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaDevice#getLabel() <em>Label</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaDevice#getOperationalState() <em>Operational State</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaDevice#getUsageState() <em>Usage State</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaDevice#getDevMgr() <em>Dev Mgr</em>}</li>
 * </ul>
 * </p>
 * <p>
 * The following operations are tested:
 * <ul>
 * <li>{@link gov.redhawk.model.sca.ScaDevice#fetchAggregateDevices(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch
 * Aggregate Devices</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaDevice#fetchAggregateDevices(org.eclipse.core.runtime.IProgressMonitor, gov.redhawk.model.sca.RefreshDepth)
 * <em>Fetch Aggregate Devices</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaDevice#fetchAdminState(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Admin
 * State</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaDevice#fetchLabel(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch
 * Label</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaDevice#fetchOperationalState(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch
 * Operational State</em>}</li>
 * <li>{@link gov.redhawk.model.sca.ScaDevice#fetchUsageState(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Usage
 * State</em>}</li>
 * <li>{@link CF.DeviceOperations#allocateCapacity(CF.DataType[]) <em>Allocate Capacity</em>}</li>
 * <li>{@link CF.DeviceOperations#deallocateCapacity(CF.DataType[]) <em>Deallocate Capacity</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class ScaDeviceTest extends ScaAbstractComponentTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ScaDeviceTest.class);
	}

	/**
	 * Constructs a new Device test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaDeviceTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Device test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ScaDevice< ? > getFixture() {
		return (ScaDevice< ? >) fixture;
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

		ScaModelCommand.execute(this.env.getDomMgr(), new ScaModelCommand() {

			@Override
			public void execute() {
				final ScaDevice< ? > device = ScaDeviceTest.this.env.getDomMgr().getDevice(ScaTestConstaints.DCE_GPP_DEVICE);
				Assert.assertNotNull(device);
				setFixture(device);
			}

		});

		Assert.assertNotNull(getFixture());
		Assert.assertNotNull(TransactionUtil.getEditingDomain(getFixture()));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InterruptedException
	 * @generated NOT
	 */
	@Override
	public void testFetchPorts__IProgressMonitor() throws InterruptedException {
		// END GENERATED CODE
		final int initialSize = 2;
		Assert.assertEquals(initialSize, getFixture().getPorts().size());
		getFixture().fetchPorts(null);
		Assert.assertEquals(initialSize, getFixture().getPorts().size());
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().unsetPorts();
				Assert.assertEquals(0, getFixture().getPorts().size());
			}

		});
		getFixture().fetchPorts(null);
		Assert.assertEquals(initialSize, getFixture().getPorts().size());
		// BEGIN GENERATED CODE
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
	 * Tests the '{@link gov.redhawk.model.sca.ScaDevice#getLabel() <em>Label</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDevice#getLabel()
	 * @generated NOT
	 */
	public void testGetLabel() {
		// END GENERATED CODE
		final String objLabel = getFixture().getObj().label();
		Assert.assertEquals(objLabel, getFixture().getLabel());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDevice#setLabel(java.lang.String) <em>Label</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDevice#setLabel(java.lang.String)
	 * @generated NOT
	 */
	public void testSetLabel() {
		final String objLabel = getFixture().getLabel();
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().setLabel(null);
			}
		});

		Assert.assertNull(getFixture().getLabel());

		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().setLabel(objLabel);
			}
		});

		Assert.assertEquals(objLabel, getFixture().getLabel());
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDevice#unsetLabel() <em>unsetLabel()</em>}' method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDevice#unsetLabel()
	 * @generated NOT
	 */
	public void testUnsetLabel() {
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().unsetLabel();
			}
		});

		Assert.assertFalse(getFixture().isSetLabel());
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDevice#isSetLabel() <em>isSetLabel()</em>}' method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDevice#isSetLabel()
	 * @generated NOT
	 */
	public void testIsSetLabel() {
		// END GENERATED CODE
		Assert.assertTrue(getFixture().isSetLabel());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDevice#getOperationalState() <em>Operational State</em>}' feature
	 * getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDevice#getOperationalState()
	 * @generated NOT
	 */
	public void testGetOperationalState() {
		// END GENERATED CODE
		final OperationalType operationalType = getFixture().getObj().operationalState();
		Assert.assertEquals(operationalType, getFixture().getOperationalState());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDevice#setOperationalState(CF.DevicePackage.OperationalType)
	 * <em>Operational State</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDevice#setOperationalState(CF.DevicePackage.OperationalType)
	 * @generated NOT
	 */
	public void testSetOperationalState() {
		final OperationalType state = getFixture().getOperationalState();
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().setOperationalState(null);
			}
		});

		Assert.assertNull(getFixture().getOperationalState());
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().setOperationalState(state);
			}
		});

		Assert.assertEquals(state, getFixture().getOperationalState());
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDevice#unsetOperationalState() <em>unsetOperationalState()</em>}'
	 * method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDevice#unsetOperationalState()
	 * @generated NOT
	 */
	public void testUnsetOperationalState() {
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().unsetOperationalState();
			}
		});

		Assert.assertFalse(getFixture().isSetOperationalState());
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDevice#isSetOperationalState() <em>isSetOperationalState()</em>}'
	 * method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDevice#isSetOperationalState()
	 * @generated NOT
	 */
	public void testIsSetOperationalState() {
		// END GENERATED CODE
		Assert.assertTrue(getFixture().isSetOperationalState());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDevice#getUsageState() <em>Usage State</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDevice#getUsageState()
	 * @generated NOT
	 */
	public void testGetUsageState() {
		// END GENERATED CODE
		final UsageType usageState = getFixture().getObj().usageState();
		Assert.assertNotNull(getFixture().getObj());
		Assert.assertEquals(usageState, getFixture().getUsageState());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDevice#setUsageState(CF.DevicePackage.UsageType) <em>Usage State</em>}
	 * ' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDevice#setUsageState(CF.DevicePackage.UsageType)
	 * @generated NOT
	 */
	public void testSetUsageState() {
		final UsageType state = getFixture().getUsageState();
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().setUsageState(null);
			}
		});

		Assert.assertNull(getFixture().getUsageState());
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().setUsageState(state);
			}
		});

		Assert.assertEquals(state, getFixture().getUsageState());
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDevice#unsetUsageState() <em>unsetUsageState()</em>}' method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDevice#unsetUsageState()
	 * @generated NOT
	 */
	public void testUnsetUsageState() {
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().unsetUsageState();
			}
		});

		Assert.assertFalse(getFixture().isSetUsageState());
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDevice#isSetUsageState() <em>isSetUsageState()</em>}' method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDevice#isSetUsageState()
	 * @generated NOT
	 */
	public void testIsSetUsageState() {
		// END GENERATED CODE
		Assert.assertTrue(getFixture().isSetUsageState());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDevice#getDevMgr() <em>Dev Mgr</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDevice#getDevMgr()
	 * @generated NOT
	 */
	public void testGetDevMgr() {
		// END GENERATED CODE
		Assert.assertNotNull(getFixture().getDevMgr());
		Assert.assertTrue(getFixture().getDevMgr().getAllDevices().contains(getFixture()));
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDevice#getAdminState() <em>Admin State</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDevice#getAdminState()
	 * @generated NOT
	 */
	public void testGetAdminState() {
		// END GENERATED CODE
		final AdminType adminState = getFixture().getObj().adminState();
		Assert.assertEquals(adminState, getFixture().getAdminState());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDevice#setAdminState(CF.DevicePackage.AdminType) <em>Admin State</em>}
	 * ' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDevice#setAdminState(CF.DevicePackage.AdminType)
	 * @generated NOT
	 */
	public void testSetAdminState() {
		final AdminType state = getFixture().getAdminState();
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().setAdminState(null);
			}
		});

		Assert.assertNull(getFixture().getAdminState());

		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().setAdminState(state);
			}
		});

		Assert.assertEquals(state, getFixture().getAdminState());
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDevice#unsetAdminState() <em>unsetAdminState()</em>}' method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDevice#unsetAdminState()
	 * @generated NOT
	 */
	public void testUnsetAdminState() {
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().unsetAdminState();
			}
		});

		Assert.assertFalse(getFixture().isSetAdminState());
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDevice#isSetAdminState() <em>isSetAdminState()</em>}' method.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDevice#isSetAdminState()
	 * @generated NOT
	 */
	public void testIsSetAdminState() {
		// END GENERATED CODE
		Assert.assertTrue(getFixture().isSetAdminState());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '
	 * {@link gov.redhawk.model.sca.ScaDevice#fetchAggregateDevices(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch
	 * Aggregate Devices</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InterruptedException
	 * @see gov.redhawk.model.sca.ScaDevice#fetchAggregateDevices(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	@SuppressWarnings("deprecation")
	public void testFetchAggregateDevices__IProgressMonitor() throws InterruptedException {
		// END GENERATED CODE
		final int[] numChildren = new int[1];
		final AggregateDevice aggregateDevice = getFixture().getObj().compositeDevice();
		if (aggregateDevice != null) {
			numChildren[0] = aggregateDevice.devices().length;
		}
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {

				Assert.assertEquals(numChildren[0], getFixture().getChildDevices().size());
			}
		});
		EList<ScaDevice< ? >> aggregateDevicesEList = getFixture().fetchAggregateDevices(null);
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				Assert.assertEquals(numChildren[0], getFixture().getChildDevices().size());
				getFixture().unsetChildDevices();
				Assert.assertEquals(0, getFixture().getChildDevices().size());
			}
		});
		try {
			aggregateDevicesEList.clear();
			Assert.fail("fetched AggregateDevices list should be unmodifiable");
		} catch (UnsupportedOperationException e) {
			Assert.assertTrue("fetched AggregateDevices list is unmodifiable", true);
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '
	 * {@link gov.redhawk.model.sca.ScaDevice#fetchAggregateDevices(org.eclipse.core.runtime.IProgressMonitor, gov.redhawk.model.sca.RefreshDepth)
	 * <em>Fetch Aggregate Devices</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDevice#fetchAggregateDevices(org.eclipse.core.runtime.IProgressMonitor,
	 * gov.redhawk.model.sca.RefreshDepth)
	 * @generated NOT
	 */
	public void testFetchAggregateDevices__IProgressMonitor_RefreshDepth() {
		// END GENERATED CODE
		final int[] numChildren = new int[1];
		final AggregateDevice aggregateDevice = getFixture().getObj().compositeDevice();
		if (aggregateDevice != null) {
			numChildren[0] = aggregateDevice.devices().length;
		}
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {

				Assert.assertEquals(numChildren[0], getFixture().getChildDevices().size());
			}
		});
		EList<ScaDevice< ? >> aggregateDevicesEList = getFixture().fetchAggregateDevices(null, RefreshDepth.SELF);
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				Assert.assertEquals(numChildren[0], getFixture().getChildDevices().size());
				getFixture().unsetChildDevices();
				Assert.assertEquals(0, getFixture().getChildDevices().size());
			}
		});
		try {
			aggregateDevicesEList.clear();
			Assert.fail("fetched AggregateDevices list should be unmodifiable");
		} catch (UnsupportedOperationException e) {
			Assert.assertTrue("fetched AggregateDevices list is unmodifiable", true);
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDevice#fetchAdminState(org.eclipse.core.runtime.IProgressMonitor)
	 * <em>Fetch Admin State</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDevice#fetchAdminState(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchAdminState__IProgressMonitor() {
		// END GENERATED CODE
		getFixture().fetchAdminState(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDevice#fetchLabel(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch
	 * Label</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDevice#fetchLabel(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchLabel__IProgressMonitor() {
		// END GENERATED CODE
		getFixture().fetchLabel(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '
	 * {@link gov.redhawk.model.sca.ScaDevice#fetchOperationalState(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch
	 * Operational State</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDevice#fetchOperationalState(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchOperationalState__IProgressMonitor() {
		// END GENERATED CODE
		getFixture().fetchOperationalState(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDevice#fetchUsageState(org.eclipse.core.runtime.IProgressMonitor)
	 * <em>Fetch Usage State</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDevice#fetchUsageState(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchUsageState__IProgressMonitor() {
		// END GENERATED CODE
		getFixture().fetchUsageState(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaDevice#fetchProfile(org.eclipse.core.runtime.IProgressMonitor)
	 * <em>Fetch Profile</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaDevice#fetchProfile(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchProfile__IProgressMonitor() {
		// END GENERATED CODE
		getFixture().fetchProfile(null);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.DeviceOperations#allocateCapacity(mil.jpeojtrs.sca.cf.DataType[])
	 * <em>Allocate Capacity</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InvalidState
	 * @throws InvalidCapacity
	 * @see mil.jpeojtrs.sca.cf.DeviceOperations#allocateCapacity(mil.jpeojtrs.sca.cf.DataType[])
	 * @generated NOT
	 */
	public void testAllocateCapacity__DataType() throws InvalidCapacity, InvalidState, InsufficientCapacity {
		// END GENERATED CODE
		getFixture().allocateCapacity(new DataType[0]);
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link mil.jpeojtrs.sca.cf.DeviceOperations#deallocateCapacity(mil.jpeojtrs.sca.cf.DataType[])
	 * <em>Deallocate Capacity</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InvalidState
	 * @throws InvalidCapacity
	 * @see mil.jpeojtrs.sca.cf.DeviceOperations#deallocateCapacity(mil.jpeojtrs.sca.cf.DataType[])
	 * @generated NOT
	 */
	public void testDeallocateCapacity__DataType() throws InvalidCapacity, InvalidState {
		// END GENERATED CODE
		getFixture().deallocateCapacity(new DataType[0]);
		// BEGIN GENERATED CODE
	}

	@Override
	protected String getRepId() {
		return DeviceHelper.id();
	}

} // ScaDeviceTest

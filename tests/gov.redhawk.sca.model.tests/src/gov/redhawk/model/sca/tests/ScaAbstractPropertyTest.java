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

import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.impl.ScaAbstractPropertyImpl;
import mil.jpeojtrs.sca.prf.AccessType;

import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.jacorb.JacorbUtil;
import org.junit.Assert;
import org.omg.CORBA.Any;

import CF.PropertySetPackage.InvalidConfiguration;
import CF.PropertySetPackage.PartialConfiguration;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Abstract Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are tested:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.ScaAbstractProperty#isIgnoreRemoteSet() <em>Ignore Remote Set</em>}</li>
 * </ul>
 * </p>
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.ScaAbstractProperty#toAny() <em>To Any</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaAbstractProperty#fromAny(org.omg.CORBA.Any) <em>From Any</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaAbstractProperty#setRemoteValue(org.omg.CORBA.Any) <em>Set Remote Value</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaAbstractProperty#getProperty() <em>Get Property</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaAbstractProperty#isDefaultValue() <em>Is Default Value</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaAbstractProperty#restoreDefaultValue() <em>Restore Default Value</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.ScaAbstractProperty#valueEquals(org.omg.CORBA.Any) <em>Value Equals</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public abstract class ScaAbstractPropertyTest extends IStatusProviderTest {
	/**
	 * Constructs a new Abstract Property test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaAbstractPropertyTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Abstract Property test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ScaAbstractProperty<?> getFixture() {
		return (ScaAbstractProperty<?>)fixture;
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaAbstractProperty#getId() <em>Id</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaAbstractProperty#getId()
	 * @generated NOT
	 */
	public void testGetId() {
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				Assert.assertNotNull(getFixture().getId());
				((ScaAbstractPropertyImpl< ? >) getFixture()).setDefinition(null);
			}
		});
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {

				Assert.assertNull(getFixture().getId());
			}
		});
	}
	
	public void testCopy() {
		ScaAbstractProperty< ? > copy = EcoreUtil.copy(getFixture());
		Assert.assertNotNull(copy.getDefinition());
		Assert.assertNotNull(copy.getId());
		Assert.assertNotNull(copy.getName());
		Assert.assertNotNull(copy.getMode());
		Assert.assertNotNull(copy.getProperty());
		
		// Test to ensure the copied object is unprotected
		copy.setStatus(ScaPackage.Literals.SCA_ABSTRACT_PROPERTY__ID, Status.CANCEL_STATUS);
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaAbstractProperty#getName() <em>Name</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaAbstractProperty#getName()
	 * @generated NOT
	 */
	public void testGetName() {
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {

				Assert.assertNotNull(getFixture().getName());
				((ScaAbstractPropertyImpl< ? >) getFixture()).setDefinition(null);
			}
		});
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {

				Assert.assertNull(getFixture().getName());
			}
		});
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaAbstractProperty#isIgnoreRemoteSet() <em>Ignore Remote Set</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaAbstractProperty#isIgnoreRemoteSet()
	 * @generated NOT
	 */
	public void testIsIgnoreRemoteSet() {
		// END GENERATED CODE
		getFixture().isIgnoreRemoteSet();
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaAbstractProperty#setIgnoreRemoteSet(boolean) <em>Ignore Remote Set</em>}' feature setter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaAbstractProperty#setIgnoreRemoteSet(boolean)
	 * @generated NOT
	 */
	public void testSetIgnoreRemoteSet() {
		// END GENERATED CODE
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().setIgnoreRemoteSet(true);
				Assert.assertTrue(getFixture().isIgnoreRemoteSet());
				getFixture().setIgnoreRemoteSet(true);
				Assert.assertTrue(getFixture().isIgnoreRemoteSet());
				getFixture().setIgnoreRemoteSet(false);
				Assert.assertTrue(getFixture().isIgnoreRemoteSet());
				getFixture().setIgnoreRemoteSet(false);
				Assert.assertFalse(getFixture().isIgnoreRemoteSet());
			}
		});

		// END GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaAbstractProperty#toAny() <em>To Any</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaAbstractProperty#toAny()
	 * @generated NOT
	 */
	public void testToAny() {
		// END GENERATED CODE
		Assert.assertNotNull(getFixture().toAny());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaAbstractProperty#fromAny(org.omg.CORBA.Any) <em>From Any</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaAbstractProperty#fromAny(org.omg.CORBA.Any)
	 * @generated NOT
	 */
	public void testFromAny__Any() {
		// END GENERATED CODE
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().fromAny(getFixture().toAny());
			}
		});
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaAbstractProperty#setRemoteValue(org.omg.CORBA.Any) <em>Set Remote Value</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @throws InvalidConfiguration 
	 * @throws PartialConfiguration 
	 * @see gov.redhawk.model.sca.ScaAbstractProperty#setRemoteValue(org.omg.CORBA.Any)
	 * @generated NOT
	 */
	public void testSetRemoteValue__Any() throws PartialConfiguration, InvalidConfiguration {
		// END GENERATED CODE
		getFixture().setRemoteValue(getFixture().toAny());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaAbstractProperty#getProperty() <em>Get Property</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaAbstractProperty#getProperty()
	 * @generated NOT
	 */
	public void testGetProperty() {
		// END GENERATED CODE
		Assert.assertNotNull(getFixture().getProperty());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaAbstractProperty#isDefaultValue() <em>Is Default Value</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaAbstractProperty#isDefaultValue()
	 * @generated NOT
	 */
	public void testIsDefaultValue() {
		// END GENERATED CODE
		Assert.assertTrue(getFixture().isDefaultValue());
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaAbstractProperty#restoreDefaultValue() <em>Restore Default Value</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaAbstractProperty#restoreDefaultValue()
	 * @generated NOT
	 */
	public void testRestoreDefaultValue() {
		// END GENERATED CODE
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				getFixture().restoreDefaultValue();
				Assert.assertTrue(getFixture().isDefaultValue());
			}

		});
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaAbstractProperty#valueEquals(org.omg.CORBA.Any) <em>Value Equals</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaAbstractProperty#valueEquals(org.omg.CORBA.Any)
	 * @generated NOT
	 */
	public void testValueEquals__Any() {
		// END GENERATED CODE
		Any any = getFixture().toAny();
		Assert.assertTrue(getFixture().valueEquals(any));
		// BEGIN GENERATED CODE
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaAbstractProperty#getDescription() <em>Description</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaAbstractProperty#getDescription()
	 * @generated NOT
	 */
	public void testGetDescription() {
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {

				Assert.assertNotNull(getFixture().getDescription());
				((ScaAbstractPropertyImpl< ? >) getFixture()).setDefinition(null);
			}
		});
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {

				Assert.assertNull(getFixture().getDescription());
			}
		});
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaAbstractProperty#getMode() <em>Mode</em>}' feature getter.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaAbstractProperty#getMode()
	 * @generated NOT
	 */
	public void testGetMode() {
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {

				Assert.assertNotNull(getFixture().getMode());
				((ScaAbstractPropertyImpl< ? >) getFixture()).setDefinition(null);
			}
		});
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {

				Assert.assertEquals(AccessType.WRITEONLY, getFixture().getMode());
			}
		});
	}

	/**
	 * Tests the '{@link gov.redhawk.model.sca.ScaAbstractProperty#fetchAttributes(org.eclipse.core.runtime.IProgressMonitor) <em>Fetch Attributes</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.model.sca.ScaAbstractProperty#fetchAttributes(org.eclipse.core.runtime.IProgressMonitor)
	 * @generated NOT
	 */
	public void testFetchAttributes__IProgressMonitor() {
		// END GENERATED CODE
		ScaModelCommand.execute(getFixture(), new ScaModelCommand() {

			@Override
			public void execute() {
				((ScaAbstractPropertyImpl< ? >) getFixture()).setDefinition(null);
			}
		});
		// BEGIN GENERATED CODE
	}

	@Override
	public void testProtected() {
		try {
			getFixture().fromAny(null);
			Assert.fail("Object not protected");
		} catch (final IllegalStateException e) {
			Assert.assertEquals("Cannot modify resource set without a write transaction", e.getMessage());
		}
	}

	private volatile boolean testSetDefintionAfterValue_remoteSetScheduled;
	private volatile boolean testSetDefintionAfterValue_remoteSetDone;

	/**
	 * IDE-1589
	 * Setting the property definition after a property already has a value should not cause the value to be remotely
	 * set.
	 */
	public void testSetDefintionAfterValue() throws InterruptedException {
		final Object doneLock = new Object();
		IJobChangeListener listener = new JobChangeAdapter() {
			@Override
			public void scheduled(IJobChangeEvent event) {
				if ("Setting Property Value".equals(event.getJob().getName())) {
					testSetDefintionAfterValue_remoteSetScheduled = true;
				}
			}

			@Override
			public void done(IJobChangeEvent event) {
				if ("Setting Property Value".equals(event.getJob().getName())) {
					synchronized (doneLock) {
						testSetDefintionAfterValue_remoteSetDone = true;
						doneLock.notifyAll();
					}
				}
			}
		};
		Job.getJobManager().addJobChangeListener(listener);

		// Ensure setting the value triggers remote value set
		synchronized (doneLock) {
			testSetDefintionAfterValue_remoteSetDone = false;
			setNewValue();
			doneLock.wait(5000);
			Assert.assertTrue(testSetDefintionAfterValue_remoteSetDone);
		}

		// Ensure changing the definition does NOT trigger remote value set
		testSetDefintionAfterValue_remoteSetScheduled = false;
		clearAndResetDefintion();
		Assert.assertFalse(testSetDefintionAfterValue_remoteSetScheduled);

		Job.getJobManager().removeJobChangeListener(listener);
	}

	/**
	 * This should call <code>setValue()</code> on the property (or some part of it if a struct / struct seq)
	 */
	protected abstract void setNewValue();

	/**
	 * This should use {@link ScaAbstractProperty#getDefinition()} and
	 * {@link ScaAbstractProperty#setDefinition(mil.jpeojtrs.sca.prf.AbstractProperty)} to set the definition to null,
	 * and then to set it back to its original value.
	 */
	protected abstract void clearAndResetDefintion();

} //ScaAbstractPropertyTest

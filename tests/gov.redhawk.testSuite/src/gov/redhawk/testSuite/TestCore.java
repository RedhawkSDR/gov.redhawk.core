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
package gov.redhawk.testSuite;

import gov.redhawk.eclipsecorba.idl.tests.IdlAllTests;
import gov.redhawk.eclipsecorba.library.tests.LibraryAllTests;
import gov.redhawk.model.sca.tests.ScaAllTests;
import gov.redhawk.partitioning.validation.tests.AllSadValidationTests;
import gov.redhawk.prf.validation.tests.AllPrfValidationTests;
import gov.redhawk.sca.efs.tests.AllScaEfsTests;
import gov.redhawk.scd.validation.tests.ScdValidationTestSuite;
import gov.redhawk.validation.tests.AllValidationTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
//import gov.redhawk.ide.pydev.tests.PydevTestSuite;


/**
 * This test-suite runs all tests that will pass when run by
 * the developer from within the IDE.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	IdlAllTests.class,
	LibraryAllTests.class,
	AllPrfValidationTests.class,
	AllSadValidationTests.class,
	AllScaEfsTests.class,
	AllValidationTests.class,
	ScaAllTests.class,
	ScdValidationTestSuite.class,
	GeneralPluginTests.class
})
public class TestCore {

}

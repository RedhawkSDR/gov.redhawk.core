package gov.redhawk.sca.tests;

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.sca.ScaPlugin;

import org.junit.Test;


public class SimpleDomainTest {

	@Test
	public void testSimpleDomain() {
		ScaDomainManager domain = ScaPlugin.getDefault().createTransientDomain("test", null);
		domain.getEnabledDataProviders();
	}
}

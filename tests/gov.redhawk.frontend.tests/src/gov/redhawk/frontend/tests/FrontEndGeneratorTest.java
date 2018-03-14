package gov.redhawk.frontend.tests;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.junit.Assert;
import org.junit.Test;

import gov.redhawk.frontend.util.TunerProperties;
import mil.jpeojtrs.sca.prf.Struct;
import mil.jpeojtrs.sca.prf.util.PrfValidator;

public class FrontEndGeneratorTest {

	/**
	 * IDE-1667 - frontend_tuner_allocation should not be created with partially configured struct
	 */
	@Test
	public void testGeneratedFeiDevicePropConfig() {
		Struct[] structs = {
			TunerProperties.TunerAllocationProperty.INSTANCE.createProperty(),
			TunerProperties.ListenerAllocationProperty.INSTANCE.createProperty()
		};
		
		for (Struct struct : structs) {
			BasicDiagnostic diagnostics = new BasicDiagnostic();
			PrfValidator.INSTANCE.validateStruct(struct, diagnostics, null);
			if (!diagnostics.getChildren().isEmpty()) {
				Assert.fail("Generated struct <" + struct.getId() + "> should not have configuration errors/warnings");
			}
		}
	}
}

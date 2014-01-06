/**
 * 
 */
package gov.redhawk.frontend.internal;

import gov.redhawk.frontend.Tuner;

import org.eclipse.core.expressions.PropertyTester;

/**
 *
 */
public class AllocationPropertyTester extends PropertyTester {

	@Override
	public boolean test(Object receiver, String property, Object[] args,
			Object expectedValue) {
		Tuner theTuner = (Tuner)receiver;
		if ("hasAllocationID".equals(property)) {
			String allocationID = theTuner.getAllocationID();
			if (!(allocationID == null || "".equals(allocationID))) {
				return true;
			}
		}
		return false;
	}

}

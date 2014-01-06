/**
 * 
 */
package gov.redhawk.frontend.internal;

import gov.redhawk.frontend.TunerContainer;
import gov.redhawk.model.sca.ScaStructProperty;

import org.eclipse.core.expressions.PropertyTester;

/**
 *
 */
public class TunerContainerPropertyTester extends PropertyTester {

	/**
	 * 
	 */
	public TunerContainerPropertyTester() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object, java.lang.String, java.lang.Object[], java.lang.Object)
	 */
	@Override
	public boolean test(Object receiver, String property, Object[] args,
			Object expectedValue) {
		TunerContainer container = (TunerContainer) receiver;
		if ("hasUnallocatedTuners".equals(property)) {
			for (ScaStructProperty tuner: container.getTuners()) {
				String allocationID = tuner.getSimple("FRONTEND::tuner_status::allocation_id_csv").toString();
				if (allocationID == null || "".equals(allocationID)) {
					return true;
				}
			}
		}
		if ("hasAllocatedTuners".equals(property)) {
			for (ScaStructProperty tuner: container.getTuners()) {
				String allocationID = tuner.getSimple("FRONTEND::tuner_status::allocation_id_csv").toString();
				if (!(allocationID == null || "".equals(allocationID))) {
					return true;
				}
			}
		}
		return false;
	}

}

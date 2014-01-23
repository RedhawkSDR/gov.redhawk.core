package gov.redhawk.frontend.ui.internal;

import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaSimpleProperty;

import org.eclipse.core.expressions.PropertyTester;

public class FrontendTunerDeviceKindPropertytester extends PropertyTester {

	private static final String PROP_DEVICE_KIND_IS_FRONTEND_TUNER = "deviceKindIsFrontendTuner";
	private static final String PROP_ID_DEVICE_KIND = "DCE:cdc5ee18-7ceb-4ae6-bf4c-31f983179b4d";
	private static final String FRONTEND_TUNER = "FRONTEND::TUNER";


	@Override
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		if (PROP_DEVICE_KIND_IS_FRONTEND_TUNER.equals(property)) {
			if (receiver instanceof ScaDevice) {
				ScaDevice<?> device = (ScaDevice<?>) receiver;
				ScaSimpleProperty simple = (ScaSimpleProperty) device.getProperty(PROP_ID_DEVICE_KIND);
				if (simple != null) {
					return FRONTEND_TUNER.equals(simple.getValue());
				}
			}
		}
		return false;
	}

}

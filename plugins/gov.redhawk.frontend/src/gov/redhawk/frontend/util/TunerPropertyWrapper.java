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
package gov.redhawk.frontend.util;

import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.util.TunerProperties.TunerStatusAllocationProperties;
import gov.redhawk.model.sca.ScaSimpleProperty;

public class TunerPropertyWrapper {

	private TunerStatus tuner;
	private ScaSimpleProperty simple;

	public TunerPropertyWrapper(TunerStatus tuner, ScaSimpleProperty simple) {
		this.tuner = tuner;
		this.simple = simple;
	}

	public String getName() {
		TunerStatusAllocationProperties prop = TunerStatusAllocationProperties.fromPropID(simple.getId());
		if (prop != null) {
			return prop.getName();
		}
		if (simple.getName() != null) {
			return simple.getName();
		} 
		return simple.getId();
	}

	public String getId() {
		return simple.getId();
	}

	public Object getValue() {
		return simple.getValue();
	}

	public TunerStatus getTuner() {
		return tuner;
	}

	public ScaSimpleProperty getSimple() {
		return simple;
	}
}

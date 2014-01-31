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
package gov.redhawk.frontend.edit.utils;

import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.model.sca.ScaSimpleProperty;

public class TunerPropertyWrapper {

	private TunerStatus tuner;
	private ScaSimpleProperty simple;
	private String name;
	private String id;
	private Object value;

	public TunerPropertyWrapper(TunerStatus tuner, ScaSimpleProperty simple) {
		this.tuner = tuner;
		this.simple = simple;
		this.id = simple.getId();
		this.value = simple.getValue();

		for (TunerProperties.TunerStatusAllocationProperties value : TunerProperties.TunerStatusAllocationProperties.values()) {
			if (value.getId().equals(simple.getId())) {
				name = value.getName();
			}
		}
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public Object getValue() {
		return value;
	}

	public TunerStatus getTuner() {
		return tuner;
	}

	public ScaSimpleProperty getSimple() {
		return simple;
	}
}

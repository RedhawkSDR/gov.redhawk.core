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
package gov.redhawk.bulkio.ui.internal;

import CF.DataType;

public class SriDataTypeWrapper {

	private DataType dt;
	private Object parent;

	public SriDataTypeWrapper(DataType dt, Object parent) {
		this.dt = dt;
		this.parent = parent;
	}

	public DataType getDataType() {
		return dt;
	}

	public void setDataType(DataType dt) {
		this.dt = dt;
	}

	public Object getParent() {
		return parent;
	}

	public void setParent(Object parent) {
		this.parent = parent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dt == null) ? 0 : dt.id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
//		System.out.println("DT Obj: " + obj);
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof SriDataTypeWrapper)) {
			return false;
		}
		SriDataTypeWrapper other = (SriDataTypeWrapper) obj;
//		System.out.println("DT: " + dt.id + "  Other: " + other.dt.id);
		if (dt == null) {
			if (other.dt != null) {
				return false;
			}
		} else if (dt.id.equals(other.dt.id)) {
			return true;
		}
		return false;
	}
}

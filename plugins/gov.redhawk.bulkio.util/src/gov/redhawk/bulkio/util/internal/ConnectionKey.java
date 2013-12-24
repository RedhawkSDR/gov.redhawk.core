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
package gov.redhawk.bulkio.util.internal;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

import gov.redhawk.bulkio.util.BulkIOType;

public class ConnectionKey {
	private String ior;
	private String connectionID;
	private BulkIOType type;

	public ConnectionKey(@NonNull String ior, @NonNull BulkIOType type, @Nullable String connectionID) {
		super();
		this.ior = ior;
		this.type = type;
		this.connectionID = connectionID;
	}

	public String getIor() {
		return ior;
	}

	public String getConnectionID() {
		return connectionID;
	}

	public BulkIOType getType() {
		return type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((connectionID == null) ? 0 : connectionID.hashCode());
		result = prime * result + ((ior == null) ? 0 : ior.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ConnectionKey other = (ConnectionKey) obj;
		if (connectionID == null) {
			if (other.connectionID != null) {
				return false;
			}
		} else if (!connectionID.equals(other.connectionID)) {
			return false;
		}
		if (ior == null) {
			if (other.ior != null) {
				return false;
			}
		} else if (!ior.equals(other.ior)) {
			return false;
		}
		if (type != other.type) {
			return false;
		}
		return true;
	}

}

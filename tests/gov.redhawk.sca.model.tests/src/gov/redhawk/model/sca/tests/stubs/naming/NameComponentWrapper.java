/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.model.sca.tests.stubs.naming;

import org.jacorb.naming.Name;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextPackage.InvalidName;

/**
 * Wraps {@link NameComponent} so they can be compared (allows use in hashtables, etc).
 */
/* package */ class NameComponentWrapper implements Comparable<NameComponentWrapper> {

	private NameComponent nc;

	NameComponentWrapper(NameComponent nc) {
		this.nc = nc;
	}

	public NameComponent getValue() {
		return nc;
	}

	@Override
	public int compareTo(NameComponentWrapper other) {
		int result = nc.id.compareTo(other.nc.id);
		if (result != 0) {
			return result;
		}
		return nc.kind.compareTo(other.nc.kind);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof NameComponentWrapper)) {
			return false;
		}
		NameComponentWrapper other = (NameComponentWrapper) obj;
		return nc.id.equals(other.nc.id) && nc.kind.equals(other.nc.kind);
	}

	@Override
	public int hashCode() {
		try {
			return Name.toString(new NameComponent[] { nc }).hashCode();
		} catch (InvalidName e) {
			return 0;
		}
	}

	@Override
	public String toString() {
		try {
			return super.toString() + " " + Name.toString(new NameComponent[] { nc });
		} catch (InvalidName e) {
			return super.toString() + " (invalid name)";
		}
	}
}

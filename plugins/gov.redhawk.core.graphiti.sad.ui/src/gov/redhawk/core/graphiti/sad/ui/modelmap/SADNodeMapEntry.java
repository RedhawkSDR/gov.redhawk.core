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
package gov.redhawk.core.graphiti.sad.ui.modelmap;

import gov.redhawk.model.sca.ScaComponent;
import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;
import mil.jpeojtrs.sca.sad.SadComponentInstantiation;

public class SADNodeMapEntry {

	private String key;
	private ScaComponent comp;
	private SadComponentInstantiation profile;

	public SADNodeMapEntry() {
	}

	public SADNodeMapEntry(ScaComponent comp, SadComponentInstantiation profile) {
		if (comp != null) {
			setScaComponent(comp);
		}
		if (profile != null) {
			setProfile(profile);
		}
	}

	public String getKey() {
		if (comp != null) {
			return comp.getInstantiationIdentifier();
		} else if (profile != null) {
			return profile.getId();
		}
		return null;
	}

	public static String getKey(ScaComponent obj) {
		return obj.getInstantiationIdentifier();
	}

	public static String getKey(ComponentInstantiation obj) {
		return obj.getId();
	}

	private void setKey(String key) {
		if (this.key == null) {
			this.key = key;
		}
	}

	public ScaComponent getScaComponent() {
		return comp;
	}

	public void setScaComponent(ScaComponent comp) {
		this.comp = comp;
		setKey(comp.getInstantiationIdentifier());
	}

	public SadComponentInstantiation getProfile() {
		return profile;
	}

	public void setProfile(SadComponentInstantiation profile) {
		this.profile = profile;
		setKey(profile.getId());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
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
		SADNodeMapEntry other = (SADNodeMapEntry) obj;
		if (key == null) {
			if (other.key != null) {
				return false;
			}
		} else if (!key.equals(other.key)) {
			return false;
		}
		return true;
	}

}

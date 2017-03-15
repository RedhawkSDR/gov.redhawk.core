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
package gov.redhawk.core.graphiti.dcd.ui.modelmap;

import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaService;
import mil.jpeojtrs.sca.dcd.DcdComponentInstantiation;
import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;
import mil.jpeojtrs.sca.scd.SoftwareComponent;

public class DCDNodeMapEntry {

	private String key;
	private ScaDevice< ? > device;
	private ScaService service;
	private DcdComponentInstantiation profile;

	public DCDNodeMapEntry() {
	}

	public DCDNodeMapEntry(ScaDevice< ? > device, DcdComponentInstantiation profile) {
		setScaDevice(device);
		setProfile(profile);
	}

	public DCDNodeMapEntry(ScaService service, DcdComponentInstantiation profile) {
		setScaService(service);
		setProfile(profile);
	}

	public String getKey() {
		if (device != null) {
			return getKey(device);
		} else if (service != null) {
			return getKey(service);
		} else if (profile != null) {
			SoftwareComponent scd = ComponentInstantiation.Util.getScd(profile);
			switch (SoftwareComponent.Util.getWellKnownComponentType(scd)) {
			case SERVICE:
				return profile.getUsageName();
			default:
				return profile.getId();
			}
		}
		return null;
	}

	public static String getKey(ScaDevice< ? > obj) {
		return obj.getIdentifier();
	}

	public static String getKey(ScaService obj) {
		return obj.getName();
	}

	public static String getKey(ComponentInstantiation obj) {
		return obj.getId();
	}

	private void setKey(String key) {
		if (this.key == null) {
			this.key = key;
		}
	}

	public void setScaDevice(ScaDevice< ? > device) {
		this.device = device;
		setKey(getKey(device));
	}

	public ScaDevice< ? > getScaDevice() {
		return device;
	}

	public ScaService getScaService() {
		return service;
	}

	public void setScaService(ScaService service) {
		this.service = service;
		setKey(getKey(service));
	}

	public DcdComponentInstantiation getProfile() {
		return profile;
	}

	public void setProfile(DcdComponentInstantiation profile) {
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
		DCDNodeMapEntry other = (DCDNodeMapEntry) obj;
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

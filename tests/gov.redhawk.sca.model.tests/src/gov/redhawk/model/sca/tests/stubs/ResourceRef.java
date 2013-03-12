/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.model.sca.tests.stubs;

import CF.Resource;

class ResourceRef {
	private Resource resource;
	private String elementId;
	private String componentId;
	private int processId;
	private String deviceId;
	private String profile;	
	
	public ResourceRef(Resource resource, String elementId, String componentId,
			int processId, String deviceId, String profile) {
		super();
		this.resource = resource;
		this.elementId = elementId;
		this.componentId = componentId;
		this.processId = processId;
		this.deviceId = deviceId;
		this.profile = profile;
	}
	
	public Resource getResource() {
		return resource;
	}
	public String getElementId() {
		return elementId;
	}
	public String getComponentId() {
		return componentId;
	}
	public int getProcessId() {
		return processId;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public String getProfile() {
		return profile;
	}

}
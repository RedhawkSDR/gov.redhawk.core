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
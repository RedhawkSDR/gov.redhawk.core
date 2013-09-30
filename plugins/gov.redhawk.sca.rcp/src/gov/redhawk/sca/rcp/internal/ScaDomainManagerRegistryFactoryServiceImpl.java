package gov.redhawk.sca.rcp.internal;

import gov.redhawk.sca.IScaDomainManagerRegistryFactoryService;

public class ScaDomainManagerRegistryFactoryServiceImpl implements
		IScaDomainManagerRegistryFactoryService {

	@Override
	public ScaDomainManagerRegistryContainerImpl getRegistryContainer() {
		return ScaDomainManagerRegistryContainerImpl.getInstance();
	}
	
	public void activate() {
		getRegistryContainer().activate();
	}
	
	public void deactivate() {
		getRegistryContainer().dispose();
	}

}

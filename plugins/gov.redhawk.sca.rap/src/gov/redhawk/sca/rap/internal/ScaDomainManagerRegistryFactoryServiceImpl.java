package gov.redhawk.sca.rap.internal;

import gov.redhawk.entrypoint.scaExplorer.EntrypointActivator;
import gov.redhawk.sca.IScaDomainManagerRegistryContainer;
import gov.redhawk.sca.IScaDomainManagerRegistryFactoryService;

import org.eclipse.rwt.SessionSingletonBase;

public class ScaDomainManagerRegistryFactoryServiceImpl implements IScaDomainManagerRegistryFactoryService {

	@Override
	public IScaDomainManagerRegistryContainer getRegistryContainer() {
		if (Boolean.valueOf(System.getProperty(EntrypointActivator.PROP_SHARED_DOMAINS))) {
			return ScaDomainManagerRegistryContainerImpl.getInstance();
		}
		return SessionSingletonBase.getInstance(ScaDomainManagerRegistryContainerImpl.class);
	}
	
	public void deactivate() {
		SessionSingletonBase.getInstance(ScaDomainManagerRegistryContainerImpl.class).dispose();
	}
	
	public void activate() {
		//Nothing to do. Model will be loaded with specific user context when registry is obtained.
	}

}

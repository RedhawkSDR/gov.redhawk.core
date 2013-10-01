package gov.redhawk.sca;


/**
 * @since 6.1
 */
public interface IScaDomainManagerRegistryFactoryService {
	
	/**
	 * @return the SCA Domain Manager registry container.
	 */
	public IScaDomainManagerRegistryContainer getRegistryContainer();

}

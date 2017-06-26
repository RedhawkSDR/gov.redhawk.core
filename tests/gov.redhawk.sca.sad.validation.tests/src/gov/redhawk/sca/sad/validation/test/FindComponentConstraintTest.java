package gov.redhawk.sca.sad.validation.test;

import java.net.URISyntaxException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.Diagnostician;
import org.junit.Test;

import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.sad.SadComponentPlacement;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.util.ScaFileSystemConstants;
import mil.jpeojtrs.sca.util.SdrURIHandler;

public class FindComponentConstraintTest {

	/**
	 * Ensure we don't have issues with the uniqueness check validating a findcomponent
	 * in a SoftwareAssembly were some component instantiations that do NOT have findcomponents
	 */
	@Test
	public void validNullFindComponent() throws URISyntaxException {
		SoftwareAssembly sad = loadSADFromDomPath("/waveforms/FindComponentWF/FindComponentWF.sad.xml");
		SadComponentPlacement placement1 = sad.getPartitioning().getComponentPlacement().get(2);
		SadComponentInstantiation ci = placement1.getComponentInstantiation().get(0);
		Diagnostician.INSTANCE.validate(ci);
	}

	private SoftwareAssembly loadSADFromDomPath(String domPath) throws URISyntaxException {
		URI uri = URI.createURI(ScaFileSystemConstants.SCHEME_TARGET_SDR_DOM + "://" + domPath);
		ResourceSet resourceSet = createResourceSet();
		Resource resource = resourceSet.getResource(uri, true);
		return SoftwareAssembly.Util.getSoftwareAssembly(resource);
	}

	private ResourceSet createResourceSet() throws URISyntaxException {
		ResourceSet resourceSet = new ResourceSetImpl();
		URL url = FileLocator.find(Platform.getBundle("gov.redhawk.sca.sad.validation.tests"), new Path("sdr"), null);
		SdrURIHandler handler = new SdrURIHandler(URI.createURI(url.toURI().toString()));
		resourceSet.getURIConverter().getURIHandlers().add(0, handler);
		return resourceSet;
	}
}

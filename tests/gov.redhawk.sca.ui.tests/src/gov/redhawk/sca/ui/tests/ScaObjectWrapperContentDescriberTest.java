package gov.redhawk.sca.ui.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.junit.Assert;
import org.junit.Test;

import gov.redhawk.model.sca.ProfileObjectWrapper;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.sca.ui.editors.IScaContentDescriber;
import gov.redhawk.sca.ui.editors.ScaObjectWrapperContentDescriber;

import org.eclipse.emf.common.util.URI;

/**
 * IDE-1593
 * Test {@link ScaObjectWrapperContentDescriber}.
 */
public class ScaObjectWrapperContentDescriberTest {

	@Test
	public void noParams() throws IOException {
		ScaObjectWrapperContentDescriber describer = new ScaObjectWrapperContentDescriber();
		Assert.assertEquals(IScaContentDescriber.INVALID, describer.describe(getObjectWithTextProfileURI()));
	}

	@Test
	public void emptyParams() throws CoreException, IOException {
		ScaObjectWrapperContentDescriber describer = new ScaObjectWrapperContentDescriber();
		Map<String, String> data = new HashMap<String, String>();
		describer.setInitializationData(null, null, data);
		Assert.assertEquals(IScaContentDescriber.INVALID, describer.describe(getObjectWithTextProfileURI()));
	}

	@Test
	public void nonProfileObjectWrapper() throws IOException, CoreException {
		Assert.assertEquals(IScaContentDescriber.INVALID, getTextDescriber().describe(new Object()));
	}

	@Test
	public void matchingProfileURI() throws IOException, CoreException {
		Assert.assertEquals(IScaContentDescriber.VALID, getTextDescriber().describe(getObjectWithTextProfileURI()));
	}

	@Test
	public void nonMatchingProfileURI() throws IOException, CoreException {
		Assert.assertEquals(IScaContentDescriber.INVALID, getTextDescriber().describe(getObjectWithXmlProfileURI()));
	}

	private ProfileObjectWrapper< ? > getObjectWithTextProfileURI() {
		ProfileObjectWrapper< ? > obj = ScaFactory.eINSTANCE.createScaComponent();
		obj.setProfileURI(URI.createFileURI("/a/b/myfile.txt"));
		return obj;
	}

	private ProfileObjectWrapper< ? > getObjectWithXmlProfileURI() {
		ProfileObjectWrapper< ? > obj = ScaFactory.eINSTANCE.createScaComponent();
		obj.setProfileURI(URI.createFileURI("/a/b/myfile.xml"));
		return obj;
	}

	private ScaObjectWrapperContentDescriber getTextDescriber() throws CoreException {
		ScaObjectWrapperContentDescriber describer = new ScaObjectWrapperContentDescriber();
		Map<String, String> data = new HashMap<String, String>();
		data.put(ScaObjectWrapperContentDescriber.PARAM_PROFILE_FILENAME, ".*\\.txt");
		describer.setInitializationData(null, null, data);
		return describer;
	}

}

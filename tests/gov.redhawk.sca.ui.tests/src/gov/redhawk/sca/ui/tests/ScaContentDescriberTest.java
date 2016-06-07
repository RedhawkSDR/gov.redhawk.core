package gov.redhawk.sca.ui.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.junit.Assert;
import org.junit.Test;

import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.sca.ui.editors.IScaContentDescriber;
import gov.redhawk.sca.ui.editors.ScaContentDescriber;
import mil.jpeojtrs.sca.spd.SoftPkg;
import mil.jpeojtrs.sca.spd.SpdFactory;
import mil.jpeojtrs.sca.util.ScaResourceFactoryUtil;

/**
 * TODO: Need to test {@link ScaContentDescriber#PARAM_CORBA_REPID}.
 */
public class ScaContentDescriberTest {

	private static final String GOOD_PROFILE_ID = "goodid";
	private static final String BAD_PROFILE_ID = "badid";
	private static final String GOOD_PROFILE_FILENAME = "example.spd.xml";
	private static final String BAD_PROFILE_FILENAME = "example.sad.xml";
	private static final String PROFILE_FILENAME_PATTERN = ".*spd.xml";

	@Test
	public void noParams() throws IOException {
		ScaContentDescriber describer = new ScaContentDescriber();
		Assert.assertEquals(IScaContentDescriber.INVALID, describer.describe(getScaObject(GOOD_PROFILE_ID, GOOD_PROFILE_FILENAME)));
	}

	@Test
	public void emptyParams() throws CoreException, IOException {
		ScaContentDescriber describer = new ScaContentDescriber();
		Map<String, String> data = new HashMap<String, String>();
		describer.setInitializationData(null, null, data);
		Assert.assertEquals(IScaContentDescriber.INVALID, describer.describe(getScaObject(GOOD_PROFILE_ID, GOOD_PROFILE_FILENAME)));
	}

	@Test
	public void nonScaObject() throws IOException, CoreException {
		Assert.assertEquals(IScaContentDescriber.INVALID, getProfileIdDescriber().describe(new Object()));
	}

	@Test
	public void matchingId() throws IOException, CoreException {
		Assert.assertEquals(IScaContentDescriber.VALID, getProfileIdDescriber().describe(getScaObject(GOOD_PROFILE_ID, GOOD_PROFILE_FILENAME)));
	}

	@Test
	public void nonMatchingId() throws IOException, CoreException {
		Assert.assertEquals(IScaContentDescriber.INVALID, getProfileIdDescriber().describe(getScaObject(BAD_PROFILE_ID, GOOD_PROFILE_FILENAME)));
	}

	@Test
	public void matchingName() throws IOException, CoreException {
		Assert.assertEquals(IScaContentDescriber.VALID, getFileNameDescriber().describe(getScaObject(GOOD_PROFILE_ID, GOOD_PROFILE_FILENAME)));
	}

	@Test
	public void nonMatchingName() throws IOException, CoreException {
		Assert.assertEquals(IScaContentDescriber.INVALID, getFileNameDescriber().describe(getScaObject(GOOD_PROFILE_ID, BAD_PROFILE_FILENAME)));
	}

	public EObject getScaObject(String profileObjId, String profileObjFileName) {
		SoftPkg spd = SpdFactory.eINSTANCE.createSoftPkg();
		spd.setId(profileObjId);

		ScaComponent component = ScaFactory.eINSTANCE.createScaComponent();
		component.setProfileObj(spd);

		ResourceSet resourceSet = ScaResourceFactoryUtil.createResourceSet();
		Resource resource = resourceSet.createResource(URI.createURI("mem:///" + profileObjFileName));
		resource.getContents().add(spd);

		return component;
	}

	private ScaContentDescriber getProfileIdDescriber() throws CoreException {
		ScaContentDescriber describer = new ScaContentDescriber();
		Map<String, String> data = new HashMap<String, String>();
		data.put(ScaContentDescriber.PARAM_PROFILE_ID, GOOD_PROFILE_ID);
		describer.setInitializationData(null, null, data);
		return describer;
	}

	private ScaContentDescriber getFileNameDescriber() throws CoreException {
		ScaContentDescriber describer = new ScaContentDescriber();
		Map<String, String> data = new HashMap<String, String>();
		data.put(ScaContentDescriber.PARAM_FILENAME, PROFILE_FILENAME_PATTERN);
		describer.setInitializationData(null, null, data);
		return describer;
	}
}

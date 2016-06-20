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

import gov.redhawk.sca.ui.editors.EObjectContentDescriber;
import gov.redhawk.sca.ui.editors.IScaContentDescriber;
import mil.jpeojtrs.sca.spd.SoftPkg;
import mil.jpeojtrs.sca.spd.SpdFactory;
import mil.jpeojtrs.sca.util.ScaResourceFactoryUtil;

public class EObjectContentDescriberTest {

	private static final String GOOD_ID = "goodid";
	private static final String BAD_ID = "badid";
	private static final String GOOD_FILENAME = "example.spd.xml";
	private static final String BAD_FILENAME = "example.sad.xml";
	private static final String FILENAME_PATTERN = ".*spd.xml";

	@Test
	public void noParams() throws IOException {
		EObjectContentDescriber describer = new EObjectContentDescriber();
		Assert.assertEquals(IScaContentDescriber.INVALID, describer.describe(getEObject(GOOD_ID, GOOD_FILENAME)));
	}

	@Test
	public void emptyParams() throws CoreException, IOException {
		EObjectContentDescriber describer = new EObjectContentDescriber();
		Map<String, String> data = new HashMap<String, String>();
		describer.setInitializationData(null, null, data);
		Assert.assertEquals(IScaContentDescriber.INVALID, describer.describe(getEObject(GOOD_ID, GOOD_FILENAME)));
	}

	@Test
	public void nonEObject() throws IOException, CoreException {
		Assert.assertEquals(IScaContentDescriber.INVALID, getIdDescriber().describe(new Object()));
	}

	@Test
	public void matchingId() throws IOException, CoreException {
		Assert.assertEquals(IScaContentDescriber.VALID, getIdDescriber().describe(getEObject(GOOD_ID, GOOD_FILENAME)));
	}

	@Test
	public void nonMatchingId() throws IOException, CoreException {
		Assert.assertEquals(IScaContentDescriber.INVALID, getIdDescriber().describe(getEObject(BAD_ID, GOOD_FILENAME)));
	}

	@Test
	public void matchingName() throws IOException, CoreException {
		Assert.assertEquals(IScaContentDescriber.VALID, getFileNameDescriber().describe(getEObject(GOOD_ID, GOOD_FILENAME)));
	}

	@Test
	public void nonMatchingName() throws IOException, CoreException {
		Assert.assertEquals(IScaContentDescriber.INVALID, getFileNameDescriber().describe(getEObject(GOOD_ID, BAD_FILENAME)));
	}

	private EObject getEObject(String id, String filename) {
		ResourceSet resourceSet = ScaResourceFactoryUtil.createResourceSet();
		Resource resource = resourceSet.createResource(URI.createURI("mem:///" + filename));
		SoftPkg spd = SpdFactory.eINSTANCE.createSoftPkg();
		spd.setId(id);
		resource.getContents().add(spd);
		return spd;
	}

	private EObjectContentDescriber getIdDescriber() throws CoreException {
		EObjectContentDescriber describer = new EObjectContentDescriber();
		Map<String, String> data = new HashMap<String, String>();
		data.put(EObjectContentDescriber.PARAM_ID, GOOD_ID);
		describer.setInitializationData(null, null, data);
		return describer;
	}

	private EObjectContentDescriber getFileNameDescriber() throws CoreException {
		EObjectContentDescriber describer = new EObjectContentDescriber();
		Map<String, String> data = new HashMap<String, String>();
		data.put(EObjectContentDescriber.PARAM_FILENAME, FILENAME_PATTERN);
		describer.setInitializationData(null, null, data);
		return describer;
	}
}

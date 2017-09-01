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
package gov.redhawk.core.graphiti.ui.tests;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.osgi.framework.Bundle;

import gov.redhawk.core.graphiti.ui.util.InterfacesUtil;
import mil.jpeojtrs.sca.partitioning.ProvidesPortStub;
import mil.jpeojtrs.sca.partitioning.UsesPortStub;
import mil.jpeojtrs.sca.sad.Port;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.util.QueryParser;
import mil.jpeojtrs.sca.util.ScaFileSystemConstants;
import mil.jpeojtrs.sca.util.ScaResourceFactoryUtil;

public class InterfacesUtilTest {

	private static final String HARDLIMIT_1 = "HardLimit_1";
	private static final String HARDLIMIT_FLOAT_IN = "dataFloat_in";
	private static final String SIGGEN_1 = "SigGen_1";
	private static final String SIGGEN_FLOAT_OUT = "dataFloat_out";
	private static final String SIGGEN_SHORT_OUT = "dataShort_out";

	private SoftwareAssembly sad;

	@Before
	public void before() throws IOException, URISyntaxException {
		// Get SDR location within our bundle
		Bundle bundle = Platform.getBundle("gov.redhawk.core.graphiti.ui.tests");
		java.net.URL sdrDomURL = bundle.getEntry("sdr/dom");
		File sdrDomDir = new File(FileLocator.resolve(sdrDomURL).toURI());

		// Load SAD
		ResourceSet resourceSet = ScaResourceFactoryUtil.createResourceSet();
		String query = QueryParser.createQuery(Collections.singletonMap(ScaFileSystemConstants.QUERY_PARAM_FS, "file://" + sdrDomDir.toString()));
		URI sadURI = URI.createHierarchicalURI(ScaFileSystemConstants.SCHEME, null, null, new Path("/waveforms/InterfacesUtil/InterfacesUtil.sad.xml").segments(), query,
			SoftwareAssembly.EOBJECT_PATH);
		this.sad = SoftwareAssembly.Util.getSoftwareAssembly(resourceSet.getResource(sadURI, true));
	}

	/**
	 * Tests uses port -> provides port, both of same type
	 */
	@Test
	public void usesToProvides_sameTypes() {
		UsesPortStub source = getUsesPortStub(SIGGEN_1, SIGGEN_FLOAT_OUT);
		ProvidesPortStub target = getProvidesPortStub(HARDLIMIT_1, HARDLIMIT_FLOAT_IN);
		Assert.assertTrue(InterfacesUtil.areCompatible(source, target));
	}

	/**
	 * Tests uses port -> provides port, of different types
	 */
	@Test
	public void usesToProvides_differentTypes() {
		UsesPortStub source = getUsesPortStub(SIGGEN_1, SIGGEN_SHORT_OUT);
		ProvidesPortStub target = getProvidesPortStub(HARDLIMIT_1, HARDLIMIT_FLOAT_IN);
		Assert.assertFalse(InterfacesUtil.areCompatible(source, target));
	}

	/**
	 * IDE-1430 Objects for external ports (@link Port) should be gracefully refused.
	 */
	@Test
	public void externalPort() {
		UsesPortStub source = getUsesPortStub(SIGGEN_1, SIGGEN_FLOAT_OUT);
		Port target = this.sad.getExternalPorts().getPort().get(0);
		Assert.assertNotNull(target);
		Assert.assertFalse(InterfacesUtil.areCompatible(source, target));
	}

	/**
	 * Find a uses port stub for a component instance
	 * @param compInst
	 * @param portName
	 * @return
	 */
	private UsesPortStub getUsesPortStub(String compInst, String portName) {
		List<UsesPortStub> usesPortStubs = this.sad.getComponentInstantiation(compInst).getUses();
		for (UsesPortStub usesPortStub : usesPortStubs) {
			if (portName.equals(usesPortStub.getName())) {
				return usesPortStub;
			}
		}

		Assert.fail(String.format("Uses port %s not found on component instance %s", portName, compInst));
		return null;
	}

	/**
	 * Find a provides port stub for a component instance
	 * @param compInst
	 * @param portName
	 * @return
	 */
	private ProvidesPortStub getProvidesPortStub(String compInst, String portName) {
		List<ProvidesPortStub> providesPortStubs = this.sad.getComponentInstantiation(compInst).getProvides();
		for (ProvidesPortStub providesPortStub : providesPortStubs) {
			if (portName.equals(providesPortStub.getName())) {
				return providesPortStub;
			}
		}

		Assert.fail(String.format("Provides port %s not found on component instance %s", portName, compInst));
		return null;
	}

}

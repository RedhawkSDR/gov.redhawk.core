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
package gov.redhawk.eclipsecorba.idl.tests;

import gov.redhawk.eclipsecorba.idl.Definition;
import gov.redhawk.eclipsecorba.idl.Module;
import gov.redhawk.eclipsecorba.idl.Specification;
import gov.redhawk.eclipsecorba.idl.util.IdlResourceImpl;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public class ParseTests extends TestCase {

	private ResourceSet resourceSet;

	@Override
	protected void setUp() throws Exception {
		final ResourceSet set = new ResourceSetImpl();
		final URL url = FileLocator.toFileURL(FileLocator.find(IdlTestPlugin.getDefault().getBundle(), new Path("idl"), null));
		final URI uri = URI.createURI(url.toURI().toString());
		set.getLoadOptions().put(IdlResourceImpl.INCLUDE_PATHS, uri);
		this.resourceSet = set;
	}

	@Override
	protected void tearDown() throws Exception {
		this.resourceSet = null;
	}

	public void test_all_idls() throws Exception { // SUPPRESS CHECKSTYLE Method Name
		final Enumeration< ? > idls = IdlTestPlugin.getDefault().getBundle().findEntries("idl", "*.idl", true);
		while (idls.hasMoreElements()) {
			final Object obj = idls.nextElement();
			if (obj instanceof URL) {
				final URI uri = URI.createURI(FileLocator.toFileURL((URL) obj).toURI().toString());
				try {
					final Resource resource = this.resourceSet.getResource(uri, true);
					assertParsedFine(resource);
				} catch (final Exception e) {
					throw new Exception("Failed to parse idl: " + uri.lastSegment(), e);
				}
			}
		}
	}

	public void test_parse_Delay_idl() throws Exception { // SUPPRESS CHECKSTYLE Method Name
		final URI uri = URI.createURI(FileLocator
		        .toFileURL(FileLocator.find(IdlTestPlugin.getDefault().getBundle(), new Path("idl/ossie/REDHAWK/Delay.idl"), null)).toURI().toString());
		try {
			final Resource resource = this.resourceSet.getResource(uri, true);
			assertParsedFine(resource);
		} catch (final Exception e) {
			throw new Exception("Failed to parse idl: " + uri.lastSegment(), e);
		}
	}

	private URI getURI(final String idlFile) throws IOException {
		final URL url = FileLocator.toFileURL(FileLocator.find(IdlTestPlugin.getDefault().getBundle(), new Path(idlFile), null));
		return URI.createURI(url.toString());
	}

	private void assertParsedFine(final Resource resource) {
		Assert.assertTrue("Failed to parse: " + resource.getURI().path(), resource.getEObject("/") instanceof Specification);
	}

	public void testParse_lab_tasking() throws Exception { // SUPPRESS CHECKSTYLE Method Name
		final Resource resource = this.resourceSet.getResource(getURI("idl/lab_tasking.idl"), true);
		assertParsedFine(resource);
	}

	public void testParse_cf() throws Exception { // SUPPRESS CHECKSTYLE Method Name
		final Resource resource = this.resourceSet.getResource(getURI("idl/ossie/CF/cf.idl"), true);
		assertParsedFine(resource);
	}

	public void testParse_AggregateDevices() throws Exception { // SUPPRESS CHECKSTYLE Method Name
		final Resource resource = this.resourceSet.getResource(getURI("idl/ossie/CF/AggregateDevices.idl"), true);
		assertParsedFine(resource);
	}

	public void testParse_PortTypes() throws Exception { // SUPPRESS CHECKSTYLE Method Name
		final Resource resource = this.resourceSet.getResource(getURI("idl/ossie/CF/PortTypes.idl"), true);
		assertParsedFine(resource);
	}

	public void testParse_StandardEvent() throws Exception { // SUPPRESS CHECKSTYLE Method Name
		final Resource resource = this.resourceSet.getResource(getURI("idl/ossie/CF/StandardEvent.idl"), true);
		assertParsedFine(resource);
	}

	public void testParse_bio_dataChar() throws Exception { // SUPPRESS CHECKSTYLE Method Name
		final Resource resource = this.resourceSet.getResource(getURI("idl/ossie/BULKIO/bio_dataChar.idl"), true);
		assertParsedFine(resource);
	}

	public void testParse_bio_dataDouble() throws Exception { // SUPPRESS CHECKSTYLE Method Name
		final Resource resource = this.resourceSet.getResource(getURI("idl/ossie/BULKIO/bio_dataDouble.idl"), true);
		assertParsedFine(resource);
	}

	public void testParse_bio_dataFile() throws Exception { // SUPPRESS CHECKSTYLE Method Name
		final Resource resource = this.resourceSet.getResource(getURI("idl/ossie/BULKIO/bio_dataFile.idl"), true);
		assertParsedFine(resource);
	}

	public void test_correctResource() throws Exception { // SUPPRESS CHECKSTYLE Method Name
		final Resource aggregateDeviceResource = this.resourceSet.getResource(getURI("idl/ossie/CF/AggregateDevices.idl"), true);
		final Resource cfResource = this.resourceSet.getResource(getURI("idl/ossie/CF/cf.idl"), true);
		assertParsedFine(aggregateDeviceResource);
		assertParsedFine(cfResource);
		Assert.assertNotSame(cfResource, aggregateDeviceResource);
		final Specification aggregateSpec = (Specification) aggregateDeviceResource.getEObject("/");
		final Specification cfSpec = (Specification) cfResource.getEObject("/");
		Assert.assertNotNull(aggregateSpec);
		Assert.assertNotNull(cfSpec);
		Assert.assertNotSame(aggregateSpec, cfSpec);
		final EList<Definition> aggregateDefinitions = aggregateSpec.getDefinitions();
		final EList<Definition> cfDefinitions = cfSpec.getDefinitions();
		Assert.assertTrue(aggregateDefinitions.size() > 0);
		Assert.assertTrue(cfDefinitions.size() > 0);
		Module cfModule = null;
		Module aggregateModule = null;
		for (final Definition cfDef : cfDefinitions) {
			if (cfDef instanceof Module && cfDef.getName().equals("CF")) {
				cfModule = (Module) cfDef;
			}
		}
		for (final Definition agDef : aggregateDefinitions) {
			if (agDef instanceof Module && agDef.getName().equals("CF")) {
				aggregateModule = (Module) agDef;
			}
		}

		Assert.assertNotNull(cfModule);
		if (cfModule == null) {
			return;
		}
		Assert.assertNotNull(aggregateModule);
		if (aggregateModule == null) {
			return;
		}
		Assert.assertNotSame(cfModule, aggregateModule);
		Assert.assertNotSame(cfModule.eResource(), aggregateModule.eResource());

	}
}

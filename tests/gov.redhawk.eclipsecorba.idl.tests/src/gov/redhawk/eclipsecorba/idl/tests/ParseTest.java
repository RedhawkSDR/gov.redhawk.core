/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
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
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.junit.Assert;

import junit.framework.TestCase;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

public class ParseTest extends TestCase {

	private ResourceSet resourceSet;

	@Override
	protected void setUp() throws Exception {
		final ResourceSet set = new ResourceSetImpl();
		final URL baseUrl = FileLocator.toFileURL(FileLocator.find(IdlTestPlugin.getDefault().getBundle(), new Path("idl"), null));
		final URL cosUrl = new URL(baseUrl, "COS");
		final List<URI> includePaths = new ArrayList<URI>();
		includePaths.add(URI.createURI(baseUrl.toURI().toString()));
		includePaths.add(URI.createURI(cosUrl.toURI().toString()));
		set.getLoadOptions().put(IdlResourceImpl.INCLUDE_PATHS, includePaths);
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
				} catch (final Exception e) { // SUPPRESS CHECKSTYLE Logged Catch all exception
					throw new Exception("Failed to parse idl: " + uri.lastSegment(), e);
				}
			}
		}
	}

	private URI getURI(final String idlFile) throws IOException {
		final URL url = FileLocator.toFileURL(FileLocator.find(IdlTestPlugin.getDefault().getBundle(), new Path(idlFile), null));
		return URI.createURI(url.toString());
	}

	private void assertParsedFine(final Resource resource) {
		Assert.assertTrue("Failed to parse: " + resource.getURI().path(), resource.getEObject("/") instanceof Specification);
	}

	public void test_parse_lab_tasking() throws Exception { // SUPPRESS CHECKSTYLE Method Name
		final Resource resource = this.resourceSet.getResource(getURI("idl/lab_tasking.idl"), true);
		assertParsedFine(resource);
	}

	public void test_parse_cf() throws Exception { // SUPPRESS CHECKSTYLE Method Name
		final Resource resource = this.resourceSet.getResource(getURI("idl/ossie/CF/cf.idl"), true);
		assertParsedFine(resource);
	}

	public void test_parse_AggregateDevices() throws Exception { // SUPPRESS CHECKSTYLE Method Name
		final Resource resource = this.resourceSet.getResource(getURI("idl/ossie/CF/AggregateDevices.idl"), true);
		assertParsedFine(resource);
	}

	public void test_parse_PortTypes() throws Exception { // SUPPRESS CHECKSTYLE Method Name
		final Resource resource = this.resourceSet.getResource(getURI("idl/ossie/CF/PortTypes.idl"), true);
		assertParsedFine(resource);
	}

	public void test_parse_StandardEvent() throws Exception { // SUPPRESS CHECKSTYLE Method Name
		final Resource resource = this.resourceSet.getResource(getURI("idl/ossie/CF/StandardEvent.idl"), true);
		assertParsedFine(resource);
	}

	public void test_parse_bio_dataChar() throws Exception { // SUPPRESS CHECKSTYLE Method Name
		final Resource resource = this.resourceSet.getResource(getURI("idl/ossie/BULKIO/bio_dataChar.idl"), true);
		assertParsedFine(resource);
	}

	public void test_parse_bio_dataDouble() throws Exception { // SUPPRESS CHECKSTYLE Method Name
		final Resource resource = this.resourceSet.getResource(getURI("idl/ossie/BULKIO/bio_dataDouble.idl"), true);
		assertParsedFine(resource);
	}

	public void test_parse_bio_dataFile() throws Exception { // SUPPRESS CHECKSTYLE Method Name
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
			if (cfDef instanceof Module && cfDef.getName().equals("CF")) { // SUPPRESS CHECKSTYLE Comparison
				cfModule = (Module) cfDef;
			}
		}
		for (final Definition agDef : aggregateDefinitions) {
			if (agDef instanceof Module && agDef.getName().equals("CF")) { // SUPPRESS CHECKSTYLE Comparison
				aggregateModule = (Module) agDef;
			}
		}

		Assert.assertNotNull(cfModule);
		Assert.assertNotNull(aggregateModule);
		Assert.assertNotSame(cfModule, aggregateModule);
		Assert.assertNotSame(cfModule.eResource(), aggregateModule.eResource());

	}
}

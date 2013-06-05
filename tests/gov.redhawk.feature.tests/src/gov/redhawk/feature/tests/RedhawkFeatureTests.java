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
package gov.redhawk.feature.tests;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import junit.framework.Assert;

import org.eclipse.core.runtime.IBundleGroup;
import org.eclipse.core.runtime.IBundleGroupProvider;
import org.eclipse.core.runtime.Platform;
import org.eclipse.update.configurator.IPlatformConfiguration.IFeatureEntry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *  Test feature.xml files of REDHAWK features for validity.  Note that these test cases will not run successfully from
 *  within the development environment.  The feature needs to be installed into a REDHAWK IDE to run as expected/
 */
@SuppressWarnings("deprecation")
public class RedhawkFeatureTests {

	private static final String IDE_FEATURE = "gov.redhawk.ide.feature";
	private static final String UI_FEATURE = "gov.redhawk.ui.feature";
	private static final String IDE_UI_FEATURE = "gov.redhawk.ide.ui.feature";
	private static final String REDHAWK_SCA_FEATURE = "gov.redhawk.sca.feature";
	private static final String SCA_FEATURE = "mil.jpeojtrs.sca.feature";
	private DocumentBuilder builder;
	private List<Node> requiresElements = new ArrayList<Node>();;
	private IBundleGroupProvider provider;
	private String featureUnderTest;

	/**
	 * Parse the sca_explorer.product file and build list of items under the
	 * configurations node.
	 *
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		this.builder = factory.newDocumentBuilder();
		// There seems to only ever be one provider; we'll assume our groups are
		// in that one.
		this.provider = Arrays.asList(Platform.getBundleGroupProviders()).get(0);
	}

	/**
	 * Tear down the builder and provider
	 *
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		this.builder = null;
		this.provider = null;
	}

	/**
	 * Tests the IDE Feature for validity.
	 *
	 * @throws IOException
	 * @throws SAXException
	 */
    @Test
	public void testIDEFeature() throws IOException, SAXException {
		this.featureUnderTest = IDE_FEATURE;
		IFeatureEntry entry = this.getFeatureEntry(this.featureUnderTest);
		Assert.assertNotNull("The feature " + this.featureUnderTest + " was not found in the product.", entry);
		Document doc = this.parseFeature(entry.getFeatureVersion());
		this.buildRequiresElementList(doc);
		this.testForPluginDependency();
		this.testFeatureMatch();
	}

	/**
	 * Tests the UI Feature for validity.
	 *
	 * @throws IOException
	 * @throws SAXException
	 */
    @Test
	public void testUIFeature() throws IOException, SAXException {
		this.featureUnderTest = UI_FEATURE;
		IFeatureEntry entry = this.getFeatureEntry(this.featureUnderTest);
		Assert.assertNotNull("The feature " + this.featureUnderTest + " was not found in the product.", entry);
		Document doc = this.parseFeature(entry.getFeatureVersion());
		this.buildRequiresElementList(doc);
		this.testForPluginDependency();
		this.testFeatureMatch();
	}

	/**
	 * Tests the IDE UI Feature for validity.
	 *
	 * @throws IOException
	 * @throws SAXException
	 */
    @Test
	public void testIDEUIFeature() throws IOException, SAXException {
		this.featureUnderTest = IDE_UI_FEATURE;
		IFeatureEntry entry = this.getFeatureEntry(this.featureUnderTest);
		Assert.assertNotNull("The feature " + this.featureUnderTest + " was not found in the product.", entry);
		Document doc = this.parseFeature(entry.getFeatureVersion());
		this.buildRequiresElementList(doc);
		this.testForPluginDependency();
		this.testFeatureMatch();
	}

	/**
	 * Tests the REDHAWK SCA Feature for validity.
	 *
	 * @throws IOException
	 * @throws SAXException
	 */
    @Test
	public void testRedhawkSCAFeature() throws IOException, SAXException {
		this.featureUnderTest = REDHAWK_SCA_FEATURE;
		IFeatureEntry entry = this.getFeatureEntry(this.featureUnderTest);
		Assert.assertNotNull("The feature " + this.featureUnderTest + " was not found in the product.", entry);
		Document doc = this.parseFeature(entry.getFeatureVersion());
		this.buildRequiresElementList(doc);
		this.testForPluginDependency();
		this.testFeatureMatch();
	}

	/**
	 * Tests the SCA Feature for validity.
	 *
	 * @throws IOException
	 * @throws SAXException
	 */
    @Test
	public void testSCAFeature() throws IOException, SAXException {
		this.featureUnderTest = SCA_FEATURE;
		IFeatureEntry entry = this.getFeatureEntry(this.featureUnderTest);
		Assert.assertNotNull("The feature " + this.featureUnderTest + " was not found in the product.", entry);
		Document doc = this.parseFeature(entry.getFeatureVersion());
		this.buildRequiresElementList(doc);
		this.testForPluginDependency();
		this.testFeatureMatch();
	}

	/**
	 * Ensure the feature under test doesn't have any specified plug-in dependencies.
	 */
	private void testForPluginDependency() {
		for (Node node : this.requiresElements) {
			if (node instanceof Element) {
				Element temp = (Element) node;
				if (temp.getNodeName().equals("import")) {
					String plugin = temp.getAttribute("plugin");
					Assert.assertEquals("The feature " + this.featureUnderTest + " should not have any plugin dependencies.", "", plugin);
				}
			}
		}
	}

	/**
	 * Tests the features under the requires element to make sure the dependencies specified match the available versions.
	 */
    private void testFeatureMatch() {
		for (Node node : this.requiresElements) {
			if (node instanceof Element) {
				Element temp = (Element) node;
				if (temp.getNodeName().equals("import")) {
					String feature = temp.getAttribute("feature");
					String version = temp.getAttribute("version");
					String match = temp.getAttribute("match");
					IFeatureEntry entry = this.getFeatureEntry(feature);
					Assert.assertNotNull("The feature " + this.featureUnderTest + " depends on " + feature + " which was not found in the product.", entry);
					String actualVersion = entry.getFeatureVersion();
					//Major versions must match
					if (match.equals("") || match.equals("compatible")) {
						Assert.assertEquals("The version of the available " + feature
						        + " feature isn't compatible with the version specified in the dependency as the major versions don't match.",
						        version.charAt(0), actualVersion.charAt(0));
						//Available version must be greater or equal to the dependency version
					} else if (match.equals("greaterOrEqual")) {
						boolean greaterOrEqual = actualVersion.compareTo(version) >= 0;
						Assert.assertEquals("The version of the available " + feature
						        + " feature isn't greaterOrEqual to the version specified in the dependency.", true, greaterOrEqual);
						//Must be exact match
					} else if (match.equals("perfect")) {
						Assert.assertEquals("The version of the available " + feature
						        + " feature isn't a perfect match for the version specified in the dependency.", version, actualVersion);
						//Major and minor must match, service can be different
					} else if (match.equals("equivalent")) {
						Assert.assertEquals("The version of the available " + feature
						        + " feature isn't equivalent to the version specified in the dependency as the major versions don't match.", version.charAt(0),
						        actualVersion.charAt(0));
						Assert.assertEquals("The version of the available " + feature
						        + " feature isn't equivalent to the version specified in the dependency as the minor versions don't match.", version.charAt(2),
						        actualVersion.charAt(2));
					}
				}
			}
		}
	}

	/**
	 *
	 * @param featureID
	 * @return
	 */
    private IFeatureEntry getFeatureEntry(String featureID) {
		for (IBundleGroup group : provider.getBundleGroups()) {
			if (group.getIdentifier().equals(featureID)) {
				// IPlatformConfiguration has been deprecated in favor of p2.
				// These classes will likely go away soon,
				// but right now I don't see how else to get the feature
				// programmatically
				return (IFeatureEntry) group;
			}
		}
		return null;
	}

	/**
	 * Parses the feature under test and returns the {@link Document}.
	 *
	 * @param version the version of the feature to parse
	 * @return the document obtained by parsing the feature
	 * @throws MalformedURLException
	 * @throws SAXException
	 * @throws IOException
	 */
	private Document parseFeature(String version) throws SAXException, IOException {
		return this.builder.parse(this.createFeatureURL(version).toString());
	}

	/**
	 * Returns a {@link URL} for the feature.xml file of the feature under test.
	 *
	 * @param version the version of the feature under test
	 * @return a URL for the feature.xml file
	 * @throws MalformedURLException
	 */
	private URL createFeatureURL(String version) throws MalformedURLException {
		return new URL(Platform.getInstallLocation().getURL().toString() + "features/" + this.featureUnderTest + "_" + version + "/feature.xml");
	}

	/**
	 * Builds a list of {@link Node} under the "requires" element.
	 *
	 * @param document
	 *            the {@link Document} associated with the feature.xml file
	 */
	private void buildRequiresElementList(Document document) {
		Element root = document.getDocumentElement();
		NodeList nodes = root.getChildNodes();
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node instanceof Element) {
				Element child = (Element) node;
				if (child.getNodeName().equals("requires")) {
					NodeList configurationsNodes = child.getChildNodes();
					for (int j = 0; j < configurationsNodes.getLength(); j++) {
						if (configurationsNodes.item(j).getNodeName().equals("import")) {
							this.requiresElements.add(configurationsNodes.item(j));
						}
					}
				}
			}
		}
	}
}

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
package gov.redhawk.testSuite;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import junit.framework.Assert;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.internal.p2.metadata.OSGiVersion;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.metadata.IRequirement;
import org.eclipse.equinox.p2.metadata.expression.IMatchExpression;
import org.eclipse.equinox.p2.publisher.eclipse.AdviceFileParser;
import org.eclipse.equinox.spi.p2.publisher.PublisherHelper;
import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.osgi.service.resolver.BundleSpecification;
import org.junit.Test;
import org.osgi.framework.Bundle;
import org.osgi.framework.Constants;
import org.osgi.framework.Version;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * 
 */
public class GeneralPluginTests {

	private static final String VENDOR = "United States Government";

	private static final List<String> ID_FIELD_NAMES = Arrays.asList(new String[] { "ID", "PLUGIN_ID" });

	private static final String EXPECTED_VERSION = "1.5";
	private static final String[] JDT_PROPS = new String[] { "org.eclipse.jdt.core.compiler.source", "org.eclipse.jdt.core.compiler.compliance",
	        "org.eclipse.jdt.core.compiler.codegen.targetPlatform" };

	private Set<String> getProjectNatures(Bundle bundle) {
		Set<String> retVal = new HashSet<String>();
		try {
			DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document document = documentBuilder.parse(FileLocator.openStream(bundle, new Path(".project"), false));
			Node rootNode = document.getFirstChild();
			NodeList childNodes = rootNode.getChildNodes();
			for (int i = 0; i < childNodes.getLength(); i++) {
				Node childNode = childNodes.item(i);
				String nodeName = childNode.getNodeName();
				if (nodeName.equals("natures")) {
					NodeList subChildren = childNode.getChildNodes();
					for (int j = 0; j < subChildren.getLength(); j++) {
						Node subChildNode = subChildren.item(j);
						if ("nature".equals(subChildNode.getNodeName())) {
							retVal.add(subChildNode.getTextContent());
						}
					}
				}
			}
		} catch (ParserConfigurationException e) {
			// PASS
		} catch (SAXException e) {
			// PASS
		} catch (IOException e) {
			// PASS
		}
		return retVal;
	}

	/**
	 * Determines if a bundle is optionally dependent on a RAP bundle.
	 * 
	 * @param description the {@link BundleDescription} to check
	 * @return <code> true </code> if the bundle is optionally dependent on a RAP bundle; <code> false </code> otherwise
	 */
	private boolean containsOptionalRapDependency(BundleDescription description) {
		for (BundleSpecification dependency : description.getRequiredBundles()) {
			if (dependency.getName().contains("rap") && dependency.isOptional()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Given a bundle description and a properties file, verify that any optional rap dependencies are listed in the
	 * file with the appropriate version range and greedy flag.
	 * 
	 * @param description the bundle description for the bundle containing optional rap dependencies
	 * @param props the p2.inf file read into a {@link Properties} object
	 */
	private void isValidP2Advice(BundleDescription description, Properties props) {
		AdviceFileParser parser = new AdviceFileParser(description.getName(), PublisherHelper.fromOSGiVersion(description.getVersion()),
		        new HashMap<String, String>((Map) props));
		parser.parse();
		for (BundleSpecification dependency : description.getRequiredBundles()) {
			//if the dependency is an optional rap dependency, make sure it's in the advice file requires section
			if (dependency.getName().contains("rap") && dependency.isOptional()) {
				boolean foundDep = false;
				for (IRequirement requirement : parser.getRequiredCapabilities()) {
					IMatchExpression<IInstallableUnit> matches = requirement.getMatches();
					List<Object> params = Arrays.asList(matches.getParameters());
					if (params.contains(dependency.getName())) {
						foundDep = true;
						Assert.assertEquals(description.getName() + " p2 advice file should include version range: " + dependency.getVersionRange() + " for "
						        + dependency.getName(), true, params.contains(OSGiVersion.create(dependency.getVersionRange().toString())));
						Assert.assertEquals(description.getName() + " p2 advice file should set greedy flag to false for " + dependency.getName(), false,
						        requirement.isGreedy());
						break;
					}
				}
				Assert.assertEquals(description.getName() + " p2 advice file should include " + dependency.getName(), true, foundDep);
			}
		}
	}

	/**
	 * Any bundles that contain optional RAP Dependencies must contain a p2.inf file so that the optional dependencies
	 * aren't greedily installed.
	 */
	@Test
	public void checkP2Inf() {
		BundleDescription[] allBundles = Platform.getPlatformAdmin().getState().getBundles();
		for (BundleDescription desc : allBundles) {
			if (checkBundle(desc)) {
				if (containsOptionalRapDependency(desc)) {
					Bundle bundle = Platform.getBundle(desc.getSymbolicName());
					URL url = FileLocator.find(bundle, new Path("META-INF/p2.inf"), null);
					Assert.assertNotNull("Bundle: " + desc.getName() + " is missing a p2.inf file", FileLocator.find(bundle, new Path("META-INF/p2.inf"), null));
					try {
						Properties props = new Properties();
						InputStream stream = FileLocator.openStream(bundle, new Path("META-INF/p2.inf"), false);
						try {
							props.load(stream);
						} finally {
							stream.close();
						}
						this.isValidP2Advice(desc, props);
					} catch (IOException e) {
						//PASS
					}
				}
			}
		}
	}

	@Test
	public void classpathCorrect() {
		BundleDescription[] allBundles = Platform.getPlatformAdmin().getState().getBundles();
		List<String> invalidMsgs = new ArrayList<String>();
		for (BundleDescription desc : allBundles) {
			if (checkBundle(desc)) {
				Bundle bundle = Platform.getBundle(desc.getSymbolicName());
				Set<String> natures = getProjectNatures(bundle);
				if (natures.contains("org.eclipse.jdt.core.javanature")) {
					try {
						Properties jdtProps = new Properties();
						InputStream stream = FileLocator.openStream(bundle, new Path(".settings/org.eclipse.jdt.core.prefs"), false);
						try {
							jdtProps.load(stream);
						} finally {
							stream.close();
						}
						for (String prop : JDT_PROPS) {
							if (!EXPECTED_VERSION.equals(jdtProps.get(prop))) {
								invalidMsgs.add(desc.getSymbolicName() + " missing JDT property " + prop + " should be " + EXPECTED_VERSION);
							}
						}
					} catch (IOException e) {
						invalidMsgs.add(desc.getSymbolicName() + " missing JDT properties. Run update classpath on this plugin.");
					}
				}
			}
		}
		if (!invalidMsgs.isEmpty()) {
			StringBuilder result = new StringBuilder();
			for (String s : invalidMsgs) {
				result.append(s);
				result.append('\n');
			}
			Assert.fail(result.toString());
		}
	}

	private boolean shouldSkipDependency(BundleSpecification dependency) {
		if (dependency.getName().equals("org.eclipse.osgi")) {
			return true;
		}
		return false;
	}

	@Test
	public void validBuildPropertiesBin() {
		BundleDescription[] allBundles = Platform.getPlatformAdmin().getState().getBundles();
		List<String> invalidMsgs = new ArrayList<String>();
		for (BundleDescription desc : allBundles) {
			if (checkBundle(desc)) {
				Bundle bundle = Platform.getBundle(desc.getSymbolicName());
				List<String> missingBuilds = new ArrayList<String>();
				try {
					InputStream propertiesStream = FileLocator.openStream(bundle, new Path("build.properties"), false);
					try {
						Properties buildProperties = new Properties();
						buildProperties.load(propertiesStream);
						String binIncludes = buildProperties.getProperty("bin.includes");
						String[] resources = { "icons", ".options", "plugin.xml", "about.ini", "about.html", "about.properties", "schema", "META-INF",
						        "plugin.properties", "OSGI-INF", "feature.xml", "feature.properties", "model" };
						for (String resource : resources) {
							URL url = FileLocator.find(bundle, new Path(resource), null);
							if (url != null) {
								URL fileUrl = FileLocator.toFileURL(url);
								if (binIncludes == null || !binIncludes.contains(resource)) {
									missingBuilds.add(resource);
								}
							}
						}
					} finally {
						propertiesStream.close();
					}
				} catch (IOException e) {
					continue;
				}
				String msg = desc.getName() + " missing files in build.properties bin.includes: " + missingBuilds;
				if (!missingBuilds.isEmpty()) {
					invalidMsgs.add(msg);
				}
			}
		}
		if (!invalidMsgs.isEmpty()) {
			StringBuilder result = new StringBuilder();
			for (String s : invalidMsgs) {
				result.append(s);
				result.append('\n');
			}
			Assert.fail(result.toString());
		}
	}

	@Test
	public void validBuildPropertiesSrc() {
		BundleDescription[] allBundles = Platform.getPlatformAdmin().getState().getBundles();
		List<String> invalidMsgs = new ArrayList<String>();
		for (BundleDescription desc : allBundles) {
			if (checkBundle(desc)) {
				Bundle bundle = Platform.getBundle(desc.getSymbolicName());
				List<String> missingBuilds = new ArrayList<String>();
				try {
					InputStream propertiesStream = FileLocator.openStream(bundle, new Path("build.properties"), false);
					try {
						Properties buildProperties = new Properties();
						buildProperties.load(propertiesStream);
						String srcIncludes = buildProperties.getProperty("src.includes");
						String[] resources = { "templates" };
						for (String resource : resources) {
							URL url = FileLocator.find(bundle, new Path(resource), null);
							if (url != null) {
								URL fileUrl = FileLocator.toFileURL(url);
								if (srcIncludes == null || !srcIncludes.contains(resource)) {
									missingBuilds.add(resource);
								}
							}
						}
					} finally {
						propertiesStream.close();
					}
				} catch (IOException e) {
					continue;
				}
				String msg = desc.getName() + " missing files in build.properties src.includes: " + missingBuilds;
				if (!missingBuilds.isEmpty()) {
					invalidMsgs.add(msg);
				}
			}
		}
		if (!invalidMsgs.isEmpty()) {
			StringBuilder result = new StringBuilder();
			for (String s : invalidMsgs) {
				result.append(s);
				result.append('\n');
			}
			Assert.fail(result.toString());
		}
	}
	
	@Test
	public void validBuildPropertiesSrcBlackList() {
		BundleDescription[] allBundles = Platform.getPlatformAdmin().getState().getBundles();
		List<String> invalidMsgs = new ArrayList<String>();
		for (BundleDescription desc : allBundles) {
			if (checkBundle(desc)) {
				Bundle bundle = Platform.getBundle(desc.getSymbolicName());
				List<String> missingBuilds = new ArrayList<String>();
				try {
					InputStream propertiesStream = FileLocator.openStream(bundle, new Path("build.properties"), false);
					try {
						Properties buildProperties = new Properties();
						buildProperties.load(propertiesStream);
						String srcIncludes = buildProperties.getProperty("src.includes");
						String[] resources = { "model" };
						for (String resource : resources) {
							URL url = FileLocator.find(bundle, new Path(resource), null);
							if (url != null) {
								URL fileUrl = FileLocator.toFileURL(url);
								if (srcIncludes != null && srcIncludes.contains(resource)) {
									missingBuilds.add(resource);
								}
							}
						}
					} finally {
						propertiesStream.close();
					}
				} catch (IOException e) {
					continue;
				}
				String msg = desc.getName() + " blacklisted files in build.properties src.includes: " + missingBuilds;
				if (!missingBuilds.isEmpty()) {
					invalidMsgs.add(msg);
				}
			}
		}
		if (!invalidMsgs.isEmpty()) {
			StringBuilder result = new StringBuilder();
			for (String s : invalidMsgs) {
				result.append(s);
				result.append('\n');
			}
			Assert.fail(result.toString());
		}
	}

	private static final List<String> REQUIRED_CHECKSTYLE_FILES_TYPES = Arrays.asList("java", "properties", "xml");

	private static final List<String> CHECKSTYLE_SKIP_BUNDLE = Arrays.asList("gov.redhawk.doc.user");

	@Test
	public void validCheckStyleConfig() {
		BundleDescription[] allBundles = Platform.getPlatformAdmin().getState().getBundles();
		List<Bundle> invalidBundle = new ArrayList<Bundle>();
		for (BundleDescription desc : allBundles) {
			if (checkBundle(desc)) {
				Bundle bundle = Platform.getBundle(desc.getSymbolicName());
				boolean found = false;
				String symbolicName = bundle.getSymbolicName();
				boolean correctFilters = CHECKSTYLE_SKIP_BUNDLE.contains(symbolicName);
				try {
					InputStream checkstyleConfig = FileLocator.openStream(bundle, new Path(".checkstyle"), false);
					try {
						DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
						Document doc = builder.parse(checkstyleConfig);
						NodeList docList = doc.getChildNodes();
						for (int i = 0; i < docList.getLength(); i++) {
							Node docNode = docList.item(i);
							if (docNode.getNodeName().equals("fileset-config")) {
								NodeList nodeList = docNode.getChildNodes();
								for (int j = 0; j < nodeList.getLength(); j++) {
									Node node = nodeList.item(j);
									if (node.getNodeName().equals("fileset")) {
										Node attr = node.getAttributes().getNamedItem("check-config-name");
										if (attr.getNodeValue().equals("REDHAWK")) {
											found = true;
										}
									} else if (!correctFilters && node.getNodeName().equals("filter")
									        && node.getAttributes().getNamedItem("name").getNodeValue().equals("FileTypesFilter")) {
										NodeList subNodes = node.getChildNodes();
										List<String> types = new ArrayList<String>(REQUIRED_CHECKSTYLE_FILES_TYPES);
										for (int k = 0; k < subNodes.getLength(); k++) {
											Node subNode = subNodes.item(k);
											if (subNode.getNodeName().equals("filter-data")) {
												types.remove(subNode.getAttributes().getNamedItem("value").getNodeValue());
											}
										}
										if (types.isEmpty()) {
											correctFilters = true;
										}
									}
								}
							}
						}
					} finally {
						checkstyleConfig.close();
					}
				} catch (Exception e) {
					// PASS
				}
				if (!found || !correctFilters) {
					invalidBundle.add(bundle);
				}
			}
		}
		if (!invalidBundle.isEmpty()) {
			StringBuilder result = new StringBuilder();
			result.append("Invalid or missing checkstyle configuration: \n\t");
			for (Bundle b : invalidBundle) {
				result.append(b.getSymbolicName());
				result.append("\n\t");
			}
			Assert.fail(result.toString());
		}
	}

	@Test
	public void validBundleDependency() {
		BundleDescription[] allBundles = Platform.getPlatformAdmin().getState().getBundles();
		List<String> invalidMsgs = new ArrayList<String>();
		for (BundleDescription desc : allBundles) {
			if (checkBundle(desc)) {
				String name = desc.getName();
				List<String> dependentBundles = new ArrayList<String>();
				for (BundleSpecification dependency : desc.getRequiredBundles()) {
					if (shouldSkipDependency(dependency)) {
						continue;
					}
					if (dependency.getVersionRange() != null) {
						if (dependency.getVersionRange().getMinimum() != null) {
							Version minimum = dependency.getVersionRange().getMinimum();
							if (minimum.getMajor() <= 0) {
								dependentBundles.add(dependency.getName());
							}
						} else {
							dependentBundles.add(dependency.getName());
						}
					} else {
						dependentBundles.add(dependency.getName());
					}
				}
				String msg = name + " missing minimum version qualifier on: " + dependentBundles;
				if (!dependentBundles.isEmpty()) {
					invalidMsgs.add(msg);
				}
			}
		}
		if (!invalidMsgs.isEmpty()) {
			StringBuilder result = new StringBuilder();
			for (String s : invalidMsgs) {
				result.append(s);
				result.append('\n');
			}
			Assert.fail(result.toString());
		}
	}

	@Test
	public void validQualifiers() {
		BundleDescription[] allBundles = Platform.getPlatformAdmin().getState().getBundles();
		List<String> invalidMsgs = new ArrayList<String>();
		for (BundleDescription desc : allBundles) {
			if (checkBundle(desc)) {
				String name = desc.getName();
				Version version = desc.getVersion();
				String msg = name + " missing version qualifier.";
				if (version == null || version.getQualifier() == null || version.getQualifier().length() == 0) {
					invalidMsgs.add(msg);
				}
			}
		}
		if (!invalidMsgs.isEmpty()) {
			StringBuilder result = new StringBuilder();
			for (String s : invalidMsgs) {
				result.append(s);
				result.append('\n');
			}
			Assert.fail(result.toString());
		}
	}

	private static final String[] VALID_EXECUTION_ENV = new String[] { "J2SE-1.5" };

	private boolean checkBundle(BundleDescription b) {
		String name = b.getName();
		return name != null && (name.startsWith("gov.redhawk") || name.startsWith("mil.jpeojtrs"));
	}

	@Test
	public void validExecutionEnv() {
		BundleDescription[] allBundles = Platform.getPlatformAdmin().getState().getBundles();
		List<String> invalidMsgs = new ArrayList<String>();
		Set<String> validExecutionEnvSet = new HashSet<String>();
		validExecutionEnvSet.addAll(Arrays.asList(VALID_EXECUTION_ENV));

		for (BundleDescription desc : allBundles) {
			if (checkBundle(desc)) {
				String name = desc.getName();
				for (String execEnv : desc.getExecutionEnvironments()) {
					if (!validExecutionEnvSet.contains(execEnv)) {
						invalidMsgs.add("Invalid execution env " + execEnv + " found in " + name);
					}
				}
			}
		}

		if (!invalidMsgs.isEmpty()) {
			StringBuilder result = new StringBuilder();
			for (String s : invalidMsgs) {
				result.append(s);
				result.append('\n');
			}
			Assert.fail(result.toString());
		}
	}

	@Test
	public void validPluginProviders() {
		BundleDescription[] allBundles = Platform.getPlatformAdmin().getState().getBundles();
		List<String> invalidMsgs = new ArrayList<String>();
		for (BundleDescription desc : allBundles) {
			String name = desc.getName();
			if (checkBundle(desc)) {
				String msg = name + " invalid plugin provider.";
				Object vendor = Platform.getBundle(desc.getSymbolicName()).getHeaders().get(Constants.BUNDLE_VENDOR);
				if (!VENDOR.equals(vendor)) {
					invalidMsgs.add(Assert.format(msg, VENDOR, vendor));
				}
			}
		}
		if (!invalidMsgs.isEmpty()) {
			StringBuilder result = new StringBuilder();
			for (String s : invalidMsgs) {
				result.append(s);
				result.append('\n');
			}
			Assert.fail(result.toString());
		}
	}

	@Test
	public void validPluginStaticIDs() {
		BundleDescription[] allBundles = Platform.getPlatformAdmin().getState().getBundles();
		List<String> invalidMsgs = new ArrayList<String>();
		for (BundleDescription desc : allBundles) {
			if (checkBundle(desc)) {
				String name = desc.getName();
				Bundle bundle = Platform.getBundle(desc.getSymbolicName());
				Object activator = bundle.getHeaders().get(Constants.BUNDLE_ACTIVATOR);
				if (activator != null) {
					try {
						Class< ? > activatorClass = bundle.loadClass(activator.toString());
						for (Field f : activatorClass.getFields()) {
							if (f.getType() == String.class && ID_FIELD_NAMES.contains(f.getName())) {
								Object id = f.get(null);
								if (!name.equals(id)) {
									String msg = name + " invalid plugin static ID.";
									invalidMsgs.add(Assert.format(msg, name, f.getName() + "=" + id));
								}
							}
						}
					} catch (Exception e) {
						// PASS
					}
				}
			}
		}
		if (!invalidMsgs.isEmpty()) {
			StringBuilder result = new StringBuilder();
			for (String s : invalidMsgs) {
				result.append(s);
				result.append('\n');
			}
			Assert.fail(result.toString());
		}
	}
}

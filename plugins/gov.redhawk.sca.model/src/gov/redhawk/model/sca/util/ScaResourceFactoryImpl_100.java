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

 // BEGIN GENERATED CODE
package gov.redhawk.model.sca.util;

import gov.redhawk.model.sca.ScaDocumentRoot;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaPackage;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.BasicResourceHandler;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.emf.mapping.ecore2xml.Ecore2XMLPackage;
import org.eclipse.emf.mapping.ecore2xml.Ecore2XMLRegistry;
import org.eclipse.emf.mapping.ecore2xml.impl.Ecore2XMLRegistryImpl;
import org.eclipse.emf.mapping.ecore2xml.util.Ecore2XMLExtendedMetaData;

/**
 * The <b>Resource Factory</b> associated with the package.
 *  @since 14.0
 * @see gov.redhawk.model.sca.util.ScaResourceImpl
 */
public class ScaResourceFactoryImpl_100 extends ResourceFactoryImpl {
	
	private static class RulesResourceHandler extends BasicResourceHandler {
		@Override
		public void postLoad(XMLResource resource, InputStream inputStream, Map< ? , ? > options) {
		    Map<EObject, AnyType> extMap = resource.getEObjectToExtensionMap();
		    for (Iterator<?> itr = extMap.entrySet().iterator(); itr.hasNext();) {
		    	Map.Entry<?, ?> entry = (Entry< ? , ? >) itr.next();
		    	EObject key = (EObject) entry.getKey();
		    	AnyType value = (AnyType) entry.getValue();
		    	handleUnknownData(key, value);
		    }
		}

		private void handleUnknownData(EObject eObj, AnyType unknownData) {
	        handleUnknownFeature(eObj, unknownData.getMixed());
	        handleUnknownFeature(eObj, unknownData.getAnyAttribute());
        }

		private void handleUnknownFeature(EObject owner, FeatureMap featureMap) {
	        for (Iterator<?> iter = featureMap.iterator(); iter.hasNext();) {
	        	FeatureMap.Entry entry = (FeatureMap.Entry) iter.next();
	        	EStructuralFeature f = entry.getEStructuralFeature();
	        	if (entry.getValue() instanceof AnyType) {
		        	if (handleUnknownFeature(owner, f, (AnyType) entry.getValue())) {
		        		iter.remove();
		        		
		        	}
	        	}
	        }
        }

		private boolean handleUnknownFeature(EObject owner, EStructuralFeature feature, AnyType value) {
			if (owner instanceof ScaDomainManagerRegistry) {
				ScaDomainManagerRegistry registry = (ScaDomainManagerRegistry) owner;
				if (feature.getName().equals("domains")) {
					for ( Iterator<?> iter = value.getMixed().iterator(); iter.hasNext();) {
						FeatureMap.Entry entry = (org.eclipse.emf.ecore.util.FeatureMap.Entry) iter.next();
						EStructuralFeature domainFeature = entry.getEStructuralFeature();
						if (domainFeature.getName().equals("domain")) {
							if (entry.getValue() instanceof AnyType) {
								registry.getDomains().add(createDomain((AnyType) entry.getValue()));
							}
						}
					}
					return true;
				}
			} else if (owner instanceof ScaDocumentRoot) {
				ScaDocumentRoot root = (ScaDocumentRoot) owner;
				if (feature.getName().equals("domainManagerRegistry")) {
					ScaDomainManagerRegistry registry = ScaFactory.eINSTANCE.createScaDomainManagerRegistry();
					root.setDomainManagerRegistry(registry);
					for ( Iterator<?> iter = value.getMixed().iterator(); iter.hasNext();) {
						FeatureMap.Entry entry = (org.eclipse.emf.ecore.util.FeatureMap.Entry) iter.next();
						EStructuralFeature domainFeature = entry.getEStructuralFeature();
						if (entry.getValue() instanceof AnyType) {
							handleUnknownFeature(registry, domainFeature, (AnyType) entry.getValue());
						}
					}
					return true;
				}
			}
	        return false;
        }

		private ScaDomainManager createDomain(AnyType value) {
			ScaDomainManager retVal = ScaFactory.eINSTANCE.createScaDomainManager();
			for ( Iterator<?> iter = value.getAnyAttribute().iterator(); iter.hasNext();) {
				FeatureMap.Entry entry = (org.eclipse.emf.ecore.util.FeatureMap.Entry) iter.next();
				EStructuralFeature f = entry.getEStructuralFeature();
				if (f.getName().equals("name")) {
					if (entry.getValue() instanceof String) {
						retVal.setName((String) entry.getValue());
					}
				} else if (f.getName().equals("autoConnect")) {
					if (entry.getValue() instanceof Boolean) {
						retVal.setAutoConnect((Boolean) entry.getValue());
					}
				}
			}
			for ( Iterator<?> iter = value.getMixed().iterator(); iter.hasNext();) {
				FeatureMap.Entry entry = (org.eclipse.emf.ecore.util.FeatureMap.Entry) iter.next();
				EStructuralFeature f = entry.getEStructuralFeature();
				if (f.getName().equals("connectionProperties")) {
					if (entry.getValue() instanceof AnyType) {
						Map<String, String> properties = createConnectionProperties((AnyType) entry.getValue());
						retVal.getConnectionProperties().putAll(properties);
					}
				}
			}
	        return retVal;
        }
		
		private Map<String, String> createConnectionProperties(AnyType value) {
			Map<String,String> retVal = new HashMap<String, String>();
			for ( Iterator<?> iter = value.getMixed().iterator(); iter.hasNext();) {
				FeatureMap.Entry entry = (org.eclipse.emf.ecore.util.FeatureMap.Entry) iter.next();
				EStructuralFeature f = entry.getEStructuralFeature();
				if (f.getName().equals("property")) {
					if (entry.getValue() instanceof AnyType) {
						retVal.putAll(createProperty((AnyType) entry.getValue()));
					}
				}
			}
			return retVal;
        }

		private Map<String, String> createProperty(AnyType anyValue) {
			String key = "";
			String value = "";
			for ( Iterator<?> iter = anyValue.getAnyAttribute().iterator(); iter.hasNext();) {
				FeatureMap.Entry entry = (org.eclipse.emf.ecore.util.FeatureMap.Entry) iter.next();
				EStructuralFeature f = entry.getEStructuralFeature();
				if (f.getName().equals("key")) {
					key= entry.getValue().toString();
				} else if (f.getName().equals("value")) {
					value= entry.getValue().toString();
				}
			}
		    return Collections.singletonMap(key, value);
	    }
	}

	private static final String SCA_100_NS_URI = "http://www.redhawk.gov/model/sca";
	private static final String SCA_PLATFORM_URI = "platform:/plugin/gov.redhawk.sca.model/model/sca.ecore";
	private static final String SCA_100_PLATFORM_URI = "platform:/plugin/gov.redhawk.sca.model/model/scaOld_2_sca.ecore2xml";
	private Ecore2XMLExtendedMetaData extendedMetaData;
	
	/**
	 * Creates an instance of the resource factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaResourceFactoryImpl_100() {
		super();
	}

	/**
	 * Creates an instance of the resource.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Resource createResource(URI uri) {
		ScaResourceImpl result = new ScaResourceImpl(uri);
		Map<Object, Object> defaultLoadOptions = result.getDefaultLoadOptions();
		result.getDefaultSaveOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
		result.getDefaultLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);

		result.getDefaultSaveOptions().put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);

		result.getDefaultLoadOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
		result.getDefaultSaveOptions().put(XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);

		result.getDefaultLoadOptions().put(XMLResource.OPTION_USE_LEXICAL_HANDLER, Boolean.TRUE);
		
		defaultLoadOptions.put(XMLResource.OPTION_EXTENDED_META_DATA, getExtendedMetaData());
		defaultLoadOptions.put(XMLResource.OPTION_RECORD_UNKNOWN_FEATURE, Boolean.TRUE);
		defaultLoadOptions.put(XMLResource.OPTION_RESOURCE_HANDLER, new RulesResourceHandler());
		return result;
	}

	private ExtendedMetaData getExtendedMetaData() {
		if (extendedMetaData == null) {
			ResourceSet resourceSet = new ResourceSetImpl();
			EPackage.Registry ePackageRegistry = resourceSet.getPackageRegistry();
			ePackageRegistry.put(SCA_100_NS_URI, ScaPackage.eINSTANCE);
			ePackageRegistry.put(SCA_PLATFORM_URI, ScaPackage.eINSTANCE);
			Ecore2XMLRegistry ecore2xmlRegistry = new Ecore2XMLRegistryImpl(Ecore2XMLRegistry.INSTANCE);
			ecore2xmlRegistry.put(SCA_100_NS_URI, EcoreUtil.getObjectByType(resourceSet.getResource(URI.createURI(SCA_100_PLATFORM_URI), true).getContents(), Ecore2XMLPackage.Literals.XML_MAP));
			extendedMetaData = new Ecore2XMLExtendedMetaData(ePackageRegistry, ecore2xmlRegistry);
		}
	    return extendedMetaData;
    }

} //ScaResourceFactoryImpl

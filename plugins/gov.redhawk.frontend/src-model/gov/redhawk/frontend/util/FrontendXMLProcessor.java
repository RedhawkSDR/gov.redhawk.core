/**
 */
package gov.redhawk.frontend.util;

import gov.redhawk.frontend.FrontendPackage;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class FrontendXMLProcessor extends XMLProcessor
{

  /**
   * Public constructor to instantiate the helper.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FrontendXMLProcessor()
  {
    super((EPackage.Registry.INSTANCE));
    FrontendPackage.eINSTANCE.eClass();
  }
  
  /**
   * Register for "*" and "xml" file extensions the FrontendResourceFactoryImpl factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected Map<String, Resource.Factory> getRegistrations()
  {
    if (registrations == null)
    {
      super.getRegistrations();
      registrations.put(XML_EXTENSION, new FrontendResourceFactoryImpl());
      registrations.put(STAR_EXTENSION, new FrontendResourceFactoryImpl());
    }
    return registrations;
  }

} //FrontendXMLProcessor

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

 // BEGIN GENERATED CODE
package gov.redhawk.model.sca;

import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Domain Manager Registry</b></em>'.
 * @since 8.0
 * @noimplement This interface is not intended to be implemented by clients.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.ScaDomainManagerRegistry#getDomains <em>Domains</em>}</li>
 * </ul>
 * </p>
 *
 * @see gov.redhawk.model.sca.ScaPackage#getScaDomainManagerRegistry()
 * @model extendedMetaData="name='ScaDomainManagerRegistry' kind='elementOnly'"
 * @generated
 */
public interface ScaDomainManagerRegistry extends IDisposable {
	/**
	 * Returns the value of the '<em><b>Domains</b></em>' containment reference list.
	 * The list contents are of type {@link gov.redhawk.model.sca.ScaDomainManager}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Domains</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Domains</em>' containment reference list.
	 * @see gov.redhawk.model.sca.ScaPackage#getScaDomainManagerRegistry_Domains()
	 * @model containment="true" resolveProxies="true"
	 *        extendedMetaData="kind='element' name='domain'"
	 * @generated
	 */
	EList<ScaDomainManager> getDomains();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	ScaDomainManager findDomain(String domainName);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model
	 * @generated
	 */
	ScaDomainManager createDomain(String name, boolean autoConnect, Map<String, String> connectionProperties);

	public static class Util {
		// END GENERATED CODE
		public static ScaDomainManagerRegistry getScaDomainManagerRegistry(final Resource resource) {
			final EObject obj = resource.getEObject("/");
			if (obj instanceof ScaDocumentRoot) {
				return ((ScaDocumentRoot) obj).getDomainManagerRegistry();
			} else if (obj instanceof ScaDomainManagerRegistry) {
				return ((ScaDomainManagerRegistry) obj);
			} else {
				return null;
			}
			// BEGIN GENERATED CODE
		}
	}

} // ScaDomainManagerRegistry

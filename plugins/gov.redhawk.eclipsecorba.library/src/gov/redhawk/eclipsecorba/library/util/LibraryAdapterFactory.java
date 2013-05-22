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
package gov.redhawk.eclipsecorba.library.util;

import gov.redhawk.eclipsecorba.idl.Commentable;
import gov.redhawk.eclipsecorba.idl.Definition;
import gov.redhawk.eclipsecorba.idl.Element;
import gov.redhawk.eclipsecorba.idl.FileRegion;
import gov.redhawk.eclipsecorba.idl.Identifiable;
import gov.redhawk.eclipsecorba.library.IdlLibrary;
import gov.redhawk.eclipsecorba.library.LibraryPackage;
import gov.redhawk.eclipsecorba.library.LocalFilePath;
import gov.redhawk.eclipsecorba.library.Path;
import gov.redhawk.eclipsecorba.library.PreferenceNodePathSet;
import gov.redhawk.eclipsecorba.library.RepositoryModule;
import gov.redhawk.eclipsecorba.library.URIPathSet;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see gov.redhawk.eclipsecorba.library.LibraryPackage
 * @generated
 */
public class LibraryAdapterFactory extends AdapterFactoryImpl {

	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static LibraryPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LibraryAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = LibraryPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LibrarySwitch<Adapter> modelSwitch =
		new LibrarySwitch<Adapter>() {
			@Override
			public Adapter caseIdlLibrary(IdlLibrary object) {
				return createIdlLibraryAdapter();
			}
			@Override
			public Adapter caseRepositoryModule(RepositoryModule object) {
				return createRepositoryModuleAdapter();
			}
			@Override
			public Adapter casePath(Path object) {
				return createPathAdapter();
			}
			@Override
			public Adapter casePreferenceNodePathSet(PreferenceNodePathSet object) {
				return createPreferenceNodePathSetAdapter();
			}
			@Override
			public Adapter caseURIPathSet(URIPathSet object) {
				return createURIPathSetAdapter();
			}
			@Override
			public Adapter caseLocalFilePath(LocalFilePath object) {
				return createLocalFilePathAdapter();
			}
			@Override
			public Adapter caseIdentifiable(Identifiable object) {
				return createIdentifiableAdapter();
			}
			@Override
			public Adapter caseFileRegion(FileRegion object) {
				return createFileRegionAdapter();
			}
			@Override
			public Adapter caseElement(Element object) {
				return createElementAdapter();
			}
			@Override
			public Adapter caseCommentable(Commentable object) {
				return createCommentableAdapter();
			}
			@Override
			public Adapter caseDefinition(Definition object) {
				return createDefinitionAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.library.IdlLibrary <em>Idl Library</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.library.IdlLibrary
	 * @generated
	 */
	public Adapter createIdlLibraryAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.library.RepositoryModule <em>Repository Module</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.library.RepositoryModule
	 * @generated
	 */
	public Adapter createRepositoryModuleAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.library.Path <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.library.Path
	 * @generated
	 */
	public Adapter createPathAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.library.PreferenceNodePathSet <em>Preference Node Path Set</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.library.PreferenceNodePathSet
	 * @generated
	 */
	public Adapter createPreferenceNodePathSetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.library.URIPathSet <em>URI Path Set</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.library.URIPathSet
	 * @generated
	 */
	public Adapter createURIPathSetAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.library.LocalFilePath <em>Local File Path</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.library.LocalFilePath
	 * @generated
	 */
	public Adapter createLocalFilePathAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.Identifiable <em>Identifiable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.Identifiable
	 * @generated
	 */
	public Adapter createIdentifiableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.FileRegion <em>File Region</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.FileRegion
	 * @generated
	 */
	public Adapter createFileRegionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.Element <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.Element
	 * @generated
	 */
	public Adapter createElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.Commentable <em>Commentable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.Commentable
	 * @generated
	 */
	public Adapter createCommentableAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link gov.redhawk.eclipsecorba.idl.Definition <em>Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see gov.redhawk.eclipsecorba.idl.Definition
	 * @generated
	 */
	public Adapter createDefinitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //LibraryAdapterFactory

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
package gov.redhawk.eclipsecorba.library;

import gov.redhawk.eclipsecorba.idl.IdlPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see gov.redhawk.eclipsecorba.library.LibraryFactory
 * @model kind="package"
 * @generated
 */
public interface LibraryPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "library";
	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///gov/redhawk/eclipsecorba/library";
	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "library";
	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	LibraryPackage eINSTANCE = gov.redhawk.eclipsecorba.library.impl.LibraryPackageImpl.init();
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.library.impl.RepositoryModuleImpl <em>Repository Module</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.library.impl.RepositoryModuleImpl
	 * @see gov.redhawk.eclipsecorba.library.impl.LibraryPackageImpl#getRepositoryModule()
	 * @generated
	 */
	int REPOSITORY_MODULE = 1;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_MODULE__NAME = IdlPackage.DEFINITION__NAME;
	/**
	 * The feature id for the '<em><b>Scoped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_MODULE__SCOPED_NAME = IdlPackage.DEFINITION__SCOPED_NAME;
	/**
	 * The feature id for the '<em><b>Rep Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_MODULE__REP_ID = IdlPackage.DEFINITION__REP_ID;
	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_MODULE__PREFIX = IdlPackage.DEFINITION__PREFIX;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_MODULE__VERSION = IdlPackage.DEFINITION__VERSION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_MODULE__ID = IdlPackage.DEFINITION__ID;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_MODULE__START_LINE = IdlPackage.DEFINITION__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_MODULE__START_COLUMN = IdlPackage.DEFINITION__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_MODULE__END_LINE = IdlPackage.DEFINITION__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_MODULE__END_COLUMN = IdlPackage.DEFINITION__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_MODULE__COMMENT = IdlPackage.DEFINITION__COMMENT;
	/**
	 * The feature id for the '<em><b>Module Definitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_MODULE__MODULE_DEFINITIONS = IdlPackage.DEFINITION_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Definitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_MODULE__DEFINITIONS = IdlPackage.DEFINITION_FEATURE_COUNT + 1;
	/**
	 * The number of structural features of the '<em>Repository Module</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_MODULE_FEATURE_COUNT = IdlPackage.DEFINITION_FEATURE_COUNT + 2;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.library.impl.IdlLibraryImpl <em>Idl Library</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.library.impl.IdlLibraryImpl
	 * @see gov.redhawk.eclipsecorba.library.impl.LibraryPackageImpl#getIdlLibrary()
	 * @generated
	 */
	int IDL_LIBRARY = 0;
	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_LIBRARY__NAME = REPOSITORY_MODULE__NAME;
	/**
	 * The feature id for the '<em><b>Scoped Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_LIBRARY__SCOPED_NAME = REPOSITORY_MODULE__SCOPED_NAME;
	/**
	 * The feature id for the '<em><b>Rep Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_LIBRARY__REP_ID = REPOSITORY_MODULE__REP_ID;
	/**
	 * The feature id for the '<em><b>Prefix</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_LIBRARY__PREFIX = REPOSITORY_MODULE__PREFIX;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_LIBRARY__VERSION = REPOSITORY_MODULE__VERSION;
	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_LIBRARY__ID = REPOSITORY_MODULE__ID;
	/**
	 * The feature id for the '<em><b>Start Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_LIBRARY__START_LINE = REPOSITORY_MODULE__START_LINE;
	/**
	 * The feature id for the '<em><b>Start Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_LIBRARY__START_COLUMN = REPOSITORY_MODULE__START_COLUMN;
	/**
	 * The feature id for the '<em><b>End Line</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_LIBRARY__END_LINE = REPOSITORY_MODULE__END_LINE;
	/**
	 * The feature id for the '<em><b>End Column</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_LIBRARY__END_COLUMN = REPOSITORY_MODULE__END_COLUMN;
	/**
	 * The feature id for the '<em><b>Comment</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_LIBRARY__COMMENT = REPOSITORY_MODULE__COMMENT;
	/**
	 * The feature id for the '<em><b>Module Definitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_LIBRARY__MODULE_DEFINITIONS = REPOSITORY_MODULE__MODULE_DEFINITIONS;
	/**
	 * The feature id for the '<em><b>Definitions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_LIBRARY__DEFINITIONS = REPOSITORY_MODULE__DEFINITIONS;
	/**
	 * The feature id for the '<em><b>Specifications</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_LIBRARY__SPECIFICATIONS = REPOSITORY_MODULE_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Paths</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_LIBRARY__PATHS = REPOSITORY_MODULE_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Load Status</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_LIBRARY__LOAD_STATUS = REPOSITORY_MODULE_FEATURE_COUNT + 2;
	/**
	 * The number of structural features of the '<em>Idl Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDL_LIBRARY_FEATURE_COUNT = REPOSITORY_MODULE_FEATURE_COUNT + 3;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.library.impl.PathImpl <em>Path</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.library.impl.PathImpl
	 * @see gov.redhawk.eclipsecorba.library.impl.LibraryPackageImpl#getPath()
	 * @generated
	 */
	int PATH = 2;
	/**
	 * The feature id for the '<em><b>Derived Path</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH__DERIVED_PATH = 0;
	/**
	 * The number of structural features of the '<em>Path</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PATH_FEATURE_COUNT = 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.library.impl.PreferenceNodePathSetImpl <em>Preference Node Path Set</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.library.impl.PreferenceNodePathSetImpl
	 * @see gov.redhawk.eclipsecorba.library.impl.LibraryPackageImpl#getPreferenceNodePathSet()
	 * @generated
	 */
	int PREFERENCE_NODE_PATH_SET = 3;
	/**
	 * The feature id for the '<em><b>Derived Path</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFERENCE_NODE_PATH_SET__DERIVED_PATH = PATH__DERIVED_PATH;
	/**
	 * The feature id for the '<em><b>Qualifier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFERENCE_NODE_PATH_SET__QUALIFIER = PATH_FEATURE_COUNT + 0;
	/**
	 * The feature id for the '<em><b>Key</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFERENCE_NODE_PATH_SET__KEY = PATH_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Delimiter</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFERENCE_NODE_PATH_SET__DELIMITER = PATH_FEATURE_COUNT + 2;
	/**
	 * The feature id for the '<em><b>File Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFERENCE_NODE_PATH_SET__FILE_URI = PATH_FEATURE_COUNT + 3;
	/**
	 * The feature id for the '<em><b>Replace Env</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFERENCE_NODE_PATH_SET__REPLACE_ENV = PATH_FEATURE_COUNT + 4;
	/**
	 * The number of structural features of the '<em>Preference Node Path Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PREFERENCE_NODE_PATH_SET_FEATURE_COUNT = PATH_FEATURE_COUNT + 5;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.library.impl.URIPathSetImpl <em>URI Path Set</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.library.impl.URIPathSetImpl
	 * @see gov.redhawk.eclipsecorba.library.impl.LibraryPackageImpl#getURIPathSet()
	 * @generated
	 */
	int URI_PATH_SET = 4;
	/**
	 * The feature id for the '<em><b>Derived Path</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URI_PATH_SET__DERIVED_PATH = PATH__DERIVED_PATH;
	/**
	 * The feature id for the '<em><b>Dirs</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URI_PATH_SET__DIRS = PATH_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>URI Path Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int URI_PATH_SET_FEATURE_COUNT = PATH_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '{@link gov.redhawk.eclipsecorba.library.impl.LocalFilePathImpl <em>Local File Path</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see gov.redhawk.eclipsecorba.library.impl.LocalFilePathImpl
	 * @see gov.redhawk.eclipsecorba.library.impl.LibraryPackageImpl#getLocalFilePath()
	 * @generated
	 */
	int LOCAL_FILE_PATH = 5;
	/**
	 * The feature id for the '<em><b>Derived Path</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_FILE_PATH__DERIVED_PATH = PATH__DERIVED_PATH;
	/**
	 * The feature id for the '<em><b>Local Paths</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_FILE_PATH__LOCAL_PATHS = PATH_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Local File Path</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCAL_FILE_PATH_FEATURE_COUNT = PATH_FEATURE_COUNT + 1;
	/**
	 * The meta object id for the '<em>URI</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.common.util.URI
	 * @see gov.redhawk.eclipsecorba.library.impl.LibraryPackageImpl#getURI()
	 * @generated
	 */
	int URI = 6;
	/**
	 * The meta object id for the '<em>IProgress Monitor</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.core.runtime.IProgressMonitor
	 * @see gov.redhawk.eclipsecorba.library.impl.LibraryPackageImpl#getIProgressMonitor()
	 * @generated
	 */
	int IPROGRESS_MONITOR = 7;
	/**
	 * The meta object id for the '<em>Core Exceptoin</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.core.runtime.CoreException
	 * @see gov.redhawk.eclipsecorba.library.impl.LibraryPackageImpl#getCoreExceptoin()
	 * @generated
	 */
	int CORE_EXCEPTOIN = 8;
	/**
	 * The meta object id for the '<em>IPath</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.core.runtime.IPath
	 * @see gov.redhawk.eclipsecorba.library.impl.LibraryPackageImpl#getIPath()
	 * @generated
	 */
	int IPATH = 9;
	/**
	 * The meta object id for the '<em>IStatus</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.core.runtime.IStatus
	 * @see gov.redhawk.eclipsecorba.library.impl.LibraryPackageImpl#getIStatus()
	 * @generated
	 */
	int ISTATUS = 10;

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.library.IdlLibrary <em>Idl Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Idl Library</em>'.
	 * @see gov.redhawk.eclipsecorba.library.IdlLibrary
	 * @generated
	 */
	EClass getIdlLibrary();

	/**
	 * Returns the meta object for the reference list '{@link gov.redhawk.eclipsecorba.library.IdlLibrary#getSpecifications <em>Specifications</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Specifications</em>'.
	 * @see gov.redhawk.eclipsecorba.library.IdlLibrary#getSpecifications()
	 * @see #getIdlLibrary()
	 * @generated
	 */
	EReference getIdlLibrary_Specifications();

	/**
	 * Returns the meta object for the containment reference list '{@link gov.redhawk.eclipsecorba.library.IdlLibrary#getPaths <em>Paths</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Paths</em>'.
	 * @see gov.redhawk.eclipsecorba.library.IdlLibrary#getPaths()
	 * @see #getIdlLibrary()
	 * @generated
	 */
	EReference getIdlLibrary_Paths();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.library.IdlLibrary#getLoadStatus <em>Load Status</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Load Status</em>'.
	 * @see gov.redhawk.eclipsecorba.library.IdlLibrary#getLoadStatus()
	 * @see #getIdlLibrary()
	 * @generated
	 */
	EAttribute getIdlLibrary_LoadStatus();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.library.RepositoryModule <em>Repository Module</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Repository Module</em>'.
	 * @see gov.redhawk.eclipsecorba.library.RepositoryModule
	 * @generated
	 */
	EClass getRepositoryModule();

	/**
	 * Returns the meta object for the reference list '{@link gov.redhawk.eclipsecorba.library.RepositoryModule#getModuleDefinitions <em>Module Definitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Module Definitions</em>'.
	 * @see gov.redhawk.eclipsecorba.library.RepositoryModule#getModuleDefinitions()
	 * @see #getRepositoryModule()
	 * @generated
	 */
	EReference getRepositoryModule_ModuleDefinitions();

	/**
	 * Returns the meta object for the reference list '{@link gov.redhawk.eclipsecorba.library.RepositoryModule#getDefinitions <em>Definitions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Definitions</em>'.
	 * @see gov.redhawk.eclipsecorba.library.RepositoryModule#getDefinitions()
	 * @see #getRepositoryModule()
	 * @generated
	 */
	EReference getRepositoryModule_Definitions();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.library.Path <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Path</em>'.
	 * @see gov.redhawk.eclipsecorba.library.Path
	 * @generated
	 */
	EClass getPath();

	/**
	 * Returns the meta object for the attribute list '{@link gov.redhawk.eclipsecorba.library.Path#getDerivedPath <em>Derived Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Derived Path</em>'.
	 * @see gov.redhawk.eclipsecorba.library.Path#getDerivedPath()
	 * @see #getPath()
	 * @generated
	 */
	EAttribute getPath_DerivedPath();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.library.PreferenceNodePathSet <em>Preference Node Path Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Preference Node Path Set</em>'.
	 * @see gov.redhawk.eclipsecorba.library.PreferenceNodePathSet
	 * @generated
	 */
	EClass getPreferenceNodePathSet();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.library.PreferenceNodePathSet#getQualifier <em>Qualifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Qualifier</em>'.
	 * @see gov.redhawk.eclipsecorba.library.PreferenceNodePathSet#getQualifier()
	 * @see #getPreferenceNodePathSet()
	 * @generated
	 */
	EAttribute getPreferenceNodePathSet_Qualifier();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.library.PreferenceNodePathSet#getKey <em>Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Key</em>'.
	 * @see gov.redhawk.eclipsecorba.library.PreferenceNodePathSet#getKey()
	 * @see #getPreferenceNodePathSet()
	 * @generated
	 */
	EAttribute getPreferenceNodePathSet_Key();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.library.PreferenceNodePathSet#getDelimiter <em>Delimiter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Delimiter</em>'.
	 * @see gov.redhawk.eclipsecorba.library.PreferenceNodePathSet#getDelimiter()
	 * @see #getPreferenceNodePathSet()
	 * @generated
	 */
	EAttribute getPreferenceNodePathSet_Delimiter();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.library.PreferenceNodePathSet#isFileUri <em>File Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File Uri</em>'.
	 * @see gov.redhawk.eclipsecorba.library.PreferenceNodePathSet#isFileUri()
	 * @see #getPreferenceNodePathSet()
	 * @generated
	 */
	EAttribute getPreferenceNodePathSet_FileUri();

	/**
	 * Returns the meta object for the attribute '{@link gov.redhawk.eclipsecorba.library.PreferenceNodePathSet#isReplaceEnv <em>Replace Env</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Replace Env</em>'.
	 * @see gov.redhawk.eclipsecorba.library.PreferenceNodePathSet#isReplaceEnv()
	 * @see #getPreferenceNodePathSet()
	 * @generated
	 */
	EAttribute getPreferenceNodePathSet_ReplaceEnv();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.library.URIPathSet <em>URI Path Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>URI Path Set</em>'.
	 * @see gov.redhawk.eclipsecorba.library.URIPathSet
	 * @generated
	 */
	EClass getURIPathSet();

	/**
	 * Returns the meta object for the attribute list '{@link gov.redhawk.eclipsecorba.library.URIPathSet#getDirs <em>Dirs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Dirs</em>'.
	 * @see gov.redhawk.eclipsecorba.library.URIPathSet#getDirs()
	 * @see #getURIPathSet()
	 * @generated
	 */
	EAttribute getURIPathSet_Dirs();

	/**
	 * Returns the meta object for class '{@link gov.redhawk.eclipsecorba.library.LocalFilePath <em>Local File Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Local File Path</em>'.
	 * @see gov.redhawk.eclipsecorba.library.LocalFilePath
	 * @generated
	 */
	EClass getLocalFilePath();

	/**
	 * Returns the meta object for the attribute list '{@link gov.redhawk.eclipsecorba.library.LocalFilePath#getLocalPaths <em>Local Paths</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Local Paths</em>'.
	 * @see gov.redhawk.eclipsecorba.library.LocalFilePath#getLocalPaths()
	 * @see #getLocalFilePath()
	 * @generated
	 */
	EAttribute getLocalFilePath_LocalPaths();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.emf.common.util.URI <em>URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>URI</em>'.
	 * @see org.eclipse.emf.common.util.URI
	 * @model instanceClass="org.eclipse.emf.common.util.URI"
	 * @generated
	 */
	EDataType getURI();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.core.runtime.IProgressMonitor <em>IProgress Monitor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>IProgress Monitor</em>'.
	 * @see org.eclipse.core.runtime.IProgressMonitor
	 * @model instanceClass="org.eclipse.core.runtime.IProgressMonitor" serializeable="false"
	 * @generated
	 */
	EDataType getIProgressMonitor();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.core.runtime.CoreException <em>Core Exceptoin</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Core Exceptoin</em>'.
	 * @see org.eclipse.core.runtime.CoreException
	 * @model instanceClass="org.eclipse.core.runtime.CoreException" serializeable="false"
	 * @generated
	 */
	EDataType getCoreExceptoin();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.core.runtime.IPath <em>IPath</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>IPath</em>'.
	 * @see org.eclipse.core.runtime.IPath
	 * @model instanceClass="org.eclipse.core.runtime.IPath"
	 * @generated
	 */
	EDataType getIPath();

	/**
	 * Returns the meta object for data type '{@link org.eclipse.core.runtime.IStatus <em>IStatus</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>IStatus</em>'.
	 * @see org.eclipse.core.runtime.IStatus
	 * @model instanceClass="org.eclipse.core.runtime.IStatus" serializeable="false"
	 * @generated
	 */
	EDataType getIStatus();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	LibraryFactory getLibraryFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {

		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.library.impl.IdlLibraryImpl <em>Idl Library</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.library.impl.IdlLibraryImpl
		 * @see gov.redhawk.eclipsecorba.library.impl.LibraryPackageImpl#getIdlLibrary()
		 * @generated
		 */
		EClass IDL_LIBRARY = eINSTANCE.getIdlLibrary();
		/**
		 * The meta object literal for the '<em><b>Specifications</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IDL_LIBRARY__SPECIFICATIONS = eINSTANCE.getIdlLibrary_Specifications();
		/**
		 * The meta object literal for the '<em><b>Paths</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IDL_LIBRARY__PATHS = eINSTANCE.getIdlLibrary_Paths();
		/**
		 * The meta object literal for the '<em><b>Load Status</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDL_LIBRARY__LOAD_STATUS = eINSTANCE.getIdlLibrary_LoadStatus();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.library.impl.RepositoryModuleImpl <em>Repository Module</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.library.impl.RepositoryModuleImpl
		 * @see gov.redhawk.eclipsecorba.library.impl.LibraryPackageImpl#getRepositoryModule()
		 * @generated
		 */
		EClass REPOSITORY_MODULE = eINSTANCE.getRepositoryModule();
		/**
		 * The meta object literal for the '<em><b>Module Definitions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REPOSITORY_MODULE__MODULE_DEFINITIONS = eINSTANCE.getRepositoryModule_ModuleDefinitions();
		/**
		 * The meta object literal for the '<em><b>Definitions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference REPOSITORY_MODULE__DEFINITIONS = eINSTANCE.getRepositoryModule_Definitions();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.library.impl.PathImpl <em>Path</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.library.impl.PathImpl
		 * @see gov.redhawk.eclipsecorba.library.impl.LibraryPackageImpl#getPath()
		 * @generated
		 */
		EClass PATH = eINSTANCE.getPath();
		/**
		 * The meta object literal for the '<em><b>Derived Path</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PATH__DERIVED_PATH = eINSTANCE.getPath_DerivedPath();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.library.impl.PreferenceNodePathSetImpl <em>Preference Node Path Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.library.impl.PreferenceNodePathSetImpl
		 * @see gov.redhawk.eclipsecorba.library.impl.LibraryPackageImpl#getPreferenceNodePathSet()
		 * @generated
		 */
		EClass PREFERENCE_NODE_PATH_SET = eINSTANCE.getPreferenceNodePathSet();
		/**
		 * The meta object literal for the '<em><b>Qualifier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREFERENCE_NODE_PATH_SET__QUALIFIER = eINSTANCE.getPreferenceNodePathSet_Qualifier();
		/**
		 * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREFERENCE_NODE_PATH_SET__KEY = eINSTANCE.getPreferenceNodePathSet_Key();
		/**
		 * The meta object literal for the '<em><b>Delimiter</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREFERENCE_NODE_PATH_SET__DELIMITER = eINSTANCE.getPreferenceNodePathSet_Delimiter();
		/**
		 * The meta object literal for the '<em><b>File Uri</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREFERENCE_NODE_PATH_SET__FILE_URI = eINSTANCE.getPreferenceNodePathSet_FileUri();
		/**
		 * The meta object literal for the '<em><b>Replace Env</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PREFERENCE_NODE_PATH_SET__REPLACE_ENV = eINSTANCE.getPreferenceNodePathSet_ReplaceEnv();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.library.impl.URIPathSetImpl <em>URI Path Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.library.impl.URIPathSetImpl
		 * @see gov.redhawk.eclipsecorba.library.impl.LibraryPackageImpl#getURIPathSet()
		 * @generated
		 */
		EClass URI_PATH_SET = eINSTANCE.getURIPathSet();
		/**
		 * The meta object literal for the '<em><b>Dirs</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute URI_PATH_SET__DIRS = eINSTANCE.getURIPathSet_Dirs();
		/**
		 * The meta object literal for the '{@link gov.redhawk.eclipsecorba.library.impl.LocalFilePathImpl <em>Local File Path</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see gov.redhawk.eclipsecorba.library.impl.LocalFilePathImpl
		 * @see gov.redhawk.eclipsecorba.library.impl.LibraryPackageImpl#getLocalFilePath()
		 * @generated
		 */
		EClass LOCAL_FILE_PATH = eINSTANCE.getLocalFilePath();
		/**
		 * The meta object literal for the '<em><b>Local Paths</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCAL_FILE_PATH__LOCAL_PATHS = eINSTANCE.getLocalFilePath_LocalPaths();
		/**
		 * The meta object literal for the '<em>URI</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.emf.common.util.URI
		 * @see gov.redhawk.eclipsecorba.library.impl.LibraryPackageImpl#getURI()
		 * @generated
		 */
		EDataType URI = eINSTANCE.getURI();
		/**
		 * The meta object literal for the '<em>IProgress Monitor</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.core.runtime.IProgressMonitor
		 * @see gov.redhawk.eclipsecorba.library.impl.LibraryPackageImpl#getIProgressMonitor()
		 * @generated
		 */
		EDataType IPROGRESS_MONITOR = eINSTANCE.getIProgressMonitor();
		/**
		 * The meta object literal for the '<em>Core Exceptoin</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.core.runtime.CoreException
		 * @see gov.redhawk.eclipsecorba.library.impl.LibraryPackageImpl#getCoreExceptoin()
		 * @generated
		 */
		EDataType CORE_EXCEPTOIN = eINSTANCE.getCoreExceptoin();
		/**
		 * The meta object literal for the '<em>IPath</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.core.runtime.IPath
		 * @see gov.redhawk.eclipsecorba.library.impl.LibraryPackageImpl#getIPath()
		 * @generated
		 */
		EDataType IPATH = eINSTANCE.getIPath();
		/**
		 * The meta object literal for the '<em>IStatus</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.core.runtime.IStatus
		 * @see gov.redhawk.eclipsecorba.library.impl.LibraryPackageImpl#getIStatus()
		 * @generated
		 */
		EDataType ISTATUS = eINSTANCE.getIStatus();

	}

} //LibraryPackage

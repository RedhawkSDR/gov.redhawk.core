/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

 // BEGIN GENERATED CODE
package gov.redhawk.model.sca.impl;

import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaFileStore;
import gov.redhawk.model.sca.ScaFileSystem;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.ScaPackage;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.filesystem.IFileSystem;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import CF.File;
import CF.FileException;
import CF.FileSystem;
import CF.FileSystemOperations;
import CF.InvalidFileName;
import CF.PropertiesHolder;
import CF.FileSystemPackage.FileInformationType;
import CF.FileSystemPackage.UnknownFileSystemProperties;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>File System</b></em>'.
 * @since 12.0
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaFileSystemImpl#getFileStore <em>File Store</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaFileSystemImpl#getChildren <em>Children</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaFileSystemImpl#getImageDesc <em>Image Desc</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaFileSystemImpl#isDirectory <em>Directory</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaFileSystemImpl#getName <em>Name</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaFileSystemImpl#getFileSystemURI <em>File System URI</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class ScaFileSystemImpl< F extends FileSystem > extends CorbaObjWrapperImpl<F> implements ScaFileSystem<F> {
	/**
	 * The default value of the '{@link #getFileStore() <em>File Store</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileStore()
	 * @generated
	 * @ordered
	 */
	protected static final IFileStore FILE_STORE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFileStore() <em>File Store</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileStore()
	 * @generated
	 * @ordered
	 */
	protected IFileStore fileStore = FILE_STORE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildren()
	 * @generated
	 * @ordered
	 */
	protected EList<ScaFileStore> children;

	/**
	 * The default value of the '{@link #getImageDesc() <em>Image Desc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImageDesc()
	 * @generated
	 * @ordered
	 */
	protected static final Object IMAGE_DESC_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getImageDesc() <em>Image Desc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImageDesc()
	 * @generated
	 * @ordered
	 */
	protected Object imageDesc = IMAGE_DESC_EDEFAULT;

	/**
	 * This is true if the Image Desc attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean imageDescESet;

	/**
	 * The default value of the '{@link #isDirectory() <em>Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDirectory()
	 * @generated NOT
	 * @ordered
	 */
	protected static final boolean DIRECTORY_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isDirectory() <em>Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDirectory()
	 * @generated
	 * @ordered
	 */
	protected boolean directory = DIRECTORY_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getFileSystemURI() <em>File System URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileSystemURI()
	 * @generated
	 * @ordered
	 */
	protected static final URI FILE_SYSTEM_URI_EDEFAULT = (URI)ScaFactory.eINSTANCE.createFromString(ScaPackage.eINSTANCE.getURI(), "");

	/**
	 * The cached value of the '{@link #getFileSystemURI() <em>File System URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFileSystemURI()
	 * @generated
	 * @ordered
	 */
	protected URI fileSystemURI = FILE_SYSTEM_URI_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScaFileSystemImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScaPackage.Literals.SCA_FILE_SYSTEM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 18.0
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific type known in this context.
	 * @generated
	 */
	@Override
	public void setObj(F newObj) {
		super.setObj(newObj);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IFileStore getFileStore() {
		return fileStore;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFileStore(IFileStore newFileStore) {
		IFileStore oldFileStore = fileStore;
		fileStore = newFileStore;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_FILE_SYSTEM__FILE_STORE, oldFileStore, fileStore));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ScaFileStore> getChildren() {
		if (children == null) {
			children = new EObjectContainmentEList.Unsettable.Resolving<ScaFileStore>(ScaFileStore.class, this, ScaPackage.SCA_FILE_SYSTEM__CHILDREN);
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetChildren() {
		if (children != null) ((InternalEList.Unsettable<?>)children).unset();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetChildren() {
		return children != null && ((InternalEList.Unsettable<?>)children).isSet();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object getImageDesc() {
		return imageDesc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImageDesc(Object newImageDesc) {
		Object oldImageDesc = imageDesc;
		imageDesc = newImageDesc;
		boolean oldImageDescESet = imageDescESet;
		imageDescESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_FILE_SYSTEM__IMAGE_DESC, oldImageDesc, imageDesc, !oldImageDescESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetImageDesc() {
		Object oldImageDesc = imageDesc;
		boolean oldImageDescESet = imageDescESet;
		imageDesc = IMAGE_DESC_EDEFAULT;
		imageDescESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, ScaPackage.SCA_FILE_SYSTEM__IMAGE_DESC, oldImageDesc, IMAGE_DESC_EDEFAULT, oldImageDescESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetImageDesc() {
		return imageDescESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDirectory() {
		return directory;
	}

	/**
	 * The default value of the '{@link #getRootFileStore() <em>Root File Store</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * @since 9.0
	 * <!-- end-user-doc -->
	 * @see #getRootFileStore()
	 * @generated NOT
	 * @ordered
	 * 
	 */
	protected static final IFileStore ROOT_FILE_STORE_EDEFAULT = EFS.getNullFileSystem().getStore(new Path("/"));

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setDirectory(boolean newDirectory) {
		// DO nothing this should always be a directory
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_FILE_SYSTEM__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public URI getFileSystemURI() {
		return fileSystemURI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFileSystemURIGen(URI newFileSystemURI) {
		URI oldFileSystemURI = fileSystemURI;
		fileSystemURI = newFileSystemURI;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_FILE_SYSTEM__FILE_SYSTEM_URI, oldFileSystemURI, fileSystemURI));
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * @since 9.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setFileSystemURI(URI newFileSystemURI) {
		setFileSystemURIGen(newFileSystemURI);
		IFileSystem fileSystem = null;
		if (newFileSystemURI != null) {
			try {
				fileSystem = EFS.getFileSystem(newFileSystemURI.getScheme());
				setFileStore(fileSystem.getStore(newFileSystemURI));
				setStatus(ScaPackage.Literals.SCA_FILE_SYSTEM__FILE_SYSTEM_URI, Status.OK_STATUS);
			} catch (final CoreException e) {
				setStatus(ScaPackage.Literals.SCA_FILE_SYSTEM__FILE_SYSTEM_URI, new Status(Status.ERROR, ScaModelPlugin.ID, "Error in resolving file system.", e));
			}
		} else {
			setFileStore(null);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public org.eclipse.emf.common.util.URI createURI(String path) {
		// END GENERATED CODE
		if (path == null) {
			return null;
		}
		org.eclipse.emf.common.util.URI retVal = null;
		if (fileStore == null) {
			return null;
		}
		try {
			retVal = org.eclipse.emf.common.util.URI.createURI(path);
			if (retVal.scheme() == null) {
				retVal = org.eclipse.emf.common.util.URI.createURI(fileStore.getFileStore(new Path(path)).toURI().toString());
			}
		} catch (Exception e) {
			retVal = org.eclipse.emf.common.util.URI.createURI(fileStore.getFileStore(new Path(path)).toURI().toString());
		}
		return retVal;
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public EList<ScaFileStore> fetchChildren(IProgressMonitor monitor) {
		// END GENERATED CODE
		ScaFileStoreImpl.internalFetchChildren(monitor, this);
		return getChildren();
		// BEGIN GENERATED CODE
	}

	public void copy(final String sourceFileName, final String destinationFileName) throws InvalidFileName, FileException {
		final FileSystem fileSys = fetchNarrowedObject(null);
		if (fileSys == null) {
			throw new IllegalStateException();
		}
		fileSys.copy(sourceFileName, destinationFileName);
	}
	
	/**
     * @since 14.0
     */
	public void move(String sourceFileName, String destinationFileName) throws InvalidFileName, FileException {
		final FileSystem fileSys = fetchNarrowedObject(null);
		if (fileSys == null) {
			throw new IllegalStateException();
		}
		fileSys.move(sourceFileName, destinationFileName);
	}

	/**
	 * @since 14.0
	 */
	public File create(final String fileName) throws InvalidFileName, FileException {
		final FileSystem fileSys = fetchNarrowedObject(null);
		if (fileSys == null) {
			throw new IllegalStateException();
		}
		return fileSys.create(fileName);
	}

	public boolean exists(final String fileName) throws InvalidFileName {
		final FileSystem fileSys = fetchNarrowedObject(null);
		if (fileSys == null) {
			throw new IllegalStateException();
		}
		return fileSys.exists(fileName);
	}

	/**
	 * @since 14.0
	 */
	public FileInformationType[] list(final String pattern) throws FileException, InvalidFileName {
		final FileSystem fileSys = fetchNarrowedObject(null);
		if (fileSys == null) {
			throw new IllegalStateException();
		}
		return fileSys.list(pattern);
	}

	public void mkdir(final String directoryName) throws InvalidFileName, FileException {
		final FileSystem fileSys = fetchNarrowedObject(null);
		if (fileSys == null) {
			throw new IllegalStateException();
		}
		fileSys.mkdir(directoryName);
	}

	/**
	 * @since 14.0
	 */
	public File open(final String fileName, final boolean read_Only) throws InvalidFileName, FileException {
		final FileSystem fileSys = fetchNarrowedObject(null);
		if (fileSys == null) {
			throw new IllegalStateException();
		}
		return fileSys.open(fileName, read_Only);
	}

	/**
	 * @since 14.0
	 */
	public void query(final PropertiesHolder fileSystemProperties) throws UnknownFileSystemProperties {
		final FileSystem fileSys = fetchNarrowedObject(null);
		if (fileSys == null) {
			throw new IllegalStateException();
		}
		fileSys.query(fileSystemProperties);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ScaPackage.SCA_FILE_SYSTEM__CHILDREN:
				return ((InternalEList<?>)getChildren()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ScaPackage.SCA_FILE_SYSTEM__FILE_STORE:
				return getFileStore();
			case ScaPackage.SCA_FILE_SYSTEM__CHILDREN:
				return getChildren();
			case ScaPackage.SCA_FILE_SYSTEM__IMAGE_DESC:
				return getImageDesc();
			case ScaPackage.SCA_FILE_SYSTEM__DIRECTORY:
				return isDirectory();
			case ScaPackage.SCA_FILE_SYSTEM__NAME:
				return getName();
			case ScaPackage.SCA_FILE_SYSTEM__FILE_SYSTEM_URI:
				return getFileSystemURI();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ScaPackage.SCA_FILE_SYSTEM__FILE_STORE:
				setFileStore((IFileStore)newValue);
				return;
			case ScaPackage.SCA_FILE_SYSTEM__CHILDREN:
				getChildren().clear();
				getChildren().addAll((Collection<? extends ScaFileStore>)newValue);
				return;
			case ScaPackage.SCA_FILE_SYSTEM__IMAGE_DESC:
				setImageDesc(newValue);
				return;
			case ScaPackage.SCA_FILE_SYSTEM__DIRECTORY:
				setDirectory((Boolean)newValue);
				return;
			case ScaPackage.SCA_FILE_SYSTEM__NAME:
				setName((String)newValue);
				return;
			case ScaPackage.SCA_FILE_SYSTEM__FILE_SYSTEM_URI:
				setFileSystemURI((URI)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ScaPackage.SCA_FILE_SYSTEM__FILE_STORE:
				setFileStore(FILE_STORE_EDEFAULT);
				return;
			case ScaPackage.SCA_FILE_SYSTEM__CHILDREN:
				unsetChildren();
				return;
			case ScaPackage.SCA_FILE_SYSTEM__IMAGE_DESC:
				unsetImageDesc();
				return;
			case ScaPackage.SCA_FILE_SYSTEM__DIRECTORY:
				setDirectory(DIRECTORY_EDEFAULT);
				return;
			case ScaPackage.SCA_FILE_SYSTEM__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ScaPackage.SCA_FILE_SYSTEM__FILE_SYSTEM_URI:
				setFileSystemURI(FILE_SYSTEM_URI_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ScaPackage.SCA_FILE_SYSTEM__FILE_STORE:
				return FILE_STORE_EDEFAULT == null ? fileStore != null : !FILE_STORE_EDEFAULT.equals(fileStore);
			case ScaPackage.SCA_FILE_SYSTEM__CHILDREN:
				return isSetChildren();
			case ScaPackage.SCA_FILE_SYSTEM__IMAGE_DESC:
				return isSetImageDesc();
			case ScaPackage.SCA_FILE_SYSTEM__DIRECTORY:
				return directory != DIRECTORY_EDEFAULT;
			case ScaPackage.SCA_FILE_SYSTEM__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ScaPackage.SCA_FILE_SYSTEM__FILE_SYSTEM_URI:
				return FILE_SYSTEM_URI_EDEFAULT == null ? fileSystemURI != null : !FILE_SYSTEM_URI_EDEFAULT.equals(fileSystemURI);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == FileSystemOperations.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == ScaFileStore.class) {
			switch (derivedFeatureID) {
				case ScaPackage.SCA_FILE_SYSTEM__FILE_STORE: return ScaPackage.SCA_FILE_STORE__FILE_STORE;
				case ScaPackage.SCA_FILE_SYSTEM__CHILDREN: return ScaPackage.SCA_FILE_STORE__CHILDREN;
				case ScaPackage.SCA_FILE_SYSTEM__IMAGE_DESC: return ScaPackage.SCA_FILE_STORE__IMAGE_DESC;
				case ScaPackage.SCA_FILE_SYSTEM__DIRECTORY: return ScaPackage.SCA_FILE_STORE__DIRECTORY;
				case ScaPackage.SCA_FILE_SYSTEM__NAME: return ScaPackage.SCA_FILE_STORE__NAME;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == FileSystemOperations.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == ScaFileStore.class) {
			switch (baseFeatureID) {
				case ScaPackage.SCA_FILE_STORE__FILE_STORE: return ScaPackage.SCA_FILE_SYSTEM__FILE_STORE;
				case ScaPackage.SCA_FILE_STORE__CHILDREN: return ScaPackage.SCA_FILE_SYSTEM__CHILDREN;
				case ScaPackage.SCA_FILE_STORE__IMAGE_DESC: return ScaPackage.SCA_FILE_SYSTEM__IMAGE_DESC;
				case ScaPackage.SCA_FILE_STORE__DIRECTORY: return ScaPackage.SCA_FILE_SYSTEM__DIRECTORY;
				case ScaPackage.SCA_FILE_STORE__NAME: return ScaPackage.SCA_FILE_SYSTEM__NAME;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (fileStore: ");
		result.append(fileStore);
		result.append(", imageDesc: ");
		if (imageDescESet) result.append(imageDesc); else result.append("<unset>");
		result.append(", directory: ");
		result.append(directory);
		result.append(", name: ");
		result.append(name);
		result.append(", fileSystemURI: ");
		result.append(fileSystemURI);
		result.append(')');
		return result.toString();
	}

	public void remove(final String fileName) throws FileException, InvalidFileName {
		final FileSystem fileSys = fetchNarrowedObject(null);
		if (fileSys == null) {
			throw new IllegalStateException();
		}
		fileSys.remove(fileName);
	}

	public void rmdir(final String directoryName) throws InvalidFileName, FileException {
		final FileSystem fileSys = fetchNarrowedObject(null);
		if (fileSys == null) {
			throw new IllegalStateException();
		}
		fileSys.rmdir(directoryName);
	}
	

	protected void internalFetchChildren(IProgressMonitor monitor) {
	    ScaFileStoreImpl.internalFetchChildren(monitor, this);
    }
	
	@Override
	public void setIor(String newIor) {
	    super.setIor(newIor);
	    setFileSystemURI(createFileSystemURI());
	}
	
	/**
	 * @since 14.0
	 * @return
	 */
    protected URI createFileSystemURI() {
		// END GENERATED CODE
		try {
			return Util.createFileSystemURI(ior);
		} catch (final URISyntaxException e) {
			return null;
		}
		// BEGIN GENERATED CODE
    }
} //ScaFileSystemImpl

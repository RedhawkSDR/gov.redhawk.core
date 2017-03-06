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

import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaFileStore;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.commands.ScaFileStoreMergeChildrenCommand;
import gov.redhawk.model.sca.commands.ScaFileStoreMergeChildrenCommand.FileStoreData;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.commands.UnsetLocalAttributeCommand;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.transaction.RunnableWithResult;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>File Store</b></em>'.
 * 
 * @since 12.0
 *        <!-- end-user-doc -->
 *        <p>
 *        The following features are implemented:
 *        </p>
 *        <ul>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaFileStoreImpl#getFileStore <em>File Store</em>}</li>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaFileStoreImpl#getChildren <em>Children</em>}</li>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaFileStoreImpl#getImageDesc <em>Image Desc</em>}</li>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaFileStoreImpl#isDirectory <em>Directory</em>}</li>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaFileStoreImpl#getName <em>Name</em>}</li>
 *        </ul>
 *
 * @generated
 */
public class ScaFileStoreImpl extends IStatusProviderImpl implements ScaFileStore {
	/**
	 * The default value of the '{@link #getFileStore() <em>File Store</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getFileStore()
	 * @generated
	 * @ordered
	 */
	protected static final IFileStore FILE_STORE_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getFileStore() <em>File Store</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getFileStore()
	 * @generated
	 * @ordered
	 */
	protected IFileStore fileStore = FILE_STORE_EDEFAULT;
	/**
	 * The cached value of the '{@link #getChildren() <em>Children</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getChildren()
	 * @generated
	 * @ordered
	 */
	protected EList<ScaFileStore> children;
	/**
	 * The default value of the '{@link #getImageDesc() <em>Image Desc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getImageDesc()
	 * @generated
	 * @ordered
	 */
	protected static final Object IMAGE_DESC_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getImageDesc() <em>Image Desc</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getImageDesc()
	 * @generated
	 * @ordered
	 */
	protected Object imageDesc = IMAGE_DESC_EDEFAULT;
	/**
	 * This is true if the Image Desc attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean imageDescESet;
	/**
	 * The default value of the '{@link #isDirectory() <em>Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #isDirectory()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DIRECTORY_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isDirectory() <em>Directory</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #isDirectory()
	 * @generated
	 * @ordered
	 */
	protected boolean directory = DIRECTORY_EDEFAULT;
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ScaFileStoreImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScaPackage.Literals.SCA_FILE_STORE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IFileStore getFileStore() {
		return fileStore;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public void setFileStore(IFileStore newFileStore) {
		setFileStoreGen(newFileStore);
		setName(newFileStore.getName());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setFileStoreGen(IFileStore newFileStore) {
		IFileStore oldFileStore = fileStore;
		fileStore = newFileStore;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_FILE_STORE__FILE_STORE, oldFileStore, fileStore));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<ScaFileStore> getChildren() {
		if (children == null) {
			children = new EObjectContainmentEList.Unsettable.Resolving<ScaFileStore>(ScaFileStore.class, this, ScaPackage.SCA_FILE_STORE__CHILDREN);
		}
		return children;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void unsetChildren() {
		if (children != null)
			((InternalEList.Unsettable< ? >) children).unset();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isSetChildren() {
		return children != null && ((InternalEList.Unsettable< ? >) children).isSet();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getImageDesc() {
		return imageDesc;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setImageDesc(Object newImageDesc) {
		Object oldImageDesc = imageDesc;
		imageDesc = newImageDesc;
		boolean oldImageDescESet = imageDescESet;
		imageDescESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_FILE_STORE__IMAGE_DESC, oldImageDesc, imageDesc, !oldImageDescESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void unsetImageDesc() {
		Object oldImageDesc = imageDesc;
		boolean oldImageDescESet = imageDescESet;
		imageDesc = IMAGE_DESC_EDEFAULT;
		imageDescESet = false;
		if (eNotificationRequired())
			eNotify(
				new ENotificationImpl(this, Notification.UNSET, ScaPackage.SCA_FILE_STORE__IMAGE_DESC, oldImageDesc, IMAGE_DESC_EDEFAULT, oldImageDescESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isSetImageDesc() {
		return imageDescESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isDirectory() {
		return directory;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setDirectory(boolean newDirectory) {
		boolean oldDirectory = directory;
		directory = newDirectory;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_FILE_STORE__DIRECTORY, oldDirectory, directory));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_FILE_STORE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 14.0
	 *        <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<ScaFileStore> fetchChildren(IProgressMonitor monitor) {
		// END GENERATED CODE
		ScaFileStoreImpl.internalFetchChildren(monitor, this);
		try {
			return ScaModelCommand.runExclusive(this, new RunnableWithResult.Impl<EList<ScaFileStore>>() {

				@Override
				public void run() {
					setResult(ECollections.unmodifiableEList(new BasicEList<ScaFileStore>(getChildren())));
				}

			});
		} catch (InterruptedException e) {
			return ECollections.emptyEList();
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ScaPackage.SCA_FILE_STORE__CHILDREN:
			return ((InternalEList< ? >) getChildren()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ScaPackage.SCA_FILE_STORE__FILE_STORE:
			return getFileStore();
		case ScaPackage.SCA_FILE_STORE__CHILDREN:
			return getChildren();
		case ScaPackage.SCA_FILE_STORE__IMAGE_DESC:
			return getImageDesc();
		case ScaPackage.SCA_FILE_STORE__DIRECTORY:
			return isDirectory();
		case ScaPackage.SCA_FILE_STORE__NAME:
			return getName();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ScaPackage.SCA_FILE_STORE__FILE_STORE:
			setFileStore((IFileStore) newValue);
			return;
		case ScaPackage.SCA_FILE_STORE__CHILDREN:
			getChildren().clear();
			getChildren().addAll((Collection< ? extends ScaFileStore>) newValue);
			return;
		case ScaPackage.SCA_FILE_STORE__IMAGE_DESC:
			setImageDesc(newValue);
			return;
		case ScaPackage.SCA_FILE_STORE__DIRECTORY:
			setDirectory((Boolean) newValue);
			return;
		case ScaPackage.SCA_FILE_STORE__NAME:
			setName((String) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case ScaPackage.SCA_FILE_STORE__FILE_STORE:
			setFileStore(FILE_STORE_EDEFAULT);
			return;
		case ScaPackage.SCA_FILE_STORE__CHILDREN:
			unsetChildren();
			return;
		case ScaPackage.SCA_FILE_STORE__IMAGE_DESC:
			unsetImageDesc();
			return;
		case ScaPackage.SCA_FILE_STORE__DIRECTORY:
			setDirectory(DIRECTORY_EDEFAULT);
			return;
		case ScaPackage.SCA_FILE_STORE__NAME:
			setName(NAME_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case ScaPackage.SCA_FILE_STORE__FILE_STORE:
			return FILE_STORE_EDEFAULT == null ? fileStore != null : !FILE_STORE_EDEFAULT.equals(fileStore);
		case ScaPackage.SCA_FILE_STORE__CHILDREN:
			return isSetChildren();
		case ScaPackage.SCA_FILE_STORE__IMAGE_DESC:
			return isSetImageDesc();
		case ScaPackage.SCA_FILE_STORE__DIRECTORY:
			return directory != DIRECTORY_EDEFAULT;
		case ScaPackage.SCA_FILE_STORE__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (fileStore: ");
		result.append(fileStore);
		result.append(", imageDesc: ");
		if (imageDescESet)
			result.append(imageDesc);
		else
			result.append("<unset>");
		result.append(", directory: ");
		result.append(directory);
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

	/**
	 * @since 14.0
	 */
	protected void internalFetchChildren(IProgressMonitor monitor) {
		ScaFileStoreImpl.internalFetchChildren(monitor, this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 14.0
	 *        <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void refresh(IProgressMonitor monitor, RefreshDepth depth) throws InterruptedException {
		// END GENERATED CODE
		switch (depth) {
		case FULL:
		case CHILDREN:
			internalFetchChildren(monitor);
			break;
		default:
			break;
		}
		// BEGIN GENERATED CODE
	}

	static void internalFetchChildren(IProgressMonitor monitor, final ScaFileStore parentStore) {
		// END GENERATED CODE
		IFileStore store = parentStore.getFileStore();
		if (store == null) {
			return;
		}

		final int WORK_FETCH_CHILD_STORES = 45;
		final int WORK_FETCH_CHILD_INFOS = 45;
		final int WORK_MODEL_UPDATE = 5;
		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching children of " + store.getName(), WORK_FETCH_CHILD_STORES + WORK_FETCH_CHILD_INFOS + WORK_MODEL_UPDATE);

		try {
			final IFileStore[] childStores = store.childStores(EFS.NONE, subMonitor.split(WORK_FETCH_CHILD_STORES));

			SubMonitor setupStoreMap = subMonitor.newChild(WORK_FETCH_CHILD_INFOS).setWorkRemaining(childStores.length);
			final Map<String, FileStoreData> newChildrenMap = new HashMap<String, FileStoreData>();
			for (final IFileStore childStore : childStores) {
				boolean isDirectory = childStore.fetchInfo(EFS.NONE, setupStoreMap.split(1)).isDirectory();
				FileStoreData data = new FileStoreData(childStore, isDirectory);
				newChildrenMap.put(childStore.getName(), data);
				setupStoreMap.worked(1);
			}

			ScaModelCommand.execute(parentStore, new ScaFileStoreMergeChildrenCommand(parentStore, newChildrenMap, Status.OK_STATUS));
			subMonitor.worked(WORK_MODEL_UPDATE);
		} catch (final CoreException e) {
			IStatus status = new Status(e.getStatus().getSeverity(), ScaModelPlugin.ID, e.getLocalizedMessage(), e);
			ScaModelCommand.execute(parentStore, new UnsetLocalAttributeCommand(parentStore, status, ScaPackage.Literals.SCA_FILE_STORE__CHILDREN));
		} finally {
			subMonitor.done();
		}
		// BEGIN GENERATED CODE
	}

} // ScaFileStoreImpl

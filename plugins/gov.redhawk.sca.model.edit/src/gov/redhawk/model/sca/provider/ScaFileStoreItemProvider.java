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
package gov.redhawk.model.sca.provider;

import gov.redhawk.model.sca.ScaFileStore;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.sca.util.SilentJob;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemColorProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITableItemColorProvider;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.PlatformUI;

/**
 * This is the item provider adapter for a {@link gov.redhawk.model.sca.ScaFileStore} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ScaFileStoreItemProvider extends IStatusProviderItemProvider implements IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource, ITableItemLabelProvider, ITableItemColorProvider, IItemColorProvider {
	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaFileStoreItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addFileStorePropertyDescriptor(object);
			addDirectoryPropertyDescriptor(object);
			addNamePropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the File Store feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addFileStorePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ScaFileStore_fileStore_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ScaFileStore_fileStore_feature", "_UI_ScaFileStore_type"),
				 ScaPackage.Literals.SCA_FILE_STORE__FILE_STORE,
				 false,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Directory feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addDirectoryPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ScaFileStore_directory_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ScaFileStore_directory_feature", "_UI_ScaFileStore_type"),
				 ScaPackage.Literals.SCA_FILE_STORE__DIRECTORY,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Name feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addNamePropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_ScaFileStore_name_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_ScaFileStore_name_feature", "_UI_ScaFileStore_type"),
				 ScaPackage.Literals.SCA_FILE_STORE__NAME,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(ScaPackage.Literals.SCA_FILE_STORE__CHILDREN);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	@Override
	public boolean hasChildren(Object object) {
		return ((ScaFileStore) object).isDirectory();
	}

	private static class FetchImageDescJob extends SilentJob {
		private ScaFileStore scaFileStore;

		public FetchImageDescJob(ScaFileStore store) {
			super("Fetching Image for: " + store.getName());
			this.scaFileStore = store;
		}

		@Override
		protected IStatus runSilent(IProgressMonitor monitor) {
			IFileStore fileStore = scaFileStore.getFileStore();
			if (fileStore == null) {
				return Status.OK_STATUS;
			}
			final String fileName = fileStore.getName();

			IContentType contentType = null;
			try {
				final InputStream is = fileStore.openInputStream(EFS.NONE, null);
				final IContentDescription contDesc = Platform.getContentTypeManager().getDescriptionFor(is, fileName, IContentDescription.ALL);
				if (contDesc != null) {
					contentType = contDesc.getContentType();
				}
			} catch (final CoreException e) {
				contentType = null;
			} catch (final IOException e) {
				contentType = null;
			}

			final ImageDescriptor imageDescriptor = PlatformUI.getWorkbench().getEditorRegistry().getImageDescriptor(fileName, contentType);
			TransactionalEditingDomain domain = TransactionUtil.getEditingDomain(scaFileStore);
			if (domain != null) {
				domain.getCommandStack().execute(SetCommand.create(domain, scaFileStore, ScaPackage.Literals.SCA_FILE_STORE__IMAGE_DESC, imageDescriptor));
			}
			return Status.OK_STATUS;
		}

	};

	private static final ImageDescriptor DEFAULT_IMG = PlatformUI.getWorkbench().getEditorRegistry().getImageDescriptor("txt");

	/**
	 * This returns ScaFileStore.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Object getImage(final Object object) {
		// END GENERATED CODE
		ScaFileStore store = (ScaFileStore) object;
		if (store.isDirectory()) {
			return overlayImage(object, getResourceLocator().getImage("full/obj16/ScaDirectory"));
		}
		if (store.getImageDesc() == null) {
			new FetchImageDescJob(store).schedule();
			return DEFAULT_IMG;
		}
		return store.getImageDesc();
		// BEGIN GENERATED CODE
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getTextGen(Object object) {
		String label = ((ScaFileStore)object).getName();
		return label == null || label.length() == 0 ?
			getString("_UI_ScaFileStore_type") :
			getString("_UI_ScaFileStore_type") + " " + label;
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public String getText(final Object object) {
		// END GENERATED CODE
		return ((ScaFileStore) object).getName();
		// BEGIN GENERATED CODE
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(ScaFileStore.class)) {
		case ScaPackage.SCA_FILE_STORE__FILE_STORE:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, true));
			return;
		case ScaPackage.SCA_FILE_STORE__IMAGE_DESC:
		case ScaPackage.SCA_FILE_STORE__DIRECTORY:
		case ScaPackage.SCA_FILE_STORE__NAME:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
			return;
		case ScaPackage.SCA_FILE_STORE__CHILDREN:
			fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
			return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);
	}

}

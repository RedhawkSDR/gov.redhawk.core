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
package gov.redhawk.ui.util;

import gov.redhawk.ui.RedhawkUiActivator;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.window.Window;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.views.navigator.ResourceComparator;

/**
 * 
 */
public final class EntryUtil {

	/**
	 * Instantiates a new entry util.
	 */
	private EntryUtil() {
	}

	/**
	 * Browse.
	 * 
	 * @param resource the resource
	 * @param endsWith the ends with
	 * @param project the project, the root project for the resource
	 * @return the string
	 */
	public static String browse(final IProject project, final IResource resource, final String endsWith) {
		final ElementTreeSelectionDialog dialog = new ElementTreeSelectionDialog(
				RedhawkUiActivator.getActiveWorkbenchShell(), new WorkbenchLabelProvider(),
				new WorkbenchContentProvider());
		dialog.setInput(project.getWorkspace());
		if (resource != null) {
			dialog.setInitialSelection(resource);
		}
		dialog.addFilter(new ViewerFilter() {
			@Override
			public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
				if (element instanceof IProject) {
					return ((IProject) element).equals(project);
				}
				if (element instanceof IFile) {
					if (endsWith == null) {
						return true;
					}
					final IFile file = (IFile) element;
					return file.getName().endsWith(endsWith);
				}
				if (element instanceof IFolder) {
					return ((IFolder) element).getProject().equals(project);
				}
				return false;
			}
		});
		dialog.setComparator(new ResourceComparator(ResourceComparator.TYPE));
		dialog.setAllowMultiple(false);
		dialog.setTitle("Resource Attribute Value");
		dialog.setMessage("Select a resource:");
		dialog.setValidator(new ISelectionStatusValidator() {
			@Override
			public IStatus validate(final Object[] selection) {
				if (selection != null && selection.length > 0
						&& (selection[0] instanceof IFile || selection[0] instanceof IContainer)) {
					return new Status(IStatus.OK, RedhawkUiActivator.getPluginId(), IStatus.OK, "", null); //$NON-NLS-1$
				}

				return new Status(IStatus.ERROR, RedhawkUiActivator.getPluginId(), IStatus.ERROR, "", null); //$NON-NLS-1$
			}
		});
		if (dialog.open() == Window.OK) {
			final IResource res = (IResource) dialog.getFirstResult();
			IPath path = res.getProjectRelativePath();
			if (res instanceof IContainer) {
				path = path.addTrailingSeparator();
			}
			final String value = path.toString();
			return value;
		}
		return (resource == null) ? null : resource.getName(); //SUPPRESS CHECKSTYLE AvoidInLine
	}

	/**
	 * Browse.
	 * 
	 * @param resource the current resource
	 * @param project the project, the root project for the resource
	 * @return the string
	 */
	public static IFolder browseDir(final IProject project, final IFolder resource) {
		final ElementTreeSelectionDialog dialog = new ElementTreeSelectionDialog(
				RedhawkUiActivator.getActiveWorkbenchShell(), new WorkbenchLabelProvider(),
				new WorkbenchContentProvider());
		dialog.setInput(project);
		if (resource != null) {
			dialog.setInitialSelection(resource);
		}
		dialog.addFilter(new ViewerFilter() {
			@Override
			public boolean select(final Viewer viewer, final Object parentElement, final Object element) {
				if (element instanceof IFolder) {
					return ((IFolder) element).getProject().equals(project);
				}
				return false;
			}
		});
		dialog.setComparator(new ResourceComparator(ResourceComparator.TYPE));
		dialog.setAllowMultiple(false);
		dialog.setTitle("Resource Attribute Value");
		dialog.setMessage("Select a directory:");
		dialog.setValidator(new ISelectionStatusValidator() {
			@Override
			public IStatus validate(final Object[] selection) {
				if (selection != null && selection.length > 0
						&& (selection[0] instanceof IFile || selection[0] instanceof IContainer)) {
					return new Status(IStatus.OK, RedhawkUiActivator.getPluginId(), IStatus.OK, "", null); //$NON-NLS-1$
				}

				return new Status(IStatus.ERROR, RedhawkUiActivator.getPluginId(), IStatus.ERROR, "", null); //$NON-NLS-1$
			}
		});
		if (dialog.open() == Window.OK) {
			final IResource res = (IResource) dialog.getFirstResult();
			IPath path = res.getProjectRelativePath();
			if (res instanceof IContainer) {
				path = path.addTrailingSeparator();
			}
			final String value = path.toString();
			return project.getFolder(value);
		}
		return resource;
	}

}

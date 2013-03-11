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
/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Alexander Fedorov <Alexander.Fedorov@borland.com>
 *     		- Bug 172000 [Wizards] WizardNewFileCreationPage should support overwriting existing resources
 *******************************************************************************/
package gov.redhawk.ui.parts;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.Iterator;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceStatus;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.undo.CreateFileOperation;
import org.eclipse.ui.ide.undo.WorkspaceUndoUtil;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.ui.internal.ide.IIDEHelpContextIds;
import org.eclipse.ui.internal.ide.dialogs.CreateLinkedResourceGroup;

/**
 * Standard main page for a wizard that creates a file resource.
 * <p>
 * This page may be used by clients as-is; it may be also be subclassed to suit.
 * </p>
 * <p>
 * Subclasses may override
 * <ul>
 * <li><code>getInitialContents</code></li>
 * <li><code>getNewFileLabel</code></li>
 * </ul>
 * </p>
 * <p>
 * Subclasses may extend
 * <ul>
 * <li><code>handleEvent</code></li>
 * </ul>
 * </p>
 */
public class WizardNewFileCreationPage extends WizardPage implements Listener {
	private static final int SIZING_CONTAINER_GROUP_HEIGHT = 250;

	// the current resource selection
	private final IStructuredSelection currentSelection;

	// cache of newly-created file
	private IFile newFile;

	private URI linkTargetPath;

	// widgets
	private ResourceAndContainerGroup resourceGroup;

	private Button advancedButton;

	private CreateLinkedResourceGroup linkedResourceGroup;

	private Composite linkedResourceParent;

	private Composite linkedResourceComposite;

	// initial value stores
	private String initialFileName;

	/**
	 * The file extension to use for this page's file name field when it does
	 * not exist yet.
	 * 
	 * @see WizardNewFileCreationPage#setFileExtension(String)
	 */
	private String initialFileExtension;

	private IPath initialContainerFullPath;

	private boolean initialAllowExistingResources = false;

	/**
	 * Height of the "advanced" linked resource group. Set when the advanced
	 * group is first made visible.
	 */
	private int linkedResourceGroupHeight = -1;

	/**
	 * First time the advanced group is validated.
	 */
	private boolean firstLinkCheck = true;

	private final IProject project;

	/**
	 * Creates a new file creation wizard page. If the initial resource
	 * selection contains exactly one container resource then it will be used as
	 * the default container resource.
	 * 
	 * @param pageName the name of the page
	 * @param selection the current resource selection
	 */
	public WizardNewFileCreationPage(final String pageName, final IStructuredSelection selection, final IProject project) {
		super(pageName);
		setPageComplete(false);
		this.currentSelection = selection;
		this.project = project;
	}

	/**
	 * Creates the widget for advanced options.
	 * 
	 * @param parent the parent composite
	 */
	protected void createAdvancedControls(final Composite parent) {
		final boolean disableLinking = Platform.getPreferencesService().getBoolean(ResourcesPlugin.PI_RESOURCES,
		        ResourcesPlugin.PREF_DISABLE_LINKING,
		        false,
		        null);
		if (!disableLinking) {
			this.linkedResourceParent = new Composite(parent, SWT.NONE);
			this.linkedResourceParent.setFont(parent.getFont());
			this.linkedResourceParent.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			final GridLayout layout = new GridLayout();
			layout.marginHeight = 0;
			layout.marginWidth = 0;
			this.linkedResourceParent.setLayout(layout);

			this.advancedButton = new Button(this.linkedResourceParent, SWT.PUSH);
			this.advancedButton.setFont(this.linkedResourceParent.getFont());
			this.advancedButton.setText(IDEWorkbenchMessages.showAdvanced);
			final GridData data = setButtonLayoutData(this.advancedButton);
			data.horizontalAlignment = GridData.BEGINNING;
			this.advancedButton.setLayoutData(data);
			this.advancedButton.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(final SelectionEvent e) {
					handleAdvancedButtonSelect();
				}
			});
		}
		this.linkedResourceGroup = new CreateLinkedResourceGroup(IResource.FILE, new Listener() {
			public void handleEvent(final Event e) {
				setPageComplete(validatePage());
				WizardNewFileCreationPage.this.firstLinkCheck = false;
			}
		}, new CreateLinkedResourceGroup.IStringValue() {
			public void setValue(final String string) {
				WizardNewFileCreationPage.this.resourceGroup.setResource(string);
			}

			public String getValue() {
				return WizardNewFileCreationPage.this.resourceGroup.getResource();
			}

			public IResource getResource() {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}

	/**
	 * (non-Javadoc) Method declared on IDialogPage.
	 */
	public void createControl(final Composite parent) {
		initializeDialogUnits(parent);
		// top level group
		final Composite topLevel = new Composite(parent, SWT.NONE);
		topLevel.setLayout(new GridLayout());
		topLevel.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_FILL | GridData.HORIZONTAL_ALIGN_FILL));
		topLevel.setFont(parent.getFont());
		PlatformUI.getWorkbench().getHelpSystem().setHelp(topLevel, IIDEHelpContextIds.NEW_FILE_WIZARD_PAGE);

		// resource and container group
		this.resourceGroup = new ResourceAndContainerGroup(topLevel,
		        this,
		        this.project,
		        getNewFileLabel(),
		        IDEWorkbenchMessages.WizardNewFileCreationPage_file,
		        false,
		        WizardNewFileCreationPage.SIZING_CONTAINER_GROUP_HEIGHT);
		this.resourceGroup.setAllowExistingResources(this.initialAllowExistingResources);
		initialPopulateContainerNameField();
		createAdvancedControls(topLevel);
		if (this.initialFileName != null) {
			this.resourceGroup.setResource(this.initialFileName);
		}
		if (this.initialFileExtension != null) {
			this.resourceGroup.setResourceExtension(this.initialFileExtension);
		}
		validatePage();
		// Show description on opening
		setErrorMessage(null);
		setMessage(null);
		setControl(topLevel);
	}

	/**
	 * Creates a file resource given the file handle and contents.
	 * 
	 * @param fileHandle the file handle to create a file resource with
	 * @param contents the initial contents of the new file resource, or
	 *            <code>null</code> if none (equivalent to an empty stream)
	 * @param monitor the progress monitor to show visual progress with
	 * @exception CoreException if the operation fails
	 * @exception OperationCanceledException if the operation is canceled
	 * @deprecated As of 3.3, use or override {@link #createNewFile()} which
	 *             uses the undoable operation support. To supply customized
	 *             file content for a subclass, use
	 *             {@link #getInitialContents()}.
	 */
	@Deprecated
	protected void createFile(final IFile fileHandle, InputStream contents, final IProgressMonitor monitor) throws CoreException {
		if (contents == null) {
			contents = new ByteArrayInputStream(new byte[0]);
		}

		try {
			// Create a new file resource in the workspace
			if (this.linkTargetPath != null) {
				fileHandle.createLink(this.linkTargetPath, IResource.ALLOW_MISSING_LOCAL, monitor);
			} else {
				final IPath path = fileHandle.getFullPath();
				final IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
				final int numSegments = path.segmentCount();
				if (numSegments > 2 && !root.getFolder(path.removeLastSegments(1)).exists()) {
					// If the direct parent of the path doesn't exist, try to
					// create the
					// necessary directories.
					for (int i = numSegments - 2; i > 0; i--) {
						final IFolder folder = root.getFolder(path.removeLastSegments(i));
						if (!folder.exists()) {
							folder.create(false, true, monitor);
						}
					}
				}
				fileHandle.create(contents, false, monitor);
			}
		} catch (final CoreException e) {
			// If the file already existed locally, just refresh to get contents
			if (e.getStatus().getCode() == IResourceStatus.PATH_OCCUPIED) {
				fileHandle.refreshLocal(IResource.DEPTH_ZERO, null);
			} else {
				throw e;
			}
		}

		if (monitor.isCanceled()) {
			throw new OperationCanceledException();
		}
	}

	/**
	 * Creates a file resource handle for the file with the given workspace
	 * path. This method does not create the file resource; this is the
	 * responsibility of <code>createFile</code>.
	 * 
	 * @param filePath the path of the file resource to create a handle for
	 * @return the new file resource handle
	 * @see #createFile
	 */
	protected IFile createFileHandle(final IPath filePath) {
		return ResourcesPlugin.getWorkspace().getRoot().getFile(filePath);
	}

	/**
	 * Creates the link target path if a link target has been specified.
	 */
	protected void createLinkTarget() {
		this.linkTargetPath = this.linkedResourceGroup.getLinkTargetURI();
	}

	/**
	 * Creates a new file resource in the selected container and with the
	 * selected name. Creates any missing resource containers along the path;
	 * does nothing if the container resources already exist.
	 * <p>
	 * In normal usage, this method is invoked after the user has pressed Finish
	 * on the wizard; the enablement of the Finish button implies that all
	 * controls on on this page currently contain valid values.
	 * </p>
	 * <p>
	 * Note that this page caches the new file once it has been successfully
	 * created; subsequent invocations of this method will answer the same file
	 * resource without attempting to create it again.
	 * </p>
	 * <p>
	 * This method should be called within a workspace modify operation since it
	 * creates resources.
	 * </p>
	 * 
	 * @return the created file resource, or <code>null</code> if the file was
	 *         not created
	 */
	public IFile createNewFile() {
		if (this.newFile != null) {
			return this.newFile;
		}

		// create the new file and cache it if successful

		final IPath containerPath = this.resourceGroup.getContainerFullPath();
		final IPath newFilePath = containerPath.append(this.resourceGroup.getResource());
		final IFile newFileHandle = createFileHandle(newFilePath);
		final InputStream initialContents = getInitialContents();

		createLinkTarget();
		final IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(final IProgressMonitor monitor) {
				final CreateFileOperation op = new CreateFileOperation(newFileHandle,
				        WizardNewFileCreationPage.this.linkTargetPath,
				        initialContents,
				        IDEWorkbenchMessages.WizardNewFileCreationPage_title);
				try {
					PlatformUI.getWorkbench().getOperationSupport().getOperationHistory().execute(op, monitor, WorkspaceUndoUtil.getUIInfoAdapter(getShell()));
				} catch (final ExecutionException e) {
					getContainer().getShell().getDisplay().asyncExec(new Runnable() {
						public void run() {
							if (e.getCause() instanceof CoreException) {
								ErrorDialog.openError(getContainer().getShell(), // Was
								        // Utilities.getFocusShell()
								        IDEWorkbenchMessages.WizardNewFileCreationPage_errorTitle,
								        null, // no special
								        // message
								        ((CoreException) e.getCause()).getStatus());
							} else {
								IDEWorkbenchPlugin.log(getClass(), "createNewFile()", e.getCause()); //$NON-NLS-1$
								MessageDialog.openError(getContainer().getShell(),
								        IDEWorkbenchMessages.WizardNewFileCreationPage_internalErrorTitle,
								        NLS.bind(IDEWorkbenchMessages.WizardNewFileCreationPage_internalErrorMessage, e.getCause().getMessage()));
							}
						}
					});
				}
			}
		};
		try {
			getContainer().run(true, true, op);
		} catch (final InterruptedException e) {
			return null;
		} catch (final InvocationTargetException e) {
			// Execution Exceptions are handled above but we may still get
			// unexpected runtime errors.
			IDEWorkbenchPlugin.log(getClass(), "createNewFile()", e.getTargetException()); //$NON-NLS-1$
			MessageDialog.openError(getContainer().getShell(),
			        IDEWorkbenchMessages.WizardNewFileCreationPage_internalErrorTitle,
			        NLS.bind(IDEWorkbenchMessages.WizardNewFileCreationPage_internalErrorMessage, e.getTargetException().getMessage()));

			return null;
		}

		this.newFile = newFileHandle;

		return this.newFile;
	}

	/**
	 * Returns the scheduling rule to use when creating the resource at the
	 * given container path. The rule should be the creation rule for the
	 * top-most non-existing parent.
	 * 
	 * @param resource The resource being created
	 * @return The scheduling rule for creating the given resource
	 * @deprecated As of 3.3, scheduling rules are provided by the undoable
	 *             operation that this page creates and executes.
	 */
	@Deprecated
	protected ISchedulingRule createRule(IResource resource) {
		IResource parent = resource.getParent();
		while (parent != null) {
			if (parent.exists()) {
				return resource.getWorkspace().getRuleFactory().createRule(resource);
			}
			resource = parent;
			parent = parent.getParent();
		}
		return resource.getWorkspace().getRoot();
	}

	/**
	 * Returns the current full path of the containing resource as entered or
	 * selected by the user, or its anticipated initial value.
	 * 
	 * @return the container's full path, anticipated initial value, or
	 *         <code>null</code> if no path is known
	 */
	public IPath getContainerFullPath() {
		return this.resourceGroup.getContainerFullPath();
	}

	/**
	 * Returns the current file name as entered by the user, or its anticipated
	 * initial value. <br>
	 * <br>
	 * The current file name will include the file extension if the
	 * preconditions are met.
	 * 
	 * @see WizardNewFileCreationPage#setFileExtension(String)
	 * @return the file name, its anticipated initial value, or
	 *         <code>null</code> if no file name is known
	 */
	public String getFileName() {
		if (this.resourceGroup == null) {
			return this.initialFileName;
		}

		return this.resourceGroup.getResource();
	}

	/**
	 * Returns the file extension to use when creating the new file.
	 * 
	 * @return the file extension or <code>null</code>.
	 * @see WizardNewFileCreationPage#setFileExtension(String)
	 */
	public String getFileExtension() {
		if (this.resourceGroup == null) {
			return this.initialFileExtension;
		}
		return this.resourceGroup.getResourceExtension();
	}

	/**
	 * Returns a stream containing the initial contents to be given to new file
	 * resource instances. <b>Subclasses</b> may wish to override. This default
	 * implementation provides no initial contents.
	 * 
	 * @return initial contents to be given to new file resource instances
	 */
	protected InputStream getInitialContents() {
		return null;
	}

	/**
	 * Returns the label to display in the file name specification visual
	 * component group.
	 * <p>
	 * Subclasses may reimplement.
	 * </p>
	 * 
	 * @return the label to display in the file name specification visual
	 *         component group
	 */
	protected String getNewFileLabel() {
		return IDEWorkbenchMessages.WizardNewFileCreationPage_fileLabel;
	}

	/**
	 * Shows/hides the advanced option widgets.
	 */
	protected void handleAdvancedButtonSelect() {
		final Shell shell = getShell();
		final Point shellSize = shell.getSize();
		final Composite composite = (Composite) getControl();

		if (this.linkedResourceComposite != null) {
			this.linkedResourceComposite.dispose();
			this.linkedResourceComposite = null;
			composite.layout();
			shell.setSize(shellSize.x, shellSize.y - this.linkedResourceGroupHeight);
			this.advancedButton.setText(IDEWorkbenchMessages.showAdvanced);
		} else {
			this.linkedResourceComposite = this.linkedResourceGroup.createContents(this.linkedResourceParent);
			if (this.linkedResourceGroupHeight == -1) {
				final Point groupSize = this.linkedResourceComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT, true);
				this.linkedResourceGroupHeight = groupSize.y;
			}
			shell.setSize(shellSize.x, shellSize.y + this.linkedResourceGroupHeight);
			composite.layout();
			this.advancedButton.setText(IDEWorkbenchMessages.hideAdvanced);
		}
	}

	/**
	 * The <code>WizardNewFileCreationPage</code> implementation of this
	 * <code>Listener</code> method handles all events and enablements for
	 * controls on this page. Subclasses may extend.
	 */
	public void handleEvent(final Event event) {
		setPageComplete(validatePage());
	}

	/**
	 * Sets the initial contents of the container name entry field, based upon
	 * either a previously-specified initial value or the ability to determine
	 * such a value.
	 */
	protected void initialPopulateContainerNameField() {
		if (this.initialContainerFullPath != null) {
			this.resourceGroup.setContainerFullPath(this.initialContainerFullPath);
		} else {
			final Iterator< ? > it = this.currentSelection.iterator();
			if (it.hasNext()) {
				final Object object = it.next();
				IResource selectedResource = null;
				if (object instanceof IResource) {
					selectedResource = (IResource) object;
				} else if (object instanceof IAdaptable) {
					selectedResource = (IResource) ((IAdaptable) object).getAdapter(IResource.class);
				}
				if (selectedResource != null) {
					if (selectedResource.getType() == IResource.FILE) {
						selectedResource = selectedResource.getParent();
					}
					if (selectedResource.isAccessible()) {
						this.resourceGroup.setContainerFullPath(selectedResource.getFullPath());
					}
				}
			}
		}
	}

	/**
	 * Sets the flag indicating whether existing resources are permitted to be
	 * specified on this page.
	 * 
	 * @param value <code>true</code> if existing resources are permitted, and
	 *            <code>false</code> otherwise
	 */
	public void setAllowExistingResources(final boolean value) {
		if (this.resourceGroup == null) {
			this.initialAllowExistingResources = value;
		} else {
			this.resourceGroup.setAllowExistingResources(value);
		}
	}

	/**
	 * Sets the value of this page's container name field, or stores it for
	 * future use if this page's controls do not exist yet.
	 * 
	 * @param path the full path to the container
	 */
	public void setContainerFullPath(final IPath path) {
		if (this.resourceGroup == null) {
			this.initialContainerFullPath = path;
		} else {
			this.resourceGroup.setContainerFullPath(path);
		}
	}

	/**
	 * Sets the value of this page's file name field, or stores it for future
	 * use if this page's controls do not exist yet.
	 * 
	 * @param value new file name
	 */
	public void setFileName(final String value) {
		if (this.resourceGroup == null) {
			this.initialFileName = value;
		} else {
			this.resourceGroup.setResource(value);
		}
	}

	/**
	 * Set the only file extension allowed for this page's file name field. If
	 * this page's controls do not exist yet, store it for future use. <br>
	 * <br>
	 * If a file extension is specified, then it will always be appended with a
	 * '.' to the text from the file name field for validation when the
	 * following conditions are met: <br>
	 * <br>
	 * (1) File extension length is greater than 0 <br>
	 * (2) File name field text length is greater than 0 <br>
	 * (3) File name field text does not already end with a '.' and the file
	 * extension specified (case sensitive) <br>
	 * <br>
	 * The file extension will not be reflected in the actual file name field
	 * until the file name field loses focus.
	 * 
	 * @param value The file extension without the '.' prefix (e.g. 'java',
	 *            'xml')
	 */
	public void setFileExtension(final String value) {
		if (this.resourceGroup == null) {
			this.initialFileExtension = value;
		} else {
			this.resourceGroup.setResourceExtension(value);
		}
	}

	/**
	 * Checks whether the linked resource target is valid. Sets the error
	 * message accordingly and returns the status.
	 * 
	 * @return IStatus validation result from the CreateLinkedResourceGroup
	 */
	protected IStatus validateLinkedResource() {
		final IPath containerPath = this.resourceGroup.getContainerFullPath();
		final IPath newFilePath = containerPath.append(this.resourceGroup.getResource());
		final IFile newFileHandle = createFileHandle(newFilePath);
		final IStatus status = this.linkedResourceGroup.validateLinkLocation(newFileHandle);

		if (status.getSeverity() == IStatus.ERROR) {
			if (this.firstLinkCheck) {
				setMessage(status.getMessage());
			} else {
				setErrorMessage(status.getMessage());
			}
		} else if (status.getSeverity() == IStatus.WARNING) {
			setMessage(status.getMessage(), IMessageProvider.WARNING);
			setErrorMessage(null);
		}
		return status;
	}

	/**
	 * Returns whether this page's controls currently all contain valid values.
	 * 
	 * @return <code>true</code> if all controls are valid, and
	 *         <code>false</code> if at least one is invalid
	 */
	protected boolean validatePage() {
		boolean valid = true;

		if (!this.resourceGroup.areAllValuesValid()) {
			// if blank name then fail silently
			if (this.resourceGroup.getProblemType() == ResourceAndContainerGroup.PROBLEM_RESOURCE_EMPTY
			        || this.resourceGroup.getProblemType() == ResourceAndContainerGroup.PROBLEM_CONTAINER_EMPTY) {
				setMessage(this.resourceGroup.getProblemMessage());
				setErrorMessage(null);
			} else {
				setErrorMessage(this.resourceGroup.getProblemMessage());
			}
			valid = false;
		}

		final String resourceName = this.resourceGroup.getResource();
		final IWorkspace workspace = ResourcesPlugin.getWorkspace();
		final IStatus result = workspace.validateName(resourceName, IResource.FILE);
		if (!result.isOK()) {
			setErrorMessage(result.getMessage());
			return false;
		}

		IStatus linkedResourceStatus = null;
		if (valid) {
			linkedResourceStatus = validateLinkedResource();
			if (linkedResourceStatus.getSeverity() == IStatus.ERROR) {
				valid = false;
			}
		}
		// validateLinkedResource sets messages itself
		if (valid && (linkedResourceStatus == null || linkedResourceStatus.isOK())) {
			setMessage(null);
			setErrorMessage(null);

			//perform "resource exists" check if it was skipped in ResourceAndContainerGroup
			if (this.resourceGroup.getAllowExistingResources()) {
				final String problemMessage = NLS.bind(IDEWorkbenchMessages.ResourceGroup_nameExists, getFileName());
				final IPath resourcePath = getContainerFullPath().append(getFileName());
				if (workspace.getRoot().getFolder(resourcePath).exists()) {
					setErrorMessage(problemMessage);
					valid = false;
				}
				if (workspace.getRoot().getFile(resourcePath).exists()) {
					setMessage(problemMessage, IMessageProvider.WARNING);
				}
			}
		}
		return valid;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.DialogPage#setVisible(boolean)
	 */
	@Override
	public void setVisible(final boolean visible) {
		super.setVisible(visible);
		if (visible) {
			this.resourceGroup.setFocus();
		}
	}
}

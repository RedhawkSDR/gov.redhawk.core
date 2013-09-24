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
/*******************************************************************************
 * Copyright (c) 2000, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *      Alexander Fedorov <Alexander.Fedorov@borland.com>
 *     		- Bug 172000 [Wizards] WizardNewFileCreationPage should support overwriting existing resources
 *******************************************************************************/
package gov.redhawk.ui.parts;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;

/**
 * Workbench-level composite for resource and container specification by the
 * user. Services such as field validation are performed by the group. The group
 * can be configured to accept existing resources, or only new resources.
 */
public class ResourceAndContainerGroup implements Listener {
	// problem identifiers

	/**
	 * Constant for no problem.
	 */
	public static final int PROBLEM_NONE = 0;
	/**
	 * Constant for empty resource.
	 */
	public static final int PROBLEM_RESOURCE_EMPTY = 1;

	/**
	 * Constant for resource already exists.
	 */
	public static final int PROBLEM_RESOURCE_EXIST = 2;

	/**
	 * Constant for invalid path.
	 */
	public static final int PROBLEM_PATH_INVALID = 4;

	/**
	 * Constant for empty container.
	 */
	public static final int PROBLEM_CONTAINER_EMPTY = 5;

	/**
	 * Constant for project does not exist.
	 */
	public static final int PROBLEM_PROJECT_DOES_NOT_EXIST = 6;

	/**
	 * Constant for invalid name.
	 */
	public static final int PROBLEM_NAME_INVALID = 7;

	/**
	 * Constant for path already occupied.
	 */
	public static final int PROBLEM_PATH_OCCUPIED = 8;

	// the client to notify of changes
	private final Listener client;

	// whether to allow existing resources
	private boolean allowExistingResources = false;

	// resource type (file, folder, project)
	private String resourceType = IDEWorkbenchMessages.ResourceGroup_resource;

	// show closed projects in the tree, by default
	private boolean showClosedProjects = true;

	// problem indicator
	private String problemMessage = ""; //$NON-NLS-1$

	private int problemType = ResourceAndContainerGroup.PROBLEM_NONE;

	// widgets
	private ContainerSelectionGroup containerGroup;

	private Text resourceNameField;

	/**
	 * The resource extension for the resource name field.
	 * 
	 * @see ResourceAndContainerGroup#setResourceExtension(String)
	 */
	private String resourceExtension;
	private final IProject root;

	// constants
	private static final int SIZING_TEXT_FIELD_WIDTH = 250;

	/**
	 * Create an instance of the group to allow the user to enter/select a
	 * container and specify a resource name.
	 * 
	 * @param parent composite widget to parent the group
	 * @param client object interested in changes to the group's fields value
	 * @param resourceFieldLabel label to use in front of the resource name
	 *            field
	 * @param resourceType one word, in lowercase, to describe the resource to
	 *            the user (file, folder, project)
	 */
	public ResourceAndContainerGroup(final Composite parent, final Listener client, final IProject root,
			final String resourceFieldLabel, final String resourceType) {
		this(parent, client, root, resourceFieldLabel, resourceType, true);
	}

	/**
	 * Create an instance of the group to allow the user to enter/select a
	 * container and specify a resource name.
	 * 
	 * @param parent composite widget to parent the group
	 * @param client object interested in changes to the group's fields value
	 * @param resourceFieldLabel label to use in front of the resource name
	 *            field
	 * @param resourceType one word, in lowercase, to describe the resource to
	 *            the user (file, folder, project)
	 * @param showClosedProjects whether or not to show closed projects
	 */
	public ResourceAndContainerGroup(final Composite parent, final Listener client, final IProject root,
			final String resourceFieldLabel, final String resourceType, final boolean showClosedProjects) {
		this(parent, client, root, resourceFieldLabel, resourceType, showClosedProjects, SWT.DEFAULT);
	}

	/**
	 * Create an instance of the group to allow the user to enter/select a
	 * container and specify a resource name.
	 * 
	 * @param parent composite widget to parent the group
	 * @param client object interested in changes to the group's fields value
	 * @param resourceFieldLabel label to use in front of the resource name
	 *            field
	 * @param resourceType one word, in lowercase, to describe the resource to
	 *            the user (file, folder, project)
	 * @param showClosedProjects whether or not to show closed projects
	 * @param heightHint height hint for the container selection widget group
	 */
	public ResourceAndContainerGroup(final Composite parent, final Listener client, final IProject root,
			final String resourceFieldLabel, final String resourceType, final boolean showClosedProjects,
			final int heightHint) {
		super();
		this.resourceType = resourceType;
		this.root = root;
		this.showClosedProjects = showClosedProjects;
		createContents(parent, resourceFieldLabel, heightHint);
		this.client = client;
	}

	/**
	 * Returns a boolean indicating whether all controls in this group contain
	 * valid values.
	 * 
	 * @return boolean
	 */
	public boolean areAllValuesValid() {
		return this.problemType == ResourceAndContainerGroup.PROBLEM_NONE;
	}

	/**
	 * Creates this object's visual components.
	 * 
	 * @param parent org.eclipse.swt.widgets.Composite
	 * @param heightHint height hint for the container selection widget group
	 */
	protected void createContents(final Composite parent, final String resourceLabelString, final int heightHint) {

		final Font font = parent.getFont();
		// server name group
		final Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		composite.setFont(font);

		// container group
		if (heightHint == SWT.DEFAULT) {
			this.containerGroup = new ContainerSelectionGroup(composite, this, this.root, true, null,
					this.showClosedProjects);
		} else {
			this.containerGroup = new ContainerSelectionGroup(composite, this, this.root, true, null,
					this.showClosedProjects, heightHint, ResourceAndContainerGroup.SIZING_TEXT_FIELD_WIDTH);
		}

		// resource name group
		final Composite nameGroup = new Composite(composite, SWT.NONE);
		layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = 0;
		nameGroup.setLayout(layout);
		nameGroup.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));
		nameGroup.setFont(font);

		final Label label = new Label(nameGroup, SWT.NONE);
		label.setText(resourceLabelString);
		label.setFont(font);

		// resource name entry field
		this.resourceNameField = new Text(nameGroup, SWT.BORDER);
		this.resourceNameField.addListener(SWT.Modify, this);
		this.resourceNameField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(final FocusEvent e) {
				handleResourceNameFocusLostEvent();
			}
		});
		final GridData data = new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL);
		data.widthHint = ResourceAndContainerGroup.SIZING_TEXT_FIELD_WIDTH;
		this.resourceNameField.setLayoutData(data);
		this.resourceNameField.setFont(font);
		validateControls();
	}

	/**
	 * Returns the path of the currently selected container or null if no
	 * container has been selected. Note that the container may not exist yet if
	 * the user entered a new container name in the field.
	 * 
	 * @return The path of the container, or <code>null</code>
	 */
	public IPath getContainerFullPath() {
		return this.containerGroup.getContainerFullPath();
	}

	/**
	 * Returns an error message indicating the current problem with the value of
	 * a control in the group, or an empty message if all controls in the group
	 * contain valid values.
	 * 
	 * @return java.lang.String
	 */
	public String getProblemMessage() {
		return this.problemMessage;
	}

	/**
	 * Returns the type of problem with the value of a control in the group.
	 * 
	 * @return one of the PROBLEM_* constants
	 */
	public int getProblemType() {
		return this.problemType;
	}

	/**
	 * Returns a string that is the name of the chosen resource, or an empty
	 * string if no resource has been entered. <br>
	 * <br>
	 * The name will include the resource extension if the preconditions are
	 * met.
	 * 
	 * @see ResourceAndContainerGroup#setResourceExtension(String)
	 * @return The resource name
	 */
	public String getResource() {
		final String resource = this.resourceNameField.getText();
		if (useResourceExtension()) {
			return resource + '.' + this.resourceExtension;
		}
		return resource;
	}

	/**
	 * Returns the resource extension.
	 * 
	 * @return The resource extension or <code>null</code>.
	 * @see ResourceAndContainerGroup#setResourceExtension(String)
	 */
	public String getResourceExtension() {
		return this.resourceExtension;
	}

	/**
	 * Determines whether the resource extension should be added to the resource
	 * name field. <br>
	 * <br>
	 * 
	 * @see ResourceAndContainerGroup#setResourceExtension(String)
	 * @return <code>true</code> if the preconditions are met; otherwise,
	 *         <code>false</code>.
	 */
	private boolean useResourceExtension() {
		final String resource = this.resourceNameField.getText();
		if ((this.resourceExtension != null) && (this.resourceExtension.length() > 0) && (resource.length() > 0)
				&& !(resource.endsWith('.' + this.resourceExtension))) {
			return true;
		}
		return false;
	}

	/**
	 * Handle the focus lost event from the resource name field. <br>
	 * Adds the resource extension to the resource name field when it loses
	 * focus (if the preconditions are met).
	 * 
	 * @see ResourceAndContainerGroup#setResourceExtension(String)
	 */
	private void handleResourceNameFocusLostEvent() {
		if (useResourceExtension()) {
			setResource(this.resourceNameField.getText() + '.' + this.resourceExtension);
		}
	}

	/**
	 * Handles events for all controls in the group.
	 * 
	 * @param e org.eclipse.swt.widgets.Event
	 */
	@Override
	public void handleEvent(final Event e) {
		validateControls();
		if (this.client != null) {
			this.client.handleEvent(e);
		}
	}

	/**
	 * Sets the flag indicating whether existing resources are permitted.
	 * 
	 * @param value
	 */
	public void setAllowExistingResources(final boolean value) {
		this.allowExistingResources = value;
	}

	/**
	 * Sets the value of this page's container.
	 * 
	 * @param path Full path to the container.
	 */
	public void setContainerFullPath(final IPath path) {
		IResource initial = ResourcesPlugin.getWorkspace().getRoot().findMember(path);
		if (initial != null) {
			if (!(initial instanceof IContainer)) {
				initial = initial.getParent();
			}
			this.containerGroup.setSelectedContainer((IContainer) initial);
		}
		validateControls();
	}

	/**
	 * Gives focus to the resource name field and selects its contents
	 */
	public void setFocus() {
		// select the whole resource name.
		this.resourceNameField.setSelection(0, this.resourceNameField.getText().length());
		this.resourceNameField.setFocus();
	}

	/**
	 * Sets the value of this page's resource name.
	 * 
	 * @param value new value
	 */
	public void setResource(final String value) {
		this.resourceNameField.setText(value);
		validateControls();
	}

	/**
	 * Set the only file extension allowed for the resource name field. <br>
	 * <br>
	 * If a resource extension is specified, then it will always be appended
	 * with a '.' to the text from the resource name field for validation when
	 * the following conditions are met: <br>
	 * <br>
	 * (1) Resource extension length is greater than 0 <br>
	 * (2) Resource name field text length is greater than 0 <br>
	 * (3) Resource name field text does not already end with a '.' and the
	 * resource extension specified (case sensitive) <br>
	 * <br>
	 * The resource extension will not be reflected in the actual resource name
	 * field until the resource name field loses focus.
	 * 
	 * @param value The resource extension without the '.' prefix (e.g. 'java',
	 *            'xml')
	 */
	public void setResourceExtension(final String value) {
		this.resourceExtension = value;
		validateControls();
	}

	/**
	 * Returns a <code>boolean</code> indicating whether a container name
	 * represents a valid container resource in the workbench. An error message
	 * is stored for future reference if the name does not represent a valid
	 * container.
	 * 
	 * @return <code>boolean</code> indicating validity of the container name
	 */
	protected boolean validateContainer() {
		IPath path = this.containerGroup.getContainerFullPath();
		if (path == null) {
			this.problemType = ResourceAndContainerGroup.PROBLEM_CONTAINER_EMPTY;
			this.problemMessage = IDEWorkbenchMessages.ResourceGroup_folderEmpty;
			return false;
		}
		final IWorkspace workspace = ResourcesPlugin.getWorkspace();
		final String projectName = path.segment(0);
		if (projectName == null || !workspace.getRoot().getProject(projectName).exists()) {
			this.problemType = ResourceAndContainerGroup.PROBLEM_PROJECT_DOES_NOT_EXIST;
			this.problemMessage = IDEWorkbenchMessages.ResourceGroup_noProject;
			return false;
		}
		// path is invalid if any prefix is occupied by a file
		final IWorkspaceRoot wsRoot = workspace.getRoot();
		while (path.segmentCount() > 1) {
			if (wsRoot.getFile(path).exists()) {
				this.problemType = ResourceAndContainerGroup.PROBLEM_PATH_OCCUPIED;
				this.problemMessage = NLS.bind(IDEWorkbenchMessages.ResourceGroup_pathOccupied, path.makeRelative());
				return false;
			}
			path = path.removeLastSegments(1);
		}
		return true;
	}

	/**
	 * Validates the values for each of the group's controls. If an invalid
	 * value is found then a descriptive error message is stored for later
	 * reference. Returns a boolean indicating the validity of all of the
	 * controls in the group.
	 */
	protected boolean validateControls() {
		// don't attempt to validate controls until they have been created
		if (this.containerGroup == null) {
			return false;
		}
		this.problemType = ResourceAndContainerGroup.PROBLEM_NONE;
		this.problemMessage = ""; //$NON-NLS-1$

		if (!validateContainer() || !validateResourceName()) {
			return false;
		}

		final IPath path = this.containerGroup.getContainerFullPath().append(getResource());
		return validateFullResourcePath(path);
	}

	/**
	 * Returns a <code>boolean</code> indicating whether the specified resource
	 * path represents a valid new resource in the workbench. An error message
	 * is stored for future reference if the path does not represent a valid new
	 * resource path.
	 * 
	 * @param resourcePath the path to validate
	 * @return <code>boolean</code> indicating validity of the resource path
	 */
	protected boolean validateFullResourcePath(final IPath resourcePath) {
		final IWorkspace workspace = ResourcesPlugin.getWorkspace();

		final IStatus result = workspace.validatePath(resourcePath.toString(), IResource.FOLDER);
		if (!result.isOK()) {
			this.problemType = ResourceAndContainerGroup.PROBLEM_PATH_INVALID;
			this.problemMessage = result.getMessage();
			return false;
		}

		if (!this.allowExistingResources
				&& (workspace.getRoot().getFolder(resourcePath).exists() || workspace.getRoot().getFile(resourcePath).exists())) {
			this.problemType = ResourceAndContainerGroup.PROBLEM_RESOURCE_EXIST;
			this.problemMessage = NLS.bind(IDEWorkbenchMessages.ResourceGroup_nameExists, getResource());
			return false;
		}
		return true;
	}

	/**
	 * Returns a <code>boolean</code> indicating whether the resource name rep-
	 * resents a valid resource name in the workbench. An error message is
	 * stored for future reference if the name does not represent a valid
	 * resource name.
	 * 
	 * @return <code>boolean</code> indicating validity of the resource name
	 */
	protected boolean validateResourceName() {
		final String resourceName = getResource();

		if (resourceName.length() == 0) {
			this.problemType = ResourceAndContainerGroup.PROBLEM_RESOURCE_EMPTY;
			this.problemMessage = NLS.bind(IDEWorkbenchMessages.ResourceGroup_emptyName, this.resourceType);
			return false;
		}

		if (!Path.ROOT.isValidPath(resourceName)) {
			this.problemType = ResourceAndContainerGroup.PROBLEM_NAME_INVALID;
			this.problemMessage = NLS.bind(IDEWorkbenchMessages.ResourceGroup_invalidFilename, resourceName);
			return false;
		}
		return true;
	}

	/**
	 * Returns the flag indicating whether existing resources are permitted.
	 * 
	 * @return The allow existing resources flag.
	 * @see ResourceAndContainerGroup#setAllowExistingResources(boolean)
	 */
	public boolean getAllowExistingResources() {
		return this.allowExistingResources;
	}

}

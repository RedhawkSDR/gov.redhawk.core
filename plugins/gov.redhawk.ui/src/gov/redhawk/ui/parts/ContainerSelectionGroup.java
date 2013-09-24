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
 * Copyright (c) 2000, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Igor Fedorenko <igorfie@yahoo.com> -
 *     		Fix for Bug 136921 [IDE] New File dialog locks for 20 seconds
 *******************************************************************************/
package gov.redhawk.ui.parts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.osgi.util.TextProcessor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.internal.ide.misc.ContainerContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.part.DrillDownComposite;

/**
 * Workbench-level composite for choosing a container.
 */
public class ContainerSelectionGroup extends Composite {
	// The listener to notify of events
	private final Listener listener;

	// Enable user to type in new container name
	private boolean allowNewContainerName = true;

	// show all projects by default
	private boolean showClosedProjects = true;

	// Last selection made by user
	private IContainer selectedContainer;

	// handle on parts
	private Text containerNameField;

	private TreeViewer treeViewer;

	private final IProject root;

	// the message to display at the top of this dialog
	private static final String DEFAULT_MSG_NEW_ALLOWED = IDEWorkbenchMessages.ContainerGroup_message;

	private static final String DEFAULT_MSG_SELECT_ONLY = IDEWorkbenchMessages.ContainerGroup_selectFolder;

	// sizing constants
	private static final int SIZING_SELECTION_PANE_WIDTH = 320;

	private static final int SIZING_SELECTION_PANE_HEIGHT = 300;

	/**
	 * Creates a new instance of the widget.
	 * 
	 * @param parent The parent widget of the group.
	 * @param listener A listener to forward events to. Can be null if no
	 *            listener is required.
	 * @param allowNewContainerName Enable the user to type in a new container
	 *            name instead of just selecting from the existing ones.
	 */
	public ContainerSelectionGroup(final Composite parent, final Listener listener, final IProject root, final boolean allowNewContainerName) {
		this(parent, listener, root, allowNewContainerName, null);
	}

	/**
	 * Creates a new instance of the widget.
	 * 
	 * @param parent The parent widget of the group.
	 * @param listener A listener to forward events to. Can be null if no
	 *            listener is required.
	 * @param allowNewContainerName Enable the user to type in a new container
	 *            name instead of just selecting from the existing ones.
	 * @param message The text to present to the user.
	 */
	public ContainerSelectionGroup(final Composite parent, final Listener listener, final IProject root, final boolean allowNewContainerName,
	        final String message) {
		this(parent, listener, root, allowNewContainerName, message, true);
	}

	/**
	 * Creates a new instance of the widget.
	 * 
	 * @param parent The parent widget of the group.
	 * @param listener A listener to forward events to. Can be null if no
	 *            listener is required.
	 * @param allowNewContainerName Enable the user to type in a new container
	 *            name instead of just selecting from the existing ones.
	 * @param message The text to present to the user.
	 * @param showClosedProjects Whether or not to show closed projects.
	 */
	public ContainerSelectionGroup(final Composite parent, final Listener listener, final IProject root, final boolean allowNewContainerName,
	        final String message, final boolean showClosedProjects) {
		this(parent, listener, root, allowNewContainerName, message, showClosedProjects, ContainerSelectionGroup.SIZING_SELECTION_PANE_HEIGHT,
		        ContainerSelectionGroup.SIZING_SELECTION_PANE_WIDTH);
	}

	/**
	 * Creates a new instance of the widget.
	 * 
	 * @param parent The parent widget of the group.
	 * @param listener A listener to forward events to. Can be null if no
	 *            listener is required.
	 * @param allowNewContainerName Enable the user to type in a new container
	 *            name instead of just selecting from the existing ones.
	 * @param message The text to present to the user.
	 * @param showClosedProjects Whether or not to show closed projects.
	 * @param heightHint height hint for the drill down composite
	 * @param widthHint width hint for the drill down composite
	 */
	public ContainerSelectionGroup(final Composite parent, final Listener listener, final IProject root, // SUPPRESS CHECKSTYLE Parameters
	        final boolean allowNewContainerName, final String message, final boolean showClosedProjects, final int heightHint, final int widthHint) {
		super(parent, SWT.NONE);
		this.listener = listener;
		this.root = root;
		this.allowNewContainerName = allowNewContainerName;
		this.showClosedProjects = showClosedProjects;
		if (message != null) {
			createContents(message, heightHint, widthHint);
		} else if (allowNewContainerName) {
			createContents(ContainerSelectionGroup.DEFAULT_MSG_NEW_ALLOWED, heightHint, widthHint);
		} else {
			createContents(ContainerSelectionGroup.DEFAULT_MSG_SELECT_ONLY, heightHint, widthHint);
		}
	}

	/**
	 * The container selection has changed in the tree view. Update the
	 * container name field value and notify all listeners.
	 * 
	 * @param container The container that changed
	 */
	public void containerSelectionChanged(final IContainer container) {
		this.selectedContainer = container;

		if (this.allowNewContainerName) {
			if (container == null) {
				this.containerNameField.setText(this.root.getName());
			} else {
				final String text = TextProcessor.process(container.getFullPath().makeRelative().toString());
				this.containerNameField.setText(text);
				this.containerNameField.setToolTipText(text);
			}
		}

		// fire an event so the parent can update its controls
		if (this.listener != null) {
			final Event changeEvent = new Event();
			changeEvent.type = SWT.Selection;
			changeEvent.widget = this;
			this.listener.handleEvent(changeEvent);
		}
	}

	/**
	 * Creates the contents of the composite.
	 * 
	 * @param message
	 */
	public void createContents(final String message) {
		createContents(message, ContainerSelectionGroup.SIZING_SELECTION_PANE_HEIGHT, ContainerSelectionGroup.SIZING_SELECTION_PANE_WIDTH);
	}

	/**
	 * Creates the contents of the composite.
	 * 
	 * @param message
	 * @param heightHint
	 * @param widthHint
	 */
	public void createContents(final String message, final int heightHint, final int widthHint) {
		final GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		setLayout(layout);
		setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		final Label label = new Label(this, SWT.WRAP);
		label.setText(message);
		label.setFont(this.getFont());

		if (this.allowNewContainerName) {
			this.containerNameField = new Text(this, SWT.SINGLE | SWT.BORDER);
			final GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.widthHint = widthHint;
			this.containerNameField.setLayoutData(gd);
			this.containerNameField.addListener(SWT.Modify, this.listener);
			this.containerNameField.setFont(this.getFont());
		} else {
			// filler...
			new Label(this, SWT.NONE);
		}

		createTreeViewer(heightHint);
		Dialog.applyDialogFont(this);
	}

	/**
	 * Returns a new drill down viewer for this dialog.
	 * 
	 * @param heightHint height hint for the drill down composite
	 */
	protected void createTreeViewer(final int heightHint) {
		// Create drill down.
		final DrillDownComposite drillDown = new DrillDownComposite(this, SWT.BORDER);
		final GridData spec = new GridData(SWT.FILL, SWT.FILL, true, true);
		spec.widthHint = ContainerSelectionGroup.SIZING_SELECTION_PANE_WIDTH;
		spec.heightHint = heightHint;
		drillDown.setLayoutData(spec);

		// Create tree viewer inside drill down.
		this.treeViewer = new TreeViewer(drillDown, SWT.NONE);
		drillDown.setChildTree(this.treeViewer);
		final ContainerContentProvider cp = new ContainerContentProvider();
		cp.showClosedProjects(this.showClosedProjects);
		this.treeViewer.setContentProvider(cp);
		this.treeViewer.setLabelProvider(WorkbenchLabelProvider.getDecoratingWorkbenchLabelProvider());
		this.treeViewer.setComparator(new ViewerComparator());
		this.treeViewer.setUseHashlookup(true);
		this.treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(final SelectionChangedEvent event) {
				final IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				containerSelectionChanged((IContainer) selection.getFirstElement()); // allow null
			}
		});
		this.treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(final DoubleClickEvent event) {
				final ISelection selection = event.getSelection();
				if (selection instanceof IStructuredSelection) {
					final Object item = ((IStructuredSelection) selection).getFirstElement();
					if (item == null) {
						return;
					}
					if (ContainerSelectionGroup.this.treeViewer.getExpandedState(item)) {
						ContainerSelectionGroup.this.treeViewer.collapseToLevel(item, 1);
					} else {
						ContainerSelectionGroup.this.treeViewer.expandToLevel(item, 1);
					}
				}
			}
		});

		// This has to be done after the viewer has been laid out
		this.treeViewer.setInput(this.root);
	}

	/**
	 * Returns the currently entered container name. Null if the field is empty.
	 * Note that the container may not exist yet if the user entered a new
	 * container name in the field.
	 * 
	 * @return IPath
	 */
	public IPath getContainerFullPath() {
		if (this.allowNewContainerName) {
			final String pathName = this.containerNameField.getText();
			if (pathName == null || pathName.length() < 1) {
				return null;
			}
			// The user may not have made this absolute so do it for them
			return (new Path(TextProcessor.deprocess(pathName))).makeAbsolute();

		}
		if (this.selectedContainer == null) {
			return null;
		}
		return this.selectedContainer.getFullPath();

	}

	/**
	 * Gives focus to one of the widgets in the group, as determined by the
	 * group.
	 */
	public void setInitialFocus() {
		if (this.allowNewContainerName) {
			this.containerNameField.setFocus();
		} else {
			this.treeViewer.getTree().setFocus();
		}
	}

	/**
	 * Sets the selected existing container.
	 * 
	 * @param container
	 */
	public void setSelectedContainer(final IContainer container) {
		this.selectedContainer = container;

		// expand to and select the specified container
		final List<IContainer> itemsToExpand = new ArrayList<IContainer>();
		IContainer parent = container.getParent();
		while (parent != null) {
			itemsToExpand.add(0, parent);
			parent = parent.getParent();
		}
		this.treeViewer.setExpandedElements(itemsToExpand.toArray());
		this.treeViewer.setSelection(new StructuredSelection(container), true);
	}
}

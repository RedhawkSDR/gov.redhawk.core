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
package gov.redhawk.sca.internal.ui.actions;

import gov.redhawk.model.sca.IRefreshable;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.sca.internal.ui.ResourceRegistry;
import gov.redhawk.sca.internal.ui.ScaContentTypeRegistry;
import gov.redhawk.sca.ui.IScaEditorDescriptor;
import gov.redhawk.sca.ui.ScaUiPlugin;

import java.text.Collator;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.progress.WorkbenchJob;
import org.eclipse.ui.statushandlers.StatusManager;

/**
 * A menu for opening files in the workbench.
 * <p>
 * An <code>OpenWithMenu</code> is used to populate a menu with
 * "Open With" actions.  One action is added for each editor which is applicable
 * to the selected file. If the user selects one of these items, the corresponding
 * editor is opened on the file.
 * </p>
 * <p>
 * This class may be instantiated; it is not intended to be subclassed.
 * </p>
 * @noextend This class is not intended to be subclassed by clients.
 */
public class OpenWithMenu extends ContributionItem {
	private final IWorkbenchPage page;

	private final Object selection;

	/**
	 * The id of this action.
	 */
	public static final String ID = PlatformUI.PLUGIN_ID + ".OpenWithMenu"; //$NON-NLS-1$

	/*
	 * Compares the labels from two IEditorDescriptor objects
	 */
	private static final Comparator<IScaEditorDescriptor> COMPARER = new Comparator<IScaEditorDescriptor>() {
		private final Collator collator = Collator.getInstance();

		@Override
		public int compare(final IScaEditorDescriptor arg0, final IScaEditorDescriptor arg1) {
			final String s1 = arg0.getEditorDescriptor().getLabel();
			final String s2 = arg1.getEditorDescriptor().getLabel();
			return this.collator.compare(s1, s2);
		}
	};

	/**
	 * Constructs a new instance of <code>OpenWithMenu</code>.
	 *
	 * @param page the page where the editor is opened if an item within
	 *		the menu is selected
	 * @param file the selected file
	 */
	public OpenWithMenu(final IWorkbenchPage page, final Object selection) {
		super(OpenWithMenu.ID);
		this.page = page;
		this.selection = selection;
	}

	/**
	 * Creates the menu item for the editor descriptor.
	 *
	 * @param menu the menu to add the item to
	 * @param descriptor the editor descriptor, or null for the system editor
	 * @param preferredEditor the descriptor of the preferred editor, or <code>null</code>
	 */
	private void createMenuItem(final Menu menu, final IScaEditorDescriptor descriptor) {
		// XXX: Would be better to use bold here, but SWT does not support it.
		final MenuItem menuItem = new MenuItem(menu, SWT.None);
		menuItem.setText(descriptor.getEditorDescriptor().getLabel());
		final Image image = getImage(descriptor);
		if (image != null) {
			menuItem.setImage(image);
		}
		final Listener listener = new Listener() {
			@Override
			public void handleEvent(final Event event) {
				switch (event.type) {
				case SWT.Selection:
					openEditor(descriptor);
					break;
				default:
					break;
				}
			}
		};
		menuItem.addListener(SWT.Selection, listener);
	}

	private Image getImage(final IScaEditorDescriptor descriptor) {
		if (descriptor == null || descriptor.getEditorDescriptor() == null || descriptor.getEditorDescriptor().getImageDescriptor() == null) {
			return null;
		}
		return ResourceRegistry.INSTANCE.getResourceManager().createImage(descriptor.getEditorDescriptor().getImageDescriptor());
	}

	/* (non-Javadoc)
	 * Fills the menu with perspective items.
	 */
	@Override
	public void fill(final Menu menu, final int index) {

		final IScaEditorDescriptor defaultEditor = ScaContentTypeRegistry.INSTANCE.getScaEditorDescriptor(this.selection);
		final IScaEditorDescriptor[] editors = ScaContentTypeRegistry.INSTANCE.getAllScaEditorDescriptors(this.selection);
		Collections.sort(Arrays.asList(editors), OpenWithMenu.COMPARER);

		//Check that we don't add it twice. This is possible
		//if the same editor goes to two mappings.
		final Set<IScaEditorDescriptor> alreadyMapped = new HashSet<IScaEditorDescriptor>();

		for (final IScaEditorDescriptor editor : editors) {
			if (!alreadyMapped.contains(editor)) {
				createMenuItem(menu, editor);
				if (defaultEditor != null && defaultEditor.getEditorDescriptor().getId().equals(editor.getEditorDescriptor().getId())) {
					continue;
				}
				alreadyMapped.add(editor);
			}
		}
	}

	/* (non-Javadoc)
	 * Returns whether this menu is dynamic.
	 */
	@Override
	public boolean isDynamic() {
		return true;
	}

	/**
	 * Opens the given editor on the selected file.
	 *
	 * @param editorDescriptor the editor descriptor, or null for the system editor
	 * @param openUsingDescriptor use the descriptor's editor ID for opening if false (normal case),
	 * or use the descriptor itself if true (needed to fix bug 178235).
	 *
	 */
	protected void openEditor(final IScaEditorDescriptor editorDescriptor) {
		final Object obj = editorDescriptor.getSelectedObject();
		if (obj instanceof IRefreshable) {
			final IRefreshable refreshable = (IRefreshable) obj;
			final Job job = new Job("Opening...") {

				@Override
				protected IStatus run(final IProgressMonitor monitor) {
					try {
						refreshable.refresh(null, RefreshDepth.FULL);
						Display display = page.getWorkbenchWindow().getShell().getDisplay();
						if (display != null) {
							final WorkbenchJob openJob = new WorkbenchJob(display, "Open") {

								@Override
								public IStatus runInUIThread(final IProgressMonitor monitor) {
									open(editorDescriptor);
									return Status.OK_STATUS;
								}

							};
							openJob.schedule();
						}
					} catch (final InterruptedException e) {
						// PASS
					}
					return Status.OK_STATUS;
				}

			};
			job.setUser(true);
			job.schedule();
		} else {
			open(editorDescriptor);
		}
	}

	private void open(final IScaEditorDescriptor editorDescriptor) {
		try {
			this.page.openEditor(editorDescriptor.getEditorInput(), editorDescriptor.getEditorDescriptor().getId(), true, IWorkbenchPage.MATCH_ID
					| IWorkbenchPage.MATCH_INPUT);
		} catch (final PartInitException e) {
			StatusManager.getManager().handle(e, ScaUiPlugin.PLUGIN_ID);
		}
	}

}

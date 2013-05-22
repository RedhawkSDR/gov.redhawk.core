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
package gov.redhawk.sca.internal.ui;

import gov.redhawk.model.sca.ScaFileStore;
import gov.redhawk.sca.internal.ui.actions.OpenAction;
import gov.redhawk.sca.ui.ScaUI;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionConstants;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;

/**
 * @deprecated Switch to using {@link gov.redhawk.sca.internal.ui.actions.OpenWithActionProvider}
 */
@Deprecated
public class RedhawkUiCommonActionProvider extends CommonActionProvider {
	/** The window. */
	private IWorkbenchWindow window;

	/** The open with menu. */
	private MenuManager openWithMenu;

	private OpenAction openDefaultAction;

	public RedhawkUiCommonActionProvider() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillActionBars(final IActionBars actionBars) {
		super.fillActionBars(actionBars);
		if (this.openDefaultAction.isEnabled()) {
			actionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, this.openDefaultAction);
		} else {
			actionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, null);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fillContextMenu(final IMenuManager menu) {
		super.fillContextMenu(menu);

		if (this.openDefaultAction.isEnabled()) {
			menu.insertAfter(ICommonMenuConstants.GROUP_OPEN, this.openDefaultAction);
		}
		if (this.openWithMenu.getItems().length > 0) {
			// append the submenu after the GROUP_NEW group.
			menu.insertAfter(ICommonMenuConstants.GROUP_OPEN_WITH, this.openWithMenu);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(final ICommonActionExtensionSite anExtensionSite) {
		super.init(anExtensionSite);
		if (anExtensionSite.getViewSite() instanceof ICommonViewerWorkbenchSite) {
			this.window = ((ICommonViewerWorkbenchSite) anExtensionSite.getViewSite()).getWorkbenchWindow();
			this.openDefaultAction = new OpenAction(this.window.getActivePage());
			this.openWithMenu = new MenuManager("&Open With", ICommonMenuConstants.GROUP_OPEN_WITH);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setContext(final ActionContext context) {
		super.setContext(context);
		updateActionBars();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateActionBars() {
		if (this.getContext() == null || this.getContext().getSelection() == null) {
			return;
		}
		final Object obj = ((IStructuredSelection) this.getContext().getSelection()).getFirstElement();
		if (this.openWithMenu == null || this.window == null || this.openDefaultAction == null) {
			return;
		}
		this.openWithMenu.removeAll();
		this.openDefaultAction.setAction(null, null);
		final IEditorRegistry editorRegistry = this.window.getWorkbench().getEditorRegistry();

		// Get visible actions.
		final String[] contentTypes = ScaContentTypeRegistry.INSTANCE.findContentTypes(obj);

		for (final String contentType : contentTypes) {
			final String[] editors = ScaContentTypeRegistry.INSTANCE.findEditors(contentType);
			final IEditorInput input = ScaContentTypeRegistry.INSTANCE.getDescriber(contentType).getEditorInput(obj);
			for (final String editorId : editors) {
				final IEditorDescriptor editor = editorRegistry.findEditor(editorId);
				this.openWithMenu.add(new OpenAction(this.window.getActivePage(), input, editor));
			}
		}

		final String contentType = ScaContentTypeRegistry.INSTANCE.findContentType(obj);
		if (contentType != null) {
			final String editorId = ScaContentTypeRegistry.INSTANCE.findEditor(contentType);
			if (editorId != null) {
				final IEditorDescriptor editor = editorRegistry.findEditor(editorId);
				final IEditorInput input = ScaContentTypeRegistry.INSTANCE.getDescriber(contentType).getEditorInput(obj);
				this.openDefaultAction.setAction(input, editor);
			}
		}

		if (obj instanceof ScaFileStore) {
			addOpenScaFileStore((ScaFileStore) obj);
		}

		super.updateActionBars();
	}

	private void addOpenScaFileStore(final ScaFileStore obj) {
		final IFileStore fileStore = obj.getFileStore();

		if ((fileStore != null) && !fileStore.fetchInfo().isDirectory()) {
			final String fileName = fileStore.getName();

			IContentType contentType = null;
			try {
				final InputStream is = fileStore.openInputStream(EFS.NONE, null);
				final IContentDescription contDesc = Platform.getContentTypeManager().getDescriptionFor(is, fileName, IContentDescription.ALL);
				if (contDesc != null) {
					contentType = contDesc.getContentType();
				}
			} catch (final CoreException e) {
				// PASS
			} catch (final IOException e) {
				// PASS
			}

			for (final IEditorDescriptor desc : this.window.getWorkbench().getEditorRegistry().getEditors(fileName, contentType)) {
				// Filter out eclipse editors that have issues with files
				// outside of the workspace
				if (desc.getId().equals("org.eclipse.wst.xml.ui.internal.tabletree.XMLMultiPageEditorPart")) {
					continue;
				}
				final OpenAction action = new OpenAction(this.window.getActivePage(), ScaUI.getEditorInput(fileStore), desc);
				this.openWithMenu.add(action);
			}

			if (!this.openDefaultAction.isEnabled()) {
				// Handle special cases where we want to force the default
				// editor
				IEditorDescriptor defaultEditor = null;

				// Try the system default editor
				defaultEditor = this.window.getWorkbench().getEditorRegistry().getDefaultEditor(fileName, contentType);

				// Now fallback to the text editor

				if (defaultEditor == null) {
					defaultEditor = this.window.getWorkbench().getEditorRegistry().findEditor("org.eclipse.ui.DefaultTextEditor");
				}

				// and make sure the default editor isn't a broken one
				if (defaultEditor.getId().equals("org.eclipse.wst.xml.ui.internal.tabletree.XMLMultiPageEditorPart")) {
					defaultEditor = this.window.getWorkbench().getEditorRegistry().findEditor("org.eclipse.ui.DefaultTextEditor");
				}

				this.openDefaultAction.setAction(ScaUI.getEditorInput(fileStore), defaultEditor);
			}
		}
	}

}

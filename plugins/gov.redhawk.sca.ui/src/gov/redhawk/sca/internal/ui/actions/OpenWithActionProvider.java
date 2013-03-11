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
package gov.redhawk.sca.internal.ui.actions;

import gov.redhawk.sca.internal.ui.ScaContentTypeRegistry;
import gov.redhawk.sca.ui.IScaEditorDescriptor;
import gov.redhawk.sca.util.PluginUtil;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionConstants;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;

/**
 * 
 */
public class OpenWithActionProvider extends CommonActionProvider {
	private OpenFileAction openFileAction;

	private ICommonViewerWorkbenchSite viewSite = null;

	private boolean contribute = false;

	@Override
	public void init(final ICommonActionExtensionSite aConfig) {
		if (aConfig.getViewSite() instanceof ICommonViewerWorkbenchSite) {
			this.viewSite = (ICommonViewerWorkbenchSite) aConfig.getViewSite();
			this.openFileAction = new OpenFileAction(this.viewSite.getPage());
			this.contribute = true;
		}
	}

	@Override
	public void fillContextMenu(final IMenuManager aMenu) {
		if (!this.contribute || getContext().getSelection().isEmpty()) {
			return;
		}

		final IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();

		this.openFileAction.selectionChanged(selection);
		if (this.openFileAction.isEnabled()) {
			aMenu.insertAfter(ICommonMenuConstants.GROUP_OPEN, this.openFileAction);
		}
		addOpenWithMenu(aMenu);
	}

	@Override
	public void fillActionBars(final IActionBars theActionBars) {
		if (!this.contribute) {
			return;
		}
		final IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
		if (selection.size() == 1) {
			this.openFileAction.selectionChanged(selection);
			theActionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, this.openFileAction);
		}

	}

	private void addOpenWithMenu(final IMenuManager aMenu) {
		final IStructuredSelection ss = (IStructuredSelection) getContext().getSelection();

		if (ss == null || ss.size() != 1) {
			return;
		}

		final Object o = ss.getFirstElement();

		final EObject openable = PluginUtil.adapt(EObject.class, o);
		final IScaEditorDescriptor[] editors = ScaContentTypeRegistry.INSTANCE.getAllScaEditorDescriptors(openable);

		if (openable != null && editors.length > 0) {
			// Create a menu flyout.
			final IMenuManager submenu = new MenuManager("Open Wit&h", ICommonMenuConstants.GROUP_OPEN_WITH);
			submenu.add(new GroupMarker(ICommonMenuConstants.GROUP_TOP));
			submenu.add(new OpenWithMenu(this.viewSite.getPage(), openable));
			submenu.add(new GroupMarker(ICommonMenuConstants.GROUP_ADDITIONS));

			// Add the submenu.
			if (submenu.getItems().length > 2 && submenu.isEnabled()) {
				aMenu.appendToGroup(ICommonMenuConstants.GROUP_OPEN_WITH, submenu);
			}
		}
	}
}

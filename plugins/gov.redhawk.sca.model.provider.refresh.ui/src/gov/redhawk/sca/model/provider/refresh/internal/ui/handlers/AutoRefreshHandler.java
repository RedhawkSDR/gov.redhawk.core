/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.sca.model.provider.refresh.internal.ui.handlers;

import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.menus.UIElement;

import gov.redhawk.model.sca.DataProviderObject;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.services.IScaDataProvider;
import gov.redhawk.sca.model.provider.refresh.internal.RefreshTasker;

public class AutoRefreshHandler extends AbstractHandler implements IElementUpdater {

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		final ISelection selection = HandlerUtil.getActiveMenuSelection(event);
		if (!(selection instanceof IStructuredSelection)) {
			return null;
		}
		final IStructuredSelection ss = (IStructuredSelection) selection;

		Object obj = ss.getFirstElement();
		if (!(obj instanceof DataProviderObject)) {
			return null;
		}
		final DataProviderObject dataProviderObj = (DataProviderObject) obj;

		try {
			ScaModelCommand.runExclusive(dataProviderObj, new RunnableWithResult.Impl<Object>() {

				@Override
				public void run() {
					for (final Object provider : dataProviderObj.getDataProviders()) {
						if (provider instanceof RefreshTasker) {
							final RefreshTasker tasker = (RefreshTasker) provider;
							boolean state = !tasker.isEnabled();
							tasker.setEnabled(state);
							updateChildren(state, dataProviderObj);
							return;
						}
					}
				}

			});
		} catch (final InterruptedException e) {
			// PASS
		}
		return null;
	}

	/**
	 * Iterate all descendants of the specified object looking for {@link DataProviderObject}. For any
	 * {@link DataProviderObject}, set the state of its {@link RefreshTasker}.
	 * @param state The new state for {@link RefreshTasker}s.
	 * @param object The object to iterate
	 */
	private void updateChildren(final boolean state, final EObject object) {
		for (final TreeIterator<EObject> iterator = object.eAllContents(); iterator.hasNext();) {
			final EObject obj = iterator.next();
			if (!(obj instanceof DataProviderObject)) {
				continue;
			}
			final DataProviderObject dataProviderObj = (DataProviderObject) obj;

			for (final Object provider : dataProviderObj.getDataProviders()) {
				if (provider instanceof RefreshTasker) {
					final RefreshTasker tasker = (RefreshTasker) provider;
					tasker.setEnabled(state);
					break;
				}
			}
		}
	}

	@Override
	public void updateElement(final UIElement element, @SuppressWarnings("rawtypes") final Map parameters) {
		final IWorkbenchWindow workbenchWindow = (IWorkbenchWindow) parameters.get(IWorkbenchWindow.class.getName());
		if (workbenchWindow == null) {
			return;
		}

		final ISelectionService provider = workbenchWindow.getSelectionService();
		if (provider == null) {
			return;
		}

		final ISelection currentSelection = provider.getSelection();
		if (!(currentSelection instanceof IStructuredSelection)) {
			return;
		}
		final IStructuredSelection ss = (IStructuredSelection) currentSelection;

		Object selectedObj = ss.getFirstElement();
		if (!(selectedObj instanceof DataProviderObject)) {
			return;
		}
		final DataProviderObject selectedDataProviderObj = (DataProviderObject) selectedObj;

		// Tell Eclipse if the menu item should be checked/unchecked.
		try {
			boolean enabled = ScaModelCommand.runExclusive(selectedDataProviderObj, new RunnableWithResult.Impl<Boolean>() {

				@Override
				public void run() {
					setResult(false);
					for (final IScaDataProvider dataProvider : selectedDataProviderObj.getDataProviders()) {
						if (dataProvider instanceof RefreshTasker) {
							final RefreshTasker tasker = (RefreshTasker) dataProvider;
							setResult(tasker.isEnabled());
							return;
						}
					}
				}
			});
			element.setChecked(enabled);
		} catch (InterruptedException e) {
			// PASS
		}
	}

}

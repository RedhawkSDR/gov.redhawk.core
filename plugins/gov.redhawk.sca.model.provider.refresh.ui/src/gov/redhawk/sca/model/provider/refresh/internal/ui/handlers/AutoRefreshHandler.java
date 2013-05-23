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
package gov.redhawk.sca.model.provider.refresh.internal.ui.handlers;

import gov.redhawk.model.sca.DataProviderObject;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.sca.model.provider.refresh.RefreshTask;

import java.util.Map;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchSite;
import org.eclipse.ui.commands.IElementUpdater;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.menus.UIElement;

/**
 * 
 */
public class AutoRefreshHandler extends AbstractHandler implements IHandler, IElementUpdater {

	/**
	 * {@inheritDoc}
	 */
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		final ISelection selection = HandlerUtil.getActiveMenuSelection(event);
		if (selection instanceof IStructuredSelection) {
			final IStructuredSelection ss = (IStructuredSelection) selection;
			for (final Object obj : ss.toArray()) {
				if (obj instanceof DataProviderObject) {
					try {
						ScaModelCommand.runExclusive((DataProviderObject) obj, new RunnableWithResult.Impl<Object>() {

							public void run() {
								final DataProviderObject dataObj = (DataProviderObject) obj;
								final Object[] providers = dataObj.getDataProviders().toArray();
								boolean state = false;
								for (final Object provider : providers) {
									if (provider instanceof RefreshTask) {
										final RefreshTask job = (RefreshTask) provider;
										state = !job.isActive();
										job.setActive(state);
									}
								}
								updateRefreshDataProvider(state, dataObj);
							}

						});
					} catch (final InterruptedException e) {
						// PASS
					}
				}
			}
		}
		return null;
	}

	private void updateRefreshDataProvider(final boolean state, final DataProviderObject object) {
		for (final TreeIterator<EObject> iterator = object.eAllContents(); iterator.hasNext();) {
			final EObject obj = iterator.next();
			if (obj instanceof DataProviderObject) {
				final DataProviderObject dataProvider = (DataProviderObject) obj;
				final Object[] providers = dataProvider.getDataProviders().toArray();
				for (final Object provider : providers) {
					if (provider instanceof RefreshTask) {
						final RefreshTask job = (RefreshTask) provider;
						job.setEnabled(state);
					}
				}
			}
		}
	}

	public void updateElement(final UIElement element, @SuppressWarnings("rawtypes") final Map parameters) {
		if (parameters == null) {
			return;
		}
		final Object partSite = parameters.get("org.eclipse.ui.part.IWorkbenchPartSite");
		if (partSite instanceof IWorkbenchSite) {
			final ISelectionProvider provider = ((IWorkbenchSite) partSite).getSelectionProvider();
			if (provider != null) {
				final ISelection currentSelection = provider.getSelection();
				if (currentSelection instanceof IStructuredSelection) {
					final IStructuredSelection ss = (IStructuredSelection) currentSelection;
					final Object selectedElement = ss.getFirstElement();
					if (selectedElement instanceof DataProviderObject) {
						final DataProviderObject dataProviderObj = (DataProviderObject) selectedElement;
						final Object[] providers = dataProviderObj.getDataProviders().toArray();
						for (final Object obj : providers) {
							if (obj instanceof RefreshTask) {
								final RefreshTask job = (RefreshTask) obj;
								element.setChecked(job.isActive());
							}
						}
					}
				}
			}
		}

	}

}

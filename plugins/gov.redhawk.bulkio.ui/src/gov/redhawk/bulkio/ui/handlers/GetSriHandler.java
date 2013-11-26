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
package gov.redhawk.bulkio.ui.handlers;

import gov.redhawk.bulkio.ui.BulkIOUIActivator;
import gov.redhawk.bulkio.ui.views.SriDataView;
import gov.redhawk.internal.ui.port.nxmplot.view.PlotSource;
import gov.redhawk.internal.ui.port.nxmplot.view.PlotView2;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;
import gov.redhawk.sca.util.PluginUtil;
import gov.redhawk.sca.util.SubMonitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.statushandlers.StatusManager;

@SuppressWarnings("restriction")
public class GetSriHandler extends AbstractHandler {

	public GetSriHandler() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		final IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getActiveMenuSelection(event);
		final List<Object> elements = new ArrayList<Object>();

		if (selection == null) {
			selection = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
			if (selection == null) {
				PlotView2 view = (PlotView2) HandlerUtil.getActivePart(event);
				List<PlotSource> sources = view.getPlotPageBook().getSources();
				for (PlotSource source : sources) {
					elements.add(source.getInput());
				}
			} else {
				elements.addAll(selection.toList());
			}
		} else {
			elements.addAll(selection.toList());
		}

		if (elements.isEmpty()) {
			return null;
		}

		for (Object obj : elements) {
			final ScaUsesPort port = PluginUtil.adapt(ScaUsesPort.class, obj, true);

			try {
				IViewPart view = window.getActivePage().showView(SriDataView.ID, SriDataView.createSecondaryId(port), IWorkbenchPage.VIEW_ACTIVATE);
				if (view instanceof SriDataView) {
					final SriDataView sriView = (SriDataView) view;

					Job job = new Job("SRI View setup...") {

						@Override
						protected IStatus run(IProgressMonitor monitor) {
							final ScaItemProviderAdapterFactory factory = new ScaItemProviderAdapterFactory();
							final StringBuilder name = new StringBuilder();
							final StringBuilder tooltip = new StringBuilder();
							SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching SRI...", elements.size());

							if (port != null) {
								port.fetchAttributes(subMonitor.newChild(1));
								List<String> tmpList = new LinkedList<String>();
								for (EObject eObj = port; !(eObj instanceof ScaDomainManagerRegistry) && eObj != null; eObj = eObj.eContainer()) {
									Adapter adapter = factory.adapt(eObj, IItemLabelProvider.class);
									if (adapter instanceof IItemLabelProvider) {
										IItemLabelProvider lp = (IItemLabelProvider) adapter;
										String text = lp.getText(eObj);
										if (text != null && !text.isEmpty()) {
											tmpList.add(0, text);
										}
									}
								}

								String nameStr = port.getName();
								if (nameStr != null && nameStr != null && !nameStr.isEmpty()) {
									name.append(nameStr + " SRI ");
								}

								if (!tmpList.isEmpty()) {
									for (Iterator<String> i = tmpList.iterator(); i.hasNext();) {
										tooltip.append(i.next());
										if (i.hasNext()) {
											tooltip.append(" -> ");
										} else {
											tooltip.append(" -> SRI");
										}
									}
									tooltip.append("\n");
								}
							} else {
								subMonitor.worked(1);
							}
							sriView.activateReceiver(port);

							factory.dispose();
							if (name.length() > 0 || tooltip.length() > 0) {
								Display display = window.getWorkbench().getDisplay();
								display.asyncExec(new Runnable() {

									@Override
									public void run() {
										if (name.length() > 0) {
											sriView.setPartName(name.toString());
										}
										if (tooltip.length() > 0) {
											sriView.setTitleToolTip(tooltip.toString());
										}
									}
								});
							}
							return Status.OK_STATUS;
						}
					};
					job.schedule();
				}
			} catch (PartInitException e) {
				StatusManager.getManager().handle(new Status(Status.ERROR, BulkIOUIActivator.PLUGIN_ID, "Failed to show SRI Data View", e),
					StatusManager.LOG | StatusManager.SHOW);
			}
		}

		return null;
	}

}

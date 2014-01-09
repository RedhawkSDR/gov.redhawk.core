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
package gov.redhawk.internal.ui.port.nxmplot.handlers;

import gov.redhawk.internal.ui.port.nxmplot.FftParameterEntryDialog;
import gov.redhawk.internal.ui.port.nxmplot.view.PlotSource;
import gov.redhawk.internal.ui.port.nxmplot.view.PlotView2;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;
import gov.redhawk.sca.util.PluginUtil;
import gov.redhawk.sca.util.SubMonitor;
import gov.redhawk.ui.port.PortHelper;
import gov.redhawk.ui.port.nxmblocks.FftNxmBlockSettings;
import gov.redhawk.ui.port.nxmblocks.PlotNxmBlockSettings;
import gov.redhawk.ui.port.nxmplot.FftSettings;
import gov.redhawk.ui.port.nxmplot.PlotActivator;
import gov.redhawk.ui.port.nxmplot.PlotType;

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
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.statushandlers.StatusManager;

import BULKIO.dataFileHelper;
import BULKIO.dataSDDSHelper;
import BULKIO.dataXMLHelper;

/**
 * @noreference This class is not intended to be referenced by clients
 */
public class PlotPortHandler extends AbstractHandler {

	public PlotPortHandler() {
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		final IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		String plotTypeStr = event.getParameter("gov.redhawk.ui.port.nxmplot.type");
		final PlotType type;
		final boolean isFFT;
		
		// need to grab Port selections first, otherwise Plot Wizard option below will change the selection
		IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getActiveMenuSelection(event);
		if (selection == null) {
			selection = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
		}
		if (selection == null) {
			return null;
		}
		final List< ? > elements = selection.toList();
		final PlotWizardSettings plotWizardSettings;
		final FftNxmBlockSettings fftNxmBlockSettings;

		if (plotTypeStr != null) {
			type = PlotType.valueOf(plotTypeStr);
			isFFT = Boolean.valueOf(event.getParameter("gov.redhawk.ui.port.nxmplot.isFft"));
			plotWizardSettings = null;
			fftNxmBlockSettings = null;
		} else { // run advanced Port plot wizard
			boolean containsBulkIOPort = false;
			boolean containsSDDSPort = false;
			for (Object obj : elements) {
				ScaUsesPort port = PluginUtil.adapt(ScaUsesPort.class, obj, true);
				if (port != null) {
					String idl = port.getRepid();
					if (dataSDDSHelper.id().equals(idl)) { // a BULKIO:dataSDDS Port
						containsSDDSPort = true;
					} else if (!dataFileHelper.id().equals(idl) && !dataXMLHelper.id().equals(idl)) { // BULKIO:dataFile and BULIO:dataXML Ports are currently unsupported
						containsBulkIOPort = true;
					}
				}
			}
			PlotWizard wizard = new PlotWizard(containsBulkIOPort, containsSDDSPort); // advanced Port Plot wizard
			WizardDialog dialog = new WizardDialog(HandlerUtil.getActiveShell(event), wizard);
			if (dialog.open() == Window.OK) {
				plotWizardSettings = wizard.getPlotSettings();
				type = plotWizardSettings.getType();
				isFFT = plotWizardSettings.isFft();
				if (isFFT) {
					fftNxmBlockSettings = plotWizardSettings.getFftBlockSettings();
				} else {
					fftNxmBlockSettings = null;
				}
			} else {
				return null;
			}
		}

		final FftSettings fft;
		if (isFFT && plotWizardSettings == null) {
			final FftParameterEntryDialog fftDialog = new FftParameterEntryDialog(HandlerUtil.getActiveShell(event), new FftSettings());
			final int result = fftDialog.open();
			if (result == Window.OK) {
				fft = fftDialog.getFFTSettings();
			} else {
				fft = null;
			}
			if (fft == null) {
				return null;
			}
		} else {
			fft = null;
		}
		try {
			IViewPart view = window.getActivePage().showView(PlotView2.ID, PlotView2.createSecondaryId(), IWorkbenchPage.VIEW_ACTIVATE);
			if (view instanceof PlotView2) {
				final PlotView2 plotView = (PlotView2) view;
				plotView.getPlotPageBook().showPlot(type);

				Job job = new Job("Adding plot sources...") {
					@Override
					protected IStatus run(IProgressMonitor monitor) {
						final ScaItemProviderAdapterFactory factory = new ScaItemProviderAdapterFactory();
						final StringBuilder name = new StringBuilder();
						final StringBuilder tooltip = new StringBuilder();
						SubMonitor subMonitor = SubMonitor.convert(monitor, "Plotting...", elements.size());
						for (Object obj : elements) {
							ScaUsesPort port = PluginUtil.adapt(ScaUsesPort.class, obj, true);
							if (port != null) {
								port.fetchAttributes(subMonitor.newChild(1));
								List<String> tmpList4Tooltip = new LinkedList<String>();
								for (EObject eObj = port; !(eObj instanceof ScaDomainManagerRegistry) && eObj != null; eObj = eObj.eContainer()) {
									Adapter adapter = factory.adapt(eObj, IItemLabelProvider.class);
									if (adapter instanceof IItemLabelProvider) {
										IItemLabelProvider lp = (IItemLabelProvider) adapter;
										String text = lp.getText(eObj);
										if (text != null && !text.isEmpty()) {
											tmpList4Tooltip.add(0, text);
										}
									}
								}
								
								String nameStr = port.getName();
								if (nameStr != null && !nameStr.isEmpty()) {
									name.append(nameStr).append(" ");
								}

								if (!tmpList4Tooltip.isEmpty()) {
									for (Iterator<String> i = tmpList4Tooltip.iterator(); i.hasNext();) {
										tooltip.append(i.next());
										if (i.hasNext()) {
											tooltip.append(" -> ");
										}
									}
									tooltip.append("\n");
								}

								if (plotWizardSettings != null) {
									PlotSource plotSource;
									String idl = port.getRepid();
									String pipeQualifiers = null;
									PlotNxmBlockSettings plotBlockSettings = plotWizardSettings.getPlotBlockSettings();
									if (dataSDDSHelper.id().equals(idl)) { // a BULKIO:dataSDDS Port
										plotSource = new PlotSource(port, plotWizardSettings.getSddsBlockSettings(), fftNxmBlockSettings, plotBlockSettings, pipeQualifiers);
									} else if (!dataFileHelper.id().equals(idl) && !dataXMLHelper.id().equals(idl)) { // BULKIO:dataFile and BULIO:dataXML Ports are currently unsupported
										plotSource = new PlotSource(port, plotWizardSettings.getBulkIOBlockSettings(), fftNxmBlockSettings, plotBlockSettings, pipeQualifiers);
									} else {
										StatusManager.getManager().handle(new Status(Status.WARNING, PlotActivator.PLUGIN_ID, "Unsupported Port: " + port + " idl: " + idl), StatusManager.LOG); 
										continue; // log warning and skip unsupported Port type
									}
									plotView.addPlotSource2(plotSource);
								} else {
									plotView.addPlotSource(port, fft, null);
								}
								
							} else {
								subMonitor.worked(1);
							}
						} // end for loop
						PortHelper.refreshPorts(elements, subMonitor);
						factory.dispose();
						if (name.length() > 0 || tooltip.length() > 0) {
							Display display = plotView.getSite().getShell().getDisplay();
							display.asyncExec(new Runnable() {
								@Override
								public void run() {
									if (name.length() > 0) {
										plotView.setPartName(name.substring(0, name.length() - 1)); // remove trailing space from view's name
									}
									if (tooltip.length() > 0) {
										plotView.setTitleToolTip(tooltip.substring(0, tooltip.length() - 1)); // remove trailing newline from view's tooltip
									}
								}
							});
						}
						return Status.OK_STATUS;
					}
				};
				job.schedule(0);
			}
		} catch (PartInitException e) {
			StatusManager.getManager().handle(new Status(Status.ERROR, PlotActivator.PLUGIN_ID, "Failed to show Plot View", e),
				StatusManager.LOG | StatusManager.SHOW);
		}
		return null;
	}

}

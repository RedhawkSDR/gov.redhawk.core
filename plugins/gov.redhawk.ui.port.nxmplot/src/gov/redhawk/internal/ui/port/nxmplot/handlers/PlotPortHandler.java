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
import gov.redhawk.internal.ui.port.nxmplot.view.PlotView2;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;
import gov.redhawk.model.sca.util.RedhawkEvents;
import gov.redhawk.sca.util.PluginUtil;
import gov.redhawk.sca.util.SubMonitor;
import gov.redhawk.ui.port.PortHelper;
import gov.redhawk.ui.port.nxmblocks.FftNxmBlockSettings;
import gov.redhawk.ui.port.nxmblocks.PlotNxmBlockSettings;
import gov.redhawk.ui.port.nxmplot.FftSettings;
import gov.redhawk.ui.port.nxmplot.IPlotWidgetListener;
import gov.redhawk.ui.port.nxmplot.PlotActivator;
import gov.redhawk.ui.port.nxmplot.PlotEvent;
import gov.redhawk.ui.port.nxmplot.PlotEvent.Click;
import gov.redhawk.ui.port.nxmplot.PlotEvent.DragBox;
import gov.redhawk.ui.port.nxmplot.PlotEvent.Motion;
import gov.redhawk.ui.port.nxmplot.PlotEvent.Pan;
import gov.redhawk.ui.port.nxmplot.PlotEvent.ZoomIn;
import gov.redhawk.ui.port.nxmplot.PlotEvent.ZoomOut;
import gov.redhawk.ui.port.nxmplot.PlotEvent.ZoomX;
import gov.redhawk.ui.port.nxmplot.PlotSource;
import gov.redhawk.ui.port.nxmplot.PlotType;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

	private static final String PARAM_PLOT_TYPE = "gov.redhawk.ui.port.nxmplot.type";

	private static final String PARAM_ISFFT = "gov.redhawk.ui.port.nxmplot.isFft";

	public PlotPortHandler() {
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		final IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		String plotTypeStr = event.getParameter(PlotPortHandler.PARAM_PLOT_TYPE);

		// need to grab Port selections first, otherwise Plot Wizard option below will change the selection
		IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getActiveMenuSelection(event);
		if (selection == null) {
			selection = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
		}
		if (selection == null) {
			return null;
		}
		final List< ? > elements = selection.toList();

		// Both of these are always set (below)
		final PlotType type;
		final boolean isFFT;

		// Either fft gets set, or plotWizardSettings and fftNxmBlockSettings get set (below)
		final FftSettings fft;
		final PlotWizardSettings plotWizardSettings;
		final FftNxmBlockSettings fftNxmBlockSettings;

		if (plotTypeStr != null) {
			type = PlotType.valueOf(plotTypeStr);
			isFFT = Boolean.valueOf(event.getParameter(PlotPortHandler.PARAM_ISFFT));
			plotWizardSettings = null;
			fftNxmBlockSettings = null;

			if (isFFT) {
				final FftParameterEntryDialog fftDialog = new FftParameterEntryDialog(HandlerUtil.getActiveShell(event), new FftSettings());
				final int result = fftDialog.open();
				if (result == Window.OK) {
					fft = fftDialog.getFFTSettings();
				} else {
					return null;
				}
			} else {
				fft = null;
			}
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
			if (dialog.open() != Window.OK) {
				return null;
			}
			plotWizardSettings = wizard.getPlotSettings();
			type = plotWizardSettings.getType();
			isFFT = plotWizardSettings.isFft();
			if (isFFT) {
				fftNxmBlockSettings = plotWizardSettings.getFftBlockSettings();
			} else {
				fftNxmBlockSettings = null;
			}
			fft = null;
		}

		try {
			final IViewPart view = window.getActivePage().showView(PlotView2.ID, PlotView2.createSecondaryId(), IWorkbenchPage.VIEW_ACTIVATE);
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
										plotSource = new PlotSource(port, plotWizardSettings.getSddsBlockSettings(), fftNxmBlockSettings, plotBlockSettings,
											pipeQualifiers);
									} else if (!dataFileHelper.id().equals(idl) && !dataXMLHelper.id().equals(idl)) { // BULKIO:dataFile and BULIO:dataXML Ports are currently unsupported
										plotSource = new PlotSource(port, plotWizardSettings.getBulkIOBlockSettings(), fftNxmBlockSettings, plotBlockSettings,
											pipeQualifiers);
									} else {
										StatusManager.getManager().handle(
											new Status(IStatus.WARNING, PlotActivator.PLUGIN_ID, "Unsupported Port: " + port + " idl: " + idl),
											StatusManager.LOG);
										continue; // log warning and skip unsupported Port type
									}
									plotView.getPlotPageBook().addSource(plotSource);
								} else {
									plotView.addPlotSource(port, fft, null);
								}

								// Add handler
								addEventForward(port, plotView);
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
			StatusManager.getManager().handle(new Status(IStatus.ERROR, PlotActivator.PLUGIN_ID, "Failed to show Plot View", e),
				StatusManager.LOG | StatusManager.SHOW);
		}
		return null;
	}

	protected void addEventForward(final ScaUsesPort port, final PlotView2 plotView) {
		plotView.getPlotPageBook().addPlotListener(new IPlotWidgetListener() {

			private String getTopic(String subTopic) {
				return PlotEvent.EventTags.TOPIC + "/" + subTopic;
			}

			@Override
			public void zoomX(double xmin, double ymin, double xmax, double ymax, Object data) {
				String topic = getTopic("zoomX");
				Map<String, Object> argMap = RedhawkEvents.createMap(port, topic);
				ZoomX event = new PlotEvent.ZoomX(plotView.getPlotPageBook(), data, xmin, ymin, xmax, ymax);
				argMap.put(PlotEvent.EventTags.PLOT_EVENT, event);
				argMap.put(PlotEvent.EventTags.SOURCE_VIEW, plotView);
				argMap.put(PlotEvent.EventTags.SOURCE_VIEW_ID, plotView.getViewSite().getId());
				argMap.put(PlotEvent.EventTags.SOURCE_VIEW_SECONDARY_ID, plotView.getViewSite().getSecondaryId());
				RedhawkEvents.publishEvent(topic, argMap);
			}

			@Override
			public void zoomOut(double x1, double y1, double x2, double y2, Object data) {
				String topic = getTopic("zoomOut");
				Map<String, Object> argMap = RedhawkEvents.createMap(port, topic);
				ZoomOut event = new PlotEvent.ZoomOut(plotView.getPlotPageBook(), data, x1, y1, x2, x2);
				argMap.put(PlotEvent.EventTags.PLOT_EVENT, event);
				argMap.put(PlotEvent.EventTags.SOURCE_VIEW, plotView);
				argMap.put(PlotEvent.EventTags.SOURCE_VIEW_ID, plotView.getViewSite().getId());
				argMap.put(PlotEvent.EventTags.SOURCE_VIEW_SECONDARY_ID, plotView.getViewSite().getSecondaryId());
				RedhawkEvents.publishEvent(topic, argMap);
			}

			@Override
			public void zoomIn(double xmin, double ymin, double xmax, double ymax, Object data) {
				ZoomIn event = new PlotEvent.ZoomIn(plotView.getPlotPageBook(), data, xmin, ymin, xmax, ymax);
				String topic = getTopic("zoomIn");
				Map<String, Object> argMap = RedhawkEvents.createMap(port, topic);
				argMap.put(PlotEvent.EventTags.PLOT_EVENT, event);
				argMap.put(PlotEvent.EventTags.SOURCE_VIEW, plotView);
				argMap.put(PlotEvent.EventTags.SOURCE_VIEW_ID, plotView.getViewSite().getId());
				argMap.put(PlotEvent.EventTags.SOURCE_VIEW_SECONDARY_ID, plotView.getViewSite().getSecondaryId());
				RedhawkEvents.publishEvent(topic, argMap);
			}

			@Override
			public void unzoom(double x1, double y1, double x2, double y2, Object data) {
				ZoomIn event = new PlotEvent.ZoomIn(plotView.getPlotPageBook(), data, x1, y1, x2, y2);
				String topic = getTopic("unzoom");
				Map<String, Object> argMap = RedhawkEvents.createMap(port, topic);
				argMap.put(PlotEvent.EventTags.PLOT_EVENT, event);
				argMap.put(PlotEvent.EventTags.SOURCE_VIEW, plotView);
				argMap.put(PlotEvent.EventTags.SOURCE_VIEW_ID, plotView.getViewSite().getId());
				argMap.put(PlotEvent.EventTags.SOURCE_VIEW_SECONDARY_ID, plotView.getViewSite().getSecondaryId());
				RedhawkEvents.publishEvent(topic, argMap);
			}

			@Override
			public void pan(double x1, double y1, double x2, double y2) {
				Pan event = new PlotEvent.Pan(plotView.getPlotPageBook(), null, x1, y1, x2, y2);
				String topic = getTopic("pan");
				Map<String, Object> argMap = RedhawkEvents.createMap(port, topic);
				argMap.put(PlotEvent.EventTags.PLOT_EVENT, event);
				argMap.put(PlotEvent.EventTags.SOURCE_VIEW, plotView);
				argMap.put(PlotEvent.EventTags.SOURCE_VIEW_ID, plotView.getViewSite().getId());
				argMap.put(PlotEvent.EventTags.SOURCE_VIEW_SECONDARY_ID, plotView.getViewSite().getSecondaryId());
				RedhawkEvents.publishEvent(topic, argMap);
			}

			@Override
			public void motion(double x, double y, double t) {
				Motion event = new PlotEvent.Motion(plotView.getPlotPageBook(), null, x, y, t);
				String topic = getTopic("motion");
				Map<String, Object> argMap = RedhawkEvents.createMap(port, topic);
				argMap.put(PlotEvent.EventTags.PLOT_EVENT, event);
				argMap.put(PlotEvent.EventTags.SOURCE_VIEW, plotView);
				argMap.put(PlotEvent.EventTags.SOURCE_VIEW_ID, plotView.getViewSite().getId());
				argMap.put(PlotEvent.EventTags.SOURCE_VIEW_SECONDARY_ID, plotView.getViewSite().getSecondaryId());
				RedhawkEvents.publishEvent(topic, argMap);
			}

			@Override
			public void dragBox(double xmin, double ymin, double xmax, double ymax) {
				DragBox event = new PlotEvent.DragBox(plotView.getPlotPageBook(), null, xmin, ymin, xmax, ymax);
				String topic = getTopic("dragBox");
				Map<String, Object> argMap = RedhawkEvents.createMap(port, topic);
				argMap.put(PlotEvent.EventTags.PLOT_EVENT, event);
				argMap.put(PlotEvent.EventTags.SOURCE_VIEW, plotView);
				argMap.put(PlotEvent.EventTags.SOURCE_VIEW_ID, plotView.getViewSite().getId());
				argMap.put(PlotEvent.EventTags.SOURCE_VIEW_SECONDARY_ID, plotView.getViewSite().getSecondaryId());
				RedhawkEvents.publishEvent(topic, argMap);
			}

			@Override
			public void click(double x, double y, double t) {
				Click event = new PlotEvent.Click(plotView.getPlotPageBook(), null, x, y, t);
				String topic = getTopic("click");
				Map<String, Object> argMap = RedhawkEvents.createMap(port, topic);
				argMap.put(PlotEvent.EventTags.PLOT_EVENT, event);
				argMap.put(PlotEvent.EventTags.SOURCE_VIEW, plotView);
				argMap.put(PlotEvent.EventTags.SOURCE_VIEW_ID, plotView.getViewSite().getId());
				argMap.put(PlotEvent.EventTags.SOURCE_VIEW_SECONDARY_ID, plotView.getViewSite().getSecondaryId());
				RedhawkEvents.publishEvent(topic, argMap);
			}
		});
	}
}

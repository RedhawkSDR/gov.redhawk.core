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

import gov.redhawk.bulkio.util.BulkIOType;
import gov.redhawk.internal.ui.port.nxmplot.view.PlotView2;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;
import gov.redhawk.sca.model.jobs.RefreshJob;
import gov.redhawk.sca.util.PluginUtil;
import gov.redhawk.sca.util.SubMonitor;
import gov.redhawk.ui.port.nxmblocks.BulkIONxmBlockSettings;
import gov.redhawk.ui.port.nxmblocks.FftNxmBlockSettings;
import gov.redhawk.ui.port.nxmblocks.PlotNxmBlockSettings;
import gov.redhawk.ui.port.nxmblocks.SddsNxmBlockSettings;
import gov.redhawk.ui.port.nxmplot.IPlotView;
import gov.redhawk.ui.port.nxmplot.PlotActivator;
import gov.redhawk.ui.port.nxmplot.PlotSettings;
import gov.redhawk.ui.port.nxmplot.PlotSettings.PlotMode;
import gov.redhawk.ui.port.nxmplot.PlotSource;
import gov.redhawk.ui.port.nxmplot.PlotType;
import gov.redhawk.ui.port.nxmplot.preferences.FftPreferences;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
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

import BULKIO.dataSDDSHelper;

/**
 * @noreference This class is not intended to be referenced by clients
 */
public class PlotPortHandler extends AbstractHandler {

	public PlotPortHandler() {
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		PlotPortHandler.showView(event);
		return null;
	}

	public static IPlotView showView(ExecutionEvent event) {
		final IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		String plotTypeStr = event.getParameter(IPlotView.PARAM_PLOT_TYPE);

		// need to grab Port selections first, otherwise Plot Wizard option below will change the selection
		IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getActiveMenuSelection(event);
		if (selection == null) {
			selection = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
		}
		if (selection == null) {
			return null;
		}
		List<ScaUsesPort> ports = ((List< ? >) selection.toList()).stream() //
				.map(element -> PluginUtil.adapt(ScaUsesPort.class, element, true)) //
				.filter(element -> element != null) //
				.collect(Collectors.toList());

		// Both of these are always set (below)
		final boolean isFFT;
		PlotSettings plotSettings = new PlotSettings();

		// Either fft gets set, or plotWizardSettings and fftNxmBlockSettings get set (below)
		final BulkIONxmBlockSettings bulkIOBlockSettings;
		final SddsNxmBlockSettings sddsBlockSettings;
		final FftNxmBlockSettings fftBlockSettings;
		final PlotNxmBlockSettings plotBlockSettings;
		final String pipeQualifiers = null;

		boolean containsBulkIOPort = false;
		boolean containsSDDSPort = false;
		for (ScaUsesPort port : ports) {
			String idl = port.getRepid();
			if (dataSDDSHelper.id().equals(idl)) { // a BULKIO:dataSDDS Port
				containsSDDSPort = true;
			} else if (PlotPortHandler.isBulkIOPortSupported(idl)) { // BULKIO data Port
				containsBulkIOPort = true;
			}
		}

		if (plotTypeStr != null) {
			//because this evaluates to true, we do not end up using addSource2 method
			plotSettings.setPlotType(PlotType.valueOf(plotTypeStr));
			isFFT = Boolean.valueOf(event.getParameter(IPlotView.PARAM_ISFFT));

			if (isFFT) {
				plotSettings.setPlotMode(PlotMode.valueOf(FftPreferences.FFT_MODE.getValue(PlotActivator.getDefault().getPreferenceStore())));
				fftBlockSettings = new FftNxmBlockSettings();
			} else {
				fftBlockSettings = null;
			}
			if (containsSDDSPort) {
				sddsBlockSettings = new SddsNxmBlockSettings();
				sddsBlockSettings.setConnectionID(event.getParameter(IPlotView.PARAM_CONNECTION_ID));
			} else {
				sddsBlockSettings = null;
			}
			if (containsBulkIOPort) {
				bulkIOBlockSettings = new BulkIONxmBlockSettings();
				bulkIOBlockSettings.setConnectionID(event.getParameter(IPlotView.PARAM_CONNECTION_ID));
			} else {
				bulkIOBlockSettings = null;
			}
			plotBlockSettings = new PlotNxmBlockSettings();
		} else { // run advanced Port plot wizard
			PlotWizard wizard = new PlotWizard(containsBulkIOPort, containsSDDSPort); // advanced Port Plot wizard
			bulkIOBlockSettings = wizard.getBulkIOBlockSettings();
			if (bulkIOBlockSettings != null) {
				bulkIOBlockSettings.setConnectionID(event.getParameter(IPlotView.PARAM_CONNECTION_ID));
			}
			sddsBlockSettings = wizard.getSddsBlockSettings();
			if (sddsBlockSettings != null) {
				sddsBlockSettings.setConnectionID(event.getParameter(IPlotView.PARAM_CONNECTION_ID));
			}

			WizardDialog dialog = new WizardDialog(HandlerUtil.getActiveShell(event), wizard);
			if (dialog.open() != Window.OK) {
				return null;
			}
			plotSettings = wizard.getPlotSettings();
			isFFT = wizard.isFft();
			if (isFFT) {
				fftBlockSettings = wizard.getFftBlockSettings();
			} else {
				fftBlockSettings = null;
			}

			plotBlockSettings = wizard.getPlotBlockSettings();
		}

		try {
			String secondaryID = event.getParameter(IPlotView.PARAM_SECONDARY_ID);
			if (secondaryID == null || secondaryID.isEmpty()) {
				secondaryID = PlotView2.createSecondaryId();
			}
			final IViewPart view = window.getActivePage().showView(PlotView2.ID, secondaryID, IWorkbenchPage.VIEW_ACTIVATE);
			if (view instanceof PlotView2) {
				final PlotView2 plotView = (PlotView2) view;
				plotView.getPlotPageBook().showPlot(plotSettings);

				Job job = Job.create("Adding plot sources...", monitor -> {
					return PlotPortHandler.addPlotSources(ports, bulkIOBlockSettings, sddsBlockSettings, fftBlockSettings, plotBlockSettings, pipeQualifiers,
						plotView, monitor);
				});
				job.setUser(true);
				job.schedule();
				return plotView;
			}
		} catch (PartInitException e) {
			StatusManager.getManager().handle(new Status(IStatus.ERROR, PlotActivator.PLUGIN_ID, "Failed to show Plot View", e),
				StatusManager.LOG | StatusManager.SHOW);
		}
		return null;
	}

	public static boolean isBulkIOPortSupported(String idl) {
		if (BulkIOType.isTypeSupported(idl) || dataSDDSHelper.id().equals(idl)) {
			return true;
		}
		return false;
	}

	private static IStatus addPlotSources(final List<ScaUsesPort> ports, final BulkIONxmBlockSettings bulkIOBlockSettings,
		final SddsNxmBlockSettings sddsBlockSettings, final FftNxmBlockSettings fftBlockSettings, final PlotNxmBlockSettings plotBlockSettings,
		final String pipeQualifiers, final PlotView2 plotView, IProgressMonitor monitor) {
		final ScaItemProviderAdapterFactory factory = new ScaItemProviderAdapterFactory();
		final StringBuilder name = new StringBuilder();
		final StringBuilder tooltip = new StringBuilder();

		SubMonitor progress = SubMonitor.convert(monitor, "Plotting...", ports.size() * 2);
		for (ScaUsesPort port : ports) {
			port.fetchAttributes(progress.newChild(1));

			getNameAndTooltip(port, factory, name, tooltip);

			final PlotSource plotSource;
			final String idl = port.getRepid();

			if (dataSDDSHelper.id().equals(idl)) { // a BULKIO:dataSDDS Port
				plotSource = new PlotSource(port, sddsBlockSettings, fftBlockSettings, plotBlockSettings, pipeQualifiers);
			} else if (PlotPortHandler.isBulkIOPortSupported(idl)) { // supported BULKIO data Port
				plotSource = new PlotSource(port, bulkIOBlockSettings, fftBlockSettings, plotBlockSettings, pipeQualifiers);
			} else {
				// Log warning and skip unsupported port
				String msg = String.format("Cannot plot port '%s' due to unsupported type '%s'", port.getName(), idl);
				StatusManager.getManager().handle(new Status(IStatus.WARNING, PlotActivator.PLUGIN_ID, msg), StatusManager.LOG);
				continue;
			}
			plotView.addPlotSource(plotSource);
			progress.worked(1);
		}
		factory.dispose();

		RefreshJob.create(ports).schedule();

		if (name.length() > 0 || tooltip.length() > 0) {
			Display display = plotView.getSite().getShell().getDisplay();
			display.asyncExec(() -> {
				if (name.length() > 0) {
					plotView.setPartName(name.substring(0, name.length() - 1)); // remove trailing space from view's name
				}
				if (tooltip.length() > 0) {
					plotView.setTitleToolTip(tooltip.substring(0, tooltip.length() - 1)); // remove trailing newline from view's tooltip
				}
			});
		}
		return Status.OK_STATUS;
	}

	private static void getNameAndTooltip(ScaUsesPort port, AdapterFactory factory, StringBuilder name, StringBuilder tooltip) {
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
	}
}

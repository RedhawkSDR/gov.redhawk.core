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
package gov.redhawk.frontend.ui.internal;

import gov.redhawk.frontend.FrontendFactory;
import gov.redhawk.frontend.Plot;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.edit.utils.TunerProperties.ListenerAllocationProperties;
import gov.redhawk.frontend.edit.utils.TunerUtils;
import gov.redhawk.frontend.ui.FrontEndUIActivator;
import gov.redhawk.internal.ui.port.nxmplot.view.PlotSource;
import gov.redhawk.internal.ui.port.nxmplot.view.PlotView2;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;
import gov.redhawk.sca.util.PluginUtil;
import gov.redhawk.sca.util.SubMonitor;
import gov.redhawk.ui.port.PortHelper;
import gov.redhawk.ui.port.nxmblocks.BulkIONxmBlockSettings;
import gov.redhawk.ui.port.nxmblocks.FftNxmBlockSettings;
import gov.redhawk.ui.port.nxmblocks.PlotNxmBlockSettings;
import gov.redhawk.ui.port.nxmblocks.SddsNxmBlockSettings;
import gov.redhawk.ui.port.nxmplot.IPlotView;
import gov.redhawk.ui.port.nxmplot.PlotActivator;
import gov.redhawk.ui.port.nxmplot.PlotType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.Simple;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
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
import CF.DataType;
import CF.PropertiesHelper;
import CF.DevicePackage.InsufficientCapacity;
import CF.DevicePackage.InvalidCapacity;
import CF.DevicePackage.InvalidState;

/**
 *
 */
@SuppressWarnings("restriction")
public class PlotHandler extends AbstractHandler implements IHandler {

	private static final AtomicInteger uniquePlotViewSecondaryId = new AtomicInteger();
	private ScaDevice< ? > device;
	private TunerStatus tuner;

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		final IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);

		// need to grab Port selections first, otherwise Plot Wizard option below will change the selection
		IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getActiveMenuSelection(event);
		if (selection == null) {
			selection = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
			if (selection == null) {
				return null;
			}
		}

		final List< ? > elements = selection.toList();
		for (Object obj : elements) {
			if (obj instanceof TunerStatus) {
				tuner = (TunerStatus) obj;
				device = tuner.getTunerContainer().getModelDevice().getScaDevice();
				final DataType[] props = createAllocationProperties();
				Job job = new Job("Allocate Capacity") {
					@Override
					protected IStatus run(IProgressMonitor monitor) {
						try {
							device.allocateCapacity(props);
							device.refresh(null, RefreshDepth.SELF);
						} catch (InvalidCapacity e) {
							return new Status(Status.ERROR, FrontEndUIActivator.PLUGIN_ID, "Invalid Capacity in plot allocation: " + e.msg, e);
						} catch (InvalidState e) {
							return new Status(Status.ERROR, FrontEndUIActivator.PLUGIN_ID, "Invalid State in plot allocation: " + e.msg, e);
						} catch (InsufficientCapacity e) {
							return new Status(Status.ERROR, FrontEndUIActivator.PLUGIN_ID, "Insufficient Capacity in plot allocation: " + e.msg, e);
						} catch (InterruptedException e) {
							return new Status(Status.ERROR, FrontEndUIActivator.PLUGIN_ID, "Interrupted Exception during plot allocation", e);
						}
						return Status.OK_STATUS;
					}
				};
				job.setUser(true);
				job.schedule();
				createPlotView(window, elements, props);
			}
		}

		return null;
	}

	private DataType[] createAllocationProperties() {
		List<DataType> listenerCapacity = new ArrayList<DataType>();
		ScaStructProperty struct;
		DataType dt = new DataType();
		struct = ScaFactory.eINSTANCE.createScaStructProperty();
		for (ListenerAllocationProperties allocProp : ListenerAllocationProperties.values()) {
			ScaSimpleProperty simple = ScaFactory.eINSTANCE.createScaSimpleProperty();
			Simple definition = (Simple) PrfFactory.eINSTANCE.create(PrfPackage.Literals.SIMPLE);
			definition.setType(allocProp.getType());
			definition.setId(allocProp.getType().getLiteral());
			definition.setName(allocProp.getType().getName());
			simple.setDefinition(definition);
			simple.setId(allocProp.getId());

			switch (allocProp) {
			case EXISTING_ALLOCATION_ID:
				simple.setValue(TunerUtils.getControlId(tuner));
				break;
			case LISTENER_ALLOCATION_ID:
				String listenerAllocationID = "Plot_" + System.getProperty("user.name") + ":" + System.currentTimeMillis();
				simple.setValue(listenerAllocationID);
				break;
			default:
			}
			struct.getSimples().add(simple);
		}
		dt.id = "FRONTEND::listener_allocation";
		dt.value = struct.toAny();
		listenerCapacity.add(dt);
		return listenerCapacity.toArray(new DataType[0]);
	}

	private void createPlotView(IWorkbenchWindow window, final List< ? > elements, final DataType[] props) {
		PlotType type = PlotType.LINE;
		final BulkIONxmBlockSettings bulkIOBlockSettings = new BulkIONxmBlockSettings();
		final PlotNxmBlockSettings plotBlockSettings = new PlotNxmBlockSettings();
		final SddsNxmBlockSettings sddsBlockSettings = new SddsNxmBlockSettings();
		//		final FftNxmBlockSettings fftBlockSettings = new FftNxmBlockSettings();
		final FftNxmBlockSettings fftBlockSettings = null;

		try {
			final IViewPart view = window.getActivePage().showView(IPlotView.ID, createSecondaryId(), IWorkbenchPage.VIEW_ACTIVATE);
			if (view instanceof PlotView2) {
				final PlotView2 plotView = (PlotView2) view;
				plotView.getPlotPageBook().showPlot(type);
				plotView.getPlotPageBook().addDisposeListener(getDisposeListener(props));

				Job job = new Job("Adding plot sources...") {
					@Override
					protected IStatus run(IProgressMonitor monitor) {
						final ScaItemProviderAdapterFactory factory = new ScaItemProviderAdapterFactory();
						final StringBuilder name = new StringBuilder();
						final StringBuilder tooltip = new StringBuilder();
						SubMonitor subMonitor = SubMonitor.convert(monitor, "Plotting...", elements.size());

						EList<ScaPort< ? , ? >> devicePorts = device.getPorts();
						for (ScaPort< ? , ? > port : devicePorts) {
							ScaUsesPort usesPort = PluginUtil.adapt(ScaUsesPort.class, port, true);

							if (usesPort != null) {
								usesPort.fetchAttributes(subMonitor.newChild(1));
								createTooltip(factory, name, tooltip, usesPort);
								bulkIOBlockSettings.setConnectionID(getListenerID(props));

								PlotSource plotSource;
								String idl = usesPort.getRepid();
								String pipeQualifiers = null;
								if (dataSDDSHelper.id().equals(idl)) { // a BULKIO:dataSDDS Port
									plotSource = new PlotSource(usesPort, sddsBlockSettings, fftBlockSettings, plotBlockSettings, pipeQualifiers);
								} else if (!dataFileHelper.id().equals(idl) && !dataXMLHelper.id().equals(idl)) { // BULKIO:dataFile and BULIO:dataXML Ports are currently unsupported
									plotSource = new PlotSource(usesPort, bulkIOBlockSettings, fftBlockSettings, plotBlockSettings, pipeQualifiers);
								} else {
									StatusManager.getManager().handle(
										new Status(IStatus.WARNING, PlotActivator.PLUGIN_ID, "Unsupported Port: " + usesPort + " idl: " + idl),
										StatusManager.LOG);
									continue; // log warning and skip unsupported Port type
								}
								plotView.addPlotSource2(plotSource);

								// TODO: Add handler - addEventForward(port, plotView) 
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

						// Creates a plot model object with reference to the listener ID and plotview for later manipulation
						// Including deallocation and disposal
						Plot plotModelObject = FrontendFactory.eINSTANCE.createPlot();
						plotModelObject.setListenerID(getListenerID(props));
						plotModelObject.setPlotView(plotView);
						tuner.getPlots().add(plotModelObject);

						return Status.OK_STATUS;

					}
				};
				job.schedule(0);
			}
		} catch (PartInitException e) {
			StatusManager.getManager().handle(new Status(IStatus.ERROR, PlotActivator.PLUGIN_ID, "Failed to show Plot View", e),
				StatusManager.LOG | StatusManager.SHOW);
		}

	}

	/**
	 * Calls deallocateCapacity(DataType[] capacities) when the plot view is disposed
	 * @param props Listener Capacity to pass to deallocateCapacity()
	 * @return
	 */
	private DisposeListener getDisposeListener(final DataType[] props) {
		DisposeListener disposeListener = new DisposeListener() {

			@Override
			public void widgetDisposed(DisposeEvent e) {
				Job job = new Job("Deallocate Listener") {
					@Override
					protected IStatus run(IProgressMonitor monitor) {
						try {
							device.deallocateCapacity(props);
							device.refresh(null, RefreshDepth.SELF);
						} catch (InvalidCapacity e) {
							return new Status(Status.ERROR, FrontEndUIActivator.PLUGIN_ID, "Invalid Capacity in plot deallocation: " + e.msg, e);
						} catch (InvalidState e) {
							return new Status(Status.ERROR, FrontEndUIActivator.PLUGIN_ID, "Invalid State in plot deallocation: " + e.msg, e);
						} catch (InterruptedException e) {
							return new Status(Status.ERROR, FrontEndUIActivator.PLUGIN_ID, "Interrupted Exception during plot deallocation", e);
						}
						return Status.OK_STATUS;
					}
				};
				job.setUser(true);
				job.schedule();
			}
		};
		return disposeListener;
	}

	private String getListenerID(DataType[] props) {
		for (DataType prop : props) {
			DataType[] dt = PropertiesHelper.extract(prop.value);
			for (DataType d : dt) {
				if (d.id.equals(ListenerAllocationProperties.LISTENER_ALLOCATION_ID.getId())) {
					return (d.value.toString());
				}
				;
			}
		}
		return "";
	}

	private String createSecondaryId() {
		return "FEI" + uniquePlotViewSecondaryId.incrementAndGet();
	}

	private void createTooltip(final ScaItemProviderAdapterFactory factory, final StringBuilder name, final StringBuilder tooltip, ScaUsesPort usesPort) {
		List<String> tmpList4Tooltip = new LinkedList<String>();
		for (EObject eObj = usesPort; !(eObj instanceof ScaDomainManagerRegistry) && eObj != null; eObj = eObj.eContainer()) {
			Adapter adapter = factory.adapt(eObj, IItemLabelProvider.class);
			if (adapter instanceof IItemLabelProvider) {
				IItemLabelProvider lp = (IItemLabelProvider) adapter;
				String text = lp.getText(eObj);
				if (text != null && !text.isEmpty()) {
					tmpList4Tooltip.add(0, text);
				}
			}
		}

		String nameStr = usesPort.getName();
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

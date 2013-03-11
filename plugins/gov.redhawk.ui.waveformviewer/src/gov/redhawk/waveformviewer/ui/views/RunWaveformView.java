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
package gov.redhawk.waveformviewer.ui.views;

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.util.ScaAdapterFactory;
import gov.redhawk.sca.ScaPlugin;
import gov.redhawk.waveformviewer.ui.ApplicationActionListener;
import gov.redhawk.waveformviewer.ui.WaveformProjectPlugin;
import gov.redhawk.waveformviewer.ui.handler.InstallWaveform;
import gov.redhawk.waveformviewer.ui.handler.UninstallWaveform;

import java.util.List;
import java.util.Vector;

import org.eclipse.core.commands.common.CommandException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.statushandlers.StatusManager;

public class RunWaveformView extends ViewPart implements DragSourceListener, DropTargetListener, ApplicationActionListener {
	public static final String ID = "gov.redhawk.ui.waveform.view.running";

	/** The object that's being dragged off the view */
	private ScaWaveform dragSource = null;
	/** Flag to prevent double processing of DND drops */
	private boolean ignoreCallback = false;
	/** The main object of the view, the tree of applications */
	private TreeViewer treeViewer;
	/** Vector of running SCA applications */
	private Vector<ScaWaveform> apps;

	@Override
	public void createPartControl(final Composite parent) {
		this.apps = new Vector<ScaWaveform>();
		this.treeViewer = new TreeViewer(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);

		getSite().setSelectionProvider(this.treeViewer);
		this.treeViewer.setContentProvider(new AdapterFactoryContentProvider(new ScaAdapterFactory()));
		this.treeViewer.setLabelProvider(new AdapterFactoryLabelProvider(new ScaAdapterFactory()));

		final Transfer[] types = new Transfer[] { TextTransfer.getInstance() };
		this.treeViewer.addDragSupport(DND.DROP_MOVE, types, this);
		this.treeViewer.addDropSupport(DND.DROP_COPY, types, this);

		// Setup right click menu
		final MenuManager manager = new MenuManager();
		final Menu menu = manager.createContextMenu(this.treeViewer.getTree());
		this.treeViewer.getTree().setMenu(menu);
		getSite().registerContextMenu(RunWaveformView.ID, manager, this.treeViewer);

		initializeWaveform();
	}

	/**
	 * This retrieves a list of running applications on all domain managers
	 */
	private void initializeWaveform() {
		// Get all domain managers
		final ScaDomainManagerRegistry registry = ScaPlugin.getDefault().getDomainManagerRegistry();
		final EList<ScaDomainManager> doms = registry.getDomains();

		this.apps.clear();

		// Loop through all domain managers, looking for running applications
		for (final ScaDomainManager dom : doms) {
			if (dom.isConnected()) {
				final EList<ScaWaveform> waveforms = dom.getWaveforms();
				for (final ScaWaveform app : waveforms) {
					this.apps.add(app);
				}
			}
		}

		// Set the tree's input if there are running applications
		if (this.apps.size() > 0) {
			this.treeViewer.setInput(this.apps.toArray());
			this.treeViewer.refresh();
		}
	}

	@Override
	public void setFocus() {
		this.treeViewer.getControl().setFocus();
	}

	public void dragEnter(final DropTargetEvent event) {
		event.detail = DND.DROP_COPY;
	}

	public void dragLeave(final DropTargetEvent event) {
	}

	public void dragOperationChanged(final DropTargetEvent event) {
	}

	public void dragOver(final DropTargetEvent event) {
	}

	public void drop(final DropTargetEvent event) {
		final Object[] expanded = this.treeViewer.getExpandedElements();

		// Only accept drops of ID's
		if (event.data instanceof String) {
			final IHandlerService serv = (IHandlerService) getSite().getService(IHandlerService.class);
			try {
				this.ignoreCallback = true;
				final Object obj = serv.executeCommand(InstallWaveform.ID, null);
				this.ignoreCallback = false;

				// Add all installed applications to the list
				if (obj instanceof List< ? >) {
					final List< ? > list = (List< ? >) obj;
					for (final Object listObj : list) {
						if (listObj instanceof ScaWaveform) {
							this.apps.add((ScaWaveform) listObj);
						}
					}
				}
			} catch (final CommandException e) {
				StatusManager.getManager().handle(new Status(IStatus.ERROR, WaveformProjectPlugin.PLUGIN_ID, "Failed to Drop on to waveform viewer.", e));
			}
		}
		if (this.apps.size() >= 1) {
			this.treeViewer.setInput(this.apps.toArray());
		}
		this.treeViewer.refresh();
		this.treeViewer.setExpandedElements(expanded);
	}

	public void dropAccept(final DropTargetEvent event) {
	}

	public void dragFinished(final DragSourceEvent event) {
		if (this.dragSource != null && event.detail == DND.DROP_MOVE) {
			final IHandlerService serv = (IHandlerService) getSite().getService(IHandlerService.class);
			final Object[] expanded = this.treeViewer.getExpandedElements();

			try {
				// Don't process the add twice, ignore the first callback
				this.ignoreCallback = true;
				serv.executeCommand(UninstallWaveform.ID, null);
				this.ignoreCallback = false;

				this.apps.remove(this.dragSource);

				// Update the tree
				this.treeViewer.setInput(this.apps.toArray());
				this.treeViewer.refresh(true);
				this.treeViewer.setExpandedElements(expanded);
			} catch (final CommandException e) {
				StatusManager.getManager().handle(new Status(IStatus.ERROR, WaveformProjectPlugin.PLUGIN_ID, "Failed to drag on to waveform viewer.", e));
			}
		}
		this.dragSource = null;
	}

	public void dragSetData(final DragSourceEvent event) {
		event.data = this.dragSource.identifier();
	}

	public void dragStart(final DragSourceEvent event) {
		final Object dragObject = this.treeViewer.getCell(new Point(event.x, event.y)).getElement();

		this.dragSource = null;

		if (dragObject instanceof ScaWaveform) {
			this.dragSource = (ScaWaveform) dragObject;
			event.data = this.dragSource.identifier();
		}
		event.doit = this.dragSource != null;
	}

	/**
	 * This performs an action, currently INSTALL or UNINSTALL on a list of
	 * applications.
	 * 
	 * @param action the action to perform, ApplicationActionListener.INSTALL or
	 *            ApplicationActionListener.UNINSTALL
	 * @param app the list of applications to perform the action on
	 */
	public void actionPerformed(final int action, final Object app) {
		if ((app != null) && !this.ignoreCallback) {
			if (app instanceof List< ? >) {
				final Object[] expanded = this.treeViewer.getExpandedElements();
				final List< ? > list = (List< ? >) app;

				for (int i = 0; i < list.size(); ++i) {
					if (action == ApplicationActionListener.INSTALL) {
						this.apps.add((ScaWaveform) list.get(i));
					} else if (action == ApplicationActionListener.UNINSTALL) {
						this.apps.remove(list.get(i));
					}
				}
				this.treeViewer.setInput(this.apps.toArray());
				this.treeViewer.refresh();
				this.treeViewer.setExpandedElements(expanded);
			}
		}
	}
}

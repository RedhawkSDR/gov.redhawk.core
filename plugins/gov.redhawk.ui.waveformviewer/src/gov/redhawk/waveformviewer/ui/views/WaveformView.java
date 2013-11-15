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
package gov.redhawk.waveformviewer.ui.views;

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaWaveformFactory;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.util.ScaAdapterFactory;
import gov.redhawk.sca.ScaPlugin;

import java.util.Vector;

import mil.jpeojtrs.sca.sad.SoftwareAssembly;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.transaction.RunnableWithResult;
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
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.progress.WorkbenchJob;

public class WaveformView extends ViewPart implements DragSourceListener, DropTargetListener {
	public static final String ID = "gov.redhawk.ui.waveform.view.available";

	/** Handle to the object being dragged, shortcut to the selected tree item */
	private SoftwareAssembly dragSource = null;
	/** The main object of the view, the tree of applications */
	private TreeViewer treeViewer;
	/** The list of available waveforms */
	private final Vector<ScaWaveformFactory> availableWaves = new Vector<ScaWaveformFactory>();

	private final Job refreshTreeDataJob = new Job("Refreshing Waveform View data") {

		@Override
		protected IStatus run(final IProgressMonitor monitor) {
			// Get all domain managers
			final ScaDomainManagerRegistry registry = ScaPlugin.getDefault().getDomainManagerRegistry(getSite().getShell().getDisplay());
			final EList<ScaDomainManager> doms = registry.getDomains();

			WaveformView.this.availableWaves.clear();

			// Loop through all domain managers, looking for running
			// applications
			try {
				ScaModelCommand.runExclusive(registry, new RunnableWithResult.Impl<Object>() {

					@Override
					public void run() {
						for (final ScaDomainManager adom : doms) {
							if (adom.isConnected()) {
								final EList<ScaWaveformFactory> factories = adom.getWaveformFactories();
								for (final ScaWaveformFactory factory : factories) {
									WaveformView.this.availableWaves.add(factory);
								}
							}
						}
					}

				});
			} catch (final InterruptedException e) {
				// PASS
			}

			final WorkbenchJob refreshJob = new WorkbenchJob(getSite().getShell().getDisplay(), "Refreshing Waveform view") {

				@Override
				public IStatus runInUIThread(final IProgressMonitor monitor) {
					if (WaveformView.this.treeViewer == null || WaveformView.this.treeViewer.getControl().isDisposed()) {
						return Status.CANCEL_STATUS;
					}
					WaveformView.this.treeViewer.refresh();
					return Status.OK_STATUS;
				}
			};
			refreshJob.schedule();
			return Status.OK_STATUS;
		}

	};

	@Override
	public void createPartControl(final Composite parent) {
		this.treeViewer = new TreeViewer(parent, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		getSite().setSelectionProvider(this.treeViewer);

		// Add a ContextMenu to the tree
		final MenuManager manager = new MenuManager();
		final Menu menu = manager.createContextMenu(this.treeViewer.getTree());
		this.treeViewer.getTree().setMenu(menu);
		this.treeViewer.setContentProvider(new AdapterFactoryContentProvider(new ScaAdapterFactory()));
		this.treeViewer.setLabelProvider(new AdapterFactoryLabelProvider(new ScaAdapterFactory()));

		getSite().registerContextMenu(WaveformView.ID, manager, this.treeViewer);

		final Transfer[] types = new Transfer[] {
			TextTransfer.getInstance()
		};
		this.treeViewer.addDropSupport(DND.DROP_MOVE, types, this);
		this.treeViewer.addDragSupport(DND.DROP_COPY, types, this);

		final ScaDomainManagerRegistry registry = ScaPlugin.getDefault().getDomainManagerRegistry(getSite().getShell().getDisplay());
		registry.eAdapters().add(new AdapterImpl() {
			@Override
			public void notifyChanged(final Notification msg) {
				super.notifyChanged(msg);
				switch (msg.getFeatureID(ScaDomainManagerRegistry.class)) {
				case ScaPackage.SCA_DOMAIN_MANAGER_REGISTRY__DOMAINS:
					WaveformView.this.refreshTreeDataJob.schedule();
					break;
				default:
					break;
				}
			}
		});
	}

	@Override
	public void setFocus() {
		this.treeViewer.getControl().setFocus();
	}

	@Override
	public void dragFinished(final DragSourceEvent event) {
		if ((this.dragSource != null) && (event.detail == DND.DROP_COPY)) {
			this.dragSource = null;
		}
	}

	@Override
	public void dragSetData(final DragSourceEvent event) {
		event.data = this.dragSource.eResource().getURI().toString();
	}

	@Override
	public void dragStart(final DragSourceEvent event) {
		final Object dragObject = this.treeViewer.getCell(new Point(event.x, event.y)).getElement();

		this.dragSource = null;

		if (dragObject instanceof SoftwareAssembly) {
			this.dragSource = (SoftwareAssembly) dragObject;
			event.data = this.dragSource.eResource().getURI().toString();
		}
		event.doit = this.dragSource != null;
	}

	@Override
	public void dragEnter(final DropTargetEvent event) {
	}

	@Override
	public void dragLeave(final DropTargetEvent event) {
	}

	@Override
	public void dragOperationChanged(final DropTargetEvent event) {
	}

	@Override
	public void dragOver(final DropTargetEvent event) {
	}

	@Override
	public void drop(final DropTargetEvent event) {
	}

	@Override
	public void dropAccept(final DropTargetEvent event) {
	}
}

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
package gov.redhawk.ui.port.playaudio.views;

import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;
import gov.redhawk.ui.port.playaudio.controller.AudioController;
import gov.redhawk.ui.port.playaudio.controller.IControllerListener;
import gov.redhawk.ui.port.playaudio.controls.PlaybackInfo;

import java.util.List;
import java.util.Map;

import javax.sound.sampled.AudioFormat;

import mil.jpeojtrs.sca.scd.provider.ScdItemProviderAdapterFactory;
import mil.jpeojtrs.sca.spd.provider.SpdItemProviderAdapterFactory;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

/**
 * @since 1.1
 */
public class View extends ViewPart implements IControllerListener {
	public static final String ID = "gov.redhawk.ui.port.playaudio.view";

	private TreeViewer treeViewer;
	private Button pauseButton;
	private Button disconnectButton;

	private PlaybackInfo infoComp;

	private AudioController controller;

	/** This is set when the view is being disposed */
	private boolean isDisposed = false;

	/**
	 * This callback allows us to create and initialize the viewer.
	 */
	@Override
	public void createPartControl(final Composite parent) {
		this.controller = new AudioController();
		this.controller.addListener(this);

		parent.setLayout(new GridLayout(4, false)); // SUPPRESS CHECKSTYLE MagicNumber

		this.treeViewer = new TreeViewer(parent, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL);
		getSite().setSelectionProvider(this.treeViewer);
		this.treeViewer.setContentProvider(new AdapterFactoryContentProvider(getAdapterFactory()) {
			@Override
			public Object[] getElements(final Object object) {
				if (object instanceof List< ? >) {
					return ((List< ? >) object).toArray();
				} else if (object instanceof Map< ? , ? >) {
					return ((Map< ? , ? >) object).keySet().toArray();
				}
				return super.getElements(object);
			}
			@Override
			public boolean hasChildren(Object object) {
				// We do not want to see the children of the port.  It causes an error when you click on the connection
				// This fixes Issue #171
			    if (object instanceof ScaPort) {
			    	return false;
			    }
			    return super.hasChildren(object);
			}
		});
		this.treeViewer.setLabelProvider(new AdapterFactoryLabelProvider(getAdapterFactory()) {
			@Override
			public String getText(final Object object) {
				String text = super.getText(object);
				if (object instanceof ScaUsesPort) {
					text = View.this.controller.getPortName((ScaUsesPort) object);
				}
				return text;
			}
		});
		this.treeViewer.getTree().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 3)); // SUPPRESS CHECKSTYLE MagicNumber
		this.treeViewer.setInput(this.controller.getConnectedPorts());

		this.infoComp = new PlaybackInfo(parent, SWT.NONE);
		this.infoComp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1)); // SUPPRESS CHECKSTYLE MagicNumber

		this.treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@SuppressWarnings("unchecked")
			public void selectionChanged(final SelectionChangedEvent event) {
				final boolean empty = event.getSelection().isEmpty();
				View.this.pauseButton.setEnabled(!empty);
				View.this.disconnectButton.setEnabled(!empty);

				if (!empty && (event.getSelection() instanceof IStructuredSelection)) {
					View.this.controller.portsSelected(((IStructuredSelection) event.getSelection()).toList());
				}

				View.this.infoComp.setInput(View.this.controller.getFormat());
			}
		});

		this.pauseButton = this.infoComp.getPauseButton();
		this.pauseButton.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(final SelectionEvent e) {
				View.this.controller.pause(View.this.pauseButton.getSelection());
			}

			public void widgetSelected(final SelectionEvent e) {
				this.widgetDefaultSelected(e);
			}
		});

		this.disconnectButton = this.infoComp.getDisconnectButton();
		this.disconnectButton.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(final SelectionEvent e) {
				View.this.disconnectPort(null);
			}

			public void widgetSelected(final SelectionEvent e) {
				View.this.disconnectPort(null);
			}
		});
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus() {
	}

	/**
	 * This will connect to the given ports to start audio playback. This will
	 * first disconnect from any connected ports then, connect to the desired
	 * port to start playback.
	 * 
	 * @param portMap map of ports to strings to play
	 */
	public void playNewPort(final Map<ScaUsesPort, String> portMap) {
		this.controller.playPort(portMap);
		this.infoComp.setInput(null);
		this.treeViewer.refresh();
		this.treeViewer.setSelection(new StructuredSelection(portMap.keySet()), true);
	}

	@Override
	public void dispose() {
		this.isDisposed = true;
		this.controller.dispose();
		super.dispose();
	}

	/**
	 * This adds the AdapterFactories to display the ScaUsesPort object.
	 * 
	 * @return the appropriate adapter factory to handle ScaUsesPort's
	 */
	private AdapterFactory getAdapterFactory() {
		final ComposedAdapterFactory af = new ComposedAdapterFactory();
		af.addAdapterFactory(new SpdItemProviderAdapterFactory());
		af.addAdapterFactory(new ScdItemProviderAdapterFactory());
		af.addAdapterFactory(new ScaItemProviderAdapterFactory());
		return af;
	}

	/**
	 * This method will disconnect the selected port.
	 */
	public void disconnectPort(String portName) {
		if (!this.isDisposed) {
			this.controller.disconnectPorts(portName);
			this.refresh();
		}
	}

	public void setFormat(final AudioFormat format) {
		if (this.infoComp != null && !this.isDisposed) {
			getViewSite().getShell().getDisplay().asyncExec(new Runnable() {
				public void run() {
					View.this.infoComp.setInput(format);
				}
			});
		}
	}

	public void refresh() {
		if (!this.isDisposed) {
			getViewSite().getShell().getDisplay().asyncExec(new Runnable() {
				public void run() {
					View.this.treeViewer.refresh();
				}
			});
		}
	}
}

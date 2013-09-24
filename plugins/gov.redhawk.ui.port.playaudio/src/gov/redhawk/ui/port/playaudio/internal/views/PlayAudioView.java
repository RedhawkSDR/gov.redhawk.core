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
package gov.redhawk.ui.port.playaudio.internal.views;

import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;
import gov.redhawk.ui.port.playaudio.controller.AudioReceiver;
import gov.redhawk.ui.port.playaudio.controls.PlaybackInfo;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mil.jpeojtrs.sca.scd.provider.ScdItemProviderAdapterFactory;
import mil.jpeojtrs.sca.spd.provider.SpdItemProviderAdapterFactory;

import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class PlayAudioView extends ViewPart {
	public static final String ID = "gov.redhawk.ui.port.playaudio.view";

	private ListViewer treeViewer;

	private PlaybackInfo infoComp;

	/** This is set when the view is being disposed */
	private boolean isDisposed = false;
	private ComposedAdapterFactory adapterFactory;
	private WritableList connections = new WritableList();

	private PropertyChangeListener listener = new PropertyChangeListener() {

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if (isDisposed) {
				return;
			}
			if (evt.getPropertyName().equals("disposed")) {
				AudioReceiver receiver = (AudioReceiver) evt.getSource();
				disconnect(receiver);
			}

		}
	};

	/**
	 * This callback allows us to create and initialize the viewer.
	 */
	@Override
	public void createPartControl(final Composite parent) {
		parent.setLayout(new GridLayout(4, false)); // SUPPRESS CHECKSTYLE MagicNumber

		this.treeViewer = new ListViewer(parent, SWT.BORDER | SWT.SINGLE | SWT.V_SCROLL);
		getSite().setSelectionProvider(this.treeViewer);
		adapterFactory = createAdapterFactory();
		this.treeViewer.setContentProvider(new ObservableListContentProvider());
		this.treeViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory) {

			@Override
			public String getText(final Object object) {
				if (object instanceof AudioReceiver) {
					AudioReceiver receiver = (AudioReceiver) object;
					ScaUsesPort port = receiver.getPort();
					return super.getText(port);
				}
				return super.getText(object);
			}

			@Override
			public Image getImage(Object element) {
				if (element instanceof AudioReceiver) {
					AudioReceiver receiver = (AudioReceiver) element;
					ScaUsesPort port = receiver.getPort();
					return super.getImage(port);
				}
				return super.getImage(element);
			}
		});
		this.treeViewer.getControl().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 3)); // SUPPRESS CHECKSTYLE MagicNumber
		this.treeViewer.setInput(this.connections);

		this.infoComp = new PlaybackInfo(parent, SWT.NONE);
		this.infoComp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1)); // SUPPRESS CHECKSTYLE MagicNumber

		this.treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(final SelectionChangedEvent event) {
				AudioReceiver input = (AudioReceiver) ((IStructuredSelection) event.getSelection()).getFirstElement();
				infoComp.setInput(input);
			}
		});
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus() {
		if (isDisposed) {
			return;
		}
		if (this.treeViewer != null) {
			this.treeViewer.getControl().setFocus();
		}
	}

	/**
	 * @since 1.2
	 */
	public void playPort(final ScaUsesPort port) {
		Job job = new Job("Connection to port") {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				try {
					final AudioReceiver receiver = new AudioReceiver(port);
					receiver.addPropertyChangeListener(listener);
					connections.getRealm().exec(new Runnable() {

						@Override
						public void run() {
							connections.add(receiver);
							treeViewer.setSelection(new StructuredSelection(receiver), true);
						}

					});
				} catch (CoreException e) {
					return e.getStatus();
				}
				return Status.OK_STATUS;
			}

		};
		job.schedule();

	}

	@Override
	public void dispose() {
		if (isDisposed) {
			return;
		}
		this.isDisposed = true;
		adapterFactory.dispose();
		final Object[] oldConnections = connections.toArray();
		connections.clear();
		if (oldConnections.length > 0) {
			Job job = new Job("Disposing Controller") {

				@Override
				protected IStatus run(IProgressMonitor monitor) {
					for (Object receiver : oldConnections) {
						((AudioReceiver) receiver).dispose();
					}
					return Status.OK_STATUS;
				}

			};
			job.schedule();
		}
		super.dispose();
	}

	/**
	 * This adds the AdapterFactories to display the ScaUsesPort object.
	 * 
	 * @return the appropriate adapter factory to handle ScaUsesPort's
	 */
	private ComposedAdapterFactory createAdapterFactory() {
		final ComposedAdapterFactory af = new ComposedAdapterFactory();
		af.addAdapterFactory(new SpdItemProviderAdapterFactory());
		af.addAdapterFactory(new ScdItemProviderAdapterFactory());
		af.addAdapterFactory(new ScaItemProviderAdapterFactory());
		return af;
	}

	private void disconnect(final AudioReceiver receiver) {
		if (!this.isDisposed) {
			receiver.removePropertyChangeListener(listener);
			connections.getRealm().exec(new Runnable() {

				@Override
				public void run() {
					connections.remove(receiver);
				}

			});

			if (!receiver.isDiposed()) {
				ScaUsesPort port = receiver.getPort();
				Job job = new Job("Disconnecting audio port " + port.getName()) {

					@Override
					protected IStatus run(IProgressMonitor monitor) {
						receiver.dispose();
						return Status.OK_STATUS;
					}

				};
				job.setUser(false);
				job.schedule();
			}
		}

	}

	/**
	 * This method will disconnect the selected port.
	 * @since 1.2
	 */
	public void disconnectPort(final ScaUsesPort port) {
		if (!this.isDisposed) {
			for (Object obj : this.connections) {
				final AudioReceiver receiver = (AudioReceiver) obj;
				if (receiver.getPort() == port) {
					disconnect(receiver);
					return;
				}
			}
		}
	}
}

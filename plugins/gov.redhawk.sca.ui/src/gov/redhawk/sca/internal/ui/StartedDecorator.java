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
package gov.redhawk.sca.internal.ui;

import gov.redhawk.model.sca.ScaAbstractComponent;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.sca.util.SilentJob;

import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IDelayedLabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;
import org.eclipse.swt.graphics.Image;

/**
 * 
 */
public class StartedDecorator extends LabelProvider implements IDelayedLabelDecorator {
	private final Adapter elementListener = new AdapterImpl() {
		@Override
		public void notifyChanged(final org.eclipse.emf.common.notify.Notification msg) {
			if (msg.getNotifier() instanceof ScaAbstractComponent< ? >) {
				switch (msg.getFeatureID(ScaAbstractComponent.class)) {
				case ScaPackage.SCA_ABSTRACT_COMPONENT__STARTED:
					if (StartedDecorator.this.refreshJob != null) {
						StartedDecorator.this.refreshJob.addElement(msg.getNotifier());
					} else {
						stopListening(msg);
					}
					break;
				default:
					break;
				}
			} else if (msg.getNotifier() instanceof ScaWaveform) {
				switch (msg.getFeatureID(ScaWaveform.class)) {
				case ScaPackage.SCA_WAVEFORM__STARTED:
					if (StartedDecorator.this.refreshJob != null) {
						StartedDecorator.this.refreshJob.addElement(msg.getNotifier());
					} else {
						stopListening(msg);
					}
					break;
				default:
					break;
				}
			}
		}

		private void stopListening(final Notification msg) {
			if (msg.getNotifier() instanceof Notifier) {
				final Notifier notifier = (Notifier) msg.getNotifier();
				notifier.eAdapters().remove(this);
			}
		}
	};

	private class LabelRefreshJob extends Job {
		public LabelRefreshJob() {
			super("Label update");
			setSystem(true);
			setPriority(Job.DECORATE);
		}

		private final BlockingQueue<Object> elementQueue = new LinkedBlockingQueue<Object>();

		public void addElement(final Object element) {
			if (!this.elementQueue.contains(element)) {
				this.elementQueue.offer(element);
				schedule();
			}
		}

		@Override
		public IStatus run(final IProgressMonitor monitor) {
			final ArrayList<Object> elements = new ArrayList<Object>();
			this.elementQueue.drainTo(elements);
			fireLabelProviderChanged(new LabelProviderChangedEvent(StartedDecorator.this, elements.toArray()));
			return Status.OK_STATUS;
		}

	}

	private LabelRefreshJob refreshJob = new LabelRefreshJob();

	@Override
	public void dispose() {
		if (this.refreshJob != null) {
			this.refreshJob.cancel();
			this.refreshJob = null;
		}
		super.dispose();
	}

	/**
	 * {@inheritDoc}
	 */
	public Image decorateImage(final Image image, final Object element) {
		return null;
	}

	private void addListener(final EObject eObject) {
		ScaModelCommand.execute(eObject, new ScaModelCommand() {

			public void execute() {
				if (!eObject.eAdapters().contains(StartedDecorator.this.elementListener)) {
					eObject.eAdapters().add(StartedDecorator.this.elementListener);
				}
			}
		});
	}

	/**
	 * {@inheritDoc}
	 */
	public String decorateText(final String text, final Object element) {
		if (!prepareDecoration(element, null)) {
			return null;
		}
		if (element instanceof ScaAbstractComponent< ? >) {
			final ScaAbstractComponent< ? > component = (ScaAbstractComponent< ? >) element;
			addListener(component);
			final Boolean started = component.getStarted();
			if (started != null && started) {
				return text + " STARTED";
			}
		} else if (element instanceof ScaWaveform) {
			final ScaWaveform waveform = (ScaWaveform) element;
			addListener(waveform);
			final Boolean started = waveform.getStarted();
			if (started != null && started) {
				return text + " STARTED";
			}
		}
		return null;
	}

	public boolean prepareDecoration(final Object element, final String originalText) {
		boolean retVal = false;
		if (element instanceof ScaAbstractComponent< ? >) {
			final ScaAbstractComponent< ? > component = (ScaAbstractComponent< ? >) element;
			retVal = component.isSetStarted();
			if (!retVal) {
				final SilentJob job = new SilentJob("Fetch started") {
					{
						setSystem(true);
						setPriority(Job.DECORATE);
					}

					@Override
					protected IStatus runSilent(final IProgressMonitor monitor) {
						if (component.isDisposed()) {
							return Status.CANCEL_STATUS;
						}
						component.fetchStarted(monitor);
						return Status.OK_STATUS;
					}
				};
				job.schedule();
			}
		} else if (element instanceof ScaWaveform) {
			final ScaWaveform waveform = (ScaWaveform) element;
			retVal = waveform.isSetStarted();
			if (!retVal) {
				final SilentJob job = new SilentJob("Fetch started") {
					{
						setSystem(true);
						setPriority(Job.DECORATE);
					}

					@Override
					protected IStatus runSilent(final IProgressMonitor monitor) {
						waveform.fetchStarted(monitor);
						return Status.OK_STATUS;
					}
				};
				job.schedule();
			}
		}
		return retVal;
	}

}

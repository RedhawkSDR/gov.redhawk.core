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
package gov.redhawk.sca.ui;

import gov.redhawk.model.sca.ScaDocumentRoot;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;
import gov.redhawk.model.sca.provider.TransientItemProvider;
import gov.redhawk.sca.internal.ui.DeferredAdapterSwitch;
import gov.redhawk.sca.internal.ui.DeferredAdapterSwitch.IDeferredAdapter;
import gov.redhawk.sca.util.SilentJob;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;
import org.eclipse.ui.progress.UIJob;

/**
 * The Class ScaContentProvider.
 * @since 8.0
 */
public class ScaContentProvider extends ScaModelAdapterFactoryContentProvider implements ICommonContentProvider {

	public ScaContentProvider() {
		super(ScaContentProvider.createAdapterFactory());
	}

	public ScaContentProvider(final AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * Creates the adapter factory.
	 * 
	 * @return the adapter factory
	 */
	protected static AdapterFactory createAdapterFactory() {
		// Create an adapter factory that yields item providers.
		//
		final ComposedAdapterFactory adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		adapterFactory.addAdapterFactory(new ScaItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

		return adapterFactory;
	}

	@Override
	public Object getParent(final Object object) {
		if (object instanceof ScaDomainManagerRegistry) {
			return null;
		}
		final Object retVal = super.getParent(object);
		if (retVal instanceof TransientItemProvider) {
			return retVal;
		} else if (!(retVal instanceof EObject) || (retVal instanceof ScaDocumentRoot)) {
			return null;
		}
		return retVal;
	}

	private abstract static class FetchJob extends SilentJob {

		private final Object element;
		private final Viewer viewer;

		public FetchJob(final Object element, final Viewer viewer) {
			super("Loading...");
			this.element = element;
			setPriority(Job.LONG);
			this.viewer = viewer;
		}

		protected abstract IStatus doFetch(IProgressMonitor monitor);

		@Override
		protected final IStatus runSilent(final IProgressMonitor monitor) {
			try {
				return doFetch(monitor);
			} finally {
				if (this.viewer != null && this.viewer.getControl() != null && !this.viewer.getControl().isDisposed()) {
					final Display display = this.viewer.getControl().getDisplay();
					final UIJob refreshJob = new UIJob(display, "Refresh") {

						@Override
						public IStatus runInUIThread(final IProgressMonitor monitor) {
							if (FetchJob.this.viewer.getControl().isDisposed()) {
								return Status.CANCEL_STATUS;
							}
							if (FetchJob.this.viewer instanceof StructuredViewer) {
								((StructuredViewer) FetchJob.this.viewer).refresh(FetchJob.this.element);
							} else {
								FetchJob.this.viewer.refresh();
							}
							return Status.OK_STATUS;
						}
					};
					refreshJob.setSystem(true);
					refreshJob.schedule();
				}
			}
		}
	}

	private final Set<Integer> fetched = new HashSet<Integer>();

	@Override
	public Object[] getChildren(final Object object) {
		final IDeferredAdapter adapter = DeferredAdapterSwitch.doSwitch(object);
		if (adapter != null && !adapter.isSet()) {
			final int systemHash = System.identityHashCode(object);
			if (!this.fetched.contains(systemHash)) {
				this.fetched.add(System.identityHashCode(object));
				final FetchJob job = new FetchJob(object, this.viewer) {

					@Override
					protected IStatus doFetch(final IProgressMonitor monitor) {
						adapter.fetchDeferredChildren(monitor);
						return Status.OK_STATUS;
					}

				};
				job.schedule();

				return new Object[] { job };
			}
		}
		return super.getChildren(object);
	}

	@Override
	public boolean hasChildren(final Object object) {
		final IDeferredAdapter adapter = DeferredAdapterSwitch.doSwitch(object);
		if (adapter != null) {
			if (adapter.isContainer()) {
				if (!adapter.isSet()) {
					return true;
				}
			}
		}
		return super.hasChildren(object);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		((ComposedAdapterFactory) this.adapterFactory).dispose();
		this.adapterFactory = null;
		this.fetched.clear();
		super.dispose();
	}

	@Override
	public void init(final ICommonContentExtensionSite config) {
		// Nothing to do
	}

	@Override
	public void restoreState(final IMemento memento) {
		// Nothing to do
	}

	@Override
	public void saveState(final IMemento memento) {
		// Nothing to do
	}

}

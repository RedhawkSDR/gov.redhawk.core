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

import java.util.concurrent.TimeUnit;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;
import org.eclipse.ui.progress.UIJob;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import gov.redhawk.model.sca.ScaDocumentRoot;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;
import gov.redhawk.model.sca.provider.TransientItemProvider;
import gov.redhawk.sca.internal.ui.DeferredAdapterSwitch;
import gov.redhawk.sca.internal.ui.DeferredAdapterSwitch.IDeferredAdapter;
import gov.redhawk.sca.util.SilentJob;

/**
 * Content provider for the SCA model. Used primarily by the REDHAWK Explorer common navigator, and a few other places.
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

	private static class FetchJob extends SilentJob {

		private final IDeferredAdapter adapter;

		public FetchJob(IDeferredAdapter adapter) {
			super("Loading...");
			this.adapter = adapter;
			setPriority(Job.LONG);
		}

		@Override
		protected final IStatus runSilent(final IProgressMonitor monitor) {
			adapter.fetchDeferredChildren(monitor);
			return Status.OK_STATUS;
		}
	}

	private static class RefreshJob extends UIJob {

		private final Object element;
		private final Viewer viewer;

		public RefreshJob(Object element, Viewer viewer) {
			super(viewer.getControl().getDisplay(), "Refresh element...");
			this.element = element;
			this.viewer = viewer;
			setSystem(true);
		}

		@Override
		public IStatus runInUIThread(IProgressMonitor monitor) {
			if (this.viewer.getControl() == null || this.viewer.getControl().isDisposed()) {
				return Status.CANCEL_STATUS;
			}

			if (this.viewer instanceof StructuredViewer) {
				((StructuredViewer) this.viewer).refresh(this.element);
			} else {
				this.viewer.refresh();
			}
			return Status.OK_STATUS;
		}
	}

	private Cache<Object, Job> fetchJobs = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.MINUTES).build();

	@Override
	public Object[] getChildren(final Object object) {
		// See if this object isn't set
		final IDeferredAdapter adapter = DeferredAdapterSwitch.doSwitch(object);
		if (adapter != null && !adapter.isSet()) {
			// See if we've scheduled a fetch
			Job fetchJob = this.fetchJobs.getIfPresent(object);

			// If not fetched, schedule fetch (and then refresh) jobs
			if (fetchJob == null) {
				fetchJob = new FetchJob(adapter);
				this.fetchJobs.put(object, fetchJob);
				if (this.viewer != null && this.viewer.getControl() != null && !this.viewer.getControl().isDisposed()) {
					final Job refreshJob = new RefreshJob(object, viewer);
					fetchJob.addJobChangeListener(new JobChangeAdapter() {
						@Override
						public void done(IJobChangeEvent event) {
							refreshJob.schedule();
						}
					});
				}
				fetchJob.schedule();
			}

			// If the fetch job hasn't completed, return it as the only child
			if (fetchJob.getResult() == null) {
				return new Object[] { fetchJob };
			}
		}

		return super.getChildren(object);
	}

	@Override
	public boolean hasChildren(final Object object) {
		final IDeferredAdapter adapter = DeferredAdapterSwitch.doSwitch(object);
		if (adapter != null && adapter.isContainer() && !adapter.isSet()) {
			return true;
		}
		return super.hasChildren(object);
	}

	@Override
	public void dispose() {
		((ComposedAdapterFactory) this.adapterFactory).dispose();
		this.fetchJobs.invalidateAll();
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

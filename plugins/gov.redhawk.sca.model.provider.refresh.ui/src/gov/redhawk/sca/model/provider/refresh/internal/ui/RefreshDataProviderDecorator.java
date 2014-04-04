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
package gov.redhawk.sca.model.provider.refresh.internal.ui;

import gov.redhawk.model.sca.DataProviderObject;
import gov.redhawk.model.sca.commands.ScaModelCommandWithResult;
import gov.redhawk.model.sca.services.IScaDataProvider;
import gov.redhawk.sca.model.provider.refresh.internal.RefreshTasker;
import gov.redhawk.sca.model.provider.refresh.ui.RefreshProviderUIActivator;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.LabelProviderChangedEvent;

/**
 * 
 */
public class RefreshDataProviderDecorator extends LabelProvider implements ILightweightLabelDecorator {

	private class PropertyListener implements PropertyChangeListener {
		private final EObject element;

		public PropertyListener(final EObject element) {
			this.element = element;
		}

		@Override
		public void propertyChange(final PropertyChangeEvent evt) {
			if (RefreshDataProviderDecorator.this.disposed) {
				if (evt.getSource() instanceof RefreshTasker) {
					final RefreshTasker job = (RefreshTasker) evt.getSource();
					job.removePropertyChangeListener(this);
					return;
				}
			}
			if (RefreshTasker.PROP_ACTIVE.equals(evt.getPropertyName())) {
				fireStatusChanged(element);
			}
		}
	};

	private boolean disposed;

	private void fireStatusChanged(final Object object) {
		final LabelProviderChangedEvent event = new LabelProviderChangedEvent(this, object);
		fireLabelProviderChanged(event);
	}

	@Override
	public void dispose() {
		this.disposed = true;
		super.dispose();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void decorate(final Object element, final IDecoration decoration) {
		if (element instanceof DataProviderObject) {
			final DataProviderObject dataProvider = (DataProviderObject) element;
			final RefreshTasker task = ScaModelCommandWithResult.execute(dataProvider, new ScaModelCommandWithResult<RefreshTasker>() {

				@Override
				public void execute() {
					for (final IScaDataProvider provider : dataProvider.getDataProviders()) {
						if (provider instanceof RefreshTasker) {
							final RefreshTasker job = (RefreshTasker) provider;
							setResult(job);
							return;
						}
					}
				}
			});
			if (task != null) {
				if (!task.isActive()) {
					decoration.addOverlay(RefreshProviderUIActivator.getDefault().getImageDescriptor("icons/stock-media-pause.png"), IDecoration.TOP_RIGHT);
				}
				// Don't add a second listener if we have already added one
				for (PropertyChangeListener l : task.getPropertyChangeListeners()) {
					if (l instanceof PropertyListener && ((PropertyListener) l).element == dataProvider) {
						return;
					}
				}
				task.addPropertyChangeListener(new PropertyListener(dataProvider));
			}
		}
	}

}

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
package gov.redhawk.model.sca.services;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

/**
 * @since 14.0
 * 
 */
public abstract class AbstractDataProviderService implements IScaDataProviderService {

	private final List<IScaDataProvider> providers = Collections.synchronizedList(new ArrayList<IScaDataProvider>());
	private boolean enabled;
	private final PropertyChangeListener listener = new PropertyChangeListener() {

		@Override
		public void propertyChange(final PropertyChangeEvent evt) {
			if (IScaDataProvider.DISPOSED_PROPERTY.equals(evt.getPropertyName())) {
				providers.remove(evt.getSource());
			}
		}
	};

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final IScaDataProvider hookDataProvider(EObject object) {
		final IScaDataProvider provider = createDataProvider(object);
		if (provider != null) {
			this.providers.add(provider);
			provider.addPropertyChangeListener(listener);
			provider.setEnabled(this.enabled);
		}
		return provider;
	}

	protected abstract IScaDataProvider createDataProvider(EObject object);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setEnabled(boolean enabled) {
		if (this.enabled == enabled) {
			return;
		}
		this.enabled = enabled;
		synchronized (providers) {
			IScaDataProvider[] providerArray = providers.toArray(new IScaDataProvider[providers.size()]);
			for (IScaDataProvider provider : providerArray) {
				provider.setEnabled(enabled);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		setEnabled(false);
		synchronized (providers) {
			IScaDataProvider[] providerArray = providers.toArray(new IScaDataProvider[providers.size()]);
			for (IScaDataProvider provider : providerArray) {
				provider.removePropertyChangeListener(listener);
				provider.dispose();
			}
			this.providers.clear();
		}
	}

}

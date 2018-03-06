/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.prf.ui.provider;

import mil.jpeojtrs.sca.prf.provider.AbstractPropertyItemProvider;
import mil.jpeojtrs.sca.prf.provider.PrfItemProviderAdapterFactory;

import java.util.Arrays;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.jface.util.IPropertyChangeListener;

import gov.redhawk.prf.internal.ui.preferences.PrfEditorPreferenceConstants;
import gov.redhawk.prf.ui.PrfUiPlugin;

/**
 * @since 1.2
 */
public class PropertiesEditorPrfItemProviderAdapterFactory extends PrfItemProviderAdapterFactory {

	private IPropertyChangeListener preferenceListener;

	public PropertiesEditorPrfItemProviderAdapterFactory() {
		preferenceListener = event -> {
			if (PrfEditorPreferenceConstants.ID_SCOPING.equals(event.getProperty())) {
				boolean newValue = (Boolean) event.getNewValue();
				for (AbstractPropertyItemProvider itemProvider : Arrays.asList(simpleItemProvider, simpleSequenceItemProvider, structItemProvider,
					structSequenceItemProvider)) {
					if (itemProvider != null) {
						itemProvider.setIdScoping(newValue);
					}
				}
			}
		};
		PrfUiPlugin.getDefault().getPreferenceStore().addPropertyChangeListener(preferenceListener);
	}

	@Override
	public Adapter createPropertiesAdapter() {
		if (this.propertiesItemProvider == null) {
			this.propertiesItemProvider = new PropertiesEditorPropertiesItemProvider(this);
		}
		return this.propertiesItemProvider;
	}

	@Override
	public Adapter createSimpleAdapter() {
		if (simpleItemProvider == null) {
			simpleItemProvider = new PropertiesEditorSimpleItemProvider(this);
			applyScopingPreference(simpleItemProvider);
		}
		return simpleItemProvider;
	}

	@Override
	public Adapter createSimpleSequenceAdapter() {
		if (simpleSequenceItemProvider == null) {
			simpleSequenceItemProvider = new PropertiesEditorSimpleSequenceItemProvider(this);
			applyScopingPreference(simpleSequenceItemProvider);
		}
		return simpleSequenceItemProvider;
	}

	@Override
	public Adapter createStructAdapter() {
		if (structItemProvider == null) {
			structItemProvider = new PropertiesEditorStructItemProvider(this);
			applyScopingPreference(structItemProvider);
		}
		return structItemProvider;
	}

	@Override
	public Adapter createStructSequenceAdapter() {
		if (structSequenceItemProvider == null) {
			structSequenceItemProvider = new PropertiesEditorStructSequenceItemProvider(this);
			applyScopingPreference(structSequenceItemProvider);
		}
		return structSequenceItemProvider;
	}

	private void applyScopingPreference(AbstractPropertyItemProvider itemProvider) {
		boolean isScoped = PrfUiPlugin.getDefault().getPreferenceStore().getBoolean(PrfEditorPreferenceConstants.ID_SCOPING);
		itemProvider.setIdScoping(isScoped);
	}

	@Override
	public Adapter createSimpleRefAdapter() {
		if (this.simpleRefItemProvider == null) {
			this.simpleRefItemProvider = new PropertiesEditorSimpleRefItemProvider(this);
		}
		return this.simpleRefItemProvider;
	}

	@Override
	public Adapter createSimpleSequenceRefAdapter() {
		if (this.simpleSequenceRefItemProvider == null) {
			this.simpleSequenceRefItemProvider = new PropertiesEditorSimpleSequenceRefItemProvider(this);
		}
		return this.simpleSequenceRefItemProvider;
	}

	@Override
	public Adapter createStructValueAdapter() {
		if (this.structValueItemProvider == null) {
			this.structValueItemProvider = new PropertiesEditorStructValueItemProvider(this);
		}
		return this.structValueItemProvider;
	}

	@Override
	public void dispose() {
		super.dispose();
		PrfUiPlugin.getDefault().getPreferenceStore().removePropertyChangeListener(preferenceListener);
	}
}

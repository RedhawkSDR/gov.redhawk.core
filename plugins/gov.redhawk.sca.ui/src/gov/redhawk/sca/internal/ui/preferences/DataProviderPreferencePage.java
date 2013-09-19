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
package gov.redhawk.sca.internal.ui.preferences;

import gov.redhawk.model.sca.IScaDataProviderServiceDescriptor;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.sca.ScaPlugin;
import gov.redhawk.sca.model.preferences.ScaModelPreferenceContants;
import gov.redhawk.sca.ui.ScaUiPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.rap.ui.internal.preferences.SessionScope;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

/**
 * 
 */
public class DataProviderPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

	private static class Descriptor {
		private final IScaDataProviderServiceDescriptor desc;
		private boolean enabled;

		public Descriptor(final IScaDataProviderServiceDescriptor desc) {
			this.desc = desc;
		}

		@Override
		public String toString() {
			return this.desc.getName();
		}
	}

	private final List<Descriptor> dataProviders = new ArrayList<DataProviderPreferencePage.Descriptor>();

	private CheckboxTableViewer viewer;

	/**
	 * 
	 */
	public DataProviderPreferencePage() {
		//setPreferenceStore(new ScopedPreferenceStore(InstanceScope.INSTANCE, ScaModelPlugin.getDefault().getBundle().getSymbolicName()));
		final String str = getPreferenceStore().getString(ScaModelPreferenceContants.DISABLED_DATA_PROVIDERS);
		final List<String> disabledProviders = Arrays.asList(str.split(","));
		for (final IScaDataProviderServiceDescriptor desc : ScaModelPlugin.getDataProviderRegistry().getDataProvidersDescriptors()) {
			final Descriptor d = new Descriptor(desc);
			d.enabled = !disabledProviders.contains(d.desc.getId());
			this.dataProviders.add(d);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Control createContents(final Composite parent) {
		this.viewer = CheckboxTableViewer.newCheckList(parent, SWT.BORDER);
		this.viewer.setContentProvider(new ArrayContentProvider());
		this.viewer.setLabelProvider(new LabelProvider());
		this.viewer.setCheckStateProvider(new ICheckStateProvider() {

			@Override
			public boolean isGrayed(final Object element) {
				return false;
			}

			@Override
			public boolean isChecked(final Object element) {
				return ((Descriptor) element).enabled;
			}
		});
		this.viewer.addCheckStateListener(new ICheckStateListener() {

			@Override
			public void checkStateChanged(final CheckStateChangedEvent event) {
				((Descriptor) event.getElement()).enabled = event.getChecked();
			}
		});
		this.viewer.setInput(this.dataProviders);
		return this.viewer.getControl();
	}

	@Override
	public boolean performOk() {
		final StringBuilder builder = new StringBuilder();
		for (final Descriptor desc : this.dataProviders) {
			if (!desc.enabled) {
				builder.append(desc.desc.getId());
				builder.append(",");
			}
		}
		String value = "";
		if (builder.length() > 0) {
			value = builder.substring(0, builder.length() - 1);
		}
		getPreferenceStore().setValue(ScaModelPreferenceContants.DISABLED_DATA_PROVIDERS, value);
		return super.performOk();
	}

	@Override
	public void init(final IWorkbench workbench) {
		setPreferenceStore(ScaUiPlugin.getDefault().getScaPreferenceStore());
	}

	@Override
	public IPreferenceStore getPreferenceStore() {
		return ScaUiPlugin.getDefault().getScaPreferenceStore();
	}
}

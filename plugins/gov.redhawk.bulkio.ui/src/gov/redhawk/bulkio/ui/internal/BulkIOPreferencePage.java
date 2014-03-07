/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.bulkio.ui.internal;

import gov.redhawk.bulkio.util.BulkIOUtilActivator;
import gov.redhawk.bulkio.util.IPortFactory;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

/**
 * 
 */
public class BulkIOPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	private IWorkbench workbench;

	/**
	 * 
	 */
	public BulkIOPreferencePage() {
		super(FieldEditorPreferencePage.GRID);
	}

	@Override
	protected void createFieldEditors() {
		Map<String, IPortFactory> factories = BulkIOUtilActivator.getDefault().getPortFactories();
		String[][] values = new String[factories.size()][];
		Iterator<Entry<String, IPortFactory>> iterator = factories.entrySet().iterator();
		for (int i = 0; i < factories.size(); i++) {
			Entry<String, IPortFactory> entry = iterator.next();
			values[i] = new String[] { entry.getKey(), entry.getKey() };
		}
		addField(new ComboFieldEditor(BulkIOUtilActivator.BULKIO_ORB_TYPE, "Port Factory:", values, getFieldEditorParent()));
	}

	@Override
	public void init(IWorkbench workbench) {
		this.workbench = workbench;
		setPreferenceStore(new ScopedPreferenceStore(InstanceScope.INSTANCE, BulkIOUtilActivator.PLUGIN_ID));
	}

}

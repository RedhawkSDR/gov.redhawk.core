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
package gov.redhawk.core.graphiti.ui.internal.preferences;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import gov.redhawk.core.graphiti.ui.GraphitiUIPlugin;
import gov.redhawk.core.graphiti.ui.preferences.DiagramPreferenceConstants;

public class DiagramPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	public DiagramPreferencePage() {
		super("Graphiti Diagram", FieldEditorPreferencePage.GRID);
	}

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(new ScopedPreferenceStore(InstanceScope.INSTANCE, GraphitiUIPlugin.PLUGIN_ID));
		setDescription("Settings for Graphiti Diagrams.");
	}

	@Override
	protected void createFieldEditors() {
		final Composite parent = getFieldEditorParent();
		addField(new BooleanFieldEditor(DiagramPreferenceConstants.HIDE_DETAILS, "Hide Shape Details", parent));
		// TODO:IDE-1147
		// addField(new BooleanFieldEditor(DiagramPreferenceConstants.HIDE_UNUSED_PORTS, "Hide Unused Ports", parent));
	}
}

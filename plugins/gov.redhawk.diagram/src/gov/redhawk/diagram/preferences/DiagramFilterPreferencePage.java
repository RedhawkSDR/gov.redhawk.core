/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.diagram.preferences;

import gov.redhawk.diagram.PartitioningDiagramPreferenceConstants;

import org.eclipse.gmf.runtime.common.ui.preferences.AbstractPreferencePage;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * @since 3.0
 */
public abstract class DiagramFilterPreferencePage extends AbstractPreferencePage implements IWorkbenchPreferencePage {
	protected BooleanFieldEditor showPortLabels; // SUPPRESS CHECKSTYLE Protected

	public DiagramFilterPreferencePage() {

	}

	@Override
	protected void addFields(final Composite parent) {
		createLabelsGroup(parent);
	}

	protected Composite createLabelsGroup(final Composite parent) {
		final Group group = new Group(parent, SWT.NONE);
		final GridLayout gridLayout = new GridLayout(2, false);
		group.setLayout(gridLayout);
		final GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalSpan = 2;
		group.setLayoutData(gridData);
		group.setText("Labels");

		final Composite composite = new Composite(group, SWT.NONE);

		addLabelFields(composite);

		return group;
	}

	public void initDefaults(final IPreferenceStore store) {
		store.setDefault(PartitioningDiagramPreferenceConstants.PREF_SHOW_PORT_LABELS, true);
	}

	protected void addLabelFields(final Composite composite) {
		this.showPortLabels = new BooleanFieldEditor(PartitioningDiagramPreferenceConstants.PREF_SHOW_PORT_LABELS, "Show Port Labels", composite);
		addField(this.showPortLabels);
	}

}

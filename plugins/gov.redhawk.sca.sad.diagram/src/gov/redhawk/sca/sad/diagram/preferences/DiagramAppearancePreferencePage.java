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
package gov.redhawk.sca.sad.diagram.preferences;

import gov.redhawk.sca.sad.diagram.SadDiagramPreferenceConstants;
import mil.jpeojtrs.sca.sad.diagram.part.SadDiagramEditorPlugin;

import org.eclipse.gmf.runtime.diagram.ui.preferences.AppearancePreferencePage;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class DiagramAppearancePreferencePage extends mil.jpeojtrs.sca.sad.diagram.preferences.DiagramAppearancePreferencePage implements
        IWorkbenchPreferencePage {

	private BooleanFieldEditor showPortLabels;

	public DiagramAppearancePreferencePage() {
		setPreferenceStore(SadDiagramEditorPlugin.getInstance().getPreferenceStore());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gmf.runtime.common.ui.preferences.AbstractPreferencePage#addFields(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void addFields(final Composite parent) {
		final Composite main = createPageLayout(parent);
		createFontAndColorGroup(main);
		createLabelsGroup(main);
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

	/**
	 * @param composite
	 */
	private void addLabelFields(final Composite composite) {
		this.showPortLabels = new BooleanFieldEditor(SadDiagramPreferenceConstants.PREF_SHOW_PORT_LABELS, "Show Port Labels", composite);
		addField(this.showPortLabels);
	}

	/**
	 * Initializes the default preference values
	 * for this preference store.
	 * 
	 * @param store
	 */
	public static void initDefaults(final IPreferenceStore store) {
		AppearancePreferencePage.initDefaults(store);

		store.setDefault(SadDiagramPreferenceConstants.PREF_SHOW_PORT_LABELS, true);
	}

}

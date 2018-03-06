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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

import gov.redhawk.core.graphiti.ui.GraphitiUIPlugin;
import gov.redhawk.core.graphiti.ui.preferences.DiagramPreferenceConstants;

public class PortStatisticsPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

	private List<FieldEditor> fields = new ArrayList<>();

	private IPropertyChangeListener validateAllFields = event -> {
		if (event.getProperty().equals(FieldEditor.IS_VALID)) {
			for (FieldEditor field : fields) {
				if (!field.isValid()) {
					setValid(false);
					return;
				}
			}
			setValid(true);
			return;
		}
	};

	@Override
	public void init(IWorkbench workbench) {
		setPreferenceStore(new ScopedPreferenceStore(InstanceScope.INSTANCE, GraphitiUIPlugin.PLUGIN_ID));
		setDescription("Graphical port monitoring preferences");
	}

	@Override
	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());
		composite.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));

		createWarningGroup(composite);
		createErrorGroup(composite);

		for (FieldEditor field : fields) {
			field.load();
			field.setPropertyChangeListener(validateAllFields);
		}

		return composite;
	}

	/**
	 * Controls for warning events when port statistics are running
	 */
	private void createWarningGroup(Composite parent) {
		Group warningGroup = new Group(parent, SWT.LEFT);
		warningGroup.setLayout(new GridLayout());
		warningGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		warningGroup.setText(GraphitiPreferencesNLS.PortStatPreference_warningGroupTitle);
		warningGroup.setToolTipText(GraphitiPreferencesNLS.PortStatPreference_warningGroupToolTip);

		// Grid composite of two columns to hold all of the actual preference entries
		Composite prefComposite = new Composite(warningGroup, SWT.LEFT);
		prefComposite.setLayout(new GridLayout(2, true));
		prefComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		DoubleFieldEditor queueLevel = new DoubleFieldEditor(DiagramPreferenceConstants.PREF_PORT_STATISTICS_QUEUE_LEVEL,
			GraphitiPreferencesNLS.PortStatPreference_warningQueueLevel, prefComposite);
		queueLevel.getTextControl(prefComposite).setToolTipText(GraphitiPreferencesNLS.PortStatPreference_warningQueueLevelToolTip);
		queueLevel.setPreferenceStore(getPreferenceStore());
		queueLevel.setPage(this);
		queueLevel.setErrorMessage(GraphitiPreferencesNLS.PortStatPreference_warningQueueLevelError);
		queueLevel.setValidRange(0.0, 100.0);
		fields.add(queueLevel);

		DoubleFieldEditor timeSinceLastPush = new DoubleFieldEditor(DiagramPreferenceConstants.PREF_PORT_STATISTICS_NO_DATA_PUSHED_SECONDS,
			GraphitiPreferencesNLS.PortStatPreference_warningNoData, prefComposite);
		timeSinceLastPush.getTextControl(prefComposite).setToolTipText(GraphitiPreferencesNLS.PortStatPreference_warningNoDataToolTip);
		timeSinceLastPush.setPreferenceStore(getPreferenceStore());
		timeSinceLastPush.setPage(this);
		timeSinceLastPush.setErrorMessage(GraphitiPreferencesNLS.PortStatPreference_warningNoDataError);
		fields.add(timeSinceLastPush);
	}

	/**
	 * Controls for error events when port statistics are running
	 */
	private void createErrorGroup(Composite parent) {
		Group errorGroup = new Group(parent, SWT.LEFT);
		errorGroup.setLayout(new GridLayout());
		errorGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		errorGroup.setText(GraphitiPreferencesNLS.PortStatPreference_errorGroupTitle);
		errorGroup.setToolTipText(GraphitiPreferencesNLS.PortStatPreference_errorGroupToolTip);

		// Grid composite of two columns to hold all of the actual preference entries
		Composite prefComposite = new Composite(errorGroup, SWT.LEFT);
		prefComposite.setLayout(new GridLayout(2, true));
		prefComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		DoubleFieldEditor queueFlush = new DoubleFieldEditor(DiagramPreferenceConstants.PREF_PORT_STATISTICS_QUEUE_FLUSH_DISPLAY,
			GraphitiPreferencesNLS.PortStatPreference_errorQueueFlush, prefComposite);
		queueFlush.getTextControl(prefComposite).setToolTipText(GraphitiPreferencesNLS.PortStatPreference_errorQueueFlushToolTip);
		queueFlush.setPreferenceStore(getPreferenceStore());
		queueFlush.setPage(this);
		queueFlush.setErrorMessage(GraphitiPreferencesNLS.PortStatPreference_errorQueueFlushError);
		fields.add(queueFlush);
	}

	@Override
	protected void performDefaults() {
		for (FieldEditor field : fields) {
			field.loadDefault();
		}
		super.performDefaults();
	}

	@Override
	public boolean performOk() {
		for (FieldEditor field : fields) {
			field.store();
		}
		return super.performOk();
	}

}

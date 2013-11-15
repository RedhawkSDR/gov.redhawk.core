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
package gov.redhawk.sca.ui.preferences;

import gov.redhawk.common.ui.doc.HelpConstants;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.sca.ScaPlugin;
import gov.redhawk.sca.preferences.ScaPreferenceConstants;
import gov.redhawk.sca.ui.ScaUiPlugin;

import java.io.IOException;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.statushandlers.StatusManager;

public class RedhawkScaPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	public static final String PREFERENCE_PAGE_ID = "gov.redhawk.ui.preferences.sca";
	private DomainListEditor domList = null;
	private ScaDomainManagerRegistry registry;

	@Override
	protected void createFieldEditors() {
		final StringFieldEditor field = new StringFieldEditor(ScaPreferenceConstants.SCA_DEFAULT_NAMING_SERVICE, "Default Naming Service:",
				getFieldEditorParent());
		addField(field);

		this.registry = ScaPlugin.getDefault().getDomainManagerRegistry(getFieldEditorParent().getDisplay());

		this.domList = new DomainListEditor(this.registry, "Domains:", getFieldEditorParent());
		addField(this.domList);

		/** We bind this field editor to a fake preference because we just use it to set the auto-connect 
		  preference on all domains when selected */
		final RadioGroupFieldEditor autoConnect = new RadioGroupFieldEditor("FAKE", "All Domains: Reconnect on startup", 2, new String[][] {
				{ "Auto", "true" }, { "Manual", "false" } }, getFieldEditorParent(), false);
		autoConnect.setPropertyChangeListener(new IPropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent event) {
				Boolean auto = Boolean.valueOf((String) event.getNewValue());
				RedhawkScaPreferencePage.this.domList.setAllAutoConnect(auto);
			}

		});

		final StringListFieldEditor listField = new StringListFieldEditor(ScaPreferenceConstants.SCA_DOMAIN_WAVEFORMS_SEARCH_PATH, "Waveforms Search Path:",
				getFieldEditorParent());
		addField(listField);
		listField.getUpButton().setVisible(false);
		listField.getDownButton().setVisible(false);

		PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), HelpConstants.reference_preferences_domainConnections);
	}

	@Override
	public boolean performOk() {
		super.performOk();
		final boolean[] success = {false};
		final boolean[] done = {false};
		ScaModelCommand.execute(RedhawkScaPreferencePage.this.registry, new ScaModelCommand() {

			@Override
			public void execute() {
				try {
					RedhawkScaPreferencePage.this.registry.eResource().save(null);
					success[0] = true;
				} catch (IOException e) {
					StatusManager.getManager().handle(new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, "Failed to save SCA Domain Connections to configuration.", e),
							StatusManager.LOG | StatusManager.SHOW);
				} finally {
					done[0] = true;
				}
			}

		});
		
		while (!done[0]) {
			if (!getShell().getDisplay().readAndDispatch()) {
				getShell().getDisplay().sleep();
			}
		}
		return success[0];
	}

	@Override
	protected void checkState() {
		super.checkState();
	}

	@Override
	public void init(final IWorkbench workbench) {
		setPreferenceStore(ScaUiPlugin.getDefault().getScaPreferenceStore());
	}

}

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
package gov.redhawk.sca.ui.singledomain.preferences;

import gov.redhawk.common.ui.doc.HelpConstants;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.sca.ScaPlugin;
import gov.redhawk.sca.ui.ScaUiPlugin;
import gov.redhawk.sca.ui.singledomain.ScaSingleDomainPreferenceConstants;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;

public class ActiveDomainPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	public static final String PREFERENCE_PAGE_ID = "gov.redhawk.ui.preferences.activeDomain";
	private ComboFieldEditor activeDomainField;
	
	private AdapterImpl domainChangeAdapter = new AdapterImpl() {
		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getNotifier() instanceof ScaDomainManagerRegistry) {
				switch (msg.getFeatureID(ScaDomainManagerRegistry.class)) {
				case ScaPackage.SCA_DOMAIN_MANAGER_REGISTRY__DOMAINS:
					switch (msg.getEventType()) {
					case Notification.REMOVE:
						ScaDomainManager domainRemoved = (ScaDomainManager) msg.getOldValue();
						if (domainRemoved.getName().equals(getActiveDomainName())) {
							setActiveDomain("");
						}
						activeDomainField.load();
						break;
					case Notification.ADD:
						if (getPreferenceStore().getBoolean(ScaSingleDomainPreferenceConstants.SCA_SET_NEW_DOMAIN_ACTIVE)) {
							ScaDomainManager newDomain = (ScaDomainManager) msg.getNewValue();
							setActiveDomain(newDomain.getName());
						} else {
							setActiveDomain("");
						}
						activeDomainField.load();
						break;
					}
					break;
				}
			}
		}
	};
	
	private BooleanFieldEditor setNewDomainActiveField;
	private BooleanFieldEditor disconnectInactive;
	
	protected String getActiveDomainName() {
		return getPreferenceStore().getString(ScaSingleDomainPreferenceConstants.SCA_ACTIVE_DOMAIN);
	}
	
	private void setActiveDomain(String name) {
		if (name == null || name.equals("")) {
			getPreferenceStore().setToDefault(ScaSingleDomainPreferenceConstants.SCA_ACTIVE_DOMAIN);
		} else {
			getPreferenceStore().setValue(ScaSingleDomainPreferenceConstants.SCA_ACTIVE_DOMAIN, name);
		}
	}
	
	

	@Override
	protected void createFieldEditors() {
		
		ScaPlugin.getDefault().getDomainManagerRegistry().eAdapters().add(domainChangeAdapter);
		
		activeDomainField = new ComboFieldEditor(ScaSingleDomainPreferenceConstants.SCA_ACTIVE_DOMAIN, "Active Domain", getDomains(), getFieldEditorParent());
		addField(activeDomainField);
		
		setNewDomainActiveField = new BooleanFieldEditor(ScaSingleDomainPreferenceConstants.SCA_SET_NEW_DOMAIN_ACTIVE, "Make a newly created domain the active domain",
				getFieldEditorParent());
		addField(setNewDomainActiveField);
		
		disconnectInactive = new BooleanFieldEditor(ScaSingleDomainPreferenceConstants.SCA_DISCONNECT_INACTIVE, "Disconnect a domain when it ceases to be the active domain",
				getFieldEditorParent());
		addField(disconnectInactive);

		PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), HelpConstants.singleDomain);
		
		activeDomainField.load();
		setNewDomainActiveField.load();
		disconnectInactive.load();
	}

	private String[][] getDomains() {
		List<String[]> domains = new ArrayList<String[]>();
		domains.add(new String[] {"", ""});
	    for (ScaDomainManager  domain : ScaPlugin.getDefault().getDomainManagerRegistry().getDomains()) {
	    	String[] nameValue = new String[2];
	    	nameValue[0] = domain.getName();
	    	nameValue[1] = domain.getName();
	    	domains.add(nameValue);
	    }
	    return domains.toArray(new String[0][0]);
    }

	@Override
	public boolean performOk() {
		activeDomainField.store();
	    setNewDomainActiveField.store();
	    disconnectInactive.store();
		return super.performOk();
	}

	@Override
	protected void checkState() {
		super.checkState();
	}

	public void init(final IWorkbench workbench) {
		setPreferenceStore(ScaUiPlugin.getDefault().getScaPreferenceStore());
	}
	
	@Override
	public IPreferenceStore getPreferenceStore() {
	    return super.getPreferenceStore();
	}
	

}

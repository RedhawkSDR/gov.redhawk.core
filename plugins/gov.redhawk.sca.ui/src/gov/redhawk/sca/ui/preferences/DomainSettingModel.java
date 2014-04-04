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

import gov.redhawk.sca.preferences.ScaPreferenceConstants;
import gov.redhawk.sca.ui.ScaUiPlugin;
import gov.redhawk.sca.util.PropertyChangeSupport;

import java.beans.PropertyChangeListener;

/**
 * @since 7.0
 */
public class DomainSettingModel {

	public static enum ConnectionMode {
		MANUAL,
		NOW,
		AUTO
	};

	public static final String PROP_DOMAIN_NAME = "domainName";
	public static final String PROP_NAME_SERVICE_INIT_REF = "nameServiceInitRef";
	public static final String PROP_CONNECTION_MODE = "connectionMode";
	private String domainName;
	private String nameServiceInitRef = "localhost";
	private ConnectionMode connectionMode = ConnectionMode.NOW;
	private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

	public DomainSettingModel() {
		final String namingService = ScaUiPlugin.getDefault().getScaPreferenceStore().getString(ScaPreferenceConstants.SCA_DEFAULT_NAMING_SERVICE);
		if (namingService != null && !"".equals(namingService)) {
			setNameServiceInitRef(namingService);
		}
	}

	public String getDomainName() {
		return this.domainName;
	}

	public void setDomainName(final String domainName) {
		final String oldValue = this.domainName;
		this.domainName = domainName;
		this.propertyChangeSupport.firePropertyChange(DomainSettingModel.PROP_DOMAIN_NAME, oldValue, domainName);
	}

	public String getNameServiceInitRef() {
		return this.nameServiceInitRef;
	}

	public void setNameServiceInitRef(final String nameServiceInitRef) {
		final String oldValue = this.nameServiceInitRef;
		this.nameServiceInitRef = nameServiceInitRef;
		this.propertyChangeSupport.firePropertyChange(DomainSettingModel.PROP_NAME_SERVICE_INIT_REF, oldValue, nameServiceInitRef);
	}

	public ConnectionMode getConnectionMode() {
		return this.connectionMode;
	}

	public void setConnectionMode(final ConnectionMode connectionMode) {
		final ConnectionMode oldValue = this.connectionMode;
		this.connectionMode = connectionMode;
		this.propertyChangeSupport.firePropertyChange(DomainSettingModel.PROP_CONNECTION_MODE, oldValue, connectionMode);
	}

	public void addPropertyChangeListener(final PropertyChangeListener listener) {
		this.propertyChangeSupport.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(final PropertyChangeListener listener) {
		this.propertyChangeSupport.removePropertyChangeListener(listener);
	}

	public void addPropertyChangeListener(final String propertyName, final PropertyChangeListener listener) {
		this.propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
	}

	public void removePropertyChangeListener(final String propertyName, final PropertyChangeListener listener) {
		this.propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
	}

}

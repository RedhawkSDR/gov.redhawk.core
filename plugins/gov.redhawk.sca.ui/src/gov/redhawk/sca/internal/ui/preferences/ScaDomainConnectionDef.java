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
package gov.redhawk.sca.internal.ui.preferences;

public class ScaDomainConnectionDef {
	private static final int NUM_PREFERENCES = 3;

	private boolean connectOnStartup;

	private String nameServiceInitRef;

	private String domainName;

	public ScaDomainConnectionDef() {
	}

	public ScaDomainConnectionDef(final String domainName, final String nameServiceInitRef, final boolean connectOnStartup) {
		super();

		this.domainName = domainName;
		this.nameServiceInitRef = nameServiceInitRef;
		this.connectOnStartup = connectOnStartup;
	}

	public boolean isConnectOnStartup() {
		return this.connectOnStartup;
	}

	public void setConnectOnStartup(final boolean connectOnStartup) {
		this.connectOnStartup = connectOnStartup;
	}

	public String getNameServiceInitRef() {
		return this.nameServiceInitRef;
	}

	public void setNameServiceInitRef(final String nameServiceInitRef) {
		this.nameServiceInitRef = nameServiceInitRef;
	}

	public String getDomainName() {
		return this.domainName;
	}

	public void setDomainName(final String domainName) {
		this.domainName = domainName;
	}

	@Override
	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append(this.domainName);
		buffer.append(" [");
		buffer.append(this.nameServiceInitRef);
		buffer.append(']');
		if (this.connectOnStartup) {
			buffer.append(" AUTO");
		}

		return buffer.toString();
	}

	public String toPreferenceValue() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append(this.domainName);
		buffer.append(',');
		buffer.append(this.nameServiceInitRef);
		buffer.append(',');
		buffer.append(this.connectOnStartup);
		buffer.append(',');

		return buffer.toString();
	}

	public void fromPreferenceValue(final String preferenceValue) {
		final String[] values = preferenceValue.split(",");
		if (values.length == ScaDomainConnectionDef.NUM_PREFERENCES) {
			this.domainName = values[0];
			this.nameServiceInitRef = values[1];
			this.connectOnStartup = Boolean.parseBoolean(values[2]);
		}
	}

	@Override
	public boolean equals(final Object obj) {
		if ((obj == null) || !(obj instanceof ScaDomainConnectionDef)) {
			return false;
		}

		final ScaDomainConnectionDef def = (ScaDomainConnectionDef) obj;
		return this.domainName.equals(def.getDomainName()) && this.nameServiceInitRef.equals(def.getNameServiceInitRef())
		        && (this.connectOnStartup == def.isConnectOnStartup());
	}
}

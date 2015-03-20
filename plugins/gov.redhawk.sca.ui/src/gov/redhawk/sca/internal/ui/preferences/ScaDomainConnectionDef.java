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

public class ScaDomainConnectionDef {
	private boolean connectOnStartup;

	private String nameServiceInitRef;

	private String domainName;
	
	private String localName;

	public ScaDomainConnectionDef() {
	}

	public ScaDomainConnectionDef(final String localName, final String domainName, final String nameServiceInitRef, final boolean connectOnStartup) {
		super();

		this.domainName = domainName;
		this.localName = localName;
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
		if (this.localName != null) {
			buffer.append(this.localName);
		} else {
			buffer.append(this.domainName);
		}
		
		buffer.append(" [");
		buffer.append(this.domainName);
		buffer.append("@");
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
		if (values.length > 0) {
			this.domainName = values[0];
		}
		if (values.length > 1) {
			this.nameServiceInitRef = values[1];
		}
		if (values.length > 2) {
			this.connectOnStartup = Boolean.parseBoolean(values[2]);
		}
		
		if (values.length > 3) {
			this.localName = values[3];
		}
	}
	
	public String getLocalName() {
		return localName;
	}
	
	public void setLocalName(String localName) {
		this.localName = localName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((connectOnStartup) ? 1231 : 1237);
		result = prime * result + ((domainName == null) ? 0 : domainName.hashCode());
		result = prime * result + ((localName == null) ? 0 : localName.hashCode());
		result = prime * result + ((nameServiceInitRef == null) ? 0 : nameServiceInitRef.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
		ScaDomainConnectionDef other = (ScaDomainConnectionDef) obj;
		if (connectOnStartup != other.connectOnStartup) {
			return false;
		}
		if (domainName == null) {
			if (other.domainName != null) {
				return false;
			}
		} else if (!domainName.equals(other.domainName)) {
			return false;
		}
		if (localName == null) {
			if (other.localName != null) {
				return false;
			}
		} else if (!localName.equals(other.localName)) {
			return false;
		}
		if (nameServiceInitRef == null) {
			if (other.nameServiceInitRef != null) {
				return false;
			}
		} else if (!nameServiceInitRef.equals(other.nameServiceInitRef)) {
			return false;
		}
		return true;
	}


}

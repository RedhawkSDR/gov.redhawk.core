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
package gov.redhawk.ui.port.nxmplot;


/**
 * @since 2.1
 */
public class CorbaConnectionSettings {
	private String host = "localhost";
	private int port = 2809; // SUPPRESS CHECKSTYLE MagicNumber
	private String resource = "<DomainManager>/<Waveform>/<Resource>";
	private String portName = "sample_out";
	private String idl;
	private boolean is2D = false;
	private String portString = "";

	public CorbaConnectionSettings() {
		this.portString = this.resource.substring(this.resource.lastIndexOf('/') + 1) + "[" + this.portName + "]";
	}

	public CorbaConnectionSettings(final String portPath, final String idl, final boolean is2D) {
		this.host = "";
		this.port = 0;
		this.portName = portPath;
		this.resource = "";
		this.is2D = is2D;
		this.idl = idl;
	}

	public String getHost() {
		return this.host;
	}

	public void setHost(final String host) {
		this.host = host;
	}

	public int getPort() {
		return this.port;
	}

	public void setPort(final int port) {
		this.port = port;
	}

	public String getResource() {
		return this.resource;
	}

	public void setResource(final String resource) {
		this.resource = resource;
	}

	public String getPortName() {
		return this.portName;
	}

	public void setPortName(final String portName) {
		this.portName = portName;
	}

	public String getIDL() {
		return this.idl;
	}

	public void setIDL(final String idl) {
		this.idl = idl;
	}

	public void set2D(final boolean is2D) {
		this.is2D = is2D;
	}

	public boolean is2D() {
		return this.is2D;
	}

	public void updatePortString() {
		int index = this.resource.lastIndexOf('/');
		this.portString = this.resource.substring(++index) + "[" + this.portName + "]";
	}

	public void setPortString(final String portString) {
		this.portString = portString;
	}

	public String getPortString() {
		return this.portString;
	}

}

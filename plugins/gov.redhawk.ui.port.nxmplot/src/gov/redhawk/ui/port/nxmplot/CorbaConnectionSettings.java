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
	private String portString = "";
	private int frameSize = 1024;
	private boolean overrideSRISubSize;

	public CorbaConnectionSettings() {
		this.portString = this.resource.substring(this.resource.lastIndexOf('/') + 1) + "[" + this.portName + "]";
	}

	/**
	 * @param portPath
	 * @param idl
	 * @param force2D
	 * @deprecated Plots are always type 2000,  use set Frame size for changing the framing.  Use {@link #CorbaConnectionSettings(String, String)} instead.
	 */
	@Deprecated
	public CorbaConnectionSettings(final String portPath, final String idl, boolean force2D) {
		this(portPath, idl);
	}

	/**
	 * @since 5.0
	 */
	public CorbaConnectionSettings(final String portPath, final String idl) {
		this.host = "";
		this.port = 0;
		this.portName = portPath;
		this.resource = "";
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

	/**
	 * 
	 * @deprecated Has no effect
	 */
	@Deprecated
	public void set2D(final boolean is2D) {

	}

	/**
	 * @deprecated Is always true
	 */
	@Deprecated
	public boolean is2D() {
		return true;
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

	/**
	 * @since 5.0
	 */
	public void setFrameSize(int frameSize) {
		this.frameSize = frameSize;
	}

	/**
	 * @since 5.0
	 */
	public int getFrameSize() {
		return frameSize;
	}

	/**
	 * @see #setOverrideSRISubSize(boolean)
	 * @since 5.0
	 */
	public boolean isOverrideSRISubSize() {
		return this.overrideSRISubSize;
	}

	/**
	 * @param overrideSRISubSize Override the SRI subsize with the given framesize. Default false.
	 * @since 5.0
	 */
	public void setOverrideSRISubSize(boolean overrideSRISubSize) {
		this.overrideSRISubSize = overrideSRISubSize;
	}

}

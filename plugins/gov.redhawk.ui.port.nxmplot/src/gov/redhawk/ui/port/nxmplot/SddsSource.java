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
 * @since 3.0
 */
public class SddsSource {

	public String mcastAddress; // SUPPRESS CHECKSTYLE VARIABLE
	public int port;            // SUPPRESS CHECKSTYLE VARIABLE
	public int vlan;            // SUPPRESS CHECKSTYLE VARIABLE
	public String format;       // SUPPRESS CHECKSTYLE VARIABLE

	public SddsSource(String mcastAddress, final int port, final int vlan, final String format) {
		this.mcastAddress = mcastAddress;
		this.port = port;
		this.vlan = vlan;
		this.format = format;
	}
}

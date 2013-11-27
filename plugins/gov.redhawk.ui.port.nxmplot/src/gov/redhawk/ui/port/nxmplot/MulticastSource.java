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
 * @deprecated since 4.3 - this is not used in this plug-in
 */
@Deprecated
public class MulticastSource {

	public String payload;      // SUPPRESS CHECKSTYLE VARIABLE
	public String mcastAddress; // SUPPRESS CHECKSTYLE VARIABLE
	public int vlan;            // SUPPRESS CHECKSTYLE VARIABLE
	public int port;            // SUPPRESS CHECKSTYLE VARIABLE
	public String format;       // SUPPRESS CHECKSTYLE VARIABLE
	public String mode;         // SUPPRESS CHECKSTYLE VARIABLE
	public Object frameSize;    // SUPPRESS CHECKSTYLE VARIABLE

	public MulticastSource(
			final String payload,
			final String mcastAddress,
			final int vlan,
			final int port,
			final String format,
			final String mode,
			final int frameSize) {
		this.payload = payload;
		this.mcastAddress = mcastAddress;
		this.vlan = vlan;
		this.port = port;
		this.format = format;
		this.mode = mode;
		this.frameSize = frameSize;
	}
}

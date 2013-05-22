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
public class MulticastSource {

	public String payload;
	public String mcastAddress;
	public int vlan;
	public int port;
	public String format;
	public String mode;
	public Object frameSize;

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

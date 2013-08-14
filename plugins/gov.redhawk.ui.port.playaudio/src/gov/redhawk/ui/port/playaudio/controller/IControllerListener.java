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
package gov.redhawk.ui.port.playaudio.controller;

import javax.sound.sampled.AudioFormat;

/**
 * @since 1.1
 * @deprecated
 */
@Deprecated
public interface IControllerListener {

	/**
	 * This method is called whenever the Controller detects a change in the
	 * AudioFormat
	 * 
	 * @param format the new AudioFormat being played
	 */
	public void setFormat(AudioFormat format);

	/**
	 * This method is called whenever a port is disconnected
	 */
	public void refresh();
}

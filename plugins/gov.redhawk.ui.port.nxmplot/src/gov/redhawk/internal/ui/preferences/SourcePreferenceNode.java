/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.internal.ui.preferences;

import org.eclipse.jface.preference.PreferenceNode;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 *
 */
public class SourcePreferenceNode extends PreferenceNode {

	private String label;
	
	/**
	 * @param id
	 * @param label
	 * @param image
	 * @param className
	 */
	public SourcePreferenceNode(String id, String label, ImageDescriptor image, String className) {
		super(id, label, image, className);
		this.label = label;
	}
	
	@Override
	public String getLabelText() {
		return label;
	}

}

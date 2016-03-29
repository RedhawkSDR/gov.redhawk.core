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

import org.eclipse.jface.preference.IPreferencePage;
import org.eclipse.jface.preference.PreferenceNode;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.model.IComparableContribution;

public class PlotPreferenceNode extends PreferenceNode implements IComparableContribution {

	public PlotPreferenceNode(String id, IPreferencePage preferencePage) {
		super(id, preferencePage);
	}

	public PlotPreferenceNode(String id, String label, ImageDescriptor image, String className) {
		super(id, label, image, className);
	}

	public PlotPreferenceNode(String id) {
		super(id);
	}

	@Override
	public int getPriority() {
		return -1;
	}

	@Override
	public String getLabel() {
		return "Plot";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" }) // Mandated by interface
	@Override
	public Object getAdapter(Class adapter) {
		return null;
	}

}

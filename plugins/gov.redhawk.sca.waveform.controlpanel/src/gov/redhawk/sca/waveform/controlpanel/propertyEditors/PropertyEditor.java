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


package gov.redhawk.sca.waveform.controlpanel.propertyEditors;


import gov.redhawk.model.sca.ScaAbstractProperty;

import org.eclipse.swt.widgets.Composite;

public abstract class PropertyEditor {
	
	protected PropertyEditor() { }
	
	public  PropertyEditor(String name, Object value) {
	}
	
	public  PropertyEditor(String name, Object value, ScaAbstractProperty<?> prop) {
	}
	
	public abstract void renderNameValuePair(Composite parent);
	
	public abstract void setupBindings();
	
	public abstract void dispose();

}

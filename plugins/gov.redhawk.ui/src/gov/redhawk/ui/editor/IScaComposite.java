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
package gov.redhawk.ui.editor;

/**
 * @since 2.3
 */
public interface IScaComposite {

	/**
	 * Enables or disables the edit capability on the fields that belong to this
	 * composite.
	 * 
	 * @param canEdit boolean representing whether the composite's fields should
	 *            be editable
	 */
	public void setEditable(boolean canEdit);
}

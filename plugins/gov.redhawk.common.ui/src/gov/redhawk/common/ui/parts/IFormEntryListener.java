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
package gov.redhawk.common.ui.parts;

import org.eclipse.ui.forms.events.IHyperlinkListener;

/**
 * The listener interface for receiving IFormEntry events. The class that is
 * interested in processing a IFormEntry event implements this interface, and
 * the object created with that class is registered with a component using the
 * component's <code>addIFormEntryListener</code> method. When the IFormEntry
 * event occurs, that object's appropriate method is invoked.
 * 
 * @see IFormEntryEvent
 * @since 3.0
 */
public interface IFormEntryListener extends IHyperlinkListener {

	/**
	 * The user pressed the button for the entry.
	 * 
	 * @param entry the entry
	 */
	void buttonSelected(FormEntry entry);
}

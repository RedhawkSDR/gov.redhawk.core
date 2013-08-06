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
package gov.redhawk.sca.ui.singledomain;

import java.util.EventObject;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;

public class CustomMouseEvent extends EventObject {
	
	public CustomMouseEvent(MouseEvent e) {
		super(e.getSource());
		x = e.x;
		y = e.y;
	}

	/**
     * 
     */
    private static final long serialVersionUID = -1326963257531635346L;
    
	public int x; // SUPPRESS CHECKSTYLE Event Object
	
	public int y; // SUPPRESS CHECKSTYLE Event Object

	public CustomMouseEvent(Object source, Point location) {
	    super(source);
	    x = location.x;
	    y = location.y;
    }

}

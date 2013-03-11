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
/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package gov.redhawk.ui.parts;

import org.eclipse.jface.viewers.ILabelProvider;

/**
 * The Interface ILinkLabelProvider.
 */
public interface ILinkLabelProvider extends ILabelProvider {

	/**
	 * Gets the status text.
	 * 
	 * @param object the object
	 * @return the status text
	 */
	String getStatusText(Object object);

	/**
	 * Gets the tool tip text.
	 * 
	 * @param object the object
	 * @return the tool tip text
	 */
	String getToolTipText(Object object);
}

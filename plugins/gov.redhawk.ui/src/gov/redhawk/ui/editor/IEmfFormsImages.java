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
/**
 * Copyright (c) 2009 Anyware Technologies and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Anyware Technologies - initial API and implementation
 *
 * $Id: IEmfFormsImages.java,v 1.4 2009/09/07 13:16:08 bcabe Exp $
 */

package gov.redhawk.ui.editor;

/**
 * Interface used to stock information about images used in the platform editor
 */
public final class IEmfFormsImages {
	private IEmfFormsImages() {

	}
	public static final String ICONS_FOLDER = "icons/"; //$NON-NLS-1$

	public static final String DECORATORS_FOLDER = IEmfFormsImages.ICONS_FOLDER + "ovr16/"; //$NON-NLS-1$

	public static final String ERROR_DECORATOR = IEmfFormsImages.DECORATORS_FOLDER + "error.gif"; //$NON-NLS-1$

	public static final String WARNING_DECORATOR = IEmfFormsImages.DECORATORS_FOLDER + "warning.gif"; //$NON-NLS-1$

	public static final String OBJECT_FOLDER = IEmfFormsImages.ICONS_FOLDER + "obj16/"; //$NON-NLS-1$

	public static final String ADD_TOOLBAR_BUTTON = IEmfFormsImages.OBJECT_FOLDER + "add.png"; //$NON-NLS-1$

	public static final String REMOVE_TOOLBAR_BUTTON = IEmfFormsImages.OBJECT_FOLDER + "remove.png"; //$NON-NLS-1$
}

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
/*******************************************************************************
 * Copyright (c) 2006, 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 ******************************************************************************/

package gov.redhawk.sca.ui.singledomain;

import gov.redhawk.sca.ui.singledomain.views.ScaExplorerSingleDomain;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.IDescriptionProvider;
import org.eclipse.ui.navigator.INavigatorContentService;

/**
 * Defines a label provider for the title bar in the tabbed properties view.
 * 
 * @since 8.0
 */
public class TabbedPropertySheetTitleProvider extends LabelProvider {

	private ILabelProvider labelProvider;

	private IDescriptionProvider descriptionProvider;

	/**
	 * Constructor for CommonNavigatorTitleProvider.
	 */
	public TabbedPropertySheetTitleProvider() {
		super();
		final IWorkbenchPart part = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(ScaExplorerSingleDomain.VIEW_ID);

		if (part != null) {
			final INavigatorContentService contentService = (INavigatorContentService) part.getAdapter(INavigatorContentService.class);
			if (contentService != null) {
				this.labelProvider = contentService.createCommonLabelProvider();
				this.descriptionProvider = contentService.createCommonDescriptionProvider();
			} else {
				ScaSingleDomainPlugin.getDefault().getLog()
				        .log(new Status(IStatus.ERROR, ScaSingleDomainPlugin.PLUGIN_ID, "Could not acquire INavigatorContentService from part (\"" //$NON-NLS-1$
				                + part.getTitle() + "\").", null)); //$NON-NLS-1$
			}
		}

	}

	/**
	 * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
	 */
	@Override
	public Image getImage(final Object object) {
		return this.labelProvider != null ? this.labelProvider.getImage(object) : null;// SUPPRESS CHECKSTYLE AvoidInLine
	}

	/**
	 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
	 */
	@Override
	public String getText(final Object object) {
		return this.descriptionProvider != null ? this.descriptionProvider.getDescription(object) : null;// SUPPRESS CHECKSTYLE AvoidInLine
	}
}

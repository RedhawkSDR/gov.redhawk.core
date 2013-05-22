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
package gov.redhawk.sca.ui.views;

import gov.redhawk.common.ui.doc.HelpConstants;
import gov.redhawk.sca.ScaPlugin;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * @since 8.0
 * 
 */
public class ScaExplorer extends CommonNavigator implements ITabbedPropertySheetPageContributor {

	public static final String VIEW_ID = "gov.redhawk.ui.sca_explorer";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createPartControl(final Composite parent) {
		super.createPartControl(parent);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(this.getCommonViewer().getControl(), HelpConstants.reference_views_scaExplorer);
	}

	@Override
	protected Object getInitialInput() {
		return ScaPlugin.getDefault().getDomainManagerRegistry();
	}

	public String getContributorId() {
		return getSite().getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(final Class adapter) {
		if (IPropertySheetPage.class == adapter) {
			return new TabbedPropertySheetPage(this);
		}
		return super.getAdapter(adapter);
	}
}

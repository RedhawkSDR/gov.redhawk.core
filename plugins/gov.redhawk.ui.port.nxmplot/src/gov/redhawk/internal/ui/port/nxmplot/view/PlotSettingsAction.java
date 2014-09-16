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
package gov.redhawk.internal.ui.port.nxmplot.view;

import gov.redhawk.internal.ui.preferences.PlotPreferenceDialog;
import gov.redhawk.internal.ui.preferences.PlotPreferenceNode;
import gov.redhawk.internal.ui.preferences.PlotPreferencePage;
import gov.redhawk.internal.ui.preferences.SourcePreferencePage;
import gov.redhawk.model.sca.ScaAbstractComponent;
import gov.redhawk.model.sca.ScaPortContainer;
import gov.redhawk.ui.port.nxmplot.INxmBlock;
import gov.redhawk.ui.port.nxmplot.PlotPageBook2;
import gov.redhawk.ui.port.nxmplot.PlotSource;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.preference.IPreferencePage;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.jface.preference.PreferenceNode;

/**
 * 
 */
public class PlotSettingsAction extends Action {

	private PlotPageBook2 pageBook;

	public PlotSettingsAction(PlotPageBook2 pageBook) {
		super("Settings...", IAction.AS_PUSH_BUTTON);

		this.pageBook = pageBook;
	}

	@Override
	public void run() {
		PreferenceManager manager = new PreferenceManager();

		if (pageBook.getSources().size() > 0) {
			PlotPreferencePage plotPage = new PlotPreferencePage("Plot", false);
			plotPage.setPreferenceStore(pageBook.getActivePlotWidget().getPreferenceStore());

			PlotPreferenceNode plotNode = new PlotPreferenceNode("plotSettings", plotPage);
			manager.addToRoot(plotNode);
			for (PlotSource source : pageBook.getSources()) {
				List<INxmBlock> blockChain = pageBook.getBlockChain(source);
				String name = source.getInput().getName();
				ScaPortContainer container = source.getInput().getPortContainer();
				if (container instanceof ScaAbstractComponent<?>) {
					ScaAbstractComponent<?> component = (ScaAbstractComponent<?>) container;
					String qualifier = component.getIdentifier();
					int colonIndex = qualifier.indexOf(':');
					if (colonIndex > -1) {
						name = qualifier.substring(0, colonIndex) + " -> " + name;
					}
				}
				SourcePreferencePage sourcePrefPage = new SourcePreferencePage(name, pageBook, blockChain);
				PreferenceNode sourceNode = new PreferenceNode(source.toString(), sourcePrefPage);

				for (INxmBlock block : blockChain) {
					IPreferencePage page = block.createPreferencePage();
					if (page != null) {
						PreferenceNode blockNode = new PreferenceNode(block.toString(), page);
						sourceNode.add(blockNode);
					}
				}
				manager.addToRoot(sourceNode);
			}
		} else {
			PlotPreferencePage plotPage = new PlotPreferencePage("Plot");
			plotPage.setPreferenceStore(pageBook.getActivePlotWidget().getPreferenceStore());
			plotPage.setBlockPreferenceStore(pageBook.getSharedPlotBlockPreferences());
			PlotPreferenceNode plotNode = new PlotPreferenceNode("plotSettings", plotPage);
			manager.addToRoot(plotNode);
		}

		PlotPreferenceDialog dialog = new PlotPreferenceDialog(pageBook.getShell(), manager);
		dialog.open();
	}
}

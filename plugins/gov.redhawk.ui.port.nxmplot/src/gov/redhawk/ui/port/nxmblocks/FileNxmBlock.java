/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.ui.port.nxmblocks;

import gov.redhawk.ui.port.nxmplot.AbstractNxmPlotWidget;
import gov.redhawk.ui.port.nxmplot.preferences.FileNxmBlockPreferences;
import gov.redhawk.ui.port.nxmplot.preferences.Preference;
import nxm.sys.lib.Command;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.PropertyChangeEvent;

/**
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.4
 */
public class FileNxmBlock extends AbstractNxmBlock<Command> {

	private final String filename;

	public FileNxmBlock(@NonNull String filename, @NonNull AbstractNxmPlotWidget plotWidget) {
		super(Command.class, plotWidget, FileNxmBlock.initStore());
		this.filename = filename;
	}

	private static IPreferenceStore initStore() {
		return Preference.initStoreFromWorkbench(FileNxmBlockPreferences.getAllPreferences());
	}

	@Override
	public void dispose() {
		// nothing to dispose for local file
	}

	@Override
	public int getMaxInputs() {
		return 0; // File is starting point (so it has no inputs)
	}

	@Override
	public String getOutputName(int index, String sriStreamID) {
		if (index != 0) {
			throw new IllegalArgumentException("Invalid index [" + index + "] specified.");
		}
		return filename;
	}

	public String getFilename() {
		return filename;
	}

	@Override
	protected String formCmdLine(AbstractNxmPlotWidget plotWidget, String streamID) {
		putOutputNameMapping(0, streamID, filename);
		return null; // return null since no command should be launched
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		// TODO Auto-generated method stub

	}

}

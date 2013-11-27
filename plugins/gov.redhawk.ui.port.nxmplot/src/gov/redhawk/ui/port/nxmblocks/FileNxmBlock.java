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
import nxm.sys.lib.Command;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.widgets.Composite;

/**
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.3
 */
public class FileNxmBlock extends AbstractNxmBlock<Command, String> {

	private final String filename;

	public FileNxmBlock(@NonNull String filename, @NonNull AbstractNxmPlotWidget plotWidget) {
		super(Command.class, "File", plotWidget);
		this.filename = filename;
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

	@Override
	public boolean hasControls() {
		return false; // no settings controls for file
	}

	@Override
	public Composite createControls(Composite parent, Object currentSettings, DataBindingContext context) {
		return null; // no settings controls for file
	}

	/** copy of current settings */
	@Override
	public String getSettings() {
		String clone = filename;
		if (clone == null) {
			clone = "";
		}
		return clone;
	}

	public void applySettings(String settings) {
		// none for file source
	}

	@Override
	protected String formCmdLine(AbstractNxmPlotWidget plotWidget, String streamID) {
		putOutputNameMapping(0, streamID, filename);
		return null; // return null since no command should be launched
	}

}

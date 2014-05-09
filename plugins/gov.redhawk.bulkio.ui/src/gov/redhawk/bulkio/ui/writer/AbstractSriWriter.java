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
package gov.redhawk.bulkio.ui.writer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import gov.redhawk.bulkio.ui.internal.SriWrapper;
import gov.redhawk.bulkio.util.BulkIOType;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

/**
 *
 */
public abstract class AbstractSriWriter {
	private String saveLocation;
	private Shell parent;
	private BulkIOType bulkioType;
	private List<String> filesWritten = new ArrayList<String>();

	public void performSave(Map<String, SriWrapper> streamMap, String saveLocation, BulkIOType bulkioType, Shell parent) throws IOException {
		this.setSaveLocation(saveLocation);
		this.setParent(parent);
		this.setBulkioType(bulkioType);
		save(streamMap);
	}

	public abstract void save(Map<String, SriWrapper> streamMap) throws IOException;

	public boolean checkForSimilarFiles(File metadataFile) {
		if (metadataFile.exists()) {
			MessageBox override = new MessageBox(parent, SWT.ICON_QUESTION | SWT.YES | SWT.NO);
			override.setMessage("This action may overwrite an existing file: " + metadataFile + ".  Do you want to proceed?");
			int result = override.open();
			if (result == SWT.NO) {
				return false;
			}
		}
		return true;
	}

	public String getSaveLocation() {
		return saveLocation;
	}

	public void setSaveLocation(String saveLocation) {
		this.saveLocation = saveLocation;
	}

	public Shell getParent() {
		return parent;
	}

	public void setParent(Shell parent) {
		this.parent = parent;
	}

	public BulkIOType getBulkioType() {
		return bulkioType;
	}

	public void setBulkioType(BulkIOType bulkioType) {
		this.bulkioType = bulkioType;
	}

	@NonNull
	public List<String> getFilesWritten() {
		return Collections.unmodifiableList(filesWritten);
	}

	protected boolean addFilesWritten(String filename) {
		return filesWritten.add(filename);
	}
}

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
package gov.redhawk.bulkio.ui.writer;

import gov.redhawk.bulkio.ui.internal.SriWrapper;
import gov.redhawk.bulkio.util.BulkIOType;
import gov.redhawk.bulkio.util.StreamSRIUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nxm.sys.lib.Table;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class SriWriter {

	private String saveLocation;
	private Shell parent;
	private BulkIOType bulkioType;
	private String fileName;

	public void performSave(Map<String, SriWrapper> streamMap, String saveLocation, BulkIOType bulkioType, Shell parent) throws IOException {
		this.saveLocation = saveLocation;
		this.parent = parent;
		this.bulkioType = bulkioType;
		save(streamMap);
	}

	private void save(Map<String, SriWrapper> streamMap) throws IOException {
		if (saveLocation == null) {
			return;
		}

		for (Iterator<Entry<String, SriWrapper>> i = streamMap.entrySet().iterator(); i.hasNext();) {
			Entry<String, SriWrapper> nextStream = i.next();
			
			String streamName = nextStream.getKey();
			streamName = streamName.replaceAll("\\s+", "_");
			fileName = saveLocation + "_" + streamName + ".sri";
			File metadataFile = new File(fileName);
			if (!checkForSimilarFiles(metadataFile)) {
				//Operation was cancelled to avoid overwriting existing files
				return;
			}
			FileWriter output = new FileWriter(metadataFile);
			BufferedWriter buffer = new BufferedWriter(output);
			PrintWriter out = new PrintWriter(buffer);

			//Build contents for .sri file
			Table rootTable = new Table();
			
			Table genInfoTable = (Table) rootTable.addTable("General Information");
			putGenInfo(nextStream.getValue(), genInfoTable);
			
			StreamSRIUtil.putSriInfo(nextStream.getValue().getSri(), rootTable);
			
			List<String> list = rootTable.toConfigFile();

			// Print to file
			try {
				for (String s : list) {
					out.println(s);
				}
			} finally {
				out.close();
			}
		}
	}

	private void putGenInfo(SriWrapper value, Table genInfoTable) {
		genInfoTable.put("BulkIOType", bulkioType);
		genInfoTable.put("Most recent Push SRI", value.getDate());
		genInfoTable.put("Most recent push packet", value.getPrecisionTime());
	}

	private boolean checkForSimilarFiles(File metadataFile) {
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
		return fileName;
	}
}

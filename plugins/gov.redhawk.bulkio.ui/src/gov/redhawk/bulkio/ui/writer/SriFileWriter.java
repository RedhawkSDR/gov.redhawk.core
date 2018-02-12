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
import gov.redhawk.bulkio.util.BulkIOFormatter;
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

public class SriFileWriter extends AbstractSriWriter {

	public void save(Map<String, SriWrapper> streamMap) throws IOException {
		if (getSaveLocation() == null) {
			return;
		}

		for (Iterator<Entry<String, SriWrapper>> i = streamMap.entrySet().iterator(); i.hasNext();) {
			Entry<String, SriWrapper> nextStream = i.next();

			String streamName = nextStream.getKey();
			streamName = streamName.replaceAll("\\s+", "_");
			String filename = getSaveLocation() + "_" + streamName + ".sri";
			File metadataFile = new File(filename);
			if (!checkForSimilarFiles(metadataFile)) {
				// Operation was cancelled to avoid overwriting existing files
				return;
			}

			// Build contents for .sri file
			Table rootTable = new Table();
			Table genInfoTable = (Table) rootTable.addTable("General Information");
			putGeneralInfo(nextStream.getValue(), genInfoTable);
			StreamSRIUtil.putSriInfo(nextStream.getValue().getSri(), rootTable);
			List<String> list = rootTable.toConfigFile();

			// Print/save to text file
			try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(metadataFile)))) {
				for (String s : list) {
					out.println(s);
				}
				addFilesWritten(filename);
			}
		}
	}

	private void putGeneralInfo(SriWrapper value, Table genInfoTable) {
		genInfoTable.put("BulkIOType", getBulkioType());
		genInfoTable.put("Most recent Push SRI", BulkIOFormatter.toISO8601(value.getPushSriDate()));
		genInfoTable.put("Most recent push packet", value.getPrecisionTime());
	}
}

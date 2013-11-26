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
import gov.redhawk.bulkio.util.StreamXMLSRIUtil;
import gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIDocumentRoot;
import gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIMetaDataFactory;
import gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIMetaDataPackage;
import gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIModel;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class SriXmlWriter {

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
			StreamSRIModel metaInfo = StreamSRIMetaDataFactory.eINSTANCE.createStreamSRIModel();
			StreamXMLSRIUtil.setStreamSRI(nextStream.getValue().getSri(), metaInfo);
			metaInfo.setBulkIOType(String.valueOf(bulkioType));

			String streamName = nextStream.getKey();
			streamName = streamName.replaceAll("\\s+", "_");
			fileName = saveLocation + "_" + streamName + ".xml";
			File metadataFile = new File(fileName);
			if (!checkForSimilarFiles(metadataFile)) {
				//Operation was cancelled to avoid overwriting existing files
				return;
			}
			ResourceSet resourceSet = new ResourceSetImpl();
			Resource resource = resourceSet.createResource(URI.createFileURI(metadataFile.getAbsolutePath()), StreamSRIMetaDataPackage.eCONTENT_TYPE);
			StreamSRIDocumentRoot root = StreamSRIMetaDataFactory.eINSTANCE.createStreamSRIDocumentRoot();
			root.setSri(metaInfo);
			resource.getContents().add(root);
			resource.save(null);
		}
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

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

public class SriXmlWriter extends AbstractSriWriter {

	public void save(Map<String, SriWrapper> streamMap) throws IOException {
		if (getSaveLocation() == null) {
			return;
		}

		for (Iterator<Entry<String, SriWrapper>> i = streamMap.entrySet().iterator(); i.hasNext();) {
			Entry<String, SriWrapper> nextStream = i.next();
			StreamSRIModel metaInfo = StreamSRIMetaDataFactory.eINSTANCE.createStreamSRIModel();
			StreamXMLSRIUtil.setStreamSRI(nextStream.getValue().getSri(), metaInfo);
			metaInfo.setBulkIOType(String.valueOf(getBulkioType()));

			String streamName = nextStream.getKey();
			streamName = streamName.replaceAll("\\s+", "_");
			setFileName(getSaveLocation() + "_" + streamName + ".xml");
			File metadataFile = new File(getFileName());
			if (!checkForSimilarFiles(metadataFile)) {
				// Operation was cancelled to avoid overwriting existing files
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
}

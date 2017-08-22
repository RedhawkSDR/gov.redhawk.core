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
package gov.redhawk.core.graphiti.ui.diagram.features;

import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.impl.AbstractCreateFeature;

import gov.redhawk.sca.util.PluginUtil;
import mil.jpeojtrs.sca.partitioning.ComponentFile;
import mil.jpeojtrs.sca.partitioning.ComponentFiles;
import mil.jpeojtrs.sca.sad.SadFactory;
import mil.jpeojtrs.sca.spd.SoftPkg;
import mil.jpeojtrs.sca.util.ScaUriHelpers;

public abstract class AbstractCreateInstantiationFeature extends AbstractCreateFeature {

	private SoftPkg spd = null;
	private String implId = null;

	public AbstractCreateInstantiationFeature(IFeatureProvider fp, SoftPkg spd, String implId) {
		super(fp, spd.getName(), spd.getDescription());
		this.spd = spd;
		this.implId = implId;
	}

	protected SoftPkg getSoftPkg() {
		return spd;
	}

	protected String getImplementationId() {
		return implId;
	}

	/**
	 * Finds a {@link ComponentFile} that matches the {@link SoftPkg} for this class, creating and adding one if
	 * necessary.
	 * @param componentFiles The list of component files
	 * @return
	 */
	protected ComponentFile getComponentFile(ComponentFiles componentFiles) {
		final String spdPath = ScaUriHelpers.getLocalFilePath(componentFiles, spd);
		for (final ComponentFile componentFile : componentFiles.getComponentFile()) {
			final SoftPkg fSpd = componentFile.getSoftPkg();
			if (fSpd != null && PluginUtil.equals(spdPath, componentFile.getLocalFile().getName())) {
				return componentFile;
			}
		}

		ComponentFile newFile = SadFactory.eINSTANCE.createComponentFile();
		componentFiles.getComponentFile().add(newFile);
		newFile.setSoftPkg(spd);
		return newFile;
	}
}

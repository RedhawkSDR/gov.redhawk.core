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
package gov.redhawk.core.resourcefactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;

import mil.jpeojtrs.sca.scd.ScdPackage;
import mil.jpeojtrs.sca.spd.Implementation;
import mil.jpeojtrs.sca.spd.SoftPkg;
import mil.jpeojtrs.sca.spd.SpdPackage;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;
import CF.ResourceFactoryOperations;

/**
 * @since 2.0
 */
public class ComponentDesc extends ResourceDesc {
	private static final EStructuralFeature [] PATH = new EStructuralFeature [] {
		SpdPackage.Literals.SOFT_PKG__DESCRIPTOR,
		SpdPackage.Literals.DESCRIPTOR__COMPONENT,
		ScdPackage.Literals.SOFTWARE_COMPONENT__COMPONENT_TYPE
	};

	private final String componentType;
	private final List<String> implementationIds;

	public ComponentDesc(SoftPkg spd, ResourceFactoryOperations factory) {
		super(spd.getId(), spd.eResource().getURI(), createProfile(spd), spd.getVersion(), factory);
		setName(spd.getName());
		setDescription(spd.getDescription());
		this.componentType = ScaEcoreUtils.getFeature(spd, PATH);
		this.implementationIds = new ArrayList<String>(spd.getImplementation().size());
		for (Implementation impl : spd.getImplementation()) {
			this.implementationIds.add(impl.getId());
		}
	}

	private static String createProfile(SoftPkg spd) {
		return "/components/" + spd.getName() + "/" + spd.eResource().getURI().lastSegment();
	}

	public String getComponentType() {
		return componentType;
	}

	public List<String> getImplementationIds() {
		return Collections.unmodifiableList(implementationIds);
	}

}

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

import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EStructuralFeature;

import mil.jpeojtrs.sca.scd.ComponentType;
import mil.jpeojtrs.sca.scd.ScdPackage;
import mil.jpeojtrs.sca.scd.SoftwareComponent;
import mil.jpeojtrs.sca.spd.Code;
import mil.jpeojtrs.sca.spd.CodeFileType;
import mil.jpeojtrs.sca.spd.Implementation;
import mil.jpeojtrs.sca.spd.SoftPkg;
import mil.jpeojtrs.sca.spd.SpdPackage;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;
import CF.ResourceFactoryOperations;

/**
 * @since 2.0
 */
public class ComponentDesc extends ResourceDesc {
	private static final EStructuralFeature[] PATH = new EStructuralFeature[] { SpdPackage.Literals.SOFT_PKG__DESCRIPTOR,
		SpdPackage.Literals.DESCRIPTOR__COMPONENT, ScdPackage.Literals.SOFTWARE_COMPONENT__COMPONENT_TYPE };

	private final String componentType;
	private final List<String> implementationIds;

	private SoftPkg spd;

	public ComponentDesc(SoftPkg spd, ResourceFactoryOperations factory) {
		super(spd.getId(), spd.eResource().getURI(), createProfile(spd), spd.getVersion(), factory);
		setSoftPkg(spd);
		setName(spd.getName());
		setDescription(spd.getDescription());
		this.componentType = ScaEcoreUtils.getFeature(spd, PATH);
		this.implementationIds = new ArrayList<String>(spd.getImplementation().size());
		for (Implementation impl : spd.getImplementation()) {
			Code code = impl.getCode();
			if (code != null) {
				CodeFileType type = code.getType();
				if (type == CodeFileType.EXECUTABLE) {
					this.implementationIds.add(impl.getId());
				}
			}
		}
	}

	private static String createProfile(SoftPkg spd) {
		Path path = new Path(spd.eResource().getURI().path());
		if (spd.getDescriptor() != null) {
			ComponentType type = SoftwareComponent.Util.getWellKnownComponentType(spd.getDescriptor().getComponent());
			if (type != null) {
				switch (type) {
				case DEVICE:
					return createPathFrom(spd, path, "devices", true);
				case RESOURCE:
					return createPathFrom(spd, path, "components", true);
				case DEVICE_MANAGER:
					return "/dev/mgr/" + spd.getName() + "/" + spd.eResource().getURI().lastSegment();
				case DOMAIN_MANAGER:
					return "/dom/mgr/" + spd.getName() + "/" + spd.eResource().getURI().lastSegment();
				case EVENT_SERVICE:
				case SERVICE:
				case NAMING_SERVICE:
					return createPathFrom(spd, path, "services", true);
				default:
					break;
				}
			}
		}
		String[] roots = new String[] { "components", "dom", "devices", "services", "dev", };
		String retVal = null;
		for (String s : roots) {
			retVal = createPathFrom(spd, path, s, false);
			if (retVal != null) {
				break;
			}
		}
		if (retVal == null) {
			retVal = spd.getName() + "/" + spd.eResource().getURI().lastSegment();
		}
		return retVal;
	}

	private static String createPathFrom(SoftPkg spd, Path path, String root, boolean force) {
		int i = 0;
		boolean found = false;
		for (String s : path.segments()) {
			if (s.equals(root)) {
				found = true;
				break;
			}
			i++;
		}
		if (found) {
			return path.removeFirstSegments(i).toString();
		} else if (force) {
			return "/" + root + "/" + spd.getName() + "/" + spd.eResource().getURI().lastSegment();
		} else {
			return null;
		}
	}

	public String getComponentType() {
		return componentType;
	}

	public List<String> getImplementationIds() {
		return Collections.unmodifiableList(implementationIds);
	}

	private void setSoftPkg(SoftPkg spd) {
		this.spd = spd;
	}

	/**
	 * @since 3.0
	 */
	public SoftPkg getSoftPkg() {
		return spd;
	}
}

/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.sca.dcd.diagram.edit.helpers;

import gov.redhawk.diagram.edit.helpers.ComponentPlacementEditHelperAdvice;
import mil.jpeojtrs.sca.dcd.DcdComponentInstantiation;
import mil.jpeojtrs.sca.dcd.DcdComponentPlacement;
import mil.jpeojtrs.sca.dcd.DcdFactory;
import mil.jpeojtrs.sca.dcd.DeviceConfiguration;
import mil.jpeojtrs.sca.partitioning.ComponentFile;
import mil.jpeojtrs.sca.partitioning.ComponentFiles;
import mil.jpeojtrs.sca.spd.SoftPkg;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;

/**
 * 
 */
public class DcdComponentPlacementEditHelperAdvice extends ComponentPlacementEditHelperAdvice<DcdComponentInstantiation, DcdComponentPlacement> {

	/**
	 * @since 2.0
	 */
	@Override
	public DcdComponentInstantiation createComponentInstantiation(final ConfigureRequest request, final SoftPkg spd) {
		final Object element = request.getParameter(ComponentPlacementEditHelperAdvice.CONFIGURE_COMPONENT_INSTANTIATION);
		final DcdComponentInstantiation ci;
		if (element instanceof DcdComponentInstantiation) {
			ci = (DcdComponentInstantiation) element;
		} else {
			ci = DcdFactory.eINSTANCE.createDcdComponentInstantiation();
		}

		final EObject eobj = EcoreUtil.getRootContainer(getObjectToConfigure(request));
		Assert.isTrue(eobj instanceof DeviceConfiguration);
		final DeviceConfiguration dcd = (DeviceConfiguration) eobj;

		String compName = getInstantiationName(request);
		if (compName == null) {
			compName = DeviceConfiguration.Util.createDeviceUsageName(dcd, spd.getName());
		}

		String id = getInstantiationID(request);
		if (id == null) {
			id = DeviceConfiguration.Util.createDeviceIdentifier(dcd, compName);
		}

		ci.setId(id);
		ci.setUsageName(compName);

		return ci;
	}

	@Override
	public ComponentFile createComponentFile() {
		return DcdFactory.eINSTANCE.createComponentFile();
	}

	@Override
	public DcdComponentPlacement getObjectToConfigure(final ConfigureRequest request) {
		return (DcdComponentPlacement) request.getElementToConfigure();
	}

	@Override
	public void setComponentFiles(final DcdComponentPlacement obj, final ComponentFiles files) {
		((DeviceConfiguration) obj.eContainer().eContainer()).setComponentFiles(files);

	}

	@Override
	public ComponentFiles getComponentFiles(final DcdComponentPlacement obj) {
		return ((DeviceConfiguration) obj.eContainer().eContainer()).getComponentFiles();
	}

}

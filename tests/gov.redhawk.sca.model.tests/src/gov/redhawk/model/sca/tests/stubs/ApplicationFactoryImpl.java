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
package gov.redhawk.model.sca.tests.stubs;

import java.util.ArrayList;
import java.util.List;

import mil.jpeojtrs.sca.sad.HostCollocation;
import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.sad.SadComponentPlacement;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.util.DceUuidUtil;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import CF.Application;
import CF.ApplicationFactoryPOA;
import CF.ApplicationHelper;
import CF.ApplicationPOATie;
import CF.DataType;
import CF.DeviceAssignmentType;
import CF.Resource;
import CF.ResourceHelper;
import CF.ResourcePOATie;
import CF.ApplicationFactoryPackage.CreateApplicationError;
import CF.ApplicationFactoryPackage.CreateApplicationRequestError;
import CF.ApplicationFactoryPackage.InvalidInitConfiguration;

/**
 * 
 */
public class ApplicationFactoryImpl extends ApplicationFactoryPOA {
	private final SoftwareAssembly sad;
	private DomainManagerImpl dmd;
	private int processId;

	public ApplicationFactoryImpl(final SoftwareAssembly sad, DomainManagerImpl dmd) {
		Assert.isNotNull(sad);
		this.sad = sad;
		this.dmd = dmd;
	}

	public String name() {
		return this.sad.getName();
	}

	public String identifier() {
		return this.sad.getId();
	}

	public String softwareProfile() {
		return this.sad.eResource().getURI().path();
	}

	public Application create(final String name, final DataType[] initConfiguration, final DeviceAssignmentType[] deviceAssignments) throws CreateApplicationError,
	        CreateApplicationRequestError,
	        InvalidInitConfiguration {	
		try {
			POA poa = dmd.getPoa();
			ApplicationImpl app = new ApplicationImpl(this.sad, name, DceUuidUtil.createDceUUID().toString(), dmd);			
			Application retVal = ApplicationHelper.narrow(poa.servant_to_reference(new ApplicationPOATie(app)));
			TreeIterator<EObject> contents = this.sad.getPartitioning().eAllContents();
			List<ResourceRef> components = new ArrayList<ResourceRef>();
			while (contents.hasNext()) {
				EObject obj = contents.next();
				if (obj instanceof HostCollocation || obj instanceof SadComponentPlacement) {
					continue;
				} else if (obj instanceof SadComponentInstantiation) {
					SadComponentInstantiation inst = (SadComponentInstantiation) obj;
					AbstractResourceImpl component = new AbstractResourceImpl(inst.getId(), inst.getUsageName(), dmd.getOrb(), dmd.getPoa());
					component.init(inst.getPlacement().getComponentFileRef().getFile().getSoftPkg());
					Resource resource = ResourceHelper.narrow(poa.servant_to_reference(new ResourcePOATie(component)));
					ResourceRef ref = new ResourceRef(resource, inst.getUsageName(), 
							inst.getId(), processId++, ScaTestConstaints.DCE_GPP_DEVICE, 
							inst.getPlacement().getComponentFileRef().getFile().getLocalFile().getName());				
					components.add(ref);
				} else {
					contents.prune();
				}
			}
			app.setComponents(components);
			dmd.registerApplication(retVal);
			return retVal;
		} catch (ServantNotActive e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); // CHECKSTYLE: DEBUG CODE
		} catch (WrongPolicy e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); // CHECKSTYLE: DEBUG CODE
		}
		throw new CreateApplicationError();
	}

}

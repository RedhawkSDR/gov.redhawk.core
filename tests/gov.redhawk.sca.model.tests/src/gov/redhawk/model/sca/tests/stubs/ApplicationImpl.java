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
package gov.redhawk.model.sca.tests.stubs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mil.jpeojtrs.sca.sad.SoftwareAssembly;

import org.eclipse.core.runtime.Assert;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import CF.ApplicationOperations;
import CF.ComponentEnumType;
import CF.ComponentType;
import CF.DeviceAssignmentType;
import CF.PortType;
import CF.Resource;
import CF.ApplicationPackage.ComponentElementType;
import CF.ApplicationPackage.ComponentProcessIdType;
import CF.LifeCyclePackage.ReleaseError;
import CF.ResourcePackage.StartError;
import CF.ResourcePackage.StopError;

/**
 * 
 */
public class ApplicationImpl extends AbstractResourceImpl implements
		ApplicationOperations {

	private SoftwareAssembly sad;
	private final List<ResourceRef> components = Collections
			.synchronizedList(new ArrayList<ResourceRef>());
	private Resource assemblyController;

	public ApplicationImpl(final SoftwareAssembly sad, final String name,
			final String identifier, DomainManagerImpl dmd) throws ServantNotActive,
			WrongPolicy {
		super(identifier, name, dmd.getOrb(), dmd.getPoa());
		Assert.isNotNull(sad);
		this.sad = sad;
	}
	
	public void setComponents(List<ResourceRef> newComponents) {
		synchronized (components) {
			components.clear();
			components.addAll(newComponents);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ComponentElementType[] componentNamingContexts() {
		final List<ComponentElementType> retVal = new ArrayList<ComponentElementType>();
		synchronized (this.components) {
			for (final ResourceRef stub : this.components) {
				final ComponentElementType type = new ComponentElementType();
				type.componentId = stub.getComponentId();
				type.elementId = stub.getElementId();
				retVal.add(type);
			}
		}
		return retVal.toArray(new ComponentElementType[retVal.size()]);
	}

	/**
	 * {@inheritDoc}
	 */
	public ComponentProcessIdType[] componentProcessIds() {
		final List<ComponentProcessIdType> retVal = new ArrayList<ComponentProcessIdType>();
		synchronized (this.components) {
			for (final ResourceRef stub : this.components) {
				final ComponentProcessIdType type = new ComponentProcessIdType();
				type.componentId = stub.getComponentId();
				type.processId = stub.getProcessId();
				retVal.add(type);
			}
		}
		return retVal.toArray(new ComponentProcessIdType[retVal.size()]);
	}

	/**
	 * {@inheritDoc}
	 */
	public DeviceAssignmentType[] componentDevices() {
		final List<DeviceAssignmentType> retVal = new ArrayList<DeviceAssignmentType>();
		synchronized (this.components) {
			for (final ResourceRef stub : this.components) {
				final DeviceAssignmentType type = new DeviceAssignmentType();
				type.componentId = stub.getComponentId();
				type.assignedDeviceId = stub.getDeviceId();
				retVal.add(type);
			}
		}
		return retVal.toArray(new DeviceAssignmentType[retVal.size()]);
	}

	/**
	 * {@inheritDoc}
	 */
	public ComponentElementType[] componentImplementations() {
		final List<ComponentElementType> retVal = new ArrayList<ComponentElementType>();
		synchronized (this.components) {
			for (final ResourceRef stub : this.components) {
				final ComponentElementType type = new ComponentElementType();
				type.componentId = stub.getComponentId();
				type.elementId = stub.getElementId();
				retVal.add(type);
			}
		}
		return retVal.toArray(new ComponentElementType[retVal.size()]);
	}

	/**
	 * {@inheritDoc}
	 */
	public String profile() {
		return (this.sad == null) ? null : this.sad.eResource().getURI().path();
	}

	/**
	 * {@inheritDoc}
	 */
	public String name() {
		return this.compName;
	}

	/**
	 * {@inheritDoc}
	 */
	public void start() throws StartError {
		super.start();
		if (this.assemblyController != null) {
			this.assemblyController.start();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void stop() throws StopError {
		super.stop();
		if (this.assemblyController != null) {
			this.assemblyController.stop();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void releaseObject() throws ReleaseError {
		super.releaseObject();
		synchronized (components) {
			for (ResourceRef ref : components) {
				ref.getResource().releaseObject();
			}
			this.components.clear();
		}
	}


	/**
	 * {@inheritDoc}
	 */
	public ComponentType[] registeredComponents() {
		final List<ComponentType> retVal = new ArrayList<ComponentType>();
		synchronized (this.components) {
			for (final ResourceRef stub : this.components) {
				final ComponentType type = new ComponentType();
				type.componentObject = stub.getResource();
				type.identifier = stub.getComponentId();
				type.softwareProfile = stub.getProfile();
				type.type = ComponentEnumType.APPLICATION_COMPONENT;
				type.providesPorts = new PortType[0]; // TODO
				retVal.add(type);
			}
		}
		return retVal.toArray(new ComponentType[retVal.size()]);
	}


}

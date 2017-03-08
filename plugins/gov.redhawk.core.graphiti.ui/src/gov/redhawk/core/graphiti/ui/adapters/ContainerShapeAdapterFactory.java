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
package gov.redhawk.core.graphiti.ui.adapters;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.ui.platform.GraphitiShapeEditPart;

import gov.redhawk.core.graphiti.ui.ext.RHContainerShape;
import gov.redhawk.core.graphiti.ui.util.DUtil;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaService;
import gov.redhawk.model.sca.ScaWaveform;
import mil.jpeojtrs.sca.dcd.DcdComponentInstantiation;
import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;
import mil.jpeojtrs.sca.sad.SadComponentInstantiation;

/**
 * Can adapt either:
 * <ul>
 * <li>{@link GraphitiShapeEditPart} (Graphiti UI part)</li>
 * <li>{@link RHContainerShape} (our Graphiti model object)</li>
 * </ul>
 * from the diagrams to the following types (and a few of their super types):
 * <ul>
 * <li>{@link ScaComponent}</li>
 * <li>{@link ScaDevice}</li>
 * <li>{@link ScaService}</li>
 * </ul>
 */
public class ContainerShapeAdapterFactory implements IAdapterFactory {

	private static final Class< ? >[] ADAPTER_TYPES = new Class< ? >[] { ScaComponent.class, ScaDevice.class, ScaService.class };

	@Override
	public < T > T getAdapter(Object adaptableObject, Class<T> adapterType) {
		// We convert the Graphiti UI part -> Graphiti PictogramElement, if not already done for us
		if (adaptableObject instanceof GraphitiShapeEditPart) {
			adaptableObject = ((GraphitiShapeEditPart) adaptableObject).getPictogramElement();
		}

		// PictogramElement must be an RHContainerShape
		if (!(adaptableObject instanceof RHContainerShape)) {
			return null;
		}
		RHContainerShape containerShape = (RHContainerShape) adaptableObject;

		// Get the diagram
		// Check to make sure the container is not null, possible in a multi-delete and probably other actions
		ContainerShape container = containerShape.getContainer();
		while (!(container instanceof Diagram)) {
			if (container == null) {
				return null;
			}
			container = container.getContainer();
		}
		Diagram diagram = (Diagram) container;

		// Try to convert the Graphiti PictogramElement to a SAD ComponentInstantiation
		ComponentInstantiation instantiation = DUtil.getBusinessObject(containerShape, ComponentInstantiation.class);
		return adaptCompInstToScaModel(diagram, instantiation, adapterType);
	}

	/**
	 * Adapts a {@link ComponentInstantiation} model object from a {@link Diagram} to an SCA model object.
	 * @param diagram
	 * @param instantiation
	 * @param adapterType
	 * @return
	 */
	/* package */ static < T > T adaptCompInstToScaModel(Diagram diagram, ComponentInstantiation instantiation, Class<T> adapterType) {
		if (instantiation instanceof SadComponentInstantiation) {
			// Get the ScaWaveform
			ScaWaveform waveform = DUtil.getBusinessObject(diagram, ScaWaveform.class);
			if (waveform == null) {
				return null;
			}

			// Try to find a matching component
			ScaComponent component = waveform.getScaComponent(instantiation.getId());
			if (adapterType.isInstance(component)) {
				return adapterType.cast(component);
			}
		} else if (instantiation instanceof DcdComponentInstantiation) {
			// Get the ScaDeviceManager
			ScaDeviceManager devMgr = DUtil.getBusinessObject(diagram, ScaDeviceManager.class);
			if (devMgr == null) {
				return null;
			}

			// Try to find a matching device or service
			ScaDevice< ? > device = devMgr.getDevice(instantiation.getId());
			if (adapterType.isInstance(device)) {
				return adapterType.cast(device);
			}
			ScaService service = devMgr.getScaService(instantiation.getUsageName());
			if (adapterType.isInstance(service)) {
				return adapterType.cast(service);
			}
		}

		return null;
	}

	@Override
	public Class< ? >[] getAdapterList() {
		return ADAPTER_TYPES;
	}

}

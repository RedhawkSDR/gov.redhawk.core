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
package gov.redhawk.core.graphiti.ui.properties;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.gef.editparts.AbstractEditPart;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.graphics.Image;

import gov.redhawk.core.graphiti.ui.util.DUtil;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;
import mil.jpeojtrs.sca.dcd.provider.DcdItemProviderAdapterFactory;
import mil.jpeojtrs.sca.partitioning.provider.PartitioningItemProviderAdapterFactory;
import mil.jpeojtrs.sca.sad.provider.SadItemProviderAdapterFactory;

public class PropertyPageLabelProvider extends AdapterFactoryLabelProvider {

	public PropertyPageLabelProvider() {
		super(new ComposedAdapterFactory(
			new AdapterFactory[] { new ScaItemProviderAdapterFactory(), new SadItemProviderAdapterFactory(), new DcdItemProviderAdapterFactory(), new PartitioningItemProviderAdapterFactory() }));
	}

	@Override
	public Image getImage(Object element) {
		element = unwrap(element);
		return super.getImage(element);
	}

	@Override
	public String getText(Object element) {
		element = unwrap(element);
		return super.getText(element);
	}

	private Object unwrap(Object element) {
		if (element instanceof IStructuredSelection) {
			element = ((IStructuredSelection) element).getFirstElement();
		}
		if (element instanceof AbstractEditPart) {
			element = ((AbstractEditPart) element).getModel();
		}
		if (element instanceof Diagram) {
			ScaWaveform waveform = DUtil.getBusinessObject((Diagram) element, ScaWaveform.class);
			if (waveform != null) {
				return waveform;
			}
			ScaDeviceManager deviceManager = DUtil.getBusinessObject((Diagram) element, ScaDeviceManager.class);
			if (deviceManager != null) {
				return deviceManager;
			}
		} else if (element instanceof PictogramElement) {
			element = ((PictogramElement) element).getLink().getBusinessObjects().get(0);
		}

		return element;
	}
}

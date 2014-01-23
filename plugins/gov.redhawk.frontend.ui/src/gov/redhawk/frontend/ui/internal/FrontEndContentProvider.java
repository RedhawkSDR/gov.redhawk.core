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
package gov.redhawk.frontend.ui.internal;

import gov.redhawk.frontend.TunerContainer;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.edit.utils.TunerPropertyWrapper;
import gov.redhawk.frontend.edit.utils.TunerUtils;
import gov.redhawk.frontend.provider.FrontendItemProviderAdapterFactory;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.sca.ui.ScaModelAdapterFactoryContentProvider;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;

import CF.DeviceHelper;

public class FrontEndContentProvider extends ScaModelAdapterFactoryContentProvider implements ICommonContentProvider {

	public FrontEndContentProvider() {
		super(FrontEndContentProvider.createAdapterFactory());
	}

	protected static AdapterFactory createAdapterFactory() {
		return new FrontendItemProviderAdapterFactory();
	}

	public static TunerStatus currentSelection;

	public static TunerStatus getCurrentSelection() {
		return currentSelection;
	}

	@Override
	public Object[] getElements(Object object) {
		//Create TunerWrapper object that returns an array of TunerProperty objects to pass to the label provider
		if (object instanceof TunerStatus) {
			TunerStatus tuner = (TunerStatus) object;

			currentSelection = tuner; // sets a static variable that is used by the allocate/deallocate handlers

			List<TunerPropertyWrapper> tunerProperties = new ArrayList<TunerPropertyWrapper>();
			for (ScaSimpleProperty simple : tuner.getSimples()) {
				TunerPropertyWrapper prop = new TunerPropertyWrapper(tuner, simple);
				tunerProperties.add(prop);
			}

			if (!tunerProperties.isEmpty()) {
				return tunerProperties.toArray();
			}

		}
		return super.getElements(object);
	}

	@Override
	public Object[] getChildren(Object object) {
		if (object instanceof ScaDevice< ? >) {
			ScaDevice< ? > device = (ScaDevice< ? >) object;
			if (device._is_a(DeviceHelper.id())) {
				return TunerUtils.INSTANCE.getTunerContainer(device);
			}
		}

		return super.getChildren(object);
	}

	@Override
	public boolean hasChildren(Object object) {
		if (object instanceof TunerContainer) {
			return true;
		}
		if (object instanceof TunerStatus) {
			if (((TunerStatus) object).getListenerAllocations().isEmpty())
				return false;
			return true;
		}
		return super.hasChildren(object);
	}

	@Override
	public void restoreState(IMemento aMemento) {

	}

	@Override
	public void saveState(IMemento aMemento) {

	}

	@Override
	public void init(ICommonContentExtensionSite aConfig) {

	}

	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);
	}
}

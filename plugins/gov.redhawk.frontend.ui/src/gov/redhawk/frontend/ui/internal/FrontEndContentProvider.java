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
import gov.redhawk.frontend.provider.FrontendItemProviderAdapterFactory;
import gov.redhawk.frontend.util.TunerUtils;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.sca.ui.ScaModelAdapterFactoryContentProvider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.ICommonContentProvider;

public class FrontEndContentProvider extends ScaModelAdapterFactoryContentProvider implements ICommonContentProvider {

	public FrontEndContentProvider() {
		super(FrontEndContentProvider.createAdapterFactory());
	}

	protected static AdapterFactory createAdapterFactory() {
		return new FrontendItemProviderAdapterFactory();
	}

	@Override
	public Object[] getChildren(Object object) {
		if (object instanceof ScaDevice< ? >) {
			ScaDevice< ? > device = (ScaDevice< ? >) object;
			TunerContainer tunerContainer = TunerUtils.INSTANCE.getTunerContainer(device);
			if (tunerContainer != null) {
				return new Object[] { tunerContainer };
			}
			return new Object[0];
		}

		return super.getChildren(object);
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

}

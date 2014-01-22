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

import gov.redhawk.frontend.edit.utils.TunerPropertyWrapper;
import gov.redhawk.frontend.provider.FrontendItemProviderAdapterFactory;
import gov.redhawk.sca.ui.ITooltipProvider;
import gov.redhawk.sca.ui.ScaModelAdapterFactoryLabelProvider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.navigator.ICommonContentExtensionSite;
import org.eclipse.ui.navigator.IDescriptionProvider;

public class FrontEndLabelProvider extends ScaModelAdapterFactoryLabelProvider implements IDescriptionProvider, ITooltipProvider{
	
	public FrontEndLabelProvider() {
		super(FrontEndLabelProvider.createAdapterFactory());
	}
	
	@Override
	public String getColumnText(Object object, int columnIndex) {

		if(object instanceof TunerPropertyWrapper) {
			TunerPropertyWrapper property = (TunerPropertyWrapper) object;
			switch (columnIndex) {
			case 0:
				return property.getID();
			case 1:
				return String.valueOf(property.getValue());
			}
		}
		return "";
	}

	private static AdapterFactory createAdapterFactory() {
		return new FrontendItemProviderAdapterFactory();
	}
	
	@Override
	public void init(ICommonContentExtensionSite aConfig) {
		
	}

	@Override
	public void restoreState(IMemento aMemento) {
		
	}

	@Override
	public void saveState(IMemento aMemento) {
		
	}

	@Override
	public Image getToolTipImage(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getToolTipText(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getToolTipBackgroundColor(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getToolTipForegroundColor(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Font getToolTipFont(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point getToolTipShift(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean useNativeToolTip(Object object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getToolTipTimeDisplayed(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getToolTipDisplayDelayTime(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getToolTipStyle(Object object) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getDescription(Object anElement) {
		return getText(anElement);
	}

	@Override
	public boolean isFireLabelUpdateNotifications() {
		return true;
	}
}

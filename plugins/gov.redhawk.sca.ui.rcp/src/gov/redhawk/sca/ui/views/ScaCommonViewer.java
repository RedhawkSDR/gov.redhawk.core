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
package gov.redhawk.sca.ui.views;

import gov.redhawk.sca.ui.ITooltipProvider;

import java.util.Set;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.internal.navigator.NavigatorDecoratingLabelProvider;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.ICommonLabelProvider;
import org.eclipse.ui.navigator.INavigatorContentExtension;

/**
 * Extends the Common Navigator framework to support extensiable tooltips
 * 
 * @see gov.redhawk.sca.ui.ITooltipProvider
 */
@SuppressWarnings("restriction")
public class ScaCommonViewer extends CommonViewer {

    private class DelegatingTooltipLabelProvider extends NavigatorDecoratingLabelProvider {

		public DelegatingTooltipLabelProvider(final ILabelProvider commonLabelProvider) {
			super(commonLabelProvider);
		}

		private ITooltipProvider getToolTipProvider(final Object object) {
			final Set< ? > labelProviders = getNavigatorContentService().findContentExtensionsByTriggerPoint(object);
			for (final Object obj : labelProviders) {
				if (obj instanceof INavigatorContentExtension) {
					final INavigatorContentExtension extension = (INavigatorContentExtension) obj;
					final ICommonLabelProvider lp = extension.getLabelProvider();
					if (lp instanceof ITooltipProvider) {
						return ((ITooltipProvider) lp);
					}
				}
			}
			return null;
		}

		@Override
		public boolean useNativeToolTip(final Object object) {
			final ITooltipProvider lp = getToolTipProvider(object);
			if (lp != null) {
				return lp.useNativeToolTip(object);
			}
			return super.useNativeToolTip(object);
		}

		@Override
		public Color getToolTipBackgroundColor(final Object object) {
			final ITooltipProvider lp = getToolTipProvider(object);
			if (lp != null) {
				return lp.getToolTipBackgroundColor(object);
			}
			return super.getToolTipBackgroundColor(object);
		}

		@Override
		public int getToolTipDisplayDelayTime(final Object object) {
			final ITooltipProvider lp = getToolTipProvider(object);
			if (lp != null) {
				return lp.getToolTipDisplayDelayTime(object);
			}
			return super.getToolTipDisplayDelayTime(object);
		}

		@Override
		public Font getToolTipFont(final Object object) {
			final ITooltipProvider lp = getToolTipProvider(object);
			if (lp != null) {
				return lp.getToolTipFont(object);
			}
			return super.getToolTipFont(object);
		}

		@Override
		public Color getToolTipForegroundColor(final Object object) {
			final ITooltipProvider lp = getToolTipProvider(object);
			if (lp != null) {
				return lp.getToolTipForegroundColor(object);
			}
			return super.getToolTipForegroundColor(object);
		}

		@Override
		public Image getToolTipImage(final Object object) {
			final ITooltipProvider lp = getToolTipProvider(object);
			if (lp != null) {
				return lp.getToolTipImage(object);
			}
			return super.getToolTipImage(object);
		}

		@Override
		public Point getToolTipShift(final Object object) {
			final ITooltipProvider lp = getToolTipProvider(object);
			if (lp != null) {
				return lp.getToolTipShift(object);
			}
			return super.getToolTipShift(object);
		}

		@Override
		public int getToolTipStyle(final Object object) {
			final ITooltipProvider lp = getToolTipProvider(object);
			if (lp != null) {
				return lp.getToolTipStyle(object);
			}
			return super.getToolTipStyle(object);
		}

		@Override
		public int getToolTipTimeDisplayed(final Object object) {
			final ITooltipProvider lp = getToolTipProvider(object);
			if (lp != null) {
				return lp.getToolTipTimeDisplayed(object);
			}
			return super.getToolTipTimeDisplayed(object);
		}

		@Override
		public String getToolTipText(final Object object) {
			final ITooltipProvider lp = getToolTipProvider(object);
			if (lp != null) {
				return lp.getToolTipText(object);
			}
			return super.getToolTipText(object);
		}
	}

	public ScaCommonViewer(final String aViewerId, final Composite aParent, final int aStyle) {
		super(aViewerId, aParent, aStyle);
	}

	@Override
	protected void init() {
		super.init();
		setLabelProvider(new DelegatingTooltipLabelProvider(getNavigatorContentService().createCommonLabelProvider()));
	}

}

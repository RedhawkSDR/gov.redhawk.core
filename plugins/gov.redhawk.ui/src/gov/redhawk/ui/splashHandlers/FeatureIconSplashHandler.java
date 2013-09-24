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
package gov.redhawk.ui.splashHandlers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.runtime.IBundleGroup;
import org.eclipse.core.runtime.IBundleGroupProvider;
import org.eclipse.core.runtime.IProduct;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.branding.IBundleGroupConstants;
import org.eclipse.ui.internal.splash.EclipseSplashHandler;

/**
 * @since 6.0
 *
 */
public class FeatureIconSplashHandler extends EclipseSplashHandler {

	public static final String PROP_FEATURE_ICON = "featureIconLocation";

	private static class FeatureIcon {
		private final Image img;
		private final String name;
		private final URL imageUrl;

		public FeatureIcon(final URL imageUrl, final String name) throws IOException {
			this.imageUrl = imageUrl;
			this.img = new Image(Display.getCurrent(), imageUrl.openStream());
			this.name = name;
		}

		@Override
		public int hashCode() {
			return this.imageUrl.getPath().hashCode();
		}

		@Override
		public boolean equals(final Object obj) {
			if (obj instanceof FeatureIcon) {
				return this.imageUrl.getPath().equals(((FeatureIcon) obj).imageUrl.getPath());
			} else {
				return super.equals(obj);
			}
		}
	}

	private final Set<FeatureIcon> featureIcons = new HashSet<FeatureIconSplashHandler.FeatureIcon>();

	/**
	 * 
	 */
	public FeatureIconSplashHandler() {
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.splash.AbstractSplashHandler#init(org.eclipse.swt.widgets.Shell)
	 */
	@Override
	public void init(final Shell splash) {
		// Store the shell
		super.init(splash);
		// Configure the shell layout
		//		configureUISplash();
		// Load all splash extensions
		loadFeatureIcons();
		// If no splash extensions were loaded abort the splash handler
		if (this.featureIcons.isEmpty()) {
			return;
		}
		final IProduct product = Platform.getProduct();
		String locationStr = "";
		if (product != null) {
			locationStr = product.getProperty(FeatureIconSplashHandler.PROP_FEATURE_ICON);
		}
		final Point location = StringConverter.asPoint(locationStr, new Point(10, 10));
		getContent().addPaintListener(new PaintListener() {

			@Override
			public void paintControl(final PaintEvent e) {
				int xposition = location.x;
				final int yposition = location.y;
				for (final FeatureIcon icon : FeatureIconSplashHandler.this.featureIcons) {
					if (splash.getSize().x < xposition) {
						break;
					}
					e.gc.drawImage(icon.img, xposition, yposition);
					xposition = icon.img.getBounds().width + 5 + xposition;
				}

			}
		});
		// Create UI
		//		createUI();
		// Configure the image panel bounds
		//		configureUICompositeIconPanelBounds();

		// Enter event loop and prevent the RCP application from 
		// loading until all work is done
		//		doEventLoop();
	}

	private void loadFeatureIcons() {
		final IBundleGroupProvider[] groupProviders = Platform.getBundleGroupProviders();
		for (final IBundleGroupProvider provider : groupProviders) {
			for (final IBundleGroup group : provider.getBundleGroups()) {
				String name = group.getName();
				if (name == null) {
					name = group.getIdentifier();
				}
				final String imageUrlStr = group.getProperty(IBundleGroupConstants.FEATURE_IMAGE);
				URL imageUrl = null;
				if (imageUrlStr != null) {
					try {
						imageUrl = new URL(imageUrlStr);
					} catch (final MalformedURLException e) {
						// PASS
					}
				}
				if (name != null && imageUrl != null) {
					if (!this.featureIcons.contains(imageUrl)) {
						try {
							this.featureIcons.add(new FeatureIcon(imageUrl, name));
						} catch (final IOException e) {
							// PASS
						}
					}
				}
			}
		}

	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.splash.AbstractSplashHandler#dispose()
	 */
	@Override
	public void dispose() {
		super.dispose();
		// Check to see if any images were defined
		if ((this.featureIcons == null) || this.featureIcons.isEmpty()) {
			return;
		}
		// Dispose of all the images
		final Iterator<FeatureIcon> iterator = this.featureIcons.iterator();
		while (iterator.hasNext()) {
			final FeatureIcon featureIcon = iterator.next();
			featureIcon.img.dispose();
		}
		this.featureIcons.clear();
	}
}

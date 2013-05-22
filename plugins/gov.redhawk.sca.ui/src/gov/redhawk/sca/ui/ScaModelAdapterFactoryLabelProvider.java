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
package gov.redhawk.sca.ui;

import gov.redhawk.sca.ScaPlugin;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.ui.provider.TransactionalAdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.IFontProvider;
import org.eclipse.jface.viewers.ITableColorProvider;
import org.eclipse.jface.viewers.ITableFontProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Color;

/**
 * @since 6.0
 *
 */
public class ScaModelAdapterFactoryLabelProvider extends TransactionalAdapterFactoryLabelProvider implements IColorProvider, ITableColorProvider,
        IFontProvider, ITableFontProvider {

	/**
	 * @since 7.0
	 */
	public ScaModelAdapterFactoryLabelProvider(final AdapterFactory adapterFactory) {
		super(TransactionalEditingDomain.Registry.INSTANCE.getEditingDomain(ScaPlugin.EDITING_DOMAIN_ID), adapterFactory);
	}

	/**
	 * @since 9.0
	 */
	public ScaModelAdapterFactoryLabelProvider(final AdapterFactory adapterFactory, final Viewer viewer) {
		this(adapterFactory);
		if (viewer != null) {
			setDefaultForeground(viewer.getControl().getForeground());
			setDefaultBackground(viewer.getControl().getBackground());
		}
	}

	@Override
	public Color getForeground(final Object object) {
		return run(new RunnableWithResult.Impl<Color>() {
			public void run() {
				setResult(ScaModelAdapterFactoryLabelProvider.super.getForeground(object));
			}
		});
	}

	@Override
	public Color getForeground(final Object object, final int columnIndex) {
		return run(new RunnableWithResult.Impl<Color>() {
			public void run() {
				setResult(ScaModelAdapterFactoryLabelProvider.super.getForeground(object, columnIndex));
			}
		});
	}

}

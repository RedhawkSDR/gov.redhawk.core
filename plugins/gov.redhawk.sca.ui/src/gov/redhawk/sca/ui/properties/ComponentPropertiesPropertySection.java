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
package gov.redhawk.sca.ui.properties;

import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaPropertyContainer;
import gov.redhawk.sca.ui.ScaComponentFactory;
import mil.jpeojtrs.sca.prf.AbstractProperty;
import mil.jpeojtrs.sca.prf.util.PropertiesUtil;

import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * @since 8.0
 */
public class ComponentPropertiesPropertySection extends AbstractPropertySection {
	/**
	 * The Property Sheet Page.
	 */
	private final ScaPropertiesAdapterFactory adapterFactory;

	private Object input;
	private TreeViewer viewer;

	public ComponentPropertiesPropertySection() {
		this.adapterFactory = new ScaPropertiesAdapterFactory();
	}

	@Override
	public void dispose() {
		if (this.adapterFactory != null) {
			this.adapterFactory.dispose();
		}
		super.dispose();
	}

	@Override
	public void createControls(final Composite parent, final TabbedPropertySheetPage atabbedPropertySheetPage) {
		super.createControls(parent, atabbedPropertySheetPage);
		this.viewer = ScaComponentFactory.createPropertyTable(getWidgetFactory(), parent, SWT.SINGLE, this.adapterFactory);
		this.viewer.addFilter(new ViewerFilter() {

			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				if (element instanceof ScaAbstractProperty< ? >) {
					if (parentElement instanceof ScaAbstractProperty< ? >) {
						// Props that are children of props are members, and hence are always shown
						return true;
					}
					AbstractProperty propDef = ((ScaAbstractProperty< ? >) element).getDefinition();
					return (propDef == null) || PropertiesUtil.canConfigureOrQuery(propDef);
				}
				return false;
			}
		});
	}

	@Override
	public void setInput(final IWorkbenchPart part, final ISelection selection) {
		super.setInput(part, selection);
		final Object oldInput = this.input;
		Object newInput = null;
		if (selection instanceof IStructuredSelection) {
			final IStructuredSelection ss = (IStructuredSelection) selection;
			final Object obj = ss.getFirstElement();
			final Object adapter = Platform.getAdapterManager().getAdapter(obj, ScaPropertyContainer.class);
			if (adapter instanceof ScaPropertyContainer< ? , ? >) {
				final ScaPropertyContainer< ? , ? > comps = (ScaPropertyContainer< ? , ? >) adapter;
				newInput = comps;
			}
		}

		if (oldInput != newInput) {
			this.input = newInput;
			if (this.viewer != null) {
				this.viewer.setInput(newInput);
			}
		}
	}

	@Override
	public void refresh() {
		if (this.viewer != null && !this.viewer.isCellEditorActive()) {
			this.viewer.refresh();
		}
	}

	@Override
	public boolean shouldUseExtraSpace() {
		return true;
	}
}

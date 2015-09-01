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

package gov.redhawk.diagram.sheet.properties;

import gov.redhawk.sca.ui.ScaComponentFactory;
import gov.redhawk.sca.ui.properties.ScaPropertiesAdapterFactory;
import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.properties.sections.AbstractModelerPropertySection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
 * @since 5.1
 */
public class ComponentPropertiesPropertySection extends AbstractModelerPropertySection implements IEditingDomainProvider {

	private AdapterFactory adapterFactory;
	private final ComponentInstantiationPropertyViewerAdapter adapter = new ComponentInstantiationPropertyViewerAdapter(this);

	public ComponentPropertiesPropertySection() {
		
	}
	
	/**
	 * @since 6.0
	 */
	protected AdapterFactory createAdapterFactory() {
		return new ScaPropertiesAdapterFactory();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void createControls(final Composite parent, final TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		this.adapterFactory = createAdapterFactory();
		
		final TreeViewer viewer = createTreeViewer(parent);
		this.adapter.setViewer(viewer);
	}
	
	/**
	 * @since 6.0
	 */
	public TreeViewer getViewer() {
		return adapter.getViewer();
	}
	
	/**
	 * @since 6.0
	 */
	public AdapterFactory getAdapterFactory() {
		return adapterFactory;
	}

	/**
	 * @since 6.0
	 */
	protected TreeViewer createTreeViewer(final Composite parent) {
		return ScaComponentFactory.createPropertyTable(getWidgetFactory(), parent, SWT.SINGLE, this.adapterFactory);
	}

	@Override
	public TransactionalEditingDomain getEditingDomain() {
		return super.getEditingDomain();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void setInput(final IWorkbenchPart part, final ISelection selection) {
		super.setInput(part, selection);
		final EObject eObj = getEObject();
		if (eObj instanceof ComponentInstantiation) {
			final ComponentInstantiation newInput = (ComponentInstantiation) eObj;
			this.adapter.setInput(newInput);
		} else {
			this.adapter.setInput(null);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void dispose() {
		this.adapter.dispose();
		if (this.adapterFactory != null) {
			if (adapterFactory instanceof IDisposable) {
				((IDisposable) this.adapterFactory).dispose();
			}
			this.adapterFactory = null;

		}
		this.input = null;
		super.dispose();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final boolean shouldUseExtraSpace() {
		return true;
	}

}

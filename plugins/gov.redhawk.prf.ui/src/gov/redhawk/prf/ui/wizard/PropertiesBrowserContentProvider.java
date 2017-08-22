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
package gov.redhawk.prf.ui.wizard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.progress.DeferredTreeContentManager;
import org.eclipse.ui.progress.IDeferredWorkbenchAdapter;
import org.eclipse.ui.progress.PendingUpdateAdapter;

import gov.redhawk.sca.properties.Category;
import gov.redhawk.sca.properties.IPropertiesProvider;
import mil.jpeojtrs.sca.prf.AbstractProperty;
import mil.jpeojtrs.sca.prf.Properties;
import mil.jpeojtrs.sca.prf.Struct;
import mil.jpeojtrs.sca.prf.StructSequence;
import mil.jpeojtrs.sca.util.collections.FeatureMapList;

/**
 * @since 4.0
 */
public class PropertiesBrowserContentProvider extends AdapterFactoryContentProvider {

	private DeferredTreeContentManager deferredContentManager;
	private final Map<Object, List<IPropertiesProvider>> map = new HashMap<Object, List<IPropertiesProvider>>();

	/**
	 * @param adapterFactory
	 */
	public PropertiesBrowserContentProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof List) {
			return ((List< ? >) parentElement).toArray();
		} else if (parentElement instanceof IPropertiesProvider) {
			return ((IPropertiesProvider) parentElement).getCategories().toArray();
		} else if (parentElement instanceof Category) {
			List<Object> children = new ArrayList<Object>();
			children.addAll(((Category) parentElement).getCategories());
			for (Properties props : ((Category) parentElement).getProperties()) {
				children.addAll(new FeatureMapList<AbstractProperty>(props.getProperties(), AbstractProperty.class));
			}
			return children.toArray();
		} else if (parentElement instanceof Struct) {
			Struct struct = (Struct) parentElement;
			List<Object> children = new ArrayList<Object>();
			children.addAll(struct.getSimple());
			children.addAll(struct.getSimpleSequence());
			return children.toArray();
		} else if (parentElement instanceof StructSequence) {
			StructSequence sequence = (StructSequence) parentElement;
			return Collections.singletonList(sequence.getStruct()).toArray();
		}
		return super.getChildren(parentElement);
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return element instanceof IPropertiesProvider || element instanceof Category || element instanceof Struct || element instanceof StructSequence;
	}

	@Override
	public void dispose() {
		//Nothing to do
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		super.inputChanged(viewer, oldInput, newInput);
		this.deferredContentManager = createDeferredContentManager((AbstractTreeViewer) viewer);
	}

	@Override
	public Object[] getElements(Object inputElement) {
		final List<IPropertiesProvider> providers = this.map.get(inputElement);
		if (providers != null) {
			return getChildren(providers);
		}
		return this.deferredContentManager.getChildren(inputElement);
	}

	/**
	 * @param viewer
	 */
	private DeferredTreeContentManager createDeferredContentManager(final AbstractTreeViewer viewer) {

		final DeferredTreeContentManager contentManager = new DeferredTreeContentManager(viewer) {
			@Override
			protected IDeferredWorkbenchAdapter getAdapter(final Object element) {
				return new PropertiesBrowserDeferredWorkbenchAdapter(map);
			}

			@Override
			protected PendingUpdateAdapter createPendingUpdateAdapter() {
				return new PropertiesPendingUpdateAdapter();
			}
		};
		return contentManager;
	}
}

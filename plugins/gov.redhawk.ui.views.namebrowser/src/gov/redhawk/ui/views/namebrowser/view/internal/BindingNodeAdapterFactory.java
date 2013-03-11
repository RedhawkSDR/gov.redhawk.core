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
package gov.redhawk.ui.views.namebrowser.view.internal;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.ui.progress.IDeferredWorkbenchAdapter;

/**
 * 
 */
public class BindingNodeAdapterFactory implements IAdapterFactory {
	private static final Class< ? >[] LIST = new Class[] { IWorkbenchAdapter.class, IDeferredWorkbenchAdapter.class };

	public static final BindingNodeDefferedWorkbenchAdapter ADAPTER = new BindingNodeDefferedWorkbenchAdapter();

	/**
	 * {@inheritDoc}
	 */
	public Object getAdapter(final Object adaptableObject, final Class adapterType) {
		if (adapterType == IWorkbenchAdapter.class) {
			return ADAPTER;
		}
		if (adapterType == IDeferredWorkbenchAdapter.class) {
			return ADAPTER;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public Class< ? >[] getAdapterList() {
		return LIST;
	}

}

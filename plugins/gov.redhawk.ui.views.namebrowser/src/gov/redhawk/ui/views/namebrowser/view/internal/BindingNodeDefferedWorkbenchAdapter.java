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

import gov.redhawk.ui.views.namebrowser.NameBrowserPlugin;
import gov.redhawk.ui.views.namebrowser.view.BindingNode;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.progress.IDeferredWorkbenchAdapter;
import org.eclipse.ui.progress.IElementCollector;

/**
 * 
 */
public class BindingNodeDefferedWorkbenchAdapter implements IDeferredWorkbenchAdapter {

	/**
	 * {@inheritDoc}
	 */
	public Object[] getChildren(final Object o) {
		return ((BindingNode) o).getContents();
	}

	/**
	 * {@inheritDoc}
	 */
	public ImageDescriptor getImageDescriptor(final Object object) {
		final BindingNode bind = (BindingNode) object;
		switch (bind.getType()) {
		case CONTEXT:
			return NameBrowserPlugin.getDefault().getImageRegistry().getDescriptor(NameBrowserPlugin.CONTEXT);
		case OBJECT:
			return NameBrowserPlugin.getDefault().getImageRegistry().getDescriptor(NameBrowserPlugin.OBJECT);
		case ROOT:
			return NameBrowserPlugin.getDefault().getImageRegistry().getDescriptor(NameBrowserPlugin.NAMESERVER);
		default:
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public String getLabel(final Object element) {
		String text = "";
		if (element instanceof BindingNode) {
			final BindingNode n = (BindingNode) element;
			if (n.getBinding() == null) {
				return n.getHost().substring(n.getHost().indexOf("::") + 2);
			} else {
				text = n.getBinding().binding_name[0].id;
			}
		}
		return text;
	}

	/**
	 * {@inheritDoc}
	 */
	public Object getParent(final Object o) {
		return ((BindingNode) o).getParent();
	}

	/**
	 * {@inheritDoc}
	 */
	public void fetchDeferredChildren(final Object object, final IElementCollector collector, final IProgressMonitor monitor) {
		final BindingNode node = (BindingNode) object;
		try {
			collector.add(node.fetchContents(), monitor);
		} finally {
			collector.done();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isContainer() {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	public ISchedulingRule getRule(final Object object) {
		// TODO Auto-generated method stub
		return null;
	}

}

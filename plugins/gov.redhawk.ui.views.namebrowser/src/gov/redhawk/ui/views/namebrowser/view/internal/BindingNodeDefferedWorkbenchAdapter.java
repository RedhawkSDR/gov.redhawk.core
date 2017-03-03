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
package gov.redhawk.ui.views.namebrowser.view.internal;

import gov.redhawk.ui.views.namebrowser.NameBrowserPlugin;
import gov.redhawk.ui.views.namebrowser.view.BindingNode;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.progress.IDeferredWorkbenchAdapter;
import org.eclipse.ui.progress.IElementCollector;
import org.omg.CosNaming.NamingContextPackage.InvalidName;

public class BindingNodeDefferedWorkbenchAdapter implements IDeferredWorkbenchAdapter {

	@Override
	public Object[] getChildren(final Object o) {
		return ((BindingNode) o).getContents();
	}

	@Override
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

	@Override
	public String getLabel(final Object element) {
		if (element instanceof BindingNode) {
			final BindingNode n = (BindingNode) element;
			if (n.getBinding() == null) {
				return n.getHost().substring(n.getHost().indexOf("::") + 2);
			} else {
				try {
					return n.getNamingContext().to_string(n.getBinding().binding_name);
				} catch (InvalidName e) {
					return "(invalid name)";
				}
			}
		}
		return "";
	}

	@Override
	public Object getParent(final Object o) {
		return ((BindingNode) o).getParent();
	}

	@Override
	public void fetchDeferredChildren(final Object object, final IElementCollector collector, final IProgressMonitor monitor) {
		final BindingNode node = (BindingNode) object;
		try {
			if (Display.getCurrent() != null) {
				Display.getCurrent().asyncExec(new Runnable() {

					@Override
					public void run() {
						collector.add(node.fetchContents(), monitor);
					}

				});
			} else {
				if (SWT.getPlatform().startsWith("rap")) {
					Display display = NameBrowserPlugin.getDefault().getDisplay();
					if (display != null) {
						display.asyncExec(new Runnable() {

							@Override
							public void run() {
								collector.add(node.fetchContents(), monitor);
							}

						});
					} else {
						NameBrowserPlugin.getDefault().getLog().log(new Status(
								Status.ERROR, NameBrowserPlugin.PLUGIN_ID, "Unable to retrieve defrrred children from non-UI thread"));
					}
				} else {
					collector.add(node.fetchContents(), monitor);
				}
			}
		} finally {
			if (Display.getCurrent() != null) {
				Display.getCurrent().asyncExec(new Runnable() {

					@Override
					public void run() {
						collector.done();
					}

				});
			} else {
				if (SWT.getPlatform().startsWith("rap")) {
					Display display = NameBrowserPlugin.getDefault().getDisplay();
					if (display != null) {
						display.asyncExec(new Runnable() {

							@Override
							public void run() {
								collector.add(node.fetchContents(), monitor);
							}

						});
					} else {
						NameBrowserPlugin.getDefault().getLog().log(new Status(
								Status.ERROR, NameBrowserPlugin.PLUGIN_ID, "Unable to retrieve defrrred children from non-UI thread"));
					}
				} else {
					collector.done();
				}
			}
		}
	}

	@Override
	public boolean isContainer() {
		return true;
	}

	@Override
	public ISchedulingRule getRule(final Object object) {
		return null;
	}

}

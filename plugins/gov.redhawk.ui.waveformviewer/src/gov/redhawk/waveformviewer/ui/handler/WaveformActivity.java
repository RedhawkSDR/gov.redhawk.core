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
package gov.redhawk.waveformviewer.ui.handler;

import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.sca.ScaPlugin;
import gov.redhawk.waveformviewer.ui.ApplicationActionListener;

import java.util.ArrayList;

import mil.jpeojtrs.sca.sad.SoftwareAssembly;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

public abstract class WaveformActivity extends AbstractHandler implements IHandler {
	private int action = ApplicationActionListener.INSTALL;
	private ApplicationActionListener appListener;
	private IWorkbenchPage[] pages;

	@Override
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		final ISelection selection = HandlerUtil.getCurrentSelection(event);
		final ArrayList<Object> ret = new ArrayList<Object>();

		// Try to find the view that triggered this event
		if ((event.getTrigger() != null) && (event.getTrigger().getClass() == Event.class)) {
			final IWorkbenchPart part = HandlerUtil.getActivePart(event);
			if (part instanceof ApplicationActionListener) {
				this.appListener = (ApplicationActionListener) part;
			}
			this.pages = HandlerUtil.getActiveWorkbenchWindow(event).getPages();
		}

		// process each selection
		if (selection instanceof IStructuredSelection) {
			final IStructuredSelection ss = (IStructuredSelection) selection;
			for (final Object obj : ss.toList()) {
				processSelection(obj, ret);
			}
		}

		// If the triggering view was actually a listener, tell it we did
		// something wave related
		if (this.appListener != null) {
			this.appListener.actionPerformed(this.action, ret);
		}

		return ret;
	}

	protected void setAction(final int action) {
		this.action = action;
	}

	/**
	 * @return the pages
	 */
	public IWorkbenchPage[] getPages() {
		return this.pages;
	}

	/**
	 * @param appListener the appListener to set
	 */
	public void setAppListener(final ApplicationActionListener appListener) {
		this.appListener = appListener;
	}

	/**
	 * This method finds the domain manager for the waveform and processes the
	 * action for it
	 * 
	 * @param selection the selected waveform or application
	 * @param ret the result list to add any returned object to
	 */
	protected void processSelection(final Object selection, final ArrayList<Object> ret) {
		Display display = null;
		if (pages.length > 0) {
			display = pages[0].getWorkbenchWindow().getShell().getDisplay();
		}
		final ScaDomainManagerRegistry registry = ScaPlugin.getDefault().getDomainManagerRegistry(display);
		final EList<ScaDomainManager> doms = registry.getDomains();

		if (selection instanceof SoftwareAssembly) {
			final SoftwareAssembly wave = (SoftwareAssembly) selection;
			//			final IPreferenceStore preferenceStore = new ScopedPreferenceStore(new ConfigurationScope(), RedhawkIdePreferenceConstants.RH_IDE_PREFERENCE_NODE);
			final String domain = ""; //preferenceStore.getString(RedhawkIdePreferenceConstants.RH_IDE_CORBA_DOMAIN_PREFERENCE);

			// Find the correct domain manager
			for (final ScaDomainManager dom : doms) {
				if (!domain.equals(dom.identifier())) {
					continue;
				}

				// Process the Software Assembly
				final Object o = processWave(dom, wave);
				if (o != null) {
					ret.add(o);
				}
				break;
			}
		} else if (selection instanceof ScaWaveform) {
			final ScaWaveform app = (ScaWaveform) selection;

			// Find the correct domain manager
			for (final ScaDomainManager dom : doms) {
				if (!app.name().startsWith(dom.identifier())) {
					continue;
				}

				// Process the Application
				final Object o = processWaveform(dom, app);
				if (o != null) {
					ret.add(o);
				}
				break;
			}
		}
	}

	protected Object processWave(final ScaDomainManager dom, final SoftwareAssembly wave) {
		return null;
	}

	protected Object processWaveform(final ScaDomainManager dom, final ScaWaveform app) {
		return null;
	}

}

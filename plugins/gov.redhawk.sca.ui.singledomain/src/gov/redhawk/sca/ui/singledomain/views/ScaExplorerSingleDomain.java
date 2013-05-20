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
package gov.redhawk.sca.ui.singledomain.views;

import gov.redhawk.model.sca.DomainConnectionException;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.sca.ScaPlugin;
import gov.redhawk.sca.ui.ScaUiPlugin;
import gov.redhawk.sca.ui.singledomain.CustomControlItem;
import gov.redhawk.sca.ui.singledomain.CustomMouseEvent;
import gov.redhawk.sca.ui.singledomain.CustomMouseTrackListener;
import gov.redhawk.sca.ui.singledomain.ScaSingleDomainPlugin;
import gov.redhawk.sca.ui.singledomain.ScaSingleDomainPreferenceConstants;
import gov.redhawk.sca.ui.singledomain.TrackableLabel;
import gov.redhawk.sca.ui.singledomain.dialogs.DialogCloseJob;
import gov.redhawk.sca.ui.singledomain.dialogs.DomainsDialog;
import gov.redhawk.sca.ui.views.ScaExplorer;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.progress.UIJob;

public class ScaExplorerSingleDomain extends ScaExplorer {

	protected static final long DIALOG_HIDE_WAIT_MS = 200;

	private DomainsDialog dialog;

	private CommonViewer viewer;

	private static IPreferenceStore prefs;

	private AdapterImpl domainChangeAdapter = new AdapterImpl() {
		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getNotifier() instanceof ScaDomainManagerRegistry) {
				switch (msg.getFeatureID(ScaDomainManagerRegistry.class)) {
				case ScaPackage.SCA_DOMAIN_MANAGER_REGISTRY__DOMAINS:
					ScaDomainManagerRegistry registry = (ScaDomainManagerRegistry) msg.getNotifier();
					switch (msg.getEventType()) {
					case Notification.REMOVE:
						ScaDomainManager domainRemoved = (ScaDomainManager) msg.getOldValue();
						if (domainRemoved.getName().equals(getActiveDomainName())) {
							if (registry.getDomains().size() > 0) {
								setActiveDomain(registry.getDomains().get(0).getName());
							} else {
								setActiveDomain("");
								if (dialog != null && dialog.getShell().isVisible()) {
									dialog.checkHyperlinkEnabled(null);
								}
							}
						} else {
							if (registry.getDomains().size() == 0) {
								setActiveDomain("");
								if (dialog != null && dialog.getShell().isVisible()) {
									dialog.checkHyperlinkEnabled(null);
								}
							} 
						}
						break;
					case Notification.ADD:
						if (prefs.getBoolean(ScaSingleDomainPreferenceConstants.SCA_SET_NEW_DOMAIN_ACTIVE)) {
							ScaDomainManager domainAdded = (ScaDomainManager) msg.getNewValue();
							setActiveDomain(domainAdded.getName());
							if (dialog != null && !dialog.getShell().isDisposed() && dialog.getShell().isVisible()) {
								dialog.checkHyperlinkEnabled(domainAdded);
							}

						}
						break;
					default:
						break;
					}
					break;
				default:
					break;
				}
			}
		}
	};

	public static String getActiveDomainName() {
		return prefs.getString(ScaSingleDomainPreferenceConstants.SCA_ACTIVE_DOMAIN);
	}

	public static ScaDomainManager getActiveDomain() {
		return ScaPlugin.getDefault().getDomainManagerRegistry().findDomain(getActiveDomainName());
	}

	private IPropertyChangeListener activeDomainListener = new IPropertyChangeListener() {

		public void propertyChange(PropertyChangeEvent event) {
			if (ScaSingleDomainPreferenceConstants.SCA_ACTIVE_DOMAIN.equals(event.getProperty())) {
				String oldDomain = (String) event.getOldValue();
				if (!oldDomain.equals("") && prefs.getBoolean(ScaSingleDomainPreferenceConstants.SCA_DISCONNECT_INACTIVE)) {
					ScaDomainManager domain = ScaPlugin.getDefault().getDomainManagerRegistry().findDomain(oldDomain);
					if (domain != null) {
						domain.disconnect();
					}
				}
				final ScaDomainManager activeDomain = getActiveDomain();
				String newDomain = (String) event.getNewValue();
				if (!newDomain.equals("")) {
					ScaDomainManager domain = ScaPlugin.getDefault().getDomainManagerRegistry().findDomain(newDomain);
					if (domain != null && domain.isAutoConnect()) {
						try {
							domain.connect(new NullProgressMonitor(), RefreshDepth.SELF);
						} catch (DomainConnectionException e) {
							ScaSingleDomainPlugin.logError("Unable to connect to domain" + domain.getName(), e);
						}
					}
				}
				if (activeDomain != null) {
					viewer.getControl().getDisplay().asyncExec(new Runnable() {

						public void run() {
							fillToolBar(activeDomain.getName().trim().equals("") ? "NO ACTIVE DOMAIN" : activeDomain.getName());
							viewer.setInput(activeDomain);
							getViewSite().getActionBars().updateActionBars();
						}

					});
				} else {
					viewer.getControl().getDisplay().asyncExec(new Runnable() {

						public void run() {
							fillToolBar("NO ACTIVE DOMAIN");
							getViewSite().getActionBars().updateActionBars();
							viewer.setInput(activeDomain);
						}

					});
				}
			}
			if (dialog != null && dialog.getShell() != null && !dialog.getShell().isDisposed() && dialog.getShell().isVisible()) {
				dialog.checkHyperlinkEnabled(getActiveDomain());
			}
		}
	};

	private CustomMouseTrackListener rapMouseTrackListener = new CustomMouseTrackListener() {

		public void mouseHover(CustomMouseEvent e) {
			/*
			 * Not sure we need mouse hover
			 */
			Point mouseLoc = getViewSite().getShell().getDisplay().getCursorLocation();
			ToolItem item = mgr.getControl().getItem(mgr.getControl().toControl(mouseLoc));
			if (item != null) {
				doMouseHover(item);
			}
		}

		public void mouseEnter(CustomMouseEvent e) {
			Point mouseLoc = getViewSite().getShell().getDisplay().getCursorLocation();
			ToolItem item = mgr.getControl().getItem(mgr.getControl().toControl(mouseLoc));
			if (item != null) {
				doMouseEnter(domains.getControl());
			}
		}

		public void mouseExit(CustomMouseEvent e) {
			// TODO Auto-generated method stub

		}

	};

	private MouseTrackListener rcpMouseTrackListener = new MouseTrackListener() {

		public void mouseEnter(MouseEvent e) {
			Point mouseLoc = getViewSite().getShell().getDisplay().getCursorLocation();
			ToolItem item = mgr.getControl().getItem(mgr.getControl().toControl(mouseLoc));
			if (item != null) {
				doMouseEnter(domains.getControl());
			}
		}

		public void mouseExit(MouseEvent e) {
			//PASS
		}

		public void mouseHover(MouseEvent e) {
			/*
			 * Not sure we need mouse hover
			 */
			Point mouseLoc = getViewSite().getShell().getDisplay().getCursorLocation();
			ToolItem item = mgr.getControl().getItem(mgr.getControl().toControl(mouseLoc));
			if (item != null) {
				doMouseHover(item);
			}
		}

	};

	private ToolBarManager mgr;

	private CustomControlItem domains;

	private UIJob mouseMoveListenerJob;

	@Override
	protected Object getInitialInput() {
		for (ScaDomainManager domain : ScaPlugin.getDefault().getDomainManagerRegistry().getDomains()) {
			if (domain.getName() != null && domain.getName().equals(getActiveDomainName())) {
				if (!domain.isConnected()) {
					try {
						domain.connect(new NullProgressMonitor(), RefreshDepth.CHILDREN);
					} catch (DomainConnectionException e) {
						ScaSingleDomainPlugin.logError("Unable to connect to domain", e);
					}
				}
				//setContentDescription(domain.getName());
				domains.setLabelText(domain.getName().trim().equals("") ? "NO ACTIVE DOMAIN" : domain.getName());
				getViewSite().getActionBars().updateActionBars();
				return domain;
			}
		}
		domains.setLabelText("NO ACTIVE DOMAIN");
		return null;
	}



	@Override
	protected CommonViewer createCommonViewerObject(final Composite aParent) {
		prefs = ScaUiPlugin.getDefault().getScaPreferenceStore();
		ScaDomainManagerRegistry registry = ScaPlugin.getDefault().getDomainManagerRegistry();
		if (!registry.eAdapters().contains(domainChangeAdapter)) {
			registry.eAdapters().add(domainChangeAdapter);
		}
		prefs.addPropertyChangeListener(activeDomainListener);
		CommonViewer retVal = super.createCommonViewerObject(aParent);
		this.viewer = retVal;
		return  retVal;
	}

	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
		fillToolBar("");
	}

	//BEGIN WORKAROUND CODE
	private void createMouseMoveListener() {
		/** remove after Juno bug 402593 is fixed, wherein listeners on toolbar don't work */
		this.mouseMoveListenerJob = new UIJob("MouseMoveListener Job") {

			@Override
			public IStatus runInUIThread(IProgressMonitor monitor) {
				final Display display = getViewSite().getShell().getDisplay();
				while (!monitor.isCanceled()) {
					final Point mouseLoc = display.getCursorLocation();
					final Rectangle toolbarItemLoc = domains.getControl().getBounds();
					if (toolbarItemLoc.contains(domains.getControl().getParent().toControl(mouseLoc))) {
						if (dialog.getShell() == null || !dialog.getShell().isVisible()) {
							doMouseEnter(domains.getControl());
						}
					}
				}
				return null;
			}

		};
		this.mouseMoveListenerJob.setSystem(true);
	}
	//END WORKAROUND CODE


	private void fillToolBar(String label) {
//		System.err.println("SWT VERSION: " + SWT.getVersion());
		mgr = (ToolBarManager) getViewSite().getActionBars().getToolBarManager();
		if (domains != null) {
			mgr.remove(domains);
			if (SWT.getPlatform().startsWith("rap")) {
				domains.removeMouseTrackListener(rapMouseTrackListener);
			} else {
				domains.removeMouseTrackListener(rcpMouseTrackListener);
			}
		}

		domains = new CustomControlItem(label);
		mgr.insert(0, domains);

		dialog = new DomainsDialog(getViewSite().getShell());

		if (SWT.getPlatform().startsWith("rap")) {
			domains.addMouseTrackListener(rapMouseTrackListener);
		} else {
			domains.addMouseTrackListener(rcpMouseTrackListener);
		}
		//BEGIN WORKAROUND CODE
		if (this.mouseMoveListenerJob == null) {
			createMouseMoveListener();
		}
		if (this.mouseMoveListenerJob.getState() != Job.RUNNING) {
			// PASS
			//this.mouseMoveListenerJob.schedule();
		}
		//END WORKAROUND CODE
	}

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);
		prefs.setDefault(ScaSingleDomainPreferenceConstants.SCA_DISCONNECT_INACTIVE, true);
		prefs.setDefault(ScaSingleDomainPreferenceConstants.SCA_SET_NEW_DOMAIN_ACTIVE, true);
	}

	private void setActiveDomain(String name) {
		if (name == null || name.equals("")) {
			prefs.setToDefault(ScaSingleDomainPreferenceConstants.SCA_ACTIVE_DOMAIN);
		} else {
			prefs.setValue(ScaSingleDomainPreferenceConstants.SCA_ACTIVE_DOMAIN, name);
		}
	}

	@Override
	public void dispose() {
		dialog.dispose();
		prefs.removePropertyChangeListener(activeDomainListener);
		ScaPlugin.getDefault().getDomainManagerRegistry().eAdapters().remove(domainChangeAdapter);
		//BEGIN WORKAROUND CODE
		if (this.mouseMoveListenerJob != null) {
			this.mouseMoveListenerJob.cancel();
		}
		//END WORKAROUND CODE
		super.dispose();
	}

	private void doMouseEnter(TrackableLabel control) {
		final int x = control.getBounds().x;
		final int y = control.getBounds().y;
		DialogCloseJob dialogCloseJob = new DialogCloseJob(dialog);
		dialog.show(control.getParent().toDisplay(new Point(x, y)), dialogCloseJob);
	}

	private void doMouseHover(ToolItem item) {
		int x = item.getBounds().x;
		int y = item.getBounds().y;
		DialogCloseJob dialogCloseJob = new DialogCloseJob(dialog);
		dialog.show(item.getParent().toDisplay(new Point(x, y)), dialogCloseJob);
	}
}

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
package gov.redhawk.sca.ui.singledomain.dialogs;

import gov.redhawk.model.sca.DomainConnectionException;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.sca.ScaPlugin;
import gov.redhawk.sca.internal.ui.handlers.NewDomainHandler;
import gov.redhawk.sca.ui.FlasherJob;
import gov.redhawk.sca.ui.ScaUiPlugin;
import gov.redhawk.sca.ui.singledomain.CustomControlItem;
import gov.redhawk.sca.ui.singledomain.CustomMouseEvent;
import gov.redhawk.sca.ui.singledomain.CustomMouseTrackListener;
import gov.redhawk.sca.ui.singledomain.ScaSingleDomainPlugin;
import gov.redhawk.sca.ui.singledomain.ScaSingleDomainPreferenceConstants;
import gov.redhawk.sca.ui.singledomain.TrackableLabel;
import gov.redhawk.sca.ui.singledomain.TrackableLabelAndHyperlink;
import gov.redhawk.sca.ui.singledomain.views.ScaExplorerSingleDomain;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISources;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.events.IHyperlinkListener;
import org.eclipse.ui.forms.widgets.Hyperlink;

public class DomainsDialog extends Dialog {

	private Font font;

	private static Color colorBackground;

	private static Color colorDomainLabel;

	private static Color colorNewDomainLink;

	private static final String CLASS_UICALLBACK = "org.eclipse.rwt.lifecycle.UICallBack";

	public enum LinkType {
		CONNECT,
		DISCONNECT,
		REFRESH,
		ALL
	}

	/**
	 * Since RAP does not have a MouseTrackListener, we implement a custom
	 * listener. Event methods on the listener are called by the custom widget
	 * implemented in the RAP client.
	 */
	private class RAPDomainHoverListener implements CustomMouseTrackListener {

		private TrackableLabel label;

		private RAPDomainHoverListener(TrackableLabel label) {
			this.label = label;
		}

		@Override
		public void mouseEnter(CustomMouseEvent e) {
			doMouseEnter(label);
		}

		@Override
		public void mouseExit(CustomMouseEvent e) {
			doMouseExit(label);
		}

		@Override
		public void mouseHover(CustomMouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	private class RCPDomainHoverListener extends MouseTrackAdapter {
		private TrackableLabelAndHyperlink label;

		private RCPDomainHoverListener(TrackableLabelAndHyperlink label) {
			this.label = label;
		}

		@Override
		public void mouseEnter(MouseEvent e) {
			doMouseEnter(label);
		}

		@Override
		public void mouseExit(MouseEvent e) {
			Control c = (Control) e.getSource();
			// don't perform exit if moving to hyperlink in same TrackableLabel
			/*
			 * this guard is not necessary for the RAP listener, because the
			 * Javascript client filters events based on the specific event
			 * source
			 */
			int y1 = label.getBounds().y;
			int y2 = y1 + label.getBounds().height;
			int eventY = label.toControl(c.toDisplay(new Point(e.x, e.y))).y;
			if (eventY <= y1 || eventY >= y2) {
				doMouseExit(label);
			}
		}
	}

	private void doMouseEnter(final TrackableLabel label) {
		label.getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				FontData fontData = label.getFont().getFontData()[0];
				font = new Font(label.getDisplay(), new FontData(fontData.getName(), fontData.getHeight(), SWT.ITALIC));
				label.setFont(font);
				label.setForeground(label.getDisplay().getSystemColor(SWT.COLOR_BLACK));
				((GridData) label.getLayoutData()).horizontalIndent = 3;
				if (label instanceof TrackableLabelAndHyperlink) {
					TrackableLabelAndHyperlink link = (TrackableLabelAndHyperlink) label;
					link.showLink();
				}
				label.resize();
				label.redraw();
				label.update();
				composite.layout();
				composite.redraw();
				composite.update();
			}

		});
	}

	private void doMouseExit(final TrackableLabel label) {
		label.getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				if (label.isDisposed()) {
					return;
				}
				FontData fontData = label.getFont().getFontData()[0];
				font = new Font(label.getDisplay(), new FontData(fontData.getName(), fontData.getHeight(), SWT.NORMAL));
				label.getLabel().setFont(font);
				label.getLabel().setForeground(label.getDisplay().getSystemColor(SWT.COLOR_DARK_BLUE));
				((GridData) label.getLayoutData()).horizontalIndent = 0;
				if (label instanceof TrackableLabelAndHyperlink) {
					TrackableLabelAndHyperlink link = (TrackableLabelAndHyperlink) label;
					link.hideLink();
				}
				label.resize();
				label.redraw();
				label.update();
				composite.layout();
				composite.redraw();
				composite.update();
			}

		});
	}

	private class DomainSelectionListener extends MouseAdapter {

		private ScaDomainManager domain;
		private TrackableLabel label;

		private DomainSelectionListener(ScaDomainManager domain, TrackableLabel label2) {
			this.domain = domain;
			this.label = label2;
		}

		@Override
		public void mouseDown(MouseEvent e) {
			if (isActiveDomain(domain)) {
				return;
			}
			Runnable postFlash = new Runnable() {

				@Override
				public void run() {
					prefs.setValue(ScaSingleDomainPreferenceConstants.SCA_ACTIVE_DOMAIN, domain.getLabel());
					if (!DomainsDialog.this.getShell().isDisposed()) {
						setActiveLabel(label);
						Point loc = shell.getLocation();
						hide();
						show(loc, new DialogCloseJob(DomainsDialog.this));
					}
				}

			};
			flash(label, postFlash);
		}
	}

	private Shell shell;

	private FlasherJob flasher;

	private Composite composite;

	private IPreferenceStore prefs;

	private Job closeListenerJob;

	private Hyperlink connect;

	private Hyperlink disconnect;

	private Hyperlink refresh;

	public DomainsDialog(Shell shell) {
		super(shell);
		prefs = ScaUiPlugin.getDefault().getScaPreferenceStore();
		colorBackground = shell.getDisplay().getSystemColor(SWT.COLOR_WHITE);
		colorDomainLabel = shell.getDisplay().getSystemColor(SWT.COLOR_DARK_BLUE);
		colorNewDomainLink = shell.getDisplay().getSystemColor(SWT.COLOR_BLUE);
	}

	public void setActiveLabel(TrackableLabel label) {
		label.setLabelText(label.getLabelText() + " (active)");
		label.redraw();
		label.update();
		for (Control child : composite.getChildren()) {
			if (child instanceof Label && child != label) {
				Label l = (Label) child;
				l.setText(l.getText().replaceAll(" \\(active\\)", ""));
				label.redraw();
				label.update();
			}
		}
	}

	public boolean isActiveDomain(ScaDomainManager domain) {
		String activeDOmain = prefs.getString(ScaSingleDomainPreferenceConstants.SCA_ACTIVE_DOMAIN);
		return domain.getLabel().equals(activeDOmain);
	}

	public void flash(Control label, final Runnable runnable) {
		this.flasher = new FlasherJob(label, true, 50, 8, runnable) {
			@Override
			protected void flash(Control control) {
				control.setForeground(colorBackground);
			};

			@Override
			protected void unFlash(Control control) {
				control.setForeground(colorDomainLabel);
			};
		};
		this.flasher.reset();
	}

	public void dispose() {
		if (this.font != null) {
			this.font.dispose();
		}
	}

	private void createComposite() {
		composite = new Composite(shell, SWT.BORDER);
		composite.setBackground(composite.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		GridLayoutFactory.fillDefaults().applyTo(shell);
		GridLayoutFactory.fillDefaults().margins(15, 10).applyTo(composite);
		GridDataFactory gdf = GridDataFactory.fillDefaults().grab(true, true);
		gdf.applyTo(composite);
	}

	private void fillComposite() {
		TrackableLabel head = new TrackableLabel(composite, "Specify the active domain", SWT.NONE, false);
		head.setBackground(head.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		composite.setBackground(head.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		GridDataFactory gdf = GridDataFactory.fillDefaults().grab(true, true);
		gdf.align(SWT.FILL, SWT.FILL).grab(true, true).applyTo(head.getLabel());
		Label sep = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		gdf.applyTo(sep);
		gdf.align(SWT.BEGINNING, SWT.CENTER).grab(true, false);
		for (ScaDomainManager domain : ScaPlugin.getDefault().getDomainManagerRegistry(getShell().getDisplay()).getDomains()) {
			String suffix = getActiveLabel(domain);
			final TrackableLabelAndHyperlink label = new TrackableLabelAndHyperlink(composite, domain.getLabel() + suffix, "delete", colorBackground, SWT.NONE,
				false);
			label.addHyperlinkListener(new IHyperlinkListener() {

				@Override
				public void linkExited(HyperlinkEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void linkEntered(HyperlinkEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void linkActivated(HyperlinkEvent e) {
					deleteDomain(((Label) label.getLabel()).getText());
					refreshDialogContents();
				}
			});
			label.setBackground(colorBackground);
			label.setForeground(colorDomainLabel);
			if (SWT.getPlatform().startsWith("rap")) {
				label.addMouseTrackListener(new RAPDomainHoverListener(label));
			} else {
				CustomControlItem.addMouseTrackListenerToControl(label.getLabel(), (MouseTrackListener) new RCPDomainHoverListener(label));
			}
			label.addMouseListener(new DomainSelectionListener(domain, label));
			label.hideLink();
			gdf.applyTo(label);
		}

		sep = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.CENTER).applyTo(sep);

		Hyperlink newDomain = new Hyperlink(composite, SWT.NONE);
		newDomain.setText("New Domain");
		newDomain.setBackground(colorBackground);
		newDomain.setForeground(colorNewDomainLink);
		newDomain.setUnderlined(true);
		newDomain.addHyperlinkListener(new IHyperlinkListener() {

			@Override
			public void linkEntered(org.eclipse.ui.forms.events.HyperlinkEvent e) {

			}

			@Override
			public void linkExited(org.eclipse.ui.forms.events.HyperlinkEvent e) {

			}

			@Override
			public void linkActivated(org.eclipse.ui.forms.events.HyperlinkEvent e) {
				EvaluationContext context = new EvaluationContext(null, getShell());
				context.addVariable(ISources.ACTIVE_SHELL_NAME, getShell());
				ExecutionEvent event = new ExecutionEvent(null, new HashMap<String, String>(), null, context);

				try {
					DomainsDialog.this.hide();
					new NewDomainHandler().execute(event);
				} catch (ExecutionException e2) {
					ScaUiPlugin.logError("Unable to launch New Domain Wizard", e2);
				}
			}
		});
		gdf.applyTo(newDomain);

		connect = new Hyperlink(composite, SWT.NONE);
		connect.setText("Connect to Active Domain");
		connect.setBackground(colorBackground);
		connect.setForeground(colorNewDomainLink);
		connect.setUnderlined(true);
		connect.addHyperlinkListener(new IHyperlinkListener() {

			@Override
			public void linkEntered(org.eclipse.ui.forms.events.HyperlinkEvent e) {

			}

			@Override
			public void linkExited(org.eclipse.ui.forms.events.HyperlinkEvent e) {

			}

			@Override
			public void linkActivated(org.eclipse.ui.forms.events.HyperlinkEvent e) {
				ScaDomainManager domain = ScaPlugin.getDefault().getDomainManagerRegistry(getShell().getDisplay()).findDomain(
					ScaExplorerSingleDomain.getActiveDomainName());
				if (domain != null && !domain.isConnected()) {
					try {
						domain.connect(new NullProgressMonitor(), RefreshDepth.SELF);
						checkHyperlinkEnabled(domain);
					} catch (DomainConnectionException ex) {
						ScaSingleDomainPlugin.logError("Unable to connect to domain" + domain.getLabel(), ex);
					}
				}
			}
		});
		gdf.applyTo(connect);

		disconnect = new Hyperlink(composite, SWT.NONE);
		disconnect.setText("Disconnect from Active Domain");
		disconnect.setBackground(colorBackground);
		disconnect.setForeground(colorNewDomainLink);
		disconnect.setUnderlined(true);
		disconnect.addHyperlinkListener(new IHyperlinkListener() {

			@Override
			public void linkEntered(org.eclipse.ui.forms.events.HyperlinkEvent e) {

			}

			@Override
			public void linkExited(org.eclipse.ui.forms.events.HyperlinkEvent e) {

			}

			@Override
			public void linkActivated(org.eclipse.ui.forms.events.HyperlinkEvent e) {
				ScaDomainManager domain = ScaPlugin.getDefault().getDomainManagerRegistry(getShell().getDisplay()).findDomain(
					ScaExplorerSingleDomain.getActiveDomainName());
				if (domain != null && domain.isConnected()) {
					domain.disconnect();
					checkHyperlinkEnabled(domain);
				}
			}
		});
		gdf.applyTo(disconnect);

		refresh = new Hyperlink(composite, SWT.NONE);
		refresh.setText("Refresh Active Domain");
		refresh.setBackground(colorBackground);
		refresh.setForeground(colorNewDomainLink);
		refresh.setUnderlined(true);
		refresh.addHyperlinkListener(new IHyperlinkListener() {

			@Override
			public void linkEntered(org.eclipse.ui.forms.events.HyperlinkEvent e) {

			}

			@Override
			public void linkExited(org.eclipse.ui.forms.events.HyperlinkEvent e) {

			}

			@Override
			public void linkActivated(org.eclipse.ui.forms.events.HyperlinkEvent e) {
				ScaDomainManager domain = ScaPlugin.getDefault().getDomainManagerRegistry(getShell().getDisplay()).findDomain(
					ScaExplorerSingleDomain.getActiveDomainName());
				if (domain != null && domain.isConnected()) {
					try {
						domain.refresh(new NullProgressMonitor(), RefreshDepth.SELF);
					} catch (InterruptedException ex) {
						ScaSingleDomainPlugin.logError("Unable to refresh domain" + domain.getLabel(), ex);
					}
				}
			}
		});
		gdf.applyTo(refresh);

		shell.pack();
		// Extra width to accommodate indent effect when rolling over domain
		// names
		Point size = shell.computeSize(SWT.DEFAULT, SWT.DEFAULT);
		shell.setSize(size.x + 10, size.y);
	}

	protected void refreshDialogContents() {
		Point location = shell.getLocation();
		this.hide();
		this.show(location, new DialogCloseJob(this));
	}

	protected void deleteDomain(String domainName) {
		ScaDomainManagerRegistry registry = ScaPlugin.getDefault().getDomainManagerRegistry(getShell().getDisplay());
		final ScaDomainManager domMgr = registry.findDomain(domainName);
		if (domMgr != null) {
			domMgr.disconnect();
			ScaModelCommand.execute(domMgr, new ScaModelCommand() {

				@Override
				public void execute() {
					ScaPlugin.getDefault().getDomainManagerRegistry(getShell().getDisplay()).getDomains().remove(domMgr);
				}

			});
		}
	}

	private String getActiveLabel(ScaDomainManager domain) {
		if (domain.getLabel().equals(ScaUiPlugin.getDefault().getScaPreferenceStore().getString(ScaSingleDomainPreferenceConstants.SCA_ACTIVE_DOMAIN))) {
			return " (active)";
		}
		return "";
	}

	public void show(Point location, Job closeListener) {
		if (composite != null) {
			composite.dispose();
			composite = null;
		}
		if (shell != null && !shell.isDisposed()) {
			shell.close();
		}
		shell = new Shell(getParent(), SWT.NO_TRIM | SWT.ON_TOP);
		createComposite();
		fillComposite();
		shell.setLocation(location);
		shell.setVisible(true);
		if (this.closeListenerJob != null) {
			this.closeListenerJob.cancel();
		}
		checkHyperlinkEnabled(ScaExplorerSingleDomain.getActiveDomain(shell.getDisplay()));
		this.closeListenerJob = closeListener;
		this.closeListenerJob.schedule();
	}

	public void hide() {
		if (shell != null && !shell.isDisposed()) {
			if (SWT.getPlatform().startsWith("rap")) {
				invokeUICallback("activate", String.valueOf(this.hashCode()));
			}
			shell.setVisible(false);
			shell.close();
			if (SWT.getPlatform().startsWith("rap")) {
				invokeUICallback("deactivate", String.valueOf(this.hashCode()));
			}
		}
		if (this.closeListenerJob != null) {
			this.closeListenerJob.cancel();
		}
	}

	private void invokeUICallback(String method, String id) {
		try {
			Class< ? > clazz = Class.forName(CLASS_UICALLBACK);
			Method m = clazz.getMethod(method, String.class);
			m.invoke(null, id);
		} catch (ClassNotFoundException e) {
			// PASS
		} catch (SecurityException e) {
			// PASS
		} catch (NoSuchMethodException e) {
			// PASS
		} catch (IllegalArgumentException e) {
			// PASS
		} catch (IllegalAccessException e) {
			// PASS
		} catch (InvocationTargetException e) {
			// PASS
		}
	}

	public Shell getShell() {
		return this.shell;
	}

	private void enableHyperlink(LinkType type, boolean enabled) {
		switch (type) {
		case CONNECT:
			connect.setEnabled(enabled);
			break;
		case DISCONNECT:
			disconnect.setEnabled(enabled);
			break;
		case REFRESH:
			refresh.setEnabled(enabled);
			break;
		case ALL:
			connect.setEnabled(enabled);
			disconnect.setEnabled(enabled);
			refresh.setEnabled(enabled);
			break;
		default:
			break;
		}
	}

	public void checkHyperlinkEnabled(ScaDomainManager domain) {
		if (this.getShell() == null || this.getShell().isDisposed() || !this.getShell().isVisible()) {
			return;
		}
		if (domain == null) {
			enableHyperlink(LinkType.ALL, false);
		} else {
			if (domain.isConnected()) {
				enableHyperlink(LinkType.CONNECT, false);
				enableHyperlink(LinkType.DISCONNECT, true);
				enableHyperlink(LinkType.REFRESH, true);
			} else {
				enableHyperlink(LinkType.CONNECT, true);
				enableHyperlink(LinkType.DISCONNECT, false);
				enableHyperlink(LinkType.REFRESH, false);
			}
		}
	}

}

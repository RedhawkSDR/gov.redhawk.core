/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.core.notification.ui.internal;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.osgi.framework.FrameworkUtil;

import gov.redhawk.core.notification.ui.Notifications;

/**
 * A SWT/JFace-based notification window. Fades in, pauses, fades out.
 */
public class NotificationWindow extends Window {

	private static final int FADE_INCREMENT_MS = 80;
	private static final int FADE_INCREMENT_ALPHA = 15;
	private static final int DISPLAY_TIME_MS = 3000;
	private static final int PAUSE_TIME_MS = 1000;
	private static final int WIDTH = 350;
	private static final int MARGIN_TO_MONITOR_SIDE = 5;

	private int severity;
	private String title;
	private String message;
	private ResourceManager resourceManager;

	/**
	 * @param parentShell The notification's location is set w.r.t this shell
	 * @param severity See {@link Notifications} for constants
	 * @param title The title
	 * @param message The message
	 */
	protected NotificationWindow(Shell parentShell, int severity, String title, String message) {
		super(parentShell);
		setShellStyle(SWT.TOOL | SWT.NO_TRIM | SWT.ON_TOP | SWT.NO_FOCUS);
		this.severity = severity;
		this.title = title;
		this.message = message;
	}

	@Override
	public int open() {
		if (getShell() == null || getShell().isDisposed()) {
			resourceManager = new LocalResourceManager(JFaceResources.getResources());
			create();
		}

		constrainShellSize();

		doFade(getShell());

		return Window.OK;
	}

	@Override
	public boolean close() {
		boolean result = super.close();
		if (resourceManager != null) {
			resourceManager.dispose();
		}
		return result;
	}

	@Override
	protected Layout getLayout() {
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		return layout;
	}

	@Override
	protected Control createContents(Composite parent) {
		// TODO: Use a different CSS element?
		// parent.setData("org.eclipse.e4.ui.css.CssClassName", "MPartStack active");

		Composite titleComposite = new Composite(parent, SWT.NONE);
		titleComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		createTitle(titleComposite);

		Composite messageComposite = new Composite(parent, SWT.NONE);
		messageComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		createMessage(messageComposite);

		return parent;
	}

	private void createTitle(Composite parent) {
		parent.setLayout(new GridLayout(2, false));
		Label titleLabel = new Label(parent, SWT.NONE);
		titleLabel.setText(title);
		titleLabel.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false));
		FontDescriptor boldDescriptor = FontDescriptor.createFrom(titleLabel.getFont()).setStyle(SWT.BOLD);
		Font boldFont = boldDescriptor.createFont(titleLabel.getDisplay());
		titleLabel.setFont(boldFont);

		Label closeIcon = new Label(parent, SWT.NONE);
		closeIcon.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false));
		closeIcon.addListener(SWT.MouseUp, event -> {
			close();
		});
		try {
			String pluginId = FrameworkUtil.getBundle(getClass()).getSymbolicName();
			IPath path = new Path("/plugin").append(pluginId).append("/icons/close_view.png");
			ImageDescriptor descriptor = ImageDescriptor.createFromURL(new URL("platform", null, path.toString()));
			closeIcon.setImage(resourceManager.createImage(descriptor));
		} catch (MalformedURLException e) {
			// PASS
		}
	}

	private void createMessage(Composite parent) {
		parent.setLayout(new GridLayout(2, false));
		Label iconLabel = new Label(parent, SWT.NONE);
		switch (severity) {
		case Notifications.ERROR:
			iconLabel.setImage(parent.getDisplay().getSystemImage(SWT.ICON_ERROR));
			break;
		case Notifications.WARNING:
			iconLabel.setImage(parent.getDisplay().getSystemImage(SWT.ICON_WARNING));
			break;
		case Notifications.INFO:
		default:
			iconLabel.setImage(parent.getDisplay().getSystemImage(SWT.ICON_INFORMATION));
			break;
		}
		iconLabel.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));

		Label messageLabel = new Label(parent, SWT.WRAP);
		messageLabel.setText(message);
		messageLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	}

	@Override
	protected Point getInitialSize() {
		// Fixed width, allow to expand in y-direction as needed
		return getShell().computeSize(WIDTH, SWT.DEFAULT, true);
	}

	@Override
	protected Point getInitialLocation(Point initialSize) {
		// Compute current monitor's bounds
		Composite parent = getShell().getParent();
		Monitor monitor = getShell().getDisplay().getPrimaryMonitor();
		if (parent != null) {
			monitor = parent.getMonitor();
		}
		Rectangle monitorBounds = monitor.getClientArea();

		// Position near top-right of monitor
		int x = monitorBounds.x + monitorBounds.width - initialSize.x - MARGIN_TO_MONITOR_SIDE;
		int y = monitorBounds.y + MARGIN_TO_MONITOR_SIDE;
		return new Point(x, y);
	}

	/**
	 * Provides a slow fade in, a pause, then a slow fade out. If the mouse moves over the pop-up during fade out, the
	 * window goes back to full visibility and starts fade-out over again once the mouse leaves.
	 * @param shell
	 */
	private void doFade(Shell shell) {
		shell.setAlpha(0);
		shell.setVisible(true);

		Runnable fadeOut = new Runnable() {
			public void run() {
				if (shell.isDisposed()) {
					return;
				}

				if (shell.getBounds().contains(shell.getDisplay().getCursorLocation())) {
					shell.setAlpha(255);
					shell.getDisplay().timerExec(PAUSE_TIME_MS, this);
					return;
				}

				int currentAlpha = shell.getAlpha();
				if (currentAlpha == 0) {
					close();
					return;
				}

				int newAlpha = Math.max(0, shell.getAlpha() - FADE_INCREMENT_ALPHA);
				shell.setAlpha(newAlpha);

				shell.getDisplay().timerExec(FADE_INCREMENT_MS, this);
			};
		};

		Runnable fadeIn = new Runnable() {
			@Override
			public void run() {
				if (shell.isDisposed()) {
					return;
				}

				int currentAlpha = shell.getAlpha();
				if (currentAlpha == 255) {
					shell.getDisplay().timerExec(DISPLAY_TIME_MS, fadeOut);
					return;
				}

				int newAlpha = Math.min(255, shell.getAlpha() + FADE_INCREMENT_ALPHA);
				shell.setAlpha(newAlpha);

				shell.getDisplay().timerExec(FADE_INCREMENT_MS, this);
			}
		};

		shell.getDisplay().timerExec(FADE_INCREMENT_MS, fadeIn);
	}
}

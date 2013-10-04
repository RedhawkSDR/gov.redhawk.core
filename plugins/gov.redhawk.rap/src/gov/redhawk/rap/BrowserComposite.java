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
package gov.redhawk.rap;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;

public abstract class BrowserComposite extends Composite {

	public static enum LoadState {
		UNLOADED, LOADED, FAILED
	}

	public static final String PROP_LOADED = "loaded";
	protected final Browser browser;
	private LoadState loaded = LoadState.UNLOADED;
	protected final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private final String replacer;

	public BrowserComposite(final Composite parent, final int style, final String replacer) {
		super(parent, style);
		super.setLayout(new FillLayout());
		this.browser = new Browser(this, SWT.NONE);
		this.replacer = replacer;
	}

	public String getReplacer() {
		return this.replacer;
	}

	public void addPropertyChangeListener(final PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(final PropertyChangeListener listener) {
		this.pcs.removePropertyChangeListener(listener);
	}

	public LoadState getLoadState() {
		return this.loaded;
	}

	protected void setLoaded(final LoadState loaded) {
		final LoadState oldValue = this.loaded;
		this.loaded = loaded;
		this.pcs.firePropertyChange(BrowserComposite.PROP_LOADED, oldValue, loaded);
	}

	public Browser getBrowser() {
		return this.browser;
	}

	@Override
	public void dispose() {
		setLoaded(LoadState.UNLOADED);
		super.dispose();
	}

}

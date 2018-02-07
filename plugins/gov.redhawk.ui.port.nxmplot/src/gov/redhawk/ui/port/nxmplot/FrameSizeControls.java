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
package gov.redhawk.ui.port.nxmplot;

import java.beans.PropertyChangeListener;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Text;

import gov.redhawk.sca.util.PropertyChangeSupport;
import gov.redhawk.ui.port.nxmplot.preferences.PlotPreferences;

/**
 * @since 6.0
 */
public class FrameSizeControls extends Composite {

	private class State {

		private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
		private int frameSize;

		@SuppressWarnings("unused")
		public int getFrameSize() {
			return frameSize;
		}

		public void setFrameSize(int newFrameSize) {
			newFrameSize = Math.max(0, newFrameSize);
			int oldFrameSize = this.frameSize;
			this.frameSize = newFrameSize;
			if (oldFrameSize != this.frameSize) {
				pcs.firePropertyChange("frameSize", oldFrameSize, this.frameSize);
				IPreferenceStore prefs = pageBook.getSharedPlotBlockPreferences();
				if (newFrameSize == 0) {
					PlotPreferences.FRAMESIZE_OVERRIDE.setToDefault(prefs);
					PlotPreferences.FRAMESIZE.setToDefault(prefs);
				} else {
					PlotPreferences.FRAMESIZE.setValue(prefs, this.frameSize);
					PlotPreferences.FRAMESIZE_OVERRIDE.setValue(prefs, true);
				}
			}
		}

		@SuppressWarnings("unused")
		public void addPropertyChangeListener(PropertyChangeListener listener) {
			pcs.addPropertyChangeListener(listener);
		}

		@SuppressWarnings("unused")
		public void removePropertyChangeListener(PropertyChangeListener listener) {
			pcs.removePropertyChangeListener(listener);
		}

	}

	private State state = new State();

	private Scale frameSizeSlider;
	private Text frameSizeText;
	private final PlotPageBook2 pageBook;
	private DataBindingContext context = new DataBindingContext();

	public FrameSizeControls(PlotPageBook2 pageBook, Composite parent) {
		super(parent, SWT.NONE);
		this.pageBook = pageBook;
		createPartControls(this);

		final IPreferenceStore prefs = pageBook.getSharedPlotBlockPreferences();
		prefs.addPropertyChangeListener(event -> {
			if (isDisposed()) {
				return;
			}
			if (!PlotPreferences.FRAMESIZE_OVERRIDE.isEvent(event) && !PlotPreferences.FRAMESIZE.isEvent(event)) {
				return;
			}
			getDisplay().asyncExec(() -> {
				updateFrameSize();
			});
		});
		updateFrameSize();
	}

	private void updateFrameSize() {
		final IPreferenceStore prefs = pageBook.getSharedPlotBlockPreferences();
		boolean override = PlotPreferences.FRAMESIZE_OVERRIDE.getValue(prefs);
		int frameSize = PlotPreferences.FRAMESIZE.getValue(prefs);
		state.setFrameSize((override) ? frameSize : 0);
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@SuppressWarnings("unchecked")
	private void createPartControls(Composite parent) {
		parent.setLayout(new GridLayout(1, false));

		Label avgLabel = new Label(parent, SWT.CENTER);
		avgLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		FontData[] fD = avgLabel.getFont().getFontData();
		fD[0].setStyle(SWT.BOLD);
		fD[0].setHeight(8);
		avgLabel.setFont(new Font(avgLabel.getDisplay(), fD[0]));
		avgLabel.setText("Frame\nSize");

		this.frameSizeText = new Text(parent, SWT.BORDER);
		fD = frameSizeText.getFont().getFontData();
		fD[0].setHeight(8);
		frameSizeText.setFont(new Font(frameSizeText.getDisplay(), fD[0]));
		GC gc = null;
		try {
			gc = new GC(frameSizeText);
			Point minSize = gc.stringExtent("XXX");
			this.frameSizeText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(minSize.x, SWT.DEFAULT).create());
		} finally {
			if (gc != null) {
				gc.dispose();
			}
		}
		this.frameSizeText.setTextLimit(4);

		// Use String <--> Integer converters which have the special case "" <--> 0
		UpdateValueStrategy textToNum = UpdateValueStrategy.create(new Converter(String.class, Integer.class) {
			@Override
			public Object convert(Object fromObject) {
				if ("".equals(fromObject)) {
					return 0;
				}
				return Integer.parseInt((String) fromObject);
			}
		});
		textToNum.setAfterGetValidator(value -> {
			if ("".equals(value)) {
				return Status.OK_STATUS;
			}
			try {
				Integer.parseInt((String) value);
			} catch (NumberFormatException e) {
				return new Status(IStatus.ERROR, PlotActivator.PLUGIN_ID, e.getMessage());
			}
			return Status.OK_STATUS;
		});
		UpdateValueStrategy numToText = UpdateValueStrategy.create(new Converter(Integer.class, String.class) {
			@Override
			public Object convert(Object fromObject) {
				if ((Integer) fromObject == 0) {
					return "";
				}
				return String.valueOf((Integer) fromObject);
			}
		});
		context.bindValue(WidgetProperties.text(SWT.Modify, SWT.FocusOut).observeDelayed(500, frameSizeText), BeanProperties.value("frameSize").observe(state),
			textToNum, numToText);

		this.frameSizeSlider = new Scale(parent, SWT.VERTICAL);
		this.frameSizeSlider.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		this.frameSizeSlider.setToolTipText("Frame Size");
		this.frameSizeSlider.setMinimum(1);
		this.frameSizeSlider.setMaximum(256);
		context.bindValue(WidgetProperties.selection().observe(frameSizeSlider), WidgetProperties.text(SWT.Modify, SWT.FocusOut).observe(frameSizeText));

	}

}

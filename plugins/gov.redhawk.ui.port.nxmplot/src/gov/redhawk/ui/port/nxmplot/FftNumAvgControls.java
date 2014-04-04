/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.ui.port.nxmplot;

import gov.redhawk.sca.util.PropertyChangeSupport;
import gov.redhawk.ui.port.nxmblocks.FftNxmBlock;
import gov.redhawk.ui.port.nxmplot.preferences.FftPreferences;

import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
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

/**
 * @since 4.4
 */
public class FftNumAvgControls extends Composite {

	private static final Integer DEFAULT_FFT_NUM_AVE = 1;

	private IPropertyChangeListener fftSettingsListener = new IPropertyChangeListener() {

		@Override
		public void propertyChange(final PropertyChangeEvent evt) {
			if (isDisposed()) {
				return;
			}
			if (FftPreferences.NUM_AVERAGES.isEvent(evt)) {

				getDisplay().asyncExec(new Runnable() {

					@Override
					public void run() {
						int numAvg = (Integer) evt.getNewValue();
						if (avgSlider != null && avgText != null) {
							if (numAvg < avgSlider.getMinimum()) {
								numAvg = avgSlider.getMinimum();
							} else if (numAvg > avgSlider.getMaximum()) {
								numAvg = avgSlider.getMaximum();
							}
							state.setNumAvg(numAvg);
						}
					}

				});
			}
		}

	};

	private PropertyChangeListener propListener = new PropertyChangeListener() {

		@Override
		public void propertyChange(java.beans.PropertyChangeEvent evt) {
			if (isDisposed()) {
				return;
			}
			if (PlotPageBook2.PROP_SOURCES.equals(evt.getPropertyName())) {
				if (evt.getNewValue() instanceof PlotSource) {
					for (INxmBlock b : pageBook.getBlockChain((PlotSource) evt.getNewValue())) {
						if (b instanceof FftNxmBlock) {
							((FftNxmBlock) b).getPreferences().addPropertyChangeListener(fftSettingsListener);
						}
					}
				}
			}
		}
	};

	private class State {
		private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
		private int numAvg;

		@SuppressWarnings("unused")
		public int getNumAvg() {
			return numAvg;
		}

		public void setNumAvg(int numAvg) {
			numAvg = Math.min(avgSlider.getMaximum(), numAvg);
			numAvg = Math.max(avgSlider.getMinimum(), numAvg);
			int oldAvg = this.numAvg;
			this.numAvg = numAvg;
			if (oldAvg != this.numAvg) {
				pcs.firePropertyChange("numAvg", oldAvg, this.numAvg);
				for (FftNxmBlock b : getFftBlocks()) {
					b.setNumAverages(this.numAvg);
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

	private Scale avgSlider;
	private Text avgText;
	private final NumberFormat numberFormatter;
	private final PlotPageBook2 pageBook;
	private DataBindingContext context = new DataBindingContext();

	public FftNumAvgControls(PlotPageBook2 pageBook, Composite parent) {
		super(parent, SWT.None);
		this.pageBook = pageBook;
		this.pageBook.addPropertyChangeListener(propListener);
		numberFormatter = new DecimalFormat("##0");
		numberFormatter.setParseIntegerOnly(true);
		createPartControls(this);
		for (PlotSource source : pageBook.getSources()) {
			for (INxmBlock b : pageBook.getBlockChain(source)) {
				if (b instanceof FftNxmBlock) {
					((FftNxmBlock) b).getPreferences().addPropertyChangeListener(fftSettingsListener);
				}
			}
		}
		state.setNumAvg(getCurrentFftAvg());
	}

	@Override
	public void dispose() {
		super.dispose();
		pageBook.removePropertyChangeListener(propListener);
		for (FftNxmBlock b : getFftBlocks()) {
			b.getPreferences().removePropertyChangeListener(fftSettingsListener);
		}
	}

	private void createPartControls(Composite parent) {
		parent.setLayout(new GridLayout(1, false));

		Label avgLabel = new Label(parent, SWT.CENTER);
		avgLabel.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).create());
		FontData[] fD = avgLabel.getFont().getFontData();
		fD[0].setStyle(SWT.BOLD);
		fD[0].setHeight(8);
		avgLabel.setFont(new Font(avgLabel.getDisplay(), fD[0]));
		avgLabel.setText("Avg");

		this.avgText = new Text(parent, SWT.BORDER);
		fD = avgText.getFont().getFontData();
		fD[0].setHeight(8);
		avgText.setFont(new Font(avgText.getDisplay(), fD[0]));
		GC gc = null;
		try {
			gc = new GC(avgText);
			Point minSize = gc.stringExtent("XXX");
			this.avgText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).hint(minSize.x, SWT.DEFAULT).create());
		} finally {
			if (gc != null) {
				gc.dispose();
			}
		}
		this.avgText.setTextLimit(3);
		context.bindValue(WidgetProperties.text(new int[] { SWT.Modify, SWT.FocusOut }).observeDelayed(500, avgText),
			BeanProperties.value("numAvg").observe(state));

		this.avgSlider = new Scale(parent, SWT.VERTICAL);
		this.avgSlider.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());
		this.avgSlider.setToolTipText("FFT Averaging");
		this.avgSlider.setMinimum(1);
		this.avgSlider.setMaximum(100);
		context.bindValue(WidgetProperties.selection().observeDelayed(100, avgSlider), BeanProperties.value("numAvg").observe(state));

	}

	private int getCurrentFftAvg() {
		for (FftNxmBlock block : getFftBlocks()) {
			return block.getSettings().getNumAverages();
		}
		return FftNumAvgControls.DEFAULT_FFT_NUM_AVE;
	}

	private List<FftNxmBlock> getFftBlocks() {
		List<FftNxmBlock> retVal = new ArrayList<FftNxmBlock>();
		for (PlotSource source : pageBook.getSources()) {
			for (INxmBlock block : pageBook.getBlockChain(source)) {
				if (block instanceof FftNxmBlock) {
					retVal.add((FftNxmBlock) block);
				}
			}
		}
		return retVal;
	}

}

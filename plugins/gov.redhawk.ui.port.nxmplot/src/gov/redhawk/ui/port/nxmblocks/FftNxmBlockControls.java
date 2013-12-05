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
package gov.redhawk.ui.port.nxmblocks;


import gov.redhawk.ui.port.nxmblocks.FftNxmBlockSettings.OutputType;
import gov.redhawk.ui.port.nxmblocks.FftNxmBlockSettings.WindowType;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * Adjust/override FFT settings user entry dialog.
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.3
 */
public class FftNxmBlockControls {

	private static final String VALUE_USE_DEFAULT = "default";
	private static final int FIELD_BINDING_DELAY = 200;
	
	private DataBindingContext dataBindingCtx;
	private final FftNxmBlockSettings settings;
	private Text        transformSizeField;
	private Text        overlapField;
	private Text        numAveragesField;
	private ComboViewer fftType;
	private ComboViewer fftWindow;

	/**
	 * Instantiates a new entry dialog.
	 * @param parentShell the parent shell
	 */
	public FftNxmBlockControls(@Nullable final FftNxmBlockSettings settings,
			final DataBindingContext dataBindingsCtx) {
		this.settings = settings;
		this.dataBindingCtx = dataBindingsCtx;
	}

	public void createControls(final Composite container) {
		container.setLayout(new GridLayout(2, false));
		Label label;

		// === FFT transform size ===
		label = new Label(container, SWT.NONE);
		label.setText("Transform Size:");
		this.transformSizeField = new Text(container, SWT.BORDER); // writable
		this.transformSizeField.setLayoutData(GridDataFactory.fillDefaults().grab(true,  false).create());
		this.transformSizeField.setText(Integer.toString(this.settings.getTransformSize()));
		this.transformSizeField.setToolTipText("Performance is best if factorable by 2, 3, 4 and 5.");

		// === percent overlap ===
		label = new Label(container, SWT.NONE);
		label.setText("Percent Overlap:");
		this.overlapField = new Text(container, SWT.BORDER);
		this.overlapField.setLayoutData(GridDataFactory.fillDefaults().grab(true,  false).create());
		this.overlapField.setText(Integer.toString(this.settings.getOverlap()));

		// === num averages (/nexp) ===
		label = new Label(container, SWT.NONE);
		label.setText("Num Averages:");
		this.numAveragesField = new Text(container, SWT.BORDER);
		this.numAveragesField.setLayoutData(GridDataFactory.fillDefaults().grab(true,  false).create());
		this.numAveragesField.setText(Integer.toString(this.settings.getNumAverages()));
		this.numAveragesField.setToolTipText("Avoid using large value as it will cause highlighted energy to remain longer.");

		// === can not change FFT output type at this time ===
		label = new Label(container, SWT.NONE);
		label.setText("Output Type:");
		this.fftType = new ComboViewer(container, SWT.READ_ONLY);
		fftType.getCombo().setLayoutData(GridDataFactory.fillDefaults().grab(true,  false).create());
		fftType.setContentProvider(ArrayContentProvider.getInstance()); // ArrayContentProvider does not store any state, therefore can re-use instances
		fftType.setLabelProvider(new LabelProvider());
		fftType.setInput(FftNxmBlockSettings.OutputType.values());
		OutputType currentOutputType = this.settings.getOutputType();
		if (currentOutputType == null) {
			currentOutputType = OutputType.PSD; // default to PSD
		}
		fftType.setSelection(new StructuredSelection(currentOutputType));
		fftType.getCombo().setEnabled(false); // disable changing fft output type

		// === FFT Window ===
		label = new Label(container, SWT.NONE);
		label.setText("Window:");

		fftWindow = new ComboViewer(container, SWT.READ_ONLY);
		fftWindow.getCombo().setLayoutData(GridDataFactory.fillDefaults().grab(true,  false).create());
		fftWindow.setContentProvider(ArrayContentProvider.getInstance()); // ArrayContentProvider does not store any state, therefore can re-use instances
		fftWindow.setLabelProvider(new LabelProvider());
		fftWindow.setInput(FftNxmBlockSettings.WindowType.values());
		WindowType windowType = this.settings.getWindow();
		if (windowType == null) {
			windowType = WindowType.HANNING;
		}
		fftWindow.setSelection(new StructuredSelection(windowType));
		
		addDataBindings();
	}

	protected void addDataBindings() {
		IObservableValue fsTargetObservableVal = WidgetProperties.text(SWT.Modify).observeDelayed(FIELD_BINDING_DELAY, this.transformSizeField);
		IObservableValue fsModelObservableVal = PojoProperties.value("transformSize").observe(this.settings);
//		IObservableValue fsModelObservableVal = BeansObservables.observeValue(settings, );
		dataBindingCtx.bindValue(fsTargetObservableVal, fsModelObservableVal, createTargetToModelForFftSize(), null);

		IObservableValue srWidgetValue = WidgetProperties.text(SWT.Modify).observe(this.overlapField);
		IObservableValue srModelValue  = PojoProperties.value("overlap").observe(this.settings);
//		IObservableValue srModelObservableVal  = BeansObservables.observeValue(settings, "overlap");
		dataBindingCtx.bindValue(srWidgetValue, srModelValue, createTargetToModelForOverlap(), null);

//		IObservableValue numAvgTargetObservableValue = WidgetProperties.text(SWT.Modify).observeDelayed(FIELD_BINDING_DELAY, this.numAveragesField);
//		IObservableValue numAvgModelObservableValue = BeansObservables.observeValue(settings, "numAverages");
//		dataBindingCtx.bindValue(numAvgTargetObservableValue, numAvgModelObservableValue, createTargetToModelForNumAverages(), null);
//
//		IObservableValue windowWidgetValue = ViewerProperties.singleSelection().observeDelayed(FIELD_BINDING_DELAY, this.fftWindow);
//		IObservableValue windowModelValue = BeansObservables.observeValue(settings, "window");
//		dataBindingCtx.bindValue(windowWidgetValue, windowModelValue, createTargetToModelForWindow(), null);
	}

	private UpdateValueStrategy createTargetToModelForOverlap() {
		UpdateValueStrategy updateValueStrategy = new UpdateValueStrategy();
		
		updateValueStrategy.setAfterGetValidator(new IValidator() {
			@Override
			public IStatus validate(Object value) {
				if ("default".equalsIgnoreCase((String) value)) {
					return ValidationStatus.ok();
				} else {
					try {
						Integer.valueOf((String) value);
						return ValidationStatus.ok();
					} catch (NumberFormatException nfe) {
						return ValidationStatus.error("Overlap percent must a number between 0 - 100.");
					}
				}
			}
		});

		updateValueStrategy.setConverter(new Converter(String.class, Integer.class) {
			@Override
			public Object convert(Object fromObject) {
				if ("default".equalsIgnoreCase((String) fromObject)) {
					return -1;
				}
				return Integer.valueOf((String) fromObject);
			}
		});

		updateValueStrategy.setAfterConvertValidator(new IValidator() {
			@Override
			public IStatus validate(Object value) {
				Integer val = (Integer) value;
				if (val >= 0 && val <= 100) { // SUPPRESS CHECKSTYLE MAGIC NUMBER
					return ValidationStatus.ok();
				}
				return ValidationStatus.error("Overlap percent must a number between 0 - 100.");
			}
		});
		return updateValueStrategy;
	}

	private UpdateValueStrategy createTargetToModelForFftSize() {
		UpdateValueStrategy updateValueStrategy = new UpdateValueStrategy();
		
		updateValueStrategy.setAfterGetValidator(new IValidator() {
			@Override
			public IStatus validate(Object value) {
				if ("default".equalsIgnoreCase((String) value)) {
					return ValidationStatus.ok();
				} else {
					try {
						Integer.valueOf((String) value);
						return ValidationStatus.ok();
					} catch (NumberFormatException nfe) {
						return ValidationStatus.error("FFT transform size must a number greater than 0.");
					}
				}
			}
		});

		updateValueStrategy.setConverter(new Converter(String.class, Integer.class) {
			@Override
			public Object convert(Object fromObject) {
				if ("default".equalsIgnoreCase((String) fromObject)) {
					return -1;
				}
				return Integer.valueOf((String) fromObject);
			}
		});
		
		updateValueStrategy.setAfterConvertValidator(new IValidator() {
			@Override
			public IStatus validate(Object value) {
				Integer val = (Integer) value;
				if (val > 0) {
					return ValidationStatus.ok();
				}
				return ValidationStatus.error("FFT transform size must be greater than 0.");
			}
		});
		return updateValueStrategy;
	}

	private UpdateValueStrategy createTargetToModelForWindow() {
		UpdateValueStrategy updateValueStrategy = new UpdateValueStrategy();
		// TODO: ?
		return updateValueStrategy;
	}

	private UpdateValueStrategy createTargetToModelForNumAverages() {
		UpdateValueStrategy updateValueStrategy = new UpdateValueStrategy();
		
		updateValueStrategy.setAfterConvertValidator(new IValidator() {
			@Override
			public IStatus validate(Object value) {
				Integer val = (Integer) value;
				if (val >= 1) {
					return ValidationStatus.ok();
				}
				return ValidationStatus.error("Num averages must be greater than 0.");
			}
		});
		return updateValueStrategy;
	}

}

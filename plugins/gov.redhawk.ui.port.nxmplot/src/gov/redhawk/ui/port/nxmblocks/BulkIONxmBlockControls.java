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

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * Adjust/override BULKIO NXM block settings user entry dialog.
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.3
 */
public class BulkIONxmBlockControls extends Composite {

	private DataBindingContext dataBindingCtx;
	private final BulkIONxmBlockSettings settings;
	private ComboViewer frameSizeField;
	private ComboViewer sampleRateField;
	private ComboViewer blockingField;

	/**
	 * Instantiates a new entry dialog.
	 *
	 * @param parentShell the parent shell
	 */
	public BulkIONxmBlockControls(Composite parent, int style, BulkIONxmBlockSettings settings, DataBindingContext dataBindingCtx) {
		super(parent, style);
		this.settings = settings;
		this.dataBindingCtx = dataBindingCtx;
		createControls(this);
	}

	protected void createControls(final Composite container) {
		final GridLayout gridLayout = new GridLayout(2, false);
		container.setLayout(gridLayout);

		// === frame size ===
		final Label frameSizeLabel = new Label(container, SWT.NONE);
		frameSizeLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false));
		frameSizeLabel.setText("Frame Size:");
		this.frameSizeField = new ComboViewer(container, SWT.BORDER); // writable
		this.frameSizeField.getCombo().setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 1, 1));
		this.frameSizeField.setContentProvider(new ArrayContentProvider());
		this.frameSizeField.setLabelProvider(new LabelProvider());


		final String otherValidFSValue = "default";
		Object currentFS = otherValidFSValue;
		final Object[] fsComboInputs;
		final Integer fs = this.settings.getFrameSize();
		if (fs != null) {
			currentFS = fs;
			fsComboInputs = new Object[] { currentFS, otherValidFSValue, 512, 1024, 2048, 4096, 8192 };
		} else {
			fsComboInputs = new Object[] { otherValidFSValue, 512, 1024, 2048, 4096, 8192 };
		}

		this.frameSizeField.setInput(fsComboInputs);
		this.frameSizeField.addSelectionChangedListener(new SelectAllTextComboTextListener(this.frameSizeField.getCombo()));

		// === sample rate ===
		final Label sampleRateValueLabel = new Label(container, SWT.NONE);
		sampleRateValueLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false));
		sampleRateValueLabel.setText("Sample rate:");
		this.sampleRateField = new ComboViewer(container, SWT.BORDER); // writable
		this.sampleRateField.getCombo().setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 1, 1));
		this.sampleRateField.setContentProvider(new ArrayContentProvider());
		this.sampleRateField.setLabelProvider(new LabelProvider());

		final String otherValidSRateValue = "default";
		Object currentSRate = otherValidSRateValue;
		final Object[] srateComboInputs;
		final Double srate = this.settings.getSampleRate();
		if (srate != null) {
			currentSRate = srate;
			srateComboInputs = new Object[] { currentSRate, otherValidSRateValue };
		} else {
			srateComboInputs = new Object[] { otherValidSRateValue };
		}

		this.sampleRateField.setInput(srateComboInputs);
		this.sampleRateField.addSelectionChangedListener(new SelectAllTextComboTextListener(this.sampleRateField.getCombo()));

		// === blocking option ===
		final Label blockingLabel = new Label(container, SWT.NONE);
		blockingLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false));
		blockingLabel.setText("Blocking:");
		this.blockingField = new ComboViewer(container, SWT.READ_ONLY);
		this.blockingField.getCombo().setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 1, 1));
		this.blockingField.setContentProvider(new ArrayContentProvider());
		this.blockingField.setLabelProvider(new LabelProvider());
		this.blockingField.setInput(new boolean[] { true, false });
	}

	protected void addBindings() {
		IObservableValue fsTargetObservableVal = WidgetProperties.text(SWT.Modify).observeDelayed(200, this.frameSizeField.getCombo());
		IObservableValue fsModelObservableVal  = BeansObservables.observeValue(settings, "frameSize");
		dataBindingCtx.bindValue(fsTargetObservableVal, fsModelObservableVal, createTargetToModelForFrameSize(), null);

		IObservableValue srTargetObservableVal = WidgetProperties.text(SWT.Modify).observeDelayed(200, this.sampleRateField.getCombo());
		IObservableValue srModelObservableVal  = BeansObservables.observeValue(settings, "sampleRate");
		dataBindingCtx.bindValue(srTargetObservableVal, srModelObservableVal, createTargetToModelForSampleRate(), null);

		IObservableValue boTargetObservableValue = WidgetProperties.text(SWT.Modify).observeDelayed(200, this.blockingField.getCombo());
		IObservableValue boModelObservableValue = BeansObservables.observeValue(settings, "blocking");
		dataBindingCtx.bindValue(boTargetObservableValue, boModelObservableValue, null, null);
	}

	private UpdateValueStrategy createTargetToModelForSampleRate() {
		UpdateValueStrategy updateValueStrategy = new UpdateValueStrategy();
		updateValueStrategy.setAfterGetValidator(new IValidator() {

			@Override
			public IStatus validate(Object value) {
				if ("default".equalsIgnoreCase((String) value)) {
					return ValidationStatus.ok();
				} else {
					try {
						Double.valueOf((String) value);
						return ValidationStatus.ok();
					} catch (NumberFormatException nfe) {
						return ValidationStatus.error("Sample rate must a number greater than 0.");
					}
				}
			}
		});

		updateValueStrategy.setConverter(new Converter(String.class, Double.class) {

			@Override
			public Object convert(Object fromObject) {
				if ("default".equalsIgnoreCase((String) fromObject)) {
					return -1.0;
				}
				return Double.valueOf((String) fromObject);
			}
		});

		updateValueStrategy.setAfterConvertValidator(new IValidator() {

			@Override
			public IStatus validate(Object value) {
				Double val = (Double) value;
				if (val > 0) {
					return ValidationStatus.ok();
				}
				return ValidationStatus.error("Sample rate must be greater than 0.");
			}
		});
		return updateValueStrategy;
	}

	private UpdateValueStrategy createTargetToModelForFrameSize() {
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
						return ValidationStatus.error("Frame size must a number greater than 0.");
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
				return ValidationStatus.error("Frame size must be greater than 0.");
			}
		});
		return updateValueStrategy;
	}

	static class SelectAllTextComboTextListener implements ISelectionChangedListener {
		private final Combo combo;

		SelectAllTextComboTextListener(Combo combo) {
			this.combo = combo;
		}

		public void selectionChanged(final SelectionChangedEvent event) {
			final String text = this.combo.getText();
			final int textLen = (text == null) ? 0 : text.length();
			this.combo.setSelection(new Point(0, textLen)); // select text from combo selection
		}
	}

}

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

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * Adjust/override BULKIO NXM block settings user entry dialog.
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.3
 */
public class BulkIONxmBlockControls {

	private static final int FIELD_BINDING_DELAY = 100;
	
	private DataBindingContext dataBindingCtx;
	private final BulkIONxmBlockSettings settings;
	private ComboViewer sampleRateField;
	private ComboViewer blockingField;

	/**
	 * Instantiates a new entry dialog.
	 * @param parentShell the parent shell
	 */
	public BulkIONxmBlockControls(BulkIONxmBlockSettings settings, DataBindingContext dataBindingCtx) {
		this.settings = settings;
		this.dataBindingCtx = dataBindingCtx;
	}

	public void createControls(final Composite container) {
		final GridLayout gridLayout = new GridLayout(2, false);
		container.setLayout(gridLayout);

		// === sample rate ===
		final Label sampleRateValueLabel = new Label(container, SWT.NONE);
		sampleRateValueLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false));
		sampleRateValueLabel.setText("Sample rate:");
		this.sampleRateField = new ComboViewer(container, SWT.BORDER); // writable
		this.sampleRateField.getCombo().setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 1, 1));
		this.sampleRateField.setContentProvider(new ArrayContentProvider());
		this.sampleRateField.setLabelProvider(new LabelProvider());

//		final Double srate = this.settings.getSampleRate();
//		if (srate != null) {
//			srateComboInputs = new Object[] { srate, otherValidSRateValue };
//		} else {
//			srateComboInputs = new Object[] { otherValidSRateValue };
//		}
		this.sampleRateField.setInput(new Object[] { "default" });
		this.sampleRateField.setSelection(new StructuredSelection("default"));
		this.sampleRateField.addSelectionChangedListener(new SelectAllTextComboTextListener(this.sampleRateField.getCombo()));

		// === blocking option ===
		final Label blockingLabel = new Label(container, SWT.NONE);
		blockingLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false));
		blockingLabel.setText("Blocking:");
		this.blockingField = new ComboViewer(container, SWT.READ_ONLY);
		this.blockingField.getCombo().setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 1, 1));
		this.blockingField.setContentProvider(new ArrayContentProvider());
		this.blockingField.setLabelProvider(new LabelProvider());
		this.blockingField.setInput(new Boolean[] { Boolean.TRUE, Boolean.FALSE });

		initDataBindings();
	}

	protected void initDataBindings() {
//		IObservableValue srWidgetValue = ViewerProperties.singleSelection().observeDelayed(FIELD_BINDING_DELAY, this.sampleRateField);
		IObservableValue srWidgetValue = WidgetProperties.selection().observeDelayed(FIELD_BINDING_DELAY, this.sampleRateField.getCombo());
		IObservableValue srModelValue = PojoProperties.value("sampleRate").observe(settings);
//		Binding srBindValue = dataBindingCtx.bindValue(srWidgetValue, srModelValue);
		UpdateValueStrategy srModelToTarget = new UpdateValueStrategy();
		srModelToTarget.setConverter(new Converter(Double.class, String.class) {
			@Override
			public Object convert(Object fromObject) {
				if (fromObject != null) {
					return fromObject.toString();
				}
				return null;
			}
		});
//		srModelToTarget.setConverter(NumberToStringConverter.fromDouble(false));
		Binding srBindValue = dataBindingCtx.bindValue(srWidgetValue, srModelValue, createTargetToModelForSampleRate(), srModelToTarget);
		ControlDecorationSupport.create(srBindValue, SWT.TOP | SWT.LEFT);
		
		IObservableValue boWidgetValue = ViewerProperties.singleSelection().observeDelayed(FIELD_BINDING_DELAY, this.blockingField);
		IObservableValue boModelValue = PojoProperties.value("blocking").observe(settings);
		Binding boBindValue = dataBindingCtx.bindValue(boWidgetValue, boModelValue);
		ControlDecorationSupport.create(boBindValue, SWT.TOP | SWT.LEFT);
	}

	private UpdateValueStrategy createTargetToModelForSampleRate() {
		UpdateValueStrategy updateValueStrategy = new UpdateValueStrategy();
		
//		updateValueStrategy.setAfterGetValidator(new IValidator() {
//			@Override
//			public IStatus validate(Object value) {
//				if (value == null) {
//					return ValidationStatus.ok();
//				}
//				if ("default".equals(value) || "".equals(value)) {
//					return ValidationStatus.ok();
//				} else {
//					try {
//						Double.valueOf((String) value);
//						return ValidationStatus.ok();
//					} catch (NumberFormatException nfe) {
//						return ValidationStatus.error("Sample rate must be a number greater than 0.");
//					}
//				}
//			}
//		});

		updateValueStrategy.setConverter(new CustomStringToDoubleConverter(true, "default"));
//			new Converter(String.class, Double.class) {
//			@Override
//			public Object convert(Object fromObject) {
//				if (fromObject == null) {
//					return null;
//				}
//				if ("default".equals(fromObject) || "".equals(fromObject)) {
//					return null;
//				}
//				return Double.valueOf((String) fromObject);
//			}
//		});

		updateValueStrategy.setAfterConvertValidator(
			new StringToDoubleValidator("Sample rate", true, true, "default", Double.MIN_VALUE, null));
//		updateValueStrategy.setAfterConvertValidator(new IValidator() {
//			@Override
//			public IStatus validate(Object value) {
//				if (value == null) {
//					return ValidationStatus.ok();
//				}
//				Double val = (Double) value;
//				if (val > 0) {
//					return ValidationStatus.ok();
//				}
//				return ValidationStatus.error("Sample rate must be greater than 0.");
//			}
//		});
		return updateValueStrategy;
	}

}

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
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * Adjust/override INxmBlock settings to plot user entry dialog.
 * @noreference This class is provisional/beta and is subject to API changes
 * @since 4.3
 */
public class PlotNxmBlockControls {

	private static final int FIELD_BINDING_DELAY = 100;
	
	private DataBindingContext dataBindingCtx;
	private final PlotNxmBlockSettings settings;
	private ComboViewer frameSizeField;

	/**
	 * Instantiates a new entry dialog.
	 * @param parentShell the parent shell
	 */
	public PlotNxmBlockControls(PlotNxmBlockSettings settings, DataBindingContext dataBindingCtx) {
		this.settings = settings;
		this.dataBindingCtx = dataBindingCtx;		
	}

	public void createControls(final Composite container) {
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

		final Integer fs = this.settings.getFrameSize();
		if (fs != null) {
			this.frameSizeField.setInput(new Object[] { fs });
		}

		this.frameSizeField.addSelectionChangedListener(new SelectAllTextComboTextListener(this.frameSizeField.getCombo()));
		
		addDataBindings();
	}

	protected void addDataBindings() {
		IObservableValue frameSizeWidgetValue = WidgetProperties.selection().observeDelayed(FIELD_BINDING_DELAY, this.frameSizeField.getCombo());
		IObservableValue frameSizeModelValue = PojoProperties.value("frameSize").observe(this.settings);
//		dataBindingCtx.bindValue(fsTargetObservableVal, fsModelObservableVal, createTargetToModelForFrameSize(), null);
		Binding fsBindValue = dataBindingCtx.bindValue(frameSizeWidgetValue, frameSizeModelValue);
		
		ControlDecorationSupport.create(fsBindValue, SWT.TOP | SWT.LEFT);
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
		
		return updateValueStrategy;
	}
}

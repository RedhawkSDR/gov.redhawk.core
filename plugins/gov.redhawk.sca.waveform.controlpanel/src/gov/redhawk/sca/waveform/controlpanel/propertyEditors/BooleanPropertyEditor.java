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

package gov.redhawk.sca.waveform.controlpanel.propertyEditors;

import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaSimpleProperty;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

public class BooleanPropertyEditor extends PropertyEditor {

	private final boolean value;
	private final String name;
	private final ScaAbstractProperty< ? > prop;
	private Button t;
	private Button f;
	private Group radioGroup;
	private EMFDataBindingContext context;

	private class BooleanUpdateStrategy extends UpdateValueStrategy {
		@Override
		public Object convert(final Object value) {
			final Boolean selected = (Boolean) value;
			BooleanPropertyEditor.this.f.setSelection(!selected);
			return super.convert(value);
		}
	}

	BooleanPropertyEditor(final String name, final Boolean value, final ScaAbstractProperty< ? > prop) {
		this.value = value;
		this.name = name;
		this.prop = prop;
	}

	@Override
	public void renderNameValuePair(final Composite parent) {
		final Label label = new Label(parent, SWT.NONE);
		label.setText(this.name);
		this.radioGroup = new Group(parent, SWT.NONE);
		this.radioGroup.setLayout(new FillLayout());
		this.t = new Button(this.radioGroup, SWT.RADIO);
		this.t.setText("True");
		this.t.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(final SelectionEvent e) {
				BooleanPropertyEditor.this.f.setSelection(!BooleanPropertyEditor.this.t.getSelection());
			}
		});
		this.f = new Button(this.radioGroup, SWT.RADIO);
		this.f.setText("False");
		if (this.value) {
			this.t.setSelection(true);
		} else {
			this.f.setSelection(true);
		}
		GridDataFactory.fillDefaults().grab(false, false).applyTo(label);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(this.radioGroup);
	}

	@Override
	public void setupBindings() {
		this.context = new EMFDataBindingContext();
		final ScaSimplePropertyControl scaSimplePropertyControl = new ScaSimplePropertyControl(this.radioGroup, (ScaSimpleProperty) this.prop);
		final ISWTObservableValue trueObservable = WidgetProperties.selection().observe(this.t);
		this.context.bindValue(trueObservable, scaSimplePropertyControl.getModel());
		this.context.bindValue(SWTObservables.observeSelection(this.t), scaSimplePropertyControl.getEditingObserable());
		this.context.bindValue(scaSimplePropertyControl.getTarget(),
		        EMFObservables.observeValue(this.prop, ScaPackage.Literals.SCA_SIMPLE_PROPERTY__VALUE),
		        null,
		        new BooleanUpdateStrategy());
	}

	@Override
	public void dispose() {
		this.context.dispose();
	}

}

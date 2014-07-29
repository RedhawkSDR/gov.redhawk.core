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

import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class StringPropertyEditor extends PropertyEditor {

	private final String name;
	private final String value;
	private final ScaAbstractProperty< ? > prop;
	private Text text;
	private EMFDataBindingContext context;

	public StringPropertyEditor(final String name, final String value, final ScaAbstractProperty< ? > prop) {
		this.name = name;
		this.value = value;
		this.prop = prop;
	}

	@Override
	public void renderNameValuePair(final Composite parent) {
		final Label label = new Label(parent, SWT.NONE);
		label.setText(this.name);
		this.text = new Text(parent, SWT.BORDER);
		if (this.value != null) {
			this.text.setText(this.value);
		} else {
			this.text.setText("");
		}
		GridDataFactory.fillDefaults().grab(false, false).applyTo(label);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(this.text);
	}

	@Override
	public void setupBindings() {
		this.context = new EMFDataBindingContext();
		final ScaSimplePropertyControl scaSimplePropertyControl = new ScaSimplePropertyControl(this.text, (ScaSimpleProperty) this.prop);
		final ISWTObservableValue textObservable = WidgetProperties.text(new int[] {
		        SWT.FocusOut, SWT.DefaultSelection
		}).observe(this.text);
		this.context.bindValue(textObservable, scaSimplePropertyControl.getModel());
		this.context.bindValue(WidgetProperties.text(SWT.Modify).observeDelayed(200, this.text), scaSimplePropertyControl.getEditingObserable());
		this.context.bindValue(scaSimplePropertyControl.getTarget(), EMFObservables.observeValue(this.prop, ScaPackage.Literals.SCA_SIMPLE_PROPERTY__VALUE));
	}

	@Override
	public void dispose() {
		this.context.dispose();
	}

}

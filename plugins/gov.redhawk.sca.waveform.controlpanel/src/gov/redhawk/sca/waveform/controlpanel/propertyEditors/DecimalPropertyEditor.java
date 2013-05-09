/**
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */

package gov.redhawk.sca.waveform.controlpanel.propertyEditors;

import gov.redhawk.common.ui.widgets.Dval;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaSimpleProperty;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import mil.jpeojtrs.sca.prf.Enumeration;
import mil.jpeojtrs.sca.prf.Enumerations;
import mil.jpeojtrs.sca.prf.Range;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

public class DecimalPropertyEditor< T extends Number > extends PropertyEditor {

	private static final double INCR_1 = 0.1;
	private static final double INCR_2 = 5;
	private static final double INCR_3 = 10;
	private static final double MIN_1 = 10;
	private static final double MIN_2 = 100;
	private static final double PAGE_INCR_MULTIPLIER = 5;
	private static final double MAX_VALUE_NO_SCI_NOTATION = 10E6;
	private static final double IBCR_0 = 1000;
	private static final int UPDATE_DELAY_MS = 200;
	private final String name;
	private Number value;
	private Control control;
	private final ScaSimpleProperty prop;
	private Dval dval;
	private final DecimalFormat form = (DecimalFormat) NumberFormat.getInstance();
	private Class<T> valueType;
	private EMFDataBindingContext context;
	private ComboViewer enumViewer;

	private class DecimalUpdateValueStrategy extends UpdateValueStrategy {

		@Override
		public Object convert(final Object value) {
			if (value instanceof Number) {
				final String pattern = getFormatPattern();
				DecimalPropertyEditor.this.form.applyPattern(pattern);
				final Number d = (Number) value;
				final String s = DecimalPropertyEditor.this.form.format(d);
				try {
					return DecimalPropertyEditor.this.form.parse(s);
				} catch (final ParseException e) {
					return value;
				}
			}
			return value;
		}
	}

	private class DecimalUpdateValueStrategy2 extends UpdateValueStrategy {

		@Override
		public Object convert(final Object value) {
			if (value instanceof String) {
				final String s = (String) value;
				try {
					final String pattern = getFormatPattern();
					DecimalPropertyEditor.this.form.applyPattern(pattern);
					if (DecimalPropertyEditor.this.valueType == Float.class) {
						return DecimalPropertyEditor.this.form.parse(s).floatValue();
					} else {
						return DecimalPropertyEditor.this.form.parse(s).doubleValue();
					}
				} catch (final NumberFormatException e) {
					return value;
				} catch (final ParseException e) {
					return value;
				}
			}
			return value;
		}
	}

	private class DecimalUpdateValueStrategy3 extends UpdateValueStrategy {

		@Override
		public Object convert(final Object value) {
			if (value instanceof Number) {
				final String pattern = getFormatPattern();
				DecimalPropertyEditor.this.form.applyPattern(pattern);
				final Number d = (Number) value;
				final String s = DecimalPropertyEditor.this.form.format(d);
				return s;
			}
			return value;
		}
	}

	private class SelectionToLocalValueStrategy extends UpdateValueStrategy {

		@Override
		public Object convert(final Object value) {
			if (value instanceof String) {
				try {
					final Enumeration enumer = getEnumFromLabel((String) value);
					DecimalPropertyEditor.this.form.applyPattern(getFormatPattern());
					return DecimalPropertyEditor.this.form.parse(enumer.getValue());
				} catch (final ParseException e) {
					return value;
				}
			}
			return value;
		}
	}

	private class LocalToSelectionValueStrategy extends UpdateValueStrategy {

		@Override
		public Object convert(final Object value) {
			if (value instanceof Number) {
				DecimalPropertyEditor.this.enumViewer.setSelection(new StructuredSelection(getEnumFromValue((Number) value)));
			}
			return value;
		}
	}

	public Enumeration getEnumFromLabel(final String value) {
		final Enumerations enumValues = (Enumerations) this.enumViewer.getInput();
		for (final Enumeration enumer : enumValues.getEnumeration()) {
			if (enumer.getLabel().equals(value)) {
				return enumer;
			}
		}
		return null;
	}

	public Object getEnumFromValue(final Number value) {
		final Enumerations enumValues = (Enumerations) this.enumViewer.getInput();
		for (final Enumeration enumer : enumValues.getEnumeration()) {
			if (this.valueType == Double.class) {
				final Double val = new Double(enumer.getValue());
				if (val.doubleValue() == value.doubleValue()) {
					return enumer;
				}
			} else if (this.valueType == Float.class) {
				final Float val = new Float(enumer.getValue());
				if (val.floatValue() == value.floatValue()) {
					return enumer;
				}
			}
		}
		return value;
	}

	@SuppressWarnings("unchecked")
	public DecimalPropertyEditor(final String name, final Number value, final ScaSimpleProperty prop) {
		this.name = name;
		this.value = value;
		this.prop = prop;
		if (value instanceof Float) {
			this.valueType = (Class<T>) Float.class;
		} else {
			this.valueType = (Class<T>) Double.class;
		}
	}

	public String getFormatPattern() {
		String pattern = "";
		if (this.value.doubleValue() > DecimalPropertyEditor.MAX_VALUE_NO_SCI_NOTATION) {
			if (this.valueType == Float.class) {
				pattern = "##0.###E0#";
			} else {
				pattern = "##0.######E0#";
			}
		} else {
			pattern = "#######0.##";
		}
		if (this.dval != null) {
			this.dval.applyFormat(pattern);
		}
		return pattern;
	}

	@Override
	public void setupBindings() {
		this.context = new EMFDataBindingContext();
		if (this.enumViewer == null) {
			final ScaSimplePropertyControl scaSimplePropertyControl = new ScaSimplePropertyControl(this.control, this.prop);
			final ISWTObservableValue textObservable = WidgetProperties.text(new int[] { SWT.FocusOut, SWT.DefaultSelection }).observe(this.control);
			this.context.bindValue(textObservable, scaSimplePropertyControl.getModel(), new DecimalUpdateValueStrategy2(), new DecimalUpdateValueStrategy3());
			this.context.bindValue(WidgetProperties.text(SWT.Modify).observeDelayed(UPDATE_DELAY_MS, this.control), scaSimplePropertyControl.getEditingObserable());
			this.context.bindValue(scaSimplePropertyControl.getTarget(), EMFObservables.observeValue(this.prop, ScaPackage.Literals.SCA_SIMPLE_PROPERTY__VALUE),
			        new DecimalUpdateValueStrategy(), new DecimalUpdateValueStrategy());
		} else {
			final ScaSimplePropertyControl scaSimplePropertyControl = new ScaSimplePropertyControl(this.enumViewer.getControl(), this.prop);
			final ISWTObservableValue selectObservable = WidgetProperties.selection().observe(this.enumViewer.getCombo());
			this.context.bindValue(selectObservable, scaSimplePropertyControl.getModel(), new SelectionToLocalValueStrategy(), new LocalToSelectionValueStrategy());
			this.context.bindValue(SWTObservables.observeSelection(this.enumViewer.getCombo()), scaSimplePropertyControl.getEditingObserable(),
			        new SelectionToLocalValueStrategy(), null);
			this.context.bindValue(scaSimplePropertyControl.getTarget(), EMFObservables.observeValue(this.prop, ScaPackage.Literals.SCA_SIMPLE_PROPERTY__VALUE),
			        new DecimalUpdateValueStrategy(), null);
		}
	}

	@Override
	public void renderNameValuePair(final Composite parent) {
		final Label label = new Label(parent, SWT.NONE);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(label);
		label.setText(this.name);
		final Range range = this.prop.getDefinition().getRange();
		//Dval not working properly on RAP
		if (range != null && !SWT.getPlatform().startsWith("rap")) {
			this.dval = new Dval(parent, SWT.BORDER);
			this.control = this.dval.getTextBox();
			final Double min = Double.parseDouble(range.getMin());
			final Double max = Double.parseDouble(range.getMax());
			this.dval.applyFormat(getFormatPattern());
			this.dval.setValue(this.value.doubleValue());
			this.dval.setMinimum(min);
			this.dval.setMaximum(max);
			double incr = 0;
			if (this.value.doubleValue() > DecimalPropertyEditor.MAX_VALUE_NO_SCI_NOTATION) {
				incr = DecimalPropertyEditor.IBCR_0;
			} else {
				if (max - min < 1) {
					incr = DecimalPropertyEditor.INCR_1;
				} else if (max - min < DecimalPropertyEditor.MIN_1) {
					incr = 1;
				} else if (max - min < DecimalPropertyEditor.MIN_2) {
					incr = DecimalPropertyEditor.INCR_2;
				} else {
					incr = DecimalPropertyEditor.INCR_3;
				}
			}
			this.dval.setIncrement(incr);
			this.dval.setPageIncrement(incr * DecimalPropertyEditor.PAGE_INCR_MULTIPLIER);
			GridDataFactory.fillDefaults().grab(true, false).applyTo(this.dval);
		} else {
			final Enumerations values = this.prop.getDefinition().getEnumerations();
			if (values == null || values.getEnumeration() == null || values.getEnumeration().size() == 0) {
				this.control = new Text(parent, SWT.BORDER);
				GridDataFactory.fillDefaults().grab(true, false).applyTo(this.control);
				this.form.applyPattern(getFormatPattern());
				((Text) this.control).setText(this.form.format(this.value));
			} else {
				this.enumViewer = new ComboViewer(parent, SWT.NONE);
				this.enumViewer.getCombo().addListener(SWT.MouseVerticalWheel, new Listener() {

					public void handleEvent(Event event) {
						// Disable Mouse Wheel Combo Box Control
						event.doit = false;
					}

				});
				GridDataFactory.fillDefaults().grab(true, false).applyTo(this.enumViewer.getControl());
				this.enumViewer.setContentProvider(new EnumValuesContentProvider());
				this.enumViewer.setLabelProvider(new EnumValuesLabelProvider());
				this.enumViewer.setInput(this.prop.getDefinition().getEnumerations());
				this.enumViewer.addSelectionChangedListener(new ISelectionChangedListener() {

					public void selectionChanged(final SelectionChangedEvent event) {
						final Enumeration sel = (Enumeration) ((StructuredSelection) event.getSelection()).getFirstElement();
						DecimalPropertyEditor.this.form.applyPattern(getFormatPattern());
						try {
							DecimalPropertyEditor.this.value = DecimalPropertyEditor.this.form.parse(sel.getValue());
						} catch (final ParseException e) {
							//PASS
						}
					}

				});
			}
		}
	}

	@Override
	public void dispose() {
		if (this.context != null) {
			this.context.dispose();
		}
	}

}

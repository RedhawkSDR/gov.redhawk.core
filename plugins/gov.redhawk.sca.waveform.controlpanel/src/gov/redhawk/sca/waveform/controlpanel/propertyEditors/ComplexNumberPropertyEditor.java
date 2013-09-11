/**
 * 
 */
package gov.redhawk.sca.waveform.controlpanel.propertyEditors;

import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.sca.observables.strategy.StringToComplexNumberConverter;
import mil.jpeojtrs.sca.prf.PropertyValueType;
import mil.jpeojtrs.sca.util.math.ComplexByte;
import mil.jpeojtrs.sca.util.math.ComplexDouble;
import mil.jpeojtrs.sca.util.math.ComplexFloat;
import mil.jpeojtrs.sca.util.math.ComplexLong;
import mil.jpeojtrs.sca.util.math.ComplexLongLong;
import mil.jpeojtrs.sca.util.math.ComplexNumber;
import mil.jpeojtrs.sca.util.math.ComplexShort;
import mil.jpeojtrs.sca.util.math.ComplexUByte;
import mil.jpeojtrs.sca.util.math.ComplexULong;
import mil.jpeojtrs.sca.util.math.ComplexULongLong;
import mil.jpeojtrs.sca.util.math.ComplexUShort;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 *
 */
public class ComplexNumberPropertyEditor extends PropertyEditor {

	private String name;
	private ComplexNumber value;
	private final ScaSimpleProperty prop;
	private Control control;
	private EMFDataBindingContext context;
	private PropertyValueType valueType;
	
	private class ComplexNumberUpdateValueStrategy extends UpdateValueStrategy {

		@Override
		public Object convert(final Object value) {
			return value.toString();
		}
	}

	public ComplexNumberPropertyEditor(String name, ComplexNumber value, ScaSimpleProperty prop) {
		this.name = name;
		this.value = value;
		this.prop = prop;
		if (value instanceof ComplexLong) {
			this.valueType = PropertyValueType.LONG;
		} else if (value instanceof ComplexShort) {
			this.valueType = PropertyValueType.SHORT;
		} else if (value instanceof ComplexByte) {
			this.valueType = PropertyValueType.OCTET;
		} else if (value instanceof ComplexUByte) {
			this.valueType = PropertyValueType.OCTET;
		} else if (value instanceof ComplexLongLong) {
			this.valueType = PropertyValueType.LONGLONG;
		} else if (value instanceof ComplexFloat) {
			this.valueType = PropertyValueType.FLOAT;
		} else if (value instanceof ComplexDouble) {
			this.valueType = PropertyValueType.DOUBLE;
		} else if (value instanceof ComplexULongLong) {
			this.valueType = PropertyValueType.ULONGLONG;
		} else if (value instanceof ComplexULong) {
			this.valueType = PropertyValueType.ULONG;
		} else if (value instanceof ComplexUShort) {
			this.valueType = PropertyValueType.USHORT;
		}
	}
	
	@Override
	public void renderNameValuePair(Composite parent) {
		final Label label = new Label(parent, SWT.NONE);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(label);
		label.setText(this.name);
		this.control = new Text(parent, SWT.BORDER);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(this.control);
		((Text) this.control).setText(this.value.toString());
		
	}

	@Override
	public void setupBindings() {
		this.context = new EMFDataBindingContext();
		
		final ScaSimplePropertyControl scaSimplePropertyControl = new ScaSimplePropertyControl(this.control, this.prop);
		final ISWTObservableValue textObservable = WidgetProperties.text(new int[] { SWT.FocusOut, SWT.DefaultSelection }).observe(this.control);
		this.context.bindValue(textObservable, scaSimplePropertyControl.getModel(), 
				new UpdateValueStrategy().setConverter(new StringToComplexNumberConverter(this.valueType)), new ComplexNumberUpdateValueStrategy());
		this.context.bindValue(WidgetProperties.text().observeDelayed(200, this.control), scaSimplePropertyControl.getEditingObserable(),
		        new UpdateValueStrategy().setConverter(new StringToComplexNumberConverter(this.valueType)),new ComplexNumberUpdateValueStrategy());
		this.context.bindValue(scaSimplePropertyControl.getTarget(), EMFObservables.observeValue(this.prop, ScaPackage.Literals.SCA_SIMPLE_PROPERTY__VALUE),
		        new UpdateValueStrategy().setConverter(new StringToComplexNumberConverter(this.valueType)),new ComplexNumberUpdateValueStrategy());
	}

	@Override
	public void dispose() {
		if (this.context != null) {
			this.context.dispose();
		}
	}
	
}

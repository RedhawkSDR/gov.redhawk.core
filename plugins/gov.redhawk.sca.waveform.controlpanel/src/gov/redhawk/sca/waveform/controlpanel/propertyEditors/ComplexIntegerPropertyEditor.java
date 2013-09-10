/**
 * 
 */
package gov.redhawk.sca.waveform.controlpanel.propertyEditors;

import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.sca.observables.strategy.StringToComplexNumberConverter;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import mil.jpeojtrs.sca.prf.PropertyValueType;
import mil.jpeojtrs.sca.util.math.ComplexLong;
import mil.jpeojtrs.sca.util.math.ComplexNumber;
import mil.jpeojtrs.sca.util.math.ComplexShort;

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
 * @author dch
 *
 */
public class ComplexIntegerPropertyEditor<T extends ComplexNumber> extends PropertyEditor {

	private String name;
	private ComplexNumber value;
	private final ScaSimpleProperty prop;
	private Class<T> valueType;
	private Control control;
	private EMFDataBindingContext context;
	private final DecimalFormat form = (DecimalFormat) NumberFormat.getInstance();
	private static final double MAX_VALUE_NO_SCI_NOTATION = 10E6;

	private class ComplexIntegerUpdateValueStrategy extends UpdateValueStrategy {

		@Override
		public Object convert(final Object value) {
			return value.toString();
		}
	}

	@SuppressWarnings("unchecked")
	public ComplexIntegerPropertyEditor(String name, ComplexNumber value, ScaSimpleProperty prop) {
		this.name = name;
		this.value = value;
		this.prop = prop;
		if (value instanceof ComplexLong) {
			this.valueType = (Class<T>) ComplexLong.class;
		} else if (value instanceof ComplexShort) {
			this.valueType = (Class<T>) ComplexShort.class;
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
				new UpdateValueStrategy().setConverter(new StringToComplexNumberConverter(PropertyValueType.LONG)), new ComplexIntegerUpdateValueStrategy());
		this.context.bindValue(WidgetProperties.text().observeDelayed(200, this.control), scaSimplePropertyControl.getEditingObserable(),
		        new UpdateValueStrategy().setConverter(new StringToComplexNumberConverter(PropertyValueType.LONG)),new ComplexIntegerUpdateValueStrategy());
		this.context.bindValue(scaSimplePropertyControl.getTarget(), EMFObservables.observeValue(this.prop, ScaPackage.Literals.SCA_SIMPLE_PROPERTY__VALUE),
		        new UpdateValueStrategy().setConverter(new StringToComplexNumberConverter(PropertyValueType.LONG)),new ComplexIntegerUpdateValueStrategy());
//		        null,null);
	}

	@Override
	public void dispose() {
		if (this.context != null) {
			this.context.dispose();
		}
	}
	
}

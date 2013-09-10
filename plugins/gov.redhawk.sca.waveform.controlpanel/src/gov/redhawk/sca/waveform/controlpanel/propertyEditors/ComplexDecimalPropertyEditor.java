/**
 * 
 */
package gov.redhawk.sca.waveform.controlpanel.propertyEditors;

import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import mil.jpeojtrs.sca.util.math.ComplexDouble;
import mil.jpeojtrs.sca.util.math.ComplexFloat;
import mil.jpeojtrs.sca.util.math.ComplexNumber;
import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaSimpleProperty;

/**
 * @author dch
 *
 */
public class ComplexDecimalPropertyEditor<T extends ComplexNumber> extends PropertyEditor {

	private String name;
	private ComplexNumber value;
	private final ScaSimpleProperty prop;
	private Class<T> valueType;
	private Control control;
	private EMFDataBindingContext context;

	@SuppressWarnings("unchecked")
	public ComplexDecimalPropertyEditor(String name, ComplexNumber value, ScaSimpleProperty prop) {
		this.name = name;
		this.value = value;
		this.prop = prop;
		if (value instanceof ComplexFloat) {
			this.valueType = (Class<T>) ComplexFloat.class;
		} else {
			this.valueType = (Class<T>) ComplexDouble.class;
		}
	}
	
	@Override
	public void renderNameValuePair(Composite parent) {
		final Label label = new Label(parent, SWT.NONE);
		GridDataFactory.fillDefaults().grab(false, false).applyTo(label);
		label.setText(this.name);
		this.control = new Text(parent, SWT.BORDER);
		
	}

	@Override
	public void setupBindings() {
		this.context = new EMFDataBindingContext();
		
	}

	@Override
	public void dispose() {
		if (this.context != null) {
			this.context.dispose();
		}
	}
	
}

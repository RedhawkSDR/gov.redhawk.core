package gov.redhawk.frontend.ui.wizard;

import gov.redhawk.frontend.edit.utils.TunerProperties.ListenerAllocationProperties;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.sca.observables.SCAObservables;

import java.util.UUID;

import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.Simple;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.dialogs.DialogPage;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;


public class ListenerAllocationWizardPage extends WizardPage {

	private Text allocIdText;
	private ScaStructProperty listenerAllocationStruct;
	private UUID uuid;
	private EMFDataBindingContext context;

	private static final String ALLOC_ID_CONTINS_COMMA_ERR_MSG = "Allocation ID must not contain a comma";
	private static final String ALLOC_ID_MISSING = "Please provide an allocation ID. Any text, excludig commas and colons is acceptable.";
	private static final String TARGET_ID_MISSING = "Please enter an existing Allocation ID.";


	private Text targetAllocText;
	private String targetId;	

	private class TargetableValidator implements IValidator {

		private Control control;

		public TargetableValidator(Control control) {
			this.control = control;
		}

		@Override
		public IStatus validate(Object value) {
			return getValidationStatus(control, value);
		}
	}

	private class TargetableFocusListener implements FocusListener {

		private Control control;

		public TargetableFocusListener(Control control) {
			this.control = control;
		}

		@Override
		public void focusGained(FocusEvent e) {
			int pageStatus = DialogPage.NONE;
			String msg = null;

			String value = null;
			if (control instanceof Text) {
				value = ((Text) control).getText();
			} else if (control instanceof Combo) {
				Combo combo = (Combo) control;
				value = combo.getItem(combo.getSelectionIndex());
			}
			IStatus status = getValidationStatus(control, value);

			switch (status.getSeverity()) {
			case Status.OK:
				//PASS
				break;
			case Status.WARNING:
				pageStatus = DialogPage.WARNING;
				msg = status.getMessage();
				break;
			case Status.ERROR:
				pageStatus = DialogPage.ERROR;
				msg = status.getMessage();
				break;
			default:
			}
			setMessage(msg, pageStatus);
		}


		@Override
		public void focusLost(FocusEvent e) {
			//PASS
		}

	}

	public IStatus getValidationStatus(Control control, Object value) {
		String s = (String) value;
		if (control == allocIdText) {
			if ("".equals(s)) {
				return ValidationStatus.error(ALLOC_ID_MISSING);
			}
			if (s.contains(",")) {
				return ValidationStatus.error(ALLOC_ID_CONTINS_COMMA_ERR_MSG);
			}
			return ValidationStatus.OK_STATUS;
		} else if (control == targetAllocText) {
			if ("".equals(s)) {
				return ValidationStatus.error(TARGET_ID_MISSING);
			}
		}
		return ValidationStatus.OK_STATUS;
	}

	protected ListenerAllocationWizardPage(String targetId) {
		super("Allocate A Tuner");
		this.targetId = targetId;
	}

	@Override
	public void createControl(Composite parent) {
		Composite comp = new Composite(parent, SWT.NONE);
		createGroupControls(comp);
		setControl(comp);

		setTitle("Listener Allocation");
		setDescription("Change the Allocation ID if desired.");
		this.uuid = UUID.randomUUID();
		initializeListenerAllocationStruct();
		context = new EMFDataBindingContext();
		addBindings();
		WizardPageSupport.create(this, context);
	}

	private void addBindings() {

		//allocation ID Text
		UpdateValueStrategy allocIdStrategy = new UpdateValueStrategy() {
			@Override
			public Object convert(Object value) {
				return (String) value;
			}
		};
		allocIdStrategy.setAfterGetValidator(new TargetableValidator(allocIdText));
		ControlDecorationSupport.create(context.bindValue(WidgetProperties.text(SWT.Modify).observe(allocIdText),
				SCAObservables.observeSimpleProperty(listenerAllocationStruct.getSimple(ListenerAllocationProperties.LISTENER_ALLOCATION_ID.getId())),
				allocIdStrategy,
				null),
				SWT.TOP | SWT.LEFT);
		allocIdText.setText(getUsername() + ":" + uuid.toString());
		allocIdText.addFocusListener(new TargetableFocusListener(allocIdText));

		//Target Allocation ID
		ControlDecorationSupport.create(context.bindValue(WidgetProperties.text(SWT.Modify).observe(targetAllocText),
				SCAObservables.observeSimpleProperty(listenerAllocationStruct.getSimple(ListenerAllocationProperties.EXISTING_ALLOCATION_ID.getId())),
				null,
				null),
				SWT.TOP | SWT.LEFT);
		targetAllocText.setText(targetId);
	}

	private String getUsername() {
		return System.getProperty("user.name");
	}

	private void initializeListenerAllocationStruct() {
		listenerAllocationStruct = ScaFactory.eINSTANCE.createScaStructProperty();
		for (ListenerAllocationProperties allocProp : ListenerAllocationProperties.values()) {
			ScaSimpleProperty simple = ScaFactory.eINSTANCE.createScaSimpleProperty();
			Simple definition = (Simple) PrfFactory.eINSTANCE.create(PrfPackage.Literals.SIMPLE);
			definition.setType(allocProp.getType());
			definition.setId(allocProp.getType().getLiteral());
			definition.setName(allocProp.getType().getName());
			simple.setDefinition(definition);
			simple.setId(allocProp.getId());
			setValueForProp(allocProp, simple);
			listenerAllocationStruct.getSimples().add(simple);
		}
	}

	private void createGroupControls(Composite parent) {
		GridLayoutFactory.fillDefaults().numColumns(2).applyTo(parent);

		Label allocIdLabel = new Label(parent, SWT.NONE);
		allocIdLabel.setText("Your Allocation ID");
		allocIdText = new Text(parent, SWT.BORDER);
		allocIdText.setToolTipText("Enter any ID for ease of reference. Additional characters will be appended after this name, to ensure uniqueness");
		GridDataFactory.fillDefaults().grab(true, false).applyTo(allocIdText);

		Label targetAllocLabel = new Label(parent, SWT.NONE);
		targetAllocLabel.setText("Existing Tuner Allocation ID");
		targetAllocText = new Text(parent, SWT.BORDER);
		targetAllocText.setToolTipText("This is the Allocation ID of the Tuner to which you are adding a Listener Allocation");
		targetAllocText.setEnabled(false);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(targetAllocText);

	}

	private void setValueForProp(ListenerAllocationProperties allocProp,
			ScaSimpleProperty simple) {
		switch (allocProp) {
		case EXISTING_ALLOCATION_ID:
			simple.setValue(targetAllocText.getText());
			break;
		case LISTENER_ALLOCATION_ID:
			simple.setValue(allocIdText.getText());
			break;
		default:
		}
	}

	public ScaStructProperty getListenerAllocationStruct() {
		return this.listenerAllocationStruct;
	}
}

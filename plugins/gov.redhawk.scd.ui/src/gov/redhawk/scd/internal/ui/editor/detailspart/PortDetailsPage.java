package gov.redhawk.scd.internal.ui.editor.detailspart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.ReplaceCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ICheckStateProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import gov.redhawk.common.ui.editor.FormLayoutFactory;
import gov.redhawk.common.ui.parts.FormEntry;
import gov.redhawk.ui.editor.SCAFormEditor;
import gov.redhawk.ui.editor.ScaDetails;
import gov.redhawk.ui.editor.ScaFormPage;
import mil.jpeojtrs.sca.scd.AbstractPort;
import mil.jpeojtrs.sca.scd.PortType;
import mil.jpeojtrs.sca.scd.PortTypeContainer;
import mil.jpeojtrs.sca.scd.Ports;
import mil.jpeojtrs.sca.scd.ScdFactory;
import mil.jpeojtrs.sca.scd.ScdPackage;
import mil.jpeojtrs.sca.scd.Uses;

public class PortDetailsPage extends ScaDetails {

	private class PortTypeAdapter extends AdapterImpl implements ICheckStateListener, ICheckStateProvider {

		@Override
		public boolean isGrayed(Object element) {
			if (element == PortType.CONTROL) {
				AbstractPort port = (AbstractPort) getTarget();
				return port == null || port.getPortType().isEmpty();
			}
			return false;
		}

		@Override
		public boolean isChecked(Object element) {
			AbstractPort port = (AbstractPort) getTarget();
			if (port == null) {
				return false;
			}
			if (element == PortType.CONTROL) {
				if (port.getPortType().isEmpty()) {
					return true;
				}
			}
			for (PortTypeContainer container : port.getPortType()) {
				if (container.getType().equals(element)) {
					return true;
				}
			}
			return false;
		}

		@Override
		public void checkStateChanged(final CheckStateChangedEvent event) {
			AbstractPort port = (AbstractPort) getTarget();
			if (port != null) {
				EditingDomain domain = PortDetailsPage.this.getEditingDomain();
				Object type = event.getElement();
				Command command;
				if (event.getChecked()) {
					command = AddCommand.create(domain, port, ScdPackage.Literals.ABSTRACT_PORT__PORT_TYPE, type);
				} else {
					command = RemoveCommand.create(domain, port, ScdPackage.Literals.ABSTRACT_PORT__PORT_TYPE, type);
				}
				domain.getCommandStack().execute(command);
			}
		}

		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getFeature() == ScdPackage.Literals.ABSTRACT_PORT__PORT_TYPE) {
				PortDetailsPage.this.typeTable.refresh();
			}
			super.notifyChanged(msg);
		}
	};

	private static enum PortDirection {
		PROVIDES("in <provides>"),
		USES("out <uses>"),
		BIDIR("bidir <uses/provides>");

		private final String label;

		private PortDirection(String label) {
			this.label = label;
		}

		@Override
		public String toString() {
			return label;
		}

		public static PortDirection getDirection(AbstractPort port) {
			if (port.isBiDirectional()) {
				return PortDirection.BIDIR;
			}
			return getInstanceDirection(port);
		}

		public static PortDirection getInstanceDirection(AbstractPort port) {
			if (port instanceof Uses) {
				return PortDirection.USES;
			} else {
				return PortDirection.PROVIDES;
			}
		}
	};

	private static final int NUM_COLUMNS = 3;

	private FormEntry nameEntry;
	private ComboViewer directionCombo;
	private CheckboxTableViewer typeTable;
	private Text descriptionText;
	private FormEntry idlEntry;

	private AbstractPort port;

	private PortTypeAdapter portTypeAdapter = new PortTypeAdapter();

	public PortDetailsPage(ScaFormPage parentPage) {
		super(parentPage);
	}

	@Override
	public void dispose() {
		removePortTypeListener();
		super.dispose();
	}

	private void removePortTypeListener() {
		if (port != null) {
			port.eAdapters().remove(portTypeAdapter);
		}
	}

	@Override
	protected void createSpecificContent(Composite parent) {
		final Section section = getToolkit().createSection(parent, Section.EXPANDED | Section.TITLE_BAR);
		section.clientVerticalSpacing = FormLayoutFactory.SECTION_HEADER_VERTICAL_SPACING;
		section.setText("Port Details");
		section.setDescription("Enter port details");
		section.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).create());

		Composite client = getToolkit().createComposite(section);
		section.setClient(client);

		createPortDetailsArea(client, getToolkit());

		getToolkit().paintBordersFor(client);
	}

	@Override
	protected List<Binding> bind(DataBindingContext dataBindingContext, EObject input) {
		removePortTypeListener();
		port = (AbstractPort) input;
		port.eAdapters().add(portTypeAdapter);
		typeTable.refresh();

		final List<Binding> bindings = new ArrayList<Binding>();

		bindings.add(dataBindingContext.bindValue(WidgetProperties.text(SWT.Modify).observeDelayed(SCAFormEditor.getFieldBindingDelay(), nameEntry.getText()),
			EMFEditObservables.observeValue(getEditingDomain(), input, ScdPackage.Literals.ABSTRACT_PORT__NAME)));

		directionCombo.setSelection(new StructuredSelection(PortDirection.getDirection((AbstractPort) input)));

		bindings.add(dataBindingContext.bindValue(WidgetProperties.text(SWT.Modify).observe(idlEntry.getText()),
			EMFEditObservables.observeValue(getEditingDomain(), input, ScdPackage.Literals.ABSTRACT_PORT__REP_ID)));

		bindings.add(dataBindingContext.bindValue(WidgetProperties.text(SWT.Modify).observeDelayed(SCAFormEditor.getFieldBindingDelay(), descriptionText),
			EMFEditObservables.observeValue(getEditingDomain(), input, ScdPackage.Literals.ABSTRACT_PORT__DESCRIPTION)));

		return bindings;
	}

	/**
	 * Creates the port details area.
	 * 
	 * @param client the client
	 * @param toolkit the toolkit
	 */
	private Composite createPortDetailsArea(final Composite client, final FormToolkit toolkit) {
		client.setLayout(FormLayoutFactory.createSectionClientGridLayout(false, NUM_COLUMNS));
		GridData data = GridDataFactory.fillDefaults().grab(true, true).create();
		client.setLayoutData(data);

		GridDataFactory gridDataFactory = GridDataFactory.fillDefaults().span(2, 1).grab(true, false);

		nameEntry = new FormEntry(client, toolkit, "Name*:", SWT.SINGLE);

		createLabel(client, toolkit, "Direction:");
		directionCombo = new ComboViewer(client);
		toolkit.adapt(directionCombo.getControl(), true, true);
		directionCombo.getControl().setLayoutData(gridDataFactory.create());
		directionCombo.setContentProvider(new ArrayContentProvider());
		directionCombo.setInput(PortDirection.values());
		directionCombo.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				PortDetailsPage.this.handlePortDirectionChange((PortDirection) selection.getFirstElement());
			}
		});

		createLabel(client, toolkit, "Type:");
		Table table = toolkit.createTable(client, SWT.CHECK);
		table.setLayoutData(gridDataFactory.create());
		typeTable = new CheckboxTableViewer(table);
		typeTable.setContentProvider(new ArrayContentProvider());
		typeTable.setLabelProvider(new LabelProvider());
		typeTable.setInput(PortType.VALUES.subList(0, PortType.VALUES.size() - 1));
		typeTable.setCheckStateProvider(portTypeAdapter);
		typeTable.addCheckStateListener(portTypeAdapter);

		idlEntry = new FormEntry(client, toolkit, "Interface:", "Browse...", false);
		idlEntry.getText().setEditable(false);

		createLabel(client, toolkit, "Description:");
		descriptionText = toolkit.createText(client, null, SWT.MULTI);
		descriptionText.setLayoutData(gridDataFactory.copy().hint(SWT.DEFAULT, 75).create());
		return client;
	}

	private Label createLabel(Composite client, FormToolkit toolkit, String text) {
		Label label = toolkit.createLabel(client, text);
		label.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
		label.setLayoutData(GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.BEGINNING).create());
		return label;
	}

	private void handlePortDirectionChange(PortDirection direction) {
		if (port != null) {
			PortDirection currentDirection = PortDirection.getDirection(port);
			if (currentDirection != direction) {
				EditingDomain domain = getEditingDomain();
				Command command = createPortDirectionChangeCommand(domain, direction);
				domain.getCommandStack().execute(new CommandWrapper("Change Direction", command.getDescription(), command));
			}
		}
	}

	private Command createPortDirectionChangeCommand(EditingDomain domain, PortDirection direction) {
		if (port.isBiDirectional()) {
			AbstractPort removedPort;
			if (PortDirection.getInstanceDirection(port) == direction) {
				removedPort = port.getSibling();
			} else {
				removedPort = port;
			}
			return RemoveCommand.create(domain, removedPort);
		} else {
			AbstractPort sibling = createSibling(port);
			if (direction == PortDirection.BIDIR){
				Ports ports = (Ports) port.eContainer();
				return AddCommand.create(domain, ports, null, sibling);
			} else {
				return ReplaceCommand.create(domain, port, Collections.singleton(sibling));
			}
		}
	}

	private AbstractPort createSibling(AbstractPort port) {
		AbstractPort sibling;
		if (port instanceof Uses) {
			sibling = ScdFactory.eINSTANCE.createProvides();
		} else {
			sibling = ScdFactory.eINSTANCE.createUses();
		}
		sibling.setDescription(port.getDescription());
		sibling.setName(port.getName());
		sibling.setRepID(port.getRepID());
		sibling.getPortType().addAll(EcoreUtil.copyAll(port.getPortType()));
		return sibling;
	}
}

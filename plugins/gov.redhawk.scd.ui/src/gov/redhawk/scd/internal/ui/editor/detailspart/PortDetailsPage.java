package gov.redhawk.scd.internal.ui.editor.detailspart;

import java.util.List;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
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
import gov.redhawk.ui.editor.ScaDetails;
import gov.redhawk.ui.editor.ScaFormPage;
import mil.jpeojtrs.sca.scd.PortType;

public class PortDetailsPage extends ScaDetails {

	private static final int NUM_COLUMNS = 2;

	private Text nameText;
	private ComboViewer directionCombo; 
	private CheckboxTableViewer typeTable;
	private Text descriptionText;

	public PortDetailsPage(ScaFormPage parentPage) {
		super(parentPage);
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
		// TODO Auto-generated method stub
		return null;
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

		GridDataFactory gridDataFactory = GridDataFactory.fillDefaults().span(1, 1).grab(true, false);

		createLabel(client, toolkit, "Name*:");
		nameText = toolkit.createText(client, null, SWT.SINGLE);
		nameText.setLayoutData(gridDataFactory.create());

		createLabel(client, toolkit, "Direction:");
		directionCombo = new ComboViewer(client);
		toolkit.adapt(directionCombo.getControl(), true, true);
		directionCombo.getControl().setLayoutData(gridDataFactory.create());
		directionCombo.setContentProvider(new ArrayContentProvider());

		createLabel(client, toolkit, "Type:");
		Table table = toolkit.createTable(client, SWT.CHECK);
		table.setLayoutData(gridDataFactory.create());
		typeTable = new CheckboxTableViewer(table);
		typeTable.setContentProvider(new ArrayContentProvider());
		typeTable.setLabelProvider(new LabelProvider());
		typeTable.setInput(PortType.VALUES.subList(0, PortType.VALUES.size() - 1));

		createLabel(client, toolkit, "Interface:");
		Text idlText = toolkit.createText(client, "IDL:TODO/todo:1.0", SWT.READ_ONLY);
		idlText.setEnabled(false);
		idlText.setLayoutData(gridDataFactory.create());

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
}

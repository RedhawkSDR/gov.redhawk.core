package gov.redhawk.scd.internal.ui.editor.detailspart;

import java.util.List;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Section;

import gov.redhawk.common.ui.editor.FormLayoutFactory;
import gov.redhawk.ui.editor.ScaDetails;
import gov.redhawk.ui.editor.ScaFormPage;

public class PortDetailsPage extends ScaDetails {

	public PortDetailsPage(ScaFormPage parentPage) {
		super(parentPage);
	}

	@Override
	protected void createSpecificContent(Composite parent) {
		final Section section = getToolkit().createSection(parent, Section.DESCRIPTION | ExpandableComposite.TITLE_BAR);
		section.clientVerticalSpacing = FormLayoutFactory.SECTION_HEADER_VERTICAL_SPACING;
		section.setText("Port Details");
		section.setDescription("Enter port details");
		
		Composite client = getToolkit().createComposite(section);
		section.setClient(client);
		
		getToolkit().paintBordersFor(client);
	}

	@Override
	protected List<Binding> bind(DataBindingContext dataBindingContext, EObject input) {
		// TODO Auto-generated method stub
		return null;
	}

}

package gov.redhawk.scd.internal.ui.editor;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IDetailsPageProvider;
import org.eclipse.ui.forms.IManagedForm;

import gov.redhawk.scd.internal.ui.editor.detailspart.PortDetailsPage;
import gov.redhawk.scd.ui.editor.page.PortsFormPage;
import gov.redhawk.ui.editor.SCAMasterDetailsBlock;
import gov.redhawk.ui.editor.ScaSection;
import mil.jpeojtrs.sca.scd.AbstractPort;

public class PortsBlock extends SCAMasterDetailsBlock {

	private PortsSection fSection;

	public PortsBlock(PortsFormPage page) {
		super(page);
	}

	@Override
	protected ScaSection createMasterSection(IManagedForm managedForm, Composite parent) {
		this.fSection = new PortsSection(this, parent);
		return this.fSection;
	}

	@Override
	protected void registerPages(DetailsPart detailsPart) {
		detailsPart.registerPage(AbstractPort.class, new PortDetailsPage(getPage()));
		detailsPart.setPageProvider(new IDetailsPageProvider() {
			
			@Override
			public Object getPageKey(Object object) {
				return AbstractPort.class;
			}
			
			@Override
			public IDetailsPage getPage(Object key) {
				return null;
			}
		});
	}

}

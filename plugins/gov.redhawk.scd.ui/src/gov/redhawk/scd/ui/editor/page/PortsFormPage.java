package gov.redhawk.scd.ui.editor.page;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.menus.IMenuService;

import gov.redhawk.scd.internal.ui.editor.PortsBlock;
import gov.redhawk.ui.editor.SCAFormEditor;
import gov.redhawk.ui.editor.ScaFormPage;

public class PortsFormPage extends ScaFormPage {
	private final PortsBlock fBlock;
	
	/** The Constant PAGE_ID. */
	public static final String PAGE_ID = "ports"; //$NON-NLS-1$

	/** The toolbar contribution ID */
	public static final String TOOLBAR_ID = "gov.redhawk.ide.spd.internal.ui.editor.overview.toolbar";

	public PortsFormPage(SCAFormEditor editor) {
		super(editor, PortsFormPage.PAGE_ID, "Ports");
		fBlock = new PortsBlock(this);
	}

	@Override
	protected void createFormContent(IManagedForm managedForm) {
		final ScrolledForm form = managedForm.getForm();
		form.setText("Ports");

		this.fBlock.createContent(managedForm);

		final ToolBarManager manager = (ToolBarManager) form.getToolBarManager();
		super.createFormContent(managedForm);
		final IMenuService service = (IMenuService) getSite().getService(IMenuService.class);
		service.populateContributionManager(manager, "toolbar:" + PortsFormPage.TOOLBAR_ID);
		manager.update(true);
	}

	@Override
	protected void refresh(Resource resource) {
		this.fBlock.refresh(resource);
	}

}

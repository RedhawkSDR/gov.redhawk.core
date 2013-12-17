package gov.redhawk.frontend.ui.tabbed;

import gov.redhawk.frontend.FrontendFactory;
import gov.redhawk.frontend.Tuner;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.impl.TunerStatusImpl;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

public class TunerStatusSection extends AbstractPropertySection {

	/**
	 * The Property Sheet Page.
	 */
	protected PropertySheetPage page;

	public TunerStatusSection() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createControls(Composite parent, final TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		Composite composite = getWidgetFactory().createFlatFormComposite(parent);
		page = new PropertySheetPage();

		page.createControl(composite);
		FormData data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(0, 0);
		data.bottom = new FormAttachment(100, 0);
		page.getControl().setLayoutData(data);

		page.getControl().addControlListener(new ControlAdapter() {

			public void controlResized(ControlEvent e) {
				aTabbedPropertySheetPage.resizeScrolledComposite();
			}
		});
	}

	public void setInput(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof StructuredSelection) {
			StructuredSelection sel = (StructuredSelection) selection;
			if (sel.getFirstElement() instanceof Tuner) {
				Tuner tuner = (Tuner) sel.getFirstElement();
				TunerStatus tunerStatus = (tuner.getTunerStatus() != null) ? tuner.getTunerStatus() : FrontendFactory.eINSTANCE.createTunerStatus();
				StructuredSelection newSel = new StructuredSelection(tunerStatus);
				super.setInput(part, newSel);
				page.selectionChanged(part, newSel);
				return;
			}
		}
		// If all else fails, create a blank tuner status
		TunerStatus tunerStatus = FrontendFactory.eINSTANCE.createTunerStatus();
		StructuredSelection newSel = new StructuredSelection(tunerStatus);
		super.setInput(part, newSel);
		page.selectionChanged(part, newSel);
	}

}

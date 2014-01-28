package gov.redhawk.frontend.ui.internal;

import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.ui.wizard.TunerAllocationSimpleWizard;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.handlers.HandlerUtil;

public class AddListenerHandler extends AbstractHandler implements IHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getActiveMenuSelection(event);
		if (selection == null) {
			selection = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
		}
		
		if (selection.getFirstElement() instanceof TunerStatus) {
			TunerStatus target = (TunerStatus) selection.getFirstElement();
			String fullId = target.getAllocationID();
			String[] parts = fullId.split(",");
			if (parts.length > 0) {
				String id = parts[0];
				WizardDialog dialog = new WizardDialog(HandlerUtil.getActiveShell(event), new TunerAllocationSimpleWizard(target, true, id));
				dialog.open();
			}
		}
		return null;
	}

	

}

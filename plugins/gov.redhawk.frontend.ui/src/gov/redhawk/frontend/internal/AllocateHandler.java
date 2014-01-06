/**
 * 
 */
package gov.redhawk.frontend.internal;

import gov.redhawk.frontend.Tuner;
import gov.redhawk.frontend.TunerContainer;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 *
 */
public class AllocateHandler extends AbstractHandler implements IHandler {

	/* (non-Javadoc)
	 * @see org.eclipse.core.commands.IHandler#execute(org.eclipse.core.commands.ExecutionEvent)
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IStructuredSelection selection = (IStructuredSelection) HandlerUtil.getActiveMenuSelection(event);
		if (selection == null) {
			selection = (IStructuredSelection) HandlerUtil.getCurrentSelection(event);
		}
		if (selection == null) {
			return null;
		}
		Object obj = selection.getFirstElement();
		if (obj instanceof Tuner) {
			Tuner tuner = (Tuner) obj;
			//TODO: Launch Allocation Wizard
//			tuner.setAllocationID("placeholder");
		}
		if (obj instanceof TunerContainer) {
			TunerContainer container = (TunerContainer) obj;
			//TODO: Launch Allocation Wizard
		}
		return null;
	}

}

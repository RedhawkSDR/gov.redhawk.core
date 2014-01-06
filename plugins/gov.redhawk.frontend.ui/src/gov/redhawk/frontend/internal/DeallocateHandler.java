/**
 * 
 */
package gov.redhawk.frontend.internal;

import gov.redhawk.frontend.Tuner;
import gov.redhawk.frontend.TunerContainer;
import gov.redhawk.frontend.edit.utils.TunerUtils;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;

/**
 *
 */
public class DeallocateHandler extends AbstractHandler implements IHandler {

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
			//TODO: Unset all the properties of the Tuner
//			tuner.setAllocationID(null);
		}
		if (obj instanceof TunerContainer) {
			TunerContainer container = (TunerContainer) obj;
			Object[] tuners = TunerUtils.INSTANCE.getChildren(container);
			for (Object tunerObj: tuners) {
				Tuner tuner = (Tuner) tunerObj;
				String allocationID = tuner.getAllocationID();
				if (!(allocationID == null || "".equals(allocationID))) {
					//TODO: deallocate tuner
				}
			}
		}
		return null;
	}

}

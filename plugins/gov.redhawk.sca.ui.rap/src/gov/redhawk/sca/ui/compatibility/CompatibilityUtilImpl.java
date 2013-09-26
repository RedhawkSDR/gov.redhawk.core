package gov.redhawk.sca.ui.compatibility;

import gov.redhawk.sca.rap.RapInit;

import java.security.Principal;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.rwt.RWT;
import org.eclipse.rwt.lifecycle.UICallBack;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;

/**
 * This class is meant as an extension mechanism to single source RCP / RAP applications
 * @since 9.1
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
public class CompatibilityUtilImpl implements ICompatibilityUtil {

	public void setFontDataStyle(FontData fontData, int style) {
		// Cannot set style on font data in RAP
	}
	
	public void disableComboWheelScrollSelect(ComboViewer viewer) {
		// Does not support mouse wheel do nothing
	}
	
	public Principal getUserPrincipal(Display display) {
		return RapInit.getUserPrincipal(display);
	}
	
	public void runInFakeUIContext(Display display, Runnable runnable) {
		UICallBack.runNonUIThreadWithFakeContext(display, runnable);
	}
	
	public void executeOnRequestThread(Runnable runnable) {
		Assert.isNotNull(Display.getCurrent(), "This method must be called from teh UI thread");
		RWT.requestThreadExec(runnable);
	}
	
	public void activateUIConnection(String id) {
		UICallBack.activate(id);
	}
	
	public void deactivateUIConnection(String id) {
		UICallBack.deactivate(id);
	}
}

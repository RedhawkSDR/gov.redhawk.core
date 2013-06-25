package gov.redhawk.sca.ui.compatibility;

import gov.redhawk.sca.rap.RapInit;

import java.security.Principal;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.rwt.lifecycle.UICallBack;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
 * This class is meant as an extension mechanism to single source RCP / RAP applications
 * @since 9.1
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
public class CompatibilityUtilImpl implements ICompatibilityUtil {

	public void setFontDataStyle(FontData fontData, int style) {
		fontData.setStyle(style);
	}
	
	public void disableComboWheelScrollSelect(ComboViewer viewer) {
		viewer.getCombo().addListener(SWT.MouseVerticalWheel, new Listener() {

			public void handleEvent(Event event) {
				// Disable Mouse Wheel Combo Box Control
				event.doit = false;
			}

		});
	}
	
	public Principal getUserPrincipal(Display display) {
		return null;
	}
	
	/**
	 * @since 1.2
	 * @param display
	 * @param runnable
	 */
	public void runInFakeUIContext(Display display, Runnable runnable) {
		//No RAP implementation
	}
	
	/**
	 * @since 1.2
	 * @param display
	 * @param runnable
	 */
	public void activateUIConnection(String id) {
		//No RAP implementation
	}
	
	public void deactivateUIConnection(String id) {
		//No RAP implementation
	}
}

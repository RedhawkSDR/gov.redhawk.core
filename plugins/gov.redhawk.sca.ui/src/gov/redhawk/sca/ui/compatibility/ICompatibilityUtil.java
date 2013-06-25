package gov.redhawk.sca.ui.compatibility;

import java.security.Principal;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;

/**
 * @since 9.1
 * @noimplement This interface is not intended to be implemented by clients.
 */
public interface ICompatibilityUtil {
	
	public void setFontDataStyle(FontData fontData, int style);

	public void disableComboWheelScrollSelect(ComboViewer viewer);

	/**
     * @since 9.1
     */
	public Principal getUserPrincipal(Display display);
	
	/**
     * @since 9.1
     */
	public void runInFakeUIContext(Display display, Runnable runnable);
	
	/**
     * @since 9.1
     */
	public void activateUIConnection(String id);
	
	/**
     * @since 9.1
     */
	public void deactivateUIConnection(String id);

}

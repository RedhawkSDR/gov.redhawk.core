package gov.redhawk.sca.ui.compatibility;

import java.security.Principal;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;

/**
 * This class is meant as an extension mechanism to single source RCP / RAP applications
 * @since 9.1
 * @noinstantiate This class is not intended to be instantiated by clients.
 */
public class CompatibilityUtilImpl implements ICompatibilityUtil {

	public void setFontDataStyle(FontData fontData, int style) {
		throw new UnsupportedOperationException();

	}

	public void disableComboWheelScrollSelect(ComboViewer viewer) {
		throw new UnsupportedOperationException();

	}

	public Principal getUserPrincipal(Display display) {
		throw new UnsupportedOperationException();
	}

	public void runInFakeUIContext(Display display, Runnable runnable) {
		throw new UnsupportedOperationException();

	}

	public void activateUIConnection(String id) {
		throw new UnsupportedOperationException();

	}

	public void deactivateUIConnection(String id) {
		throw new UnsupportedOperationException();

	}

}

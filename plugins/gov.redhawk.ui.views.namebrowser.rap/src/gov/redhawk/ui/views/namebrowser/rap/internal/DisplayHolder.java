package gov.redhawk.ui.views.namebrowser.rap.internal;

import org.eclipse.rwt.SessionSingletonBase;
import org.eclipse.swt.widgets.Display;

/**
 * @since 1.2
 */
public class DisplayHolder extends SessionSingletonBase {

	private Display display;
	
	//force singleton
	private DisplayHolder(){}

	public static DisplayHolder getInstance() {
		return SessionSingletonBase.getInstance(DisplayHolder.class);
	}
	
	public void setDisplay(Display display) {
		this.display = display;
	}
	
	public Display getDisplay() {
		return this.display;
	}
}

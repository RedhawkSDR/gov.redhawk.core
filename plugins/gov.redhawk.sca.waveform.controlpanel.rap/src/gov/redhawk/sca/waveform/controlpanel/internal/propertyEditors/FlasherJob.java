package gov.redhawk.sca.waveform.controlpanel.internal.propertyEditors;

import gov.redhawk.sca.waveform.controlpanel.propertyEditors.ScaSimplePropertyControl;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.rwt.lifecycle.UICallBack;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.progress.UIJob;

public class FlasherJob extends UIJob {
	private static final long DEFAULT_INTERVAL_DELAY = 50;
	private static final int DEFAULT_NUM_FLASHES = 5;
	private int flash = FlasherJob.DEFAULT_NUM_FLASHES;
	private final Control control;
	private final Color defaultBackground;

	public FlasherJob(final Control control) {
		super(control.getDisplay(), "Flasher");
		this.setUser(false);
		this.setSystem(true);
		this.control = control;
		this.defaultBackground = this.control.getBackground();
	}

	@Override
	public IStatus runInUIThread(final IProgressMonitor monitor) {
		this.flash--;
		if (this.flash % 2 == 1) {
			this.control.setBackground(this.control.getDisplay().getSystemColor(SWT.COLOR_BLUE));
		} else {
			this.control.setBackground(this.defaultBackground);
		}

		if (this.flash > 0) {
			schedule(FlasherJob.DEFAULT_INTERVAL_DELAY);
		} else {
			//PASS
			UICallBack.deactivate(ScaSimplePropertyControl.FLASH_ID);
		}
		return Status.OK_STATUS;
	}

	public void reset() {
		this.flash = FlasherJob.DEFAULT_NUM_FLASHES;
		schedule(FlasherJob.DEFAULT_INTERVAL_DELAY);
	}

};

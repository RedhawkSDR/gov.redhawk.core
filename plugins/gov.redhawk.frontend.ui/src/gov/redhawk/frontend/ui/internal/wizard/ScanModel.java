/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.frontend.ui.internal.wizard;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import FRONTEND.ScanningTunerPackage.OutputControlMode;
import FRONTEND.ScanningTunerPackage.ScanMode;

/**
 * This model is used by {@link ScanWizardPage}.
 */
/* package */ class ScanModel {

	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	private ScanMode mode;
	private OutputControlMode controlMode;
	private double controlValue;
	private double delay = 10;

	public ScanMode getMode() {
		return mode;
	}

	public void setMode(ScanMode mode) {
		ScanMode oldValue = this.mode;
		this.mode = mode;
		pcs.firePropertyChange("mode", oldValue, mode); //$NON-NLS-1$
	}

	public OutputControlMode getControlMode() {
		return controlMode;
	}

	public void setControlMode(OutputControlMode controlMode) {
		OutputControlMode oldValue = this.controlMode;
		this.controlMode = controlMode;
		pcs.firePropertyChange("controlMode", oldValue, controlMode); //$NON-NLS-1$
	}

	public double getControlValue() {
		return controlValue;
	}

	public void setControlValue(double controlValue) {
		double oldValue = this.controlValue;
		this.controlValue = controlValue;
		pcs.firePropertyChange("controlValue", oldValue, controlValue); //$NON-NLS-1$
	}

	public double getDelay() {
		return delay;
	}

	public void setDelay(double delay) {
		double oldValue = this.delay;
		this.delay = delay;
		pcs.firePropertyChange("delay", oldValue, delay); //$NON-NLS-1$
	}

	public void addPropertyChangeListener(final PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(final PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

}

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

/**
 * Model used by {@link SpanScanPage}.
 */
/* package */ class SpanModel {
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	private double lowFrequency;
	private double highFrequency;
	private double step;

	SpanModel(double lowFrequency, double highFrequency, double step) {
		this.lowFrequency = lowFrequency;
		this.highFrequency = highFrequency;
		this.step = step;
	}

	public double getLowFrequency() {
		return lowFrequency;
	}

	public void setLowFrequency(double lowFrequency) {
		double oldValue = this.lowFrequency;
		this.lowFrequency = lowFrequency;
		pcs.firePropertyChange("lowFrequency", oldValue, lowFrequency); //$NON-NLS-1$
	}

	public double getHighFrequency() {
		return highFrequency;
	}

	public void setHighFrequency(double highFrequency) {
		double oldValue = this.highFrequency;
		this.highFrequency = highFrequency;
		pcs.firePropertyChange("highFrequency", oldValue, highFrequency); //$NON-NLS-1$
	}

	public double getStep() {
		return step;
	}

	public void setStep(double step) {
		double oldValue = this.step;
		this.step = step;
		pcs.firePropertyChange("step", oldValue, step); //$NON-NLS-1$
	}

	public void addPropertyChangeListener(final PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(final PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}
}

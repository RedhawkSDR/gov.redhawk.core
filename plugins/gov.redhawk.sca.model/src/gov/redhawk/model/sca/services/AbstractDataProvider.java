/** 
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.model.sca.services;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * @since 14.0
 * 
 */
public abstract class AbstractDataProvider implements IScaDataProvider {

	private final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);
	private boolean disposed;
	private boolean enabled;
	private IStatus status;

	/**
	 * {@inheritDoc}
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.addPropertyChangeListener(listener);
	}
	

	/**
	 * @return
	 * @see java.beans.PropertyChangeSupport#getPropertyChangeListeners()
	 * @since 19.0
	 */
	public PropertyChangeListener[] getPropertyChangeListeners() {
		return propertyChangeSupport.getPropertyChangeListeners();
	}


	/**
	 * {@inheritDoc}
	 */
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propertyChangeSupport.removePropertyChangeListener(listener);
	}

	/**
	 * {@inheritDoc}
	 */
	public void dispose() {
		boolean oldDisposed = disposed;
		this.disposed = true;
		propertyChangeSupport.firePropertyChange(IScaDataProvider.DISPOSED_PROPERTY, oldDisposed, disposed);
	}
	

	protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
	    propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

	protected void firePropertyChange(String propertyName, boolean oldValue, boolean newValue) {
	    propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }

	protected void firePropertyChange(PropertyChangeEvent evt) {
	    propertyChangeSupport.firePropertyChange(evt);
    }

	/**
	 * {@inheritDoc}
	 */
	public boolean isDisposed() {
		return disposed;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setEnabled(boolean enabled) {
		if (this.enabled == enabled) {
			return;
		}
		boolean oldValue = this.enabled;
		this.enabled = enabled;
		
		propertyChangeSupport.firePropertyChange(IScaDataProvider.ENABLED_PROPERTY, oldValue, isEnabled());
	}
	
	protected void setStatus(IStatus status) {
		if (this.status != null && (status == null || (status.getSeverity() == this.status.getSeverity()))) {
			return;
		}
		IStatus oldValue = this.status;
		this.status = status;
		firePropertyChange(STATUS_PROPERTY, oldValue, this.status);
	}
	
	public IStatus getStatus() {
	    return this.status;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isEnabled() {
		return enabled && !isDisposed();
	}
	
	public IStatus refresh(IProgressMonitor monitor) {
	    return Status.OK_STATUS;
	}

}

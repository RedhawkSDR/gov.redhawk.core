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
package gov.redhawk.model.sca.commands;

import gov.redhawk.model.sca.IDisposable;

import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.emf.common.command.AbstractCommand;

/**
 * @since 14.0
 */
public class DisposeCommand extends AbstractCommand {

	private final IDisposable disposable;

	public DisposeCommand(IDisposable disposable) {
		this.disposable = disposable;
	}

	@Override
	protected boolean prepare() {
		return this.disposable != null && !this.disposable.isDisposed();
	}

	/**
	 * {@inheritDoc}
	 */
	public void execute() {
		SafeRunner.run(new ISafeRunnable() {

			public void run() {
				disposable.dispose();   
            }

			public void handleException(Throwable exception) {
	            // TODO Auto-generated method stub
	            
            }
			
		});
	}

	@Override
	public boolean canUndo() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public void redo() {
		// TODO Auto-generated method stub

	}

}

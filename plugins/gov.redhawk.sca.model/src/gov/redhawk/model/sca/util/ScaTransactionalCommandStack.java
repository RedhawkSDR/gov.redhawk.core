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
package gov.redhawk.model.sca.util;

import java.util.Map;

import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.emf.common.command.AbortExecutionException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.transaction.RollbackException;
import org.eclipse.emf.transaction.Transaction;
import org.eclipse.emf.transaction.impl.InternalTransaction;
import org.eclipse.emf.transaction.impl.TransactionalCommandStackImpl;

/**
 * @since 14.0
 * 
 */
public class ScaTransactionalCommandStack extends TransactionalCommandStackImpl {

	@Override
	public boolean canRedo() {
		return false;
	}

	@Override
	public boolean canUndo() {
		return false;
	}

	@Override
	protected void basicExecute(Command command) {
		// If the command is executable, and execute it.
		//
		if (command != null) {
			if (command.canExecute()) {
				try {
					command.execute();
					notifyListeners();
				} catch (AbortExecutionException exception) {
					command.dispose();
				} catch (RuntimeException exception) {
					handleError(exception);
					command.dispose();
					notifyListeners();
				} finally {
					command.dispose();
				}
			} else {
				command.dispose();
			}
		}
	}
	
	@Override
	public Command getRedoCommand() {
	    return null;
	}
	
	@Override
	public Command getUndoCommand() {
	    return null;
	}
	
	@Override
	public boolean isSaveNeeded() {
	    return false;
	}
	
	@Override
	public void redo() {
	    // Not supported
	}
	
	@Override
	public void undo() {
	    // Not supported
	}
	
	@Override
	protected void doExecute(Command command, Map< ? , ? > options) throws InterruptedException, RollbackException {
		InternalTransaction tx = createTransaction(command, options);
	
		try {
			basicExecute(command);
			// commit the transaction now
			tx.commit();
		} catch (OperationCanceledException e) {
			// PASS
			// snuff the exception, because this is expected (user asked to
			//    cancel the model change).  We will rollback, below
		} 
	}
	
    protected void rollback(Transaction tx) {
    	// Don't allow rollback
    }
	
	@Override
	protected void handleRollback(Command command, RollbackException rbe) {
	   // We don't allow roll back
	}

	public boolean isEmpty() {
		return commandList.isEmpty();
	}
}

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
package gov.redhawk.sca.ui.editors;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.MultiPageEditorPart;

/**
 * @since 6.0
 * 
 */
public abstract class AbstractMultiPageScaEditor extends MultiPageEditorPart {

	private final Map<String, IAction> actionMap = new HashMap<String, IAction>();

	/**
	 * 
	 */
	public AbstractMultiPageScaEditor() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doSave(final IProgressMonitor monitor) {
		// PASS SCA Runtime Editors do not Save
		throw new UnsupportedOperationException("Runtime editors can not be saved");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void doSaveAs() {
		// PASS SCA Runtime Editors do not Save
		throw new UnsupportedOperationException("Runtime editors can not be saved");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void init(final IEditorSite site, final IEditorInput input) throws PartInitException {
		setSite(site);
		setInput(input);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isDirty() {
		// Should always NOT be dirty
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSaveAsAllowed() {
		// Never save
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getAdapter(@SuppressWarnings("unchecked") final Class adapter) {
		if (adapter == ISelectionProvider.class) {
			return this.getSelectionProvider();
		}
		return super.getAdapter(adapter);
	}

	/**
	 * Returns the action installed under the given action id.
	 * 
	 * @param actionId the action id
	 * @return the action, or <code>null</code> if none
	 * @see #setAction(String, IAction)
	 */
	public IAction getAction(final String actionId) {
		return this.actionMap.get(actionId);
	}

	/**
	 * Installs the given action under the given action id.
	 * 
	 * @param actionID the action id
	 * @param action the action, or <code>null</code> to clear it
	 * @see #getAction(String)
	 */
	public void setAction(final String actionID, final IAction action) {
		if (action == null) {
			this.actionMap.remove(actionID);
		} else {
			this.actionMap.put(actionID, action);
		}
	}

	/**
	 * Returns this text editor's selection provider. Repeated calls to this
	 * method return the same selection provider.
	 * 
	 * @return the selection provider
	 */
	public ISelectionProvider getSelectionProvider() {
		return getSite().getSelectionProvider();
	}
}

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
package gov.redhawk.sca.sad.diagram.edit.parts;

import gov.redhawk.diagram.edit.parts.IUsesPortStubNameEditPart;
import gov.redhawk.diagram.edit.parts.UsesPortStubNameEditPartHelper;
import gov.redhawk.sca.sad.diagram.RedhawkSadDiagramPlugin;

import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPreferenceStore;

public class UsesPortStubNameEditPart extends mil.jpeojtrs.sca.sad.diagram.edit.parts.UsesPortStubNameEditPart implements IUsesPortStubNameEditPart {

	private final UsesPortStubNameEditPartHelper editPartHelper = new UsesPortStubNameEditPartHelper(this);

	public UsesPortStubNameEditPart(final View view) {
		super(view);
		disableEditMode();
	}

	@Override
	public void enableEditMode() {
		this.editPartHelper.enableEditMode();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void addNotationalListeners() {
		this.editPartHelper.addNotationalListeners();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void removeNotationalListeners() {
		this.editPartHelper.removeNotationalListeners();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void refreshVisibility() {
		this.editPartHelper.refreshVisibility();
	}

	@Override
	public boolean isSelectable() {
		return this.editPartHelper.isSelectable();
	}

	public void basicAddNotationalListeners() {
		super.addNotationalListeners();
	}

	public IPreferenceStore getPreferenceStore() {
		return RedhawkSadDiagramPlugin.getDefault().getPreferenceStore();
	}

	public void basicRemoveNotationalListeners() {
		super.removeNotationalListeners();
	}

	@Override
	public void setVisibility(final boolean vis) {
		super.setVisibility(vis);
	}

	@Override
	protected boolean isEditable() {
		return this.editPartHelper.isEditable();
	}
}

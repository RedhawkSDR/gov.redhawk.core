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
package gov.redhawk.sca.dcd.diagram.edit.parts;

import gov.redhawk.diagram.edit.parts.IUsesPortStubNameEditPart;
import gov.redhawk.diagram.edit.parts.UsesPortStubNameEditPartHelper;
import gov.redhawk.sca.dcd.diagram.DcdDiagramPluginActivator;

import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * 
 */
public class UsesPortStubNameEditPart extends mil.jpeojtrs.sca.dcd.diagram.edit.parts.UsesPortStubNameEditPart implements IUsesPortStubNameEditPart {

	private final UsesPortStubNameEditPartHelper editPartHelper = new UsesPortStubNameEditPartHelper(this);

	public UsesPortStubNameEditPart(final View view) {
		super(view);
	}

	@Override
	public void enableEditMode() {
		this.editPartHelper.enableEditMode();
	}

	@Override
	public void addNotationalListeners() {
		this.editPartHelper.addNotationalListeners();
	}

	@Override
	public void removeNotationalListeners() {
		this.editPartHelper.removeNotationalListeners();
	}

	@Override
	public void refreshVisibility() {
		this.editPartHelper.refreshVisibility();
	}

	@Override
	public boolean isSelectable() {
		return this.editPartHelper.isSelectable();
	}

	/**
	 * {@inheritDoc}
	 */
	public void basicAddNotationalListeners() {
		super.addNotationalListeners();
	}

	/**
	 * {@inheritDoc}
	 */
	public IPreferenceStore getPreferenceStore() {
		return DcdDiagramPluginActivator.getDefault().getPreferenceStore();
	}

	/**
	 * {@inheritDoc}
	 */
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

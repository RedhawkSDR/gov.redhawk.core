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
package gov.redhawk.diagram.edit.parts;

import gov.redhawk.diagram.PartitioningDiagramPreferenceConstants;

import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

/**
 * @since 3.0
 */
public class UsesPortStubNameEditPartHelper {

	private final IUsesPortStubNameEditPart editPart;

	public UsesPortStubNameEditPartHelper(final IUsesPortStubNameEditPart editPart) {
		this.editPart = editPart;
		editPart.disableEditMode();
	}

	public void enableEditMode() {
		this.editPart.disableEditMode();
	};

	private final IPropertyChangeListener listener = new IPropertyChangeListener() {
		@Override
		public void propertyChange(final PropertyChangeEvent event) {
			if (event.getProperty().equals(PartitioningDiagramPreferenceConstants.PREF_SHOW_PORT_LABELS)) {
				refreshVisibility();
			}
		}

	};

	/**
	 * {@inheritDoc}
	 */
	public void addNotationalListeners() {
		this.editPart.basicAddNotationalListeners();
		this.editPart.getPreferenceStore().addPropertyChangeListener(this.listener);
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeNotationalListeners() {
		this.editPart.basicRemoveNotationalListeners();
		this.editPart.getPreferenceStore().removePropertyChangeListener(this.listener);
	}

	/**
	 * {@inheritDoc}
	 */
	public void refreshVisibility() {
		final Object model = this.editPart.getModel();
		if (model instanceof View) {
			if (!this.editPart.getPreferenceStore().getString(PartitioningDiagramPreferenceConstants.PREF_SHOW_PORT_LABELS).equals("")) {
				this.editPart.setVisibility(((View) model).isVisible()
				        && this.editPart.getPreferenceStore().getBoolean(PartitioningDiagramPreferenceConstants.PREF_SHOW_PORT_LABELS));
			} else {
				this.editPart.setVisibility(true);
			}
		}
	}

	public boolean isSelectable() {
		return false;
	}

	public boolean isEditable() {
		return (this.editPart.getParent().getParent() instanceof FindByStubEditPart);
	}
}

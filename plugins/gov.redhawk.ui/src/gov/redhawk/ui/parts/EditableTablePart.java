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
/*******************************************************************************
 * Copyright (c) 2000, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package gov.redhawk.ui.parts;

import gov.redhawk.ui.wizards.RenameDialog;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * The Class EditableTablePart.
 */
public class EditableTablePart extends TablePart {
	private boolean editable;
	private Action renameAction;

	/**
	 * The Class RenameAction.
	 */
	class RenameAction extends Action {

		/**
		 * Instantiates a new rename action.
		 */
		public RenameAction() {
			super("Rename");
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public void run() {
			doRename();
		}
	}

	/**
	 * The Class NameModifier.
	 */
	class NameModifier implements ICellModifier {

		/**
		 * {@inheritDoc}
		 */
		public boolean canModify(final Object object, final String property) {
			return true;
		}

		/**
		 * {@inheritDoc}
		 */
		public void modify(final Object object, final String property, final Object value) {
			entryModified(object, value.toString());
		}

		/**
		 * {@inheritDoc}
		 */
		public Object getValue(final Object object, final String property) {
			return object.toString();
		}
	}

	/**
	 * Constructor for EditableTablePart.
	 * 
	 * @param buttonLabels the button labels
	 */
	public EditableTablePart(final String[] buttonLabels) {
		super(buttonLabels);
	}

	/**
	 * Checks if is editable.
	 * 
	 * @return true, if is editable
	 */
	public boolean isEditable() {
		return this.editable;
	}

	/**
	 * Sets the editable.
	 * 
	 * @param editable the new editable
	 */
	public void setEditable(final boolean editable) {
		this.editable = editable;
	}

	/**
	 * Gets the rename action.
	 * 
	 * @return the rename action
	 */
	public IAction getRenameAction() {
		if (this.renameAction == null) {
			this.renameAction = new RenameAction();
		}
		return this.renameAction;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected StructuredViewer createStructuredViewer(final Composite parent, final int style, final FormToolkit toolkit) {
		final TableViewer tableViewer = (TableViewer) super.createStructuredViewer(parent, style, toolkit);
		return tableViewer;
	}

	/**
	 * Do rename.
	 */
	private void doRename() {
		final TableViewer viewer = getTableViewer();
		final IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
		if (selection.size() == 1 && isEditable()) {
			final Object obj = selection.getFirstElement();
			final String oldName = obj.toString();
			final RenameDialog dialog = new RenameDialog(getControl().getShell(), oldName);
			dialog.create();
			dialog.getShell().setText("Rename");
			dialog.getShell().setSize(300, 150); // SUPPRESS CHECKSTYLE MagicNumber
			if (dialog.open() == Window.OK) {
				entryModified(obj, dialog.getNewName());
			}
		}
	}

	/**
	 * Entry modified.
	 * 
	 * @param entry the entry
	 * @param value the value
	 */
	protected void entryModified(final Object entry, final String value) {
	}
}

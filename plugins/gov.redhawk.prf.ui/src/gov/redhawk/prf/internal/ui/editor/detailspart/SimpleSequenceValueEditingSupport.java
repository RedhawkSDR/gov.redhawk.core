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
package gov.redhawk.prf.internal.ui.editor.detailspart;

import java.util.Arrays;

import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.PropertyValueType;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.ui.celleditor.ExtendedComboBoxCellEditor;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Widget;

/**
 * @since 2.1
 * 
 */
public class SimpleSequenceValueEditingSupport extends EditingSupport {

	private final PropertyValueType valueType;
	private final Boolean complex;

	public SimpleSequenceValueEditingSupport(final PropertyValueType valueType, Boolean complex, final TableViewer viewer) {
		super(viewer);
		this.valueType = valueType;
		this.complex = complex;
	}

	@Override
	public TableViewer getViewer() {
		return (TableViewer) super.getViewer();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected CellEditor getCellEditor(final Object element) {
		final EDataType eDataType = this.valueType.toDataType();
		final Composite composite = (Composite) getViewer().getControl();
		if (eDataType.getInstanceClass() == Boolean.class || eDataType.getInstanceClass() == Boolean.TYPE) {
			return new ExtendedComboBoxCellEditor(composite, Arrays.asList(new Object[] { Boolean.FALSE, Boolean.TRUE }), getEditLabelProvider(), true);
		}
		return new SimpleSequenceValuePropertyDescriptor.SimpleSequenceValueCellEditor(this.valueType, this.complex, composite);
	}

	protected ILabelProvider getEditLabelProvider() {
		return new LabelProvider();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean canEdit(final Object element) {
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Object getValue(final Object element) {
		return AdapterFactoryEditingDomain.unwrap(element);
	}

	@Override
	protected void saveCellEditorValue(final CellEditor cellEditor, final ViewerCell cell) {
		final Object newValue = cellEditor.getValue();
		final Widget item = cell.getItem();
		final int valueIndex = getViewer().getTable().indexOf((TableItem) item);
		final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(getViewer().getInput());
		final Command command = SetCommand.create(editingDomain, getViewer().getInput(), PrfPackage.Literals.VALUES__VALUE,
		        (newValue == null) ? "" : newValue.toString(), valueIndex);
		if (editingDomain != null) {
			editingDomain.getCommandStack().execute(command);
		} else {
			command.execute();
		}
	}

	@Override
	protected void setValue(final Object element, final Object value) {
		// should never be called
		throw new UnsupportedOperationException();
	}

}

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
package gov.redhawk.sca.internal.ui.properties;

import gov.redhawk.model.sca.ScaSimpleSequenceProperty;
import gov.redhawk.model.sca.util.ModelUtil;
import gov.redhawk.sca.internal.ui.properties.ScaSimpleSequencePropertyValuePropertyDescriptor.ScaSimpleSequencePropertyValueCellEditor;

import java.util.Arrays;

import mil.jpeojtrs.sca.prf.provider.RadixLabelProviderUtil;

import org.eclipse.emf.common.ui.celleditor.ExtendedComboBoxCellEditor;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
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
public class ScaSimpleSequenceValueEditingSupport extends EditingSupport {

	private final ScaSimpleSequenceProperty property;

	public ScaSimpleSequenceValueEditingSupport(final ScaSimpleSequenceProperty property, final TableViewer viewer) {
		super(viewer);
		this.property = property;
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

		if (!ModelUtil.isSettable(this.property)) {
			return null;
		}

		final EDataType eDataType = this.property.getDefinition().getType().toEDataType(this.property.getDefinition().isComplex());
		final Composite composite = (Composite) getViewer().getControl();
		if (eDataType.getInstanceClass() == Boolean.class || eDataType.getInstanceClass() == Boolean.TYPE) {
			return new ExtendedComboBoxCellEditor(composite, Arrays.asList(new Object[] {
			        Boolean.FALSE, Boolean.TRUE
			}), getEditLabelProvider(), true);
		}
		return new ScaSimpleSequencePropertyValuePropertyDescriptor.ScaSimpleSequencePropertyValueCellEditor(this.property, composite);
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
		this.property.getValues().set(valueIndex, newValue);
	}

	@Override
	protected void setValue(final Object element, final Object value) {
		// should never be called
		throw new UnsupportedOperationException();
	}

	@Override
	protected void initializeCellEditorValue(final CellEditor cellEditor, final ViewerCell cell) {
		if (cellEditor instanceof ScaSimpleSequencePropertyValueCellEditor) {
			final ScaSimpleSequencePropertyValueCellEditor simpleSequenceCellEditor = (ScaSimpleSequencePropertyValueCellEditor) cellEditor;
			int[] radix = new int[0];
			if (this.property.getDefinition().getValues() != null) {
				radix = RadixLabelProviderUtil.getRadix(this.property.getDefinition().getValues().getValue());
			}
			final Widget item = cell.getItem();
			final int valueIndex = getViewer().getTable().indexOf((TableItem) item);
			if (valueIndex < radix.length) {
				simpleSequenceCellEditor.getValidator().setRadix(radix[valueIndex]);
			} else if (radix.length > 0) {
				simpleSequenceCellEditor.getValidator().setRadix(radix[0]);
			}
		}
		super.initializeCellEditorValue(cellEditor, cell);
	}
}

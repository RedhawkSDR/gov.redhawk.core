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
package gov.redhawk.prf.internal.ui.editor.composite;

import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertySourceProvider;

import gov.redhawk.sca.internal.ui.wizards.ValuesDialogEditor;
import gov.redhawk.sca.ui.properties.AbstractPropertyEditingSupport;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.PropertyValueType;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleRef;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.SimpleSequenceRef;

public class StructValueEditingSupport extends AbstractPropertyEditingSupport {
	public StructValueEditingSupport(ColumnViewer viewer, IPropertySourceProvider propertySourceProvider) {
		super(viewer, propertySourceProvider);
	}

	@Override
	protected CellEditor getCellEditor(Object object) {
		object = AdapterFactoryEditingDomain.unwrap(object);
		if (object instanceof SimpleRef) {
			final Simple simple = ((SimpleRef) object).getProperty();
			if (simple.getType() == PropertyValueType.BOOLEAN) {
				final ComboBoxViewerCellEditor editor = new ComboBoxViewerCellEditor((Composite)getViewer().getControl(), SWT.READ_ONLY);
				editor.setContentProvider(new ArrayContentProvider());
				editor.setLabelProvider(new LabelProvider());
				editor.setInput(new Object[] { "true", "false" });
				return editor;
			}
		} else if (object instanceof SimpleSequenceRef) { 
			final SimpleSequence simpleSequence = ((SimpleSequenceRef) object).getProperty();
			return new ValuesDialogEditor((Composite)getViewer().getControl(),simpleSequence.getType(), simpleSequence.isComplex());
		}
		return super.getCellEditor(object);
	}

	@Override
	protected Object getPropertyID(Object object) {
		object = AdapterFactoryEditingDomain.unwrap(object);
		if (object instanceof SimpleRef) {
			return PrfPackage.Literals.SIMPLE_REF__VALUE.getName();
		} else if (object instanceof SimpleSequenceRef) {
			return PrfPackage.Literals.SIMPLE_SEQUENCE_REF__VALUES.getName();
		}
		return null;
	}
}

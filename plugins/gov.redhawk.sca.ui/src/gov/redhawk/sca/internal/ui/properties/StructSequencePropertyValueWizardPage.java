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

import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.ScaStructSequenceProperty;
import gov.redhawk.sca.ui.ScaComponentFactory;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationListener;
import org.eclipse.jface.viewers.ColumnViewerEditorDeactivationEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class StructSequencePropertyValueWizardPage extends AbstractSequencePropertyValueWizardPage {

	protected StructSequencePropertyValueWizardPage(final ScaStructSequenceProperty property) {
		super(property);
	}

	@Override
	protected EList< ? > getList() {
		return ((ScaStructSequenceProperty) property).getStructs();
	}

	@Override
	protected void handleAddValue() {
		final ScaStructSequenceProperty structSeqProperty = (ScaStructSequenceProperty) this.property;
		final ScaStructProperty newStruct = structSeqProperty.createScaStructProperty();
		for (final ScaSimpleProperty p : newStruct.getSimples()) {
			if (p.getValue() == null) {
				final Object value = getDefaultValue(p.getDefinition().getType(), p.getDefinition().isComplex());
				p.setValue(value);
			}
		}
		structSeqProperty.getStructs().add(newStruct);
	}

	@Override
	protected TableViewer createTableViewer(Composite parent) {
		final TableViewer viewer = ScaComponentFactory.createStructSequenceTable(parent, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL,
			adapterFactory, (ScaStructSequenceProperty) property);
		viewer.getColumnViewerEditor().addEditorActivationListener(new ColumnViewerEditorActivationListener() {

			@Override
			public void beforeEditorDeactivated(final ColumnViewerEditorDeactivationEvent event) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeEditorActivated(final ColumnViewerEditorActivationEvent event) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterEditorDeactivated(final ColumnViewerEditorDeactivationEvent event) {
				viewer.refresh(true);
			}

			@Override
			public void afterEditorActivated(final ColumnViewerEditorActivationEvent event) {

			}
		});
		return viewer;
	}
}

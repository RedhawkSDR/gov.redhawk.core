/** 
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.sca.internal.ui.properties;

import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaSimpleSequenceProperty;
import gov.redhawk.model.sca.ScaStructSequenceProperty;
import gov.redhawk.model.sca.util.ModelUtil;

import org.eclipse.emf.common.ui.celleditor.ExtendedDialogCellEditor;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class SequencePropertyValueDescriptor extends PropertyDescriptor {

	public SequencePropertyValueDescriptor(final Object object, final IItemPropertyDescriptor itemPropertyDescriptor) {
		super(object, itemPropertyDescriptor);
	}

	@Override
	public CellEditor createPropertyEditor(final Composite composite) {
		final ScaAbstractProperty< ? > property = (ScaAbstractProperty< ? >) this.object;
		if (!ModelUtil.isSettable(property)) {
			return null;
		}

		CellEditor result = null;
		final ILabelProvider editLabelProvider = getEditLabelProvider();
		result = new ExtendedDialogCellEditor(composite, editLabelProvider) {

			@Override
			protected Object openDialogBox(final Control cellEditorWindow) {
				final SequencePropertyValueWizard wizard = new SequencePropertyValueWizard(copyProperty(property));
				final WizardDialog dialog = new WizardDialog(getControl().getShell(), wizard);
				if (dialog.open() == Window.OK) {
					final ScaAbstractProperty< ? > newProperty = wizard.getProperty();
					if (newProperty instanceof ScaSimpleSequenceProperty) {
						final ScaSimpleSequenceProperty newSimpleSequenceProperty = (ScaSimpleSequenceProperty) newProperty;
						return newSimpleSequenceProperty.getValues();
					} else if (newProperty instanceof ScaStructSequenceProperty) {
						final ScaStructSequenceProperty newStructSequenceProperty = (ScaStructSequenceProperty) newProperty;
						return newStructSequenceProperty.getStructs();
					}
					return null;
				}
				return null;
			}

		};
		return result;
	}

	private ScaAbstractProperty< ? > copyProperty(final ScaAbstractProperty< ? > property) {
		final ScaAbstractProperty< ? > retVal = EcoreUtil.copy(property);
		// Recopy the value since it is overriden during the copy process
		retVal.fromAny(property.toAny());
		return retVal;
	}
}

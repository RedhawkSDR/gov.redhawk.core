/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.sca.internal.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource2;

import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaTransport;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.sca.internal.ui.dialogs.TransportDetailsDialog;
import gov.redhawk.sca.ui.RedhawkUiAdapterFactory;

public class ScaPortAdapterFactory extends RedhawkUiAdapterFactory {

	public static class SupportedTransportsPropertyDescriptor extends PropertyDescriptor {

		public SupportedTransportsPropertyDescriptor(Object object, IItemPropertyDescriptor itemPropertyDescriptor) {
			super(object, itemPropertyDescriptor);
		}

		@Override
		public CellEditor createPropertyEditor(final Composite composite) {
			return new DialogCellEditor(composite) {

				private Button button;

				@Override
				protected Object openDialogBox(Control cellEditorWindow) {
					// Clone the objects so we don't have to worry about model changes
					EcoreEList< ? > list = (EcoreEList< ? >) getValue();
					List<ScaTransport> transportListCopy = ScaModelCommand.runExclusive(list.getEObject(), () -> {
						List<ScaTransport> listCopy = new ArrayList<>();
						for (Object transport : list) {
							listCopy.add(EcoreUtil.copy((ScaTransport) transport));
						}
						return listCopy;
					});

					new TransportDetailsDialog(cellEditorWindow.getShell(), transportListCopy).open();
					return null;
				}

				@Override
				protected Button createButton(Composite parent) {
					button = super.createButton(parent);
					return button;
				}

				@Override
				protected void updateContents(Object value) {
					getDefaultLabel().setText(getEditLabelProvider().getText(value));
					EcoreEList< ? > list = (EcoreEList< ? >) value;
					if (button != null) {
						button.setEnabled(!list.isEmpty());
					}
				}
			};
		}
	}

	public static class ScaPortPropertySource extends StatusProviderObjectAdapterFactory.DataProviderPropertySource {

		public ScaPortPropertySource(final Object object, final IItemPropertySource itemPropertySource) {
			super(object, itemPropertySource);
		}

		@Override
		protected IPropertyDescriptor createPropertyDescriptor(final IItemPropertyDescriptor itemPropertyDescriptor) {
			if (itemPropertyDescriptor.getFeature(this.object) == ScaPackage.Literals.SCA_PORT__SUPPORTED_TRANSPORTS) {
				return new SupportedTransportsPropertyDescriptor(this.object, itemPropertyDescriptor);
			} else {
				return super.createPropertyDescriptor(itemPropertyDescriptor);
			}
		}

	}

	@Override
	protected IPropertySource2 createPropertySource(final Object adaptableObject, final IItemPropertySource itemPropertySource) {
		return new ScaPortPropertySource(adaptableObject, itemPropertySource);
	}
}

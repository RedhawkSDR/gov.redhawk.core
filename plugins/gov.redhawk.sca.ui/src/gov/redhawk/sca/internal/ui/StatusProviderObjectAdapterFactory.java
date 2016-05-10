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
package gov.redhawk.sca.internal.ui;

import gov.redhawk.model.sca.IStatusProvider;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.sca.ui.EventDetailsDialog;
import gov.redhawk.sca.ui.RedhawkUiAdapterFactory;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

public class StatusProviderObjectAdapterFactory extends RedhawkUiAdapterFactory {

	public static class StatusProviderPropertyDescriptor extends PropertyDescriptor {

		public StatusProviderPropertyDescriptor(final Object object, final IItemPropertyDescriptor itemPropertyDescriptor) {
			super(object, itemPropertyDescriptor);
		}

		@Override
		public CellEditor createPropertyEditor(final Composite composite) {
			return new DialogCellEditor(composite) {

				private Label label;
				private Button detailButton;

				@Override
				protected Object openDialogBox(final Control cellEditorWindow) {
					final IStatus status = ((IStatusProvider) StatusProviderPropertyDescriptor.this.object).getStatus();
					final EventDetailsDialog dialog = new EventDetailsDialog(cellEditorWindow.getShell(), status);
					dialog.open();
					return null;
				}

				@Override
				protected Button createButton(final Composite parent) {
					this.detailButton = new Button(parent, SWT.DOWN);
					this.detailButton.setText("Details"); //$NON-NLS-1$
					return this.detailButton;
				}

				@Override
				protected Control createContents(final Composite cell) {
					this.label = new Label(cell, SWT.LEFT);
					this.label.setFont(cell.getFont());
					this.label.setBackground(cell.getBackground());
					return this.label;
				}

				@Override
				protected void updateContents(final Object value) {
					if (value != null) {
						if (this.detailButton != null) {
							this.detailButton.setEnabled(!((IStatus) value).isOK());
						}
						this.label.setText(getEditLabelProvider().getText(value));
					} else {
						if (this.detailButton != null) {
							this.detailButton.setEnabled(false);
						}
						this.label.setText("");
					}
				}

			};
		}
	}

	public static class DataProviderPropertySource extends ScaPropertySource {

		public DataProviderPropertySource(final Object object, final IItemPropertySource itemPropertySource) {
			super(object, itemPropertySource);
		}

		@Override
		protected IPropertyDescriptor createPropertyDescriptor(final IItemPropertyDescriptor itemPropertyDescriptor) {
			if (ScaPackage.Literals.ISTATUS_PROVIDER__STATUS == itemPropertyDescriptor.getFeature(this.object)) {
				return new StatusProviderPropertyDescriptor(this.object, itemPropertyDescriptor);
			} else {
				return super.createPropertyDescriptor(itemPropertyDescriptor);
			}
		}

	}

	@Override
	protected IPropertySource createPropertySource(final Object adaptableObject, final IItemPropertySource itemPropertySource) {
		return new DataProviderPropertySource(adaptableObject, itemPropertySource);
	}
}

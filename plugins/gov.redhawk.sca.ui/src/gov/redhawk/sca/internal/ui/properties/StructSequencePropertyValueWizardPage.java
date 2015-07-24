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

import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaSimpleSequenceProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.ScaStructSequenceProperty;
import gov.redhawk.model.sca.provider.ScaSimplePropertyItemProvider;
import gov.redhawk.model.sca.provider.ScaSimpleSequencePropertyItemProvider;
import gov.redhawk.sca.ui.ScaComponentFactory;
import gov.redhawk.sca.ui.properties.ScaPropertiesAdapterFactory;
import mil.jpeojtrs.sca.prf.Simple;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

public class StructSequencePropertyValueWizardPage extends AbstractSequencePropertyValueWizardPage {

	private final ScaPropertiesAdapterFactory adapterFactory = new ScaPropertiesAdapterFactory() {

		@Override
		public Adapter createScaSimpleSequencePropertyAdapter() {
			// Override default item provider to turn notifications on individual simple sequences into notifications
			// for the containing struct value.
			return new ScaSimpleSequencePropertyItemProvider(this) {
				@Override
				public void notifyChanged(final org.eclipse.emf.common.notify.Notification notification) {
					updateChildren(notification);

					switch (notification.getFeatureID(ScaSimpleSequenceProperty.class)) {
					case ScaPackage.SCA_SIMPLE_SEQUENCE_PROPERTY__VALUES:
						fireNotifyChanged(new ViewerNotification(notification, ((EObject) notification.getNotifier()).eContainer(), false, true));
						return;
					default:
						break;
					}
					super.notifyChanged(notification);
				}
			};
		}

		@Override
		public Adapter createScaSimplePropertyAdapter() {
			// Override default item provider to turn notifications on individual simples into notifications for the
			// containing struct value.
			return new ScaSimplePropertyItemProvider(this) {
				@Override
				public void notifyChanged(final org.eclipse.emf.common.notify.Notification notification) {
					updateChildren(notification);

					switch (notification.getFeatureID(ScaSimpleSequenceProperty.class)) {
					case ScaPackage.SCA_SIMPLE_PROPERTY__VALUE:
						fireNotifyChanged(new ViewerNotification(notification, ((EObject) notification.getNotifier()).eContainer(), false, true));
						return;
					default:
						break;
					}
					super.notifyChanged(notification);
				}
			};
		}
	};

	protected StructSequencePropertyValueWizardPage(final ScaStructSequenceProperty property) {
		super(property);
	}

	@Override
	public void dispose()
	{
		super.dispose();
		adapterFactory.dispose();
	}

	@Override
	protected EList< ? > getList() {
		return ((ScaStructSequenceProperty) property).getStructs();
	}

	@Override
	protected void handleAddValue() {
		final ScaStructSequenceProperty structSeqProperty = (ScaStructSequenceProperty) property;
		final ScaStructProperty newStruct = structSeqProperty.createScaStructProperty();
		for (final ScaAbstractProperty< ? > field : newStruct.getFields()) {
			// For simple fields, if there is no default value, set one; simple sequences without a default value will
			// will be an empty list
			if (field instanceof ScaSimpleProperty) {
				final ScaSimpleProperty simple = (ScaSimpleProperty) field;
				if (simple.getValue() == null) {
					final Simple definition = simple.getDefinition();
					final Object value = getDefaultValue(definition.getType(), definition.isComplex());
					simple.setValue(value);
				}
			}
		}
		structSeqProperty.getStructs().add(newStruct);
	}

	@Override
	protected TableViewer createViewer(Composite parent) {
		return ScaComponentFactory.createStructSequenceTable(parent, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL,
			adapterFactory, (ScaStructSequenceProperty) property);
	}
}

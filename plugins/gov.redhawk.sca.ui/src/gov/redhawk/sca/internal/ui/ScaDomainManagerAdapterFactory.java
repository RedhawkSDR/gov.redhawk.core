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

import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.impl.StringToStringMapImpl;
import gov.redhawk.sca.internal.ui.wizards.ConnectionPropertiesWizard;
import gov.redhawk.sca.ui.RedhawkUiAdapterFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

/**
 * 
 */
public class ScaDomainManagerAdapterFactory extends RedhawkUiAdapterFactory implements IAdapterFactory {

	public static class ConnectionPropertiesPropertyDescriptor extends PropertyDescriptor {

		public ConnectionPropertiesPropertyDescriptor(final Object object, final IItemPropertyDescriptor itemPropertyDescriptor) {
			super(object, itemPropertyDescriptor);
		}

		@Override
		public CellEditor createPropertyEditor(final Composite composite) {
			return new DialogCellEditor(composite) {

				@Override
				protected Object openDialogBox(final Control cellEditorWindow) {
					final ConnectionPropertiesWizard wizard = new ConnectionPropertiesWizard();
					final EMap< ? , ? > value = (EMap< ? , ? >) getValue();
					wizard.setStringMap(value);
					final WizardDialog dialog = new WizardDialog(getControl().getShell(), wizard);
					if (dialog.open() == Window.OK) {
						return new BasicEMap<String, String>(wizard.getStringMap());
					}
					return null;
				}

				@Override
				protected void updateContents(final Object value) {
					final EMap< ? , ? > map = (EMap< ? , ? >) value;
					final StringBuilder builder = new StringBuilder();
					if (map != null) {
						for (final Iterator< ? > i = map.iterator(); i.hasNext();) {
							final Map.Entry< ? , ? > entry = (Entry< ? , ? >) i.next();
							builder.append(entry.getKey());
							builder.append("->");
							builder.append(entry.getValue());
							if (i.hasNext()) {
								builder.append(", ");
							}
						}
					}
					super.updateContents(builder);
				}
			};
		}
	}

	public static class ScaDomainManagerPropertySource extends StatusProviderObjectAdapterFactory.DataProviderPropertySource {

		public ScaDomainManagerPropertySource(final Object object, final IItemPropertySource itemPropertySource) {
			super(object, itemPropertySource);
		}

		@Override
		protected IPropertyDescriptor createPropertyDescriptor(final IItemPropertyDescriptor itemPropertyDescriptor) {
			if (itemPropertyDescriptor.getFeature(this.object) == ScaPackage.Literals.SCA_DOMAIN_MANAGER__CONNECTION_PROPERTIES) {
				return new ConnectionPropertiesPropertyDescriptor(this.object, itemPropertyDescriptor);
			} else {
				return super.createPropertyDescriptor(itemPropertyDescriptor);
			}
		}

		@Override
		public void setPropertyValue(final Object propertyId, Object value) {
			if (propertyId.equals(ScaPackage.Literals.SCA_DOMAIN_MANAGER__CONNECTION_PROPERTIES.getName())) {
				final List<StringToStringMapImpl> list = new ArrayList<StringToStringMapImpl>();
				for (final Entry<String, String> e : (List<Map.Entry<String, String>>) value) {
					final StringToStringMapImpl mapEntry = (StringToStringMapImpl) ScaFactory.eINSTANCE.create(ScaPackage.Literals.STRING_TO_STRING_MAP);
					mapEntry.setKey(e.getKey());
					mapEntry.setValue(e.getValue());
					list.add(mapEntry);
				}
				value = list;
			}
			super.setPropertyValue(propertyId, value);
		}

	}

	@Override
	protected IPropertySource createPropertySource(final Object adaptableObject, final IItemPropertySource itemPropertySource) {
		return new ScaDomainManagerPropertySource(adaptableObject, itemPropertySource);
	}
}

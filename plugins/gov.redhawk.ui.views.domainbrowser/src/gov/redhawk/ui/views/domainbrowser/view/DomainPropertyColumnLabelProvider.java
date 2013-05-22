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
package gov.redhawk.ui.views.domainbrowser.view;

import org.eclipse.emf.edit.provider.AttributeValueWrapperItemProvider;
import org.eclipse.emf.edit.provider.DelegatingWrapperItemProvider;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.ui.views.properties.PropertyColumnLabelProvider;

import CF.DevicePackage.AdminType;
import CF.DevicePackage.OperationalType;
import CF.DevicePackage.UsageType;

public class DomainPropertyColumnLabelProvider extends PropertyColumnLabelProvider {
	public DomainPropertyColumnLabelProvider(final IPropertySourceProvider propertySourceProvider, final Object propertyID) {
		super(propertySourceProvider, propertyID);
	}

	@Override
	public String getText(final Object object) {
		String retVal = null;
		if (object instanceof DelegatingWrapperItemProvider) {
			final DelegatingWrapperItemProvider wrapper = (DelegatingWrapperItemProvider) object;

			if (wrapper.getValue() instanceof AttributeValueWrapperItemProvider) {
				retVal = getValueFromWrapper((AttributeValueWrapperItemProvider) wrapper.getValue());
			}
		}

		if (object instanceof AttributeValueWrapperItemProvider) {
			retVal = getValueFromWrapper((AttributeValueWrapperItemProvider) object);
		}

		if (retVal != null) {
			return retVal;
		}

		return super.getText(object);
	}

	private String getValueFromWrapper(final AttributeValueWrapperItemProvider attr) {
		if (attr.getValue() != null) {
			if (attr.getValue() instanceof String) {
				return (String) attr.getValue();
			} else if (attr.getValue() instanceof UsageType) {
				switch (((UsageType) attr.getValue()).value()) {
				case UsageType._ACTIVE:
					return "ACTIVE";
				case UsageType._BUSY:
					return "BUSY";
				case UsageType._IDLE:
					return "IDLE";
				default:
					break;
				}
			} else if (attr.getValue() instanceof OperationalType) {
				switch (((OperationalType) attr.getValue()).value()) {
				case OperationalType._DISABLED:
					return "DISABLED";
				case OperationalType._ENABLED:
					return "ENABLED";
				default:
					break;
				}
			} else if (attr.getValue() instanceof AdminType) {
				switch (((AdminType) attr.getValue()).value()) {
				case AdminType._LOCKED:
					return "LOCKED";
				case AdminType._UNLOCKED:
					return "UNLOCKED";
				case AdminType._SHUTTING_DOWN:
					return "SHUTTING_DOWN";
				default:
					break;
				}
			}
		}
		return "";
	}
}

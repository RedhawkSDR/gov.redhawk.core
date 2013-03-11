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
package gov.redhawk.eclipsecorba.idl.provider.decorator;

import gov.redhawk.eclipsecorba.idl.IdlInterfaceDcl;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;
import org.eclipse.jface.viewers.IDecoration;
import org.eclipse.jface.viewers.ILightweightLabelDecorator;
import org.eclipse.jface.viewers.LabelProvider;

/**
 * 
 */
public class IdlInterfaceDclDecorator extends LabelProvider implements ILightweightLabelDecorator {

	/**
	 * {@inheritDoc}
	 */
	public void decorate(final Object element, final IDecoration decoration) {
		if (!(element instanceof EObject || element instanceof FeatureMap.Entry || element instanceof IWrapperItemProvider)) {
			return;
		}

		EObject object = null;
		if (element instanceof EObject) {
			object = (EObject) element;
		} else {
			final Object unwrapped = AdapterFactoryEditingDomain.unwrap(element);
			if (unwrapped instanceof EObject) {
				object = (EObject) unwrapped;
			}
		}

		if (object instanceof IdlInterfaceDcl) {
			final IdlInterfaceDcl inter = (IdlInterfaceDcl) object;
			final StringBuilder label = new StringBuilder();
			if (!inter.getInheritedInterfaces().isEmpty()) {
				label.append(" -> ");
				for (final Iterator<IdlInterfaceDcl> i = inter.getInheritedInterfaces().iterator(); i.hasNext();) {
					label.append(i.next().getName());
					if (i.hasNext()) {
						label.append(", ");
					}
				}
			}

			decoration.addSuffix(label.toString());
		}

	}

}

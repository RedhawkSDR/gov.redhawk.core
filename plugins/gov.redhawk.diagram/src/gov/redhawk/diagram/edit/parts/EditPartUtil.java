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
package gov.redhawk.diagram.edit.parts;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @since 3.0
 * 
 */
public final class EditPartUtil {
	private EditPartUtil() {

	}

	public static EObject getSemanticModelObject(final EditPart editPart) {
		final Object model = editPart.getModel();
		if (model instanceof View) {
			final View view = (View) model;
			return view.getElement();
		}
		return null;
	}
}

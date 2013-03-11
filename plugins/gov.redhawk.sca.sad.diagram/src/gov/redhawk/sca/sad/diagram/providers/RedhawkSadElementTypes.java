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
package gov.redhawk.sca.sad.diagram.providers;

import gov.redhawk.diagram.providers.PartitioningElementTypes;
import mil.jpeojtrs.sca.sad.diagram.providers.SadElementTypes;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.swt.graphics.Image;

/**
 * 
 */
public enum RedhawkSadElementTypes implements PartitioningElementTypes {
	INSTANCE;
	private RedhawkSadElementTypes() {

	}

	public Image getImage(final EClass eClass) {
		return SadElementTypes.getImage(eClass);
	}

	public IElementType getProvidesPortStubElementType() {
		return SadElementTypes.ProvidesPortStub_3004;
	}

	public IElementType getConnectInterfaceElementType() {
		return SadElementTypes.SadConnectInterface_4001;
	}

	public IElementType getComponentSupportedInterfaceStubElementType() {
		return SadElementTypes.ComponentSupportedInterfaceStub_3005;
	}

	public IElementType getUsesPortStubElementType() {
		return SadElementTypes.UsesPortStub_3003;
	}

	public IElementType getElementType(final int visualID) {
		return SadElementTypes.getElementType(visualID);
	}
}

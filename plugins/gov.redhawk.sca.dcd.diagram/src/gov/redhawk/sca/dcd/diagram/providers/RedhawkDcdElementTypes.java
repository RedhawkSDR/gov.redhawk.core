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
package gov.redhawk.sca.dcd.diagram.providers;

import gov.redhawk.diagram.providers.PartitioningElementTypes;
import mil.jpeojtrs.sca.dcd.diagram.providers.DcdElementTypes;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.swt.graphics.Image;

/**
 * 
 */
public enum RedhawkDcdElementTypes implements PartitioningElementTypes {
	INSTANCE;

	/**
	 * {@inheritDoc}
	 */
	public Image getImage(final EClass eClass) {
		return DcdElementTypes.getImage(eClass);
	}

	/**
	 * {@inheritDoc}
	 */
	public IElementType getProvidesPortStubElementType() {
		return DcdElementTypes.ProvidesPortStub_3004;
	}

	/**
	 * {@inheritDoc}
	 */
	public IElementType getConnectInterfaceElementType() {
		return DcdElementTypes.DcdConnectInterface_4001;
	}

	/**
	 * {@inheritDoc}
	 */
	public IElementType getComponentSupportedInterfaceStubElementType() {
		return DcdElementTypes.ComponentSupportedInterfaceStub_3005;
	}

	/**
	 * {@inheritDoc}
	 */
	public IElementType getUsesPortStubElementType() {
		return DcdElementTypes.UsesPortStub_3003;
	}

	/**
	 * {@inheritDoc}
	 */
	public IElementType getElementType(final int visualID) {
		return DcdElementTypes.getElementType(visualID);
	}

}

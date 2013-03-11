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
package gov.redhawk.diagram.providers;

import gov.redhawk.diagram.edit.parts.DomainFinderEditPart;
import gov.redhawk.diagram.edit.parts.FindByStubEditPart;
import gov.redhawk.diagram.edit.parts.NamingServiceEditPart;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.swt.graphics.Image;

/**
 * @since 3.0
 * 
 */
public interface PartitioningElementTypes {
	// BEGIN GENERATED CODE
	public static final IElementType FindByStub = PartitioningElementTypes.Util
	        .getElementType("mil.jpeojtrs.sca.partitioning.diagram.FindByStub_" + FindByStubEditPart.VISUAL_ID); //$NON-NLS-1$

	public static final IElementType NamingService = PartitioningElementTypes.Util
	        .getElementType("mil.jpeojtrs.sca.partitioning.diagram.NamingService_" + NamingServiceEditPart.VISUAL_ID); //$NON-NLS-1$

	public static final IElementType DomainFinder = PartitioningElementTypes.Util
	        .getElementType("mil.jpeojtrs.sca.partitioning.diagram.DomainFinder_" + DomainFinderEditPart.VISUAL_ID); //$NON-NLS-1$

	// END GENERATED CODE

	public static final class Util {
		private Util() {

		}

		private static IElementType getElementType(final String id) {
			return ElementTypeRegistry.getInstance().getType(id);
		}
	}

	Image getImage(EClass eClass);

	IElementType getProvidesPortStubElementType();

	IElementType getConnectInterfaceElementType();

	IElementType getComponentSupportedInterfaceStubElementType();

	IElementType getUsesPortStubElementType();

	IElementType getElementType(int visualID);

}

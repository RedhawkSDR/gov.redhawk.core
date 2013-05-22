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
// BEGIN GENERATED CODE

package mil.jpeojtrs.sca.sad.diagram.providers;

import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import mil.jpeojtrs.sca.partitioning.PartitioningPackage;
import mil.jpeojtrs.sca.sad.SadPackage;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentPlacement2EditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentPlacementEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentSupportedInterfaceStubEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.HostCollocationEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.PartitioningEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ProvidesPortStubEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.SadComponentInstantiationEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.SadConnectInterfaceEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.SoftwareAssemblyEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.UsesPortStubEditPart;
import mil.jpeojtrs.sca.sad.diagram.part.SadDiagramEditorPlugin;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.emf.type.core.ElementTypeRegistry;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;

/**
 * @generated
 */
public class SadElementTypes {

	/**
	 * @generated
	 */
	private SadElementTypes() {
	}

	/**
	 * @generated
	 */
	private static Map<IElementType, ENamedElement> elements;
	/**
	 * @generated
	 */
	private static ImageRegistry imageRegistry;
	/**
	 * @generated
	 */
	private static Set<IElementType> KNOWN_ELEMENT_TYPES;
	/**
	 * @generated
	 */
	public static final IElementType SoftwareAssembly_1000 = getElementType("mil.jpeojtrs.sca.sad.diagram.SoftwareAssembly_1000"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType SadPartitioning_2001 = getElementType("mil.jpeojtrs.sca.sad.diagram.SadPartitioning_2001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType SadComponentPlacement_3001 = getElementType("mil.jpeojtrs.sca.sad.diagram.SadComponentPlacement_3001"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType SadComponentInstantiation_3002 = getElementType("mil.jpeojtrs.sca.sad.diagram.SadComponentInstantiation_3002"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType UsesPortStub_3003 = getElementType("mil.jpeojtrs.sca.sad.diagram.UsesPortStub_3003"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType ProvidesPortStub_3004 = getElementType("mil.jpeojtrs.sca.sad.diagram.ProvidesPortStub_3004"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType ComponentSupportedInterfaceStub_3005 = getElementType("mil.jpeojtrs.sca.sad.diagram.ComponentSupportedInterfaceStub_3005"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType HostCollocation_3006 = getElementType("mil.jpeojtrs.sca.sad.diagram.HostCollocation_3006"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType SadComponentPlacement_3007 = getElementType("mil.jpeojtrs.sca.sad.diagram.SadComponentPlacement_3007"); //$NON-NLS-1$
	/**
	 * @generated
	 */
	public static final IElementType SadConnectInterface_4001 = getElementType("mil.jpeojtrs.sca.sad.diagram.SadConnectInterface_4001"); //$NON-NLS-1$

	/**
	 * @generated
	 */
	private static ImageRegistry getImageRegistry() {
		if (imageRegistry == null) {
			imageRegistry = new ImageRegistry();
		}
		return imageRegistry;
	}

	/**
	 * @generated
	 */
	private static String getImageRegistryKey(ENamedElement element) {
		return element.getName();
	}

	/**
	 * @generated
	 */
	private static ImageDescriptor getProvidedImageDescriptor(
			ENamedElement element) {
		if (element instanceof EStructuralFeature) {
			EStructuralFeature feature = ((EStructuralFeature) element);
			EClass eContainingClass = feature.getEContainingClass();
			EClassifier eType = feature.getEType();
			if (eContainingClass != null && !eContainingClass.isAbstract()) {
				element = eContainingClass;
			} else if (eType instanceof EClass
					&& !((EClass) eType).isAbstract()) {
				element = eType;
			}
		}
		if (element instanceof EClass) {
			EClass eClass = (EClass) element;
			if (!eClass.isAbstract()) {
				return SadDiagramEditorPlugin.getInstance()
						.getItemImageDescriptor(
								eClass.getEPackage().getEFactoryInstance()
										.create(eClass));
			}
		}
		// TODO : support structural features
		return null;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(ENamedElement element) {
		String key = getImageRegistryKey(element);
		ImageDescriptor imageDescriptor = getImageRegistry().getDescriptor(key);
		if (imageDescriptor == null) {
			imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
		}
		return imageDescriptor;
	}

	/**
	 * @generated
	 */
	public static Image getImage(ENamedElement element) {
		String key = getImageRegistryKey(element);
		Image image = getImageRegistry().get(key);
		if (image == null) {
			ImageDescriptor imageDescriptor = getProvidedImageDescriptor(element);
			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			getImageRegistry().put(key, imageDescriptor);
			image = getImageRegistry().get(key);
		}
		return image;
	}

	/**
	 * @generated
	 */
	public static ImageDescriptor getImageDescriptor(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImageDescriptor(element);
	}

	/**
	 * @generated
	 */
	public static Image getImage(IAdaptable hint) {
		ENamedElement element = getElement(hint);
		if (element == null) {
			return null;
		}
		return getImage(element);
	}

	/**
	 * Returns 'type' of the ecore object associated with the hint.
	 * 
	 * @generated
	 */
	public static ENamedElement getElement(IAdaptable hint) {
		Object type = hint.getAdapter(IElementType.class);
		if (elements == null) {
			elements = new IdentityHashMap<IElementType, ENamedElement>();

			elements.put(SoftwareAssembly_1000,
					SadPackage.eINSTANCE.getSoftwareAssembly());

			elements.put(SadPartitioning_2001,
					SadPackage.eINSTANCE.getSadPartitioning());

			elements.put(SadComponentPlacement_3001,
					SadPackage.eINSTANCE.getSadComponentPlacement());

			elements.put(SadComponentInstantiation_3002,
					SadPackage.eINSTANCE.getSadComponentInstantiation());

			elements.put(UsesPortStub_3003,
					PartitioningPackage.eINSTANCE.getUsesPortStub());

			elements.put(ProvidesPortStub_3004,
					PartitioningPackage.eINSTANCE.getProvidesPortStub());

			elements.put(ComponentSupportedInterfaceStub_3005,
					PartitioningPackage.eINSTANCE
							.getComponentSupportedInterfaceStub());

			elements.put(HostCollocation_3006,
					SadPackage.eINSTANCE.getHostCollocation());

			elements.put(SadComponentPlacement_3007,
					SadPackage.eINSTANCE.getSadComponentPlacement());

			elements.put(SadConnectInterface_4001,
					SadPackage.eINSTANCE.getSadConnectInterface());
		}
		return (ENamedElement) elements.get(type);
	}

	/**
	 * @generated
	 */
	private static IElementType getElementType(String id) {
		return ElementTypeRegistry.getInstance().getType(id);
	}

	/**
	 * @generated
	 */
	public static boolean isKnownElementType(IElementType elementType) {
		if (KNOWN_ELEMENT_TYPES == null) {
			KNOWN_ELEMENT_TYPES = new HashSet<IElementType>();
			KNOWN_ELEMENT_TYPES.add(SoftwareAssembly_1000);
			KNOWN_ELEMENT_TYPES.add(SadPartitioning_2001);
			KNOWN_ELEMENT_TYPES.add(SadComponentPlacement_3001);
			KNOWN_ELEMENT_TYPES.add(SadComponentInstantiation_3002);
			KNOWN_ELEMENT_TYPES.add(UsesPortStub_3003);
			KNOWN_ELEMENT_TYPES.add(ProvidesPortStub_3004);
			KNOWN_ELEMENT_TYPES.add(ComponentSupportedInterfaceStub_3005);
			KNOWN_ELEMENT_TYPES.add(HostCollocation_3006);
			KNOWN_ELEMENT_TYPES.add(SadComponentPlacement_3007);
			KNOWN_ELEMENT_TYPES.add(SadConnectInterface_4001);
		}
		return KNOWN_ELEMENT_TYPES.contains(elementType);
	}

	/**
	 * @generated
	 */
	public static IElementType getElementType(int visualID) {
		switch (visualID) {
		case SoftwareAssemblyEditPart.VISUAL_ID:
			return SoftwareAssembly_1000;
		case PartitioningEditPart.VISUAL_ID:
			return SadPartitioning_2001;
		case ComponentPlacementEditPart.VISUAL_ID:
			return SadComponentPlacement_3001;
		case SadComponentInstantiationEditPart.VISUAL_ID:
			return SadComponentInstantiation_3002;
		case UsesPortStubEditPart.VISUAL_ID:
			return UsesPortStub_3003;
		case ProvidesPortStubEditPart.VISUAL_ID:
			return ProvidesPortStub_3004;
		case ComponentSupportedInterfaceStubEditPart.VISUAL_ID:
			return ComponentSupportedInterfaceStub_3005;
		case HostCollocationEditPart.VISUAL_ID:
			return HostCollocation_3006;
		case ComponentPlacement2EditPart.VISUAL_ID:
			return SadComponentPlacement_3007;
		case SadConnectInterfaceEditPart.VISUAL_ID:
			return SadConnectInterface_4001;
		}
		return null;
	}

}

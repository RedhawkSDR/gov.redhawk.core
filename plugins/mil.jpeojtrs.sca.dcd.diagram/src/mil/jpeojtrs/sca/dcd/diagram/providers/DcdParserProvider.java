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
package mil.jpeojtrs.sca.dcd.diagram.providers;

import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdComponentInstantiationUsageNameEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.DcdComponentPlacementNameEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.ProvidesPortStubNameEditPart;
import mil.jpeojtrs.sca.dcd.diagram.edit.parts.UsesPortStubNameEditPart;
import mil.jpeojtrs.sca.dcd.diagram.parsers.MessageFormatParser;
import mil.jpeojtrs.sca.dcd.diagram.part.DcdVisualIDRegistry;
import mil.jpeojtrs.sca.partitioning.PartitioningPackage;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.AbstractProvider;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @generated
 */
public class DcdParserProvider extends AbstractProvider implements
		IParserProvider {

	/**
	 * @generated
	 */
	private IParser dcdComponentPlacementName_5005Parser;

	/**
	 * @generated
	 */
	private IParser getDcdComponentPlacementName_5005Parser() {
		if (dcdComponentPlacementName_5005Parser == null) {
			EAttribute[] features = new EAttribute[] { PartitioningPackage.eINSTANCE
					.getComponentPlacement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			dcdComponentPlacementName_5005Parser = parser;
		}
		return dcdComponentPlacementName_5005Parser;
	}

	/**
	 * @generated
	 */
	private IParser dcdComponentInstantiationUsageName_5004Parser;

	/**
	 * @generated
	 */
	private IParser getDcdComponentInstantiationUsageName_5004Parser() {
		if (dcdComponentInstantiationUsageName_5004Parser == null) {
			EAttribute[] features = new EAttribute[] { PartitioningPackage.eINSTANCE
					.getComponentInstantiation_UsageName() };
			MessageFormatParser parser = new MessageFormatParser(features);
			dcdComponentInstantiationUsageName_5004Parser = parser;
		}
		return dcdComponentInstantiationUsageName_5004Parser;
	}

	/**
	 * @generated
	 */
	private IParser usesPortStubName_5001Parser;

	/**
	 * @generated
	 */
	private IParser getUsesPortStubName_5001Parser() {
		if (usesPortStubName_5001Parser == null) {
			EAttribute[] features = new EAttribute[] { PartitioningPackage.eINSTANCE
					.getUsesPortStub_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			usesPortStubName_5001Parser = parser;
		}
		return usesPortStubName_5001Parser;
	}

	/**
	 * @generated
	 */
	private IParser providesPortStubName_5002Parser;

	/**
	 * @generated
	 */
	private IParser getProvidesPortStubName_5002Parser() {
		if (providesPortStubName_5002Parser == null) {
			EAttribute[] features = new EAttribute[] { PartitioningPackage.eINSTANCE
					.getProvidesPortStub_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			providesPortStubName_5002Parser = parser;
		}
		return providesPortStubName_5002Parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case DcdComponentPlacementNameEditPart.VISUAL_ID:
			return getDcdComponentPlacementName_5005Parser();
		case DcdComponentInstantiationUsageNameEditPart.VISUAL_ID:
			return getDcdComponentInstantiationUsageName_5004Parser();
		case UsesPortStubNameEditPart.VISUAL_ID:
			return getUsesPortStubName_5001Parser();
		case ProvidesPortStubNameEditPart.VISUAL_ID:
			return getProvidesPortStubName_5002Parser();
		}
		return null;
	}

	/**
	 * Utility method that consults ParserService
	 * @generated
	 */
	public static IParser getParser(IElementType type, EObject object,
			String parserHint) {
		return ParserService.getInstance().getParser(
				new HintAdapter(type, object, parserHint));
	}

	/**
	 * @generated
	 */
	public IParser getParser(IAdaptable hint) {
		String vid = (String) hint.getAdapter(String.class);
		if (vid != null) {
			return getParser(DcdVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(DcdVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (DcdElementTypes.getElement(hint) == null) {
				return false;
			}
			return getParser(hint) != null;
		}
		return false;
	}

	/**
	 * @generated
	 */
	private static class HintAdapter extends ParserHintAdapter {

		/**
		 * @generated
		 */
		private final IElementType elementType;

		/**
		 * @generated
		 */
		public HintAdapter(IElementType type, EObject object, String parserHint) {
			super(object, parserHint);
			assert type != null;
			elementType = type;
		}

		/**
		 * @generated
		 */
		public Object getAdapter(Class adapter) {
			if (IElementType.class.equals(adapter)) {
				return elementType;
			}
			return super.getAdapter(adapter);
		}
	}

}

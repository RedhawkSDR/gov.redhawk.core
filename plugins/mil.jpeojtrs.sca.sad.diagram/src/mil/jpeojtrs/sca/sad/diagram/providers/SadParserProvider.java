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
// BEGIN GENERATED CODE

package mil.jpeojtrs.sca.sad.diagram.providers;

import mil.jpeojtrs.sca.partitioning.PartitioningPackage;
import mil.jpeojtrs.sca.sad.SadPackage;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentPlacementName2EditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ComponentPlacementNameEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.HostCollocationIdEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.HostCollocationNameEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.ProvidesPortStubNameEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.SadComponentInstantiationUsageNameEditPart;
import mil.jpeojtrs.sca.sad.diagram.edit.parts.UsesPortStubNameEditPart;
import mil.jpeojtrs.sca.sad.diagram.parsers.MessageFormatParser;
import mil.jpeojtrs.sca.sad.diagram.part.SadVisualIDRegistry;

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
public class SadParserProvider extends AbstractProvider implements
		IParserProvider {

	/**
	 * @generated
	 */
	private IParser sadComponentPlacementName_5006Parser;

	/**
	 * @generated
	 */
	private IParser getSadComponentPlacementName_5006Parser() {
		if (sadComponentPlacementName_5006Parser == null) {
			EAttribute[] features = new EAttribute[] { PartitioningPackage.eINSTANCE
					.getComponentPlacement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			sadComponentPlacementName_5006Parser = parser;
		}
		return sadComponentPlacementName_5006Parser;
	}

	/**
	 * @generated
	 */
	private IParser sadComponentInstantiationUsageName_5004Parser;

	/**
	 * @generated
	 */
	private IParser getSadComponentInstantiationUsageName_5004Parser() {
		if (sadComponentInstantiationUsageName_5004Parser == null) {
			EAttribute[] features = new EAttribute[] { PartitioningPackage.eINSTANCE
					.getComponentInstantiation_UsageName() };
			MessageFormatParser parser = new MessageFormatParser(features);
			sadComponentInstantiationUsageName_5004Parser = parser;
		}
		return sadComponentInstantiationUsageName_5004Parser;
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
	private IParser hostCollocationName_5008Parser;

	/**
	 * @generated
	 */
	private IParser getHostCollocationName_5008Parser() {
		if (hostCollocationName_5008Parser == null) {
			EAttribute[] features = new EAttribute[] { SadPackage.eINSTANCE
					.getHostCollocation_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			hostCollocationName_5008Parser = parser;
		}
		return hostCollocationName_5008Parser;
	}

	/**
	 * @generated
	 */
	private IParser hostCollocationId_5009Parser;

	/**
	 * @generated
	 */
	private IParser getHostCollocationId_5009Parser() {
		if (hostCollocationId_5009Parser == null) {
			EAttribute[] features = new EAttribute[] { SadPackage.eINSTANCE
					.getHostCollocation_Id() };
			MessageFormatParser parser = new MessageFormatParser(features);
			hostCollocationId_5009Parser = parser;
		}
		return hostCollocationId_5009Parser;
	}

	/**
	 * @generated
	 */
	private IParser sadComponentPlacementName_5007Parser;

	/**
	 * @generated
	 */
	private IParser getSadComponentPlacementName_5007Parser() {
		if (sadComponentPlacementName_5007Parser == null) {
			EAttribute[] features = new EAttribute[] { PartitioningPackage.eINSTANCE
					.getComponentPlacement_Name() };
			MessageFormatParser parser = new MessageFormatParser(features);
			sadComponentPlacementName_5007Parser = parser;
		}
		return sadComponentPlacementName_5007Parser;
	}

	/**
	 * @generated
	 */
	protected IParser getParser(int visualID) {
		switch (visualID) {
		case ComponentPlacementNameEditPart.VISUAL_ID:
			return getSadComponentPlacementName_5006Parser();
		case SadComponentInstantiationUsageNameEditPart.VISUAL_ID:
			return getSadComponentInstantiationUsageName_5004Parser();
		case UsesPortStubNameEditPart.VISUAL_ID:
			return getUsesPortStubName_5001Parser();
		case ProvidesPortStubNameEditPart.VISUAL_ID:
			return getProvidesPortStubName_5002Parser();
		case HostCollocationNameEditPart.VISUAL_ID:
			return getHostCollocationName_5008Parser();
		case HostCollocationIdEditPart.VISUAL_ID:
			return getHostCollocationId_5009Parser();
		case ComponentPlacementName2EditPart.VISUAL_ID:
			return getSadComponentPlacementName_5007Parser();
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
			return getParser(SadVisualIDRegistry.getVisualID(vid));
		}
		View view = (View) hint.getAdapter(View.class);
		if (view != null) {
			return getParser(SadVisualIDRegistry.getVisualID(view));
		}
		return null;
	}

	/**
	 * @generated
	 */
	public boolean provides(IOperation operation) {
		if (operation instanceof GetParserOperation) {
			IAdaptable hint = ((GetParserOperation) operation).getHint();
			if (SadElementTypes.getElement(hint) == null) {
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

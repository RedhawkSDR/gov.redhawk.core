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
package gov.redhawk.diagram.providers;

import gov.redhawk.diagram.edit.parts.DomainFinderNameEditPart;
import gov.redhawk.diagram.edit.parts.DomainFinderTypeEditPart;
import gov.redhawk.diagram.edit.parts.NamingServiceNameEditPart;
import mil.jpeojtrs.sca.partitioning.PartitioningPackage;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.GetParserOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.ParserService;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.ui.services.parser.ParserHintAdapter;

/**
 * @since 3.0
 * 
 */
public class PartitioningParserProviderHelper {
	/**
	* 
	*/
	private IParser namingServiceNameParser;
	private final IPartitioningParserProvider provider;

	public PartitioningParserProviderHelper(final IPartitioningParserProvider provider) {
		this.provider = provider;
	}

	/**
	* 
	*/
	private IParser getNamingServiceName_5013Parser() {
		if (this.namingServiceNameParser == null) {
			final EAttribute[] features = new EAttribute[] { PartitioningPackage.eINSTANCE.getNamingService_Name() };
			this.namingServiceNameParser = this.provider.createMessageFormatParser(features);
		}
		return this.namingServiceNameParser;
	}

	public static IParser getParser(final IElementType type, final EObject object, final String parserHint) {
		return ParserService.getInstance().getParser(new HintAdapter(type, object, parserHint));
	}

	/**
	* 
	*/
	private IParser domainFinderNameParser;

	/**
	* 
	*/
	private IParser getDomainFinderName_5014Parser() {
		if (this.domainFinderNameParser == null) {
			final EAttribute[] features = new EAttribute[] { PartitioningPackage.eINSTANCE.getDomainFinder_Name() };
			this.domainFinderNameParser = this.provider.createMessageFormatParser(features);
		}
		return this.domainFinderNameParser;
	}

	/**
	* 
	*/
	private IParser domainFinderTypeParser;

	/**
	* 
	*/
	private IParser getDomainFinderType_5015Parser() {
		if (this.domainFinderTypeParser == null) {
			final EAttribute[] features = new EAttribute[] { PartitioningPackage.eINSTANCE.getDomainFinder_Type() };
			this.domainFinderTypeParser = this.provider.createMessageFormatParser(features);
		}
		return this.domainFinderTypeParser;
	}

	/**
	* 
	*/
	public IParser getParser(final int visualID) {
		switch (visualID) {
		case NamingServiceNameEditPart.VISUAL_ID:
			return getNamingServiceName_5013Parser();
		case DomainFinderNameEditPart.VISUAL_ID:
			return getDomainFinderName_5014Parser();
		case DomainFinderTypeEditPart.VISUAL_ID:
			return getDomainFinderType_5015Parser();
		default:
			break;
		}
		return this.provider.basicGetParser(visualID);
	}

	public boolean provides(final IOperation operation) {
		if (operation instanceof GetParserOperation) {
			final IAdaptable hint = ((GetParserOperation) operation).getHint();
			final IElementType element = (IElementType) hint.getAdapter(IElementType.class);
			if (element == PartitioningElementTypes.DomainFinder || element == PartitioningElementTypes.FindByStub
			        || element == PartitioningElementTypes.NamingService) {
				return this.provider.getParser(hint) != null;
			}
		}
		return this.provider.basicProvides(operation);
	}

	private static class HintAdapter extends ParserHintAdapter {

		private final IElementType elementType;

		public HintAdapter(final IElementType type, final EObject object, final String parserHint) {
			super(object, parserHint);
			assert type != null;
			this.elementType = type;
		}

		@Override
		public Object getAdapter(final Class adapter) {
			if (IElementType.class.equals(adapter)) {
				return this.elementType;
			}
			return super.getAdapter(adapter);
		}
	}
}

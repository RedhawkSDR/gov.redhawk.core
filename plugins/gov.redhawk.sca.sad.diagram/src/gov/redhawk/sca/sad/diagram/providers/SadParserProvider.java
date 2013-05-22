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
package gov.redhawk.sca.sad.diagram.providers;

import gov.redhawk.diagram.providers.IPartitioningParserProvider;
import gov.redhawk.diagram.providers.PartitioningParserProviderHelper;
import mil.jpeojtrs.sca.sad.diagram.parsers.MessageFormatParser;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParser;
import org.eclipse.gmf.runtime.common.ui.services.parser.IParserProvider;

/**
 * 
 */
public class SadParserProvider extends mil.jpeojtrs.sca.sad.diagram.providers.SadParserProvider implements IParserProvider, IPartitioningParserProvider {

	private final PartitioningParserProviderHelper parserProviderHelper = new PartitioningParserProviderHelper(this);

	public SadParserProvider() {

	}

	/**
	* 
	*/
	@Override
	protected IParser getParser(final int visualID) {
		return this.parserProviderHelper.getParser(visualID);
	}

	@Override
	public boolean provides(final IOperation operation) {
		return this.parserProviderHelper.provides(operation);
	}

	public IParser basicGetParser(final int visualID) {
		return super.getParser(visualID);
	}

	public boolean basicProvides(final IOperation operation) {
		return super.provides(operation);
	}

	public IParser createMessageFormatParser(final EAttribute[] features) {
		return new MessageFormatParser(features);
	}
}

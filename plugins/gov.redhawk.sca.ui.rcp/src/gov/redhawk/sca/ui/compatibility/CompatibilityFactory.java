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
package gov.redhawk.sca.ui.compatibility;

import org.eclipse.jface.fieldassist.ContentProposalAdapter;
import org.eclipse.jface.fieldassist.IContentProposalProvider;
import org.eclipse.jface.fieldassist.IControlContentAdapter;
import org.eclipse.swt.widgets.Control;

/**
 * @since 10.0
 * 
 */
public final class CompatibilityFactory {

	private CompatibilityFactory() {

	}

	/**
     * @since 10.0
     */
	public static ContentProposalAdapter createContentProposalAdapter(final Control control, final IControlContentAdapter controlContentAdapter,
	        final IContentProposalProvider proposalProvider, final char[] autoActivationCharacters) {
		return new ContentProposalAdapter(control, controlContentAdapter, proposalProvider, null, autoActivationCharacters);
	}

}

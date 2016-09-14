/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package gov.redhawk.core.graphiti.ui.internal.style;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.graphiti.mm.algorithms.styles.Style;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.PictogramsFactory;

import gov.redhawk.core.graphiti.ui.util.StyleUtil;

public class StyleResource extends ResourceImpl {

	/**
	 * Base URI for referencing styles
	 */
	public static final URI STYLE_URI = URI.createPlatformPluginURI("gov.redhawk.ide.graphiti.ui/style", false);

	private Diagram diagram;

	public StyleResource() {
		this(StyleResource.STYLE_URI);
	}

	public StyleResource(URI uri) {
		super(uri);
		diagram = PictogramsFactory.eINSTANCE.createDiagram();
		StyleUtil.createAllStyles(diagram);
		getContents().add(diagram);
	}

	@Override
	public String getURIFragment(EObject eObject) {
		if (eObject instanceof Style) {
			return ((Style) eObject).getId();
		}
		return super.getURIFragment(eObject);
	}

	@Override
	public EObject getEObject(String uriFragment) {
		return StyleUtil.findStyle(diagram, uriFragment);
	}
}

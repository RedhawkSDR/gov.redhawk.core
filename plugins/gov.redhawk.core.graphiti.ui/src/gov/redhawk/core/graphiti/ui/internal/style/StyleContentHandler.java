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

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.impl.ContentHandlerImpl;

public class StyleContentHandler extends ContentHandlerImpl {

	public static final String CONTENT_TYPE = "gov.redhawk.ide.graphiti.ui.style";

	@Override
	public boolean canHandle(URI uri) {
		return StyleResource.STYLE_URI.equals(uri);
	}

	@Override
	public Map<String, Object> contentDescription(URI uri, InputStream inputStream, Map< ? , ? > options, Map<Object, Object> context) throws IOException {
		Map<String, Object> description = super.contentDescription(uri, inputStream, options, context);
		description.put(ContentHandler.CONTENT_TYPE_PROPERTY, StyleContentHandler.CONTENT_TYPE);
		return description;
	}

}

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
package gov.redhawk.eclipsecorba.library.tests;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashSet;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.osgi.framework.Bundle;

/**
 * 
 */
public final class LibraryTestUtil {
	private LibraryTestUtil() {

	}

	public static URI getURI(final String pluginFilePath) throws IOException {
		URL url = FileLocator.find(Platform.getBundle("gov.redhawk.eclipsecorba.library.tests"), new Path(pluginFilePath), null);
		if (url != null) {
			return URI.createURI(FileLocator.toFileURL(url).toString());
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
    public static Collection<URI> getIdlURIs() throws IOException {
		Collection<URI> uris = new HashSet<URI>();
		Bundle bundle = Platform.getBundle("gov.redhawk.eclipsecorba.library.tests");
		for (Enumeration<URL> enumeration = bundle.findEntries("idl", "*.idl", true); enumeration.hasMoreElements();) {
			uris.add(URI.createURI(FileLocator.toFileURL(enumeration.nextElement()).toString()));
		}
		return uris;
	}

}

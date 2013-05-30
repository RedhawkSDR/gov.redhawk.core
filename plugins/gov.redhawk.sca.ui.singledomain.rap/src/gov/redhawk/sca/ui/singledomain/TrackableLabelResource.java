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
package gov.redhawk.sca.ui.singledomain;

import org.eclipse.rwt.resources.IResource;
import org.eclipse.rwt.resources.IResourceManager.RegisterOptions;

@SuppressWarnings("restriction")
public class TrackableLabelResource implements IResource {

    public ClassLoader getLoader() {
	   return this.getClass().getClassLoader();
    }

    public String getLocation() {
	    return "gov/redhawk/sca/ui/singledomain/TrackableLabel.js";
    }

    public String getCharset() {
	    return "ISO_8859_1";
    }

    public RegisterOptions getOptions() {
	    return RegisterOptions.VERSION_AND_COMPRESS;
    }

    public boolean isJSLibrary() {
	    return true;
    }

    public boolean isExternal() {
	    return false;
    }

}

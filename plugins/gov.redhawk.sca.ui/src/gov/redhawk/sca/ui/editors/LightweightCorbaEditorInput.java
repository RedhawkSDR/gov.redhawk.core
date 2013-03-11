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
package gov.redhawk.sca.ui.editors;

import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IURIEditorInput;

/**
 * @since 2.2
 */
public class LightweightCorbaEditorInput extends PlatformObject implements IEditorInput, IURIEditorInput {

	private final String ior;
	private final URI uri;
	private ImageDescriptor imageDescriptor;
	private String name = "SCA CORBA Object";
	private String tooltip = "";

	public LightweightCorbaEditorInput(final String ior, final URI uri) {
		this.ior = ior;
		this.uri = uri;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean exists() {
		return this.ior != null;
	}

	/**
	 * {@inheritDoc}
	 */
	public ImageDescriptor getImageDescriptor() {
		return this.imageDescriptor;
	}

	/**
	 * @param imageDescriptor the imageDescriptor to set
	 */
	public void setImageDescriptor(final ImageDescriptor imageDescriptor) {
		this.imageDescriptor = imageDescriptor;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * {@inheritDoc}
	 */
	public IPersistableElement getPersistable() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getToolTipText() {
		return this.tooltip;
	}

	/**
	 * @param tooltip the tooltip to set
	 */
	public void setTooltip(final String tooltip) {
		this.tooltip = tooltip;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this.ior != null && obj instanceof LightweightCorbaEditorInput) {
			return this.ior.equals(((LightweightCorbaEditorInput) obj).ior);
		}
		return super.equals(obj);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return this.ior.hashCode();
	}

	/**
	 * @return the ior
	 */
	public String getIor() {
		return this.ior;
	}

	/**
	 * @return the uri
	 */
	public URI getUri() {
		return this.uri;
	}

	/**
	 * @since 8.0
	 */
	public java.net.URI getURI() {
		return java.net.URI.create(this.uri.toString());
	}

}

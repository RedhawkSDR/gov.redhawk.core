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
package gov.redhawk.sca.ui.editors;

import java.net.URI;
import java.net.URISyntaxException;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.PlatformObject;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IURIEditorInput;

/**
 * @since 2.2
 * 
 */
public class EObjectEditorInput extends PlatformObject implements IEditorInput, IURIEditorInput {

	private final EObject input;

	public EObjectEditorInput(final EObject input) {
		Assert.isNotNull(input);
		this.input = input;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean exists() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		final Adapter adapter = EcoreUtil.getRegisteredAdapter(this.input, IItemLabelProvider.class);
		if (adapter instanceof IItemLabelProvider) {
			return ((IItemLabelProvider) adapter).getText(this.input);
		}

		final String retVal = EcoreUtil.getID(this.input);
		if (retVal == null) {
			return "";
		} else {
			return retVal;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IPersistableElement getPersistable() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getToolTipText() {
		return "";
	}

	public EObject getInput() {
		return this.input;
	}

	/**
	 * @since 8.0
	 */
	@Override
	public URI getURI() {
		if (this.input != null && this.input.eResource() != null && this.input.eResource().getURI() != null) {
			try {
				return new URI(this.input.eResource().getURI().toString());
			} catch (final URISyntaxException e) {
				// PASS
			}
		}
		return null;
	}

}

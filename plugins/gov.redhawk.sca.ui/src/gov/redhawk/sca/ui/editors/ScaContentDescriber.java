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

import gov.redhawk.model.sca.ICorbaObjectDescriptorAdapter;
import gov.redhawk.model.sca.IScaObjectIdentifierAdapter;

import java.io.IOException;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.IEditorInput;

/**
 * @since 2.2
 */
public class ScaContentDescriber implements IScaContentDescriber, IExecutableExtension {
	public static final String PARAM_PROFILE_ID = "profileId";
	public static final String PARAM_CORBA_REPID = "corbaRepId";
	public static final String PARAM_EDITOR_INPUT_TYPE = "editorInputType";
	/**
	 * @since 8.0
	 */
	public static final String PARAM_FILENAME = "fileName";
	private Map<String, String> params;

	/**
	 * 
	 */
	public ScaContentDescriber() {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int describe(final Object contents) throws IOException {
		if (this.params == null) {
			return IScaContentDescriber.INDETERMINATE;
		}

		final IAdapterManager adapterManager = Platform.getAdapterManager();

		Object obj = adapterManager.getAdapter(contents, IScaObjectIdentifierAdapter.class);
		IScaObjectIdentifierAdapter idAdapter = null;
		if (obj instanceof IScaObjectIdentifierAdapter) {
			idAdapter = (IScaObjectIdentifierAdapter) obj;
		}

		obj = adapterManager.getAdapter(contents, ICorbaObjectDescriptorAdapter.class);
		ICorbaObjectDescriptorAdapter corbaAdapter = null;
		if (obj instanceof ICorbaObjectDescriptorAdapter) {
			corbaAdapter = (ICorbaObjectDescriptorAdapter) obj;
		}

		boolean valid = false;

		final String profileFilename = this.params.get(ScaContentDescriber.PARAM_FILENAME);
		if (profileFilename != null) {
			if (idAdapter == null) {
				return IScaContentDescriber.INVALID;
			} else {
				final EObject profileObject = idAdapter.getScaEObject(contents);
				if (profileObject != null && profileObject.eResource() != null && profileObject.eResource().getURI() != null
						&& profileObject.eResource().getURI().lastSegment() != null && profileObject.eResource().getURI().lastSegment().matches(profileFilename)) {
					valid = true;
				} else {
					return IScaContentDescriber.INVALID;
				}
			}
		}

		final String scaId = this.params.get(ScaContentDescriber.PARAM_PROFILE_ID);
		if (scaId != null) {
			if (idAdapter == null) {
				return IScaContentDescriber.INVALID;
			} else {
				if (scaId.equals(idAdapter.getIdentifier(contents))) {
					valid = true;
				} else {
					return IScaContentDescriber.INVALID;
				}
			}
		}

		final String repId = this.params.get(ScaContentDescriber.PARAM_CORBA_REPID);
		if (repId != null) {
			if (corbaAdapter == null) {
				return IScaContentDescriber.INVALID;
			} else {
				if (corbaAdapter.supports(contents, repId)) {
					valid = true;
				} else {
					return IScaContentDescriber.INVALID;
				}
			}
		}
		if (valid) {
			return IScaContentDescriber.VALID;
		} else {
			return IScaContentDescriber.INDETERMINATE;
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IEditorInput getEditorInput(final Object contents) {
		final IAdapterManager adapterManager = Platform.getAdapterManager();

		if ("lightweight".equals(this.params.get(ScaContentDescriber.PARAM_EDITOR_INPUT_TYPE))) {
			final ICorbaObjectDescriptorAdapter corbaAdapter = (ICorbaObjectDescriptorAdapter) adapterManager.getAdapter(contents,
				ICorbaObjectDescriptorAdapter.class);
			final IScaObjectIdentifierAdapter idAdapter = (IScaObjectIdentifierAdapter) adapterManager.getAdapter(contents, IScaObjectIdentifierAdapter.class);
			String ior = null;
			if (corbaAdapter != null) {
				ior = corbaAdapter.getIOR(contents);
			}
			URI uri = null;
			if (idAdapter != null) {
				final EObject eObject = idAdapter.getScaEObject(contents);
				uri = EcoreUtil.getURI(eObject);
			}

			return new LightweightCorbaEditorInput(ior, uri);
		} else {
			return new EObjectEditorInput((EObject) adapterManager.getAdapter(contents, EObject.class));
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void setInitializationData(final IConfigurationElement config, final String propertyName, final Object data) throws CoreException {
		if (data instanceof Map) {
			this.params = (Map<String, String>) data;
		}

	}

}

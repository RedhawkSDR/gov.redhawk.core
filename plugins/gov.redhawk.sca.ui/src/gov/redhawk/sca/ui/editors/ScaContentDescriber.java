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
import gov.redhawk.sca.ui.ScaUiPlugin;

import java.io.IOException;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdapterManager;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.IEditorInput;

/**
 * Describes objects which can be adapted with {@link IScaObjectIdentifierAdapter} and/or
 * {@link ICorbaObjectDescriptorAdapter}.
 * <p/>
 * The following checks are available (1+ must be specified):
 * <p/>
 * <table>
 * <tr><th>Parameter</th><th>Adapter method</th></tr>
 * <tr><td>{@link #PARAM_FILENAME}</td><td>{@link IScaObjectIdentifierAdapter#getScaEObject(Object)}</td></tr>
 * <tr><td>{@link #PARAM_PROFILE_ID}</td><td>{@link IScaObjectIdentifierAdapter#getIdentifier(Object)}</td></tr>
 * <tr><td>{@link #PARAM_CORBA_REPID}</td><td>{@link ICorbaObjectDescriptorAdapter#supports(Object, String)}</td></tr>
 * </table>
 * <p/>
 * Provides an editor input of {@link EObjectEditorInput}, unless {@link #PARAM_EDITOR_INPUT_TYPE} is set to
 * <code>"lightweight"</code>, in which case {@link LightweightCorbaEditorInput}.
 * @since 2.2
 */
public class ScaContentDescriber implements IScaContentDescriber, IExecutableExtension {
	/**
	 * The ID of the profile object (e.g. softwareassembly id, softpkg id)
	 */
	public static final String PARAM_PROFILE_ID = "profileId";

	/**
	 * Checks the CORBA repository ID of the object
	 */
	public static final String PARAM_CORBA_REPID = "corbaRepId";

	/**
	 * Optional. Specify "lightweight" to opt for {@link LightweightCorbaEditorInput}
	 */
	public static final String PARAM_EDITOR_INPUT_TYPE = "editorInputType";

	/**
	 * A regular expression on the last segment of the profile URI.
	 * @since 8.0
	 */
	public static final String PARAM_FILENAME = "fileName";

	private Map<String, String> params;

	public ScaContentDescriber() {
	}

	@Override
	public int describe(final Object contents) throws IOException {
		if (this.params == null) {
			IStatus status = new Status(IStatus.WARNING, ScaUiPlugin.PLUGIN_ID, "No parameters provided");
			ScaUiPlugin.getDefault().getLog().log(status);
			return IScaContentDescriber.INVALID;
		}

		final IAdapterManager adapterManager = Platform.getAdapterManager();
		IScaObjectIdentifierAdapter idAdapter = adapterManager.getAdapter(contents, IScaObjectIdentifierAdapter.class);
		ICorbaObjectDescriptorAdapter corbaAdapter = adapterManager.getAdapter(contents, ICorbaObjectDescriptorAdapter.class);

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
			IStatus status = new Status(IStatus.WARNING, ScaUiPlugin.PLUGIN_ID, "At least one parameter must be supplied");
			ScaUiPlugin.getDefault().getLog().log(status);
			return IScaContentDescriber.INVALID;
		}
	}

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

	@Override
	@SuppressWarnings("unchecked")
	public void setInitializationData(final IConfigurationElement config, final String propertyName, final Object data) throws CoreException {
		if (data instanceof Map) {
			this.params = (Map<String, String>) data;
		}

	}

}

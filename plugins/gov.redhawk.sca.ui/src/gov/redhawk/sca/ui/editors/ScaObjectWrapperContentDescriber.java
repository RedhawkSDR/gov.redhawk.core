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

import gov.redhawk.model.sca.CorbaObjWrapper;
import gov.redhawk.model.sca.ProfileObjectWrapper;
import gov.redhawk.model.sca.util.ScaFileSystemUtil;
import gov.redhawk.sca.ui.ScaFileStoreEditorInput;
import gov.redhawk.sca.ui.ScaUI;
import gov.redhawk.sca.ui.ScaUiPlugin;

import java.io.IOException;
import java.util.Map;
import java.util.regex.PatternSyntaxException;

import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.util.URI;
import org.eclipse.ui.IEditorInput;

/**
 * Describes a {@link ProfileObjectWrapper} based on a regular expression of the profile URI's last segment.
 * @since 2.2
 */
public class ScaObjectWrapperContentDescriber implements IScaContentDescriber, IExecutableExtension {

	public static final String PARAM_PROFILE_FILENAME = "profileFilename";

	private Map<String, String> params;

	@Override
	public int describe(final Object contents) throws IOException {
		if (this.params == null) {
			IStatus status = new Status(IStatus.WARNING, ScaUiPlugin.PLUGIN_ID, "No parameters provided");
			ScaUiPlugin.getDefault().getLog().log(status);
			return IScaContentDescriber.INVALID;
		}
		final String profileFileName = this.params.get(ScaObjectWrapperContentDescriber.PARAM_PROFILE_FILENAME);
		if (profileFileName == null) {
			IStatus status = new Status(IStatus.WARNING, ScaUiPlugin.PLUGIN_ID, "Profile filename not provided");
			ScaUiPlugin.getDefault().getLog().log(status);
			return IScaContentDescriber.INVALID;
		}

		if (!(contents instanceof ProfileObjectWrapper< ? >)) {
			return IScaContentDescriber.INVALID;
		}
		final ProfileObjectWrapper< ? > obj = (ProfileObjectWrapper< ? >) contents;

		try {
			URI profileUri = obj.getProfileURI();
			if (profileUri == null) {
				return IScaContentDescriber.INDETERMINATE;
			} else if (profileUri.lastSegment() == null || !profileUri.lastSegment().matches(profileFileName)) {
				return IScaContentDescriber.INVALID;
			}
		} catch (final PatternSyntaxException e) {
			IStatus status = new Status(IStatus.WARNING, ScaUiPlugin.PLUGIN_ID, "Invalid profile filename regular expression");
			ScaUiPlugin.getDefault().getLog().log(status);
		}

		return IScaContentDescriber.VALID;
	}

	@Override
	public IEditorInput getEditorInput(final Object contents) {
		if (!(contents instanceof ProfileObjectWrapper)) {
			return null;
		}
		IFileStore store;
		try {
			store = ScaFileSystemUtil.getFileStore((ProfileObjectWrapper< ? >) contents);
		} catch (CoreException e) {
			return null;
		}
		if (contents instanceof CorbaObjWrapper< ? >) {
			return new ScaFileStoreEditorInput((CorbaObjWrapper< ? >) contents, store);
		}
		return ScaUI.getEditorInput(store);
	}

	/**
	 * @since 9.3
	 * @deprecated Use {@link ScaFileSystemUtil#getFileStore(ProfileObjectWrapper)}
	 */
	@Deprecated
	public static IFileStore getFileStore(ProfileObjectWrapper< ? > obj) throws CoreException {
		return ScaFileSystemUtil.getFileStore(obj);
	}

	@Override
	@SuppressWarnings("unchecked")
	public void setInitializationData(final IConfigurationElement config, final String propertyName, final Object data) throws CoreException {
		if (data instanceof Map) {
			this.params = (Map<String, String>) data;
		}
	}
}

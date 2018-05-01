/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.sca.ui.describers;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IEditorInput;

import gov.redhawk.model.sca.CorbaObjWrapper;
import gov.redhawk.model.sca.ProfileObjectWrapper;
import gov.redhawk.sca.ui.ScaFileStoreEditorInput;
import gov.redhawk.sca.ui.ScaUI;
import gov.redhawk.sca.ui.editors.IScaContentDescriber;
import mil.jpeojtrs.sca.util.CorbaUtils2;
import mil.jpeojtrs.sca.util.QueryParser;
import mil.jpeojtrs.sca.util.ScaFileSystemConstants;

public abstract class AbstractScaContentDescriber implements IScaContentDescriber {

	public IEditorInput getEditorInput(final Object contents) {
		final ProfileObjectWrapper< ? > obj = (ProfileObjectWrapper< ? >) contents;

		// Fetch profile object if necessary
		final Object profileObj;
		if (!obj.isSetProfileObj()) {
			profileObj = fetchProfileObj(obj);
		} else {
			profileObj = obj.getProfileObj();
		}

		URI uri = null;
		if (profileObj instanceof EObject && ((EObject) profileObj).eResource() != null) {
			final EObject scaObj = (EObject) profileObj;
			uri = scaObj.eResource().getURI();
			if (!uri.isFile()) {
				final String query = uri.query();
				final Map<String, String> oldtParams = QueryParser.parseQuery(query);
				final Map<String, String> queryParams = new HashMap<String, String>();
				queryParams.putAll(oldtParams);
				if (obj instanceof CorbaObjWrapper< ? >) {
					final CorbaObjWrapper< ? > wrapper = (CorbaObjWrapper< ? >) obj;
					queryParams.put(ScaFileSystemConstants.QUERY_PARAM_WF, wrapper.getIor());
				}
				uri = uri.trimQuery().appendQuery(QueryParser.createQuery(queryParams));
			}
		}
		if (uri == null) {
			return null;
		}
		IFileStore store;
		try {
			if (uri.isPlatform()) {
				store = EFS.getStore(java.net.URI.create(CommonPlugin.resolve(uri).toString()));
			} else {
				store = EFS.getStore(java.net.URI.create(uri.toString()));
			}
		} catch (final CoreException e) {
			return null;
		}
		if (obj instanceof CorbaObjWrapper< ? >) {
			return new ScaFileStoreEditorInput((CorbaObjWrapper< ? >) obj, store);
		}
		return ScaUI.getEditorInput(store);
	}

	private Object fetchProfileObj(final ProfileObjectWrapper< ? > obj) {
		if (Display.getCurrent() != null) {
			ProgressMonitorDialog dialog = new ProgressMonitorDialog(Display.getCurrent().getActiveShell());
			try {
				dialog.run(true, true, monitor -> {
					CorbaUtils2.invokeUI(() -> {
						return obj.fetchProfileObject(monitor);
					}, monitor);
				});
				return obj.getProfileObj();
			} catch (InvocationTargetException e) {
				throw new IllegalStateException("Unable to fetch profile URI for waveform", e.getCause());
			} catch (InterruptedException e) {
				throw new IllegalStateException("Interrupted while fetching profile URI for waveform");
			}
		} else {
			return obj.fetchProfileObject(new NullProgressMonitor());
		}
	}

}

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
package gov.redhawk.sca.efs.internal.ui;

import gov.redhawk.model.sca.DomainConnectionException;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaDomainManagerRegistry;
import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;
import gov.redhawk.sca.ScaPlugin;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import mil.jpeojtrs.sca.util.ScaFileSystemConstants;

import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;
import org.eclipse.ui.ide.fileSystem.FileSystemContributor;

/**
 * 
 */
public class ScaFileSystemContributor extends FileSystemContributor {

	/**
	 * 
	 */
	public ScaFileSystemContributor() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public URI browseFileSystem(final String initialPath, final Shell shell) {
		final List<Object> input = new ArrayList<Object>();
		input.add("< SDR DEV >");
		input.add("< SDR DOM >");
		final ScaDomainManagerRegistry registry = ScaPlugin.getDefault().getDomainManagerRegistry();
		input.addAll(registry.getDomains());

		final ScaItemProviderAdapterFactory adapterFactory = new ScaItemProviderAdapterFactory();
		final AdapterFactoryLabelProvider lp = new AdapterFactoryLabelProvider(adapterFactory);
		try {
			final ElementListSelectionDialog dialog = new ElementListSelectionDialog(shell, lp);
			dialog.setAllowDuplicates(false);
			dialog.setMultipleSelection(false);
			dialog.setTitle("SCA File System");
			dialog.setMessage("Select file system root:");
			dialog.setElements(input.toArray());
			if (Window.OK == dialog.open()) {
				final Object[] result = dialog.getResult();
				if (result != null && result.length == 1) {
					if ("< SDR DEV >".equals(result[0])) {
						return URI.create(org.eclipse.emf.common.util.URI.createHierarchicalURI(ScaFileSystemConstants.SCHEME_TARGET_SDR_DEV,
						        "",
						        null,
						        new String[0],
						        null,
						        null).toString());
					} else if ("< SDR DOM >".equals(result[0])) {
						return URI.create(org.eclipse.emf.common.util.URI.createHierarchicalURI(ScaFileSystemConstants.SCHEME_TARGET_SDR_DOM,
						        "",
						        null,
						        new String[0],
						        null,
						        null).toString());
					} else {
						final ScaDomainManager domMgr = (ScaDomainManager) result[0];
						if (!domMgr.isConnected()) {
							try {
								domMgr.connect(null, RefreshDepth.SELF);
							} catch (final DomainConnectionException e) {
								// PASS
							}
						}

						final IFileStore fileStore = domMgr.fetchFileManager(null).getFileStore();
						if (fileStore != null) {
							return fileStore.toURI();
						} else {
							return null;
						}
					}
				}
			}
		} finally {
			adapterFactory.dispose();
			lp.dispose();
		}
		return null;
	}

	@Override
	public URI getURI(final String string) {
		return URI.create(string);
	}
}

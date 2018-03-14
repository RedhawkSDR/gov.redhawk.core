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
package gov.redhawk.model.sca.commands;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.IStatus;

import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaFileStore;
import gov.redhawk.model.sca.ScaPackage;

/**
 * @since 21.1
 */
public class ScaFileStoreMergeChildrenCommand extends SetStatusCommand<ScaFileStore> {

	public static class FileStoreData {
		private final IFileStore fileStore;
		private final boolean isDirectory;

		public FileStoreData(IFileStore childStore, boolean isDirectory) {
			fileStore = childStore;
			this.isDirectory = isDirectory;
		}

		public IFileStore getFileStore() {
			return fileStore;
		}

		public boolean isDirectory() {
			return isDirectory;
		}
	}

	private Map<String, FileStoreData> newChildrenMap;

	public ScaFileStoreMergeChildrenCommand(ScaFileStore provider, Map<String, FileStoreData> newChildrenMap, IStatus status) {
		super(provider, ScaPackage.Literals.SCA_FILE_STORE__CHILDREN, status);
		this.newChildrenMap = newChildrenMap;
	}

	@Override
	public void execute() {
		Map<String, ScaFileStore> currentChildren = new HashMap<String, ScaFileStore>();
		for (ScaFileStore store : provider.getChildren()) {
			currentChildren.put(store.getFileStore().getName(), store);
		}

		Map<String, ScaFileStore> removeChildrenMap = new HashMap<String, ScaFileStore>(currentChildren);
		removeChildrenMap.keySet().removeAll(newChildrenMap.keySet());

		if (!removeChildrenMap.isEmpty() && !provider.getChildren().isEmpty()) {
			provider.getChildren().removeAll(removeChildrenMap.values());
		}

		newChildrenMap.keySet().removeAll(currentChildren.keySet());

		List<ScaFileStore> newChildren = new ArrayList<>(newChildrenMap.size());
		for (FileStoreData childStore : newChildrenMap.values()) {
			ScaFileStore childFileStore = ScaFactory.eINSTANCE.createScaFileStore();
			childFileStore.setDirectory(childStore.isDirectory);
			childFileStore.setFileStore(childStore.fileStore);
			newChildren.add(childFileStore);
		}
		if (newChildren.size() > 0) {
			provider.getChildren().addAll(newChildren);
		}

		if (!provider.isSetChildren()) {
			provider.getChildren().clear();
		}

		super.execute();
	}
}

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
package gov.redhawk.internal.ui.editor;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.ClosedWatchServiceException;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import gov.redhawk.ui.RedhawkUiActivator;

/**
 * Watches a directory for specific file(s) to be deleted. Exceptions are not thrown (but may be logged) if monitoring
 * fails.
 */
public class DeletionWatcher implements Closeable {

	private WatchService watcher;

	/**
	 * @param directory The directory to monitor
	 * @param files The file(s) in the directory to monitor for deletion
	 * @param action The action to take upon deletion
	 */
	public DeletionWatcher(Path directory, Set<Path> files, Runnable action) {
		try {
			watcher = directory.getFileSystem().newWatchService();
			directory.register(watcher, StandardWatchEventKinds.ENTRY_DELETE);
		} catch (UnsupportedOperationException e) {
			// The file system may not support monitoring / notification
			return;
		} catch (IOException e) {
			IStatus status = new Status(IStatus.ERROR, RedhawkUiActivator.PLUGIN_ID, "Unable to create watch service", e);
			RedhawkUiActivator.getDefault().getLog().log(status);
			return;
		}

		Thread thread = new Thread(() -> {
			for (;;) {
				WatchKey wk;
				try {
					wk = watcher.take();
				} catch (ClosedWatchServiceException | InterruptedException e) {
					return;
				}

				for (WatchEvent< ? > event : wk.pollEvents()) {
					java.nio.file.Path changed = (java.nio.file.Path) event.context();
					if (changed != null && files.contains(changed)) {
						action.run();
					}
				}

				// reset the key
				boolean valid = wk.reset();
				if (!valid) {
					return;
				}
			}
		}, "Watch filesystem " + directory.toString());
		thread.setDaemon(true);
		thread.start();
	}

	@Override
	public void close() {
		if (watcher == null) {
			return;
		}

		try {
			watcher.close();
		} catch (IOException e) {
			IStatus status = new Status(IStatus.ERROR, RedhawkUiActivator.PLUGIN_ID, "Unable to close file watch service", e);
			RedhawkUiActivator.getDefault().getLog().log(status);
		} finally {
			watcher = null;
		}
	}
}

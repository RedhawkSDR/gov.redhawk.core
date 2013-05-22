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
package gov.redhawk.sca.internal.ui.handlers;

import gov.redhawk.model.sca.ScaFileStore;
import gov.redhawk.sca.internal.ui.actions.UploadJob;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.EvaluationContext;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.ResourceTransfer;

/**
 * 
 */
@SuppressWarnings("restriction")
public class ScaFileStorePastActionHandler extends AbstractHandler {

	private static final IResource[] EMPTY = new IResource[0];

	/**
	 * {@inheritDoc}
	 */
	public Object execute(final ExecutionEvent event) throws ExecutionException {
		ISelection selection = HandlerUtil.getActiveMenuSelection(event);
		if (selection == null) {
			selection = HandlerUtil.getCurrentSelection(event);
		}
		if (selection instanceof IStructuredSelection) {
			final IStructuredSelection ss = (IStructuredSelection) selection;
			final Object obj = ss.getFirstElement();
			ScaFileStore targetContainer = null;
			if (obj instanceof ScaFileStore) {
				final ScaFileStore store = (ScaFileStore) obj;
				if (store.isDirectory()) {
					targetContainer = store;
				} else {
					targetContainer = (ScaFileStore) store.eContainer();
				}
			}
			if (targetContainer != null) {
				final Object[] items = ScaFileStorePastActionHandler.getItemsToCopy(HandlerUtil.getActiveShell(event).getDisplay());
				final IResource[] resources = (IResource[]) items[0];
				final String[] files = (String[]) items[1];
				final ScaFileStore[] stores = (ScaFileStore[]) items[2];
				final UploadJob job = new UploadJob(targetContainer, resources, files, stores);
				job.schedule();
			}
		}
		return null;
	}

	@Override
	public void setEnabled(final Object evaluationContext) {
		final Display display;
		if ((evaluationContext != null) && (evaluationContext instanceof EvaluationContext)) {
			final EvaluationContext context = (EvaluationContext) evaluationContext;
			final Shell shell = (Shell) context.getVariable("activeShell");
			if (shell.isDisposed()) {
				setBaseEnabled(false);
				return;
			}
			display = shell.getDisplay();
		} else {
			setBaseEnabled(false);
			return;
		}

		final Object[] items = ScaFileStorePastActionHandler.getItemsToCopy(display);
		final IResource[] resources = (IResource[]) items[0];
		final String[] files = (String[]) items[1];
		final ScaFileStore[] stores = (ScaFileStore[]) items[2];
		setBaseEnabled(resources.length > 0 || files.length > 0 || stores.length > 0);
	}

	private static Object[] getItemsToCopy(final Display display) {
		final Clipboard clipboard = new Clipboard(display);

		try {
			final IResource[] resources = (IResource[]) clipboard.getContents(ResourceTransfer.getInstance());
			final String[] files = (String[]) clipboard.getContents(FileTransfer.getInstance());
			final Object[] localTransfers = (Object[]) clipboard.getContents(LocalTransfer.getInstance());
			final Set<String> filesSet = new HashSet<String>();
			if (files != null) {
				filesSet.addAll(Arrays.asList(files));
			}
			if (resources != null) {
				for (final IResource resource : resources) {
					filesSet.remove(resource.getFullPath().toOSString());
				}
			}
			final List<ScaFileStore> stores = new ArrayList<ScaFileStore>();
			if (localTransfers != null) {
				for (final Object obj : localTransfers) {
					if (obj instanceof ScaFileStore) {
						stores.add((ScaFileStore) obj);
					}
				}
			}
			return new Object[] {
			        (resources == null) ? ScaFileStorePastActionHandler.EMPTY : resources, // SUPPRESS CHECKSTYLE Inline
			        filesSet.toArray(new String[0]),
			        stores.toArray(new ScaFileStore[stores.size()])
			};
		} finally {
			clipboard.dispose();
		}
	}

}

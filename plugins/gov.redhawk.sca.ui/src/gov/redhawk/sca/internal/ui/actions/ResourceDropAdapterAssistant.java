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
package gov.redhawk.sca.internal.ui.actions;

import gov.redhawk.model.sca.ScaFileStore;
import gov.redhawk.sca.ui.ScaUiPlugin;
import gov.redhawk.sca.util.Debug;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.ui.navigator.CommonDropAdapter;
import org.eclipse.ui.navigator.CommonDropAdapterAssistant;
import org.eclipse.ui.part.PluginTransfer;

/**
 * 
 */
public class ResourceDropAdapterAssistant extends CommonDropAdapterAssistant {
	private static final Debug DEBUG = new Debug(ScaUiPlugin.PLUGIN_ID, "DND");

	/**
	 * 
	 */
	public ResourceDropAdapterAssistant() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IStatus validateDrop(final Object target, final int operation, final TransferData transferType) {
		if (ResourceDropAdapterAssistant.DEBUG.enabled) {
			ResourceDropAdapterAssistant.DEBUG.enteringMethod(target, operation, transferType);
		}
		IStatus retVal;
		if (target instanceof ScaFileStore) {
			if (LocalSelectionTransfer.getTransfer().isSupportedType(transferType)) {
				retVal = Status.OK_STATUS;
			} else if (FileTransfer.getInstance().isSupportedType(transferType)) {
				retVal = Status.OK_STATUS;
			} else {
				retVal = Status.CANCEL_STATUS;
			}
		} else if (target instanceof IResource) {
			if (PluginTransfer.getInstance().isSupportedType(transferType)) {
				retVal = Status.OK_STATUS;
			} else if (LocalSelectionTransfer.getTransfer().isSupportedType(transferType)) {
				retVal = Status.OK_STATUS;
			} else {
				retVal = Status.CANCEL_STATUS;
			}
		} else {
			retVal = Status.CANCEL_STATUS;
		}

		if (ResourceDropAdapterAssistant.DEBUG.enabled) {
			ResourceDropAdapterAssistant.DEBUG.exitingMethod(retVal);
		}
		return retVal;
	}

	@Override
	public boolean isSupportedType(final TransferData aTransferType) {
		if (ResourceDropAdapterAssistant.DEBUG.enabled) {
			ResourceDropAdapterAssistant.DEBUG.enteringMethod(aTransferType);
		}
		final boolean retVal = super.isSupportedType(aTransferType) || FileTransfer.getInstance().isSupportedType(aTransferType)
		        || PluginTransfer.getInstance().isSupportedType(aTransferType);
		if (ResourceDropAdapterAssistant.DEBUG.enabled) {
			ResourceDropAdapterAssistant.DEBUG.exitingMethod(retVal);
		}
		return retVal;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IStatus handleDrop(final CommonDropAdapter aDropAdapter, final DropTargetEvent aDropTargetEvent, final Object aTarget) {
		if (ResourceDropAdapterAssistant.DEBUG.enabled) {
			ResourceDropAdapterAssistant.DEBUG.enteringMethod(aDropAdapter, aDropTargetEvent, aTarget);
		}
		final IStatus retVal;
		if (aTarget == null || aDropTargetEvent.data == null) {
			retVal = Status.CANCEL_STATUS;
		} else {
			final TransferData currentTransfer = aDropAdapter.getCurrentTransfer();

			if (aTarget instanceof ScaFileStore) {
				ScaFileStore store = (ScaFileStore) aTarget;
				if (!store.isDirectory()) {
					store = (ScaFileStore) store.eContainer();
				}

				if (LocalSelectionTransfer.getTransfer().isSupportedType(currentTransfer)) {
					retVal = handleSelectionDrop(store);
				} else if (FileTransfer.getInstance().isSupportedType(currentTransfer)) {
					retVal = performFileDrop(aDropAdapter, aDropTargetEvent.data, store);
				} else {
					retVal = Status.CANCEL_STATUS;
				}
			} else if (aTarget instanceof IResource) {
				final IContainer target = getActualTarget((IResource) aTarget);
				if (LocalSelectionTransfer.getTransfer().isSupportedType(currentTransfer)) {
					final ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();
					if (selection instanceof IStructuredSelection) {
						final ScaFileStore[] stores = getSelectedResource((IStructuredSelection) selection);
						final DownloadJob job = new DownloadJob(stores, target);
						job.schedule();
						retVal = Status.OK_STATUS;
					} else {
						retVal = Status.CANCEL_STATUS;
					}
				} else {
					retVal = Status.CANCEL_STATUS;
				}
			} else {
				retVal = Status.CANCEL_STATUS;
			}
		}
		if (ResourceDropAdapterAssistant.DEBUG.enabled) {
			ResourceDropAdapterAssistant.DEBUG.exitingMethod(retVal);
		}
		return retVal;
	}

	private IStatus performFileDrop(final CommonDropAdapter aDropAdapter, final Object data, final ScaFileStore store) {
		final String[] names = (String[]) data;
		final Job job = new UploadJob(store, names);
		job.schedule();
		return Status.OK_STATUS;
	}

	private IStatus handleSelectionDrop(final ScaFileStore store) {
		final ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();
		if (selection instanceof IStructuredSelection) {
			final IStructuredSelection ss = (IStructuredSelection) selection;
			final List<IResource> resources = new ArrayList<IResource>();
			final List<ScaFileStore> fileStores = new ArrayList<ScaFileStore>();
			for (final Object obj : ss.toList()) {
				if (obj instanceof IResource) {
					resources.add((IResource) obj);
				} else if (obj instanceof ScaFileStore) {
					fileStores.add((ScaFileStore) obj);
				}
			}
			final Job job = new UploadJob(store,
			        resources.toArray(new IResource[resources.size()]),
			        new String[0],
			        fileStores.toArray(new ScaFileStore[fileStores.size()]));
			job.schedule();
		}
		return Status.OK_STATUS;
	}

	@Override
	public IStatus handlePluginTransferDrop(final IStructuredSelection aDragSelection, final Object aDropTarget) {
		if (ResourceDropAdapterAssistant.DEBUG.enabled) {
			ResourceDropAdapterAssistant.DEBUG.enteringMethod(aDragSelection, aDropTarget);
		}
		final IContainer target = getActualTarget((IResource) aDropTarget);
		final ScaFileStore[] stores = getSelectedResource(aDragSelection);
		final DownloadJob job = new DownloadJob(stores, target);
		job.schedule();
		final IStatus retVal = Status.OK_STATUS;
		if (ResourceDropAdapterAssistant.DEBUG.enabled) {
			ResourceDropAdapterAssistant.DEBUG.exitingMethod(retVal);
		}
		return retVal;
	}

	private ScaFileStore[] getSelectedResource(final IStructuredSelection aDragSelection) {
		final List<ScaFileStore> retVal = new ArrayList<ScaFileStore>(aDragSelection.size());
		for (final Object obj : aDragSelection.toArray()) {
			if (obj instanceof ScaFileStore) {
				retVal.add((ScaFileStore) obj);
			}
		}
		return retVal.toArray(new ScaFileStore[retVal.size()]);
	}

	/**
	 * Returns the actual target of the drop, given the resource under the
	 * mouse. If the mouse target is a file, then the drop actually occurs in
	 * its parent. If the drop location is before or after the mouse target and
	 * feedback is enabled, the target is also the parent.
	 */
	private IContainer getActualTarget(final IResource mouseTarget) {

		/* if cursor is on a file, return the parent */
		if (mouseTarget.getType() == IResource.FILE) {
			return mouseTarget.getParent();
		}
		/* otherwise the mouseTarget is the real target */
		return (IContainer) mouseTarget;
	}

	@Override
	public IStatus validatePluginTransferDrop(final IStructuredSelection aDragSelection, final Object aDropTarget) {
		if (ResourceDropAdapterAssistant.DEBUG.enabled) {
			ResourceDropAdapterAssistant.DEBUG.enteringMethod(aDragSelection, aDropTarget);
		}
		IStatus retVal;
		if (aDropTarget instanceof IResource) {
			retVal = Status.OK_STATUS;
		} else {
			retVal = Status.CANCEL_STATUS;
		}
		if (ResourceDropAdapterAssistant.DEBUG.enabled) {
			ResourceDropAdapterAssistant.DEBUG.exitingMethod(retVal);
		}
		return retVal;
	}

}

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
package gov.redhawk.eclipsecorba.library.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import gov.redhawk.eclipsecorba.library.IdlLibrary;
import gov.redhawk.eclipsecorba.library.LibraryFactory;
import gov.redhawk.eclipsecorba.library.LibraryPackage;
import gov.redhawk.eclipsecorba.library.URIPathSet;
import gov.redhawk.eclipsecorba.library.util.RefreshIdlLibraryJob;

public class RefreshIdlLibraryTest {

	private IdlLibrary library;

	@Before
	public void before() throws IOException, CoreException {
		final TransactionalEditingDomain editingDomain = TransactionalEditingDomain.Factory.INSTANCE.createEditingDomain();
		final ResourceSet resourceSet = editingDomain.getResourceSet();
		final Resource libraryResource = resourceSet.createResource(URI.createFileURI(".library"));

		library = LibraryFactory.eINSTANCE.createIdlLibrary();
		final URIPathSet uriPath = LibraryFactory.eINSTANCE.createURIPathSet();
		uriPath.getDirs().add(LibraryTestUtil.getURI("idl"));
		library.getPaths().add(uriPath);

		editingDomain.getCommandStack().execute(new AddCommand(editingDomain, libraryResource.getContents(), library));

		library.load(null);

		Assert.assertTrue(library.getLoadStatus().getMessage(), library.getLoadStatus().isOK());
	}

	/**
	 * IDE-1493
	 * Tests that the IDL library actually gets reloaded by the {@link RefreshIdlLibraryJob}
	 * @throws InterruptedException
	 */
	@Test(timeout = 5000)
	public void reloadIdlLibrary() throws InterruptedException {
		// Remember history
		final List<IStatus> statusHistory = new ArrayList<IStatus>();
		statusHistory.add(library.getLoadStatus());
		library.eAdapters().add(new AdapterImpl() {
			public void notifyChanged(Notification notification) {
				if (notification.getFeatureID(IdlLibrary.class) == LibraryPackage.IDL_LIBRARY__LOAD_STATUS && notification.getEventType() == Notification.SET) {
					statusHistory.add((IStatus) notification.getNewValue());
				}
			}
		});

		RefreshIdlLibraryJob job = new RefreshIdlLibraryJob(library);
		job.schedule();
		job.join();

		Assert.assertTrue(statusHistory.get(0).isOK());
		Assert.assertEquals(IStatus.INFO, statusHistory.get(1).getSeverity());
		Assert.assertEquals("Loading...", statusHistory.get(1).getMessage());
		Assert.assertTrue(statusHistory.get(2).isOK());
	}

}

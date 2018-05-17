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
package gov.redhawk.model.sca.tests;

import java.io.File;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.junit.Assert;
import org.omg.CosNaming.NamingContextExt;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import CF.DataType;
import CF.DeviceAssignmentType;
import gov.redhawk.model.sca.DomainConnectionState;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaDocumentRoot;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.ScaWaveformFactory;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.impl.ScaDomainManagerImpl;
import gov.redhawk.model.sca.tests.stubs.DeviceManagerImpl;
import gov.redhawk.model.sca.tests.stubs.DomainManagerImpl;
import gov.redhawk.model.sca.tests.stubs.ScaTestConstaints;
import gov.redhawk.model.sca.tests.stubs.naming.NamingContextExtImpl;
import gov.redhawk.sca.model.internal.DataProviderServicesRegistry;
import gov.redhawk.sca.util.OrbSession;

public class TestEnvirornment {

	private static final String DOMAIN_NAME = "REDHAWK_DEV";
	private static final String DEV_MGR_NAME = "DevMgr_localhost";
	public static final String EDITING_DOMAIN_ID = "gov.redhawk.sc.model.tests.editingDomain";

	private final TransactionalEditingDomain editingDomain;

	private OrbSession session;
	private NamingContextExt namingContextRef;

	private final ScaDomainManagerImpl domMgr;
	private DomainManagerImpl domainMgrImpl;
	private DeviceManagerImpl devMgrImpl;

	static {
		DataProviderServicesRegistry.INSTANCE.clearDataProviders();
	}

	private TestEnvirornment() throws Exception {
		this.editingDomain = TransactionalEditingDomain.Registry.INSTANCE.getEditingDomain("gov.redhawk.sca.editingDomain");
		final ResourceSet resourceSet = this.editingDomain.getResourceSet();

		final Resource resource = resourceSet.getResource(org.eclipse.emf.common.util.URI.createURI(ScaTestConstaints.SCA_DOMAINS_URI), true);
		final ScaDocumentRoot root = (ScaDocumentRoot) resource.getEObject("/");
		this.domMgr = (ScaDomainManagerImpl) root.getDomainManagerRegistry().getDomains().get(0);

		session = OrbSession.createSession();

		NamingContextExtImpl namingContextImpl = new NamingContextExtImpl(session);
		namingContextRef = namingContextImpl.getNarrowedObj();

		URL domFileUrl = FileLocator.toFileURL(FileLocator.find(Platform.getBundle("gov.redhawk.sca.model.tests"), new Path("sdr/dom"), null));
		File domRoot = new File(domFileUrl.toURI());
		Assert.assertTrue(domRoot.exists());
		domainMgrImpl = new DomainManagerImpl(domRoot, "/domain/DomainManager.dmd.xml", DOMAIN_NAME, DOMAIN_NAME, session, namingContextRef);

		URL devFileUrl = FileLocator.toFileURL(FileLocator.find(Platform.getBundle("gov.redhawk.sca.model.tests"), new Path("sdr/dev"), null));
		File devRoot = new File(devFileUrl.toURI());
		Assert.assertTrue(devRoot.exists());
		devMgrImpl = new DeviceManagerImpl(devRoot, "/nodes/REDHAWK_DevMgr/DeviceManager.dcd.xml", DOMAIN_NAME, DEV_MGR_NAME, DEV_MGR_NAME, session,
			namingContextRef);

		execute(new ScaModelCommand() {

			@Override
			public void execute() {
				((ScaDomainManagerImpl) domMgr).setOrbSession(session);
			}
		});
	}

	private static TestEnvirornment env;

	public static TestEnvirornment getInstance() throws Exception {
		if (env == null) {
			env = new TestEnvirornment();
		}
		env.setUp();
		return env;
	}

	public ScaDomainManager getDomMgr() {
		return this.domMgr;
	}

	protected void setUp() throws Exception {
		domainMgrImpl.reset();
		devMgrImpl.reset();
		execute(new ScaModelCommand() {

			@Override
			public void execute() {
				domMgr.unsetObj();
				domMgr.unsetCorbaObj();
				domMgr.unsetRootContext();
				domMgr.unsetEventChannels();
				domMgr.unsetWaveformFactories();
				domMgr.unsetDeviceManagers();
				domMgr.unsetFileManager();
				domMgr.clearAllStatus();
				domMgr.setCorbaObj(domainMgrImpl.getRef());
				domMgr.setRootContext(namingContextRef);
				domMgr.setState(DomainConnectionState.CONNECTED);
			}

		});
		domMgr.registerDeviceManager(devMgrImpl.getRef());
		domMgr.installApplication("waveforms/FinishedExampleWaveform/ExampleWaveform.sad.xml");
		domMgr.applicationFactories()[0].create("Waveform", new DataType[0], new DeviceAssignmentType[0]);

		domMgr.refresh(null, RefreshDepth.FULL);
	}

	private void validateStartState(final ScaPort< ? , ? > port) {
		Assert.assertNotNull(port.toString(), port);
		Assert.assertNotNull(port.toString(), port.getCorbaObj());
		Assert.assertNotNull(port.toString(), port.getObj());
		Assert.assertNotNull(port.toString(), port.getProfileObj());
		Assert.assertNotNull(port.toString(), port.getIor());
		Assert.assertNotNull(port.toString(), port.getName());
		Assert.assertNotNull(port.toString(), port.getRepid());
	}

	private void validateStartState(final ScaAbstractProperty< ? > property) {
		Assert.assertNotNull(property.getDefinition());
		Assert.assertNotNull(property.getId());
		Assert.assertNotNull(property.getMode());
	}

	public void validateStartState() {
		TreeIterator<Object> contents = EcoreUtil.getAllContents(domMgr, false);
		while (contents.hasNext()) {
			Object obj = contents.next();
			if (obj instanceof EObject) {
				Assert.assertNotNull(TransactionUtil.getEditingDomain(obj));
			}
		}
		Assert.assertFalse(domMgr.isDisposed());
		Assert.assertNotNull(this.domMgr.getConnectionProperties());
		Assert.assertNotNull(this.domMgr.getConnectionPropertiesContainer());
		Assert.assertNotNull(this.domMgr.getCorbaObj());
		Assert.assertNotNull(this.domMgr.getObj());
		// Assert.assertNotNull(this.domMgr.getRootContext());
		Assert.assertNotNull(this.domMgr.getState());
		Assert.assertNotNull(this.domMgr.getIdentifier());
		Assert.assertNotNull(this.domMgr.getIor());
		Assert.assertNotNull(this.domMgr.getName());
		Assert.assertNotNull(this.domMgr.getProfile());

		Assert.assertNotNull(this.domMgr.getFileManager());
		Assert.assertNotNull(this.domMgr.getFileManager().getCorbaObj());
		Assert.assertNotNull(this.domMgr.getFileManager().getObj());
		Assert.assertNotNull(this.domMgr.getFileManager().getIor());
		Assert.assertNull(this.domMgr.getFileManager().getName());
		Assert.assertNotNull(this.domMgr.getFileManager().getFileSystemURI());
		Assert.assertNotNull(this.domMgr.getFileManager().getFileStore());
		Assert.assertNotNull(this.domMgr.getProfileURI());
		Assert.assertNotNull(this.domMgr.getProfileObj());
		for (final ScaAbstractProperty< ? > property : this.domMgr.getProperties()) {
			validateStartState(property);
		}

		Assert.assertFalse(this.domMgr.getDeviceManagers().isEmpty());
		for (final ScaDeviceManager devMgr : this.domMgr.getDeviceManagers()) {
			Assert.assertNotNull(devMgr.getCorbaObj());
			Assert.assertNotNull(devMgr.getObj());
			Assert.assertNotNull(devMgr.getIdentifier());
			Assert.assertNotNull(devMgr.getIor());
			Assert.assertNotNull(devMgr.getLabel());
			Assert.assertNotNull(devMgr.getProfile());
			Assert.assertNotNull(devMgr.getFileSystem());
			Assert.assertNotNull(devMgr.getFileSystem().getCorbaObj());
			Assert.assertNotNull(devMgr.getFileSystem().getObj());
			Assert.assertNotNull(devMgr.getFileSystem().getIor());
			Assert.assertNull(devMgr.getFileSystem().getName());
			Assert.assertNotNull(devMgr.getFileSystem().getFileSystemURI());
			Assert.assertNotNull(devMgr.getFileSystem().getFileStore());
			Assert.assertNotNull(devMgr.getProfileURI());
			Assert.assertNotNull(devMgr.getProfileObj());

			for (final ScaAbstractProperty< ? > property : devMgr.getProperties()) {
				validateStartState(property);
			}

			for (final ScaPort< ? , ? > port : devMgr.getPorts()) {
				validateStartState(port);
			}

			Assert.assertFalse(devMgr.getDevices().isEmpty());
			for (final ScaDevice< ? > device : devMgr.getAllDevices()) {
				Assert.assertNotNull(device);
				Assert.assertNotNull(device.getCorbaObj());
				Assert.assertNotNull(device.getObj());
				Assert.assertNotNull(device.getIdentifier());
				Assert.assertNotNull(device.getIor());
				Assert.assertNotNull(device.getLabel());
				Assert.assertNotNull(device.getProfile());
				Assert.assertNotNull(device.getAdminState());
				Assert.assertNotNull(device.getOperationalState());
				Assert.assertNotNull(device.getUsageState());
				Assert.assertNotNull(device.getProfileURI());
				Assert.assertNotNull(device.getProfileObj());

				for (final ScaAbstractProperty< ? > property : device.getProperties()) {
					validateStartState(property);
				}

				for (final ScaPort< ? , ? > port : device.getPorts()) {
					validateStartState(port);
				}
			}
		}

		Assert.assertFalse(this.domMgr.getWaveformFactories().isEmpty());
		for (final ScaWaveformFactory factory : this.domMgr.getWaveformFactories()) {
			Assert.assertNotNull(factory.getCorbaObj());
			Assert.assertNotNull(factory.getObj());
			Assert.assertNotNull(factory.getProfileURI());
			Assert.assertNotNull(factory.getProfile());
			Assert.assertNotNull(factory.getProfileObj());
			Assert.assertNotNull(factory.getIdentifier());
			Assert.assertNotNull(factory.getIor());
			Assert.assertNotNull(factory.getName());

		}

		for (final ScaWaveform waveform : this.domMgr.getWaveforms()) {
			Assert.assertNotNull(waveform.getCorbaObj());
			Assert.assertNotNull(waveform.getObj());
			Assert.assertNotNull(waveform.getProfileURI());
			Assert.assertNotNull(waveform.getProfile());
			Assert.assertNotNull(waveform.getProfileObj());

			Assert.assertNotNull(waveform.getIdentifier());
			Assert.assertNotNull(waveform.getIor());
			Assert.assertNotNull(waveform.getName());

			Assert.assertNotNull(waveform.getAssemblyController());

			for (final ScaAbstractProperty< ? > property : waveform.getProperties()) {
				validateStartState(property);
			}

			for (final ScaPort< ? , ? > port : waveform.getPorts()) {
				validateStartState(port);
			}

			for (final ScaComponent component : waveform.getComponents()) {
				Assert.assertNotNull(component.getComponentInstantiation());
				Assert.assertNotNull(component.getIdentifier());
				Assert.assertNotNull(component.getInstantiationIdentifier());
				Assert.assertNotNull(component.getIor());
				Assert.assertNotNull(component.getProfileURI());
				Assert.assertNotNull(component.getCorbaObj());
				Assert.assertNotNull(component.getObj());
				Assert.assertNotNull(component.getProfileObj());

				for (final ScaAbstractProperty< ? > property : component.getProperties()) {
					validateStartState(property);
				}

				for (final ScaPort< ? , ? > port : component.getPorts()) {
					validateStartState(port);
				}
			}
		}
	}

	public void execute(final Command command) {
		this.editingDomain.getCommandStack().execute(command);
	}

	public < T > T runExclusive(final RunnableWithResult<T> runnable) throws InterruptedException {
		return TransactionUtil.runExclusive(this.editingDomain, runnable);
	}

	public OrbSession getOrbSession() {
		return this.session;
	}

	public NamingContextExt getNamingContext() {
		return namingContextRef;
	}

	public static void log(int severity, String message, Throwable exception) {
		Bundle bundle = FrameworkUtil.getBundle(TestEnvirornment.class);
		IStatus status = new Status(severity, bundle.getSymbolicName(), message, exception);
		Platform.getLog(bundle).log(status);
	}
}

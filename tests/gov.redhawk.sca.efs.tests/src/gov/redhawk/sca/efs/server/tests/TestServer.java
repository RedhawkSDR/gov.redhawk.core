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
package gov.redhawk.sca.efs.server.tests;

import gov.redhawk.efs.sca.server.internal.FileSystemImpl;
import gov.redhawk.sca.util.OrbSession;

import java.io.File;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.omg.CORBA.ORB;
import org.osgi.framework.Bundle;

import CF.FileSystem;
import CF.FileSystemHelper;
import CF.FileSystemOperations;
import CF.FileSystemPOATie;

public class TestServer {

	public static final Bundle BUNDLE = Platform.getBundle("gov.redhawk.sca.efs.tests");
	private OrbSession session;
	private FileSystemOperations orbFileSystem;
	private FileSystem fs;
	private final String initName = "TestFileSystem";
	private File rootFile;

	public TestServer() {

	}

	public synchronized void initOrb() throws Exception {
		this.session = OrbSession.createSession();

		this.rootFile = new File(FileLocator.toFileURL(FileLocator.find(TestServer.BUNDLE, new Path("sdr"), null)).toURI());
		this.orbFileSystem = new FileSystemImpl(this.rootFile, session.getOrb(), session.getPOA());
		
		this.fs = FileSystemHelper.narrow(session.getPOA().servant_to_reference(new FileSystemPOATie(orbFileSystem)));
	}

	public synchronized void shutdownOrb() throws Exception {
		session.dispose();
		this.orbFileSystem = null;
	}

	public File getRootFile() {
		return this.rootFile;
	}

	public ORB getOrb() {
		return session.getOrb();
	}

	public FileSystemOperations getOrbFileSystem() {
		return this.orbFileSystem;
	}

	public void setOrbFileSystem(final FileSystemOperations orbFileSystem) {
		this.orbFileSystem = orbFileSystem;
	}

	public FileSystem getFs() {
		return this.fs;
	}

	public void setFs(final FileSystem fs) {
		this.fs = fs;
	}

	public String getInitName() {
		return this.initName;
	}
}

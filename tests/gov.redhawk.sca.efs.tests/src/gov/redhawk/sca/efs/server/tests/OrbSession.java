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
import gov.redhawk.sca.util.ORBUtil;

import java.io.File;
import java.util.Properties;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.osgi.framework.Bundle;

import CF.FileSystem;
import CF.FileSystemOperations;

public class OrbSession {

	public static final Bundle BUNDLE = Platform.getBundle("gov.redhawk.sca.efs.tests");
	private ORB orb;
	private FileSystemOperations orbFileSystem;
	private FileSystem fs;
	private final String initName = "TestFileSystem";
	private Thread orbThread;
	private final Properties properties;
	private File rootFile;

	public OrbSession() {
		this.properties = new Properties();
		this.properties.putAll(System.getProperties());
	}

	public synchronized void initOrb() throws Exception {
		this.orb = ORBUtil.init(this.properties);

		final POA rootpoa = POAHelper.narrow(this.orb.resolve_initial_references("RootPOA"));
		rootpoa.the_POAManager().activate();

		this.rootFile = new File(FileLocator.toFileURL(FileLocator.find(OrbSession.BUNDLE, new Path("sdr"), null)).toURI());
		this.orbFileSystem = new FileSystemImpl(this.rootFile, this.orb, rootpoa);

		this.fs = ((FileSystemImpl) this.orbFileSystem)._this(this.orb);

		this.orbThread = new Thread("ORB Thread") {
			@Override
			public void run() {
				OrbSession.this.orb.run();
			}
		};

		this.orbThread.start();
	}

	public synchronized void shutdownOrb() throws Exception {
		if (this.orb != null) {
			this.orb.shutdown(true);
			this.orb = null;
		}
		if (this.orbThread != null) {
			this.orbThread.interrupt();
			this.orbThread = null;
		}
		this.orbFileSystem = null;
	}

	public File getRootFile() {
		return this.rootFile;
	}

	public ORB getOrb() {
		return this.orb;
	}

	public void setOrb(final ORB orb) {
		this.orb = orb;
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

	public Thread getOrbThread() {
		return this.orbThread;
	}

	public void setOrbThread(final Thread orbThread) {
		this.orbThread = orbThread;
	}

	public String getInitName() {
		return this.initName;
	}

	public Properties getProperties() {
		return this.properties;
	}
}

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
package gov.redhawk.core.resourcefactory;

import org.eclipse.core.runtime.Assert;

import CF.FileSystem;
import CF.ResourceFactory;
import CF.ResourceFactoryPackage.ShutdownFailure;

public final class ResourceDesc implements Comparable<ResourceDesc> {

	public static enum Type {
		COMPONENT("components"), WAVEFORM("waveforms"), DEVICE("devices"), SERVICE("services");
		private final String dir;

		Type(final String dir) {
			this.dir = dir;
		}

		public String getDir() {
			return this.dir;
		}
	}

	private FileSystem root;
	private final String profile;
	private final String refId;
	private final Type type;
	private ResourceFactory factory;
	private final int priority;

	public ResourceDesc(final FileSystem root, final String profile, final String refId, final Type type, final ResourceFactory factory, final int priority) {
		Assert.isNotNull(profile, "Profile path must not be null");
		this.root = root;
		this.profile = profile;
		this.refId = refId;
		this.type = (type == null) ? Type.COMPONENT : type;
		this.factory = factory;
		this.priority = priority;
	}

	public int getPriority() {
		return this.priority;
	}

	public ResourceFactory getFactory() {
		return this.factory;
	}

	public Type getType() {
		return this.type;
	}

	public FileSystem getRoot() {
		return this.root;
	}

	public String getProfile() {
		return this.profile;
	}

	public String getRefId() {
		return this.refId;
	}

	public void dispose() {
		this.root = null;
		if (this.factory != null) {
			try {
				this.factory.shutdown();
			} catch (final ShutdownFailure e) {
				// PASS
			}
			this.factory = null;
		}
	}

	public int compareTo(final ResourceDesc o) {
		return -Integer.valueOf(this.priority).compareTo(o.priority);
	}

}

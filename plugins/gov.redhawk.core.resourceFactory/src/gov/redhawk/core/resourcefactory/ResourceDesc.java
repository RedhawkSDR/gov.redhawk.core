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
package gov.redhawk.core.resourcefactory;

import org.eclipse.core.runtime.Assert;

import CF.FileSystem;
import CF.ResourceFactory;
import CF.ResourceFactoryPackage.ShutdownFailure;

public final class ResourceDesc implements Comparable<ResourceDesc> {

	private FileSystem root;
	private final String profile;
	private final String refId;
	private ResourceFactory factory;
	private final int priority;

	public ResourceDesc(final FileSystem root, final String profile, final String refId, final ResourceFactory factory, final int priority) {
		Assert.isNotNull(profile, "Profile path must not be null");
		this.root = root;
		this.profile = profile;
		this.refId = refId;
		this.factory = factory;
		this.priority = priority;
	}

	public int getPriority() {
		return this.priority;
	}

	public ResourceFactory getFactory() {
		return this.factory;
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
		if (this.root != null) {
			this.root._release();
			this.root = null;
		}
		
		if (this.factory != null) {
			try {
				this.factory.shutdown();
				this.factory._release();
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

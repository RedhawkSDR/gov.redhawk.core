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
package gov.redhawk.sca.util;

import org.eclipse.core.runtime.jobs.ISchedulingRule;

/**
 * @since 2.2
 * 
 */
public class MutexRule implements ISchedulingRule {

	private final Object obj;

	public MutexRule(final Object obj) {
		this.obj = obj;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean contains(final ISchedulingRule rule) {
		if (rule instanceof MutexRule) {
			final MutexRule r = (MutexRule) rule;
			return PluginUtil.equals(this.obj, r.obj);
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isConflicting(final ISchedulingRule rule) {
		return contains(rule);
	}

}

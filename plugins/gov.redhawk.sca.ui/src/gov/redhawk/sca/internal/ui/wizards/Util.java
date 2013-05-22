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
package gov.redhawk.sca.internal.ui.wizards;

import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;
import mil.jpeojtrs.sca.partitioning.ComponentProperties;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleRef;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.SimpleSequenceRef;

import org.eclipse.emf.common.util.EList;

/**
 * 
 */
final class Util {
	private Util() {

	}

	public static ComponentProperties getComponentProperties(final ComponentInstantiation input) {
		return input.getComponentProperties();
	}

	public static SimpleRef getRef(final Simple simple, final ComponentProperties properties) {
		SimpleRef ref = null;
		if (properties != null) {
			final EList<SimpleRef> simples = properties.getSimpleRef();
			for (final SimpleRef simpleRef : simples) {
				if (simple.getId().equals(simpleRef.getRefID())) {
					ref = simpleRef;
					break;
				}
			}
		}
		return ref;
	}

	public static SimpleRef getRef(final Simple simple, final ComponentInstantiation input) {
		final ComponentProperties properties = Util.getComponentProperties(input);
		return Util.getRef(simple, properties);
	}

	public static SimpleSequenceRef getRef(final SimpleSequence simple, final ComponentProperties properties) {
		SimpleSequenceRef ref = null;
		if (properties != null) {
			final EList<SimpleSequenceRef> simples = properties.getSimpleSequenceRef();
			for (final SimpleSequenceRef simpleRef : simples) {
				if (simple.getId().equals(simpleRef.getRefID())) {
					ref = simpleRef;
					break;
				}
			}
		}
		return ref;
	}

	public static SimpleSequenceRef getRef(final SimpleSequence simple, final ComponentInstantiation input) {
		final ComponentProperties properties = Util.getComponentProperties(input);
		return Util.getRef(simple, properties);
	}
}

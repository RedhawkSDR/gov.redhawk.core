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
package gov.redhawk.core.graphiti.ui.modelmap;

import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;

/**
 * Implementers of this class should use a {@link org.eclipse.graphiti.features.ICreateFeature} to create the
 * appropriate {@link ComponentInstantiation} and associated Graphiti objects within the diagram.
 */
public abstract class CreateComponentInstantiationCommand extends RecordingCommand {

	public CreateComponentInstantiationCommand(TransactionalEditingDomain domain) {
		super(domain);
	}

	public abstract ComponentInstantiation getComponentInstantiation();

}

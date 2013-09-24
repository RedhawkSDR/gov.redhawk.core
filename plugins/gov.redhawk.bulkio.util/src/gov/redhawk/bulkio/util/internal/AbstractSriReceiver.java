/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.bulkio.util.internal;

import gov.redhawk.bulkio.util.AbstractBulkIOPort;
import gov.redhawk.bulkio.util.BulkIOType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.jdt.annotation.NonNull;
import org.omg.PortableServer.Servant;

import BULKIO.StreamSRI;
import BULKIO.updateSRIOperations;

/**
 * 
 */
public abstract class AbstractSriReceiver< T extends updateSRIOperations > extends AbstractBulkIOPort {

	private final List<T> children = Collections.synchronizedList(Collections.checkedList(new ArrayList<T>(), getType()));

	protected AbstractSriReceiver(BulkIOType type) {
		super(type);
	}
	
	@Override
	public void pushSRI(final StreamSRI sri) {
		super.pushSRI(sri);
		Object [] childrenArray = children.toArray();
		for (final Object child : childrenArray) {
			SafeRunner.run(new ISafeRunnable() {

				@Override
				public void run() throws Exception {
					((updateSRIOperations) child).pushSRI(sri);
				}

				@Override
				public void handleException(Throwable exception) {

				}
			});

		}
	}

	@SuppressWarnings("null")
	@NonNull
	protected List<T> getChildren() {
		return children;
	}

	@SuppressWarnings("unchecked")
	public void registerDataReceiver(@NonNull updateSRIOperations receiver) {
		children.add((T) receiver);
		StreamSRI[] currentSri = activeSRIs();
		if (currentSri != null) {
			for (StreamSRI sri : currentSri) {
				receiver.pushSRI(sri);
			}
		}
	}

	public void deregisterDataReceiver(@NonNull updateSRIOperations receiver) {
		children.remove(receiver);
	}

	@NonNull
	public abstract Servant toServant();

	@NonNull
	public abstract Class<T> getType();

	public boolean isEmpty() {
		return children.isEmpty();
	}

	public void clear() {
		children.clear();
	}
}

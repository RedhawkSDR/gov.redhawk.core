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
package gov.redhawk.sca.model.provider.refresh.internal;

import gov.redhawk.model.sca.IRefreshable;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaAbstractComponent;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaPropertyContainer;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.util.ScaSwitch;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.omg.CORBA.Object;

import CF.Resource;

/**
 * 
 */
public class RefresherSwitch extends ScaSwitch<IRefresher> {

	@Override
	public IRefresher caseScaDeviceManager(final ScaDeviceManager object) {
		return new IRefresher() {

			@Override
			public void refresh(final IProgressMonitor monitor) {
				try {
					object.refresh(monitor, RefreshDepth.CHILDREN);
				} catch (final InterruptedException e) {
					// PASS
				}
			}
		};
	}

	@Override
	public IRefresher caseScaUsesPort(final ScaUsesPort object) {
		return new IRefresher() {

			@Override
			public void refresh(final IProgressMonitor monitor) {
				try {
					object.refresh(monitor, RefreshDepth.CHILDREN);
				} catch (final InterruptedException e) {
					// PASS
				}
			}
		};
	}

	@Override
	public IRefresher caseScaDomainManager(final ScaDomainManager object) {
		return new IRefresher() {

			@Override
			public void refresh(final IProgressMonitor monitor) {
				try {
					object.refresh(monitor, RefreshDepth.CHILDREN);
				} catch (final InterruptedException e) {
					// PASS
				}
			}
		};
	}

	@Override
	public < P extends Object, E > IRefresher caseScaPropertyContainer(final ScaPropertyContainer<P, E> object) {
		return new IRefresher() {
			@Override
			public void refresh(final IProgressMonitor monitor) {
				try {
					object.refresh(null, RefreshDepth.SELF);
				} catch (final InterruptedException e) {
					// PASS
				}
			}
		};
	}

	@Override
	public < R extends Resource > IRefresher caseScaAbstractComponent(final ScaAbstractComponent<R> object) {
		return new IRefresher() {
			@Override
			public void refresh(final IProgressMonitor monitor) {
				try {
					object.refresh(null, RefreshDepth.SELF);
				} catch (final InterruptedException e) {
					// PASS
				}
			}
		};
	}

	@Override
	public IRefresher caseIRefreshable(final IRefreshable object) {
		return new IRefresher() {
			@Override
			public void refresh(final IProgressMonitor monitor) {
				try {
					object.refresh(null, RefreshDepth.SELF);
				} catch (final InterruptedException e) {
					// PASS
				}
			}
		};
	}

	@Override
	public IRefresher defaultCase(EObject object) {
		if (object instanceof IRefreshable) {
			final IRefreshable refreshable = (IRefreshable) object;
			return new IRefresher() {
				@Override
				public void refresh(final IProgressMonitor monitor) {
					try {
						refreshable.refresh(null, RefreshDepth.SELF);
					} catch (final InterruptedException e) {
						// PASS
					}
				}
			};
		}
		return super.defaultCase(object);
	}

}

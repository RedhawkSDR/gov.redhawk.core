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
package gov.redhawk.sca.model.provider.refresh.internal;

import gov.redhawk.model.sca.CorbaObjWrapper;
import gov.redhawk.model.sca.IRefreshable;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaAbstractComponent;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaDomainManager;
import gov.redhawk.model.sca.ScaPropertyContainer;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.util.ScaSwitch;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.ecore.EObject;
import org.omg.CORBA.Object;

import CF.Resource;

/**
 * Creates an {@link IRefresher} appropriate for the target {@link IRefreshable}.
 */
public class RefresherSwitch extends ScaSwitch<IRefresher> {

	@Override
	public IRefresher caseScaDeviceManager(final ScaDeviceManager object) {
		return createRefresher(object, RefreshDepth.SELF);
	}

	@Override
	public IRefresher caseScaUsesPort(final ScaUsesPort object) {
		return createRefresher(object, RefreshDepth.CHILDREN);
	}

	@Override
	public IRefresher caseScaDomainManager(final ScaDomainManager object) {
		return new IRefresher() {

			@Override
			public boolean canRefresh() {
				// Check if the CORBA object exists
				return object.exists();
			}

			@Override
			public void refresh(final IProgressMonitor monitor) {
				SubMonitor progress = SubMonitor.convert(monitor, 2);
				try {
					object.refresh(progress.newChild(1), RefreshDepth.SELF);
					object.fetchEventChannels(progress.newChild(1), RefreshDepth.NONE);
				} catch (final InterruptedException e) {
					// PASS
				}
			}
		};
	}

	@Override
	public < P extends Object, E > IRefresher caseScaPropertyContainer(final ScaPropertyContainer<P, E> object) {
		return createRefresher(object, RefreshDepth.SELF);
	}

	@Override
	public < R extends Resource > IRefresher caseScaAbstractComponent(final ScaAbstractComponent<R> object) {
		return createRefresher(object, RefreshDepth.SELF);
	}

	@Override
	public IRefresher caseIRefreshable(final IRefreshable object) {
		return createRefresher(object, RefreshDepth.SELF);
	}

	@Override
	public IRefresher defaultCase(EObject object) {
		if (object instanceof IRefreshable) {
			return createRefresher((IRefreshable) object, RefreshDepth.SELF);
		}
		return super.defaultCase(object);
	}

	private IRefresher createRefresher(final IRefreshable refreshable, final RefreshDepth depth) {
		return new IRefresher() {

			@Override
			public boolean canRefresh() {
				// Assume true
				return true;
			}

			@Override
			public void refresh(final IProgressMonitor monitor) {
				try {
					refreshable.refresh(monitor, depth);
				} catch (final InterruptedException e) {
					// PASS
				}
			}
		};
	}

	private IRefresher createRefresher(final CorbaObjWrapper< ? > refreshable, final RefreshDepth depth) {
		return new IRefresher() {

			@Override
			public boolean canRefresh() {
				// Check if the CORBA object exists
				return refreshable.exists();
			}

			@Override
			public void refresh(final IProgressMonitor monitor) {
				try {
					refreshable.refresh(monitor, depth);
				} catch (final InterruptedException e) {
					// PASS
				}
			}
		};
	}
}

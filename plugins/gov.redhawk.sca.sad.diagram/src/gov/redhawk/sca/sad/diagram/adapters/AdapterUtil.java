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
package gov.redhawk.sca.sad.diagram.adapters;

import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaWaveform;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import mil.jpeojtrs.sca.util.ProtectedThreadExecutor;

final class AdapterUtil {

	private AdapterUtil() {

	}

	public static List<ScaComponent> safeFetchComponents(final ScaWaveform waveform) {
		final Callable<List<ScaComponent>> callable = new Callable<List<ScaComponent>>() {

			public List<ScaComponent> call() throws Exception {
				return waveform.fetchComponents(null);
			}

		};
		try {
			return ProtectedThreadExecutor.submit(callable);
		} catch (final InterruptedException e) {
			// PASS
		} catch (final ExecutionException e) {
			// PASS
		} catch (final TimeoutException e) {
			// PASS
		}
		return Collections.emptyList();
	}

	public static List<ScaPort< ? , ? >> safeFetchPorts(final ScaComponent component) {
		final Callable<List<ScaPort< ? , ? >>> callable = new Callable<List<ScaPort< ? , ? >>>() {

			public List<ScaPort< ? , ? >> call() throws Exception {
				return component.fetchPorts(null);
			}

		};
		try {
			return ProtectedThreadExecutor.submit(callable);
		} catch (final InterruptedException e) {
			// PASS
		} catch (final ExecutionException e) {
			// PASS
		} catch (final TimeoutException e) {
			// PASS
		}
		return Collections.emptyList();
	}
}

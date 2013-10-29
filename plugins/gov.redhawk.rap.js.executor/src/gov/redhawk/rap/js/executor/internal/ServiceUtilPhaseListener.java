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
package gov.redhawk.rap.js.executor.internal;

import gov.redhawk.rap.js.executor.DisposeEvent;
import gov.redhawk.rap.js.executor.IDisposeListener;
import gov.redhawk.rap.js.executor.IFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.rwt.RWT;
import org.eclipse.rwt.internal.lifecycle.LifeCycleUtil;
import org.eclipse.rwt.lifecycle.PhaseEvent;
import org.eclipse.rwt.lifecycle.PhaseId;
import org.eclipse.rwt.lifecycle.PhaseListener;
import org.eclipse.swt.widgets.Display;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class ServiceUtilPhaseListener implements PhaseListener {
	public static final String PHASE_TYPE = "redhawk.rap.util";
	public static final String PHASE_ID = "redhawk.rap.util.id";
	public static final String PHASE_ARGS = "redhawk.rap.util.args";
	public static final String PHASE_TYPE_EVALUATE = "evaluate";
	public static final String PHASE_TYPE_FUNCTION = "function";
	private static final IFunction[] EMPTY = new IFunction[0];
	private Display display;
	private final Map<String, List<IFunction>> listeners = new HashMap<String, List<IFunction>>();
	private final Set<HttpServletRequest> requests = Collections.synchronizedSet(new HashSet<HttpServletRequest>());

	public void setDisplay(Display display) {
		this.display = display;
	}

	public Display getDisplay() {
		return display;
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		// Do nothing

	}

	public void dispose() {
		listeners.clear();
		display = null;
	}

	@Override
	public void afterPhase(PhaseEvent event) {
		final HttpServletRequest req = RWT.getRequest();
		if (display == null || listeners.isEmpty() || this.display != LifeCycleUtil.getSessionDisplay()) {
			return;
		}
		synchronized (requests) {
			if (requests.contains(req)) {
				return;
			} else {
				requests.add(req);
			}
		}

		try {
			final String type = req.getParameter(PHASE_TYPE);
			if (PHASE_TYPE_FUNCTION.equals(type) || PHASE_TYPE_EVALUATE.equals(type)) {
				final String id = req.getParameter(PHASE_ID);
				final String args = req.getParameter(PHASE_ARGS);
				final JsonElement result;
				if (args != null) {
					final JsonParser parser = new JsonParser();
					result = parser.parse(args);
				} else {
					result = null;
				}
				if (id == null) {
					return;
				}
				
				// Functions must be run in an asycn call or we get deadlocks
				if (Display.getCurrent() != this.display || PHASE_TYPE_FUNCTION.equals(type)) {
					this.display.asyncExec(new Runnable() {

						@Override
						public void run() {
							handleFunction(id, result);
						}

					});
				} else {
					// This is to prevent the UI thread from deadlocking during
					// startup by making sure ServiceUtil.evaluate()
					// doesn't wait forever for the preceding Runnable to be
					// executed
					handleFunction(id, result);
				}
			}
		} finally {
			requests.remove(req);
		}
	}

	public synchronized void addServiceListener(final String id,
			final IFunction function) {
		List<IFunction> list = this.listeners.get(id);
		if (list == null) {
			list = new ArrayList<IFunction>();
			this.listeners.put(id, list);
		}
		function.addDisposeListener(new IDisposeListener() {

			@Override
			public void disposed(DisposeEvent event) {
				removeServiceListener(id, function);
			}
		});
		list.add(function);
	}

	public synchronized void removeServiceListener(String id, IFunction listener) {
		final List<IFunction> list = this.listeners.get(id);
		if (list != null) {
			list.remove(listener);
			if (list.isEmpty()) {
				this.listeners.remove(id);
			}
		}
	}

	private void handleFunction(String id, final JsonElement result) {
		final IFunction[] listArray;
		synchronized (this) {
			final List<IFunction> list = this.listeners.get(id);
			if (list != null) {
				listArray = list.toArray(new IFunction[list.size()]);
			} else {
				listArray = EMPTY;
			}
		}
		for (final IFunction listener : listArray) {
			SafeRunnable.run(new ISafeRunnable() {

				@Override
				public void run() throws Exception {
					listener.handle(result);
				}

				@Override
				public void handleException(Throwable exception) {

				}
			});
		}
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.READ_DATA;
	}

}

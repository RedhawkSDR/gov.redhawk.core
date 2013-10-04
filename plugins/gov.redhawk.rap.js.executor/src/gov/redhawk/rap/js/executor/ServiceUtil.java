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
package gov.redhawk.rap.js.executor;

import gov.redhawk.rap.js.executor.internal.ServiceUtilPhaseListener;

import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.core.runtime.Assert;
import org.eclipse.rwt.RWT;
import org.eclipse.rwt.SessionSingletonBase;
import org.eclipse.rwt.internal.util.EncodingUtil;
import org.eclipse.rwt.internal.widgets.JSExecutor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import com.google.gson.JsonElement;
import com.google.gson.GsonBuilder;

public class ServiceUtil {
	private static AtomicInteger idGen = new AtomicInteger();

	public static final GsonBuilder GSON_BUILDER = new GsonBuilder();

	static {
		ServiceUtil.GSON_BUILDER.registerTypeHierarchyAdapter(IFunction.class, new FunctionTypeAdapter());
	}
	
	private ServiceUtil() {
		
	}

	private static ServiceUtilPhaseListener getPhaseListener() {
		final ServiceUtilPhaseListener phaseListener = SessionSingletonBase.getInstance(ServiceUtilPhaseListener.class);

		if (phaseListener.getDisplay() == null) {
			final Display display = Display.getCurrent();
			phaseListener.setDisplay(display);
			RWT.getLifeCycle().addPhaseListener(phaseListener);

			display.addListener(SWT.Dispose, new Listener() {
				@Override
				public void handleEvent(Event event) {
					RWT.getLifeCycle().removePhaseListener(phaseListener);
					phaseListener.dispose();
				}
			});
		}

		return phaseListener;
	}

	public static String quoteString(String value) {
		return "\"" + value + "\"";
	}

	public static void assertThreadState() {
		Assert.isNotNull(Display.getCurrent(), "Invalid thread, operation must be run from the main thread.");
	}

	public static IFunctionRef createAnnonymousFunction(final IFunction function) {
		final int id = ServiceUtil.idGen.incrementAndGet();
		ServiceUtil.getPhaseListener().addServiceListener(String.valueOf(id), function);
		return new IFunctionRef() {

			@Override
			public String getCodeString() {
				final StringBuilder code = new StringBuilder();
				code.append("gov.redhawk.rap.JSHelper.getInstance()._createAnnonymousFunction(");
				code.append(id);
				code.append(")");
				return code.toString();
			}

			@Override
			public void dispose() {
				function.dispose();
			}
		};
	}

	public static JsonElement evaluate(String script) {
		final String scriptStr = ServiceUtil.quoteString(ServiceUtil.escapeDoubleQuoted(ServiceUtil.prepareScript(script)));
		final int id = ServiceUtil.idGen.incrementAndGet();
		final JsonElement[] elementResult = new JsonElement[1];
		final boolean[] waitForResult = { true };
		ServiceUtil.getPhaseListener().addServiceListener(String.valueOf(id), new AbstractFunction() {

			@Override
			public void handle(JsonElement element) {
				elementResult[0] = element;
				waitForResult[0] = false;
				dispose();
			}
		});

		final StringBuilder code = new StringBuilder();
		code.append("gov.redhawk.rap.JSHelper.getInstance()._evaluateScript(");
		code.append(scriptStr);
		code.append(",");
		code.append(id);
		code.append(");");

		ServiceUtil.execute(code.toString());

		final Display display = Display.getCurrent();

		while (!display.isDisposed() && waitForResult[0]) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		if (!display.isDisposed()) {
			display.update();
		}

		return elementResult[0];
	}

	public static void execute(String script) {
		JSExecutor.executeJS(script.toString());
	}

	private static String prepareScript(String script) {
		final StringBuilder buffer = new StringBuilder("(function(){");
		buffer.append(script);
		buffer.append("})();");
		return buffer.toString();
	}

	public static String escapeDoubleQuoted(String str) {
		return EncodingUtil.escapeDoubleQuoted(str);
	}

	public static String replaceNewLines(String str) {
		return EncodingUtil.replaceNewLines(str);
	}

}

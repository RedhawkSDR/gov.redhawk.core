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
package gov.redhawk.sca.ui.singledomain;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.EventListener;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jface.action.ControlContribution;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class CustomControlItem  extends ControlContribution {

	private static final String METHOD_ADD_MOUSETRACK_LISTENER = "addMouseTrackListener";
	private static final String METHOD_REMOVE_MOUSETRACK_LISTENER = "removeMouseTrackListener";
	private static final String MOUSE_TRACK_LISTENER_CLASS_NAME = "org.eclipse.swt.events.MouseTrackListener";
	private String text;
	private TrackableLabel control;
	private ListenerList<EventListener> deferredListeners = new ListenerList<EventListener>();
	private Composite parent;

	public CustomControlItem(String text) {
		super(text);
		this.text = text;
	}
	
	public void setLabelText(String text) {
		this.text = text;
	}

	@Override
	protected Control createControl(Composite parent) {
		this.parent = parent;
		GridLayoutFactory.fillDefaults().margins(5, 0).applyTo(parent);
		this.control = new TrackableLabel(parent, text, SWT.PUSH, true);
		GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).grab(true, true).applyTo(control);
		this.control.resize();
		parent.layout(true);
		for (Object listener : deferredListeners.getListeners()) {
			if (listener instanceof CustomMouseTrackListener) { //RAP Listener
				control.addMouseTrackListener((CustomMouseTrackListener) listener);
			} else if (listener instanceof MouseTrackListener) { //RCP Listener
				/*Control#addMouseTrackListener does not exist in RAP. So we use reflection to keep
				the RAP compiler happy */
				addMouseTrackListenerToControl(control.getLabel(), (MouseTrackListener) listener);
			}
		}
		deferredListeners.clear();
		return this.control;
	}
	
	public static void addMouseTrackListenerToControl(Control control, MouseTrackListener listener) {
		try {
			Class<?> clazz = Control.class;
			Method m = clazz.getMethod(METHOD_ADD_MOUSETRACK_LISTENER, Class.forName(MOUSE_TRACK_LISTENER_CLASS_NAME));
			m.invoke(control, listener);
		} catch (SecurityException e) {
			ScaSingleDomainPlugin.logError("SecuarityException thrown while adding mouse track listner", e);
		} catch (NoSuchMethodException e) {
			ScaSingleDomainPlugin.logError("NoSuchMethodException thrown while adding mouse track listner", e);
		} catch (IllegalArgumentException e) {
			ScaSingleDomainPlugin.logError("IllegalArgumentExceptionthrown while adding mouse track listner", e);
		} catch (IllegalAccessException e) {
			ScaSingleDomainPlugin.logError("IllegalAccessException thrown while adding mouse track listner", e);
		} catch (InvocationTargetException e) {
			ScaSingleDomainPlugin.logError("InvocationTargetException thrown while adding mouse track listner", e);
		} catch (ClassNotFoundException e) {
			ScaSingleDomainPlugin.logError("ClassNotFoundException thrown while adding mouse track listner", e);
		}
	}
	
	public static void removeMouseTrackListenerFromControl(Control control, MouseTrackListener listener) {
		try {
			Class<?> clazz = Control.class;
			Method m = clazz.getMethod(METHOD_REMOVE_MOUSETRACK_LISTENER,  Class.forName(MOUSE_TRACK_LISTENER_CLASS_NAME));
			m.invoke(control, listener);
		} catch (SecurityException e) {
			ScaSingleDomainPlugin.logError("Failed to remove mouse track listener.", e);
		} catch (NoSuchMethodException e) {
			ScaSingleDomainPlugin.logError("Failed to add mouse track listener.", e);
		} catch (IllegalArgumentException e) {
			ScaSingleDomainPlugin.logError("Failed to add mouse track listener.", e);
		} catch (IllegalAccessException e) {
			ScaSingleDomainPlugin.logError("Failed to add mouse track listener.", e);
		} catch (InvocationTargetException e) {
			ScaSingleDomainPlugin.logError("Failed to add mouse track listener.", e);
		} catch (ClassNotFoundException e) {
			ScaSingleDomainPlugin.logError("Failed to add mouse track listener.", e);
		}
	}

	public void addMouseTrackListener(MouseTrackListener listener) {
		if (control != null) {
			addMouseTrackListenerToControl(control.getLabel(), (MouseTrackListener) listener);
		} else {
			deferredListeners.add(listener);
		}
	}
	
	public void addMouseTrackListener(CustomMouseTrackListener listener) {
		if (control != null) {
			control.addMouseTrackListener(listener);
		} else {
			deferredListeners.add(listener);
		}
	}
	
	public void removeMouseTrackListener(MouseTrackListener listener) {
		if (control != null) {
			removeMouseTrackListenerFromControl(control, (MouseTrackListener) listener);
		} else {
			deferredListeners.remove(listener);
		}
	}
	
	public void removeMouseTrackListener(CustomMouseTrackListener listener) {
		if (control != null) {
			control.removeMouseTrackListener(listener);
		} else {
			deferredListeners.remove(listener);
		}
	}

	public TrackableLabel getControl() {
		return this.control;
	}
	
	@Override
	public void update() {
		this.control.resize(true);
		this.parent.setSize(this.parent.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		this.parent.layout();
	}

}

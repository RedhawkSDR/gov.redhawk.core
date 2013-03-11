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
package gov.redhawk.ui.port.internal;

import gov.redhawk.ui.port.Activator;
import gov.redhawk.ui.port.IPortHandler;
import gov.redhawk.ui.port.IPortHandlerRegistry;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.dynamichelpers.ExtensionTracker;
import org.eclipse.core.runtime.dynamichelpers.IExtensionChangeHandler;
import org.eclipse.core.runtime.dynamichelpers.IExtensionTracker;
import org.eclipse.core.runtime.dynamichelpers.IFilter;

public class PortHandlerRegistry implements IPortHandlerRegistry, IExtensionChangeHandler {

	/**
	 * Create a tracker.
	 */
	private final ExtensionTracker tracker;

	/**
	 * The Constant ATTR_ID
	 */
	private static final String ATTR_ID = "id";

	/**
	 * The Constant ATTR_PORTHANDLER
	 */
	private static final String ATTR_PORTHANDLER = "portHandler";

	private static final String ATTR_TYPE = "dataType";

	/**
	 * The porthandler objects.
	 */
	private final HashMap<String, IPortHandler> portHandlerMap;

	private final HashMap<String, ArrayList<String>> typeToIdMap;

	public PortHandlerRegistry() {
		// Get the extension registry and the extension point for the port handlers
		final IExtensionRegistry reg = Platform.getExtensionRegistry();
		final IExtensionPoint ep = reg.getExtensionPoint(IPortHandlerRegistry.EXTENSION_POINT);

		this.portHandlerMap = new HashMap<String, IPortHandler>();
		this.typeToIdMap = new HashMap<String, ArrayList<String>>();
		this.tracker = new ExtensionTracker(reg);

		// Make sure we found the extension point
		if (ep != null) {
			// Put a filter on the tracker for this extension point
			final IFilter filter = ExtensionTracker.createExtensionPointFilter(ep);
			this.tracker.registerHandler(this, filter);

			for (final IExtension ext : ep.getExtensions()) {
				// check to see if the extension has implemented
				// IPortHandler
				final IConfigurationElement[] elements = reg.getConfigurationElementsFor(ext.getExtensionPointUniqueIdentifier());

				// Loop through all the configurations for the
				// extension points listed
				for (final IConfigurationElement element : elements) {
					try {
						// Get the class for the listed point and add the
						// extension to the registry
						final Object obj = element.createExecutableExtension("class");
						if (obj instanceof IPortHandler) {
							this.mapExtension(element);
						}
					} catch (final CoreException e) {
						Activator.logError("Exception processing extension", e);
					}
				}
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void addExtension(final IExtensionTracker tracker, final IExtension extension) {
		final IConfigurationElement[] configs = extension.getConfigurationElements();

		for (final IConfigurationElement element : configs) {
			this.mapExtension(element);
		}
	}

	/**
	 * This will only add the specified element to the handler map
	 * 
	 * @param element the handler to add
	 */
	private void mapExtension(final IConfigurationElement element) {
		// Only add PortHandler extensions
		if (PortHandlerRegistry.ATTR_PORTHANDLER.equals(element.getName())) {
			// get the ID and children of the implemented extension
			final String id = element.getAttribute(PortHandlerRegistry.ATTR_ID);
			final IConfigurationElement[] children = element.getChildren();

			try {
				if (this.portHandlerMap.get(id) == null) {
					// Instantiate the handler and put it in the handler map
					final IPortHandler tempHandler = (IPortHandler) element.createExecutableExtension("class");
					this.portHandlerMap.put(id, tempHandler);

					// Map the Handler to its datatypes
					for (final IConfigurationElement child : children) {
						final String type = child.getAttribute(PortHandlerRegistry.ATTR_TYPE);
						ArrayList<String> ids = this.typeToIdMap.get(type);
						// Make sure we have a list to put the data into first
						if (ids == null) {
							ids = new ArrayList<String>();
							this.typeToIdMap.put(type, ids);
						}
						ids.add(id);
					}
				}
			} catch (final CoreException e) {
				Activator.logError("Exception mapping port handlers", e);
			}
		}
	}

	public void removeExtension(final IExtension extension, final Object[] objects) {
		// TODO Fill this in: remove mapped objects
	}

	public IPortHandler findPortHandler(final String handlerid) {
		return this.portHandlerMap.get(handlerid);
	}
	
	public IPortHandler findPortHandler(String handlerid, String type) {
		final ArrayList<String> ids = this.typeToIdMap.get(type);
		if (ids != null && ids.contains(handlerid)) {
		    return findPortHandler(handlerid);
		}
		return null;
	}

	public IPortHandler[] getPortHandlers() {
		return this.portHandlerMap.values().toArray(new IPortHandler[this.portHandlerMap.size()]);
	}

	public String[] getIds() {
		return this.portHandlerMap.keySet().toArray(new String[this.portHandlerMap.size()]);
	}

	public IPortHandler[] findPortHandlersByType(final String type) {
		final ArrayList<String> ids = this.typeToIdMap.get(type);
		final ArrayList<IPortHandler> handlers = new ArrayList<IPortHandler>();

		if (ids != null && ids.size() > 0) {
			for (final String id : ids) {
				handlers.add(this.portHandlerMap.get(id));
			}
		}

		return handlers.toArray(new IPortHandler[handlers.size()]);
	}

}

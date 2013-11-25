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
package gov.redhawk.sca.internal.ui;

import gov.redhawk.sca.ScaPlugin;
import gov.redhawk.sca.ui.IScaContentTypeRegistry;
import gov.redhawk.sca.ui.IScaEditorDescriptor;
import gov.redhawk.sca.ui.ScaUI;
import gov.redhawk.sca.ui.ScaUiPlugin;
import gov.redhawk.sca.ui.editors.IScaContentDescriber;
import gov.redhawk.sca.util.PluginUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import mil.jpeojtrs.sca.util.ProtectedThreadExecutor;

import org.apache.commons.io.IOUtils;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.content.IContentDescription;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.core.runtime.dynamichelpers.ExtensionTracker;
import org.eclipse.core.runtime.dynamichelpers.IExtensionChangeHandler;
import org.eclipse.core.runtime.dynamichelpers.IExtensionTracker;
import org.eclipse.core.runtime.dynamichelpers.IFilter;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.PlatformUI;

/**
 * 
 */
public enum ScaContentTypeRegistry implements IExtensionChangeHandler, IScaContentTypeRegistry {
	INSTANCE;

	private static final String EP_ID = "scaContentTypes";
	private ExtensionTracker tracker;

	private final Map<String, List<ScaContentTypeBinding>> bindings = new HashMap<String, List<ScaContentTypeBinding>>();
	private final Map<String, ScaContentType> registry = new HashMap<String, ScaContentType>();

	private enum Priority {
		HIGHEST, HIGH, NORMAL, LOW, LOWEST
	}

	private static class ScaContentTypeBinding {
		private final String contentTypeId;
		private final String editorId;
		private final Priority priority;

		protected ScaContentTypeBinding(final String contentTypeId, final String editorId, final Priority priority) {
			super();
			this.contentTypeId = contentTypeId;
			this.editorId = editorId;
			this.priority = priority;
		}
	}

	private static class ScaContentType {
		private final String id;
		private final String name;
		private final IScaContentDescriber contentDescriber;
		private final Priority priority;

		protected ScaContentType(final String id, final String name, final IScaContentDescriber contentDescriber, final Priority priority) {
			this.id = id;
			this.name = name;
			this.contentDescriber = contentDescriber;
			this.priority = priority;
		}
	}

	private ScaContentTypeRegistry() {
		populateRegistry();
	}

	/**
	 * 
	 */
	private void populateRegistry() {
		final IExtensionRegistry reg = Platform.getExtensionRegistry();
		final IExtensionPoint ep = reg.getExtensionPoint(ScaUiPlugin.PLUGIN_ID, ScaContentTypeRegistry.EP_ID);

		this.tracker = new ExtensionTracker(reg);

		if (ep != null) {
			final IFilter filter = ExtensionTracker.createExtensionPointFilter(ep);
			this.tracker.registerHandler(this, filter);
			final IExtension[] extensions = ep.getExtensions();
			for (final IExtension extension : extensions) {
				addExtension(this.tracker, extension);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addExtension(final IExtensionTracker tracker, final IExtension extension) {
		final IConfigurationElement[] configs = extension.getConfigurationElements();
		for (final IConfigurationElement element : configs) {
			if ("contentType".equals(element.getName())) {
				addContentType(extension, element);
			} else if ("contentTypeBinding".equals(element.getName())) {
				addContentTypeBinding(extension, element);
			}
		}

	}

	/**
	 * @param extension
	 * @param element
	 */
	private void addContentTypeBinding(final IExtension extension, final IConfigurationElement element) {
		final String contentTypeId = element.getAttribute("contentTypeId");
		final String editorId = element.getAttribute("editorId");
		final Priority priority = Priority.valueOf(element.getAttribute("priority"));
		final ScaContentTypeBinding binding = new ScaContentTypeBinding(contentTypeId, editorId, priority);

		List<ScaContentTypeBinding> typeBindings = this.bindings.get(contentTypeId);
		if (typeBindings == null) {
			typeBindings = new ArrayList<ScaContentTypeBinding>();
			this.bindings.put(contentTypeId, typeBindings);
		}
		typeBindings.add(binding);
		this.tracker.registerObject(extension, binding, IExtensionTracker.REF_SOFT);
	}

	/**
	 * @param extension
	 * @param element
	 */
	private void addContentType(final IExtension extension, final IConfigurationElement element) {
		try {
			final ScaContentType desc = createContentType(element);
			if (desc != null) {
				this.tracker.registerObject(extension, desc, IExtensionTracker.REF_SOFT);
				this.registry.put(desc.id, desc);
			}
		} catch (final CoreException e) {
			final MultiStatus status = new MultiStatus(ScaUiPlugin.PLUGIN_ID, IStatus.OK, "Failed to add configuration element", e);
			(status).merge((e).getStatus());
			ScaUiPlugin.getDefault().getLog().log(status);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String[] findContentTypes(final Object input) {
		final List<String> retVal = new ArrayList<String>();
		if (input != null) {
			final MultiStatus status = new MultiStatus(ScaUiPlugin.PLUGIN_ID, IStatus.OK, "Errors occured while attempting to find content Type for input: "
				+ input, null);
			for (final ScaContentType type : this.registry.values()) {
				final List<ScaContentTypeBinding> editors = this.bindings.get(type.id);
				if (editors == null) {
					continue;
				}
				try {
					if (type.contentDescriber.describe(input) == IScaContentDescriber.VALID && !editors.isEmpty()) {
						retVal.add(type.id);
					}
				} catch (final IOException e) {
					final String bundleId = ScaPlugin.getDefault().getBundleId(type.contentDescriber);
					status.add(new Status(IStatus.WARNING, bundleId, type.id + " content Describer error.", e));
				}
			}
			if (!status.isOK()) {
				ScaUiPlugin.getDefault().getLog().log(status);
			}
		}
		return retVal.toArray(new String[retVal.size()]);
	}

	/**
	 * @param extension
	 * @return
	 * @throws CoreException
	 */
	private ScaContentType createContentType(final IConfigurationElement element) throws CoreException {
		final String id = element.getAttribute("id");
		final String name = element.getAttribute("name");
		final Priority priority = Priority.valueOf(element.getAttribute("priority"));
		final IScaContentDescriber describer = (IScaContentDescriber) element.createExecutableExtension("describer");
		final ScaContentType type = new ScaContentType(id, name, describer, priority);
		return type;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void removeExtension(final IExtension extension, final Object[] objects) {
		for (final Object obj : objects) {
			if (obj instanceof ScaContentType) {
				final ScaContentType desc = (ScaContentType) obj;
				for (final String key : this.registry.keySet()) {
					if (desc.equals(this.registry.get(key))) {
						this.registry.remove(key);
						break;
					}
				}
			}
			if (obj instanceof ScaContentTypeBinding) {
				final ScaContentTypeBinding binding = (ScaContentTypeBinding) obj;
				final List<ScaContentTypeBinding> type = this.bindings.get(binding.contentTypeId);
				if (type != null) {
					type.remove(binding);
				}
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IScaContentDescriber getDescriber(final String contentType) {
		final ScaContentType desc = this.registry.get(contentType);
		if (desc != null) {
			return desc.contentDescriber;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String[] findEditors(final String contentType) {
		final List<ScaContentTypeBinding> typeBindings = this.bindings.get(contentType);
		final List<String> editors = new ArrayList<String>();
		if (typeBindings != null) {
			for (final ScaContentTypeBinding binding : typeBindings) {
				editors.add(binding.editorId);
			}
		}
		return editors.toArray(new String[editors.size()]);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String findContentType(final Object obj) {
		final List<String> contentTypes = new ArrayList<String>(Arrays.asList(findContentTypes(obj)));
		Collections.sort(contentTypes, new Comparator<String>() {

			@Override
			public int compare(final String o1, final String o2) {
				final ScaContentType contentType1 = ScaContentTypeRegistry.this.registry.get(o1);
				final ScaContentType contentType2 = ScaContentTypeRegistry.this.registry.get(o2);

				return contentType1.priority.compareTo(contentType2.priority);
			}

		});
		if (contentTypes.isEmpty()) {
			return null;
		}

		return contentTypes.get(0);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String findEditor(final String contentTypeId) {
		if (contentTypeId != null) {
			final List<ScaContentTypeBinding> editors = this.bindings.get(contentTypeId);
			if (editors == null) {
				return null;
			}

			Collections.sort(editors, new Comparator<ScaContentTypeBinding>() {

				@Override
				public int compare(final ScaContentTypeBinding o1, final ScaContentTypeBinding o2) {
					return o1.priority.compareTo(o2.priority);
				}

			});
			if (editors.isEmpty()) {
				return null;
			}
			return editors.get(0).editorId;
		}
		return null;
	}

	private IScaEditorDescriptor createEditorDescriptor(final Object obj, final String contentTypeId, final String editorId) {
		final IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry().findEditor(editorId);
		final IScaContentDescriber describer = getDescriber(contentTypeId);
		final IEditorInput input = describer.getEditorInput(obj);
		return new ScaEditorDescriptor(desc, input, obj);
	}

	public IContentType getContentType(final IFileStore fileStore) {
		if (fileStore != null && !fileStore.fetchInfo().isDirectory()) {
			final String fileName = fileStore.getName();
			final Callable<IContentType> callable = new Callable<IContentType>() {

				@Override
				public IContentType call() throws Exception {
					InputStream is = null;
					try {
						is = fileStore.openInputStream(EFS.NONE, null);
						final IContentDescription contDesc = Platform.getContentTypeManager().getDescriptionFor(is, fileName, IContentDescription.ALL);
						if (contDesc != null) {
							return contDesc.getContentType();
						}
						return null;
					} finally {
						IOUtils.closeQuietly(is);
					}
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
		}
		return null;
	}

	@Override
	public IScaEditorDescriptor getScaEditorDescriptor(final Object obj) {
		final String contentTypeId = findContentType(obj);
		final String editorId = findEditor(contentTypeId);
		if (contentTypeId != null && editorId != null) {
			final IScaEditorDescriptor result = createEditorDescriptor(obj, contentTypeId, editorId);
			if (result.getEditorDescriptor() != null && result.getEditorInput() != null) {
				return result;
			}
		}
		final IFileStore fileStore = PluginUtil.adapt(IFileStore.class, obj);
		if (fileStore != null) {
			final IEditorDescriptor defaultEditor = getDefaultEditor(fileStore);
			if (defaultEditor != null) {
				final IEditorInput input = ScaUI.getEditorInput(fileStore);
				final ScaEditorDescriptor result = new ScaEditorDescriptor(defaultEditor, input, obj);
				if (result.getEditorDescriptor() != null && result.getEditorInput() != null) {
					return result;
				}
			}
		}
		return null;
	}

	public IEditorDescriptor getDefaultEditor(final IFileStore fileStore) {
		final IContentType contentType = getContentType(fileStore);
		if (contentType != null) {
			final IEditorRegistry editorRegistry = PlatformUI.getWorkbench().getEditorRegistry();
			IEditorDescriptor defaultEditor = editorRegistry.getDefaultEditor(fileStore.getName(), contentType);

			// Now fallback to the text editor
			if (defaultEditor == null) {
				defaultEditor = editorRegistry.findEditor("org.eclipse.ui.DefaultTextEditor");
				if (defaultEditor == null) {
					return null;
				}
			}

			// and make sure the default editor isn't a broken one
			if ("org.eclipse.wst.xml.ui.internal.tabletree.XMLMultiPageEditorPart".equals(defaultEditor.getId())) {
				defaultEditor = editorRegistry.findEditor("org.eclipse.ui.DefaultTextEditor");
			}
			return defaultEditor;
		}
		return null;
	}

	@Override
	public IScaEditorDescriptor[] getAllScaEditorDescriptors(final Object obj) {
		final List<IScaEditorDescriptor> retVal = new ArrayList<IScaEditorDescriptor>();
		final String[] contentTypesIds = findContentTypes(obj);
		for (final String contentTypeId : contentTypesIds) {
			final String[] editorIds = findEditors(contentTypeId);
			for (final String editorId : editorIds) {
				final IScaEditorDescriptor item = createEditorDescriptor(obj, contentTypeId, editorId);
				if (item.getEditorDescriptor() != null && item.getEditorInput() != null) {
					retVal.add(item);
				}
			}
		}
		final IFileStore fileStore = PluginUtil.adapt(IFileStore.class, obj);
		final IContentType contentType = getContentType(fileStore);
		final IEditorRegistry editorRegistry = PlatformUI.getWorkbench().getEditorRegistry();
		if (contentType != null) {
			final IEditorDescriptor[] editors = editorRegistry.getEditors(fileStore.getName(), contentType);
			final IEditorInput input = ScaUI.getEditorInput(fileStore);
			for (final IEditorDescriptor editor : editors) {
				final ScaEditorDescriptor item = new ScaEditorDescriptor(editor, input, obj);
				if (item.getEditorDescriptor() != null && item.getEditorInput() != null) {
					retVal.add(item);
				}
			}
		}

		return retVal.toArray(new IScaEditorDescriptor[retVal.size()]);
	}
}

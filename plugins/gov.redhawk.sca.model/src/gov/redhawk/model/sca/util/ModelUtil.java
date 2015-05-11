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
package gov.redhawk.model.sca.util;

import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.sca.util.Debug;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mil.jpeojtrs.sca.dcd.DeviceConfiguration;
import mil.jpeojtrs.sca.prf.Properties;
import mil.jpeojtrs.sca.prf.util.PrfResourceImpl;
import mil.jpeojtrs.sca.prf.util.PropertiesUtil;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.scd.SoftwareComponent;
import mil.jpeojtrs.sca.scd.util.ScdResourceImpl;
import mil.jpeojtrs.sca.spd.Descriptor;
import mil.jpeojtrs.sca.spd.LocalFile;
import mil.jpeojtrs.sca.spd.PropertyFile;
import mil.jpeojtrs.sca.spd.SoftPkg;
import mil.jpeojtrs.sca.spd.util.SpdResourceImpl;
import mil.jpeojtrs.sca.util.ScaResourceFactoryUtil;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.emf.workspace.util.WorkspaceSynchronizer;

/**
 * The Class ModelUtil.
 */
public final class ModelUtil {

	private static final Debug TRACE_LOGGER = new Debug(ScaModelPlugin.ID, "ModelUtil");

	/**
	 * Instantiates a new model util.
	 */
	private ModelUtil() {

	}

	/**
	 * Gets the spd file name.
	 * 
	 * @param softPkg the soft pkg
	 * 
	 * @return the spd file name
	 * @since 11.0
	 */
	public static String getSpdFileName(final SoftPkg softPkg) {
		final IFile file = ModelUtil.getResource(softPkg.eResource());
		if (file == null) {
			return null;
		}
		return file.getName();
	}

	/**
	 * Gets the scd file name.
	 * 
	 * @param softPkg the soft pkg
	 * 
	 * @return the scd file name
	 */
	public static String getScdFileName(final SoftPkg softPkg) {
		final Descriptor descriptor = softPkg.getDescriptor();
		if (descriptor == null) {
			return null;
		}
		final LocalFile file = descriptor.getLocalfile();
		if (file == null) {
			return null;
		}
		return file.getName();
	}

	/**
	 * Gets the scd file name.
	 * 
	 * @param softPkg the soft pkg
	 * 
	 * @return the scd file name
	 */
	public static IFile getScdFile(final Descriptor descriptor) {
		if (descriptor == null) {
			return null;
		}
		return ModelUtil.getResource(descriptor.getComponent());
	}

	/**
	 * Gets the prf file.
	 * 
	 * @param propertyFile the property file
	 * 
	 * @return the prf file
	 */
	public static IFile getPrfFile(final PropertyFile propertyFile) {
		if (propertyFile == null) {
			return null;
		}
		return ModelUtil.getResource(propertyFile.getProperties());
	}

	/**
	 * Gets the prf file name.
	 * 
	 * @param propertyFile the property file
	 * 
	 * @return the prf file name
	 */
	public static String getPrfFileName(final PropertyFile propertyFile) {
		if (propertyFile == null) {
			return null;
		}
		final LocalFile file = propertyFile.getLocalFile();
		if (file == null) {
			return null;
		}
		return file.getName();
	}

	/**
	 * Gets the software component.
	 * 
	 * @param resource the resource
	 * 
	 * @return the software component
	 */
	public static SoftwareComponent getSoftwareComponent(final Resource resource) {
		if (resource == null) {
			return null;
		}
		return SoftwareComponent.Util.getSoftwareComponent(resource);
	}

	/**
	 * Gets the soft pkg.
	 * 
	 * @param resource the resource
	 * 
	 * @return the soft pkg
	 */
	public static SoftPkg getSoftPkg(final Resource resource) {
		if (resource != null) {
			if (resource instanceof ScdResourceImpl) {
				EObject container = SoftwareComponent.Util.getSoftwareComponent(resource).eContainer();
				if (container != null) {
					return (SoftPkg) container.eContainer();
				}
			} else if (resource instanceof SpdResourceImpl) {
				return SoftPkg.Util.getSoftPkg(resource);
			}
		}
		return null;
	}

	/**
	 * Gets the resource.
	 * 
	 * @param eObject the e object
	 * 
	 * @return the resource
	 */
	public static IFile getResource(final EObject eObject) {
		if (eObject == null) {
			return null;
		}
		final Resource eResource = eObject.eResource();
		return ModelUtil.getResource(eResource);
	}

	public static IProject getProject(final Resource eResource) {
		final IFile file = ModelUtil.getResource(eResource);
		if (file != null) {
			return file.getProject();
		}
		return null;
	}

	public static IProject getProject(final EObject eObject) {
		if (eObject == null) {
			return null;
		}
		final IFile resource = ModelUtil.getResource(eObject);
		if (resource != null) {
			return resource.getProject();
		} else {
			return null;
		}
	}

	/**
	 * Gets the resource.
	 * 
	 * @param eResource the e resource
	 * 
	 * @return the resource
	 */
	public static IFile getResource(final Resource eResource) {
		if (eResource == null) {
			return null;
		}
		return WorkspaceSynchronizer.getFile(eResource);
	}

	/**
	 * Gets the sad file.
	 * 
	 * @param assem the assem
	 * 
	 * @return the sad file
	 */
	public static IFile getSadFile(final SoftwareAssembly assem) {
		return ModelUtil.getResource(assem);
	}

	/**
	 * Gets the dcd file.
	 * 
	 * @param dcd the dcd
	 * 
	 * @return the dcd file
	 * @since 12.0
	 */
	public static IFile getDcdFile(final DeviceConfiguration dcd) {
		return ModelUtil.getResource(dcd);
	}

	/**
	 * Gets the properties.
	 * 
	 * @param resource the resource
	 * 
	 * @return the properties
	 */
	public static Properties getProperties(final Resource resource) {
		if (resource != null) {
			if (resource instanceof PrfResourceImpl) {
				return Properties.Util.getProperties(resource);
			} else if (resource instanceof SpdResourceImpl) {
				return SoftPkg.Util.getSoftPkg(resource).getPropertyFile().getProperties();
			}
		}
		return null;

	}

	/**
	 * Load a DeviceConfiguration(node).
	 * 
	 * @param fileURI the file uri
	 * 
	 * @return the DeviceConfiguration specified by the URI
	 */
	public static DeviceConfiguration loadDeviceConfiguration(final URI fileURI) {
		try {
			final ResourceSet resourceSet = ScaResourceFactoryUtil.createResourceSet();
			// Demand load the resource for this file.
			final Resource resource = resourceSet.getResource(fileURI, true);

			return ModelUtil.getDeviceConfiguration(resource);
		} catch (WrappedException we) {
			if (TRACE_LOGGER.enabled) {
				TRACE_LOGGER.catching("Unable to load DCD file: " + fileURI, we);
			}
			return null;
		}
	}

	public static DeviceConfiguration getDeviceConfiguration(final Resource resource) {
		if (resource == null) {
			return null;
		}
		return DeviceConfiguration.Util.getDeviceConfiguration(resource);
	}

	/**
	 * Load soft pkg.
	 * 
	 * @param fileURI the file uri
	 * 
	 * @return the soft pkg
	 */
	public static SoftPkg loadSoftPkg(final URI fileURI) {
		try {
			// Parse the SPD file and copy all referenced files into the workspace
			final ResourceSet resourceSet = ScaResourceFactoryUtil.createResourceSet();
			//resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(".xml", new XMLResourceFactoryImpl());

			// Demand load the resource for this file.
			final Resource resource = resourceSet.getResource(fileURI, true);

			return ModelUtil.getSoftPkg(resource);
		} catch (WrappedException we) {
			if (TRACE_LOGGER.enabled) {
				TRACE_LOGGER.catching("Unable to load SPD file: " + fileURI, we);
			}
			return null;
		}
	}

	/**
	 * Load software component.
	 * 
	 * @param fileURI the file uri
	 * 
	 * @return the software component
	 */
	public static SoftwareComponent loadSoftwareComponent(final URI fileURI) {
		try {
			// Parse the SPD file and copy all referenced files into the workspace
			final ResourceSet resourceSet = ScaResourceFactoryUtil.createResourceSet();
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(".xml", new XMLResourceFactoryImpl());

			// Demand load the resource for this file.
			final Resource resource = resourceSet.getResource(fileURI, true);

			return ModelUtil.getSoftwareComponent(resource);
		} catch (WrappedException we) {
			if (TRACE_LOGGER.enabled) {
				TRACE_LOGGER.catching("Unable to load SCD file: " + fileURI, we);
			}
			return null;
		}
	}

	/**
	 * Load software assembly.
	 * 
	 * @param fileURI the file uri
	 * 
	 * @return the software assembly
	 */
	public static SoftwareAssembly loadSoftwareAssembly(final URI fileURI) {
		try {
			// Parse the SPD file and copy all referenced files into the workspace
			final ResourceSet resourceSet = ScaResourceFactoryUtil.createResourceSet();
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(".xml", new XMLResourceFactoryImpl());

			// Demand load the resource for this file.
			final Resource resource = resourceSet.getResource(fileURI, true);

			return ModelUtil.getSoftwareAssembly(resource);
		} catch (WrappedException we) {
			if (TRACE_LOGGER.enabled) {
				TRACE_LOGGER.catching("Unable to load SAD file: " + fileURI, we);
			}
			return null;
		}
	}

	/**
	 * Gets the software assembly.
	 * 
	 * @param resource the resource
	 * 
	 * @return the software assembly
	 */
	public static SoftwareAssembly getSoftwareAssembly(final Resource resource) {
		if (resource == null) {
			return null;
		}
		return SoftwareAssembly.Util.getSoftwareAssembly(resource);
	}

	/**
	 * @param sadURI
	 * @return
	 */
	public static IFile getResource(final URI uri) {
		final IPath path = new Path(uri.toPlatformString(true));
		IFile result = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
		if ((result == null) && !uri.isRelative()) {
			try {
				final IFile[] files = ResourcesPlugin.getWorkspace().getRoot().findFilesForLocationURI(new java.net.URI(uri.toString()));
				if (files.length > 0) {
					// set the result to be the first file found
					result = files[0];
				}
			} catch (final URISyntaxException e) {
				// PASS
				// won't get this because EMF provides a well-formed URI
			}
		}

		return result;
	}

	/**
	 * Take a project as input, search its members and return the desired file if it is found
	 * 
	 * @param project the project that may contain the desired file
	 * @return file return the project's file, if it has one
	 * @since 14.0
	 */
	public static IFile getFile(final IProject project, String fileExtension) {
		IFile file;

		try {
			for (IResource resource : project.members()) {
				if (resource.getName().endsWith(fileExtension)) {
					file = (IFile) resource;

					if (file.exists()) {
						return file;
					}
				}
			}
		} catch (CoreException e) {
			// PASS
		}

		return null;
	}

	/**
	 * @since 15.0
	 */
	public static boolean isSettable(ScaAbstractProperty< ? > prop) {
		TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(prop);
		if (editingDomain == null) {
			return PropertiesUtil.canOverride(prop.getDefinition());
		} else {
			return PropertiesUtil.canConfigure(prop.getDefinition());
		}
	}
	
	/**
	 * 
	 * @param obj
	 * @param feature
	 * @return A Immutable copy of the list. 
	 * @since 19.1
	 */
	public static <T extends Object> List<T> getAsImmutableList(final EObject obj, final EStructuralFeature feature) {
		try {
			List<T> retVal = ScaModelCommand.runExclusive(obj, new RunnableWithResult.Impl<List<T>>() {

				@SuppressWarnings("unchecked")
				@Override
				public void run() {
					Object value = obj.eGet(feature);
					if (value instanceof List<?>) {
						setResult(Collections.unmodifiableList(new ArrayList<T>((List<T>) value)));
					}
				}
			});
			if (retVal == null) {
				return Collections.emptyList();
			} else {
				return retVal;
			}
		} catch (InterruptedException e) {
			return Collections.emptyList();
		}
	}
	
	/**
	 * Takes an IProject and will attempt to find the soft package associated with the project loading it into
	 * the provided resource set.
	 * @param project The IProject for the resource
	 * @param set The resource set to load the softpackage into
	 * @return The SoftPkg associated with the project or null if a softpackage is not found
	 * @since 19.1
	 */
	public static SoftPkg getSoftPkg(final IProject project, ResourceSet set) {
		String projectName = project.getName();
		int lastDotIndex = projectName.lastIndexOf('.');
		String projectBaseName;
		if (lastDotIndex != -1) {
			projectBaseName = projectName.substring(lastDotIndex + 1);
		} else {
			projectBaseName = projectName;
		}
		final IFile softPkgFile = project.getFile(projectBaseName + ".spd.xml");
		SoftPkg softPkg = null;
		
		if (softPkgFile.exists()) {
			final Resource resource = set.getResource(URI.createFileURI(softPkgFile.getLocation().toString()), true);
			softPkg = SoftPkg.Util.getSoftPkg(resource);
		}
		return softPkg;
	}
	
	/**
	 * Takes an IProject and will attempt to find the soft package associated with the project.
	 * @param project The IProject for the resource
	 * @return The SoftPkg associated with the project or null if a softpackage is not found
	 * @since 19.1
	 */
	public static SoftPkg getSoftPkg(final IProject project) {
		final ResourceSet set = ScaResourceFactoryUtil.createResourceSet();
		return getSoftPkg(project, set);
	}
}

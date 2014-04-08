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
package gov.redhawk.diagram;

import gov.redhawk.diagram.editor.URIEditorInputProxy;
import gov.redhawk.sca.efs.ScaFileSystemPlugin;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import mil.jpeojtrs.sca.util.ScaResourceFactoryUtil;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.core.services.ViewService;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.ui.IEditorInput;

/**
 * @since 3.0
 */
public final class DiagramUtil {

	private DiagramUtil() {

	}

	/**
	 * 
	 */
	public static void initializeDiagramResource(final IDiagramUtilHelper options, final URI diagramURI, final Resource sadResource) throws IOException,
	        CoreException {
		if (diagramURI.isPlatform()) {
			final IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(diagramURI.toPlatformString(true)));
			
			file.refreshLocal(IResource.DEPTH_ONE, new NullProgressMonitor());
			
			if (!file.exists()) {
				final IWorkspaceRunnable operation = new IWorkspaceRunnable() {

					@Override
					public void run(final IProgressMonitor monitor) throws CoreException {
						final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
						try {
							DiagramUtil.populateDiagram(options, diagramURI, sadResource, buffer);
						} catch (final IOException e) {
							// PASS
						}
						file.create(new ByteArrayInputStream(buffer.toByteArray()), true, monitor);
					}

				};
				final ISchedulingRule rule = ResourcesPlugin.getWorkspace().getRuleFactory().createRule(file);

				ResourcesPlugin.getWorkspace().run(operation, rule, 0, null);
			}
		} else {
			DiagramUtil.populateDiagram(options, diagramURI, sadResource, null);
		}
	}

	private static void populateDiagram(final IDiagramUtilHelper options, final URI diagramURI, final Resource resource, final OutputStream buffer)
	        throws IOException {
		// Create a resource set
		//
		final ResourceSet resourceSet = ScaResourceFactoryUtil.createResourceSet();

		// Create a resource for this file.
		//
		final Resource diagramResource = resourceSet.createResource(diagramURI);

		final String diagramName = diagramURI.lastSegment();
		final EObject obj = options.getRootDiagramObject(resource);
		final Diagram diagram = ViewService.createDiagram(obj, options.getModelId(), options.getDiagramPreferencesHint());
		if (diagram != null) {
			diagram.setName(diagramName);
			diagram.setElement(obj);
			diagramResource.getContents().add(diagram);
			if (buffer != null) {
				diagramResource.save(buffer, options.getSaveOptions());
			} else {
				diagramResource.save(options.getSaveOptions());
			}
		}
	}

	public static IEditorInput getDiagramWrappedInput(final URI diagramURI, final TransactionalEditingDomain editingDomaing) {
		return new URIEditorInputProxy(new URIEditorInput(diagramURI), editingDomaing);
	}

	public static URI getDiagramResourceURI(final IDiagramUtilHelper options, final Resource resource) throws IOException {
		if (resource != null) {
			final URI uri = resource.getURI();
			if (uri.isPlatformResource()) {
				final IFile file = options.getResource(resource);
				return DiagramUtil.getRelativeDiagramResourceURI(options, file);
			} else {
				return DiagramUtil.getTemporaryDiagramResourceURI(options, uri);
			}
		}
		return null;
	}

	/**
	 * Initialize sad diagram.
	 * 
	 * @param b
	 * @throws IOException
	 */
	private static URI getTemporaryDiagramResourceURI(final IDiagramUtilHelper options, final URI uri) throws IOException {
		final String name = uri.lastSegment();
		String tmpName = "rh_" + name.substring(0, name.length() - options.getSemanticFileExtension().length());
		File tempDir = ScaFileSystemPlugin.getDefault().getTempDirectory();
		final File tempFile = File.createTempFile(tmpName, options.getDiagramFileExtension(), tempDir);
		tempFile.deleteOnExit();

		final URI retVal = URI.createURI(tempFile.toURI().toString());

		return retVal;
	}

	/**
	 * Initialize sad diagram.
	 * 
	 * @param b
	 */
	private static URI getRelativeDiagramResourceURI(final IDiagramUtilHelper options, final IFile file) {
		final IFile diagramFile = file.getParent()
		        .getFile(
		                new Path(file.getName().substring(0, file.getName().length() - options.getSemanticFileExtension().length())
		                        + options.getDiagramFileExtension()));
		final URI uri = URI.createPlatformResourceURI(diagramFile.getFullPath().toString(), true);
		return uri;
	}
	
	/**
	 * 
	 * @param resource
	 * @return
	 * @since 4.0
	 */
	public static boolean isDiagramLocalSandbox(final Resource resource) {
		return "LocalSca.sad.xml".equals(resource.getURI().lastSegment());
	}
}

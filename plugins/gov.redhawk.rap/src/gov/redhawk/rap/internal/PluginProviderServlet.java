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
package gov.redhawk.rap.internal;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

/**
 * 
 */
public class PluginProviderServlet extends HttpServlet {

	@Override
	protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
		final Path path = new Path(req.getRequestURI());
		String pluginId = path.lastSegment();
		if (pluginId.endsWith(".jar")) {
			pluginId = pluginId.substring(0, pluginId.length() - 4);
		}
		final Bundle b = Platform.getBundle(pluginId);
		if (b == null) {
			final String protocol = req.getProtocol();
			final String msg = "Plugin does not exist: " + pluginId;
			if (protocol.endsWith("1.1")) {
				resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, msg);
			} else {
				resp.sendError(HttpServletResponse.SC_BAD_REQUEST, msg);
			}
			return;
		}
		final File file = FileLocator.getBundleFile(b);
		resp.setContentType("application/octet-stream");

		if (file.isFile()) {
			final String contentDisposition = "attachment; filename=\"" + file.getName() + "\"";
			resp.setHeader("Content-Disposition", contentDisposition);
			resp.setContentLength((int) file.length());
			final FileInputStream istream = new FileInputStream(file);
			try {
				IOUtils.copy(istream, resp.getOutputStream());
			} finally {
				istream.close();
			}
			resp.flushBuffer();
		} else {
			final String contentDisposition = "attachment; filename=\"" + file.getName() + ".jar\"";
			resp.setHeader("Content-Disposition", contentDisposition);
			final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

			final Manifest man = new Manifest(new FileInputStream(new File(file, "META-INF/MANIFEST.MF")));
			final JarOutputStream out = new JarOutputStream(outputStream, man);
			final File binDir = new File(file, "bin");

			if (binDir.exists()) {
				addFiles(out, Path.ROOT, binDir.listFiles());
				for (final File f : file.listFiles()) {
					if (!f.getName().equals("bin")) {
						addFiles(out, Path.ROOT, f);
					}
				}
			} else {
				addFiles(out, Path.ROOT, file.listFiles());
			}
			out.close();
			outputStream.close();
			final ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());
			resp.setContentLength(outputStream.size());
			try {
				IOUtils.copy(inputStream, resp.getOutputStream());
			} finally {
				inputStream.close();
			}
			resp.flushBuffer();
		}

	}

	private void addFiles(final JarOutputStream out, final IPath path, final File... listFiles) throws IOException {
		for (final File f : listFiles) {
			if (f.getName().charAt(0) == '.') {
				continue;
			} else if (f.getName().equals("MANIFEST.MF")) {
				continue;
			}
			final IPath filePath = path.append(f.getName());
			String str = filePath.toString();
			if (str.startsWith("/")) {
				str = str.substring(1);
			}
			if (f.isDirectory()) {
				if (!str.endsWith("/")) {
					str = str + "/";
				}
				final JarEntry entry = new JarEntry(str);
				entry.setSize(0);
				entry.setTime(f.lastModified());
				out.putNextEntry(entry);
				out.closeEntry();
				addFiles(out, filePath, f.listFiles());
			} else {
				final JarEntry entry = new JarEntry(str);
				entry.setSize(f.length());
				entry.setTime(f.lastModified());
				out.putNextEntry(entry);
				final FileInputStream istream = new FileInputStream(f);
				try {
					IOUtils.copy(istream, out);
				} finally {
					istream.close();
				}
				out.closeEntry();
			}
		}
	}
}

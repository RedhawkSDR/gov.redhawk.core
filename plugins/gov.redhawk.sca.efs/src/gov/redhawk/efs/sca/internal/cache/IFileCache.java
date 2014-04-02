/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.efs.sca.internal.cache;

import java.io.File;
import java.io.InputStream;

import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

import CF.FileSystemOperations;

/**
 * 
 */
public interface IFileCache {

	FileSystemOperations getScaFileSystem() throws CoreException;

	boolean isDirectory();

	String[] childNames(int options, IProgressMonitor monitor) throws CoreException;

	InputStream openInputStream() throws CoreException;

	IFileInfo[] childInfos(int options, IProgressMonitor monitor) throws CoreException;

	void update() throws CoreException;

	File toLocalFile() throws CoreException;

}

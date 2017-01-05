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
package gov.redhawk.core.internal.filemanager;

import java.util.List;

import org.eclipse.core.runtime.IPath;

import CF.File;
import CF.FileException;
import CF.InvalidFileName;
import CF.FileSystemPackage.FileInformationType;

/**
 * An entry in the IDE's file manager file system. See derived classes for details.
 */
public interface Node {

	public void remove(IPath fileName) throws FileException, InvalidFileName;

	public void rmdir(IPath directoryName) throws InvalidFileName, FileException;

	public File create(IPath fileName) throws InvalidFileName, FileException;

	public void mkdir(IPath directoryName) throws InvalidFileName, FileException;

	public boolean exists(IPath fileName) throws InvalidFileName;

	public List<FileInformationType> list(IPath pattern) throws FileException, InvalidFileName;

	public File open(IPath fileName, final boolean readOnly) throws InvalidFileName, FileException;

}

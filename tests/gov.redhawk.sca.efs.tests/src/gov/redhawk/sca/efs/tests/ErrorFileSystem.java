/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.sca.efs.tests;

import CF.DataType;
import CF.ErrorNumberType;
import CF.File;
import CF.FileException;
import CF.FileSystemOperations;
import CF.InvalidFileName;
import CF.PropertiesHolder;
import CF.FileSystemPackage.FileInformationType;
import CF.FileSystemPackage.FileType;
import CF.FileSystemPackage.UnknownFileSystemProperties;

/**
 * Methods are designed to throw an exception only for specifically named files/directories. This allows triggering
 * specific errors from file system operations layered on top of this class.
 * <p/>
 * Conventions: a single-letter name for directories (e.g. "A") and double-letter for files (e.g. "AA").
 */
public class ErrorFileSystem implements FileSystemOperations {

	public ErrorFileSystem() {
	}

	public void remove(String fileName) throws FileException, InvalidFileName {
		if ("/A/*".equals(fileName)) {
			throw new FileException(ErrorNumberType.CF_E2BIG, "ABC");
		} else if ("/AA".equals(fileName)) {
			throw new FileException(ErrorNumberType.CF_E2BIG, "AABC");
		} else if ("/B/*".equals(fileName)) {
			throw new InvalidFileName(ErrorNumberType.CF_EACCES, "BCD");
		} else if ("/BB".equals(fileName)) {
			throw new InvalidFileName(ErrorNumberType.CF_EACCES, "BBCD");
		}
	}

	public void copy(String sourceFileName, String destinationFileName) throws InvalidFileName, FileException {
		if ("/CC".equals(sourceFileName)) {
			throw new FileException(ErrorNumberType.CF_EAGAIN, "CCDE");
		} else if ("/DD".equals(sourceFileName)) {
			throw new InvalidFileName(ErrorNumberType.CF_EBADF, "DDEF");
		}
	}

	public void move(String sourceFileName, String destinationFileName) throws InvalidFileName, FileException {
		if ("/E".equals(sourceFileName)) {
			throw new FileException(ErrorNumberType.CF_EBADMSG, "EFG");
		} else if ("/F".equals(sourceFileName)) {
			throw new InvalidFileName(ErrorNumberType.CF_EBUSY, "FGH");
		}
	}

	public boolean exists(String fileName) throws InvalidFileName {
		if ("/G".equals(fileName)) {
			throw new InvalidFileName(ErrorNumberType.CF_ECANCELED, "GHI");
		} else if ("/JJ".equals(fileName) || "/KK".equals(fileName)) {
			// For create()
			return false;
		} else if ("/N".equals(fileName) || "/O".equals(fileName)) {
			// For mkdir()
			return false;
		}
		return true;
	}

	public FileInformationType[] list(String pattern) throws FileException, InvalidFileName {
		if ("/H".equals(pattern)) {
			throw new FileException(ErrorNumberType.CF_ECHILD, "HIJ");
		} else if ("/I".equals(pattern)) {
			throw new InvalidFileName(ErrorNumberType.CF_EDEADLK, "IJK");
		} else if ("/R/*".equals(pattern)) {
			throw new FileException(ErrorNumberType.CF_EISDIR, "RST");
		} else if ("/S/*".equals(pattern)) {
			throw new InvalidFileName(ErrorNumberType.CF_EMFILE, "STU");
		}

		if ("/JJ".equals(pattern) || "/KK".equals(pattern)) {
			// For create()
			throw new FileException(ErrorNumberType.CF_ENOENT, "File does not exist");
		}
		if ("/N".equals(pattern) || "/O".equals(pattern)) {
			// For mkdir()
			throw new FileException(ErrorNumberType.CF_ENOENT, "File does not exist");
		}

		if (!pattern.endsWith("/*")) {
			if (pattern.length() == 2) {
				// Return a directory of the same name
				return new FileInformationType[] { new FileInformationType(pattern.substring(1), FileType.DIRECTORY, 0, new DataType[0]) };
			} else {
				// Return a file of the same name
				return new FileInformationType[] { new FileInformationType(pattern.substring(1), FileType.PLAIN, 1, new DataType[0]) };
			}
		}
		return new FileInformationType[0];
	}

	public File create(String fileName) throws InvalidFileName, FileException {
		if ("/JJ".equals(fileName)) {
			throw new FileException(ErrorNumberType.CF_EDOM, "JJKL");
		} else if ("/KK".equals(fileName)) {
			throw new InvalidFileName(ErrorNumberType.CF_EEXIST, "KKLM");
		}
		return null;
	}

	public File open(String fileName, boolean readOnly) throws InvalidFileName, FileException {
		if ("/LL".equals(fileName)) {
			throw new FileException(ErrorNumberType.CF_EFAULT, "LLMN");
		} else if ("/MM".equals(fileName)) {
			throw new InvalidFileName(ErrorNumberType.CF_EFBIG, "MMNO");
		}
		return null;
	}

	public void mkdir(String directoryName) throws InvalidFileName, FileException {
		if ("/N".equals(directoryName)) {
			throw new FileException(ErrorNumberType.CF_EINPROGRESS, "NOP");
		} else if ("/O".equals(directoryName)) {
			throw new InvalidFileName(ErrorNumberType.CF_EINTR, "OPQ");
		}
	}

	public void rmdir(String directoryName) throws InvalidFileName, FileException {
		if ("/P".equals(directoryName)) {
			throw new FileException(ErrorNumberType.CF_EINVAL, "PQR");
		} else if ("/Q".equals(directoryName)) {
			throw new InvalidFileName(ErrorNumberType.CF_EIO, "QRS");
		}
	}

	public void query(PropertiesHolder fileSystemProperties) throws UnknownFileSystemProperties {
		throw new UnknownFileSystemProperties();
	}
}

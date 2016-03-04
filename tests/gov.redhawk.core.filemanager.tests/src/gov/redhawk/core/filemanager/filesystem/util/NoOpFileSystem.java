package gov.redhawk.core.filemanager.filesystem.util;

import CF.File;
import CF.FileException;
import CF.InvalidFileName;
import CF.PropertiesHolder;
import CF._FileSystemStub;
import CF.FileSystemPackage.FileInformationType;
import CF.FileSystemPackage.UnknownFileSystemProperties;

public class NoOpFileSystem extends _FileSystemStub {

	private String removed;
	private String copied;
	private String moved;
	private String exists;
	private String listed;
	private String created;
	private String opened;
	private String mkdired;
	private String rmdired;

	public String getRemoved() {
		return removed;
	}

	public String getCopied() {
		return copied;
	}

	public String getMoved() {
		return moved;
	}

	public String getExists() {
		return exists;
	}

	public String getListed() {
		return listed;
	}

	public String getCreated() {
		return created;
	}

	public String getOpened() {
		return opened;
	}

	public String getMkdired() {
		return mkdired;
	}

	public String getRmdired() {
		return rmdired;
	}

	@Override
	public void remove(String fileName) throws FileException, InvalidFileName {
		removed = fileName;
	}

	@Override
	public void copy(String sourceFileName, String destinationFileName) throws InvalidFileName, FileException {
		copied = sourceFileName + " to " + destinationFileName;
	}

	@Override
	public void move(String sourceFileName, String destinationFileName) throws InvalidFileName, FileException {
		moved = sourceFileName + " to " + destinationFileName;
	}

	@Override
	public boolean exists(String fileName) throws InvalidFileName {
		exists = fileName;
		return false;
	}

	@Override
	public FileInformationType[] list(String pattern) throws FileException, InvalidFileName {
		listed = pattern;
		return new FileInformationType[0];
	}

	@Override
	public File create(String fileName) throws InvalidFileName, FileException {
		created = fileName;
		return null;
	}

	@Override
	public File open(String fileName, boolean read_Only) throws InvalidFileName, FileException {
		opened = fileName + " as " + (read_Only ? "read-only" : "read-write");
		return null;
	}

	@Override
	public void mkdir(String directoryName) throws InvalidFileName, FileException {
		mkdired = directoryName;
	}

	@Override
	public void rmdir(String directoryName) throws InvalidFileName, FileException {
		rmdired = directoryName;
	}

	@Override
	public void query(PropertiesHolder fileSystemProperties) throws UnknownFileSystemProperties {
	}

}

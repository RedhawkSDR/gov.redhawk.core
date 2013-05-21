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
package gov.redhawk.core.internal.filemanager;

import gov.redhawk.core.filemanager.filesystem.AbstractFileSystem.ScaFileInformationDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import mil.jpeojtrs.sca.util.AnyUtils;

import org.omg.CORBA.Any;
import org.omg.CORBA.TCKind;

import CF.DataType;
import CF.ErrorNumberType;
import CF.File;
import CF.FileException;
import CF.FileSystem;
import CF.InvalidFileName;
import CF.FileManagerPackage.InvalidFileSystem;
import CF.FileManagerPackage.MountPointAlreadyExists;
import CF.FileManagerPackage.MountType;
import CF.FileManagerPackage.NonExistentMount;
import CF.FileSystemPackage.FileInformationType;
import CF.FileSystemPackage.FileType;

public class Directory implements Node {
	private final Map<String, List<Node>> mounts = new HashMap<String, List<Node>>();

	public void remove(final List<String> fileName) throws FileException, InvalidFileName {
		if (fileName.size() == 0) {
			throw new InvalidFileName();
		}
		final List<Node> nodes = this.mounts.get(fileName.get(0));
		if (fileName.size() == 1) {
			throw new FileException(ErrorNumberType.CF_EIO, "Can not remove");
		} else {
			if (nodes == null) {
				throw new FileException(ErrorNumberType.CF_EIO, "File does not exist");
			} else if (nodes.get(0) instanceof Directory) {
				((Directory) nodes.get(0)).remove(fileName.subList(1, fileName.size()));
			} else if (nodes.get(0) instanceof MountPoint) {
				final StringBuilder builder = new StringBuilder();
				for (final String s : fileName.subList(1, fileName.size())) {
					builder.append('/');
					builder.append(s);
				}
				((MountPoint) nodes.get(0)).getFileSystem().remove(builder.toString());
			} else {
				throw new FileException(ErrorNumberType.CF_EIO, "UnsupportedOperationException");
			}
		}
	}

	public void rmdir(final List<String> directoryName) throws InvalidFileName, FileException {
		if (directoryName.size() == 0) {
			throw new InvalidFileName();
		}
		final List<Node> nodes = this.mounts.get(directoryName.get(0));
		if (directoryName.size() == 1) {
			if (nodes != null) {
				this.mounts.remove(directoryName.get(0));
			} else {
				throw new FileException(ErrorNumberType.CF_EIO, "File does not exist");
			}
		} else {
			if (nodes == null) {
				throw new FileException(ErrorNumberType.CF_EIO, "File does not exist");
			} else if (nodes.get(0) instanceof Directory) {
				((Directory) nodes.get(0)).rmdir(directoryName.subList(1, directoryName.size()));
			} else if (nodes.get(0) instanceof MountPoint) {
				final StringBuilder builder = new StringBuilder();
				for (final String s : directoryName.subList(1, directoryName.size())) {
					builder.append('/');
					builder.append(s);
				}
				((MountPoint) nodes.get(0)).getFileSystem().rmdir(builder.toString());
			} else {
				throw new UnsupportedOperationException();
			}
		}
	}

	public File create(final List<String> fileName) throws InvalidFileName, FileException {
		if (fileName.size() == 0) {
			throw new InvalidFileName();
		}
		final List<Node> nodes = this.mounts.get(fileName.get(0));
		if (fileName.size() == 1) {
			throw new FileException(ErrorNumberType.CF_EIO, "File already exists");
		} else {
			if (nodes == null) {
				Directory subDir = new Directory();
				this.mounts.put(fileName.get(0), Collections.singletonList((Node) subDir));
				return subDir.create(fileName.subList(1, fileName.size()));
			} else if (nodes.get(0) instanceof Directory) {
				return ((Directory) nodes.get(0)).create(fileName.subList(1, fileName.size()));
			} else if (nodes.get(0) instanceof MountPoint) {
				final StringBuilder builder = new StringBuilder();
				for (final String s : fileName.subList(1, fileName.size())) {
					builder.append('/');
					builder.append(s);
				}
				return ((MountPoint) nodes.get(0)).getFileSystem().create(builder.toString());
			} else {
				throw new UnsupportedOperationException();
			}
		}
	}

	public void mkdir(final List<String> name) throws InvalidFileName, FileException {
		if (name.size() == 0) {
			throw new InvalidFileName();
		}
		final List<Node> nodes = this.mounts.get(name.get(0));
		if (name.size() == 1) {
			if (nodes == null) {
				this.mounts.put(name.get(0), Collections.singletonList((Node) new Directory()));
			} else {
				throw new FileException(ErrorNumberType.CF_EIO, "File aleady exists");
			}
		} else {
			if (nodes == null) {
				Directory subDir = new Directory();
				this.mounts.put(name.get(0), Collections.singletonList((Node) subDir));
				subDir.mkdir(name.subList(1, name.size()));
			} else if (nodes.get(0) instanceof Directory) {
				((Directory) nodes.get(0)).mkdir(name.subList(1, name.size()));
			} else if (nodes.get(0) instanceof MountPoint) {
				final StringBuilder builder = new StringBuilder();
				for (final String s : name.subList(1, name.size())) {
					builder.append('/');
					builder.append(s);
				}
				((MountPoint) nodes.get(0)).getFileSystem().mkdir(builder.toString());
			}
		}
	}

	public void mount(final List<String> path, final FileSystem fileSystem) throws InvalidFileName, InvalidFileSystem, MountPointAlreadyExists {
		if (path.size() == 0) {
			throw new InvalidFileName(ErrorNumberType.CF_EIO, "Invalid path");
		}
		List<Node> nodes = this.mounts.get(path.get(0));
		if (path.size() == 1) {
			if (nodes == null) {
				nodes = new ArrayList<Node>();
				this.mounts.put(path.get(0), nodes);
			}
			try {
				nodes.add(new MountPoint(fileSystem));
			} catch (final Exception e) {
				throw new MountPointAlreadyExists(e.getMessage());
			}
		} else {
			if (nodes == null) {
				Directory subDir = new Directory();
				this.mounts.put(path.get(0), Collections.singletonList((Node) subDir));
				subDir.mount(path.subList(1, path.size()), fileSystem);
			} else if (nodes.get(0) instanceof Directory) {
				((Directory) nodes.get(0)).mount(path.subList(1, path.size()), fileSystem);
			} else {
				throw new MountPointAlreadyExists(path.toString());
			}
		}
	}

	public void unmount(final List<String> path) throws NonExistentMount {
		if (path.size() == 0) {
			throw new NonExistentMount("");
		}
		final List< ? extends Node> nodes = this.mounts.get(path.get(0));
		if (path.size() == 1) {
			final Object mp = this.mounts.remove(path.get(0));
			if (mp == null) {
				throw new NonExistentMount("");
			}
		} else {
			if (nodes.get(0) instanceof Directory) {
				final Directory d = (Directory) nodes.get(0);
				d.unmount(path.subList(1, path.size()));
			} else {
				throw new NonExistentMount("");
			}
		}
	}

	public List<MountType> getMounts() {
		final List<MountType> retVal = new ArrayList<MountType>();
		for (final Entry<String, List<Node>> entry : this.mounts.entrySet()) {
			if (entry.getValue() instanceof Directory) {
				final Directory d = (Directory) entry.getValue();
				for (final MountType t : d.getMounts()) {
					t.mountPoint = "/" + entry.getKey() + t.mountPoint;
					retVal.add(t);
				}
			} else if (entry.getValue() instanceof MountPoint) {
				final MountPoint mp = (MountPoint) entry.getValue();
				retVal.add(new MountType("/" + entry.getKey(), mp.getFileSystem()));
			}
		}
		return retVal;
	}

	public boolean exists(final List<String> fileName) throws InvalidFileName {
		if (fileName.size() == 0) {
			return true;
		}
		final List< ? extends Node> nodes = this.mounts.get(fileName.get(0));
		if (fileName.size() == 1) {
			return nodes != null;
		} else {
			final List<String> subList = fileName.subList(1, fileName.size());
			if (nodes.get(0) instanceof Directory) {
				return ((Directory) nodes.get(0)).exists(subList);
			} else if (nodes.get(0) instanceof MountPoint) {
				final StringBuilder builder = new StringBuilder();
				for (final String s : subList) {
					builder.append('/');
					builder.append(s);
				}
				return ((MountPoint) nodes.get(0)).getFileSystem().exists(builder.toString());
			} else {
				throw new InvalidFileName(ErrorNumberType.CF_EIO, "");
			}
		}
	}

	public List<FileInformationType> list(final List<String> pattern) throws FileException, InvalidFileName {
		if (pattern.isEmpty()) {
			return Collections.singletonList(createFileInformationType());
		}
		final String regex = pattern.get(0).replaceAll("\\*", ".*");
		final List<FileInformationType> retVal = new ArrayList<FileInformationType>();
		for (final Entry<String, List<Node>> entry : this.mounts.entrySet()) {
			final Node node = entry.getValue().get(0);
			if (entry.getKey().matches(regex)) {
				if (pattern.size() == 1) {
					final FileInformationType value = node.createFileInformationType();
					value.name = entry.getKey();
					retVal.add(value);
				} else if (node instanceof Directory) {
					retVal.addAll(((Directory) node).list(pattern.subList(1, pattern.size())));
				} else if (node instanceof MountPoint) {
					final MountPoint point = (MountPoint) node;
					final FileSystem fs = point.getFileSystem();
					final StringBuilder str = new StringBuilder();
					for (final String s : pattern.subList(1, pattern.size())) {
						str.append('/');
						str.append(s);
					}
					retVal.addAll(Arrays.asList(fs.list(str.toString())));
				} else {
					throw new FileException(ErrorNumberType.CF_EIO, "File does not exist");
				}
			}
		}
		return retVal;
	}

	public File open(final List<String> fileName, final boolean readOnly) throws InvalidFileName, FileException {
		if (fileName.isEmpty() || fileName.size() == 1) {
			throw new FileException(ErrorNumberType.CF_EIO, "Can not open a directory");
		}
		final List< ? extends Node> nodes = this.mounts.get(fileName.get(0));
		if (nodes == null) {
			throw new FileException(ErrorNumberType.CF_EIO, "Invalid file to open");
		}
		final Node node = nodes.get(0);
		if (node instanceof MountPoint) {
			final MountPoint mp = (MountPoint) node;
			final List<String> subList = fileName.subList(1, fileName.size());
			final StringBuilder path = new StringBuilder();
			for (final Iterator<String> i = subList.iterator(); i.hasNext();) {
				path.append("/");
				path.append(i.next());
			}
			return mp.getFileSystem().open(path.toString(), readOnly);
		} else if (node instanceof Directory) {
			final Directory d = (Directory) node;
			return d.open(fileName.subList(1, fileName.size()), readOnly);
		} else {
			throw new FileException(ErrorNumberType.CF_EIO, "Invalid file to open");
		}
	}

	public DataType[] createDataTypeArray() {
		final Any readOnly = AnyUtils.toAny(true, TCKind.tk_boolean);

		return new DataType[] { new DataType(ScaFileInformationDataType.READ_ONLY.name(), readOnly) };

	}

	public FileInformationType createFileInformationType() {
		final FileInformationType info = new FileInformationType();
		info.fileProperties = createDataTypeArray();
		info.kind = FileType.DIRECTORY;
		//		info.name = name;

		return info;
	}

}

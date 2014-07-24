package CF.jni;
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

public class _FileManagerStub extends omnijni.ObjectImpl implements CF.FileManager
{
  public _FileManagerStub ()
  {
  }

  protected _FileManagerStub (long ref)
  {
    super(ref);
  }

  public void remove (String fileName)
  {
    remove(this.ref_, fileName);
  }
  private static native void remove (long __ref__, String fileName);

  public void copy (String sourceFileName, String destinationFileName)
  {
    copy(this.ref_, sourceFileName, destinationFileName);
  }
  private static native void copy (long __ref__, String sourceFileName, String destinationFileName);

  public void move (String sourceFileName, String destinationFileName)
  {
    move(this.ref_, sourceFileName, destinationFileName);
  }
  private static native void move (long __ref__, String sourceFileName, String destinationFileName);

  public boolean exists (String fileName)
  {
    return exists(this.ref_, fileName);
  }
  private static native boolean exists (long __ref__, String fileName);

  public CF.FileSystemPackage.FileInformationType[] list (String pattern)
  {
    return list(this.ref_, pattern);
  }
  private static native CF.FileSystemPackage.FileInformationType[] list (long __ref__, String pattern);

  public CF.File create (String fileName)
  {
    return create(this.ref_, fileName);
  }
  private static native CF.File create (long __ref__, String fileName);

  public CF.File open (String fileName, boolean read_Only)
  {
    return open(this.ref_, fileName, read_Only);
  }
  private static native CF.File open (long __ref__, String fileName, boolean read_Only);

  public void mkdir (String directoryName)
  {
    mkdir(this.ref_, directoryName);
  }
  private static native void mkdir (long __ref__, String directoryName);

  public void rmdir (String directoryName)
  {
    rmdir(this.ref_, directoryName);
  }
  private static native void rmdir (long __ref__, String directoryName);

  public void query (CF.PropertiesHolder fileSystemProperties)
  {
    query(this.ref_, fileSystemProperties);
  }
  private static native void query (long __ref__, CF.PropertiesHolder fileSystemProperties);

  public void mount (String mountPoint, CF.FileSystem file_System)
  {
    mount(this.ref_, mountPoint, file_System);
  }
  private static native void mount (long __ref__, String mountPoint, CF.FileSystem file_System);

  public void unmount (String mountPoint)
  {
    unmount(this.ref_, mountPoint);
  }
  private static native void unmount (long __ref__, String mountPoint);

  public CF.FileManagerPackage.MountType[] getMounts ()
  {
    return getMounts(this.ref_);
  }
  private static native CF.FileManagerPackage.MountType[] getMounts (long __ref__);

  private static String __ids[] = {
    "IDL:CF/FileManager:1.0",
    "IDL:CF/FileSystem:1.0",
  };

  public String[] _ids ()
  {
    return (String[])__ids.clone();
  }

  static {
    System.loadLibrary("ossiecfjni");
  }

  protected native long _get_object_ref(long ref);
  protected native long _narrow_object_ref(long ref);
}

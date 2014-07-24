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

public class _FileStub extends omnijni.ObjectImpl implements CF.File
{
  public _FileStub ()
  {
  }

  protected _FileStub (long ref)
  {
    super(ref);
  }

  public String fileName ()
  {
    return _get_fileName(this.ref_);
  }
  private static native String _get_fileName (long __ref__);

  public int filePointer ()
  {
    return _get_filePointer(this.ref_);
  }
  private static native int _get_filePointer (long __ref__);

  public void read (CF.OctetSequenceHolder data, int length)
  {
    read(this.ref_, data, length);
  }
  private static native void read (long __ref__, CF.OctetSequenceHolder data, int length);

  public void write (byte[] data)
  {
    write(this.ref_, data);
  }
  private static native void write (long __ref__, byte[] data);

  public int sizeOf ()
  {
    return sizeOf(this.ref_);
  }
  private static native int sizeOf (long __ref__);

  public void close ()
  {
    close(this.ref_);
  }
  private static native void close (long __ref__);

  public void setFilePointer (int filePointer)
  {
    setFilePointer(this.ref_, filePointer);
  }
  private static native void setFilePointer (long __ref__, int filePointer);

  private static String __ids[] = {
    "IDL:CF/File:1.0",
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

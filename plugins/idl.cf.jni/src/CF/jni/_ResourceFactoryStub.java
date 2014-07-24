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

public class _ResourceFactoryStub extends omnijni.ObjectImpl implements CF.ResourceFactory
{
  public _ResourceFactoryStub ()
  {
  }

  protected _ResourceFactoryStub (long ref)
  {
    super(ref);
  }

  public String identifier ()
  {
    return _get_identifier(this.ref_);
  }
  private static native String _get_identifier (long __ref__);

  public CF.Resource createResource (String resourceId, CF.DataType[] qualifiers)
  {
    return createResource(this.ref_, resourceId, qualifiers);
  }
  private static native CF.Resource createResource (long __ref__, String resourceId, CF.DataType[] qualifiers);

  public void releaseResource (String resourceId)
  {
    releaseResource(this.ref_, resourceId);
  }
  private static native void releaseResource (long __ref__, String resourceId);

  public void shutdown ()
  {
    shutdown(this.ref_);
  }
  private static native void shutdown (long __ref__);

  private static String __ids[] = {
    "IDL:CF/ResourceFactory:1.0",
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

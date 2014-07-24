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

public class _PropertySetStub extends omnijni.ObjectImpl implements CF.PropertySet
{
  public _PropertySetStub ()
  {
  }

  protected _PropertySetStub (long ref)
  {
    super(ref);
  }

  public void configure (CF.DataType[] configProperties)
  {
    configure(this.ref_, configProperties);
  }
  private static native void configure (long __ref__, CF.DataType[] configProperties);

  public void query (CF.PropertiesHolder configProperties)
  {
    query(this.ref_, configProperties);
  }
  private static native void query (long __ref__, CF.PropertiesHolder configProperties);

  private static String __ids[] = {
    "IDL:CF/PropertySet:1.0",
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

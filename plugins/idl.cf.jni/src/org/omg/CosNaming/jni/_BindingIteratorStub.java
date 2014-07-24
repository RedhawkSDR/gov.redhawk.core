package org.omg.CosNaming.jni;
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

public class _BindingIteratorStub extends omnijni.ObjectImpl implements org.omg.CosNaming.BindingIterator
{
  public _BindingIteratorStub ()
  {
  }

  protected _BindingIteratorStub (long ref)
  {
    super(ref);
  }

  public boolean next_one (org.omg.CosNaming.BindingHolder b)
  {
    return next_one(this.ref_, b);
  }
  private static native boolean next_one (long __ref__, org.omg.CosNaming.BindingHolder b);

  public boolean next_n (int how_many, org.omg.CosNaming.BindingListHolder bl)
  {
    return next_n(this.ref_, how_many, bl);
  }
  private static native boolean next_n (long __ref__, int how_many, org.omg.CosNaming.BindingListHolder bl);

  public void destroy ()
  {
    destroy(this.ref_);
  }
  private static native void destroy (long __ref__);

  private static String __ids[] = {
    "IDL:omg.org/CosNaming/BindingIterator:1.0",
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

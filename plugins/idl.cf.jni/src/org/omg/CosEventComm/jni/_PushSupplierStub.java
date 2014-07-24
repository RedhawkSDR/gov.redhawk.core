package org.omg.CosEventComm.jni;
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

public class _PushSupplierStub extends omnijni.ObjectImpl implements org.omg.CosEventComm.PushSupplier
{
  public _PushSupplierStub ()
  {
  }

  protected _PushSupplierStub (long ref)
  {
    super(ref);
  }

  public void disconnect_push_supplier ()
  {
    disconnect_push_supplier(this.ref_);
  }
  private static native void disconnect_push_supplier (long __ref__);

  private static String __ids[] = {
    "IDL:omg.org/CosEventComm/PushSupplier:1.0",
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

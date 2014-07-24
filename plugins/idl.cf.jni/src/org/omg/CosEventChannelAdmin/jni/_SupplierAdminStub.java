package org.omg.CosEventChannelAdmin.jni;
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

public class _SupplierAdminStub extends omnijni.ObjectImpl implements org.omg.CosEventChannelAdmin.SupplierAdmin
{
  public _SupplierAdminStub ()
  {
  }

  protected _SupplierAdminStub (long ref)
  {
    super(ref);
  }

  public org.omg.CosEventChannelAdmin.ProxyPushConsumer obtain_push_consumer ()
  {
    return obtain_push_consumer(this.ref_);
  }
  private static native org.omg.CosEventChannelAdmin.ProxyPushConsumer obtain_push_consumer (long __ref__);

  public org.omg.CosEventChannelAdmin.ProxyPullConsumer obtain_pull_consumer ()
  {
    return obtain_pull_consumer(this.ref_);
  }
  private static native org.omg.CosEventChannelAdmin.ProxyPullConsumer obtain_pull_consumer (long __ref__);

  private static String __ids[] = {
    "IDL:omg.org/CosEventChannelAdmin/SupplierAdmin:1.0",
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

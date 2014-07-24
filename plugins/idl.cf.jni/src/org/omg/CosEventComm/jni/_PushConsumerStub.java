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

public class _PushConsumerStub extends omnijni.ObjectImpl implements org.omg.CosEventComm.PushConsumer
{
  public _PushConsumerStub ()
  {
  }

  protected _PushConsumerStub (long ref)
  {
    super(ref);
  }

  public void push (org.omg.CORBA.Any data)
  {
    push(this.ref_, data);
  }
  private static native void push (long __ref__, org.omg.CORBA.Any data);

  public void disconnect_push_consumer ()
  {
    disconnect_push_consumer(this.ref_);
  }
  private static native void disconnect_push_consumer (long __ref__);

  private static String __ids[] = {
    "IDL:omg.org/CosEventComm/PushConsumer:1.0",
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

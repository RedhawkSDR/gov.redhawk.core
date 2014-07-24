package ExtendedEvent.jni;
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

public class _MessageEventStub extends omnijni.ObjectImpl implements ExtendedEvent.MessageEvent
{
  public _MessageEventStub ()
  {
  }

  protected _MessageEventStub (long ref)
  {
    super(ref);
  }

  public org.omg.CosEventChannelAdmin.ConsumerAdmin for_consumers ()
  {
    return for_consumers(this.ref_);
  }
  private static native org.omg.CosEventChannelAdmin.ConsumerAdmin for_consumers (long __ref__);

  public org.omg.CosEventChannelAdmin.SupplierAdmin for_suppliers ()
  {
    return for_suppliers(this.ref_);
  }
  private static native org.omg.CosEventChannelAdmin.SupplierAdmin for_suppliers (long __ref__);

  public void destroy ()
  {
    destroy(this.ref_);
  }
  private static native void destroy (long __ref__);

  public void connectPort (org.omg.CORBA.Object connection, String connectionId)
  {
    connectPort(this.ref_, connection, connectionId);
  }
  private static native void connectPort (long __ref__, org.omg.CORBA.Object connection, String connectionId);

  public void disconnectPort (String connectionId)
  {
    disconnectPort(this.ref_, connectionId);
  }
  private static native void disconnectPort (long __ref__, String connectionId);

  private static String __ids[] = {
    "IDL:ExtendedEvent/MessageEvent:1.0",
    "IDL:omg.org/CosEventChannelAdmin/EventChannel:1.0",
    "IDL:CF/Port:1.0",
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

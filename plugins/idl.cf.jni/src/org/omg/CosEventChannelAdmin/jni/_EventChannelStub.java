package org.omg.CosEventChannelAdmin.jni;

public class _EventChannelStub extends omnijni.ObjectImpl implements org.omg.CosEventChannelAdmin.EventChannel
{
  public _EventChannelStub ()
  {
  }

  protected _EventChannelStub (long ref)
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

  private static String __ids[] = {
    "IDL:omg.org/CosEventChannelAdmin/EventChannel:1.0",
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

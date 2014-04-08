package org.omg.CosEventComm.jni;

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

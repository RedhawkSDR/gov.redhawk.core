package org.omg.CosEventChannelAdmin.jni;

public class _ConsumerAdminStub extends omnijni.ObjectImpl implements org.omg.CosEventChannelAdmin.ConsumerAdmin
{
  public _ConsumerAdminStub ()
  {
  }

  protected _ConsumerAdminStub (long ref)
  {
    super(ref);
  }

  public org.omg.CosEventChannelAdmin.ProxyPushSupplier obtain_push_supplier ()
  {
    return obtain_push_supplier(this.ref_);
  }
  private static native org.omg.CosEventChannelAdmin.ProxyPushSupplier obtain_push_supplier (long __ref__);

  public org.omg.CosEventChannelAdmin.ProxyPullSupplier obtain_pull_supplier ()
  {
    return obtain_pull_supplier(this.ref_);
  }
  private static native org.omg.CosEventChannelAdmin.ProxyPullSupplier obtain_pull_supplier (long __ref__);

  private static String __ids[] = {
    "IDL:omg.org/CosEventChannelAdmin/ConsumerAdmin:1.0",
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

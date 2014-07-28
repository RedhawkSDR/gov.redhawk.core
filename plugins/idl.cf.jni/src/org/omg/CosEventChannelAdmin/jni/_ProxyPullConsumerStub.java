package org.omg.CosEventChannelAdmin.jni;

public class _ProxyPullConsumerStub extends omnijni.ObjectImpl implements org.omg.CosEventChannelAdmin.ProxyPullConsumer
{
  public _ProxyPullConsumerStub ()
  {
  }

  protected _ProxyPullConsumerStub (long ref)
  {
    super(ref);
  }

  public void disconnect_pull_consumer ()
  {
    disconnect_pull_consumer(this.ref_);
  }
  private static native void disconnect_pull_consumer (long __ref__);

  public void connect_pull_supplier (org.omg.CosEventComm.PullSupplier pull_supplier)
  {
    connect_pull_supplier(this.ref_, pull_supplier);
  }
  private static native void connect_pull_supplier (long __ref__, org.omg.CosEventComm.PullSupplier pull_supplier);

  private static String __ids[] = {
    "IDL:omg.org/CosEventChannelAdmin/ProxyPullConsumer:1.0",
    "IDL:omg.org/CosEventComm/PullConsumer:1.0",
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

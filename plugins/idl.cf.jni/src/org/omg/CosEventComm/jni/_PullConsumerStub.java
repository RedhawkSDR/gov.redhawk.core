package org.omg.CosEventComm.jni;

public class _PullConsumerStub extends omnijni.ObjectImpl implements org.omg.CosEventComm.PullConsumer
{
  public _PullConsumerStub ()
  {
  }

  protected _PullConsumerStub (long ref)
  {
    super(ref);
  }

  public void disconnect_pull_consumer ()
  {
    disconnect_pull_consumer(this.ref_);
  }
  private static native void disconnect_pull_consumer (long __ref__);

  private static String __ids[] = {
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

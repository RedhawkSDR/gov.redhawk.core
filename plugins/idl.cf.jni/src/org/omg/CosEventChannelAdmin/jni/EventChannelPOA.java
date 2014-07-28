package org.omg.CosEventChannelAdmin.jni;

public abstract class EventChannelPOA extends omnijni.Servant implements org.omg.CosEventChannelAdmin.EventChannelOperations
{
  public EventChannelPOA ()
  {
  }

  public org.omg.CORBA.Object _this_object (org.omg.CORBA.ORB orb)
  {
    this._activate();
    long ref = EventChannelPOA.new_reference(this.servant_);
    org.omg.CosEventChannelAdmin.jni._EventChannelStub stub = new org.omg.CosEventChannelAdmin.jni._EventChannelStub(ref);
    String ior = omnijni.ORB.object_to_string(stub);
    return orb.string_to_object(ior);
  }

  public synchronized void _activate ()
  {
    if (this.servant_ == 0) {
      this.servant_ = EventChannelPOA.new_servant();
      set_delegate(this.servant_, this);
    }
  }

  public synchronized void _deactivate ()
  {
    if (this.servant_ != 0) {
      EventChannelPOA.del_servant(this.servant_);
      this.servant_ = 0;
    }
  }

  static {
    System.loadLibrary("ossiecfjni");
  }

  private static native long new_servant();
  private static native long del_servant(long servant);
  private static native long new_reference(long servant);
  private static native void set_delegate (long servant, org.omg.CosEventChannelAdmin.EventChannelOperations delegate);
  private long servant_;
}

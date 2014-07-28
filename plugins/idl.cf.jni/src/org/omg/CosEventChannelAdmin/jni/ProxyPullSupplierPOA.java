package org.omg.CosEventChannelAdmin.jni;

public abstract class ProxyPullSupplierPOA extends omnijni.Servant implements org.omg.CosEventChannelAdmin.ProxyPullSupplierOperations
{
  public ProxyPullSupplierPOA ()
  {
  }

  public org.omg.CORBA.Object _this_object (org.omg.CORBA.ORB orb)
  {
    this._activate();
    long ref = ProxyPullSupplierPOA.new_reference(this.servant_);
    org.omg.CosEventChannelAdmin.jni._ProxyPullSupplierStub stub = new org.omg.CosEventChannelAdmin.jni._ProxyPullSupplierStub(ref);
    String ior = omnijni.ORB.object_to_string(stub);
    return orb.string_to_object(ior);
  }

  public synchronized void _activate ()
  {
    if (this.servant_ == 0) {
      this.servant_ = ProxyPullSupplierPOA.new_servant();
      set_delegate(this.servant_, this);
    }
  }

  public synchronized void _deactivate ()
  {
    if (this.servant_ != 0) {
      ProxyPullSupplierPOA.del_servant(this.servant_);
      this.servant_ = 0;
    }
  }

  static {
    System.loadLibrary("ossiecfjni");
  }

  private static native long new_servant();
  private static native long del_servant(long servant);
  private static native long new_reference(long servant);
  private static native void set_delegate (long servant, org.omg.CosEventChannelAdmin.ProxyPullSupplierOperations delegate);
  private long servant_;
}

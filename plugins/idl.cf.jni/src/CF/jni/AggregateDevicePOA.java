package CF.jni;

public abstract class AggregateDevicePOA extends omnijni.Servant implements CF.AggregateDeviceOperations
{
  public AggregateDevicePOA ()
  {
  }

  public org.omg.CORBA.Object _this_object (org.omg.CORBA.ORB orb)
  {
    this._activate();
    long ref = AggregateDevicePOA.new_reference(this.servant_);
    CF.jni._AggregateDeviceStub stub = new CF.jni._AggregateDeviceStub(ref);
    String ior = omnijni.ORB.object_to_string(stub);
    return orb.string_to_object(ior);
  }

  public synchronized void _activate ()
  {
    if (this.servant_ == 0) {
      this.servant_ = AggregateDevicePOA.new_servant();
      set_delegate(this.servant_, this);
    }
  }

  public synchronized void _deactivate ()
  {
    if (this.servant_ != 0) {
      AggregateDevicePOA.del_servant(this.servant_);
      this.servant_ = 0;
    }
  }

  static {
    System.loadLibrary("ossiecfjni");
  }

  private static native long new_servant();
  private static native long del_servant(long servant);
  private static native long new_reference(long servant);
  private static native void set_delegate (long servant, CF.AggregateDeviceOperations delegate);
  private long servant_;
}

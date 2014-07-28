package BULKIO.jni;

public abstract class dataVITA49POA extends omnijni.Servant implements BULKIO.dataVITA49Operations
{
  public dataVITA49POA ()
  {
  }

  public org.omg.CORBA.Object _this_object (org.omg.CORBA.ORB orb)
  {
    this._activate();
    long ref = dataVITA49POA.new_reference(this.servant_);
    BULKIO.jni._dataVITA49Stub stub = new BULKIO.jni._dataVITA49Stub(ref);
    String ior = omnijni.ORB.object_to_string(stub);
    return orb.string_to_object(ior);
  }

  public synchronized void _activate ()
  {
    if (this.servant_ == 0) {
      this.servant_ = dataVITA49POA.new_servant();
      set_delegate(this.servant_, this);
    }
  }

  public synchronized void _deactivate ()
  {
    if (this.servant_ != 0) {
      dataVITA49POA.del_servant(this.servant_);
      this.servant_ = 0;
    }
  }

  static {
    System.loadLibrary("bulkiojni");
  }

  private static native long new_servant();
  private static native long del_servant(long servant);
  private static native long new_reference(long servant);
  private static native void set_delegate (long servant, BULKIO.dataVITA49Operations delegate);
  private long servant_;
}

package BULKIO.jni;

public abstract class dataCharPOA extends omnijni.Servant implements BULKIO.dataCharOperations
{
  public dataCharPOA ()
  {
  }

  public org.omg.CORBA.Object _this_object (org.omg.CORBA.ORB orb)
  {
    this._activate();
    long ref = dataCharPOA.new_reference(this.servant_);
    BULKIO.jni._dataCharStub stub = new BULKIO.jni._dataCharStub(ref);
    String ior = omnijni.ORB.object_to_string(stub);
    return orb.string_to_object(ior);
  }

  public synchronized void _activate ()
  {
    if (this.servant_ == 0) {
      this.servant_ = dataCharPOA.new_servant();
      set_delegate(this.servant_, this);
    }
  }

  public synchronized void _deactivate ()
  {
    if (this.servant_ != 0) {
      dataCharPOA.del_servant(this.servant_);
      this.servant_ = 0;
    }
  }

  static {
    System.loadLibrary("bulkiojni");
  }

  private static native long new_servant();
  private static native long del_servant(long servant);
  private static native long new_reference(long servant);
  private static native void set_delegate (long servant, BULKIO.dataCharOperations delegate);
  private long servant_;
}

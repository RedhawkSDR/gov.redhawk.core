package ExtendedCF.jni;

public abstract class NegotiableProvidesPortPOA extends omnijni.Servant implements ExtendedCF.NegotiableProvidesPortOperations
{
  public NegotiableProvidesPortPOA ()
  {
  }

  public org.omg.CORBA.Object _this_object (org.omg.CORBA.ORB orb)
  {
    this._activate();
    long ref = NegotiableProvidesPortPOA.new_reference(this.servant_);
    ExtendedCF.jni._NegotiableProvidesPortStub stub = new ExtendedCF.jni._NegotiableProvidesPortStub(ref);
    String ior = omnijni.ORB.object_to_string(stub);
    return orb.string_to_object(ior);
  }

  public synchronized void _activate ()
  {
    if (this.servant_ == 0) {
      this.servant_ = NegotiableProvidesPortPOA.new_servant();
      set_delegate(this.servant_, this);
    }
  }

  public synchronized void _deactivate ()
  {
    if (this.servant_ != 0) {
      NegotiableProvidesPortPOA.del_servant(this.servant_);
      this.servant_ = 0;
    }
  }

  static {
    System.loadLibrary("ossiecfjni");
  }

  private static native long new_servant();
  private static native long del_servant(long servant);
  private static native long new_reference(long servant);
  private static native void set_delegate (long servant, ExtendedCF.NegotiableProvidesPortOperations delegate);
  private long servant_;
}

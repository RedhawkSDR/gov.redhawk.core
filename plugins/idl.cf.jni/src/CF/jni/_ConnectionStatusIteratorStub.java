package CF.jni;

public class _ConnectionStatusIteratorStub extends omnijni.ObjectImpl implements CF.ConnectionStatusIterator
{
  public _ConnectionStatusIteratorStub ()
  {
  }

  protected _ConnectionStatusIteratorStub (long ref)
  {
    super(ref);
  }

  public boolean next_one (CF.ConnectionManagerPackage.ConnectionStatusTypeHolder connection)
  {
    return next_one(this.ref_, connection);
  }
  private static native boolean next_one (long __ref__, CF.ConnectionManagerPackage.ConnectionStatusTypeHolder connection);

  public boolean next_n (int count, CF.ConnectionManagerPackage.ConnectionStatusSequenceHolder connections)
  {
    return next_n(this.ref_, count, connections);
  }
  private static native boolean next_n (long __ref__, int count, CF.ConnectionManagerPackage.ConnectionStatusSequenceHolder connections);

  public void destroy ()
  {
    destroy(this.ref_);
  }
  private static native void destroy (long __ref__);

  private static String __ids[] = {
    "IDL:CF/ConnectionStatusIterator:1.0",
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

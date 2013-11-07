package CF.jni;

public class _LifeCycleStub extends omnijni.ObjectImpl implements CF.LifeCycle
{
  public _LifeCycleStub ()
  {
  }

  protected _LifeCycleStub (long ref)
  {
    super(ref);
  }

  public void initialize ()
  {
    initialize(this.ref_);
  }
  private static native void initialize (long __ref__);

  public void releaseObject ()
  {
    releaseObject(this.ref_);
  }
  private static native void releaseObject (long __ref__);

  private static String __ids[] = {
    "IDL:CF/LifeCycle:1.0",
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

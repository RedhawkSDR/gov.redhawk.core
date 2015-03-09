package CF.jni;

public class _EventChannelInfoIteratorStub extends omnijni.ObjectImpl implements CF.EventChannelInfoIterator
{
  public _EventChannelInfoIteratorStub ()
  {
  }

  protected _EventChannelInfoIteratorStub (long ref)
  {
    super(ref);
  }

  public boolean next_one (CF.EventChannelManagerPackage.EventChannelInfoHolder eci)
  {
    return next_one(this.ref_, eci);
  }
  private static native boolean next_one (long __ref__, CF.EventChannelManagerPackage.EventChannelInfoHolder eci);

  public boolean next_n (int how_many, CF.EventChannelManagerPackage.EventChannelInfoListHolder ecil)
  {
    return next_n(this.ref_, how_many, ecil);
  }
  private static native boolean next_n (long __ref__, int how_many, CF.EventChannelManagerPackage.EventChannelInfoListHolder ecil);

  public void destroy ()
  {
    destroy(this.ref_);
  }
  private static native void destroy (long __ref__);

  private static String __ids[] = {
    "IDL:CF/EventChannelInfoIterator:1.0",
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

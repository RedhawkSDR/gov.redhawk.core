package CF.jni;

public class _DeviceLocationIteratorStub extends omnijni.ObjectImpl implements CF.DeviceLocationIterator
{
  public _DeviceLocationIteratorStub ()
  {
  }

  protected _DeviceLocationIteratorStub (long ref)
  {
    super(ref);
  }

  public boolean next_one (CF.AllocationManagerPackage.DeviceLocationTypeHolder deviceLocation)
  {
    return next_one(this.ref_, deviceLocation);
  }
  private static native boolean next_one (long __ref__, CF.AllocationManagerPackage.DeviceLocationTypeHolder deviceLocation);

  public boolean next_n (int count, CF.AllocationManagerPackage.DeviceLocationSequenceHolder deviceLocations)
  {
    return next_n(this.ref_, count, deviceLocations);
  }
  private static native boolean next_n (long __ref__, int count, CF.AllocationManagerPackage.DeviceLocationSequenceHolder deviceLocations);

  public void destroy ()
  {
    destroy(this.ref_);
  }
  private static native void destroy (long __ref__);

  private static String __ids[] = {
    "IDL:CF/DeviceLocationIterator:1.0",
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

package CF.jni;

public class _AllocationStatusIteratorStub extends omnijni.ObjectImpl implements CF.AllocationStatusIterator
{
  public _AllocationStatusIteratorStub ()
  {
  }

  protected _AllocationStatusIteratorStub (long ref)
  {
    super(ref);
  }

  public boolean next_one (CF.AllocationManagerPackage.AllocationStatusTypeHolder allocation)
  {
    return next_one(this.ref_, allocation);
  }
  private static native boolean next_one (long __ref__, CF.AllocationManagerPackage.AllocationStatusTypeHolder allocation);

  public boolean next_n (int count, CF.AllocationManagerPackage.AllocationStatusSequenceHolder allocations)
  {
    return next_n(this.ref_, count, allocations);
  }
  private static native boolean next_n (long __ref__, int count, CF.AllocationManagerPackage.AllocationStatusSequenceHolder allocations);

  public void destroy ()
  {
    destroy(this.ref_);
  }
  private static native void destroy (long __ref__);

  private static String __ids[] = {
    "IDL:CF/AllocationStatusIterator:1.0",
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

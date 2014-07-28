package CF.jni;

public class _AllocationManagerStub extends omnijni.ObjectImpl implements CF.AllocationManager
{
  public _AllocationManagerStub ()
  {
  }

  protected _AllocationManagerStub (long ref)
  {
    super(ref);
  }

  public CF.AllocationManagerPackage.DeviceLocationType[] allDevices ()
  {
    return _get_allDevices(this.ref_);
  }
  private static native CF.AllocationManagerPackage.DeviceLocationType[] _get_allDevices (long __ref__);

  public CF.AllocationManagerPackage.DeviceLocationType[] authorizedDevices ()
  {
    return _get_authorizedDevices(this.ref_);
  }
  private static native CF.AllocationManagerPackage.DeviceLocationType[] _get_authorizedDevices (long __ref__);

  public CF.AllocationManagerPackage.DeviceLocationType[] localDevices ()
  {
    return _get_localDevices(this.ref_);
  }
  private static native CF.AllocationManagerPackage.DeviceLocationType[] _get_localDevices (long __ref__);

  public CF.DomainManager domainMgr ()
  {
    return _get_domainMgr(this.ref_);
  }
  private static native CF.DomainManager _get_domainMgr (long __ref__);

  public CF.AllocationManagerPackage.AllocationResponseType[] allocate (CF.AllocationManagerPackage.AllocationRequestType[] requests)
  {
    return allocate(this.ref_, requests);
  }
  private static native CF.AllocationManagerPackage.AllocationResponseType[] allocate (long __ref__, CF.AllocationManagerPackage.AllocationRequestType[] requests);

  public CF.AllocationManagerPackage.AllocationResponseType[] allocateLocal (CF.AllocationManagerPackage.AllocationRequestType[] requests, String domainName)
  {
    return allocateLocal(this.ref_, requests, domainName);
  }
  private static native CF.AllocationManagerPackage.AllocationResponseType[] allocateLocal (long __ref__, CF.AllocationManagerPackage.AllocationRequestType[] requests, String domainName);

  public void deallocate (String[] allocationIDs)
  {
    deallocate(this.ref_, allocationIDs);
  }
  private static native void deallocate (long __ref__, String[] allocationIDs);

  public CF.AllocationManagerPackage.AllocationStatusType[] allocations (String[] allocationIDs)
  {
    return allocations(this.ref_, allocationIDs);
  }
  private static native CF.AllocationManagerPackage.AllocationStatusType[] allocations (long __ref__, String[] allocationIDs);

  public CF.AllocationManagerPackage.AllocationStatusType[] localAllocations (String[] allocationIDs)
  {
    return localAllocations(this.ref_, allocationIDs);
  }
  private static native CF.AllocationManagerPackage.AllocationStatusType[] localAllocations (long __ref__, String[] allocationIDs);

  private static String __ids[] = {
    "IDL:CF/AllocationManager:1.0",
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

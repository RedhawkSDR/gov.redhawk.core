package CF.jni;

public class _DeviceStub extends omnijni.ObjectImpl implements CF.Device
{
  public _DeviceStub ()
  {
  }

  protected _DeviceStub (long ref)
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

  public void runTest (int testid, CF.PropertiesHolder testValues)
  {
    runTest(this.ref_, testid, testValues);
  }
  private static native void runTest (long __ref__, int testid, CF.PropertiesHolder testValues);

  public void configure (CF.DataType[] configProperties)
  {
    configure(this.ref_, configProperties);
  }
  private static native void configure (long __ref__, CF.DataType[] configProperties);

  public void query (CF.PropertiesHolder configProperties)
  {
    query(this.ref_, configProperties);
  }
  private static native void query (long __ref__, CF.PropertiesHolder configProperties);

  public org.omg.CORBA.Object getPort (String name)
  {
    return getPort(this.ref_, name);
  }
  private static native org.omg.CORBA.Object getPort (long __ref__, String name);

  public String identifier ()
  {
    return _get_identifier(this.ref_);
  }
  private static native String _get_identifier (long __ref__);

  public boolean started ()
  {
    return _get_started(this.ref_);
  }
  private static native boolean _get_started (long __ref__);

  public String softwareProfile ()
  {
    return _get_softwareProfile(this.ref_);
  }
  private static native String _get_softwareProfile (long __ref__);

  public void start ()
  {
    start(this.ref_);
  }
  private static native void start (long __ref__);

  public void stop ()
  {
    stop(this.ref_);
  }
  private static native void stop (long __ref__);

  public CF.DevicePackage.UsageType usageState ()
  {
    return _get_usageState(this.ref_);
  }
  private static native CF.DevicePackage.UsageType _get_usageState (long __ref__);

  public CF.DevicePackage.AdminType adminState ()
  {
    return _get_adminState(this.ref_);
  }
  private static native CF.DevicePackage.AdminType _get_adminState (long __ref__);

  public void adminState (CF.DevicePackage.AdminType value)
  {
    _set_adminState(this.ref_, value);
  }
  private static native void _set_adminState (long __ref__, CF.DevicePackage.AdminType value);

  public CF.DevicePackage.OperationalType operationalState ()
  {
    return _get_operationalState(this.ref_);
  }
  private static native CF.DevicePackage.OperationalType _get_operationalState (long __ref__);

  public String label ()
  {
    return _get_label(this.ref_);
  }
  private static native String _get_label (long __ref__);

  public CF.AggregateDevice compositeDevice ()
  {
    return _get_compositeDevice(this.ref_);
  }
  private static native CF.AggregateDevice _get_compositeDevice (long __ref__);

  public boolean allocateCapacity (CF.DataType[] capacities)
  {
    return allocateCapacity(this.ref_, capacities);
  }
  private static native boolean allocateCapacity (long __ref__, CF.DataType[] capacities);

  public void deallocateCapacity (CF.DataType[] capacities)
  {
    deallocateCapacity(this.ref_, capacities);
  }
  private static native void deallocateCapacity (long __ref__, CF.DataType[] capacities);

  private static String __ids[] = {
    "IDL:CF/Device:1.0",
    "IDL:CF/Resource:1.0",
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

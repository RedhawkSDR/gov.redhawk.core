package CF.jni;

public class _AggregatePlainDeviceStub extends omnijni.ObjectImpl implements CF.AggregatePlainDevice
{
  public _AggregatePlainDeviceStub ()
  {
  }

  protected _AggregatePlainDeviceStub (long ref)
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

  public String registerPropertyListener (org.omg.CORBA.Object obj, String[] prop_ids, float interval)
  {
    return registerPropertyListener(this.ref_, obj, prop_ids, interval);
  }
  private static native String registerPropertyListener (long __ref__, org.omg.CORBA.Object obj, String[] prop_ids, float interval);

  public void unregisterPropertyListener (String id)
  {
    unregisterPropertyListener(this.ref_, id);
  }
  private static native void unregisterPropertyListener (long __ref__, String id);

  public org.omg.CORBA.Object getPort (String name)
  {
    return getPort(this.ref_, name);
  }
  private static native org.omg.CORBA.Object getPort (long __ref__, String name);

  public CF.LogEvent[] retrieve_records (org.omg.CORBA.IntHolder howMany, int startingRecord)
  {
    return retrieve_records(this.ref_, howMany, startingRecord);
  }
  private static native CF.LogEvent[] retrieve_records (long __ref__, org.omg.CORBA.IntHolder howMany, int startingRecord);

  public CF.LogEvent[] retrieve_records_by_date (org.omg.CORBA.IntHolder howMany, long to_timeStamp)
  {
    return retrieve_records_by_date(this.ref_, howMany, to_timeStamp);
  }
  private static native CF.LogEvent[] retrieve_records_by_date (long __ref__, org.omg.CORBA.IntHolder howMany, long to_timeStamp);

  public CF.LogEvent[] retrieve_records_from_date (org.omg.CORBA.IntHolder howMany, long from_timeStamp)
  {
    return retrieve_records_from_date(this.ref_, howMany, from_timeStamp);
  }
  private static native CF.LogEvent[] retrieve_records_from_date (long __ref__, org.omg.CORBA.IntHolder howMany, long from_timeStamp);

  public int log_level ()
  {
    return _get_log_level(this.ref_);
  }
  private static native int _get_log_level (long __ref__);

  public void log_level (int value)
  {
    _set_log_level(this.ref_, value);
  }
  private static native void _set_log_level (long __ref__, int value);

  public void setLogLevel (String logger_id, int newLevel)
  {
    setLogLevel(this.ref_, logger_id, newLevel);
  }
  private static native void setLogLevel (long __ref__, String logger_id, int newLevel);

  public String getLogConfig ()
  {
    return getLogConfig(this.ref_);
  }
  private static native String getLogConfig (long __ref__);

  public void setLogConfig (String config_contents)
  {
    setLogConfig(this.ref_, config_contents);
  }
  private static native void setLogConfig (long __ref__, String config_contents);

  public void setLogConfigURL (String config_url)
  {
    setLogConfigURL(this.ref_, config_url);
  }
  private static native void setLogConfigURL (long __ref__, String config_url);

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

  public CF.Device[] devices ()
  {
    return _get_devices(this.ref_);
  }
  private static native CF.Device[] _get_devices (long __ref__);

  public void addDevice (CF.Device associatedDevice)
  {
    addDevice(this.ref_, associatedDevice);
  }
  private static native void addDevice (long __ref__, CF.Device associatedDevice);

  public void removeDevice (CF.Device associatedDevice)
  {
    removeDevice(this.ref_, associatedDevice);
  }
  private static native void removeDevice (long __ref__, CF.Device associatedDevice);

  private static String __ids[] = {
    "IDL:CF/AggregatePlainDevice:1.0",
    "IDL:CF/Device:1.0",
    "IDL:CF/AggregateDevice:1.0",
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

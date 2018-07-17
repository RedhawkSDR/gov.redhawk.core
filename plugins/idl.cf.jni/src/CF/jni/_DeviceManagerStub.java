package CF.jni;

public class _DeviceManagerStub extends omnijni.ObjectImpl implements CF.DeviceManager
{
  public _DeviceManagerStub ()
  {
  }

  protected _DeviceManagerStub (long ref)
  {
    super(ref);
  }

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

  public void initializeProperties (CF.DataType[] initialProperties)
  {
    initializeProperties(this.ref_, initialProperties);
  }
  private static native void initializeProperties (long __ref__, CF.DataType[] initialProperties);

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

  public CF.PortSetPackage.PortInfoType[] getPortSet ()
  {
    return getPortSet(this.ref_);
  }
  private static native CF.PortSetPackage.PortInfoType[] getPortSet (long __ref__);

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

  public int getLogLevel (String logger_id)
  {
    return getLogLevel(this.ref_, logger_id);
  }
  private static native int getLogLevel (long __ref__, String logger_id);

  public void setLogLevel (String logger_id, int newLevel)
  {
    setLogLevel(this.ref_, logger_id, newLevel);
  }
  private static native void setLogLevel (long __ref__, String logger_id, int newLevel);

  public String[] getNamedLoggers ()
  {
    return getNamedLoggers(this.ref_);
  }
  private static native String[] getNamedLoggers (long __ref__);

  public void resetLog ()
  {
    resetLog(this.ref_);
  }
  private static native void resetLog (long __ref__);

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

  public String deviceConfigurationProfile ()
  {
    return _get_deviceConfigurationProfile(this.ref_);
  }
  private static native String _get_deviceConfigurationProfile (long __ref__);

  public CF.FileSystem fileSys ()
  {
    return _get_fileSys(this.ref_);
  }
  private static native CF.FileSystem _get_fileSys (long __ref__);

  public String identifier ()
  {
    return _get_identifier(this.ref_);
  }
  private static native String _get_identifier (long __ref__);

  public String label ()
  {
    return _get_label(this.ref_);
  }
  private static native String _get_label (long __ref__);

  public CF.DomainManager domMgr ()
  {
    return _get_domMgr(this.ref_);
  }
  private static native CF.DomainManager _get_domMgr (long __ref__);

  public CF.Device[] registeredDevices ()
  {
    return _get_registeredDevices(this.ref_);
  }
  private static native CF.Device[] _get_registeredDevices (long __ref__);

  public CF.DeviceManagerPackage.ServiceType[] registeredServices ()
  {
    return _get_registeredServices(this.ref_);
  }
  private static native CF.DeviceManagerPackage.ServiceType[] _get_registeredServices (long __ref__);

  public void registerDevice (CF.Device registeringDevice)
  {
    registerDevice(this.ref_, registeringDevice);
  }
  private static native void registerDevice (long __ref__, CF.Device registeringDevice);

  public void unregisterDevice (CF.Device registeredDevice)
  {
    unregisterDevice(this.ref_, registeredDevice);
  }
  private static native void unregisterDevice (long __ref__, CF.Device registeredDevice);

  public void shutdown ()
  {
    shutdown(this.ref_);
  }
  private static native void shutdown (long __ref__);

  public void registerService (org.omg.CORBA.Object registeringService, String name)
  {
    registerService(this.ref_, registeringService, name);
  }
  private static native void registerService (long __ref__, org.omg.CORBA.Object registeringService, String name);

  public void unregisterService (org.omg.CORBA.Object unregisteringService, String name)
  {
    unregisterService(this.ref_, unregisteringService, name);
  }
  private static native void unregisterService (long __ref__, org.omg.CORBA.Object unregisteringService, String name);

  public String getComponentImplementationId (String componentInstantiationId)
  {
    return getComponentImplementationId(this.ref_, componentInstantiationId);
  }
  private static native String getComponentImplementationId (long __ref__, String componentInstantiationId);

  private static String __ids[] = {
    "IDL:CF/DeviceManager:1.0",
    "IDL:CF/PropertyEmitter:1.0",
    "IDL:CF/PortSet:1.0",
    "IDL:CF/Logging:1.0",
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

package CF.jni;

public class _LoggingStub extends omnijni.ObjectImpl implements CF.Logging
{
  public _LoggingStub ()
  {
  }

  protected _LoggingStub (long ref)
  {
    super(ref);
  }

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

  private static String __ids[] = {
    "IDL:CF/Logging:1.0",
    "IDL:CF/LogEventConsumer:1.0",
    "IDL:CF/LogConfiguration:1.0",
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

package CF.jni;

public class _LogConfigurationStub extends omnijni.ObjectImpl implements CF.LogConfiguration
{
  public _LogConfigurationStub ()
  {
  }

  protected _LogConfigurationStub (long ref)
  {
    super(ref);
  }

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

  private static String __ids[] = {
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

package CF.jni;

public class _LogConfiguratorStub extends omnijni.ObjectImpl implements CF.LogConfigurator
{
  public _LogConfiguratorStub ()
  {
  }

  protected _LogConfiguratorStub (long ref)
  {
    super(ref);
  }

  public int getLogLevel (String config_id)
  {
    return getLogLevel(this.ref_, config_id);
  }
  private static native int getLogLevel (long __ref__, String config_id);

  public String getLogConfig (String config_id)
  {
    return getLogConfig(this.ref_, config_id);
  }
  private static native String getLogConfig (long __ref__, String config_id);

  private static String __ids[] = {
    "IDL:CF/LogConfigurator:1.0",
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

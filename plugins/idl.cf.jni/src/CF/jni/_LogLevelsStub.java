package CF.jni;

public class _LogLevelsStub extends omnijni.ObjectImpl implements CF.LogLevels
{
  public _LogLevelsStub ()
  {
  }

  protected _LogLevelsStub (long ref)
  {
    super(ref);
  }

  private static String __ids[] = {
    "IDL:CF/LogLevels:1.0",
    "IDL:CF/SysLogLevels:1.0",
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

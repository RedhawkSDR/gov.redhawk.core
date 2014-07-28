package CF.jni;

public class _SysLogLevelsStub extends omnijni.ObjectImpl implements CF.SysLogLevels
{
  public _SysLogLevelsStub ()
  {
  }

  protected _SysLogLevelsStub (long ref)
  {
    super(ref);
  }

  private static String __ids[] = {
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

package CF.jni;

public class _DeviceStatusStub extends omnijni.ObjectImpl implements CF.DeviceStatus
{
  public _DeviceStatusStub ()
  {
  }

  protected _DeviceStatusStub (long ref)
  {
    super(ref);
  }

  public void statusChanged (CF.DeviceStatusType status)
  {
    statusChanged(this.ref_, status);
  }
  private static native void statusChanged (long __ref__, CF.DeviceStatusType status);

  private static String __ids[] = {
    "IDL:CF/DeviceStatus:1.0",
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

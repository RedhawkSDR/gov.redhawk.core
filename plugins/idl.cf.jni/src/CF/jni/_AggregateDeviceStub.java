package CF.jni;

public class _AggregateDeviceStub extends omnijni.ObjectImpl implements CF.AggregateDevice
{
  public _AggregateDeviceStub ()
  {
  }

  protected _AggregateDeviceStub (long ref)
  {
    super(ref);
  }

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

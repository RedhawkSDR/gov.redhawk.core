package CF.jni;

public class _ApplicationFactoryStub extends omnijni.ObjectImpl implements CF.ApplicationFactory
{
  public _ApplicationFactoryStub ()
  {
  }

  protected _ApplicationFactoryStub (long ref)
  {
    super(ref);
  }

  public String name ()
  {
    return _get_name(this.ref_);
  }
  private static native String _get_name (long __ref__);

  public String identifier ()
  {
    return _get_identifier(this.ref_);
  }
  private static native String _get_identifier (long __ref__);

  public String softwareProfile ()
  {
    return _get_softwareProfile(this.ref_);
  }
  private static native String _get_softwareProfile (long __ref__);

  public CF.Application create (String name, CF.DataType[] initConfiguration, CF.DeviceAssignmentType[] deviceAssignments)
  {
    return create(this.ref_, name, initConfiguration, deviceAssignments);
  }
  private static native CF.Application create (long __ref__, String name, CF.DataType[] initConfiguration, CF.DeviceAssignmentType[] deviceAssignments);

  private static String __ids[] = {
    "IDL:CF/ApplicationFactory:1.0",
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

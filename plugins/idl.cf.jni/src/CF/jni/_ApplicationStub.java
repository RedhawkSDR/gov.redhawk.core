package CF.jni;

public class _ApplicationStub extends omnijni.ObjectImpl implements CF.Application
{
  public _ApplicationStub ()
  {
  }

  protected _ApplicationStub (long ref)
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

  public org.omg.CORBA.Object getPort (String name)
  {
    return getPort(this.ref_, name);
  }
  private static native org.omg.CORBA.Object getPort (long __ref__, String name);

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

  public CF.ComponentType[] registeredComponents ()
  {
    return _get_registeredComponents(this.ref_);
  }
  private static native CF.ComponentType[] _get_registeredComponents (long __ref__);

  public CF.ApplicationPackage.ComponentElementType[] componentNamingContexts ()
  {
    return _get_componentNamingContexts(this.ref_);
  }
  private static native CF.ApplicationPackage.ComponentElementType[] _get_componentNamingContexts (long __ref__);

  public CF.ApplicationPackage.ComponentProcessIdType[] componentProcessIds ()
  {
    return _get_componentProcessIds(this.ref_);
  }
  private static native CF.ApplicationPackage.ComponentProcessIdType[] _get_componentProcessIds (long __ref__);

  public CF.DeviceAssignmentType[] componentDevices ()
  {
    return _get_componentDevices(this.ref_);
  }
  private static native CF.DeviceAssignmentType[] _get_componentDevices (long __ref__);

  public CF.ApplicationPackage.ComponentElementType[] componentImplementations ()
  {
    return _get_componentImplementations(this.ref_);
  }
  private static native CF.ApplicationPackage.ComponentElementType[] _get_componentImplementations (long __ref__);

  public String profile ()
  {
    return _get_profile(this.ref_);
  }
  private static native String _get_profile (long __ref__);

  public String name ()
  {
    return _get_name(this.ref_);
  }
  private static native String _get_name (long __ref__);

  private static String __ids[] = {
    "IDL:CF/Application:1.0",
    "IDL:CF/Resource:1.0",
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

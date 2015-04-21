package CF.jni;

public class _PortSupplierStub extends omnijni.ObjectImpl implements CF.PortSupplier
{
  public _PortSupplierStub ()
  {
  }

  protected _PortSupplierStub (long ref)
  {
    super(ref);
  }

  public org.omg.CORBA.Object getPort (String name)
  {
    return getPort(this.ref_, name);
  }
  private static native org.omg.CORBA.Object getPort (long __ref__, String name);

  public CF.PortSupplierPackage.PortInfoType[] getPortSet ()
  {
    return getPortSet(this.ref_);
  }
  private static native CF.PortSupplierPackage.PortInfoType[] getPortSet (long __ref__);

  private static String __ids[] = {
    "IDL:CF/PortSupplier:1.0",
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

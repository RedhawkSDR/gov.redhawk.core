package ExtendedCF.jni;

public class _NegotiablePortStub extends omnijni.ObjectImpl implements ExtendedCF.NegotiablePort
{
  public _NegotiablePortStub ()
  {
  }

  protected _NegotiablePortStub (long ref)
  {
    super(ref);
  }

  public ExtendedCF.TransportInfo[] supportedTransports ()
  {
    return _get_supportedTransports(this.ref_);
  }
  private static native ExtendedCF.TransportInfo[] _get_supportedTransports (long __ref__);

  private static String __ids[] = {
    "IDL:ExtendedCF/NegotiablePort:1.0",
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

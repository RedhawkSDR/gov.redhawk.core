package ExtendedCF.jni;

public class _NegotiableProvidesPortStub extends omnijni.ObjectImpl implements ExtendedCF.NegotiableProvidesPort
{
  public _NegotiableProvidesPortStub ()
  {
  }

  protected _NegotiableProvidesPortStub (long ref)
  {
    super(ref);
  }

  public ExtendedCF.TransportInfo[] supportedTransports ()
  {
    return _get_supportedTransports(this.ref_);
  }
  private static native ExtendedCF.TransportInfo[] _get_supportedTransports (long __ref__);

  public ExtendedCF.NegotiationResult negotiateTransport (String transportType, CF.DataType[] transportProperties)
  {
    return negotiateTransport(this.ref_, transportType, transportProperties);
  }
  private static native ExtendedCF.NegotiationResult negotiateTransport (long __ref__, String transportType, CF.DataType[] transportProperties);

  public void disconnectTransport (String transportId)
  {
    disconnectTransport(this.ref_, transportId);
  }
  private static native void disconnectTransport (long __ref__, String transportId);

  private static String __ids[] = {
    "IDL:ExtendedCF/NegotiableProvidesPort:1.0",
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

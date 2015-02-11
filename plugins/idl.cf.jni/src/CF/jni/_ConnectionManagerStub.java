package CF.jni;

public class _ConnectionManagerStub extends omnijni.ObjectImpl implements CF.ConnectionManager
{
  public _ConnectionManagerStub ()
  {
  }

  protected _ConnectionManagerStub (long ref)
  {
    super(ref);
  }

  public void connect (CF.ConnectionManagerPackage.EndpointRequest usesEndpoint, CF.ConnectionManagerPackage.EndpointRequest providesEndpoint, org.omg.CORBA.StringHolder connectionId)
  {
    connect(this.ref_, usesEndpoint, providesEndpoint, connectionId);
  }
  private static native void connect (long __ref__, CF.ConnectionManagerPackage.EndpointRequest usesEndpoint, CF.ConnectionManagerPackage.EndpointRequest providesEndpoint, org.omg.CORBA.StringHolder connectionId);

  public void disconnect (String connectionId)
  {
    disconnect(this.ref_, connectionId);
  }
  private static native void disconnect (long __ref__, String connectionId);

  public CF.ConnectionManagerPackage.ConnectionStatusType[] connections ()
  {
    return _get_connections(this.ref_);
  }
  private static native CF.ConnectionManagerPackage.ConnectionStatusType[] _get_connections (long __ref__);

  public void listConnections (int how_many, CF.ConnectionManagerPackage.ConnectionStatusSequenceHolder connections, CF.ConnectionStatusIteratorHolder iter)
  {
    listConnections(this.ref_, how_many, connections, iter);
  }
  private static native void listConnections (long __ref__, int how_many, CF.ConnectionManagerPackage.ConnectionStatusSequenceHolder connections, CF.ConnectionStatusIteratorHolder iter);

  private static String __ids[] = {
    "IDL:CF/ConnectionManager:1.0",
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

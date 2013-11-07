package BULKIO.jni;

public class _UsesPortStatisticsProviderStub extends omnijni.ObjectImpl implements BULKIO.UsesPortStatisticsProvider
{
  public _UsesPortStatisticsProviderStub ()
  {
  }

  protected _UsesPortStatisticsProviderStub (long ref)
  {
    super(ref);
  }

  public void connectPort (org.omg.CORBA.Object connection, String connectionId)
  {
    connectPort(this.ref_, connection, connectionId);
  }
  private static native void connectPort (long __ref__, org.omg.CORBA.Object connection, String connectionId);

  public void disconnectPort (String connectionId)
  {
    disconnectPort(this.ref_, connectionId);
  }
  private static native void disconnectPort (long __ref__, String connectionId);

  public ExtendedCF.UsesConnection[] connections ()
  {
    return _get_connections(this.ref_);
  }
  private static native ExtendedCF.UsesConnection[] _get_connections (long __ref__);

  public BULKIO.UsesPortStatistics[] statistics ()
  {
    return _get_statistics(this.ref_);
  }
  private static native BULKIO.UsesPortStatistics[] _get_statistics (long __ref__);

  private static String __ids[] = {
    "IDL:BULKIO/UsesPortStatisticsProvider:1.0",
    "IDL:ExtendedCF/QueryablePort:1.0",
  };

  public String[] _ids ()
  {
    return (String[])__ids.clone();
  }

  static {
    System.loadLibrary("bulkiojni");
  }

  protected native long _get_object_ref(long ref);
  protected native long _narrow_object_ref(long ref);
}

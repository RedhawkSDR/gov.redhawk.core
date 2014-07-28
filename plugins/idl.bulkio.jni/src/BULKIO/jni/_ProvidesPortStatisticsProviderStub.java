package BULKIO.jni;

public class _ProvidesPortStatisticsProviderStub extends omnijni.ObjectImpl implements BULKIO.ProvidesPortStatisticsProvider
{
  public _ProvidesPortStatisticsProviderStub ()
  {
  }

  protected _ProvidesPortStatisticsProviderStub (long ref)
  {
    super(ref);
  }

  public BULKIO.PortUsageType state ()
  {
    return _get_state(this.ref_);
  }
  private static native BULKIO.PortUsageType _get_state (long __ref__);

  public BULKIO.PortStatistics statistics ()
  {
    return _get_statistics(this.ref_);
  }
  private static native BULKIO.PortStatistics _get_statistics (long __ref__);

  private static String __ids[] = {
    "IDL:BULKIO/ProvidesPortStatisticsProvider:1.0",
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

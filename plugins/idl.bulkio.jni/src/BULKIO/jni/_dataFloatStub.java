package BULKIO.jni;

public class _dataFloatStub extends omnijni.ObjectImpl implements BULKIO.dataFloat
{
  public _dataFloatStub ()
  {
  }

  protected _dataFloatStub (long ref)
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

  public BULKIO.StreamSRI[] activeSRIs ()
  {
    return _get_activeSRIs(this.ref_);
  }
  private static native BULKIO.StreamSRI[] _get_activeSRIs (long __ref__);

  public void pushSRI (BULKIO.StreamSRI H)
  {
    pushSRI(this.ref_, H);
  }
  private static native void pushSRI (long __ref__, BULKIO.StreamSRI H);

  public void pushPacket (float[] data, BULKIO.PrecisionUTCTime T, boolean EOS, String streamID)
  {
    pushPacket(this.ref_, data, T, EOS, streamID);
  }
  private static native void pushPacket (long __ref__, float[] data, BULKIO.PrecisionUTCTime T, boolean EOS, String streamID);

  private static String __ids[] = {
    "IDL:BULKIO/dataFloat:1.0",
    "IDL:BULKIO/ProvidesPortStatisticsProvider:1.0",
    "IDL:BULKIO/updateSRI:1.0",
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

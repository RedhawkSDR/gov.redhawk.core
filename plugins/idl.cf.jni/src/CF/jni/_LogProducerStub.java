package CF.jni;

public class _LogProducerStub extends omnijni.ObjectImpl implements CF.LogProducer
{
  public _LogProducerStub ()
  {
  }

  protected _LogProducerStub (long ref)
  {
    super(ref);
  }

  public long get_max_size ()
  {
    return get_max_size(this.ref_);
  }
  private static native long get_max_size (long __ref__);

  public long get_current_size ()
  {
    return get_current_size(this.ref_);
  }
  private static native long get_current_size (long __ref__);

  public long get_n_records ()
  {
    return get_n_records(this.ref_);
  }
  private static native long get_n_records (long __ref__);

  public CF.LogFullAction get_log_full_action ()
  {
    return get_log_full_action(this.ref_);
  }
  private static native CF.LogFullAction get_log_full_action (long __ref__);

  public CF.LogAdministrativeState get_administrative_state ()
  {
    return get_administrative_state(this.ref_);
  }
  private static native CF.LogAdministrativeState get_administrative_state (long __ref__);

  public CF.LogOperationalState get_operational_state ()
  {
    return get_operational_state(this.ref_);
  }
  private static native CF.LogOperationalState get_operational_state (long __ref__);

  public CF.LogAvailabilityStatus get_availability_status ()
  {
    return get_availability_status(this.ref_);
  }
  private static native CF.LogAvailabilityStatus get_availability_status (long __ref__);

  public void write_records (CF.LogEvent[] records)
  {
    write_records(this.ref_, records);
  }
  private static native void write_records (long __ref__, CF.LogEvent[] records);

  public void write_record (CF.LogEvent[] record)
  {
    write_record(this.ref_, record);
  }
  private static native void write_record (long __ref__, CF.LogEvent[] record);

  private static String __ids[] = {
    "IDL:CF/LogProducer:1.0",
    "IDL:CF/LogStatus:1.0",
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

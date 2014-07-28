package CF.jni;

public class _LogAdministratorStub extends omnijni.ObjectImpl implements CF.LogAdministrator
{
  public _LogAdministratorStub ()
  {
  }

  protected _LogAdministratorStub (long ref)
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

  public void set_max_size (long size)
  {
    set_max_size(this.ref_, size);
  }
  private static native void set_max_size (long __ref__, long size);

  public void set_log_full_action (CF.LogFullAction action)
  {
    set_log_full_action(this.ref_, action);
  }
  private static native void set_log_full_action (long __ref__, CF.LogFullAction action);

  public void set_administrative_state (CF.LogAdministrativeState state)
  {
    set_administrative_state(this.ref_, state);
  }
  private static native void set_administrative_state (long __ref__, CF.LogAdministrativeState state);

  public void clear_log ()
  {
    clear_log(this.ref_);
  }
  private static native void clear_log (long __ref__);

  public void destroy ()
  {
    destroy(this.ref_);
  }
  private static native void destroy (long __ref__);

  private static String __ids[] = {
    "IDL:CF/LogAdministrator:1.0",
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

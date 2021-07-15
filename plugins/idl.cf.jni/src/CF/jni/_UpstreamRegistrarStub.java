package CF.jni;

public class _UpstreamRegistrarStub extends omnijni.ObjectImpl implements CF.UpstreamRegistrar
{
  public _UpstreamRegistrarStub ()
  {
  }

  protected _UpstreamRegistrarStub (long ref)
  {
    super(ref);
  }

  public void setUpstream (CF.UpstreamTuple src)
  {
    setUpstream(this.ref_, src);
  }
  private static native void setUpstream (long __ref__, CF.UpstreamTuple src);

  public void removeUpstream (CF.UpstreamTuple src)
  {
    removeUpstream(this.ref_, src);
  }
  private static native void removeUpstream (long __ref__, CF.UpstreamTuple src);

  public CF.UpstreamTuple[] upstreams ()
  {
    return _get_upstreams(this.ref_);
  }
  private static native CF.UpstreamTuple[] _get_upstreams (long __ref__);

  private static String __ids[] = {
    "IDL:CF/UpstreamRegistrar:1.0",
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

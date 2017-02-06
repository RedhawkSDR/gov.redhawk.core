package CF.jni;

public class _EventChannelManagerStub extends omnijni.ObjectImpl implements CF.EventChannelManager
{
  public _EventChannelManagerStub ()
  {
  }

  protected _EventChannelManagerStub (long ref)
  {
    super(ref);
  }

  public org.omg.CosEventChannelAdmin.EventChannel create (String channel_name)
  {
    return create(this.ref_, channel_name);
  }
  private static native org.omg.CosEventChannelAdmin.EventChannel create (long __ref__, String channel_name);

  public org.omg.CosEventChannelAdmin.EventChannel get (String channel_name)
  {
    return get(this.ref_, channel_name);
  }
  private static native org.omg.CosEventChannelAdmin.EventChannel get (long __ref__, String channel_name);

  public org.omg.CosEventChannelAdmin.EventChannel createForRegistrations (String channel_name)
  {
    return createForRegistrations(this.ref_, channel_name);
  }
  private static native org.omg.CosEventChannelAdmin.EventChannel createForRegistrations (long __ref__, String channel_name);

  public void markForRegistrations (String channel_name)
  {
    markForRegistrations(this.ref_, channel_name);
  }
  private static native void markForRegistrations (long __ref__, String channel_name);

  public void release (String channel_name)
  {
    release(this.ref_, channel_name);
  }
  private static native void release (long __ref__, String channel_name);

  public void forceRelease (String channel_name)
  {
    forceRelease(this.ref_, channel_name);
  }
  private static native void forceRelease (long __ref__, String channel_name);

  public CF.EventChannelManagerPackage.EventChannelReg registerConsumer (org.omg.CosEventComm.PushConsumer consumer, CF.EventChannelManagerPackage.EventRegistration req)
  {
    return registerConsumer(this.ref_, consumer, req);
  }
  private static native CF.EventChannelManagerPackage.EventChannelReg registerConsumer (long __ref__, org.omg.CosEventComm.PushConsumer consumer, CF.EventChannelManagerPackage.EventRegistration req);

  public CF.EventChannelManagerPackage.PublisherReg registerPublisher (CF.EventChannelManagerPackage.EventRegistration req, org.omg.CosEventComm.PushSupplier disconnectReceiver)
  {
    return registerPublisher(this.ref_, req, disconnectReceiver);
  }
  private static native CF.EventChannelManagerPackage.PublisherReg registerPublisher (long __ref__, CF.EventChannelManagerPackage.EventRegistration req, org.omg.CosEventComm.PushSupplier disconnectReceiver);

  public CF.EventChannelManagerPackage.EventChannelReg registerResource (CF.EventChannelManagerPackage.EventRegistration req)
  {
    return registerResource(this.ref_, req);
  }
  private static native CF.EventChannelManagerPackage.EventChannelReg registerResource (long __ref__, CF.EventChannelManagerPackage.EventRegistration req);

  public void unregister (CF.EventChannelManagerPackage.EventRegistration reg)
  {
    unregister(this.ref_, reg);
  }
  private static native void unregister (long __ref__, CF.EventChannelManagerPackage.EventRegistration reg);

  public void listChannels (int how_many, CF.EventChannelManagerPackage.EventChannelInfoListHolder elist, CF.EventChannelInfoIteratorHolder eiter)
  {
    listChannels(this.ref_, how_many, elist, eiter);
  }
  private static native void listChannels (long __ref__, int how_many, CF.EventChannelManagerPackage.EventChannelInfoListHolder elist, CF.EventChannelInfoIteratorHolder eiter);

  public void listRegistrants (String channel_name, int how_many, CF.EventChannelManagerPackage.EventRegistrantListHolder rlist, CF.EventRegistrantIteratorHolder riter)
  {
    listRegistrants(this.ref_, channel_name, how_many, rlist, riter);
  }
  private static native void listRegistrants (long __ref__, String channel_name, int how_many, CF.EventChannelManagerPackage.EventRegistrantListHolder rlist, CF.EventRegistrantIteratorHolder riter);

  private static String __ids[] = {
    "IDL:CF/EventChannelManager:1.0",
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

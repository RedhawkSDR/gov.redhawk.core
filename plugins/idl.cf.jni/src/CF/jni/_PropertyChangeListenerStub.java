package CF.jni;

public class _PropertyChangeListenerStub extends omnijni.ObjectImpl implements CF.PropertyChangeListener
{
  public _PropertyChangeListenerStub ()
  {
  }

  protected _PropertyChangeListenerStub (long ref)
  {
    super(ref);
  }

  public void propertyChange (CF.PropertyChangeListenerPackage.PropertyChangeEvent prop_event)
  {
    propertyChange(this.ref_, prop_event);
  }
  private static native void propertyChange (long __ref__, CF.PropertyChangeListenerPackage.PropertyChangeEvent prop_event);

  private static String __ids[] = {
    "IDL:CF/PropertyChangeListener:1.0",
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

package ExtendedEvent.jni;

public abstract class MessageEventHelper extends ExtendedEvent.MessageEventHelper
{
  public static ExtendedEvent.MessageEvent narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null) {
      return null;
    }
    else if (obj instanceof ExtendedEvent.jni._MessageEventStub) {
      return (ExtendedEvent.MessageEvent)obj;
    }
    else if (!obj._is_a(id())) {
      throw new org.omg.CORBA.BAD_PARAM();
    }
    else if (obj instanceof omnijni.ObjectImpl) {
      ExtendedEvent.jni._MessageEventStub stub = new ExtendedEvent.jni._MessageEventStub();
      long ref = ((omnijni.ObjectImpl)obj)._get_object_ref();
      stub._set_object_ref(ref);
      return (ExtendedEvent.MessageEvent)stub;
    }
    else {
      org.omg.CORBA.ORB orb = ((org.omg.CORBA.portable.ObjectImpl)obj)._orb();
      String ior = orb.object_to_string(obj);
      return narrow(omnijni.ORB.string_to_object(ior));
    }
  }
}

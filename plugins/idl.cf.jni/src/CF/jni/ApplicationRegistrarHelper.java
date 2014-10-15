package CF.jni;

public abstract class ApplicationRegistrarHelper extends CF.ApplicationRegistrarHelper
{
  public static CF.ApplicationRegistrar narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null) {
      return null;
    }
    else if (obj instanceof CF.jni._ApplicationRegistrarStub) {
      return (CF.ApplicationRegistrar)obj;
    }
    else if (!obj._is_a(id())) {
      throw new org.omg.CORBA.BAD_PARAM();
    }
    else if (obj instanceof omnijni.ObjectImpl) {
      CF.jni._ApplicationRegistrarStub stub = new CF.jni._ApplicationRegistrarStub();
      long ref = ((omnijni.ObjectImpl)obj)._get_object_ref();
      stub._set_object_ref(ref);
      return (CF.ApplicationRegistrar)stub;
    }
    else {
      org.omg.CORBA.ORB orb = ((org.omg.CORBA.portable.ObjectImpl)obj)._orb();
      String ior = orb.object_to_string(obj);
      return narrow(omnijni.ORB.string_to_object(ior));
    }
  }
}

package CF.jni;

public abstract class DomainManagerHelper extends CF.DomainManagerHelper
{
  public static CF.DomainManager narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null) {
      return null;
    }
    else if (obj instanceof CF.jni._DomainManagerStub) {
      return (CF.DomainManager)obj;
    }
    else if (!obj._is_a(id())) {
      throw new org.omg.CORBA.BAD_PARAM();
    }
    else if (obj instanceof omnijni.ObjectImpl) {
      CF.jni._DomainManagerStub stub = new CF.jni._DomainManagerStub();
      long ref = ((omnijni.ObjectImpl)obj)._get_object_ref();
      stub._set_object_ref(ref);
      return (CF.DomainManager)stub;
    }
    else {
      org.omg.CORBA.ORB orb = ((org.omg.CORBA.portable.ObjectImpl)obj)._orb();
      String ior = orb.object_to_string(obj);
      return narrow(omnijni.ORB.string_to_object(ior));
    }
  }
}

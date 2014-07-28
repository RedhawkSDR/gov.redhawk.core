package ExtendedCF.jni;

public abstract class QueryablePortHelper extends ExtendedCF.QueryablePortHelper
{
  public static ExtendedCF.QueryablePort narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null) {
      return null;
    }
    else if (obj instanceof ExtendedCF.jni._QueryablePortStub) {
      return (ExtendedCF.QueryablePort)obj;
    }
    else if (!obj._is_a(id())) {
      throw new org.omg.CORBA.BAD_PARAM();
    }
    else if (obj instanceof omnijni.ObjectImpl) {
      ExtendedCF.jni._QueryablePortStub stub = new ExtendedCF.jni._QueryablePortStub();
      long ref = ((omnijni.ObjectImpl)obj)._get_object_ref();
      stub._set_object_ref(ref);
      return (ExtendedCF.QueryablePort)stub;
    }
    else {
      org.omg.CORBA.ORB orb = ((org.omg.CORBA.portable.ObjectImpl)obj)._orb();
      String ior = orb.object_to_string(obj);
      return narrow(omnijni.ORB.string_to_object(ior));
    }
  }
}

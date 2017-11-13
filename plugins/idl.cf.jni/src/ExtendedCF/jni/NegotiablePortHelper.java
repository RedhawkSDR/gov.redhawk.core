package ExtendedCF.jni;

public abstract class NegotiablePortHelper extends ExtendedCF.NegotiablePortHelper
{
  public static ExtendedCF.NegotiablePort narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null) {
      return null;
    }
    else if (obj instanceof ExtendedCF.jni._NegotiablePortStub) {
      return (ExtendedCF.NegotiablePort)obj;
    }
    else if (!obj._is_a(id())) {
      throw new org.omg.CORBA.BAD_PARAM();
    }
    else if (obj instanceof omnijni.ObjectImpl) {
      ExtendedCF.jni._NegotiablePortStub stub = new ExtendedCF.jni._NegotiablePortStub();
      long ref = ((omnijni.ObjectImpl)obj)._get_object_ref();
      stub._set_object_ref(ref);
      return (ExtendedCF.NegotiablePort)stub;
    }
    else {
      org.omg.CORBA.ORB orb = ((org.omg.CORBA.portable.ObjectImpl)obj)._orb();
      String ior = orb.object_to_string(obj);
      return narrow(omnijni.ORB.string_to_object(ior));
    }
  }
}

package ExtendedCF.jni;

public abstract class NegotiableProvidesPortHelper extends ExtendedCF.NegotiableProvidesPortHelper
{
  public static ExtendedCF.NegotiableProvidesPort narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null) {
      return null;
    }
    else if (obj instanceof ExtendedCF.jni._NegotiableProvidesPortStub) {
      return (ExtendedCF.NegotiableProvidesPort)obj;
    }
    else if (!obj._is_a(id())) {
      throw new org.omg.CORBA.BAD_PARAM();
    }
    else if (obj instanceof omnijni.ObjectImpl) {
      ExtendedCF.jni._NegotiableProvidesPortStub stub = new ExtendedCF.jni._NegotiableProvidesPortStub();
      long ref = ((omnijni.ObjectImpl)obj)._get_object_ref();
      stub._set_object_ref(ref);
      return (ExtendedCF.NegotiableProvidesPort)stub;
    }
    else {
      org.omg.CORBA.ORB orb = ((org.omg.CORBA.portable.ObjectImpl)obj)._orb();
      String ior = orb.object_to_string(obj);
      return narrow(omnijni.ORB.string_to_object(ior));
    }
  }
}

package org.omg.CosNaming.jni;

public abstract class NamingContextHelper extends org.omg.CosNaming.NamingContextHelper
{
  public static org.omg.CosNaming.NamingContext narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null) {
      return null;
    }
    else if (obj instanceof org.omg.CosNaming.jni._NamingContextStub) {
      return (org.omg.CosNaming.NamingContext)obj;
    }
    else if (!obj._is_a(id())) {
      throw new org.omg.CORBA.BAD_PARAM();
    }
    else if (obj instanceof omnijni.ObjectImpl) {
      org.omg.CosNaming.jni._NamingContextStub stub = new org.omg.CosNaming.jni._NamingContextStub();
      long ref = ((omnijni.ObjectImpl)obj)._get_object_ref();
      stub._set_object_ref(ref);
      return (org.omg.CosNaming.NamingContext)stub;
    }
    else {
      org.omg.CORBA.ORB orb = ((org.omg.CORBA.portable.ObjectImpl)obj)._orb();
      String ior = orb.object_to_string(obj);
      return narrow(omnijni.ORB.string_to_object(ior));
    }
  }
}

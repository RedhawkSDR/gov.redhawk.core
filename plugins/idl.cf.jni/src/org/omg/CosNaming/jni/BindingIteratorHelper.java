package org.omg.CosNaming.jni;

public abstract class BindingIteratorHelper extends org.omg.CosNaming.BindingIteratorHelper
{
  public static org.omg.CosNaming.BindingIterator narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null) {
      return null;
    }
    else if (obj instanceof org.omg.CosNaming.jni._BindingIteratorStub) {
      return (org.omg.CosNaming.BindingIterator)obj;
    }
    else if (!obj._is_a(id())) {
      throw new org.omg.CORBA.BAD_PARAM();
    }
    else if (obj instanceof omnijni.ObjectImpl) {
      org.omg.CosNaming.jni._BindingIteratorStub stub = new org.omg.CosNaming.jni._BindingIteratorStub();
      long ref = ((omnijni.ObjectImpl)obj)._get_object_ref();
      stub._set_object_ref(ref);
      return (org.omg.CosNaming.BindingIterator)stub;
    }
    else {
      org.omg.CORBA.ORB orb = ((org.omg.CORBA.portable.ObjectImpl)obj)._orb();
      String ior = orb.object_to_string(obj);
      return narrow(omnijni.ORB.string_to_object(ior));
    }
  }
}

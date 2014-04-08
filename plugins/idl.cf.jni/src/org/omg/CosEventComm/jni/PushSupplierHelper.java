package org.omg.CosEventComm.jni;

public abstract class PushSupplierHelper extends org.omg.CosEventComm.PushSupplierHelper
{
  public static org.omg.CosEventComm.PushSupplier narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null) {
      return null;
    }
    else if (obj instanceof org.omg.CosEventComm.jni._PushSupplierStub) {
      return (org.omg.CosEventComm.PushSupplier)obj;
    }
    else if (!obj._is_a(id())) {
      throw new org.omg.CORBA.BAD_PARAM();
    }
    else if (obj instanceof omnijni.ObjectImpl) {
      org.omg.CosEventComm.jni._PushSupplierStub stub = new org.omg.CosEventComm.jni._PushSupplierStub();
      long ref = ((omnijni.ObjectImpl)obj)._get_object_ref();
      stub._set_object_ref(ref);
      return (org.omg.CosEventComm.PushSupplier)stub;
    }
    else {
      org.omg.CORBA.ORB orb = ((org.omg.CORBA.portable.ObjectImpl)obj)._orb();
      String ior = orb.object_to_string(obj);
      return narrow(omnijni.ORB.string_to_object(ior));
    }
  }
}

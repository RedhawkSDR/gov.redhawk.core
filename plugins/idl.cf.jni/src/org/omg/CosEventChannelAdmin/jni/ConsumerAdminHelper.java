package org.omg.CosEventChannelAdmin.jni;

public abstract class ConsumerAdminHelper extends org.omg.CosEventChannelAdmin.ConsumerAdminHelper
{
  public static org.omg.CosEventChannelAdmin.ConsumerAdmin narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null) {
      return null;
    }
    else if (obj instanceof org.omg.CosEventChannelAdmin.jni._ConsumerAdminStub) {
      return (org.omg.CosEventChannelAdmin.ConsumerAdmin)obj;
    }
    else if (!obj._is_a(id())) {
      throw new org.omg.CORBA.BAD_PARAM();
    }
    else if (obj instanceof omnijni.ObjectImpl) {
      org.omg.CosEventChannelAdmin.jni._ConsumerAdminStub stub = new org.omg.CosEventChannelAdmin.jni._ConsumerAdminStub();
      long ref = ((omnijni.ObjectImpl)obj)._get_object_ref();
      stub._set_object_ref(ref);
      return (org.omg.CosEventChannelAdmin.ConsumerAdmin)stub;
    }
    else {
      org.omg.CORBA.ORB orb = ((org.omg.CORBA.portable.ObjectImpl)obj)._orb();
      String ior = orb.object_to_string(obj);
      return narrow(omnijni.ORB.string_to_object(ior));
    }
  }
}

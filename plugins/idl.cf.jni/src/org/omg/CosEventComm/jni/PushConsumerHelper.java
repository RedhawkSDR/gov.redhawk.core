package org.omg.CosEventComm.jni;

public abstract class PushConsumerHelper extends org.omg.CosEventComm.PushConsumerHelper
{
  public static org.omg.CosEventComm.PushConsumer narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null) {
      return null;
    }
    else if (obj instanceof org.omg.CosEventComm.jni._PushConsumerStub) {
      return (org.omg.CosEventComm.PushConsumer)obj;
    }
    else if (!obj._is_a(id())) {
      throw new org.omg.CORBA.BAD_PARAM();
    }
    else if (obj instanceof omnijni.ObjectImpl) {
      org.omg.CosEventComm.jni._PushConsumerStub stub = new org.omg.CosEventComm.jni._PushConsumerStub();
      long ref = ((omnijni.ObjectImpl)obj)._get_object_ref();
      stub._set_object_ref(ref);
      return (org.omg.CosEventComm.PushConsumer)stub;
    }
    else {
      org.omg.CORBA.ORB orb = ((org.omg.CORBA.portable.ObjectImpl)obj)._orb();
      String ior = orb.object_to_string(obj);
      return narrow(omnijni.ORB.string_to_object(ior));
    }
  }
}

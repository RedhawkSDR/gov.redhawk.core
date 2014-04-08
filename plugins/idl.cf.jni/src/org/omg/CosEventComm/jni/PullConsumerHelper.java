package org.omg.CosEventComm.jni;

public abstract class PullConsumerHelper extends org.omg.CosEventComm.PullConsumerHelper
{
  public static org.omg.CosEventComm.PullConsumer narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null) {
      return null;
    }
    else if (obj instanceof org.omg.CosEventComm.jni._PullConsumerStub) {
      return (org.omg.CosEventComm.PullConsumer)obj;
    }
    else if (!obj._is_a(id())) {
      throw new org.omg.CORBA.BAD_PARAM();
    }
    else if (obj instanceof omnijni.ObjectImpl) {
      org.omg.CosEventComm.jni._PullConsumerStub stub = new org.omg.CosEventComm.jni._PullConsumerStub();
      long ref = ((omnijni.ObjectImpl)obj)._get_object_ref();
      stub._set_object_ref(ref);
      return (org.omg.CosEventComm.PullConsumer)stub;
    }
    else {
      org.omg.CORBA.ORB orb = ((org.omg.CORBA.portable.ObjectImpl)obj)._orb();
      String ior = orb.object_to_string(obj);
      return narrow(omnijni.ORB.string_to_object(ior));
    }
  }
}

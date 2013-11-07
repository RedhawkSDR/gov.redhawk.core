package BULKIO.jni;

public abstract class dataUlongHelper extends BULKIO.dataUlongHelper
{
  public static BULKIO.dataUlong narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null) {
      return null;
    }
    else if (obj instanceof BULKIO.jni._dataUlongStub) {
      return (BULKIO.dataUlong)obj;
    }
    else if (!obj._is_a(id())) {
      throw new org.omg.CORBA.BAD_PARAM();
    }
    else if (obj instanceof omnijni.ObjectImpl) {
      BULKIO.jni._dataUlongStub stub = new BULKIO.jni._dataUlongStub();
      long ref = ((omnijni.ObjectImpl)obj)._get_object_ref();
      stub._set_object_ref(ref);
      return (BULKIO.dataUlong)stub;
    }
    else {
      org.omg.CORBA.ORB orb = ((org.omg.CORBA.portable.ObjectImpl)obj)._orb();
      String ior = orb.object_to_string(obj);
      return narrow(omnijni.ORB.string_to_object(ior));
    }
  }
}

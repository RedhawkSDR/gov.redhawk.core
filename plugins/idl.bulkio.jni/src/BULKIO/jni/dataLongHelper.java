package BULKIO.jni;

public abstract class dataLongHelper extends BULKIO.dataLongHelper
{
  public static BULKIO.dataLong narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null) {
      return null;
    }
    else if (obj instanceof BULKIO.jni._dataLongStub) {
      return (BULKIO.dataLong)obj;
    }
    else if (!obj._is_a(id())) {
      throw new org.omg.CORBA.BAD_PARAM();
    }
    else if (obj instanceof omnijni.ObjectImpl) {
      BULKIO.jni._dataLongStub stub = new BULKIO.jni._dataLongStub();
      long ref = ((omnijni.ObjectImpl)obj)._get_object_ref();
      stub._set_object_ref(ref);
      return (BULKIO.dataLong)stub;
    }
    else {
      org.omg.CORBA.ORB orb = ((org.omg.CORBA.portable.ObjectImpl)obj)._orb();
      String ior = orb.object_to_string(obj);
      return narrow(omnijni.ORB.string_to_object(ior));
    }
  }
}

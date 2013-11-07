package BULKIO.jni;

public abstract class dataOctetHelper extends BULKIO.dataOctetHelper
{
  public static BULKIO.dataOctet narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null) {
      return null;
    }
    else if (obj instanceof BULKIO.jni._dataOctetStub) {
      return (BULKIO.dataOctet)obj;
    }
    else if (!obj._is_a(id())) {
      throw new org.omg.CORBA.BAD_PARAM();
    }
    else if (obj instanceof omnijni.ObjectImpl) {
      BULKIO.jni._dataOctetStub stub = new BULKIO.jni._dataOctetStub();
      long ref = ((omnijni.ObjectImpl)obj)._get_object_ref();
      stub._set_object_ref(ref);
      return (BULKIO.dataOctet)stub;
    }
    else {
      org.omg.CORBA.ORB orb = ((org.omg.CORBA.portable.ObjectImpl)obj)._orb();
      String ior = orb.object_to_string(obj);
      return narrow(omnijni.ORB.string_to_object(ior));
    }
  }
}

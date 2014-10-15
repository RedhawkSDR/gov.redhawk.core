package CF.jni;

public abstract class AllocationStatusIteratorHelper extends CF.AllocationStatusIteratorHelper
{
  public static CF.AllocationStatusIterator narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null) {
      return null;
    }
    else if (obj instanceof CF.jni._AllocationStatusIteratorStub) {
      return (CF.AllocationStatusIterator)obj;
    }
    else if (!obj._is_a(id())) {
      throw new org.omg.CORBA.BAD_PARAM();
    }
    else if (obj instanceof omnijni.ObjectImpl) {
      CF.jni._AllocationStatusIteratorStub stub = new CF.jni._AllocationStatusIteratorStub();
      long ref = ((omnijni.ObjectImpl)obj)._get_object_ref();
      stub._set_object_ref(ref);
      return (CF.AllocationStatusIterator)stub;
    }
    else {
      org.omg.CORBA.ORB orb = ((org.omg.CORBA.portable.ObjectImpl)obj)._orb();
      String ior = orb.object_to_string(obj);
      return narrow(omnijni.ORB.string_to_object(ior));
    }
  }
}

package CF.jni;
/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */

public abstract class PortSupplierHelper extends CF.PortSupplierHelper
{
  public static CF.PortSupplier narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null) {
      return null;
    }
    else if (obj instanceof CF.jni._PortSupplierStub) {
      return (CF.PortSupplier)obj;
    }
    else if (!obj._is_a(id())) {
      throw new org.omg.CORBA.BAD_PARAM();
    }
    else if (obj instanceof omnijni.ObjectImpl) {
      CF.jni._PortSupplierStub stub = new CF.jni._PortSupplierStub();
      long ref = ((omnijni.ObjectImpl)obj)._get_object_ref();
      stub._set_object_ref(ref);
      return (CF.PortSupplier)stub;
    }
    else {
      org.omg.CORBA.ORB orb = ((org.omg.CORBA.portable.ObjectImpl)obj)._orb();
      String ior = orb.object_to_string(obj);
      return narrow(omnijni.ORB.string_to_object(ior));
    }
  }
}

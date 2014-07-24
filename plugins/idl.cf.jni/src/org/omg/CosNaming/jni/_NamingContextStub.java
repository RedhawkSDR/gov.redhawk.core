package org.omg.CosNaming.jni;
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

public class _NamingContextStub extends omnijni.ObjectImpl implements org.omg.CosNaming.NamingContext
{
  public _NamingContextStub ()
  {
  }

  protected _NamingContextStub (long ref)
  {
    super(ref);
  }

  public void bind (org.omg.CosNaming.NameComponent[] n, org.omg.CORBA.Object obj)
  {
    bind(this.ref_, n, obj);
  }
  private static native void bind (long __ref__, org.omg.CosNaming.NameComponent[] n, org.omg.CORBA.Object obj);

  public void rebind (org.omg.CosNaming.NameComponent[] n, org.omg.CORBA.Object obj)
  {
    rebind(this.ref_, n, obj);
  }
  private static native void rebind (long __ref__, org.omg.CosNaming.NameComponent[] n, org.omg.CORBA.Object obj);

  public void bind_context (org.omg.CosNaming.NameComponent[] n, org.omg.CosNaming.NamingContext nc)
  {
    bind_context(this.ref_, n, nc);
  }
  private static native void bind_context (long __ref__, org.omg.CosNaming.NameComponent[] n, org.omg.CosNaming.NamingContext nc);

  public void rebind_context (org.omg.CosNaming.NameComponent[] n, org.omg.CosNaming.NamingContext nc)
  {
    rebind_context(this.ref_, n, nc);
  }
  private static native void rebind_context (long __ref__, org.omg.CosNaming.NameComponent[] n, org.omg.CosNaming.NamingContext nc);

  public org.omg.CORBA.Object resolve (org.omg.CosNaming.NameComponent[] n)
  {
    return resolve(this.ref_, n);
  }
  private static native org.omg.CORBA.Object resolve (long __ref__, org.omg.CosNaming.NameComponent[] n);

  public void unbind (org.omg.CosNaming.NameComponent[] n)
  {
    unbind(this.ref_, n);
  }
  private static native void unbind (long __ref__, org.omg.CosNaming.NameComponent[] n);

  public org.omg.CosNaming.NamingContext new_context ()
  {
    return new_context(this.ref_);
  }
  private static native org.omg.CosNaming.NamingContext new_context (long __ref__);

  public org.omg.CosNaming.NamingContext bind_new_context (org.omg.CosNaming.NameComponent[] n)
  {
    return bind_new_context(this.ref_, n);
  }
  private static native org.omg.CosNaming.NamingContext bind_new_context (long __ref__, org.omg.CosNaming.NameComponent[] n);

  public void destroy ()
  {
    destroy(this.ref_);
  }
  private static native void destroy (long __ref__);

  public void list (int how_many, org.omg.CosNaming.BindingListHolder bl, org.omg.CosNaming.BindingIteratorHolder bi)
  {
    list(this.ref_, how_many, bl, bi);
  }
  private static native void list (long __ref__, int how_many, org.omg.CosNaming.BindingListHolder bl, org.omg.CosNaming.BindingIteratorHolder bi);

  private static String __ids[] = {
    "IDL:omg.org/CosNaming/NamingContext:1.0",
  };

  public String[] _ids ()
  {
    return (String[])__ids.clone();
  }

  static {
    System.loadLibrary("ossiecfjni");
  }

  protected native long _get_object_ref(long ref);
  protected native long _narrow_object_ref(long ref);
}

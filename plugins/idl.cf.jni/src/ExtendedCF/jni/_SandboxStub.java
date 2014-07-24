package ExtendedCF.jni;
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

public class _SandboxStub extends omnijni.ObjectImpl implements ExtendedCF.Sandbox
{
  public _SandboxStub ()
  {
  }

  protected _SandboxStub (long ref)
  {
    super(ref);
  }

  public org.omg.CosNaming.NamingContext namingContext ()
  {
    return _get_namingContext(this.ref_);
  }
  private static native org.omg.CosNaming.NamingContext _get_namingContext (long __ref__);

  public CF.DeviceManager deviceManager ()
  {
    return _get_deviceManager(this.ref_);
  }
  private static native CF.DeviceManager _get_deviceManager (long __ref__);

  public String[] availableProfiles ()
  {
    return _get_availableProfiles(this.ref_);
  }
  private static native String[] _get_availableProfiles (long __ref__);

  public CF.FileManager fileManager ()
  {
    return _get_fileManager(this.ref_);
  }
  private static native CF.FileManager _get_fileManager (long __ref__);

  public ExtendedCF.ResourceDesc[] registeredResources ()
  {
    return _get_registeredResources(this.ref_);
  }
  private static native ExtendedCF.ResourceDesc[] _get_registeredResources (long __ref__);

  public CF.ResourceFactory getResourceFactory (String identifier)
  {
    return getResourceFactory(this.ref_, identifier);
  }
  private static native CF.ResourceFactory getResourceFactory (long __ref__, String identifier);

  public CF.ResourceFactory getResourceFactoryByProfile (String profile)
  {
    return getResourceFactoryByProfile(this.ref_, profile);
  }
  private static native CF.ResourceFactory getResourceFactoryByProfile (long __ref__, String profile);

  public void refresh (org.omg.CORBA.Object obj, ExtendedCF.SandboxPackage.Depth dpth)
  {
    refresh(this.ref_, obj, dpth);
  }
  private static native void refresh (long __ref__, org.omg.CORBA.Object obj, ExtendedCF.SandboxPackage.Depth dpth);

  private static String __ids[] = {
    "IDL:redhawk.gov/ExtendedCF/Sandbox:1.0",
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

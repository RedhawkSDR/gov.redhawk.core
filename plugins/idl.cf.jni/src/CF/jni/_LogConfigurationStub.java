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

public class _LogConfigurationStub extends omnijni.ObjectImpl implements CF.LogConfiguration
{
  public _LogConfigurationStub ()
  {
  }

  protected _LogConfigurationStub (long ref)
  {
    super(ref);
  }

  public int log_level ()
  {
    return _get_log_level(this.ref_);
  }
  private static native int _get_log_level (long __ref__);

  public void log_level (int value)
  {
    _set_log_level(this.ref_, value);
  }
  private static native void _set_log_level (long __ref__, int value);

  public void setLogLevel (String logger_id, int newLevel)
  {
    setLogLevel(this.ref_, logger_id, newLevel);
  }
  private static native void setLogLevel (long __ref__, String logger_id, int newLevel);

  public String getLogConfig ()
  {
    return getLogConfig(this.ref_);
  }
  private static native String getLogConfig (long __ref__);

  public void setLogConfig (String config_contents)
  {
    setLogConfig(this.ref_, config_contents);
  }
  private static native void setLogConfig (long __ref__, String config_contents);

  public void setLogConfigURL (String config_url)
  {
    setLogConfigURL(this.ref_, config_url);
  }
  private static native void setLogConfigURL (long __ref__, String config_url);

  private static String __ids[] = {
    "IDL:CF/LogConfiguration:1.0",
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

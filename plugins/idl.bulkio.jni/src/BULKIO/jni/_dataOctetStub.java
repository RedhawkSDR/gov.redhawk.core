package BULKIO.jni;
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

public class _dataOctetStub extends omnijni.ObjectImpl implements BULKIO.dataOctet
{
  public _dataOctetStub ()
  {
  }

  protected _dataOctetStub (long ref)
  {
    super(ref);
  }

  public BULKIO.PortUsageType state ()
  {
    return _get_state(this.ref_);
  }
  private static native BULKIO.PortUsageType _get_state (long __ref__);

  public BULKIO.PortStatistics statistics ()
  {
    return _get_statistics(this.ref_);
  }
  private static native BULKIO.PortStatistics _get_statistics (long __ref__);

  public BULKIO.StreamSRI[] activeSRIs ()
  {
    return _get_activeSRIs(this.ref_);
  }
  private static native BULKIO.StreamSRI[] _get_activeSRIs (long __ref__);

  public void pushSRI (BULKIO.StreamSRI H)
  {
    pushSRI(this.ref_, H);
  }
  private static native void pushSRI (long __ref__, BULKIO.StreamSRI H);

  public void pushPacket (byte[] data, BULKIO.PrecisionUTCTime T, boolean EOS, String streamID)
  {
    pushPacket(this.ref_, data, T, EOS, streamID);
  }
  private static native void pushPacket (long __ref__, byte[] data, BULKIO.PrecisionUTCTime T, boolean EOS, String streamID);

  private static String __ids[] = {
    "IDL:BULKIO/dataOctet:1.0",
    "IDL:BULKIO/ProvidesPortStatisticsProvider:1.0",
    "IDL:BULKIO/updateSRI:1.0",
  };

  public String[] _ids ()
  {
    return (String[])__ids.clone();
  }

  static {
    System.loadLibrary("bulkiojni");
  }

  protected native long _get_object_ref(long ref);
  protected native long _narrow_object_ref(long ref);
}

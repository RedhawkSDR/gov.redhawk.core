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

public class _dataVITA49Stub extends omnijni.ObjectImpl implements BULKIO.dataVITA49
{
  public _dataVITA49Stub ()
  {
  }

  protected _dataVITA49Stub (long ref)
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

  public BULKIO.dataVITA49Package.InputUsageState usageState ()
  {
    return _get_usageState(this.ref_);
  }
  private static native BULKIO.dataVITA49Package.InputUsageState _get_usageState (long __ref__);

  public BULKIO.VITA49StreamDefinition[] attachedStreams ()
  {
    return _get_attachedStreams(this.ref_);
  }
  private static native BULKIO.VITA49StreamDefinition[] _get_attachedStreams (long __ref__);

  public String[] attachmentIds ()
  {
    return _get_attachmentIds(this.ref_);
  }
  private static native String[] _get_attachmentIds (long __ref__);

  public String attach (BULKIO.VITA49StreamDefinition stream, String userid)
  {
    return attach(this.ref_, stream, userid);
  }
  private static native String attach (long __ref__, BULKIO.VITA49StreamDefinition stream, String userid);

  public void detach (String attachId)
  {
    detach(this.ref_, attachId);
  }
  private static native void detach (long __ref__, String attachId);

  public BULKIO.VITA49StreamDefinition getStreamDefinition (String attachId)
  {
    return getStreamDefinition(this.ref_, attachId);
  }
  private static native BULKIO.VITA49StreamDefinition getStreamDefinition (long __ref__, String attachId);

  public String getUser (String attachId)
  {
    return getUser(this.ref_, attachId);
  }
  private static native String getUser (long __ref__, String attachId);

  public BULKIO.StreamSRI[] attachedSRIs ()
  {
    return _get_attachedSRIs(this.ref_);
  }
  private static native BULKIO.StreamSRI[] _get_attachedSRIs (long __ref__);

  public void pushSRI (BULKIO.StreamSRI H, BULKIO.PrecisionUTCTime T)
  {
    pushSRI(this.ref_, H, T);
  }
  private static native void pushSRI (long __ref__, BULKIO.StreamSRI H, BULKIO.PrecisionUTCTime T);

  private static String __ids[] = {
    "IDL:BULKIO/dataVITA49:1.0",
    "IDL:BULKIO/ProvidesPortStatisticsProvider:1.0",
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

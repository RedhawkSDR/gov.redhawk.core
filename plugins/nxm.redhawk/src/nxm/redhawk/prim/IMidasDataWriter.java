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
package nxm.redhawk.prim;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;

import BULKIO.PrecisionUTCTime;
import BULKIO.StreamSRI;

/**
 * @since 10.1
 * @noreference This interface is not intended to be referenced by clients.
 */
public interface IMidasDataWriter {
	void setStreamSri(@NonNull String streamID, @Nullable StreamSRI oldSri, @NonNull StreamSRI newSri);
	void write(final Object dataArray, final int size, final byte type, final boolean endOfStream, final PrecisionUTCTime time, final String streamID);
}

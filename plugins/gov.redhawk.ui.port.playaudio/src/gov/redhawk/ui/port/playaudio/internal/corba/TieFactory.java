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
package gov.redhawk.ui.port.playaudio.internal.corba;

import gov.redhawk.ui.port.playaudio.internal.Activator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;

import BULKIO.dataDoubleOperations;
import BULKIO.dataDoublePOATie;
import BULKIO.dataFloatOperations;
import BULKIO.dataFloatPOATie;
import BULKIO.dataOctetOperations;
import BULKIO.dataOctetPOATie;
import BULKIO.dataShortOperations;
import BULKIO.dataShortPOATie;
import BULKIO.dataUlongOperations;
import BULKIO.dataUlongPOATie;

/**
 * This class is used to generate the correct Tie object for a given data type.
 */
public class TieFactory {

	private TieFactory() {
	}

	/**
	 * This method creates the Tie object for given format. The created Tie is
	 * activated with the ORB.
	 * 
	 * @param <T> the type of the tieClass
	 * @param format the format string of the data type to process
	 * @param orb the CORBA ORB to register this Tie with
	 * @param rootpoa the POA to tie this to
	 * @param tieClass the object that implements the necessary interfaces for
	 *            the given format
	 * @return the CORBA Object that is tied to the interface, else null if an
	 *         error occurred or a bad format
	 */
	public static < T > org.omg.CORBA.Object getTie(final String format, final ORB orb, final POA rootpoa, final T tieClass) {
		org.omg.CORBA.Object obj = null;

		// Make sure we have the right amount of format
		if (format.length() == 2) {
			// Store the characters for easy access
			final char c2 = format.charAt(1);

			try {
				// Check for Scalars
				switch (c2) {
				case 'B':
					// create a tie, with servant being the delegate.
					final dataOctetPOATie tie = new dataOctetPOATie((dataOctetOperations) tieClass, rootpoa);

					// obtain the objectRef for the tie this step also
					// implicitly activates the object
					obj = tie._this(orb);
					break;
				case 'D':
					// create a tie, with servant being the delegate.
					final dataDoublePOATie tieDouble = new dataDoublePOATie((dataDoubleOperations) tieClass, rootpoa);

					// obtain the objectRef for the tie this step also
					// implicitly activates the object
					obj = tieDouble._this(orb);
					break;
				case 'F':
					// create a tie, with servant being the delegate.
					final dataFloatPOATie tieFloat = new dataFloatPOATie((dataFloatOperations) tieClass, rootpoa);

					// obtain the objectRef for the tie this step also
					// implicitly activates the object
					obj = tieFloat._this(orb);
					break;
				case 'I':
					// create a tie, with servant being the delegate.
					final dataShortPOATie tieShort = new dataShortPOATie((dataShortOperations) tieClass, rootpoa);

					// obtain the objectRef for the tie this step also
					// implicitly activates the object
					obj = tieShort._this(orb);
					break;
				case 'X':
					// create a tie, with servant being the delegate.
					final dataUlongPOATie tieLong = new dataUlongPOATie((dataUlongOperations) tieClass, rootpoa);

					// obtain the objectRef for the tie this step also
					// implicitly activates the object
					obj = tieLong._this(orb);
					break;
				default:
					break;
				}

			} catch (final ClassCastException e) {
				Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Error casting " + tieClass + " to type: " + format, e));
			}
		}

		return obj;
	}
}

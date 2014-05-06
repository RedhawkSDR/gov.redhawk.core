/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.bulkio.util.internal;

import gov.redhawk.bulkio.util.BulkIOType;
import gov.redhawk.bulkio.util.BulkIOUtilActivator;
import gov.redhawk.bulkio.util.IPortFactory;
import gov.redhawk.bulkio.util.PortReference;
import gov.redhawk.sca.util.ORBUtil;
import gov.redhawk.sca.util.OrbSession;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.omg.CORBA.SystemException;
import org.omg.PortableServer.Servant;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import BULKIO.updateSRIOperations;
import CF.Port;
import CF.PortHelper;
import CF.PortPackage.InvalidPort;
import CF.PortPackage.OccupiedPort;

/**
 * 
 */
public class PortFactoryDefaultImpl implements IPortFactory {

	private static final OrbSession SESSION = OrbSession.createSession();

	/*
	 * @see gov.redhawk.bulkio.util.IPortFactory#connect(java.lang.String, gov.redhawk.bulkio.util.Port, gov.redhawk.bulkio.util.dataDoubleOperations)
	 */
	@Override
	public PortReference connect(final String connectionID, String portIor, BulkIOType type, updateSRIOperations handler) throws CoreException {
		Servant tie = createTie(type, handler);

		try {
			org.omg.CORBA.Object portRef = SESSION.getOrb().string_to_object(portIor);
			final Port port = PortHelper.narrow(portRef);
			final org.omg.CORBA.Object ref = SESSION.getPOA().servant_to_reference(tie);
			port.connectPort(ref, connectionID);
			return new PortReference() {

				@Override
				public void dispose() {
					try {
						port.disconnectPort(connectionID);
					} catch (InvalidPort e) {
						// PASS
					} catch (SystemException e) {
						// PASS
					}
					ORBUtil.release(ref);

				}
			};
		} catch (ServantNotActive e) {
			throw new CoreException(new Status(Status.ERROR, BulkIOUtilActivator.PLUGIN_ID, "Failed to connect BulkIO Port ", e));
		} catch (WrongPolicy e) {
			throw new CoreException(new Status(Status.ERROR, BulkIOUtilActivator.PLUGIN_ID, "Failed to connect BulkIO Port ", e));
		} catch (InvalidPort e) {
			throw new CoreException(new Status(Status.ERROR, BulkIOUtilActivator.PLUGIN_ID, "Failed to connect BulkIO Port ", e));
		} catch (OccupiedPort e) {
			throw new CoreException(new Status(Status.ERROR, BulkIOUtilActivator.PLUGIN_ID, "Failed to connect BulkIO Port ", e));
		} catch (SystemException e) {
			throw new CoreException(new Status(Status.ERROR, BulkIOUtilActivator.PLUGIN_ID, "Failed to connect BulkIO Port ", e));
		}
	}

	/**
	 * @param type
	 * @param handler
	 * @return
	 */
	private Servant createTie(BulkIOType type, updateSRIOperations handler) {
		return type.createServant(handler);
	}

}

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

// BEGIN GENERATED CODE
package gov.redhawk.model.sca;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;

import CF.Port;
import CF.PortOperations;
import CF.PortPackage.InvalidPort;
import gov.redhawk.bulkio.util.AbstractBulkIOPort;
import gov.redhawk.bulkio.util.BulkIOType;
import gov.redhawk.bulkio.util.BulkIOUtilActivator;
import mil.jpeojtrs.sca.scd.Uses;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Uses Port</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 * <li>{@link gov.redhawk.model.sca.ScaUsesPort#getConnections <em>Connections</em>}</li>
 * </ul>
 *
 * @see gov.redhawk.model.sca.ScaPackage#getScaUsesPort()
 * @model superTypes="gov.redhawk.model.sca.ScaPort&lt;mil.jpeojtrs.sca.scd.Uses, mil.jpeojtrs.sca.cf.Port&gt;
 * mil.jpeojtrs.sca.cf.PortOperations"
 * extendedMetaData="name='ScaUsesPort' kind='empty'"
 * @generated
 */
public interface ScaUsesPort extends ScaPort<Uses, Port>, PortOperations {

	/**
	 * Returns the value of the '<em><b>Connections</b></em>' containment reference list.
	 * The list contents are of type {@link gov.redhawk.model.sca.ScaConnection}.
	 * It is bidirectional and its opposite is '{@link gov.redhawk.model.sca.ScaConnection#getPort <em>Port</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connections</em>' containment reference list isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connections</em>' containment reference list.
	 * @see #isSetConnections()
	 * @see #unsetConnections()
	 * @see gov.redhawk.model.sca.ScaPackage#getScaUsesPort_Connections()
	 * @see gov.redhawk.model.sca.ScaConnection#getPort
	 * @model opposite="port" containment="true" resolveProxies="true" unsettable="true" transient="true"
	 * @generated
	 */
	EList<ScaConnection> getConnections();

	/**
	 * Unsets the value of the '{@link gov.redhawk.model.sca.ScaUsesPort#getConnections <em>Connections</em>}'
	 * containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetConnections()
	 * @see #getConnections()
	 * @generated
	 */
	void unsetConnections();

	/**
	 * Returns whether the value of the '{@link gov.redhawk.model.sca.ScaUsesPort#getConnections <em>Connections</em>}'
	 * containment reference list is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Connections</em>' containment reference list is set.
	 * @see #unsetConnections()
	 * @see #getConnections()
	 * @generated
	 */
	boolean isSetConnections();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	EList<ScaConnection> fetchConnections(IProgressMonitor monitor);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model exceptions="mil.jpeojtrs.sca.cf.InvalidPort"
	 * @generated
	 */
	void disconnectPort(ScaConnection connection) throws InvalidPort;

	// END GENERATED CODE

	/**
	 * @since 21.1
	 */
	public static class Util {
		private static final String CONNECTION_TABLE = "connectionTable";
		private static final String CONNECTION_ID = CONNECTION_TABLE + "::connection_id";
		private static final String PORT_NAME = CONNECTION_TABLE + "::port_name";

		private Util() {
		}

		/**
		 * @return a Map<String, Boolean) of all connection ID's in the container's connectionTable property.<br/>
		 * <li>Key - {@link String} connectionId
		 * <li>Value - {@link Boolean} True if connection ID is available (either not being used, or only being
		 * used by the IDE). False means the connection ID is not available, such as being used for a connection
		 * to a components provides port
		 */
		public static Map<String, Boolean> getConnectionIds(ScaUsesPort port) {
			Map<String, Boolean> connectionIdMap = new HashMap<>();

			// filter out non-BULKIO ports
			if (!(port.eContainer() instanceof ScaPropertyContainer< ? , ? >)) {
				return connectionIdMap;
			}

			// A list of connection IDs for any existing connections owned by the port
			List<String> existingConnections = new ArrayList<>();
			for (ScaConnection connection : port.getConnections()) {
				existingConnections.add(connection.getId());
			}

			ScaPropertyContainer< ? , ? > propContainer = (ScaPropertyContainer< ? , ? >) port.eContainer();
			ScaStructSequenceProperty connectionTable = (ScaStructSequenceProperty) propContainer.getProperty(CONNECTION_TABLE);
			if (connectionTable == null) {
				return connectionIdMap;
			}

			for (ScaStructProperty struct : connectionTable.getStructs()) {
				ScaSimpleProperty connectionPortName = struct.getSimple(PORT_NAME);
				if (connectionPortName == null || !connectionPortName.getValue().equals(port.getName())) {
					continue;
				}

				ScaSimpleProperty connectionIdProp = struct.getSimple(CONNECTION_ID);
				if (connectionIdProp != null && connectionIdProp.getValue() != null) {
					String connectionId = connectionIdProp.getValue().toString();
					if (!existingConnections.contains(connectionId)) {
						// Not being used. Add ID to map and mark as available.
						connectionIdMap.put(connectionId, true);
					} else {
						// A connection already exists! Check to see if IDE owns it.

						// Actually we have no way of telling if non-BulkIO port connections are IDE owned, so assumed
						// they are unavailable
						if (!isBulkIOPortSupported(port)) {
							connectionIdMap.put(connectionId, false);
							continue;
						}

						AbstractBulkIOPort abstractPort = BulkIOUtilActivator.getBulkIOPortConnectionManager().getExternalPort(port.getIor(),
							BulkIOType.getType(port.getRepid()), connectionId);
						if (abstractPort != null) {
							// OK, the BulkIOPortConnectionManager is aware of this connection, so safe to assume it is
							// owned by the IDE.
							connectionIdMap.put(connectionId, true);
						} else {
							// Hmm, not owned by us. Add the connection, but mark as unavailable.
							connectionIdMap.put(connectionId, false);
						}
					}
				}
			}

			return connectionIdMap;
		}

		private static boolean isBulkIOPortSupported(ScaUsesPort port) {
			String idl = port.getRepid();
			if (BulkIOType.isTypeSupported(idl)) {
				return true;
			}
			return false;
		}
	}

	// BEGIN GENERATED CODE

} // ScaUsesPort

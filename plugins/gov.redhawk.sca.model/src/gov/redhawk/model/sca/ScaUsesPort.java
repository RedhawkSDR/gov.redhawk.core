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
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.EList;

import CF.Port;
import CF.PortOperations;
import CF.PortPackage.InvalidPort;
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
 *        mil.jpeojtrs.sca.cf.PortOperations"
 *        extendedMetaData="name='ScaUsesPort' kind='empty'"
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
	 * 
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
	 * 
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
	 * 
	 * @return whether the value of the '<em>Connections</em>' containment reference list is set.
	 * @see #unsetConnections()
	 * @see #getConnections()
	 * @generated
	 */
	boolean isSetConnections();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @model monitorDataType="gov.redhawk.model.sca.IProgressMonitor"
	 * @generated
	 */
	EList<ScaConnection> fetchConnections(IProgressMonitor monitor);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @model exceptions="mil.jpeojtrs.sca.cf.InvalidPort"
	 * @generated
	 */
	void disconnectPort(ScaConnection connection) throws InvalidPort;

	/**
	 * @since 21.0
	 */
	public static class Util {
		private static final String CONNECTION_TABLE = "connectionTable";
		private static final String CONNECTION_ID = CONNECTION_TABLE + "::connection_id";
		private static final String PORT_NAME = CONNECTION_TABLE + "::port_name";

		// END GENERATED CODE
		private Util() {
		}

		/**
		 * @return True if the ports container has a 'connectionTable' struct property with one or more entries
		 *         belonging to the provided port
		 */
		public static boolean isMultiOutPort(ScaUsesPort port) {
			return getConnectionIds(port).size() >= 1;
		}

		/**
		 * @return a list of all connection ID's in the container's connectionTable property, or an empty list if none
		 *         are found
		 */
		public static List<String> getConnectionIds(ScaUsesPort port) {
			List<String> connectionIds = new ArrayList<>();

			if (!(port.eContainer() instanceof ScaPropertyContainer< ? , ? >)) {
				return connectionIds;
			}

			ScaPropertyContainer< ? , ? > propContainer = (ScaPropertyContainer< ? , ? >) port.eContainer();
			ScaStructSequenceProperty connectionTable = (ScaStructSequenceProperty) propContainer.getProperty(CONNECTION_TABLE);
			if (connectionTable == null) {
				return connectionIds;
			}

			for (ScaStructProperty struct : connectionTable.getStructs()) {
				ScaSimpleProperty connectionPortName = struct.getSimple(PORT_NAME);
				if (connectionPortName == null || !connectionPortName.getValue().equals(port.getName())) {
					continue;
				}

				ScaSimpleProperty connectionId = struct.getSimple(CONNECTION_ID);
				if (connectionId != null && connectionId.getValue() != null) {
					connectionIds.add(connectionId.getValue().toString());
				}
			}

			return connectionIds;
		}
		// BEGIN GENERATED CODE
	}
} // ScaUsesPort

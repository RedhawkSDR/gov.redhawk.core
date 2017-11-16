/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.ui.views.connmgr;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.nebula.widgets.xviewer.XViewerFactory;
import org.eclipse.nebula.widgets.xviewer.core.model.SortDataType;
import org.eclipse.nebula.widgets.xviewer.core.model.XViewerAlign;
import org.eclipse.nebula.widgets.xviewer.core.model.XViewerColumn;
import org.eclipse.nebula.widgets.xviewer.customize.XViewerCustomMenu;

public class ConnMgrXViewerFactory extends XViewerFactory {

	private static final String NAMESPACE = ConnMgrPlugin.ID + ".viewer";
	/* package */ static final String ID_CONNECTION_RECORD_ID = NAMESPACE + ".connectionRecordId";
	/* package */ static final String ID_CONNECTION_ID = NAMESPACE + ".connectionId";
	/* package */ static final String ID_REQUESTER_ID = NAMESPACE + ".requesterId";
	/* package */ static final String ID_CONNECTED = NAMESPACE + ".connected";
	/* package */ static final String ID_SOURCE_ENTITY_ID = NAMESPACE + ".sourceEntityID";
	/* package */ static final String ID_SOURCE_PORT = NAMESPACE + ".sourcePort";
	/* package */ static final String ID_SOURCE_REPO_ID = NAMESPACE + ".sourceRepoId";
	/* package */ static final String ID_TARGET_ENTITY_ID = NAMESPACE + ".targetEntityID";
	/* package */ static final String ID_TARGET_PORT = NAMESPACE + ".targetPort";
	/* package */ static final String ID_TARGET_REPO_ID = NAMESPACE + ".targetRepoId";

	private final XViewerColumn colConnectionRecordID = new XViewerColumn(ID_CONNECTION_RECORD_ID, "Connection Record ID", 400, XViewerAlign.Left, false,
		SortDataType.String, false, "Connection Record ID");
	private final XViewerColumn colConnectionID = new XViewerColumn(ID_CONNECTION_ID, "Connection ID", 200, XViewerAlign.Left, true, SortDataType.String, false,
		"Connection ID");
	private final XViewerColumn colRequesterID = new XViewerColumn(ID_REQUESTER_ID, "Requester ID", 200, XViewerAlign.Left, true, SortDataType.String, false,
		"Requester ID");
	private final XViewerColumn colConnected = new XViewerColumn(ID_CONNECTED, "Connected", 85, XViewerAlign.Center, true, SortDataType.Boolean, false,
		"If the connection is in place or deferred");
	private final XViewerColumn colSourceEntityID = new XViewerColumn(ID_SOURCE_ENTITY_ID, "Source ID", 120, XViewerAlign.Left, true, SortDataType.String,
		false, "Source Entity ID");
	private final XViewerColumn colSourcePort = new XViewerColumn(ID_SOURCE_PORT, "Source Port", 100, XViewerAlign.Left, true, SortDataType.String, false,
		"Source Port Name");
	private final XViewerColumn colSourceRepoID = new XViewerColumn(ID_SOURCE_REPO_ID, "Source Repo. ID", 200, XViewerAlign.Left, false, SortDataType.String,
		false, "The IDL repository ID of the source");
	private final XViewerColumn colTargetEntityID = new XViewerColumn(ID_TARGET_ENTITY_ID, "Target ID", 120, XViewerAlign.Left, true, SortDataType.String,
		false, "Target Entity ID");
	private final XViewerColumn colTargetPort = new XViewerColumn(ID_TARGET_PORT, "Target Port", 100, XViewerAlign.Left, true, SortDataType.String, false,
		"Target Port Name");
	private final XViewerColumn colTargetRepoID = new XViewerColumn(ID_TARGET_REPO_ID, "Target Repo. ID", 200, XViewerAlign.Left, false, SortDataType.String,
		false, "The IDL repository ID of the target");

	public ConnMgrXViewerFactory() {
		super(NAMESPACE);
		registerColumns(colConnectionRecordID, colConnectionID, colRequesterID, colConnected, colSourceEntityID, colSourcePort, colSourceRepoID,
			colTargetEntityID, colTargetPort, colTargetRepoID);
	}

	@Override
	public boolean isAdmin() {
		return false;
	}

	@Override
	public XViewerCustomMenu getXViewerCustomMenu() {
		return new ConnMgrMenu();
	}

	public Map<XViewerColumn, EStructuralFeature> getColumnsToFeatures() {
		Map<XViewerColumn, EStructuralFeature> map = new HashMap<>();
		map.put(colConnectionRecordID, ConnMgrPackage.Literals.CONNECTION_STATUS__CONNECTION_RECORD_ID);
		map.put(colConnectionID, ConnMgrPackage.Literals.CONNECTION_STATUS__CONNECTION_ID);
		map.put(colRequesterID, ConnMgrPackage.Literals.CONNECTION_STATUS__REQUESTER_ID);
		map.put(colConnected, ConnMgrPackage.Literals.CONNECTION_STATUS__CONNECTED);
		map.put(colSourceEntityID, ConnMgrPackage.Literals.CONNECTION_STATUS__SOURCE_ENTITY_NAME);
		map.put(colSourcePort, ConnMgrPackage.Literals.CONNECTION_STATUS__SOURCE_PORT_NAME);
		map.put(colSourceRepoID, ConnMgrPackage.Literals.CONNECTION_STATUS__SOURCE_REPO_ID);
		map.put(colTargetEntityID, ConnMgrPackage.Literals.CONNECTION_STATUS__TARGET_ENTITY_NAME);
		map.put(colTargetPort, ConnMgrPackage.Literals.CONNECTION_STATUS__TARGET_PORT_NAME);
		map.put(colTargetRepoID, ConnMgrPackage.Literals.CONNECTION_STATUS__TARGET_REPO_ID);
		return map;
	}

}

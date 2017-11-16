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
package gov.redhawk.ui.views.allocmgr;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.nebula.widgets.xviewer.XViewerFactory;
import org.eclipse.nebula.widgets.xviewer.core.model.SortDataType;
import org.eclipse.nebula.widgets.xviewer.core.model.XViewerAlign;
import org.eclipse.nebula.widgets.xviewer.core.model.XViewerColumn;
import org.eclipse.nebula.widgets.xviewer.customize.XViewerCustomMenu;

public class AllocMgrXViewerFactory extends XViewerFactory {

	private static final String NAMESPACE = AllocMgrPlugin.ID + ".viewer";
	/* package */ static final String ID_ALLOC_ID = NAMESPACE + ".allocid";
	/* package */ static final String ID_DOMAIN = NAMESPACE + ".domain";
	/* package */ static final String ID_DEVICE = NAMESPACE + ".device";
	/* package */ static final String ID_DEVICE_MGR = NAMESPACE + ".devicemgr";
	/* package */ static final String ID_SOURCE_ID = NAMESPACE + ".sourceid";

	private final XViewerColumn colAllocID = new XViewerColumn(ID_ALLOC_ID, "Allocation ID", 340, XViewerAlign.Left, true, SortDataType.String, false,
		"Allocation ID");
	private final XViewerColumn colDomain = new XViewerColumn(ID_DOMAIN, "Domain", 150, XViewerAlign.Left, true, SortDataType.String, false,
		"Requesting domain");
	private final XViewerColumn colDevice = new XViewerColumn(ID_DEVICE, "Device", 150, XViewerAlign.Left, true, SortDataType.String, false, "Device label");
	private final XViewerColumn colDeviceMgr = new XViewerColumn(ID_DEVICE_MGR, "Device Manager", 150, XViewerAlign.Left, true, SortDataType.String, false,
		"Device manager label");
	private final XViewerColumn colSourceID = new XViewerColumn(ID_SOURCE_ID, "Source ID", 200, XViewerAlign.Left, true, SortDataType.String, false,
		"Source ID");

	public AllocMgrXViewerFactory() {
		super(NAMESPACE);
		registerColumns(colAllocID, colDomain, colDevice, colDeviceMgr, colSourceID);
	}

	@Override
	public boolean isAdmin() {
		return false;
	}

	@Override
	public XViewerCustomMenu getXViewerCustomMenu() {
		return new AllocMgrMenu();
	}

	public Map<XViewerColumn, EStructuralFeature> getColumnsToFeatures() {
		Map<XViewerColumn, EStructuralFeature> map = new HashMap<>();
		map.put(colAllocID, AllocMgrPackage.Literals.ALLOCATION_STATUS__ALLOCATION_ID);
		map.put(colDomain, AllocMgrPackage.Literals.ALLOCATION_STATUS__REQUESTING_DOMAIN);
		map.put(colDevice, AllocMgrPackage.Literals.ALLOCATION_STATUS__DEVICE_LABEL);
		map.put(colDeviceMgr, AllocMgrPackage.Literals.ALLOCATION_STATUS__DEVICE_MGR_LABEL);
		map.put(colSourceID, AllocMgrPackage.Literals.ALLOCATION_STATUS__SOURCE_ID);
		return map;
	}

}

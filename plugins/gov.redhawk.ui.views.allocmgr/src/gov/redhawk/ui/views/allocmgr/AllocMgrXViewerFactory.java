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

public class AllocMgrXViewerFactory extends XViewerFactory {

	private static final String NAMESPACE = AllocMgrPlugin.ID + ".viewer";
	/* package */ static final String ID_ALLOC_ID = NAMESPACE + ".allocid";
	/* package */ static final String ID_DOMAIN = NAMESPACE + ".domain";
	/* package */ static final String ID_DEVICE = NAMESPACE + ".device";
	/* package */ static final String ID_DEVICE_MGR = NAMESPACE + ".devicemgr";
	/* package */ static final String ID_SOURCE_ID = NAMESPACE + ".sourceid";

	private static final XViewerColumn COL_ALLOC_ID = new XViewerColumn(ID_ALLOC_ID, "Allocation ID", 340, XViewerAlign.Left, true, SortDataType.String, false,
		"Allocation ID");
	private static final XViewerColumn COL_DOMAIN = new XViewerColumn(ID_DOMAIN, "Domain", 150, XViewerAlign.Left, true, SortDataType.String, false,
		"Requesting domain");
	private static final XViewerColumn COL_DEVICE = new XViewerColumn(ID_DEVICE, "Device", 150, XViewerAlign.Left, true, SortDataType.String, false,
		"Device label");
	private static final XViewerColumn COL_DEVICE_MGR = new XViewerColumn(ID_DEVICE_MGR, "Device Manager", 150, XViewerAlign.Left, true, SortDataType.String,
		false, "Device manager label");
	private static final XViewerColumn COL_SOURCE_ID = new XViewerColumn(ID_SOURCE_ID, "Source ID", 200, XViewerAlign.Left, true, SortDataType.String, false,
		"Source ID");

	public AllocMgrXViewerFactory() {
		super(NAMESPACE);
		registerColumns(COL_ALLOC_ID, COL_DOMAIN, COL_DEVICE, COL_DEVICE_MGR, COL_SOURCE_ID);
	}

	@Override
	public boolean isAdmin() {
		return false;
	}

	public static Map<XViewerColumn, EStructuralFeature> getColumnsToFeatures() {
		Map<XViewerColumn, EStructuralFeature> map = new HashMap<>();
		map.put(COL_ALLOC_ID, AllocMgrPackage.Literals.ALLOCATION_STATUS__ALLOCATION_ID);
		map.put(COL_DOMAIN, AllocMgrPackage.Literals.ALLOCATION_STATUS__REQUESTING_DOMAIN);
		map.put(COL_DEVICE, AllocMgrPackage.Literals.ALLOCATION_STATUS__DEVICE_LABEL);
		map.put(COL_DEVICE_MGR, AllocMgrPackage.Literals.ALLOCATION_STATUS__DEVICE_MGR_LABEL);
		map.put(COL_SOURCE_ID, AllocMgrPackage.Literals.ALLOCATION_STATUS__SOURCE_ID);
		return map;
	}

}

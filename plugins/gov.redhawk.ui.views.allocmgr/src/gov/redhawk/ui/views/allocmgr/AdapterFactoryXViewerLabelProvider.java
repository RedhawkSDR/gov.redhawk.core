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

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.nebula.widgets.xviewer.IXViewerLabelProvider;
import org.eclipse.nebula.widgets.xviewer.IXViewerPreComputedColumn;
import org.eclipse.nebula.widgets.xviewer.IXViewerValueColumn;
import org.eclipse.nebula.widgets.xviewer.XViewer;
import org.eclipse.nebula.widgets.xviewer.XViewerCells;
import org.eclipse.nebula.widgets.xviewer.core.model.XViewerColumn;
import org.eclipse.nebula.widgets.xviewer.util.XViewerException;
import org.eclipse.swt.graphics.Image;

/**
 * A label provider for use with an {@link XViewer} and an EMF model.
 * <p/>
 * Each {@link XViewerColumn} is mapped to a specific {@link EStructuralFeature}. This allows the raw value for a cell
 * to be easily computed. When text or an image is needed, the {@link EStructuralFeature}'s feature ID is passed to the
 * applicable {@link ITableLabelProvider} method (implemented in the {@link EObject}'s item provider).
 */
public class AdapterFactoryXViewerLabelProvider extends AdapterFactoryLabelProvider implements IXViewerLabelProvider {

	private XViewer viewer;

	/**
	 * Store index of columnIndex to XViewerColumns to speed up label providing
	 */
	private final Map<Integer, XViewerColumn> indexToXViewerColumnMap = new HashMap<Integer, XViewerColumn>();

	private Map<XViewerColumn, EStructuralFeature> columnsToFeatures;

	public AdapterFactoryXViewerLabelProvider(XViewer viewer, AdapterFactory adapterFactory, Map<XViewerColumn, EStructuralFeature> columnsToFeatures) {
		super(adapterFactory);
		this.viewer = viewer;
		this.columnsToFeatures = columnsToFeatures;
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		XViewerColumn xViewerColumn = getTreeColumnOffIndex(columnIndex);

		// If not shown, don't process any further
		if (!xViewerColumn.isShow()) {
			return null;
		}

		if (xViewerColumn instanceof IXViewerValueColumn) {
			try {
				Image image = ((IXViewerValueColumn) xViewerColumn).getColumnImage(element, xViewerColumn, columnIndex);
				return (image != null) ? image : null;
			} catch (XViewerException e) {
				return null;
			}
		}

		return getColumnImage(element, xViewerColumn, columnIndex);
	}

	public Image getColumnImage(Object element, XViewerColumn xViewerColumn, int columnIndex) {
		if (!columnsToFeatures.containsKey(xViewerColumn)) {
			return null;
		}
		int featureID = columnsToFeatures.get(xViewerColumn).getFeatureID();
		return super.getColumnImage(element, featureID);
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		XViewerColumn xViewerColumn = getTreeColumnOffIndex(columnIndex);

		// If not shown, don't process any further
		if (!xViewerColumn.isShow()) {
			return "";
		}

		if (xViewerColumn instanceof IXViewerPreComputedColumn) {
			IXViewerPreComputedColumn preComputedColumn = (IXViewerPreComputedColumn) xViewerColumn;
			Long key = preComputedColumn.getKey(element);
			String cachedValue = xViewerColumn.getPreComputedValue(key);
			String result = ((IXViewerPreComputedColumn) xViewerColumn).getText(element, key, cachedValue);
			return result;
		}

		// First check value column's methods
		if (xViewerColumn instanceof IXViewerValueColumn) {
			String str;
			try {
				str = ((IXViewerValueColumn) xViewerColumn).getColumnText(element, xViewerColumn, columnIndex);
				return (str != null) ? str : "";
			} catch (XViewerException e) {
				return XViewerCells.getCellExceptionString(e);
			}
		}

		// Return label provider's value
		try {
			return getColumnText(element, xViewerColumn, columnIndex);
		} catch (Exception e) { // SUPPRESS CHECKSTYLE Forced due to XViewer API
			return XViewerCells.getCellExceptionString(e);
		}
	}

	@Override
	public String getColumnText(Object element, XViewerColumn xViewerColumn, int columnIndex) throws Exception {
		if (!columnsToFeatures.containsKey(xViewerColumn)) {
			return "";
		}
		int featureID = columnsToFeatures.get(xViewerColumn).getFeatureID();
		return super.getColumnText(element, featureID);
	}

	@Override
	public XViewerColumn getTreeColumnOffIndex(int columnIndex) {
		if (!indexToXViewerColumnMap.containsKey(columnIndex)) {
			XViewerColumn xViewerColumn = viewer.getXTreeColumn(columnIndex);
			if (xViewerColumn != null) {
				indexToXViewerColumnMap.put(columnIndex, xViewerColumn);
			}
		}
		return indexToXViewerColumnMap.get(columnIndex);
	}

	@Override
	public int getColumnGradient(Object element, XViewerColumn xViewerColumn, int columnIndex) throws Exception {
		return 0;
	}

	@Override
	public Object getBackingData(Object element, XViewerColumn xViewerColumn, int columnIndex) throws Exception {
		if (!columnsToFeatures.containsKey(xViewerColumn)) {
			return null;
		}
		return ((EObject) element).eGet(columnsToFeatures.get(xViewerColumn));
	}

	@Override
	public void clearXViewerColumnIndexCache() {
		indexToXViewerColumnMap.clear();
	}

}

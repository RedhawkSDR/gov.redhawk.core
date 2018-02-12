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
package gov.redhawk.sca.ui;

import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaStructSequenceProperty;
import gov.redhawk.model.sca.provider.ScaSimplePropertyItemProvider;
import gov.redhawk.sca.internal.ui.properties.StructFieldPropertyColumnLabelProvider;
import gov.redhawk.sca.internal.ui.properties.StructFieldPropertyEditingSupport;
import gov.redhawk.sca.ui.compatibility.CompatibilityUtil;
import gov.redhawk.sca.ui.properties.ScaPropertiesContentProvider;

import java.util.ArrayList;
import java.util.List;

import mil.jpeojtrs.sca.prf.Enumeration;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.util.AnyUtils;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @since 8.0
 */
public final class ScaComponentFactory {

	private ScaComponentFactory() {
	}

	/**
	 * @since 9.0
	 */
	public static TreeViewer createPropertyTable(final Composite parent, final int style, final AdapterFactory adapterFactory) {
		if (parent.getLayout() == null) {
			parent.setLayout(new FillLayout());
		}
		return new ScaPropertiesViewer(parent, SWT.None, style | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.SINGLE, adapterFactory).getViewer();
	}

	/**
	 * @since 9.0
	 */
	public static TreeViewer createPropertyTable(final TabbedPropertySheetWidgetFactory widgetFactory, final Composite parent, final int style,
		final AdapterFactory adapterFactory) {
		ScaPropertiesViewer viewer = new ScaPropertiesViewer(parent, SWT.None, style | widgetFactory.getOrientation() | widgetFactory.getBorderStyle()
			| SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.SINGLE, adapterFactory);
		widgetFactory.adapt(viewer);
		widgetFactory.adapt(viewer.getViewer().getControl(), false, false);
		return viewer.getViewer();
	}

	/**
	 * @since 9.0
	 */
	public static TableViewer createStructSequenceTable(final Composite parent, final int style, final AdapterFactory adapterFactory,
		final ScaStructSequenceProperty property) {
		final TableViewer viewer = new TableViewer(parent, style | SWT.FULL_SELECTION);
		ColumnViewerToolTipSupport.enableFor(viewer);
		final TableColumnLayout layout = new TableColumnLayout();
		parent.setLayout(layout);
		final ScaPropertiesContentProvider contentProvider = new ScaPropertiesContentProvider(adapterFactory);

		if (property.getDefinition() != null) {
			for (final Simple propDef : property.getDefinition().getStruct().getSimple()) {
				final TableViewerColumn columnViewer = new TableViewerColumn(viewer, SWT.CENTER);
				columnViewer.setEditingSupport(new StructFieldPropertyEditingSupport(viewer, contentProvider, propDef.getId()));
				columnViewer.setLabelProvider(new StructFieldPropertyColumnLabelProvider(contentProvider, propDef.getId()));
				String label;
				if (propDef.getName() != null) {
					label = propDef.getName();
				} else {
					label = propDef.getId();
				}
				layout.setColumnData(columnViewer.getColumn(), new ColumnPixelData(Math.min(300, 8 * label.length() + 10), true));
				columnViewer.getColumn().setText(label);
			}
			for (final SimpleSequence propDef : property.getDefinition().getStruct().getSimpleSequence()) {
				final TableViewerColumn columnViewer = new TableViewerColumn(viewer, SWT.CENTER);
				columnViewer.setEditingSupport(new StructFieldPropertyEditingSupport(viewer, contentProvider, propDef.getId(), true));
				columnViewer.setLabelProvider(new StructFieldPropertyColumnLabelProvider(contentProvider, propDef.getId(), true));
				String label;
				if (propDef.getName() != null) {
					label = propDef.getName();
				} else {
					label = propDef.getId();
				}
				layout.setColumnData(columnViewer.getColumn(), new ColumnPixelData(Math.min(300, 8 * label.length() + 10), true));
				columnViewer.getColumn().setText(label);
			}
		}

		viewer.getTable().setHeaderVisible(true);
		viewer.getTable().setLinesVisible(true);

		viewer.setContentProvider(contentProvider);
		viewer.setInput(property);

		return viewer;
	}

	/**
	 * @since 9.0
	 */
	public static Viewer createEnumPropertyViewer(final Composite parent, final int style, final ScaSimpleProperty prop) {
		if (prop.getDefinition() != null && prop.getDefinition().getEnumerations() != null) {
			final ComboViewer viewer = new ComboViewer(parent, style);
			CompatibilityUtil.disableComboWheelScrollSelect(viewer);
			viewer.setLabelProvider(new LabelProvider() {
				@Override
				public String getText(final Object element) {
					return ScaSimplePropertyItemProvider.getValueText(prop, element);
				}
			});
			viewer.setContentProvider(new ArrayContentProvider());
			final List<Object> values = new ArrayList<Object>();
			for (final Enumeration e : prop.getDefinition().getEnumerations().getEnumeration()) {
				values.add(AnyUtils.convertString(e.getValue(), prop.getDefinition().getType().getLiteral(), prop.getDefinition().isComplex()));
			}
			viewer.setInput(values);
			return viewer;
		}
		return null;
	}
}

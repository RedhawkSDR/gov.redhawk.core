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

import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaSimpleSequenceProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.ScaStructSequenceProperty;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.provider.ScaSimplePropertyItemProvider;
import gov.redhawk.model.sca.util.ModelUtil;
import gov.redhawk.sca.internal.ui.properties.StructFieldPropertyColumnLabelProvider;
import gov.redhawk.sca.internal.ui.properties.StructFieldPropertyEditingSupport;
import gov.redhawk.sca.ui.compatibility.ColumnViewerToolTipSupport;
import gov.redhawk.sca.ui.compatibility.CompatibilityUtil;
import gov.redhawk.sca.ui.properties.AbstractPropertyEditingSupport;
import gov.redhawk.sca.ui.properties.ScaPropertiesContentProvider;

import java.util.ArrayList;
import java.util.List;

import mil.jpeojtrs.sca.prf.Enumeration;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.util.AnyUtils;

import org.apache.commons.lang.WordUtils;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationListener;
import org.eclipse.jface.viewers.ColumnViewerEditorDeactivationEvent;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TreeColumnViewerLabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
 * @since 8.0
 */
public final class ScaComponentFactory {

	private static class PropertyViewerComparator extends ViewerComparator {

		@Override
		public int compare(final Viewer viewer, final Object e1, final Object e2) {
			final Tree tree = ((Tree) viewer.getControl());

			if (tree.getSortDirection() == SWT.UP) {
				return super.compare(viewer, e1, e2);
			} else {
				return -1 * super.compare(viewer, e1, e2);
			}
		}
	}

	private ScaComponentFactory() {

	}

	/**
	 * @since 9.0
	 */
	public static TreeViewer createPropertyTable(final Composite parent, final int style, final AdapterFactory adapterFactory) {
		final TreeColumnLayout layout = new TreeColumnLayout();
		parent.setLayout(layout);
		final Tree tree = new Tree(parent, style | SWT.FULL_SELECTION);
		return ScaComponentFactory.createPropertyTreeViewer(tree, adapterFactory, layout);
	}

	/**
	 * @since 9.0
	 */
	public static TreeViewer createPropertyTable(final TabbedPropertySheetWidgetFactory widgetFactory, final Composite parent, final int style,
	        final AdapterFactory adapterFactory) {
		final TreeColumnLayout layout = new TreeColumnLayout();
		parent.setLayout(layout);
		final Tree tree = widgetFactory.createTree(parent, style | SWT.FULL_SELECTION);
		return ScaComponentFactory.createPropertyTreeViewer(tree, adapterFactory, layout);
	}

	private static TreeViewer createPropertyTreeViewer(final Tree tree, final AdapterFactory adapterFactory, final TreeColumnLayout layout) {
		final TreeViewer viewer = new TreeViewer(tree);
		viewer.getTree().setHeaderVisible(true);
		viewer.getTree().setLinesVisible(true);

		ColumnViewerToolTipSupport.enableFor(viewer);
		final Action revert = new Action("Revert to default") {
			@Override
			public void run() {
				final IStructuredSelection ss = (IStructuredSelection) viewer.getSelection();
				for (final Object o : ss.toList()) {
					if (o instanceof ScaAbstractProperty< ? >) {
						final ScaAbstractProperty< ? > prop = (ScaAbstractProperty< ? >) o;
						ScaModelCommand.execute(prop, new ScaModelCommand() {
							public void execute() {
								prop.restoreDefaultValue();
							}
						});
					}
				}
				viewer.refresh();
				viewer.setSelection(viewer.getSelection());
			}
		};

		viewer.getColumnViewerEditor().addEditorActivationListener(new ColumnViewerEditorActivationListener() {

			@Override
			public void beforeEditorDeactivated(final ColumnViewerEditorDeactivationEvent event) {

			}

			@Override
			public void beforeEditorActivated(final ColumnViewerEditorActivationEvent event) {

			}

			@Override
			public void afterEditorDeactivated(final ColumnViewerEditorDeactivationEvent event) {
				if (event.eventType == ColumnViewerEditorDeactivationEvent.EDITOR_SAVED) {
					viewer.refresh();
					viewer.setSelection(viewer.getSelection());
				}
			}

			@Override
			public void afterEditorActivated(final ColumnViewerEditorActivationEvent event) {

			}
		});

		revert.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_UNDO));
		revert.setDisabledImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_UNDO_DISABLED));

		viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(final SelectionChangedEvent event) {
				if (event.getSelection() instanceof IStructuredSelection) {
					final IStructuredSelection ss = (IStructuredSelection) event.getSelection();
					boolean enabled = true;
					for (final Object o : ss.toList()) {
						if (o instanceof ScaAbstractProperty< ? >) {
							final ScaAbstractProperty< ? > prop = (ScaAbstractProperty< ? >) o;
							enabled = !prop.isDefaultValue() && ModelUtil.isSettable(prop);
						} else {
							enabled = false;
						}
						if (!enabled) {
							break;
						}
					}
					revert.setEnabled(enabled);
				}
			}
		});

		final MenuManager menuMgr = new MenuManager("#Popup");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {

			public void menuAboutToShow(final IMenuManager manager) {
				manager.add(revert);
			}
		});
		final Menu menu = menuMgr.createContextMenu(viewer.getTree());
		viewer.getTree().setMenu(menu);

		//		this.viewer.getColumnViewerEditor().addEditorActivationListener(this.listener);
		final ScaPropertiesContentProvider contentProvider = new ScaPropertiesContentProvider(adapterFactory);
		viewer.setContentProvider(contentProvider);

		viewer.setComparator(new PropertyViewerComparator());
		viewer.getTree().setSortDirection(SWT.UP);

		final FontData[] fontData = viewer.getControl().getFont().getFontData();
		for (final FontData d : fontData) {
			CompatibilityUtil.setFontDataStyle(d, SWT.BOLD);
		}
		final Font nonDefaultFont = new Font(Display.getCurrent(), fontData);
		final ScaModelAdapterFactoryLabelProvider baseLabelProvider = new ScaModelAdapterFactoryLabelProvider(adapterFactory, viewer) {
			@Override
			public Font getFont(final Object object, final int columnIndex) {
				return getFont(object);
			}

			@Override
			public Font getFont(final Object object) {
				if (object instanceof ScaAbstractProperty< ? >) {
					final ScaAbstractProperty< ? > property = (ScaAbstractProperty< ? >) object;
					if (ModelUtil.isSettable(property) && !property.isDefaultValue()) {
						return nonDefaultFont;
					}
				}
				return super.getFont(object);
			}

			@Override
			public void dispose() {
				nonDefaultFont.dispose();
				super.dispose();
			}
		};
		viewer.setLabelProvider(baseLabelProvider);

		final TreeColumnViewerLabelProvider lp = new TreeColumnViewerLabelProvider(baseLabelProvider) {

			/**
			 * How many characters wide to allow a line to be in a tooltip before line wrapping it.
			 */
			private static final int TOOLTIP_WRAP_LEN = 80;

			@Override
			public String getToolTipText(final Object element) {
				final ScaAbstractProperty< ? > prop = (ScaAbstractProperty< ? >) element;

				StringBuilder sb = new StringBuilder();
				if (prop instanceof ScaSimpleProperty) {
					final String typeString = "< " + ((ScaSimpleProperty) prop).getDefinition().getType().getLiteral() + " >";
					sb.append(typeString);
				} else if (prop instanceof ScaSimpleSequenceProperty) {
					final String typeString = "< " + ((ScaSimpleSequenceProperty) prop).getDefinition().getType().getLiteral() + " >";
					sb.append(typeString);
				}

				String description = prop.getDescription();
				if (description != null) {
					if (sb.length() > 0) {
						sb.append("\n");
					}
					for (String line : description.split("\n")) {
						sb.append(WordUtils.wrap(line, TOOLTIP_WRAP_LEN, null, true));
						sb.append("\n");
					}
				}

				return sb.toString();
			}

			@Override
			public int getToolTipDisplayDelayTime(final Object object) {
				return 100;
			}

			@Override
			public int getToolTipTimeDisplayed(final Object object) {
				return 5000;
			}
		};
		TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.None);
		viewer.getTree().setSortColumn(column.getColumn());
		column.getColumn().setMoveable(false);
		column.getColumn().setResizable(true);
		column.getColumn().setText("Property");
		layout.setColumnData(column.getColumn(), new ColumnPixelData(250, column.getColumn().getResizable()));
		column.setLabelProvider(lp);
		column.getColumn().addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(final SelectionEvent e) {
				final Tree tree = viewer.getTree();
				if (tree.getSortDirection() == SWT.UP) {
					tree.setSortDirection(SWT.DOWN);
				} else {
					tree.setSortDirection(SWT.UP);
				}
				viewer.refresh();
			}
		});

		column = new TreeViewerColumn(viewer, SWT.None);
		column.getColumn().setMoveable(false);
		column.getColumn().setResizable(true);
		column.getColumn().setText("Value");
		layout.setColumnData(column.getColumn(), new ColumnPixelData(50, column.getColumn().getResizable()));
		column.setLabelProvider(lp);
		column.setEditingSupport(new AbstractPropertyEditingSupport(viewer, contentProvider) {

			@Override
			protected Object getPropertyID(final Object object) {
				if (object instanceof ScaSimpleProperty) {
					return ScaPackage.Literals.SCA_SIMPLE_PROPERTY__VALUE.getName();
				} else if (object instanceof ScaSimpleSequenceProperty) {
					return ScaPackage.Literals.SCA_SIMPLE_SEQUENCE_PROPERTY__VALUES.getName();
				} else if (object instanceof ScaStructProperty) {
					return ScaPackage.Literals.SCA_STRUCT_PROPERTY__SIMPLES.getName();
				} else if (object instanceof ScaStructSequenceProperty) {
					return ScaPackage.Literals.SCA_STRUCT_SEQUENCE_PROPERTY__STRUCTS.getName();
				}
				return null;
			}

		});

		return viewer;
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
		if (prop.getDefinition().getEnumerations() != null) {
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
				values.add(AnyUtils.convertString(e.getValue(), prop.getDefinition().getType().getLiteral()));
			}
			viewer.setInput(values);
			return viewer;
		}
		return null;
	}
}

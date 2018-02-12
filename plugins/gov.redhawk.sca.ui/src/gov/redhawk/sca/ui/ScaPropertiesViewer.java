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
package gov.redhawk.sca.ui;

import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.util.ModelUtil;
import gov.redhawk.sca.ui.compatibility.CompatibilityUtil;
import gov.redhawk.sca.ui.properties.AbstractPropertyEditingSupport;
import gov.redhawk.sca.ui.properties.ScaPropertiesAdapterFactory;
import gov.redhawk.sca.ui.properties.ScaPropertiesContentProvider;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationListener;
import org.eclipse.jface.viewers.ColumnViewerEditorDeactivationEvent;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
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
import org.eclipse.ui.views.properties.IPropertySourceProvider;

/**
 * @since 9.3
 */
public class ScaPropertiesViewer extends Composite {

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

	private TreeColumnLayout treeColumnLayout;
	private Action revertAction;
	private TreeViewer viewer;
	private AdapterFactory adapterFactory;

	public ScaPropertiesViewer(Composite parent, int style, int treeStyle, AdapterFactory adapterFactory) {
		super(parent, style);
		this.adapterFactory = adapterFactory;
		init(this, treeStyle | SWT.FULL_SELECTION);
	}

	public ScaPropertiesViewer(Composite parent, int style, int treeStyle) {
		this(parent, style, treeStyle, null);
	}

	protected TreeColumnLayout getTreeColumnLayout() {
		return treeColumnLayout;
	}

	protected void init(Composite parent, int style) {
		treeColumnLayout = new TreeColumnLayout();
		parent.setLayout(treeColumnLayout);

		viewer = createTreeViewer(parent, style);

		if (this.adapterFactory == null) {
			adapterFactory = createAdapterFactory();
		}

		ColumnViewerToolTipSupport.enableFor(viewer);

		createActions();

		addEditorActivationListener();

		createContextMenu();

		viewer.setContentProvider(createContentProvider());

		viewer.setComparator(createComparator());
		viewer.getTree().setSortDirection(SWT.UP);

		ILabelProvider rootLabelProvider = createRootLabelProvider();
		viewer.setLabelProvider(rootLabelProvider);

		createPropertyColumn(rootLabelProvider);

		createValueColumn(rootLabelProvider);
	}

	protected TreeViewer createTreeViewer(Composite parent, int style) {
		final Tree tree = new Tree(parent, style | SWT.FULL_SELECTION);
		TreeViewer retVal;
		if ((SWT.CHECK & tree.getStyle()) == SWT.CHECK) {
			retVal = new CheckboxTreeViewer(tree);
		} else {
			retVal = new TreeViewer(tree);
		}
		retVal.getTree().setHeaderVisible(true);
		retVal.getTree().setLinesVisible(true);
		return retVal;
	}

	protected void createValueColumn(ILabelProvider rootLabelProvider) {
		TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.None);
		column.getColumn().setMoveable(false);
		column.getColumn().setResizable(true);
		column.getColumn().setText("Value");
		treeColumnLayout.setColumnData(column.getColumn(), new ColumnPixelData(50, column.getColumn().getResizable()));
		column.setLabelProvider(createValueColumnLabelProvider(rootLabelProvider));
		column.setEditingSupport(createValueColumnEditingSupport());
	}

	protected AbstractPropertyEditingSupport createValueColumnEditingSupport() {
		return new ScaPropertiesEditingSupport(getViewer(), (IPropertySourceProvider) getViewer().getContentProvider());
	}

	protected CellLabelProvider createValueColumnLabelProvider(ILabelProvider rootLabelProvider) {
		return createPropertyColumnLabelProvider(rootLabelProvider);
	}

	protected TreeViewerColumn createPropertyColumn(ILabelProvider rootLabelProvider) {
		TreeViewerColumn column = new TreeViewerColumn(viewer, SWT.None);
		viewer.getTree().setSortColumn(column.getColumn());
		column.getColumn().setMoveable(false);
		column.getColumn().setResizable(true);
		column.getColumn().setText("Property");
		treeColumnLayout.setColumnData(column.getColumn(), new ColumnPixelData(250, column.getColumn().getResizable()));
		column.setLabelProvider(createPropertyColumnLabelProvider(rootLabelProvider));
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

		return column;
	}

	protected CellLabelProvider createPropertyColumnLabelProvider(ILabelProvider rootLabelProvider) {
		return new ScaPropertiesViewerColumnLabelProvider(rootLabelProvider);
	}

	protected ILabelProvider createRootLabelProvider() {
		final FontData[] fontData = viewer.getControl().getFont().getFontData();
		for (final FontData d : fontData) {
			CompatibilityUtil.setFontDataStyle(d, SWT.BOLD);
		}
		final Font nonDefaultFont = new Font(Display.getCurrent(), fontData);
		return new ScaModelAdapterFactoryLabelProvider(adapterFactory, viewer) {
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
	}

	protected ViewerComparator createComparator() {
		return new PropertyViewerComparator();
	}

	protected ITreeContentProvider createContentProvider() {
		return new ScaPropertiesContentProvider(adapterFactory);
	}

	protected AdapterFactory createAdapterFactory() {
		return new ScaPropertiesAdapterFactory();
	}

	protected void createContextMenu() {
		final MenuManager menuMgr = new MenuManager("#Popup");
		contributeContextMenuActions(menuMgr);

		final Menu menu = menuMgr.createContextMenu(viewer.getTree());
		viewer.getTree().setMenu(menu);
	}

	protected void contributeContextMenuActions(IMenuManager menuMgr) {
		menuMgr.add(new ActionContributionItem(revertAction));
	}

	protected void addEditorActivationListener() {
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
	}

	private void createActions() {
		revertAction = new Action("Revert to default") {
			@Override
			public void run() {
				final IStructuredSelection ss = (IStructuredSelection) viewer.getSelection();
				for (final Object o : ss.toList()) {
					if (o instanceof ScaAbstractProperty< ? >) {
						final ScaAbstractProperty< ? > prop = (ScaAbstractProperty< ? >) o;
						ScaModelCommand.execute(prop, new ScaModelCommand() {
							@Override
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
		revertAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_UNDO));
		revertAction.setDisabledImageDescriptor(PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_TOOL_UNDO_DISABLED));

		viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
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
					revertAction.setEnabled(enabled);
				}
			}
		});
	}

	public TreeViewer getViewer() {
		return viewer;
	}

	protected AdapterFactory getAdapterFactory() {
		return adapterFactory;
	}

	protected Action getRevertAction() {
		return revertAction;
	}
}

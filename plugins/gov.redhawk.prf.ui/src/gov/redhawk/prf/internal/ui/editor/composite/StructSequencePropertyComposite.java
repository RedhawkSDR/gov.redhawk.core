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
package gov.redhawk.prf.internal.ui.editor.composite;

import gov.redhawk.common.ui.editor.FormLayoutFactory;
import gov.redhawk.prf.ui.provider.PropertiesEditorPrfItemProviderAdapterFactory;
import gov.redhawk.prf.ui.provider.PropertiesEditorStructSequenceItemProvider;
import gov.redhawk.ui.util.SWTUtil;

import java.util.ArrayList;
import java.util.Collection;

import mil.jpeojtrs.sca.prf.PrfPackage;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IFormColors;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.views.properties.PropertyColumnLabelProvider;
import org.eclipse.ui.views.properties.PropertyEditingSupport;

public class StructSequencePropertyComposite extends BasicStructPropertyComposite {

	private TreeViewer structValueViewer;
	private final AdapterFactory adapterFactory;
	private Button addButton;
	private Button removeButton;

	public StructSequencePropertyComposite(final Composite parent, final int style, final FormToolkit toolkit) {
		super(parent, style, toolkit);
		this.adapterFactory = this.createAdapterFactory();
		createControls(this, toolkit);
	}
	
	protected void createControls(Composite parent, FormToolkit toolkit) {
		parent.setLayout(FormLayoutFactory.createSectionClientGridLayout(false, AbstractPropertyComposite.NUM_COLUMNS));

		createIDEntryField(toolkit, parent);
		createNameEntryField(toolkit, parent);
		createStructValueViewer(toolkit);
		createMessage(parent, toolkit);
		createConfigurationKindViewer(parent, toolkit);
		createModeViewer(parent, toolkit);
		createDescription(parent, toolkit);
		toolkit.paintBordersFor(parent);
		
		ArrayList<Control> tabList = new ArrayList<Control>();
		
		tabList.add(getIdEntry().getText());
		tabList.add(getNameEntry().getText());
		tabList.add(getMessageButton());
		tabList.add(this.structValueViewer.getControl().getParent());
		tabList.add(getConfigurationKindViewer().getControl());
		tabList.add(getModeViewer().getControl());
		tabList.add(getDescriptionText());
		
		parent.setTabList(tabList.toArray(new Control[tabList.size()]));
	}

	private void createStructValueViewer(final FormToolkit toolkit) {
		final Label label = toolkit.createLabel(this, "StructValue:");
		label.setForeground(toolkit.getColors().getColor(IFormColors.TITLE));
		label.setLayoutData(new GridData(GridData.BEGINNING, GridData.BEGINNING, false, false));
		final Composite treeComposite = toolkit.createComposite(this, SWT.NULL);
		final GridLayout layout = SWTUtil.TABLE_ENTRY_LAYOUT_FACTORY.create();
		treeComposite.setLayout(layout);
		treeComposite.setLayoutData(GridDataFactory.fillDefaults().hint(SWT.DEFAULT, 275).span(2, 1).grab(true, false).create());// SUPPRESS CHECKSTYLE MagicNumber
		final Tree tree = new Tree(treeComposite, SWT.MULTI | SWT.FULL_SELECTION | SWT.BORDER);
		tree.setLinesVisible(true);
		tree.setHeaderVisible(true);
		tree.setLayoutData(GridDataFactory.fillDefaults().span(1, 3).grab(true, true).create()); // SUPPRESS CHECKSTYLE MagicNumber

		final AdapterFactoryContentProvider contentProvider = new AdapterFactoryContentProvider(this.adapterFactory);
		final AdapterFactoryLabelProvider labelProvider = new AdapterFactoryLabelProvider(this.adapterFactory);
		this.structValueViewer = new TreeViewer(tree);
		this.structValueViewer.setColumnProperties(new String[] {
		        "refid", "value"
		});
		this.structValueViewer.setLabelProvider(new DecoratingLabelProvider(labelProvider, PlatformUI.getWorkbench().getDecoratorManager().getLabelDecorator()));
		this.structValueViewer.setContentProvider(contentProvider);
		TreeColumn column = new TreeColumn(tree, SWT.NONE);
		column = new TreeColumn(tree, SWT.NONE);
		column.setText("Name (Refid)");
		column.setWidth(175);// SUPPRESS CHECKSTYLE MagicNumber
		column = new TreeColumn(tree, SWT.NONE);
		column.setText("Value");
		column.setWidth(200);// SUPPRESS CHECKSTYLE MagicNumber
		TreeViewerColumn viewerColumn = new TreeViewerColumn(this.structValueViewer, column);
		viewerColumn = new TreeViewerColumn(this.structValueViewer, column);
		viewerColumn.setLabelProvider(new PropertyColumnLabelProvider(contentProvider, "value"));
		viewerColumn.setEditingSupport(new PropertyEditingSupport(this.structValueViewer, contentProvider, "value"));
		this.addButton = toolkit.createButton(treeComposite, "Add...", SWT.PUSH);
		this.addButton.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.TOP).create());
		this.removeButton = toolkit.createButton(treeComposite, "Remove", SWT.PUSH);
		this.removeButton.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.TOP).create());
		this.removeButton.setEnabled(false);
	}

	/**
	 * Create an adapter factory that yields item providers.
	 * 
	 * @return the composed adapter factory
	 */
	private AdapterFactory createAdapterFactory() {
		final ComposedAdapterFactory newAdapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		final PropertiesEditorPrfItemProviderAdapterFactory customAdapterFactory = new PropertiesEditorPrfItemProviderAdapterFactory() {
			@Override
			public Adapter createStructSequenceAdapter() {
				return new StructSequencePropertyCompositeItemProvider(getRootAdapterFactory());
			}
		};
		newAdapterFactory.addAdapterFactory(customAdapterFactory);
		newAdapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		newAdapterFactory.addAdapterFactory(new EcoreItemProviderAdapterFactory());
		newAdapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

		return newAdapterFactory;
	}

	public Button getAddButton() {
		return this.addButton;
	}

	public Button getRemoveButton() {
		return this.removeButton;
	}

	public StructuredViewer getStructValueViewer() {
		return this.structValueViewer;
	}

	/**
	 * Custom Item Provider for the StructSequencePropertyComposite.
	 */
	private static class StructSequencePropertyCompositeItemProvider extends PropertiesEditorStructSequenceItemProvider {

		public StructSequencePropertyCompositeItemProvider(final AdapterFactory adapterFactory) {
			super(adapterFactory);
		}

		/**
		 * Only the StructValues should be visible as children
		 * {@inheritDoc}
		 */
		@Override
		public Collection< ? extends EStructuralFeature> getChildrenFeatures(final Object object) {
			if (this.childrenFeatures == null) {
				super.getChildrenFeatures(object);
				this.childrenFeatures.remove(PrfPackage.Literals.STRUCT_SEQUENCE__STRUCT);
				this.childrenFeatures.add(PrfPackage.Literals.STRUCT_SEQUENCE__STRUCT_VALUE);
			}
			return this.childrenFeatures;
		}
	}

	@Override
	public void setEditable(final boolean canEdit) {
		super.setEditable(canEdit);
		if (this.structValueViewer != null) {
			this.structValueViewer.getTree().setEnabled(canEdit);
		}
		//		this.addButton.setEnabled(canEdit);
	}

}

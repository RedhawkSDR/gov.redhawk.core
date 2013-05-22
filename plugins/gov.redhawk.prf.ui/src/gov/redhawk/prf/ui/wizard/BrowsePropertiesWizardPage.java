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
package gov.redhawk.prf.ui.wizard;

import gov.redhawk.sca.properties.IPropertiesProviderDescriptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mil.jpeojtrs.sca.prf.AbstractProperty;
import mil.jpeojtrs.sca.prf.provider.PrfItemProviderAdapterFactory;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.dialogs.FilteredTree;

/**
 * This {@link WizardPage} provides a way to browse known properties.
 */
public class BrowsePropertiesWizardPage extends WizardPage {

	private static final String PAGE_NAME = "browsePropertyWizard";

	private List<EObject> properties = new ArrayList<EObject>();

	private TreeViewer propertyTree;

	private final GridLayoutFactory layoutFactory = GridLayoutFactory.fillDefaults().numColumns(2).equalWidth(false);

	private final GridDataFactory dataFactory = GridDataFactory.fillDefaults();

	private List<IPropertiesProviderDescriptor> descriptors;

	private DataBindingContext context = new EMFDataBindingContext();

	/**
	 * 
	 * @param descriptors
	 */
	public BrowsePropertiesWizardPage(final List<IPropertiesProviderDescriptor> descriptors) {
		super(BrowsePropertiesWizardPage.PAGE_NAME, "Browse Available Properties", null);
		this.descriptors = descriptors;
		this.setDescription("Browse Available Properties");
		setPageComplete(false);
	}

	/**
	 * 
	 * @return
	 */
	public List<EObject> getProperties() {
		return this.properties;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		this.context.dispose();
		super.dispose();
	}

	/**
	 * {@inheritDoc}
	 */
	public void createControl(final Composite parent) {

		final Composite client = new Composite(parent, SWT.NULL);
		client.setLayout(new GridLayout(2, false));

		final Control tree = createPropertyTree(client);
		tree.setLayoutData(this.dataFactory.hint(400, SWT.DEFAULT).grab(true, true).create()); // SUPPRESS CHECKSTYLE MagicNumber
		WizardPageSupport.create(this, this.context);
		this.setControl(client);
	}

	/**
	 * 
	 */
	private Control createPropertyTree(final Composite parent) {
		final Group treeGroup = new Group(parent, SWT.NULL);
		treeGroup.setText("Properties");
		treeGroup.setLayout(this.layoutFactory.numColumns(1).create());

		final BrowsePatternFilter patternFilter = new BrowsePatternFilter();
		patternFilter.setIncludeLeadingWildcard(false);

		final FilteredTree tree = new FilteredTree(treeGroup, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER, patternFilter, true);
		this.propertyTree = tree.getViewer();
		this.propertyTree.getControl().setLayoutData(this.dataFactory.span(1, 1).grab(true, true).create());

		final AdapterFactory myAdapterFactory = new PrfItemProviderAdapterFactory();
		final PropertiesBrowserContentProvider contentProvider = new PropertiesBrowserContentProvider(myAdapterFactory);

		this.propertyTree.setContentProvider(contentProvider);
		this.propertyTree.setLabelProvider(new PropertiesBrowserLabelProvider(myAdapterFactory));
		this.propertyTree.setSorter(new ViewerSorter());
		this.propertyTree.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				properties.clear();
				List<Object> selection = Arrays.asList(((IStructuredSelection) event.getSelection()).toArray());
				for (Object obj : selection) {
					if (obj instanceof AbstractProperty) {
						AbstractProperty prop = (AbstractProperty) obj;
						//If children of a selection are also selected, don't add them too.
						if (!selection.contains(prop.eContainer())) {
							properties.add(EcoreUtil.copy((EObject) obj));
						}

					}
				}
			}
		});
		this.propertyTree.setInput(this.descriptors);
		this.propertyTree.getControl().setLayoutData(this.dataFactory.grab(true, true).create());
		return treeGroup;
	}
}

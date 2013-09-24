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

import gov.redhawk.ui.validation.EmfValidationStatusProvider;
import mil.jpeojtrs.sca.prf.Enumeration;
import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.PrfPackage;

import org.eclipse.emf.databinding.EMFDataBindingContext;
import org.eclipse.emf.databinding.EMFObservables;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * The Class OsWizardPage.
 */
public class EnumerationWizardPage extends WizardPage {

	private static final String PAGE_NAME = "enumerationPage";

	private Enumeration enumeration = PrfFactory.eINSTANCE.createEnumeration();

	private final EMFDataBindingContext context = new EMFDataBindingContext();

	private WizardPageSupport pageSupport;

	private ComposedAdapterFactory adapterFactory;

	/**
	 * @param implementationSettings
	 * @param pageName
	 * @param title
	 * @param titleImage
	 * @since 2.0
	 */
	public EnumerationWizardPage() {
		super(EnumerationWizardPage.PAGE_NAME, "New Enumeration", null);
		this.setDescription("Set values of new Enumeration.");
		setPageComplete(false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		super.dispose();
		this.pageSupport.dispose();
		this.context.dispose();
		this.adapterFactory.dispose();
	}

	/**
	 * @return the enumeration
	 */
	public Enumeration getEnumeration() {
		return this.enumeration;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createControl(final Composite parent) {

		// Create an adapter factory that yields item providers.
		//
		this.adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		this.adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		this.adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

		Label label;
		Text text;

		final Composite client = new Composite(parent, SWT.NULL);
		client.setLayout(new GridLayout(2, false));

		label = new Label(client, SWT.NULL);
		label.setText("Label:");
		text = new Text(client, SWT.BORDER);
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL));
		text.setToolTipText("The Enumeration Label");

		// Bind and validate
		this.context.bindValue(SWTObservables.observeText(text, SWT.Modify),
		        EMFObservables.observeValue(this.enumeration, PrfPackage.Literals.ENUMERATION__LABEL), null, null);

		label = new Label(client, SWT.NULL);
		label.setText("Value:");
		text = new Text(client, SWT.BORDER);
		text.setToolTipText("The Enumeration Value");
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL));
		this.context.bindValue(SWTObservables.observeText(text, SWT.Modify),
		        EMFObservables.observeValue(this.enumeration, PrfPackage.Literals.ENUMERATION__VALUE), null, null);

		final EmfValidationStatusProvider provider = new EmfValidationStatusProvider(this.enumeration, this.context, this.adapterFactory);
		this.context.addValidationStatusProvider(provider);
		this.pageSupport = WizardPageSupport.create(this, this.context);

		this.setControl(client);
	}

	/**
	 * 
	 * @param enumeration
	 */
	public void setEnumeration(final Enumeration enumeration) {
		this.enumeration = EcoreUtil.copy(enumeration);
		this.setTitle("Edit Enumeration");
		this.setDescription("Edit Enumeration Values");
	}

}

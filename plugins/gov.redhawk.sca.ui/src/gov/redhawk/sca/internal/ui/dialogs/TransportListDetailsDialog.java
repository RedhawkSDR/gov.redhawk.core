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
package gov.redhawk.sca.internal.ui.dialogs;

import java.util.List;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.Disposable;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaTransport;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;
import gov.redhawk.sca.ui.ScaComponentFactory;
import gov.redhawk.sca.ui.properties.ScaPropertiesAdapterFactory;

/**
 * @since 11.0
 */
public class TransportListDetailsDialog extends Dialog {

	private Disposable disposable;
	private List< ? > transports;

	public TransportListDetailsDialog(Shell parentShell, List< ? > transports) {
		super(parentShell);
		this.transports = transports;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		this.getShell().setText("Transport Details");
		this.getShell().setMinimumSize(500, 400);

		// Adapter factories
		ScaItemProviderAdapterFactory adapterFactory = new ScaItemProviderAdapterFactory();
		ScaPropertiesAdapterFactory propAdapterFactory = new ScaPropertiesAdapterFactory();
		disposable = new Disposable();
		disposable.add(adapterFactory);
		disposable.add(propAdapterFactory);

		// Composite
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		composite.setLayout(layout);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		// Transport selection
		new Label(composite, SWT.NONE).setText("Transport:");
		ComboViewer comboViewer = new ComboViewer(composite, SWT.DROP_DOWN | SWT.READ_ONLY);
		comboViewer.getCombo().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		comboViewer.setContentProvider(ArrayContentProvider.getInstance());
		comboViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		comboViewer.setInput(transports);

		// Properties viewer
		Composite propertiesComposite = new Composite(composite, SWT.BORDER);
		propertiesComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());
		TreeViewer propertiesViewer = ScaComponentFactory.createPropertyTable(propertiesComposite, SWT.NONE, propAdapterFactory);
		ScaComponent propHolder = ScaFactory.eINSTANCE.createScaComponent();
		propHolder.getProperties();
		propertiesViewer.setInput(propHolder);

		// Selection handlers
		comboViewer.addSelectionChangedListener(event -> {
			ScaTransport transport = (ScaTransport) ((IStructuredSelection) event.getSelection()).getFirstElement();
			propHolder.getProperties().clear();
			ScaModelCommand.runExclusive(transport, () -> {
				for (ScaAbstractProperty< ? > prop : transport.getTransportProperties()) {
					propHolder.getProperties().add(EcoreUtil.copy(prop));
				}
				return null;
			});
		});

		// Initial selection
		if (transports.size() > 0) {
			comboViewer.setSelection(new StructuredSelection(transports.get(0)));
		}

		return composite;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.CLOSE_LABEL, true);
	}

	@Override
	public boolean close() {
		boolean returnValue = super.close();
		if (returnValue) {
			disposable.dispose();
			disposable = null;
		}
		return returnValue;
	}
}

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

import org.eclipse.emf.common.notify.AdapterFactory;
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
import org.eclipse.swt.widgets.Text;

import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaNegotiatedConnection;
import gov.redhawk.model.sca.ScaTransport;
import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;
import gov.redhawk.sca.ui.ScaComponentFactory;
import gov.redhawk.sca.ui.properties.ScaPropertiesAdapterFactory;

/**
 * @since 11.0
 */
public class TransportDetailsDialog extends Dialog {

	private List<ScaTransport> transports;
	private ScaNegotiatedConnection connection;

	private Disposable disposable;
	private Label description;

	/**
	 * Initializes the dialog to display details about a list of transport options.
	 * @param parentShell
	 * @param transports
	 */
	public TransportDetailsDialog(Shell parentShell, List<ScaTransport> transports) {
		super(parentShell);
		this.transports = transports;
	}

	/**
	 * Initializes the dialog to display details about a specific connection's transport.
	 * @param parentShell
	 * @param connection
	 */
	public TransportDetailsDialog(Shell parentShell, ScaNegotiatedConnection connection) {
		super(parentShell);
		this.connection = connection;
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		this.getShell().setText(Messages.TransportListDetailsDialog_ShellTitle);
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
		new Label(composite, SWT.NONE).setText(Messages.TransportListDetailsDialog_TransportLabel);
		ComboViewer transportDropDown = null;
		if (transports != null) {
			transportDropDown = createTransportDropDown(composite, adapterFactory);
		} else {
			createTransportText(composite);
		}

		// Description
		description = new Label(composite, SWT.WRAP);
		description.setLayoutData(GridDataFactory.fillDefaults().align(SWT.FILL, SWT.CENTER).grab(true, false).span(2, 1).create());

		// Properties viewer
		Composite propertiesComposite = new Composite(composite, SWT.BORDER);
		propertiesComposite.setLayoutData(GridDataFactory.fillDefaults().grab(true, true).span(2, 1).create());
		TreeViewer propertiesViewer = ScaComponentFactory.createPropertyTable(propertiesComposite, SWT.NONE, propAdapterFactory);
		ScaComponent propHolder = ScaFactory.eINSTANCE.createScaComponent();
		propHolder.getProperties();
		propertiesViewer.setInput(propHolder);

		if (transports != null) {
			// Selection handlers
			transportDropDown.addSelectionChangedListener(event -> {
				// Show the transport's properties
				ScaTransport transport = (ScaTransport) ((IStructuredSelection) event.getSelection()).getFirstElement();
				propHolder.getProperties().clear();
				propHolder.getProperties().addAll(transport.getTransportProperties());
				setDescriptionFor(transport.getTransportType());
			});

			// Set initial selection to first transport
			if (transports.size() > 0) {
				transportDropDown.setSelection(new StructuredSelection(transports.get(0)));
			}
		} else {
			// Show the connection's properties
			propHolder.getProperties().addAll(connection.getTransportInfo());
			setDescriptionFor(connection.getTransportType());
		}

		return composite;
	}

	private ComboViewer createTransportDropDown(Composite parent, AdapterFactory adapterFactory) {
		ComboViewer comboViewer = new ComboViewer(parent, SWT.DROP_DOWN | SWT.READ_ONLY);
		comboViewer.getCombo().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		comboViewer.setContentProvider(ArrayContentProvider.getInstance());
		comboViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
		comboViewer.setInput(transports);
		return comboViewer;
	}

	private void createTransportText(Composite parent) {
		Text transportText = new Text(parent, SWT.READ_ONLY | SWT.BORDER);
		transportText.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		transportText.setText(connection.getTransportType());
	}

	private void setDescriptionFor(String transportType) {
		if (transportType == null) {
			description.setText(Messages.TransportListDetailsDialog_EmptyDescription);
			return;
		}

		String newText;
		switch (transportType) {
		case "local": //$NON-NLS-1$
			newText = Messages.TransportListDetailsDialog_LocalTransportDescription;
			break;
		case "shmipc": //$NON-NLS-1$
			newText = Messages.TransportListDetailsDialog_SharedMemoryIPCTransportDescription;
			break;
		case "vita49": //$NON-NLS-1$
			newText = Messages.TransportListDetailsDialog_VITA49TransportDescription;
			break;
		case "CORBA": //$NON-NLS-1$
			newText = Messages.TransportListDetailsDialog_CORBATransportDescription;
			break;
		default:
			newText = Messages.TransportListDetailsDialog_UnknownTransportDescription;
		}
		description.setText(newText);
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

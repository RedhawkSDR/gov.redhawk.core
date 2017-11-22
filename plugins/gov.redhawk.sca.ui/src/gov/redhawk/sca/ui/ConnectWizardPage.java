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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.ValidationStatusProvider;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.navigator.CommonViewer;

import gov.redhawk.model.sca.CorbaObjWrapper;
import gov.redhawk.model.sca.ScaEventChannel;
import gov.redhawk.model.sca.ScaFileStore;
import gov.redhawk.model.sca.ScaFileSystem;
import gov.redhawk.model.sca.ScaProvidesPort;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.ScaWaveformFactory;
import gov.redhawk.model.sca.provider.ScaEventChannelsContainerItemProvider;
import gov.redhawk.model.sca.provider.ScaWaveformFactoriesContainerItemProvider;
import gov.redhawk.sca.ScaPlugin;
import gov.redhawk.sca.ui.views.ScaExplorer;
import gov.redhawk.sca.util.PropertyChangeSupport;

/**
 * @since 10.2
 */
public class ConnectWizardPage extends WizardPage {

	private class SourceTargetValidator extends ValidationStatusProvider implements PropertyChangeListener {
		private final WritableValue<IStatus> status = new WritableValue<IStatus>();
		private IObservableList<WritableValue<IStatus>> list = new WritableList<WritableValue<IStatus>>();
		{
			list.add(status);
		}

		@Override
		public IObservableValue<IStatus> getValidationStatus() {
			return status;
		}

		@Override
		public IObservableList<WritableValue<IStatus>> getTargets() {
			return list;
		}

		@Override
		public IObservableList< ? > getModels() {
			return null;
		}

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			if (source != null && target != null) {
				if (!target._is_a(source.getRepid())) {
					status.setValue(ValidationStatus.warning("Warning: Connection types are not an exact match, connection may not be possible. Target is not of type: " + source.getRepid()));
					return;
				}
			}
			status.setValue(ValidationStatus.ok());
		}
	}

	private SourceTargetValidator connectionValidator = new SourceTargetValidator();
	private static final String SDR_ROOT_CLASS = "gov.redhawk.ide.sdr.impl.SdrRootImpl";
	private ScaUsesPort source;
	private CorbaObjWrapper< ? > target;
	private String connectionID = ConnectPortWizard.generateDefaultConnectionID();
	private WizardPageSupport support;
	private DataBindingContext context;
	private PropertyChangeSupport pcs = new PropertyChangeSupport(this);
	private boolean connectionIDEnabled = true;
	private Object defaultInput = ScaPlugin.getDefault().getDomainManagerRegistry(Display.getCurrent());
	private boolean showAllInputs = true;
	private boolean showAllOutputs = true;
	private Object sourceInput = defaultInput;
	private Object targetInput = defaultInput;
	
	// A list of possible connection IDs, used by multi-out ports
	private Map<String, Boolean> connectionIds;
	private Text idText;
	private ComboViewer idCombo;

	protected ConnectWizardPage(Map<String, Boolean> connectionIds) {
		super("connectPage", "Create new connection", null);
		setDescription("Select the source and target connection elements.\nAlso enter a connection id.");
		pcs.addPropertyChangeListener(connectionValidator);
		this.connectionIds = connectionIds;
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(listener);
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(listener);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.None);
		composite.setLayout(new GridLayout(4, true));

		Group sourceGroup = new Group(composite, SWT.None);
		sourceGroup.setText("Source");
		sourceGroup.setLayout(new FillLayout());
		sourceGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).hint(SWT.DEFAULT, 200).create());
		int sourceViewerStyle = SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE | SWT.FULL_SELECTION;
		if (!isShowAllInputs() && getSource() != null) {
			sourceViewerStyle = sourceViewerStyle | SWT.READ_ONLY;
		}
		CommonViewer sourceViewer = new CommonViewer(ScaExplorer.VIEW_ID, sourceGroup, sourceViewerStyle);
		sourceViewer.addFilter(new ViewerFilter() {

			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				if (!isShowAllInputs()) {
					return element == source;
				}
				// This is a hack to filter out the sdr root.
				if (element.getClass().getName().equals(ConnectWizardPage.SDR_ROOT_CLASS)) {
					return false;
				} else if (element instanceof ScaProvidesPort) {
					return false;
				} else if (element instanceof ScaFileStore) {
					return false;
				} else if (element instanceof ScaWaveformFactory) {
					return false;
				} else if (element instanceof ScaWaveformFactoriesContainerItemProvider) {
					return false;
				} else if (element instanceof ScaEventChannelsContainerItemProvider) {
					return false;
				} else if (element instanceof ScaEventChannel) {
					return false;
				}
				return true;
			}
		});

		Group targetGroup = new Group(composite, SWT.None);
		targetGroup.setText("Target");
		targetGroup.setLayout(new FillLayout());
		targetGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).hint(SWT.DEFAULT, 200).create());
		int targetViewerStyle = SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.SINGLE | SWT.FULL_SELECTION;
		if (!isShowAllOutputs() && getTarget() != null) {
			targetViewerStyle = targetViewerStyle | SWT.READ_ONLY;
		}
		CommonViewer targetViewer = new CommonViewer(ScaExplorer.VIEW_ID, targetGroup, targetViewerStyle);
		targetViewer.addFilter(new ViewerFilter() {

			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				if (!isShowAllOutputs()) {
					return element == target;
				}
				// This is a hack to filter out the sdr root.
				if (element.getClass().getName().equals(ConnectWizardPage.SDR_ROOT_CLASS)) {
					return false;
				} else if (element instanceof ScaUsesPort) {
					return false;
				} else if (element instanceof ScaWaveformFactoriesContainerItemProvider) {
					return false;
				} else if (element instanceof ScaFileSystem< ? >) {
					return true;
				} else if (element instanceof ScaFileStore) {
					return false;
				}
				return true;
			}
		});

		Label label = new Label(composite, SWT.None);
		label.setText("Connection ID:");
		if (connectionIds.isEmpty()) {
			idText = new Text(composite, SWT.BORDER);
			idText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(3, 1).create());
		} else {
			idCombo = new ComboViewer(composite, SWT.BORDER | SWT.READ_ONLY);
			idCombo.getCombo().setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(3, 1).create());
			idCombo.getCombo().setToolTipText("Available mulit-out port connection IDs");
			idCombo.setContentProvider(ArrayContentProvider.getInstance());
			idCombo.setLabelProvider(new LabelProvider() {
				@Override
				public String getText(Object element) {
					if (element instanceof Entry) {
						return ((Entry< ? , ? >) element).getKey().toString();
					}
					return super.getText(element);
				}
			});
			idCombo.setInput(connectionIds.entrySet());
		}

		setControl(composite);

		context = new DataBindingContext();
		context.bindValue(ViewerProperties.input().observe(sourceViewer), BeanProperties.value("sourceInput").observe(this));
		context.bindValue(ViewerProperties.input().observe(targetViewer), BeanProperties.value("targetInput").observe(this));
		context.bindValue(ViewerProperties.singleSelection().observe(sourceViewer), BeanProperties.value("source").observe(this),
			createSourceTargetToModel(), null);
		context.bindValue(ViewerProperties.singleSelection().observe(targetViewer), BeanProperties.value("target").observe(this),
			createTargetTargetToModel(), null);
		if (idText != null) {
			context.bindValue(WidgetProperties.text(SWT.Modify).observe(idText), BeanProperties.value("connectionID").observe(this),
				createConnectionIDTextTargetToModel(), null);
			context.bindValue(WidgetProperties.enabled().observe(idText), BeanProperties.value("connectionIDReadOnly").observe(this));
		} else {
			context.bindValue(WidgetProperties.selection().observe(idCombo.getCombo()), BeanProperties.value("connectionID").observe(this),
				createConnectionIDComboTargetToModel(), null);
		}
		context.addValidationStatusProvider(connectionValidator);

		support = WizardPageSupport.create(this, context);

		// Set selection at first available connectionId
		// This needs to happen after the data bindings have been initialized
		if (idCombo != null) {
			for (Entry<String, Boolean> entry : connectionIds.entrySet()) {
				if (entry.getValue()) {
					idCombo.setSelection(new StructuredSelection(entry));
					return;
				}
			}
			// If we get here, then all the connections are in use, so default to the first one
			idCombo.setSelection(new StructuredSelection(connectionIds.entrySet().toArray()[0]));
		}
	}

	private UpdateValueStrategy createConnectionIDTextTargetToModel() {
		UpdateValueStrategy strategy = new UpdateValueStrategy();
		strategy.setAfterConvertValidator(new IValidator() {

			@Override
			public IStatus validate(Object value) {
				if (value == null || value.toString().trim().isEmpty()) {
					return ValidationStatus.error("Must enter a connection ID");
				}
				return ValidationStatus.ok();
			}

		});
		return strategy;
	}

	private UpdateValueStrategy createConnectionIDComboTargetToModel() {
		UpdateValueStrategy strategy = new UpdateValueStrategy();
		strategy.setAfterGetValidator(new IValidator() {

			@Override
			public IStatus validate(Object value) {
				Object element = idCombo.getStructuredSelection().getFirstElement();
				if (element instanceof Entry) {
					Boolean isValid = (Boolean) ((Entry< ? , ? >) element).getValue();
					if (!isValid) {
						return ValidationStatus.error("Selected connection ID is already in use");
					}
				}
				return ValidationStatus.ok();
			}
		});
		return strategy;
	}

	@Override
	public void dispose() {
		super.dispose();
		support.dispose();
		context.dispose();
	}

	private UpdateValueStrategy createTargetTargetToModel() {
		UpdateValueStrategy strategy = new UpdateValueStrategy();
		strategy.setAfterGetValidator(new IValidator() {

			@Override
			public IStatus validate(Object value) {
				if (!(value instanceof CorbaObjWrapper< ? >)) {
					return ValidationStatus.error("Target not specified.");
				}
				return ValidationStatus.ok();
			}

		});
		return strategy;
	}

	private UpdateValueStrategy createSourceTargetToModel() {
		UpdateValueStrategy strategy = new UpdateValueStrategy();
		strategy.setAfterGetValidator(new IValidator() {

			@Override
			public IStatus validate(Object value) {
				if (value instanceof ScaUsesPort) {
					return ValidationStatus.ok();
				}
				return ValidationStatus.error("Source must be of type 'Uses Port'");
			}

		});
		return strategy;
	}

	public String getConnectionID() {
		return connectionID;
	}

	public void setConnectionID(String connectionID) {
		String oldValue = this.connectionID;
		this.connectionID = connectionID;
		pcs.firePropertyChange("connectionID", oldValue, connectionID);
	}

	public ScaUsesPort getSource() {
		return source;
	}

	public void setSource(ScaUsesPort source) {
		ScaUsesPort oldValue = this.source;
		this.source = source;
		
		pcs.firePropertyChange("source", oldValue, source);
	}

	public CorbaObjWrapper< ? > getTarget() {
		return target;
	}

	public void setTarget(CorbaObjWrapper< ? > target) {
		CorbaObjWrapper< ? > oldValue = this.target;
		this.target = target;
		pcs.firePropertyChange("target", oldValue, target);
	}

	public void setConnectionIDReadOnly(boolean connectionIDReadOnly) {
		boolean oldValue = this.connectionIDEnabled;
		this.connectionIDEnabled = connectionIDReadOnly;
		pcs.firePropertyChange("connectionIDReadOnly", oldValue, connectionIDReadOnly);
	}

	public boolean isConnectionIDReadOnly() {
		return connectionIDEnabled;
	}

	public void setSourceInput(Object sourceInput) {
		if (sourceInput == null) {
			sourceInput = defaultInput;
		}
		Object oldValue = this.sourceInput;
		this.sourceInput = sourceInput;
		pcs.firePropertyChange("sourceInput", oldValue, sourceInput);
	}

	public Object getSourceInput() {
		return sourceInput;
	}

	public void setTargetInput(Object targetInput) {
		if (targetInput == null) {
			targetInput = defaultInput;
		}
		Object oldValue = this.targetInput;
		this.targetInput = targetInput;
		pcs.firePropertyChange("targetInput", oldValue, targetInput);
	}

	public Object getTargetInput() {
		return targetInput;
	}

	public boolean isShowAllInputs() {
		return showAllInputs;
	}

	public void setShowAllInputs(boolean showAllInputs) {
		this.showAllInputs = showAllInputs;
	}

	public boolean isShowAllOutputs() {
		return showAllOutputs;
	}

	public void setShowAllOutputs(boolean showAllOutputs) {
		this.showAllOutputs = showAllOutputs;
	}
}

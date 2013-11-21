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
package gov.redhawk.sca.internal.ui.handlers;

import gov.redhawk.model.sca.CorbaObjWrapper;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaProvidesPort;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.sca.ScaPlugin;
import gov.redhawk.sca.ui.views.ScaExplorer;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewerProperties;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.Wizard;
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

/**
 * 
 */
public class ConnectPortWizard extends Wizard {
	private static class ConnectWizardPage extends WizardPage {

		private static final String SDR_ROOT_CLASS = "gov.redhawk.ide.sdr.impl.SdrRootImpl";
		private ScaUsesPort source;
		private CorbaObjWrapper< ? > target;
		private String connectionID;
		private WizardPageSupport support;
		private DataBindingContext context;

		protected ConnectWizardPage() {
			super("connectPage", "Create new connection", null);
			setDescription("Select the source and target connection elements.  Also enter a connection id");
		}

		@Override
		public void createControl(Composite parent) {
			Composite composite = new Composite(parent, SWT.None);
			composite.setLayout(new GridLayout(4, false));

			Group sourceGroup = new Group(composite, SWT.None);
			sourceGroup.setText("Source");
			sourceGroup.setLayout(new FillLayout());
			sourceGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).hint(SWT.DEFAULT, 200).create());
			CommonViewer sourceViewer = new CommonViewer(ScaExplorer.VIEW_ID, sourceGroup, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
			sourceViewer.setInput(ScaPlugin.getDefault().getDomainManagerRegistry(Display.getCurrent()));
			sourceViewer.addFilter(new ViewerFilter() {

				@Override
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					// This is a hack to filter out the sdr root.
					if (element.getClass().getName().equals(SDR_ROOT_CLASS)) {
						return false;
					}
					return true;
				}
			});

			Group targetGroup = new Group(composite, SWT.None);
			targetGroup.setText("Target");
			targetGroup.setLayout(new FillLayout());
			targetGroup.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(2, 1).hint(SWT.DEFAULT, 200).create());
			CommonViewer targetViewer = new CommonViewer(ScaExplorer.VIEW_ID, targetGroup, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
			targetViewer.setInput(ScaPlugin.getDefault().getDomainManagerRegistry(Display.getCurrent()));
			targetViewer.addFilter(new ViewerFilter() {

				@Override
				public boolean select(Viewer viewer, Object parentElement, Object element) {
					// This is a hack to filter out the sdr root.
					if (element.getClass().getName().equals(SDR_ROOT_CLASS)) {
						return false;
					}
					return true;
				}
			});

			Label label = new Label(composite, SWT.None);
			label.setText("Connection ID:");
			Text idText = new Text(composite, SWT.BORDER);
			idText.setLayoutData(GridDataFactory.fillDefaults().grab(true, false).span(3, 1).create());

			setControl(composite);

			context = new DataBindingContext();
			context.bindValue(ViewerProperties.singleSelection().observe(sourceViewer), PojoProperties.value("source").observe(this),
				createSourceTargetToModel(), null);
			context.bindValue(ViewerProperties.singleSelection().observe(targetViewer), PojoProperties.value("target").observe(this),
				createTargetTargetToModel(), null);
			context.bindValue(WidgetProperties.text(SWT.Modify).observe(idText), PojoProperties.value("connectionID").observe(this),
				createConnectionIDTargetToModel(), null);

			if (source != null) {
				sourceViewer.reveal(source);
			}

			if (target != null) {
				targetViewer.reveal(target);
			}
			
			support = WizardPageSupport.create(this, context);
		}

		private UpdateValueStrategy createConnectionIDTargetToModel() {
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
					if (value instanceof ScaProvidesPort) {
						return ValidationStatus.ok();
					}
					if (value instanceof ScaComponent) {
						return ValidationStatus.ok();
					}
					return ValidationStatus.error("Target must be of type 'Provides Port' or 'Resource'");
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
			this.connectionID = connectionID;
		}

		public ScaUsesPort getSource() {
			return source;
		}

		public void setSource(ScaUsesPort source) {
			this.source = source;
		}

		public CorbaObjWrapper< ? > getTarget() {
			return target;
		}

		public void setTarget(CorbaObjWrapper< ? > target) {
			this.target = target;
		}

	}

	private ConnectWizardPage page = new ConnectWizardPage();

	public ConnectPortWizard() {
		setWindowTitle("Connect");
	}

	@Override
	public void addPages() {
		addPage(page);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		return true;
	}

	public String getConnectionID() {
		return page.connectionID;
	}

	public void setConnectionID(String connectionID) {
		page.setConnectionID(connectionID);
	}

	public ScaUsesPort getSource() {
		return page.source;
	}

	public void setSource(ScaUsesPort source) {
		page.source = source;
	}

	public CorbaObjWrapper< ? > getTarget() {
		return page.target;
	}

	public void setTarget(CorbaObjWrapper< ? > target) {
		page.target = target;
	}

}

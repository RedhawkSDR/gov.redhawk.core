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

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TrayDialog;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.statushandlers.StatusManager;

/**
 * Displays details about Status.
 * Event information is split in three sections: details, stack trace. Details
 * contain event message and severity. Stack trace is displayed if an exception is bound
 * to event. 
 * @since 7.0
 */
public class EventDetailsDialog extends TrayDialog {

	private final IStatus status;

	private static final int SAVE_SELECTED_STATUS = IDialogConstants.CLIENT_ID + 1;
	private static final int SAVE_ROOT_STATUS = IDialogConstants.CLIENT_ID;
	
	private Label severityImageLabel;
	private Label severityLabel;
	private Text msgText;
	private Text stackTraceText;

	// location configuration
	private IStatus selection;
	private TreeViewer multiStatusViewer;

	/**
	 * 
	 * @param parentShell shell in which dialog is displayed
	 * @param selection entry initially selected and to be displayed
	 * @param provider viewer
	 * @param comparator comparator used to order all entries
	 */
	public EventDetailsDialog(final Shell parentShell, final IStatus status) {
		super(parentShell);
		this.status = status;
		setShellStyle(SWT.MODELESS | SWT.MIN | SWT.MAX | SWT.RESIZE | SWT.CLOSE | SWT.BORDER | SWT.TITLE);
	}

	@Override
	public void create() {
		super.create();
		
		final int SHELL_WIDTH = 500;
		final int SHELL_HEIGHT = 550;
		getShell().setSize(SHELL_WIDTH, SHELL_HEIGHT);
		getShell().setText("Event Details");

		Dialog.applyDialogFont(this.buttonBar);
		getButton(IDialogConstants.OK_ID).setFocus();
	}

	@Override
	protected void buttonPressed(final int buttonId) {
		if (IDialogConstants.OK_ID == buttonId) {
			okPressed();
		} else if (IDialogConstants.CANCEL_ID == buttonId) {
			cancelPressed();
		} else if (EventDetailsDialog.SAVE_ROOT_STATUS == buttonId) {
			copyPressed(this.status);
		} else if (EventDetailsDialog.SAVE_SELECTED_STATUS == buttonId) {
			copyPressed(this.selection);
		}
	}

	protected void copyPressed(final IStatus entry) {
		if (entry != null) {
			StatusManager.getManager().handle(entry, StatusManager.LOG);
		}
	}

	public void updateProperties() {
		if (this.selection != null) {
			this.severityImageLabel.setImage(getSeverityImage(this.selection));
			this.severityLabel.setText(getSeverityText(this.selection));
			this.msgText.setText(this.selection.getMessage());
			String stack = null;
			if (this.selection.getException() != null) {
				final StringWriter writer = new StringWriter();
				final PrintWriter pwriter = new PrintWriter(writer);
				this.selection.getException().printStackTrace(pwriter);// SUPPRESS CHECKSTYLE LOG 
				stack = writer.toString();
			}

			if (stack != null) {
				this.stackTraceText.setText(stack);
			} else {
				this.stackTraceText.setText("No stack");
			}
		} else {
			this.severityImageLabel.setImage(null);
			this.severityLabel.setText("");
			this.msgText.setText("");
			this.stackTraceText.setText("");
		}
	}

	private Image getSeverityImage(final IStatus entry) {
		switch (entry.getSeverity()) {
		case IStatus.ERROR:
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_ERROR_TSK);
		case IStatus.INFO:
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_INFO_TSK);
		case IStatus.WARNING:
			return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_WARN_TSK);
		case IStatus.OK:
		case IStatus.CANCEL:
		default:
			break;
		}
		return null;
	}

	private String getSeverityText(final IStatus entry) {
		switch (entry.getSeverity()) {
		case IStatus.ERROR:
			return "Error";
		case IStatus.INFO:
			return "Info";
		case IStatus.WARNING:
			return "Warning";
		case IStatus.OK:
			return "Ok";
		case IStatus.CANCEL:
			return "Cancel";
		default:
			return "";
		}
	}

	@Override
	protected Control createDialogArea(final Composite parent) {
		final Composite container = new Composite(parent, SWT.NONE);
		final GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		container.setLayout(layout);
		final GridData gd = new GridData(GridData.FILL_BOTH);
		container.setLayoutData(gd);

		if (this.status.isMultiStatus()) {
			createMultiStatusSection(container);
		} else {
			this.selection = this.status;
		}
		createDetailsSection(container);
		final SashForm sashForm = createSashForm(container);
		createStackSection(sashForm);

		updateProperties();
		Dialog.applyDialogFont(container);
		return container;
	}

	private void createMultiStatusSection(final Composite parent) {
		final int MULTISTATUS_MARGIN = 0;
		final int MULTISTATUS_PREFERRED_HEIGHT = 50;
		final Group container = new Group(parent, SWT.NONE);
		final GridLayout layout = new GridLayout();
		layout.marginWidth = MULTISTATUS_MARGIN;
		layout.marginHeight = MULTISTATUS_MARGIN;
		layout.numColumns = 2;
		container.setLayout(layout);
		container.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		final GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = MULTISTATUS_PREFERRED_HEIGHT;
		container.setLayoutData(gd);
		container.setText("Multi-Status");

		createMultiStatusTreeSection(container);
		createToolbarButtonBar(container, SAVE_ROOT_STATUS);
	}

	private void createMultiStatusTreeSection(final Composite parent) {
		this.multiStatusViewer = new TreeViewer(parent, SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER | SWT.FULL_SELECTION);
		final GridData gd = new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL);
		gd.grabExcessHorizontalSpace = true;
		this.multiStatusViewer.getControl().setLayoutData(gd);
		this.multiStatusViewer.setContentProvider(new ITreeContentProvider() {

			public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
				// TODO Auto-generated method stub

			}

			public void dispose() {
				// TODO Auto-generated method stub

			}

			public boolean hasChildren(final Object element) {
				if (element instanceof MultiStatus) {
					final MultiStatus s = (MultiStatus) element;
					return s.getChildren().length > 0;
				}
				return false;
			}

			public Object getParent(final Object element) {
				return null;
			}

			public Object[] getElements(final Object element) {
				if (element instanceof MultiStatus) {
					final MultiStatus s = (MultiStatus) element;
					return s.getChildren();
				} else {
					return Collections.emptyList().toArray();
				}
			}

			public Object[] getChildren(final Object parentElement) {
				// TODO Auto-generated method stub
				return getElements(parentElement);
			}
		});
		this.multiStatusViewer.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(final Object element) {
				return ((IStatus) element).getMessage();
			}

			@Override
			public Image getImage(final Object element) {
				return getSeverityImage((IStatus) element);
			}
		});
		this.multiStatusViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(final SelectionChangedEvent event) {
				EventDetailsDialog.this.selection = (IStatus) ((IStructuredSelection) event.getSelection()).getFirstElement();
				updateProperties();
			}
		});
		this.multiStatusViewer.setInput(this.status);
	}

	private SashForm createSashForm(final Composite parent) {
		final int SASH_FORM_MARGIN = 0;
		final SashForm sashForm = new SashForm(parent, SWT.VERTICAL);
		final GridLayout layout = new GridLayout();
		layout.marginWidth = SASH_FORM_MARGIN;
		layout.marginHeight = SASH_FORM_MARGIN;
		sashForm.setLayout(layout);
		sashForm.setLayoutData(new GridData(GridData.FILL_BOTH));
		return sashForm;
	}

	private void createToolbarButtonBar(final Composite parent, final int saveId) {
		final int TOOLBAR_BUTTON_MARGIN = 0;
		final Composite comp = new Composite(parent, SWT.NONE);
		final GridLayout layout = new GridLayout();
		layout.marginWidth = TOOLBAR_BUTTON_MARGIN;
		layout.marginHeight = TOOLBAR_BUTTON_MARGIN;
		layout.numColumns = 1;
		comp.setLayout(layout);
		comp.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		((GridData) comp.getLayoutData()).verticalAlignment = SWT.TOP;

		final Button saveButton = createButton(comp, saveId, "", false); //$NON-NLS-1$
		final GridData gd = new GridData();
		saveButton.setLayoutData(gd);
		saveButton.setImage(PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_ETOOL_SAVE_EDIT));
		saveButton.setToolTipText("Save event details to the log.");
	}

	@Override
	protected void createButtonsForButtonBar(final Composite parent) {
		// create OK button only by default
		/** RAP DEPENDENCY ISSUE **/
		//Static member not available in RAP
		createButton(parent, IDialogConstants.OK_ID, /*IDialogConstants.OK_LABEL*/"Ok", true);
	}

	private void createDetailsSection(final Composite parent) {
		final int DETAILS_MARGIN = 0;
		final Composite container = new Composite(parent, SWT.NONE);
		final GridLayout layout = new GridLayout();
		layout.marginWidth = DETAILS_MARGIN;
		layout.marginHeight = DETAILS_MARGIN;
		layout.numColumns = 2;
		container.setLayout(layout);
		container.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		createTextSection(container);
		createToolbarButtonBar(container, SAVE_SELECTED_STATUS);
	}

	private void createTextSection(final Composite parent) {
		final int TEXT_CONTAINER_MARGIN = 0;
		final int TEXT_CONTAINER_COLUMNS = 3;
		final Composite textContainer = new Composite(parent, SWT.NONE);
		final GridLayout layout = new GridLayout();
		layout.numColumns = TEXT_CONTAINER_COLUMNS;
		layout.marginWidth = TEXT_CONTAINER_MARGIN;
		layout.marginHeight = TEXT_CONTAINER_MARGIN;
		textContainer.setLayout(layout);
		textContainer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label label = new Label(textContainer, SWT.NONE);
		label.setText("Severity:");
		this.severityImageLabel = new Label(textContainer, SWT.NULL);
		this.severityLabel = new Label(textContainer, SWT.NULL);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		this.severityLabel.setLayoutData(gd);

		label = new Label(textContainer, SWT.NONE);
		label.setText("Message:");
		gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		label.setLayoutData(gd);
		
		final int MSG_TEXT_PREFERRED_HEIGHT = 44;
		this.msgText = new Text(textContainer, SWT.MULTI | SWT.V_SCROLL | SWT.WRAP | SWT.BORDER);
		this.msgText.setEditable(false);
		gd = new GridData(GridData.FILL_BOTH | GridData.VERTICAL_ALIGN_BEGINNING | GridData.GRAB_VERTICAL);
		gd.horizontalSpan = 2;
		gd.heightHint = MSG_TEXT_PREFERRED_HEIGHT;
		gd.grabExcessVerticalSpace = true;
		this.msgText.setLayoutData(gd);
	}

	private void createStackSection(final Composite parent) {
		final int STACK_SECTION_MARGIN_WIDTH = 6;
		final int STACK_SECTION_MARGIN_HEIGHT = 0;
		final int STACK_SECTION_PREFERRED_HEIGHT = 100;
		final Composite container = new Composite(parent, SWT.NONE);
		final GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = STACK_SECTION_MARGIN_HEIGHT;
		layout.marginWidth = STACK_SECTION_MARGIN_WIDTH;
		container.setLayout(layout);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = STACK_SECTION_PREFERRED_HEIGHT;
		container.setLayoutData(gd);

		final Label label = new Label(container, SWT.NONE);
		label.setText("Exception Stack Trace:");
		gd = new GridData();
		gd.verticalAlignment = SWT.BOTTOM;
		label.setLayoutData(gd);

		this.stackTraceText = new Text(container, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
		gd = new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL);
		gd.grabExcessHorizontalSpace = true;
		gd.horizontalSpan = 2;
		this.stackTraceText.setLayoutData(gd);
		this.stackTraceText.setEditable(false);
	}
}

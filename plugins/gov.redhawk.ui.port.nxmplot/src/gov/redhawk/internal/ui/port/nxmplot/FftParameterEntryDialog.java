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
package gov.redhawk.internal.ui.port.nxmplot;

import gov.redhawk.ui.port.nxmplot.FftSettings;
import gov.redhawk.ui.port.nxmplot.FftSettings.OutputType;
import gov.redhawk.ui.port.nxmplot.FftSettings.WindowType;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * FFT parameters/settings user entry dialog.
 * @noreference This class is not intended to be referenced by clients
 */
public class FftParameterEntryDialog extends Dialog {

	private static class OverlapValidator implements IInputValidator {
		@Override
		public String isValid(final String newText) {
			String status = "Percent Overlap must be between 0 and 100.";
			if ((newText != null) && !"".equals(newText.trim())) {
				try {
					double d = Double.parseDouble(newText.trim());
					if ((d <= 100.0) && (d >= 0.0)) { // SUPPRESS CHECKSTYLE MagicNumber
						status = null;
					}
				} catch (final NumberFormatException n) {
					status = "Invalid character in Percent Overlap.";
				}
			}
			return status;
		}
	}

	/** The validator for the overlapField */
	private static final OverlapValidator OVERLAP_VALIDATOR = new OverlapValidator();
	/** The validator for the {@link #transformSizeField}. */
	private static final MinIntegerValidator TRANSFORM_SIZE_VALIDATOR = new MinIntegerValidator("Transform Size", 2, null);
	/** The validator for the {@link #numAveragesField}. */
	private static final MinIntegerValidator NUM_AVERAGES_VALIDATOR = new MinIntegerValidator("Num Averages", 1, null);
	
	/** The settings for calculating the FFT */
	private final FftSettings fftSettings;
	/** The size of the transform to perform */
	private Text transformSizeField;
	/** The percentage of overlap for FFTing */
	private Text overlapField;
	/** The number of averages for FFTing */
	private Text numAveragesField;
	/** Error message label widget. */
	private Text errorMessageText;
	/** Error message string. */
	private String errorMessage;
	private boolean disableChangeOutputType;

	/**
	 * Instantiates a new manual stream parameter entry dialog.
	 *
	 * @param parentShell the parent shell
	 */
	public FftParameterEntryDialog(final Shell parentShell, final FftSettings settings) {
		super(parentShell);
		this.fftSettings = settings;
	}

	@Override
	protected void configureShell(final Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("FFT Settings");
	}

	@Override
	protected Control createDialogArea(final Composite parent) {
		final Composite container = (Composite) super.createDialogArea(parent);
		final GridLayout gridLayout = new GridLayout(2, false);
		container.setLayout(gridLayout);

		this.errorMessageText = new Text(container, SWT.READ_ONLY | SWT.WRAP);
		this.errorMessageText.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 2, 1));
		this.errorMessageText.setBackground(this.errorMessageText.getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		this.errorMessageText.setForeground(this.errorMessageText.getDisplay().getSystemColor(SWT.COLOR_RED));
		// Set the error message text
		// See https://bugs.eclipse.org/bugs/show_bug.cgi?id=66292
		setErrorMessage(this.errorMessage);

		final Label transformSizeLabel = new Label(container, SWT.NONE);
		transformSizeLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false));
		transformSizeLabel.setText("Transform Size:");

		this.transformSizeField = new Text(container, SWT.BORDER);
		this.transformSizeField.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		this.transformSizeField.setText(this.fftSettings.getTransformSize());
		this.transformSizeField.setToolTipText("Performance is best if factorable by 2, 3, 4 and 5.");
		this.transformSizeField.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(final ModifyEvent e) {
				FftParameterEntryDialog.this.fftSettings.setTransformSize(FftParameterEntryDialog.this.transformSizeField.getText().trim());
				validateInputs();
			}
		});

		final Label percentOverlapLabel = new Label(container, SWT.NONE);
		percentOverlapLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false));
		percentOverlapLabel.setText("Percent Overlap:");

		this.overlapField = new Text(container, SWT.BORDER);
		this.overlapField.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		this.overlapField.setText(this.fftSettings.getOverlap());
		this.overlapField.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(final ModifyEvent e) {
				final String newText = FftParameterEntryDialog.this.overlapField.getText();
				FftParameterEntryDialog.this.fftSettings.setOverlap(newText.trim());
				validateInputs();
			}
		});

		final Label numAveragesLabel = new Label(container, SWT.NONE);
		numAveragesLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false));
		numAveragesLabel.setText("Num Averages:");

		this.numAveragesField = new Text(container, SWT.BORDER);
		this.numAveragesField.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 1, 1));
		this.numAveragesField.setText(this.fftSettings.getNumAverages());
		this.numAveragesField.setToolTipText("Avoid using large value as it will cause highlighted energy to remain longer.");
		this.numAveragesField.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(final ModifyEvent e) {
				FftParameterEntryDialog.this.fftSettings.setNumAverages(FftParameterEntryDialog.this.numAveragesField.getText().trim());
				validateInputs();
			}
		});

		final Label typeLabel = new Label(container, SWT.NONE);
		typeLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false));
		typeLabel.setText("FFT Output Type:");

		final ComboViewer type = new ComboViewer(container, SWT.READ_ONLY);
		type.getCombo().setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 1, 1));
		type.setContentProvider(new ArrayContentProvider());
		type.setLabelProvider(new LabelProvider());
		OutputType currentOutputType = this.fftSettings.getOutputType();
		if (currentOutputType == null) {
			currentOutputType = FftSettings.OutputType.PSD; // default to PSD
		}
		type.setInput(FftSettings.OutputType.values());
		type.setSelection(new StructuredSelection(currentOutputType));
		if (disableChangeOutputType) {
			type.getCombo().setEnabled(false); // disable changing output type
		} else {
			type.addSelectionChangedListener(new ISelectionChangedListener() {
				@Override
				public void selectionChanged(final SelectionChangedEvent event) {
					FftParameterEntryDialog.this.fftSettings
					        .setOutputType((FftSettings.OutputType) ((IStructuredSelection) event.getSelection()).getFirstElement());
				}
			});
		}

		final Label windowLabel = new Label(container, SWT.NONE);
		windowLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false));
		windowLabel.setText("Window:");

		final ComboViewer window = new ComboViewer(container, SWT.READ_ONLY);
		window.getCombo().setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 1, 1));
		window.setContentProvider(new ArrayContentProvider());
		window.setLabelProvider(new LabelProvider());
		window.setInput(FftSettings.WindowType.values());
		WindowType currentWindowType = this.fftSettings.getWindowType();
		if (currentWindowType == null) {
			currentWindowType = FftSettings.WindowType.HANNING;
		} 
		window.setSelection(new StructuredSelection(currentWindowType));
		window.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(final SelectionChangedEvent event) {
				FftParameterEntryDialog.this.fftSettings.setWindow((FftSettings.WindowType) ((IStructuredSelection) event.getSelection()).getFirstElement());
			}
		});

		Dialog.applyDialogFont(container);

		return container;
	}

	/**
	 * Gets the FFT settings created by this dialog.
	 *
	 * @return the FFT settings
	 */
	public FftSettings getFFTSettings() {
		return this.fftSettings;
	}

	/**
	 * Validates user input fields (overlap, transform size, num averages) and
	 * calls {@link #setErrorMessage(String)} with combined error messages for those input fields.
	 */
	private void validateInputs() {
		String overlapErrMsg = OVERLAP_VALIDATOR.isValid(overlapField.getText());
		String fftSizeErrmsg = TRANSFORM_SIZE_VALIDATOR.isValid(transformSizeField.getText());
		String numAvgErrMsg  = NUM_AVERAGES_VALIDATOR.isValid(numAveragesField.getText());
		String errorMsg      = overlapErrMsg;
		if (fftSizeErrmsg != null) {
			if (errorMsg == null) {
				errorMsg = fftSizeErrmsg;
			} else {
				errorMsg += "\n" + fftSizeErrmsg;
			}
		}
		if (numAvgErrMsg != null) {
			if (errorMsg == null) {
				errorMsg = numAvgErrMsg;
			} else {
				errorMsg += "\n" + numAvgErrMsg;
			}
		}
		setErrorMessage(errorMsg);
	}

	/**
	 * Sets or clears the error message. If not <code>null</code>, the OK button is disabled.
	 *
	 * @param errorMessage the error message, or <code>null</code> to clear
	 * @since 3.0
	 */
	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
		if (this.errorMessageText != null && !this.errorMessageText.isDisposed()) {
			this.errorMessageText.setText(errorMessage == null ? " \n " : errorMessage); //$NON-NLS-1$ SUPPRESS CHECKSTYLE Inline
			// Disable the error message text control if there is no error, or
			// no error text (empty or whitespace only).  Hide it also to avoid
			// color change.
			// See https://bugs.eclipse.org/bugs/show_bug.cgi?id=130281
			final boolean hasError = errorMessage != null && (StringConverter.removeWhiteSpaces(errorMessage)).length() > 0;
			this.errorMessageText.setEnabled(hasError);
			this.errorMessageText.setVisible(hasError);
			this.errorMessageText.getParent().update();
			// Access the ok button by id, in case clients have overridden button creation.
			// See https://bugs.eclipse.org/bugs/show_bug.cgi?id=113643
			final Control button = getButton(IDialogConstants.OK_ID);
			if (button != null) {
				button.setEnabled(errorMessage == null);
			}
		}
	}

	/**
	 * @param disableChangeOutputType
	 * @noreference This method is not intended to be referenced by clients.
	 */
	public void setDisableChangeOutputType(boolean disableChangeOutputType) {
		this.disableChangeOutputType = disableChangeOutputType;
	}

	
}

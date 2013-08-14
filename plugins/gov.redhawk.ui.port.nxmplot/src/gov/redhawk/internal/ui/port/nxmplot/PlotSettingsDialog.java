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

import gov.redhawk.ui.port.nxmplot.PlotSettings;
import gov.redhawk.ui.port.nxmplot.PlotType;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Adjust/override PLOT settings user entry dialog.
 * @since 4.2
 */
public class PlotSettingsDialog extends Dialog {

	private final PlotSettings plotSettings;
	private ComboViewer frameSizeField;
	private ComboViewer minField;
	private ComboViewer maxField;
	private ComboViewer sampleRateField;
	private ComboViewer plotTypeField;
	/** Error message label widget. */
	private Text errorMessageText;
	/** Error message string. */
	private String errorMessage;

	/** The validator for the {@link #frameSizeField}. */
	private static final MinIntegerValidator FRAME_SIZE_VALIDATOR = new MinIntegerValidator("Frame Size", 1, "default");
	/** The validator for the {@link #minField}. */
	private static final CustomDoubleValidator MIN_VALUE_VALIDATOR = new CustomDoubleValidator("Min Value", "AutoMin");
	/** The validator for the {@link #maxField}. */
	private static final CustomDoubleValidator MAX_VALUE_VALIDATOR = new CustomDoubleValidator("Max Value", "AutoMax");
	/** The validator for the {@link #sampleRateField}. */
	private static final CustomDoubleValidator SAMPLE_RATE_VALIDATOR = new CustomDoubleValidator("Sample Rate", "default");

	/**
	 * Instantiates a new entry dialog.
	 *
	 * @param parentShell the parent shell
	 */
	public PlotSettingsDialog(final Shell parentShell, final PlotSettings settings) {
		super(parentShell);
		this.plotSettings = new PlotSettings(settings);
	}

	@Override
	protected void configureShell(final Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Adjust/Override PLOT Settings");
	}

	@Override
	protected Control createDialogArea(final Composite parent) {
		final Composite container = (Composite) super.createDialogArea(parent);
		final GridLayout gridLayout = new GridLayout(2, false);
		container.setLayout(gridLayout);

		// === error message ===
		this.errorMessageText = new Text(container, SWT.READ_ONLY | SWT.WRAP);
		this.errorMessageText.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true, 2, 4));
		this.errorMessageText.setBackground(this.errorMessageText.getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));
		this.errorMessageText.setForeground(this.errorMessageText.getDisplay().getSystemColor(SWT.COLOR_RED));
		setErrorMessage(this.errorMessage); // Set the error message text - see https://bugs.eclipse.org/bugs/show_bug.cgi?id=66292

		// === frame size ===
		final Label frameSizeLabel = new Label(container, SWT.NONE);
		frameSizeLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false));
		frameSizeLabel.setText("Frame Size:");
		this.frameSizeField = new ComboViewer(container, SWT.BORDER); // writable
		this.frameSizeField.getCombo().setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 1, 1));
		this.frameSizeField.setContentProvider(new ArrayContentProvider());
		this.frameSizeField.setLabelProvider(new LabelProvider());

		final String otherValidFSValue = FRAME_SIZE_VALIDATOR.getOtherAllowedValue();
		Assert.isTrue(otherValidFSValue != null);
		Object currentFS = otherValidFSValue;
		final Object[] fsComboInputs;
		final Integer fs = this.plotSettings.getFrameSize();
		if (fs != null) {
			currentFS = fs;
			fsComboInputs = new Object[] { currentFS, otherValidFSValue, 512, 1024, 2048, 4096, 8192 };
		} else {
			fsComboInputs = new Object[] { otherValidFSValue, 512, 1024, 2048, 4096, 8192 };
		}

		this.frameSizeField.setInput(fsComboInputs);
		this.frameSizeField.setSelection(new StructuredSelection(currentFS));

		final Combo fsCombo = this.frameSizeField.getCombo();
		fsCombo.addModifyListener(new ComboVerifyAndSetListener(fsCombo, FRAME_SIZE_VALIDATOR, this) {
			void updateSettings(Double newValue) {
				Integer newIntValue = (newValue == null) ? null : newValue.intValue();
				PlotSettingsDialog.this.plotSettings.setFrameSize(newIntValue);
			}
		});
		this.frameSizeField.addSelectionChangedListener(new SelectComboTextListener(fsCombo));

		// === sample rate ===
		final Label sampleRateValueLabel = new Label(container, SWT.NONE);
		sampleRateValueLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false));
		sampleRateValueLabel.setText("Sample rate:");
		this.sampleRateField = new ComboViewer(container, SWT.BORDER); // writable
		this.sampleRateField.getCombo().setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 1, 1));
		this.sampleRateField.setContentProvider(new ArrayContentProvider());
		this.sampleRateField.setLabelProvider(new LabelProvider());

		final String otherValidSRateValue = SAMPLE_RATE_VALIDATOR.getOtherAllowedValue();
		Assert.isTrue(otherValidSRateValue != null);
		Object currentSRate = otherValidSRateValue;
		final Object[] srateComboInputs;
		final Double srate = this.plotSettings.getSampleRate();
		if (srate != null) {
			currentSRate = srate;
			srateComboInputs = new Object[] { currentSRate, otherValidSRateValue };
		} else {
			srateComboInputs = new Object[] { otherValidSRateValue };
		}

		this.sampleRateField.setInput(srateComboInputs);
		this.sampleRateField.setSelection(new StructuredSelection(currentSRate));

		final Combo sRateCombo = this.sampleRateField.getCombo();
		sRateCombo.addModifyListener(new ComboVerifyAndSetListener(sRateCombo, SAMPLE_RATE_VALIDATOR, this) {
			void updateSettings(Double newValue) {
				PlotSettingsDialog.this.plotSettings.setSampleRate(newValue);
			}
		});
		this.sampleRateField.addSelectionChangedListener(new SelectComboTextListener(sRateCombo));

		// === Min Value ===
		final Label minValueLabel = new Label(container, SWT.NONE);
		minValueLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false));
		minValueLabel.setText("Min value:");
		this.minField = new ComboViewer(container, SWT.BORDER); // writable
		this.minField.getCombo().setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 1, 1));
		this.minField.setContentProvider(new ArrayContentProvider());
		this.minField.setLabelProvider(new LabelProvider());

		final String otherValidMinValue = MIN_VALUE_VALIDATOR.getOtherAllowedValue();
		Assert.isTrue(otherValidMinValue != null);
		Object currentMinVal = otherValidMinValue;
		final Object[] minValComboInputs;
		final Double minVal = this.plotSettings.getMinValue();
		if (minVal != null) {
			currentMinVal = minVal;
			minValComboInputs = new Object[] { currentMinVal, otherValidMinValue };
		} else {
			minValComboInputs = new Object[] { otherValidMinValue };
		}

		this.minField.setInput(minValComboInputs);
		this.minField.setSelection(new StructuredSelection(currentMinVal));

		final Combo minValCombo = this.minField.getCombo();
		minValCombo.addModifyListener(new ComboVerifyAndSetListener(minValCombo, MIN_VALUE_VALIDATOR, this) {
			void updateSettings(Double newValue) {
				PlotSettingsDialog.this.plotSettings.setMinValue(newValue);
			}
		});
		this.minField.addSelectionChangedListener(new SelectComboTextListener(minValCombo));

		// === Max Value ===
		final Label maxValueLabel = new Label(container, SWT.NONE);
		maxValueLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false));
		maxValueLabel.setText("Max value:");
		this.maxField = new ComboViewer(container, SWT.BORDER); // writable
		this.maxField.getCombo().setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 1, 1));
		this.maxField.setContentProvider(new ArrayContentProvider());
		this.maxField.setLabelProvider(new LabelProvider());

		final String otherValidMaxValue = MAX_VALUE_VALIDATOR.getOtherAllowedValue();
		Assert.isTrue(otherValidMaxValue != null);
		Object currentMaxVal = otherValidMaxValue;
		final Object[] maxValComboInputs;
		final Double maxVal = this.plotSettings.getMaxValue();
		if (maxVal != null) {
			currentMaxVal = maxVal;
			maxValComboInputs = new Object[] { currentMaxVal, otherValidMaxValue };
		} else {
			maxValComboInputs = new Object[] { otherValidMaxValue };
		}

		this.maxField.setInput(maxValComboInputs);
		this.maxField.setSelection(new StructuredSelection(currentMaxVal));

		final Combo maxValCombo = this.maxField.getCombo();
		maxValCombo.addModifyListener(new ComboVerifyAndSetListener(maxValCombo, MAX_VALUE_VALIDATOR, this) {
			void updateSettings(Double newValue) {
				PlotSettingsDialog.this.plotSettings.setMaxValue(newValue);
			}
		});
		this.maxField.addSelectionChangedListener(new SelectComboTextListener(maxValCombo));

		// === plot type ===
		final Label typeLabel = new Label(container, SWT.NONE);
		typeLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false));
		typeLabel.setText("Plot Type:");
		this.plotTypeField = new ComboViewer(container, SWT.READ_ONLY);
		this.plotTypeField.getCombo().setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 1, 1));
		this.plotTypeField.setContentProvider(new ArrayContentProvider());
		this.plotTypeField.setLabelProvider(new LabelProvider());
		this.plotTypeField.addFilter(new ViewerFilter() {

			@Override
			public boolean select(Viewer viewer, Object parentElement, Object element) {
				switch ((PlotType) element) {
				case CONTOUR:
				case MESH:
					return false;
				default:
					break;
				}
				return true;
			}
		});
		this.plotTypeField.setInput(PlotType.values());
		this.plotTypeField.setSelection(new StructuredSelection(this.plotSettings.getPlotType()));
		this.plotTypeField.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(final SelectionChangedEvent event) {
				Object newVal = ((IStructuredSelection) event.getSelection()).getFirstElement();
				if (newVal instanceof PlotType) {
					PlotSettingsDialog.this.plotSettings.setPlotType((PlotType) newVal);
				}
			}
		});

		// === blocking option ===
		final Label blockingLabel = new Label(container, SWT.NONE);
		blockingLabel.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false));
		blockingLabel.setText("Blocking:");
		this.blockingOptionField = new ComboViewer(container, SWT.READ_ONLY);
		this.blockingOptionField.getCombo().setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false, 1, 1));
		this.blockingOptionField.setContentProvider(new ArrayContentProvider());
		this.blockingOptionField.setLabelProvider(new LabelProvider());
		this.blockingOptionField.setInput(new Boolean[] { Boolean.TRUE, Boolean.FALSE });
		Boolean curBlockingOption = this.plotSettings.getBlockingOption();
		if (curBlockingOption != null) {
			this.blockingOptionField.setSelection(new StructuredSelection(curBlockingOption));
		}
		this.blockingOptionField.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(final SelectionChangedEvent event) {
				Object newVal = ((IStructuredSelection) event.getSelection()).getFirstElement();
				if (newVal instanceof Boolean) {
					PlotSettingsDialog.this.plotSettings.setBlockingOption((Boolean) newVal);
				}

		Dialog.applyDialogFont(container);

		return container;
	}

	/**
	 * Gets the settings created by this dialog.
	 *
	 * @return the PLOT settings
	 */
	public PlotSettings getSettings() {
		return this.plotSettings;
	}

	/**
	 * Validates user input fields (overlap, transform size, num averages) and
	 * calls {@link #setErrorMessage(String)} with combined error messages for those input fields.
	 * @return true is valid, false
	 */
	private void validateInputs() {
		String frameSizeErrMsg = FRAME_SIZE_VALIDATOR.isValid(frameSizeField.getCombo().getText());
		String sampleRateErrMsg = SAMPLE_RATE_VALIDATOR.isValid(sampleRateField.getCombo().getText());
		String minValueErrMsg = MIN_VALUE_VALIDATOR.isValid(minField.getCombo().getText());
		String maxValueErrMsg = MAX_VALUE_VALIDATOR.isValid(maxField.getCombo().getText());
		String errorMsg = frameSizeErrMsg;
		errorMsg = appendIfNotNull(errorMsg, sampleRateErrMsg);
		errorMsg = appendIfNotNull(errorMsg, minValueErrMsg);
		errorMsg = appendIfNotNull(errorMsg, maxValueErrMsg);
		setErrorMessage(errorMsg);
	}

	private static String appendIfNotNull(String currentMsg, final String appendMsg) {
		if (appendMsg != null) {
			if (currentMsg == null) {
				currentMsg = appendMsg;
			} else {
				currentMsg += "\n" + appendMsg;
			}
		}
		return currentMsg;
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
			// no error text (empty or whitespace only).  Hide it also to avoid color change.
			// See https://bugs.eclipse.org/bugs/show_bug.cgi?id=130281
			final boolean hasError = errorMessage != null && (StringConverter.removeWhiteSpaces(errorMessage)).length() > 0;
			this.errorMessageText.setEnabled(hasError);
			this.errorMessageText.setVisible(hasError);
			this.errorMessageText.getParent().update();
			// Access the ok button by id, in case clients have overridden button creation.
			// See https://bugs.eclipse.org/bugs/show_bug.cgi?id=113643
			final Control button = getButton(IDialogConstants.OK_ID);
			if (button != null) {
				// button.setEnabled(errorMessage == null);
				button.setEnabled(!hasError);
			}
		}
	}

	static class SelectComboTextListener implements ISelectionChangedListener {
		private final Combo combo;

		SelectComboTextListener(Combo combo) {
			this.combo = combo;
		}

		public void selectionChanged(final SelectionChangedEvent event) {
			final String text = this.combo.getText();
			final int textLen = (text == null) ? 0 : text.length();
			this.combo.setSelection(new Point(0, textLen)); // select text from combo selection
		}
	}

	abstract static class ComboVerifyAndSetListener implements ModifyListener {
		private final Combo combo;
		private final IOtherAllowedInputValidator validator;
		private final String otherValidValue;
		private final PlotSettingsDialog settingsDialog;

		ComboVerifyAndSetListener(Combo combo, IOtherAllowedInputValidator validator, PlotSettingsDialog dialog) {
			Assert.isTrue(combo != null && validator != null && dialog != null);
			this.combo = combo;
			this.validator = validator;
			this.otherValidValue = validator.getOtherAllowedValue();
			this.settingsDialog = dialog;
		}

		public void modifyText(ModifyEvent e) {
			final String text = this.combo.getText();
			if (this.validator.isValid(text) == null) {
				Double newValue = null;
				if (!this.otherValidValue.equals(text)) {
					newValue = Double.valueOf(text);
				}
				updateSettings(newValue);
			}
			this.settingsDialog.validateInputs(); // validate other fields on dialog
		}

		abstract void updateSettings(Double newValue);
	}
}

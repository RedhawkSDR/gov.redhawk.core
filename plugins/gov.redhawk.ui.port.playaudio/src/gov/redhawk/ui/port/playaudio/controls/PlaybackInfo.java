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
package gov.redhawk.ui.port.playaudio.controls;

import javax.sound.sampled.AudioFormat;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @since 1.1
 */
public class PlaybackInfo extends Composite {
	private final Text encodingBox;
	private final Text sampleRateBox;
	private final Text sampleSizeBox;
	private final Text channelsBox;
	private final Text frameRateBox;
	private final Text frameSizeBox;
	private final Button pauseButton;
	private final Button disconnectButton;

	/** The current audio format being played */
	private AudioFormat audioFormat;

	public PlaybackInfo(final Composite parent, final int style) {
		super(parent, style);

		this.setLayout(new GridLayout(4, false)); // SUPPRESS CHECKSTYLE MagicNumber

		Label label = new Label(this, SWT.NONE);
		label.setText("Encoding:");
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false, 1, 1));

		this.encodingBox = new Text(this, SWT.SINGLE | SWT.BORDER);
		this.encodingBox.setText("");
		this.encodingBox.setEnabled(false);
		this.encodingBox.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		label = new Label(this, SWT.NONE);
		label.setText("Channels:");
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false, 1, 1));

		this.channelsBox = new Text(this, SWT.SINGLE | SWT.BORDER);
		this.channelsBox.setText("");
		this.channelsBox.setEnabled(false);
		this.channelsBox.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		label = new Label(this, SWT.NONE);
		label.setText("Sample Rate:");
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false, 1, 1));

		this.sampleRateBox = new Text(this, SWT.SINGLE | SWT.BORDER);
		this.sampleRateBox.setText("");
		this.sampleRateBox.setEnabled(false);
		this.sampleRateBox.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		label = new Label(this, SWT.NONE);
		label.setText("Size (bits):");
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false, 1, 1));

		this.sampleSizeBox = new Text(this, SWT.SINGLE | SWT.BORDER);
		this.sampleSizeBox.setText("");
		this.sampleSizeBox.setEnabled(false);
		this.sampleSizeBox.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		label = new Label(this, SWT.NONE);
		label.setText("Frame Rate:");
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false, 1, 1));

		this.frameRateBox = new Text(this, SWT.SINGLE | SWT.BORDER);
		this.frameRateBox.setText("");
		this.frameRateBox.setEnabled(false);
		this.frameRateBox.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		label = new Label(this, SWT.NONE);
		label.setText("Size (bytes):");
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false, 1, 1));

		this.frameSizeBox = new Text(this, SWT.SINGLE | SWT.BORDER);
		this.frameSizeBox.setText("");
		this.frameSizeBox.setEnabled(false);
		this.frameSizeBox.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		this.pauseButton = new Button(this, SWT.TOGGLE);
		this.pauseButton.setText("Pause");
		this.pauseButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1));
		this.pauseButton.setEnabled(false);

		this.disconnectButton = new Button(this, SWT.PUSH);
		this.disconnectButton.setText("Disconnect");
		this.disconnectButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1));
		this.disconnectButton.setEnabled(false);
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	public Button getPauseButton() {
		return this.pauseButton;
	}

	public Button getDisconnectButton() {
		return this.disconnectButton;
	}

	/**
	 * This method is used to indicate a new AudioFormat was connected and
	 * updates the text boxes to display the stats on it
	 * 
	 * @param fmt the new AudioFormat that's being played
	 */
	public void setInput(final AudioFormat fmt) {
		this.audioFormat = fmt;
		setText(PlaybackInfo.this.audioFormat);
	}

	/**
	 * This clears the text from all the textboxes.
	 */
	private void setText(final AudioFormat fmt) {
		if (fmt == null || fmt.getEncoding() == null) {
			PlaybackInfo.this.encodingBox.setText("No Encoding Detected");
			PlaybackInfo.this.sampleRateBox.setText("");
			PlaybackInfo.this.sampleSizeBox.setText("");
			PlaybackInfo.this.channelsBox.setText("");
			PlaybackInfo.this.frameRateBox.setText("");
			PlaybackInfo.this.frameSizeBox.setText("");
		} else {
			PlaybackInfo.this.encodingBox.setText(fmt.getEncoding().toString());
			PlaybackInfo.this.sampleRateBox.setText(Float.toString(fmt.getSampleRate()));
			PlaybackInfo.this.sampleSizeBox.setText(Integer.toString(fmt.getSampleSizeInBits()));
			PlaybackInfo.this.channelsBox.setText(Integer.toString(fmt.getChannels()));
			PlaybackInfo.this.frameRateBox.setText(Float.toString(fmt.getFrameRate()));
			PlaybackInfo.this.frameSizeBox.setText(Integer.toString(fmt.getFrameSize()));
		}
	}
}

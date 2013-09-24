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

import gov.redhawk.ui.port.playaudio.controller.AudioReceiver;
import gov.redhawk.ui.port.playaudio.internal.Activator;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.LineUnavailableException;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.statushandlers.StatusManager;

/**
 * @since 1.1
 */
public class PlaybackInfo extends Composite {

	@SuppressWarnings("unused")
	private static class AudioFormatBean {

		/**
		 * The audio encoding technique used by this format.
		 */
		private String encoding;

		/**
		 * The number of samples played or recorded per second, for sounds that have this format.
		 */
		private float sampleRate;

		/**
		 * The number of bits in each sample of a sound that has this format.
		 */
		private int sampleSizeInBits;

		/**
		 * The number of audio channels in this format (1 for mono, 2 for stereo).
		 */
		private int channels;

		/**
		 * The number of bytes in each frame of a sound that has this format.
		 */
		private int frameSize;

		/**
		 * The number of frames played or recorded per second, for sounds that have this format.
		 */
		private float frameRate;

		/**
		 * Indicates whether the audio data is stored in big-endian or little-endian order.
		 */
		private boolean bigEndian;

		private AudioFormat format;
		private PropertyChangeSupport pcs = new PropertyChangeSupport(this);

		/**
		 * @return the encoding
		 */
		public String getEncoding() {
			return encoding;
		}

		/**
		 * @param encoding the encoding to set
		 */
		public void setEncoding(String encoding) {
			String oldValue = this.encoding;
			this.encoding = encoding;
			pcs.firePropertyChange("encoding", oldValue, encoding);
		}

		/**
		 * @return the sampleRate
		 */
		public float getSampleRate() {
			return sampleRate;
		}

		/**
		 * @param sampleRate the sampleRate to set
		 */
		public void setSampleRate(float sampleRate) {
			float oldValue = this.sampleRate;
			this.sampleRate = sampleRate;
			pcs.firePropertyChange("sampleRate", oldValue, sampleRate);
		}

		/**
		 * @return the sampleSizeInBits
		 */
		public int getSampleSizeInBits() {
			return sampleSizeInBits;
		}

		/**
		 * @param sampleSizeInBits the sampleSizeInBits to set
		 */
		public void setSampleSizeInBits(int sampleSizeInBits) {
			int oldValue = this.sampleSizeInBits;
			this.sampleSizeInBits = sampleSizeInBits;
			pcs.firePropertyChange("sampleSizeInBits", oldValue, sampleSizeInBits);
		}

		/**
		 * @return the channels
		 */
		public int getChannels() {
			return channels;
		}

		/**
		 * @param channels the channels to set
		 */
		public void setChannels(int channels) {
			int oldValue = this.channels;
			this.channels = channels;
			pcs.firePropertyChange("channels", oldValue, channels);
		}

		/**
		 * @return the frameSize
		 */
		public int getFrameSize() {
			return frameSize;
		}

		/**
		 * @param frameSize the frameSize to set
		 */
		public void setFrameSize(int frameSize) {
			int oldValue = this.frameSize;
			this.frameSize = frameSize;
			pcs.firePropertyChange("frameSize", oldValue, frameSize);
		}

		/**
		 * @return the frameRate
		 */
		public float getFrameRate() {
			return frameRate;
		}

		/**
		 * @param frameRate the frameRate to set
		 */
		public void setFrameRate(float frameRate) {
			float oldValue = this.frameRate;
			this.frameRate = frameRate;
			pcs.firePropertyChange("frameRate", oldValue, frameRate);
		}

		/**
		 * @return the format
		 */
		public AudioFormat getFormat() {
			return format;
		}

		/**
		 * @param format the format to set
		 */
		public void setFormat(AudioFormat format) {
			AudioFormat oldValue = this.format;
			this.format = format;
			if (this.format != null) {
				setChannels(format.getChannels());
				if (format.getEncoding() == null || format.getEncoding().toString().isEmpty()) {
					setEncoding("No Encoding Detected");
				} else {
					setEncoding(format.getEncoding().toString());
				}
				setFrameRate(format.getFrameRate());
				setFrameSize(format.getFrameSize());
				setSampleRate(format.getSampleRate());
				setSampleSizeInBits(format.getSampleSizeInBits());
			}
			pcs.firePropertyChange("format", oldValue, format);
		}

		/**
		 * @param listener
		 * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(java.beans.PropertyChangeListener)
		 */
		public void addPropertyChangeListener(PropertyChangeListener listener) {
			pcs.addPropertyChangeListener(listener);
		}

		/**
		 * @param listener
		 * @see java.beans.PropertyChangeSupport#removePropertyChangeListener(java.beans.PropertyChangeListener)
		 */
		public void removePropertyChangeListener(PropertyChangeListener listener) {
			pcs.removePropertyChangeListener(listener);
		}

		/**
		 * @param propertyName
		 * @param listener
		 * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(java.lang.String, java.beans.PropertyChangeListener)
		 */
		public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
			pcs.addPropertyChangeListener(propertyName, listener);
		}

		/**
		 * @param propertyName
		 * @param listener
		 * @see java.beans.PropertyChangeSupport#removePropertyChangeListener(java.lang.String, java.beans.PropertyChangeListener)
		 */
		public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
			pcs.removePropertyChangeListener(propertyName, listener);
		}
	}

	private final Text encodingBox;
	private final Text sampleRateBox;
	private final Text sampleSizeBox;
	private final Text channelsBox;
	private final Text frameRateBox;
	private final Text frameSizeBox;
	private final Button pauseButton;
	private final Button disconnectButton;
	private final AudioFormatBean bean = new AudioFormatBean();

	/** The current audio format being played */
	private AudioReceiver receiver;
	private DataBindingContext context;
	private Text gainBox;
	private ComboViewer blockingField;

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

		label = new Label(this, SWT.NONE);
		label.setText("Gain Multiplier:");
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false, 1, 1));

		this.gainBox = new Text(this, SWT.SINGLE | SWT.BORDER);
		this.gainBox.setText("");
		this.gainBox.setEnabled(false);
		this.gainBox.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		label = new Label(this, SWT.NONE);
		label.setText("Blocking:");
		label.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false, 1, 1));

		this.blockingField = new ComboViewer(this, SWT.SINGLE | SWT.BORDER | SWT.READ_ONLY);
		this.blockingField.setContentProvider(new ArrayContentProvider());
		this.blockingField.setLabelProvider(new LabelProvider());
		this.blockingField.setInput(new Object[] { true, false });
		this.blockingField.getControl().setEnabled(false);
		this.blockingField.getControl().setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		this.pauseButton = new Button(this, SWT.TOGGLE);
		this.pauseButton.setText("Play");
		this.pauseButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1));
		this.pauseButton.setEnabled(false);
		this.pauseButton.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(final SelectionEvent e) {
				if (receiver.isPlaying()) {
					receiver.pause();
				} else {
					try {
						receiver.play();
					} catch (LineUnavailableException e1) {
						Status status = new Status(Status.ERROR, Activator.PLUGIN_ID, "Failed to play audio", e1);
						StatusManager.getManager().handle(status, StatusManager.SHOW | StatusManager.LOG);
					}
				}
			}
		});

		this.disconnectButton = new Button(this, SWT.PUSH);
		this.disconnectButton.setText("Disconnect");
		this.disconnectButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1));
		this.disconnectButton.setEnabled(false);
		this.disconnectButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AudioReceiver oldValue = receiver;
				setInput(null);
				oldValue.dispose();
			}
		});
	}

	@Override
	public void dispose() {
		if (this.context != null) {
			context.dispose();
			context = null;
		}
		super.dispose();
	}

	/**
	 * This method is used to indicate a new AudioFormat was connected and
	 * updates the text boxes to display the stats on it
	 * 
	 * @param fmt the new AudioFormat that's being played
	 * @since 2.0
	 */
	public void setInput(final AudioReceiver receiver) {
		this.receiver = receiver;
		updateBindings();
	}

	/**
	 * This clears the text from all the textboxes.
	 */
	private void updateBindings() {
		if (context != null) {
			context.dispose();
			context = null;
		}

		if (receiver == null) {
			bean.setFormat(null);
			PlaybackInfo.this.encodingBox.setText("No Encoding Detected");
			encodingBox.setEnabled(false);
			PlaybackInfo.this.sampleRateBox.setText("");
			sampleRateBox.setEnabled(false);
			PlaybackInfo.this.sampleSizeBox.setText("");
			sampleSizeBox.setEnabled(false);
			PlaybackInfo.this.channelsBox.setText("");
			channelsBox.setEnabled(false);
			PlaybackInfo.this.frameRateBox.setText("");
			frameRateBox.setEnabled(false);
			PlaybackInfo.this.frameSizeBox.setText("");
			frameSizeBox.setEnabled(false);
			this.gainBox.setText("");
			this.gainBox.setEnabled(false);
			this.blockingField.setSelection(new StructuredSelection());
			this.blockingField.getControl().setEnabled(false);
			this.pauseButton.setEnabled(false);
			this.disconnectButton.setEnabled(false);
		} else {
			this.context = new DataBindingContext();
			this.gainBox.setEnabled(true);
			this.blockingField.getControl().setEnabled(true);
			pauseButton.setEnabled(true);
			disconnectButton.setEnabled(true);

			this.context.bindValue(BeansObservables.observeValue(bean, "format"), BeansObservables.observeValue(receiver, "audioFormat"));
			this.context.bindValue(WidgetProperties.text().observe(pauseButton), BeansObservables.observeValue(receiver, "playing"), new UpdateValueStrategy(
				UpdateValueStrategy.POLICY_NEVER), getPlayingUpdateModel());
			this.context.bindValue(WidgetProperties.text().observe(this.encodingBox), BeansObservables.observeValue(bean, "encoding"));
			this.context.bindValue(WidgetProperties.text().observe(this.sampleRateBox), BeansObservables.observeValue(bean, "sampleRate"));
			this.context.bindValue(WidgetProperties.text().observe(this.sampleSizeBox), BeansObservables.observeValue(bean, "sampleSizeInBits"));
			this.context.bindValue(WidgetProperties.text().observe(this.channelsBox), BeansObservables.observeValue(bean, "channels"));
			this.context.bindValue(WidgetProperties.text().observe(this.frameRateBox), BeansObservables.observeValue(bean, "frameRate"));
			this.context.bindValue(WidgetProperties.text().observe(this.frameSizeBox), BeansObservables.observeValue(bean, "frameSize"));

			this.context.bindValue(WidgetProperties.text(SWT.Modify).observeDelayed(500, gainBox), BeansObservables.observeValue(receiver, "multiplier"));
			this.context.bindValue(ViewersObservables.observeSingleSelection(blockingField), BeansObservables.observeValue(receiver, "blocking"));
		}
	}

	private UpdateValueStrategy getPlayingUpdateModel() {
		UpdateValueStrategy strategy = new UpdateValueStrategy();
		strategy.setConverter(new Converter(Boolean.class, String.class) {

			@Override
			public Object convert(Object fromObject) {
				if ((Boolean) fromObject) {
					return "Stop";
				} else {
					return "Play";
				}
			}
		});

		return strategy;
	}
}

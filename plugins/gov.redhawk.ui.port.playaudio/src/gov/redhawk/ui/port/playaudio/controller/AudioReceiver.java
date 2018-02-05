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
package gov.redhawk.ui.port.playaudio.controller;

import gov.redhawk.bulkio.util.AbstractBulkIOPort;
import gov.redhawk.bulkio.util.BulkIOType;
import gov.redhawk.bulkio.util.BulkIOUtilActivator;
import gov.redhawk.bulkio.util.StreamSRIUtil;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.sca.model.jobs.RefreshJob;
import gov.redhawk.sca.util.PropertyChangeSupport;
import gov.redhawk.ui.port.playaudio.internal.Activator;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.Map;
import java.util.concurrent.Callable;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import mil.jpeojtrs.sca.util.CorbaUtils;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.jdt.annotation.Nullable;

import BULKIO.PrecisionUTCTime;
import BULKIO.StreamSRI;
import BULKIO.dataCharOperations;
import BULKIO.dataDoubleOperations;
import BULKIO.dataFloatOperations;
import BULKIO.dataLongLongOperations;
import BULKIO.dataLongOperations;
import BULKIO.dataOctetOperations;
import BULKIO.dataShortOperations;
import BULKIO.dataUlongOperations;
import BULKIO.dataUshortOperations;

/**
 * @since 2.0
 */
public class AudioReceiver extends AbstractBulkIOPort implements dataCharOperations, dataDoubleOperations, dataFloatOperations, dataLongOperations,
		dataLongLongOperations, dataOctetOperations, dataShortOperations, dataUlongOperations, dataUshortOperations {

	private static final double SCALE_LONG_TO_INT = ((double) Integer.MAX_VALUE) / ((double) Long.MAX_VALUE);

	private AudioFormat audioFormat;
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	private SourceDataLine sourceDataLine;
	private final ScaUsesPort port;
	private final String ior;
	private boolean blocking = true;
	private double multiplier = 1.0;
	private boolean disposed;

	private Adapter portListener = new AdapterImpl() {
		@Override
		public void notifyChanged(final org.eclipse.emf.common.notify.Notification msg) {
			switch (msg.getFeatureID(ScaUsesPort.class)) {
			case ScaPackage.SCA_USES_PORT__DISPOSED:
				if (msg.getNewBooleanValue()) {
					dispose();
				}
				break;
			default:
				break;
			}

		}

	};
	private boolean playing;

	public AudioReceiver(final ScaUsesPort port) {
		super(BulkIOType.getType(port.getRepid()));
		this.port = port;
		ScaModelCommand.execute(port, new ScaModelCommand() {

			@Override
			public void execute() {
				port.eAdapters().add(portListener);
			}
		});
		this.ior = port.getIor();
		pcs.addPropertyChangeListener(new PropertyChangeListener() {

			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if ("audioFormat".equals(evt.getPropertyName())) {
					AudioFormat newValue = (AudioFormat) evt.getNewValue();
					if (newValue == null) {
						setSourceDataLine(null);
					} else {
						SourceDataLine newLine = null;
						try {
							final DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, newValue);
							newLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
						} catch (LineUnavailableException e) {
							Activator.logError("Error getting audio line for playback", e);
						} finally {
							setSourceDataLine(newLine);
						}
					}
				} else if ("sourceDataLine".equals(evt.getPropertyName())) {
					setPlaying(false);
					try {
						play();
					} catch (LineUnavailableException e) {
						Activator.getDefault().getLog().log(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Failed to play audio", e));
					}
				}
			}
		});

		Job connectJob = Job.create("Connecting...", monitor -> {
			try {
				connect(monitor);
			} catch (CoreException e) {
				return new Status(e.getStatus().getSeverity(), Activator.PLUGIN_ID, "Failed to connect.", e);
			}
			return Status.OK_STATUS;
		});
		connectJob.addJobChangeListener(new JobChangeAdapter() {
			@Override
			public void done(IJobChangeEvent event) {
				RefreshJob.create(port).schedule();
			}
		});
		connectJob.schedule();
	}

	private void connect(IProgressMonitor monitor) throws CoreException {
		final BulkIOType type2 = getBulkIOType();
		final String ior2 = ior;
		if (type2 != null && ior2 != null) {
			try {
				CorbaUtils.invoke(new Callable<Object>() {

					@Override
					public Object call() throws Exception {
						BulkIOUtilActivator.getBulkIOPortConnectionManager().connect(ior2, type2, AudioReceiver.this);
						return null;
					}

				}, monitor);
			} catch (InterruptedException e) {
				// PASS
			}

		}
	}

	private void disconnect(IProgressMonitor monitor) {
		BulkIOType type2 = getBulkIOType();
		String ior2 = ior;
		if (type2 != null && ior2 != null) {
			BulkIOUtilActivator.getBulkIOPortConnectionManager().disconnect(ior2, type2, this);
		}
	}

	public ScaUsesPort getPort() {
		return port;
	}

	@Override
	protected void handleStreamSRIChanged(@Nullable StreamSRI oldSri, @Nullable StreamSRI newSri) {
		AudioFormat.Encoding enc;
		int sampleSizeInBytes;
		BulkIOType bulkIOType = getBulkIOType();
		if (bulkIOType != null) {
			switch (bulkIOType) {
			case OCTET:
				sampleSizeInBytes = bulkIOType.getBytePerAtom();
				enc = AudioFormat.Encoding.PCM_SIGNED;
				break;
			case CHAR: // Treat Char as short data
			case SHORT:
				sampleSizeInBytes = bulkIOType.getBytePerAtom();
				enc = AudioFormat.Encoding.PCM_SIGNED;
				break;
			case LONG:
				sampleSizeInBytes = bulkIOType.getBytePerAtom();
				enc = AudioFormat.Encoding.PCM_SIGNED;
				break;
			case LONG_LONG:
				// Convert Longlong to long
				sampleSizeInBytes = BulkIOType.LONG.getBytePerAtom();
				enc = AudioFormat.Encoding.PCM_SIGNED;
				break;
			case DOUBLE: // Convert double to short
			case FLOAT: // Convert float to short
				sampleSizeInBytes = BulkIOType.SHORT.getBytePerAtom();
				enc = AudioFormat.Encoding.PCM_SIGNED;
				break;
			case USHORT:
				sampleSizeInBytes = bulkIOType.getBytePerAtom();
				enc = AudioFormat.Encoding.PCM_UNSIGNED;
				break;
			case ULONG:
				sampleSizeInBytes = bulkIOType.getBytePerAtom();
				enc = AudioFormat.Encoding.PCM_UNSIGNED;
				break;
			case ULONG_LONG:
				// Convert ULonglong to ulong
				sampleSizeInBytes = BulkIOType.ULONG.getBytePerAtom();
				enc = AudioFormat.Encoding.PCM_UNSIGNED;
				break;
			default:
				// TODO Unsupported format
				setAudioFormat(null);
				return;
			}
		} else {
			// TODO No type information
			setAudioFormat(null);
			return;
		}

		if (newSri == null) {
			setAudioFormat(null);
			return;
		}

		// by default for BULKIO everything is mono
		int channels = 1;

		// By default the frameSize is always equals to number of bytes * number of channels
		int frameSize = sampleSizeInBytes * channels;

		float sampleRate = Math.round(1.0f / (float) newSri.xdelta);

		// By default, for PCM frameRate equals sampleRate
		float frameRate = sampleRate;

		// Use keywords to override values
		Map<String, Object> newKeywords = StreamSRIUtil.extractKeyWords(newSri);

		Object value = newKeywords.get("AUDIO_ENCODING");
		if (value instanceof String) {
			enc = new AudioFormat.Encoding((String) value);
		}

		value = newKeywords.get("AUDIO_CHANNELS");
		if (value instanceof Number) {
			channels = ((Number) value).intValue();
		}

		value = newKeywords.get("AUDIO_FRAME_RATE");
		if (value instanceof Number) {
			frameRate = ((Number) value).floatValue();
		}

		value = newKeywords.get("AUDIO_FRAME_SIZE");
		if (value instanceof Number) {
			frameSize = ((Number) value).intValue();
		}

		// Create Audio Format
		boolean bigEndian = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
		int sampleSizeInBits = sampleSizeInBytes * 8;
		setAudioFormat(new AudioFormat(enc, sampleRate, sampleSizeInBits, channels, frameSize, frameRate, bigEndian));
	}

	public void setAudioFormat(final AudioFormat newAudioFormat) {
		final AudioFormat oldValue = this.audioFormat;
		this.audioFormat = newAudioFormat;
		SafeRunner.run(new ISafeRunnable() {

			@Override
			public void run() throws Exception {
				pcs.firePropertyChange("audioFormat", oldValue, newAudioFormat);
			}

			@Override
			public void handleException(Throwable exception) {

			}
		});
	}

	public void setSourceDataLine(final SourceDataLine newDataLine) {
		final SourceDataLine oldValue = this.sourceDataLine;
		this.sourceDataLine = newDataLine;

		SafeRunner.run(new ISafeRunnable() {

			@Override
			public void run() throws Exception {
				if (oldValue != null) {
					oldValue.stop();
					oldValue.close();
				}
				pcs.firePropertyChange("sourceDataLine", oldValue, newDataLine);
			}

			@Override
			public void handleException(Throwable exception) {

			}
		});
	}

	public AudioFormat getAudioFormat() {
		return audioFormat;
	}

	@Override
	public void pushPacket(byte[] data, PrecisionUTCTime time, boolean eos, String streamID) {
		if (!super.pushPacket(data.length, time, eos, streamID)) {
			return;
		}
		if (multiplier != 1.0) {
			for (int i = 0; i < data.length; i++) {
				data[i] = (byte) (data[i] * multiplier);
			}
		}
		write(data);
	}

	@Override
	public void pushPacket(char[] data, PrecisionUTCTime time, boolean eos, String streamID) {
		AudioFormat localAudioFormat = audioFormat;
		if (!super.pushPacket(data.length, time, eos, streamID) || localAudioFormat == null) {
			return;
		}

		ByteBuffer buffer = ByteBuffer.allocate(data.length * localAudioFormat.getSampleSizeInBits() / 8);
		buffer.order(ByteOrder.nativeOrder());
		if (multiplier != 1.0) {
			for (int i = 0; i < data.length; i++) {
				data[i] = (char) (data[i] * multiplier);
			}
		}
		CharBuffer cBuffer = buffer.asCharBuffer();
		cBuffer.put(data);
		byte[] byteBuffer = buffer.array();
		write(byteBuffer);
	}

	@Override
	public void pushPacket(short[] data, PrecisionUTCTime time, boolean eos, String streamID) {
		AudioFormat localAudioFormat = audioFormat;
		if (!super.pushPacket(data.length, time, eos, streamID) || localAudioFormat == null) {
			return;
		}

		ByteBuffer buffer = ByteBuffer.allocate(data.length * localAudioFormat.getSampleSizeInBits() / 8);
		buffer.order(ByteOrder.nativeOrder());
		if (multiplier != 1.0) {
			for (int i = 0; i < data.length; i++) {
				data[i] = (short) (data[i] * multiplier);
			}
		}
		ShortBuffer sBuffer = buffer.asShortBuffer();
		sBuffer.put(data);
		byte[] byteBuffer = buffer.array();
		write(byteBuffer);
	}

	@Override
	public void pushPacket(int[] data, PrecisionUTCTime time, boolean eos, String streamID) {
		AudioFormat localAudioFormat = audioFormat;
		if (!super.pushPacket(data.length, time, eos, streamID) || localAudioFormat == null) {
			return;
		}

		ByteBuffer buffer = ByteBuffer.allocate(data.length * localAudioFormat.getSampleSizeInBits() / 8);
		buffer.order(ByteOrder.nativeOrder());
		if (multiplier != 1.0) {
			for (int i = 0; i < data.length; i++) {
				data[i] = (int) (data[i] * multiplier);
			}
		}
		IntBuffer iBuffer = buffer.asIntBuffer();
		iBuffer.put(data);
		byte[] byteBuffer = buffer.array();
		write(byteBuffer);
	}

	@Override
	public void pushPacket(long[] data, PrecisionUTCTime time, boolean eos, String streamID) {
		AudioFormat localAudioFormat = audioFormat;
		if (!super.pushPacket(data.length, time, eos, streamID) || localAudioFormat == null) {
			return;
		}

		ByteBuffer buffer = ByteBuffer.allocate(data.length * localAudioFormat.getSampleSizeInBits() / 8);
		buffer.order(ByteOrder.nativeOrder());
		IntBuffer iBuffer = buffer.asIntBuffer();
		// Convert to Int
		for (long l : data) {
			int value = (int) (multiplier * SCALE_LONG_TO_INT * l);
			iBuffer.put(value);
		}
		byte[] byteBuffer = buffer.array();
		write(byteBuffer);
	}

	@Override
	public void pushPacket(float[] data, PrecisionUTCTime time, boolean eos, String streamID) {
		AudioFormat localAudioFormat = audioFormat;
		if (!super.pushPacket(data.length, time, eos, streamID) || localAudioFormat == null) {
			return;
		}

		ByteBuffer buffer = ByteBuffer.allocate(data.length * localAudioFormat.getSampleSizeInBits() / 8);
		buffer.order(ByteOrder.nativeOrder());
		ShortBuffer tBuffer = buffer.asShortBuffer();
		// Convert to Short
		for (float l : data) {
			// Treat as actual magnitude
			short value = (short) (l * multiplier);
			tBuffer.put(value);
		}
		byte[] byteBuffer = buffer.array();
		write(byteBuffer);
	}

	@Override
	public void pushPacket(double[] data, PrecisionUTCTime time, boolean eos, String streamID) {
		AudioFormat localAudioFormat = audioFormat;
		if (!super.pushPacket(data.length, time, eos, streamID) || localAudioFormat == null) {
			return;
		}

		ByteBuffer buffer = ByteBuffer.allocate(data.length * localAudioFormat.getSampleSizeInBits() / 8);
		buffer.order(ByteOrder.nativeOrder());
		ShortBuffer tBuffer = buffer.asShortBuffer();
		// Convert to Short
		for (double l : data) {
			// Treat as actual magnitude
			short value = (short) (l * multiplier);
			tBuffer.put(value);
		}
		byte[] byteBuffer = buffer.array();
		write(byteBuffer);
	}

	private void write(byte[] byteBuffer) {
		if (this.sourceDataLine != null && isPlaying()) {
			int bufferSize = byteBuffer.length;
			if (blocking) {
				this.sourceDataLine.write(byteBuffer, 0, bufferSize);
			} else if (this.sourceDataLine.available() >= bufferSize) {
				int numBytes = Math.min(sourceDataLine.available(), bufferSize);
				this.sourceDataLine.write(byteBuffer, 0, numBytes);
			}
		}
	}

	public boolean isBlocking() {
		return blocking;
	}

	public void setBlocking(boolean blocking) {
		boolean oldValue = this.blocking;
		this.blocking = blocking;
		pcs.firePropertyChange("blocking", oldValue, blocking);
	}

	public void play() throws LineUnavailableException {
		if (sourceDataLine != null) {
			if (!sourceDataLine.isOpen()) {
				final int frameSize = this.audioFormat.getFrameSize();
				// Buffer 0.5 seconds of data to ensure smooth play back
				final int bufferSize = (int) ((0.5 * audioFormat.getFrameRate()) * frameSize);
				sourceDataLine.open(audioFormat, bufferSize);
			}
			sourceDataLine.start();
			setPlaying(true);
		}
	}

	private void setPlaying(boolean playing) {
		boolean oldValue = this.playing;
		this.playing = playing;
		pcs.firePropertyChange("playing", oldValue, this.playing);
	}

	public boolean isPlaying() {
		return sourceDataLine != null && this.playing;
	}

	public void pause() {
		if (this.sourceDataLine != null) {
			this.sourceDataLine.stop();
			this.sourceDataLine.flush();
		}
		setPlaying(false);
	}

	public void dispose() {
		if (disposed) {
			return;
		}
		disposed = true;
		Job.create("Disconnect", monitor -> {
			SubMonitor progress = SubMonitor.convert(monitor, 2);
			disconnect(progress.newChild(1));
			if (progress.isCanceled()) {
				return Status.CANCEL_STATUS;
			}

			try {
				port.refresh(progress.newChild(1), RefreshDepth.FULL);
			} catch (InterruptedException e) {
				return Status.CANCEL_STATUS;
			}

			return Status.OK_STATUS;
		}).schedule();

		setAudioFormat(null);
		ScaModelCommand.execute(port, new ScaModelCommand() {

			@Override
			public void execute() {
				port.eAdapters().remove(portListener);
			}
		});
		pcs.firePropertyChange("disposed", false, disposed);
	}

	public boolean isDiposed() {
		return disposed;
	}

	public void setMultiplier(double multiplier) {
		double oldValue = this.multiplier;
		this.multiplier = multiplier;
		pcs.firePropertyChange("multiplier", oldValue, multiplier);
	}

	public double getMultiplier() {
		return multiplier;
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
	 * @see java.beans.PropertyChangeSupport#addPropertyChangeListener(java.lang.String,
	 * java.beans.PropertyChangeListener)
	 */
	public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		pcs.addPropertyChangeListener(propertyName, listener);
	}

	/**
	 * @param propertyName
	 * @param listener
	 * @see java.beans.PropertyChangeSupport#removePropertyChangeListener(java.lang.String,
	 * java.beans.PropertyChangeListener)
	 */
	public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		pcs.removePropertyChangeListener(propertyName, listener);
	}

}

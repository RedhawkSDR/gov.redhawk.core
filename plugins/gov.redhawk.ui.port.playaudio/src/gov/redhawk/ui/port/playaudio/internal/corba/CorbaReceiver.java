/**
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.ui.port.playaudio.internal.corba;

import gov.redhawk.sca.util.ORBUtil;
import gov.redhawk.sca.util.OrbSession;
import gov.redhawk.ui.port.playaudio.controller.AudioController;
import gov.redhawk.ui.port.playaudio.internal.Activator;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.UUID;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.omg.CORBA.SystemException;
import org.omg.CORBA.TCKind;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.PortableServer.POAManagerPackage.AdapterInactive;
import org.omg.PortableServer.POAPackage.ObjectNotActive;
import org.omg.PortableServer.POAPackage.WrongAdapter;
import org.omg.PortableServer.POAPackage.WrongPolicy;

import BULKIO.PortStatistics;
import BULKIO.PortUsageType;
import BULKIO.PrecisionUTCTime;
import BULKIO.StreamSRI;
import BULKIO.dataCharOperations;
import BULKIO.dataDoubleOperations;
import BULKIO.dataFloatOperations;
import BULKIO.dataOctetOperations;
import BULKIO.dataShortOperations;
import BULKIO.dataUlongOperations;
import CF.DataType;
import CF.Port;
import CF.PortHelper;
import CF.PortPackage.InvalidPort;

/**
 * This class connects to the specified CORBA host and receives data. It then
 * writes the data for sound playback. Generics are used to receive all types of
 * data(Octet(byte)/Short/Long/Float/Double).
 */
public class CorbaReceiver implements dataShortOperations, dataCharOperations, dataOctetOperations, dataUlongOperations, dataFloatOperations,
        dataDoubleOperations {

	private static final int PIPE_SIZE = 1024000;
	// Keyword Constants
	//private static final String BIGENDIAN = "BigEndian";
	private static final String CHANNELS = "AUDIO_CHANNELS";
	private static final String ENCODING = "AUDIO_ENCODING";
	private static final String FRAMERATE = "AUDIO_FRAME_RATE";
	private static final String FRAMESIZE = "AUDIO_FRAME_SIZE";
	/** The maximum number of times to retry the initial ORB connection */
	private static final int MAX_RETRIES = 5;
	// Sample size constants for 8, 16 and 32bit sample sizes
	private static final int BYTE_SAMPLE_SIZE = 8;
	private static final int SHORT_SAMPLE_SIZE = 16;
	private static final int INT_SAMPLE_SIZE = 32;
	/** Time to sleep when waiting */
	private static final long SLEEP_TIME = 1000;
	/** Time to sleep when waiting */
	private static final long EMPTY_QUEUE_TIME = 10;
	private static final int DOUBLE_BYTE_SIZE = 8;

	private OrbSession session = OrbSession.createSession();
	private POA rootpoa;
	
	/** flag if we're done processing */
	private boolean isDone = false;
	
	/** The list of port connections */
	private final Map<Port, String> connections = new HashMap<Port, String>();
	/** The list of ior to ports */
	private final Map<String, Port> ports = new HashMap<String, Port>();
	/** The list of port connections */
	private final Map<Port, org.omg.CORBA.Object> ties = new HashMap<Port, org.omg.CORBA.Object>();
	/** Format of the incoming data */
	private String format = "";
	/** whether or not the class has been configured for receiving data */
	private boolean configured = false;
	/** The temporary buffer to store samples in when input size != bufferLength */
	private ByteBuffer tempBuffer = null;
	/** Lock object for the tempBuffer */
	private final Object tempBuffLock = new Object();
	/** The parent of this object, used to update the audio format display */
	private final AudioController parent;
	/** current length of the buffer */
	private int bufferLength = 0;
	/** The current AudioFormat used for playback */
	private AudioFormat audioFormat = null;
	/** The source line that data is written to */
	private SourceDataLine sourceDataLine = null;
	/** Flag to stop playing data, also shutsdown the playThread */
	private volatile boolean stopPlayback = false;
	/** Flag to pause playback */
	private boolean paused = false;
	/** The thread that initializes playing back the data */
	private volatile Thread playThread = null;
	/** The lock object for setting up the playThread */
	private final Object playThreadLock = new Object();
	/** The current audio format samplesize */
	private int sampleSize = CorbaReceiver.SHORT_SAMPLE_SIZE;
	/** Lock object for the pipes */
	private final Object pipeLock = new Object();
	/** The pipe for pushing data to the sourceDataLine for playback */
	private PipedInputStream pipeIn;
	/** The pipe for queueing data for playback */
	private PipedOutputStream pipeOut;


	/**
	 * The constructor to connect to ports and playback data.
	 * 
	 * @param audioController the parent that spawned me
	 * @param port the IOR of the port to connect to
	 * @param format the format of the ports data
	 */
	public CorbaReceiver(final AudioController audioController, final String format) throws CoreException {
		this.isDone = false;

		this.createPipes();
		rootpoa = session.getPOA();

		this.parent = audioController;
		this.format = format;
		switch (this.format.charAt(1)) {
		case 'B':
			this.sampleSize = CorbaReceiver.BYTE_SAMPLE_SIZE;
			break;
		case 'I':
			this.sampleSize = CorbaReceiver.SHORT_SAMPLE_SIZE;
			break;
		case 'L':
			this.sampleSize = CorbaReceiver.INT_SAMPLE_SIZE;
			break;
		default:
			break;
		}
	}

	private void createPipes() throws CoreException {
		synchronized (this.pipeLock) {
			this.pipeOut = new PipedOutputStream();
			this.pipeIn = new PipedInputStream() {
				@Override
				public void connect(final PipedOutputStream src) throws IOException {
					this.buffer = new byte[CorbaReceiver.PIPE_SIZE];
					super.connect(src);
				}
			};

			try {
				this.pipeIn.connect(this.pipeOut);
			} catch (final IOException e) {
				throw new CoreException(new Status(IStatus.ERROR, Activator.PLUGIN_ID, "Unable to connect pipes", e));
			}

		}
	}

	/**
	 * This method is used to connect to a different port for playback.
	 * 
	 * @param format the format of the port's data
	 * @param port the IOR of the port
	 * @return true if a successful connection was made
	 */
	public boolean connectToPort(final String format, final String port) {
		this.format = format;
		switch (this.format.charAt(1)) {
		case 'B':
			this.sampleSize = CorbaReceiver.BYTE_SAMPLE_SIZE;
			break;
		case 'I':
			this.sampleSize = CorbaReceiver.SHORT_SAMPLE_SIZE;
			break;
		case 'L':
			this.sampleSize = CorbaReceiver.INT_SAMPLE_SIZE;
			break;
		default:
			break;
		}

		this.disconnect(null, false);

		return connectPort(TieFactory.getTie(this.format, session.getOrb(), this.rootpoa, this), port);
	}

	/**
	 * This method disconnects all the ports and stops playback.
	 */
	public void shutdown() {
		this.stopPlayback = true;
		try {
			if (this.playThread != null) {
				this.playThread.join();
			}
		} catch (final InterruptedException e) {
			// PASS
		}
		this.playThread = null;

		// Disconnect the ports
		disconnect(null, false);

		// Shutdown the ORB connection
		session.dispose();
		rootpoa = null;
		this.isDone = true;
	}

	/**
	 * This method will pause or resume writing of data to the audio playback.
	 * 
	 * @param paused true if we should pause playback
	 */
	public void setPaused(final boolean paused) {
		this.paused = paused;
	}

	/**
	 * This method connects the given Tie object to the port from the arguments
	 * list. It will retry MAX_RETRIES times before giving up trying to connect.
	 * 
	 * @param tie the Tie CORBA object. This is generic to allow any type of
	 *            connection.
	 * @return true if the port was successfully connected
	 */
	private boolean connectPort(final org.omg.CORBA.Object tie, final String ior) {
		if (tie == null) {
			return false;
		}

		this.stopPlayback = false;

		int retry = 0;

		while (retry < CorbaReceiver.MAX_RETRIES) {
			try {
				// If ncRef was null, we were passed an IOR for the
				// port. Use this to directly resolve the port.
				final org.omg.CORBA.Object portRef = session.getOrb().string_to_object(ior);
				final Port port = PortHelper.narrow(portRef);

				// Make a new connection from the port to the given object
				final UUID uuid = UUID.randomUUID();
				port.connectPort(tie, uuid.toString());

				// Store the connected port for later cleanup
				this.ports.put(ior, port);
				this.connections.put(port, uuid.toString());
				this.ties.put(port, tie);
				break;
			} catch (final Exception e) {
				if (++retry == CorbaReceiver.MAX_RETRIES) {
					this.isDone = true;
				}
				try {
					Thread.sleep(CorbaReceiver.SLEEP_TIME);
				} catch (final InterruptedException i) {
					// PASS
				}
			}
		}

		return !this.isDone;
	}

	/**
	 * This method will disconnect one or all of the connected port from
	 * playback. Currently there is only one connected port at a time.
	 * 
	 * @param ior the IOR if known, or null to disconnect everything
	 * @param force this is called when the waveform has been released, the port
	 *            isn't there anymore
	 */
	public void disconnect(final String ior, final boolean force) {
		// Set the stop flags
		this.stopPlayback = true;
		try {
			if (this.playThread != null) {
				this.playThread.join();
			}
		} catch (final InterruptedException e) {
			// PASS
		}
		this.playThread = null;

		// cleanup the port connections
		if (ior != null) {
			final Port port = this.ports.remove(ior);
			final String conn = this.connections.remove(port);
			final org.omg.CORBA.Object tie = this.ties.remove(port);
			if (port != null) {
				byte[] oid;
				try {
					if (!force) {
						port.disconnectPort(conn);
					}
				} catch (final InvalidPort i) {
					// PASS
				} catch (final SystemException s) {
					// PASS
				}

				try {
					oid = this.rootpoa.reference_to_id(tie);
					this.rootpoa.deactivate_object(oid);
				} catch (final ObjectNotActive e) {
					// PASS
				} catch (final WrongPolicy e) {
					// PASS
				} catch (final WrongAdapter e) {
					// PASS
				}
				port._release();
			}
		} else {
			// loop through all the ports and disconnect/release
			for (final String portIOR : this.ports.keySet()) {
				final Port port = this.ports.get(portIOR);
				final org.omg.CORBA.Object tie = this.ties.get(port);
				try {
					if (!force) {
						port.disconnectPort(this.connections.get(port));
					}
				} catch (final InvalidPort i) {
					// PASS
				} catch (final SystemException s) {
					// PASS
				}

				try {
					final byte[] oid = this.rootpoa.reference_to_id(tie);
					this.rootpoa.deactivate_object(oid);
				} catch (final ObjectNotActive e) {
					// PASS
				} catch (final WrongPolicy e) {
					// PASS
				} catch (final WrongAdapter e) {
					// PASS
				}
			}

			this.ports.clear();
			this.connections.clear();
			this.ties.clear();
		}
	}

	/**
	 * This will determine if we should continue processing the packet. To
	 * process, the endOfStream must be false and we must have previously
	 * received SRI.
	 * 
	 * @param endOfStream true if the received stream has closed
	 * @param streamID the ID of the stream this packet is for
	 * @param length length of the data received
	 * @return true if we should process the data
	 */
	private final boolean processPacket(final boolean endOfStream, final String streamID, final int length) {
		boolean status = true;
		if (endOfStream) {
			this.isDone = true;
			status = false;
		} else if (this.paused || this.stopPlayback || !this.configured || (this.sourceDataLine == null)) {
			status = false;
		} else {
			if ((this.tempBuffer == null) || (this.bufferLength < length)) {
				this.bufferLength = length;
				final int capacity = (this.tempBuffer == null) ? 0 : this.tempBuffer.capacity(); // SUPPRESS CHECKSTYLE AvoidInline
				final int newSize = this.bufferLength * CorbaReceiver.DOUBLE_BYTE_SIZE;

				if (newSize > capacity) {
					synchronized (this.tempBuffLock) {
						this.tempBuffer = ByteBuffer.allocate(newSize);
						final boolean bigEndian = this.audioFormat.isBigEndian();
						this.tempBuffer.order((bigEndian) ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN); // SUPPRESS CHECKSTYLE AvoidInline
					}
				}
			}
		}

		return status;
	}

	/**
	 * This method receives a byte array from CORBA and writes it to the opened
	 * pipe.
	 * 
	 * @param byteArray the array of byte data to write out
	 * @param time the time the data was received
	 * @param endOfStream true if the stream has ended
	 * @param streamID string ID of the stream of data
	 */
	public void pushPacket(final byte[] byteArray, final PrecisionUTCTime time, final boolean endOfStream, final String streamID) {
		if (!processPacket(endOfStream, streamID, byteArray.length)) {
			return;
		}

		writeByteData(byteArray);
	}

	/**
	 * This method receives a char array from CORBA and writes it to the opened
	 * pipe.
	 * 
	 * @param charArray the array of char data to write out
	 * @param time the time the data was received
	 * @param endOfStream true if the stream has ended
	 * @param streamID string ID of the stream of data
	 */
	public void pushPacket(final char[] charArray, final PrecisionUTCTime time, final boolean endOfStream, final String streamID) {
		if (!processPacket(endOfStream, streamID, charArray.length)) {
			return;
		}

		writeCharData(charArray);
	}

	/**
	 * This method receives a short array from CORBA and writes it to the opened
	 * pipe.
	 * 
	 * @param shortArray the array of short data to write out
	 * @param time the time the data was received
	 * @param endOfStream true if the stream has ended
	 * @param streamID string ID of the stream of data
	 */
	public void pushPacket(final short[] shortArray, final PrecisionUTCTime time, final boolean endOfStream, final String streamID) {
		if (!processPacket(endOfStream, streamID, shortArray.length)) {
			return;
		}

		writeShortData(shortArray);
	}

	/**
	 * This method receives an int array from CORBA and writes it to the opened
	 * pipe.
	 * 
	 * @param intArray the array of int data to write out
	 * @param time the time the data was received
	 * @param endOfStream true if the stream has ended
	 * @param streamID string ID of the stream of data
	 */
	public void pushPacket(final int[] intArray, final PrecisionUTCTime time, final boolean endOfStream, final String streamID) {
		if (!processPacket(endOfStream, streamID, intArray.length)) {
			return;
		}

		writeIntData(intArray);
	}

	/**
	 * This method receives an float array from CORBA and writes it to the
	 * opened pipe.
	 * 
	 * @param intArray the array of float data to write out
	 * @param time the time the data was received
	 * @param endOfStream true if the stream has ended
	 * @param streamID string ID of the stream of data
	 */
	public void pushPacket(final float[] floatArray, final PrecisionUTCTime time, final boolean endOfStream, final String streamID) {
		if (!processPacket(endOfStream, streamID, floatArray.length)) {
			return;
		}

		writeFloatData(floatArray);
	}

	/**
	 * This method receives an double array from CORBA and writes it to the
	 * opened pipe.
	 * 
	 * @param doubleArray the array of double data to write out
	 * @param time the time the data was received
	 * @param endOfStream true if the stream has ended
	 * @param streamID string ID of the stream of data
	 */
	public void pushPacket(final double[] doubleArray, final PrecisionUTCTime time, final boolean endOfStream, final String streamID) {
		if (!processPacket(endOfStream, streamID, doubleArray.length)) {
			return;
		}

		writeDoubleData(doubleArray);
	}

	public PortUsageType state() {
		// TODO Auto-generated method stub
		return null;
	}

	public PortStatistics statistics() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * This method writes out a byte Array to the pipe in the desired format for
	 * playback.
	 * 
	 * @param byteArray the data to write out
	 */
	private void writeByteData(final byte[] byteArray) {
		synchronized (this.tempBuffLock) {
			for (final byte b : byteArray) {
				switch (this.sampleSize) {
				case BYTE_SAMPLE_SIZE:
					this.tempBuffer.put(b);
					break;
				case SHORT_SAMPLE_SIZE:
					this.tempBuffer.putShort(b);
					break;
				case INT_SAMPLE_SIZE:
					this.tempBuffer.putInt(b);
					break;
				default:
					break;
				}
			}

			if (!this.stopPlayback) {
				try {
					this.pipeOut.write(this.tempBuffer.array(), 0, byteArray.length * this.audioFormat.getFrameSize());
				} catch (final IOException e) {
					synchronized (this.pipeLock) {
						try {
							this.createPipes();
						} catch (final CoreException e1) {
							// PASS
						}
					}
				}
			}
			this.tempBuffer.position(0);
		}
	}

	/**
	 * This method writes out a char Array to the pipe in the desired format for
	 * playback.
	 * 
	 * @param charArray the data to write out
	 */
	private void writeCharData(final char[] charArray) {
		synchronized (this.tempBuffLock) {
			for (final char c : charArray) {
				switch (this.sampleSize) {
				case BYTE_SAMPLE_SIZE:
					this.tempBuffer.putChar(c);
					break;
				case SHORT_SAMPLE_SIZE:
					this.tempBuffer.putShort((short) c);
					break;
				case INT_SAMPLE_SIZE:
					this.tempBuffer.putInt(c);
					break;
				default:
					break;
				}
			}

			if (!this.stopPlayback) {
				try {
					this.pipeOut.write(this.tempBuffer.array(), 0, charArray.length * this.audioFormat.getFrameSize());
				} catch (final IOException e) {
					synchronized (this.pipeLock) {
						try {
							this.createPipes();
						} catch (final CoreException e1) {
							// PASS
						}
					}
				}
			}
			this.tempBuffer.position(0);
		}
	}

	/**
	 * This method writes out a short Array to the pipe in the desired format
	 * for playback.
	 * 
	 * @param shortArray the data to write out
	 */
	private void writeShortData(final short[] shortArray) {
		synchronized (this.tempBuffLock) {
			for (final short s : shortArray) {
				switch (this.sampleSize) {
				case BYTE_SAMPLE_SIZE:
					this.tempBuffer.put((byte) s);
					break;
				case SHORT_SAMPLE_SIZE:
					this.tempBuffer.putShort(s);
					break;
				case INT_SAMPLE_SIZE:
					this.tempBuffer.putInt(s);
					break;
				default:
					break;
				}
			}

			if (!this.stopPlayback) {
				try {
					this.pipeOut.write(this.tempBuffer.array(), 0, shortArray.length * this.audioFormat.getFrameSize());
				} catch (final IOException e) {
					synchronized (this.pipeLock) {
						try {
							this.createPipes();
						} catch (final CoreException e1) {
							// PASS
						}
					}
				}
			}
			this.tempBuffer.position(0);
		}
	}

	/**
	 * This method writes out a int Array to the pipe in the desired format for
	 * playback.
	 * 
	 * @param intArray the data to write out
	 */
	private void writeIntData(final int[] intArray) {
		synchronized (this.tempBuffLock) {
			for (final int i : intArray) {
				switch (this.sampleSize) {
				case BYTE_SAMPLE_SIZE:
					this.tempBuffer.put((byte) i);
					break;
				case SHORT_SAMPLE_SIZE:
					this.tempBuffer.putShort((short) i);
					break;
				case INT_SAMPLE_SIZE:
					this.tempBuffer.putInt(i);
					break;
				default:
					break;
				}
			}

			try {
				this.pipeOut.write(this.tempBuffer.array(), 0, intArray.length * this.audioFormat.getFrameSize());
			} catch (final IOException e) {
				synchronized (this.pipeLock) {
					try {
						this.createPipes();
					} catch (final CoreException e1) {
						// PASS
					}
				}
			}
			this.tempBuffer.position(0);
		}
	}

	/**
	 * This method writes out a float Array to the pipe in the desired format
	 * for playback.
	 * 
	 * @param floatArray the data to write out
	 */
	private void writeFloatData(final float[] floatArray) {
		synchronized (this.tempBuffLock) {
			for (final float f : floatArray) {
				switch (this.sampleSize) {
				case BYTE_SAMPLE_SIZE:
					this.tempBuffer.put((byte) f);
					break;
				case SHORT_SAMPLE_SIZE:
					this.tempBuffer.putShort((short) f);
					break;
				case INT_SAMPLE_SIZE:
					this.tempBuffer.putInt((int) f);
					break;
				default:
					break;
				}
			}

			try {
				this.pipeOut.write(this.tempBuffer.array(), 0, floatArray.length * this.audioFormat.getFrameSize());
			} catch (final IOException e) {
				synchronized (this.pipeLock) {
					try {
						this.createPipes();
					} catch (final CoreException e1) {
						// PASS
					}
				}
			}
			this.tempBuffer.position(0);
		}
	}

	/**
	 * This method writes out a double Array to the pipe in the desired format
	 * for playback.
	 * 
	 * @param doubleArray the data to write out
	 */
	private void writeDoubleData(final double[] doubleArray) {
		synchronized (this.tempBuffLock) {
			for (final double d : doubleArray) {
				switch (this.sampleSize) {
				case BYTE_SAMPLE_SIZE:
					this.tempBuffer.put((byte) d);
					break;
				case SHORT_SAMPLE_SIZE:
					this.tempBuffer.putShort((short) d);
					break;
				case INT_SAMPLE_SIZE:
					this.tempBuffer.putInt((int) d);
					break;
				default:
					break;
				}
			}

			try {
				this.pipeOut.write(this.tempBuffer.array(), 0, doubleArray.length * this.audioFormat.getFrameSize());
			} catch (final IOException e) {
				synchronized (this.pipeLock) {
					try {
						this.createPipes();
					} catch (final CoreException e1) {
						// PASS
					}
				}
			}
			this.tempBuffer.position(0);
		}
	}

	/**
	 * This receives the SRI and updates or modifies the output pipe.
	 * 
	 * @param sri the SRI containing information about the incoming data
	 */
	public void pushSRI(final StreamSRI sri) {
		final boolean oldStop = this.stopPlayback;
		// Check if the SRI is different
		if (!this.checkSRI(sri)) {
			// If it is, stop the playback so we can reset the audio
			this.stopPlayback = true;
			try {
				if (this.playThread != null) {
					this.playThread.join();
				}
			} catch (final InterruptedException e) {
				// PASS
			}
			this.stopPlayback = oldStop;
		}

		if (!oldStop && (this.playThread == null)) {
			AudioFormat.Encoding enc = AudioFormat.Encoding.PCM_SIGNED; // Default for BULKIO is just linear data, although we should check the port type to see if signed or unsigned is appropriate.
			int channels = 1; // by default for BULKIO everything is mono
			int frameSize = (this.sampleSize / 8) * channels; // By default for PCM_SIGNED the frameSize is always equals to number of bytes * number of channels
			float sampleRate = Math.round(1.0f / (float) sri.xdelta);
			float frameRate = sampleRate; // By default, for PCM frameRate equals sampleRate

			for (final DataType word : sri.keywords) {
				final int tkValue = word.value.type().kind().value();
				if (CorbaReceiver.ENCODING.equals(word.id)) {
					if (tkValue == TCKind._tk_string) {
						enc = new AudioFormat.Encoding(word.value.extract_string());
					} else {
						Activator.logWarn("Received invalid type for " + CorbaReceiver.ENCODING + " keyword. Expected string, got: " + tkValue, null);
					}
				} else if (CorbaReceiver.CHANNELS.equals(word.id)) {
					if (tkValue == TCKind._tk_long) {
						channels = word.value.extract_long();
					} else {
						Activator.logWarn("Received invalid type for " + CorbaReceiver.CHANNELS + " keyword. Expected long, got: " + tkValue, null);
					}
				} else if (CorbaReceiver.FRAMESIZE.equals(word.id)) {
					if (tkValue == TCKind._tk_long) {
						frameSize = word.value.extract_long();
					} else {
						Activator.logWarn("Received invalid type for " + CorbaReceiver.FRAMESIZE + " keyword. Expected long, got: " + tkValue, null);
					}
				} else if (CorbaReceiver.FRAMERATE.equals(word.id)) {
					if (tkValue == TCKind._tk_float) {
						frameRate = word.value.extract_float();
					} else {
						Activator.logWarn("Received invalid type for " + CorbaReceiver.FRAMERATE + " keyword. Expected float, got: " + tkValue, null);
					}
				}
			}
			
			// If no encoding is given we cannot play the port.  Just return, the ViewPart will inform the user that the encoding is null
			if (enc == null) {
				return;
			}

			try {
				this.audioFormat = new AudioFormat(enc,
						sampleRate,
				        this.sampleSize,
				        channels,
				        frameSize,
				        frameRate,
				        (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN));
				this.parent.newFormat(this.audioFormat);
				final DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, this.audioFormat);
				this.sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
			} catch (final NullPointerException e) {
				Activator.logError("No Audio keywords in SRI", e);
				this.sourceDataLine = null;
			} catch (final LineUnavailableException e) {
				Activator.logError("Error getting audio line for playback", e);
				this.sourceDataLine = null;
			} catch(Exception e) {
				Activator.logError("Error getting audio line for playback", e);
				this.sourceDataLine = null;
			}

			if (this.sourceDataLine != null) {
				this.stopPlayback = false;

				synchronized (this.playThreadLock) {
					this.playThread = new PlayThread("Audio Playback for Stream: " + sri.streamID);
					this.playThread.start();
				}
			}
		}
	}

	/**
	 * This method checks to see if the relevant SRI has changed
	 * 
	 * @param sri the StreamSRI to check
	 * @return true if the SRI is the same, false if something has changed
	 */
	private boolean checkSRI(final StreamSRI sri) {
		if (this.audioFormat == null) {
			return false;
		}

		AudioFormat.Encoding enc = this.audioFormat.getEncoding();
		int channels = this.audioFormat.getChannels();
		int frameSize = this.audioFormat.getFrameSize();
		float frameRate = this.audioFormat.getFrameRate();

		for (final DataType word : sri.keywords) {
			final int tkValue = word.value.type().kind().value();
			if (CorbaReceiver.ENCODING.equals(word.id)) {
				if (tkValue == TCKind._tk_string) {
					enc = new AudioFormat.Encoding(word.value.extract_string());
				}
			} else if (CorbaReceiver.CHANNELS.equals(word.id)) {
				if (tkValue == TCKind._tk_long) {
					channels = word.value.extract_long();
				}
			} else if (CorbaReceiver.FRAMESIZE.equals(word.id)) {
				if (tkValue == TCKind._tk_long) {
					frameSize = word.value.extract_long();
				}
			} else if (CorbaReceiver.FRAMERATE.equals(word.id)) {
				if (tkValue == TCKind._tk_float) {
					frameRate = word.value.extract_float();
				}
			}
		}

		return (enc.equals(this.audioFormat.getEncoding()) && (channels == this.audioFormat.getChannels()) && (frameSize == this.audioFormat.getFrameSize())
		        && (frameRate == this.audioFormat.getFrameRate()) && ((1.0f / (float) sri.xdelta) == this.audioFormat.getSampleRate()));
	}

	private class PlayThread extends Thread {
		public PlayThread(final String string) {
			super(string);
		}

		@Override
		public void run() {
			// Have a buffer of ~ .5 sec.  Basically, it will take 0.5 seconds before the audio
			// starts to play...but this ensures that the incoming stream can have a bit of
			// jitter and still play continously
			final int frameSize = CorbaReceiver.this.audioFormat.getFrameSize();
			final int PLAYBACK_SIZE = (int) ((0.5 * CorbaReceiver.this.audioFormat.getFrameRate()) * frameSize);
			final byte[] buf = new byte[PLAYBACK_SIZE];
			

			try {
				CorbaReceiver.this.configured = true;
				CorbaReceiver.this.sourceDataLine.open(CorbaReceiver.this.audioFormat, PLAYBACK_SIZE);
				CorbaReceiver.this.sourceDataLine.start();

				while (!CorbaReceiver.this.stopPlayback) {
					try {
						if (CorbaReceiver.this.pipeIn.available() > 0) {
							try {
								int avail;
								final int read;
								synchronized (CorbaReceiver.this.pipeLock) {
									avail = CorbaReceiver.this.pipeIn.available();
									avail = Math.min(avail, PLAYBACK_SIZE);
									read = CorbaReceiver.this.pipeIn.read(buf, 0, (avail - (avail % frameSize)));
								}
								if (read == -1) {
									CorbaReceiver.this.stopPlayback = true;
									break;
								}
								CorbaReceiver.this.sourceDataLine.write(buf, 0, read);
							} catch (final IOException e) {
								// PASS
							}
						} else {
							Thread.sleep(CorbaReceiver.EMPTY_QUEUE_TIME);
						}
					} catch (final IOException e) {
						Thread.sleep(CorbaReceiver.EMPTY_QUEUE_TIME);
					}
				}
			} catch (final LineUnavailableException e) {
				Activator.logError("Error opening Audio Playback line. Playback unavailable.", e);
			} catch (final InterruptedException e) {
				// PASS
			} catch(Exception e) {
				Activator.logError("Error opening Audio Playback line. Playback unavailable.", e);
			} finally {
				CorbaReceiver.this.configured = false;
				CorbaReceiver.this.audioFormat = null;
				flushStreams();
				CorbaReceiver.this.sourceDataLine = null;

				synchronized (CorbaReceiver.this.playThreadLock) {
					CorbaReceiver.this.playThread = null;
				}
			}
		}

		private void flushStreams() {
			try {
				CorbaReceiver.this.pipeOut.flush();
			} catch (final IOException e) {
				// PASS
			}
			int avail = 0;
			try {
				avail = CorbaReceiver.this.pipeIn.available();
			} catch (final IOException e) {
				// PASS
			}
			while (avail > 0) {
				try {
					CorbaReceiver.this.pipeIn.skip(avail);
					avail = CorbaReceiver.this.pipeIn.available();
				} catch (final IOException e) {
					// PASS
				}
			}

			if (CorbaReceiver.this.sourceDataLine != null) {
				CorbaReceiver.this.sourceDataLine.stop();
				CorbaReceiver.this.sourceDataLine.flush();
				CorbaReceiver.this.sourceDataLine.close();
			}
		}
	}

	public StreamSRI[] activeSRIs() {
		// TODO Auto-generated method stub
		return null;
	}
}

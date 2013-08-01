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
package gov.redhawk.ui.port.playaudio.controller;

import gov.redhawk.model.sca.ScaAbstractComponent;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.ui.port.playaudio.internal.Activator;
import gov.redhawk.ui.port.playaudio.internal.corba.CorbaReceiver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.sound.sampled.AudioFormat;

import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.omg.CORBA.SystemException;

/**
 * @since 1.1
 */
public class AudioController {
	private static final String IDL_START = "IDL:BULKIO/data";
	private static final int IDL_START_LEN = AudioController.IDL_START.length();

	/** The object that receives the audio */
	private CorbaReceiver audioReceiver;

	/** Map of Ports to AudioFormats */
	private final Map<ScaUsesPort, AudioFormat> formatMap = Collections.synchronizedMap(new HashMap<ScaUsesPort, AudioFormat>());

	/** Map of Ports to Waveform Adapters */
	private final Map<ScaUsesPort, Adapter> adapterMap = Collections.synchronizedMap(new HashMap<ScaUsesPort, Adapter>());

	/** The currently selected ports */
	private final List<ScaUsesPort> curPorts = Collections.synchronizedList(new ArrayList<ScaUsesPort>());

	/** The map of available ports to names */
	private final Map<ScaUsesPort, String> connectedPorts = Collections.synchronizedMap(new HashMap<ScaUsesPort, String>());
	private boolean isDisposed;
	private final Set<IControllerListener> listenerList = Collections.synchronizedSet(new HashSet<IControllerListener>());

	/**
	 * Adds a listener to the list for changes.
	 *
	 * @param listener the listener to add
	 */
	public void addListener(final IControllerListener listener) {
		this.listenerList.add(listener);
	}

	/**
	 * Removes a listener.
	 *
	 * @param listener the listener to remove
	 */
	public void removeListener(final IControllerListener listener) {
		this.listenerList.remove(listener);
	}

	/**
	 * Selects a Collection of new ports to connect to.
	 *
	 * @param ports the new ports to connect
	 */
	public void portsSelected(final Collection<ScaUsesPort> ports) {
		synchronized (this.curPorts) {
			final List<ScaUsesPort> newPorts = new ArrayList<ScaUsesPort>();
			for (final ScaUsesPort port : ports) {
				if (!this.curPorts.contains(port)) {
					final String ior = port.getIor();
					final String portType = processPortType(port.getRepid());
					if (this.audioReceiver == null) {
						try {
							this.audioReceiver = new CorbaReceiver(this, portType);
						} catch (final Exception e) {
							Activator.logError("Error starting the Audio Receiver", e);
						}
					}
					this.audioReceiver.connectToPort(portType, ior);
				}

				newPorts.add(port);
			}

			this.curPorts.clear();
			this.curPorts.addAll(newPorts);
		}
	}

	/**
	 * This method pauses and resumes audio playback.
	 *
	 * @param paused true to pause playback, false to resume
	 */
	public void pause(final boolean paused) {
		if (this.audioReceiver != null) {
			this.audioReceiver.setPaused(paused);
		}
	}

	/**
	 * This converts the BULKIO interface type to a Midassy format.
	 *
	 * @param portType the IDL rep of the port
	 * @return string form of the Midas type similar to this
	 */
	private String processPortType(final String portType) {
		String fmt = "";

		if (portType.startsWith(AudioController.IDL_START)) {
			final String type = portType.substring(AudioController.IDL_START_LEN);

			switch (type.charAt(0)) {
			case 'O':
				fmt = "SB";
				break;
			case 'D':
				fmt = "SD";
				break;
			case 'F':
				fmt = "SF";
				break;
			case 'S':
				fmt = "SI";
				break;
			case 'U':
				fmt = "SX";
				break;
			default:
				fmt = "";
			}
		}
		return fmt;
	}

	/**
	 * This returns a map of the currently connect audio ports.
	 *
	 * @return Map of the currently connect audio ports
	 */
	public Map<ScaUsesPort, String> getConnectedPorts() {
		return this.connectedPorts;
	}

	/**
	 * The AudioFormat of the currently selected audio port, or null if 0 or > 1
	 * ports are selected.
	 *
	 * @return format of the selected audio port, or null if 0 or > 1 are selected
	 */
	public AudioFormat getFormat() {
		return (this.curPorts.size() == 1) ? this.formatMap.get(this.curPorts.get(0)) : null; // SUPPRESS CHECKSTYLE AvoidInline
	}

	/**
	 * This method is used to indicate a new AudioFormat was connected and
	 * updates the text boxes to display the stats on it
	 *
	 * @param fmt the new AudioFormat that's being played
	 */
	public void newFormat(final AudioFormat fmt) {
		for (final ScaUsesPort port : this.curPorts) {
			this.formatMap.put(port, fmt);
		}
		for (final IControllerListener listener : AudioController.this.listenerList) {
			listener.setFormat(fmt);
		}
	}

	/**
	 * This disconnects all selected ports.
	 * @param portName
	 */
	public void disconnectPorts(String portName) {
		if (portName != null) {
			ScaUsesPort port = null;
			for (final Entry<ScaUsesPort, String> entry : this.connectedPorts.entrySet()) {
				if (portName.equals(entry.getValue())) {
					port = entry.getKey();
					break;
				}
			}
			if (port != null) {
				try {
					this.audioReceiver.disconnect(port.getIor(), false);
				} catch (final SystemException s) {
					Activator.logError("Exception trying to disconnect port", s);
				}
				this.curPorts.remove(port);
				this.formatMap.remove(port);
				this.connectedPorts.remove(port);
			}

		} else {
			
			for (final ScaUsesPort port : this.curPorts) {
				if (this.audioReceiver != null) {
					try {
						this.audioReceiver.disconnect(port.getIor(), false);
					} catch (final SystemException s) {
						Activator.logError("Exception trying to disconnect port", s);
					}
				}
				this.formatMap.remove(port);
				this.connectedPorts.remove(port);
			}
			this.curPorts.clear();
		}
		if (!this.isDisposed) {
			for (final IControllerListener listener : this.listenerList) {
				listener.refresh();
			}
		}
	}

	/**
	 * This cleans up the adapters and receiver when the panel is disposed.
	 */
	public void dispose() {
		if (isDisposed) {
			return;
		}
		this.isDisposed = true;
		if (this.audioReceiver != null) {
			this.audioReceiver.shutdown();
			this.audioReceiver = null;
		}
		for (final Map.Entry<ScaUsesPort, Adapter> entry : this.adapterMap.entrySet()) {
			ScaModelCommand.execute(entry.getKey(), new ScaModelCommand() {

				public void execute() {
					entry.getKey().eContainer().eContainer().eAdapters().remove(entry.getValue());     
                }
				
			});
		}
	}

	/**
	 * This selects, listens to waveform changes and plays the passed in ports.
	 *
	 * @param portMap Map of ports to play
	 */
	public void playPort(final Map<ScaUsesPort, String> portMap) {
		final Map<ScaUsesPort, String> newList = new HashMap<ScaUsesPort, String>();

		// If we're already connected to this port, don't do anything
		for (final ScaUsesPort scaPort : portMap.keySet()) {
			if (this.connectedPorts.get(scaPort) == null) {
				final ScaWaveform wave = ScaEcoreUtils.getEContainerOfType(scaPort, ScaWaveform.class);
				String name = portMap.get(scaPort);
				if (name == null) {
					name = scaPort.getName();
					// TODO Handle other port container types
					EObject portContainer = scaPort.eContainer();
					if (portContainer instanceof ScaAbstractComponent< ? >) {
						final ScaAbstractComponent< ? > comp = (ScaAbstractComponent< ? >) portContainer;
						if (comp.getProfileObj() != null) {
							String res = comp.getProfileObj().getName();
							if (comp instanceof ScaComponent) {
								SadComponentInstantiation compInstantiation = ((ScaComponent) comp).getComponentInstantiation();
								if (compInstantiation != null) {
									res = compInstantiation.getUsageName();
								} else { // fall-back to SCA Component's name
									res = ((ScaComponent) comp).getName();
								}
							} else if (comp instanceof ScaDevice< ? >) {
								res = ((ScaDevice< ? >) comp).getLabel();
							}
							name += " [" + res + "]";
						}
					}
				}
				newList.put(scaPort, name);
				// Only do this if we have a waveform to watch
				if (wave != null) {
					final Adapter adapt = new AdapterImpl() {

						public void notifyChanged(final Notification notification) {
							switch (notification.getFeatureID(ScaUsesPort.class)) {
							case ScaPackage.SCA_USES_PORT__DISPOSED:
								if (notification.getNewBooleanValue()) {
									final Job job = new Job("Remove Play Port tab") {

										@Override
										public IStatus run(final IProgressMonitor monitor) {
											if (AudioController.this.audioReceiver != null) {
												try {
													AudioController.this.audioReceiver.disconnect(scaPort.getIor(), true);
												} catch (final SystemException s) {
													Activator.logError("Exception trying to disconnect port", s);
												}
											}
											AudioController.this.formatMap.remove(scaPort);
											AudioController.this.connectedPorts.remove(scaPort);
											AudioController.this.adapterMap.remove(scaPort);
											for (final IControllerListener listener : AudioController.this.listenerList) {
												listener.refresh();
											}

											return Status.OK_STATUS;
										}

									};
									job.setSystem(true);
									job.schedule();
								}
								break;
							default:
								return;
							}
						}
					};
					AudioController.this.adapterMap.put(scaPort, adapt);
					ScaModelCommand.execute(scaPort, new ScaModelCommand() {

						public void execute() {
							scaPort.eAdapters().add(adapt);
						}

					});
				}
			}
		}

		if (newList.isEmpty()) {
			return;
		}

		this.connectedPorts.putAll(newList);
		portsSelected(portMap.keySet());
	}

	/**
	 * This returns the name of the specified port.
	 *
	 * @param port the port to get the name for
	 * @return the String name of the port
	 */
	public String getPortName(final ScaUsesPort port) {
		return this.connectedPorts.get(port);
	}

}

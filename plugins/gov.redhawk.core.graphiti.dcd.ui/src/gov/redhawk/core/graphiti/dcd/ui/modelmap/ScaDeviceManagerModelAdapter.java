/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package gov.redhawk.core.graphiti.dcd.ui.modelmap;

import java.util.Collection;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.FeatureMap;

import gov.redhawk.model.sca.ScaConnection;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaService;
import gov.redhawk.model.sca.ScaUsesPort;

/**
 * Adapts changes on the SCA model (specifically an {@link ScaDeviceManager}) to calls on the model map.
 */
public class ScaDeviceManagerModelAdapter extends EContentAdapter {

	private final GraphitiDCDModelMap modelMap;

	public ScaDeviceManagerModelAdapter(final GraphitiDCDModelMap modelMap) {
		this.modelMap = modelMap;
	}

	@Override
	public void notifyChanged(final Notification notification) {
		// Ignore this as it is a duplicate notification of SCA_DEVICE_MANAGER__DEVICES, just minus the FeatureMap info
		if (ScaPackage.Literals.SCA_DEVICE_MANAGER__ALL_DEVICES.equals(notification.getFeature())) {
			return;
		}

		super.notifyChanged(notification);

		if (notification.getNotifier() instanceof ScaDeviceManager) {
			switch (notification.getFeatureID(ScaDeviceManager.class)) {
			case ScaPackage.SCA_DEVICE_MANAGER__DEVICES:
				switch (notification.getEventType()) {
				case Notification.ADD:
					ScaDevice< ? > newVal = (ScaDevice< ? >) ((FeatureMap.Entry) notification.getNewValue()).getValue();
					if (checkDeviceAttr(newVal)) {
						this.modelMap.add(newVal);
					}
					break;
				case Notification.ADD_MANY:
					for (final Object obj : (Collection< ? >) notification.getNewValue()) {
						ScaDevice< ? > newDevice = (ScaDevice< ? >) ((FeatureMap.Entry) obj).getValue();
						if (checkDeviceAttr(newDevice)) {
							this.modelMap.add(newDevice);
						}
					}
					break;
				case Notification.REMOVE:
					ScaDevice< ? > oldVal = (ScaDevice< ? >) ((FeatureMap.Entry) notification.getOldValue()).getValue();
					this.modelMap.remove(oldVal);
					break;
				case Notification.REMOVE_MANY:
					for (final Object obj : (Collection< ? >) notification.getOldValue()) {
						ScaDevice< ? > oldDevice = (ScaDevice< ? >) ((FeatureMap.Entry) obj).getValue();
						this.modelMap.remove(oldDevice);
					}
					break;
				default:
					break;
				}
				break;
			case ScaPackage.SCA_DEVICE_MANAGER__SERVICES:
				switch (notification.getEventType()) {
				case Notification.ADD:
					ScaService newVal = (ScaService) notification.getNewValue();
					if (checkServiceAttr(newVal)) {
						this.modelMap.add(newVal);
					}
					break;
				case Notification.ADD_MANY:
					for (final Object obj : (Collection< ? >) notification.getNewValue()) {
						ScaService newService = (ScaService) obj;
						if (checkServiceAttr(newService)) {
							this.modelMap.add(newService);
						}
					}
					break;
				case Notification.REMOVE:
					ScaService oldVal = (ScaService) notification.getOldValue();
					this.modelMap.remove(oldVal);
					break;
				case Notification.REMOVE_MANY:
					for (final Object obj : (Collection< ? >) notification.getOldValue()) {
						ScaService oldService = (ScaService) obj;
						this.modelMap.remove(oldService);
					}
					break;
				default:
					break;
				}
				break;
			default:
				break;
			}
		} else if (notification.getNotifier() instanceof ScaDevice) {
			ScaDevice< ? > device = (ScaDevice< ? >) notification.getNotifier();
			switch (notification.getFeatureID(ScaDevice.class)) {
			case ScaPackage.SCA_DEVICE__IDENTIFIER:
				switch (notification.getEventType()) {
				case Notification.SET:
					this.modelMap.add(device);
					break;
				default:
					break;
				}
				break;
			case ScaPackage.SCA_DEVICE__STARTED:
				final Boolean started = (Boolean) notification.getNewValue();
				this.modelMap.startStopDevice(device, started);
				break;
			case ScaPackage.SCA_DEVICE__STATUS:
				IStatus status = (IStatus) notification.getNewValue();
				this.modelMap.reflectErrorState(device, status);
				break;
			case ScaPackage.SCA_DEVICE__DISPOSED:
				device.eAdapters().remove(this);
				break;
			default:
				break;
			}
		} else if (notification.getNotifier() instanceof ScaService) {
			ScaService service = (ScaService) notification.getNotifier();
			switch (notification.getFeatureID(ScaService.class)) {
			case ScaPackage.SCA_SERVICE__NAME:
				switch (notification.getEventType()) {
				case Notification.SET:
					if (checkServiceAttr(service)) {
						this.modelMap.add(service);
					}
					break;
				default:
					break;
				}
				break;
			case ScaPackage.SCA_SERVICE__STATUS:
				IStatus status = (IStatus) notification.getNewValue();
				this.modelMap.reflectErrorState(service, status);
				break;
			case ScaPackage.SCA_SERVICE__DISPOSED:
				service.eAdapters().remove(this);
				break;
			default:
				break;
			}
		} else if (notification.getNotifier() instanceof ScaUsesPort) {
			switch (notification.getFeatureID(ScaUsesPort.class)) {
			case ScaPackage.SCA_USES_PORT__CONNECTIONS:
				switch (notification.getEventType()) {
				case Notification.ADD:
					Object newVal = notification.getNewValue();
					this.modelMap.add((ScaConnection) newVal);
					break;
				case Notification.ADD_MANY:
					for (final Object obj : (Collection< ? >) notification.getNewValue()) {
						this.modelMap.add((ScaConnection) obj);
					}
					break;
				case Notification.REMOVE:
					Object oldVal = notification.getOldValue();
					this.modelMap.remove((ScaConnection) oldVal);
					break;
				case Notification.REMOVE_MANY:
					for (final Object obj : (Collection< ? >) notification.getOldValue()) {
						this.modelMap.remove((ScaConnection) obj);
					}
					break;
				default:
					break;
				}
				break;
			default:
				break;
			}
		}
	}

	private boolean checkDeviceAttr(ScaDevice< ? > device) {
		return device.isSetIdentifier();
	}

	// ScaService must have the name set before we can add it to the model map
	protected boolean checkServiceAttr(ScaService service) {
		return service.getName() != null;
	}

	protected GraphitiDCDModelMap getModelMap() {
		return modelMap;
	}

	@Override
	public void addAdapter(final Notifier notifier) {
		if (notifier instanceof ScaDeviceManager || notifier instanceof ScaDevice || notifier instanceof ScaService || notifier instanceof ScaUsesPort) {
			super.addAdapter(notifier);
		}
	}
}

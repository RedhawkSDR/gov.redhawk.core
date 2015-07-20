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
// BEGIN GENERATED CODE
package gov.redhawk.monitor.model.ports.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.transaction.RunnableWithResult;

import gov.redhawk.model.sca.IDisposable;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaPortContainer;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.model.sca.commands.ScaModelCommandWithResult;
import gov.redhawk.monitor.model.ports.PortMonitor;
import gov.redhawk.monitor.model.ports.PortSupplierMonitor;
import gov.redhawk.monitor.model.ports.PortsFactory;
import gov.redhawk.monitor.model.ports.PortsPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Port Supplier Monitor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link gov.redhawk.monitor.model.ports.impl.PortSupplierMonitorImpl#getPortContainer <em>Port Container</em>}
 * </li>
 * <li>{@link gov.redhawk.monitor.model.ports.impl.PortSupplierMonitorImpl#getMonitors <em>Monitors</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PortSupplierMonitorImpl extends EObjectImpl implements PortSupplierMonitor {
	/**
	 * The cached value of the '{@link #getPortContainer() <em>Port Container</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPortContainer()
	 * @generated
	 * @ordered
	 */
	protected ScaPortContainer portContainer;
	/**
	 * The cached value of the '{@link #getMonitors() <em>Monitors</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMonitors()
	 * @generated
	 * @ordered
	 */
	protected EList<PortMonitor> monitors;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PortSupplierMonitorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PortsPackage.Literals.PORT_SUPPLIER_MONITOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScaPortContainer getPortContainer() {
		if (portContainer != null && portContainer.eIsProxy()) {
			InternalEObject oldPortContainer = (InternalEObject) portContainer;
			portContainer = (ScaPortContainer) eResolveProxy(oldPortContainer);
			if (portContainer != oldPortContainer) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PortsPackage.PORT_SUPPLIER_MONITOR__PORT_CONTAINER, oldPortContainer,
						portContainer));
			}
		}
		return portContainer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaPortContainer basicGetPortContainer() {
		return portContainer;
	}

	private final Adapter listener = new AdapterImpl() {
		@Override
		public void notifyChanged(final Notification msg) {
			switch (msg.getFeatureID(ScaPortContainer.class)) {
			case ScaPackage.SCA_PORT_CONTAINER__PORTS:
				updateMonitors();
				break;
			default:
				break;
			}
			switch (msg.getFeatureID(IDisposable.class)) {
			case ScaPackage.IDISPOSABLE__DISPOSED:
				EcoreUtil.delete(PortSupplierMonitorImpl.this);
				break;
			default:
				break;
			}
		}
	};

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void setPortContainer(final ScaPortContainer newPortContainer) {
		if (this.portContainer != null) {
			this.portContainer.eAdapters().remove(this.listener);
		}
		setPortContainerGen(newPortContainer);
		if (this.portContainer != null) {
			this.portContainer.eAdapters().add(this.listener);
		}
		updateMonitors();
	}

	private void updateMonitors() {
		getMonitors().clear();
		if (this.portContainer != null && this.portContainer instanceof ScaWaveform) {
			ScaWaveform waveform = (ScaWaveform) portContainer;
			for (ScaComponent component : waveform.getComponents()) {
				for (final ScaPort< ? , ? > port : component.getPorts()) {
					final PortMonitor pm = PortsFactory.eINSTANCE.createPortMonitor();
					pm.setPort(port);
					getMonitors().add(pm);
				}
			}
		} else if (this.portContainer != null && this.portContainer instanceof ScaDeviceManager) {
			ScaDeviceManager deviceManager = (ScaDeviceManager) portContainer;
			for (ScaDevice< ? > device : deviceManager.getAllDevices()) {
				for (final ScaPort< ? , ? > port : device.getPorts()) {
					final PortMonitor pm = PortsFactory.eINSTANCE.createPortMonitor();
					pm.setPort(port);
					getMonitors().add(pm);
				}
			}
		} else if (this.portContainer != null) {

			for (final ScaPort< ? , ? > port : this.portContainer.getPorts()) {
				final PortMonitor pm = PortsFactory.eINSTANCE.createPortMonitor();
				pm.setPort(port);
				getMonitors().add(pm);
			}
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPortContainerGen(ScaPortContainer newPortContainer) {
		ScaPortContainer oldPortContainer = portContainer;
		portContainer = newPortContainer;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PortsPackage.PORT_SUPPLIER_MONITOR__PORT_CONTAINER, oldPortContainer, portContainer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<PortMonitor> getMonitors() {
		if (monitors == null) {
			monitors = new EObjectContainmentEList<PortMonitor>(PortMonitor.class, this, PortsPackage.PORT_SUPPLIER_MONITOR__MONITORS);
		}
		return monitors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void fetchStats() {
		// END GENERATED CODE
		try {
			List<PortMonitor> tmpPortMonitors = ScaModelCommandWithResult.runExclusive(this, new RunnableWithResult.Impl<List<PortMonitor>>() {
				@Override
				public void run() {
					setResult(new ArrayList<PortMonitor>(getMonitors()));
				}
			});
			for (final PortMonitor pm : tmpPortMonitors) {
				pm.fetchStats();
			}
		} catch (InterruptedException ex) {
			// PASS
		}
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case PortsPackage.PORT_SUPPLIER_MONITOR__MONITORS:
			return ((InternalEList< ? >) getMonitors()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case PortsPackage.PORT_SUPPLIER_MONITOR__PORT_CONTAINER:
			if (resolve)
				return getPortContainer();
			return basicGetPortContainer();
		case PortsPackage.PORT_SUPPLIER_MONITOR__MONITORS:
			return getMonitors();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case PortsPackage.PORT_SUPPLIER_MONITOR__PORT_CONTAINER:
			setPortContainer((ScaPortContainer) newValue);
			return;
		case PortsPackage.PORT_SUPPLIER_MONITOR__MONITORS:
			getMonitors().clear();
			getMonitors().addAll((Collection< ? extends PortMonitor>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case PortsPackage.PORT_SUPPLIER_MONITOR__PORT_CONTAINER:
			setPortContainer((ScaPortContainer) null);
			return;
		case PortsPackage.PORT_SUPPLIER_MONITOR__MONITORS:
			getMonitors().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case PortsPackage.PORT_SUPPLIER_MONITOR__PORT_CONTAINER:
			return portContainer != null;
		case PortsPackage.PORT_SUPPLIER_MONITOR__MONITORS:
			return monitors != null && !monitors.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // PortSupplierMonitorImpl

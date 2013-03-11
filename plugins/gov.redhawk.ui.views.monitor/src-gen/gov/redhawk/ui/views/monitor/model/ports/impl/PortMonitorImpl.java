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

 // BEGIN GENERATED CODE
package gov.redhawk.ui.views.monitor.model.ports.impl;

import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.ui.views.monitor.model.ports.PortConnectionMonitor;
import gov.redhawk.ui.views.monitor.model.ports.PortMonitor;
import gov.redhawk.ui.views.monitor.model.ports.PortStatisticsProvider;
import gov.redhawk.ui.views.monitor.model.ports.PortsFactory;
import gov.redhawk.ui.views.monitor.model.ports.PortsPackage;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.omg.CORBA.SystemException;

import BULKIO.PortStatistics;
import BULKIO.PortUsageType;
import BULKIO.ProvidesPortStatisticsProvider;
import BULKIO.ProvidesPortStatisticsProviderHelper;
import BULKIO.UsesPortStatistics;
import BULKIO.UsesPortStatisticsProvider;
import BULKIO.UsesPortStatisticsProviderHelper;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Port Monitor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.ui.views.monitor.model.ports.impl.PortMonitorImpl#getData <em>Data</em>}</li>
 *   <li>{@link gov.redhawk.ui.views.monitor.model.ports.impl.PortMonitorImpl#getPort <em>Port</em>}</li>
 *   <li>{@link gov.redhawk.ui.views.monitor.model.ports.impl.PortMonitorImpl#getConnections <em>Connections</em>}</li>
 *   <li>{@link gov.redhawk.ui.views.monitor.model.ports.impl.PortMonitorImpl#getState <em>State</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PortMonitorImpl extends EObjectImpl implements PortMonitor {
	/**
	 * The default value of the '{@link #getData() <em>Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getData()
	 * @generated
	 * @ordered
	 */
	protected static final PortStatistics DATA_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getData() <em>Data</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getData()
	 * @generated
	 * @ordered
	 */
	protected PortStatistics data = DATA_EDEFAULT;
	/**
	 * The cached value of the '{@link #getPort() <em>Port</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPort()
	 * @generated
	 * @ordered
	 */
	protected ScaPort<?, ?> port;
	/**
	 * The cached value of the '{@link #getConnections() <em>Connections</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnections()
	 * @generated
	 * @ordered
	 */
	protected EList<PortConnectionMonitor> connections;
	/**
	 * The default value of the '{@link #getState() <em>State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected static final PortUsageType STATE_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getState() <em>State</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected PortUsageType state = STATE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PortMonitorImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PortsPackage.Literals.PORT_MONITOR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortStatistics getData() {
		return data;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setData(PortStatistics newData) {
		PortStatistics oldData = data;
		data = newData;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PortsPackage.PORT_MONITOR__DATA, oldData, data));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaPort<?, ?> getPort() {
		if (port != null && port.eIsProxy()) {
			InternalEObject oldPort = (InternalEObject)port;
			port = (ScaPort<?, ?>)eResolveProxy(oldPort);
			if (port != oldPort) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, PortsPackage.PORT_MONITOR__PORT, oldPort, port));
			}
		}
		return port;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScaPort<?, ?> basicGetPort() {
		return port;
	}

	private final DisposeAdapter disposeAdapter = new DisposeAdapter(this);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void setPort(final ScaPort<?, ?> newPort) {
		if (this.port != null) {
			this.port.eAdapters().remove(this.disposeAdapter);
		}
		setPortGen(newPort);
		if (this.port != null) {
			this.port.eAdapters().add(this.disposeAdapter);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPortGen(ScaPort<?, ?> newPort) {
		ScaPort<?, ?> oldPort = port;
		port = newPort;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PortsPackage.PORT_MONITOR__PORT, oldPort, port));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PortConnectionMonitor> getConnections() {
		if (connections == null) {
			connections = new EObjectContainmentWithInverseEList<PortConnectionMonitor>(PortConnectionMonitor.class, this, PortsPackage.PORT_MONITOR__CONNECTIONS, PortsPackage.PORT_CONNECTION_MONITOR__PORT);
		}
		return connections;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PortUsageType getState() {
		return state;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setState(PortUsageType newState) {
		PortUsageType oldState = state;
		state = newState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PortsPackage.PORT_MONITOR__STATE, oldState, state));
	}

	private final Job fetchJob = new Job("Fetching stats") {
		
		{
			setSystem(true);
			setUser(false);
		}

		@Override
        protected IStatus run(final IProgressMonitor monitor) {
			monitor.beginTask("Fetching stats for " + PortMonitorImpl.this.port.getName(), IProgressMonitor.UNKNOWN);
			final org.omg.CORBA.Object portObj = PortMonitorImpl.this.port.getObj();
			if (portObj == null) {
				return Status.CANCEL_STATUS;
			}
			try {
				if (portObj._non_existent()) {
					return Status.CANCEL_STATUS;
				}
				
				if (portObj._is_a(ProvidesPortStatisticsProviderHelper.id())) {
					final ProvidesPortStatisticsProvider provider = ProvidesPortStatisticsProviderHelper.narrow(portObj);
					setState(provider.state());
					setData(provider.statistics());
				} else {
					setState(null);
					setData(null);
				}
				
				
				if (portObj._is_a(UsesPortStatisticsProviderHelper.id())) {
					final Map<String, PortConnectionMonitor> currentMap = new HashMap<String, PortConnectionMonitor>();
					for (final PortConnectionMonitor pcm : getConnections()) {
						currentMap.put(pcm.getConnectionId(), pcm);
					}
					final Map<String, PortConnectionMonitor> toRemove = new HashMap<String, PortConnectionMonitor>();
					toRemove.putAll(currentMap);

					final UsesPortStatisticsProvider provider = UsesPortStatisticsProviderHelper.narrow(portObj);
					final UsesPortStatistics[] stats = provider.statistics();

					final Map<String, UsesPortStatistics> newStatsMap = new HashMap<String, UsesPortStatistics>();
					for (final UsesPortStatistics stat : stats) {
						PortConnectionMonitor pcm = currentMap.get(stat.connectionId);
						if (pcm == null) {
							pcm = PortsFactory.eINSTANCE.createPortConnectionMonitor();
							pcm.setConnectionId(stat.connectionId);
							getConnections().add(pcm);
						}
						pcm.setData(stat.statistics);
						newStatsMap.put(stat.connectionId, stat);
					}

					toRemove.keySet().removeAll(newStatsMap.keySet());
					getConnections().removeAll(toRemove.values());
				} else {
					getConnections().clear();
				}
			} catch (final SystemException e) {
				return Status.CANCEL_STATUS;
			}
					
			
			
	        return Status.OK_STATUS;
        }
		
	};

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void fetchStats() {
		// END GENERATED CODE
		if (this.port != null) {
			this.fetchJob.schedule();
		} else {
			this.setState(null);
			this.setData(null);
			getConnections().clear();
		}

		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PortsPackage.PORT_MONITOR__CONNECTIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getConnections()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PortsPackage.PORT_MONITOR__CONNECTIONS:
				return ((InternalEList<?>)getConnections()).basicRemove(otherEnd, msgs);
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
			case PortsPackage.PORT_MONITOR__DATA:
				return getData();
			case PortsPackage.PORT_MONITOR__PORT:
				if (resolve) return getPort();
				return basicGetPort();
			case PortsPackage.PORT_MONITOR__CONNECTIONS:
				return getConnections();
			case PortsPackage.PORT_MONITOR__STATE:
				return getState();
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
			case PortsPackage.PORT_MONITOR__DATA:
				setData((PortStatistics)newValue);
				return;
			case PortsPackage.PORT_MONITOR__PORT:
				setPort((ScaPort<?, ?>)newValue);
				return;
			case PortsPackage.PORT_MONITOR__CONNECTIONS:
				getConnections().clear();
				getConnections().addAll((Collection<? extends PortConnectionMonitor>)newValue);
				return;
			case PortsPackage.PORT_MONITOR__STATE:
				setState((PortUsageType)newValue);
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
			case PortsPackage.PORT_MONITOR__DATA:
				setData(DATA_EDEFAULT);
				return;
			case PortsPackage.PORT_MONITOR__PORT:
				setPort((ScaPort<?, ?>)null);
				return;
			case PortsPackage.PORT_MONITOR__CONNECTIONS:
				getConnections().clear();
				return;
			case PortsPackage.PORT_MONITOR__STATE:
				setState(STATE_EDEFAULT);
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
			case PortsPackage.PORT_MONITOR__DATA:
				return DATA_EDEFAULT == null ? data != null : !DATA_EDEFAULT.equals(data);
			case PortsPackage.PORT_MONITOR__PORT:
				return port != null;
			case PortsPackage.PORT_MONITOR__CONNECTIONS:
				return connections != null && !connections.isEmpty();
			case PortsPackage.PORT_MONITOR__STATE:
				return STATE_EDEFAULT == null ? state != null : !STATE_EDEFAULT.equals(state);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == PortStatisticsProvider.class) {
			switch (derivedFeatureID) {
				case PortsPackage.PORT_MONITOR__DATA: return PortsPackage.PORT_STATISTICS_PROVIDER__DATA;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == PortStatisticsProvider.class) {
			switch (baseFeatureID) {
				case PortsPackage.PORT_STATISTICS_PROVIDER__DATA: return PortsPackage.PORT_MONITOR__DATA;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (data: ");
		result.append(data);
		result.append(", state: ");
		result.append(state);
		result.append(')');
		return result.toString();
	}

} //PortMonitorImpl

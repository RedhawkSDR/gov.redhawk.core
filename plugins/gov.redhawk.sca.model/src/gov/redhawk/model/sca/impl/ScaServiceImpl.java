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
package gov.redhawk.model.sca.impl;

import gov.redhawk.model.sca.ProfileObjectWrapper;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaDeviceManager;
import gov.redhawk.model.sca.ScaDeviceManagerFileSystem;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaPortContainer;
import gov.redhawk.model.sca.ScaService;
import gov.redhawk.model.sca.commands.MergePortsCommand;
import gov.redhawk.model.sca.commands.MergePortsCommand.PortData;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.commands.UnsetLocalAttributeCommand;
import gov.redhawk.model.sca.commands.VersionedFeature;
import gov.redhawk.model.sca.commands.VersionedFeature.Transaction;
import gov.redhawk.sca.util.PluginUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import mil.jpeojtrs.sca.dcd.DcdComponentInstantiation;
import mil.jpeojtrs.sca.dcd.DcdComponentPlacement;
import mil.jpeojtrs.sca.dcd.DcdPartitioning;
import mil.jpeojtrs.sca.dcd.DeviceConfiguration;
import mil.jpeojtrs.sca.prf.AbstractProperty;
import mil.jpeojtrs.sca.scd.AbstractPort;
import mil.jpeojtrs.sca.scd.Interface;
import mil.jpeojtrs.sca.scd.Ports;
import mil.jpeojtrs.sca.scd.ScdPackage;
import mil.jpeojtrs.sca.scd.SupportsInterface;
import mil.jpeojtrs.sca.spd.SoftPkg;
import mil.jpeojtrs.sca.spd.SpdPackage;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.FeatureMap.ValueListIterator;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.omg.CORBA.SystemException;

import CF.DataType;
import CF.PortSupplier;
import CF.PortSupplierHelper;
import CF.PortSupplierOperations;
import CF.PropertiesHolder;
import CF.PropertyEmitter;
import CF.PropertyEmitterHelper;
import CF.PropertyEmitterOperations;
import CF.PropertySet;
import CF.PropertySetHelper;
import CF.PropertySetOperations;
import CF.UnknownProperties;
import CF.PortSupplierPackage.UnknownPort;
import CF.PropertyEmitterPackage.AlreadyInitialized;
import CF.PropertySetPackage.InvalidConfiguration;
import CF.PropertySetPackage.PartialConfiguration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service</b></em>'.
 * 
 * @since 12.0
 *        <!-- end-user-doc -->
 *        <p>
 *        The following features are implemented:
 *        </p>
 *        <ul>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaServiceImpl#getPorts <em>Ports</em>}</li>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaServiceImpl#getName <em>Name</em>}</li>
 *        <li>{@link gov.redhawk.model.sca.impl.ScaServiceImpl#getDevMgr <em>Dev Mgr</em>}</li>
 *        </ul>
 *
 * @generated
 */
public class ScaServiceImpl extends ScaPropertyContainerImpl<org.omg.CORBA.Object, SoftPkg> implements ScaService {
	/**
	 * The cached value of the '{@link #getPorts() <em>Ports</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * 
	 * @since 18.0
	 *        <!-- end-user-doc -->
	 * @see #getPorts()
	 * @generated
	 * @ordered
	 */
	protected EList<ScaPort< ? , ? >> ports;
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	private PropertySetOperations propertySetOp;

	private PropertyEmitterOperations propertyEmitterOp;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ScaServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScaPackage.Literals.SCA_SERVICE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 18.0
	 *        <!-- end-user-doc -->
	 *        This is specialized for the more specific type known in this context.
	 * @generated
	 */
	@Override
	public void setProfileObj(SoftPkg newProfileObj) {
		super.setProfileObj(newProfileObj);
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 18.0
	 *        <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<ScaPort< ? , ? >> getPorts() {
		if (ports == null) {
			ports = new EObjectContainmentWithInverseEList.Unsettable<ScaPort< ? , ? >>(ScaPort.class, this, ScaPackage.SCA_SERVICE__PORTS,
				ScaPackage.SCA_PORT__PORT_CONTAINER);
		}
		return ports;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 18.0
	 *        <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetPorts() {
		if (ports != null)
			((InternalEList.Unsettable< ? >) ports).unset();
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 18.0
	 *        <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetPorts() {
		return ports != null && ((InternalEList.Unsettable< ? >) ports).isSet();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.SCA_SERVICE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 18.0
	 *        <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScaDeviceManager getDevMgr() {
		if (eContainerFeatureID() != ScaPackage.SCA_SERVICE__DEV_MGR)
			return null;
		return (ScaDeviceManager) eInternalContainer();
	}

	// END GENERATED CODE

	/**
	 * EMF feature path from a {@link ScaService} to the ports in its SCD file.
	 */
	private static final EStructuralFeature[] RESOURCE_TO_INTERFACES_PATH = { ScaPackage.Literals.PROFILE_OBJECT_WRAPPER__PROFILE_OBJ,
		SpdPackage.Literals.SOFT_PKG__DESCRIPTOR, SpdPackage.Literals.DESCRIPTOR__COMPONENT, ScdPackage.Literals.SOFTWARE_COMPONENT__COMPONENT_FEATURES,
		ScdPackage.Literals.COMPONENT_FEATURES__SUPPORTS_INTERFACE };

	// BEGIN GENERATED CODE

	/**
	 * <!-- begin-user-doc -->
	 * @since 20.4
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isInstance(Interface intf) {
		// END GENERATED CODE
		List<SupportsInterface> supportsInterfaces = ScaEcoreUtils.getFeature(this, RESOURCE_TO_INTERFACES_PATH);
		if (supportsInterfaces == null) {
			return false;
		}
		for (SupportsInterface supportsInterface : supportsInterfaces) {
			Interface thisIntf = supportsInterface.getInterface();
			if (thisIntf != null && thisIntf.isInstance(intf)) {
				return true;
			}
		}
		return false;
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ScaPackage.SCA_SERVICE__PORTS:
			return ((InternalEList<InternalEObject>) (InternalEList< ? >) getPorts()).basicAdd(otherEnd, msgs);
		case ScaPackage.SCA_SERVICE__DEV_MGR:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return eBasicSetContainer(otherEnd, ScaPackage.SCA_SERVICE__DEV_MGR, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ScaPackage.SCA_SERVICE__PORTS:
			return ((InternalEList< ? >) getPorts()).basicRemove(otherEnd, msgs);
		case ScaPackage.SCA_SERVICE__DEV_MGR:
			return eBasicSetContainer(null, ScaPackage.SCA_SERVICE__DEV_MGR, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
		case ScaPackage.SCA_SERVICE__DEV_MGR:
			return eInternalContainer().eInverseRemove(this, ScaPackage.SCA_DEVICE_MANAGER__SERVICES, ScaDeviceManager.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ScaPackage.SCA_SERVICE__PORTS:
			return getPorts();
		case ScaPackage.SCA_SERVICE__NAME:
			return getName();
		case ScaPackage.SCA_SERVICE__DEV_MGR:
			return getDevMgr();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ScaPackage.SCA_SERVICE__PORTS:
			getPorts().clear();
			getPorts().addAll((Collection< ? extends ScaPort< ? , ? >>) newValue);
			return;
		case ScaPackage.SCA_SERVICE__NAME:
			setName((String) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case ScaPackage.SCA_SERVICE__PORTS:
			unsetPorts();
			return;
		case ScaPackage.SCA_SERVICE__NAME:
			setName(NAME_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case ScaPackage.SCA_SERVICE__PORTS:
			return isSetPorts();
		case ScaPackage.SCA_SERVICE__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
		case ScaPackage.SCA_SERVICE__DEV_MGR:
			return getDevMgr() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class< ? > baseClass) {
		if (baseClass == ScaPortContainer.class) {
			switch (derivedFeatureID) {
			case ScaPackage.SCA_SERVICE__PORTS:
				return ScaPackage.SCA_PORT_CONTAINER__PORTS;
			default:
				return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class< ? > baseClass) {
		if (baseClass == ScaPortContainer.class) {
			switch (baseFeatureID) {
			case ScaPackage.SCA_PORT_CONTAINER__PORTS:
				return ScaPackage.SCA_SERVICE__PORTS;
			default:
				return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

	@Override
	protected org.omg.CORBA.Object narrow(org.omg.CORBA.Object obj) {
		// We can't narrow the service object since its type isn't known a priori
		return obj;
	}

	@Override
	protected Class<org.omg.CORBA.Object> getCorbaType() {
		return org.omg.CORBA.Object.class;
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 18.0
	 *        <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public ScaPort< ? , ? > getScaPort(String name) {
		// END GENERATED CODE
		for (ScaPort< ? , ? > port : getPorts()) {
			if (port.getName().equals(name)) {
				return port;
			}
		}
		return null;
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 18.0
	 *        <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<ScaPort< ? , ? >> fetchPorts(IProgressMonitor monitor) {
		if (isDisposed()) {
			return ECollections.emptyEList();
		}

		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching ports", 2);
		if (subMonitor.isCanceled()) {
			throw new OperationCanceledException();
		}
		internalFetchPorts(subMonitor.newChild(1));

		List<ScaPort< ? , ? >> ports = null;
		try {
			ports = ScaModelCommand.runExclusive(this, new RunnableWithResult.Impl<List<ScaPort< ? , ? >>>() {

				@Override
				public void run() {
					setResult(new ArrayList<>(getPorts()));
				}

			});
		} catch (InterruptedException e) {
			throw new OperationCanceledException();
		}
		if (ports != null) {
			SubMonitor portRefresh = subMonitor.newChild(1).setWorkRemaining(ports.size());
			for (ScaPort< ? , ? > port : ports) {
				try {
					if (subMonitor.isCanceled()) {
						throw new OperationCanceledException();
					}
					port.refresh(portRefresh.newChild(1), RefreshDepth.SELF);
				} catch (InterruptedException e) {
					throw new OperationCanceledException();
				}
			}
		}

		subMonitor.done();
		if (ports != null) {
			return ECollections.unmodifiableEList(new BasicEList<ScaPort< ? , ? >>(ports));
		} else {
			return ECollections.emptyEList();
		}
	}

	// END GENERATED CODE

	private final VersionedFeature portRevision = new VersionedFeature(this, ScaPackage.Literals.SCA_PORT_CONTAINER__PORTS);
	private PortSupplierOperations portSupplier;

	/**
	 * EMF feature path from a {@link ScaService} to the ports in its SCD file.
	 */
	private static final EStructuralFeature[] RESOURCE_TO_PORTS_PATH = { ScaPackage.Literals.PROFILE_OBJECT_WRAPPER__PROFILE_OBJ,
		SpdPackage.Literals.SOFT_PKG__DESCRIPTOR, SpdPackage.Literals.DESCRIPTOR__COMPONENT, ScdPackage.Literals.SOFTWARE_COMPONENT__COMPONENT_FEATURES,
		ScdPackage.Literals.COMPONENT_FEATURES__PORTS };

	// BEGIN GENERATED CODE

	/**
	 * <!-- begin-user-doc -->
	 * 
	 * @since 18.0
	 *        <!-- end-user-doc -->
	 * @throws InterruptedException
	 * @generated NOT
	 */
	protected void internalFetchPorts(IProgressMonitor monitor) {
		// END GENERATED CODE
		if (isDisposed()) {
			return;
		}

		SubMonitor subMonitor = SubMonitor.convert(monitor, 4);
		PortSupplierOperations currentObj = getPortSupplier();
		Transaction transaction = portRevision.createTransaction();
		if (currentObj != null) {
			if (subMonitor.isCanceled()) {
				throw new OperationCanceledException();
			}
			fetchProfileObject(subMonitor.newChild(1));

			Ports scdPorts = ScaEcoreUtils.getFeature(this, RESOURCE_TO_PORTS_PATH);
			int size = getPorts().size();
			int groupSize = (scdPorts == null) ? 0 : scdPorts.getGroup().size();
			if (isSetPorts() && size == groupSize) {
				return;
			}

			// Load all of the ports
			List<MergePortsCommand.PortData> newPorts = new ArrayList<MergePortsCommand.PortData>();
			final MultiStatus fetchPortsStatus = new MultiStatus(ScaModelPlugin.ID, Status.OK, "Fetch ports status.", null);
			if (scdPorts != null) {
				for (AbstractPort abstractPort : scdPorts.getAllPorts()) {
					String portName = abstractPort.getName();
					try {
						if (subMonitor.isCanceled()) {
							throw new OperationCanceledException();
						}
						org.omg.CORBA.Object portCorbaObj = currentObj.getPort(portName);
						newPorts.add(new PortData(abstractPort, portCorbaObj));
					} catch (UnknownPort e) {
						fetchPortsStatus.add(new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch port '" + portName + "'", e));
					} catch (SystemException e) {
						fetchPortsStatus.add(new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch port '" + portName + "'", e));
					}
				}
			}
			subMonitor.worked(1);

			MergePortsCommand command = new MergePortsCommand(this, newPorts, fetchPortsStatus);
			transaction.addCommand(command);
		} else {
			transaction.addCommand(new UnsetLocalAttributeCommand(this, Status.OK_STATUS, ScaPackage.Literals.SCA_PORT_CONTAINER__PORTS));
		}

		subMonitor.setWorkRemaining(1);
		transaction.commit();
		subMonitor.done();
		// BEGIN GENERATED CODE
	}

	private PortSupplierOperations getPortSupplier() {
		if (portSupplier == null) {
			// Cast or narrow
			final org.omg.CORBA.Object corbaObj = obj;
			final PortSupplier narrowedObj;
			if (corbaObj instanceof PortSupplier) {
				narrowedObj = (PortSupplier) corbaObj;
			} else if (corbaObj._is_a(PortSupplierHelper.id())) {
				narrowedObj = PortSupplierHelper.unchecked_narrow(corbaObj);
			} else {
				return null;
			}

			// Update in protected context so that we don't hold on to incorrect information if the CORBA object was
			// unset or changed
			ScaModelCommand.execute(this, new ScaModelCommand() {
				@Override
				public void execute() {
					if (obj == corbaObj) {
						portSupplier = narrowedObj;
					}
				}
			});
		}
		return portSupplier;
	}

	@Override
	public void query(PropertiesHolder configProperties) throws UnknownProperties {
		PropertySetOperations set = getPropertySet();
		if (set != null) {
			set.query(configProperties);
		}
	}

	private final VersionedFeature profileObjectRevision = new VersionedFeature(this, ScaPackage.Literals.PROFILE_OBJECT_WRAPPER__PROFILE_OBJ);

	/**
	 * @since 18.0
	 */
	@Override
	public SoftPkg fetchProfileObject(IProgressMonitor monitor) {
		if (isDisposed()) {
			return null;
		}
		if (isSetProfileObj()) {
			return getProfileObj();
		}

		Transaction transaction = profileObjectRevision.createTransaction();
		Command command = ProfileObjectWrapper.Util.fetchProfileObject(monitor, this, SoftPkg.class, SoftPkg.EOBJECT_PATH);
		transaction.addCommand(command);
		transaction.commit();
		return getProfileObj();
	}

	// END GENERATED CODE

	private final VersionedFeature profileURIFeature = new VersionedFeature(this, ScaPackage.Literals.PROFILE_OBJECT_WRAPPER__PROFILE_URI);

	/**
	 * Retrieves the {@link VersionedFeature} for the profile URI feature.
	 * 
	 * @since 20.4
	 */
	protected VersionedFeature getProfileURIVersionedFeature() {
		return profileURIFeature;
	}

	@Override
	public URI fetchProfileURI(IProgressMonitor monitor) {
		if (isDisposed()) {
			return null;
		}
		if (isSetProfileURI()) {
			return getProfileURI();
		}

		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetch profile URI.", 2);
		ScaDeviceManager devMgr = getDevMgr();
		if (devMgr != null) {
			if (subMonitor.isCanceled()) {
				throw new OperationCanceledException();
			}
			DeviceConfiguration dcd = devMgr.fetchProfileObject(subMonitor.newChild(1));

			String profilePath = null;
			ScaDeviceManagerFileSystem fileSystem = devMgr.fetchFileSystem(subMonitor.newChild(1), RefreshDepth.SELF);

			if (dcd != null) {
				DcdPartitioning part = dcd.getPartitioning();
				if (part != null) {
					out: for (DcdComponentPlacement cp : part.getComponentPlacement()) {
						for (DcdComponentInstantiation ci : cp.getComponentInstantiation()) {
							String usageName = ci.getUsageName();
							if (PluginUtil.equals(usageName, name)) {
								profilePath = cp.getComponentFileRef().getFile().getLocalFile().getName();
								break out;
							}
						}
					}
				}
			}
			if (fileSystem != null && profilePath != null) {
				Transaction transaction = profileURIFeature.createTransaction();
				final URI newURI = fileSystem.createURI(profilePath);
				transaction.addCommand(new ScaModelCommand() {

					@Override
					public void execute() {
						setProfileURI(newURI);
					}
				});
				transaction.commit();
			}
		}

		subMonitor.done();
		return getProfileURI();
	}

	/**
	 * @since 20.0
	 */
	@Override
	public void initializeProperties(final DataType[] configProperties) throws AlreadyInitialized, InvalidConfiguration, PartialConfiguration {
		PropertyEmitterOperations propEmitter = getPropertyEmitter();
		if (propEmitter == null) {
			throw new IllegalStateException("CORBA Object is null, or service does not support " + PropertyEmitterHelper.id());
		}
		propEmitter.initializeProperties(configProperties);
	}

	@Override
	public void configure(DataType[] configProperties) throws InvalidConfiguration, PartialConfiguration {
		PropertySetOperations set = getPropertySet();
		if (set == null) {
			throw new IllegalStateException("CORBA Object is null, or service does not support " + PropertySetHelper.id());
		}
		set.configure(configProperties);
		fetchProperties(null);
	}

	private PropertySetOperations getPropertySet() {
		if (propertySetOp == null) {
			// Cast or narrow
			final org.omg.CORBA.Object corbaObj = obj;
			final PropertySet narrowedObj;
			if (corbaObj instanceof PropertySet) {
				narrowedObj = (PropertySet) corbaObj;
			} else if (corbaObj._is_a(PropertySetHelper.id())) {
				narrowedObj = PropertySetHelper.unchecked_narrow(corbaObj);
			} else {
				return null;
			}

			// Update in protected context so that we don't hold on to incorrect information if the CORBA object was
			// unset or changed
			ScaModelCommand.execute(this, new ScaModelCommand() {
				@Override
				public void execute() {
					if (obj == corbaObj) {
						propertySetOp = narrowedObj;
					}
				}
			});
		}
		return propertySetOp;
	}

	private PropertyEmitterOperations getPropertyEmitter() {
		if (propertyEmitterOp == null) {
			// Cast or narrow
			final org.omg.CORBA.Object corbaObj = obj;
			final PropertyEmitter narrowedObj;
			if (corbaObj instanceof PropertyEmitter) {
				narrowedObj = (PropertyEmitter) corbaObj;
			} else if (corbaObj._is_a(PropertyEmitterHelper.id())) {
				narrowedObj = PropertyEmitterHelper.narrow(corbaObj);
			} else {
				return null;
			}

			// Update in protected context so that we don't hold on to incorrect information if the CORBA object was
			// unset or changed
			ScaModelCommand.execute(this, new ScaModelCommand() {
				@Override
				public void execute() {
					if (obj == corbaObj) {
						propertyEmitterOp = narrowedObj;
					}
				}
			});
		}
		return propertyEmitterOp;
	}

	private static final EStructuralFeature[] PRF_PATH = { SpdPackage.Literals.SOFT_PKG__PROPERTY_FILE, SpdPackage.Literals.PROPERTY_FILE__PROPERTIES };

	@Override
	protected List<AbstractProperty> fetchPropertyDefinitions(IProgressMonitor monitor) {
		if (isDisposed()) {
			return Collections.emptyList();
		}

		EObject localProfile = fetchProfileObject(monitor);
		mil.jpeojtrs.sca.prf.Properties propDefintions = ScaEcoreUtils.getFeature(localProfile, PRF_PATH);
		List<AbstractProperty> retVal = new ArrayList<AbstractProperty>();
		if (propDefintions != null) {
			for (ValueListIterator<Object> i = propDefintions.getProperties().valueListIterator(); i.hasNext();) {
				Object propDef = i.next();
				if (propDef instanceof AbstractProperty) {
					retVal.add((AbstractProperty) propDef);
				}
			}
		}
		return retVal;
	}

	@Override
	protected void internalFetchChildren(IProgressMonitor monitor) throws InterruptedException {
		internalFetchPorts(monitor);
	}

	// BEGIN GENERATED CODE

	@Override
	public void fetchAttributes(IProgressMonitor monitor) {
		// END GENERATED CODE
		SubMonitor subMonitor = SubMonitor.convert(monitor, 2);

		if (subMonitor.isCanceled()) {
			throw new OperationCanceledException();
		}
		super.fetchAttributes(subMonitor.newChild(1));

		if (subMonitor.isCanceled()) {
			throw new OperationCanceledException();
		}
		fetchProfileObject(subMonitor.newChild(1));

		if (subMonitor.isCanceled()) {
			throw new OperationCanceledException();
		}
		fetchProperties(subMonitor.newChild(1));

		subMonitor.done();
		// BEGIN GENERATED CODE
	}

	/**
	 * @since 14.0
	 */
	@Override
	protected void notifyChanged(Notification msg) {
		super.notifyChanged(msg);
		switch (msg.getFeatureID(ScaService.class)) {
		case ScaPackage.SCA_SERVICE__CORBA_OBJ:
			unsetPorts();
			portSupplier = null;
			propertySetOp = null;
			propertyEmitterOp = null;
			break;
		default:
			break;
		}
	}

} // ScaServiceImpl

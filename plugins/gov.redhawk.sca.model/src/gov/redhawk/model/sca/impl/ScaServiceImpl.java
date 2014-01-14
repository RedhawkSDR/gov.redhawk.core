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
import java.util.List;

import mil.jpeojtrs.sca.dcd.DcdComponentInstantiation;
import mil.jpeojtrs.sca.dcd.DcdComponentPlacement;
import mil.jpeojtrs.sca.dcd.DcdPartitioning;
import mil.jpeojtrs.sca.dcd.DeviceConfiguration;
import mil.jpeojtrs.sca.prf.AbstractProperty;
import mil.jpeojtrs.sca.scd.AbstractPort;
import mil.jpeojtrs.sca.scd.ScdPackage;
import mil.jpeojtrs.sca.spd.SoftPkg;
import mil.jpeojtrs.sca.spd.SpdPackage;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap.ValueListIterator;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.omg.CORBA.SystemException;

import CF.DataType;
import CF.PortSupplierHelper;
import CF.PortSupplierOperations;
import CF.PropertiesHolder;
import CF.PropertySetHelper;
import CF.PropertySetOperations;
import CF.UnknownProperties;
import CF.PortSupplierPackage.UnknownPort;
import CF.PropertySetPackage.InvalidConfiguration;
import CF.PropertySetPackage.PartialConfiguration;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service</b></em>'.
 * @since 12.0
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaServiceImpl#getPorts <em>Ports</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaServiceImpl#getName <em>Name</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.ScaServiceImpl#getDevMgr <em>Dev Mgr</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScaServiceImpl extends ScaPropertyContainerImpl<org.omg.CORBA.Object, SoftPkg> implements ScaService {
	/**
	 * The cached value of the '{@link #getPorts() <em>Ports</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * @since 18.0
	 * <!-- end-user-doc -->
	 * @see #getPorts()
	 * @generated
	 * @ordered
	 */
	protected EList<ScaPort< ? , ? >> ports;
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;
	private PropertySetOperations propertySetOp;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScaServiceImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScaPackage.Literals.SCA_SERVICE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 18.0
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific type known in this context.
	 * @generated
	 */
	@Override
	public void setProfileObj(SoftPkg newProfileObj) {
		super.setProfileObj(newProfileObj);
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 18.0
	 * <!-- end-user-doc -->
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
	 * @since 18.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetPorts() {
		if (ports != null)
			((InternalEList.Unsettable< ? >) ports).unset();
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 18.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetPorts() {
		return ports != null && ((InternalEList.Unsettable< ? >) ports).isSet();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
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
	 * @since 18.0
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ScaDeviceManager getDevMgr() {
		if (eContainerFeatureID() != ScaPackage.SCA_SERVICE__DEV_MGR)
			return null;
		return (ScaDeviceManager) eInternalContainer();
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
		// We don't know what to narrow to here so return the original object
		return obj;
	}

	@Override
	protected Class<org.omg.CORBA.Object> getCorbaType() {
		return org.omg.CORBA.Object.class;
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 18.0
	 * <!-- end-user-doc -->
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
	 * @since 18.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public EList<ScaPort< ? , ? >> fetchPorts(IProgressMonitor monitor) {
		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetching ports", 2);
		internalFetchPorts(subMonitor.newChild(1));
		ScaPort< ? , ? >[] ports = null;
		try {
			ports = ScaModelCommand.runExclusive(this, new RunnableWithResult.Impl<ScaPort< ? , ? >[]>() {

				@Override
				public void run() {
					setResult(getPorts().toArray(new ScaPort< ? , ? >[getPorts().size()]));
				}

			});
		} catch (InterruptedException e) {
			// PASS
		}
		if (ports != null) {
			SubMonitor portRefresh = subMonitor.newChild(1);
			portRefresh.beginTask("Refreshing state of ports", ports.length);
			for (ScaPort< ? , ? > port : ports) {
				try {
					port.refresh(portRefresh.newChild(1), RefreshDepth.SELF);
				} catch (InterruptedException e) {
					// PASS
				}
			}
		}
		subMonitor.done();
		return getPorts();
	}

	private final VersionedFeature portRevision = new VersionedFeature(this, ScaPackage.Literals.SCA_PORT_CONTAINER__PORTS);
	private PortSupplierOperations portSupplier;
	private static final EStructuralFeature[] PORTS_GROUP_PATH = { ScaPackage.Literals.PROFILE_OBJECT_WRAPPER__PROFILE_OBJ,
		SpdPackage.Literals.SOFT_PKG__DESCRIPTOR, SpdPackage.Literals.DESCRIPTOR__COMPONENT, ScdPackage.Literals.SOFTWARE_COMPONENT__COMPONENT_FEATURES,
		ScdPackage.Literals.COMPONENT_FEATURES__PORTS, ScdPackage.Literals.PORTS__GROUP };

	/**
	 * <!-- begin-user-doc -->
	 * @since 18.0
	 * <!-- end-user-doc -->
	 * @throws InterruptedException 
	 * @generated NOT
	 */
	protected void internalFetchPorts(IProgressMonitor monitor) {
		// END GENERATED CODE
		SubMonitor subMonitor = SubMonitor.convert(monitor, 4);
		PortSupplierOperations currentObj = getPortSupplier();
		Transaction transaction = portRevision.createTransaction();
		if (currentObj != null) {
			if (!isSetProfileObj()) {
				fetchProfileObject(subMonitor.newChild(1));
			} else {
				subMonitor.setWorkRemaining(2);
			}

			FeatureMap portGroup = ScaEcoreUtils.getFeature(this, PORTS_GROUP_PATH);
			int size = getPorts().size();
			int groupSize = (portGroup == null) ? 0 : portGroup.size();
			if (isSetPorts() && size == groupSize) {
				return;
			}
			List<MergePortsCommand.PortData> newPorts = new ArrayList<MergePortsCommand.PortData>();
			// Load all of the ports
			final MultiStatus fetchPortsStatus = new MultiStatus(ScaModelPlugin.ID, Status.OK, "Fetch ports status.", null);
			if (portGroup != null) {
				for (ValueListIterator<Object> i = portGroup.valueListIterator(); i.hasNext();) {
					Object portObj = i.next();
					if (portObj instanceof AbstractPort) {
						AbstractPort abstractPort = (AbstractPort) portObj;
						String portName = abstractPort.getName();
						try {
							org.omg.CORBA.Object portCorbaObj = currentObj.getPort(portName);
							newPorts.add(new PortData(abstractPort, portCorbaObj));
						} catch (UnknownPort e) {
							fetchPortsStatus.add(new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch port '" + portName + "'", e));
						} catch (SystemException e) {
							fetchPortsStatus.add(new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch port '" + portName + "'", e));
						}

					}
				}
			}
			subMonitor.worked(1);

			MergePortsCommand command = new MergePortsCommand(this, newPorts, fetchPortsStatus);

			// Perform the actions
			transaction.addCommand(command);
		} else {
			transaction.addCommand(new UnsetLocalAttributeCommand(this, Status.OK_STATUS, ScaPackage.Literals.SCA_PORT_CONTAINER__PORTS));
		}
		subMonitor.setWorkRemaining(1);
		transaction.commit();
		subMonitor.worked(1);
		subMonitor.done();
		// BEGIN GENERATED CODE
	}

	private PortSupplierOperations getPortSupplier() {
		if (this.portSupplier == null) {
			if (this.obj instanceof PortSupplierOperations) {
				this.portSupplier = (PortSupplierOperations) this.obj;
			}
			if (this.obj._is_a(PortSupplierHelper.id())) {
				this.portSupplier = PortSupplierHelper.narrow(obj);
			}
		}
		return this.portSupplier;
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
		Transaction transaction = profileObjectRevision.createTransaction();
		Command command = ProfileObjectWrapper.Util.fetchProfileObject(monitor, this, SoftPkg.class, SoftPkg.EOBJECT_PATH);
		transaction.addCommand(command);
		transaction.commit();
		return getProfileObj();
	}

	private final VersionedFeature profileURIFeature = new VersionedFeature(this, ScaPackage.Literals.PROFILE_OBJECT_WRAPPER__PROFILE_URI);

	@Override
	public URI fetchProfileURI(IProgressMonitor monitor) {
		if (isSetProfileURI()) {
			return getProfileURI();
		}
		SubMonitor subMonitor = SubMonitor.convert(monitor, "Fetch profile URI.", 2);
		ScaDeviceManager devMgr = getDevMgr();
		if (devMgr != null) {
			DeviceConfiguration dcd = devMgr.fetchProfileObject(subMonitor.newChild(1));
			String profilePath = null;
			ScaDeviceManagerFileSystem fileSystem = devMgr.fetchFileSystem(subMonitor.newChild(1));
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

	@Override
	public void configure(DataType[] configProperties) throws InvalidConfiguration, PartialConfiguration {
		PropertySetOperations set = getPropertySet();
		if (set != null) {
			set.configure(configProperties);
		}
	}

	private PropertySetOperations getPropertySet() {
		if (this.propertySetOp == null) {
			if (this.obj instanceof PropertySetOperations) {
				this.propertySetOp = (PropertySetOperations) this.obj;
			}
			if (this.obj._is_a(PropertySetHelper.id())) {
				this.propertySetOp = PropertySetHelper.narrow(obj);
			}
		}
		return this.propertySetOp;
	}

	private static final EStructuralFeature[] PRF_PATH = { SpdPackage.Literals.SOFT_PKG__PROPERTY_FILE, SpdPackage.Literals.PROPERTY_FILE__PROPERTIES };

	@Override
	protected List<AbstractProperty> fetchPropertyDefinitions(IProgressMonitor monitor) {
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

	@Override
	public void fetchAttributes(IProgressMonitor monitor) {
		SubMonitor subMonitor = SubMonitor.convert(monitor, 2); //SUPPRESS CHECKSTYLE MagicNumber
		super.fetchAttributes(subMonitor.newChild(1));
		fetchProfileObject(subMonitor.newChild(1));
		fetchProperties(subMonitor.newChild(1));
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
			break;
		default:
			break;
		}
	}

} //ScaServiceImpl

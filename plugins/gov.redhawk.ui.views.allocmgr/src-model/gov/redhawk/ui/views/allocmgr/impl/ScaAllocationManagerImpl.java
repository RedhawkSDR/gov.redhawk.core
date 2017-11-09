/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
// BEGIN GENERATED CODE
package gov.redhawk.ui.views.allocmgr.impl;

import java.util.Collection;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.omg.CORBA.SystemException;

import CF.AllocationManager;
import CF.AllocationManagerHelper;
import CF.AllocationStatusIteratorHolder;
import CF.DeviceLocationIteratorHolder;
import CF.DomainManager;
import CF.AllocationManagerPackage.AllocationError;
import CF.AllocationManagerPackage.AllocationRequestType;
import CF.AllocationManagerPackage.AllocationResponseType;
import CF.AllocationManagerPackage.AllocationScopeType;
import CF.AllocationManagerPackage.AllocationStatusSequenceHolder;
import CF.AllocationManagerPackage.AllocationStatusType;
import CF.AllocationManagerPackage.DeviceLocationSequenceHolder;
import CF.AllocationManagerPackage.DeviceLocationType;
import CF.AllocationManagerPackage.DeviceScopeType;
import CF.AllocationManagerPackage.InvalidAllocationId;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.commands.SetStatusCommand;
import gov.redhawk.model.sca.commands.UnsetLocalAttributeCommand;
import gov.redhawk.model.sca.commands.VersionedFeature;
import gov.redhawk.model.sca.commands.VersionedFeature.Transaction;
import gov.redhawk.model.sca.impl.CorbaObjWrapperImpl;
import gov.redhawk.ui.views.allocmgr.AllocMgrPackage;
import gov.redhawk.ui.views.allocmgr.AllocationStatus;
import gov.redhawk.ui.views.allocmgr.ScaAllocationManager;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Sca Allocation Manager</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 * <li>{@link gov.redhawk.ui.views.allocmgr.impl.ScaAllocationManagerImpl#getAllocations <em>Allocations</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ScaAllocationManagerImpl extends CorbaObjWrapperImpl<AllocationManager> implements ScaAllocationManager {
	/**
	 * The cached value of the '{@link #getAllocations() <em>Allocations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAllocations()
	 * @generated
	 * @ordered
	 */
	protected EList<AllocationStatus> allocations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScaAllocationManagerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return AllocMgrPackage.Literals.SCA_ALLOCATION_MANAGER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * This is specialized for the more specific type known in this context.
	 * @generated
	 */
	@Override
	public void setObj(AllocationManager newObj) {
		super.setObj(newObj);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AllocationStatus> getAllocations() {
		if (allocations == null) {
			allocations = new EObjectContainmentEList<AllocationStatus>(AllocationStatus.class, this, AllocMgrPackage.SCA_ALLOCATION_MANAGER__ALLOCATIONS);
		}
		return allocations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case AllocMgrPackage.SCA_ALLOCATION_MANAGER__ALLOCATIONS:
			return ((InternalEList< ? >) getAllocations()).basicRemove(otherEnd, msgs);
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
		case AllocMgrPackage.SCA_ALLOCATION_MANAGER__ALLOCATIONS:
			return getAllocations();
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
		case AllocMgrPackage.SCA_ALLOCATION_MANAGER__ALLOCATIONS:
			getAllocations().clear();
			getAllocations().addAll((Collection< ? extends AllocationStatus>) newValue);
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
		case AllocMgrPackage.SCA_ALLOCATION_MANAGER__ALLOCATIONS:
			getAllocations().clear();
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
		case AllocMgrPackage.SCA_ALLOCATION_MANAGER__ALLOCATIONS:
			return allocations != null && !allocations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	// END GENERATED CODE

	@Override
	public DeviceLocationType[] allDevices() {
		AllocationManager allocMgr = fetchNarrowedObject(null);
		if (allocMgr == null) {
			throw new IllegalStateException("CORBA Object is null");
		}

		return allocMgr.allDevices();
	}

	@Override
	public DeviceLocationType[] authorizedDevices() {
		AllocationManager allocMgr = fetchNarrowedObject(null);
		if (allocMgr == null) {
			throw new IllegalStateException("CORBA Object is null");
		}

		return allocMgr.authorizedDevices();
	}

	@Override
	public DeviceLocationType[] localDevices() {
		AllocationManager allocMgr = fetchNarrowedObject(null);
		if (allocMgr == null) {
			throw new IllegalStateException("CORBA Object is null");
		}

		return allocMgr.localDevices();
	}

	@Override
	public void listDevices(DeviceScopeType deviceScope, int count, DeviceLocationSequenceHolder devices, DeviceLocationIteratorHolder dl) {
		AllocationManager allocMgr = fetchNarrowedObject(null);
		if (allocMgr == null) {
			throw new IllegalStateException("CORBA Object is null");
		}

		allocMgr.listDevices(deviceScope, count, devices, dl);
	}

	@Override
	public DomainManager domainMgr() {
		AllocationManager allocMgr = fetchNarrowedObject(null);
		if (allocMgr == null) {
			throw new IllegalStateException("CORBA Object is null");
		}

		return allocMgr.domainMgr();
	}

	@Override
	public AllocationResponseType[] allocate(AllocationRequestType[] requests) throws AllocationError {
		AllocationManager allocMgr = fetchNarrowedObject(null);
		if (allocMgr == null) {
			throw new IllegalStateException("CORBA Object is null");
		}

		return allocMgr.allocate(requests);
	}

	@Override
	public AllocationResponseType[] allocateLocal(AllocationRequestType[] requests, String domainName) throws AllocationError {
		AllocationManager allocMgr = fetchNarrowedObject(null);
		if (allocMgr == null) {
			throw new IllegalStateException("CORBA Object is null");
		}

		return allocMgr.allocateLocal(requests, domainName);
	}

	@Override
	public void deallocate(String[] allocationIDs) throws InvalidAllocationId {
		AllocationManager allocMgr = fetchNarrowedObject(null);
		if (allocMgr == null) {
			throw new IllegalStateException("CORBA Object is null");
		}

		allocMgr.deallocate(allocationIDs);
	}

	@Override
	public AllocationStatusType[] allocations(String[] allocationIDs) throws InvalidAllocationId {
		AllocationManager allocMgr = fetchNarrowedObject(null);
		if (allocMgr == null) {
			throw new IllegalStateException("CORBA Object is null");
		}

		return allocMgr.allocations(allocationIDs);
	}

	@Override
	public AllocationStatusType[] localAllocations(String[] allocationIDs) throws InvalidAllocationId {
		AllocationManager allocMgr = fetchNarrowedObject(null);
		if (allocMgr == null) {
			throw new IllegalStateException("CORBA Object is null");
		}

		return allocMgr.localAllocations(allocationIDs);
	}

	@Override
	public void listAllocations(AllocationScopeType allocScope, int howMany, AllocationStatusSequenceHolder allocs, AllocationStatusIteratorHolder ai) {
		AllocationManager allocMgr = fetchNarrowedObject(null);
		if (allocMgr == null) {
			throw new IllegalStateException("CORBA Object is null");
		}

		allocMgr.listAllocations(allocScope, howMany, allocs, ai);
	}

	@Override
	protected AllocationManager narrow(org.omg.CORBA.Object obj) {
		return AllocationManagerHelper.narrow(obj);
	}

	@Override
	protected void internalFetchChildren(IProgressMonitor monitor) throws InterruptedException {
		internalFetchAllocations(monitor);
	}

	private VersionedFeature allocationFeature = new VersionedFeature(this, AllocMgrPackage.Literals.SCA_ALLOCATION_MANAGER__ALLOCATIONS);

	private void internalFetchAllocations(IProgressMonitor monitor) {
		if (isDisposed()) {
			return;
		}

		final SubMonitor subMonitor = SubMonitor.convert(monitor, 3);
		final AllocationManager allocMgr = fetchNarrowedObject(subMonitor.split(1));
		if (subMonitor.isCanceled()) {
			throw new OperationCanceledException();
		}

		Transaction transaction = allocationFeature.createTransaction();
		if (allocMgr != null) {
			AllocationStatusType[] cfAllocStatuses;
			try {
				cfAllocStatuses = allocMgr.allocations(new String[0]);
				transaction.addCommand(new ScaAllocationManagerMergeAllocationsCommand(this, cfAllocStatuses));
			} catch (InvalidAllocationId | SystemException e) {
				Status status = new Status(Status.ERROR, ScaModelPlugin.ID, "Failed to fetch allocations.", e);
				transaction.addCommand(new SetStatusCommand<>(this, AllocMgrPackage.Literals.SCA_ALLOCATION_MANAGER__ALLOCATIONS, status));
			}
			subMonitor.worked(1);
		} else {
			transaction.addCommand(new UnsetLocalAttributeCommand(this, null, AllocMgrPackage.Literals.SCA_ALLOCATION_MANAGER__ALLOCATIONS));
		}

		// Perform Actions
		subMonitor.setWorkRemaining(1);
		transaction.commit();
		subMonitor.done();
	}

	// BEGIN GENERATED CODE

} // ScaAllocationManagerImpl

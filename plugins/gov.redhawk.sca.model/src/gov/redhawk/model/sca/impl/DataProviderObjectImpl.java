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

import gov.redhawk.model.sca.DataProviderObject;
import gov.redhawk.model.sca.IDisposable;
import gov.redhawk.model.sca.IRefreshable;
import gov.redhawk.model.sca.IScaDataProviderServiceDescriptor;
import gov.redhawk.model.sca.RefreshDepth;
import gov.redhawk.model.sca.ScaModelPlugin;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.services.IScaDataProvider;
import gov.redhawk.model.sca.services.IScaDataProviderService;
import gov.redhawk.sca.model.internal.DataProviderServicesRegistry;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.transaction.RunnableWithResult;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Provider Object</b></em>'.
 * @since 12.0
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.model.sca.impl.DataProviderObjectImpl#isDisposed <em>Disposed</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.DataProviderObjectImpl#getDataProviders <em>Data Providers</em>}</li>
 *   <li>{@link gov.redhawk.model.sca.impl.DataProviderObjectImpl#isDataProvidersEnabled <em>Data Providers Enabled</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class DataProviderObjectImpl extends IStatusProviderImpl implements DataProviderObject {
	/**
	 * The default value of the '{@link #isDisposed() <em>Disposed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDisposed()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DISPOSED_EDEFAULT = false;
	/**
	 * The cached value of the '{@link #isDisposed() <em>Disposed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDisposed()
	 * @generated
	 * @ordered
	 */
	protected boolean disposed = DISPOSED_EDEFAULT;
	/**
	 * The cached value of the '{@link #getDataProviders() <em>Data Providers</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataProviders()
	 * @generated
	 * @ordered
	 */
	protected EList<IScaDataProvider> dataProviders;
	/**
	 * The default value of the '{@link #isDataProvidersEnabled() <em>Data Providers Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDataProvidersEnabled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DATA_PROVIDERS_ENABLED_EDEFAULT = true;
	/**
	 * The cached value of the '{@link #isDataProvidersEnabled() <em>Data Providers Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDataProvidersEnabled()
	 * @generated
	 * @ordered
	 */
	protected boolean dataProvidersEnabled = DATA_PROVIDERS_ENABLED_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataProviderObjectImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ScaPackage.Literals.DATA_PROVIDER_OBJECT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isDisposed() {
		return disposed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<IScaDataProvider> getDataProviders() {
		if (dataProviders == null) {
			dataProviders = new EDataTypeUniqueEList.Unsettable<IScaDataProvider>(IScaDataProvider.class, this, ScaPackage.DATA_PROVIDER_OBJECT__DATA_PROVIDERS);
		}
		return dataProviders;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetDataProviders() {
		if (dataProviders != null)
			((InternalEList.Unsettable< ? >) dataProviders).unset();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetDataProviders() {
		return dataProviders != null && ((InternalEList.Unsettable< ? >) dataProviders).isSet();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isDataProvidersEnabled() {
		return dataProvidersEnabled;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDataProvidersEnabled(boolean newDataProvidersEnabled) {
		boolean oldDataProvidersEnabled = dataProvidersEnabled;
		dataProvidersEnabled = newDataProvidersEnabled;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.DATA_PROVIDER_OBJECT__DATA_PROVIDERS_ENABLED, oldDataProvidersEnabled,
				dataProvidersEnabled));
	}

	private PropertyChangeListener providerListener = new PropertyChangeListener() {

		@Override
		public void propertyChange(final PropertyChangeEvent evt) {
			if (isDisposed()) {
				if (evt.getSource() instanceof IScaDataProvider) {
					((IScaDataProvider) evt.getSource()).removePropertyChangeListener(this);
				}
				return;
			}
			if (IScaDataProvider.DISPOSED_PROPERTY.equals(evt.getPropertyName())) {
				ScaModelCommand.execute(DataProviderObjectImpl.this, new ScaModelCommand() {

					@Override
					public void execute() {
						dataProviderDisposed((IScaDataProvider) evt.getSource());
					}
				});
			} else if (IScaDataProvider.STATUS_PROPERTY.equals(evt.getPropertyName())) {
				ScaModelCommand.execute(DataProviderObjectImpl.this, new ScaModelCommand() {

					@Override
					public void execute() {
						updateDataProviderStatus();
					}
				});
			}
		}
	};
	/**
	 * The default value of the '{@link #getStatus() <em>Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStatus()
	 * @generated NOT
	 * @ordered
	 */
	protected static final IStatus STATUS_EDEFAULT = Status.OK_STATUS;

	private void updateDataProviderStatus() {
		final MultiStatus dataProviderStatus = new MultiStatus(ScaModelPlugin.ID, Status.OK, "Problems in data providers", null);
		for (final IScaDataProvider provider : getDataProviders()) {
			if (provider == null) {
				continue;
			}
			SafeRunner.run(new ISafeRunnable() {

				@Override
				public void run() throws Exception {
					IStatus status = provider.getStatus();
					if (status != null && !status.isOK()) {
						dataProviderStatus.add(status);
					}
				}

				@Override
				public void handleException(Throwable exception) {
				}
			});
		}
		if (dataProviderStatus.isOK()) {
			setStatus(ScaPackage.Literals.DATA_PROVIDER_OBJECT__DATA_PROVIDERS, null);
		} else {
			if (dataProviderStatus.getChildren().length == 1) {
				setStatus(ScaPackage.Literals.DATA_PROVIDER_OBJECT__DATA_PROVIDERS, dataProviderStatus.getChildren()[0]);
			} else {
				setStatus(ScaPackage.Literals.DATA_PROVIDER_OBJECT__DATA_PROVIDERS, dataProviderStatus);
			}
		}
	}

	private void dataProviderDisposed(final IScaDataProvider source) {
		getDataProviders().remove(source);
	}

	/**
	 * TODO I'm still not convinced we should be doing this
	 * {@inheritDoc}
	 */
	@Override
	public void eNotify(Notification notification) {
		if (notification.getEventType() == Notification.SET && notification.isTouch()) {
			return;
		} else {
			super.eNotify(notification);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void attachDataProviders() {
		// END GENERATED CODE
		if (isSetDataProviders()) {
			return;
		}
		if (!isDataProvidersEnabled()) {
			// DO this to set the is set status
			getDataProviders().clear();
			return;
		}
		List<IScaDataProviderServiceDescriptor> descriptors = DataProviderServicesRegistry.INSTANCE.getDataProvidersDescriptors();
		final List<IScaDataProvider> newProviders = new ArrayList<IScaDataProvider>();
		for (final IScaDataProviderServiceDescriptor desc : descriptors) {
			if (desc.getService() == null) {
				continue;
			}
			SafeRunner.run(new ISafeRunnable() {

				@Override
				public void run() throws Exception {
					IScaDataProviderService service = desc.getService();
					IScaDataProvider provider = service.hookDataProvider(DataProviderObjectImpl.this);
					if (provider != null) {
						newProviders.add(provider);
					}
				}

				@Override
				public void handleException(Throwable exception) {
				}
			});
		}
		getDataProviders().addAll(newProviders);
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 14.0
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void detachDataProviders() {
		// END GENERATED CODE
		unsetDataProviders();
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * Ask all data providers connected to this object to do an immediate refresh.
	 * <!-- end-user-doc -->
	 * @throws InterruptedException 
	 * @generated NOT
	 */
	@Override
	public void refresh(IProgressMonitor monitor, RefreshDepth depth) throws InterruptedException {
		// END GENERATED CODE
		if (isDisposed()) {
			return;
		}
		Assert.isNotNull(depth);
		if (depth == RefreshDepth.NONE) {
			return;
		}
		final SubMonitor subMonitor = SubMonitor.convert(monitor, 4);

		if (RefreshDepth.CHILDREN == depth) {
			depth = RefreshDepth.SELF;
		} else if (RefreshDepth.SELF == depth) {
			depth = null;
		}

		if (depth != null) {
			final EObject[] contents = ScaModelCommand.runExclusive(this, new RunnableWithResult.Impl<EObject[]>() {

				@Override
				public void run() {
					setResult(eContents().toArray(new EObject[eContents().size()]));
				}

			});
			if (contents != null) {
				SubMonitor contentsMonitor = subMonitor.newChild(1).setWorkRemaining(contents.length);
				int index = contents.length;
				for (final EObject obj : contents) {
					if (obj instanceof DataProviderObject) {
						((DataProviderObject) obj).refresh(contentsMonitor.newChild(1), depth);
					}
					index--;
					contentsMonitor.setWorkRemaining(index);
				}
			} else {
				subMonitor.worked(1);
			}
		}
		subMonitor.setWorkRemaining(1);
		subMonitor.done();
		// BEGIN GENERATED CODE
	}

	private void removeDataProvider(final IScaDataProvider dataProvider) {
		SafeRunner.run(new ISafeRunnable() {

			@Override
			public void run() throws Exception {
				dataProvider.removePropertyChangeListener(providerListener);
				dataProvider.dispose();
			}

			@Override
			public void handleException(Throwable exception) {
			}
		});
	}

	@Override
	protected void notifyChanged(Notification msg) {
		// END GENERATED CODE
		super.notifyChanged(msg);
		switch (msg.getFeatureID(DataProviderObject.class)) {
		case ScaPackage.DATA_PROVIDER_OBJECT__DATA_PROVIDERS_ENABLED:
			if (!msg.isTouch()) {
				if (!msg.getNewBooleanValue()) {
					detachDataProviders();
				} else {
					unsetDataProviders();
					attachDataProviders();
				}
			}
			break;
		case ScaPackage.DATA_PROVIDER_OBJECT__DATA_PROVIDERS:
			switch (msg.getEventType()) {
			case Notification.REMOVE:
				if (msg.getOldValue() instanceof IScaDataProvider) {
					final IScaDataProvider dataProvider = (IScaDataProvider) msg.getOldValue();
					removeDataProvider(dataProvider);
				}
				break;
			case Notification.REMOVE_MANY:
				if (msg.getOldValue() instanceof Collection< ? >) {
					for (Object obj : (Collection< ? >) msg.getOldValue()) {
						if (obj instanceof IScaDataProvider) {
							final IScaDataProvider dataProvider = (IScaDataProvider) obj;
							removeDataProvider(dataProvider);
						}
					}
				}
				break;
			case Notification.ADD:
				if (msg.getNewValue() instanceof IScaDataProvider) {
					IScaDataProvider dataProvider = (IScaDataProvider) msg.getNewValue();
					addDataProvider(dataProvider);
				}
				break;
			case Notification.ADD_MANY:
				if (msg.getNewValue() instanceof Collection< ? >) {
					for (Object obj : (Collection< ? >) msg.getNewValue()) {
						if (obj instanceof IScaDataProvider) {
							IScaDataProvider dataProvider = (IScaDataProvider) obj;
							addDataProvider(dataProvider);
						}
					}
				}
				break;
			default:
				break;
			}
			if (!msg.isTouch()) {
				updateDataProviderStatus();
			}
			break;
		default:
			break;
		}
		// BEGIN GENERATED CODE
	}

	private void addDataProvider(final IScaDataProvider dataProvider) {
		SafeRunner.run(new ISafeRunnable() {

			@Override
			public void run() throws Exception {
				dataProvider.addPropertyChangeListener(providerListener);
			}

			@Override
			public void handleException(Throwable exception) {
			}
		});
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public void dispose() {
		// END GENERATED CODE
		this.disposed = true;
		detachDataProviders();
		clearAllStatus();
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, ScaPackage.DATA_PROVIDER_OBJECT__DISPOSED, false, this.disposed));
		}
		eAdapters().clear();
		// BEGIN GENERATED CODE
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ScaPackage.DATA_PROVIDER_OBJECT__DISPOSED:
			return isDisposed();
		case ScaPackage.DATA_PROVIDER_OBJECT__DATA_PROVIDERS:
			return getDataProviders();
		case ScaPackage.DATA_PROVIDER_OBJECT__DATA_PROVIDERS_ENABLED:
			return isDataProvidersEnabled();
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
		case ScaPackage.DATA_PROVIDER_OBJECT__DATA_PROVIDERS:
			getDataProviders().clear();
			getDataProviders().addAll((Collection< ? extends IScaDataProvider>) newValue);
			return;
		case ScaPackage.DATA_PROVIDER_OBJECT__DATA_PROVIDERS_ENABLED:
			setDataProvidersEnabled((Boolean) newValue);
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
		case ScaPackage.DATA_PROVIDER_OBJECT__DATA_PROVIDERS:
			unsetDataProviders();
			return;
		case ScaPackage.DATA_PROVIDER_OBJECT__DATA_PROVIDERS_ENABLED:
			setDataProvidersEnabled(DATA_PROVIDERS_ENABLED_EDEFAULT);
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
		case ScaPackage.DATA_PROVIDER_OBJECT__DISPOSED:
			return disposed != DISPOSED_EDEFAULT;
		case ScaPackage.DATA_PROVIDER_OBJECT__DATA_PROVIDERS:
			return isSetDataProviders();
		case ScaPackage.DATA_PROVIDER_OBJECT__DATA_PROVIDERS_ENABLED:
			return dataProvidersEnabled != DATA_PROVIDERS_ENABLED_EDEFAULT;
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
		if (baseClass == IDisposable.class) {
			switch (derivedFeatureID) {
			case ScaPackage.DATA_PROVIDER_OBJECT__DISPOSED:
				return ScaPackage.IDISPOSABLE__DISPOSED;
			default:
				return -1;
			}
		}
		if (baseClass == IRefreshable.class) {
			switch (derivedFeatureID) {
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
		if (baseClass == IDisposable.class) {
			switch (baseFeatureID) {
			case ScaPackage.IDISPOSABLE__DISPOSED:
				return ScaPackage.DATA_PROVIDER_OBJECT__DISPOSED;
			default:
				return -1;
			}
		}
		if (baseClass == IRefreshable.class) {
			switch (baseFeatureID) {
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
		result.append(" (disposed: ");
		result.append(disposed);
		result.append(", dataProviders: ");
		result.append(dataProviders);
		result.append(", dataProvidersEnabled: ");
		result.append(dataProvidersEnabled);
		result.append(')');
		return result.toString();
	}

	@Override
	protected void eBasicSetContainer(InternalEObject newContainer, int newContainerFeatureID) {
		if (newContainer instanceof DataProviderObject) {
			setDataProvidersEnabled(((DataProviderObject) newContainer).isDataProvidersEnabled());
		}
		super.eBasicSetContainer(newContainer, newContainerFeatureID);
	}

} //DataProviderObjectImpl

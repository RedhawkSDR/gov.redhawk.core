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

 // BEGIN GENERATED CODE
package gov.redhawk.eclipsecorba.library.impl;

import gov.redhawk.eclipsecorba.idl.Definition;
import gov.redhawk.eclipsecorba.idl.Specification;
import gov.redhawk.eclipsecorba.idl.util.IdlResourceFactoryImpl;
import gov.redhawk.eclipsecorba.idl.util.IdlResourceImpl;
import gov.redhawk.eclipsecorba.library.IdlLibrary;
import gov.redhawk.eclipsecorba.library.LibraryPackage;
import gov.redhawk.eclipsecorba.library.LibraryPlugin;
import gov.redhawk.eclipsecorba.library.Path;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.Resource.Diagnostic;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Idl Library</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.library.impl.IdlLibraryImpl#getSpecifications <em>Specifications</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.library.impl.IdlLibraryImpl#getPaths <em>Paths</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.library.impl.IdlLibraryImpl#getLoadStatus <em>Load Status</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IdlLibraryImpl extends RepositoryModuleImpl implements IdlLibrary {
	/**
	 * The cached value of the '{@link #getSpecifications() <em>Specifications</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSpecifications()
	 * @generated
	 * @ordered
	 */
	protected EList<Specification> specifications;
	/**
	 * The cached value of the '{@link #getPaths() <em>Paths</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPaths()
	 * @generated
	 * @ordered
	 */
	protected EList<Path> paths;
	/**
	 * The default value of the '{@link #getLoadStatus() <em>Load Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoadStatus()
	 * @generated
	 * @ordered
	 */
	protected static final IStatus LOAD_STATUS_EDEFAULT = null;
	/**
	 * The cached value of the '{@link #getLoadStatus() <em>Load Status</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLoadStatus()
	 * @generated
	 * @ordered
	 */
	protected IStatus loadStatus = LOAD_STATUS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IdlLibraryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LibraryPackage.Literals.IDL_LIBRARY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Specification> getSpecifications() {
		if (specifications == null) {
			specifications = new EObjectResolvingEList<Specification>(Specification.class, this, LibraryPackage.IDL_LIBRARY__SPECIFICATIONS);
		}
		return specifications;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Path> getPaths() {
		if (paths == null) {
			paths = new EObjectContainmentEList<Path>(Path.class, this, LibraryPackage.IDL_LIBRARY__PATHS);
		}
		return paths;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IStatus getLoadStatus() {
		return loadStatus;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLoadStatus(IStatus newLoadStatus) {
		IStatus oldLoadStatus = loadStatus;
		loadStatus = newLoadStatus;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LibraryPackage.IDL_LIBRARY__LOAD_STATUS, oldLoadStatus, loadStatus));
	}

	// KLUDGE ALERT!! omniORB ships with crappy IDLs.  Skip the ones that are broken..
	private final String[] BROKEN_OMNIORB_IDL_FILES = new String[] {
	        "Naming.idl", // Conflicts with CosNaming.idl
	        // OmniORB doesn't support security interfaces
	        "DCE_CIOPSecurity.idl", "Security.idl", "SecurityLevel1.idl", "SecurityLevel2.idl", "SecurityAdmin.idl", "SecurityReplaceable.idl",
	        "NRService.idl", "SECIOP.idl", "SSLIOP.idl",
	        // OmniORB doesn't support Transactions
	        "CosTransactions.idl", "CosTSPortability.idl", "CosConcurrencyControl.idl", };
	{
		Arrays.sort(this.BROKEN_OMNIORB_IDL_FILES); // Must be sorted to use binarySearch later on
	}
	
	private final Adapter listener = new AdapterImpl() {
		{
			eAdapters().add(this);
		}
		@SuppressWarnings("unchecked")
        @Override
        public void notifyChanged(final Notification msg) {
			switch(msg.getFeatureID(IdlLibrary.class)) {
			case LibraryPackage.IDL_LIBRARY__SPECIFICATIONS:
				switch (msg.getEventType()) {
				case Notification.ADD:
					specificationAdded((Specification) msg.getNewValue());
					break;
				case Notification.ADD_MANY:
					for (final Specification spec : ((Collection<Specification>)msg.getNewValue())) {
						specificationAdded(spec);
					}
					break;
				}
				break;
			}
		}
	};

	private void specificationAdded(final Specification spec) {
		final EList<Definition> definitions = spec.getDefinitions();
		for (final Definition def : definitions) {
			addDefinition(def);
		}
	}

	private List<URI> getPathList() {
		final List<URI> derivedPaths = new LinkedList<URI>();
		for (final Path path : getPaths()) {
			derivedPaths.addAll(path.getDerivedPath());
		}
		return Collections.unmodifiableList(derivedPaths);
	}

	protected void addFileStore(final EditingDomain domain, final IFileStore store, final IProgressMonitor monitor) throws CoreException {
		try {
			if (store.getName().startsWith(".")){
				return;
			}
			if (store.fetchInfo().isDirectory()) {
				final SubMonitor subMonitor = SubMonitor.convert(monitor, "Adding directory store: " + store.getName(), 100);
				final IFileStore[] children = store.childStores(0, subMonitor.newChild(50));
				final SubMonitor childMonitor = subMonitor.newChild(50);
				childMonitor.beginTask("Adding children...", children.length);
				for (final IFileStore child : children) {
					if (store.toString().matches(".*/omniORB.*")) {
						if (Arrays.binarySearch(this.BROKEN_OMNIORB_IDL_FILES, child.getName()) >= 0) {
							continue;
						}
					}
					addFileStore(domain, child, childMonitor.newChild(1));
				}
			} else if (store.getName().endsWith(".idl")) {
				final SubMonitor subMonitor = SubMonitor.convert(monitor, "Adding idl store: " + store.getName(), 100);
				final URI uri = URI.createURI(store.toURI().toString()).appendFragment("/");
				EObject tmpObj = null;
				try {
					tmpObj = eResource().getResourceSet().getEObject(uri, true);
				} catch (final Exception e) {
					// PASS (we collect any load errors later from the ResourceSet)
				}
				final EObject eObj = tmpObj;
				subMonitor.worked(50);
				if (eObj instanceof Specification) {
					final Specification spec = (Specification) eObj;
					domain.getCommandStack().execute(new AbstractCommand() {

						@Override
						protected boolean prepare() {
							return true;
						}

						public void execute() {
							getSpecifications().add(spec);
						}

						@Override
						public boolean canUndo() {
							return false;
						}

						public void redo() {

						}

					});
					subMonitor.worked(50);
				}
			}
		} finally {
			monitor.done();
		}

	}

	@Override
	protected void clear() {
		eAdapters().remove(this.listener);
		getSpecifications().clear();
	    super.clear();
	    eAdapters().add(this.listener);
	}

	/**
	 * <!-- begin-user-doc -->
	 * @since 2.1 
	 * @param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility
	 *  to call done() on the given monitor. Accepts null, indicating that no progress should be
	 *  reported and that the operation cannot be canceled.
	 * <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public synchronized IStatus load(final IProgressMonitor monitor) throws CoreException {
		// END GENERATED CODE
		final TransactionalEditingDomain editingDomain = TransactionUtil.getEditingDomain(this);

		final IStatus loadingStatus = new Status(IStatus.INFO, LibraryPlugin.PLUGIN_ID, "Loading...");
		editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, this, LibraryPackage.Literals.IDL_LIBRARY__LOAD_STATUS, loadingStatus));
		editingDomain.getCommandStack().execute(new AbstractCommand() {

			@Override
			protected boolean prepare() {
				return true;
			}

			public void execute() {
				clear();
			}

			@Override
			public boolean canUndo() {
				return false;
			}

			public void redo() {

			}

		});
		final ResourceSet resourceSet = editingDomain.getResourceSet();

		// Ensure we are using our IDL resource factory
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("idl", new IdlResourceFactoryImpl());

		resourceSet.getLoadOptions().remove(IdlResourceImpl.ROOT_SCOPE);
		final Resource[] resources = resourceSet.getResources().toArray(new Resource[resourceSet.getResources().size()]);

		for (final Resource r : resources) {
			if (r instanceof IdlResourceImpl) {
				r.unload();
				resourceSet.getResources().remove(r);
			}
		}

		final MultiStatus libraryStatus = new MultiStatus(LibraryPlugin.PLUGIN_ID, IStatus.OK, "Problems occured while loading IDL library.", null);

		final List<URI> pathList = getPathList();
		resourceSet.getLoadOptions().put(IdlResourceImpl.INCLUDE_PATHS, pathList);
		final SubMonitor subMonitor = SubMonitor.convert(monitor, "Loading IDL Library...", pathList.size());
		for (final URI uri : pathList) {
			final IFileStore store;
			try {
				store = EFS.getStore(new java.net.URI(uri.toString()));
			} catch (final CoreException e) {
				libraryStatus.add(new Status(IStatus.ERROR, LibraryPlugin.PLUGIN_ID, "Cannot load file store for URI", e));
				continue;
			} catch (final URISyntaxException e) {
				libraryStatus.add(new Status(IStatus.ERROR, LibraryPlugin.PLUGIN_ID, "Invalid URI", e));
				continue;
			}
			addFileStore(editingDomain, store, subMonitor.newChild(1));
		}

		for (final Resource resource : eResource().getResourceSet().getResources()) {
			if (resource != eResource() && resource instanceof IdlResourceImpl) {
				final MultiStatus resourceStatus = new MultiStatus(LibraryPlugin.PLUGIN_ID, IStatus.OK, "Problems occured while loading IDL file: "
				        + resource.getURI().path(), null);

				for (final Diagnostic diag : resource.getErrors()) {
					Throwable exception = null;
					if (diag instanceof Exception) {
						exception = (Exception) diag;
					}
					final Status error = new Status(IStatus.ERROR, LibraryPlugin.PLUGIN_ID, diag.getMessage(), exception);
					resourceStatus.add(error);
				}

				for (final Diagnostic diag : resource.getWarnings()) {
					Throwable exception = null;
					if (diag instanceof Exception) {
						exception = (Exception) diag;
					}
					final Status warning = new Status(IStatus.WARNING, LibraryPlugin.PLUGIN_ID, diag.getMessage(), exception);
					resourceStatus.add(warning);
				}

				if (!resourceStatus.isOK()) {
					libraryStatus.add(resourceStatus);
				}
			}
		}

		if (libraryStatus.isOK()) {
			editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, this, LibraryPackage.Literals.IDL_LIBRARY__LOAD_STATUS, Status.OK_STATUS));
		} else {
			editingDomain.getCommandStack().execute(SetCommand.create(editingDomain, this, LibraryPackage.Literals.IDL_LIBRARY__LOAD_STATUS, libraryStatus));
		}

		return getLoadStatus();
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
			case LibraryPackage.IDL_LIBRARY__PATHS:
				return ((InternalEList<?>)getPaths()).basicRemove(otherEnd, msgs);
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
			case LibraryPackage.IDL_LIBRARY__SPECIFICATIONS:
				return getSpecifications();
			case LibraryPackage.IDL_LIBRARY__PATHS:
				return getPaths();
			case LibraryPackage.IDL_LIBRARY__LOAD_STATUS:
				return getLoadStatus();
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
			case LibraryPackage.IDL_LIBRARY__SPECIFICATIONS:
				getSpecifications().clear();
				getSpecifications().addAll((Collection<? extends Specification>)newValue);
				return;
			case LibraryPackage.IDL_LIBRARY__PATHS:
				getPaths().clear();
				getPaths().addAll((Collection<? extends Path>)newValue);
				return;
			case LibraryPackage.IDL_LIBRARY__LOAD_STATUS:
				setLoadStatus((IStatus)newValue);
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
			case LibraryPackage.IDL_LIBRARY__SPECIFICATIONS:
				getSpecifications().clear();
				return;
			case LibraryPackage.IDL_LIBRARY__PATHS:
				getPaths().clear();
				return;
			case LibraryPackage.IDL_LIBRARY__LOAD_STATUS:
				setLoadStatus(LOAD_STATUS_EDEFAULT);
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
			case LibraryPackage.IDL_LIBRARY__SPECIFICATIONS:
				return specifications != null && !specifications.isEmpty();
			case LibraryPackage.IDL_LIBRARY__PATHS:
				return paths != null && !paths.isEmpty();
			case LibraryPackage.IDL_LIBRARY__LOAD_STATUS:
				return LOAD_STATUS_EDEFAULT == null ? loadStatus != null : !LOAD_STATUS_EDEFAULT.equals(loadStatus);
		}
		return super.eIsSet(featureID);
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
		result.append(" (loadStatus: ");
		result.append(loadStatus);
		result.append(')');
		return result.toString();
	}
	

} // IdlLibraryImpl

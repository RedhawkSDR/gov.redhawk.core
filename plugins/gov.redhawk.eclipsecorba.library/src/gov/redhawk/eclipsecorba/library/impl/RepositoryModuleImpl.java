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
import gov.redhawk.eclipsecorba.idl.Identifiable;
import gov.redhawk.eclipsecorba.idl.Module;
import gov.redhawk.eclipsecorba.idl.impl.DefinitionImpl;
import gov.redhawk.eclipsecorba.library.LibraryFactory;
import gov.redhawk.eclipsecorba.library.LibraryPackage;
import gov.redhawk.eclipsecorba.library.RepositoryModule;
import gov.redhawk.model.sca.commands.ScaModelCommand;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RunnableWithResult;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Repository Module</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link gov.redhawk.eclipsecorba.library.impl.RepositoryModuleImpl#getModuleDefinitions <em>Module Definitions</em>}</li>
 *   <li>{@link gov.redhawk.eclipsecorba.library.impl.RepositoryModuleImpl#getDefinitions <em>Definitions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RepositoryModuleImpl extends DefinitionImpl implements RepositoryModule {
	/**
	 * The cached value of the '{@link #getModuleDefinitions() <em>Module Definitions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModuleDefinitions()
	 * @generated
	 * @ordered
	 */
	protected EList<Module> moduleDefinitions;

	/**
	 * The cached value of the '{@link #getDefinitions() <em>Definitions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefinitions()
	 * @generated
	 * @ordered
	 */
	protected EList<Definition> definitions;


	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RepositoryModuleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return LibraryPackage.Literals.REPOSITORY_MODULE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Module> getModuleDefinitions() {
		if (moduleDefinitions == null) {
			moduleDefinitions = new EObjectResolvingEList<Module>(Module.class, this, LibraryPackage.REPOSITORY_MODULE__MODULE_DEFINITIONS);
		}
		return moduleDefinitions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EList<Definition> getDefinitions() {
		if (definitions == null) {
			definitions = new EObjectResolvingEList<Definition>(Definition.class, this, LibraryPackage.REPOSITORY_MODULE__DEFINITIONS);
		}
		return definitions;
	}

	private final Adapter listener = new AdapterImpl() {
		
		{
			eAdapters().add(this);
		}
		@SuppressWarnings("unchecked")
        @Override
		public void notifyChanged(final Notification msg) {
			switch (msg.getFeatureID(RepositoryModule.class)) {
			case LibraryPackage.REPOSITORY_MODULE__MODULE_DEFINITIONS:
				switch(msg.getEventType()) {
				case Notification.REMOVE:
				case Notification.REMOVE_MANY:
					getDefinitions().clear();
					for (final Module module : getModuleDefinitions()) {
						addModuleDef(module);
					}
					break;
				case Notification.ADD:
				{
					final Module m = (Module) msg.getNewValue();
					addModuleDef(m);
					break;
				}
				case Notification.ADD_MANY:
				{
					for (final Module m : ((Collection<Module>)msg.getNewValue())) {
						addModuleDef(m);
					}
					break;
				}
				}
				break;
			case LibraryPackage.REPOSITORY_MODULE__DEFINITIONS:
				switch(msg.getEventType()) {
				case Notification.ADD:
				{
					final Definition def = (Definition) msg.getNewValue();
					mapDefinition(def);
					break;
				}
				case Notification.ADD_MANY:
				{
					for (final Definition d : ((Collection<Definition>)msg.getNewValue())) {
						mapDefinition(d);
					}
					break;
				}
				case Notification.REMOVE:
				{
					final Definition def = (Definition) msg.getOldValue();
					unmapDefinition(def);
					break;
				}
				case Notification.REMOVE_MANY:
				{
					for (final Definition d : ((Collection<Definition>)msg.getOldValue())) {
						unmapDefinition(d);
					}
					break;
				}
				}
				break;
			}
		}
	};
	
	private final Map<String,Definition> definitionMap = Collections.synchronizedMap(new HashMap<String, Definition>());


	private void dispose() {
		clear();
		eAdapters().remove(this.listener);
	}
	
	protected void clear() {
		eAdapters().remove(this.listener);
		this.definitionMap.clear();
		getDefinitions().clear();
		getModuleDefinitions().clear();
		eAdapters().add(this.listener);
	}
	
	private void addModuleDef(final Module module) {
		for (final EObject obj : module.eContents()) {
			if (obj instanceof Definition) {
				addDefinition((Definition) obj);
			}
		}
	}

	private void addSubModule(final Module module) {
		RepositoryModule currentDef = (RepositoryModule) find(module.getRepId());
		if (currentDef != null) {
			if (currentDef.getComment() == null && currentDef.getComment() != null) {
				module.setComment(EcoreUtil.copy(module.getComment()));
			}
		} else {
			currentDef = LibraryFactory.eINSTANCE.createRepositoryModule();
			currentDef.setName(module.getName());
			currentDef.setRepId(module.getRepId());
			if (module.getComment() != null) {
				currentDef.setComment(EcoreUtil.copy(module.getComment()));
			}
			
			final Resource resource = getRepoModuleResource();
			resource.getContents().add(currentDef);
			getDefinitions().add(currentDef);
		}
		currentDef.getModuleDefinitions().add(module);
	}
	
	private static final URI REPO_URI = URI.createURI("mem:///repoModule.resource");
	private Resource repoResource;
	private Resource getRepoModuleResource() {
		if (this.repoResource != null) {
			return this.repoResource;
		}
		final ResourceSet resourceSet = eResource().getResourceSet();
		for (final Resource r : resourceSet.getResources()) {
			if (RepositoryModuleImpl.REPO_URI.equals(r.getURI())) {
				this.repoResource = r;
				break;
			}
		}
		if (this.repoResource == null) {
			this.repoResource = resourceSet.createResource(RepositoryModuleImpl.REPO_URI);
		}
		return this.repoResource;
    }

	protected void addDefinition(final Definition def) {
		if (def instanceof Module) {
			addSubModule((Module) def);
		} else {
			if (find(def.getRepId()) == null) {
				getDefinitions().add(def);
			}
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@Override
	public Identifiable find(final String repId) {
		// END GENERATED CODE
		Identifiable retVal = this.definitionMap.get(repId);
		if (retVal == null) {
			Definition[] tmp;
			try {
				tmp = ScaModelCommand.runExclusive(this, new RunnableWithResult.Impl<Definition[]>() {

					@Override
					public void run() {
						setResult(getDefinitions().toArray(new Definition[getDefinitions().size()]));
					}

				});
			} catch (final InterruptedException e) {
				return null;
			}
			if (tmp != null) {
				for (final Definition def : tmp) {
					if (def instanceof RepositoryModule) {
						retVal = ((RepositoryModule) def).find(repId);
						if (retVal != null) {
							break;
						}
					}
				}
			}
		}
		return retVal;
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
			case LibraryPackage.REPOSITORY_MODULE__MODULE_DEFINITIONS:
				return getModuleDefinitions();
			case LibraryPackage.REPOSITORY_MODULE__DEFINITIONS:
				return getDefinitions();
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
			case LibraryPackage.REPOSITORY_MODULE__MODULE_DEFINITIONS:
				getModuleDefinitions().clear();
				getModuleDefinitions().addAll((Collection<? extends Module>)newValue);
				return;
			case LibraryPackage.REPOSITORY_MODULE__DEFINITIONS:
				getDefinitions().clear();
				getDefinitions().addAll((Collection<? extends Definition>)newValue);
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
			case LibraryPackage.REPOSITORY_MODULE__MODULE_DEFINITIONS:
				getModuleDefinitions().clear();
				return;
			case LibraryPackage.REPOSITORY_MODULE__DEFINITIONS:
				getDefinitions().clear();
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
			case LibraryPackage.REPOSITORY_MODULE__MODULE_DEFINITIONS:
				return moduleDefinitions != null && !moduleDefinitions.isEmpty();
			case LibraryPackage.REPOSITORY_MODULE__DEFINITIONS:
				return definitions != null && !definitions.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	protected void unmapDefinition(final Definition def) {
		if (def instanceof RepositoryModuleImpl) {
			((RepositoryModuleImpl)def).dispose();
		}
		this.definitionMap.remove(def.getRepId());
    }

	protected void mapDefinition(final Definition def) {
		this.definitionMap.put(def.getRepId(), def);
    }

} // RepositoryModuleImpl

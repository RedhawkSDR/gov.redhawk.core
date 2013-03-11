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
package gov.redhawk.model.sca.provider;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

/**
 * @since 10.0
 * 
 */
public class TransientItemProvider extends ItemProviderAdapter implements IEditingDomainItemProvider, IStructuredItemContentProvider,
        ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource {
	public TransientItemProvider(AdapterFactory adapterFactory, EObject object) {
		super(adapterFactory);
		object.eAdapters().add(this);
	}
	
	@Override
	public boolean hasChildren(Object object) {
	    return super.hasChildren(target);
	}
	
	@Override
	public Collection< ? > getNewChildDescriptors(Object object, EditingDomain editingDomain, Object sibling) {
	    return super.getNewChildDescriptors(target, editingDomain, sibling);
	}
	
	@Override
	protected ResourceLocator getResourceLocator() {
	    return ScaEditPlugin.INSTANCE;
	}
	
	@Override
	public Command createCommand(Object object, EditingDomain domain, Class< ? extends Command> commandClass, CommandParameter commandParameter) {
		commandParameter.setOwner(target);
	    return super.createCommand(target, domain, commandClass, commandParameter);
	}
	
	@Override
	protected Command createRemoveCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Collection< ? > collection) {
		return createWrappedCommand(super.createRemoveCommand(domain, owner, feature, collection), owner);
	}
	
	@Override
	protected Command createAddCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Collection< ? > collection, int index) {    // TODO Auto-generated method stub
	    return createWrappedCommand(super.createAddCommand(domain, owner, feature, collection, index), owner);
	}
	
	protected Command createWrappedCommand(Command command , final EObject owner) {
		return new CommandWrapper(command) {
			@Override
			public Collection< ? > getAffectedObjects() {
			    Collection<?> affected = super.getAffectedObjects();
			    if (affected.contains(owner)) {
			    	affected = Collections.singleton(TransientItemProvider.this);
			    }
			    return affected;
			}
		};
	}
	
	@Override
	public Collection< ? > getChildren(Object object) {
	    return super.getChildren(target);
	}
	
	@Override
	public Collection< ? > getElements(Object object) {
	    return super.getElements(target);
	}
	
	
	@Override
	public Object getParent(Object object) {
	    return target;
	}

	
	
}

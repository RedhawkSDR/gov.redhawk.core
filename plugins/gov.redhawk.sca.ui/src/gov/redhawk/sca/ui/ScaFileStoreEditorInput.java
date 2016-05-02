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
package gov.redhawk.sca.ui;

import gov.redhawk.model.sca.CorbaObjWrapper;
import gov.redhawk.model.sca.ScaDomainManager;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.ide.FileStoreEditorInput;

public class ScaFileStoreEditorInput extends FileStoreEditorInput {

	private final CorbaObjWrapper< ? > scaObject;

	/**
	 * @since 8.0
	 */
	public ScaFileStoreEditorInput(final CorbaObjWrapper< ? > scaElement, final IFileStore profileStore) {
		super(profileStore);
		this.scaObject = scaElement;
	}

	/**
	 * @since 8.0
	 */
	public CorbaObjWrapper< ? > getScaObject() {
		return this.scaObject;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	@Override
	public String getName() {
		final ReflectiveItemProviderAdapterFactory adapterFactory = new ReflectiveItemProviderAdapterFactory();
		try {
			final IItemLabelProvider lp = (IItemLabelProvider) adapterFactory.adapt(this.scaObject, IItemLabelProvider.class);
			if (lp != null) {
				return lp.getText(this.scaObject);
			}
		} finally {
			adapterFactory.dispose();
		}
		return super.getName();
	}

	@Override
	public String getToolTipText() {
		ScaDomainManager domMgr = ScaEcoreUtils.getEContainerOfType(this.scaObject, ScaDomainManager.class);
		if (domMgr != null) {
			return domMgr.getLabel() + " - " + getURI().getPath();
		}
		return getURI().getPath();
	}
	
	@Override
	public boolean equals(Object o) {

		if (o == this) {
			return true;
		}

		if (o instanceof ScaFileStoreEditorInput) {
			ScaFileStoreEditorInput input = (ScaFileStoreEditorInput) o;
			return scaObject.equals(input.scaObject);
		}

		return false;
	}

	@Override
	public String getFactoryId() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IPersistable#saveState(org.eclipse.ui.IMemento)
	 */
	@Override
	public void saveState(final IMemento memento) {
		throw new UnsupportedOperationException();
	}
}

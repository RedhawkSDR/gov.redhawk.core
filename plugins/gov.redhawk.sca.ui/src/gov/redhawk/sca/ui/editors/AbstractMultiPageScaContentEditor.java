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
package gov.redhawk.sca.ui.editors;

import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * @since 6.0
 */
public abstract class AbstractMultiPageScaContentEditor< T extends EObject > extends AbstractMultiPageScaEditor {

	private AdapterFactory adapterFactory;
	private final Adapter disposeListener = new AdapterImpl() {
		@Override
		public void notifyChanged(final org.eclipse.emf.common.notify.Notification msg) {
			switch (msg.getFeatureID(gov.redhawk.model.sca.IDisposable.class)) {
			case ScaPackage.IDISPOSABLE__DISPOSED:
				if (msg.getNewBooleanValue()) {
					PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
						@Override
						public void run() {
							getSite().getPage().closeEditor(AbstractMultiPageScaContentEditor.this, true);
						}
					});
				}
				break;
			default:
				break;
			}
		}
	};

	@Override
	public EObjectEditorInput getEditorInput() {
		return (EObjectEditorInput) super.getEditorInput();
	}

	@Override
	public void init(final IEditorSite site, final IEditorInput input) throws PartInitException {
		if (!(input instanceof EObjectEditorInput)) {
			throw new IllegalArgumentException("Editor input must be an instanceof of EObjectEditorInput");
		}
		super.init(site, input);
		final EObject obj = getInput();
		if (obj instanceof gov.redhawk.model.sca.IDisposable) {
			obj.eAdapters().add(this.disposeListener);
		}

	}

	@Override
	public <T2> T2 getAdapter(Class<T2> adapter) {
		if (adapter == EObject.class) {
			return adapter.cast(getInput());
		}
		return super.getAdapter(adapter);
	}

	/**
	 * Helper method to return the input object
	 * @return The input object
	 */
	@SuppressWarnings("unchecked")
	public T getInput() {
		return (T) getEditorInput().getInput();
	}

	/**
	 * A default adapter factory for REDHAWK Model Objects
	 * @return	default adapter factory for REDHAWK model objects
	 */
	public AdapterFactory getAdapterFactory() {
		if (this.adapterFactory == null) {
			this.adapterFactory = createAdapterFactory();
		}
		return this.adapterFactory;
	}

	/**
	 * Subclasses should override this method to return a type to check the input against.
	 * 
	 * @return Input type, default is null which performs no checks on the editor input.
	 * @since 7.0
	 */
	protected Class<T> getInputType() {
		return null;
	}

	/**
	 * Create a default adapter factory
	 */
	protected AdapterFactory createAdapterFactory() {
		final ComposedAdapterFactory retVal = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		retVal.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		retVal.addAdapterFactory(new ScaItemProviderAdapterFactory());
		retVal.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

		return retVal;
	}

	@Override
	public void dispose() {
		super.dispose();
		getInput().eAdapters().remove(this.disposeListener);
		if (this.adapterFactory instanceof IDisposable) {
			((IDisposable) this.adapterFactory).dispose();
			this.adapterFactory = null;
		}
	}

}

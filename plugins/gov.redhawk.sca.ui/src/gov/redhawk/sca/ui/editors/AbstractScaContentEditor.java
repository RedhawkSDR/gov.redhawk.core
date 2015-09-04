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

/**
 * @since 2.2
 * 
 */
public abstract class AbstractScaContentEditor< T extends EObject > extends AbstractScaEditor {

	private AdapterFactory adapterFactory;
	private final Adapter disposeListener = new AdapterImpl() {
		@Override
		public void notifyChanged(final org.eclipse.emf.common.notify.Notification msg) {
			switch (msg.getFeatureID(gov.redhawk.model.sca.IDisposable.class)) {
			case ScaPackage.IDISPOSABLE__DISPOSED:
				if (msg.getNewBooleanValue()) {
					//For RAP compatibility, do not get Display from static call to PlatformUI
					getSite().getWorkbenchWindow().getShell().getDisplay().asyncExec(new Runnable() {
						@Override
						public void run() {
							getSite().getPage().closeEditor(AbstractScaContentEditor.this, true);
						}
					});
				}
				break;
			default:
				break;
			}
		}
	};

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
	 * {@inheritDoc}
	 */
	@Override
	public IEditorInput getEditorInput() {
		return super.getEditorInput();
	}

	@Override
	public void init(final IEditorSite site, final IEditorInput input) throws PartInitException {
		if (input instanceof EObjectEditorInput) {
			final Class<T> inputType = getInputType();
			final EObject eInput = ((EObjectEditorInput) input).getInput();
			if (inputType != null && eInput != null && !inputType.isInstance(eInput)) {
				throw new IllegalArgumentException("Editor input must be of type " + inputType + " but found " + eInput.getClass());
			}
			super.init(site, input);
			final EObject obj = getInput();
			if (obj instanceof gov.redhawk.model.sca.IDisposable) {
				obj.eAdapters().add(this.disposeListener);
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") final Class adapter) {
		if (adapter == EObject.class) {
			return getInput();
		}
		return super.getAdapter(adapter);
	}

	/**
	 * Helper method to return the input object
	 * @return input object
	 */
	@SuppressWarnings("unchecked")
	public T getInput() {
		if (getEditorInput() instanceof EObjectEditorInput) {
			return (T) ((EObjectEditorInput) getEditorInput()).getInput();
		} else {
			return null;
		}
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
	 * Create a default adapter factory
	 */
	protected AdapterFactory createAdapterFactory() {
		final ComposedAdapterFactory retVal = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		retVal.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		retVal.addAdapterFactory(new ScaItemProviderAdapterFactory());
		retVal.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

		return retVal;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void dispose() {
		super.dispose();
		if (getInput() != null) {
			getInput().eAdapters().remove(this.disposeListener);
		}
		if (this.adapterFactory instanceof IDisposable) {
			((IDisposable) this.adapterFactory).dispose();
			this.adapterFactory = null;
		}
	}

}

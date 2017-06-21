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
package gov.redhawk.prf.internal.ui.editor;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.ui.PlatformUI;

import gov.redhawk.prf.ui.editor.page.PropertiesFormPage;
import gov.redhawk.ui.editor.FormOutlinePage;
import gov.redhawk.ui.editor.SCAFormEditor;
import mil.jpeojtrs.sca.prf.Properties;
import mil.jpeojtrs.sca.prf.Struct;
import mil.jpeojtrs.sca.prf.StructSequence;

/**
 * Provides the outline for the PRF editor.
 */
public class PropertiesOutlinePage extends FormOutlinePage {

	public PropertiesOutlinePage(final SCAFormEditor editor) {
		super(editor);

		// Create our own label provider that will decorate items that have warnings/errors.
		// We re-use the editor's adapter factory so that objects are the same between the editor & outline
		ILabelProvider provider = new AdapterFactoryLabelProvider(fEditor.getAdapterFactory());
		ILabelDecorator decorator = PlatformUI.getWorkbench().getDecoratorManager().getLabelDecorator();
		super.setLabelProvider(new DecoratingLabelProvider(provider, decorator));
	}

	@Override
	protected void addItemProviders(final ComposedAdapterFactory adapterFactory) {
		// We override getAdapterFactory(), so this isn't necessary
		throw new IllegalStateException("Internal error - this method should never be called");
	}

	@Override
	public AdapterFactory getAdapterFactory() {
		// We re-use the editor's adapter factory
		return fEditor.getAdapterFactory();
	}

	@Override
	protected boolean getChildren(final Object parent) {
		return (parent instanceof Properties || parent instanceof Struct || parent instanceof StructSequence);
	}

	@Override
	protected String getParentPageId(final Object item) {
		// Only one page in the editor
		return PropertiesFormPage.PAGE_ID;
	}
}

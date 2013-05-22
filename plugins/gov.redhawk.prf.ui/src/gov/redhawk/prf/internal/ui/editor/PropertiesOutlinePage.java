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

import gov.redhawk.prf.ui.editor.page.PropertiesFormPage;
import gov.redhawk.prf.ui.provider.PropertiesEditorPrfItemProviderAdapterFactory;
import gov.redhawk.ui.editor.FormOutlinePage;
import gov.redhawk.ui.editor.SCAFormEditor;
import mil.jpeojtrs.sca.prf.Properties;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.Struct;
import mil.jpeojtrs.sca.prf.StructSequence;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.DecoratingLabelProvider;
import org.eclipse.ui.PlatformUI;

/**
 * The Class PropertiesOutlinePage.
 */
public class PropertiesOutlinePage extends FormOutlinePage {

	private ComposedAdapterFactory adapterFactory;

	/**
	 * @since 2.0
	 */
	public class PropertiesOutlinePageAdapterFactoryLabelProvider extends AdapterFactoryLabelProvider {

		public PropertiesOutlinePageAdapterFactoryLabelProvider(final AdapterFactory adapterFactory) {
			super(adapterFactory);
		}

		@Override
		public String getText(final Object object) {
			if (object instanceof Properties) {
				return "Properties";
			}
			return super.getText(object);
		}

	}

	/**
	 * The Constructor.
	 * 
	 * @param editor the editor
	 */
	public PropertiesOutlinePage(final SCAFormEditor editor) {
		super(editor);
		super.setLabelProvider(new DecoratingLabelProvider(new PropertiesOutlinePageAdapterFactoryLabelProvider(getAdapterFactory()), PlatformUI.getWorkbench()
		        .getDecoratorManager().getLabelDecorator()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void addItemProviders(final ComposedAdapterFactory adapterFactory) {
		adapterFactory.addAdapterFactory(new PropertiesEditorPrfItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new EcoreItemProviderAdapterFactory());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean getChildren(final Object parent) {
		boolean retVal = false;
		if (parent instanceof Properties || parent instanceof Struct || parent instanceof StructSequence) {
			retVal = true;
		}
		return retVal;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected String getParentPageId(final Object item) {
		String pageId = null;
		if (item instanceof Simple || item instanceof SimpleSequence || item instanceof Struct || item instanceof StructSequence) {
			pageId = PropertiesFormPage.PAGE_ID;
		}
		if (pageId != null) {
			return pageId;
		}
		return super.getParentPageId(item);
	}

}

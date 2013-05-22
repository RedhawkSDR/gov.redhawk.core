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

import gov.redhawk.prf.internal.ui.editor.detailspart.SimplePropertyDetailsPage;
import gov.redhawk.prf.internal.ui.editor.detailspart.SimpleRefDetailsPage;
import gov.redhawk.prf.internal.ui.editor.detailspart.SimpleSequencePropertyDetailsPage;
import gov.redhawk.prf.internal.ui.editor.detailspart.StructPropertyDetailsPage;
import gov.redhawk.prf.internal.ui.editor.detailspart.StructSequencePropertyDetailsPage;
import gov.redhawk.prf.ui.editor.page.PropertiesFormPage;
import gov.redhawk.ui.editor.SCAMasterDetailsBlock;
import gov.redhawk.ui.editor.ScaSection;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleRef;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.Struct;
import mil.jpeojtrs.sca.prf.StructSequence;
import mil.jpeojtrs.sca.prf.StructValue;

import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IDetailsPageProvider;
import org.eclipse.ui.forms.IManagedForm;

/**
 * The Class ScrolledPropertiesBlock.
 */
public class PropertiesBlock extends SCAMasterDetailsBlock {

	private static final int PAGE_LIMIT = 10;
	private PropertiesSection fSection;

	/**
	 * Instantiates a new scrolled properties block.
	 * 
	 * @param page the page
	 */
	public PropertiesBlock(final PropertiesFormPage page) {
		super(page);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PropertiesFormPage getPage() {
		return (PropertiesFormPage) super.getPage();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ScaSection createMasterSection(final IManagedForm managedForm, final Composite parent) {
		this.fSection = new PropertiesSection(getPage(), parent);
		return this.fSection;
	}

	/**
	 * @return the Section
	 */
	public PropertiesSection getSection() {
		return this.fSection;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void registerPages(final DetailsPart detailsPart) {
		detailsPart.setPageLimit(PropertiesBlock.PAGE_LIMIT);
		detailsPart.registerPage(Simple.class, new SimplePropertyDetailsPage(this.fSection));
		detailsPart.registerPage(SimpleSequence.class, new SimpleSequencePropertyDetailsPage(this.fSection));
		detailsPart.registerPage(Struct.class, new StructPropertyDetailsPage(this.fSection));
		detailsPart.registerPage(StructSequence.class, new StructSequencePropertyDetailsPage(this.fSection));
		detailsPart.registerPage(SimpleRef.class, new SimpleRefDetailsPage(this.fSection));
		detailsPart.setPageProvider(new IDetailsPageProvider() {

			public Object getPageKey(Object object) {
				object = AdapterFactoryEditingDomain.unwrap(object);
				if (object instanceof Simple) {
					return Simple.class;
				} else if (object instanceof SimpleSequence) {
					return SimpleSequence.class;
				} else if (object instanceof Struct) {
					return Struct.class;
				} else if (object instanceof StructSequence) {
					return StructSequence.class;
				} else if (object instanceof StructValue) {
					return StructValue.class;
				} else if (object instanceof SimpleRef) {
					return SimpleRef.class;
				}
				return null;
			}

			public IDetailsPage getPage(final Object key) {
				return null;
			}
		});
	}
}

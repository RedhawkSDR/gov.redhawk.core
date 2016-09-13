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
import gov.redhawk.prf.internal.ui.editor.detailspart.SimpleSequencePropertyDetailsPage;
import gov.redhawk.prf.internal.ui.editor.detailspart.StructPropertyDetailsPage;
import gov.redhawk.prf.internal.ui.editor.detailspart.StructSequencePropertyDetailsPage;
import gov.redhawk.prf.internal.ui.editor.detailspart.StructSequenceSimplePropertyDetailsPage;
import gov.redhawk.prf.internal.ui.editor.detailspart.StructSequenceStructPropertyDetailsPage;
import gov.redhawk.prf.internal.ui.editor.detailspart.StructSimplePropertyDetailsPage;
import gov.redhawk.prf.internal.ui.editor.detailspart.StructSimpleSequencePropertyDetailsPage;
import gov.redhawk.prf.ui.editor.page.PropertiesFormPage;
import gov.redhawk.ui.editor.SCAMasterDetailsBlock;
import gov.redhawk.ui.editor.ScaSection;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.Struct;
import mil.jpeojtrs.sca.prf.StructSequence;

import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.DetailsPart;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IDetailsPageProvider;
import org.eclipse.ui.forms.IManagedForm;

public class PropertiesBlock extends SCAMasterDetailsBlock {
	private PropertiesSection fSection;

	public PropertiesBlock(final PropertiesFormPage page) {
		super(page);
	}

	@Override
	public PropertiesFormPage getPage() {
		return (PropertiesFormPage) super.getPage();
	}

	@Override
	protected ScaSection createMasterSection(final IManagedForm managedForm, final Composite parent) {
		this.fSection = new PropertiesSection(this, parent);
		return this.fSection;
	}

	public PropertiesSection getSection() {
		return this.fSection;
	}

	@Override
	protected void registerPages(final DetailsPart detailsPart) {
		detailsPart.setPageProvider(new IDetailsPageProvider() {

			@Override
			public Object getPageKey(Object object) {
				object = AdapterFactoryEditingDomain.unwrap(object);
				if (object instanceof Simple) {
					Simple s = (Simple) object;
					if (s.eContainer() instanceof Struct) {
						Struct struct = (Struct) s.eContainer();
						if (struct.eContainer() instanceof StructSequence) {
							return StructSequenceSimplePropertyDetailsPage.class;
						} else {
							return StructSimplePropertyDetailsPage.class;
						}
					} else {
						return SimplePropertyDetailsPage.class;
					}
				} else if (object instanceof SimpleSequence) {
					SimpleSequence ss = (SimpleSequence) object;
					if (ss.eContainer() instanceof Struct) {
						return StructSimpleSequencePropertyDetailsPage.class;
					} else {
						return SimpleSequencePropertyDetailsPage.class;
					}
				} else if (object instanceof Struct) {
					Struct struct = (Struct) object;
					if (struct.eContainer() instanceof StructSequence) {
						return StructSequenceStructPropertyDetailsPage.class;
					} else {
						return StructPropertyDetailsPage.class;
					}
				} else if (object instanceof StructSequence) {
					return StructSequencePropertyDetailsPage.class;
				}
				return null;
			}

			@Override
			public IDetailsPage getPage(final Object key) {
				if (key == StructSequencePropertyDetailsPage.class) {
					return new StructSequencePropertyDetailsPage(fSection);
				}
				if (key == StructSequenceStructPropertyDetailsPage.class) {
					return new StructSequenceStructPropertyDetailsPage(fSection);
				}
				if (key == StructSequenceSimplePropertyDetailsPage.class) {
					return new StructSequenceSimplePropertyDetailsPage(fSection);
				}
				if (key == StructPropertyDetailsPage.class) {
					return new StructPropertyDetailsPage(fSection);
				}
				if (key == StructSimplePropertyDetailsPage.class) {
					return new StructSimplePropertyDetailsPage(fSection);
				}
				if (key == StructSimpleSequencePropertyDetailsPage.class) {
					return new StructSimpleSequencePropertyDetailsPage(fSection);
				}
				if (key == SimplePropertyDetailsPage.class) {
					return new SimplePropertyDetailsPage(fSection);
				}
				if (key == SimpleSequencePropertyDetailsPage.class) {
					return new SimpleSequencePropertyDetailsPage(fSection);
				}
				return null;
			}
		});
	}
}

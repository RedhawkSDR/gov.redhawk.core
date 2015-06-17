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
package gov.redhawk.ui.editor;

import gov.redhawk.common.ui.editor.FormLayoutFactory;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

public abstract class ScaSection extends SectionPart implements IContextPart, IAdaptable {
	private final ScaFormPage fPage;

	public ScaSection(final ScaFormPage page, final Composite parent, final int style) {
		this(page, parent, style, true);
	}

	public ScaSection(final ScaFormPage page, final Composite parent, final int style, final boolean titleBar) {
		super(parent, page.getManagedForm().getToolkit(), (titleBar) ? (ExpandableComposite.TITLE_BAR | style) : style); // SUPPRESS CHECKSTYLE AvoidInLine
		fPage = page;
		initialize(page.getManagedForm());
		getSection().clientVerticalSpacing = FormLayoutFactory.SECTION_HEADER_VERTICAL_SPACING;
		getSection().setData("part", this); //$NON-NLS-1$
	}

	protected abstract void createClient(Section section, FormToolkit toolkit);

	public void refresh(final Resource resource) {
		// Default does nothing clients should override
	}

	@Override
	public ScaFormPage getPage() {
		return this.fPage;
	}
	
	/**
	 * @since 8.0
	 */
	public Object getAdapter(Class adapter) {
		return null;
	}
}

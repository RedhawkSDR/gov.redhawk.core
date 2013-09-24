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

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.SectionPart;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

// TODO: Auto-generated Javadoc
/**
 * The Class ScaSection.
 */
public abstract class ScaSection extends SectionPart implements IContextPart {
	private final ScaFormPage fPage;

	/**
	 * Instantiates a new sca section.
	 * 
	 * @param page the page
	 * @param parent the parent
	 * @param style the style
	 */
	public ScaSection(final ScaFormPage page, final Composite parent, final int style) {
		this(page, parent, style, true);
	}

	/**
	 * Instantiates a new sca section.
	 * 
	 * @param page the page
	 * @param parent the parent
	 * @param style the style
	 * @param titleBar the title bar
	 */
	public ScaSection(final ScaFormPage page, final Composite parent, final int style, final boolean titleBar) {
		super(parent, page.getManagedForm().getToolkit(), (titleBar) ? (ExpandableComposite.TITLE_BAR | style) : style); // SUPPRESS CHECKSTYLE AvoidInLine
		this.fPage = page;
		initialize(page.getManagedForm());
		getSection().clientVerticalSpacing = FormLayoutFactory.SECTION_HEADER_VERTICAL_SPACING;
	}

	/**
	 * Creates the client.
	 * 
	 * @param section the section
	 * @param toolkit the toolkit
	 */
	protected abstract void createClient(Section section, FormToolkit toolkit);

	/**
	 * @param resource
	 */
	public void refresh(final Resource resource) {
		// Default does nothing clients should override
	}

	/**
	 * Gets the page.
	 * 
	 * @return the page
	 */
	@Override
	public ScaFormPage getPage() {
		return this.fPage;
	}
}

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

import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.events.IHyperlinkListener;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormText;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;

// TODO: Auto-generated Javadoc
/**
 * The Class LaunchShortcutOverviewPage.
 */
public abstract class AbstractOverviewPage extends ScaFormPage implements IHyperlinkListener {

	/**
	 * Instantiates a new launch shortcut overview page.
	 * 
	 * @param editor the editor
	 * @param id the id
	 * @param title the title
	 */
	public AbstractOverviewPage(final SCAFormEditor editor, final String id, final String title) {
		super(editor, id, title);
	}

	/**
	 * Creates the static section.
	 * 
	 * @param toolkit the toolkit
	 * @param parent the parent
	 * @param text the text
	 * @return the section
	 */
	protected final Section createStaticSection(final FormToolkit toolkit, final Composite parent, final String text) {
		final Section section = toolkit.createSection(parent, ExpandableComposite.TITLE_BAR);
		section.clientVerticalSpacing = FormLayoutFactory.SECTION_HEADER_VERTICAL_SPACING;
		section.setText(text);
		section.setLayout(FormLayoutFactory.createClearTableWrapLayout(false, 1));
		final TableWrapData data = new TableWrapData(TableWrapData.FILL_GRAB);
		section.setLayoutData(data);
		return section;
	}

	/**
	 * Creates the client.
	 * 
	 * @param section the section
	 * @param content the content
	 * @param toolkit the toolkit
	 * @return the form text
	 */
	public final FormText createClient(final Composite section, final String content, final FormToolkit toolkit) {
		final FormText text = toolkit.createFormText(section, true);
		try {
			text.setText(content, true, false);
		} catch (final SWTException e) {
			text.setText(e.getMessage(), false, false);
		}
		text.addHyperlinkListener(this);
		return text;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void linkEntered(final HyperlinkEvent e) {
		final IStatusLineManager mng = getEditor().getEditorSite().getActionBars().getStatusLineManager();
		mng.setMessage(e.getLabel());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void linkExited(final HyperlinkEvent e) {
		final IStatusLineManager mng = getEditor().getEditorSite().getActionBars().getStatusLineManager();
		mng.setMessage(null);
	}
}

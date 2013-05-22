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

import java.util.List;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.AbstractFormPart;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IFormPart;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * The Class ScaDetails.
 */
public abstract class ScaDetails extends AbstractFormPart implements IDetailsPage, IContextPart {

	private Composite mainDetailComposite;
	private final ScaFormPage parentPage;
	private List<Binding> bindings;
	private EObject selection;
	private FormToolkit toolkit;

	public ScaDetails(final ScaFormPage parentPage) {
		this.parentPage = parentPage;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public final void createContents(final Composite parent) {

		GridLayoutFactory.fillDefaults().applyTo(parent);

		this.mainDetailComposite = new Composite(parent, SWT.NONE);
		this.mainDetailComposite.setLayout(FormLayoutFactory.createDetailsGridLayout(false, 1));
		// GridLayoutFactory.fillDefaults().numColumns(1).applyTo(this._mainDetailComposite);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(this.mainDetailComposite);

		createSpecificContent(this.mainDetailComposite);

		getToolkit().adapt(this.mainDetailComposite);
	}

	protected abstract void createSpecificContent(Composite parent);

	@Override
	public final void commit(final boolean onSave) {
		// nothing
	}

	@Override
	public void dispose() {
		// nothing
	}

	@Override
	public final boolean isDirty() {
		return getPage().getCommandStack().isSaveNeeded();
	}

	@Override
	public final boolean isStale() {
		// Leave for Editor
		return false;
	}

	@Override
	public final void refresh() {
		// DO Nothing
	}

	@Override
	public final void setFocus() {
		this.mainDetailComposite.setFocus();
	}

	@Override
	public final boolean setFormInput(final Object input) {
		return false;
	}

	public final void selectionChanged(final IFormPart part, final ISelection selection) {
		final IStructuredSelection sel = (IStructuredSelection) selection;
		if (!sel.isEmpty()) {
			final EObject eObj = (EObject) AdapterFactoryEditingDomain.unwrap(sel.getFirstElement());
			if (this.selection != eObj) {
				this.selection = eObj;
				if (this.bindings != null) {
					for (final Binding binding : this.bindings) {
						binding.dispose();
					}
					this.bindings = null;
				}

				if (this.selection != null) {
					this.bindings = bind(getEditor().getDataBindingContext(), this.selection);
				}
				this.validate();
			}
		}
	}

	/**
	 * @param dataBindingContext
	 * @param unwrap
	 */
	protected abstract List<Binding> bind(DataBindingContext dataBindingContext, EObject input);

	/**
	 * @since 4.0
	 */
	protected final EditingDomain getEditingDomain() {
		return getEditor().getEditingDomain();
	}

	/**
	 * @since 4.0
	 */
	protected AdapterFactory getAdapterFactory() {
		return getEditor().getAdapterFactory();
	}

	protected final FormToolkit getToolkit() {
		if (this.toolkit == null) {
			if (getEditor() != null) {
				this.toolkit = getEditor().getToolkit();
			} else {
				this.toolkit = new FormToolkit(Display.getCurrent());
			}
		}
		return this.toolkit;
	}

	public ScaFormPage getPage() {
		return this.parentPage;
	}

	public final SCAFormEditor getEditor() {
		if (this.parentPage != null) {
			return this.parentPage.getEditor();
		} else {
			return null;
		}
	}

	protected void execute(final Command command) {
		getEditingDomain().getCommandStack().execute(command);
	}

	protected void validate() {
		getEditor().validate();
	}
}

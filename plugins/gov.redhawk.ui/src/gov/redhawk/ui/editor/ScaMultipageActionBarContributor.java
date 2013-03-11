/**
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.ui.editor;

import gov.redhawk.internal.ui.SubActionBarsExt;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.IActionBars2;
import org.eclipse.ui.IEditorActionBarContributor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.part.MultiPageEditorActionBarContributor;

/**
 * A special implementation of a
 * <code>MultiPageEditorActionBarContributor</code> to switch between action bar
 * contributions for tree-based editor pages and diagram editor pages.
 * 
 * @see MultiPageEditorActionBarContributor
 */
public abstract class ScaMultipageActionBarContributor extends MultiPageEditorActionBarContributor implements IMenuListener {

	private final Map<IEditorPart, SubActionBarsExt> actionBarMap = new HashMap<IEditorPart, SubActionBarsExt>();

	private SubActionBarsExt myActiveEditorActionBars;

	private IEditorPart myActiveEditor;

	private final IPropertyListener myEditorPropertyChangeListener = new IPropertyListener() {

		public void propertyChanged(final Object source, final int propId) {
			if (ScaMultipageActionBarContributor.this.myActiveEditorActionBars != null) {
				if (ScaMultipageActionBarContributor.this.myActiveEditorActionBars.getContributor() instanceof EditingDomainActionBarContributor
				        && ScaMultipageActionBarContributor.this.myActiveEditor.getEditorSite() != null) {
					ScaMultipageActionBarContributor.this.myActiveEditorActionBars.getContributor()
					        .setActiveEditor(ScaMultipageActionBarContributor.this.myActiveEditor);
					((EditingDomainActionBarContributor) ScaMultipageActionBarContributor.this.myActiveEditorActionBars.getContributor()).update();
				}
			}
		}
	};

	public IActionBars2 getActionBarContributor(final IEditorPart part) {
		return this.actionBarMap.get(part);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.part.MultiPageEditorActionBarContributor#setActiveEditor
	 * (org.eclipse.ui.IEditorPart)
	 */
	@Override
	public void setActiveEditor(final IEditorPart part) {
		if (this.myActiveEditor != null) {
			this.myActiveEditor.removePropertyListener(this.myEditorPropertyChangeListener);
		}
		this.myActiveEditor = part;
		super.setActiveEditor(part);
		if (this.myActiveEditor instanceof IEditingDomainProvider) {
			this.myActiveEditor.addPropertyListener(this.myEditorPropertyChangeListener);
		}
	}

	/**
	 * @since 2.3
	 */
	public IEditorPart getActiveEditor() {
		return this.myActiveEditor;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IActionBars2 getActionBars() {
		return (IActionBars2) super.getActionBars();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.part.MultiPageEditorActionBarContributor#setActivePage
	 * (org.eclipse.ui.IEditorPart)
	 */
	@Override
	public void setActivePage(IEditorPart activeEditor) {
		if (activeEditor == null) {
			activeEditor = this.myActiveEditor;
		}
		SubActionBarsExt actionBars = this.actionBarMap.get(activeEditor);
		if (actionBars == null) {
			final IEditorActionBarContributor subBars = getSubActionBarContributor(activeEditor);
			if (subBars != null) {
				actionBars = new SubActionBarsExt(getPage(), getActionBars(), subBars, getType(activeEditor));
				this.actionBarMap.put(activeEditor, actionBars);
			}
		}
		setActiveActionBars(actionBars, activeEditor);
	}

	/**
	 * @param activeEditor
	 * @return
	 */
	protected abstract String getType(IEditorPart activeEditor);

	/**
	 * @param activeEditor
	 * @return
	 */
	protected IEditorActionBarContributor getSubActionBarContributor(final IEditorPart activeEditor) {
		if (activeEditor == null) {
			return null;
		}
		return (IEditorActionBarContributor) activeEditor.getAdapter(IEditorActionBarContributor.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.EditorActionBarContributor#dispose()
	 */
	@Override
	public void dispose() {
		super.dispose();
		for (final SubActionBarsExt ext : this.actionBarMap.values()) {
			ext.dispose();
		}
		this.actionBarMap.clear();
	}

	/**
	 * Switches the active action bars.
	 */
	private void setActiveActionBars(final SubActionBarsExt actionBars, final IEditorPart activeEditor) {
		if (this.myActiveEditorActionBars != null && this.myActiveEditorActionBars != actionBars) {
			this.myActiveEditorActionBars.deactivate();
			getActionBars().clearGlobalActionHandlers();
			//			this.myActiveEditorActionBars.clearGlobalActionHandlers();
		}
		this.myActiveEditorActionBars = actionBars;
		if (this.myActiveEditorActionBars != null) {
			this.myActiveEditorActionBars.setEditorPart(activeEditor);
			this.myActiveEditorActionBars.activate();
			this.myActiveEditorActionBars.updateActionBars();

			// Set new actions.
			final Map< ? , ? > newActionHandlers = this.myActiveEditorActionBars.getGlobalActionHandlers();
			if (newActionHandlers != null) {
				final Set< ? > keys = newActionHandlers.entrySet();
				final Iterator< ? > iter = keys.iterator();
				while (iter.hasNext()) {
					final Map.Entry< ? , ? > entry = (Map.Entry< ? , ? >) iter.next();
					getActionBars().setGlobalActionHandler((String) entry.getKey(), (IAction) entry.getValue());
				}
			}
			getActionBars().updateActionBars();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public void menuAboutToShow(final IMenuManager manager) {
		final SubActionBarsExt actionBars = this.actionBarMap.get(this.myActiveEditor);
		if (actionBars != null && actionBars.getContributor() instanceof IMenuListener) {
			((IMenuListener) actionBars.getContributor()).menuAboutToShow(manager);
		}
	}

}

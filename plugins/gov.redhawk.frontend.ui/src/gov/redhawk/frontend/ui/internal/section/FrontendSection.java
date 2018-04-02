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
package gov.redhawk.frontend.ui.internal.section;

import gov.redhawk.frontend.ListenerAllocation;
import gov.redhawk.frontend.TunerContainer;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.UnallocatedTunerContainer;
import gov.redhawk.frontend.ui.internal.AllocateAction;
import gov.redhawk.frontend.ui.internal.DeallocateAction;
import gov.redhawk.frontend.ui.internal.FeiPlotAction;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

public class FrontendSection extends AbstractPropertySection {

	private TunerStatusProperitesViewer viewer;

	private EObject theInput;
	private IAction deallocateAction;
	private IAction allocateAction;
	private IAction plotAction;
	private IWorkbenchPart inputPart;

	/**
	 * The Property Sheet Page.
	 */
	private TabbedPropertySheetPage page;

	public FrontendSection() {
	}

	@Override
	public void createControls(Composite parent, final TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		TabbedPropertySheetWidgetFactory factory = aTabbedPropertySheetPage.getWidgetFactory();
		viewer = new TunerStatusProperitesViewer(parent, SWT.None, factory.getOrientation() | factory.getBorderStyle() | SWT.H_SCROLL | SWT.V_SCROLL
			| SWT.FULL_SELECTION | SWT.SINGLE);
		factory.adapt(viewer);
		factory.adapt(viewer.getViewer().getControl(), false, false);

		allocateAction = new AllocateAction(this);
		deallocateAction = new DeallocateAction(this);
		plotAction = new FeiPlotAction(this);
		page = aTabbedPropertySheetPage;
	}

	@Override
	public boolean shouldUseExtraSpace() {
		return true;
	}

	private IToolBarManager getToolbar() {
		if (page != null && page.getSite() != null && page.getSite().getActionBars() != null) {
			return page.getSite().getActionBars().getToolBarManager();
		} else {
			return null;
		}
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		inputPart = part;
		if (page == null) {
			return;
		}
		if (selection instanceof StructuredSelection) {
			StructuredSelection sel = (StructuredSelection) selection;
			if (sel.getFirstElement() instanceof EObject) {
				theInput = (EObject) sel.getFirstElement();
				viewer.getViewer().setInput(theInput);
				if (theInput instanceof TunerStatus) {
					viewer.setTuner((TunerStatus) theInput);
				}
			} else {
				theInput = null;
				viewer.getViewer().setInput(null);
				viewer.setTuner(null);
			}
			showRightToolbarButtons(theInput);
		}
	}

	public EObject getInput() {
		return theInput;
	}

	public IWorkbenchPart getInputPart() {
		return inputPart;
	}

	public void unsetPageSelection() {
		page.selectionChanged(inputPart, StructuredSelection.EMPTY);
		setInput(null, null);
	}

	private void hideAllToolbarButtons() {
		if (getToolbar() == null) {
			return;
		}
		getToolbar().remove(allocateAction.getId());
		getToolbar().remove(deallocateAction.getId());
		getToolbar().remove(plotAction.getId());
		page.getSite().getActionBars().updateActionBars();
	}

	private void addToToolbar(IAction action) {
		if (getToolbar() == null) {
			return;
		}
		if (getToolbar().find(action.getId()) == null) {
			getToolbar().add(action);
		}
	}

	private void setComponentName(IAction action) {
		ActionContributionItem item = (ActionContributionItem) getToolbar().find(action.getId());
		if (item == null) {
			return;
		}
		Widget w = item.getWidget();
		if (w != null) {
			w.setData("TEST_COMP_NAME", action.getId());
		}
	}
	
	private void showRightToolbarButtons(Object obj) {
		if (getToolbar() == null) {
			return;
		}
		getToolbar().remove(allocateAction.getId());
		getToolbar().remove(deallocateAction.getId());
		getToolbar().remove(plotAction.getId());
		if (obj instanceof TunerStatus) {
			addToToolbar(deallocateAction);
			addToToolbar(plotAction);
		}
		if (obj instanceof ListenerAllocation) {
			addToToolbar(deallocateAction);
		}
		if (obj instanceof TunerContainer) {
			addToToolbar(allocateAction);
			addToToolbar(deallocateAction);
		}
		if (obj instanceof UnallocatedTunerContainer) {
			addToToolbar(allocateAction);
		}
		page.getSite().getActionBars().updateActionBars();
		setComponentName(allocateAction);
		setComponentName(deallocateAction);
		setComponentName(plotAction);
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#refresh()
	 */
	@Override
	public void refresh() {
		if (this.viewer != null && !this.viewer.getViewer().isCellEditorActive()) {
			this.viewer.getViewer().refresh();
		}
	}

	@Override
	public void dispose() {
		hideAllToolbarButtons();
		super.dispose();
	}

	@Override
	public void aboutToBeHidden() {
		hideAllToolbarButtons();
		super.aboutToBeHidden();
	}

	@Override
	public void aboutToBeShown() {
		showRightToolbarButtons(getInput());
		super.aboutToBeShown();
	}
}

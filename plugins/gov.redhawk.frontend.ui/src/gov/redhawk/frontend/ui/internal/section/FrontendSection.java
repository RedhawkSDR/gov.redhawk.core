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

import gov.redhawk.frontend.FrontendPackage;
import gov.redhawk.frontend.ListenerAllocation;
import gov.redhawk.frontend.TunerContainer;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.UnallocatedTunerContainer;
import gov.redhawk.frontend.provider.FrontendItemProviderAdapterFactory;
import gov.redhawk.frontend.ui.internal.FrontendAction;
import gov.redhawk.frontend.ui.internal.TunerStatusFilter;
import gov.redhawk.frontend.util.TunerProperties.TunerStatusAllocationProperties;
import gov.redhawk.frontend.util.TunerPropertyWrapper;
import gov.redhawk.frontend.util.TunerUtils;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.commands.ScaModelCommand;

import java.util.ArrayList;
import java.util.List;

import mil.jpeojtrs.sca.util.AnyUtils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.layout.TreeColumnLayout;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.progress.UIJob;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

public class FrontendSection extends AbstractPropertySection {

	private TreeViewer viewer;
	private TreeColumnLayout treeLayout;
	private EObject theInput;
	private IAction deallocateAction;
	private IAction allocateAction;
	private IAction plotAction;
	private IWorkbenchPart inputPart;
	private UIJob job;

	/**
	 * The Property Sheet Page.
	 */
	private TabbedPropertySheetPage page;
	private Adapter inputListener = new AdapterImpl() {
		public void notifyChanged(org.eclipse.emf.common.notify.Notification msg) {
			if (msg.isTouch()) {
				return;
			}
			if (msg.getFeature() == FrontendPackage.Literals.TUNER_STATUS__TUNER_STATUS_STRUCT) {
				return;
			}
			// TODO Be more efficient
			if (viewer != null && !viewer.getControl().isDisposed() && job != null) {
				job.schedule();
			}
		}
	};

	public FrontendSection() {
	}

	@Override
	public void createControls(Composite parent, final TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		final Composite composite = new Composite(parent, SWT.None);
		viewer = new TreeViewer(composite, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
		viewer.setUseHashlookup(true);
		viewer.getTree().setHeaderVisible(true);
		viewer.getTree().setLinesVisible(true);

		treeLayout = new TreeColumnLayout();
		composite.setLayout(treeLayout);

		GridData layoutData = new GridData();
		layoutData.grabExcessHorizontalSpace = true;
		layoutData.grabExcessVerticalSpace = true;
		layoutData.horizontalAlignment = GridData.FILL;
		viewer.getControl().setLayoutData(layoutData);

		initializeColumns();

		FrontendItemProviderAdapterFactory adapterFactory = new FrontendItemProviderAdapterFactory();

		viewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory) {
			@Override
			public Object[] getElements(Object object) {
				if (object instanceof TunerStatus) {
					List<TunerPropertyWrapper> tunerProperties = new ArrayList<TunerPropertyWrapper>();
					TunerStatus tuner = (TunerStatus) object;
					for (ScaSimpleProperty simple : tuner.getSimples()) {
						TunerPropertyWrapper prop = new TunerPropertyWrapper(tuner, simple);
						tunerProperties.add(prop);
					}
					return tunerProperties.toArray();
				} else if (object instanceof UnallocatedTunerContainer) {
					List<String[]> elements = new ArrayList<String[]>();
					UnallocatedTunerContainer container = (UnallocatedTunerContainer) object;
					elements.add(new String[] { "Unallocated " + container.getTunerType(), container.getCount() + " available" });
					return elements.toArray();
				} else if (object instanceof ListenerAllocation) {
					List<String[]> elements = new ArrayList<String[]>();
					elements.add(new String[] { "Listener ID", ((ListenerAllocation) object).getListenerID() });
					elements.add(new String[] { "Existing Tuner ID", TunerUtils.getControlId(((ListenerAllocation) object).getTunerStatus()) });
					return elements.toArray();
				}
				return super.getElements(object);
			}
		});
		viewer.setFilters(new ViewerFilter[] { new TunerStatusFilter() });

		allocateAction = new FrontendAction(this, "Allocate...", "gov.redhawk.frontend.actions.allocate", "gov.redhawk.frontend.commands.allocate",
			"icons/allocate.gif");
		deallocateAction = new FrontendAction(this, "Deallocate", "gov.redhawk.frontend.actions.deallocate", "gov.redhawk.frontend.commands.deallocate",
			"icons/deallocate.gif");
		plotAction = new FrontendAction(this, "Plot", "gov.redhawk.frontend.actions.plot", "gov.redhawk.frontend.commands.plot", "icons/plot.gif");

		page = aTabbedPropertySheetPage;

		job = new UIJob(viewer.getControl().getDisplay(), "Refreshing view") {

			{
				setUser(false);
				setSystem(true);
			}

			@Override
			public IStatus runInUIThread(IProgressMonitor monitor) {
				if (viewer != null && !viewer.getControl().isDisposed()) {
					viewer.refresh();
				}
				return Status.OK_STATUS;
			}
		};
	}

	@Override
	public boolean shouldUseExtraSpace() {
		return true;
	}

	private void initializeColumns() {
		TreeViewerColumn propertyColumn = new TreeViewerColumn(viewer, SWT.None);
		propertyColumn.getColumn().setWidth(200);
		propertyColumn.getColumn().setText("Property");
		propertyColumn.setLabelProvider(new StyledCellLabelProvider() {

			@Override
			public void update(ViewerCell cell) {
				if (cell.getElement() instanceof TunerPropertyWrapper) {
					TunerPropertyWrapper wrapper = (TunerPropertyWrapper) cell.getElement();
					cell.setText(wrapper.getName());

					// If this is not an editable field, set label color to dark gray
					if (!isEditable(wrapper.getName())) {
						StyleRange styleRange = new StyleRange(0, cell.getText().length(), Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GRAY), null);
						StyleRange[] range = { styleRange };
						cell.setStyleRanges(range);
					}
				} else if (cell.getElement() instanceof String[]) {
					cell.setText(((String[]) cell.getElement())[0]);
				} else if (cell.getElement() instanceof TunerStatus) {
					cell.setText("Allocated " + ((TunerStatus) cell.getElement()).getTunerType());
				} else if (cell.getElement() instanceof UnallocatedTunerContainer) {
					cell.setText("Unallocated " + ((UnallocatedTunerContainer) cell.getElement()).getTunerType());
				} else if (cell.getElement() instanceof ListenerAllocation) {
					cell.setText("Listener");
				} else {
					// Default behavior for any object other than a TunerWrapper
					cell.setText(cell.getElement().toString());
				}
				super.update(cell);
			}

		});
		treeLayout.setColumnData(propertyColumn.getColumn(), new ColumnPixelData(300));

		TreeViewerColumn valueColumn = new TreeViewerColumn(viewer, SWT.None);
		valueColumn.getColumn().setWidth(400);
		valueColumn.getColumn().setText("Value");
		valueColumn.setLabelProvider(new StyledCellLabelProvider() {

			@Override
			public void update(ViewerCell cell) {
				if (cell.getElement() instanceof TunerPropertyWrapper) {
					TunerPropertyWrapper wrapper = (TunerPropertyWrapper) cell.getElement();
					cell.setText(String.valueOf(wrapper.getValue()));

					// If this is not an editable field, set label color to dark gray
					if (!isEditable(wrapper.getName())) {
						StyleRange styleRange = new StyleRange(0, cell.getText().length(), Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GRAY), null);
						StyleRange[] range = { styleRange };
						cell.setStyleRanges(range);
					}
				} else if (cell.getElement() instanceof String[]) {
					cell.setText(((String[]) cell.getElement())[1]);
				} else if (cell.getElement() instanceof TunerStatus) {
					cell.setText(((TunerStatus) cell.getElement()).getAllocationID());
				} else if (cell.getElement() instanceof ListenerAllocation) {
					cell.setText(((ListenerAllocation) cell.getElement()).getListenerID());
				} else if (cell.getElement() instanceof UnallocatedTunerContainer) {
					UnallocatedTunerContainer container = (UnallocatedTunerContainer) cell.getElement();
					cell.setText(container.getCount() + " available");
				} else {
					// Default behavior for any object other than a TunerWrapper
					cell.setText(cell.getElement().toString());
				}
				super.update(cell);
			}

		});
		treeLayout.setColumnData(valueColumn.getColumn(), new ColumnPixelData(600));
		final TextCellEditor cellEditor = new TextCellEditor(viewer.getTree());
		valueColumn.setEditingSupport(new EditingSupport(viewer) {

			@Override
			protected void setValue(Object element, Object value) {
				if (element instanceof TunerPropertyWrapper) {
					TunerPropertyWrapper wrapper = (TunerPropertyWrapper) element;
					if (value instanceof String) {
						value = AnyUtils.convertString((String) value, wrapper.getSimple().getDefinition().getType().toString());
					}
					TunerStatusAllocationProperties.updateValue(wrapper, value);
				}
			}

			@Override
			protected Object getValue(Object element) {
				if (element instanceof TunerPropertyWrapper) {
					TunerPropertyWrapper wrapper = (TunerPropertyWrapper) element;
					Object value = wrapper.getValue();
					return value.toString();
				}
				return "";
			}

			@Override
			protected CellEditor getCellEditor(Object element) {
				return cellEditor;
			}

			@Override
			protected boolean canEdit(Object element) {
				if (element instanceof TunerPropertyWrapper) {
					TunerPropertyWrapper wrapper = (TunerPropertyWrapper) element;

					// If tuner is not allocated, all fields are read only
					String allocID = wrapper.getTuner().getAllocationID();
					if (allocID == null || allocID.isEmpty()) {
						return false;
					}

					// Return true for editable properties
					String id = wrapper.getName();
					if (isEditable(id)) {
						return true;
					}
				}
				return false;
			}

		});
	}

	private boolean isEditable(String id) {
		List<String> editableProperties = new ArrayList<String>();
		editableProperties.add(TunerStatusAllocationProperties.AGC.getName());
		editableProperties.add(TunerStatusAllocationProperties.SAMPLE_RATE.getName());
		editableProperties.add(TunerStatusAllocationProperties.BANDWIDTH.getName());
		editableProperties.add(TunerStatusAllocationProperties.CENTER_FREQUENCY.getName());
		editableProperties.add(TunerStatusAllocationProperties.ENABLED.getName());
		editableProperties.add(TunerStatusAllocationProperties.GAIN.getName());
		editableProperties.add(TunerStatusAllocationProperties.REFERENCE_SOURCE.getName());

		for (String prop : editableProperties) {
			if (id.equals(prop)) {
				return true;
			}
		}
		return false;
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
		if (theInput != null) {
			ScaModelCommand.execute(theInput, new ScaModelCommand() {

				@Override
				public void execute() {
					theInput.eAdapters().remove(inputListener);
				}
			});
		}
		inputPart = part;
		if (page == null) {
			return;
		}
		if (selection instanceof StructuredSelection) {
			StructuredSelection sel = (StructuredSelection) selection;
			if (sel.getFirstElement() instanceof EObject) {
				theInput = (EObject) sel.getFirstElement();
				ScaModelCommand.execute(theInput, new ScaModelCommand() {

					@Override
					public void execute() {
						theInput.eAdapters().add(inputListener);
					}
				});
				viewer.setInput(theInput);
			} else {
				theInput = null;
				viewer.setInput(null);
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
		getToolbar().update(false);
	}

	private void addToToolbar(IAction action) {
		if (getToolbar() == null) {
			return;
		}
		if (getToolbar().find(action.getId()) == null) {
			getToolbar().add(action);
		}
	}

	private void showRightToolbarButtons(Object obj) {
		if (getToolbar() == null) {
			return;
		}
		if (obj instanceof TunerStatus) {
			getToolbar().remove(allocateAction.getId());
			addToToolbar(deallocateAction);
			addToToolbar(plotAction);
		}
		if (obj instanceof ListenerAllocation) {
			getToolbar().remove(allocateAction.getId());
			getToolbar().remove(plotAction.getId());
			addToToolbar(deallocateAction);
		}
		if (obj instanceof TunerContainer) {
			getToolbar().remove(plotAction.getId());
			addToToolbar(allocateAction);
			addToToolbar(deallocateAction);
		}
		getToolbar().update(false);
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

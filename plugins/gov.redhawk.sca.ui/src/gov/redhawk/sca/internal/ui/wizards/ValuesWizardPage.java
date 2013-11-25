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
package gov.redhawk.sca.internal.ui.wizards;

import java.util.ArrayList;
import java.util.List;

import mil.jpeojtrs.sca.prf.PropertyValueType;
import mil.jpeojtrs.sca.util.AnyUtils;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * 
 */
public class ValuesWizardPage extends WizardPage {

	private static class ClassStringContainer {
		private String value;

		public ClassStringContainer(final String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return this.value;
		}
	}

	private TableViewer viewer;
	private final List<ClassStringContainer> input = new ArrayList<ClassStringContainer>();
	private Button upButton;
	private Button downButton;
	private Button addButton;
	private Button removeButton;
	private final PropertyValueType type;
	private Boolean complex;

	/**
	 * @param type 
	 * @param pageName
	 * @param title
	 * @param titleImage
	 */
	protected ValuesWizardPage(final PropertyValueType type, Boolean complex) {
		super("valuesPage", "Values", null);
		this.setDescription("Edit the values.");
		this.type = type;
		this.complex = complex;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void createControl(final Composite parent) {
		final Composite control = new Composite(parent, SWT.None);
		control.setLayout(new GridLayout(2, false));

		Control subControl = createViewer(control);
		subControl.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		subControl = createButtons(control);

		setControl(control);
	}

	/**
	 * @param control
	 */
	private Control createButtons(final Composite control) {
		final Composite parent = new Composite(control, SWT.None);
		parent.setLayout(new GridLayout(1, false));

		this.upButton = new Button(parent, SWT.PUSH);
		this.upButton.setText("Up");
		this.upButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		this.upButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				final ClassStringContainer element = getSelectedElement();
				final int index = getSelectionIndex();
				ValuesWizardPage.this.input.remove(index);
				ValuesWizardPage.this.input.add(index - 1, element);
				updateButton();
				ValuesWizardPage.this.viewer.refresh();
			}
		});

		this.downButton = new Button(parent, SWT.PUSH);
		this.downButton.setText("Down");
		this.downButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		this.downButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				final ClassStringContainer element = getSelectedElement();
				final int index = getSelectionIndex();
				ValuesWizardPage.this.input.remove(index);
				ValuesWizardPage.this.input.add(index + 1, element);
				updateButton();
				ValuesWizardPage.this.viewer.refresh();
			}
		});

		this.addButton = new Button(parent, SWT.PUSH);
		this.addButton.setText("Add");
		this.addButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		this.addButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				final ClassStringContainer element = new ClassStringContainer("<Enter Value>");
				ValuesWizardPage.this.input.add(element);
				ValuesWizardPage.this.viewer.refresh();
				ValuesWizardPage.this.viewer.getTable().select(ValuesWizardPage.this.input.size() - 1);
				ValuesWizardPage.this.viewer.editElement(element, 0);
			}
		});

		this.removeButton = new Button(parent, SWT.PUSH);
		this.removeButton.setText("Remove");
		this.removeButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		this.removeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				final int index = getSelectionIndex();
				ValuesWizardPage.this.input.remove(index);
				ValuesWizardPage.this.viewer.refresh();
			}
		});

		updateButton();
		return parent;
	}

	/**
	 * @return
	 */
	protected int getSelectionIndex() {
		return this.input.indexOf(getSelectedElement());
	}

	private ClassStringContainer getSelectedElement() {
		return (ClassStringContainer) ((IStructuredSelection) this.viewer.getSelection()).getFirstElement();
	}

	private void updateButton() {
		final ClassStringContainer element = getSelectedElement();
		if (element != null) {
			final int index = getSelectionIndex();
			if (index == 0) {
				this.upButton.setEnabled(false);
			} else {
				this.upButton.setEnabled(true);
			}
			if (index + 1 == this.input.size()) {
				this.downButton.setEnabled(false);
			} else {
				this.downButton.setEnabled(true);
			}
			this.removeButton.setEnabled(true);
		} else {
			this.upButton.setEnabled(false);
			this.downButton.setEnabled(false);
			this.removeButton.setEnabled(false);
		}
	}

	/**
	 * @param control
	 */
	private Control createViewer(final Composite control) {
		this.viewer = new TableViewer(control, SWT.FULL_SELECTION | SWT.BORDER | SWT.SINGLE);
		final TableLayout layout = new TableLayout();
		this.viewer.getTable().setLayout(layout);
		this.viewer.setLabelProvider(new LabelProvider());
		this.viewer.setContentProvider(new ArrayContentProvider());
		this.viewer.setInput(this.input);
		this.viewer.getTable().setHeaderVisible(true);

		final TableViewerColumn valueCol = new TableViewerColumn(this.viewer, SWT.NONE);
		valueCol.getColumn().setText("Value");
		layout.addColumnData(new ColumnWeightData(2, 100, false)); // SUPPRESS CHECKSTYLE MagicNumber
		valueCol.setLabelProvider(new CellLabelProvider() {

			@Override
			public void update(final ViewerCell cell) {
				cell.setText(cell.getElement().toString());
			}

		});
		valueCol.setEditingSupport(new EditingSupport(this.viewer) {

			@Override
			protected boolean canEdit(final Object element) {
				return true;
			}

			@Override
			protected CellEditor getCellEditor(final Object element) {
				final TextCellEditor editor = new TextCellEditor((Composite) ValuesWizardPage.this.viewer.getControl());
				editor.setValidator(new ICellEditorValidator() {

					@Override
					public String isValid(final Object value) {
						try {
							AnyUtils.convertString(value.toString(), type.getLiteral(), complex);
							return null;
						} catch (final Exception e) { // SUPPRESS CHECKSTYLE Logged Error
							return e.getMessage();
						}
					}
				});
				return editor;
			}

			@Override
			protected Object getValue(final Object element) {
				return ((ClassStringContainer) element).value;
			}

			@Override
			protected void setValue(final Object element, final Object value) {
				((ClassStringContainer) element).value = value.toString();
				ValuesWizardPage.this.viewer.refresh(element);
			}

		});

		this.viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(final SelectionChangedEvent event) {
				updateButton();
			}

		});

		return this.viewer.getControl();
	}

	public void setInput(final String[] input) {
		this.input.clear();
		if (input != null) {
			for (final String str : input) {
				this.input.add(new ClassStringContainer(str));
			}
		}
		if (this.viewer != null) {
			this.viewer.refresh();
		}
	}

	/**
	 * @return
	 */
	public String[] getValues() {
		final String[] retVal = new String[this.input.size()];
		for (int i = 0; i < retVal.length; i++) {
			final ClassStringContainer cont = this.input.get(i);
			retVal[i] = cont.value;
		}
		return retVal;
	}

}

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
package gov.redhawk.sca.internal.ui.properties;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.provider.IWrapperItemProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationListener;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationStrategy;
import org.eclipse.jface.viewers.ColumnViewerEditorDeactivationEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TableViewerEditor;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Widget;

import gov.redhawk.model.sca.ScaSimpleSequenceProperty;
import gov.redhawk.model.sca.provider.ScaItemProviderAdapterFactory;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.provider.RadixLabelProviderUtil;

public class SimpleSequencePropertyValueWizardPage extends AbstractSequencePropertyValueWizardPage {

	private final ScaItemProviderAdapterFactory adapterFactory = new ScaItemProviderAdapterFactory();

	protected SimpleSequencePropertyValueWizardPage(final ScaSimpleSequenceProperty property) {
		super(property);
	}

	@Override
	public void dispose() {
		super.dispose();
		adapterFactory.dispose();
	}

	@Override
	protected EList< ? > getList() {
		return ((ScaSimpleSequenceProperty) property).getValues();
	}

	@Override
	protected void handleAddValue() {
		final ScaSimpleSequenceProperty seqProperty = (ScaSimpleSequenceProperty) property;
		SimpleSequence definition = seqProperty.getDefinition();
		Object value = definition.getType().getDefaultValue(definition.isComplex());
		seqProperty.getValues().add(value);
	}

	@Override
	protected int indexOf(List< ? > list, Object object) {
		return ((IWrapperItemProvider) object).getIndex();
	}

	@Override
	protected TableViewer createViewer(Composite parent) {
		final TableColumnLayout layout = new TableColumnLayout();
		parent.setLayout(layout);
		TableViewer viewer = new TableViewer(parent, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.H_SCROLL);
		createSimpleSequenceColumns(viewer, layout, (ScaSimpleSequenceProperty) property);
		viewer.getTable().setHeaderVisible(true);
		viewer.getTable().setLinesVisible(true);

		viewer.setContentProvider(new AdapterFactoryContentProvider(this.adapterFactory));
		return viewer;
	}

	private void createSimpleSequenceColumns(final TableViewer viewer, final TableColumnLayout layout, final ScaSimpleSequenceProperty seqProperty) {
		final TableViewerColumn columnViewer = new TableViewerColumn(viewer, SWT.CENTER);
		columnViewer.setEditingSupport(new ScaSimpleSequenceValueEditingSupport(seqProperty, viewer));
		TableViewerEditor.create(viewer, new DoubleClickActivationStrategy(viewer), 1);
		columnViewer.setLabelProvider(new ColumnLabelProvider() {

			@Override
			public void update(final ViewerCell cell) {
				super.update(cell);
				if (seqProperty.getDefinition() != null
					&& RadixLabelProviderUtil.supports(seqProperty.getDefinition().getType(), seqProperty.getDefinition().isComplex())) {
					int[] radix = new int[0];
					if (seqProperty.getDefinition().getValues() != null) {
						radix = RadixLabelProviderUtil.getRadix(seqProperty.getDefinition().getValues().getValue());
					}
					final Widget item = cell.getItem();
					final int valueIndex = viewer.getTable().indexOf((TableItem) item);
					if (valueIndex < radix.length) {
						cell.setText(RadixLabelProviderUtil.getText(seqProperty.getValues().get(valueIndex), radix[valueIndex]));
					} else if (radix.length > 0) {
						cell.setText(RadixLabelProviderUtil.getText(seqProperty.getValues().get(valueIndex), radix[0]));
					}
				}
			}

			@Override
			public String getText(final Object element) {
				return super.getText(AdapterFactoryEditingDomain.unwrap(element));
			}
		});
		layout.setColumnData(columnViewer.getColumn(), new ColumnPixelData(100, true));
		columnViewer.getColumn().setText("Value < " + seqProperty.getDefinition().getType().getLiteral() + " >");

		columnViewer.getViewer().getColumnViewerEditor().addEditorActivationListener(new ColumnViewerEditorActivationListener() {
			@Override
			public void beforeEditorDeactivated(ColumnViewerEditorDeactivationEvent event) {
			}

			@Override
			public void beforeEditorActivated(ColumnViewerEditorActivationEvent event) {
				viewer.setSelection(null);
			}

			@Override
			public void afterEditorDeactivated(ColumnViewerEditorDeactivationEvent event) {
			}

			@Override
			public void afterEditorActivated(ColumnViewerEditorActivationEvent event) {
			}
		});
	}

	private class DoubleClickActivationStrategy extends ColumnViewerEditorActivationStrategy {

		public DoubleClickActivationStrategy(ColumnViewer viewer) {
			super(viewer);
		}

		@Override
		protected boolean isEditorActivationEvent(ColumnViewerEditorActivationEvent event) {
			return (event.eventType == ColumnViewerEditorActivationEvent.MOUSE_DOUBLE_CLICK_SELECTION && ((MouseEvent) event.sourceEvent).button == 1);
		}

	}
}

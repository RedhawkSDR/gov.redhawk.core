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

import gov.redhawk.model.sca.ScaSimpleSequenceProperty;
import gov.redhawk.model.sca.provider.ScaSimpleSequencePropertyItemProvider;
import gov.redhawk.sca.ui.properties.ScaPropertiesAdapterFactory;
import mil.jpeojtrs.sca.prf.provider.RadixLabelProviderUtil;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Widget;

public class SimpleSequencePropertyValueWizardPage extends AbstractSequencePropertyValueWizardPage {

	protected final ScaPropertiesAdapterFactory adapterFactory = new ScaPropertiesAdapterFactory() {

		@Override
		public Adapter createScaSimpleSequencePropertyAdapter() {
			// We need to return our own instance of ScaSimpleSequencePropertyItemProvider here because the default
			// factory creates an overridden one that does not return any children.
			return new ScaSimpleSequencePropertyItemProvider(this);
		}
	};

	protected SimpleSequencePropertyValueWizardPage(final ScaSimpleSequenceProperty property) {
		super(property);
	}

	@Override
	public void dispose()
	{
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
		Object value = getDefaultValue(seqProperty.getDefinition().getType(), seqProperty.getDefinition().isComplex());
		seqProperty.getValues().add(value);
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
	}
}

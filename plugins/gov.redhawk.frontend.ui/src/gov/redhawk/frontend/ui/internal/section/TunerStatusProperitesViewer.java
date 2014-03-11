/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.frontend.ui.internal.section;

import gov.redhawk.frontend.ListenerAllocation;
import gov.redhawk.frontend.TunerStatus;
import gov.redhawk.frontend.UnallocatedTunerContainer;
import gov.redhawk.frontend.provider.FrontendItemProviderAdapterFactory;
import gov.redhawk.frontend.ui.internal.TunerStatusFilter;
import gov.redhawk.frontend.util.TunerProperties.TunerStatusAllocationProperties;
import gov.redhawk.frontend.util.TunerUtils;
import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.sca.ui.ScaModelAdapterFactoryLabelProvider;
import gov.redhawk.sca.ui.ScaPropertiesEditingSupport;
import gov.redhawk.sca.ui.ScaPropertiesViewer;
import gov.redhawk.sca.ui.ScaPropertiesViewerColumnLabelProvider;
import gov.redhawk.sca.ui.properties.AbstractPropertyEditingSupport;
import gov.redhawk.sca.ui.properties.ScaPropertiesContentProvider;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.views.properties.IPropertySourceProvider;

/**
 * 
 */
public class TunerStatusProperitesViewer extends ScaPropertiesViewer {

	private TunerStatus tuner;

	public TunerStatusProperitesViewer(Composite parent, int style, int treeStyle, AdapterFactory adapterFactory) {
		super(parent, style, treeStyle, adapterFactory);
	}

	public TunerStatusProperitesViewer(Composite parent, int style, int treeStyle) {
		super(parent, style, treeStyle);
	}

	@Override
	protected AdapterFactory createAdapterFactory() {
		ComposedAdapterFactory factory = new ComposedAdapterFactory();
		factory.addAdapterFactory(super.createAdapterFactory());
		factory.addAdapterFactory(new FrontendItemProviderAdapterFactory());
		return factory;
	}

	@Override
	protected void init(Composite parent, int style) {
		super.init(parent, style);
		getViewer().setFilters(new ViewerFilter[] { new TunerStatusFilter() });
	}

	@Override
	protected ITreeContentProvider createContentProvider() {
		return new ScaPropertiesContentProvider(getAdapterFactory()) {
			@Override
			public Object[] getElements(Object object) {
				if (object instanceof TunerStatus) {
					return ((TunerStatus) object).getSimples().toArray();
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
		};
	}

	@Override
	protected ILabelProvider createRootLabelProvider() {
		return new ScaModelAdapterFactoryLabelProvider(getAdapterFactory(), getViewer()) {

			@Override
			public Color getForeground(Object object) {
				if (object instanceof ScaAbstractProperty< ? >) {
					ScaAbstractProperty< ? > prop = (ScaAbstractProperty< ? >) object;
					TunerStatusAllocationProperties statusProp = TunerStatusAllocationProperties.fromPropID(prop.getId());
					if (statusProp != null && statusProp.isEditable()) {
						return getViewer().getControl().getForeground();
					}
				}
				return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_GRAY);
			}

			@Override
			public Color getForeground(Object object, int columnIndex) {
				return getForeground(object);
			}

		};
	}

	@Override
	protected CellLabelProvider createPropertyColumnLabelProvider(ILabelProvider rootLabelProvider) {
		return new ScaPropertiesViewerColumnLabelProvider(rootLabelProvider) {
			@Override
			public void update(ViewerCell cell) {
				if (cell.getElement() instanceof ScaAbstractProperty< ? >) {
					super.update(cell);
					ScaAbstractProperty< ? > prop = (ScaAbstractProperty< ? >) cell.getElement();
					TunerStatusAllocationProperties statusProp = TunerStatusAllocationProperties.fromPropID(prop.getId());
					if (statusProp != null) {
						cell.setText(statusProp.getName());
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
					super.update(cell);
				}
			}
		};
	}

	@Override
	protected CellLabelProvider createValueColumnLabelProvider(ILabelProvider rootLabelProvider) {
		return new ScaPropertiesViewerColumnLabelProvider(rootLabelProvider) {
			@Override
			public void update(ViewerCell cell) {
				if (cell.getElement() instanceof String[]) {
					cell.setText(((String[]) cell.getElement())[1]);
				} else if (cell.getElement() instanceof TunerStatus) {
					cell.setText(((TunerStatus) cell.getElement()).getAllocationID());
				} else if (cell.getElement() instanceof ListenerAllocation) {
					cell.setText(((ListenerAllocation) cell.getElement()).getListenerID());
				} else if (cell.getElement() instanceof UnallocatedTunerContainer) {
					UnallocatedTunerContainer container = (UnallocatedTunerContainer) cell.getElement();
					cell.setText(container.getCount() + " available");
				} else {
					super.update(cell);
				}
			}
		};
	}

	@Override
	protected AbstractPropertyEditingSupport createValueColumnEditingSupport() {
		return new ScaPropertiesEditingSupport(getViewer(), (IPropertySourceProvider) getViewer().getContentProvider()) {

			@Override
			protected boolean canEdit(ScaAbstractProperty< ? > prop) {
				TunerStatusAllocationProperties statusProp = TunerStatusAllocationProperties.fromPropID(prop.getId());
				if (statusProp != null) {
					return statusProp.isEditable();
				}
				return super.canEdit(prop);
			}

			@Override
			protected void setValue(Object object, final Object newValue) {
				if (object instanceof ScaAbstractProperty< ? >) {
					final ScaAbstractProperty< ? > prop = (ScaAbstractProperty< ? >) object;
					final TunerStatusAllocationProperties statusProp = TunerStatusAllocationProperties.fromPropID(prop.getId());
					if (statusProp != null && tuner != null) {
						ScaModelCommand.execute(prop, new ScaModelCommand() {

							@Override
							public void execute() {
								TunerStatusAllocationProperties.updateTunerStatusValue(tuner, statusProp, newValue);
								try {
									prop.setIgnoreRemoteSet(true);
									if (prop instanceof ScaSimpleProperty) {
										((ScaSimpleProperty) prop).setValue(newValue);
									}
								} finally {
									prop.setIgnoreRemoteSet(false);
								}
							}
						});
					}
				}

			}
		};
	}

	public void setTuner(TunerStatus tuner) {
		this.tuner = tuner;
	}

	public TunerStatus getTuner() {
		return tuner;
	}

}

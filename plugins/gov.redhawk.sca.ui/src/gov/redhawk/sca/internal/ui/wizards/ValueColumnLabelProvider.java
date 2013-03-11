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
package gov.redhawk.sca.internal.ui.wizards;

import java.util.Arrays;
import java.util.List;

import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;
import mil.jpeojtrs.sca.prf.Enumeration;
import mil.jpeojtrs.sca.prf.Enumerations;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleRef;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.SimpleSequenceRef;
import mil.jpeojtrs.sca.prf.util.PropertiesUtil;
import mil.jpeojtrs.sca.util.AnyUtils;

import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Control;

import CF.DataType;

/**
 * @since 2.0
 * 
 */
public class ValueColumnLabelProvider extends ColumnLabelProvider {
	private static final int DEFAULT_TOOLTIP_DELAY = 100;

	private final TableViewer viewer;
	private final List<DataType> overwrittenProps;

	/**
	 * @param viewer
	 */
	public ValueColumnLabelProvider(final TableViewer viewer, final List<DataType> overwrittenProps) {
		this.viewer = viewer;
		this.overwrittenProps = overwrittenProps;
	}

	@Override
	public int getToolTipTimeDisplayed(final Object element) {
		boolean display = false;
		if (element instanceof Simple) {
			display = true;
		} else if (element instanceof SimpleSequence) {
			display = true;
		}
		if (display) {
			return -1;
		}
		return super.getToolTipTimeDisplayed(element);
	}

	@Override
	public String getToolTipText(final Object element) {
		if (element instanceof Simple) {
			final Simple ref = (Simple) element;
			final StringBuilder builder = new StringBuilder();
			if (ref.getUnits() != null) {
				builder.append("Units:");
				builder.append(ref.getUnits());
				builder.append('\n');
			}
			if (ref.getRange() != null) {
				builder.append("Range:");
				builder.append('\n');
				builder.append("\tMin:");
				builder.append(ref.getRange().getMin());
				builder.append('\n');
				builder.append("\tMax:");
				builder.append(ref.getRange().getMax());
				builder.append('\n');
			}
			if (builder.toString().equals("")) {
				return null;
			}
			return builder.toString();

		} else if (element instanceof SimpleSequence) {
			final SimpleSequence ref = (SimpleSequence) element;
			final StringBuilder builder = new StringBuilder();
			if (ref.getUnits() != null) {
				builder.append("Units:");
				builder.append(ref.getUnits());
				builder.append('\n');
			}
			if (ref.getRange() != null) {
				builder.append("Range:");
				builder.append('\n');
				builder.append("\tMin:");
				builder.append(ref.getRange().getMin());
				builder.append('\n');
				builder.append("\tMax:");
				builder.append(ref.getRange().getMax());
				builder.append('\n');
			}
			if (builder.toString().equals("")) {
				return null;
			}
			return builder.toString();
		}
		return super.getToolTipText(element);
	}

	@Override
	public Image getImage(final Object element) {
		// TODO Auto-generated method stub
		Image result;
		final Object staticImage = ItemPropertyDescriptor.GENERIC_VALUE_IMAGE;
		result = ExtendedImageRegistry.getInstance().getImage(staticImage);
		return result;
	}

	@Override
	public int getToolTipDisplayDelayTime(final Object element) {
		boolean display = false;
		if (element instanceof Simple) {
			display = true;
		} else if (element instanceof SimpleSequence) {
			display = true;
		}
		if (display) {
			return ValueColumnLabelProvider.DEFAULT_TOOLTIP_DELAY;
		}
		return super.getToolTipDisplayDelayTime(element);
	}

	@Override
	public void update(final ViewerCell cell) {
		final Object element = cell.getElement();
		if (element instanceof Simple) {
			final Simple simple = (Simple) element;
			final SimpleRef ref = Util.getRef(simple, getComponentInstantiation());
			final DataType override = getOverride(simple.getId());
			if (override == null) {
				cell.setForeground(getDefaultColor(cell.getControl()));
				if (ref == null) {
					if (simple.getValue() != null) {
						cell.setText(getText(simple.getEnumerations(), simple.getValue()));
					} else {
						cell.setText("");
					}
				} else {
					cell.setText(ref.getValue());
				}
			} else {
				cell.setText(AnyUtils.convertAny(override.value).toString());
				cell.setForeground(null);
			}
			Image image = null;
			if (PropertiesUtil.canOverride(simple)) {
				image = getImage(element);
			}
			cell.setImage(image);
		} else if (element instanceof SimpleSequence) {
			final SimpleSequence simple = (SimpleSequence) element;
			final SimpleSequenceRef ref = Util.getRef(simple, getComponentInstantiation());
			final DataType override = getOverride(simple.getId());
			if (override == null) {
				cell.setForeground(getDefaultColor(cell.getControl()));
				if (ref == null) {
					if (simple.getValues() != null) {
						cell.setText(Arrays.toString(simple.getValues().getValue().toArray()));
					} else {
						cell.setText("");
					}
				} else {
					cell.setText(Arrays.toString(ref.getValues().getValue().toArray()));
				}
			} else {
				cell.setText(Arrays.toString((Object[]) AnyUtils.convertAny(override.value, override.value.type())));
				cell.setForeground(null);
			}
			Image image = null;
			if (PropertiesUtil.canOverride(simple)) {
				image = getImage(element);
			}
			cell.setImage(image);
		}

	}

	/**
	 * @return
	 */
	protected Color getDefaultColor(final Control control) {
		return control.getDisplay().getSystemColor(SWT.COLOR_DARK_GRAY);
	}

	private ComponentInstantiation getComponentInstantiation() {
		return (ComponentInstantiation) this.viewer.getInput();
	}

	/**
	 * @param enumerations
	 * @param value
	 * @return
	 */
	private String getText(final Enumerations enumerations, final String value) {
		if (enumerations != null) {
			for (final Enumeration enumeration : enumerations.getEnumeration()) {
				if (enumeration.getValue().equals(value)) {
					return enumeration.getLabel();
				}
			}
		}
		return value;
	}

	private DataType getOverride(final String id) {
		for (final DataType dt : this.overwrittenProps) {
			if (dt.id.equals(id)) {
				return dt;
			}
		}
		return null;
	}

}

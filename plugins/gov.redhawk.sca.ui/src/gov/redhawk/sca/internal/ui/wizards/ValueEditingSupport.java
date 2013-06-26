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

import gov.redhawk.sca.util.PluginUtil;

import java.util.Arrays;
import java.util.List;

import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;
import mil.jpeojtrs.sca.prf.AbstractProperty;
import mil.jpeojtrs.sca.prf.Enumeration;
import mil.jpeojtrs.sca.prf.Enumerations;
import mil.jpeojtrs.sca.prf.PropertyValueType;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleRef;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.SimpleSequenceRef;
import mil.jpeojtrs.sca.prf.util.PropertiesUtil;
import mil.jpeojtrs.sca.util.AnyUtils;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import CF.DataType;

/**
 * 
 */
public class ValueEditingSupport extends EditingSupport {
	private final List<DataType> overwrittenProps;

	/**
	 * @param viewer
	 * @param componentPropertiesPropertySection
	 */
	public ValueEditingSupport(final ColumnViewer viewer, final List<DataType> overwrittenProps) {
		super(viewer);
		this.overwrittenProps = overwrittenProps;
	}

	@Override
	protected boolean canEdit(final Object element) {
		if (element instanceof AbstractProperty) {
			return PropertiesUtil.canOverride((AbstractProperty) element);
		}
		return false;
	}

	@Override
	protected CellEditor getCellEditor(final Object element) {
		if (element instanceof Simple) {
			final Simple simple = (Simple) element;
			if (simple.getEnumerations() != null) {
				final Enumerations enumList = simple.getEnumerations();
				final ComboBoxViewerCellEditor editor = new ComboBoxViewerCellEditor((Composite) getViewer().getControl(), SWT.READ_ONLY);
				editor.setContenProvider(new ArrayContentProvider());
				editor.setLabelProvider(new LabelProvider() {
					@Override
					public String getText(final Object element) {
						return ((Enumeration) element).getLabel();
					}
				});
				editor.setInput(enumList.getEnumeration());
				return editor;
			} else if (simple.getType() == PropertyValueType.BOOLEAN) {
				final ComboBoxViewerCellEditor editor = new ComboBoxViewerCellEditor((Composite) getViewer().getControl(), SWT.READ_ONLY);
				editor.setContenProvider(new ArrayContentProvider());
				editor.setLabelProvider(new LabelProvider());
				editor.setInput(new Object[] {
				        true, false
				});
				return editor;
			} else {
				final TextCellEditor editor = new TextCellEditor((Composite) getViewer().getControl());
				editor.setValidator(new ICellEditorValidator() {

					public String isValid(final Object value) {
						try {
							AnyUtils.convertString(value.toString(), simple.getType().getLiteral(), simple.isComplex());
							return null;
						} catch (final Exception e) {
							return e.getMessage();
						}
					}
				});
				return editor;
			}
		} else if (element instanceof SimpleSequence) {
			final SimpleSequence ss = (SimpleSequence) element;
			return new ValuesDialogEditor((Composite) getViewer().getControl(), ss.getType(), ss.isComplex());
		}
		// TODO Auto-generated method stub
		return null;
	}

	private ComponentInstantiation getComponentInstantiation() {
		return (ComponentInstantiation) getViewer().getInput();
	}

	@Override
	protected Object getValue(final Object element) {
		if (element instanceof Simple) {
			final Simple simple = (Simple) element;
			final SimpleRef ref = Util.getRef(simple, getComponentInstantiation());
			final DataType override = getOverride(simple.getId());
			if (override != null) {
				return AnyUtils.convertAny(override.value).toString();
			} else if (ref != null) {
				return getValue(simple.getEnumerations(), ref.getValue()).toString();
			} else {
				return getValue(simple.getEnumerations(), simple.getValue()).toString();
			}
		} else if (element instanceof SimpleSequence) {
			final SimpleSequence simple = (SimpleSequence) element;
			final SimpleSequenceRef ref = Util.getRef(simple, getComponentInstantiation());
			final DataType override = getOverride(simple.getId());
			if (override != null) {
				return Arrays.toString((Object[]) AnyUtils.convertAny(override.value, override.value.type()));
			} else if (ref != null) {
				if (ref.getValues() != null) {
					return ref.getValues().getValue().toArray(new String[ref.getValues().getValue().size()]);
				}
			} else {
				if (simple.getValues() != null) {
					return simple.getValues().getValue().toArray(new String[simple.getValues().getValue().size()]);
				}
			}
		}
		return null;
	}

	@Override
	protected void setValue(final Object element, Object value) {
		final ComponentInstantiation inst = getComponentInstantiation();
		inst.getComponentProperties();
		if (element instanceof Simple) {
			final Simple simple = (Simple) element;
			final SimpleRef ref = Util.getRef(simple, inst);
			String defaultValue = simple.getValue();
			if (ref != null) {
				defaultValue = ref.getValue();
			}
			DataType override = getOverride(simple.getId());
			if (override == null) {
				if (value instanceof Enumeration) {
					value = ((Enumeration) value).getValue();
				}
				if (!(value == null || "".equals(value.toString()) || value.equals(defaultValue))) {
					// Create new component property
					override = new DataType(simple.getId(), AnyUtils.toAny(value, simple.getType().toString(), simple.isComplex()));
					this.overwrittenProps.add(override);
				}
			} else {
				if (value == null || "".equals(value.toString()) || value.equals(defaultValue)) {
					// Revert to default
					this.overwrittenProps.remove(override);
				} else {
					override.value = AnyUtils.toAny(value, simple.getType().toString(), simple.isComplex());
				}
			}
		} else if (element instanceof SimpleSequence) {
			final SimpleSequence seq = (SimpleSequence) element;

			final SimpleSequenceRef ref = Util.getRef(seq, inst.getComponentProperties());
			String[] valuesArray = new String[0];
			if (value instanceof String[]) {
				valuesArray = (String[]) value;
			} else if (value instanceof String) {
				valuesArray = ((String) value).substring(1, ((String) value).length() - 1).split(", ");
			}

			final String[] defaultArray = new String[0];
			if ((seq.getValues() != null) && (ref == null)) {
				seq.getValues().getValue().toArray(new String[seq.getValues().getValue().size()]);
			} else if (ref != null) {
				if (ref.getValues() != null) {
					ref.getValues().getValue().toArray(new String[ref.getValues().getValue().size()]);
				}
			}

			DataType override = getOverride(seq.getId());
			if (override == null) {
				if (value instanceof Enumeration) {
					value = ((Enumeration) value).getValue();
				}
				if (!(value == null || valuesArray.length == 0 || Arrays.deepEquals(valuesArray, defaultArray))) {
					// Create new component property
					override = new DataType(seq.getId(), AnyUtils.toAny(valuesArray, seq.getType().toString(), seq.isComplex()));
					this.overwrittenProps.add(override);
				}
			} else {
				if (value == null || valuesArray.length == 0 || Arrays.deepEquals(valuesArray, defaultArray)) {
					// Revert to default
					this.overwrittenProps.remove(override);
				} else {
					override.value = AnyUtils.toAny(valuesArray, seq.getType().toString(), seq.isComplex());
				}
			}
		}
		final ColumnViewer myViewer = getViewer();
		if (myViewer != null) {
			myViewer.refresh();
		}
	}

	private Object getValue(final Enumerations enumerations, final String value) {
		if (enumerations != null) {
			for (final Enumeration enumeration : enumerations.getEnumeration()) {
				if (PluginUtil.equals(enumeration.getValue(), value)) {
					return enumeration;
				}
			}
		}
		return (value == null) ? "" : value; // SUPPRESS CHECKSTYLE AvoidInLine
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

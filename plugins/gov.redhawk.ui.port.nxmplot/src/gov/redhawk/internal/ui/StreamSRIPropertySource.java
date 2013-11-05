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
package gov.redhawk.internal.ui;

import java.util.ArrayList;
import java.util.List;

import mil.jpeojtrs.sca.util.AnyUtils;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource2;

import BULKIO.StreamSRI;
import CF.DataType;

/**
 * @noreference This class is not intended to be referenced by clients.
 */
public class StreamSRIPropertySource implements IPropertySource2 {

	private enum SRI_PROP implements IPropertyDescriptor {
		hversion, xstart, xdelta, xunits, subsize, ystart, ydelta, yunits, mode, streamID, blocking, keywords;

		@Override
		public CellEditor createPropertyEditor(final Composite parent) {
			return null;
		}

		@Override
		public String getCategory() {
			return StreamSRI.class.getName();
		}

		@Override
		public String getDescription() {
			return null;
		}

		@Override
		public String getDisplayName() {
			return name();
		}

		@Override
		public String[] getFilterFlags() {
			return null;
		}

		@Override
		public Object getHelpContextIds() {
			return null;
		}

		@Override
		public Object getId() {
			return name();
		}

		@Override
		public ILabelProvider getLabelProvider() {
			return new LabelProvider() {
				@Override
				public String getText(final Object obj) {
					if (obj instanceof DataType[]) {
						final DataType[] keywords = (DataType[]) obj;
						final List<String> result = new ArrayList<String>();
						for (final DataType t : keywords) {
							result.add(getText(t));
						}
						return result.toString();
					} else if (obj instanceof DataType) {
						final DataType t = (DataType) obj;
						return t.id + ", " + getText(AnyUtils.convertAny(t.value));
					}
					return super.getText(obj);
				}
			};
		}

		@Override
		public boolean isCompatibleWith(final IPropertyDescriptor anotherProperty) {
			return false;
		}
	}

	private final StreamSRI sri;

	public StreamSRIPropertySource(final StreamSRI sri) {
		this.sri = sri;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getEditableValue() {
		return this.sri;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		return SRI_PROP.values();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Object getPropertyValue(final Object id) {
		switch (SRI_PROP.valueOf((String) id)) {
		case blocking:
			return this.sri.blocking;
		case hversion:
			return this.sri.hversion;
		case keywords:
			return this.sri.keywords;
		case mode:
			return this.sri.mode;
		case streamID:
			return this.sri.streamID;
		case subsize:
			return this.sri.subsize;
		case xdelta:
			return this.sri.xdelta;
		case xstart:
			return this.sri.xstart;
		case xunits:
			return this.sri.xunits;
		case ydelta:
			return this.sri.ydelta;
		case ystart:
			return this.sri.ystart;
		case yunits:
			return this.sri.yunits;
		default:
			break;
		}
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void resetPropertyValue(final Object id) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPropertyValue(final Object id, final Object value) {

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isPropertyResettable(final Object id) {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isPropertySet(final Object id) {
		return true;
	}

}

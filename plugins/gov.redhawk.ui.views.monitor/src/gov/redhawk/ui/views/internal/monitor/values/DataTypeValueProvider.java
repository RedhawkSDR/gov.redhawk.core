/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.ui.views.internal.monitor.values;

import org.omg.CORBA.Any;

import BULKIO.PortStatistics;
import CF.DataType;
import gov.redhawk.monitor.model.ports.PortStatisticsProvider;

public abstract class DataTypeValueProvider< T > implements ValueProvider<T> {

	private String id;

	public DataTypeValueProvider(String id) {
		this.id = id;
	}

	protected Any getAny(Object input) {
		if (!(input instanceof PortStatisticsProvider)) {
			return null;
		}
		final PortStatistics stats = ((PortStatisticsProvider) input).getData();
		if (stats == null) {
			return null;
		}
		final DataType[] keywords = stats.keywords;
		for (final DataType keyword : keywords) {
			if (keyword.id.equals(this.id)) {
				return keyword.value;
			}
		}
		return null;
	}

}

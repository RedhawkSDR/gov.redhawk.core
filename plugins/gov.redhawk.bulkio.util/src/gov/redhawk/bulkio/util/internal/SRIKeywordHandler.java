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
package gov.redhawk.bulkio.util.internal;

import java.util.Arrays;

import mil.jpeojtrs.sca.util.AnyUtils;
import nxm.sys.lib.Table;

import org.omg.CORBA.Any;
import org.omg.CORBA.AnySeqHelper;

import CF.DataType;
import CF.PropertiesHelper;

public class SRIKeywordHandler {
	private Table keywordTable;

	public void addKeywordsToTable(DataType[] keywords, Table keywordTable) {
		this.keywordTable = keywordTable;
		for (DataType dt : keywords) {
			if (AnySeqHelper.type().equal(dt.value.type())) {
				parseStructSequence(dt);
			} else if (PropertiesHelper.type().equal(dt.value.type())) {
				parseStruct(dt);
			} else {
				Object value = AnyUtils.convertAny(dt.value);
				if (value instanceof Object[]) {
					//parse simple sequence
					Object[] values = (Object[]) value;
					keywordTable.put(dt.id, Arrays.toString(values));
				} else {
					//parse simple
					keywordTable.put(dt.id, value);
				}
			}
		}
	}

	private void parseStruct(DataType dt) {
		Table structTable = (Table) keywordTable.addTable(dt.id);
		DataType[] elements = PropertiesHelper.extract(dt.value);
		for (DataType element : elements) {
			structTable.put(element.id, element.value);
		}
	}

	private void parseStructSequence(DataType dt) {
		Table structSeqTable = (Table) keywordTable.addTable(dt.id);
		Any[] elements = AnySeqHelper.extract(dt.value);
		int elementNum = 0;
		for (Any element : elements) {
			Table elementTable = (Table) structSeqTable.addTable(dt.id + "[" + elementNum++ + "]");
			if (PropertiesHelper.type().equal(element.type())) {
				DataType[] childElements = PropertiesHelper.extract(element);
				for (DataType childElement : childElements) {
					elementTable.put(childElement.id, childElement.value);
				}
			}
		}
	}
}

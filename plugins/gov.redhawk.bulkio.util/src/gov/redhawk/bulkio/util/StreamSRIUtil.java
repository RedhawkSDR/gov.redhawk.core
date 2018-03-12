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
package gov.redhawk.bulkio.util;

import gov.redhawk.bulkio.util.internal.SRIKeywordHandler;
import gov.redhawk.sca.util.PluginUtil;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import mil.jpeojtrs.sca.util.AnyUtils;
import nxm.sys.lib.Table;

import BULKIO.StreamSRI;
import CF.DataType;

/**
 * @since 2.0
 */
public final class StreamSRIUtil {
	private StreamSRIUtil() {
	}

	/**
	 * @since 2.0
	 */
	public static void putSriInfo(StreamSRI sri, Table rootTable) {
		Table sriTable = (Table) rootTable.addTable("SRI");
		sriTable.put("streamid", sri.streamID);
		sriTable.put("blocking", sri.blocking);
		sriTable.put("hversion", sri.hversion);
		sriTable.put("mode", sri.mode);
		sriTable.put("subsize", sri.subsize);
		sriTable.put("xdelta", sri.xdelta);
		sriTable.put("xstart", sri.xstart);
		sriTable.put("xunits", sri.xunits);
		sriTable.put("ydelta", sri.ydelta);
		sriTable.put("ystart", sri.ystart);
		sriTable.put("yunits", sri.yunits);

		if (sri.keywords.length > 0) {
			Table keywordTable = (Table) rootTable.addTable("Keywords");
			SRIKeywordHandler keyHandler = new SRIKeywordHandler();
			keyHandler.addKeywordsToTable(sri.keywords, keywordTable);
		}
	}
	
	public static Map<String, Object> toMap(DataType[] dataTypes) {
		if (dataTypes == null || dataTypes.length == 0) {
			return Collections.emptyMap();
		}
		Map<String, Object> retVal = new LinkedHashMap<String, Object>(dataTypes.length);
		for (DataType keyword : dataTypes) {
			Object value = AnyUtils.convertAny(keyword.value);

			if (value instanceof DataType[]) {
				DataType[] subTypes = (DataType[]) value;
				retVal.put(keyword.id, toMap(subTypes));
			} else if (value instanceof DataType) {
				DataType[] subTypes = new DataType[] { (DataType) value };
				retVal.put(keyword.id, toMap(subTypes));
			} else {
				retVal.put(keyword.id, value);
			}
		}
		return retVal;
	}

	public static Map<String, Object> extractKeyWords(StreamSRI sri) {
		if (sri == null) {
			return Collections.emptyMap();
		}
		return toMap(sri.keywords);
	}

	public static boolean equals(StreamSRI sri1, StreamSRI sri2) {
		if (sri1 == sri2) {
			return true;
		} else if (sri1 == null || sri2 == null) {
			return false;
		}

		if (sri1.blocking == sri2.blocking && sri1.hversion == sri2.hversion && sri1.mode == sri2.mode && sri1.subsize == sri2.subsize
			&& sri1.xdelta == sri2.xdelta && sri1.xstart == sri2.xstart && sri1.xunits == sri2.xunits && sri1.ydelta == sri2.ydelta
			&& sri1.ystart == sri2.ystart && sri1.yunits == sri2.yunits) {
			if (sri1.keywords == sri2.keywords) {
				return true;
			} else if (sri1.keywords == null || sri2.keywords == null) {
				return false;
			} else if (sri1.keywords.length != sri2.keywords.length) {
				return false;
			}
			Map<String, Object> keywords1 = extractKeyWords(sri1);
			Map<String, Object> keywords2 = extractKeyWords(sri2);
			return equals(keywords1, keywords2);
		}

		return false;
	}

	private static boolean equals(Map< ? , ? > map1, Map< ? , ? > map2) {
		if (map1 == map2) {
			return true;
		} else if (map1 == null || map2 == null) {
			return false;
		} else if (map1.size() != map2.size()) {
			return false;
		}
		Iterator< ? > entries1 = map1.entrySet().iterator();
		while (entries1.hasNext()) {
			Entry< ? , ? > entry1 = (Entry< ? , ? >) entries1.next();
			Object value1 = entry1.getValue();
			Object value2 = map2.get(entry1.getKey());
			if (value1 instanceof Map< ? , ? > && value2 instanceof Map< ? , ? >) {
				if (!equals((Map< ? , ? >) value1, (Map< ? , ? >) value2)) {
					return false;
				}
			} else {
				if (!PluginUtil.equals(value1, value2)) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Useful for debugging info in StreamSRI
	 * @since 2.0
	 */
	public static CharSequence toString(final StreamSRI sri) {
		final StringBuilder sb = new StringBuilder();
		if (sri == null) {
			sb.append(sri);
		} else {
			sb.append(sri.toString());
			sb.append(" hversion=").append(sri.hversion);
			sb.append(" xstart=").append(sri.xstart);
			sb.append(" xdelta=").append(sri.xdelta);
			sb.append(" xunits=").append(sri.xunits);
			sb.append(" subsize=").append(sri.subsize);
			sb.append(" ystart=").append(sri.ystart);
			sb.append(" ydelta=").append(sri.ydelta);
			sb.append(" yunits=").append(sri.yunits);
			sb.append(" mode=").append(sri.mode);
			sb.append(" streamID=").append(sri.streamID);
			sb.append(" blocking=").append(sri.blocking);
			sb.append(" keywords=");
			if (sri.keywords != null) {
				sb.append("[length=").append(sri.keywords.length).append("] {");
				for (CF.DataType cfDataType : sri.keywords) {
					sb.append(cfDataType.id).append('=').append(cfDataType.value).append(',');
				}
			}
		}
		return sb;
	}
}

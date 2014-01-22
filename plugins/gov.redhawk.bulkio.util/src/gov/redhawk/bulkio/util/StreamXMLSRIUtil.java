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

import gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIMetaDataFactory;
import gov.redhawk.bulkio.util.StreamSRIMetaData.StreamSRIModel;
import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.Properties;
import mil.jpeojtrs.sca.prf.PropertyValueType;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleRef;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.Struct;
import mil.jpeojtrs.sca.prf.StructSequence;
import mil.jpeojtrs.sca.prf.StructValue;
import mil.jpeojtrs.sca.prf.Values;
import mil.jpeojtrs.sca.util.AnyUtils;

import org.eclipse.emf.common.util.EList;
import org.omg.CORBA.Any;
import org.omg.CORBA.AnySeqHelper;

import BULKIO.StreamSRI;
import CF.DataType;
import CF.PropertiesHelper;

/**
 * @since 2.0
 */
public final class StreamXMLSRIUtil {
	protected enum type {
		STRUCT_SEQUENCE, STRUCT, SIMPLE_SEQUENCE, SIMPLE
	};

	private StreamXMLSRIUtil() {
	}

	public static StreamSRIModel setStreamSRI(StreamSRI sri, StreamSRIModel metaInfo) {
		metaInfo.setStreamSRI(StreamSRIMetaDataFactory.eINSTANCE.createSRI());

		metaInfo.getStreamSRI().setHversion(sri.hversion);
		metaInfo.getStreamSRI().setXstart(sri.xstart);
		metaInfo.getStreamSRI().setXdelta((sri.xdelta == 0) ? 1.0 : sri.xdelta); // SUPPRESS CHECKSTYLE AvoidInline
		metaInfo.getStreamSRI().setXunits(sri.xunits);
		metaInfo.getStreamSRI().setYstart(sri.ystart);
		metaInfo.getStreamSRI().setYdelta((sri.ydelta == 0) ? 1.0 : sri.ydelta); // SUPPRESS CHECKSTYLE AvoidInline
		metaInfo.getStreamSRI().setYunits(sri.yunits);
		metaInfo.getStreamSRI().setSubsize(sri.subsize);
		metaInfo.getStreamSRI().setMode(sri.mode);
		metaInfo.getStreamSRI().setStreamID(sri.streamID);
		metaInfo.getStreamSRI().setBlocking(sri.blocking);

		Properties props = PrfFactory.eINSTANCE.createProperties();

		if (sri.keywords != null && sri.keywords.length > 0) {
			metaInfo.getStreamSRI().setKeywords(props);
			for (DataType dt : sri.keywords) {
				switch (getPropsType(dt)) {
				case SIMPLE:
					Simple simple = PrfFactory.eINSTANCE.createSimple();
					simple.setId(dt.id);
					simple.setType(getPropsValueType(dt.value));
					simple.setValue(String.valueOf(dt.value));
					props.getSimple().add(simple);
					break;
				case SIMPLE_SEQUENCE:
					SimpleSequence simpleSeq = PrfFactory.eINSTANCE.createSimpleSequence();
					simpleSeq.setId(dt.id);
					simpleSeq.setType(getPropsValueType(dt.value));

					Object anyValue = AnyUtils.convertAny(dt.value);
					Object[] values = (Object[]) anyValue;
					Values value = PrfFactory.eINSTANCE.createValues();
					for (Object o : values) {
						value.getValue().add(String.valueOf(o));
					}
					simpleSeq.setValues(value);
					props.getSimpleSequence().add(simpleSeq);
					break;
				case STRUCT:
					Struct struct = PrfFactory.eINSTANCE.createStruct();
					buildStruct(dt, struct);
					props.getStruct().add(struct);
					break;
				case STRUCT_SEQUENCE:
					StructSequence structSeq = PrfFactory.eINSTANCE.createStructSequence();
					buildStructSeq(dt, structSeq);
					props.getStructSequence().add(structSeq);
					break;
				default:
					break;
				}
			}
		}
		return metaInfo;
	}

	private static PropertyValueType getPropsValueType(Any anyValue) {
		Object temp = AnyUtils.convertAny(anyValue);
		Object value;
		if (temp instanceof Object[]) {
			Object[] values = (Object[]) temp;
			value = values[0];
		} else {
			value = AnyUtils.convertAny(anyValue);
		}
		if (value instanceof Short) {
			return PropertyValueType.SHORT;
		} else if (value instanceof Long) {
			return PropertyValueType.LONGLONG;
		} else if (value instanceof Integer) {
			return PropertyValueType.LONG;
		} else if (value instanceof Character) {
			return PropertyValueType.CHAR;
		} else if (value instanceof String) {
			return PropertyValueType.STRING;
		} else if (value instanceof Boolean) {
			return PropertyValueType.BOOLEAN;
		} else if (value instanceof Double) {
			return PropertyValueType.DOUBLE;
		} else if (value instanceof Float) {
			return PropertyValueType.FLOAT;
		} else {
			return PropertyValueType.OBJREF;
		}
		//		return PropertyValueType.OCTET
		//		return PropertyValueType.ULONG
		//		return PropertyValueType.ULONGLONG_VALUE
		//		return PropertyValueType.USHORT_VALUE
		//		return PropertyValueType.VALUES
	}

	private static type getPropsType(DataType dt) {
		if (AnySeqHelper.type().equal(dt.value.type())) {
			return type.STRUCT_SEQUENCE;
		} else if (PropertiesHelper.type().equal(dt.value.type())) {
			return type.STRUCT;
		} else {
			Object anyValue = AnyUtils.convertAny(dt.value);
			if (anyValue instanceof Object[]) {
				return type.SIMPLE_SEQUENCE;
			} else {
				return type.SIMPLE;
			}
		}
	}

	private static void buildStructSeq(DataType dt, StructSequence structSeq) {
		Struct struct = PrfFactory.eINSTANCE.createStruct();
		struct.setId(dt.id);
		EList<Simple> simpleList = struct.getSimple();

		//List of structs contained in the structSequence
		Any[] elements = AnySeqHelper.extract(dt.value);

		//Check to make sure list contents are of type DataType[]
		if (PropertiesHelper.type().equal(elements[0].type())) {
			//Build the struct template for the structSequence using the first element in the list 
			for (int i = 0; i < 1; i++) {
				DataType[] childElements = PropertiesHelper.extract(elements[0]);
				for (DataType childElement : childElements) {
					Simple simple = PrfFactory.eINSTANCE.createSimple();
					simple.setId(childElement.id);
					simple.setType(getPropsValueType(childElement.value));
					simpleList.add(simple);
				}
				structSeq.setStruct(struct);
			}

			//Display nested simples in format: <structValue><simpleRef refId="id" value="value" /></structValue>
			for (Any element : elements) {
				DataType[] childElements = PropertiesHelper.extract(element);
				StructValue structValue = PrfFactory.eINSTANCE.createStructValue();
				EList<SimpleRef> simpleRefList = structValue.getSimpleRef();
				for (DataType childElement : childElements) {
					SimpleRef simpleRef = PrfFactory.eINSTANCE.createSimpleRef();
					simpleRef.setRefID(childElement.id);
					simpleRef.setValue(String.valueOf(childElement.value));
					simpleRefList.add(simpleRef);
				}
				structSeq.getStructValue().add(structValue);
			}
		}
	}

	private static void buildStruct(DataType dt, Struct struct) {
		EList<Simple> simpleList = struct.getSimple();
		DataType[] simples = PropertiesHelper.extract(dt.value);
		for (DataType simple : simples) {
			Simple simpleValue = PrfFactory.eINSTANCE.createSimple();
			simpleValue.setId(simple.id);
			simpleValue.setType(getPropsValueType(simple.value));
			simpleValue.setValue(String.valueOf(simple.value));
			simpleList.add(simpleValue);
		}
	}

}

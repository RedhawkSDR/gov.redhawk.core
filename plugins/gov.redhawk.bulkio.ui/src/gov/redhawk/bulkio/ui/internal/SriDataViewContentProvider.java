package gov.redhawk.bulkio.ui.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.omg.CORBA.Any;
import org.omg.CORBA.AnySeqHelper;
import org.omg.CORBA.TypeCode;

import BULKIO.StreamSRI;
import CF.DataType;
import CF.PropertiesHelper;

public class SriDataViewContentProvider implements ITreeContentProvider {

	private String activeSriID;

	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}

	public void setActiveSriID(String activeSriID) {
		//Captures active SRI to use in getElements, passed from SRIView as part of the dropdown action
		this.activeSriID = activeSriID;
	}

	@Override
	@NonNull
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof Map< ? , ? >) {
			@SuppressWarnings("unchecked")
			Map<String, SriWrapper> streamMap = (Map<String, SriWrapper>) inputElement;
			//If activeSriID has not been set, grab the first item in the map
			if (activeSriID == null) {
				activeSriID = streamMap.entrySet().iterator().next().getKey();
			}
			SriWrapper sriWrapper = streamMap.get(activeSriID);

			if (sriWrapper == null) {
				activeSriID = streamMap.entrySet().iterator().next().getKey();
				sriWrapper = streamMap.get(activeSriID);
			}

			StreamSRI sri = sriWrapper.getSri();

			List<SriBuilder> attributes = new ArrayList<SriBuilder>();
			if (sriWrapper.isEOS()) {
				attributes.add(new SriBuilder("<End of Stream> streamID: ", sri.streamID, sriWrapper));
			} else {
				attributes.add(new SriBuilder("streamID: ", sri.streamID, sriWrapper));
			}
			attributes.add(new SriBuilder("Most recent push SRI: ", sriWrapper.getDate(), sriWrapper));
			attributes.add(new SriBuilder("Most recent push packet: ", sriWrapper.getPrecisionTime(), sriWrapper));
			attributes.add(new SriBuilder("blocking: ", sri.blocking, sriWrapper));
			attributes.add(new SriBuilder("h version: ", sri.hversion, sriWrapper));
			attributes.add(new SriBuilder("mode: ", sri.mode, sriWrapper));
			attributes.add(new SriBuilder("subsize: ", sri.subsize, sriWrapper));
			attributes.add(new SriBuilder("xdelta: ", sri.xdelta, sriWrapper));
			attributes.add(new SriBuilder("xstart: ", sri.xstart, sriWrapper));
			attributes.add(new SriBuilder("xunits: ", sri.xunits, sriWrapper));
			attributes.add(new SriBuilder("ydelta: ", sri.ydelta, sriWrapper));
			attributes.add(new SriBuilder("ystart: ", sri.ystart, sriWrapper));
			attributes.add(new SriBuilder("yunits: ", sri.yunits, sriWrapper));
			attributes.add(new SriBuilder("keywords: ", sri.keywords, sriWrapper));
			return attributes.toArray();
		}
		return new Object[0];
	}

	@Override
	@NonNull
	public Object[] getChildren(Object parentElement) {
		// parent is Root
		if (parentElement instanceof SriBuilder) {
			SriBuilder attribute = (SriBuilder) parentElement;
			List<SriDataTypeWrapper> children = toWrapper(attribute.getValue(), parentElement);
			return children.toArray();
		}

		// parent is Data Type wrappers of (Simples, Simple Sequences, Structs, Struct Sequences)
		if (parentElement instanceof SriDataTypeWrapper) {
			SriDataTypeWrapper attribute = (SriDataTypeWrapper) parentElement;
			if (attribute.getDataType().value != null) {
				TypeCode anyType = attribute.getDataType().value.type();

				if (AnySeqHelper.type().equal(anyType)) {
					// Struct Sequence return list of structs
					Any[] elements = AnySeqHelper.extract(attribute.getDataType().value);

					List<StructSequenceHelper> helperList = new ArrayList<StructSequenceHelper>();
					int index = 0;

					// Building Structs
					for (Any any : elements) {
						StructSequenceHelper helper = new StructSequenceHelper(attribute.getDataType().id, any, index++, attribute);
						helperList.add(helper);
					}

					return helperList.toArray();
				} else if (PropertiesHelper.type().equal(anyType)) {
					// Structs return list of simples
					return toWrapper(PropertiesHelper.extract(attribute.getDataType().value), attribute).toArray();
				} else {
					// Simples and simple sequences do not have children
					return new Object[0];
				}
			}
		}

		// parent is Struct Sequence
		if (parentElement instanceof StructSequenceHelper) {
			StructSequenceHelper sequence = (StructSequenceHelper) parentElement;

			// Get list of structs
			List<SriDataTypeWrapper> children = toWrapper(sequence.getArray(), parentElement);
			return children.toArray();
		}

		return new Object[0];
	}

	@NonNull
	private List<SriDataTypeWrapper> toWrapper(final Object value, Object parentElement) {
		if (value instanceof DataType[]) {
			final DataType[] keywords = (DataType[]) value;
			final List<SriDataTypeWrapper> result = new ArrayList<SriDataTypeWrapper>();
			for (final DataType dt : keywords) {
				result.add(new SriDataTypeWrapper(dt, parentElement));
			}
			return result;
		}

		return Collections.emptyList();
	}

	@Override
	public Object getParent(Object element) {
		if (element instanceof SriWrapper) {
			return null;
		} else if (element instanceof SriBuilder) {
			return ((SriBuilder) element).getParent();
		} else if (element instanceof SriDataTypeWrapper) {
			return ((SriDataTypeWrapper) element).getParent();
		} else if (element instanceof StructSequenceHelper) {
			return ((StructSequenceHelper) element).getParent();
		}

		return null;
	}

	@Override
	@NonNull
	public boolean hasChildren(Object element) {
		if (element instanceof SriBuilder) {
			SriBuilder attribute = (SriBuilder) element;
			if (attribute.getName().equals("keywords: ")) {
				return true;
			} else {
				return false;
			}
		}

		if (element instanceof SriDataTypeWrapper) {
			SriDataTypeWrapper attribute = (SriDataTypeWrapper) element;
			if (attribute.getDataType().value != null) {
				TypeCode anyType = attribute.getDataType().value.type();
				if (AnySeqHelper.type().equal(anyType) || PropertiesHelper.type().equal(anyType)) {
					return true;
				}
			}
		}
		if (element instanceof StructSequenceHelper) {
			StructSequenceHelper helper = (StructSequenceHelper) element;
			if (helper.isArray()) {
				return true;
			}
		}
		return false;
	}
}

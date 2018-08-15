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
package gov.redhawk.model.sca.tests.stubs;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.util.FeatureMap.ValueListIterator;
import org.omg.CORBA.Any;
import org.omg.PortableServer.Servant;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;
import org.ossie.component.Resource;
import org.ossie.properties.Action;
import org.ossie.properties.BooleanProperty;
import org.ossie.properties.BooleanSequenceProperty;
import org.ossie.properties.CharProperty;
import org.ossie.properties.CharSequenceProperty;
import org.ossie.properties.ComplexBooleanProperty;
import org.ossie.properties.ComplexBooleanSequenceProperty;
import org.ossie.properties.ComplexDoubleProperty;
import org.ossie.properties.ComplexDoubleSequenceProperty;
import org.ossie.properties.ComplexFloatProperty;
import org.ossie.properties.ComplexFloatSequenceProperty;
import org.ossie.properties.ComplexLongLongProperty;
import org.ossie.properties.ComplexLongLongSequenceProperty;
import org.ossie.properties.ComplexLongProperty;
import org.ossie.properties.ComplexLongSequenceProperty;
import org.ossie.properties.ComplexOctetProperty;
import org.ossie.properties.ComplexOctetSequenceProperty;
import org.ossie.properties.ComplexShortProperty;
import org.ossie.properties.ComplexShortSequenceProperty;
import org.ossie.properties.ComplexULongLongProperty;
import org.ossie.properties.ComplexULongLongSequenceProperty;
import org.ossie.properties.ComplexULongProperty;
import org.ossie.properties.ComplexULongSequenceProperty;
import org.ossie.properties.ComplexUShortProperty;
import org.ossie.properties.ComplexUShortSequenceProperty;
import org.ossie.properties.DoubleProperty;
import org.ossie.properties.DoubleSequenceProperty;
import org.ossie.properties.FloatProperty;
import org.ossie.properties.FloatSequenceProperty;
import org.ossie.properties.IProperty;
import org.ossie.properties.Kind;
import org.ossie.properties.LongLongProperty;
import org.ossie.properties.LongLongSequenceProperty;
import org.ossie.properties.LongProperty;
import org.ossie.properties.LongSequenceProperty;
import org.ossie.properties.Mode;
import org.ossie.properties.OctetProperty;
import org.ossie.properties.OctetSequenceProperty;
import org.ossie.properties.ShortProperty;
import org.ossie.properties.ShortSequenceProperty;
import org.ossie.properties.StringProperty;
import org.ossie.properties.StringSequenceProperty;
import org.ossie.properties.StructDef;
import org.ossie.properties.StructProperty;
import org.ossie.properties.StructSequenceProperty;
import org.ossie.properties.ULongLongProperty;
import org.ossie.properties.ULongLongSequenceProperty;
import org.ossie.properties.ULongProperty;
import org.ossie.properties.ULongSequenceProperty;
import org.ossie.properties.UShortProperty;
import org.ossie.properties.UShortSequenceProperty;

import CF.DataType;
import CF.PortOperations;
import CF.PortPOATie;
import CF.complexBoolean;
import CF.complexDouble;
import CF.complexFloat;
import CF.complexLong;
import CF.complexLongLong;
import CF.complexOctet;
import CF.complexShort;
import CF.complexULong;
import CF.complexULongLong;
import CF.complexUShort;
import CF.LifeCyclePackage.InitializeError;
import CF.PortPackage.InvalidPort;
import CF.PortPackage.OccupiedPort;
import ExtendedCF.ConnectionStatus;
import ExtendedCF.NegotiablePortOperations;
import ExtendedCF.NegotiableProvidesPortOperations;
import ExtendedCF.NegotiableProvidesPortPOATie;
import ExtendedCF.NegotiableUsesPortOperations;
import ExtendedCF.NegotiableUsesPortPOATie;
import ExtendedCF.NegotiationError;
import ExtendedCF.NegotiationResult;
import ExtendedCF.TransportInfo;
import ExtendedCF.UsesConnection;
import gov.redhawk.model.sca.tests.TestEnvirornment;
import gov.redhawk.sca.util.OrbSession;
import mil.jpeojtrs.sca.prf.AbstractProperty;
import mil.jpeojtrs.sca.prf.ConfigurationKind;
import mil.jpeojtrs.sca.prf.Properties;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleRef;
import mil.jpeojtrs.sca.prf.SimpleSequence;
import mil.jpeojtrs.sca.prf.Struct;
import mil.jpeojtrs.sca.prf.StructSequence;
import mil.jpeojtrs.sca.prf.StructValue;
import mil.jpeojtrs.sca.scd.Ports;
import mil.jpeojtrs.sca.scd.Provides;
import mil.jpeojtrs.sca.scd.Uses;
import mil.jpeojtrs.sca.spd.SoftPkg;
import mil.jpeojtrs.sca.util.AnyUtils;
import mil.jpeojtrs.sca.util.CFErrorFormatter;
import mil.jpeojtrs.sca.util.math.ComplexBoolean;
import mil.jpeojtrs.sca.util.math.ComplexDouble;
import mil.jpeojtrs.sca.util.math.ComplexFloat;
import mil.jpeojtrs.sca.util.math.ComplexLong;
import mil.jpeojtrs.sca.util.math.ComplexLongLong;
import mil.jpeojtrs.sca.util.math.ComplexOctet;
import mil.jpeojtrs.sca.util.math.ComplexShort;
import mil.jpeojtrs.sca.util.math.ComplexULong;
import mil.jpeojtrs.sca.util.math.ComplexULongLong;
import mil.jpeojtrs.sca.util.math.ComplexUShort;

public class AbstractResourceImpl extends Resource {

	protected SoftPkg spd; // SUPPRESS CHECKSTYLE Protected Field
	private OrbSession session;

	public AbstractResourceImpl() {
		super();
	}

	public AbstractResourceImpl(String compId, String compName, String profile, OrbSession session) throws ServantNotActive, WrongPolicy, CoreException {
		setup(compId, compName, profile, session.getOrb(), session.getPOA());
		this.session = session;
	}

	public OrbSession getSession() {
		return session;
	}

	public void init(SoftPkg spd) {
		this.spd = spd;
		Properties prf = spd.getPropertyFile().getProperties();

		this.softwareProfile = spd.eResource().getURI().path();
		if (this.compId == null) {
			this.compId = spd.getId();
		}
		if (this.compName == null) {
			this.compName = spd.getName();
		}

		initProperties(prf);
		initPorts(spd.getDescriptor().getComponent().getComponentFeatures().getPorts());

		try {
			initialize();
		} catch (InitializeError e) {
			TestEnvirornment.log(IStatus.ERROR, CFErrorFormatter.format(e, spd.getName()), e);
		}
	}

	private class AbstractNegotiableProvidesPort extends AbstractNegotiablePort implements NegotiableProvidesPortOperations {

		@Override
		public NegotiationResult negotiateTransport(String transportType, DataType[] transportProperties) throws NegotiationError {
			return null;
		}

		@Override
		public void disconnectTransport(String transportId) throws NegotiationError {
		}

	}

	private class AbstractNegotiableUsesPort extends AbstractNegotiablePort implements NegotiableUsesPortOperations {

		@Override
		public UsesConnection[] connections() {
			List<UsesConnection> retVal = new ArrayList<>();
			for (String connectionID : getConnections().keySet()) {
				retVal.add(new UsesConnection(connectionID, getConnections().get(connectionID)));
			}
			return retVal.toArray(new UsesConnection[retVal.size()]);
		}

		@Override
		public ConnectionStatus[] connectionStatus() {
			List<ConnectionStatus> retVal = new ArrayList<>();
			for (String connectionID : getConnections().keySet()) {
				retVal.add(new ConnectionStatus(connectionID, getConnections().get(connectionID), true, "corba", new DataType[] {}));
			}
			return retVal.toArray(new ConnectionStatus[retVal.size()]);
		}

	}

	private class AbstractNegotiablePort extends AbstractPort implements NegotiablePortOperations {

		@Override
		public TransportInfo[] supportedTransports() {
			String progLang = spd.getImplementation().get(0).getProgrammingLanguage().getName();
			if (progLang.toLowerCase().equals("c++")) {
				Any any = AnyUtils.toAny(123, "long", false);
				DataType simpleProp = new DataType("simpleProp", any);
				return new TransportInfo[] { new TransportInfo("shmipc", new DataType[] { simpleProp }) };
			} else {
				return new TransportInfo[] { };
			}
		}

	}

	private class AbstractPort implements PortOperations {

		private Map<String, org.omg.CORBA.Object> connections = new HashMap<>();

		protected Map<String, org.omg.CORBA.Object> getConnections() {
			return connections;
		}

		@Override
		public void connectPort(org.omg.CORBA.Object connection, String connectionId) throws InvalidPort, OccupiedPort {
			connections.put(connectionId, connection);
		}

		@Override
		public void disconnectPort(String connectionId) throws InvalidPort {
			if (connections.remove(connectionId) == null) {
				throw new InvalidPort((short) 0, "Invalid connection ID");
			}
		}

	}

	protected void initPorts(Ports ports) {
		for (Provides out : ports.getProvides()) {
			Servant servant;
			if (out.getRepID().startsWith("IDL:BULKIO/data")) {
				servant = new NegotiableProvidesPortPOATie(new AbstractNegotiableProvidesPort());
			} else {
				servant = new PortPOATie(new AbstractPort());
			}
			addPort(out.getName(), servant);
		}

		for (Uses in : ports.getUses()) {
			Servant servant;
			if (in.getRepID().startsWith("IDL:BULKIO/data")) {
				servant = new NegotiableUsesPortPOATie(new AbstractNegotiableUsesPort());
			} else {
				servant = new PortPOATie(new AbstractPort());
			}
			addPort(in.getName(), servant);
		}
	}

	@SuppressWarnings("unchecked")
	protected void initProperties(Properties prf) {
		for (ValueListIterator<Object> iterator = prf.getProperties().valueListIterator(); iterator.hasNext();) {
			AbstractProperty prop = (AbstractProperty) iterator.next();
			if (prop instanceof Simple) {
				Simple simple = (Simple) prop;

				IProperty newProp = createSimpleProperty(simple);
				if (newProp != null) {
					addProperty(newProp);
				}
			} else if (prop instanceof SimpleSequence) {
				SimpleSequence sequence = (SimpleSequence) prop;

				IProperty newProperty = createSimpleSequenceProperty(sequence);
				if (newProperty != null) {
					addProperty(newProperty);
				}
			} else if (prop instanceof Struct) {
				Struct struct = (Struct) prop;

				List<String> kinds = new ArrayList<>(struct.getConfigurationKind().size());
				for (ConfigurationKind k : struct.getConfigurationKind()) {
					kinds.add(k.getType().toString());
				}

				StructDef structDef = createStructDef(struct);
				StructProperty<StructDef> newProperty = new StructProperty<>(struct.getId(), struct.getName(), structDef, structDef,
					struct.getMode().toString(), kinds.toArray(new String[kinds.size()]));
				addProperty(newProperty);
			} else if (prop instanceof StructSequence) {
				StructSequence structSequence = (StructSequence) prop;

				List<String> kindsList = new ArrayList<>(structSequence.getConfigurationKind().size());
				for (ConfigurationKind k : structSequence.getConfigurationKind()) {
					kindsList.add(k.getType().toString());
				}
				Class<StructDef> structClass = (Class<StructDef>) createStructDef(structSequence.getStruct()).getClass();

				List<StructDef> value = new ArrayList<>();
				if (structSequence.getStructValue() != null) {
					for (StructValue v : structSequence.getStructValue()) {
						StructDef newValue = createStructDef(v.getStruct());
						for (SimpleRef ref : v.getSimpleRef()) {
							newValue.getElement(ref.getRefID()).fromString(ref.getValue());
						}
						value.add(newValue);
					}
				}

				Mode mode = Mode.get(structSequence.getMode().getLiteral());
				Kind[] kinds = Kind.get(kindsList.toArray(new String[kindsList.size()]));
				StructSequenceProperty<StructDef> newProperty = new StructSequenceProperty<>(structSequence.getId(), structSequence.getName(), structClass,
					value, mode, kinds);
				addProperty(newProperty);
			}
		}

	}

	private IProperty createSimpleSequenceProperty(SimpleSequence sequence) {
		String id = sequence.getId();
		String name = sequence.getName();
		Mode mode = Mode.get(sequence.getMode().getLiteral());
		Action action = Action.get(sequence.getAction().getType().getLiteral());
		Kind[] kinds = new Kind[sequence.getKind().size()];
		for (int i = 0; i < sequence.getKind().size(); i++) {
			kinds[i] = Kind.get(sequence.getKind().get(i).getType().getLiteral());
		}
		String type = sequence.getType().toString();

		if (sequence.isComplex()) {
			switch (type) {
			case "boolean":
				List<complexBoolean> booleanList = createValueList(sequence, type, complexBoolean.class);
				return new ComplexBooleanSequenceProperty(id, name, booleanList, mode, action, kinds);
			case "double":
				List<complexDouble> doubleList = createValueList(sequence, type, complexDouble.class);
				return new ComplexDoubleSequenceProperty(id, name, doubleList, mode, action, kinds);
			case "float":
				List<complexFloat> floatList = createValueList(sequence, type, complexFloat.class);
				return new ComplexFloatSequenceProperty(id, name, floatList, mode, action, kinds);
			case "long":
				List<complexLong> longList = createValueList(sequence, type, complexLong.class);
				return new ComplexLongSequenceProperty(id, name, longList, mode, action, kinds);
			case "longlong":
				List<complexLongLong> longLongList = createValueList(sequence, type, complexLongLong.class);
				return new ComplexLongLongSequenceProperty(id, name, longLongList, mode, action, kinds);
			case "octet":
				List<complexOctet> octetList = createValueList(sequence, type, complexOctet.class);
				return new ComplexOctetSequenceProperty(id, name, octetList, mode, action, kinds);
			case "short":
				List<complexShort> shortList = createValueList(sequence, type, complexShort.class);
				return new ComplexShortSequenceProperty(id, name, shortList, mode, action, kinds);
			case "ulong":
				List<complexULong> uLongList = createValueList(sequence, type, complexULong.class);
				return new ComplexULongSequenceProperty(id, name, uLongList, mode, action, kinds);
			case "ulonglong":
				List<complexULongLong> uLongLongList = createValueList(sequence, type, complexULongLong.class);
				return new ComplexULongLongSequenceProperty(id, name, uLongLongList, mode, action, kinds);
			case "ushort":
				List<complexUShort> uShortList = createValueList(sequence, type, complexUShort.class);
				return new ComplexUShortSequenceProperty(id, name, uShortList, mode, action, kinds);
			default:
				throw new IllegalArgumentException("Test harness doesn't have support for type: " + type);
			}
		} else {
			switch (type) {
			case "boolean":
				List<Boolean> booleanValue = createValueList(sequence, type, Boolean.class);
				return new BooleanSequenceProperty(id, name, booleanValue, mode, action, kinds);
			case "char":
				List<Character> characterValue = createValueList(sequence, type, Character.class);
				return new CharSequenceProperty(id, name, characterValue, mode, action, kinds);
			case "double":
				List<Double> doubleValue = createValueList(sequence, type, Double.class);
				return new DoubleSequenceProperty(id, name, doubleValue, mode, action, kinds);
			case "float":
				List<Float> floatValue = createValueList(sequence, type, Float.class);
				return new FloatSequenceProperty(id, name, floatValue, mode, action, kinds);
			case "long":
				List<Integer> longValue = createValueList(sequence, type, Integer.class);
				return new LongSequenceProperty(id, name, longValue, mode, action, kinds);
			case "longlong":
				List<Long> longLongValue = createValueList(sequence, type, Long.class);
				return new LongLongSequenceProperty(id, name, longLongValue, mode, action, kinds);
			case "octet":
				List<Short> octetRealValue = createValueList(sequence, type, Short.class);
				List<Byte> octetValue = new ArrayList<Byte>();
				for (Short s : octetRealValue) {
					octetValue.add(s.byteValue());
				}
				return new OctetSequenceProperty(id, name, octetValue, mode, action, kinds);
			case "short":
				List<Short> shortValue = createValueList(sequence, type, Short.class);
				return new ShortSequenceProperty(id, name, shortValue, mode, action, kinds);
			case "string":
				List<String> stringValue = createValueList(sequence, type, String.class);
				return new StringSequenceProperty(id, name, stringValue, mode, action, kinds);
			case "ulong":
				List<Long> uLongRealValue = createValueList(sequence, type, Long.class);
				List<Integer> uLongValue = new ArrayList<Integer>();
				for (Long l : uLongRealValue) {
					uLongValue.add(l.intValue());
				}
				return new ULongSequenceProperty(id, name, uLongValue, mode, action, kinds);
			case "ulonglong":
				List<BigInteger> uLongLongRealValue = createValueList(sequence, type, BigInteger.class);
				List<Long> uLongLongValue = new ArrayList<>();
				for (BigInteger bi : uLongLongRealValue) {
					uLongLongValue.add(bi.longValue());
				}
				return new ULongLongSequenceProperty(id, name, uLongLongValue, mode, action, kinds);
			case "ushort":
				List<Integer> uShortRealValue = createValueList(sequence, type, Integer.class);
				List<Short> uShortValue = new ArrayList<Short>();
				for (Integer i : uShortRealValue) {
					uShortValue.add(i.shortValue());
				}
				return new UShortSequenceProperty(id, name, uShortValue, mode, action, kinds);
			default:
				throw new IllegalArgumentException("Test harness doesn't have support for type: " + type);
			}
		}
	}

	private < T > List<T> createValueList(SimpleSequence sequence, String type, Class<T> valueType) {
		List<T> values = new ArrayList<>();
		if (sequence.getValues() != null) {
			for (String v : sequence.getValues().getValue()) {
				Object value = AnyUtils.convertString(v, type, sequence.isComplex());
				if (sequence.isComplex()) {
					switch (type) {
					case "boolean":
						value = ((ComplexBoolean) value).toCFType();
						break;
					case "double":
						value = ((ComplexDouble) value).toCFType();
						break;
					case "float":
						value = ((ComplexFloat) value).toCFType();
						break;
					case "long":
						value = ((ComplexLong) value).toCFType();
						break;
					case "longlong":
						value = ((ComplexLongLong) value).toCFType();
						break;
					case "octet":
						value = ((ComplexOctet) value).toCFType();
						break;
					case "short":
						value = ((ComplexShort) value).toCFType();
						break;
					case "ulong":
						value = ((ComplexULong) value).toCFType();
						break;
					case "ulonglong":
						value = ((ComplexULongLong) value).toCFType();
						break;
					case "ushort":
						value = ((ComplexUShort) value).toCFType();
						break;
					default:
						throw new IllegalArgumentException("Test harness doesn't have support for type: " + type);
					}
				}
				values.add(valueType.cast(value));
			}
		}
		return values;
	}

	private IProperty createSimpleProperty(Simple simple) {
		String id = simple.getId();
		String name = simple.getName();
		Object value = AnyUtils.convertString(simple.getValue(), simple.getType().toString(), simple.isComplex());
		Mode mode = Mode.get(simple.getMode().getLiteral());
		Action action = (simple.getAction() == null) ? null : Action.get(simple.getAction().getType().getLiteral());
		Kind[] kinds = new Kind[simple.getKind().size()];
		for (int i = 0; i < simple.getKind().size(); i++) {
			kinds[i] = Kind.get(simple.getKind().get(i).getType().getLiteral());
		}
		String type = simple.getType().toString();

		if (simple.isComplex()) {
			switch (type) {
			case "boolean":
				return new ComplexBooleanProperty(id, name, ((ComplexBoolean) value).toCFType(), mode, action, kinds);
			case "double":
				return new ComplexDoubleProperty(id, name, ((ComplexDouble) value).toCFType(), mode, action, kinds);
			case "float":
				return new ComplexFloatProperty(id, name, ((ComplexFloat) value).toCFType(), mode, action, kinds);
			case "longlong":
				return new ComplexLongLongProperty(id, name, ((ComplexLongLong) value).toCFType(), mode, action, kinds);
			case "long":
				return new ComplexLongProperty(id, name, ((ComplexLong) value).toCFType(), mode, action, kinds);
			case "octet":
				return new ComplexOctetProperty(id, name, ((ComplexOctet) value).toCFType(), mode, action, kinds);
			case "short":
				return new ComplexShortProperty(id, name, ((ComplexShort) value).toCFType(), mode, action, kinds);
			case "ulonglong":
				return new ComplexULongLongProperty(id, name, ((ComplexULongLong) value).toCFType(), mode, action, kinds);
			case "ulong":
				return new ComplexULongProperty(id, name, ((ComplexULong) value).toCFType(), mode, action, kinds);
			case "ushort":
				return new ComplexUShortProperty(id, name, ((ComplexUShort) value).toCFType(), mode, action, kinds);
			default:
				throw new IllegalArgumentException("Test harness doesn't have support for type: " + type);
			}
		} else {
			switch (type) {
			case "boolean":
				return new BooleanProperty(id, name, (Boolean) value, mode, action, kinds);
			case "char":
				return new CharProperty(id, name, (Character) value, mode, action, kinds);
			case "double":
				return new DoubleProperty(id, name, (Double) value, mode, action, kinds);
			case "float":
				return new FloatProperty(id, name, (Float) value, mode, action, kinds);
			case "longlong":
				return new LongLongProperty(id, name, (Long) value, mode, action, kinds);
			case "long":
				return new LongProperty(id, name, (Integer) value, mode, action, kinds);
			case "octet":
				if (value != null) {
					value = ((Short) value).byteValue();
				}
				return new OctetProperty(id, name, (Byte) value, mode, action, kinds);
			case "short":
				return new ShortProperty(id, name, (Short) value, mode, action, kinds);
			case "string":
				return new StringProperty(id, name, (String) value, mode, action, kinds);
			case "ulonglong":
				if (value != null) {
					value = ((BigInteger) value).longValue();
				}
				return new ULongLongProperty(id, name, (Long) value, mode, action, kinds);
			case "ulong":
				if (value != null) {
					value = ((Long) value).intValue();
				}
				return new ULongProperty(id, name, (Integer) value, mode, action, kinds);
			case "ushort":
				if (value != null) {
					value = ((Integer) value).shortValue();
				}
				return new UShortProperty(id, name, (Short) value, mode, action, kinds);
			default:
				throw new IllegalArgumentException("Test harness doesn't have support for type: " + type);
			}
		}
	}

	public static class DynamicStuctDef extends StructDef {
		@Override
		public void addElement(IProperty element) {
			super.addElement(element);
		}
	}

	private StructDef createStructDef(Struct struct) {
		DynamicStuctDef retVal = new DynamicStuctDef();
		for (Simple simple : struct.getSimple()) {
			IProperty prop = createSimpleProperty(simple);
			if (prop != null) {
				retVal.addElement(prop);
			}
		}

		return retVal;
	}

	@Override
	public void run() {
		// Do Nothing

	}

}

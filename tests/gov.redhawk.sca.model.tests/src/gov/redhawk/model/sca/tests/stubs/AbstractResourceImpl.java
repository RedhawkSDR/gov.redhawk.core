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
import java.util.List;

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

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.util.FeatureMap.ValueListIterator;
import org.omg.CORBA.ORB;
import org.omg.CORBA.TCKind;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;
import org.osgi.framework.FrameworkUtil;
import org.ossie.component.Resource;
import org.ossie.properties.Action;
import org.ossie.properties.BooleanProperty;
import org.ossie.properties.BooleanSequenceProperty;
import org.ossie.properties.CharProperty;
import org.ossie.properties.CharSequenceProperty;
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

import CF.PortOperations;
import CF.PortPOATie;
import CF.LifeCyclePackage.InitializeError;
import CF.PortPackage.InvalidPort;
import CF.PortPackage.OccupiedPort;

public class AbstractResourceImpl extends Resource {

	protected SoftPkg spd; // SUPPRESS CHECKSTYLE Protected Field
	private static final String PLUGIN_ID = "gov.redhawk.sca.model.tests";
	private ILog log;

	public AbstractResourceImpl() {
		super();
		log = Platform.getLog(FrameworkUtil.getBundle(getClass()));
	}

	public AbstractResourceImpl(String compId, String compName, ORB orb, POA poa) throws ServantNotActive, WrongPolicy {
		super(compId, compName, orb, poa);
		log = Platform.getLog(FrameworkUtil.getBundle(getClass()));
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
			log.log(new Status(IStatus.ERROR, PLUGIN_ID, CFErrorFormatter.format(e, spd.getName())));
		}
	}

	private static class AbstractPort implements PortOperations {

		@Override
		public void connectPort(org.omg.CORBA.Object connection, String connectionId) throws InvalidPort, OccupiedPort {
			// TODO Auto-generated method stub

		}

		@Override
		public void disconnectPort(String connectionId) throws InvalidPort {
			// TODO Auto-generated method stub

		}

	}

	protected void initPorts(Ports ports) {
		for (Provides out : ports.getProvides()) {
			PortPOATie tie = new PortPOATie(new AbstractPort());
			addPort(out.getName(), tie);
		}

		for (Uses in : ports.getUses()) {
			PortPOATie tie = new PortPOATie(new AbstractPort());
			addPort(in.getName(), tie);
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

				List<String> kinds = new ArrayList<String>(struct.getConfigurationKind().size());
				for (ConfigurationKind k : struct.getConfigurationKind()) {
					kinds.add(k.getType().toString());
				}

				StructDef structDef = createStructDef(struct);
				StructProperty<StructDef> newProperty = new StructProperty<StructDef>(struct.getId(),
						struct.getName(),
						structDef,
						structDef,
						struct.getMode().toString(),
						kinds.toArray(new String[kinds.size()]));
				addProperty(newProperty);
			} else if (prop instanceof StructSequence) {
				StructSequence structSequence = (StructSequence) prop;

				List<String> kindsList = new ArrayList<String>(structSequence.getConfigurationKind().size());
				for (ConfigurationKind k : structSequence.getConfigurationKind()) {
					kindsList.add(k.getType().toString());
				}
				Class< StructDef> structClass = (Class<StructDef>) createStructDef(structSequence.getStruct()).getClass();

				List<StructDef> value = new ArrayList<StructDef>();
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
				StructSequenceProperty<StructDef> newProperty = new StructSequenceProperty<StructDef>(structSequence.getId(),
						structSequence.getName(),
						structClass,
						value,
						mode,
						kinds);
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

		switch (AnyUtils.convertToTCKind(type).value()) {
		case TCKind._tk_boolean:
			List<Boolean> booleanValue = createValueList(sequence, type, Boolean.class);
			return new BooleanSequenceProperty(id, name, booleanValue, mode, action, kinds);
		case TCKind._tk_char:
			List<Character> characterValue = createValueList(sequence, type, Character.class);
			return new CharSequenceProperty(id, name, characterValue, mode, action, kinds);
		case TCKind._tk_double:
			List<Double> doubleValue = createValueList(sequence, type, Double.class);
			return new DoubleSequenceProperty(id, name, doubleValue, mode, action, kinds);
		case TCKind._tk_float:
			List<Float> floatValue = createValueList(sequence, type, Float.class);
			return new FloatSequenceProperty(id, name, floatValue, mode, action, kinds);
		case TCKind._tk_longlong:
			List<Long> longLongValue = createValueList(sequence, type, Long.class);
			return new LongLongSequenceProperty(id, name, longLongValue, mode, action, kinds);
		case TCKind._tk_long:
			List<Integer> longValue = createValueList(sequence, type, Integer.class);
			return new LongSequenceProperty(id, name, longValue, mode, action, kinds);
		case TCKind._tk_octet:
			List<Short> octetRealValue = createValueList(sequence, type, Short.class);
			List<Byte> octetValue = new ArrayList<Byte>();
			for (Short s : octetRealValue) {
				octetValue.add(s.byteValue());
			}
			return new OctetSequenceProperty(id, name, octetValue, mode, action, kinds);
		case TCKind._tk_short:
			List<Short> shortValue = createValueList(sequence, type, Short.class);
			return new ShortSequenceProperty(id, name, shortValue, mode, action, kinds);
		case TCKind._tk_string:
			List<String> stringValue = createValueList(sequence, type, String.class);
			return new StringSequenceProperty(id, name, stringValue, mode, action, kinds);
		case TCKind._tk_ulonglong:
			List<BigInteger> uLongLongRealValue = createValueList(sequence, type, BigInteger.class);
			List<Long> uLongLongValue = createValueList(sequence, type, Long.class);
			for (BigInteger bi : uLongLongRealValue) {
				uLongLongValue.add(bi.longValue());
			}
			return new ULongLongSequenceProperty(id, name, uLongLongValue, mode, action, kinds);
		case TCKind._tk_ulong:
			List<Long> uLongRealValue = createValueList(sequence, type, Long.class);
			List<Integer> uLongValue = new ArrayList<Integer>();
			for (Long l : uLongRealValue) {
				uLongValue.add(l.intValue());
			}
			return new ULongSequenceProperty(id, name, uLongValue, mode, action, kinds);
		case TCKind._tk_ushort:
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

	private < T > List<T> createValueList(SimpleSequence sequence, String type, Class<T> valueType) {
		List<T> value = new ArrayList<T>();
		if (sequence.getValues() != null) {
			for (String v : sequence.getValues().getValue()) {
				value.add(valueType.cast(AnyUtils.convertString(v, type, sequence.isComplex())));
			}
		}
		return value;
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

		switch (AnyUtils.convertToTCKind(type).value()) {
		case TCKind._tk_boolean:
			return new BooleanProperty(id, name, (Boolean) value, mode, action, kinds);
		case TCKind._tk_char:
			return new CharProperty(id, name, (Character) value, mode, action, kinds);
		case TCKind._tk_double:
			return new DoubleProperty(id, name, (Double) value, mode, action, kinds);
		case TCKind._tk_float:
			return new FloatProperty(id, name, (Float) value, mode, action, kinds);
		case TCKind._tk_longlong:
			return new LongLongProperty(id, name, (Long) value, mode, action, kinds);
		case TCKind._tk_long:
			return new LongProperty(id, name, (Integer) value, mode, action, kinds);
		case TCKind._tk_octet:
			if (value != null) {
				value = ((Short) value).byteValue();
			}
			return new OctetProperty(id, name, (Byte) value, mode, action, kinds);
		case TCKind._tk_short:
			return new ShortProperty(id, name, (Short) value, mode, action, kinds);
		case TCKind._tk_string:
			return new StringProperty(id, name, (String) value, mode, action, kinds);
		case TCKind._tk_ulonglong:
			if (value != null) {
				value = ((BigInteger) value).longValue();
			}
			return new ULongLongProperty(id, name, (Long) value, mode, action, kinds);
		case TCKind._tk_ulong:
			if (value != null) {
				value = ((Long) value).intValue();
			}
			return new ULongProperty(id, name, (Integer) value, mode, action, kinds);
		case TCKind._tk_ushort:
			if (value != null) {
				value = ((Integer) value).shortValue();
			}
			return new UShortProperty(id, name, (Short) value, mode, action, kinds);
		default:
			throw new IllegalArgumentException("Test harness doesn't have support for type: " + type);
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

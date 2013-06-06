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

import java.util.ArrayList;
import java.util.List;

import mil.jpeojtrs.sca.prf.AbstractProperty;
import mil.jpeojtrs.sca.prf.ConfigurationKind;
import mil.jpeojtrs.sca.prf.Kind;
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

import org.eclipse.emf.ecore.util.FeatureMap.ValueListIterator;
import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAPackage.ServantNotActive;
import org.omg.PortableServer.POAPackage.WrongPolicy;
import org.ossie.properties.IProperty;
import org.ossie.properties.SimpleProperty;
import org.ossie.properties.SimpleSequenceProperty;
import org.ossie.properties.StructDef;
import org.ossie.properties.StructProperty;
import org.ossie.properties.StructSequenceProperty;

import CF.PortOperations;
import CF.PortPOATie;
import CF.LifeCyclePackage.InitializeError;
import CF.PortPackage.InvalidPort;
import CF.PortPackage.OccupiedPort;

public class AbstractResourceImpl extends Resource {

	protected SoftPkg spd;

	public AbstractResourceImpl() {
		super();
	}

	public AbstractResourceImpl(String compId, String compName, ORB orb, POA poa) throws ServantNotActive, WrongPolicy {
		super(compId, compName, orb, poa);
	}

	public void init(SoftPkg spd) {
		this.spd = spd;
		Properties prf = spd.getPropertyFile().getProperties();
		if (this.compId == null) {
			this.compId = spd.getId();
		}

		if (this.compName == null) {
			this.compName = spd.getName();
		}
		initProperties(prf);
		try {
			initPorts(spd.getDescriptor().getComponent().getComponentFeatures().getPorts());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); // CHECKSTYLE: DEBUG CODE
		}
		try {
			initialize();
		} catch (InitializeError e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); // CHECKSTYLE: DEBUG CODE
		}
	}

	private static class AbstractPort implements PortOperations {

		public void connectPort(org.omg.CORBA.Object connection, String connectionId) throws InvalidPort, OccupiedPort {
			// TODO Auto-generated method stub

		}

		public void disconnectPort(String connectionId) throws InvalidPort {
			// TODO Auto-generated method stub

		}

	}

	protected void initPorts(Ports ports) throws Exception {
		for (Provides out : ports.getProvides()) {
			PortPOATie tie = new PortPOATie(new AbstractPort());
			addPort(out.getName(), tie);
		}

		for (Uses in : ports.getUses()) {
			PortPOATie tie = new PortPOATie(new AbstractPort());
			addPort(in.getName(), tie);
		}
	}

	@SuppressWarnings("rawtypes")
	protected void initProperties(Properties prf) {
		for (ValueListIterator<Object> iterator = prf.getProperties().valueListIterator(); iterator.hasNext();) {
			AbstractProperty prop = (AbstractProperty) iterator.next();
			if (prop instanceof Simple) {
				Simple simple = (Simple) prop;

				SimpleProperty< ? > newProp = createSimpleProperty(simple);
				if (newProp != null) {
					addProperty(newProp);
				}
			} else if (prop instanceof SimpleSequence) {
				SimpleSequence sequence = (SimpleSequence) prop;

				SimpleSequenceProperty< ? > newProperty = createSimpleSequenceProperty(sequence);
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

				List<String> kinds = new ArrayList<String>(structSequence.getConfigurationKind().size());
				for (ConfigurationKind k : structSequence.getConfigurationKind()) {
					kinds.add(k.getType().toString());
				}
				Class structClass = createStructDef(structSequence.getStruct()).getClass();

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

				StructSequenceProperty<StructDef> newProperty = new StructSequenceProperty<StructDef>(structSequence.getId(),
						structSequence.getName(),
						structClass,
						value,
						structSequence.getMode().toString(),
						kinds.toArray(new String[kinds.size()]));
				addProperty(newProperty);
			}
		}

	}

	private SimpleSequenceProperty< ? > createSimpleSequenceProperty(SimpleSequence sequence) {
		List<Object> value = new ArrayList<Object>();
		String type = sequence.getType().toString();
		if (type.equals("objref")) {
			return null; // TODO AnyUtil doesn't support objref type
		}
		if (sequence.getValues() != null) {
			for (String v : sequence.getValues().getValue()) {
				value.add(AnyUtils.convertString(v, type));
			}
		}
		List<String> kinds = new ArrayList<String>(sequence.getKind().size());
		for (Kind k : sequence.getKind()) {
			kinds.add(k.getType().toString());
		}

		return new SimpleSequenceProperty<Object>(sequence.getId(),
				sequence.getName(),
				type,
				value,
				sequence.getMode().toString(),
				sequence.getAction().toString(),
				kinds.toArray(new String[kinds.size()]));
	}

	private SimpleProperty< ? > createSimpleProperty(Simple simple) {
		Object value = AnyUtils.convertString(simple.getValue(), simple.getType().toString());
		List<String> kinds = new ArrayList<String>(simple.getKind().size());
		String type = simple.getType().toString();
		if (type.equals("objref")) {
			return null; // TODO AnyUtil doesn't support objref type
		}
		for (Kind k : simple.getKind()) {
			kinds.add(k.getType().toString());
		}
		return new SimpleProperty<Object>(simple.getId(),
				simple.getName(),
				type,
				value,
				simple.getMode().toString(),
				simple.getAction().toString(),
				kinds.toArray(new String[kinds.size()]));
	}

	private static class DynamicStuctDef extends StructDef {
		@Override
		public void addElement(IProperty element) {
			super.addElement(element);
		}
	}

	private StructDef createStructDef(Struct struct) {
		DynamicStuctDef retVal = new DynamicStuctDef();
		for (Simple simple : struct.getSimple()) {
			SimpleProperty< ? > prop = createSimpleProperty(simple);
			if (prop != null) {
				retVal.addElement(prop);
			}
		}

		return retVal;
	}

	public void run() {
		// Do Nothing

	}

}

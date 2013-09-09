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
package gov.redhawk.diagram.sheet.properties;

import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaSimpleProperty;
import gov.redhawk.model.sca.ScaSimpleSequenceProperty;
import gov.redhawk.model.sca.ScaStructProperty;
import gov.redhawk.model.sca.ScaStructSequenceProperty;
import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;
import mil.jpeojtrs.sca.partitioning.ComponentProperties;
import mil.jpeojtrs.sca.partitioning.PartitioningFactory;
import mil.jpeojtrs.sca.partitioning.PartitioningPackage;
import mil.jpeojtrs.sca.prf.AbstractPropertyRef;
import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.SimpleRef;
import mil.jpeojtrs.sca.prf.SimpleSequenceRef;
import mil.jpeojtrs.sca.prf.StructRef;
import mil.jpeojtrs.sca.prf.StructSequenceRef;
import mil.jpeojtrs.sca.prf.StructValue;
import mil.jpeojtrs.sca.prf.Values;
import mil.jpeojtrs.sca.spd.SoftPkg;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMap.ValueListIterator;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.jface.viewers.TreeViewer;

/**
 * @since 5.0
 */
public class ComponentInstantiationPropertyViewerAdapter {
	private ComponentInstantiation input = null;
	private final ScaComponent component = ScaFactory.eINSTANCE.createScaComponent();
	private TreeViewer viewer;
	private final IEditingDomainProvider editingDomainProvider;
	private final Adapter contentAdapter = new EContentAdapter() {
		@Override
		public void notifyChanged(final org.eclipse.emf.common.notify.Notification notification) {
			super.notifyChanged(notification);
			if (!notification.isTouch() && !ignore) {
				mergeValues();
			}
		}
	};
	private boolean ignore = false;

	public ComponentInstantiationPropertyViewerAdapter(final IEditingDomainProvider provider) {
		editingDomainProvider = provider;
		component.eAdapters().add(contentAdapter);
	}

	public void setViewer(final TreeViewer viewer) {
		this.viewer = viewer;
		if (this.viewer != null) {
			this.viewer.setInput(component);
		}
	}
	
	/**
	 * @since 6.0
	 */
	public TreeViewer getViewer() {
		return viewer;
	}

	private void mergeValues() {
		ComponentProperties cp = PartitioningFactory.eINSTANCE.createComponentProperties();
		for (final ScaAbstractProperty< ? > prop : component.getProperties()) {
			if (!prop.isDefaultValue()) {
				if (prop instanceof ScaSimpleProperty) {
					cp.getSimpleRef().add(createRef((ScaSimpleProperty) prop));
				} else if (prop instanceof ScaSimpleSequenceProperty) {
					cp.getSimpleSequenceRef().add(createRef((ScaSimpleSequenceProperty) prop));
				} else if (prop instanceof ScaStructProperty) {
					cp.getStructRef().add(createRef((ScaStructProperty) prop));
				} else if (prop instanceof ScaStructSequenceProperty) {
					cp.getStructSequenceRef().add(createRef((ScaStructSequenceProperty) prop));
				}
			}
		}
		if (cp.getProperties().isEmpty()) {
			cp = null;
		}

		if (!EcoreUtil.equals(cp, input.getComponentProperties()) && getEditingDomain() != null && getEditingDomain().getCommandStack() != null) {
			getEditingDomain().getCommandStack().execute(
			        SetCommand.create(getEditingDomain(), input, PartitioningPackage.Literals.COMPONENT_INSTANTIATION__COMPONENT_PROPERTIES, cp));
		}
	}

	public EditingDomain getEditingDomain() {
		return editingDomainProvider.getEditingDomain();
	}

	private StructSequenceRef createRef(final ScaStructSequenceProperty prop) {
		final StructSequenceRef retVal = PrfFactory.eINSTANCE.createStructSequenceRef();
		retVal.setProperty(prop.getDefinition());
		for (final ScaStructProperty struct : prop.getStructs()) {
			final StructValue value = PrfFactory.eINSTANCE.createStructValue();
			for (final ScaSimpleProperty simple : struct.getSimples()) {
				if (!simple.isDefaultValue()) {
					value.getSimpleRef().add(createRef(simple));
				}
			}
			retVal.getStructValue().add(value);
		}
		return retVal;
	}

	private StructRef createRef(final ScaStructProperty prop) {
		final StructRef retVal = PrfFactory.eINSTANCE.createStructRef();
		retVal.setProperty(prop.getDefinition());
		for (final ScaSimpleProperty simple : prop.getSimples()) {
			if (!simple.isDefaultValue()) {
				retVal.getSimpleRef().add(createRef(simple));
			}
		}
		return retVal;
	}

	private SimpleSequenceRef createRef(final ScaSimpleSequenceProperty prop) {
		final SimpleSequenceRef retVal = PrfFactory.eINSTANCE.createSimpleSequenceRef();
		retVal.setProperty(prop.getDefinition());
		final Values values = PrfFactory.eINSTANCE.createValues();
		for (final Object obj : prop.getValues()) {
			values.getValue().add(obj.toString());
		}
		retVal.setValues(values);
		return retVal;
	}

	private SimpleRef createRef(final ScaSimpleProperty prop) {
		final SimpleRef retVal = PrfFactory.eINSTANCE.createSimpleRef();
		retVal.setProperty(prop.getDefinition());
		final Object value = prop.getValue();
		final String strValue = (value == null) ? null : value.toString();
		retVal.setValue(strValue);
		return retVal;
	}

	public final void setInput(final ComponentInstantiation inst) {
		ignore = true;
		input = inst;
		if (input != null) {
			final SoftPkg spd = input.getPlacement().getComponentFileRef().getFile().getSoftPkg();
			component.unsetProfileObj();
			component.setProfileObj(spd);
			component.fetchProperties(null);
			if (input.getComponentProperties() != null) {
				for (final ValueListIterator<Object> iterator = input.getComponentProperties().getProperties().valueListIterator(); iterator.hasNext();) {
					final Object obj = iterator.next();
					if (obj instanceof AbstractPropertyRef< ? >) {
						final AbstractPropertyRef< ? > ref = (AbstractPropertyRef< ? >) obj;
						final ScaAbstractProperty< ? > prop = component.getProperty(ref.getRefID());
						if (ref instanceof SimpleRef && prop instanceof ScaSimpleProperty) {
							setValue(ref, prop);
						} else if (ref instanceof SimpleSequenceRef && prop instanceof ScaSimpleSequenceProperty) {
							setValue(ref, prop);
						} else if (ref instanceof StructRef && prop instanceof ScaStructProperty) {
							setValue(ref, prop);
						} else if (ref instanceof StructSequenceRef && prop instanceof ScaStructSequenceProperty) {
							setValue(ref, prop);
						}
					}
				}
			}
		} else {
			component.unsetProfileObj();
		}
		ignore = false;
		viewer.refresh();
	}

	private void setValue(final AbstractPropertyRef< ? > ref, final ScaAbstractProperty< ? > prop) {
		prop.fromAny(ref.toAny());
	}

}

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
import mil.jpeojtrs.sca.partitioning.ComponentInstantiation;
import mil.jpeojtrs.sca.partitioning.ComponentProperties;
import mil.jpeojtrs.sca.partitioning.PartitioningFactory;
import mil.jpeojtrs.sca.partitioning.PartitioningPackage;
import mil.jpeojtrs.sca.prf.AbstractPropertyRef;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.SimpleRef;
import mil.jpeojtrs.sca.prf.SimpleSequenceRef;
import mil.jpeojtrs.sca.prf.StructRef;
import mil.jpeojtrs.sca.prf.StructSequenceRef;
import mil.jpeojtrs.sca.spd.SoftPkg;
import mil.jpeojtrs.sca.util.collections.FeatureMapList;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
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
		public void notifyChanged(final Notification notification) {
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
				AbstractPropertyRef< ? > propertyRef = prop.createPropertyRef();
				switch (propertyRef.eClass().getClassifierID()) {
				case PrfPackage.SIMPLE_REF:
					cp.getSimpleRef().add((SimpleRef) propertyRef);
					break;
				case PrfPackage.SIMPLE_SEQUENCE_REF:
					cp.getSimpleSequenceRef().add((SimpleSequenceRef) propertyRef);
					break;
				case PrfPackage.STRUCT_REF:
					cp.getStructRef().add((StructRef) propertyRef);
					break;
				case PrfPackage.STRUCT_SEQUENCE_REF:
					cp.getStructSequenceRef().add((StructSequenceRef) propertyRef);
					break;
				default:
					throw new IllegalArgumentException("Invalid property");
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

	public final void setInput(final ComponentInstantiation inst) {
		if (inst == input) {
			return;
		}

		ignore = true;
		input = inst;
		if (input != null && input.getPlacement() != null) {
			// Load profile
			final SoftPkg spd = input.getPlacement().getComponentFileRef().getFile().getSoftPkg();
			component.unsetProfileObj();
			component.setProfileObj(spd);

			// Load properties
			for (ScaAbstractProperty< ? > prop : component.fetchProperties(null)) {
				prop.setIgnoreRemoteSet(true);
			}

			// Set property overrides
			if (input.getComponentProperties() != null) {
				for (AbstractPropertyRef< ? > ref : new FeatureMapList<AbstractPropertyRef>(input.getComponentProperties().getProperties(), AbstractPropertyRef.class)) {
					final ScaAbstractProperty< ? > prop = component.getProperty(ref.getRefID());
					prop.fromAny(ref.toAny());
				}
			}
		} else {
			component.unsetProfileObj();
		}
		ignore = false;
		viewer.refresh();
	}

	/**
	 * @since 7.0
	 */
	public void dispose() {
		component.eAdapters().remove(contentAdapter);
		component.dispose();
	}

}

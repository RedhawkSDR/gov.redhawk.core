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
package gov.redhawk.prf.internal.ui.editor.detailspart;

import gov.redhawk.prf.internal.ui.editor.PropertiesSection;
import gov.redhawk.prf.internal.ui.editor.composite.BasicStructPropertyComposite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mil.jpeojtrs.sca.prf.ConfigurationKind;
import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.Struct;
import mil.jpeojtrs.sca.prf.StructPropertyConfigurationType;
import mil.jpeojtrs.sca.prf.StructSequence;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.list.IListChangeListener;
import org.eclipse.core.databinding.observable.list.ListChangeEvent;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;

public abstract class BasicStructPropertyDetailsPage extends
		AbstractPropertyDetailsPage {

	public BasicStructPropertyDetailsPage(final PropertiesSection section) {
		super(section);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<Binding> bind(final DataBindingContext context,
			final EObject input) {

		final EditingDomain domain = getEditingDomain();

		final List<Binding> retVal = super.bind(context, input);

		// Configuration Kind
		if (getComposite().getConfigurationKindViewer() != null) {
			getComposite().getConfigurationKindViewer().setCheckedElements(
					Collections.EMPTY_LIST.toArray());
			createKindBinding(context, input, domain, retVal);
		}

		return retVal;
	}

	@Override
	protected BasicStructPropertyComposite getComposite() {
		return (BasicStructPropertyComposite) super.getComposite();
	}

	/**
	 * Creates the kind binding.
	 * 
	 * @param context
	 * @param retVal
	 */
	private void createKindBinding(final DataBindingContext context,
			final EObject input, final EditingDomain domain,
			final List<Binding> retVal) {
		final WritableList myList = new WritableList();
		myList.addListChangeListener(new IListChangeListener() {

			public void handleListChange(ListChangeEvent event) {
				List<StructPropertyConfigurationType> newChecked = new ArrayList<StructPropertyConfigurationType>();
				for (Object obj : myList) {
					if (obj instanceof ConfigurationKind) {
						newChecked.add(((ConfigurationKind) obj).getType());
					} else if (obj instanceof StructPropertyConfigurationType) {
						newChecked.add(((StructPropertyConfigurationType) obj));
					}
				}
				((BasicStructPropertyComposite) getComposite())
						.getConfigurationKindViewer().setCheckedElements(
								newChecked.toArray());
			}
		});

		((BasicStructPropertyComposite) getComposite())
				.getConfigurationKindViewer().addCheckStateListener(
						new ICheckStateListener() {

							public void checkStateChanged(
									CheckStateChangedEvent event) {
								if (event.getChecked()) {
									ConfigurationKind kind = PrfFactory.eINSTANCE
											.createConfigurationKind();
									kind.setType(((StructPropertyConfigurationType) event
											.getElement()));
									myList.add(kind);
								} else {
									for (Object obj : myList) {
										if (obj instanceof ConfigurationKind
												&& ((ConfigurationKind) obj)
														.getType() == event
														.getElement()) {
											myList.remove(obj);
											break;
										}
									}
								}
							}
						});

		EReference literal = null;
		if (input instanceof StructSequence) {
			literal = PrfPackage.Literals.STRUCT_SEQUENCE__CONFIGURATION_KIND;
		} else if (input instanceof Struct) {
			literal = PrfPackage.Literals.STRUCT__CONFIGURATION_KIND;
		}

		retVal.add(context.bindList(myList, EMFEditObservables.observeList(
				getEditingDomain(), input, literal)));
	}

}

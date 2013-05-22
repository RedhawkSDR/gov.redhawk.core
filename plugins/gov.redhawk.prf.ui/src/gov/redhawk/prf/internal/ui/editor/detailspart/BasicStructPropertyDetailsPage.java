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
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.list.IListChangeListener;
import org.eclipse.core.databinding.observable.list.ListChangeEvent;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.databinding.EMFUpdateValueStrategy;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.swt.WidgetProperties;
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
		if (getComposite().getMessageButton() != null) {
			createMessageBinding(context, input, domain, retVal);
		}

		return retVal;
	}

	@Override
	protected BasicStructPropertyComposite getComposite() {
		return (BasicStructPropertyComposite) super.getComposite();
	}

	private void createMessageBinding(final DataBindingContext context,
			final EObject input, final EditingDomain domain,
			final List<Binding> retVal) {

		EMFUpdateValueStrategy modelToTargetStrategy = new EMFUpdateValueStrategy();
		EMFUpdateValueStrategy targetToModelStrategy = new EMFUpdateValueStrategy();

		// Goes from the EMF Model object of EList<ConfigurationKind> to the
		// checkbox
		modelToTargetStrategy.setConverter(new Converter(Object.class,
				Boolean.class) {

			public Object convert(final Object fromObject) {
				if (fromObject instanceof EList<?>) {
					EList<ConfigurationKind> kindList = (EList<ConfigurationKind>) fromObject;

					for (ConfigurationKind kind : kindList) {
						if (kind.getType() == StructPropertyConfigurationType.MESSAGE) {
							return true;
						}
					}
					return false;
				}
				throw new IllegalArgumentException();
			}
		});

		// Goes from the Boolean based checkbox to the EMF Model object of
		// EList<Kind>
		targetToModelStrategy.setConverter(new Converter(Boolean.class,
				Object.class) {

			public Object convert(final Object fromObject) {
				if (fromObject instanceof Boolean) {
					Boolean checked = (Boolean) fromObject;

					EList<ConfigurationKind> kindList = new BasicEList<ConfigurationKind>();
					ConfigurationKind messageKind = PrfFactory.eINSTANCE
							.createConfigurationKind();

					if (checked) {
						messageKind
								.setType(StructPropertyConfigurationType.MESSAGE);
					} else {
						messageKind
								.setType(StructPropertyConfigurationType.CONFIGURE);
					}

					kindList.add(messageKind);
					return kindList;
				}

				throw new IllegalArgumentException();
			}
		});
		EReference literal = null;
		if (input instanceof StructSequence) {
			literal = PrfPackage.Literals.STRUCT_SEQUENCE__CONFIGURATION_KIND;
		} else if (input instanceof Struct) {
			literal = PrfPackage.Literals.STRUCT__CONFIGURATION_KIND;
		}

		// Bind the checkbox to the model
		retVal.add(context.bindValue(
				WidgetProperties.selection().observe(
						((BasicStructPropertyComposite) getComposite())
								.getMessageButton()), EMFEditObservables
						.observeValue(domain, input, literal),
				targetToModelStrategy, modelToTargetStrategy));

		EMFUpdateValueStrategy enabledToTargetStrategy = new EMFUpdateValueStrategy();

		// Goes from the checkbox to the enabled state of the viewer
		enabledToTargetStrategy.setConverter(new Converter(Boolean.class,
				Boolean.class) {
			public Object convert(final Object fromObject) {
				if (fromObject instanceof Boolean) {
					boolean editable = getComposite().isEditable();
					if (editable) {
						return !((Boolean) fromObject);
					} else {
						return editable;
					}
				}
				throw new IllegalArgumentException();
			}
		});

		retVal.add(context.bindValue(
				WidgetProperties.enabled().observe(
						(((BasicStructPropertyComposite) getComposite())
								.getConfigurationKindViewer().getControl())),
				WidgetProperties.selection().observe(
						((BasicStructPropertyComposite) getComposite())
								.getMessageButton()), new UpdateValueStrategy(
						UpdateValueStrategy.POLICY_NEVER),
				enabledToTargetStrategy));
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
						newChecked.add(((ConfigurationKind) obj).getType());
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

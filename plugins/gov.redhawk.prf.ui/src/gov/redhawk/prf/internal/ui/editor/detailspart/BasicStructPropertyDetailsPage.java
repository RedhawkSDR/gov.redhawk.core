/**
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 * 
 * This file is part of REDHAWK IDE.
 * 
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 *
 */
package gov.redhawk.prf.internal.ui.editor.detailspart;

import gov.redhawk.prf.internal.ui.editor.PropertiesSection;
import gov.redhawk.prf.internal.ui.editor.composite.AbstractPropertyComposite;
import gov.redhawk.prf.internal.ui.editor.composite.BasicStructPropertyComposite;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mil.jpeojtrs.sca.prf.ConfigurationKind;
import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.Struct;
import mil.jpeojtrs.sca.prf.StructPropertyConfigurationType;
import mil.jpeojtrs.sca.prf.StructSequence;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.set.ISetChangeListener;
import org.eclipse.core.databinding.observable.set.SetChangeEvent;
import org.eclipse.core.databinding.observable.set.WritableSet;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.viewers.ViewersObservables;

public abstract class BasicStructPropertyDetailsPage extends AbstractPropertyDetailsPage {

	private Map<EObject, WritableSet> setMap;
	private Property property;
	private EObject input;

	public BasicStructPropertyDetailsPage(final PropertiesSection section) {
		super(section);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<Binding> bind(final DataBindingContext context, final EObject input) {
		

		final EditingDomain domain = getEditingDomain();
		this.input = input;
		this.property = getProperty(this.input);

		final List<Binding> retVal = super.bind(context, input);

		// Configuration Kind
		if (getComposite().getConfigurationKindViewer() != null) {
			getComposite().getConfigurationKindViewer().setCheckedElements(Collections.EMPTY_LIST.toArray());
			createKindBinding(context, input, domain, retVal);	
		}

		return retVal;
	}
	
	@Override
	protected BasicStructPropertyComposite getComposite() {
	    return (BasicStructPropertyComposite) super.getComposite();
	}

	private void createKindBinding(final DataBindingContext context, final EObject input, final EditingDomain domain, final List<Binding> retVal) {
		final EList<ConfigurationKind> kindList = getKindList(input);

		if (this.setMap == null) {
			this.setMap = new HashMap<EObject, WritableSet>();
		}
		if (!this.setMap.containsKey(input)) {
			final WritableSet mySet = new WritableSet();
			for (final ConfigurationKind kind : kindList) {
				mySet.add(kind.getType());
			}
			mySet.addSetChangeListener(new ISetChangeListener() {
				public void handleSetChange(final SetChangeEvent event) {
					for (final ConfigurationKind k : kindList) {
						if (event.diff.getRemovals().contains(k.getType())) {
							final Command command = RemoveCommand.create(domain, input, BasicStructPropertyDetailsPage.this.property.getConfigurationKind(), k);
							execute(command);
						}
					}
					for (final Object obj : event.diff.getAdditions()) {
						final ConfigurationKind kind = PrfFactory.eINSTANCE.createConfigurationKind();
						kind.setType((StructPropertyConfigurationType) obj);
						final Command command = AddCommand.create(domain, input, BasicStructPropertyDetailsPage.this.property.getConfigurationKind(), kind);
						execute(command);
					}
				}

			});
			this.setMap.put(input, mySet);
		}

		retVal.add(context.bindSet(ViewersObservables.observeCheckedElements((((BasicStructPropertyComposite) getComposite()).getConfigurationKindViewer()),
		        StructPropertyConfigurationType.class), this.setMap.get(input), null, null));
	}

	private EList<ConfigurationKind> getKindList(final EObject input) {
		EList<ConfigurationKind> kindList = null;
		if (input instanceof Struct) {
			kindList = ((Struct) input).getConfigurationKind();
		} else if (input instanceof StructSequence) {
			kindList = ((StructSequence) input).getConfigurationKind();
		}
		return kindList;
	}

}

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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.emf.databinding.EMFUpdateValueStrategy;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.viewers.ViewersObservables;

import gov.redhawk.prf.internal.ui.editor.PropertiesSection;
import gov.redhawk.prf.internal.ui.editor.composite.BasicStructPropertyComposite;
import mil.jpeojtrs.sca.prf.ConfigurationKind;
import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.StructPropertyConfigurationType;

public abstract class BasicStructPropertyDetailsPage extends AbstractPropertyDetailsPage {

	public BasicStructPropertyDetailsPage(final PropertiesSection section) {
		super(section);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<Binding> bind(final DataBindingContext context, final EObject input) {

		final EditingDomain domain = getEditingDomain();
		final BasicStructPropertyComposite composite = getComposite();
		final Property property = getProperty(input);

		final List<Binding> retVal = super.bind(context, input);

		// Configuration Kind
		if (composite.getConfigurationKindViewer() != null) {
			// Search for configure/execparam properties if they are being used for backwards compatibility
			boolean oldStyle = hasConfigureOrExecParamProperties(input);
			composite.showConfigure(oldStyle);

			retVal.add(context.bindValue(ViewersObservables.observeSingleSelection(composite.getConfigurationKindViewer()),
				EMFEditObservables.observeValue(domain, input, property.getConfigurationKind()), createKindTargetToModel(), createKindModelToTarget()));
		}

		return retVal;
	}

	/**
	 * Creates a {@link UpdateValueStrategy} that converts the {@link List} < {@link ConfigurationKind} > from the EMF
	 * model object to a single {@link StructPropertyConfigurationType} for the drop-down combo box.
	 * @return
	 */
	private UpdateValueStrategy createKindModelToTarget() {
		final EMFUpdateValueStrategy strategy = new EMFUpdateValueStrategy();
		strategy.setConverter(new Converter(List.class, StructPropertyConfigurationType.class) {

			@Override
			public Object convert(final Object fromObject) {
				if (fromObject instanceof List) {
					final List< ? > kindList = (List< ? >) fromObject;

					// Normally, we expect exactly one kind type in the list
					if (kindList.size() == 1) {
						ConfigurationKind kind = (ConfigurationKind) kindList.get(0);
						if (kind.isSetType()) {
							// Message should only be shown as an option if the user explicitly enters it
							if (kind.getType().equals(StructPropertyConfigurationType.MESSAGE)) {
								BasicStructPropertyDetailsPage.this.getComposite().showMessage(true);
							} else {
								BasicStructPropertyDetailsPage.this.getComposite().showMessage(false);
							}

							return kind.getType();
						} else {
							return StructPropertyConfigurationType.PROPERTY;
						}
					}
					// If empty, default to CONFIGURE
					if (kindList.size() == 0) {
						return StructPropertyConfigurationType.CONFIGURE;
					}

					// We can only display one kind type, even though the XML allows multiple. As of Redhawk 2.0, we
					// only expect one type to be used. Since we have multiple, we'll select the "most important" one
					// to show.
					Set<StructPropertyConfigurationType> kindTypeSet = new HashSet<StructPropertyConfigurationType>();
					for (Object obj : kindList) {
						ConfigurationKind kind = (ConfigurationKind) obj;
						if (kind.isSetType()) {
							kindTypeSet.add(kind.getType());
						}
					}
					if (kindTypeSet.contains(StructPropertyConfigurationType.PROPERTY)) {
						return StructPropertyConfigurationType.PROPERTY;
					}
					if (kindTypeSet.contains(StructPropertyConfigurationType.ALLOCATION)) {
						return StructPropertyConfigurationType.ALLOCATION;
					}
					if (kindTypeSet.contains(StructPropertyConfigurationType.CONFIGURE)) {
						return StructPropertyConfigurationType.CONFIGURE;
					}
					if (kindTypeSet.contains(StructPropertyConfigurationType.MESSAGE)) {
						return StructPropertyConfigurationType.MESSAGE;
					}
					StructPropertyConfigurationType displayKindType = ((ConfigurationKind) kindList.get(0)).getType();
					return displayKindType;
				}
				throw new IllegalArgumentException();
			}
		});
		return strategy;
	}

	/**
	 * Creates a {@link UpdateValueStrategy} that converts a {@link StructPropertyConfigurationType} from the drop-down
	 * combo box to a {@link List} < {@link ConfigurationKind} > for the EMF model object.
	 * @return
	 */
	private UpdateValueStrategy createKindTargetToModel() {
		final EMFUpdateValueStrategy strategy = new EMFUpdateValueStrategy();
		strategy.setConverter(new Converter(StructPropertyConfigurationType.class, List.class) {

			@Override
			public Object convert(final Object fromObject) {
				if (fromObject instanceof StructPropertyConfigurationType) {
					final StructPropertyConfigurationType kindType = (StructPropertyConfigurationType) fromObject;

					// Message should only be shown as an option if is already exists in the prf.xml
					boolean isMessageShown = BasicStructPropertyDetailsPage.this.getComposite().isShowMessage();
					if (!kindType.equals(StructPropertyConfigurationType.MESSAGE) && isMessageShown) {
						BasicStructPropertyDetailsPage.this.getComposite().showMessage(false);
					}

					final ConfigurationKind kind = PrfFactory.eINSTANCE.createConfigurationKind();
					kind.setType(kindType);
					List<ConfigurationKind> kindList = new ArrayList<ConfigurationKind>();
					kindList.add(kind);
					return kindList;
				}
				throw new IllegalArgumentException();
			}
		});
		return strategy;
	}

	@Override
	protected BasicStructPropertyComposite getComposite() {
		return (BasicStructPropertyComposite) super.getComposite();
	}
}

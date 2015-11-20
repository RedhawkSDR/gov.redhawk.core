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
import gov.redhawk.prf.internal.ui.editor.composite.AbstractPropertyComposite;
import gov.redhawk.ui.editor.SCAFormEditor;
import gov.redhawk.ui.editor.ScaDetails;
import gov.redhawk.ui.util.EMFEmptyStringToNullUpdateValueStrategy;
import gov.redhawk.ui.util.SCAEditorUtil;

import java.util.ArrayList;
import java.util.List;

import mil.jpeojtrs.sca.prf.AbstractProperty;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.ChangeEvent;
import org.eclipse.core.databinding.observable.IChangeListener;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.emf.databinding.EMFUpdateValueStrategy;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * Provides functionality common to all Property types, including data binding of all common widgets.
 */
public abstract class AbstractPropertyDetailsPage extends ScaDetails {

	private final PropertiesSection section;
	private Property property;
	private EObject input;
	private AbstractPropertyComposite composite;
	private Binding nameBinding;
	private boolean nameTab;

	public AbstractPropertyDetailsPage(final PropertiesSection section) {
		super(section.getPage());
		this.section = section;
	}

	@Override
	protected void createSpecificContent(final Composite parent) {
		final FormToolkit toolkit = getManagedForm().getToolkit();
		this.composite = createSection(parent, toolkit);
		this.addListeners();
	}

	protected void addListeners() {
		final Color color = AbstractPropertyDetailsPage.this.composite.getNameEntry().getText().getForeground();
		final Color disabledColor = AbstractPropertyDetailsPage.this.composite.getDisplay().getSystemColor(SWT.COLOR_GRAY);
	
		this.composite.getNameEntry().getText().addModifyListener(new ModifyListener() {
			public void modifyText(final ModifyEvent e) {
				String actualName = (String) AbstractPropertyDetailsPage.this.composite.getNameEntry().getText().getData("actual-name");
				Boolean ignoreModify = (Boolean) AbstractPropertyDetailsPage.this.composite.getNameEntry().getText().getData("ignore-modify");
				if (Boolean.FALSE.equals(ignoreModify)) {
					// If the control has focus and the text is modified, the person is typing so update the actual name
					actualName = AbstractPropertyDetailsPage.this.composite.getNameEntry().getText().getText();
					if ("".equals(actualName)) {
						actualName = null;
					}
					AbstractPropertyDetailsPage.this.composite.getNameEntry().getText().setData("actual-name", actualName);
				} 
				
				if (actualName == null) {
					// If the actual name is null or empty string mark the text in grey
					if (AbstractPropertyDetailsPage.this.composite.getNameEntry().getText().getForeground() != disabledColor) {
						AbstractPropertyDetailsPage.this.composite.getNameEntry().getText().setForeground(disabledColor);
						AbstractPropertyDetailsPage.this.composite.removeNameFromTabList();
					}
				} else {
					// otherwise mark the text in black
					if (AbstractPropertyDetailsPage.this.composite.getNameEntry().getText().getForeground() != color) {
						AbstractPropertyDetailsPage.this.composite.addNameToTabList();
						AbstractPropertyDetailsPage.this.composite.getNameEntry().getText().setForeground(color);
					}
				}
			}
		});

		this.composite.getNameEntry().getText().addFocusListener(new FocusListener() {
			//If there isn't a name set, the name field displays the id
			public void focusLost(final FocusEvent e) {
				AbstractPropertyDetailsPage.this.composite.getNameEntry().getText().setData("ignore-modify", true);
				String actualName = (String) AbstractPropertyDetailsPage.this.composite.getNameEntry().getText().getData("actual-name");
				final AbstractProperty prfProp = (AbstractProperty) AbstractPropertyDetailsPage.this.input;
				if (actualName == null) {
					String id = prfProp.getId();
					if (id == null) {
						id = "";
					}
					AbstractPropertyDetailsPage.this.nameBinding.updateModelToTarget();
					if (AbstractPropertyDetailsPage.this.composite.getNameEntry().getText().getForeground() != disabledColor) {
						AbstractPropertyDetailsPage.this.composite.getNameEntry().getText().setForeground(disabledColor);
						AbstractPropertyDetailsPage.this.composite.removeNameFromTabList();
					}
				}
			}

			//If the name isn't set, clear out the text in the field
			public void focusGained(final FocusEvent e) {
				AbstractPropertyDetailsPage.this.composite.getNameEntry().getText().setData("ignore-modify", false);
			}
		});
	}

	@Override
	protected List<Binding> bind(final DataBindingContext dataBindingContext, final EObject input) {
		final List<Binding> retVal = new ArrayList<Binding>();
		final EditingDomain domain = getEditingDomain();
		this.input = input;
		this.property = getProperty(this.input);
		this.nameTab = (((AbstractProperty) this.input).getName() == null);

		// ID
		retVal.add(dataBindingContext.bindValue(WidgetProperties.text(SWT.Modify).observeDelayed(SCAFormEditor.getFieldBindingDelay(),
		        this.composite.getIdEntry().getText()),
		        EMFEditObservables.observeValue(getEditingDomain(), input, this.property.getId()),
		        new EMFEmptyStringToNullUpdateValueStrategy(),
		        null));

		final WritableValue value = new WritableValue();
		value.addChangeListener(new IChangeListener() {

			public void handleChange(final ChangeEvent event) {
				if (AbstractPropertyDetailsPage.this.nameBinding != null && !AbstractPropertyDetailsPage.this.nameBinding.isDisposed()) {
					AbstractPropertyDetailsPage.this.nameBinding.updateModelToTarget();
				}
			}
		});

		retVal.add(dataBindingContext.bindValue(value, EMFEditObservables.observeValue(getEditingDomain(), input, this.property.getId())));

		this.nameBinding = dataBindingContext.bindValue(WidgetProperties.text(SWT.Modify).observeDelayed(SCAFormEditor.getFieldBindingDelay(),
		        this.composite.getNameEntry().getText()),
		        EMFEditObservables.observeValue(domain, input, this.property.getName()),
		        new EMFUpdateValueStrategy() {
			        @Override
			        public Object convert(final Object value) {
						String actualName = (String) AbstractPropertyDetailsPage.this.composite.getNameEntry().getText().getData("actual-name");
			        	return actualName;
			        }
		        },
		        new EMFUpdateValueStrategy() {
			        @Override
			        public Object convert(final Object value) {
			        	AbstractPropertyDetailsPage.this.composite.getNameEntry().getText().setData("actual-name", value);
				        if (value == null || value instanceof String && ((String) value).equals("")) {
					        final String idValue = ((AbstractProperty) input).getId();
					        return super.convert(idValue);
				        }
				        return super.convert(value);			        
			        }
		        });

		// Name
		retVal.add(this.nameBinding);

		// Mode
		retVal.add(dataBindingContext.bindValue(ViewersObservables.observeSingleSelection(this.composite.getModeViewer()),
		        EMFEditObservables.observeValue(domain, input, this.property.getMode()),
		        null,
		        null));

		// Description
		retVal.add(dataBindingContext.bindValue(WidgetProperties.text(SWT.Modify).observeDelayed(SCAFormEditor.getFieldBindingDelay(),
		        this.composite.getDescriptionText()),
		        EMFEditObservables.observeValue(domain, input, this.property.getDescription()),
		        new EMFEmptyStringToNullUpdateValueStrategy(),
		        null));

		this.setEditable();
		return retVal;
	}

	protected PropertiesSection getSection() {
		return this.section;
	}

	protected abstract AbstractPropertyComposite createSection(Composite parent, FormToolkit toolkit);

	/**
	 * Returns the {@link Property} type based on the given input.
	 * 
	 * @param input the {@link EObject} to obtain a {@link Property} for
	 * @return the {@link Property} associated with the input if it exists, <code> null </code >otherwise
	 */
	protected Property getProperty(final EObject input) {
		return Property.getProperty(input);
	}

	protected boolean isEditable() {
		return SCAEditorUtil.isEditableResource(getPage(), this.input.eResource());
	}

	private void setEditable() {
		this.composite.setEditable(isEditable());
	}

	protected AbstractPropertyComposite getComposite() {
		return this.composite;
	}
}

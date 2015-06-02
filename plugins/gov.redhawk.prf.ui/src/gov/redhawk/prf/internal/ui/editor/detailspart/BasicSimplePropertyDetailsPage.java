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
import gov.redhawk.prf.internal.ui.editor.composite.BasicSimplePropertyComposite;
import gov.redhawk.prf.ui.wizard.EnumerationWizard;
import gov.redhawk.ui.editor.SCAFormEditor;
import gov.redhawk.ui.util.EMFEmptyStringToNullUpdateValueStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mil.jpeojtrs.sca.prf.Action;
import mil.jpeojtrs.sca.prf.ActionType;
import mil.jpeojtrs.sca.prf.Enumeration;
import mil.jpeojtrs.sca.prf.Enumerations;
import mil.jpeojtrs.sca.prf.Kind;
import mil.jpeojtrs.sca.prf.PrfFactory;
import mil.jpeojtrs.sca.prf.PrfPackage;
import mil.jpeojtrs.sca.prf.PropertyConfigurationType;
import mil.jpeojtrs.sca.prf.Range;
import mil.jpeojtrs.sca.prf.Simple;
import mil.jpeojtrs.sca.prf.SimpleSequence;	//SUPPRESS CHECKSTYLE INLINE - Used in a Javadoc

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.databinding.EMFUpdateValueStrategy;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.databinding.edit.EMFEditObservables;
import org.eclipse.emf.databinding.edit.EMFEditProperties;
import org.eclipse.emf.databinding.edit.IEMFEditValueProperty;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.ReplaceCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

/**
 * Provides functionality common to {@link Simple} and {@link SimpleSequence} types, including data binding of all common widgets.
 */
public abstract class BasicSimplePropertyDetailsPage extends AbstractPropertyDetailsPage {

	private EObject input;
	private Property property;
	private Binding minBinding;
	private Binding maxBinding;

	private final SelectionListener rangeListener = new SelectionAdapter() {
		@Override
		public void widgetSelected(final SelectionEvent e) {
			if (BasicSimplePropertyDetailsPage.this.minBinding != null && !BasicSimplePropertyDetailsPage.this.minBinding.isDisposed()) {
				BasicSimplePropertyDetailsPage.this.minBinding.updateModelToTarget();
				BasicSimplePropertyDetailsPage.this.minBinding.updateTargetToModel();
				BasicSimplePropertyDetailsPage.this.minBinding.validateModelToTarget();
				BasicSimplePropertyDetailsPage.this.minBinding.validateTargetToModel();
			}
			if (BasicSimplePropertyDetailsPage.this.maxBinding != null && !BasicSimplePropertyDetailsPage.this.maxBinding.isDisposed()) {
				BasicSimplePropertyDetailsPage.this.maxBinding.updateModelToTarget();
				BasicSimplePropertyDetailsPage.this.maxBinding.updateTargetToModel();
				BasicSimplePropertyDetailsPage.this.maxBinding.validateModelToTarget();
				BasicSimplePropertyDetailsPage.this.maxBinding.validateTargetToModel();
			}
		}
	};

	public BasicSimplePropertyDetailsPage(final PropertiesSection section) {
		super(section);
	}

	private void addRangeListener() {
		// NOTE: MUST remove listener prior to adding since 
		//		1.) We don't want multiple listeners
		// 		2.) This listener MUST be triggered AFTER the binding listener, therefore, it is adding every time in the bind method
		((BasicSimplePropertyComposite) getComposite()).getRangeButton().removeSelectionListener(this.rangeListener);
		((BasicSimplePropertyComposite) getComposite()).getRangeButton().addSelectionListener(this.rangeListener);
	}

	@Override
	protected List<Binding> bind(final DataBindingContext dataBindingContext, final EObject input) {
		final BasicSimplePropertyComposite composite = getComposite();

		final EditingDomain domain = getEditingDomain();
		this.input = input;
		this.property = getProperty(this.input);

		// Search for configure/execparam properties if they are being used for backwards compatibility
		boolean oldStyle = hasConfigureOrExecParamProperties(this.input);
		composite.showConfigureAndExecParam(oldStyle);

		final List<Binding> retVal = super.bind(dataBindingContext, input);

		// Type
		if (composite.getTypeViewer() != null) {
			retVal.add(dataBindingContext.bindValue(ViewersObservables.observeSingleSelection(composite.getTypeViewer()),
				EMFEditObservables.observeValue(domain, input, this.property.getType()), null, null));
		}

		if (composite.getTypeModifier() != null) {
			retVal.add(dataBindingContext.bindValue(WidgetProperties.selection().observe(composite.getTypeModifier()),
				EMFEditObservables.observeValue(domain, input, this.property.getTypeModifier()), createTypeModifierTargetToModel(),
				createTypeModifierModelTarget()));
		}

		// Units
		if (composite.getUnitsEntry() != null) {
			retVal.add(dataBindingContext.bindValue(
				WidgetProperties.text(SWT.Modify).observeDelayed(SCAFormEditor.getFieldBindingDelay(), composite.getUnitsEntry().getText()),
				EMFEditObservables.observeValue(domain, input, this.property.getUnits()), new EMFEmptyStringToNullUpdateValueStrategy(), null));
		}

		// Kind
		if (composite.getKindViewer() != null) {
			retVal.add(dataBindingContext.bindValue(ViewersObservables.observeSingleSelection(composite.getKindViewer()),
				EMFEditObservables.observeValue(domain, input, this.property.getKind()), createKindTargetToModel(), createKindModelToTarget()));
		}

		// Action
		if (composite.getActionViewer() != null) {
			retVal.add(dataBindingContext.bindValue(ViewersObservables.observeSingleSelection(composite.getActionViewer()),
				EMFEditObservables.observeValue(domain, input, this.property.getAction()), createActionTargetToModel(), createActionModelToTarget()));
		}

		// Range
		if (composite.getRangeButton() != null) {
			retVal.addAll(bindRanges(input, dataBindingContext, domain));
			addRangeListener();
		}

		// Optional
		if (composite.getOptionalCombo() != null) {
			retVal.add(dataBindingContext.bindValue(WidgetProperties.selection().observe(composite.getOptionalCombo()),
				EMFEditObservables.observeValue(domain, input, this.property.getOptional()),
				createStringToBoolean(),   // target to model
				createBooleanToString())); // model to target
		}

		return retVal;
	}

	private UpdateValueStrategy createTypeModifierModelTarget() {
		EMFUpdateValueStrategy strategy = new EMFUpdateValueStrategy();
		strategy.setConverter(new Converter(Boolean.class, String.class) {

			@Override
			public Object convert(Object fromObject) {
				if (fromObject == null) {
					return "";
				}
				boolean state = false;
				if (fromObject instanceof Boolean) {
					state = (Boolean) fromObject;
				}
				if (state) {
					return "complex";
				} else {
					return "real";
				}
			}
		});
		return strategy;
	}

	private UpdateValueStrategy createTypeModifierTargetToModel() {
		EMFUpdateValueStrategy strategy = new EMFUpdateValueStrategy();
		strategy.setConverter(new Converter(String.class, Boolean.class) {

			@Override
			public Object convert(Object fromObject) {
				if (fromObject instanceof String) {
					String fromObjectStr = (String) fromObject;
					if (fromObjectStr.isEmpty()) {
						return null;
					}
					if ("real".equalsIgnoreCase(fromObjectStr)) {
						return false;
					} else if ("complex".equalsIgnoreCase(fromObjectStr)) {
						return true;
					}
				}
				return null;
			}
		});
		return strategy;
	}

	/**
	 * Creates a {@link UpdateValueStrategy} that converts the {@link List} < {@link Kind} > from the EMF model object
	 * to a single {@link PropertyConfigurationType} for the drop-down combo box.
	 * @return
	 */
	private UpdateValueStrategy createKindModelToTarget() {
		final EMFUpdateValueStrategy strategy = new EMFUpdateValueStrategy();
		strategy.setConverter(new Converter(List.class, PropertyConfigurationType.class) {

			@Override
			public Object convert(final Object fromObject) {
				if (fromObject instanceof List) {
					final List<?> kindList = (List<?>) fromObject;

					// Normally, we expect exactly one kind type in the list
					if (kindList.size() == 1) {
						Kind kind = (Kind) kindList.get(0);
						if (kind.isSetType()) {
							return kind.getType();
						} else {
							return PropertyConfigurationType.PROPERTY;
						}
					}
					// If empty, default to PROPERTY
					if (kindList.size() == 0) {
						PropertyConfigurationType displayKindType = PropertyConfigurationType.PROPERTY;
						return displayKindType;
					}

					// We can only display one kind type, even though the XML allows multiple. As of Redhawk 2.0, we only
					// expect one type to be used. Since we have multiple, we'll select the "most important" one to show.
					Set<PropertyConfigurationType> kindTypeSet = new HashSet<PropertyConfigurationType>();
					for (Object obj : kindList) {
						Kind kind = (Kind) obj;
						if (kind.isSetType()) {
							kindTypeSet.add(kind.getType());
						}
					}
					if (kindTypeSet.contains(PropertyConfigurationType.PROPERTY)) {
						return PropertyConfigurationType.PROPERTY;
					}
					if (kindTypeSet.contains(PropertyConfigurationType.ALLOCATION)) {
						return PropertyConfigurationType.ALLOCATION;
					}
					if (kindTypeSet.contains(PropertyConfigurationType.CONFIGURE)) {
						return PropertyConfigurationType.CONFIGURE;
					}
					if (kindTypeSet.contains(PropertyConfigurationType.EXECPARAM)) {
						return PropertyConfigurationType.EXECPARAM;
					}
					if (kindTypeSet.contains(PropertyConfigurationType.MESSAGE)) {
						return PropertyConfigurationType.MESSAGE;
					}
					PropertyConfigurationType displayKindType = ((Kind) kindList.get(0)).getType();
					return displayKindType;
				}
				throw new IllegalArgumentException();
			}
		});
		return strategy;
	}

	/**
	 * Creates a {@link UpdateValueStrategy} that converts a {@link PropertyConfigurationType} from the drop-down combo
	 * box to a {@link List} < {@link Kind} > for the EMF model object.
	 * @return
	 */
	private UpdateValueStrategy createKindTargetToModel() {
		final EMFUpdateValueStrategy strategy = new EMFUpdateValueStrategy();
		strategy.setConverter(new Converter(PropertyConfigurationType.class, List.class) {

			@Override
			public Object convert(final Object fromObject) {
				if (fromObject instanceof PropertyConfigurationType) {
					final PropertyConfigurationType kindType = (PropertyConfigurationType) fromObject;
					final Kind kind = PrfFactory.eINSTANCE.createKind();
					kind.setType(kindType);
					List<Kind> kindList = new ArrayList<Kind>();
					kindList.add(kind);
					return kindList;
				}
				throw new IllegalArgumentException();
			}
		});
		return strategy;
	}

	private UpdateValueStrategy createActionModelToTarget() {
		final EMFUpdateValueStrategy strategy = new EMFUpdateValueStrategy();
		strategy.setConverter(new Converter(Action.class, Object.class) {

			@Override
			public Object convert(final Object fromObject) {
				if (fromObject instanceof Action) {
					final Action a = (Action) fromObject;
					return a.getType();
				} else if (fromObject == null) {
					return "";
				}
				throw new IllegalArgumentException();
			}
		});
		return strategy;
	}

	private UpdateValueStrategy createActionTargetToModel() {
		final EMFUpdateValueStrategy strategy = new EMFUpdateValueStrategy();
		strategy.setConverter(new Converter(Object.class, ActionType.class) {

			@Override
			public Object convert(final Object fromObject) {
				if (fromObject instanceof ActionType) {
					final ActionType a = (ActionType) fromObject;
					final Action action = PrfFactory.eINSTANCE.createAction();
					action.setType(a);
					return action;
				} else if (fromObject instanceof String) {
					return null;
				}
				throw new IllegalArgumentException();
			}
		});
		return strategy;
	}

	private List<Binding> bindRanges(final EObject input, final DataBindingContext context, final EditingDomain domain) {
		final BasicSimplePropertyComposite composite = (BasicSimplePropertyComposite) getComposite();
		final List<Binding> retVal = new ArrayList<Binding>();
		final Text minText = composite.getMinText().getText();
		final Text maxText = composite.getMaxText().getText();
		retVal.add(this.bindMin(context, minText));
		retVal.add(this.bindMax(context, maxText));
		retVal.addAll(this.bindButton(context, composite.getRangeButton(), minText, maxText));

		//		addRangeListener();

		return retVal;
	}

	public List<Binding> bindButton(final DataBindingContext context, final Button rangeButton, final Text minText, final Text maxText) {

		final EMFUpdateValueStrategy targetToModel = new EMFUpdateValueStrategy();
		targetToModel.setConverter(new Converter(Boolean.class, Range.class) {

			@Override
			public Object convert(final Object fromObject) {
				if ((Boolean) fromObject) {
					return PrfFactory.eINSTANCE.createRange();
				} else {
					return null;
				}
			}
		});

		final EMFUpdateValueStrategy modelToTarget = new EMFUpdateValueStrategy();
		modelToTarget.setConverter(new Converter(Range.class, Boolean.class) {

			@Override
			public Object convert(final Object fromObject) {
				return fromObject != null;
			}

		});

		final List<Binding> buttonBindings = new ArrayList<Binding>();
		buttonBindings.add(context.bindValue(WidgetProperties.selection().observe(rangeButton),
			EMFEditObservables.observeValue(getEditingDomain(), this.input, this.property.getRange()), targetToModel, modelToTarget));

		buttonBindings.add(context.bindValue(WidgetProperties.enabled().observe(minText), WidgetProperties.selection().observe(rangeButton)));
		buttonBindings.add(context.bindValue(WidgetProperties.enabled().observe(maxText), WidgetProperties.selection().observe(rangeButton)));
		return buttonBindings;
	}

	public Binding bindMin(final DataBindingContext context, final Text minText) {
		final IEMFEditValueProperty minProperty = EMFEditProperties.value(getEditingDomain(),
			FeaturePath.fromList(this.property.getRange(), PrfPackage.Literals.RANGE__MIN));
		final IObservableValue minObserver = minProperty.observe(this.input);
		this.minBinding = context.bindValue(
			WidgetProperties.text(SWT.Modify).observeDelayed(SCAFormEditor.getFieldBindingDelay(),
				((BasicSimplePropertyComposite) getComposite()).getMinText().getText()), minObserver, EMFEmptyStringToNullUpdateValueStrategy.INSTANCE, null);
		return this.minBinding;
	}

	public Binding bindMax(final DataBindingContext context, final Text maxText) {
		final IEMFEditValueProperty maxProperty = EMFEditProperties.value(getEditingDomain(),
			FeaturePath.fromList(this.property.getRange(), PrfPackage.Literals.RANGE__MAX));
		final IObservableValue maxObserver = maxProperty.observe(this.input);
		this.maxBinding = context.bindValue(
			WidgetProperties.text(SWT.Modify).observeDelayed(SCAFormEditor.getFieldBindingDelay(),
				((BasicSimplePropertyComposite) getComposite()).getMaxText().getText()), maxObserver, EMFEmptyStringToNullUpdateValueStrategy.INSTANCE, null);
		return this.maxBinding;
	}

	@Override
	protected void addListeners() {
		super.addListeners();
		if (getComposite().getAddEnumButton() != null) {
			getComposite().getAddEnumButton().addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(final SelectionEvent e) {
					handleAddEnum();
				}
			});
		}
		if (getComposite().getEditEnumButton() != null) {
			getComposite().getEditEnumButton().addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(final SelectionEvent e) {
					handleEditEnumeration();
				}
			});
		}
		if (getComposite().getRemoveEnumButton() != null) {
			getComposite().getRemoveEnumButton().addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(final SelectionEvent e) {
					handleRemoveEnumeration();
				}
			});
		}
	}

	@Override
	protected BasicSimplePropertyComposite getComposite() {
		return (BasicSimplePropertyComposite) super.getComposite();
	}

	private Enumeration getEnumerationViewerSelection() {
		return (Enumeration) ((IStructuredSelection) getComposite().getEnumerationViewer().getSelection()).getFirstElement();
	}

	public void handleAddEnum() {
		final EnumerationWizard wizard = new EnumerationWizard();

		final WizardDialog dialog = new WizardDialog(getPage().getSite().getShell(), wizard);

		if (dialog.open() == Window.OK) {
			final Enumeration enumeration = wizard.getEnumeration();
			if (enumeration != null) {
				Command command = null;
				if (input instanceof Simple) {
					Enumerations enums = ((Simple) this.input).getEnumerations();
					if (enums == null) {
						enums = PrfFactory.eINSTANCE.createEnumerations();
						enums.getEnumeration().add(enumeration);
						command = SetCommand.create(getEditingDomain(), this.input, PrfPackage.Literals.SIMPLE__ENUMERATIONS, enums);
					} else {
						command = AddCommand.create(getEditingDomain(), enums, PrfPackage.Literals.ENUMERATIONS__ENUMERATION, enumeration);
					}
				}
				execute(command);
			}
		}
	}

	protected void handleEditEnumeration() {
		final EnumerationWizard wizard = new EnumerationWizard();
		final Enumeration enumeration = getEnumerationViewerSelection();

		wizard.setEnumeration(enumeration);

		final WizardDialog dialog = new WizardDialog(getPage().getSite().getShell(), wizard);

		if (dialog.open() == Window.OK && input instanceof Simple) {
			Simple simple = (Simple) input;
			final Command command = ReplaceCommand.create(getEditingDomain(), simple.getEnumerations(), PrfPackage.Literals.ENUMERATIONS__ENUMERATION,
				enumeration, Collections.singleton(wizard.getEnumeration()));
			execute(command);
		}
	}

	protected void handleRemoveEnumeration() {
		Command command = null;
		if (input instanceof Simple) {
			Simple simple = (Simple) this.input;
			if (simple.getEnumerations() != null) {
				command = RemoveCommand.create(getEditingDomain(), simple.getEnumerations(), PrfPackage.Literals.ENUMERATIONS__ENUMERATION,
					getEnumerationViewerSelection());
				if (simple.getEnumerations().getEnumeration().size() - 1 == 0) {
					command = SetCommand.create(getEditingDomain(), this.input, PrfPackage.Literals.SIMPLE__ENUMERATIONS, null);
				}
			}
		}
		if (command != null && command.canExecute()) {
			execute(command);
		}
	}

	private UpdateValueStrategy createStringToBoolean() {
		EMFUpdateValueStrategy strategy = new EMFUpdateValueStrategy();
		strategy.setConverter(new Converter(String.class, Boolean.class) {

			@Override
			public Object convert(Object fromObject) {
				if (fromObject instanceof String) {
					String fromObjectStr = ((String) fromObject).trim();
					if (fromObjectStr.isEmpty()) {
						return null;
					}
					if ("true".equalsIgnoreCase(fromObjectStr)) {
						return Boolean.TRUE;
					} else {
						return Boolean.FALSE;
					}
				}
				return null;
			}
		});
		return strategy;
	}

	private UpdateValueStrategy createBooleanToString() {
		EMFUpdateValueStrategy strategy = new EMFUpdateValueStrategy();
		strategy.setConverter(new Converter(Boolean.class, String.class) {

			@Override
			public Object convert(Object fromObject) {
				if (fromObject == null) {
					return "";
				}
				if (fromObject instanceof Boolean) {
					return ((Boolean) fromObject).toString();
				} else {
					return ""; // return empty string for unknown
				}
			}
		});
		return strategy;
	}

}

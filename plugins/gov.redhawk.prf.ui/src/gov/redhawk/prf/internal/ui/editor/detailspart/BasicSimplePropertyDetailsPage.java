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
import java.util.List;

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

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.Converter;
import org.eclipse.core.databinding.observable.list.IListChangeListener;
import org.eclipse.core.databinding.observable.list.ListChangeEvent;
import org.eclipse.core.databinding.observable.list.WritableList;
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
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
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
	private WritableList kindList = new WritableList();
	{
		kindList.addListChangeListener(new IListChangeListener() {

			public void handleListChange(ListChangeEvent event) {
				List<PropertyConfigurationType> newChecked = new ArrayList<PropertyConfigurationType>();
				for (Object obj : kindList) {
					if (obj instanceof Kind) {
						newChecked.add(((Kind) obj).getType());
					} else if (obj instanceof PropertyConfigurationType) {
						newChecked.add((PropertyConfigurationType) obj);
					}
				}
				((BasicSimplePropertyComposite) getComposite()).getKindViewer().setCheckedElements(newChecked.toArray());
			}
		});
	}

	@Override
	public void dispose() {
		super.dispose();
		kindList.dispose();
		kindList = null;
	}

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
		final BasicSimplePropertyComposite composite = (BasicSimplePropertyComposite) getComposite();

		final EditingDomain domain = getEditingDomain();
		this.input = input;
		this.property = getProperty(this.input);

		final List<Binding> retVal = super.bind(dataBindingContext, input);

		// Type
		if (composite.getTypeViewer() != null) {
			retVal.add(dataBindingContext.bindValue(ViewersObservables.observeSingleSelection(composite.getTypeViewer()),
				EMFEditObservables.observeValue(domain, input, this.property.getType()), null, null));
		}

		if (composite.getTypeModifier() != null) {
			retVal.add(dataBindingContext.bindValue(SWTObservables.observeSelection(composite.getTypeModifier()),
				EMFEditObservables.observeValue(domain, input, this.property.getTypeModifier()), createTypeModifierTargetToModel(),
				createTypeModifierModelTarget()));
		}

		// Units
		if (getComposite().getUnitsEntry() != null) {
			retVal.add(dataBindingContext.bindValue(
				WidgetProperties.text(SWT.Modify).observeDelayed(SCAFormEditor.getFieldBindingDelay(), composite.getUnitsEntry().getText()),
				EMFEditObservables.observeValue(domain, input, this.property.getUnits()), new EMFEmptyStringToNullUpdateValueStrategy(), null));
		}

		// Kind
		if (getComposite().getKindViewer() != null) {
			composite.getKindViewer().setCheckedElements(Collections.EMPTY_LIST.toArray());
			createKindBinding(dataBindingContext, input, domain, retVal);
		}

		// Action
		if (getComposite().getActionViewer() != null) {
			retVal.add(dataBindingContext.bindValue(ViewersObservables.observeSingleSelection(composite.getActionViewer()),
				EMFEditObservables.observeValue(domain, input, this.property.getAction()), createActionTargetToModel(), createActionModelToTarget()));
		}

		// Range
		if (getComposite().getRangeButton() != null) {
			retVal.addAll(bindRanges(input, dataBindingContext, domain));
			addRangeListener();
		}

		return retVal;
	}

	private UpdateValueStrategy createTypeModifierModelTarget() {
		EMFUpdateValueStrategy strategy = new EMFUpdateValueStrategy();
		strategy.setConverter(new Converter(Boolean.class, String.class) {

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

			public Object convert(Object fromObject) {
				if (fromObject instanceof String) {
					if (fromObject.toString().equals("")) {
						return null;
					}
					if (fromObject.toString().equalsIgnoreCase("real")) {
						return false;
					} else if (fromObject.toString().equalsIgnoreCase("complex")) {
						return true;
					}
				}
				return null;
			}
		});
		return strategy;
	}

	/**
	 * Creates the action mode to target.
	 * 
	 * @return the update value strategy
	 */
	private UpdateValueStrategy createActionModelToTarget() {
		final EMFUpdateValueStrategy strategy = new EMFUpdateValueStrategy();
		strategy.setConverter(new Converter(Action.class, Object.class) {

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

	/**
	 * Creates the action target to model.
	 * 
	 * @return the update value strategy
	 */
	private UpdateValueStrategy createActionTargetToModel() {
		final EMFUpdateValueStrategy strategy = new EMFUpdateValueStrategy();
		strategy.setConverter(new Converter(Object.class, ActionType.class) {

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

	/**
	 * Bind ranges.
	 * 
	 * @param domain
	 * @param retVal
	 */
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

	/**
	 * Creates the kind binding.
	 * 
	 * @param context
	 * @param retVal
	 */
	private void createKindBinding(final DataBindingContext context, final EObject input, final EditingDomain domain, final List<Binding> retVal) {
		retVal.add(context.bindList(kindList, EMFEditObservables.observeList(getEditingDomain(), input, BasicSimplePropertyDetailsPage.this.property.getKind())));
	}

	@Override
	protected void createSpecificContent(Composite parent) {
		super.createSpecificContent(parent);
		if (getComposite().getKindViewer() != null) {
			getComposite().getKindViewer().addCheckStateListener(new ICheckStateListener() {

				public void checkStateChanged(CheckStateChangedEvent event) {
					if (event.getChecked()) {
						Kind kind = PrfFactory.eINSTANCE.createKind();
						kind.setType((PropertyConfigurationType) event.getElement());
						kindList.add(kind);
					} else {
						for (Object obj : kindList) {
							if (obj instanceof Kind && ((Kind) obj).getType() == event.getElement()) {
								kindList.remove(obj);
								break;
							}
						}
					}
				}
			});
		}
	}

	public List<Binding> bindButton(final DataBindingContext context, final Button rangeButton, final Text minText, final Text maxText) {

		final EMFUpdateValueStrategy targetToModel = new EMFUpdateValueStrategy();
		targetToModel.setConverter(new Converter(Boolean.class, Range.class) {

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

			public Object convert(final Object fromObject) {
				return fromObject != null;
			}

		});

		final List<Binding> buttonBindings = new ArrayList<Binding>();
		buttonBindings.add(context.bindValue(SWTObservables.observeSelection(rangeButton),
			EMFEditObservables.observeValue(getEditingDomain(), this.input, this.property.getRange()), targetToModel, modelToTarget));

		buttonBindings.add(context.bindValue(SWTObservables.observeEnabled(minText), SWTObservables.observeSelection(rangeButton)));
		buttonBindings.add(context.bindValue(SWTObservables.observeEnabled(maxText), SWTObservables.observeSelection(rangeButton)));
		return buttonBindings;
	}

	public Binding bindMin(final DataBindingContext context, final Text minText) {
		final IEMFEditValueProperty minProperty = EMFEditProperties.value(getEditingDomain(),
			FeaturePath.fromList(this.property.getRange(), PrfPackage.Literals.RANGE__MIN));
		final IObservableValue minObserver = minProperty.observe(this.input);
		return this.minBinding = context.bindValue(
			WidgetProperties.text(SWT.Modify).observeDelayed(SCAFormEditor.getFieldBindingDelay(),
				((BasicSimplePropertyComposite) getComposite()).getMinText().getText()), minObserver, EMFEmptyStringToNullUpdateValueStrategy.INSTANCE, null);
	}

	public Binding bindMax(final DataBindingContext context, final Text maxText) {
		final IEMFEditValueProperty maxProperty = EMFEditProperties.value(getEditingDomain(),
			FeaturePath.fromList(this.property.getRange(), PrfPackage.Literals.RANGE__MAX));
		final IObservableValue maxObserver = maxProperty.observe(this.input);
		return this.maxBinding = context.bindValue(
			WidgetProperties.text(SWT.Modify).observeDelayed(SCAFormEditor.getFieldBindingDelay(),
				((BasicSimplePropertyComposite) getComposite()).getMaxText().getText()), maxObserver, EMFEmptyStringToNullUpdateValueStrategy.INSTANCE, null);
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

	/**
	 * Handle edit enumeration.
	 */
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

	/**
	 * Handle enumeration removed.
	 */
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

}

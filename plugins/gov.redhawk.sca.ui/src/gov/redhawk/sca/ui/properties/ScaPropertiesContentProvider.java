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
package gov.redhawk.sca.ui.properties;

import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaPropertyContainer;
import gov.redhawk.model.sca.commands.ScaModelCommand;
import gov.redhawk.model.sca.commands.ScaModelCommandWithResult;
import gov.redhawk.sca.internal.ui.properties.ScaSimplePropertyValuePropertyDescriptor;
import gov.redhawk.sca.internal.ui.properties.SequencePropertyValueDescriptor;
import gov.redhawk.sca.ui.ScaModelAdapterFactoryContentProvider;
import gov.redhawk.sca.ui.ScaUiPlugin;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.jface.viewers.ICellEditorListener;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.omg.CORBA.Any;

import CF.PropertySetPackage.InvalidConfiguration;
import CF.PropertySetPackage.PartialConfiguration;

/**
 * @since 9.0
 */
public class ScaPropertiesContentProvider extends ScaModelAdapterFactoryContentProvider {

	private static class ValueWrapperPropertySource extends PropertySource {

		public ValueWrapperPropertySource(final Object object, final IItemPropertySource itemPropertySource) {
			super(object, itemPropertySource);
		}

		@Override
		protected IPropertyDescriptor createPropertyDescriptor(final IItemPropertyDescriptor itemPropertyDescriptor) {
			final Object genericFeature = itemPropertyDescriptor.getFeature(this.object);
			if (genericFeature == ScaPackage.Literals.SCA_SIMPLE_SEQUENCE_PROPERTY__VALUES) {
				return new SequencePropertyValueDescriptor(this.object, itemPropertyDescriptor);
			} else if (genericFeature == ScaPackage.Literals.SCA_STRUCT_SEQUENCE_PROPERTY__STRUCTS) {
				return new SequencePropertyValueDescriptor(this.object, itemPropertyDescriptor);
			} else if (genericFeature == ScaPackage.Literals.SCA_SIMPLE_PROPERTY__VALUE) {
				return new ScaSimplePropertyValuePropertyDescriptor(this.object, itemPropertyDescriptor);
			} else {
				return super.createPropertyDescriptor(itemPropertyDescriptor);
			}
		}

		@Override
		public void setPropertyValue(final Object propertyId, final Object value) {
			final ScaAbstractProperty< ? > prop = (ScaAbstractProperty< ? >) this.object;
			final Any any = ScaModelCommandWithResult.execute(prop, new ScaModelCommandWithResult<Any>() {

				@Override
				public void execute() {
					prop.setIgnoreRemoteSet(true);
					final Any oldValue = prop.toAny();
					ValueWrapperPropertySource.super.setPropertyValue(propertyId, value);
					if (!prop.valueEquals(oldValue)) {
						setResult(prop.toAny());
					}
				}
			});
			try {
				if (any != null) {
					final Job setJob = new Job("Setting property value") {

						@Override
						protected IStatus run(final IProgressMonitor monitor) {
							try {
								prop.setRemoteValue(any);
							} catch (final PartialConfiguration e) {
								return new Status(IStatus.WARNING, ScaUiPlugin.PLUGIN_ID, "Setting property: " + prop.getId() + " partial configuration. "
								        + e.getMessage(), e);
							} catch (final InvalidConfiguration e) {
								return new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, "Failed to set property '" + prop.getId()
								        + "', due to Invalid Configuration. " + e.msg, e);
							} finally {
								for (EObject parent = prop.eContainer(); parent != null; parent = parent.eContainer()) {
									if (parent instanceof ScaPropertyContainer< ? , ? >) {
										final ScaPropertyContainer< ? , ? > container = (ScaPropertyContainer< ? , ? >) parent;
										container.fetchProperties(monitor);
										break;
									}
								}
							}
							return Status.OK_STATUS;
						}

					};
					setJob.schedule();
				}
			} finally {
				ScaModelCommand.execute(prop, new ScaModelCommand() {

					@Override
					public void execute() {
						prop.setIgnoreRemoteSet(false);
					}
				});
			}
		}
	}

	/**
	 * @since 9.0
	 */
	public ScaPropertiesContentProvider(final AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * 
	 * @param adapterFactory
	 * @param cellListener
	 * @param object2
	 * @deprecated Use {@link #ScaPropertiesContentProvider(AdapterFactory)}
	 */
	@Deprecated
	public ScaPropertiesContentProvider(final AdapterFactory adapterFactory, final Listener cellListener, final ICellEditorListener object2) {
		this(adapterFactory);
	}

	@Override
	protected IPropertySource createPropertySource(final Object object, final IItemPropertySource itemPropertySource) {
		final ValueWrapperPropertySource retVal = new ValueWrapperPropertySource(object, itemPropertySource);
		return wrap(run(new RunnableWithResult.Impl<IPropertySource>() {
			@Override
			public void run() {
				setResult(retVal);
			}
		}));
	}

}

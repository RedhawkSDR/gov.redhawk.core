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

import java.util.concurrent.ExecutionException;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.emf.transaction.RunnableWithResult;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.omg.CORBA.Any;

import CF.PropertySetPackage.InvalidConfiguration;
import CF.PropertySetPackage.PartialConfiguration;
import gov.redhawk.model.sca.ScaAbstractProperty;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaPropertyContainer;
import gov.redhawk.model.sca.commands.ScaModelCommandWithResult;
import gov.redhawk.sca.internal.ui.properties.ScaSimplePropertyValuePropertyDescriptor;
import gov.redhawk.sca.internal.ui.properties.SequencePropertyValueDescriptor;
import gov.redhawk.sca.ui.ScaModelAdapterFactoryContentProvider;
import gov.redhawk.sca.ui.ScaUiPlugin;
import mil.jpeojtrs.sca.util.CFErrorFormatter;
import mil.jpeojtrs.sca.util.CorbaUtils2;
import mil.jpeojtrs.sca.util.ScaEcoreUtils;

/**
 * A content provider for SCA model property objects. Supports both design time and runtime.
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

			// The new model object's value, if different than the old, will be returned
			final Any any = ScaModelCommandWithResult.execute(prop, new ScaModelCommandWithResult<Any>() {

				@Override
				public void execute() {
					// Don't perform any CORBA calls
					boolean originalIgnore = prop.isIgnoreRemoteSet();
					prop.setIgnoreRemoteSet(true);

					try {
						// Set the value; compare to the old value
						final Any oldValue = prop.toAny();
						ValueWrapperPropertySource.super.setPropertyValue(propertyId, value);
						if (!originalIgnore && !prop.valueEquals(oldValue)) {
							// New value is different than old, and setting the remote CORBA object wasn't already
							// suppressed. Return the new value.
							setResult(prop.toAny());
						}
					} finally {
						// Restore flag
						prop.setIgnoreRemoteSet(false);
					}
				}
			});

			if (any == null) {
				// Property value is not affected, or shouldn't be remotely set
				return;
			}

			// Use a job to set the remote value via CORBA call
			final String jobText = String.format("Setting value for property %s", prop.getId());
			final Job setJob = Job.create(jobText, monitor -> {
				boolean okay = true;
				try {
					IStatus status = CorbaUtils2.invoke(() -> {
						try {
							prop.setRemoteValue(any);
							return Status.OK_STATUS;
						} catch (final PartialConfiguration e) {
							return new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, CFErrorFormatter.format(e), e);
						} catch (final InvalidConfiguration e) {
							return new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, CFErrorFormatter.format(e), e);
						}
					}, monitor);
					if (!status.isOK()) {
						okay = false;
					}
					return status;
				} catch (ExecutionException e) {
					okay = false;
					return new Status(IStatus.ERROR, ScaUiPlugin.PLUGIN_ID, "Unable to set remote property value", e.getCause());
				} finally {
					// If there was a problem, we fetch properties to try and get them back in a representative
					// state. If there wasn't a problem, the model should already be consistent.
					if (!okay) {
						final ScaPropertyContainer< ? , ? > parent = ScaEcoreUtils.getEContainerOfType(prop, ScaPropertyContainer.class);
						try {
							CorbaUtils2.invoke(() -> {
								parent.fetchProperties(monitor);
								return null;
							}, monitor);
						} catch (ExecutionException e) {
							// PASS
						}
					}
				}
			});
			setJob.schedule();
		}
	}

	/**
	 * @since 9.0
	 */
	public ScaPropertiesContentProvider(final AdapterFactory adapterFactory) {
		super(adapterFactory);
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

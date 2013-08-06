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
package gov.redhawk.partitioning.validation.tests;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.validation.EMFEventType;
import org.eclipse.emf.validation.IValidationContext;
import org.eclipse.emf.validation.model.Category;
import org.eclipse.emf.validation.model.ConstraintSeverity;
import org.eclipse.emf.validation.model.ConstraintStatus;
import org.eclipse.emf.validation.model.EvaluationMode;
import org.eclipse.emf.validation.model.IModelConstraint;
import org.eclipse.emf.validation.service.IConstraintDescriptor;

public class PartitioningTestsHelper {

	public PartitioningTestsHelper() {

	}

	protected EObject someObject; // SUPPRESS CHECKSTYLE Protected field

	public IValidationContext createSuccessfulValidationContext(final EObject someObject) {
		this.someObject = someObject;

		final IValidationContext ctx = new IValidationContext() {

			public String getCurrentConstraintId() {

				return null;
			}

			public EObject getTarget() {
				return PartitioningTestsHelper.this.someObject;
			}

			public EMFEventType getEventType() {

				return null;
			}

			public List<Notification> getAllEvents() {

				return null;
			}

			public EStructuralFeature getFeature() {

				return null;
			}

			public Object getFeatureNewValue() {

				return null;
			}

			public void skipCurrentConstraintFor(final EObject eObject) {

			}

			public void skipCurrentConstraintForAll(final Collection< ? > eObjects) {

			}

			public void disableCurrentConstraint(final Throwable exception) {

			}

			public Object getCurrentConstraintData() {

				return null;
			}

			public Object putCurrentConstraintData(final Object newData) {

				return null;
			}

			public Set<EObject> getResultLocus() {

				return null;
			}

			public void addResult(final EObject eObject) {

			}

			public void addResults(final Collection< ? extends EObject> eObjects) {

			}

			public IStatus createSuccessStatus() {
				final IStatus success = new Status(IStatus.OK, "gov.redhawk.sad.validation.tests", "Success");
				return success;
			}

			public IStatus createFailureStatus(final Object... messageArgument) {

				return null;
			}
		};

		return ctx;
	}

	public IValidationContext createUnsuccessfulValidationConstraint(final EObject someObject) {
		this.someObject = someObject;
		final IValidationContext ctx = new IValidationContext() {

			public String getCurrentConstraintId() {
				return null;
			}

			public EObject getTarget() {
				return PartitioningTestsHelper.this.someObject;
			}

			public EMFEventType getEventType() {
				return null;
			}

			public List<Notification> getAllEvents() {
				return null;
			}

			public EStructuralFeature getFeature() {
				return null;
			}

			public Object getFeatureNewValue() {
				return null;
			}

			public void skipCurrentConstraintFor(final EObject eObject) {

			}

			public void skipCurrentConstraintForAll(final Collection< ? > eObjects) {

			}

			public void disableCurrentConstraint(final Throwable exception) {

			}

			public Object getCurrentConstraintData() {
				return null;
			}

			public Object putCurrentConstraintData(final Object newData) {
				return null;
			}

			public Set<EObject> getResultLocus() {
				return null;
			}

			public void addResult(final EObject eObject) {

			}

			public void addResults(final Collection< ? extends EObject> eObjects) {

			}

			public IStatus createSuccessStatus() {
				return null;
			}

			public IStatus createFailureStatus(final Object... messageArgument) {

				final IModelConstraint constraint = new IModelConstraint() {

					public IStatus validate(final IValidationContext ctx) {

						return null;
					}

					public IConstraintDescriptor getDescriptor() {
						final IConstraintDescriptor desc = new IConstraintDescriptor() {

							public String getName() {
								return null;
							}

							public String getId() {
								return null;
							}

							public String getPluginId() {
								return "gov.redhawk.sad.validation.tests";
							}

							public String getDescription() {
								return null;
							}

							public ConstraintSeverity getSeverity() {
								return ConstraintSeverity.ERROR;
							}

							public int getStatusCode() {
								return 0;
							}

							public EvaluationMode< ? > getEvaluationMode() {
								return null;
							}

							public boolean targetsTypeOf(final EObject eObject) {
								return false;
							}

							public boolean targetsEvent(final Notification notification) {
								return false;
							}

							public boolean isBatch() {
								return false;
							}

							public boolean isLive() {
								return false;
							}

							public boolean isError() {
								return false;
							}

							public Throwable getException() {
								return null;
							}

							public boolean isEnabled() {
								return false;
							}

							public void setEnabled(final boolean enabled) {
							}

							public void setError(final Throwable exception) {

							}

							public Set<Category> getCategories() {
								return null;
							}

							public void addCategory(final Category category) {

							}

							public void removeCategory(final Category category) {

							}

							public String getMessagePattern() {
								return null;
							}

							public String getBody() {
								return null;
							}

						};

						return desc;
					}

				};

				final ConstraintStatus failure = new ConstraintStatus(constraint, this.getTarget(), messageArgument.toString(), null);
				return failure;
			}
		};
		return ctx;
	}
	
	public static URI getURI(final String filePath) throws IOException {
		final URL url = FileLocator.toFileURL(FileLocator.find(Platform.getBundle("gov.redhawk.partitioning.validation.tests"), new Path(filePath), null));
		return URI.createURI(url.toString());
	}

}

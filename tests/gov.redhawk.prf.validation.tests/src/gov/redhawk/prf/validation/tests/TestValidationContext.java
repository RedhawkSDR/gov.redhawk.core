/*******************************************************************************
 * This file is protected by Copyright. 
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under 
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at 
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package gov.redhawk.prf.validation.tests;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.Notification;
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

public class TestValidationContext implements IValidationContext {

	private EObject target;
	private String constraintId;

	public TestValidationContext(String constraintId, EObject target) {
		this.constraintId = constraintId;
		this.target = target;
	}

	@Override
	public String getCurrentConstraintId() {
		return this.constraintId;
	}

	@Override
	public EObject getTarget() {
		return this.target;
	}

	@Override
	public EMFEventType getEventType() {
		return null;
	}

	@Override
	public List<Notification> getAllEvents() {
		return null;
	}

	@Override
	public EStructuralFeature getFeature() {
		return null;
	}

	@Override
	public Object getFeatureNewValue() {
		return null;
	}

	@Override
	public void skipCurrentConstraintFor(EObject eObject) {
	}

	@Override
	public void skipCurrentConstraintForAll(Collection< ? > eObjects) {
	}

	@Override
	public void disableCurrentConstraint(Throwable exception) {
	}

	@Override
	public Object getCurrentConstraintData() {
		return null;
	}

	@Override
	public Object putCurrentConstraintData(Object newData) {
		return null;

	}

	@Override
	public Set<EObject> getResultLocus() {
		return null;
	}

	@Override
	public void addResult(EObject eObject) {
	}

	@Override
	public void addResults(Collection< ? extends EObject> eObjects) {
	}

	@Override
	public IStatus createSuccessStatus() {
		return Status.OK_STATUS;
	}

	@Override
	public IStatus createFailureStatus(Object... messageArgument) {
		return new ConstraintStatus(new IModelConstraint() {
			
			@Override
			public IStatus validate(IValidationContext ctx) {
				return new Status(IStatus.ERROR, "", "");
			}
			
			@Override
			public IConstraintDescriptor getDescriptor() {
				return new IConstraintDescriptor() {
					
					@Override
					public boolean targetsTypeOf(EObject eObject) {
						return false;
					}
					
					@Override
					public boolean targetsEvent(Notification notification) {
						return false;
					}
					
					@Override
					public void setError(Throwable exception) {
					}
					
					@Override
					public void setEnabled(boolean enabled) {
					}
					
					@Override
					public void removeCategory(Category category) {
					}
					
					@Override
					public boolean isLive() {
						return false;
					}
					
					@Override
					public boolean isError() {
						return true;
					}
					
					@Override
					public boolean isEnabled() {
						return true;
					}
					
					@Override
					public boolean isBatch() {
						return false;
					}
					
					@Override
					public int getStatusCode() {
						return 0;
					}
					
					@Override
					public ConstraintSeverity getSeverity() {
						return ConstraintSeverity.ERROR;
					}
					
					@Override
					public String getPluginId() {
						return "testPlugin";
					}
					
					@Override
					public String getName() {
						return "";
					}
					
					@Override
					public String getMessagePattern() {
						return null;
					}
					
					@Override
					public String getId() {
						return "";
					}
					
					@Override
					public Throwable getException() {
						return null;
					}
					
					@Override
					public EvaluationMode< ? > getEvaluationMode() {
						return null;
					}
					
					@Override
					public String getDescription() {
						return "";
					}
					
					@Override
					public Set<Category> getCategories() {
						return null;
					}
					
					@Override
					public String getBody() {
						return "";
					}
					
					@Override
					public void addCategory(Category category) {
					}
				};
			}
		},  this.target);
	}

}

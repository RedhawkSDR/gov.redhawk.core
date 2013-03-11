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
package gov.redhawk.scd.validation.tests;

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

	public String getCurrentConstraintId() {
		return this.constraintId;
	}

	public EObject getTarget() {
		return this.target;
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

	public void skipCurrentConstraintFor(EObject eObject) {
	}

	public void skipCurrentConstraintForAll(Collection< ? > eObjects) {
	}

	public void disableCurrentConstraint(Throwable exception) {
	}

	public Object getCurrentConstraintData() {
		return null;
	}

	public Object putCurrentConstraintData(Object newData) {
		return null;

	}

	public Set<EObject> getResultLocus() {
		return null;
	}

	public void addResult(EObject eObject) {
	}

	public void addResults(Collection< ? extends EObject> eObjects) {
	}

	public IStatus createSuccessStatus() {
		return Status.OK_STATUS;
	}

	public IStatus createFailureStatus(Object... messageArgument) {
		return new ConstraintStatus(new IModelConstraint() {
			
			public IStatus validate(IValidationContext ctx) {
				return new Status(IStatus.ERROR, "", "");
			}
			
			public IConstraintDescriptor getDescriptor() {
				return new IConstraintDescriptor() {
					
					public boolean targetsTypeOf(EObject eObject) {
						return false;
					}
					
					public boolean targetsEvent(Notification notification) {
						return false;
					}
					
					public void setError(Throwable exception) {
					}
					
					public void setEnabled(boolean enabled) {
					}
					
					public void removeCategory(Category category) {
					}
					
					public boolean isLive() {
						return false;
					}
					
					public boolean isError() {
						return true;
					}
					
					public boolean isEnabled() {
						return true;
					}
					
					public boolean isBatch() {
						return false;
					}
					
					public int getStatusCode() {
						return 0;
					}
					
					public ConstraintSeverity getSeverity() {
						return ConstraintSeverity.ERROR;
					}
					
					public String getPluginId() {
						return "testPlugin";
					}
					
					public String getName() {
						return "";
					}
					
					public String getMessagePattern() {
						return null;
					}
					
					public String getId() {
						return "";
					}
					
					public Throwable getException() {
						return null;
					}
					
					public EvaluationMode< ? > getEvaluationMode() {
						return null;
					}
					
					public String getDescription() {
						return "";
					}
					
					public Set<Category> getCategories() {
						return null;
					}
					
					public String getBody() {
						return "";
					}
					
					public void addCategory(Category category) {
					}
				};
			}
		},  this.target);
	}

}

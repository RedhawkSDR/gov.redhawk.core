/**
 * This file is protected by Copyright.
 * Please refer to the COPYRIGHT file distributed with this source distribution.
 *
 * This file is part of REDHAWK IDE.
 *
 * All rights reserved.  This program and the accompanying materials are made available under
 * the terms of the Eclipse Public License v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 */
package gov.redhawk.core.graphiti.ui.diagram.patterns;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.graphiti.features.IReason;
import org.eclipse.graphiti.features.context.IDirectEditingContext;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.PictogramElement;
import org.eclipse.graphiti.mm.pictograms.Shape;
import org.eclipse.graphiti.pattern.AbstractPattern;
import org.eclipse.graphiti.pattern.config.IPatternConfiguration;

import gov.redhawk.core.graphiti.ui.util.DUtil;
import mil.jpeojtrs.sca.util.CollectionUtils;

public abstract class AbstractContainerPattern extends AbstractPattern {

	public AbstractContainerPattern(IPatternConfiguration patternConfiguration) {
		super(null);
	}

	/**
	 * Unless overridden, this method ensures consistency of the validation logic
	 * and associated error messages for Component, FindBy, and HostCollocation names.
	 */
	@Override
	public String checkValueValid(String value, IDirectEditingContext context) {
		return validate(getCheckValueValidName(), value);
	}

	/**
	 * If <code>getCreateName</code> does not return the desired name for an error
	 * message, this method can be overridden.
	 */
	protected String getCheckValueValidName() {
		return getCreateName();
	}

	protected Map<EObject, UpdateAction> getChildrenToUpdate(ContainerShape containerShape, List< ? extends EObject> modelChildren) {
		// Put the model children into a set for tracking
		Set<EObject> expectedChildren = new HashSet<EObject>(modelChildren);

		// Record the actions for each port shape or model stub
		Map<EObject, UpdateAction> actions = new HashMap<EObject, UpdateAction>();

		// First, check the existing shapes for removal or update
		for (Shape child : containerShape.getChildren()) {
			// Check the existence of the child business object, and try to remove it from the set of expected
			// children. This lets us know if the shape should exist (remove returns true), and any objects still
			// left in the set after checking need to be added
			EObject bo = (EObject) getBusinessObjectForPictogramElement(child);
			if (bo == null || !expectedChildren.remove(bo)) {
				// Delete non-existent or stale (no longer contained in model) child
				actions.put(child, UpdateAction.REMOVE);
			} else {
				// Ask the feature provider if the child needs an update
				IReason reason = getFeatureProvider().updateNeeded(new UpdateContext(child));
				if (reason.toBoolean()) {
					actions.put(child, UpdateAction.UPDATE);
				}
			}
		}

		// Add shapes for missing children
		for (EObject instantiation : expectedChildren) {
			actions.put(instantiation, UpdateAction.ADD);
		}
		return actions;
	}

	protected void updateChildren(ContainerShape containerShape, Map<EObject, UpdateAction> actions) {
		for (Map.Entry<EObject, UpdateAction> entry : actions.entrySet()) {
			switch (entry.getValue()) {
			case ADD:
				DUtil.addShapeViaFeature(getFeatureProvider(), containerShape, entry.getKey());
				break;
			case REMOVE:
				DUtil.removeShapeViaFeature(getFeatureProvider(), (Shape) entry.getKey());
				break;
			case UPDATE:
				updatePictogramElement((PictogramElement) entry.getKey());
				break;
			default:
				break;
			}
		}
	}

	/**
	 * Checks to see if the given String <code>value</code> is valid. Returns an error
	 * message if invalid and <code>null</code> if valid.
	 * @param valueType - should be capitalized (e.g. Component, Host Collocation, etc.)
	 * @param value
	 * @return error message
	 */
	public static String validate(String valueType, String value) {
		if (value.length() < 1) {
			return valueType + " Name must not be empty";
		}
		if (value.contains(" ")) {
			return valueType + " Name must not include spaces";
		}
		if (value.contains("\n")) {
			return valueType + " Name must not include line breaks";
		}
		// null means, that the value is valid
		return null;
	}

	/**
	 * Nested helper class for sorting child shapes based on the order of their business objects in their original
	 * containing list.
	 */
	protected class BusinessObjectListComparator implements Comparator<Shape> {
		private final List< ? > list;

		public BusinessObjectListComparator(List< ? > list) {
			this.list = list;
		}

		@Override
		public int compare(Shape o1, Shape o2) {
			return Integer.compare(getIndex(o1), getIndex(o2));
		}

		private int getIndex(Shape shape) {
			Object bo = getBusinessObjectForPictogramElement(shape);
			return list.indexOf(bo);
		}
	}

	protected boolean isSortedByBusinessObject(EList<Shape> children, List< ? > list) {
		return CollectionUtils.isSorted(children, new BusinessObjectListComparator(list));
	}

	protected boolean sortByBusinessObject(EList<Shape> children, List< ? > list) {
		Comparator<Shape> comparator = new BusinessObjectListComparator(list);
		if (!CollectionUtils.isSorted(children, comparator)) {
			ECollections.sort(children, comparator);
			return true;
		}
		return false;
	}

	/**
	 * Checks whether the provided pictogram element is linked to exactly the set of objects given. It is assumed that
	 * order does not matter and there are no duplicates.
	 *
	 * @param pictogramElement
	 * @param businessObjects
	 * @return true if the set of linked objects is exactly the set of business objects
	 */
	protected boolean isLinked(PictogramElement pictogramElement, List< ? > businessObjects) {
		Object[] linkedObjects = getMappingProvider().getAllBusinessObjectsForPictogramElement(pictogramElement);
		if (businessObjects.size() != linkedObjects.length) {
			return false;
		}
		return businessObjects.containsAll(Arrays.asList(linkedObjects));
	}
}

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
* Identification: $Revision: 4927 $
*/

package gov.redhawk.diagram.edit.policies;

import gov.redhawk.diagram.part.PartitioningVisualIDRegistry;
import gov.redhawk.diagram.part.PartitioningVisualIDRegistry.MAPPING_ID;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import mil.jpeojtrs.sca.partitioning.FindByStub;
import mil.jpeojtrs.sca.partitioning.PartitioningPackage;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.gmf.runtime.diagram.ui.editpolicies.CanonicalEditPolicy;
import org.eclipse.gmf.runtime.notation.View;

/**
 * @since 3.0
 * 
 */
public class FindByStubCanonicalEditPolicy extends CanonicalEditPolicy {

	/**
	* 
	*/
	private Set<EStructuralFeature> myFeaturesToSynchronize;
	private final PartitioningVisualIDRegistry visualIdRegistry;

	public FindByStubCanonicalEditPolicy(final PartitioningVisualIDRegistry visualIdRegistry) {
		this.visualIdRegistry = visualIdRegistry;
	}

	/**
	* 
	*/
	@Override
	public Set<EStructuralFeature> getFeaturesToSynchronize() {
		if (this.myFeaturesToSynchronize == null) {
			this.myFeaturesToSynchronize = new HashSet<EStructuralFeature>();
			this.myFeaturesToSynchronize.add(PartitioningPackage.eINSTANCE.getFindByStub_Provides());
			this.myFeaturesToSynchronize.add(PartitioningPackage.eINSTANCE.getFindByStub_Uses());
			this.myFeaturesToSynchronize.add(PartitioningPackage.eINSTANCE.getFindByStub_Interface());
		}
		return this.myFeaturesToSynchronize;
	}

	/**
	* 
	*/
	@Override
	@SuppressWarnings("rawtypes")
	public List getSemanticChildrenList() {
		final LinkedList<EObject> result = new LinkedList<EObject>();
		result.add(getSemanticHost().getInterface());
		result.addAll(getSemanticHost().getProvides());
		result.addAll(getSemanticHost().getUses());
		return result;
	}

	@Override
	public FindByStub getSemanticHost() {
		return (FindByStub) super.getSemanticHost();
	}

	/**
	* 
	*/
	@Override
	public boolean isOrphaned(final Collection<EObject> semanticChildren, final View view) {
		return isMyDiagramElement(view) && !semanticChildren.contains(view.getElement());
	}

	/**
	* 
	*/
	public boolean isMyDiagramElement(final View view) {
		final MAPPING_ID visualIDMapping = this.visualIdRegistry.getMappingID(view);
		return visualIDMapping == MAPPING_ID.ProvidesPortStubEditPart || visualIDMapping == MAPPING_ID.UsesPortStubEditPart
		        || visualIDMapping == MAPPING_ID.ComponentSupportedInterfaceStubEditPart;
	}
}

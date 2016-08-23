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
package gov.redhawk.core.graphiti.sad.ui.utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalCommandStack;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.graphiti.features.IFeatureProvider;
import org.eclipse.graphiti.features.IUpdateFeature;
import org.eclipse.graphiti.features.context.impl.UpdateContext;
import org.eclipse.graphiti.mm.pictograms.ContainerShape;
import org.eclipse.graphiti.mm.pictograms.Diagram;
import org.eclipse.graphiti.mm.pictograms.Shape;

import gov.redhawk.core.graphiti.sad.ui.ext.ComponentShape;
import gov.redhawk.core.graphiti.ui.util.DUtil;
import mil.jpeojtrs.sca.sad.AssemblyController;
import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.sad.SadComponentInstantiationRef;
import mil.jpeojtrs.sca.sad.SadFactory;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;

public class SADUtils {

	private SADUtils() {
	}

	/**
	 * Recursively find all {@link ComponentShape}s in the diagram.
	 * @param containerShape
	 * @return
	 */
	public static List<ComponentShape> getAllComponentShapes(Diagram diagram) {
		List<ComponentShape> children = new ArrayList<ComponentShape>();
		for (Shape s : diagram.getChildren()) {
			if (s instanceof ContainerShape) {
				getAllComponentShapesInternal(children, (ContainerShape) s);
			}
		}
		return children;
	}

	private static void getAllComponentShapesInternal(List<ComponentShape> children, ContainerShape containerShape) {
		if (containerShape instanceof ComponentShape) {
			children.add((ComponentShape) containerShape);
		} else {
			for (Shape s : containerShape.getChildren()) {
				if (s instanceof ContainerShape) {
					getAllComponentShapesInternal(children, (ContainerShape) s);
				}
			}
		}
	}

	/**
	 * Get the assembly controller. Checks that the component instantiation ref is present first.
	 * @param featureProvider
	 * @param diagram
	 * @return
	 */
	private static AssemblyController getAssemblyController(IFeatureProvider featureProvider, Diagram diagram) {
		final SoftwareAssembly sad = DUtil.getDiagramSAD(diagram);
		if (sad.getAssemblyController() != null && sad.getAssemblyController().getComponentInstantiationRef() != null) {
			return sad.getAssemblyController();
		}
		return null;

	}

	/**
	 * Finds a component instantiation with the provided start order.
	 * @param sad
	 * @param startOrder
	 * @return
	 */
	public static SadComponentInstantiation getComponentInstantiationViaStartOrder(final SoftwareAssembly sad, final BigInteger startOrder) {
		for (SadComponentInstantiation ci : sad.getAllComponentInstantiations()) {
			if (ci.getStartOrder() != null && ci.getStartOrder().compareTo(startOrder) == 0) {
				return ci;
			}
		}
		return null;
	}

	/**
	 * Organize start order.
	 * <ul>
	 * <li>If there is no assembly controller, the earliest started component becomes it</li>
	 * <li>The assembly controller gets start order 0</li>
	 * <li>Components with start orders may have them modified so that each component is only +1 ahead of the previous
	 * component</li>
	 * </ul>
	 * @param sad
	 * @param diagram
	 * @param featureProvider
	 */
	public static void organizeStartOrder(final SoftwareAssembly sad, final Diagram diagram, final IFeatureProvider featureProvider) {
		BigInteger startOrder = BigInteger.ZERO;

		// get components by start order
		EList<SadComponentInstantiation> componentInstantiationsInStartOrder = sad.getComponentInstantiationsInStartOrder();

		// if assembly controller was deleted (or component that used to be assembly controller was deleted)
		// set a new assembly controller
		AssemblyController assemblyController = getAssemblyController(featureProvider, diagram);
		if ((assemblyController == null || assemblyController.getComponentInstantiationRef().getInstantiation() == null)
			&& componentInstantiationsInStartOrder.size() > 0) {
			// assign assembly controller assign to first component
			assemblyController = SadFactory.eINSTANCE.createAssemblyController();
			SadComponentInstantiation ci = componentInstantiationsInStartOrder.get(0);
			SadComponentInstantiationRef sadComponentInstantiationRef = SadFactory.eINSTANCE.createSadComponentInstantiationRef();
			sadComponentInstantiationRef.setInstantiation(ci);
			assemblyController.setComponentInstantiationRef(sadComponentInstantiationRef);
			sad.setAssemblyController(assemblyController);

			// If the component has a start order defined, update it to run first
			if (ci.getStartOrder() != null) {
				ci.setStartOrder(BigInteger.ZERO);
			}

		}

		if (assemblyController != null && assemblyController.getComponentInstantiationRef() != null) {
			final SadComponentInstantiation ci = assemblyController.getComponentInstantiationRef().getInstantiation();
			// first check to make sure start order is set to zero
			if (ci != null && ci.getStartOrder() != null && ci.getStartOrder() != BigInteger.ZERO) {
				TransactionalEditingDomain editingDomain = featureProvider.getDiagramTypeProvider().getDiagramBehavior().getEditingDomain();
				TransactionalCommandStack stack = (TransactionalCommandStack) editingDomain.getCommandStack();
				stack.execute(new RecordingCommand(editingDomain) {

					@Override
					protected void doExecute() {
						ci.setStartOrder(BigInteger.ZERO);
					}
				});
			}

			// remove assembly controller from list, it has already been updated
			componentInstantiationsInStartOrder.remove(assemblyController.getComponentInstantiationRef().getInstantiation());
		}

		// set start order
		for (final SadComponentInstantiation ci : componentInstantiationsInStartOrder) {
			// Don't update start order if it has not already been declared for this component
			if (ci.getStartOrder() != null) {
				startOrder = startOrder.add(BigInteger.ONE);

				// Only call the update if a change is needed
				if (ci.getStartOrder().intValue() != startOrder.intValue()) {
					final BigInteger newStartOrder = startOrder;
					TransactionalEditingDomain editingDomain = featureProvider.getDiagramTypeProvider().getDiagramBehavior().getEditingDomain();
					TransactionalCommandStack stack = (TransactionalCommandStack) editingDomain.getCommandStack();
					stack.execute(new RecordingCommand(editingDomain) {

						@Override
						protected void doExecute() {
							ci.setStartOrder(newStartOrder);

							// Force pictogram elements to update
							ComponentShape componentShape = (ComponentShape) DUtil.getPictogramElementForBusinessObject(diagram, ci, ComponentShape.class);
							UpdateContext context = new UpdateContext(componentShape);
							IUpdateFeature feature = featureProvider.getUpdateFeature(context);
							feature.execute(context);
						}
					});
				}
			}
		}
	}

	/**
	 * Swap start order of provided components. Change assembly controller if start order zero.
	 * @param sad
	 * @param featureProvider
	 * @param lowerCi - The component that currently has the lower start order
	 * @param higherCi - The component that currently has the higher start order
	 */
	public static void swapStartOrder(SoftwareAssembly sad, IFeatureProvider featureProvider, final SadComponentInstantiation lowerCi,
		final SadComponentInstantiation higherCi) {

		// editing domain for our transaction
		TransactionalEditingDomain editingDomain = featureProvider.getDiagramTypeProvider().getDiagramBehavior().getEditingDomain();

		// get AssemblyController
		final AssemblyController assemblyController = sad.getAssemblyController();

		// Perform business object manipulation in a Command
		TransactionalCommandStack stack = (TransactionalCommandStack) editingDomain.getCommandStack();
		stack.execute(new RecordingCommand(editingDomain) {
			@Override
			protected void doExecute() {

				// Increment start order
				lowerCi.setStartOrder(higherCi.getStartOrder());
				// Decrement start order
				higherCi.setStartOrder(higherCi.getStartOrder().subtract(BigInteger.ONE));

				// set assembly controller if start order is zero
				if (lowerCi.getStartOrder().compareTo(BigInteger.ZERO) == 0) {
					assemblyController.getComponentInstantiationRef().setInstantiation(lowerCi);
				} else if (higherCi.getStartOrder().compareTo(BigInteger.ZERO) == 0) {
					assemblyController.getComponentInstantiationRef().setInstantiation(higherCi);
				}
			}
		});
	}
}

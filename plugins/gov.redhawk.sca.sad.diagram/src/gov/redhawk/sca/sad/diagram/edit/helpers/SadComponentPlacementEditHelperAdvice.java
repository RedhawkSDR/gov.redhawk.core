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

// BEGIN GENERATED CODE
package gov.redhawk.sca.sad.diagram.edit.helpers;

import gov.redhawk.diagram.DiagramUtil;
import gov.redhawk.diagram.edit.helpers.ComponentPlacementEditHelperAdvice;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import mil.jpeojtrs.sca.partitioning.ComponentFile;
import mil.jpeojtrs.sca.partitioning.ComponentFiles;
import mil.jpeojtrs.sca.partitioning.NamingService;
import mil.jpeojtrs.sca.partitioning.PartitioningFactory;
import mil.jpeojtrs.sca.sad.AssemblyController;
import mil.jpeojtrs.sca.sad.FindComponent;
import mil.jpeojtrs.sca.sad.HostCollocation;
import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.sad.SadComponentInstantiationRef;
import mil.jpeojtrs.sca.sad.SadComponentPlacement;
import mil.jpeojtrs.sca.sad.SadFactory;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.spd.SoftPkg;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.gmf.runtime.common.core.command.CommandResult;
import org.eclipse.gmf.runtime.common.core.command.ICommand;
import org.eclipse.gmf.runtime.emf.commands.core.command.AbstractTransactionalCommand;
import org.eclipse.gmf.runtime.emf.type.core.requests.ConfigureRequest;
import org.eclipse.gmf.runtime.emf.type.core.requests.DestroyElementRequest;

public class SadComponentPlacementEditHelperAdvice extends ComponentPlacementEditHelperAdvice<SadComponentInstantiation, SadComponentPlacement> {

	public SadComponentPlacementEditHelperAdvice() {
		super();
	}

	/**
	 * @since 2.0
	 */
	@Override
	public SadComponentInstantiation createComponentInstantiation(final ConfigureRequest request, final SoftPkg spd) {
		final Object element = request.getParameter(ComponentPlacementEditHelperAdvice.CONFIGURE_COMPONENT_INSTANTIATION);
		final SadComponentInstantiation retval;
		if (element instanceof SadComponentInstantiation) {
			retval = (SadComponentInstantiation) element;
		} else {
			retval = SadFactory.eINSTANCE.createSadComponentInstantiation();
		}
		
		final EObject eobj = EcoreUtil.getRootContainer(getObjectToConfigure(request));
		Assert.isTrue(eobj instanceof SoftwareAssembly);
		final SoftwareAssembly sa = (SoftwareAssembly) eobj;
		
		String compName = getInstantiationName(request);
		if (compName == null) {
			compName =  SoftwareAssembly.Util.createComponentUsageName(sa, spd.getName());
		}
		
		String id = getInstantiationID(request);
		if (id == null) {
			id = SoftwareAssembly.Util.createComponentIdentifier(sa, compName);
		}
		
		retval.setUsageName(compName);
		retval.setId(id);
		
		final FindComponent findComponent = SadFactory.eINSTANCE.createFindComponent();
		final NamingService namingService = PartitioningFactory.eINSTANCE.createNamingService();
		namingService.setName(compName);
		findComponent.setNamingService(namingService);
		retval.setFindComponent(findComponent);
		
		return retval;
	}

	@Override
	protected ICommand getAfterConfigureCommand(final ConfigureRequest request) {
		return new ConfigureCommand(request) {
			@Override
			protected CommandResult doExecuteWithResult(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
				final SadComponentPlacement cp = (SadComponentPlacement) this.request.getElementToConfigure();
				final SoftwareAssembly softwareAssembly = SoftwareAssembly.Util.getSoftwareAssembly(cp.eResource());
				// check if we are in the Sandbox/LocalSCA file
				final boolean inSandbox = DiagramUtil.isDiagramLocalSandbox(softwareAssembly.eResource());
				
				if (!inSandbox) { // don't add start order or assembly controller to components in sandbox
					AssemblyController asm = softwareAssembly.getAssemblyController();
					
					if (asm == null) {
						asm = SadFactory.eINSTANCE.createAssemblyController();
						softwareAssembly.setAssemblyController(asm);
					}
	
					if (asm.getComponentInstantiationRef() == null) {
						final SadComponentInstantiationRef instRef = SadFactory.eINSTANCE.createSadComponentInstantiationRef();
						asm.setComponentInstantiationRef(instRef);
					}
	
					if (asm.getComponentInstantiationRef().getRefid() == null) {
						Assert.isTrue(cp.getComponentInstantiation().size() == 1);  // assumes only one component instantiation
						asm.getComponentInstantiationRef().setRefid(cp.getComponentInstantiation().get(0).getId());
						cp.getComponentInstantiation().get(0).setStartOrder(BigInteger.valueOf(0)); // assembly controller always has start-order 0
					} else {
						int startOrder = SoftwareAssembly.Util.getLastStartOrder(softwareAssembly);
						startOrder += 1; // begin with the next start order
						for (final SadComponentInstantiation ci : cp.getComponentInstantiation()) {
							if (ci.getStartOrder() == null) {
								ci.setStartOrder(BigInteger.valueOf(startOrder));
								startOrder += 1;
							}
						}
					}
				}
				
				return CommandResult.newOKCommandResult();
			}
		};
	}

	@Override
	public ComponentFile createComponentFile() {
		return SadFactory.eINSTANCE.createComponentFile();
	}

	@Override
	public SadComponentPlacement getObjectToConfigure(final ConfigureRequest request) {
		return (SadComponentPlacement) request.getElementToConfigure();
	}

	@Override
	public void setComponentFiles(final SadComponentPlacement obj, final ComponentFiles files) {
		final SoftwareAssembly sad = SoftwareAssembly.Util.getSoftwareAssembly(obj.eResource());
		sad.setComponentFiles(files);
	}

	@Override
	public ComponentFiles getComponentFiles(final SadComponentPlacement obj) {
		final SoftwareAssembly sad = SoftwareAssembly.Util.getSoftwareAssembly(obj.eResource());
		return sad.getComponentFiles();
	}

	@Override
	protected ICommand getAfterDestroyElementCommand(final DestroyElementRequest request) {
		return createReassignAssemblyControllerCommand(request);
	}

	private ICommand createReassignAssemblyControllerCommand(final DestroyElementRequest request) {
		final EObject container = request.getContainer();
		final SoftwareAssembly sad = SoftwareAssembly.Util.getSoftwareAssembly(container.eResource());
		SadComponentInstantiation ci = null;

		if (request.getElementToDestroy() instanceof SadComponentPlacement) {
			ci = ((SadComponentPlacement) request.getElementToDestroy()).getComponentInstantiation().get(0);
		} else {
			ci = (SadComponentInstantiation) request.getElementToDestroy();
		}

		if (sad.getAssemblyController() != null && sad.getAssemblyController().getComponentInstantiationRef() != null
		        && !sad.getAssemblyController().getComponentInstantiationRef().getRefid().equals(ci.getId())) {
			return null;
		}

		final List<SadComponentInstantiation> allComponents = getAllComponents(sad);
		allComponents.remove(ci);

		if (allComponents.size() > 0) {
			final SadComponentInstantiation newAssem = allComponents.get(0);
			final AbstractTransactionalCommand command = new AbstractTransactionalCommand(request.getEditingDomain(), "Reassign Assembly Controller", null) {

				@Override
				protected CommandResult doExecuteWithResult(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
					final AssemblyController ac = SadFactory.eINSTANCE.createAssemblyController();
					final SadComponentInstantiationRef ref = SadFactory.eINSTANCE.createSadComponentInstantiationRef();
					ref.setInstantiation(newAssem);
					newAssem.setStartOrder(BigInteger.valueOf(0)); // assembly controller always has start-order 0
					ac.setComponentInstantiationRef(ref);
					sad.setAssemblyController(ac);
					return CommandResult.newOKCommandResult();
				}
			};

			return command;
		} else {
			final AbstractTransactionalCommand command = new AbstractTransactionalCommand(request.getEditingDomain(), "Unassign Assembly Controller", null) {

				@Override
				protected CommandResult doExecuteWithResult(final IProgressMonitor monitor, final IAdaptable info) throws ExecutionException {
					sad.setAssemblyController(null);
					return CommandResult.newOKCommandResult();
				}
			};

			return command;
		}

	}

	private List<SadComponentInstantiation> getAllComponents(final SoftwareAssembly sad) {
		final List<SadComponentInstantiation> retVal = new ArrayList<SadComponentInstantiation>();
		if (sad.getPartitioning() != null) {
			for (final SadComponentPlacement cp : sad.getPartitioning().getComponentPlacement()) {
				retVal.addAll(cp.getComponentInstantiation());
			}
			for (final HostCollocation h : sad.getPartitioning().getHostCollocation()) {
				for (final SadComponentPlacement cp : h.getComponentPlacement()) {
					retVal.addAll(cp.getComponentInstantiation());
				}
			}
		}

		return retVal;
	}
}

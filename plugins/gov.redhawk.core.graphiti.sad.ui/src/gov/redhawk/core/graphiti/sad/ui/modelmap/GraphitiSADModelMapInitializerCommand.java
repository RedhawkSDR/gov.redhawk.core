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
package gov.redhawk.core.graphiti.sad.ui.modelmap;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.AbstractCommand;

import gov.redhawk.core.graphiti.sad.ui.GraphitiSadUIPlugin;
import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaConnection;
import gov.redhawk.model.sca.ScaDevice;
import gov.redhawk.model.sca.ScaPort;
import gov.redhawk.model.sca.ScaPortContainer;
import gov.redhawk.model.sca.ScaProvidesPort;
import gov.redhawk.model.sca.ScaUsesPort;
import gov.redhawk.model.sca.ScaWaveform;
import gov.redhawk.sca.util.PluginUtil;
import mil.jpeojtrs.sca.partitioning.ComponentFile;
import mil.jpeojtrs.sca.partitioning.ComponentFileRef;
import mil.jpeojtrs.sca.partitioning.ComponentSupportedInterface;
import mil.jpeojtrs.sca.partitioning.NamingService;
import mil.jpeojtrs.sca.partitioning.PartitioningFactory;
import mil.jpeojtrs.sca.sad.FindComponent;
import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.sad.SadComponentInstantiationRef;
import mil.jpeojtrs.sca.sad.SadComponentPlacement;
import mil.jpeojtrs.sca.sad.SadConnectInterface;
import mil.jpeojtrs.sca.sad.SadFactory;
import mil.jpeojtrs.sca.sad.SadProvidesPort;
import mil.jpeojtrs.sca.sad.SadUsesPort;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.spd.SoftPkg;
import mil.jpeojtrs.sca.util.ProtectedThreadExecutor;

/**
 * Uses the REDHAWK SCA model to build a corresponding SAD
 */
public class GraphitiSADModelMapInitializerCommand extends AbstractCommand {

	private final GraphitiSADModelMap modelMap;
	private final ScaWaveform waveform;
	private final SoftwareAssembly sad;

	public GraphitiSADModelMapInitializerCommand(final GraphitiSADModelMap map, final SoftwareAssembly sad, final ScaWaveform waveform) {
		this.modelMap = map;
		this.waveform = waveform;
		this.sad = sad;
	}

	private void initConnection(final ScaConnection con) {
		final ScaUsesPort uses = con.getPort();
		final ScaPortContainer container = uses.getPortContainer();
		if (!(container instanceof ScaComponent)) {
			// Can only add connections from components' ports
			return;
		}
		final ScaComponent comp = (ScaComponent) container;

		// Initialize the connection ID and the USES side of the connection
		final SadConnectInterface sadCon = SadFactory.eINSTANCE.createSadConnectInterface();
		sadCon.setId(con.getId());
		initConnectionSource(sadCon, comp, uses);
		initConnectionTarget(sadCon, uses, con.getData().port);
		if (sadCon.getProvidesPort() == null && sadCon.getComponentSupportedInterface() == null) {
			// We were unable to find the target side of the connection; ignore it
			return;
		}

		if (sad.getConnections() == null) {
			sad.setConnections(SadFactory.eINSTANCE.createSadConnections());
		}
		sad.getConnections().getConnectInterface().add(sadCon);
		modelMap.put(con, sadCon);
	}

	private void initConnectionSource(SadConnectInterface sadCon, ScaComponent sourceComponent, ScaUsesPort sourcePort) {
		final SadUsesPort usesPort = SadFactory.eINSTANCE.createSadUsesPort();
		final SadComponentInstantiationRef usesCompRef = SadFactory.eINSTANCE.createSadComponentInstantiationRef();
		usesCompRef.setInstantiation(modelMap.getComponentInstantiation(sourceComponent));
		usesPort.setComponentInstantiationRef(usesCompRef);
		usesPort.setUsesIdentifier(sourcePort.getName());
		sadCon.setUsesPort(usesPort);
	}

	private void initConnectionTarget(SadConnectInterface sadCon, ScaUsesPort scaUsesPort, org.omg.CORBA.Object targetPortObject) {
		// Iterate port containers looking for a provides ports which may match
		for (final ScaComponent component : this.waveform.getComponents()) {
			for (final ScaPort< ? , ? > port : component.getPorts()) {
				if (port instanceof ScaProvidesPort && is_equivalent(targetPortObject, port.getCorbaObj())) {
					final SadProvidesPort sadProvidesPort = SadFactory.eINSTANCE.createSadProvidesPort();
					final SadComponentInstantiationRef ref = SadFactory.eINSTANCE.createSadComponentInstantiationRef();
					ref.setInstantiation(modelMap.getComponentInstantiation(component));
					sadProvidesPort.setComponentInstantiationRef(ref);
					sadProvidesPort.setProvidesIdentifier(port.getName());
					sadCon.setProvidesPort(sadProvidesPort);
					return;
				}
			}
		}

		// Iterate anything that could be a component supported interface looking for a match
		for (final ScaComponent component : this.waveform.getComponents()) {
			if (is_equivalent(targetPortObject, component.getCorbaObj())) {
				final ComponentSupportedInterface csi = PartitioningFactory.eINSTANCE.createComponentSupportedInterface();
				final SadComponentInstantiationRef ref = SadFactory.eINSTANCE.createSadComponentInstantiationRef();
				ref.setInstantiation(modelMap.getComponentInstantiation(component));
				csi.setComponentInstantiationRef(ref);
				csi.setSupportedIdentifier(scaUsesPort.getProfileObj().getRepID());
				sadCon.setComponentSupportedInterface(csi);
				return;
			}
		}
	}

	private static boolean is_equivalent(final org.omg.CORBA.Object obj1, final org.omg.CORBA.Object obj2) {
		if (obj1 == null || obj2 == null) {
			return false;
		}
		if (obj1 == obj2) {
			return true;
		}
		try {
			return ProtectedThreadExecutor.submit(new Callable<Boolean>() {

				@Override
				public Boolean call() throws Exception {
					return obj1._is_equivalent(obj2);
				}

			});
		} catch (InterruptedException e) {
			return false;
		} catch (ExecutionException e) {
			return false;
		} catch (TimeoutException e) {
			return false;
		}

	}

	/**
	 * Adds the XML to the SAD for a {@link ScaDevice}.
	 * @param device
	 */
	private void initComponent(final ScaComponent comp) {
		final SoftPkg spd = comp.getProfileObj();
		if (spd == null) {
			IStatus status = new Status(IStatus.ERROR, GraphitiSadUIPlugin.PLUGIN_ID, "Failed to find SPD for component: " + comp.getInstantiationIdentifier());
			Platform.getLog(Platform.getBundle(GraphitiSadUIPlugin.PLUGIN_ID)).log(status);
			return;
		}

		SadComponentInstantiation inst = createComponentInstatitation(spd, comp.getInstantiationIdentifier(), comp.getName(), comp.getName());
		modelMap.put(comp, inst);
	}

	/**
	 * Create a new component instantiation
	 * @param spd
	 * @param id
	 * @param usageName
	 * @param namingServiceName
	 * @return
	 */
	private SadComponentInstantiation createComponentInstatitation(SoftPkg spd, String id, String usageName, String namingServiceName) {
		// Find an existing component file declaration, or create a new one
		ComponentFile spdFile = null;
		for (final ComponentFile file : sad.getComponentFiles().getComponentFile()) {
			if (file.getSoftPkg() != null) {
				if (PluginUtil.equals(file.getSoftPkg().getId(), spd.getId())) {
					spdFile = file;
					break;
				}
			}
		}
		if (spdFile == null) {
			spdFile = SadFactory.eINSTANCE.createComponentFile();
			spdFile.setSoftPkg(spd);
			sad.getComponentFiles().getComponentFile().add(spdFile);
		}

		// Create the component placement and add to its parent, the partitioning
		final SadComponentPlacement placement = SadFactory.eINSTANCE.createSadComponentPlacement();
		sad.getPartitioning().getComponentPlacement().add(placement);

		// Create a component file ref
		final ComponentFileRef fileRef = PartitioningFactory.eINSTANCE.createComponentFileRef();
		fileRef.setFile(spdFile);

		// Create a component instantiation
		final SadComponentInstantiation inst = SadFactory.eINSTANCE.createSadComponentInstantiation();
		inst.setId(id);
		inst.setUsageName(usageName);
		final FindComponent findComponent = SadFactory.eINSTANCE.createFindComponent();
		final NamingService namingService = PartitioningFactory.eINSTANCE.createNamingService();
		namingService.setName(namingServiceName);
		findComponent.setNamingService(namingService);
		inst.setFindComponent(findComponent);

		// Add the component file ref and then the component instantiation to their parent the component placement.
		// IMPORTANT: The component instantiation is added to its parent LAST, because it must be able to navigate the
		// XML all the way to the SCD file so it can populate the port stubs. See code paths to
		// ComponentInstantiationImpl#refreshPorts().
		placement.setComponentFileRef(fileRef);
		placement.getComponentInstantiation().add(inst);

		return inst;
	}

	@Override
	public void execute() {
		this.sad.setComponentFiles(PartitioningFactory.eINSTANCE.createComponentFiles());
		this.sad.setPartitioning(SadFactory.eINSTANCE.createSadPartitioning());
		this.sad.setConnections(SadFactory.eINSTANCE.createSadConnections());
		this.sad.setAssemblyController(null);
		this.sad.setExternalPorts(null);
		if (waveform != null) {
			for (final ScaComponent comp : this.waveform.getComponents()) {
				initComponent(comp);
			}

			for (final ScaComponent comp : this.waveform.getComponents()) {
				for (final ScaPort< ? , ? > port : comp.getPorts()) {
					if (port instanceof ScaUsesPort) {
						final ScaUsesPort uses = (ScaUsesPort) port;
						for (final ScaConnection con : uses.getConnections()) {
							if (con != null) {
								initConnection(con);
							}
						}
					}
				}
			}
		}
	}

	@Override
	protected boolean prepare() {
		return true;
	}

	@Override
	public boolean canUndo() {
		return false;
	}

	@Override
	public void redo() {
		throw new UnsupportedOperationException();

	}
}

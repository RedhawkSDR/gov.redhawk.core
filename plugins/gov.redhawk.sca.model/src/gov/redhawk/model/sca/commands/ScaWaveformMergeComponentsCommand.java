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
package gov.redhawk.model.sca.commands;

import gov.redhawk.model.sca.ScaComponent;
import gov.redhawk.model.sca.ScaFactory;
import gov.redhawk.model.sca.ScaPackage;
import gov.redhawk.model.sca.ScaWaveform;

import java.util.HashMap;
import java.util.Map;

import mil.jpeojtrs.sca.sad.HostCollocation;
import mil.jpeojtrs.sca.sad.SadComponentInstantiation;
import mil.jpeojtrs.sca.sad.SadComponentPlacement;
import mil.jpeojtrs.sca.sad.SadPartitioning;
import mil.jpeojtrs.sca.sad.SoftwareAssembly;
import mil.jpeojtrs.sca.util.ScaUriHelpers;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import CF.ComponentType;
import CF.Resource;
import CF.ResourceHelper;

/**
 * @since 14.0
 * 
 */
public class ScaWaveformMergeComponentsCommand extends SetStatusCommand<ScaWaveform> {

	private static class ComponentInfo {
		public String identifier = null; // SUPPRESS CHECKSTYLE Inner Class
		public String softwareProfile = null; // SUPPRESS CHECKSTYLE Inner Class
		public CF.ComponentEnumType type = null; // SUPPRESS CHECKSTYLE Inner Class
		public Resource componentObject = null; // SUPPRESS CHECKSTYLE Inner Class
		public CF.PortType [] providesPorts = null; // SUPPRESS CHECKSTYLE Inner Class
	}

	private final ComponentInfo[] compTypes;

	/**
	 * 
	 * @param provider
	 * @param assemblyControlerId
	 * @param compTypes
	 * @param componentStatus
	 * @deprecated Use {@link #ScaWaveformMergeComponentsCommand(ScaWaveform, ComponentType[], IStatus)}
	 */
	@Deprecated
	public ScaWaveformMergeComponentsCommand(ScaWaveform provider, String assemblyControlerId, ComponentType[] compTypes, IStatus componentStatus) {
		this(provider, compTypes, componentStatus);
	}

	/**
	 * @since 15.0
	 */
	public ScaWaveformMergeComponentsCommand(ScaWaveform provider, ComponentType[] compTypes, IStatus componentStatus) {
		super(provider, ScaPackage.Literals.SCA_WAVEFORM__COMPONENTS, componentStatus);
		if (compTypes == null) {
			compTypes = new ComponentType[0];
		}
		this.compTypes = new ComponentInfo[compTypes.length];
		for (int i = 0; i < compTypes.length; i++) {
			this.compTypes[i] = new ComponentInfo();
			this.compTypes[i].identifier = compTypes[i].identifier;
			this.compTypes[i].softwareProfile = compTypes[i].softwareProfile;
			this.compTypes[i].type = compTypes[i].type;
			this.compTypes[i].componentObject = ResourceHelper.narrow(compTypes[i].componentObject);
			this.compTypes[i].providesPorts = compTypes[i].providesPorts;
		}
	}

	@Override
	protected boolean prepare() {
		return super.prepare();
	}

	@Override
	public void execute() {
		if (status.isOK()) {
			final Map<String, ComponentInfo> newComponentsMap = new HashMap<String, ComponentInfo>();
			if (compTypes != null) {
				for (final ComponentInfo component : compTypes) {
					if (component == null) {
						continue;
					}
					newComponentsMap.put(component.componentObject.toString(), component);
				}
			}

			// Current component Map
			final Map<String, ScaComponent> currentComponents = new HashMap<String, ScaComponent>();
			for (final ScaComponent component : provider.getComponents()) {
				String componentId = component.getIor();
				currentComponents.put(componentId, component);
			}

			// Components to remove
			final Map<String, ScaComponent> removeComponents = new HashMap<String, ScaComponent>();
			removeComponents.putAll(currentComponents);
			removeComponents.keySet().removeAll(newComponentsMap.keySet());

			// Remove components
			if (!removeComponents.isEmpty() && !provider.getComponents().isEmpty()) {
				provider.getComponents().removeAll(removeComponents.values());
			}

			// Remove duplicates
			newComponentsMap.keySet().removeAll(currentComponents.keySet());

			URI profileURI = provider.getProfileURI();
			for (final ComponentInfo typeInfo : newComponentsMap.values()) {
				URI spdUri = ScaUriHelpers.createFileSystemURI(typeInfo.softwareProfile, profileURI, null);
				final ScaComponent component = createComponent();
				component.setCorbaObj(typeInfo.componentObject);
				component.setObj(typeInfo.componentObject);
				component.setProfileURI(spdUri);
				component.setIdentifier(typeInfo.identifier);
				provider.getComponents().add(component);
				String ciId = component.getInstantiationIdentifier();
				if (ciId != null) {
					for (TreeIterator<EObject> iterator = EcoreUtil.getAllContents(provider.getProfileObj(), false); iterator.hasNext();) {
						EObject obj = iterator.next();
						if (obj instanceof SoftwareAssembly || obj instanceof SadPartitioning || obj instanceof SadComponentPlacement || obj instanceof HostCollocation) {
							continue;
						} else if (obj instanceof SadComponentInstantiation) {
							SadComponentInstantiation ci = (SadComponentInstantiation) obj;
							String sadCiId = ci.getId();
							if (ciId.equals(sadCiId)) {
								component.setComponentInstantiation(ci);
								break;
							}
							iterator.prune();
						} else {
							iterator.prune();
						}
					}
				}
			}

			if (!provider.isSetComponents()) {
				provider.getComponents().clear();
			}
		} else {
			provider.unsetComponents();
			provider.unsetAssemblyController();
		}
		super.execute();
	}

	protected ScaComponent createComponent() {
		return ScaFactory.eINSTANCE.createScaComponent();
	}

}

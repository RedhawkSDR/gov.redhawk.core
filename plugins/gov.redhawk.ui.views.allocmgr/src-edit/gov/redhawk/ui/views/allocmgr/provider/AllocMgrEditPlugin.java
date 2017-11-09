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
// BEGIN GENERATED CODE
package gov.redhawk.ui.views.allocmgr.provider;

import gov.redhawk.model.sca.provider.ScaEditPlugin;
import mil.jpeojtrs.sca.cf.provider.CfEditPlugin;

import mil.jpeojtrs.sca.dcd.provider.DcdEditPlugin;
import mil.jpeojtrs.sca.dmd.provider.DmdEditPlugin;
import mil.jpeojtrs.sca.dpd.provider.DpdEditPlugin;
import mil.jpeojtrs.sca.partitioning.provider.PartitioningEditPlugin;
import mil.jpeojtrs.sca.prf.provider.PrfEditPlugin;
import mil.jpeojtrs.sca.sad.provider.SadEditPlugin;
import mil.jpeojtrs.sca.scd.provider.ScdEditPlugin;
import mil.jpeojtrs.sca.spd.provider.SpdEditPlugin;
import org.eclipse.emf.common.EMFPlugin;

import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.provider.EcoreEditPlugin;

/**
 * This is the central singleton for the AllocMgr edit plugin.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public final class AllocMgrEditPlugin extends EMFPlugin {
	/**
	 * Keep track of the singleton.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final AllocMgrEditPlugin INSTANCE = new AllocMgrEditPlugin();

	/**
	 * Keep track of the singleton.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static Implementation plugin;

	/**
	 * Create the instance.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AllocMgrEditPlugin() {
		super(new ResourceLocator[] { ScaEditPlugin.INSTANCE, CfEditPlugin.INSTANCE, SpdEditPlugin.INSTANCE, PrfEditPlugin.INSTANCE, SadEditPlugin.INSTANCE,
			DcdEditPlugin.INSTANCE, ScdEditPlugin.INSTANCE, DmdEditPlugin.INSTANCE, EcoreEditPlugin.INSTANCE, PartitioningEditPlugin.INSTANCE,
			DpdEditPlugin.INSTANCE, });
	}

	/**
	 * Returns the singleton instance of the Eclipse plugin.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the singleton instance.
	 * @generated
	 */
	@Override
	public ResourceLocator getPluginResourceLocator() {
		return plugin;
	}

	/**
	 * Returns the singleton instance of the Eclipse plugin.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the singleton instance.
	 * @generated
	 */
	public static Implementation getPlugin() {
		return plugin;
	}

	/**
	 * The actual implementation of the Eclipse <b>Plugin</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static class Implementation extends EclipsePlugin {
		/**
		 * Creates an instance.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public Implementation() {
			super();

			// Remember the static instance.
			//
			plugin = this;
		}
	}

}
